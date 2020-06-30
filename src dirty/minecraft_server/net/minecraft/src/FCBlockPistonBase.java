package net.minecraft.src;

import java.util.List;

public class FCBlockPistonBase extends BlockPistonBase
{
    public FCBlockPistonBase(int var1, boolean var2)
    {
        super(var1, var2);
        this.SetPicksEffectiveOn(true);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    }

    public boolean CanContainPistonPackingToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        return !isExtended(var6) || Block.GetOppositeFacing(getOrientation(var6)) == var5;
    }

    public int GetFacing(int var1)
    {
        return var1 & 7;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= -8;
        return var1 | var2;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !isExtended(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !isExtended(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !isExtended(var1.getBlockMetadata(var2, var3, var4));
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        return !isExtended(var1) ? super.RotateMetadataAroundJAxis(var1, var2) : var1;
    }

    public boolean CanBlockBePushedByPiston(World var1, int var2, int var3, int var4, int var5)
    {
        return !isExtended(var1.getBlockMetadata(var2, var3, var4));
    }

    /**
     * handles attempts to extend or retract the piston.
     */
    protected void updatePistonState(World var1, int var2, int var3, int var4)
    {
        this.ValidatePistonState(var1, var2, var3, var4);
        super.updatePistonState(var1, var2, var3, var4);
    }

    /**
     * checks to see if this piston could push the blocks in front of it.
     */
    protected boolean canExtend(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var2 + Facing.offsetsXForSide[var5];
        int var7 = var3 + Facing.offsetsYForSide[var5];
        int var8 = var4 + Facing.offsetsZForSide[var5];
        int var9 = 0;

        while (var9 < 13)
        {
            if (var7 > 0 && var7 < 255)
            {
                Block var10 = blocksList[var1.getBlockId(var6, var7, var8)];

                if (var10 != null)
                {
                    if (!var10.CanBlockBePushedByPiston(var1, var6, var7, var8, var5))
                    {
                        return false;
                    }

                    int var11 = var10.getMobilityFlag();
                    int var12 = this.GetPistonShovelEjectionDirection(var1, var6, var7, var8, var5);

                    if (var11 != 1 && var12 < 0)
                    {
                        if (var9 == 12)
                        {
                            return false;
                        }

                        var6 += Facing.offsetsXForSide[var5];
                        var7 += Facing.offsetsYForSide[var5];
                        var8 += Facing.offsetsZForSide[var5];
                        ++var9;
                        continue;
                    }
                }

                return true;
            }

            return false;
        }

        return true;
    }

    /**
     * attempts to extend the piston. returns false if impossible.
     */
    protected boolean tryExtend(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var2 + Facing.offsetsXForSide[var5];
        int var7 = var3 + Facing.offsetsYForSide[var5];
        int var8 = var4 + Facing.offsetsZForSide[var5];
        int var9 = 0;

        while (true)
        {
            int var10;
            int var12;
            int var13;
            int var15;
            int var16;
            int var17;

            if (var9 < 13)
            {
                if (var7 <= 0 || var7 >= 255)
                {
                    return false;
                }

                var10 = var1.getBlockId(var6, var7, var8);
                Block var11 = blocksList[var10];

                if (var11 != null)
                {
                    if (!var11.CanBlockBePushedByPiston(var1, var6, var7, var8, var5))
                    {
                        return false;
                    }

                    var12 = var11.getMobilityFlag();
                    var13 = this.GetPistonShovelEjectionDirection(var1, var6, var7, var8, var5);

                    if (var12 != 1 && var13 < 0)
                    {
                        if (var9 == 12)
                        {
                            return false;
                        }

                        var6 += Facing.offsetsXForSide[var5];
                        var7 += Facing.offsetsYForSide[var5];
                        var8 += Facing.offsetsZForSide[var5];
                        ++var9;
                        continue;
                    }

                    int var14 = var1.getBlockMetadata(var6, var7, var8);

                    if (var13 >= 0)
                    {
                        var14 = var11.AdjustMetadataForPistonMove(var14);
                        var15 = var6 + Facing.offsetsXForSide[var13];
                        var16 = var7 + Facing.offsetsYForSide[var13];
                        var17 = var8 + Facing.offsetsZForSide[var13];
                        this.OnShovelEjectIntoBlock(var1, var15, var16, var17);
                        var1.setBlock(var15, var16, var17, Block.pistonMoving.blockID, var14, 4);
                        var1.setBlockTileEntity(var15, var16, var17, FCBlockPistonMoving.GetShoveledTileEntity(var10, var14, var13));
                    }
                    else
                    {
                        var11.OnBrokenByPistonPush(var1, var6, var7, var8, var14);
                    }

                    var1.setBlockToAir(var6, var7, var8);
                }
            }

            var10 = var6;
            int var20 = var7;
            var12 = var8;
            var13 = 0;
            int[] var21;

            for (var21 = new int[13]; var6 != var2 || var7 != var3 || var8 != var4; var8 = var17)
            {
                var15 = var6 - Facing.offsetsXForSide[var5];
                var16 = var7 - Facing.offsetsYForSide[var5];
                var17 = var8 - Facing.offsetsZForSide[var5];
                int var18 = var1.getBlockId(var15, var16, var17);
                int var19 = var1.getBlockMetadata(var15, var16, var17);

                if (var18 == this.blockID && var15 == var2 && var16 == var3 && var17 == var4)
                {
                    var1.setBlock(var6, var7, var8, Block.pistonMoving.blockID, var5 | (this.isSticky ? 8 : 0), 4);
                    var1.setBlockTileEntity(var6, var7, var8, BlockPistonMoving.getTileEntity(Block.pistonExtension.blockID, var5 | (this.isSticky ? 8 : 0), var5, true, false));
                }
                else
                {
                    if (Block.blocksList[var18] != null)
                    {
                        var19 = Block.blocksList[var18].AdjustMetadataForPistonMove(var19);
                    }

                    var1.setBlock(var6, var7, var8, Block.pistonMoving.blockID, var19, 4);
                    var1.setBlockTileEntity(var6, var7, var8, BlockPistonMoving.getTileEntity(var18, var19, var5, true, false));
                }

                var21[var13++] = var18;
                var6 = var15;
                var7 = var16;
            }

            var6 = var10;
            var7 = var20;
            var8 = var12;

            for (var13 = 0; var6 != var2 || var7 != var3 || var8 != var4; var8 = var17)
            {
                var15 = var6 - Facing.offsetsXForSide[var5];
                var16 = var7 - Facing.offsetsYForSide[var5];
                var17 = var8 - Facing.offsetsZForSide[var5];
                var1.notifyBlocksOfNeighborChange(var15, var16, var17, var21[var13++]);
                var6 = var15;
                var7 = var16;
            }

            return true;
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

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if (isExtended(var5))
        {
            switch (getOrientation(var5))
            {
                case 0:
                    return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.25D, 0.0D, 1.0D, 1.0D, 1.0D);

                case 1:
                    return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);

                case 2:
                    return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D);

                case 3:
                    return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D);

                case 4:
                    return AxisAlignedBB.getAABBPool().getAABB(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

                case 5:
                    return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 1.0D);
            }
        }

        return super.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4);
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        this.getCollisionBoundingBoxFromPool(var1, var2, var3, var4).AddToListIfIntersects(var5, var6);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return this.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4).offset((double)var2, (double)var3, (double)var4);
    }

    public boolean CanSupportFallingBlocks(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        return GetOppositeFacing(var5);
    }

    public int PreBlockPlacedBy(World var1, int var2, int var3, int var4, int var5, EntityLiving var6)
    {
        return determineOrientation(var1, var2, var3, var4, var6);
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6) {}

    /**
     * Called after a block is placed
     */
    public void onPostBlockPlaced(World var1, int var2, int var3, int var4, int var5)
    {
        if (!var1.isRemote)
        {
            this.updatePistonState(var1, var2, var3, var4);
        }
    }

    protected void ValidatePistonState(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if (!isExtended(var5))
        {
            int var6 = getOrientation(var5);
            FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4, var6);
            int var8 = var1.getBlockId(var7.i, var7.j, var7.k);

            if (var8 == Block.pistonExtension.blockID)
            {
                int var9 = var1.getBlockMetadata(var7.i, var7.j, var7.k);

                if (BlockPistonExtension.getDirectionMeta(var9) == var6)
                {
                    var1.SetBlockMetadataWithNotify(var2, var3, var4, var5 | 8, 2);
                }
            }
        }
    }

    protected int GetPistonShovelEjectionDirection(World var1, int var2, int var3, int var4, int var5)
    {
        Block var6 = Block.blocksList[var1.getBlockId(var2, var3, var4)];

        if (var6 != null && var6.CanBePistonShoveled(var1, var2, var3, var4))
        {
            int var7 = Block.GetOppositeFacing(var5);
            int var8 = var2 + Facing.offsetsXForSide[var7];
            int var9 = var3 + Facing.offsetsYForSide[var7];
            int var10 = var4 + Facing.offsetsZForSide[var7];
            Block var11 = Block.blocksList[var1.getBlockId(var8, var9, var10)];

            if (var11 != null)
            {
                int var12 = var11.GetPistonShovelEjectDirection(var1, var8, var9, var10, var5);

                if (var12 >= 0 && this.CanShovelEjectToFacing(var1, var2, var3, var4, var12))
                {
                    return var12;
                }
            }
        }

        return -1;
    }

    protected boolean CanShovelEjectToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var2 + Facing.offsetsXForSide[var5];
        int var7 = var3 + Facing.offsetsYForSide[var5];
        int var8 = var4 + Facing.offsetsZForSide[var5];
        Block var9 = Block.blocksList[var1.getBlockId(var6, var7, var8)];
        return var9 != null ? var9.getMobilityFlag() == 1 : true;
    }

    protected void OnShovelEjectIntoBlock(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3, var4)];

        if (var5 != null && var5.getMobilityFlag() == 1)
        {
            var5.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockToAir(var2, var3, var4);
        }
    }
}
