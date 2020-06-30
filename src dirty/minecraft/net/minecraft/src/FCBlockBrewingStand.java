package net.minecraft.src;

import java.util.List;

public class FCBlockBrewingStand extends BlockBrewingStand
{
    protected static final double m_dBaseHeight = 0.125D;
    protected static final double m_dBaseWidth = 0.875D;
    protected static final double m_dBaseHalfWidth = 0.4375D;
    protected static final double m_dCenterColumnWidth = 0.125D;
    protected static final double m_dCenterColumnHalfWidth = 0.0625D;
    protected static final double m_dCenterAssemblyWidth = 0.625D;
    protected static final double m_dCenterAssemblyHalfWidth = 0.3125D;

    public FCBlockBrewingStand(int var1)
    {
        super(var1);
    }

    public boolean DoesBlockHopperInsert(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        AxisAlignedBB var8 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D).offset((double)var2, (double)var3, (double)var4);
        var8.AddToListIfIntersects(var5, var6);
        var8 = AxisAlignedBB.getAABBPool().getAABB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D).offset((double)var2, (double)var3, (double)var4);
        var8.AddToListIfIntersects(var5, var6);
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.125D, 0.9375D);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.1875D, 0.125D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
        return var7.GetFirstIntersection();
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        return var1.RenderBlockBrewingStand(this, var2, var3, var4);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return AxisAlignedBB.getAABBPool().getAABB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D).offset((double)var2, (double)var3, (double)var4);
    }
}
