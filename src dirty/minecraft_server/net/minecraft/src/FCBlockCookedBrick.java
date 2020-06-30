package net.minecraft.src;

import java.util.Random;

public class FCBlockCookedBrick extends Block
{
    public static final double m_dBrickHeight = 0.25D;
    public static final double m_dBrickWidth = 0.375D;
    public static final double m_dBrickHalfWidth = 0.1875D;
    public static final double m_dBrickLength = 0.75D;
    public static final double m_dBrickHalfLength = 0.375D;

    public FCBlockCookedBrick(int var1)
    {
        super(var1, Material.circuits);
        this.setHardness(0.0F);
        this.SetPicksEffectiveOn(true);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockCookedBrick");
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        return this.SetIAligned(var9, this.IsFacingIAligned(var5));
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(var5);
        this.SetIAligned(var1, var2, var3, var4, this.IsFacingIAligned(var7));
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.brick.itemID;
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
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsIAligned(var1, var2, var3, var4) ? AxisAlignedBB.getAABBPool().getAABB(0.125D, 0.0D, 0.3125D, 0.875D, 0.25D, 0.6875D) : AxisAlignedBB.getAABBPool().getAABB(0.3125D, 0.0D, 0.125D, 0.6875D, 0.25D, 0.875D);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
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

    public int GetFacing(int var1)
    {
        return this.GetIsIAligned(var1) ? 4 : 2;
    }

    public int SetFacing(int var1, int var2)
    {
        return this.SetIAligned(var1, this.IsFacingIAligned(var2));
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        return this.SetIAligned(var1, !this.GetIsIAligned(var1));
    }

    public void SetIAligned(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetIAligned(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetIAligned(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 1;
        }
        else
        {
            var1 &= -2;
        }

        return var1;
    }

    public boolean GetIsIAligned(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsIAligned(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean GetIsIAligned(int var1)
    {
        return (var1 & 1) != 0;
    }

    public boolean IsFacingIAligned(int var1)
    {
        return var1 >= 4;
    }
}
