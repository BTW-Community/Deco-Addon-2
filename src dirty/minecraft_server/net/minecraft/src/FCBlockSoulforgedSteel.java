package net.minecraft.src;

import java.util.Random;

public class FCBlockSoulforgedSteel extends Block
{
    private static final float m_fBlockHardness = 100.0F;
    private static final float m_fBlockExplosionResistance = 2000.0F;
    private static final int m_iTickRate = 4;
    private static final float m_iStrongholdActivationDistance = 64.0F;
    private static final float m_iStrongholdActivationDistanceSq = 4096.0F;

    protected FCBlockSoulforgedSteel(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialSoulforgedSteel);
        this.setHardness(100.0F);
        this.setResistance(2000.0F);
        this.setStepSound(soundMetalFootstep);
        this.setUnlocalizedName("fcBlockSoulforgedSteel");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    public int getMobilityFlag()
    {
        return 2;
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 4;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if ((this.IsRedstonePowerFlagOn(var1, var2, var3, var4) != this.IsReceivingRedstonePower(var1, var2, var3, var4) || this.IsActivatedByWaterFlagOn(var1, var2, var3, var4) != this.IsBlockNeighboringOnWater(var1, var2, var3, var4)) && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.IsReceivingRedstonePower(var1, var2, var3, var4);

        if (this.IsRedstonePowerFlagOn(var1, var2, var3, var4) != var6)
        {
            this.SetRedstonePowerFlag(var1, var2, var3, var4, var6);

            if (var6)
            {
                var1.playAuxSFX(2225, var2, var3, var4, 1);
            }
        }

        boolean var7 = this.IsBlockNeighboringOnWater(var1, var2, var3, var4);

        if (this.IsActivatedByWaterFlagOn(var1, var2, var3, var4) != var7)
        {
            this.SetActivatedByWaterFlag(var1, var2, var3, var4, var7);

            if (var7)
            {
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.drink", 0.25F + var1.rand.nextFloat() * 0.25F, var1.rand.nextFloat() * 0.75F + 0.25F);

                if (this.GetStrongholdIndexWithinActivationRange(var1, var2, var3, var4) == 0)
                {
                    var1.playAuxSFX(2228, var2, var3, var4, 0);
                }
            }
        }
    }

    public boolean IsRedstonePowerFlagOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return (var5 & 1) > 0;
    }

    private void SetRedstonePowerFlag(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -2;

        if (var5)
        {
            var6 |= 1;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    private boolean IsReceivingRedstonePower(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 + 1, var4);
        return var5 == Block.redstoneWire.blockID ? var1.getBlockMetadata(var2, var3 + 1, var4) > 0 : false;
    }

    private void EmitPoweredParticles(World var1, int var2, int var3, int var4, Random var5)
    {
        for (int var6 = 0; var6 < 10; ++var6)
        {
            float var7 = (float)var2 + var5.nextFloat();
            float var8 = (float)var3 + var5.nextFloat() * 0.5F + 1.0F;
            float var9 = (float)var4 + var5.nextFloat();
            var1.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
        }
    }

    public boolean IsActivatedByWaterFlagOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return (var5 & 2) > 0;
    }

    private void SetActivatedByWaterFlag(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -3;

        if (var5)
        {
            var6 |= 2;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public boolean IsRecentlyActivatedFlagOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return (var5 & 4) > 0;
    }

    private void SetRecentlyActivatedFlag(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -5;

        if (var5)
        {
            var6 |= 4;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    private boolean IsBlockNeighboringOnWater(World var1, int var2, int var3, int var4)
    {
        return this.IsWaterBlock(var1, var2 + 1, var3, var4) || this.IsWaterBlock(var1, var2 - 1, var3, var4) || this.IsWaterBlock(var1, var2, var3 + 1, var4) || this.IsWaterBlock(var1, var2, var3 - 1, var4) || this.IsWaterBlock(var1, var2, var3, var4 + 1) || this.IsWaterBlock(var1, var2, var3, var4 - 1);
    }

    private boolean IsWaterBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);
        return var5 == Block.waterMoving.blockID || var5 == Block.waterStill.blockID;
    }

    public int GetStrongholdIndexWithinActivationRange(World var1, int var2, int var3, int var4)
    {
        return -1;
    }
}
