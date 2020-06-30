package net.minecraft.src;

import java.util.Random;

public class FCBlockStakeString extends Block
{
    public static final double m_dHeight = 0.015625D;
    public static final double m_dHalfHeight = 0.0078125D;
    public static final double m_dSelectionBoxHeight = 0.0625D;
    public static final double m_dSelectionBoxHalfHeight = 0.03125D;
    private static final long m_lMinTimeBetweenLengthDisplays = 200L;
    static long m_lTimeOfLastLengthDisplay = 0L;
    static int m_iLengthOfLastLengthDisplay = 0;

    public FCBlockStakeString(int var1)
    {
        super(var1, Material.circuits);
        this.SetAxesEffectiveOn(true);
        this.SetFireProperties(FCEnumFlammability.EXTREME);
        this.setStepSound(soundClothFootstep);
        this.setUnlocalizedName("fcBlockStakeString");
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
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        MovingObjectPosition var7 = null;
        MovingObjectPosition[] var8 = new MovingObjectPosition[8];
        int var9 = 0;

        for (int var10 = 0; var10 < 3; ++var10)
        {
            if (this.GetExtendsAlongAxis(var1, var2, var3, var4, var10))
            {
                Vec3 var11 = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
                Vec3 var12 = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
                this.GetBlockBoundsForAxis(var10, var11, var12, 0.03125D);
                var8[var9] = FCUtilsMisc.RayTraceWithBox(var1, var2, var3, var4, var11, var12, var5, var6);

                if (var8[var9] != null)
                {
                    ++var9;
                }
            }
        }

        if (var9 > 0)
        {
            --var9;

            for (double var14 = 0.0D; var9 >= 0; --var9)
            {
                double var15 = var8[var9].hitVec.squareDistanceTo(var6);

                if (var15 > var14)
                {
                    var7 = var8[var9];
                    var14 = var15;
                }
            }
        }

        return var7;
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
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.silk.itemID;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (!var1.isRemote)
        {
            for (int var8 = 0; var8 < 3; ++var8)
            {
                if (this.GetExtendsAlongAxisFromMetadata(var5, var8))
                {
                    FCUtilsItem.DropSingleItemAsIfBlockHarvested(var1, var2, var3, var4, this.idDropped(var5, var1.rand, var7), this.damageDropped(var5));
                }
            }
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        this.ValidateState(var1, var2, var3, var4);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        int var10 = this.ComputeStringLength(var1, var2, var3, var4);
        double var11 = 64.0D - (double)var10;

        if (!var1.isRemote)
        {
            float var13 = (float)Math.pow(2.0D, (var11 - 32.0D) / 32.0D);
            var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "note.bass", 3.0F, var13 - 0.0F);
        }
        else
        {
            var1.spawnParticle("note", (double)var2 + 0.5D, (double)var3 + 1.2D, (double)var4 + 0.5D, var11 / 64.0D, 0.0D, 0.0D);
            long var17 = var1.getWorldTime();
            long var15 = var17 - m_lTimeOfLastLengthDisplay;

            if (var15 < 0L || var15 >= 200L || m_iLengthOfLastLengthDisplay != var10)
            {
                var5.addChatMessage("Sounds like " + (var10 + 1) + ".");
                m_lTimeOfLastLengthDisplay = var17;
                m_iLengthOfLastLengthDisplay = var10;
            }
        }

        return true;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }

    public void SetExtendsAlongAxis(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        this.SetExtendsAlongAxis(var1, var2, var3, var4, var5, var6, true);
    }

