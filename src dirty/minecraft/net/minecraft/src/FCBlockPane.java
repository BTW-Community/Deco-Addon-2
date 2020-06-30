package net.minecraft.src;

import java.util.List;

public class FCBlockPane extends BlockPane
{
    protected FCBlockPane(int var1, String var2, String var3, Material var4, boolean var5)
    {
        super(var1, var2, var3, var4, var5);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        float var5 = 0.4375F;
        float var6 = 0.5625F;
        float var7 = 0.4375F;
        float var8 = 0.5625F;
        boolean var9 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2, var3, var4 - 1));
        boolean var10 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2, var3, var4 + 1));
        boolean var11 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2 - 1, var3, var4));
        boolean var12 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2 + 1, var3, var4));

        if ((!var11 || !var12) && (var11 || var12 || var9 || var10))
        {
            if (var11 && !var12)
            {
                var5 = 0.0F;
            }
            else if (!var11 && var12)
            {
                var6 = 1.0F;
            }
        }
        else
        {
            var5 = 0.0F;
            var6 = 1.0F;
        }

        if ((!var9 || !var10) && (var11 || var12 || var9 || var10))
        {
            if (var9 && !var10)
            {
                var7 = 0.0F;
            }
            else if (!var9 && var10)
            {
                var8 = 1.0F;
            }
        }
        else
        {
            var7 = 0.0F;
            var8 = 1.0F;
        }

        return AxisAlignedBB.getAABBPool().getAABB((double)var5, 0.0D, (double)var7, (double)var6, 1.0D, (double)var8);
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        boolean var8 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2, var3, var4 - 1));
        boolean var9 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2, var3, var4 + 1));
        boolean var10 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2 - 1, var3, var4));
        boolean var11 = this.canThisPaneConnectToThisBlockID(var1.getBlockId(var2 + 1, var3, var4));
        AxisAlignedBB var12;

        if ((!var10 || !var11) && (var10 || var11 || var8 || var9))
        {
            if (var10 && !var11)
            {
                var12 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.4375D, 0.5D, 1.0D, 0.5625D).offset((double)var2, (double)var3, (double)var4);
                var12.AddToListIfIntersects(var5, var6);
            }
            else if (!var10 && var11)
            {
                var12 = AxisAlignedBB.getAABBPool().getAABB(0.5D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D).offset((double)var2, (double)var3, (double)var4);
                var12.AddToListIfIntersects(var5, var6);
            }
        }
        else
        {
            var12 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D).offset((double)var2, (double)var3, (double)var4);
            var12.AddToListIfIntersects(var5, var6);
        }

        if ((!var8 || !var9) && (var10 || var11 || var8 || var9))
        {
            if (var8 && !var9)
            {
                var12 = AxisAlignedBB.getAABBPool().getAABB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5D).offset((double)var2, (double)var3, (double)var4);
                var12.AddToListIfIntersects(var5, var6);
            }
            else if (!var8 && var9)
            {
                var12 = AxisAlignedBB.getAABBPool().getAABB(0.4375D, 0.0D, 0.5D, 0.5625D, 1.0D, 1.0D).offset((double)var2, (double)var3, (double)var4);
                var12.AddToListIfIntersects(var5, var6);
            }
        }
        else
        {
            var12 = AxisAlignedBB.getAABBPool().getAABB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D).offset((double)var2, (double)var3, (double)var4);
            var12.AddToListIfIntersects(var5, var6);
        }
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender() {}

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderBlockPane(this, var2, var3, var4);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockId(var2, var3, var4);
        return var6 != this.blockID && this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }
}
