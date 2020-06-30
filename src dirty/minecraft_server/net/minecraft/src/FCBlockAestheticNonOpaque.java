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
}