    public void SetExtendsAlongAxis(World var1, int var2, int var3, int var4, int var5, boolean var6, boolean var7)
    {
        int var8 = var1.getBlockMetadata(var2, var3, var4) & ~(1 << var5);

        if (var6)
        {
            var8 |= 1 << var5;
        }

        if (var7)
        {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var8);
        }
        else
        {
            var1.setBlockMetadataWithClient(var2, var3, var4, var8);
        }
    }

    public void SetExtendsAlongFacing(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        this.SetExtendsAlongAxis(var1, var2, var3, var4, ConvertFacingToAxis(var5), var6);
    }

    public void SetExtendsAlongFacing(World var1, int var2, int var3, int var4, int var5, boolean var6, boolean var7)
    {
        this.SetExtendsAlongAxis(var1, var2, var3, var4, ConvertFacingToAxis(var5), var6, var7);
    }

    public boolean GetExtendsAlongAxis(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.GetExtendsAlongAxisFromMetadata(var1.getBlockMetadata(var2, var3, var4), var5);
    }

    public boolean GetExtendsAlongAxisFromMetadata(int var1, int var2)
    {
        return (var1 & 1 << var2) > 0;
    }

    public boolean GetExtendsAlongFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.GetExtendsAlongAxis(var1, var2, var3, var4, ConvertFacingToAxis(var5));
    }

    public boolean GetExtendsAlongOtherFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.GetExtendsAlongOtherAxis(var1, var2, var3, var4, ConvertFacingToAxis(var5));
    }

    public boolean GetExtendsAlongOtherAxis(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.GetExtendsAlongOtherAxisFromMetadata(var1.getBlockMetadata(var2, var3, var4), var5);
    }

    public boolean GetExtendsAlongOtherAxisFromMetadata(int var1, int var2)
    {
        var1 &= ~(1 << var2);
        return (var1 & 7) != 0;
    }

    public static int ConvertFacingToAxis(int var0)
    {
        return var0 != 4 && var0 != 5 ? (var0 != 0 && var0 != 1 ? 2 : 1) : 0;
    }

    private void GetBlockBoundsForAxis(int var1, Vec3 var2, Vec3 var3, double var4)
    {
        if (var1 == 0)
        {
            var2.setComponents(0.0D, 0.5D - var4, 0.5D - var4);
            var3.setComponents(1.0D, 0.5D + var4, 0.5D + var4);
        }
        else if (var1 == 1)
        {
            var2.setComponents(0.5D - var4, 0.0D, 0.5D - var4);
            var3.setComponents(0.5D + var4, 1.0D, 0.5D + var4);
        }
        else
        {
            var2.setComponents(0.5D - var4, 0.5D - var4, 0.0D);
            var3.setComponents(0.5D + var4, 0.5D + var4, 1.0D);
        }
    }

    public void ValidateState(World var1, int var2, int var3, int var4)
    {
        int var5 = 0;

        for (int var6 = 0; var6 < 3; ++var6)
        {
            if (this.GetExtendsAlongAxis(var1, var2, var3, var4, var6))
            {
                if (this.HasValidAttachmentPointsAlongAxis(var1, var2, var3, var4, var6))
                {
                    ++var5;
                }
                else
                {
                    this.SetExtendsAlongAxis(var1, var2, var3, var4, var6, false);
                    FCUtilsItem.DropSingleItemAsIfBlockHarvested(var1, var2, var3, var4, Item.silk.itemID, 0);
                }
            }
        }

        if (var5 <= 0)
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    private boolean HasValidAttachmentPointsAlongAxis(World var1, int var2, int var3, int var4, int var5)
    {
        byte var6;
        byte var7;

        switch (var5)
        {
            case 0:
                var6 = 4;
                var7 = 5;
                break;

            case 1:
                var6 = 0;
                var7 = 1;
                break;

            default:
                var6 = 2;
                var7 = 3;
        }

        return this.HasValidAttachmentPointToFacing(var1, var2, var3, var4, var6) && this.HasValidAttachmentPointToFacing(var1, var2, var3, var4, var7);
    }

    private boolean HasValidAttachmentPointToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);
        var6.AddFacingAsOffset(var5);
        int var7 = var1.getBlockId(var6.i, var6.j, var6.k);

        if (var7 == this.blockID)
        {
            if (this.GetExtendsAlongFacing(var1, var6.i, var6.j, var6.k, var5))
            {
                return true;
            }
        }
        else if (var7 == FCBetterThanWolves.fcBlockStake.blockID)
        {
            return true;
        }

        return false;
    }

    protected int ComputeStringLength(World var1, int var2, int var3, int var4)
    {
        int var5 = 0;

        for (int var6 = 0; var6 < 3; ++var6)
        {
            int var7 = this.ComputeStringLengthAlongAxis(var1, var2, var3, var4, var6);

            if (var7 > var5)
            {
                var5 = var7;
            }
        }

        return var5;
    }

    protected int ComputeStringLengthAlongAxis(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = 0;

        if (this.GetExtendsAlongAxis(var1, var2, var3, var4, var5))
        {
            int var7 = this.GetFirstFacingForAxis(var5);
            var6 = this.ComputeStringLengthToFacing(var1, var2, var3, var4, var7);
            var7 = Block.GetOppositeFacing(var7);
            var6 += this.ComputeStringLengthToFacing(var1, var2, var3, var4, var7);
            ++var6;
        }

        return var6;
    }

    protected int ComputeStringLengthToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = 0;
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4, var5);

        while (var1.blockExists(var7.i, var7.j, var7.k) && var1.getBlockId(var7.i, var7.j, var7.k) == this.blockID)
        {
            ++var6;
            var7.AddFacingAsOffset(var5);
        }

        return var6;
    }

    protected int GetFirstFacingForAxis(int var1)
    {
        return var1 == 0 ? 4 : (var1 == 1 ? 0 : 2);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, GetOppositeFacing(var5));
        return !this.GetExtendsAlongFacing(var1, var6.i, var6.j, var6.k, var5);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        double var5 = (double)var2 + 0.5D - 0.03125D;
        double var7 = (double)var3 + 0.5D - 0.03125D;
        double var9 = (double)var4 + 0.5D - 0.03125D;
        double var11 = (double)var2 + 0.5D + 0.03125D;
        double var13 = (double)var3 + 0.5D + 0.03125D;
        double var15 = (double)var4 + 0.5D + 0.03125D;

        if (this.GetExtendsAlongAxis(var1, var2, var3, var4, 0))
        {
            var5 = (double)var2;
            var11 = (double)var2 + 1.0D;
        }

        if (this.GetExtendsAlongAxis(var1, var2, var3, var4, 1))
        {
            var7 = (double)var3;
            var13 = (double)var3 + 1.0D;
        }

        if (this.GetExtendsAlongAxis(var1, var2, var3, var4, 2))
        {
            var9 = (double)var4;
            var15 = (double)var4 + 1.0D;
        }

        return AxisAlignedBB.getAABBPool().getAABB(var5, var7, var9, var11, var13, var15);
    }

    private void SetRenderBoundsForAxis(RenderBlocks var1, int var2)
    {
        Vec3 var3 = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
        Vec3 var4 = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
        this.GetBlockBoundsForAxis(var2, var3, var4, 0.0078125D);
        var1.setRenderBounds((double)((float)var3.xCoord), (double)((float)var3.yCoord), (double)((float)var3.zCoord), (double)((float)var4.xCoord), (double)((float)var4.yCoord), (double)((float)var4.zCoord));
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        return this.idDropped(var1.getBlockMetadata(var2, var3, var4), var1.rand, 0);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;

        for (int var6 = 0; var6 < 3; ++var6)
        {
            if (this.GetExtendsAlongAxis(var5, var2, var3, var4, var6))
            {
                this.SetRenderBoundsForAxis(var1, var6);
                var1.renderStandardBlock(this, var2, var3, var4);
            }
        }

        return true;
    }
}
