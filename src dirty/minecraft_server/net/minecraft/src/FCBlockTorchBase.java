package net.minecraft.src;

import java.util.Random;

public class FCBlockTorchBase extends Block
{
    protected FCBlockTorchBase(int var1)
    {
        super(var1, Material.circuits);
        this.setHardness(0.0F);
        this.SetBuoyant();
        this.SetFilterableProperties(4);
        this.setStepSound(soundWoodFootstep);
        this.setTickRandomly(true);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 2;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 2; var5 < 6; ++var5)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);

            if (FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var1, var6.i, var6.j, var6.k, Block.GetOppositeFacing(var5)))
            {
                return true;
            }
        }

        return this.canPlaceTorchOn(var1, var2, var3 - 1, var4);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        if (var5 == 1)
        {
            if (this.canPlaceTorchOn(var1, var2, var3 - 1, var4))
            {
                var9 = SetOrientation(var9, 5);
            }
        }
        else if (var5 != 0)
        {
            FCUtilsBlockPos var10 = new FCUtilsBlockPos(var2, var3, var4, Block.GetOppositeFacing(var5));

            if (FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var1, var10.i, var10.j, var10.k, var5))
            {
                var9 = SetOrientation(var9, 6 - var5);
            }
        }

        return var9;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        super.updateTick(var1, var2, var3, var4, var5);

        if (this.GetOrientation(var1, var2, var3, var4) == 0)
        {
            this.onBlockAdded(var1, var2, var3, var4);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        if (this.GetOrientation(var1, var2, var3, var4) == 0)
        {
            for (int var5 = 1; var5 < 6; ++var5)
            {
                FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, Block.GetOppositeFacing(var5));

                if (FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var1, var6.i, var6.j, var6.k, var5))
                {
                    this.SetOrientation(var1, var2, var3, var4, 6 - var5);
                    return;
                }
            }

            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        this.ValidateState(var1, var2, var3, var4, var5);
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetOrientation(var1, var2, var3, var4);
        float var6 = 0.15F;

        if (var5 == 1)
        {
            return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.20000000298023224D, (double)(0.5F - var6), (double)(var6 * 2.0F), 0.800000011920929D, (double)(0.5F + var6));
        }
        else if (var5 == 2)
        {
            return AxisAlignedBB.getAABBPool().getAABB((double)(1.0F - var6 * 2.0F), 0.20000000298023224D, (double)(0.5F - var6), 1.0D, 0.800000011920929D, (double)(0.5F + var6));
        }
        else if (var5 == 3)
        {
            return AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var6), 0.20000000298023224D, 0.0D, (double)(0.5F + var6), 0.800000011920929D, (double)(var6 * 2.0F));
        }
        else if (var5 == 4)
        {
            return AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var6), 0.20000000298023224D, (double)(1.0F - var6 * 2.0F), (double)(0.5F + var6), 0.800000011920929D, 1.0D);
        }
        else
        {
            var6 = 0.1F;
            return AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var6), 0.0D, (double)(0.5F - var6), (double)(0.5F + var6), 0.6000000238418579D, (double)(0.5F + var6));
        }
    }

    public boolean IsBlockRestingOnThatBelow(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetOrientation(var1, var2, var3, var4) == 5;
    }

    public int GetFacing(int var1)
    {
        return MathHelper.clamp_int(6 - GetOrientation(var1), 1, 5);
    }

    public int SetFacing(int var1, int var2)
    {
        var2 = MathHelper.clamp_int(var2, 1, 5);
        return SetOrientation(var1, 6 - var2);
    }

    public boolean CanRotateAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 == Block.GetOppositeFacing(this.GetFacing(var1, var2, var3, var4));
    }

    public int GetNewMetadataRotatedAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var1.getBlockMetadata(var2, var3, var4);
        return this.SetFacing(var7, Block.GetOppositeFacing(var6));
    }

    public void OnNeighborDisrupted(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 == Block.GetOppositeFacing(this.GetFacing(var1, var2, var3, var4)))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    protected boolean canPlaceTorchOn(World var1, int var2, int var3, int var4)
    {
        return FCUtilsWorld.DoesBlockHaveSmallCenterHardpointToFacing(var1, var2, var3, var4, 1, true);
    }

    protected boolean ValidateState(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetOrientation(var1, var2, var3, var4);
        boolean var7 = false;

        if (var6 != 0)
        {
            int var10 = 6 - var6;
            boolean var8 = false;

            if (var10 == 1)
            {
                if (!this.canPlaceTorchOn(var1, var2, var3 - 1, var4))
                {
                    var8 = true;
                }
            }
            else
            {
                FCUtilsBlockPos var9 = new FCUtilsBlockPos(var2, var3, var4, Block.GetOppositeFacing(var10));

                if (!FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var1, var9.i, var9.j, var9.k, var10))
                {
                    var8 = true;
                }
            }

            if (var8)
            {
                this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
                var1.setBlockWithNotify(var2, var3, var4, 0);
                return true;
            }
        }

        return false;
    }

    public int GetOrientation(IBlockAccess var1, int var2, int var3, int var4)
    {
        return GetOrientation(var1.getBlockMetadata(var2, var3, var4));
    }

    public static int GetOrientation(int var0)
    {
        return var0 & 7;
    }

    public void SetOrientation(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = SetOrientation(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public static int SetOrientation(int var0, int var1)
    {
        var0 &= -8;
        return var0 | var1;
    }

    public boolean IsRainingOnTorch(World var1, int var2, int var3, int var4)
    {
        return var1.isRaining() && var1.IsRainingAtPos(var2, var3, var4);
    }
}
