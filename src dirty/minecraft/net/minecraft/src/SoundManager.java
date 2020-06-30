package net.minecraft.src;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.codecs.CodecWav;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

public class SoundManager
{
    /** A reference to the sound system. */
    private static SoundSystem sndSystem;

    /** Sound pool containing sounds. */
    private SoundPool soundPoolSounds = new SoundPool();

    /** Sound pool containing streaming audio. */
    private SoundPool soundPoolStreaming = new SoundPool();

    /** Sound pool containing music. */
    private SoundPool soundPoolMusic = new SoundPool();

    /**
     * The last ID used when a sound is played, passed into SoundSystem to give active sounds a unique ID
     */
    private int latestSoundID = 0;

    /** A reference to the game settings. */
    private GameSettings options;

    /** Identifiers of all currently playing sounds. Type: HashSet<String> */
    private Set playingSounds = new HashSet();
    private List field_92072_h = new ArrayList();

    /** Set to true when the SoundManager has been initialised. */
    private static boolean loaded = false;

    /** RNG. */
    private Random rand = new Random();
    private int ticksBeforeMusic;
    private Map m_Tracked3DSoundsHashMap = new HashMap();

    public SoundManager()
    {
        this.ticksBeforeMusic = this.rand.nextInt(12000);
    }

    /**
     * Used for loading sound settings from GameSettings
     */
    public void loadSoundSettings(GameSettings par1GameSettings)
    {
        this.soundPoolStreaming.isGetRandomSound = false;
        this.options = par1GameSettings;

        if (!loaded && (par1GameSettings == null || par1GameSettings.soundVolume != 0.0F || par1GameSettings.musicVolume != 0.0F))
        {
            this.tryToSetLibraryAndCodecs();
        }
    }

    /**
     * Tries to add the paulscode library and the relevant codecs. If it fails, the volumes (sound and music) will be
     * set to zero in the options file.
     */
    private void tryToSetLibraryAndCodecs()
    {
        try
        {
            float var1 = this.options.soundVolume;
            float var2 = this.options.musicVolume;
            this.options.soundVolume = 0.0F;
            this.options.musicVolume = 0.0F;
            this.options.saveOptions();
            SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
            SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
            SoundSystemConfig.setCodec("mus", CodecMus.class);
            SoundSystemConfig.setCodec("wav", CodecWav.class);
            sndSystem = new SoundSystem();
            this.options.soundVolume = var1;
            this.options.musicVolume = var2;
            this.options.saveOptions();
        }
        catch (Throwable var3)
        {
            var3.printStackTrace();
            System.err.println("error linking with the LibraryJavaSound plug-in");
        }

        loaded = true;
    }

    /**
     * Called when one of the sound level options has changed.
     */
    public void onSoundOptionsChanged()
    {
        if (!loaded && (this.options.soundVolume != 0.0F || this.options.musicVolume != 0.0F))
        {
            this.tryToSetLibraryAndCodecs();
        }

        if (loaded)
        {
            if (this.options.musicVolume == 0.0F)
            {
                sndSystem.stop("BgMusic");
                sndSystem.stop("streaming");
            }
            else
            {
                sndSystem.setVolume("BgMusic", this.options.musicVolume);
                sndSystem.setVolume("streaming", this.options.musicVolume);
            }
        }
    }

    /**
     * Called when Minecraft is closing down.
     */
    public void closeMinecraft()
    {
        if (loaded)
        {
            sndSystem.cleanup();
        }
    }

    /**
     * Adds a sounds with the name from the file. Args: name, file
     */
    public void addSound(String par1Str, File par2File)
    {
        this.soundPoolSounds.addSound(par1Str, par2File);
    }

    /**
     * Adds an audio file to the streaming SoundPool.
     */
    public void addStreaming(String par1Str, File par2File)
    {
        this.soundPoolStreaming.addSound(par1Str, par2File);
    }

    /**
     * Adds an audio file to the music SoundPool.
     */
    public void addMusic(String par1Str, File par2File)
    {
        this.soundPoolMusic.addSound(par1Str, par2File);
    }

