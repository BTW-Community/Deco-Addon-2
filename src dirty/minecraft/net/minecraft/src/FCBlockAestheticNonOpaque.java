package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockAestheticNonOpaque extends Block
{
    public static final int m_iSubtypeUrn = 0;
    public static final int m_iSubtypeColumn = 1;
    public static final int m_iSubtypePedestalUp = 2;
    public static final int m_iSubtypePedestalDown = 3;
    public static final int m_iSubtypeTable = 4;
    public static final int m_iSubtypeWickerSlab = 5;
    public static final int m_iSubtypeGrate = 6;
    public static final int m_iSubtypeWicker = 7;
    public static final int m_iSubtypeSlats = 8;
    public static final int m_iSubtypeWickerSlabUpsideDown = 9;
    public static final int m_iSubtypeWhiteCobbleSlab = 10;
    public static final int m_iSubtypeWhiteCobbleSlabUpsideDown = 11;
    public static final int m_iSubtypeLightningRod = 12;
    public static final int m_iNumSubtypes = 13;
    private static final float m_fDefaultHardness = 2.0F;
    private static final float m_fColumWidth = 0.625F;
    private static final float m_fColumHalfWidth = 0.3125F;
    private static final float m_fPedestalBaseHeight = 0.75F;
    private static final float m_fPedestalMiddleHeight = 0.125F;
    private static final float m_fPedestalMiddleWidth = 0.875F;
    private static final float m_fPedestalMiddleHalfWidth = 0.4375F;
    private static final float m_fPedestalTopHeight = 0.125F;
    private static final float m_fPedestalTopWidth = 0.75F;
    private static final float m_fPedestalTopHalfWidth = 0.375F;
    private static final float m_fTableTopHeight = 0.125F;
    private static final float m_fTableLegHeight = 0.875F;
    private static final float m_fTableLegWidth = 0.25F;
    private static final float m_fTableLegHalfWidth = 0.125F;
    private static final float m_fLightningRodShaftWidth = 0.0625F;
    private static final float m_fLightningRodShaftHalfWidth = 0.03125F;
    private static final float m_fLightningRodBaseWidth = 0.25F;
    private static final float m_fLightningRodBaseHalfWidth = 0.125F;
    private static final float m_fLightningRodBaseHeight = 0.125F;
    private static final float m_fLightningRodBaseHalfHeight = 0.0625F;
    private static final float m_fLightningRodBallWidth = 0.1875F;
    private static final float m_fLightningRodBallHalfWidth = 0.09375F;
    private static final float m_fLightningRodBallVeticalOffset = 0.625F;
    private static final float m_fLightningRodCandleHolderWidth = 0.25F;
    private static final float m_fLightningRodCandleHolderHalfWidth = 0.125F;
    private static final float m_fLightningRodCandleHolderHeight = 0.0625F;
    private static final float m_fLightningRodCandleHolderHalfHeight = 0.03125F;
    private static final float m_fLightningRodCandleHolderVeticalOffset = 0.99609375F;
    private Icon m_IconUrn;
    private Icon m_IconColumnStoneTop;
    private Icon m_IconColumnStoneSide;
    private Icon m_IconPedestalStoneTop;
    private Icon m_IconPedestalStoneSide;
    private Icon m_IconSlabWicker;
    private Icon m_IconGrate;
    private Icon m_IconWicker;
    private Icon m_IconSlats;
    private Icon m_IconSlatsSide;
    private Icon m_IconWhiteCobble;
    private Icon m_IconLightningRod;
    public Icon m_IconTableWoodOakTop;
    public Icon m_IconTableWoodOakLeg;

    public FCBlockAestheticNonOpaque(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialMiscellaneous);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn(true);
        this.SetPicksEffectiveOn(true);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockAestheticNonOpaque");
        this.setCreativeTab(CreativeTabs.tabDecorations);
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
        return var1 == 0 ? FCBetterThanWolves.fcItemUrn.itemID : (var1 != 5 && var1 != 9 ? (var1 == 6 ? FCBetterThanWolves.fcBlockGrate.blockID : (var1 == 7 ? FCBetterThanWolves.fcBlockWickerPane.blockID : (var1 == 8 ? FCBetterThanWolves.fcBlockSlats.blockID : (var1 != 3 && var1 != 2 && var1 != 1 ? (var1 == 4 ? FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID : this.blockID) : FCBetterThanWolves.fcBlockSmoothStoneMouldingAndDecorative.blockID)))) : FCBetterThanWolves.fcBlockWickerSlab.blockID);
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        if (var1 != 3 && var1 != 2)
        {
            if (var1 == 1)
            {
                var1 = 12;
            }
            else if (var1 == 11)
            {
                var1 = 10;
            }
            else
            {
                if (var1 == 4)
                {
                    return 8;
                }

                if (var1 == 0 || var1 == 5 || var1 == 9 || var1 == 6 || var1 == 7 || var1 == 8)
                {
                    var1 = 0;
                }
            }
        }
        else
        {
            var1 = 13;
        }

        return var1;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        if (var9 == 2)
        {
            if (var5 == 0 || var5 != 1 && (double)var7 > 0.5D)
            {
                return 3;
            }
        }
        else if (var9 == 5)
        {
            if (var5 == 0 || var5 != 1 && (double)var7 > 0.5D)
            {
                return 9;
            }
        }
        else if (var9 == 10 && (var5 == 0 || var5 != 1 && (double)var7 > 0.5D))
        {
            return 11;
        }

        return var9;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        switch (var5)
        {
            case 0:
                AxisAlignedBB var6 = AxisAlignedBB.getAABBPool().getAABB(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.625D, 0.6875D);

                if (var1.getBlockId(var2, var3 + 1, var4) == FCBetterThanWolves.fcHopper.blockID)
                {
                    var6.offset(0.0D, 0.375D, 0.0D);
                }

                return var6;

            case 1:
                return AxisAlignedBB.getAABBPool().getAABB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D);

            case 2:
            case 3:
            default:
                return super.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4);

            case 4:
                if (!this.IsTableOnCorner(var1, var2, var3, var4))
                {
                    return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.875D, 0.0D, 1.0D, 1.0D, 1.0D);
                }

                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

            case 5:
            case 10:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

            case 6:
            case 7:
            case 8:
                return this.GetBlockBoundsFromPoolForPane(var1, var2, var3, var4, var5);

            case 9:
            case 11:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);

            case 12:
                return AxisAlignedBB.getAABBPool().getAABB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
        }
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        int var8 = var1.getBlockMetadata(var2, var3, var4);

        if (var8 != 6 && var8 != 7 && var8 != 8)
        {
            super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            boolean var9 = this.ShouldPaneConnectToBlock(var1, var2, var3, var4 - 1, var8);
            boolean var10 = this.ShouldPaneConnectToBlock(var1, var2, var3, var4 + 1, var8);
            boolean var11 = this.ShouldPaneConnectToBlock(var1, var2 - 1, var3, var4, var8);
            boolean var12 = this.ShouldPaneConnectToBlock(var1, var2 + 1, var3, var4, var8);
            AxisAlignedBB var13;

            if ((!var11 || !var12) && (var11 || var12 || var9 || var10))
            {
                if (var11 && !var12)
                {
                    var13 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.4375D, 0.5D, 1.0D, 0.5625D).offset((double)var2, (double)var3, (double)var4);
                    var13.AddToListIfIntersects(var5, var6);
                }
                else if (!var11 && var12)
                {
                    var13 = AxisAlignedBB.getAABBPool().getAABB(0.5D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D).offset((double)var2, (double)var3, (double)var4);
                    var13.AddToListIfIntersects(var5, var6);
                }
            }
            else
            {
                var13 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D).offset((double)var2, (double)var3, (double)var4);
                var13.AddToListIfIntersects(var5, var6);
            }

            if ((!var9 || !var10) && (var11 || var12 || var9 || var10))
            {
                if (var9 && !var10)
                {
                    var13 = AxisAlignedBB.getAABBPool().getAABB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5D).offset((double)var2, (double)var3, (double)var4);
                    var13.AddToListIfIntersects(var5, var6);
                }
                else if (!var9 && var10)
                {
                    var13 = AxisAlignedBB.getAABBPool().getAABB(0.4375D, 0.0D, 0.5D, 0.5625D, 1.0D, 1.0D).offset((double)var2, (double)var3, (double)var4);
                    var13.AddToListIfIntersects(var5, var6);
                }
            }
            else
            {
                var13 = AxisAlignedBB.getAABBPool().getAABB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D).offset((double)var2, (double)var3, (double)var4);
                var13.AddToListIfIntersects(var5, var6);
            }
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
        int var6 = this.GetSubtype(var1, var2, var3, var4);

        if (var6 == 12)
        {
            if (!CanLightningRodStay(var1, var2, var3, var4))
            {
                if (var1.getBlockId(var2, var3, var4) == this.blockID)
                {
                    this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
                    var1.setBlockWithNotify(var2, var3, var4, 0);
                }
            }
            else
            {
                var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
            }
        }
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        int var7 = var1.getBlockMetadata(var2, var3, var4);

        switch (var7)
        {
            case 1:
                return var5 == 0 || var5 == 1;

            case 2:
            case 3:
                return true;

            default:
                return super.HasCenterHardPointToFacing(var1, var2, var3, var4, var5, var6);
        }
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        int var7 = var1.getBlockMetadata(var2, var3, var4);

        switch (var7)
        {
            case 2:
                return var5 == 0;

            case 3:
                return var5 == 1;

            case 4:
                return var5 == 1;

            case 5:
            case 10:
                return var5 == 0;

            case 6:
            case 7:
            case 8:
            default:
                return super.HasLargeCenterHardPointToFacing(var1, var2, var3, var4, var5, var6);

            case 9:
            case 11:
                return var5 == 1;
        }
    }

    public boolean HasSmallCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        int var7 = var1.getBlockMetadata(var2, var3, var4);
        return var7 == 12 ? var5 == 1 : super.HasSmallCenterHardPointToFacing(var1, var2, var3, var4, var5, var6);
    }

    public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 != 0 && var5 != 4 && var5 != 5 && var5 != 9 && var5 != 6 && var5 != 7 && var5 != 8;
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 1.2F;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 != 5 && var5 != 10 ? (var5 == 0 ? var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4) : super.CanGroundCoverRestOnBlock(var1, var2, var3, var4)) : true;
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 != 5 && var5 != 10 ? (var5 == 0 ? -1.0F : super.GroundCoverRestingOnVisualOffset(var1, var2, var3, var4)) : -0.5F;
    }

    public int GetSubtype(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.getBlockMetadata(var2, var3, var4);
    }

    public void SetSubtype(World var1, int var2, int var3, int var4, int var5)
    {
        var1.setBlockMetadata(var2, var3, var4, var5);
    }

    public boolean IsBlockTable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2, var3, var4) == FCBetterThanWolves.fcAestheticNonOpaque.blockID && var1.getBlockMetadata(var2, var3, var4) == 4;
    }

    public boolean IsTableOnCorner(IBlockAccess var1, int var2, int var3, int var4)
    {
        boolean var5 = this.IsBlockTable(var1, var2 + 1, var3, var4);
        boolean var6 = this.IsBlockTable(var1, var2 - 1, var3, var4);
        boolean var7 = this.IsBlockTable(var1, var2, var3, var4 + 1);
        boolean var8 = this.IsBlockTable(var1, var2, var3, var4 - 1);
        return !var5 && (!var7 || !var8) || !var6 && (!var7 || !var8);
    }

    private boolean ShouldPaneConnectToBlock(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockId(var2, var3, var4);

        if (!Block.opaqueCubeLookup[var6] && var6 != Block.glass.blockID)
        {
            if (var6 == this.blockID)
            {
                int var7 = var1.getBlockMetadata(var2, var3, var4);

                if (var7 == var5)
                {
                    return true;
                }
            }

            return false;
        }
        else
        {
            return true;
        }
    }

    public AxisAlignedBB GetBlockBoundsFromPoolForPane(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        float var6 = 0.4375F;
        float var7 = 0.5625F;
        float var8 = 0.4375F;
        float var9 = 0.5625F;
        boolean var10 = this.ShouldPaneConnectToBlock(var1, var2, var3, var4 - 1, var5);
        boolean var11 = this.ShouldPaneConnectToBlock(var1, var2, var3, var4 + 1, var5);
        boolean var12 = this.ShouldPaneConnectToBlock(var1, var2 - 1, var3, var4, var5);
        boolean var13 = this.ShouldPaneConnectToBlock(var1, var2 + 1, var3, var4, var5);

        if ((!var12 || !var13) && (var12 || var13 || var10 || var11))
        {
            if (var12 && !var13)
            {
                var6 = 0.0F;
            }
            else if (!var12 && var13)
            {
                var7 = 1.0F;
            }
        }
        else
        {
            var6 = 0.0F;
            var7 = 1.0F;
        }

        if ((!var10 || !var11) && (var12 || var13 || var10 || var11))
        {
            if (var10 && !var11)
            {
                var8 = 0.0F;
            }
            else if (!var10 && var11)
            {
                var9 = 1.0F;
            }
        }
        else
        {
            var8 = 0.0F;
            var9 = 1.0F;
        }

        return AxisAlignedBB.getAABBPool().getAABB((double)var6, 0.0D, (double)var8, (double)var7, 1.0D, (double)var9);
    }

    public static boolean CanLightningRodStay(World var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2 - 1, var3);
        return var4 == FCBetterThanWolves.fcAestheticNonOpaque.blockID && var0.getBlockMetadata(var1, var2 - 1, var3) == 12 ? true : FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var0, var1, var2 - 1, var3, 1, true);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("stone");
        this.m_IconUrn = var1.registerIcon("fcBlockUrn");
        this.m_IconColumnStoneTop = var1.registerIcon("fcBlockColumnStone_top");
        this.m_IconColumnStoneSide = var1.registerIcon("fcBlockColumnStone_side");
        this.m_IconPedestalStoneTop = var1.registerIcon("fcBlockPedestalStone_top");
        this.m_IconPedestalStoneSide = var1.registerIcon("fcBlockPedestalStone_side");
        this.m_IconTableWoodOakTop = var1.registerIcon("fcBlockTableWoodOak_top");
        this.m_IconTableWoodOakLeg = var1.registerIcon("fcBlockTableWoodOak_leg");
        this.m_IconSlabWicker = var1.registerIcon("fcBlockSlabWicker");
        this.m_IconGrate = var1.registerIcon("fcBlockGrate");
        this.m_IconWicker = var1.registerIcon("fcBlockWicker");
        this.m_IconSlats = var1.registerIcon("fcBlockSlats");
        this.m_IconSlatsSide = var1.registerIcon("fcBlockSlats_side");
        this.m_IconWhiteCobble = var1.registerIcon("fcBlockWhiteCobble");
        this.m_IconLightningRod = var1.registerIcon("fcBlockLightningRodOld");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        switch (var2)
        {
            case 1:
                if (var1 < 2)
                {
                    return this.m_IconColumnStoneTop;
                }

                return this.m_IconColumnStoneSide;

            case 2:
            case 3:
                if (var1 < 2)
                {
                    return this.m_IconPedestalStoneTop;
                }

                return this.m_IconPedestalStoneSide;

            case 4:
                return this.m_IconTableWoodOakTop;

            case 5:
            case 9:
                return this.m_IconSlabWicker;

            case 6:
                return this.m_IconGrate;

            case 7:
                return this.m_IconWicker;

            case 8:
                return this.m_IconSlats;

            case 10:
            case 11:
                return this.m_IconWhiteCobble;

            case 12:
                return this.m_IconLightningRod;

            default:
                return this.blockIcon;
        }
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3) {}

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
        int var6 = var5.getBlockMetadata(var2, var3, var4);

        switch (var6)
        {
            case 0:
                float var7 = 0.0F;

                if (var5.getBlockId(var2, var3 + 1, var4) == FCBetterThanWolves.fcHopper.blockID)
                {
                    var7 = 0.375F;
                }

                FCBlockUnfiredPottery var10000 = (FCBlockUnfiredPottery)((FCBlockUnfiredPottery)FCBetterThanWolves.fcUnfiredPottery);
                return FCBlockUnfiredPottery.RenderUnfiredUrn(var1, var5, var2, var3, var4, this, this.m_IconUrn, var7);

            case 1:
            case 5:
            case 9:
            case 10:
            case 11:
            default:
                var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
                return var1.renderStandardBlock(this, var2, var3, var4);

            case 2:
                return this.RenderPedestalUp(var1, var5, var2, var3, var4, this);

            case 3:
                return this.RenderPedestalDown(var1, var5, var2, var3, var4, this);

            case 4:
                return this.RenderTable(var1, var5, var2, var3, var4, this);

            case 6:
            case 7:
            case 8:
                return this.RenderPane(var1, var5, var2, var3, var4, this, var6);

            case 12:
                return this.RenderLightningRod(var1, var5, var2, var3, var4, this);
        }
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        switch (var2)
        {
            case 0:
                FCBlockUnfiredPottery var10000 = (FCBlockUnfiredPottery)((FCBlockUnfiredPottery)FCBetterThanWolves.fcUnfiredPottery);
                FCBlockUnfiredPottery.RenderUnfiredUrnInvBlock(var1, this, var2, this.m_IconUrn);
                return;

            case 1:
                var1.setRenderBounds(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
                break;

            case 2:
                this.RenderPedestalUpInvBlock(var1, this);
                return;

            case 3:
                this.RenderPedestalDownInvBlock(var1, this);
                return;

            case 4:
                this.RenderTableInvBlock(var1, this);
                return;

            case 5:
            case 10:
                var1.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
                break;

            case 6:
            case 7:
            case 8:
            default:
                var1.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
                break;

            case 9:
            case 11:
                var1.setRenderBounds(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
                break;

            case 12:
                this.RenderLightningRodInvBlock(var1, this);
                return;
        }

        FCClientUtilsRender.RenderInvBlockWithMetadata(var1, this, -0.5F, -0.5F, -0.5F, var2);
    }

    public boolean RenderPedestalUp(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, Block var6)
    {
        var1.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
        var1.renderStandardBlock(var6, var3, var4, var5);
        var1.setRenderBounds(0.0625D, 0.75D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
        var1.renderStandardBlock(var6, var3, var4, var5);
        var1.setRenderBounds(0.125D, 0.875D, 0.125D, 0.875D, 1.0D, 0.875D);
        var1.renderStandardBlock(var6, var3, var4, var5);
        return true;
    }

    public void RenderPedestalUpInvBlock(RenderBlocks var1, Block var2)
    {
        var1.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var1, var2, -0.5F, -0.5F, -0.5F, 2);
        var1.setRenderBounds(0.0625D, 0.75D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var1, var2, -0.5F, -0.5F, -0.5F, 2);
        var1.setRenderBounds(0.125D, 0.875D, 0.125D, 0.875D, 1.0D, 0.875D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var1, var2, -0.5F, -0.5F, -0.5F, 2);
    }

    public boolean RenderPedestalDown(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, Block var6)
    {
        var1.setRenderBounds(0.0D, 0.25D, 0.0D, 1.0D, 1.0D, 1.0D);
        var1.renderStandardBlock(var6, var3, var4, var5);
        var1.setRenderBounds(0.0625D, 0.125D, 0.0625D, 0.9375D, 0.25D, 0.9375D);
        var1.renderStandardBlock(var6, var3, var4, var5);
        var1.setRenderBounds(0.125D, 0.0D, 0.125D, 0.875D, 0.125D, 0.875D);
        var1.renderStandardBlock(var6, var3, var4, var5);
        return true;
    }

    public void RenderPedestalDownInvBlock(RenderBlocks var1, Block var2)
    {
        var1.setRenderBounds(0.0D, 0.25D, 0.0D, 1.0D, 1.0D, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var1, var2, -0.5F, -0.5F, -0.5F, 3);
        var1.setRenderBounds(0.0625D, 0.125D, 0.0625D, 0.9375D, 0.25D, 0.9375D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var1, var2, -0.5F, -0.5F, -0.5F, 3);
        var1.setRenderBounds(0.125D, 0.0D, 0.125D, 0.875D, 0.125D, 0.875D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var1, var2, -0.5F, -0.5F, -0.5F, 3);
    }

    public boolean RenderTable(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, Block var6)
    {
        var1.setRenderBounds(0.0D, 0.875D, 0.0D, 1.0D, 1.0D, 1.0D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var1, var6, var3, var4, var5, this.m_IconTableWoodOakTop);

        if (this.IsTableOnCorner(var2, var3, var4, var5))
        {
            var1.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.875D, 0.625D);
            FCClientUtilsRender.RenderStandardBlockWithTexture(var1, var6, var3, var4, var5, this.m_IconTableWoodOakLeg);
        }

        return true;
    }

    public void RenderTableInvBlock(RenderBlocks var1, Block var2)
    {
        var1.setRenderBounds(0.0D, 0.875D, 0.0D, 1.0D, 1.0D, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithTexture(var1, var2, -0.5F, -0.5F, -0.5F, this.m_IconTableWoodOakTop);
        var1.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.875D, 0.625D);
        FCClientUtilsRender.RenderInvBlockWithTexture(var1, var2, -0.5F, -0.5F, -0.5F, this.m_IconTableWoodOakLeg);
    }

    public boolean RenderPane(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, Block var6, int var7)
    {
        short var8 = 256;
        Tessellator var9 = Tessellator.instance;
        var9.setBrightness(var6.getMixedBrightnessForBlock(var2, var3, var4, var5));
        int var10 = var6.colorMultiplier(var2, var3, var4, var5);
        float var11 = (float)(var10 >> 16 & 255) / 255.0F;
        float var12 = (float)(var10 >> 8 & 255) / 255.0F;
        float var13 = (float)(var10 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            var11 = (var11 * 30.0F + var12 * 59.0F + var13 * 11.0F) / 100.0F;
            var12 = (var11 * 30.0F + var12 * 70.0F) / 100.0F;
            var13 = (var11 * 30.0F + var13 * 70.0F) / 100.0F;
        }

        var9.setColorOpaque_F(var11, var12, var13);
        Icon var14;
        Icon var15;
        int var16;

        if (var1.hasOverrideBlockTexture())
        {
            var14 = var1.GetOverrideTexture();
            var15 = var1.GetOverrideTexture();
        }
        else
        {
            var16 = var2.getBlockMetadata(var3, var4, var5);
            var14 = var6.getIcon(0, var16);
            var15 = var6.getIcon(0, var16);

            if (var7 == 8)
            {
                var15 = this.m_IconSlatsSide;
            }
        }

        var16 = var14.getOriginX();
        int var17 = var14.getOriginY();
        double var18 = (double)var14.getMinU();
        double var20 = (double)var14.getInterpolatedU(8.0D);
        double var22 = (double)var14.getMaxU();
        double var24 = (double)var14.getMinV();
        double var26 = (double)var14.getMaxV();
        int var28 = var15.getOriginX();
        int var29 = var15.getOriginY();
        double var30 = (double)var15.getInterpolatedU(7.0D);
        double var32 = (double)var15.getInterpolatedU(9.0D);
        double var34 = (double)var15.getMinV();
        double var36 = (double)var15.getInterpolatedV(8.0D);
        double var38 = (double)var15.getMaxV();
        double var40 = (double)var3;
        double var42 = (double)var3 + 0.5D;
        double var44 = (double)(var3 + 1);
        double var46 = (double)var5;
        double var48 = (double)var5 + 0.5D;
        double var50 = (double)(var5 + 1);
        double var52 = (double)var3 + 0.5D - 0.0625D;
        double var54 = (double)var3 + 0.5D + 0.0625D;
        double var56 = (double)var5 + 0.5D - 0.0625D;
        double var58 = (double)var5 + 0.5D + 0.0625D;
        boolean var60 = this.ShouldPaneConnectToBlock(var2, var3, var4, var5 - 1, var7);
        boolean var61 = this.ShouldPaneConnectToBlock(var2, var3, var4, var5 + 1, var7);
        boolean var62 = this.ShouldPaneConnectToBlock(var2, var3 - 1, var4, var5, var7);
        boolean var63 = this.ShouldPaneConnectToBlock(var2, var3 + 1, var4, var5, var7);
        boolean var64 = !this.ShouldPaneConnectToBlock(var2, var3, var4 + 1, var5, var7);
        boolean var65 = !this.ShouldPaneConnectToBlock(var2, var3, var4 - 1, var5, var7);

        if ((!var62 || !var63) && (var62 || var63 || var60 || var61))
        {
            if (var62 && !var63)
            {
                var9.addVertexWithUV(var40, (double)(var4 + 1), var48, var18, var24);
                var9.addVertexWithUV(var40, (double)(var4 + 0), var48, var18, var26);
                var9.addVertexWithUV(var42, (double)(var4 + 0), var48, var20, var26);
                var9.addVertexWithUV(var42, (double)(var4 + 1), var48, var20, var24);
                var9.addVertexWithUV(var42, (double)(var4 + 1), var48, var18, var24);
                var9.addVertexWithUV(var42, (double)(var4 + 0), var48, var18, var26);
                var9.addVertexWithUV(var40, (double)(var4 + 0), var48, var20, var26);
                var9.addVertexWithUV(var40, (double)(var4 + 1), var48, var20, var24);

                if (!var61 && !var60)
                {
                    var9.addVertexWithUV(var42, (double)(var4 + 1), var58, var30, var34);
                    var9.addVertexWithUV(var42, (double)(var4 + 0), var58, var30, var38);
                    var9.addVertexWithUV(var42, (double)(var4 + 0), var56, var32, var38);
                    var9.addVertexWithUV(var42, (double)(var4 + 1), var56, var32, var34);
                    var9.addVertexWithUV(var42, (double)(var4 + 1), var56, var30, var34);
                    var9.addVertexWithUV(var42, (double)(var4 + 0), var56, var30, var38);
                    var9.addVertexWithUV(var42, (double)(var4 + 0), var58, var32, var38);
                    var9.addVertexWithUV(var42, (double)(var4 + 1), var58, var32, var34);
                }

                if (var64 || var4 < var8 - 1 && var2.isAirBlock(var3 - 1, var4 + 1, var5))
                {
                    var9.addVertexWithUV(var40, (double)(var4 + 1) + 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var58, var32, var38);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var56, var30, var38);
                    var9.addVertexWithUV(var40, (double)(var4 + 1) + 0.01D, var56, var30, var36);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var40, (double)(var4 + 1) + 0.01D, var58, var32, var38);
                    var9.addVertexWithUV(var40, (double)(var4 + 1) + 0.01D, var56, var30, var38);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var56, var30, var36);
                }

                if (var65 || var4 > 1 && var2.isAirBlock(var3 - 1, var4 - 1, var5))
                {
                    var9.addVertexWithUV(var40, (double)var4 - 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var58, var32, var38);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var56, var30, var38);
                    var9.addVertexWithUV(var40, (double)var4 - 0.01D, var56, var30, var36);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var40, (double)var4 - 0.01D, var58, var32, var38);
                    var9.addVertexWithUV(var40, (double)var4 - 0.01D, var56, var30, var38);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var56, var30, var36);
                }
            }
            else if (!var62 && var63)
            {
                var9.addVertexWithUV(var42, (double)(var4 + 1), var48, var20, var24);
                var9.addVertexWithUV(var42, (double)(var4 + 0), var48, var20, var26);
                var9.addVertexWithUV(var44, (double)(var4 + 0), var48, var22, var26);
                var9.addVertexWithUV(var44, (double)(var4 + 1), var48, var22, var24);
                var9.addVertexWithUV(var44, (double)(var4 + 1), var48, var20, var24);
                var9.addVertexWithUV(var44, (double)(var4 + 0), var48, var20, var26);
                var9.addVertexWithUV(var42, (double)(var4 + 0), var48, var22, var26);
                var9.addVertexWithUV(var42, (double)(var4 + 1), var48, var22, var24);

                if (!var61 && !var60)
                {
                    var9.addVertexWithUV(var42, (double)(var4 + 1), var56, var30, var34);
                    var9.addVertexWithUV(var42, (double)(var4 + 0), var56, var30, var38);
                    var9.addVertexWithUV(var42, (double)(var4 + 0), var58, var32, var38);
                    var9.addVertexWithUV(var42, (double)(var4 + 1), var58, var32, var34);
                    var9.addVertexWithUV(var42, (double)(var4 + 1), var58, var30, var34);
                    var9.addVertexWithUV(var42, (double)(var4 + 0), var58, var30, var38);
                    var9.addVertexWithUV(var42, (double)(var4 + 0), var56, var32, var38);
                    var9.addVertexWithUV(var42, (double)(var4 + 1), var56, var32, var34);
                }

                if (var64 || var4 < var8 - 1 && var2.isAirBlock(var3 + 1, var4 + 1, var5))
                {
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var58, var32, var34);
                    var9.addVertexWithUV(var44, (double)(var4 + 1) + 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var44, (double)(var4 + 1) + 0.01D, var56, var30, var36);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var56, var30, var34);
                    var9.addVertexWithUV(var44, (double)(var4 + 1) + 0.01D, var58, var32, var34);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var56, var30, var36);
                    var9.addVertexWithUV(var44, (double)(var4 + 1) + 0.01D, var56, var30, var34);
                }

                if (var65 || var4 > 1 && var2.isAirBlock(var3 + 1, var4 - 1, var5))
                {
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var58, var32, var34);
                    var9.addVertexWithUV(var44, (double)var4 - 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var44, (double)var4 - 0.01D, var56, var30, var36);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var56, var30, var34);
                    var9.addVertexWithUV(var44, (double)var4 - 0.01D, var58, var32, var34);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var56, var30, var36);
                    var9.addVertexWithUV(var44, (double)var4 - 0.01D, var56, var30, var34);
                }
            }
        }
        else
        {
            var9.addVertexWithUV(var40, (double)(var4 + 1), var48, var18, var24);
            var9.addVertexWithUV(var40, (double)(var4 + 0), var48, var18, var26);
            var9.addVertexWithUV(var44, (double)(var4 + 0), var48, var22, var26);
            var9.addVertexWithUV(var44, (double)(var4 + 1), var48, var22, var24);
            var9.addVertexWithUV(var44, (double)(var4 + 1), var48, var18, var24);
            var9.addVertexWithUV(var44, (double)(var4 + 0), var48, var18, var26);
            var9.addVertexWithUV(var40, (double)(var4 + 0), var48, var22, var26);
            var9.addVertexWithUV(var40, (double)(var4 + 1), var48, var22, var24);

            if (var64)
            {
                var9.addVertexWithUV(var40, (double)(var4 + 1) + 0.01D, var58, var32, var38);
                var9.addVertexWithUV(var44, (double)(var4 + 1) + 0.01D, var58, var32, var34);
                var9.addVertexWithUV(var44, (double)(var4 + 1) + 0.01D, var56, var30, var34);
                var9.addVertexWithUV(var40, (double)(var4 + 1) + 0.01D, var56, var30, var38);
                var9.addVertexWithUV(var44, (double)(var4 + 1) + 0.01D, var58, var32, var38);
                var9.addVertexWithUV(var40, (double)(var4 + 1) + 0.01D, var58, var32, var34);
                var9.addVertexWithUV(var40, (double)(var4 + 1) + 0.01D, var56, var30, var34);
                var9.addVertexWithUV(var44, (double)(var4 + 1) + 0.01D, var56, var30, var38);
            }
            else
            {
                if (var4 < var8 - 1 && var2.isAirBlock(var3 - 1, var4 + 1, var5))
                {
                    var9.addVertexWithUV(var40, (double)(var4 + 1) + 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var58, var32, var38);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var56, var30, var38);
                    var9.addVertexWithUV(var40, (double)(var4 + 1) + 0.01D, var56, var30, var36);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var40, (double)(var4 + 1) + 0.01D, var58, var32, var38);
                    var9.addVertexWithUV(var40, (double)(var4 + 1) + 0.01D, var56, var30, var38);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var56, var30, var36);
                }

                if (var4 < var8 - 1 && var2.isAirBlock(var3 + 1, var4 + 1, var5))
                {
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var58, var32, var34);
                    var9.addVertexWithUV(var44, (double)(var4 + 1) + 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var44, (double)(var4 + 1) + 0.01D, var56, var30, var36);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var56, var30, var34);
                    var9.addVertexWithUV(var44, (double)(var4 + 1) + 0.01D, var58, var32, var34);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var42, (double)(var4 + 1) + 0.01D, var56, var30, var36);
                    var9.addVertexWithUV(var44, (double)(var4 + 1) + 0.01D, var56, var30, var34);
                }
            }

            if (var65)
            {
                var9.addVertexWithUV(var40, (double)var4 - 0.01D, var58, var32, var38);
                var9.addVertexWithUV(var44, (double)var4 - 0.01D, var58, var32, var34);
                var9.addVertexWithUV(var44, (double)var4 - 0.01D, var56, var30, var34);
                var9.addVertexWithUV(var40, (double)var4 - 0.01D, var56, var30, var38);
                var9.addVertexWithUV(var44, (double)var4 - 0.01D, var58, var32, var38);
                var9.addVertexWithUV(var40, (double)var4 - 0.01D, var58, var32, var34);
                var9.addVertexWithUV(var40, (double)var4 - 0.01D, var56, var30, var34);
                var9.addVertexWithUV(var44, (double)var4 - 0.01D, var56, var30, var38);
            }
            else
            {
                if (var4 > 1 && var2.isAirBlock(var3 - 1, var4 - 1, var5))
                {
                    var9.addVertexWithUV(var40, (double)var4 - 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var58, var32, var38);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var56, var30, var38);
                    var9.addVertexWithUV(var40, (double)var4 - 0.01D, var56, var30, var36);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var40, (double)var4 - 0.01D, var58, var32, var38);
                    var9.addVertexWithUV(var40, (double)var4 - 0.01D, var56, var30, var38);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var56, var30, var36);
                }

                if (var4 > 1 && var2.isAirBlock(var3 + 1, var4 - 1, var5))
                {
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var58, var32, var34);
                    var9.addVertexWithUV(var44, (double)var4 - 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var44, (double)var4 - 0.01D, var56, var30, var36);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var56, var30, var34);
                    var9.addVertexWithUV(var44, (double)var4 - 0.01D, var58, var32, var34);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var58, var32, var36);
                    var9.addVertexWithUV(var42, (double)var4 - 0.01D, var56, var30, var36);
                    var9.addVertexWithUV(var44, (double)var4 - 0.01D, var56, var30, var34);
                }
            }
        }

        if ((!var60 || !var61) && (var62 || var63 || var60 || var61))
        {
            if (var60 && !var61)
            {
                var9.addVertexWithUV(var42, (double)(var4 + 1), var46, var18, var24);
                var9.addVertexWithUV(var42, (double)(var4 + 0), var46, var18, var26);
                var9.addVertexWithUV(var42, (double)(var4 + 0), var48, var20, var26);
                var9.addVertexWithUV(var42, (double)(var4 + 1), var48, var20, var24);
                var9.addVertexWithUV(var42, (double)(var4 + 1), var48, var18, var24);
                var9.addVertexWithUV(var42, (double)(var4 + 0), var48, var18, var26);
                var9.addVertexWithUV(var42, (double)(var4 + 0), var46, var20, var26);
                var9.addVertexWithUV(var42, (double)(var4 + 1), var46, var20, var24);

                if (!var63 && !var62)
                {
                    var9.addVertexWithUV(var52, (double)(var4 + 1), var48, var30, var34);
                    var9.addVertexWithUV(var52, (double)(var4 + 0), var48, var30, var38);
                    var9.addVertexWithUV(var54, (double)(var4 + 0), var48, var32, var38);
                    var9.addVertexWithUV(var54, (double)(var4 + 1), var48, var32, var34);
                    var9.addVertexWithUV(var54, (double)(var4 + 1), var48, var30, var34);
                    var9.addVertexWithUV(var54, (double)(var4 + 0), var48, var30, var38);
                    var9.addVertexWithUV(var52, (double)(var4 + 0), var48, var32, var38);
                    var9.addVertexWithUV(var52, (double)(var4 + 1), var48, var32, var34);
                }

                if (var64 || var4 < var8 - 1 && var2.isAirBlock(var3, var4 + 1, var5 - 1))
                {
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var46, var32, var34);
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var48, var32, var36);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var48, var30, var36);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var46, var30, var34);
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var48, var32, var34);
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var46, var32, var36);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var46, var30, var36);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var48, var30, var34);
                }

                if (var65 || var4 > 1 && var2.isAirBlock(var3, var4 - 1, var5 - 1))
                {
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var46, var32, var34);
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var48, var32, var36);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var48, var30, var36);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var46, var30, var34);
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var48, var32, var34);
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var46, var32, var36);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var46, var30, var36);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var48, var30, var34);
                }
            }
            else if (!var60 && var61)
            {
                var9.addVertexWithUV(var42, (double)(var4 + 1), var48, var20, var24);
                var9.addVertexWithUV(var42, (double)(var4 + 0), var48, var20, var26);
                var9.addVertexWithUV(var42, (double)(var4 + 0), var50, var22, var26);
                var9.addVertexWithUV(var42, (double)(var4 + 1), var50, var22, var24);
                var9.addVertexWithUV(var42, (double)(var4 + 1), var50, var20, var24);
                var9.addVertexWithUV(var42, (double)(var4 + 0), var50, var20, var26);
                var9.addVertexWithUV(var42, (double)(var4 + 0), var48, var22, var26);
                var9.addVertexWithUV(var42, (double)(var4 + 1), var48, var22, var24);

                if (!var63 && !var62)
                {
                    var9.addVertexWithUV(var54, (double)(var4 + 1), var48, var30, var34);
                    var9.addVertexWithUV(var54, (double)(var4 + 0), var48, var30, var38);
                    var9.addVertexWithUV(var52, (double)(var4 + 0), var48, var32, var38);
                    var9.addVertexWithUV(var52, (double)(var4 + 1), var48, var32, var34);
                    var9.addVertexWithUV(var52, (double)(var4 + 1), var48, var30, var34);
                    var9.addVertexWithUV(var52, (double)(var4 + 0), var48, var30, var38);
                    var9.addVertexWithUV(var54, (double)(var4 + 0), var48, var32, var38);
                    var9.addVertexWithUV(var54, (double)(var4 + 1), var48, var32, var34);
                }

                if (var64 || var4 < var8 - 1 && var2.isAirBlock(var3, var4 + 1, var5 + 1))
                {
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var48, var30, var36);
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var50, var30, var38);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var50, var32, var38);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var48, var32, var36);
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var50, var30, var36);
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var48, var30, var38);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var48, var32, var38);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var50, var32, var36);
                }

                if (var65 || var4 > 1 && var2.isAirBlock(var3, var4 - 1, var5 + 1))
                {
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var48, var30, var36);
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var50, var30, var38);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var50, var32, var38);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var48, var32, var36);
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var50, var30, var36);
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var48, var30, var38);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var48, var32, var38);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var50, var32, var36);
                }
            }
        }
        else
        {
            var9.addVertexWithUV(var42, (double)(var4 + 1), var50, var18, var24);
            var9.addVertexWithUV(var42, (double)(var4 + 0), var50, var18, var26);
            var9.addVertexWithUV(var42, (double)(var4 + 0), var46, var22, var26);
            var9.addVertexWithUV(var42, (double)(var4 + 1), var46, var22, var24);
            var9.addVertexWithUV(var42, (double)(var4 + 1), var46, var18, var24);
            var9.addVertexWithUV(var42, (double)(var4 + 0), var46, var18, var26);
            var9.addVertexWithUV(var42, (double)(var4 + 0), var50, var22, var26);
            var9.addVertexWithUV(var42, (double)(var4 + 1), var50, var22, var24);

            if (var64)
            {
                var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var50, var32, var38);
                var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var46, var32, var34);
                var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var46, var30, var34);
                var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var50, var30, var38);
                var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var46, var32, var38);
                var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var50, var32, var34);
                var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var50, var30, var34);
                var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var46, var30, var38);
            }
            else
            {
                if (var4 < var8 - 1 && var2.isAirBlock(var3, var4 + 1, var5 - 1))
                {
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var46, var32, var34);
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var48, var32, var36);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var48, var30, var36);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var46, var30, var34);
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var48, var32, var34);
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var46, var32, var36);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var46, var30, var36);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var48, var30, var34);
                }

                if (var4 < var8 - 1 && var2.isAirBlock(var3, var4 + 1, var5 + 1))
                {
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var48, var30, var36);
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var50, var30, var38);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var50, var32, var38);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var48, var32, var36);
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var50, var30, var36);
                    var9.addVertexWithUV(var52, (double)(var4 + 1) + 0.005D, var48, var30, var38);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var48, var32, var38);
                    var9.addVertexWithUV(var54, (double)(var4 + 1) + 0.005D, var50, var32, var36);
                }
            }

            if (var65)
            {
                var9.addVertexWithUV(var54, (double)var4 - 0.005D, var50, var32, var38);
                var9.addVertexWithUV(var54, (double)var4 - 0.005D, var46, var32, var34);
                var9.addVertexWithUV(var52, (double)var4 - 0.005D, var46, var30, var34);
                var9.addVertexWithUV(var52, (double)var4 - 0.005D, var50, var30, var38);
                var9.addVertexWithUV(var54, (double)var4 - 0.005D, var46, var32, var38);
                var9.addVertexWithUV(var54, (double)var4 - 0.005D, var50, var32, var34);
                var9.addVertexWithUV(var52, (double)var4 - 0.005D, var50, var30, var34);
                var9.addVertexWithUV(var52, (double)var4 - 0.005D, var46, var30, var38);
            }
            else
            {
                if (var4 > 1 && var2.isAirBlock(var3, var4 - 1, var5 - 1))
                {
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var46, var32, var34);
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var48, var32, var36);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var48, var30, var36);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var46, var30, var34);
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var48, var32, var34);
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var46, var32, var36);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var46, var30, var36);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var48, var30, var34);
                }

                if (var4 > 1 && var2.isAirBlock(var3, var4 - 1, var5 + 1))
                {
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var48, var30, var36);
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var50, var30, var38);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var50, var32, var38);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var48, var32, var36);
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var50, var30, var36);
                    var9.addVertexWithUV(var52, (double)var4 - 0.005D, var48, var30, var38);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var48, var32, var38);
                    var9.addVertexWithUV(var54, (double)var4 - 0.005D, var50, var32, var36);
                }
            }
        }

        return true;
    }

    public boolean RenderLightningRod(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, Block var6)
    {
        Icon var7 = this.m_IconLightningRod;
        var1.setRenderBounds(0.46875D, 0.0D, 0.46875D, 0.53125D, 1.0D, 0.53125D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var1, var6, var3, var4, var5, this.m_IconLightningRod);

        if (var2.getBlockId(var3, var4 - 1, var5) != FCBetterThanWolves.fcAestheticNonOpaque.blockID || var2.getBlockMetadata(var3, var4 - 1, var5) != 12)
        {
            var1.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.125D, 0.625D);
            FCClientUtilsRender.RenderStandardBlockWithTexture(var1, var6, var3, var4, var5, this.m_IconLightningRod);
        }

        int var8 = var2.getBlockId(var3, var4 + 1, var5);
        int var9 = var2.getBlockMetadata(var3, var4 + 1, var5);

        if (var8 != FCBetterThanWolves.fcAestheticNonOpaque.blockID || var9 != 12)
        {
            if (var8 == FCBetterThanWolves.fcBlockCandle.blockID)
            {
                var1.setRenderBounds(0.375D, 0.99609375D, 0.375D, 0.625D, 1.05859375D, 0.625D);
            }
            else
            {
                var1.setRenderBounds(0.40625D, 0.625D, 0.40625D, 0.59375D, 0.8125D, 0.59375D);
            }

            FCClientUtilsRender.RenderStandardBlockWithTexture(var1, var6, var3, var4, var5, this.m_IconLightningRod);
        }

        return true;
    }

    public void RenderLightningRodInvBlock(RenderBlocks var1, Block var2)
    {
        Icon var3 = this.m_IconLightningRod;
        var1.setRenderBounds(0.46875D, 0.0D, 0.46875D, 0.53125D, 1.0D, 0.53125D);
        FCClientUtilsRender.RenderInvBlockWithTexture(var1, var2, -0.5F, -0.5F, -0.5F, var3);
        var1.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.125D, 0.625D);
        FCClientUtilsRender.RenderInvBlockWithTexture(var1, var2, -0.5F, -0.5F, -0.5F, var3);
        var1.setRenderBounds(0.40625D, 0.625D, 0.40625D, 0.59375D, 0.8125D, 0.59375D);
        FCClientUtilsRender.RenderInvBlockWithTexture(var1, var2, -0.5F, -0.5F, -0.5F, var3);
    }
}
