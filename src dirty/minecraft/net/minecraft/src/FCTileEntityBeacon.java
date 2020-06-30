package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class FCTileEntityBeacon extends TileEntityBeacon
{
    public static final double[] m_dRangePerLevel = new double[] {0.0D, 20.0D, 40.0D, 80.0D, 160.0D};
    private static final long m_iTicksUpdates = 80L;
    private static final int m_iEffectDuration = 180;
    private static final int m_iGlobalEffectsRandomUpdateFrequency = 6000;
    private static ArrayList[] m_beaconEffectsByBlockID = new ArrayList[4096];
    private static final int m_iSoulforgedBeaconBlightSpreadFrequency = 10000;
    public static final int[] m_iSoulforgedBeaconBlightSpreadRange = new int[] {0, 8, 16, 32, 64};
    public static final int[] m_iEnderAntennaBeaconSpawnRange = new int[] {0, 8, 16, 32, 64};
    private static final int m_iEnderAntennaBeaconBaseEnderManSpawnFrequency = 1200;
    public static final int[] m_iEnderAntennaBeaconEndermanSpawnChancePerLevel = new int[] {0, 1, 8, 27, 64};
    private static final int m_iEnderAntennaBeaconBaseSilverfishSpawnFrequency = 1200;
    public static final int[] m_iEnderAntennaBeaconSilverfishSpawnChancePerLevel = new int[] {0, 1, 8, 27, 64};
    private static Random rand = new Random();
    private long m_iUpdateOffset;
    public boolean m_bPlayerRespawnedAtBeacon = false;
    public static final int m_iEffectIDStrongMagneticField = 22222;
    public static final int m_iEffectIDSpawnPoint = 22223;
    public static final int m_iEffectIDDecorativeOnly = 22224;
    public static final int m_iEffectIDDungCloud = 22225;
    public static final int m_iEffectIDEnderChestAntenna = 22226;

    public FCTileEntityBeacon()
    {
        this.m_iUpdateOffset = (long)rand.nextInt(80);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if ((this.worldObj.getTotalWorldTime() + this.m_iUpdateOffset) % 80L == 0L)
        {
            this.UpdatePowerState();
            this.ApplyEffectToPlayersWithinRange();
        }

        if (!this.worldObj.isRemote)
        {
            if (rand.nextInt(6000) == 0)
            {
                this.ValidateGlobalEffects();
            }

            this.BeaconTypeSpecificServerSideUpdate();

            if (this.m_bPlayerRespawnedAtBeacon)
            {
                this.m_bPlayerRespawnedAtBeacon = false;
                this.worldObj.playAuxSFX(2225, this.xCoord, this.yCoord, this.zCoord, 1);
                this.worldObj.playSoundEffect((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "mob.wither.spawn", 1.0F + rand.nextFloat() * 0.1F, 1.0F + rand.nextFloat() * 0.1F);
            }
        }
        else
        {
            this.BeaconTypeSpecificClientSideUpdate();
        }
    }

    private void ApplyEffectToPlayersWithinRange()
    {
        if (!this.worldObj.isRemote && this.IsOn() && this.getLevels() > 0 && this.getPrimaryEffect() > 0)
        {
            if (this.getPrimaryEffect() < 256)
            {
                int var1 = 0;

                if (this.getPrimaryEffect() == FCBetterThanWolves.potionFortune.getId() || this.getPrimaryEffect() == FCBetterThanWolves.potionLooting.getId() || this.getPrimaryEffect() == Potion.digSpeed.getId())
                {
                    var1 = this.getLevels() - 1;
                }

                this.ApplyPotionEffectToPlayersInRange(this.getPrimaryEffect(), var1);
            }
            else if (this.getPrimaryEffect() == 22225)
            {
                this.ApplyDungCloudToPlayersInRange();
            }
        }
    }

    private void ApplyPotionEffectToPlayersInRange(int var1, int var2)
    {
        double var3 = m_dRangePerLevel[this.getLevels()];
        Iterator var5 = this.worldObj.playerEntities.iterator();
        double var6 = (double)this.xCoord;
        double var8 = (double)this.zCoord;

        while (var5.hasNext())
        {
            EntityPlayer var10 = (EntityPlayer)var5.next();
            double var11 = Math.abs(var6 - var10.posX);

            if (var11 <= var3)
            {
                double var13 = Math.abs(var8 - var10.posZ);

                if (var13 <= var3 && !var10.isDead && !var10.capabilities.isCreativeMode)
                {
                    var10.addPotionEffect(new PotionEffect(var1, 180, var2, true));
                }
            }
        }
    }

    private void ApplyDungCloudToPlayersInRange()
    {
        double var1 = m_dRangePerLevel[this.getLevels()];
        Iterator var3 = this.worldObj.playerEntities.iterator();
        double var4 = (double)this.xCoord;
        double var6 = (double)this.zCoord;

        while (var3.hasNext())
        {
            EntityPlayer var8 = (EntityPlayer)var3.next();
            double var9 = Math.abs(var4 - var8.posX);

            if (var9 <= var1)
            {
                double var11 = Math.abs(var6 - var8.posZ);

                if (var11 <= var1 && !var8.isDead && !var8.capabilities.isCreativeMode && !var8.IsWearingFullSuitSoulforgedArmor())
                {
                    var8.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 180, 0, true));
                    var8.addPotionEffect(new PotionEffect(Potion.poison.getId(), 180, 0, true));
                }
            }
        }
    }

    private boolean IsPyramidLevelValid(int var1, int var2, int var3)
    {
        int var4 = this.yCoord - var1;

        if (var4 < 0)
        {
            return false;
        }
        else
        {
            for (int var5 = this.xCoord - var1; var5 <= this.xCoord + var1; ++var5)
            {
                for (int var6 = this.zCoord - var1; var6 <= this.zCoord + var1; ++var6)
                {
                    int var7 = this.worldObj.getBlockId(var5, var4, var6);

                    if (var7 != var2 || var3 != -1 && this.worldObj.getBlockMetadata(var5, var4, var6) != var3)
                    {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    private void UpdatePowerState()
    {
        if (this.worldObj.checkChunksExist(this.xCoord - 4, this.yCoord, this.zCoord - 4, this.xCoord + 4, this.yCoord, this.zCoord + 4))
        {
            if (!this.CanBeaconSeeSky())
            {
                this.SetPowerState(false, 0, 0);
            }
            else
            {
                int var1 = this.worldObj.getBlockId(this.xCoord, this.yCoord - 1, this.zCoord);
                int var2 = 0;
                int var3 = 0;
                FCTileEntityBeaconEffectDescriptor var4 = this.GetEffectDescriptor(var1, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord - 1, this.zCoord));

                if (var4 != null)
                {
                    int var5 = var4.m_iBlockMetadata;
                    var3 = var4.m_iEffectID;

                    for (int var6 = 1; var6 <= 4 && this.IsPyramidLevelValid(var6, var1, var5); var2 = var6++)
                    {
                        ;
                    }
                }

                if (var2 > 0)
                {
                    this.SetPowerState(true, var3, var2);
                }
                else
                {
                    this.SetPowerState(false, 0, 0);
                }
            }

            this.IgniteIfHellfirePyramid();
        }
    }

    public void SetPowerState(boolean var1, int var2, int var3)
    {
        int var4 = this.getPrimaryEffect();
        int var5 = this.getLevels();

        if (!this.worldObj.isRemote)
        {
            this.UpdateGlobalMagneticFieldListForStateChange(var3, var2, var5, var4);
            this.UpdateGlobalLootingListForStateChange(var3, var2, var5, var4);
        }
        else if (var3 <= 0 && var5 > 0)
        {
            this.worldObj.playSound((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "mob.wither.death", 1.0F + rand.nextFloat() * 0.1F, 1.0F + rand.nextFloat() * 0.1F);
        }
        else if (var5 <= 0 && var3 > 0)
        {
            this.worldObj.playSound((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "mob.wither.spawn", 1.0F + rand.nextFloat() * 0.1F, 1.0F + rand.nextFloat() * 0.1F);

            if (var2 == 22223)
            {
                this.worldObj.playSound((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "mob.ghast.scream", 1.0F, rand.nextFloat() * 0.4F + 0.25F);
            }
        }

        this.SetIsOn(var1);
        this.SetPrimaryEffect(var2);
        this.setLevelsServerSafe(var3);
    }

    private void UpdateGlobalMagneticFieldListForStateChange(int var1, int var2, int var3, int var4)
    {
        if (var1 > 0 && var2 != 22224)
        {
            if (var3 > 0 && var4 != 22224)
            {
                if (var3 != var1)
                {
                    this.worldObj.GetMagneticPointList().ChangePowerLevelOfPointAt(this.xCoord, this.yCoord, this.zCoord, this.GetMagneticFieldLevel(var1, var2));
                }
            }
            else
            {
                this.worldObj.GetMagneticPointList().AddPoint(this.xCoord, this.yCoord, this.zCoord, this.GetMagneticFieldLevel(var1, var2));
            }
        }
        else if (var3 > 0 && var4 != 22224)
        {
            this.worldObj.GetMagneticPointList().RemovePointAt(this.xCoord, this.yCoord, this.zCoord);
        }
    }

    private void UpdateGlobalLootingListForStateChange(int var1, int var2, int var3, int var4)
    {
        if (var1 > 0 && var2 == FCBetterThanWolves.potionLooting.getId())
        {
            if (var3 > 0 && var4 == FCBetterThanWolves.potionLooting.getId())
            {
                if (var3 != var1)
                {
                    this.worldObj.GetLootingBeaconLocationList().ChangeEffectLevelOfPointAt(this.xCoord, this.yCoord, this.zCoord, var1, (int)m_dRangePerLevel[var1]);
                }
            }
            else
            {
                this.worldObj.GetLootingBeaconLocationList().AddPoint(this.xCoord, this.yCoord, this.zCoord, var1, (int)m_dRangePerLevel[var1]);
            }
        }
        else if (var3 > 0 && var4 == FCBetterThanWolves.potionLooting.getId())
        {
            this.worldObj.GetLootingBeaconLocationList().RemovePointAt(this.xCoord, this.yCoord, this.zCoord);
        }
    }

    private int GetMagneticFieldLevel(int var1, int var2)
    {
        if (var2 == 22222 || var2 == 22223)
        {
            var1 *= 2;
        }

        return var1;
    }

    private void ValidateGlobalEffects()
    {
        this.ValidateMagneticField();
        this.ValidateGlobalLootingEffects();
    }

    private void ValidateMagneticField()
    {
        if (this.getLevels() > 0 && this.getPrimaryEffect() != 22224)
        {
            FCMagneticPoint var1 = this.worldObj.GetMagneticPointList().GetMagneticPointAtLocation(this.xCoord, this.yCoord, this.zCoord);
            int var2 = this.GetMagneticFieldLevel(this.getLevels(), this.getPrimaryEffect());

            if (var1 == null)
            {
                this.worldObj.GetMagneticPointList().AddPoint(this.xCoord, this.yCoord, this.zCoord, var2);
            }
            else
            {
                var1.m_iFieldLevel = var2;
            }
        }
        else
        {
            this.worldObj.GetMagneticPointList().RemovePointAt(this.xCoord, this.yCoord, this.zCoord);
        }
    }

    private void ValidateGlobalLootingEffects()
    {
        int var1 = this.getLevels();

        if (var1 > 0 && this.getPrimaryEffect() == FCBetterThanWolves.potionLooting.getId())
        {
            FCBeaconEffectLocation var2 = this.worldObj.GetLootingBeaconLocationList().GetEffectAtLocation(this.xCoord, this.yCoord, this.zCoord);
            int var3 = (int)m_dRangePerLevel[var1];

            if (var2 == null)
            {
                this.worldObj.GetLootingBeaconLocationList().AddPoint(this.xCoord, this.yCoord, this.zCoord, var1, var3);
            }
            else
            {
                var2.m_iEffectLevel = var1;
                var2.m_iRange = var3;
            }
        }
        else
        {
            this.worldObj.GetLootingBeaconLocationList().RemovePointAt(this.xCoord, this.yCoord, this.zCoord);
        }
    }

    private boolean CanBeaconSeeSky()
    {
        if (this.worldObj.provider.dimensionId != -1)
        {
            return this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord);
        }
        else if (!this.worldObj.isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord))
        {
            return false;
        }
        else
        {
            for (int var1 = this.yCoord + 2; var1 < 256; ++var1)
            {
                if (!this.worldObj.isAirBlock(this.xCoord, var1, this.zCoord))
                {
                    int var2 = this.worldObj.getBlockId(this.xCoord, var1, this.zCoord);
                    return var2 == Block.bedrock.blockID;
                }
            }

            return true;
        }
    }

    private void IgniteIfHellfirePyramid()
    {
        if (!this.worldObj.isRemote && this.getPrimaryEffect() == Potion.fireResistance.getId() && this.getLevels() > 0)
        {
            boolean var1 = false;
            int var2;
            int var3;

            for (var2 = 1; var2 <= this.getLevels(); ++var2)
            {
                var3 = this.yCoord + 1 - var2;
                int var4;

                for (var4 = this.xCoord - var2; var4 <= this.xCoord + var2; ++var4)
                {
                    var1 = this.IgniteIfEmptyWithState(var4, var3, this.zCoord - var2, var1);
                    var1 = this.IgniteIfEmptyWithState(var4, var3, this.zCoord + var2, var1);
                }

                for (var4 = this.zCoord - (var2 - 1); var4 <= this.zCoord + (var2 - 1); ++var4)
                {
                    var1 = this.IgniteIfEmptyWithState(this.xCoord - var2, var3, var4, var1);
                    var1 = this.IgniteIfEmptyWithState(this.xCoord + var2, var3, var4, var1);
                }
            }

            var2 = this.getLevels();

            for (var3 = this.xCoord - var2; var3 <= this.xCoord + var2; ++var3)
            {
                var1 = this.IgniteIfEmptyWithState(var3, this.yCoord - var2, this.zCoord - (var2 + 1), var1);
                var1 = this.IgniteIfEmptyWithState(var3, this.yCoord - var2, this.zCoord + var2 + 1, var1);
            }

            for (var3 = this.zCoord - var2; var3 <= this.zCoord + var2; ++var3)
            {
                var1 = this.IgniteIfEmptyWithState(this.xCoord - (var2 + 1), this.yCoord - var2, var3, var1);
                var1 = this.IgniteIfEmptyWithState(this.xCoord + var2 + 1, this.yCoord - var2, var3, var1);
            }

            if (var1)
            {
                this.worldObj.playSoundEffect((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "mob.ghast.fireball", 1.0F, this.worldObj.rand.nextFloat() * 0.4F + 0.8F);
            }
        }
    }

    public boolean IgniteIfEmptyWithState(int var1, int var2, int var3, boolean var4)
    {
        if (this.worldObj.isAirBlock(var1, var2, var3))
        {
            this.worldObj.setBlock(var1, var2, var3, Block.fire.blockID);
            var4 = true;
        }

        return var4;
    }

    private void BeaconTypeSpecificServerSideUpdate()
    {
        int var1 = this.getLevels();

        if (var1 > 0)
        {
            int var2 = this.getPrimaryEffect();

            if (var2 == 22223)
            {
                this.CheckForBlightSpread();
            }
            else if (var2 == 22226)
            {
                if (this.worldObj.provider.dimensionId != 1)
                {
                    this.CheckForEndermanSpawn();
                }
                else
                {
                    this.CheckForSilverfishSpawn();
                }
            }
        }
    }

    private void CheckForBlightSpread()
    {
        if (rand.nextInt(10000) == 0)
        {
            int var1 = this.getLevels();
            int var2 = m_iSoulforgedBeaconBlightSpreadRange[var1];
            int var3 = this.xCoord + rand.nextInt(var2 * 2 + 1) - var2;
            int var4 = rand.nextInt(256);
            int var5 = this.zCoord + rand.nextInt(var2 * 2 + 1) - var2;
            int var6 = this.worldObj.getBlockId(var3, var4, var5);

            if (var6 == Block.grass.blockID)
            {
                this.worldObj.setBlockAndMetadataWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID, 0);
            }
        }
    }

    private void CheckForEndermanSpawn()
    {
        EnumCreatureType var1 = EnumCreatureType.monster;
        int var2 = this.worldObj.CountEntitiesThatApplyToSpawnCap(var1.getCreatureClass());
        int var3 = var1.getMaxNumberOfCreature();
        var3 += var3 >> 2;

        if (var2 < var3)
        {
            int var4 = this.getLevels();

            if (rand.nextInt(1200) < m_iEnderAntennaBeaconEndermanSpawnChancePerLevel[var4])
            {
                int var5 = m_iEnderAntennaBeaconSpawnRange[var4];
                int var6 = this.xCoord + rand.nextInt(var5 * 2 + 1) - var5;
                int var7 = this.zCoord + rand.nextInt(var5 * 2 + 1) - var5;
                Chunk var9 = this.worldObj.getChunkFromBlockCoords(var6, var7);
                int var8;

                if (var9 == null)
                {
                    var8 = rand.nextInt(this.worldObj.getActualHeight());
                }
                else
                {
                    var8 = rand.nextInt(var9.getTopFilledSegment() + 16 - 1);
                }

                Material var10 = this.worldObj.getBlockMaterial(var6, var8, var7);

                if (!var10.isSolid() && !var10.isLiquid() && this.worldObj.doesBlockHaveSolidTopSurface(var6, var8 - 1, var7))
                {
                    FCEntityEnderman var11 = new FCEntityEnderman(this.worldObj);
                    double var12 = (double)var6 + 0.5D;
                    double var14 = (double)var8;
                    double var16 = (double)var7 + 0.5D;
                    var11.setLocationAndAngles(var12, var14, var16, rand.nextFloat() * 360.0F, 0.0F);

                    if (this.worldObj.checkNoEntityCollision(var11.boundingBox) && this.worldObj.getCollidingBoundingBoxes(var11, var11.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(var11.boundingBox))
                    {
                        this.worldObj.spawnEntityInWorld(var11);
                        var11.initCreature();
                        this.worldObj.playAuxSFX(2247, var6, var8, var7, 0);
                    }
                }
            }
        }
    }

    private void CheckForSilverfishSpawn()
    {
        EnumCreatureType var1 = EnumCreatureType.monster;
        int var2 = this.worldObj.CountEntitiesThatApplyToSpawnCap(var1.getCreatureClass());
        int var3 = var1.getMaxNumberOfCreature();
        var3 += var3 >> 2;

        if (var2 < var3)
        {
            int var4 = this.getLevels();

            if (rand.nextInt(1200) < m_iEnderAntennaBeaconSilverfishSpawnChancePerLevel[var4])
            {
                int var5 = m_iEnderAntennaBeaconSpawnRange[var4];
                int var6 = this.xCoord + rand.nextInt(var5 * 2 + 1) - var5;
                int var7 = this.zCoord + rand.nextInt(var5 * 2 + 1) - var5;
                Chunk var9 = this.worldObj.getChunkFromBlockCoords(var6, var7);
                int var8;

                if (var9 == null)
                {
                    var8 = rand.nextInt(this.worldObj.getActualHeight());
                }
                else
                {
                    var8 = rand.nextInt(var9.getTopFilledSegment() + 16 - 1);
                }

                Material var10 = this.worldObj.getBlockMaterial(var6, var8, var7);

                if (!var10.isSolid() && !var10.isLiquid() && this.worldObj.doesBlockHaveSolidTopSurface(var6, var8 - 1, var7))
                {
                    EntitySilverfish var11 = new EntitySilverfish(this.worldObj);
                    double var12 = (double)var6 + 0.5D;
                    double var14 = (double)var8;
                    double var16 = (double)var7 + 0.5D;
                    var11.setLocationAndAngles(var12, var14, var16, rand.nextFloat() * 360.0F, 0.0F);

                    if (this.worldObj.checkNoEntityCollision(var11.boundingBox) && this.worldObj.getCollidingBoundingBoxes(var11, var11.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(var11.boundingBox))
                    {
                        this.worldObj.spawnEntityInWorld(var11);
                        var11.initCreature();
                        this.worldObj.playAuxSFX(2247, var6, var8, var7, 0);
                        var11.spawnExplosionParticle();
                    }
                }
            }
        }
    }

    private void BeaconTypeSpecificClientSideUpdate()
    {
        int var1 = this.getLevels();

        if (var1 > 0 && this.getPrimaryEffect() == 22223 && rand.nextInt(60) <= var1)
        {
            int var2 = rand.nextInt(var1) + 1;
            int var3 = rand.nextInt(var1 * 2 + 1) - var1;
            int var4 = rand.nextInt(var1 * 2 + 1) - var1;
            this.worldObj.playSound((double)(this.xCoord + var3), (double)(this.yCoord - var1), (double)(this.zCoord + var4), "mob.ghast.moan", 0.5F, rand.nextFloat() * 0.4F + 0.25F);
        }
    }

    private FCTileEntityBeaconEffectDescriptor GetEffectDescriptor(int var1, int var2)
    {
        ArrayList var3 = m_beaconEffectsByBlockID[var1];

        for (int var4 = 0; var4 < var3.size(); ++var4)
        {
            FCTileEntityBeaconEffectDescriptor var5 = (FCTileEntityBeaconEffectDescriptor)var3.get(var4);

            if (var5.m_iBlockMetadata == -1 || var5.m_iBlockMetadata == var2)
            {
                return var5;
            }
        }

        return null;
    }

    public static void AddBeaconEffect(int var0, int var1)
    {
        AddBeaconEffect(var0, -1, var1);
    }

    public static void AddBeaconEffect(int var0, int var1, int var2)
    {
        m_beaconEffectsByBlockID[var0].add(new FCTileEntityBeaconEffectDescriptor(var1, var2));
    }

    public static void InitializeEffectsByBlockID()
    {
        for (int var0 = 0; var0 < 4096; ++var0)
        {
            m_beaconEffectsByBlockID[var0] = new ArrayList();
        }

        AddBeaconEffect(Block.blockGold.blockID, Potion.digSpeed.getId());
        AddBeaconEffect(Block.blockDiamond.blockID, FCBetterThanWolves.potionFortune.getId());
        AddBeaconEffect(Block.blockEmerald.blockID, FCBetterThanWolves.potionLooting.getId());
        AddBeaconEffect(Block.blockLapis.blockID, FCBetterThanWolves.potionTrueSight.getId());
        AddBeaconEffect(Block.glowStone.blockID, Potion.nightVision.getId());
        AddBeaconEffect(FCBetterThanWolves.fcAestheticOpaque.blockID, 3, Potion.fireResistance.getId());
        AddBeaconEffect(Block.blockIron.blockID, 22222);
        AddBeaconEffect(FCBetterThanWolves.fcSoulforgedSteelBlock.blockID, 22223);
        AddBeaconEffect(Block.glass.blockID, 22224);
        AddBeaconEffect(FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID, 7, 22225);
        AddBeaconEffect(FCBetterThanWolves.fcAestheticOpaque.blockID, 14, 22226);
    }
}
