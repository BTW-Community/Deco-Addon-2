package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockUnfiredPottery extends Block
{
    public static final int m_iNumSubtypes = 14;
    public static final int m_iNumSpunPotteryTypes = 5;
    public static final int m_iSubtypeCrucible = 0;
    public static final int m_iSubtypePlanter = 1;
    public static final int m_iSubtypeVase = 2;
    public static final int m_iSubtypeUrn = 3;
    public static final int m_iSubtypeMould = 4;
    public static final int m_iSubtypeClayBrick = 5;
    public static final int m_iSubtypeClayBrickIAligned = 6;
    public static final int m_iSubtypeNetherBrick = 7;
    public static final int m_iSubtypeNetherBrickIAligned = 8;
    public static final int m_iSubtypeUncookedCake = 9;
    public static final int m_iSubtypeUncookedCookies = 10;
    public static final int m_iSubtypeUncookedCookiesIAligned = 11;
    public static final int m_iSubtypeUncookedPumpkinPie = 12;
    public static final int m_iSubtypeUncookedBread = 13;
    public static final int m_iSubtypeUncookedBreadIAligned = 14;
    public static final int m_iRotationsOnTurntableToChangState = 8;
    public static final float m_fUnfiredPotteryUrnBaseWidth = 0.25F;
    public static final float m_fUnfiredPotteryUrnBaseHalfWidth = 0.125F;
    public static final float m_fUnfiredPotteryUrnBaseHeight = 0.0625F;
    public static final float m_fUnfiredPotteryUrnBodyWidth = 0.375F;
    public static final float m_fUnfiredPotteryUrnBodyHalfWidth = 0.1875F;
    public static final float m_fUnfiredPotteryUrnBodyHeight = 0.375F;
    public static final float m_fUnfiredPotteryUrnNeckWidth = 0.25F;
    public static final float m_fUnfiredPotteryUrnNeckHalfWidth = 0.125F;
    public static final float m_fUnfiredPotteryUrnNeckHeight = 0.0625F;
    public static final float m_fUnfiredPotteryUrnTopWidth = 0.375F;
    public static final float m_fUnfiredPotteryUrnTopHalfWidth = 0.1875F;
    public static final float m_fUnfiredPotteryUrnTopHeight = 0.0625F;
    public static final float m_fUnfiredPotteryUrnLidWidth = 0.25F;
    public static final float m_fUnfiredPotteryUrnLidHalfWidth = 0.125F;
    public static final float m_fUnfiredPotteryUrnLidHeight = 0.0625F;
    public static final float m_fUnfiredPotteryUrnHeight = 0.625F;
    public static final float m_fUnfiredPotteryMouldHeight = 0.125F;
    public static final float m_fUnfiredPotteryMouldWidth = 0.375F;
    public static final float m_fUnfiredPotteryMouldHalfWidth = 0.1875F;
    public static final float m_fUnfiredPotteryBrickHeight = 0.25F;
    public static final float m_fUnfiredPotteryBrickWidth = 0.375F;
    public static final float m_fUnfiredPotteryBrickHalfWidth = 0.1875F;
    public static final float m_fUnfiredPotteryBrickLength = 0.75F;
    public static final float m_fUnfiredPotteryBrickHalfLength = 0.375F;
    public static final float m_fUnfiredPotteryUncookedCakeHeight = 0.5F;
    public static final float m_fUnfiredPotteryUncookedCakeWidth = 0.875F;
    public static final float m_fUnfiredPotteryUncookedCakeHalfWidth = 0.4375F;
    public static final float m_fUnfiredPotteryUncookedCakeLength = 0.875F;
    public static final float m_fUnfiredPotteryUncookedCakeHalfLength = 0.4375F;
    public static final float m_fUnfiredPotteryUncookedCookiesHeight = 0.0625F;
    public static final float m_fUnfiredPotteryUncookedCookiesWidth = 0.375F;
    public static final float m_fUnfiredPotteryUncookedCookiesHalfWidth = 0.1875F;
    public static final float m_fUnfiredPotteryUncookedCookiesLength = 0.875F;
    public static final float m_fUnfiredPotteryUncookedCookiesHalfLength = 0.4375F;
    public static final float m_fUnfiredPotteryUncookedCookiesIndividualWidth = 0.125F;
    public static final float m_fUnfiredPotteryUncookedCookiesIndividualHalfWidth = 0.0625F;
    public static final float m_fUnfiredPotteryUncookedPumpkinPieHeight = 0.25F;
    public static final float m_fUnfiredPotteryUncookedPumpkinPieWidth = 0.75F;
    public static final float m_fUnfiredPotteryUncookedPumpkinPieHalfWidth = 0.375F;
    public static final float m_fUnfiredPotteryUncookedPumpkinPieLength = 0.75F;
    public static final float m_fUnfiredPotteryUncookedPumpkinPieHalfLength = 0.375F;
    private Icon m_IconNetherSludge;
    private Icon m_IconUncookedPastry;
    private Icon m_IconUncookedPumpkinPieTop;

    public FCBlockUnfiredPottery(int var1)
    {
        super(var1, Material.clay);
        this.setHardness(0.6F);
        this.SetShovelsEffectiveOn(true);
        this.setStepSound(FCBetterThanWolves.fcStepSoundSquish);
        this.SetCanBeCookedByKiln(true);
        this.setUnlocalizedName("fcBlockUnfiredPottery");
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        if (!FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        if (var5 == 4 || var5 == 5)
        {
            if (var9 == 5)
            {
                var9 = 6;
            }
            else if (var9 == 7)
            {
                var9 = 8;
            }
            else if (var9 == 10)
            {
                var9 = 11;
            }
            else if (var9 == 13)
            {
                var9 = 14;
            }
        }

        return var9;
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(var5);

        if (var7 == 4 || var7 == 5)
        {
            int var8 = var1.getBlockMetadata(var2, var3, var4);

            if (var8 == 5)
            {
                var1.setBlockMetadataWithNotify(var2, var3, var4, 6);
            }
            else if (var8 == 7)
            {
                var1.setBlockMetadataWithNotify(var2, var3, var4, 8);
            }
            else if (var8 == 10)
            {
                var1.setBlockMetadataWithNotify(var2, var3, var4, 11);
            }
            else if (var8 == 13)
            {
                var1.setBlockMetadataWithNotify(var2, var3, var4, 14);
            }
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return var1 != 5 && var1 != 6 ? (var1 != 7 && var1 != 8 ? (var1 == 9 ? FCBetterThanWolves.fcItemPastryUncookedCake.itemID : (var1 != 10 && var1 != 11 ? (var1 == 12 ? FCBetterThanWolves.fcItemPastryUncookedPumpkinPie.itemID : (var1 != 13 && var1 != 14 ? this.blockID : FCBetterThanWolves.fcItemBreadDough.itemID)) : FCBetterThanWolves.fcItemPastryUncookedCookies.itemID)) : FCBetterThanWolves.fcItemNetherSludge.itemID) : Item.clay.itemID;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1 < 5 ? var1 : 0;
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

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        switch (var5)
        {
            case 0:
            case 1:
            default:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

            case 2:
                return AxisAlignedBB.getAABBPool().getAABB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D);

            case 3:
                return AxisAlignedBB.getAABBPool().getAABB(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.625D, 0.6875D);

            case 4:
                return AxisAlignedBB.getAABBPool().getAABB(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.125D, 0.6875D);

            case 5:
            case 7:
            case 13:
                return AxisAlignedBB.getAABBPool().getAABB(0.3125D, 0.0D, 0.125D, 0.6875D, 0.25D, 0.875D);

            case 6:
            case 8:
            case 14:
                return AxisAlignedBB.getAABBPool().getAABB(0.125D, 0.0D, 0.3125D, 0.875D, 0.25D, 0.6875D);

            case 9:
                return AxisAlignedBB.getAABBPool().getAABB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D);

            case 10:
                return AxisAlignedBB.getAABBPool().getAABB(0.3125D, 0.0D, 0.0625D, 0.6875D, 0.0625D, 0.9375D);

            case 11:
                return AxisAlignedBB.getAABBPool().getAABB(0.0625D, 0.0D, 0.3125D, 0.9375D, 0.0625D, 0.6875D);

            case 12:
                return AxisAlignedBB.getAABBPool().getAABB(0.125D, 0.0D, 0.125D, 0.875D, 0.25D, 0.875D);
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockToAir(var2, var3, var4);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true) && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 1);
        }
    }

    public int GetCookTimeMultiplierInKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        switch (var5)
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return 4;

            default:
                return 1;
        }
    }

    public int GetItemIndexDroppedWhenCookedByKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        switch (var5)
        {
            case 0:
                return FCBetterThanWolves.fcCrucible.blockID;

            case 1:
                return FCBetterThanWolves.fcPlanter.blockID;

            case 2:
                return FCBetterThanWolves.fcVase.blockID;

            case 3:
                return FCBetterThanWolves.fcItemUrn.itemID;

            case 4:
                return FCBetterThanWolves.fcItemMould.itemID;

            case 5:
            case 6:
                return Item.brick.itemID;

            case 7:
            case 8:
                return FCBetterThanWolves.fcItemNetherBrick.itemID;

            case 9:
                return Item.cake.itemID;

            case 10:
            case 11:
            default:
                return -1;

            case 12:
                return Item.pumpkinPie.itemID;

            case 13:
            case 14:
                return Item.bread.itemID;
        }
    }

    public void OnCookedByKiln(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if (var5 != 10 && var5 != 11)
        {
            super.OnCookedByKiln(var1, var2, var3, var4);
        }
        else
        {
            var1.setBlockToAir(var2, var3, var4);

            for (int var6 = 0; var6 < 8; ++var6)
            {
                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, Item.cookie.itemID, 0);
            }
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        int var7 = var1.getBlockMetadata(var2, var3, var4);
        return var7 == 2 ? FCBetterThanWolves.fcVase.collisionRayTrace(var1, var2, var3, var4, var5, var6) : (var7 == 1 ? FCBetterThanWolves.fcPlanter.collisionRayTrace(var1, var2, var3, var4, var5, var6) : super.collisionRayTrace(var1, var2, var3, var4, var5, var6));
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        if (var1 == 5)
        {
            var1 = 6;
        }
        else if (var1 == 6)
        {
            var1 = 5;
        }
        else if (var1 == 7)
        {
            var1 = 8;
        }
        else if (var1 == 8)
        {
            var1 = 7;
        }
        else if (var1 == 10)
        {
            var1 = 11;
        }
        else if (var1 == 11)
        {
            var1 = 10;
        }
        else if (var1 == 13)
        {
            var1 = 14;
        }
        else if (var1 == 14)
        {
            var1 = 13;
        }

        return var1;
    }

    public void OnRotatedOnTurntable(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if (var5 < 5)
        {
            var1.playAuxSFX(2252, var2, var3, var4, this.blockID);
        }
    }

    public int RotateOnTurntable(World var1, int var2, int var3, int var4, boolean var5, int var6)
    {
        var6 = super.RotateOnTurntable(var1, var2, var3, var4, var5, var6);
        var6 = this.TurntableCraftingRotation(var1, var2, var3, var4, var5, var6);
        return var6;
    }

    public int GetRotationsToCraftOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 8;
    }

    public int GetNewBlockIDCraftedOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 < 4 ? this.blockID : 0;
    }

    public int GetNewMetadataCraftedOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 < 4 ? var5 + 1 : 0;
    }

    public int GetItemIDCraftedOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 != 7 && var5 != 8 ? (var5 == 9 ? FCBetterThanWolves.fcItemPastryUncookedCake.itemID : (var5 != 10 && var5 != 11 ? (var5 == 12 ? FCBetterThanWolves.fcItemPastryUncookedPumpkinPie.itemID : (var5 != 13 && var5 != 14 ? Item.clay.itemID : FCBetterThanWolves.fcItemBreadDough.itemID)) : FCBetterThanWolves.fcItemPastryUncookedCookies.itemID)) : FCBetterThanWolves.fcItemNetherSludge.itemID;
    }

    public int GetItemCountCraftedOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 != 0 && var5 != 1 && var5 != 2 ? 1 : 2;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 != 2 && var5 != 3 ? super.CanGroundCoverRestOnBlock(var1, var2, var3, var4) : var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 != 2 && var5 != 3 ? super.GroundCoverRestingOnVisualOffset(var1, var2, var3, var4) : -1.0F;
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
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_IconNetherSludge = var1.registerIcon("fcBlockNetherSludge");
        this.m_IconUncookedPastry = var1.registerIcon("fcBlockPastryUncooked");
        this.m_IconUncookedPumpkinPieTop = var1.registerIcon("fcBlockUncookedPumpkinPie_top");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var2 != 7 && var2 != 8 ? (var2 >= 9 ? (var1 == 1 && var2 == 12 ? this.m_IconUncookedPumpkinPieTop : this.m_IconUncookedPastry) : this.blockIcon) : this.m_IconNetherSludge;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 0));
        var3.add(new ItemStack(var1, 1, 1));
        var3.add(new ItemStack(var1, 1, 2));
        var3.add(new ItemStack(var1, 1, 3));
        var3.add(new ItemStack(var1, 1, 4));
        var3.add(new ItemStack(var1, 1, 5));
        var3.add(new ItemStack(var1, 1, 7));
        var3.add(new ItemStack(var1, 1, 9));
        var3.add(new ItemStack(var1, 1, 10));
        var3.add(new ItemStack(var1, 1, 12));
        var3.add(new ItemStack(var1, 1, 13));
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = var5.getBlockMetadata(var2, var3, var4);

        switch (var6)
        {
            case 0:
                Icon var7 = this.getBlockTexture(var5, var2, var3, var4, 0);
                RenderUnfiredCrucible(var1, var5, var2, var3, var4, this, var7);
                break;

            case 1:
                FCBlockPlanter.RenderEmptyPlanterBlock(var1, var5, var2, var3, var4, this);
                break;

            case 2:
                FCBlockVase.RenderVaseBlock(var1, var5, var2, var3, var4, this);
                break;

            case 3:
                Icon var8 = this.getBlockTexture(var5, var2, var3, var4, 0);
                RenderUnfiredUrn(var1, var5, var2, var3, var4, this, var8, 0.0F);
                break;

            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            default:
                super.RenderBlock(var1, var2, var3, var4);
                break;

            case 10:
            case 11:
                RenderUncookedCookies(var1, var5, var2, var3, var4, this, var6 == 11);
        }

        return true;
    }

    public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        this.RenderCookingByKilnOverlay(var1, var2, var3, var4, var5);
    }

    public static boolean RenderUnfiredUrn(RenderBlocks var0, IBlockAccess var1, int var2, int var3, int var4, Block var5, Icon var6, float var7)
    {
        var0.setRenderBounds(0.375D, (double)(0.0F + var7), 0.375D, 0.625D, (double)(0.0625F + var7), 0.625D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        var0.setRenderBounds(0.3125D, (double)(0.0625F + var7), 0.3125D, 0.6875D, (double)(0.4375F + var7), 0.6875D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        var0.setRenderBounds(0.375D, (double)(0.4375F + var7), 0.375D, 0.625D, (double)(0.5F + var7), 0.625D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        var0.setRenderBounds(0.3125D, (double)(0.5F + var7), 0.3125D, 0.6875D, (double)(0.5625F + var7), 0.6875D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        var0.setRenderBounds(0.375D, (double)(0.5625F + var7), 0.375D, 0.625D, (double)(0.625F + var7), 0.625D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        return true;
    }

    public static boolean RenderUnfiredCrucible(RenderBlocks var0, IBlockAccess var1, int var2, int var3, int var4, Block var5, Icon var6)
    {
        var0.setRenderBounds(0.0625D, 0.0D, 0.0625D, 0.1875D, 1.0D, 0.8125D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        var0.setRenderBounds(0.0625D, 0.0D, 0.8125D, 0.8125D, 1.0D, 0.9375D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        var0.setRenderBounds(0.8125D, 0.0D, 0.1875D, 0.9375D, 1.0D, 0.9375D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        var0.setRenderBounds(0.1875D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.1875D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        var0.setRenderBounds(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.125D, 0.8125D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        var0.setRenderBounds(0.0D, 0.125D, 0.0D, 0.125D, 0.875D, 0.875D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        var0.setRenderBounds(0.0D, 0.125D, 0.875D, 0.875D, 0.875D, 1.0D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        var0.setRenderBounds(0.875D, 0.125D, 0.125D, 1.0D, 0.875D, 1.0D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        var0.setRenderBounds(0.125D, 0.125D, 0.0D, 1.0D, 0.875D, 0.125D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, var6);
        return true;
    }

    public static boolean RenderUncookedCookies(RenderBlocks var0, IBlockAccess var1, int var2, int var3, int var4, Block var5, boolean var6)
    {
        byte var7;
        byte var8;
        float var9;
        float var10;

        if (!var6)
        {
            var7 = 2;
            var8 = 4;
            var9 = 0.375F;
            var10 = 0.125F;
        }
        else
        {
            var7 = 4;
            var8 = 2;
            var9 = 0.125F;
            var10 = 0.375F;
        }

        for (int var11 = 0; var11 < var7; ++var11)
        {
            for (int var12 = 0; var12 < var8; ++var12)
            {
                float var13 = var9 + (float)var11 * 0.125F * 2.0F;
                float var14 = var10 + (float)var12 * 0.125F * 2.0F;
                var0.setRenderBounds((double)(var13 - 0.0625F), 0.0D, (double)(var14 - 0.0625F), (double)(var13 + 0.0625F), 0.0625D, (double)(var14 + 0.0625F));
                var0.renderStandardBlock(var5, var2, var3, var4);
            }
        }

        return true;
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        switch (var2)
        {
            case 0:
                RenderUnfiredCrucibleInvBlock(var1, this, var2, this.blockIcon);
                break;

            case 1:
                FCBlockPlanter.RenderEmptyPlanterInvBlock(var1, this, var2);
                break;

            case 2:
                FCBlockVase.RenderInvBlock(var1, this, var2);
                break;

            case 3:
                RenderUnfiredUrnInvBlock(var1, this, var2, this.blockIcon);
                break;

            case 4:
                var1.setRenderBounds(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.125D, 0.6875D);

            default:
                FCClientUtilsRender.RenderInvBlockWithMetadata(var1, this, -0.5F, -0.5F, -0.5F, var2);
        }
    }

    public static void RenderUnfiredUrnInvBlock(RenderBlocks var0, Block var1, int var2, Icon var3)
    {
        var0.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.0625D, 0.625D);
        FCClientUtilsRender.RenderInvBlockWithTexture(var0, var1, -0.5F, -0.5F, -0.5F, var3);
        var0.setRenderBounds(0.3125D, 0.0625D, 0.3125D, 0.6875D, 0.4375D, 0.6875D);
        FCClientUtilsRender.RenderInvBlockWithTexture(var0, var1, -0.5F, -0.5F, -0.5F, var3);
        var0.setRenderBounds(0.375D, 0.4375D, 0.375D, 0.625D, 0.5D, 0.625D);
        FCClientUtilsRender.RenderInvBlockWithTexture(var0, var1, -0.5F, -0.5F, -0.5F, var3);
        var0.setRenderBounds(0.3125D, 0.5D, 0.3125D, 0.6875D, 0.5625D, 0.6875D);
        FCClientUtilsRender.RenderInvBlockWithTexture(var0, var1, -0.5F, -0.5F, -0.5F, var3);
        var0.setRenderBounds(0.375D, 0.5625D, 0.375D, 0.625D, 0.625D, 0.625D);
        FCClientUtilsRender.RenderInvBlockWithTexture(var0, var1, -0.5F, -0.5F, -0.5F, var3);
    }

    public static void RenderUnfiredCrucibleInvBlock(RenderBlocks var0, Block var1, int var2, Icon var3)
    {
        var0.setRenderBounds(0.0625D, 0.0D, 0.0625D, 0.1875D, 1.0D, 0.8125D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, 0);
        var0.setRenderBounds(0.0625D, 0.0D, 0.8125D, 0.8125D, 1.0D, 0.9375D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, 0);
        var0.setRenderBounds(0.8125D, 0.0D, 0.1875D, 0.9375D, 1.0D, 0.9375D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, 0);
        var0.setRenderBounds(0.1875D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.1875D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, 0);
        var0.setRenderBounds(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.125D, 0.8125D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, 0);
        var0.setRenderBounds(0.0D, 0.125D, 0.0D, 0.125D, 0.875D, 0.875D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, 0);
        var0.setRenderBounds(0.0D, 0.125D, 0.875D, 0.875D, 0.875D, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, 0);
        var0.setRenderBounds(0.875D, 0.125D, 0.125D, 1.0D, 0.875D, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, 0);
        var0.setRenderBounds(0.125D, 0.125D, 0.0D, 1.0D, 0.875D, 0.125D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, 0);
    }
}
