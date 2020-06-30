package net.minecraft.src;

import java.util.List;

public class FCBlockVanillaCauldron extends BlockCauldron
{
    public FCBlockVanillaCauldron(int var1)
    {
        super(var1);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        AxisAlignedBB var8 = this.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
        var8.AddToListIfIntersects(var5, var6);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderBlockCauldron(this, var2, var3, var4);
    }
}
