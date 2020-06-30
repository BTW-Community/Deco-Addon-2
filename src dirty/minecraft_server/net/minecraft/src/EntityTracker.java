package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EntityTracker
{
    private final WorldServer theWorld;

    /**
     * List of tracked entities, used for iteration operations on tracked entities.
     */
    private Set trackedEntities = new HashSet();

    /** Used for identity lookup of tracked entities. */
    private IntHashMap trackedEntityHashTable = new IntHashMap();
    private int maxTrackingDistanceThreshold;

    public EntityTracker(WorldServer par1WorldServer)
    {
        this.theWorld = par1WorldServer;
        this.maxTrackingDistanceThreshold = par1WorldServer.getMinecraftServer().getConfigurationManager().getEntityViewDistance();
    }

    public void trackEntity(Entity par1Entity)
    {
        if (par1Entity instanceof EntityPlayerMP)
        {
            this.trackEntity(par1Entity, 512, 2);
            EntityPlayerMP var2 = (EntityPlayerMP)par1Entity;
            Iterator var3 = this.trackedEntities.iterator();

            while (var3.hasNext())
            {
                EntityTrackerEntry var4 = (EntityTrackerEntry)var3.next();

                if (var4.trackedEntity != var2)
                {
                    var4.updatePlayerEntity(var2);
                }
            }
        }
        else if (par1Entity instanceof FCIEntityPacketHandler)
        {
            FCIEntityPacketHandler var5 = (FCIEntityPacketHandler)par1Entity;
            this.trackEntity(par1Entity, var5.GetTrackerViewDistance(), var5.GetTrackerUpdateFrequency(), var5.GetTrackMotion());
        }
        else if (par1Entity instanceof EntityFishHook)
        {
            this.trackEntity(par1Entity, 64, 5, true);
        }
        else if (par1Entity instanceof EntityArrow)
        {
            this.trackEntity(par1Entity, 64, 20, false);
        }
        else if (par1Entity instanceof EntitySmallFireball)
        {
            this.trackEntity(par1Entity, 64, 10, false);
        }
        else if (par1Entity instanceof EntityFireball)
        {
            this.trackEntity(par1Entity, 64, 10, false);
        }
        else if (par1Entity instanceof EntitySnowball)
        {
            this.trackEntity(par1Entity, 64, 10, true);
        }
        else if (par1Entity instanceof EntityEnderPearl)
        {
            this.trackEntity(par1Entity, 64, 10, true);
        }
        else if (par1Entity instanceof EntityEnderEye)
        {
            this.trackEntity(par1Entity, 64, 4, true);
        }
        else if (par1Entity instanceof EntityEgg)
        {
            this.trackEntity(par1Entity, 64, 10, true);
        }
        else if (par1Entity instanceof EntityPotion)
        {
            this.trackEntity(par1Entity, 64, 10, true);
        }
        else if (par1Entity instanceof EntityExpBottle)
        {
            this.trackEntity(par1Entity, 64, 10, true);
        }
        else if (par1Entity instanceof EntityFireworkRocket)
        {
            this.trackEntity(par1Entity, 64, 10, true);
        }
        else if (par1Entity instanceof EntityItem)
        {
            this.trackEntity(par1Entity, 64, 20, true);
        }
        else if (par1Entity instanceof EntityMinecart)
        {
            this.trackEntity(par1Entity, 80, 3, true);
        }
        else if (par1Entity instanceof EntityBoat)
        {
            this.trackEntity(par1Entity, 80, 3, true);
        }
        else if (par1Entity instanceof FCEntitySquid)
        {
            this.trackEntity(par1Entity, 64, 3, true);
        }
        else if (par1Entity instanceof FCEntityWither)
        {
            this.trackEntity(par1Entity, 80, 3, false);
        }
        else if (par1Entity instanceof FCEntityBat)
        {
            this.trackEntity(par1Entity, 80, 3, false);
        }
        else if (par1Entity instanceof IAnimals)
        {
            this.trackEntity(par1Entity, 80, 3, true);
        }
        else if (par1Entity instanceof EntityDragon)
        {
            this.trackEntity(par1Entity, 160, 3, true);
        }
        else if (par1Entity instanceof EntityTNTPrimed)
        {
            this.trackEntity(par1Entity, 160, 10, true);
        }
        else if (par1Entity instanceof EntityFallingSand)
        {
            this.trackEntity(par1Entity, 160, 20, true);
        }
        else if (par1Entity instanceof EntityPainting)
        {
            this.trackEntity(par1Entity, 160, Integer.MAX_VALUE, false);
        }
        else if (par1Entity instanceof EntityXPOrb)
        {
            this.trackEntity(par1Entity, 160, 20, true);
        }
        else if (par1Entity instanceof EntityEnderCrystal)
        {
            this.trackEntity(par1Entity, 256, Integer.MAX_VALUE, false);
        }
        else if (par1Entity instanceof EntityItemFrame)
        {
            this.trackEntity(par1Entity, 160, Integer.MAX_VALUE, false);
        }
    }

    public void trackEntity(Entity par1Entity, int par2, int par3)
    {
        this.trackEntity(par1Entity, par2, par3, false);
    }

    public void trackEntity(Entity par1Entity, int par2, int par3, boolean par4)
    {
        if (par2 > this.maxTrackingDistanceThreshold)
        {
            par2 = this.maxTrackingDistanceThreshold;
        }

        try
        {
            if (this.trackedEntityHashTable.containsItem(par1Entity.entityId))
            {
                throw new IllegalStateException("Entity is already tracked!");
            }

            EntityTrackerEntry var5 = new EntityTrackerEntry(par1Entity, par2, par3, par4);
            this.trackedEntities.add(var5);
            this.trackedEntityHashTable.addKey(par1Entity.entityId, var5);
            var5.updatePlayerEntities(this.theWorld.playerEntities);
        }
        catch (Throwable var11)
        {
            CrashReport var6 = CrashReport.makeCrashReport(var11, "Adding entity to track");
            CrashReportCategory var7 = var6.makeCategory("Entity To Track");
            var7.addCrashSection("Tracking range", par2 + " blocks");
            var7.addCrashSectionCallable("Update interval", new CallableEntityTracker(this, par3));
            par1Entity.func_85029_a(var7);
            CrashReportCategory var8 = var6.makeCategory("Entity That Is Already Tracked");
            ((EntityTrackerEntry)this.trackedEntityHashTable.lookup(par1Entity.entityId)).trackedEntity.func_85029_a(var8);

            try
            {
                throw new ReportedException(var6);
            }
            catch (ReportedException var10)
            {
                System.err.println("\"Silently\" catching entity tracking error.");
                var10.printStackTrace();
            }
        }
    }

    public void untrackEntity(Entity par1Entity)
    {
        if (par1Entity instanceof EntityPlayerMP)
        {
            EntityPlayerMP var2 = (EntityPlayerMP)par1Entity;
            Iterator var3 = this.trackedEntities.iterator();

            while (var3.hasNext())
            {
                EntityTrackerEntry var4 = (EntityTrackerEntry)var3.next();
                var4.removeFromTrackedPlayers(var2);
            }
        }

        EntityTrackerEntry var5 = (EntityTrackerEntry)this.trackedEntityHashTable.removeObject(par1Entity.entityId);

        if (var5 != null)
        {
            this.trackedEntities.remove(var5);
            var5.sendDestroyEntityPacketToTrackedPlayers();
        }
    }

    public void updateTrackedEntities()
    {
        ArrayList var1 = new ArrayList();
        Iterator var2 = this.trackedEntities.iterator();

        while (var2.hasNext())
        {
            EntityTrackerEntry var3 = (EntityTrackerEntry)var2.next();
            var3.updatePlayerList(this.theWorld.playerEntities);

            if (var3.playerEntitiesUpdated && var3.trackedEntity instanceof EntityPlayerMP)
            {
                var1.add((EntityPlayerMP)var3.trackedEntity);
            }
        }

        for (int var7 = 0; var7 < var1.size(); ++var7)
        {
            EntityPlayerMP var4 = (EntityPlayerMP)var1.get(var7);
            Iterator var5 = this.trackedEntities.iterator();

            while (var5.hasNext())
            {
                EntityTrackerEntry var6 = (EntityTrackerEntry)var5.next();

                if (var6.trackedEntity != var4)
                {
                    var6.updatePlayerEntity(var4);
                }
            }
        }
    }

    public void sendPacketToTrackedPlayers(Entity par1Entity, Packet par2Packet)
    {
        EntityTrackerEntry var3 = (EntityTrackerEntry)this.trackedEntityHashTable.lookup(par1Entity.entityId);

        if (var3 != null)
        {
            var3.sendPacketToTrackedPlayers(par2Packet);
        }
    }

    public void sendPacketToTrackedPlayersAndTrackedEntity(Entity par1Entity, Packet par2Packet)
    {
        EntityTrackerEntry var3 = (EntityTrackerEntry)this.trackedEntityHashTable.lookup(par1Entity.entityId);

        if (var3 != null)
        {
            var3.sendPacketToTrackedPlayersAndTrackedEntity(par2Packet);
        }
    }

    public void removePlayerFromTrackers(EntityPlayerMP par1EntityPlayerMP)
    {
        Iterator var2 = this.trackedEntities.iterator();

        while (var2.hasNext())
        {
            EntityTrackerEntry var3 = (EntityTrackerEntry)var2.next();
            var3.removeTrackedPlayerSymmetric(par1EntityPlayerMP);
        }
    }

    public void func_85172_a(EntityPlayerMP par1EntityPlayerMP, Chunk par2Chunk)
    {
        Iterator var3 = this.trackedEntities.iterator();

        while (var3.hasNext())
        {
            EntityTrackerEntry var4 = (EntityTrackerEntry)var3.next();

            if (var4.trackedEntity != par1EntityPlayerMP && var4.trackedEntity.chunkCoordX == par2Chunk.xPosition && var4.trackedEntity.chunkCoordZ == par2Chunk.zPosition)
            {
                var4.updatePlayerEntity(par1EntityPlayerMP);
            }
        }
    }
}
