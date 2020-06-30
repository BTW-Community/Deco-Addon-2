package net.minecraft.src;

import java.util.Random;

public class FCBlockShaft extends Block
{
    protected static final double m_dShaftWidth = 0.125D;
    protected static final double m_dShaftHalfWidth = 0.0625D;
    protected static final double m_dShaftHeight = 0.75D;
    protected static final double m_dSelectionBoxWidth = 0.25D;
    protected static final double m_dSelectionBoxHalfWidth = 0.125D;
    protected static final double m_dSelectionBoxHeight = 0.8125D;
    private static final AxisAlignedBB m_boxShaft = new AxisAlignedBB(0.4375D, 0.25D, 0.4375D, 0.5625D, 1.0D, 0.5625D);
    private static final AxisAlignedBB m_boxShaftSupporting = new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D);
    private static final AxisAlignedBB m_boxSelection = new AxisAlignedBB(0.375D, 0.1875D, 0.375D, 0.625D, 1.0D, 0.625D);
    private static final AxisAlignedBB m_boxSelectionSupporting = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

    public FCBlockShaft(int var1)
    {
        super(var1, Material.circuits);
        this.setHardness(0.0F);
        this.setResistance(0.0F);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockShaft");
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
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, Block.GetOppositeFacing(var5));

        if (FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var1, var6.i, var6.j, var6.k, var5))
        {
            int var7 = var1.getBlockId(var6.i, var6.j, var6.k);

            if (this.CanStickInBlockType(var7))
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
        var9 = this.SetFacing(var9, Block.GetOppositeFacing(var5));
        FCUtilsBlockPos var10 = new FCUtilsBlockPos(var2, var3, var4, Block.GetOppositeFacing(var5));
        int var11 = var1.getBlockId(var10.i, var10.j, var10.k);
        Block var12 = Block.blocksList[var11];

        if (var12 != null)
        {
            var1.playSoundEffect((double)((float)var2 + 0.5F), (double)((float)var3 - 0.5F), (double)((float)var4 + 0.5F), var12.stepSound.getPlaceSound(), var12.stepSound.getVolume() / 2.0F, var12.stepSound.getPitch() * 0.8F);

            if (!var1.isRemote)
            {
                var12.OnPlayerWalksOnBlock(var1, var10.i, var10.j, var10.k, (EntityPlayer)null);
            }
        }

        return var9;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.stick.itemID;
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
        AxisAlignedBB var5;

        if (this.IsSupportingOtherBlock(var1, var2, var3, var4))
        {
            var5 = m_boxShaftSupporting.MakeTemporaryCopy();
        }
        else
        {
            var5 = m_boxShaft.MakeTemporaryCopy();
        }

        var5.TiltToFacingAlongJ(this.GetFacing(var1, var2, var3, var4));
        return var5;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);

        if (!this.canPlaceBlockOnSide(var1, var2, var3, var4, Block.GetOppositeFacing(var6)))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    public boolean HasSmallCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 == 1 && this.GetFacing(var1, var2, var3, var4) == 0;
    }

    public boolean CanBeCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        return true;
    }

    public void OnCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        if (!var1.isRemote)
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
        }
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

    public boolean CanRotateAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 == this.GetFacing(var1, var2, var3, var4);
    }

    public int GetNewMetadataRotatedAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var1.getBlockMetadata(var2, var3, var4);
        return this.SetFacing(var7, var6);
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
        if (var5 == this.GetFacing(var1, var2, var3, var4))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    public boolean CanStickInBlockType(int var1)
    {
        Block var2 = Block.blocksList[var1];
        return var2 != null && ((FCItemTool)((FCItemTool)Item.shovelWood)).IsToolTypeEfficientVsBlockType(var2);
    }

    public boolean IsSupportingOtherBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetFacing(var1, var2, var3, var4) == 0 && FCUtilsWorld.IsBlockRestingOnThatBelow(var1, var2, var3 + 1, var4);
    }
}
