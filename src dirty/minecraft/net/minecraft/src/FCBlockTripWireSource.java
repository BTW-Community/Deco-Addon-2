package net.minecraft.src;

public class FCBlockTripWireSource extends BlockTripWireSource
{
    public FCBlockTripWireSource(int var1)
    {
        super(var1);
        this.setUnlocalizedName("tripWireSource");
        this.setCreativeTab((CreativeTabs)null);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4) & 3;
        float var6 = 0.1875F;
        return var5 == 3 ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.20000000298023224D, (double)(0.5F - var6), (double)(var6 * 2.0F), 0.800000011920929D, (double)(0.5F + var6)) : (var5 == 1 ? AxisAlignedBB.getAABBPool().getAABB((double)(1.0F - var6 * 2.0F), 0.20000000298023224D, (double)(0.5F - var6), 1.0D, 0.800000011920929D, (double)(0.5F + var6)) : (var5 == 0 ? AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var6), 0.20000000298023224D, 0.0D, (double)(0.5F + var6), 0.800000011920929D, (double)(var6 * 2.0F)) : AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var6), 0.20000000298023224D, (double)(1.0F - var6 * 2.0F), (double)(0.5F + var6), 0.800000011920929D, 1.0D)));
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderBlockTripWireSource(this, var2, var3, var4);
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
