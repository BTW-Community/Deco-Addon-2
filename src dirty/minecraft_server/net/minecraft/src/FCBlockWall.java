package net.minecraft.src;

import java.util.Random;

public class FCBlockWall extends BlockWall
{
    public FCBlockWall(int var1, Block var2)
    {
        super(var1, var2);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 == 0 || var5 == 1;
    }

    public int GetWeightOnPathBlocked(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -3;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        int var4 = var1 & 1;
        return var4 == 0 ? FCBetterThanWolves.fcItemStone.itemID : super.idDropped(var1, var2, var3);
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        int var2 = var1 & 1;
        return var2 == 0 ? 0 : super.damageDropped(var1);
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        int var8 = var5 & 1;

        if (var8 == 0)
        {
            if (!var1.isRemote)
            {
                byte var9 = 4;

                for (int var10 = 0; var10 < var9; ++var10)
                {
                    int var11 = this.idDropped(var5, var1.rand, var7);

                    if (var11 > 0)
                    {
                        this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(var11, 1, this.damageDropped(var5)));
                    }
                }
            }
        }
        else
        {
            super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);
        }
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

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        boolean var5 = this.canConnectWallTo(var1, var2, var3, var4 - 1);
        boolean var6 = this.canConnectWallTo(var1, var2, var3, var4 + 1);
        boolean var7 = this.canConnectWallTo(var1, var2 - 1, var3, var4);
        boolean var8 = this.canConnectWallTo(var1, var2 + 1, var3, var4);
        float var9 = 0.25F;
        float var10 = 0.75F;
        float var11 = 0.25F;
        float var12 = 0.75F;
        float var13 = 1.0F;

        if (var5)
        {
            var11 = 0.0F;
        }

        if (var6)
        {
            var12 = 1.0F;
        }

        if (var7)
        {
            var9 = 0.0F;
        }

        if (var8)
        {
            var10 = 1.0F;
        }

        if (var5 && var6 && !var7 && !var8)
        {
            var13 = 0.8125F;
            var9 = 0.3125F;
            var10 = 0.6875F;
        }
        else if (!var5 && !var6 && var7 && var8)
        {
            var13 = 0.8125F;
            var11 = 0.3125F;
            var12 = 0.6875F;
        }

        return AxisAlignedBB.getAABBPool().getAABB((double)var9, 0.0D, (double)var11, (double)var10, (double)var13, (double)var12);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = this.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4);
        var5.maxY = 1.5D;
        return var5.offset((double)var2, (double)var3, (double)var4);
    }
}
