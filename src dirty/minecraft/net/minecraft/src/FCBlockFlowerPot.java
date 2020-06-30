package net.minecraft.src;

public class FCBlockFlowerPot extends BlockFlowerPot
{
    protected static final double m_dHeight = 0.375D;
    protected static final double m_dWidth = 0.375D;
    protected static final double m_dHalfWidth = 0.1875D;

    public FCBlockFlowerPot(int var1)
    {
        super(var1);
        this.InitBlockBounds(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.375D, 0.6875D);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        ItemStack var10 = var5.inventory.getCurrentItem();

        if (var10 != null && var1.getBlockMetadata(var2, var3, var4) == 0)
        {
            int var11 = this.GetMetadataForItemStack(var10);

            if (var11 > 0)
            {
                var1.SetBlockMetadataWithNotify(var2, var3, var4, var11, 2);

                if (!var5.capabilities.isCreativeMode)
                {
                    --var10.stackSize;

                    if (var10.stackSize <= 0)
                    {
                        var5.inventory.setInventorySlotContents(var5.inventory.currentItem, (ItemStack)null);
                    }
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World var1, int var2, int var3, int var4)
    {
        ItemStack var5 = this.GetPlantStackForMetadata(var1.getBlockMetadata(var2, var3, var4));
        return var5 == null ? Item.flowerPot.itemID : var5.getItemDamage();
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (!var1.isRemote)
        {
            int var8 = this.quantityDroppedWithBonus(var7, var1.rand);

            for (int var9 = 0; var9 < var8; ++var9)
            {
                if (var1.rand.nextFloat() <= var6)
                {
                    int var10 = this.idDropped(var5, var1.rand, var7);

                    if (var10 > 0)
                    {
                        this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(var10, 1, this.damageDropped(var5)));
                    }
                }
            }
        }

        if (var5 > 0)
        {
            ItemStack var11 = this.GetPlantStackForMetadata(var5);

            if (var11 != null)
            {
                this.dropBlockAsItem_do(var1, var2, var3, var4, var11);
            }
        }
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }

    private ItemStack GetPlantStackForMetadata(int var1)
    {
        return var1 == 7 ? new ItemStack(FCBetterThanWolves.fcItemMushroomRed) : (var1 == 8 ? new ItemStack(FCBetterThanWolves.fcItemMushroomBrown) : getPlantForMeta(var1));
    }

    private int GetMetadataForItemStack(ItemStack var1)
    {
        int var2 = var1.getItem().itemID;
        return var2 == FCBetterThanWolves.fcItemMushroomRed.itemID ? 7 : (var2 == FCBetterThanWolves.fcItemMushroomBrown.itemID ? 8 : getMetaForPlant(var1));
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.RenderBlockFlowerpot(this, var2, var3, var4);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 == 0 ? !var1.isBlockOpaqueCube(var2, var3, var4) : true;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        ItemStack var5 = this.GetPlantStackForMetadata(var1.getBlockMetadata(var2, var3, var4));
        return var5 == null ? Item.flowerPot.itemID : var5.itemID;
    }
}
