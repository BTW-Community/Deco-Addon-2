package net.minecraft.src;

import java.util.List;

public class FCBlockEndPortalFrame extends BlockEndPortalFrame
{
    public FCBlockEndPortalFrame(int var1)
    {
        super(var1);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        AxisAlignedBB var8 = this.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
        var8.AddToListIfIntersects(var5, var6);

        if (isEnderEyeInserted(var1.getBlockMetadata(var2, var3, var4)))
        {
            var8 = AxisAlignedBB.getAABBPool().getAABB(0.3125D, 0.8125D, 0.3125D, 0.6875D, 1.0D, 0.6875D).offset((double)var2, (double)var3, (double)var4);
            var8.AddToListIfIntersects(var5, var6);
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        var7.AddBoxWithLocalCoordsToIntersectionList(this.GetFixedBlockBoundsFromPool());

        if (isEnderEyeInserted(var1.getBlockMetadata(var2, var3, var4)))
        {
            var7.AddBoxWithLocalCoordsToIntersectionList(0.25D, 0.8125D, 0.25D, 0.75D, 1.0D, 0.75D);
        }

        return var7.GetFirstIntersection();
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        return var1.RenderBlockEndPortalFrame(this, var2, var3, var4);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 != 1 ? !var1.isBlockOpaqueCube(var2, var3, var4) : true;
    }
}
