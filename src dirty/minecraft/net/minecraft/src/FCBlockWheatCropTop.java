package net.minecraft.src;

public class FCBlockWheatCropTop extends FCBlockCropsDailyGrowth
{
    private Icon[] m_iconArray;

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

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.m_iconArray = new Icon[4];

        for (int var2 = 0; var2 < this.m_iconArray.length; ++var2)
        {
            this.m_iconArray[var2] = var1.registerIcon("fcBlockWheatCropTop_" + var2);
        }

        this.blockIcon = this.m_iconArray[3];
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetGrowthLevel(var1, var2, var3, var4);
        var6 = MathHelper.clamp_int(var6, 0, 3);
        return this.m_iconArray[var6];
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        this.RenderCrops(var1, var2, var3, var4);
        return true;
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        if (!this.IsFullyGrown(var1, var2, var3, var4))
        {
            Block var5 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

            if (var5 != null)
            {
                return var5.getSelectedBoundingBoxFromPool(var1, var2, var3 - 1, var4);
            }
        }

        return super.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
    }
}
