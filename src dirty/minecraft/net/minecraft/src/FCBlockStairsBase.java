package net.minecraft.src;

import java.util.List;

public class FCBlockStairsBase extends Block
{
    private boolean m_bRenderingBase = false;

    protected FCBlockStairsBase(int var1, Material var2)
    {
        super(var1, var2);
        this.setLightOpacity(255);
        Block.useNeighborBrightness[var1] = true;
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
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 10;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        this.NotifyAllNearbyBlocksFlat(var1, var2, var3, var4);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        this.NotifyAllNearbyBlocksFlat(var1, var2, var3, var4);
    }

    public int PreBlockPlacedBy(World var1, int var2, int var3, int var4, int var5, EntityLiving var6)
    {
        int var7 = MathHelper.floor_float(var6.rotationYaw * 4.0F / 360.0F + 0.5F) & 3;

        if (var7 == 0)
        {
            var5 = this.SetDirection(var5, 2);
        }
        else if (var7 == 1)
        {
            var5 = this.SetDirection(var5, 1);
        }
        else if (var7 == 2)
        {
            var5 = this.SetDirection(var5, 3);
        }
        else
        {
            var5 = this.SetDirection(var5, 0);
        }

        return this.ValidateMetadataForLocation(var1, var2, var3, var4, var5);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        if (var5 == 0 || var5 != 1 && (double)var7 > 0.5D)
        {
            var9 = this.SetUpsideDown(var9);
        }

        return var9;
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        AxisAlignedBB var8 = this.GetBoundsFromPoolForBase(var1, var2, var3, var4).offset((double)var2, (double)var3, (double)var4);
        var8.AddToListIfIntersects(var5, var6);
        AxisAlignedBB var9 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        boolean var10 = this.GetBoundsForSecondaryPiece(var1, var2, var3, var4, var9);
        var9.offset((double)var2, (double)var3, (double)var4);
        var9.AddToListIfIntersects(var5, var6);

        if (var10)
        {
            AxisAlignedBB var11 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            int var12 = this.GetBoundsForTertiaryPiece(var1, var2, var3, var4, var11);

            if (var12 >= 0)
            {
                var11.offset((double)var2, (double)var3, (double)var4);
                var11.AddToListIfIntersects(var5, var6);
            }
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        AxisAlignedBB var8 = this.GetBoundsFromPoolForBase(var1, var2, var3, var4);
        var7.AddBoxWithLocalCoordsToIntersectionList(var8);
        AxisAlignedBB var9 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        boolean var10 = this.GetBoundsForSecondaryPiece(var1, var2, var3, var4, var9);
        var7.AddBoxWithLocalCoordsToIntersectionList(var9);

        if (var10)
        {
            AxisAlignedBB var11 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            int var12 = this.GetBoundsForTertiaryPiece(var1, var2, var3, var4, var11);

            if (var12 >= 0)
            {
                var7.AddBoxWithLocalCoordsToIntersectionList(var11);
            }
        }

        return var7.GetFirstIntersection();
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        if (var5 == 0)
        {
            return !this.GetIsUpsideDown(var1, var2, var3, var4);
        }
        else if (var5 == 1)
        {
            return this.GetIsUpsideDown(var1, var2, var3, var4);
        }
        else
        {
            int var7 = this.ConvertDirectionToFacing(this.GetDirection(var1, var2, var3, var4));
            return var5 == var7 ? true : (var5 != Block.GetOppositeFacing(var7) ? this.HasSecondaryFullSurfaceToFacing(var1, var2, var3, var4, var5) : false);
        }
    }

    public boolean IsStairBlock()
    {
        return true;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return true;
    }

    public boolean HasContactPointToFullFace(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean HasContactPointToSlabSideFace(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        int var7 = this.ConvertDirectionToFacing(this.GetDirection(var1, var2, var3, var4));
        return var5 == Block.GetOppositeFacing(var7) ? this.GetIsUpsideDown(var1, var2, var3, var4) == var6 : true;
    }

    public boolean HasContactPointToStairNarrowVerticalFace(IBlockAccess var1, int var2, int var3, int var4, int var5, int var6)
    {
        boolean var7 = this.GetIsUpsideDown(var1, var2, var3, var4);

        if (var7 == (var5 == 1))
        {
            return true;
        }
        else
        {
            int var8 = this.ConvertDirectionToFacing(this.GetDirection(var1, var2, var3, var4));
            return var8 != Block.GetOppositeFacing(var6);
        }
    }

    public boolean HasNeighborWithMortarInContact(World var1, int var2, int var3, int var4)
    {
        int var5 = this.ConvertDirectionToFacing(this.GetDirection(var1, var2, var3, var4));
        boolean var6 = this.GetIsUpsideDown(var1, var2, var3, var4);
        return this.HasNeighborWithMortarInContact(var1, var2, var3, var4, var5, var6);
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        int var3 = (var1 & 3) + 2;
        var3 = Block.RotateFacingAroundJ(var3, !var2);
        return var1 & -4 | var3 - 2;
    }

    public boolean CanMobsSpawnOn(World var1, int var2, int var3, int var4)
    {
        return this.blockMaterial.GetMobsCanSpawnOn(var1.provider.dimensionId);
    }

    protected int ValidateMetadataForLocation(World var1, int var2, int var3, int var4, int var5)
    {
        return var5;
    }

    public boolean HasNeighborWithMortarInContact(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        if (!var6)
        {
            if (FCUtilsWorld.HasNeighborWithMortarInFullFaceContactToFacing(var1, var2, var3, var4, 0) || FCUtilsWorld.HasNeighborWithMortarInStairNarrowVerticalContactToFacing(var1, var2, var3, var4, 1, var5))
            {
                return true;
            }
        }
        else if (FCUtilsWorld.HasNeighborWithMortarInFullFaceContactToFacing(var1, var2, var3, var4, 1) || FCUtilsWorld.HasNeighborWithMortarInStairNarrowVerticalContactToFacing(var1, var2, var3, var4, 0, var5))
        {
            return true;
        }

        int var7 = Block.GetOppositeFacing(var5);

        for (int var8 = 2; var8 < 6; ++var8)
        {
            if (var8 == var7)
            {
                if (FCUtilsWorld.HasNeighborWithMortarInSlabSideContactToFacing(var1, var2, var3, var4, var8, var6))
                {
                    return true;
                }
            }
            else if (FCUtilsWorld.HasNeighborWithMortarInStairShapedContactToFacing(var1, var2, var3, var4, var8))
            {
                return true;
            }
        }

        return false;
    }

    protected int ConvertDirectionToFacing(int var1)
    {
        int var2 = 5 - var1;
        return var2;
    }

    protected AxisAlignedBB GetBoundsFromPoolForBase(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetBoundsFromPoolForBase(var1.getBlockMetadata(var2, var3, var4));
    }

    protected AxisAlignedBB GetBoundsFromPoolForBase(int var1)
    {
        return this.GetIsUpsideDown(var1) ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    }

    protected boolean GetBoundsForSecondaryPiece(IBlockAccess var1, int var2, int var3, int var4, AxisAlignedBB var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        int var7 = this.GetDirection(var6);
        boolean var8 = this.GetIsUpsideDown(var6);
        var5.minY = 0.5D;
        var5.maxY = 1.0D;

        if (var8)
        {
            var5.minY = 0.0D;
            var5.maxY = 0.5D;
        }

        var5.minX = 0.0D;
        var5.minZ = 0.0D;
        var5.maxX = 1.0D;
        var5.maxZ = 0.5D;
        boolean var9 = true;
        int var10;
        int var11;
        int var12;

        if (var7 == 0)
        {
            var5.minX = 0.5D;
            var5.maxZ = 1.0D;
            var10 = var1.getBlockId(var2 + 1, var3, var4);
            var11 = var1.getBlockMetadata(var2 + 1, var3, var4);

            if (this.IsStairBlock(var10) && (var6 & 4) == (var11 & 4))
            {
                var12 = var11 & 3;

                if (var12 == 3 && !this.IsStairBlockWithMetadata(var1, var2, var3, var4 + 1, var6))
                {
                    var5.maxZ = 0.5D;
                    var9 = false;
                }
                else if (var12 == 2 && !this.IsStairBlockWithMetadata(var1, var2, var3, var4 - 1, var6))
                {
                    var5.minZ = 0.5D;
                    var9 = false;
                }
            }
        }
        else if (var7 == 1)
        {
            var5.maxX = 0.5D;
            var5.maxZ = 1.0D;
            var10 = var1.getBlockId(var2 - 1, var3, var4);
            var11 = var1.getBlockMetadata(var2 - 1, var3, var4);

            if (this.IsStairBlock(var10) && (var6 & 4) == (var11 & 4))
            {
                var12 = var11 & 3;

                if (var12 == 3 && !this.IsStairBlockWithMetadata(var1, var2, var3, var4 + 1, var6))
                {
                    var5.maxZ = 0.5D;
                    var9 = false;
                }
                else if (var12 == 2 && !this.IsStairBlockWithMetadata(var1, var2, var3, var4 - 1, var6))
                {
                    var5.minZ = 0.5D;
                    var9 = false;
                }
            }
        }
        else if (var7 == 2)
        {
            var5.minZ = 0.5D;
            var5.maxZ = 1.0D;
            var10 = var1.getBlockId(var2, var3, var4 + 1);
            var11 = var1.getBlockMetadata(var2, var3, var4 + 1);

            if (this.IsStairBlock(var10) && (var6 & 4) == (var11 & 4))
            {
                var12 = var11 & 3;

                if (var12 == 1 && !this.IsStairBlockWithMetadata(var1, var2 + 1, var3, var4, var6))
                {
                    var5.maxX = 0.5D;
                    var9 = false;
                }
                else if (var12 == 0 && !this.IsStairBlockWithMetadata(var1, var2 - 1, var3, var4, var6))
                {
                    var5.minX = 0.5D;
                    var9 = false;
                }
            }
        }
        else if (var7 == 3)
        {
            var10 = var1.getBlockId(var2, var3, var4 - 1);
            var11 = var1.getBlockMetadata(var2, var3, var4 - 1);

            if (this.IsStairBlock(var10) && (var6 & 4) == (var11 & 4))
            {
                var12 = var11 & 3;

                if (var12 == 1 && !this.IsStairBlockWithMetadata(var1, var2 + 1, var3, var4, var6))
                {
                    var5.maxX = 0.5D;
                    var9 = false;
                }
                else if (var12 == 0 && !this.IsStairBlockWithMetadata(var1, var2 - 1, var3, var4, var6))
                {
                    var5.minX = 0.5D;
                    var9 = false;
                }
            }
        }

        return var9;
    }

    protected AxisAlignedBB GetBoundsFromPoolForSecondaryPiece(int var1)
    {
        AxisAlignedBB var2 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        int var3 = this.GetDirection(var1);
        boolean var4 = this.GetIsUpsideDown(var1);
        var2.minY = 0.5D;
        var2.maxY = 1.0D;

        if (var4)
        {
            var2.minY = 0.0D;
            var2.maxY = 0.5D;
        }

        var2.minX = 0.0D;
        var2.minZ = 0.0D;
        var2.maxX = 1.0D;
        var2.maxZ = 0.5D;

        if (var3 == 0)
        {
            var2.minX = 0.5D;
            var2.maxZ = 1.0D;
        }
        else if (var3 == 1)
        {
            var2.maxX = 0.5D;
            var2.maxZ = 1.0D;
        }
        else if (var3 == 2)
        {
            var2.minZ = 0.5D;
            var2.maxZ = 1.0D;
        }

        return var2;
    }

    protected int GetBoundsForTertiaryPiece(IBlockAccess var1, int var2, int var3, int var4, AxisAlignedBB var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        int var7 = this.GetDirection(var6);
        boolean var8 = this.GetIsUpsideDown(var6);
        byte var9 = -1;
        var5.minY = 0.5D;
        var5.maxY = 1.0D;

        if (var8)
        {
            var5.minY = 0.0D;
            var5.maxY = 0.5D;
        }

        var5.minX = 0.0D;
        var5.minZ = 0.5D;
        var5.maxX = 0.5D;
        var5.maxZ = 1.0D;
        int var10;
        int var11;
        int var12;

        if (var7 == 0)
        {
            var10 = var1.getBlockId(var2 - 1, var3, var4);
            var11 = var1.getBlockMetadata(var2 - 1, var3, var4);

            if (this.IsStairBlock(var10) && (var6 & 4) == (var11 & 4))
            {
                var12 = var11 & 3;

                if (var12 == 3 && !this.IsStairBlockWithMetadata(var1, var2, var3, var4 - 1, var6))
                {
                    var5.minZ = 0.0D;
                    var5.maxZ = 0.5D;
                    var9 = 2;
                }
                else if (var12 == 2 && !this.IsStairBlockWithMetadata(var1, var2, var3, var4 + 1, var6))
                {
                    var5.minZ = 0.5D;
                    var5.maxZ = 1.0D;
                    var9 = 3;
                }
            }
        }
        else if (var7 == 1)
        {
            var10 = var1.getBlockId(var2 + 1, var3, var4);
            var11 = var1.getBlockMetadata(var2 + 1, var3, var4);

            if (this.IsStairBlock(var10) && (var6 & 4) == (var11 & 4))
            {
                var5.minX = 0.5D;
                var5.maxX = 1.0D;
                var12 = var11 & 3;

                if (var12 == 3 && !this.IsStairBlockWithMetadata(var1, var2, var3, var4 - 1, var6))
                {
                    var5.minZ = 0.0D;
                    var5.maxZ = 0.5D;
                    var9 = 2;
                }
                else if (var12 == 2 && !this.IsStairBlockWithMetadata(var1, var2, var3, var4 + 1, var6))
                {
                    var5.minZ = 0.5D;
                    var5.maxZ = 1.0D;
                    var9 = 3;
                }
            }
        }
        else if (var7 == 2)
        {
            var10 = var1.getBlockId(var2, var3, var4 - 1);
            var11 = var1.getBlockMetadata(var2, var3, var4 - 1);

            if (this.IsStairBlock(var10) && (var6 & 4) == (var11 & 4))
            {
                var5.minZ = 0.0D;
                var5.maxZ = 0.5D;
                var12 = var11 & 3;

                if (var12 == 1 && !this.IsStairBlockWithMetadata(var1, var2 - 1, var3, var4, var6))
                {
                    var9 = 4;
                }
                else if (var12 == 0 && !this.IsStairBlockWithMetadata(var1, var2 + 1, var3, var4, var6))
                {
                    var5.minX = 0.5D;
                    var5.maxX = 1.0D;
                    var9 = 5;
                }
            }
        }
        else if (var7 == 3)
        {
            var10 = var1.getBlockId(var2, var3, var4 + 1);
            var11 = var1.getBlockMetadata(var2, var3, var4 + 1);

            if (this.IsStairBlock(var10) && (var6 & 4) == (var11 & 4))
            {
                var12 = var11 & 3;

                if (var12 == 1 && !this.IsStairBlockWithMetadata(var1, var2 - 1, var3, var4, var6))
                {
                    var9 = 4;
                }
                else if (var12 == 0 && !this.IsStairBlockWithMetadata(var1, var2 + 1, var3, var4, var6))
                {
                    var5.minX = 0.5D;
                    var5.maxX = 1.0D;
                    var9 = 5;
                }
            }
        }

        return var9;
    }

    private boolean HasSecondaryFullSurfaceToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        AxisAlignedBB var6 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        boolean var7 = this.GetBoundsForSecondaryPiece(var1, var2, var3, var4, var6);
        return var7 && var5 == this.GetBoundsForTertiaryPiece(var1, var2, var3, var4, var6);
    }

    protected boolean IsStairBlock(int var1)
    {
        Block var2 = Block.blocksList[var1];
        return var2 != null ? var2.IsStairBlock() : false;
    }

    protected boolean GetIsUpsideDown(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsUpsideDown(var1.getBlockMetadata(var2, var3, var4));
    }

    protected boolean GetIsUpsideDown(int var1)
    {
        return (var1 & 4) != 0;
    }

    protected int SetUpsideDown(int var1)
    {
        return var1 | 4;
    }

    protected int SetIsUpsideDown(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 4;
        }
        else
        {
            var1 &= -5;
        }

        return var1;
    }