    /**
     * If its time to play new music it starts it up.
     */
    public void playRandomMusicIfReady()
    {
        if (loaded && this.options.musicVolume != 0.0F && !sndSystem.playing("BgMusic") && !sndSystem.playing("streaming"))
        {
            if (this.ticksBeforeMusic > 0)
            {
                --this.ticksBeforeMusic;
                return;
            }

            SoundPoolEntry var1 = this.soundPoolMusic.getRandomSound();

            if (var1 != null)
            {
                this.ticksBeforeMusic = this.rand.nextInt(12000) + 12000;
                sndSystem.backgroundMusic("BgMusic", var1.soundUrl, var1.soundName, false);
                sndSystem.setVolume("BgMusic", this.options.musicVolume);
                sndSystem.play("BgMusic");
            }
        }
    }

    /**
     * Sets the listener of sounds
     */
    public void setListener(EntityLiving par1EntityLiving, float par2)
    {
        if (loaded && this.options.soundVolume != 0.0F && par1EntityLiving != null)
        {
            float var3 = par1EntityLiving.prevRotationPitch + (par1EntityLiving.rotationPitch - par1EntityLiving.prevRotationPitch) * par2;
            float var4 = par1EntityLiving.prevRotationYaw + (par1EntityLiving.rotationYaw - par1EntityLiving.prevRotationYaw) * par2;
            double var5 = par1EntityLiving.prevPosX + (par1EntityLiving.posX - par1EntityLiving.prevPosX) * (double)par2;
            double var7 = par1EntityLiving.prevPosY + (par1EntityLiving.posY - par1EntityLiving.prevPosY) * (double)par2;
            double var9 = par1EntityLiving.prevPosZ + (par1EntityLiving.posZ - par1EntityLiving.prevPosZ) * (double)par2;
            float var11 = MathHelper.cos(-var4 * 0.017453292F - (float)Math.PI);
            float var12 = MathHelper.sin(-var4 * 0.017453292F - (float)Math.PI);
            float var13 = -var12;
            float var14 = -MathHelper.sin(-var3 * 0.017453292F - (float)Math.PI);
            float var15 = -var11;
            float var16 = 0.0F;
            float var17 = 1.0F;
            float var18 = 0.0F;
            this.Update3DSoundTrackerForNewPosition((float)var5, (float)var7, (float)var9);
            sndSystem.setListenerPosition((float)var5, (float)var7, (float)var9);
            sndSystem.setListenerOrientation(var13, var14, var15, var16, var17, var18);
        }
    }

    /**
     * Stops all currently playing sounds
     */
    public void stopAllSounds()
    {
        Iterator var1 = this.playingSounds.iterator();

        while (var1.hasNext())
        {
            String var2 = (String)var1.next();
            sndSystem.stop(var2);
        }

        this.playingSounds.clear();
    }

    public void playStreaming(String par1Str, float par2, float par3, float par4)
    {
        if (loaded && (this.options.soundVolume != 0.0F || par1Str == null))
        {
            String var5 = "streaming";

            if (sndSystem.playing(var5))
            {
                sndSystem.stop(var5);
            }

            if (par1Str != null)
            {
                SoundPoolEntry var6 = this.soundPoolStreaming.getRandomSoundFromSoundPool(par1Str);

                if (var6 != null)
                {
                    if (sndSystem.playing("BgMusic"))
                    {
                        sndSystem.stop("BgMusic");
                    }

                    float var7 = 16.0F;
                    sndSystem.newStreamingSource(true, var5, var6.soundUrl, var6.soundName, false, par2, par3, par4, 2, var7 * 4.0F);
                    sndSystem.setVolume(var5, 0.5F * this.options.soundVolume);
                    sndSystem.play(var5);
                }
            }
        }
    }

    /**
     * Updates the sound associated with the entity with that entity's position and velocity. Args: the entity
     */
    public void updateSoundLocation(Entity par1Entity)
    {
        this.updateSoundLocation(par1Entity, par1Entity);
    }

    /**
     * Updates the sound associated with soundEntity with the position and velocity of trackEntity. Args: soundEntity,
     * trackEntity
     */
    public void updateSoundLocation(Entity par1Entity, Entity par2Entity)
    {
        String var3 = "entity_" + par1Entity.entityId;

        if (this.playingSounds.contains(var3))
        {
            if (sndSystem.playing(var3))
            {
                sndSystem.setPosition(var3, (float)par2Entity.posX, (float)par2Entity.posY, (float)par2Entity.posZ);
                sndSystem.setVelocity(var3, (float)par2Entity.motionX, (float)par2Entity.motionY, (float)par2Entity.motionZ);
            }
            else
            {
                this.playingSounds.remove(var3);
            }
        }
    }

