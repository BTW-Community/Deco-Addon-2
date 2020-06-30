package net.minecraft.src;

public class FCBlockWheatCrop extends FCBlockCropsDailyGrowth
{
    protected FCBlockWheatCrop(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockWheatCrop");
    }

    protected int GetSeedItemID()
    {
        return 0;
    }

    protected int GetCropItemID()
    {
        return FCBetterThanWolves.fcItemStraw.itemID;
    }

    public boolean IsBlockHydratedForPlantGrowthOn(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];
        return var5 != null && var5.IsBlockHydratedForPlantGrowthOn(var1, var2, var3 - 1, var4);
    }

    protected void IncrementGrowthLevel(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetGrowthLevel(var1, var2, var3, var4);

        if (var5 == 6)
        {
            if (var1.isAirBlock(var2, var3 + 1, var4))
            {
                this.SetGrowthLevel(var1, var2, var3, var4, var5 + 1);
                int var6 = 0;
                Block var7 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

                if (var7 == null || !var7.GetIsFertilizedForPlantGrowth(var1, var2, var3 - 1, var4))
                {
                    var6 = FCBetterThanWolves.fcBlockWheatCrop.SetHasGrownToday(var6, true);
                }

                var1.setBlockAndMetadataWithNotify(var2, var3 + 1, var4, FCBetterThanWolves.fcBlockWheatCropTop.blockID, var6);
            }
        }
        else
        {
            super.IncrementGrowthLevel(var1, var2, var3, var4);
        }
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];
        return var5 != null ? var5.GroundCoverRestingOnVisualOffset(var1, var2, var3 - 1, var4) : 0.0F;
    }

    public boolean GetIsFertilizedForPlantGrowth(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];
        return var5 != null && var5.GetIsFertilizedForPlantGrowth(var1, var2, var3 - 1, var4);
    }

    public void NotifyOfFullStagePlantGrowthOn(World var1, int var2, int var3, int var4, Block var5)
    {
        Block var6 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

        if (var6 != null)
        {
            var6.NotifyOfFullStagePlantGrowthOn(var1, var2, var3 - 1, var4, var5);
        }
    }

    public boolean HasTopBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2, var3 + 1, var4) == FCBetterThanWolves.fcBlockWheatCropTop.blockID;
    }

    public int GetTopBlockGrowthLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return FCBetterThanWolves.fcBlockWheatCropTop.GetGrowthLevel(var1, var2, var3 + 1, var4);
    }
}
