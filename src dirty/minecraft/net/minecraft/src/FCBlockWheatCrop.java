package net.minecraft.src;

public class FCBlockWheatCrop extends FCBlockCropsDailyGrowth
{
    private Icon[] m_iconArray;
    private Icon[] m_connectToTopIconArray;

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

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.m_iconArray = new Icon[8];
        int var2;

        for (var2 = 0; var2 < this.m_iconArray.length; ++var2)
        {
            this.m_iconArray[var2] = var1.registerIcon("fcBlockWheatCrop_" + var2);
        }

        this.blockIcon = this.m_iconArray[7];
        this.m_connectToTopIconArray = new Icon[4];

        for (var2 = 0; var2 < this.m_connectToTopIconArray.length; ++var2)
        {
            this.m_connectToTopIconArray[var2] = var1.registerIcon("fcBlockWheatCrop_7_" + var2);
        }
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetGrowthLevel(var1, var2, var3, var4);
        var6 = MathHelper.clamp_int(var6, 0, 7);

        if (var6 == 7 && this.HasTopBlock(var1, var2, var3, var4))
        {
            int var7 = this.GetTopBlockGrowthLevel(var1, var2, var3, var4);
            var7 = MathHelper.clamp_int(var7, 0, 3);
            return this.m_connectToTopIconArray[var7];
        }
        else
        {
            return this.m_iconArray[var6];
        }
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        if (this.IsFullyGrown(var1, var2, var3, var4) && this.HasTopBlock(var1, var2, var3, var4))
        {
            int var5 = this.GetTopBlockGrowthLevel(var1, var2, var3, var4);

            if (var5 < 3)
            {
                float var6 = 0.0F;
                Block var7 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

                if (var7 != null)
                {
                    var6 = var7.GroundCoverRestingOnVisualOffset(var1, var2, var3 - 1, var4);
                }

                double var8 = (double)(1 + var5) / 8.0D;
                return AxisAlignedBB.getAABBPool().getAABB(0.125D, 0.0D + (double)var6, 0.125D, 0.875D, 1.0D + (double)var6 + var8, 0.875D).offset((double)var2, (double)var3, (double)var4);
            }
        }

        return super.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
    }
}