    /**
     * Returns true if a sound is currently associated with the given entity, or false otherwise.
     */
    public boolean isEntitySoundPlaying(Entity par1Entity)
    {
        if (par1Entity != null && loaded)
        {
            String var2 = "entity_" + par1Entity.entityId;
            return sndSystem.playing(var2);
        }
        else
        {
            return false;
        }
    }

    /**
     * Stops playing the sound associated with the given entity
     */
    public void stopEntitySound(Entity par1Entity)
    {
        if (par1Entity != null && loaded)
        {
            String var2 = "entity_" + par1Entity.entityId;

            if (this.playingSounds.contains(var2))
            {
                if (sndSystem.playing(var2))
                {
                    sndSystem.stop(var2);
                }

                this.playingSounds.remove(var2);
            }
        }
    }

    /**
     * Sets the volume of the sound associated with the given entity, if one is playing. The volume is scaled by the
     * global sound volume. Args: the entity, the volume (from 0 to 1)
     */
    public void setEntitySoundVolume(Entity par1Entity, float par2)
    {
        if (par1Entity != null && loaded && loaded && this.options.soundVolume != 0.0F)
        {
            String var3 = "entity_" + par1Entity.entityId;

            if (sndSystem.playing(var3))
            {
                sndSystem.setVolume(var3, par2 * this.options.soundVolume);
            }
        }
    }

    /**
     * Sets the pitch of the sound associated with the given entity, if one is playing. Args: the entity, the pitch
     */
    public void setEntitySoundPitch(Entity par1Entity, float par2)
    {
        if (par1Entity != null && loaded && loaded && this.options.soundVolume != 0.0F)
        {
            String var3 = "entity_" + par1Entity.entityId;

            if (sndSystem.playing(var3))
            {
                sndSystem.setPitch(var3, par2);
            }
        }
    }

    /**
     * If a sound is already playing from the given entity, update the position and velocity of that sound to match the
     * entity. Otherwise, start playing a sound from that entity. Setting the last flag to true will prevent other
     * sounds from overriding this one. Args: The sound name, the entity, the volume, the pitch, priority
     */
    public void playEntitySound(String par1Str, Entity par2Entity, float par3, float par4, boolean par5)
    {
        if (par2Entity != null && loaded && (this.options.soundVolume != 0.0F || par1Str == null))
        {
            String var6 = "entity_" + par2Entity.entityId;

            if (this.playingSounds.contains(var6))
            {
                this.updateSoundLocation(par2Entity);
            }
            else
            {
                if (sndSystem.playing(var6))
                {
                    sndSystem.stop(var6);
                }

                if (par1Str == null)
                {
                    return;
                }

                SoundPoolEntry var7 = this.soundPoolSounds.getRandomSoundFromSoundPool(par1Str);

                if (var7 != null && par3 > 0.0F)
                {
                    float var8 = 16.0F;

                    if (par3 > 1.0F)
                    {
                        var8 *= par3;
                    }

                    sndSystem.newSource(par5, var6, var7.soundUrl, var7.soundName, false, (float)par2Entity.posX, (float)par2Entity.posY, (float)par2Entity.posZ, 2, var8);
                    sndSystem.setLooping(var6, true);
                    sndSystem.setPitch(var6, par4);

                    if (par3 > 1.0F)
                    {
                        par3 = 1.0F;
                    }

                    sndSystem.setVolume(var6, par3 * this.options.soundVolume);
                    sndSystem.setVelocity(var6, (float)par2Entity.motionX, (float)par2Entity.motionY, (float)par2Entity.motionZ);
                    sndSystem.play(var6);
                    this.playingSounds.add(var6);
                }
            }
        }
    }

