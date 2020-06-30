package net.minecraft.src;

import java.util.Random;

public class FCBlockStake extends Block
{
    private final double m_dBlockHeight = 0.625D;
    private final double m_dBlockWidth = 0.25D;
    private final double m_dBlockHalfWidth = 0.125D;
    private final int m_iTopTextureIndex = 142;
    private final int m_iTopWithStringTextureIndex = 143;
    private final int m_iSideTextureIndex = 144;
    private final int m_iSideWithTopStringTextureIndex = 145;
    private Icon m_IconTop;
    private Icon m_IconTopWithString;
    private Icon m_IconSide;
    private Icon m_IconSideWithString;
    private Icon m_IconString;

    public FCBlockStake(int var1)
    {
        super(var1, Material.wood);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.SetAxesEffectiveOn(true);
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockStake");
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
        for (int var5 = 0; var5 < 6; ++var5)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);

            if (FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var1, var6.i, var6.j, var6.k, Block.GetOppositeFacing(var5)))
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
        FCUtilsBlockPos var10 = new FCUtilsBlockPos(var2, var3, var4, Block.GetOppositeFacing(var5));

        if (!FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var1, var10.i, var10.j, var10.k, var5))
        {
            var5 = this.FindValidFacing(var1, var2, var3, var4);
        }

        return this.SetFacing(var9, var5);
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = AxisAlignedBB.getAABBPool().getAABB(0.375D, 0.0D, 0.375D, 0.625D, 0.625D, 0.625D);
        var5.TiltToFacingAlongJ(this.GetFacing(var1, var2, var3, var4));
        return var5;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcItemStake.itemID;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4, Block.GetOppositeFacing(var6));

        if (!FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var1, var7.i, var7.j, var7.k, var6))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        ItemStack var10 = var5.getCurrentEquippedItem();
        int var11 = Block.GetOppositeFacing(FCUtilsMisc.ConvertPlacingEntityOrientationToBlockFacingReversed(var5));
        int var12;

        if (this.HasConnectedStringToFacing(var1, var2, var3, var4, var11))
        {
            if (!var1.isRemote)
            {
                var12 = this.ClearStringToFacingNoDrop(var1, var2, var3, var4, var11);

                if (var12 > 0)
                {
                    FCUtilsItem.DropStackAsIfBlockHarvested(var1, var2, var3, var4, new ItemStack(Item.silk.itemID, var12, 0));
                }

                var1.playSoundAtEntity(var5, "random.bow", 0.25F, var1.rand.nextFloat() * 0.4F + 1.2F);
            }

            return true;
        }
        else
        {
            if (var10 != null && var10.getItem().itemID == Item.silk.itemID)
            {
                var12 = var10.stackSize;

                if (var12 > 0)
                {
                    this.GetFacing(var1, var2, var3, var4);
                    int var14 = this.CheckForValidConnectingStakeToFacing(var1, var2, var3, var4, var11, var12);

                    if (var14 <= 0)
                    {
                        int var15 = MathHelper.floor_double((double)(var5.rotationYaw * 8.0F / 360.0F)) & 7;

                        if (var15 >= 0 && var15 <= 3)
                        {
                            var11 = 4;
                        }
                        else
                        {
                            var11 = 5;
                        }

                        var14 = this.CheckForValidConnectingStakeToFacing(var1, var2, var3, var4, var11, var12);

                        if (var14 <= 0)
                        {
                            if (var15 >= 2 && var15 <= 5)
                            {
                                var11 = 2;
                            }
                            else
                            {
                                var11 = 3;
                            }
                        }

                        var14 = this.CheckForValidConnectingStakeToFacing(var1, var2, var3, var4, var11, var12);

                        if (var14 <= 0)
                        {
                            if (var5.rotationPitch > 0.0F)
                            {
                                var11 = 0;
                            }
                            else
                            {
                                var11 = 1;
                            }

                            var14 = this.CheckForValidConnectingStakeToFacing(var1, var2, var3, var4, var11, var12);
                        }
                    }

                    if (var14 > 0)
                    {
                        if (!var1.isRemote)
                        {
                            FCBlockStakeString var19 = (FCBlockStakeString)((FCBlockStakeString)FCBetterThanWolves.fcBlockStakeString);
                            FCUtilsBlockPos var16 = new FCUtilsBlockPos(var2, var3, var4);
                            int var17;

                            for (var17 = 0; var17 < var14; ++var17)
                            {
                                var16.AddFacingAsOffset(var11);
                                int var18 = var1.getBlockId(var16.i, var16.j, var16.k);

                                if (var18 != var19.blockID)
                                {
                                    var1.setBlock(var16.i, var16.j, var16.k, var19.blockID, 0, 2);
                                }

                                var19.SetExtendsAlongFacing(var1, var16.i, var16.j, var16.k, var11, true, false);

                                if (!var5.capabilities.isCreativeMode)
                                {
                                    --var10.stackSize;
                                }
                            }

                            var16 = new FCUtilsBlockPos(var2, var3, var4);

                            for (var17 = 0; var17 < var14; ++var17)
                            {
                                var16.AddFacingAsOffset(var11);
                                var1.notifyBlockChange(var16.i, var16.j, var16.k, FCBetterThanWolves.fcBlockStakeString.blockID);
                            }

                            var1.playSoundAtEntity(var5, "random.bow", 0.25F, var1.rand.nextFloat() * 0.2F + 0.8F);
                        }
                        else if (!var5.capabilities.isCreativeMode)
                        {
                            var10.stackSize -= var14;
                        }
                    }

                    return true;
                }
            }

            return false;
        }
    }

    public int GetFacing(int var1)
    {
        return var1 & 7;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= -8;
        var1 |= var2;
        return var1;
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

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }

    private int CheckForValidConnectingStakeToFacing(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCBlockStakeString var7 = (FCBlockStakeString)((FCBlockStakeString)FCBetterThanWolves.fcBlockStakeString);
        FCUtilsBlockPos var8 = new FCUtilsBlockPos(var2, var3, var4);
        boolean var9 = false;

        for (int var10 = 0; var10 <= var6; ++var10)
        {
            var8.AddFacingAsOffset(var5);

            if (!var1.isAirBlock(var8.i, var8.j, var8.k))
            {
                int var11 = var1.getBlockId(var8.i, var8.j, var8.k);

                if (var11 == this.blockID)
                {
                    return var10;
                }

                if (var11 == var7.blockID)
                {
                    if (var7.GetExtendsAlongFacing(var1, var8.i, var8.j, var8.k, var5))
                    {
                        return 0;
                    }
                }
                else
                {
                    Block var12 = Block.blocksList[var11];

                    if (!var12.blockMaterial.isReplaceable() || var12.blockMaterial.isLiquid())
                    {
                        return 0;
                    }
                }
            }
        }

        return 0;
    }

    private int FindValidFacing(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < 6; ++var5)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);

            if (FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var1, var6.i, var6.j, var6.k, Block.GetOppositeFacing(var5)))
            {
                return Block.GetOppositeFacing(var5);
            }
        }

        return 0;
    }

    public boolean HasConnectedStringToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        FCBlockStakeString var6 = (FCBlockStakeString)((FCBlockStakeString)FCBetterThanWolves.fcBlockStakeString);
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
        var7.AddFacingAsOffset(var5);
        int var8 = var1.getBlockId(var7.i, var7.j, var7.k);
        return var8 == var6.blockID ? var6.GetExtendsAlongFacing(var1, var7.i, var7.j, var7.k, var5) : false;
    }

    private int ClearStringToFacingNoDrop(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = 0;
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);

        do
        {
            var7.AddFacingAsOffset(var5);

            if (var1.getBlockId(var7.i, var7.j, var7.k) != FCBetterThanWolves.fcBlockStakeString.blockID)
            {
                break;
            }

            FCBlockStakeString var8 = (FCBlockStakeString)FCBetterThanWolves.fcBlockStakeString;

            if (!var8.GetExtendsAlongFacing(var1, var7.i, var7.j, var7.k, var5))
            {
                break;
            }

            if (var8.GetExtendsAlongOtherFacing(var1, var7.i, var7.j, var7.k, var5))
            {
                var8.SetExtendsAlongFacing(var1, var7.i, var7.j, var7.k, var5, false, false);
            }
            else
            {
                var1.setBlock(var7.i, var7.j, var7.k, 0, 0, 2);
            }

            ++var6;
        }
        while (var6 < 64);

        if (var6 > 0)
        {
            var7 = new FCUtilsBlockPos(var2, var3, var4);

            for (int var9 = 0; var9 < var6; ++var9)
            {
                var7.AddFacingAsOffset(var5);
                var1.notifyBlockChange(var7.i, var7.j, var7.k, FCBetterThanWolves.fcBlockStakeString.blockID);
            }
        }

        return var6;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        Icon var2 = var1.registerIcon("fcBlockStake_side");
        this.blockIcon = var2;
        this.m_IconTop = var1.registerIcon("fcBlockStake_top");
        this.m_IconTopWithString = var1.registerIcon("fcBlockStake_top_string");
        this.m_IconSide = var2;
        this.m_IconSideWithString = var1.registerIcon("fcBlockStake_side_string");
        this.m_IconString = var1.registerIcon("fcBlockStakeString");
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        boolean var7 = this.HasConnectedStringToFacing(var1, var2, var3, var4, var6);
        return var5 != var6 && var5 != Block.GetOppositeFacing(var6) ? (!var7 ? this.m_IconSide : this.m_IconSideWithString) : (!var7 ? this.m_IconTop : this.m_IconTopWithString);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return true;
    }

    private AxisAlignedBB GetBoundsFromPoolForStringToFacing(int var1)
    {
        AxisAlignedBB var2 = AxisAlignedBB.getAABBPool().getAABB(0.4921875D, 0.5D, 0.4921875D, 0.5078125D, 1.0D, 0.5078125D);
        var2.TiltToFacingAlongJ(var1);
        return var2;
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
        int var6 = this.GetFacing(var5, var2, var3, var4);

        if (var6 == 0)
        {
            var1.SetUvRotateSouth(3);
            var1.SetUvRotateNorth(3);
            var1.SetUvRotateEast(3);
            var1.SetUvRotateWest(3);
        }
        else if (var6 == 2)
        {
            var1.SetUvRotateSouth(1);
            var1.SetUvRotateNorth(2);
        }
        else if (var6 == 3)
        {
            var1.SetUvRotateSouth(2);
            var1.SetUvRotateNorth(1);
            var1.SetUvRotateTop(3);
            var1.SetUvRotateBottom(3);
        }
        else if (var6 == 4)
        {
            var1.SetUvRotateEast(1);
            var1.SetUvRotateWest(2);
            var1.SetUvRotateTop(2);
            var1.SetUvRotateBottom(1);
        }
        else if (var6 == 5)
        {
            var1.SetUvRotateEast(2);
            var1.SetUvRotateWest(1);
            var1.SetUvRotateTop(1);
            var1.SetUvRotateBottom(2);
        }

        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        var1.renderStandardBlock(this, var2, var3, var4);
        var1.ClearUvRotation();
        Icon var7 = this.m_IconString;

        for (int var8 = 0; var8 < 6; ++var8)
        {
            if (this.HasConnectedStringToFacing(var5, var2, var3, var4, var8))
            {
                var1.setRenderBounds(this.GetBoundsFromPoolForStringToFacing(var8));
                FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, var7);
            }
        }

        return true;
    }
}
