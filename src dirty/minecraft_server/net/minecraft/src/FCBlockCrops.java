package net.minecraft.src;

import java.util.Random;

public abstract class FCBlockCrops extends FCBlockPlants
{
    protected static final double m_dBoundsWidth = 0.75D;
    protected static final double m_dBoundsHalfWidth = 0.375D;

    protected FCBlockCrops(int var1)
    {
        super(var1, Material.plants);
        this.setHardness(0.0F);
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.CROPS);
        this.InitBlockBounds(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D);
        this.setStepSound(soundGrassFootstep);
        this.setTickRandomly(true);
        this.disableStats();
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
        this.UpdateIfBlockStays(var1, var2, var3, var4);
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (!var1.isRemote && this.IsFullyGrown(var5))
        {
            super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, 0);
            this.DropSeeds(var1, var2, var3, var4, var5);
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return this.IsFullyGrown(var1) ? this.GetCropItemID() : 0;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        float var5 = 0.0F;
        Block var6 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

        if (var6 != null)
        {
            var5 = var6.GroundCoverRestingOnVisualOffset(var1, var2, var3 - 1, var4);
        }

        int var7 = var1.getBlockMetadata(var2, var3, var4);

        if (this.IsFullyGrown(var7))
        {
            return AxisAlignedBB.getAABBPool().getAABB(0.125D, 0.0D + (double)var5, 0.125D, 0.875D, 1.0D + (double)var5, 0.875D);
        }
        else
        {
            int var8 = this.GetGrowthLevel(var7);
            double var9 = (double)(1 + var8) / 8.0D;
            int var11 = this.GetWeedsGrowthLevel(var1, var2, var3, var4);

            if (var11 > 0)
            {
                var9 = Math.max(var9, FCBlockWeeds.GetWeedsBoundsHeight(var11));
            }

            return AxisAlignedBB.getAABBPool().getAABB(0.125D, (double)(0.0F + var5), 0.125D, 0.875D, var9 + (double)var5, 0.875D);
        }
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return true;
    }

    public void OnGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
        super.OnGrazed(var1, var2, var3, var4, var5);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.UpdateIfBlockStays(var1, var2, var3, var4) && var1.provider.dimensionId != 1 && !this.IsFullyGrown(var1, var2, var3, var4))
        {
            this.AttemptToGrow(var1, var2, var3, var4, var5);
        }
    }

    public boolean CanWeedsGrowInBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetConvertsLegacySoil(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    protected boolean CanGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3, var4)];
        return var5 != null && var5.CanDomesticatedCropsGrowOnBlock(var1, var2, var3, var4);
    }

    protected abstract int GetCropItemID();

    protected abstract int GetSeedItemID();

    protected boolean UpdateIfBlockStays(World var1, int var2, int var3, int var4)
    {
        if (!this.canBlockStay(var1, var2, var3, var4))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockToAir(var2, var3, var4);
            return false;
        }
        else
        {
            return true;
        }
    }

    protected void AttemptToGrow(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.GetWeedsGrowthLevel(var1, var2, var3, var4) == 0 && var1.GetBlockNaturalLightValue(var2, var3 + 1, var4) >= this.GetLightLevelForGrowth())
        {
            Block var6 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

            if (var6 != null && var6.IsBlockHydratedForPlantGrowthOn(var1, var2, var3 - 1, var4))
            {
                float var7 = this.GetBaseGrowthChance(var1, var2, var3, var4) * var6.GetPlantGrowthOnMultiplier(var1, var2, var3 - 1, var4, this);

                if (var5.nextFloat() <= var7)
                {
                    this.IncrementGrowthLevel(var1, var2, var3, var4);
                }
            }
        }
    }

    public void DropSeeds(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetSeedItemID();

        if (var6 > 0)
        {
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(var6, 1, 0));
        }
    }

    public float GetBaseGrowthChance(World var1, int var2, int var3, int var4)
    {
        return 0.05F;
    }

    protected void IncrementGrowthLevel(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetGrowthLevel(var1, var2, var3, var4) + 1;
        this.SetGrowthLevel(var1, var2, var3, var4, var5);

        if (this.IsFullyGrown(var1, var2, var3, var4))
        {
            Block var6 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

            if (var6 != null)
            {
                var6.NotifyOfFullStagePlantGrowthOn(var1, var2, var3 - 1, var4, this);
            }
        }
    }

    protected int GetGrowthLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetGrowthLevel(var1.getBlockMetadata(var2, var3, var4));
    }

    protected int GetGrowthLevel(int var1)
    {
        return var1 & 7;
    }

    protected void SetGrowthLevel(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -8;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6 | var5);
    }

    protected void SetGrowthLevelNoNotify(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -8;
        var1.setBlockMetadata(var2, var3, var4, var6 | var5);
    }

    protected boolean IsFullyGrown(World var1, int var2, int var3, int var4)
    {
        return this.IsFullyGrown(var1.getBlockMetadata(var2, var3, var4));
    }

    protected boolean IsFullyGrown(int var1)
    {
        return this.GetGrowthLevel(var1) >= 7;
    }

    protected int GetLightLevelForGrowth()
    {
        return 11;
    }
}
