package net.minecraft.src;

import java.util.List;

public class FCBlockPistonExtension extends BlockPistonExtension
{
    public FCBlockPistonExtension(int var1)
    {
        super(var1);
        this.SetPicksEffectiveOn(true);
    }

    public AxisAlignedBB GetAsPistonMovingBoundingBox(World var1, int var2, int var3, int var4)
    {
        return this.GetFixedBlockBoundsFromPool().offset((double)var2, (double)var3, (double)var4);
    }

    public boolean CanContainPistonPackingToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        return BlockPistonExtension.getDirectionMeta(var6) == var5;
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        int var8 = getDirectionMeta(var1.getBlockMetadata(var2, var3, var4));
        AxisAlignedBB var9 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.75D, 0.0D, 1.0D, 1.0D, 1.0D);
        var9.TiltToFacingAlongJ(var8);
        var9.offset((double)var2, (double)var3, (double)var4);
        var9.AddToListIfIntersects(var5, var6);
        var9 = AxisAlignedBB.getAABBPool().getAABB(0.375D, 0.0D, 0.375D, 0.625D, 0.75D, 0.625D);
        var9.TiltToFacingAlongJ(var8);
        var9.offset((double)var2, (double)var3, (double)var4);
        var9.AddToListIfIntersects(var5, var6);
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = getDirectionMeta(var1.getBlockMetadata(var2, var3, var4));
        AxisAlignedBB var6 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.75D, 0.0D, 1.0D, 1.0D, 1.0D);
        var6.TiltToFacingAlongJ(var5);
        return var6;
    }

    public boolean CanSupportFallingBlocks(IBlockAccess var1, int var2, int var3, int var4)
    {
        return getDirectionMeta(var1.getBlockMetadata(var2, var3, var4)) == 1;
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderPistonExtension(this, var2, var3, var4, true);
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
