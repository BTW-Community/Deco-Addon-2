package net.minecraft.src;

import java.util.Random;

public class FCBlockToolPlaced extends BlockContainer
{
    private static final double m_dBoundingThickness = 0.125D;
    private static final double m_dBoundingHalfThickness = 0.0625D;

    public FCBlockToolPlaced(int var1)
    {
        super(var1, Material.circuits);
        this.setHardness(0.05F);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockToolPlaced");
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityToolPlaced();
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCTileEntityToolPlaced var7 = (FCTileEntityToolPlaced)var1.getBlockTileEntity(var2, var3, var4);
        var7.EjectContents();
        super.breakBlock(var1, var2, var3, var4, var5, var6);
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
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return false;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        return this.SetFacing(var9, var5);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetAttachedToFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4, var6);

        if (!FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var1, var7.i, var7.j, var7.k, Block.GetOppositeFacing(var6), true))
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        FCTileEntityToolPlaced var10 = (FCTileEntityToolPlaced)var1.getBlockTileEntity(var2, var3, var4);
        ItemStack var11 = var10.GetToolStack();

        if (var11 != null)
        {
            FCUtilsItem.GivePlayerStackOrEjectFavorEmptyHand(var5, var11, var2, var3, var4);
            var10.SetToolStack((ItemStack)null);
            var1.setBlockWithNotify(var2, var3, var4, 0);
            return true;
        }
        else
        {
            return false;
        }
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
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        int var6 = this.GetFacing(var5);
        int var7 = this.GetVerticalOrientation(var5);
        float var8 = 0.75F;
        float var9 = 0.75F;
        FCTileEntityToolPlaced var10 = (FCTileEntityToolPlaced)var1.getBlockTileEntity(var2, var3, var4);

        if (var10 != null)
        {
            ItemStack var11 = var10.GetToolStack();

            if (var11 != null)
            {
                var8 = ((FCItemTool)var11.getItem()).GetBlockBoundingBoxHeight();
                var9 = ((FCItemTool)var11.getItem()).GetBlockBoundingBoxWidth();
            }
        }

        double var23 = (double)(0.5F - var9 / 2.0F);
        double var13 = (double)(0.5F + var9 / 2.0F);
        double var15 = var23;
        double var17 = var13;
        double var19 = var23;
        double var21 = var13;

        if (var6 < 4)
        {
            var23 = 0.4375D;
            var13 = 0.5625D;
        }
        else
        {
            var15 = 0.4375D;
            var17 = 0.5625D;
        }

        if (var7 == 0)
        {
            var19 = 0.0D;
            var21 = (double)var8;
        }
        else if (var7 == 1)
        {
            var19 = (double)(1.0F - var8);
            var21 = 1.0D;
        }
        else if (var6 == 2)
        {
            var15 = 0.0D;
            var17 = (double)var8;
        }
        else if (var6 == 3)
        {
            var15 = (double)(1.0F - var8);
            var17 = 1.0D;
        }
        else if (var6 == 4)
        {
            var23 = 0.0D;
            var13 = (double)var8;
        }
        else if (var6 == 5)
        {
            var23 = (double)(1.0F - var8);
            var13 = 1.0D;
        }

        return AxisAlignedBB.getAABBPool().getAABB(var23, var19, var15, var13, var21, var17);
    }

    public boolean CanBeCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        return true;
    }

    public int GetFacing(int var1)
    {
        return (var1 & 3) + 2;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= -4;
        var1 |= MathHelper.clamp_int(var2, 2, 5) - 2;
        return var1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanRotateAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        return this.GetVerticalOrientation(var6) == 2 ? var5 == this.GetFacing(var6) : false;
    }

    public boolean OnRotatedAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        var1.setBlockToAir(var2, var3, var4);
        return false;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }

    public void OnNeighborDisrupted(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 == this.GetAttachedToFacing(var1, var2, var3, var4))
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    public int GetVerticalOrientation(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetVerticalOrientation(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetVerticalOrientation(int var1)
    {
        return (var1 & 12) >> 2;
    }

    public void SetVerticalOrientation(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetVerticalOrientation(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetVerticalOrientation(int var1, int var2)
    {
        var1 &= -13;
        var1 |= var2 << 2;
        return var1;
    }

    protected int GetAttachedToFacing(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetVerticalOrientation(var1, var2, var3, var4);

        if (var5 >= 2)
        {
            var5 = this.GetFacing(var1, var2, var3, var4);
        }

        return var5;
    }
}