    /**
     * Plays a sound. Args: soundName, x, y, z, volume, pitch
     */
    public void playSound(String par1Str, float par2, float par3, float par4, float par5, float par6)
    {
        if (loaded && this.options.soundVolume != 0.0F)
        {
            SoundPoolEntry var7 = this.soundPoolSounds.getRandomSoundFromSoundPool(par1Str);

            if (var7 != null && par5 > 0.0F)
            {
                this.latestSoundID = (this.latestSoundID + 1) % 256;
                String var8 = "sound_" + this.latestSoundID;
                float var9 = 16.0F;

                if (par5 > 1.0F)
                {
                    var9 *= par5;
                }

                sndSystem.newSource(par5 > 1.0F, var8, var7.soundUrl, var7.soundName, false, par2, par3, par4, 2, var9);
                sndSystem.setPitch(var8, par6);

                if (par5 > 1.0F)
                {
                    par5 = 1.0F;
                }

                sndSystem.setVolume(var8, par5 * this.options.soundVolume);
                sndSystem.play(var8);
                this.Add3DSoundToTracker(var8, par2, par3, par4, var9);
            }
        }
    }

    /**
     * Plays a sound effect with the volume and pitch of the parameters passed. The sound isn't affected by position of
     * the player (full volume and center balanced)
     */
    public void playSoundFX(String par1Str, float par2, float par3)
    {
        if (loaded && this.options.soundVolume != 0.0F)
        {
            SoundPoolEntry var4 = this.soundPoolSounds.getRandomSoundFromSoundPool(par1Str);

            if (var4 != null)
            {
                this.latestSoundID = (this.latestSoundID + 1) % 256;
                String var5 = "sound_" + this.latestSoundID;
                sndSystem.newSource(false, var5, var4.soundUrl, var4.soundName, false, 0.0F, 0.0F, 0.0F, 0, 0.0F);

                if (par2 > 1.0F)
                {
                    par2 = 1.0F;
                }

                par2 *= 0.25F;
                sndSystem.setPitch(var5, par3);
                sndSystem.setVolume(var5, par2 * this.options.soundVolume);
                sndSystem.play(var5);
            }
        }
    }

    /**
     * Pauses all currently playing sounds
     */
    public void pauseAllSounds()
    {
        Iterator var1 = this.playingSounds.iterator();

        while (var1.hasNext())
        {
            String var2 = (String)var1.next();
            sndSystem.pause(var2);
        }
    }

    /**
     * Resumes playing all currently playing sounds (after pauseAllSounds)
     */
    public void resumeAllSounds()
    {
        Iterator var1 = this.playingSounds.iterator();

        while (var1.hasNext())
        {
            String var2 = (String)var1.next();
            sndSystem.play(var2);
        }
    }

    public void func_92071_g()
    {
        if (!this.field_92072_h.isEmpty())
        {
            Iterator var1 = this.field_92072_h.iterator();

            while (var1.hasNext())
            {
                ScheduledSound var2 = (ScheduledSound)var1.next();
                --var2.field_92064_g;

                if (var2.field_92064_g <= 0)
                {
                    this.playSound(var2.field_92069_a, var2.field_92067_b, var2.field_92068_c, var2.field_92065_d, var2.field_92066_e, var2.field_92063_f);
                    var1.remove();
                }
            }
        }
    }

    public void func_92070_a(String par1Str, float par2, float par3, float par4, float par5, float par6, int par7)
    {
        this.field_92072_h.add(new ScheduledSound(par1Str, par2, par3, par4, par5, par6, par7));
    }

    private void Add3DSoundToTracker(String var1, float var2, float var3, float var4, float var5)
    {
        this.m_Tracked3DSoundsHashMap.put(var1, new FCClientSoundTrackerEntry(var1, var2, var3, var4, var5));
    }

    private void Update3DSoundTrackerForNewPosition(float var1, float var2, float var3)
    {
        Iterator var4 = this.m_Tracked3DSoundsHashMap.values().iterator();

        while (var4.hasNext())
        {
            FCClientSoundTrackerEntry var5 = (FCClientSoundTrackerEntry)var4.next();

            if (sndSystem.playing(var5.m_sName))
            {
                float var6 = var1 - var5.m_fXPos;
                float var7 = var2 - var5.m_fYPos;
                float var8 = var3 - var5.m_fZPos;
                float var9 = var6 * var6 + var7 * var7 + var8 * var8;

                if (var9 >= var5.m_fMaxRangeSq)
                {
                    sndSystem.stop(var5.m_sName);
                    var4.remove();
                }
            }
            else
            {
                var4.remove();
            }
        }
    }
}
