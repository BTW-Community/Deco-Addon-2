package net.minecraft.src;

public class FCBlockButton extends BlockButton
{
    protected FCBlockButton(int var1, boolean var2)
    {
        super(var1, var2);
    }

    public int GetFacing(int var1)
    {
        return MathHelper.clamp_int(6 - (var1 & 7), 2, 5);
    }

    public boolean CanRotateAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 == Block.GetOppositeFacing(this.GetFacing(var1, var2, var3, var4));
    }

    public boolean OnRotatedAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
        var1.setBlockToAir(var2, var3, var4);
        return false;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
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
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        int var6 = var5 & 7;
        boolean var7 = (var5 & 8) > 0;
        float var8 = 0.375F;
        float var9 = 0.625F;
        float var10 = 0.1875F;
        float var11 = 0.125F;

        if (var7)
        {
            var11 = 0.0625F;
        }

        return var6 == 1 ? AxisAlignedBB.getAABBPool().getAABB(0.0D, (double)var8, (double)(0.5F - var10), (double)var11, (double)var9, (double)(0.5F + var10)) : (var6 == 2 ? AxisAlignedBB.getAABBPool().getAABB((double)(1.0F - var11), (double)var8, (double)(0.5F - var10), 1.0D, (double)var9, (double)(0.5F + var10)) : (var6 == 3 ? AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var10), (double)var8, 0.0D, (double)(0.5F + var10), (double)var9, (double)var11) : AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var10), (double)var8, (double)(1.0F - var11), (double)(0.5F + var10), (double)var9, 1.0D)));
    }
}