    protected void SetIsUpsideDown(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetIsUpsideDown(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    protected void SetDirection(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetDirection(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.SetBlockMetadataWithNotify(var2, var3, var4, var6, 2);
    }

    protected int SetDirection(int var1, int var2)
    {
        var1 &= -4;
        return var1 | var2;
    }

    protected int GetDirection(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetDirection(var1.getBlockMetadata(var2, var3, var4));
    }

    protected int GetDirection(int var1)
    {
        return var1 & 3;
    }

    protected boolean IsStairBlockWithMetadata(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return FCUtilsWorld.IsStairBlock(var1, var2, var3, var4) && var1.getBlockMetadata(var2, var3, var4) == var5;
    }

    protected void NotifyAllNearbyBlocksFlat(World var1, int var2, int var3, int var4)
    {
        var1.notifyBlockOfNeighborChange(var2 - 1, var3, var4, this.blockID);
        var1.notifyBlockOfNeighborChange(var2 - 2, var3, var4, this.blockID);
        var1.notifyBlockOfNeighborChange(var2 + 1, var3, var4, this.blockID);
        var1.notifyBlockOfNeighborChange(var2 + 2, var3, var4, this.blockID);
        var1.notifyBlockOfNeighborChange(var2, var3, var4 - 1, this.blockID);
        var1.notifyBlockOfNeighborChange(var2, var3, var4 - 2, this.blockID);
        var1.notifyBlockOfNeighborChange(var2, var3, var4 + 1, this.blockID);
        var1.notifyBlockOfNeighborChange(var2, var3, var4 + 2, this.blockID);
        var1.notifyBlockOfNeighborChange(var2 - 1, var3, var4 - 1, this.blockID);
        var1.notifyBlockOfNeighborChange(var2 - 1, var3, var4 + 1, this.blockID);
        var1.notifyBlockOfNeighborChange(var2 + 1, var3, var4 - 1, this.blockID);
        var1.notifyBlockOfNeighborChange(var2 + 1, var3, var4 + 1, this.blockID);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return this.renderBlockStairs(var1, var2, var3, var4);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (!this.m_bRenderingBase && var5 < 2)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, GetOppositeFacing(var5));

            if (this.GetIsUpsideDown(var1, var6.i, var6.j, var6.k))
            {
                if (var5 == 1)
                {
                    return false;
                }
            }
            else if (var5 == 0)
            {
                return false;
            }
        }

        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }

    public boolean ShouldRenderNeighborHalfSlabSide(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        boolean var7 = this.GetIsUpsideDown(var1, var2, var3, var4);

        if (var7 == var6)
        {
            return false;
        }
        else
        {
            int var8 = this.ConvertDirectionToFacing(this.GetDirection(var1, var2, var3, var4));
            return var5 != Block.GetOppositeFacing(var8);
        }
    }

    public boolean ShouldRenderNeighborFullFaceSide(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (var5 < 2)
        {
            boolean var7 = this.GetIsUpsideDown(var1, var2, var3, var4);
            return var5 == 0 ? !var7 : var7;
        }
        else
        {
            int var6 = this.ConvertDirectionToFacing(this.GetDirection(var1, var2, var3, var4));
            return var5 != Block.GetOppositeFacing(var6);
        }
    }

    private boolean renderBlockStairs(RenderBlocks var1, int var2, int var3, int var4)
    {
        this.m_bRenderingBase = true;
        var1.setRenderBounds(this.GetBoundsFromPoolForBase(var1.blockAccess, var2, var3, var4));
        var1.renderStandardBlock(this, var2, var3, var4);
        this.m_bRenderingBase = false;
        AxisAlignedBB var5 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        this.GetBoundsForSecondaryPiece(var1.blockAccess, var2, var3, var4, var5);
        var1.setRenderBounds(var5);
        var1.renderStandardBlock(this, var2, var3, var4);
        AxisAlignedBB var7 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        int var8 = this.GetBoundsForTertiaryPiece(var1.blockAccess, var2, var3, var4, var7);

        if (var8 >= 0)
        {
            var1.setRenderBounds(var7);
            var1.renderStandardBlock(this, var2, var3, var4);
        }

        return true;
    }
}
