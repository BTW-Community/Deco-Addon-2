package net.minecraft.src;

import java.util.Random;

public class FCBlockLadderBase extends Block
{
    protected static final float m_fLadderThickness = 0.125F;
    protected static final AxisAlignedBB m_boxCollision = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);

    protected FCBlockLadderBase(int var1)
    {
        super(var1, Material.circuits);
        this.setHardness(0.4F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyant();
        this.setStepSound(soundLadderFootstep);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcBlockLadder.blockID;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = m_boxCollision.MakeTemporaryCopy();
        var5.RotateAroundJToFacing(this.GetFacing(var1, var2, var3, var4));
        return var5;
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
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 2; var5 <= 5; ++var5)
        {
            if (this.CanAttachToFacing(var1, var2, var3, var4, Block.GetOppositeFacing(var5)))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        if (this.CanAttachToFacing(var1, var2, var3, var4, Block.GetOppositeFacing(var5)))
        {
            var9 = this.SetFacing(var9, var5);
        }
        else
        {
            for (int var10 = 2; var10 <= 5; ++var10)
            {
                if (this.CanAttachToFacing(var1, var2, var3, var4, var10))
                {
                    var9 = this.SetFacing(var9, Block.GetOppositeFacing(var10));
                    break;
                }
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
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (!this.CanAttachToFacing(var1, var2, var3, var4, Block.GetOppositeFacing(this.GetFacing(var6))))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var6, 0);
            var1.setBlockToAir(var2, var3, var4);
        }

        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 8;
    }

    public boolean IsBlockClimbable(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int GetFacing(int var1)
    {
        return (var1 & 3) + 2;
    }

    public int SetFacing(int var1, int var2)
    {
        int var3 = MathHelper.clamp_int(var2, 2, 5) - 2;
        var1 &= -4;
        return var1 | var3;
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

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        int var2 = var1.getItem().GetFilterableProperties(var1);
        return (var2 & 1) == 0;
    }

    public boolean CanMobsSpawnOn(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    protected boolean CanAttachToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 >= 2)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
            return FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var6.i, var6.j, var6.k, Block.GetOppositeFacing(var5));
        }
        else
        {
            return false;
        }
    }
}
