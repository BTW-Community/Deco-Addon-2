package net.minecraft.src;

public class FCBlockCake extends BlockCake
{
    static final float m_fBorderWidth = 0.0625F;
    static final float m_fHeight = 0.5F;

    protected FCBlockCake(int var1)
    {
        super(var1);
        this.SetBuoyant();
    }

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
        int var5 = this.GetEatState(var1, var2, var3, var4);
        float var6 = (float)(1 + var5 * 2) / 16.0F;
        return AxisAlignedBB.getAABBPool().getAABB((double)var6, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return this.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4).offset((double)var2, (double)var3, (double)var4);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        this.EatCakeSliceLocal(var1, var2, var3, var4, var5);
        return true;
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {}

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!this.canBlockStay(var1, var2, var3, var4))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
        else
        {
            boolean var6 = this.IsRedstoneOn(var1, var2, var3, var4);
            boolean var7 = var1.isBlockGettingPowered(var2, var3, var4);

            if (var6 != var7)
            {
                this.SetRedstoneOn(var1, var2, var3, var4, var7);

                if (var7)
                {
                    var1.playAuxSFX(2225, var2, var3, var4, 0);
                }
            }
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        boolean var5 = var1.isBlockGettingPowered(var2, var3, var4);

        if (var5)
        {
            this.SetRedstoneOn(var1, var2, var3, var4, true);
            var1.playAuxSFX(2225, var2, var3, var4, 0);
        }
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return (var5 & -9) == 0 ? new ItemStack(Item.cake.itemID, 1, 0) : null;
    }

    private void EatCakeSliceLocal(World var1, int var2, int var3, int var4, EntityPlayer var5)
    {
        if (var5.canEat(true))
        {
            var5.getFoodStats().addStats(4, 4.0F);
            int var6 = this.GetEatState(var1, var2, var3, var4) + 1;

            if (var6 >= 6)
            {
                var1.setBlockWithNotify(var2, var3, var4, 0);
            }
            else
            {
                this.SetEatState(var1, var2, var3, var4, var6);
            }
        }
    }

    public boolean IsRedstoneOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 8) > 0;
    }

    public void SetRedstoneOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -9;

        if (var5)
        {
            var6 |= 8;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int GetEatState(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.getBlockMetadata(var2, var3, var4) & 7;
    }

    public void SetEatState(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & 8;
        var6 |= var5;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }
}
