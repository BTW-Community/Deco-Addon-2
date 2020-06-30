package net.minecraft.src;

public class FCBlockPistonMoving extends BlockPistonMoving
{
    public FCBlockPistonMoving(int var1)
    {
        super(var1);
    }

    public AxisAlignedBB getAxisAlignedBB(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (var5 != 0 && var5 != this.blockID)
        {
            AxisAlignedBB var8 = Block.blocksList[var5].GetAsPistonMovingBoundingBox(var1, var2, var3, var4);

            if (var8 == null)
            {
                return null;
            }
            else
            {
                if (Facing.offsetsXForSide[var7] < 0)
                {
                    var8.minX -= (double)((float)Facing.offsetsXForSide[var7] * var6);
                }
                else
                {
                    var8.maxX -= (double)((float)Facing.offsetsXForSide[var7] * var6);
                }

                if (Facing.offsetsYForSide[var7] < 0)
                {
                    var8.minY -= (double)((float)Facing.offsetsYForSide[var7] * var6);
                }
                else
                {
                    var8.maxY -= (double)((float)Facing.offsetsYForSide[var7] * var6);
                }

                if (Facing.offsetsZForSide[var7] < 0)
                {
                    var8.minZ -= (double)((float)Facing.offsetsZForSide[var7] * var6);
                }
                else
                {
                    var8.maxZ -= (double)((float)Facing.offsetsZForSide[var7] * var6);
                }

                return var8;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        TileEntity var5 = var1.getBlockTileEntity(var2, var3, var4);

        if (var5 != null && var5 instanceof TileEntityPiston)
        {
            TileEntityPiston var6 = (TileEntityPiston)var5;
            Block var7 = Block.blocksList[var6.getStoredBlockID()];

            if (var7 != null || var7 != this)
            {
                AxisAlignedBB var8 = var7.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4);
                float var9 = var6.getProgress(0.0F);

                if (var6.isExtending())
                {
                    var9 = 1.0F - var9;
                }

                int var10 = var6.getPistonOrientation();
                var8.minX -= (double)((float)Facing.offsetsXForSide[var10] * var9);
                var8.minY -= (double)((float)Facing.offsetsYForSide[var10] * var9);
                var8.minZ -= (double)((float)Facing.offsetsZForSide[var10] * var9);
                var8.maxX -= (double)((float)Facing.offsetsXForSide[var10] * var9);
                var8.maxY -= (double)((float)Facing.offsetsYForSide[var10] * var9);
                var8.maxZ -= (double)((float)Facing.offsetsZForSide[var10] * var9);
                return var8;
            }
        }

        return super.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4);
    }

    public boolean CanSupportFallingBlocks(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    public static TileEntity GetShoveledTileEntity(int var0, int var1, int var2)
    {
        return new TileEntityPiston(var0, var1, var2, true, false, true);
    }
}
