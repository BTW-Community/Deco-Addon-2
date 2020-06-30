package net.minecraft.src;

public class FCBlockWheatCropTop extends FCBlockCropsDailyGrowth
{
    protected FCBlockWheatCropTop(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockWheatCropTop");
    }

    public boolean DoesBlockDropAsItemOnSaw(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    protected int GetSeedItemID()
    {
        return 0;
    }

    protected int GetCropItemID()
    {
        return FCBetterThanWolves.fcItemWheat.itemID;
    }

    protected boolean IsFullyGrown(int var1)
    {
        return this.GetGrowthLevel(var1) >= 3;
    }

    protected boolean CanGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2, var3, var4) == FCBetterThanWolves.fcBlockWheatCrop.blockID;
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
            return AxisAlignedBB.getAABBPool().getAABB(0.125D, 0.0D + (double)var5, 0.125D, 0.875D, 0.5D + (double)var5, 0.875D);
        }
        else
        {
            int var8 = this.GetGrowthLevel(var7);
            return AxisAlignedBB.getAABBPool().getAABB(0.125D, (double)(0.0F + var5), 0.125D, 0.875D, (double)(1 + var8) / 8.0D + (double)var5, 0.875D);
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        super.breakBlock(var1, var2, var3, var4, var5, var6);

        if (!this.IsFullyGrown(var6))
        {
            int var7 = var1.getBlockId(var2, var3 - 1, var4);

            if (var7 == FCBetterThanWolves.fcBlockWheatCrop.blockID)
            {
                var1.setBlockToAir(var2, var3 - 1, var4);
            }
        }
    }

    protected void UpdateFlagForGrownToday(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

        if (var5 != null && (!var5.GetIsFertilizedForPlantGrowth(var1, var2, var3 - 1, var4) || this.GetGrowthLevel(var1, var2, var3, var4) % 2 == 1))
        {
            this.SetHasGrownToday(var1, var2, var3, var4, true);
        }
    }
}
