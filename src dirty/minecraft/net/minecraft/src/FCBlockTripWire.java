package net.minecraft.src;

public class FCBlockTripWire extends BlockTripWire
{
    public FCBlockTripWire(int var1)
    {
        super(var1);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.15625D, 1.0D);
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (var5.CanEntityTriggerTripwire())
        {
            super.onEntityCollidedWithBlock(var1, var2, var3, var4, var5);
        }
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        boolean var6 = (var5 & 4) == 4;
        boolean var7 = (var5 & 2) == 2;
        return !var7 ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.09375D, 1.0D) : (!var6 ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0625D, 0.0D, 1.0D, 0.15625D, 1.0D));
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderBlockTripWire(this, var2, var3, var4);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }
}
