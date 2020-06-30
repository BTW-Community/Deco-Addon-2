package net.minecraft.src;

import java.util.Random;

public class FCBlockStem extends BlockStem
{
    private static final int m_iStemTickRate = 2;
    private static final double m_dWidth = 0.25D;
    private static final double m_dHalfWidth = 0.125D;

    protected FCBlockStem(int var1, Block var2)
    {
        super(var1, var2);
        this.setHardness(0.0F);
        this.SetBuoyant();
        this.InitBlockBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.25D, 0.625D);
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("pumpkinStem");
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 2;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.checkFlowerChange(var1, var2, var3, var4);

        if (var1.getBlockId(var2, var3, var4) == this.blockID)
        {
            this.ValidateFruitState(var1, var2, var3, var4, var5);
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.updateTick(var1, var2, var3, var4, var5);

        if (var1.getBlockId(var2, var3, var4) == this.blockID && var1.provider.dimensionId != 1)
        {
            this.CheckForGrowth(var1, var2, var3, var4, var5);
        }
    }

    public boolean OnBlockSawed(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {}

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender() {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if (var5 == 15 && !this.HasConnectedFruit(var1, var2, var3, var4))
        {
            var5 = 7;
        }

        double var6 = (double)(var5 + 1) / 16.0D;

        if (var6 < 0.125D)
        {
            var6 = 0.125D;
        }

        double var8 = 0.125D;
        int var10 = this.GetWeedsGrowthLevel(var1, var2, var3, var4);

        if (var10 > 0)
        {
            var6 = Math.max(var6, FCBlockWeeds.GetWeedsBoundsHeight(var10));
            var8 = 0.375D;
        }

        return AxisAlignedBB.getAABBPool().getAABB(0.5D - var8, 0.0D, 0.5D - var8, 0.5D + var8, var6, 0.5D + var8);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);

        if (var1.getBlockId(var2, var3, var4) == this.blockID)
        {
            this.ValidateFruitState(var1, var2, var3, var4, var1.rand);
        }
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return true;
    }

    protected boolean CanGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3, var4)];
        return var5 != null && var5.CanDomesticatedCropsGrowOnBlock(var1, var2, var3, var4);
    }

    public boolean CanWeedsGrowInBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public void SetFruitBlock(Block var1)
    {
        this.fruitType = var1;
    }

    private boolean HasConnectedFruit(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetConnectedFruitDirection(var1, var2, var3, var4) > 0;
    }

    private int GetConnectedFruitDirection(IBlockAccess var1, int var2, int var3, int var4)
    {
        for (int var5 = 2; var5 < 6; ++var5)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);
            var6.AddFacingAsOffset(var5);
            int var7 = var1.getBlockId(var6.i, var6.j, var6.k);

            if (var7 == this.fruitType.blockID && this.fruitType.IsBlockAttachedToFacing(var1, var6.i, var6.j, var6.k, Block.GetOppositeFacing(var5)))
            {
                return var5;
            }
        }

        return -1;
    }

    private void ValidateFruitState(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var6 == 15 && !this.HasConnectedFruit(var1, var2, var3, var4))
        {
            var1.setBlockMetadataWithNotify(var2, var3, var4, 7);
        }
    }

    private void CheckForGrowth(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.GetWeedsGrowthLevel(var1, var2, var3, var4) == 0 && var1.getBlockLightValue(var2, var3 + 1, var4) >= 9)
        {
            Block var6 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

            if (var6 != null && var6.IsBlockHydratedForPlantGrowthOn(var1, var2, var3 - 1, var4))
            {
                float var7 = 0.2F * var6.GetPlantGrowthOnMultiplier(var1, var2, var3 - 1, var4, this);

                if (var5.nextFloat() <= var7)
                {
                    int var8 = var1.getBlockMetadata(var2, var3, var4);

                    if (var8 < 14)
                    {
                        ++var8;
                        var1.setBlockMetadataWithNotify(var2, var3, var4, var8);
                    }
                    else if (var8 == 14)
                    {
                        FCUtilsBlockPos var9 = new FCUtilsBlockPos(var2, var3, var4);
                        int var10 = 0;

                        if (this.HasSpaceToGrow(var1, var2, var3, var4))
                        {
                            var10 = var5.nextInt(4) + 2;
                            var9.AddFacingAsOffset(var10);
                        }

                        if (this.CanGrowFruitAt(var1, var9.i, var9.j, var9.k))
                        {
                            var6.NotifyOfFullStagePlantGrowthOn(var1, var2, var3 - 1, var4, this);
                            var1.setBlockWithNotify(var9.i, var9.j, var9.k, this.fruitType.blockID);

                            if (var10 != 0)
                            {
                                this.fruitType.AttachToFacing(var1, var9.i, var9.j, var9.k, Block.GetOppositeFacing(var10));
                                var1.setBlockMetadataWithNotify(var2, var3, var4, 15);
                            }
                        }
                    }
                }
            }
        }
    }

    protected boolean HasSpaceToGrow(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 2; var5 <= 5; ++var5)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);
            var6.AddFacingAsOffset(var5);

            if (this.CanGrowFruitAt(var1, var6.i, var6.j, var6.k))
            {
                return true;
            }
        }

        return false;
    }

    protected boolean CanGrowFruitAt(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);
        Block var6 = Block.blocksList[var5];
        return (FCUtilsWorld.IsReplaceableBlock(var1, var2, var3, var4) || var6 != null && var6.blockMaterial == Material.plants && var5 != Block.cocoaPlant.blockID) && (var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4) || this.CanGrowOnBlock(var1, var2, var3 - 1, var4));
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        var1.renderBlockStem(this, var2, var3, var4);
        FCBetterThanWolves.fcBlockWeeds.RenderWeeds(this, var1, var2, var3, var4);
        return true;
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int var1)
    {
        int var2 = var1 * 16;
        int var3 = 255 - var1 * 4;
        int var4 = var1 * 2;
        return var2 << 16 | var3 << 8 | var4;
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if (var5 == 15 && !this.HasConnectedFruit(var1, var2, var3, var4))
        {
            var5 = 7;
        }

        return this.getRenderColor(var5);
    }

    /**
     * Returns the current state of the stem. Returns -1 if the stem is not fully grown, or a value between 0 and 3
     * based on the direction the stem is facing.
     */
    public int getState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if (var5 == 15)
        {
            int var6 = this.GetConnectedFruitDirection(var1, var2, var3, var4);

            if (var6 > 0)
            {
                if (var6 == 2)
                {
                    return 2;
                }

                if (var6 == 3)
                {
                    return 3;
                }

                if (var6 == 4)
                {
                    return 0;
                }

                if (var6 == 5)
                {
                    return 1;
                }
            }
        }

        return -1;
    }
}
