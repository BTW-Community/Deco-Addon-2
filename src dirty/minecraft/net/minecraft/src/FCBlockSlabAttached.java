package net.minecraft.src;

public abstract class FCBlockSlabAttached extends FCBlockSlab
{
    protected FCBlockSlabAttached(int var1, Material var2)
    {
        super(var1, var2);
    }

    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 != 0 && var5 != 1)
        {
            if (this.HasValidAnchorToFacing(var1, var2, var3, var4, 0) || this.HasValidAnchorToFacing(var1, var2, var3, var4, 1))
            {
                return super.canPlaceBlockOnSide(var1, var2, var3, var4, var5);
            }
        }
        else if (this.HasValidAnchorToFacing(var1, var2, var3, var4, Block.GetOppositeFacing(var5)))
        {
            return super.canPlaceBlockOnSide(var1, var2, var3, var4, var5);
        }

        return false;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        if (var5 == 0)
        {
            var9 = this.SetIsUpsideDown(var9, true);
        }
        else if (var5 != 1)
        {
            if ((double)var7 > 0.5D)
            {
                if (this.HasValidAnchorToFacing(var1, var2, var3, var4, 1))
                {
                    var9 = this.SetIsUpsideDown(var9, true);
                }
            }
            else if (!this.HasValidAnchorToFacing(var1, var2, var3, var4, 0))
            {
                var9 = this.SetIsUpsideDown(var9, true);
            }
        }

        return var9;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        byte var6 = 0;

        if (this.GetIsUpsideDown(var1.getBlockMetadata(var2, var3, var4)))
        {
            var6 = 1;
        }

        if (!this.HasValidAnchorToFacing(var1, var2, var3, var4, var6))
        {
            this.OnAnchorBlockLost(var1, var2, var3, var4);
            var1.setBlockToAir(var2, var3, var4);
        }
    }

    protected boolean HasValidAnchorToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
        return FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var6.i, var6.j, var6.k, Block.GetOppositeFacing(var5), true);
    }

    protected abstract void OnAnchorBlockLost(World var1, int var2, int var3, int var4);
}
