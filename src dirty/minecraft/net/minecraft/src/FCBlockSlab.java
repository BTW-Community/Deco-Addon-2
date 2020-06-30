package net.minecraft.src;

public abstract class FCBlockSlab extends Block
{
    public FCBlockSlab(int var1, Material var2)
    {
        super(var1, var2);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
        this.setLightOpacity(255);
        Block.useNeighborBrightness[var1] = true;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        if ((var5 == 0 || var5 != 1 && var7 > 0.5F) && this.CanBePlacedUpsideDownAtLocation(var1, var2, var3, var4))
        {
            var9 = this.SetIsUpsideDown(var9, true);
        }

        return var9;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetBlockBoundsFromPoolFromMetadata(var1.getBlockMetadata(var2, var3, var4));
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

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        boolean var7 = this.GetIsUpsideDown(var1, var2, var3, var4);
        return var7 ? var5 == 1 : var5 == 0;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !this.GetIsUpsideDown(var1, var2, var3, var4) ? -0.5F : 0.0F;
    }

    public boolean IsSnowCoveringTopSurface(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !this.GetIsUpsideDown(var1, var2, var3, var4) ? var1.getBlockId(var2, var3 + 1, var4) == Block.snow.blockID : super.IsSnowCoveringTopSurface(var1, var2, var3, var4);
    }

    public boolean HasContactPointToFullFace(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (var5 < 2)
        {
            boolean var6 = this.GetIsUpsideDown(var1, var2, var3, var4);
            return var6 == (var5 == 1);
        }
        else
        {
            return true;
        }
    }

    public boolean HasContactPointToSlabSideFace(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var6 == this.GetIsUpsideDown(var1, var2, var3, var4);
    }

    public boolean HasNeighborWithMortarInContact(World var1, int var2, int var3, int var4)
    {
        boolean var5 = this.GetIsUpsideDown(var1, var2, var3, var4);
        return this.HasNeighborWithMortarInContact(var1, var2, var3, var4, var5);
    }

    public boolean HasStickySnowNeighborInContact(World var1, int var2, int var3, int var4)
    {
        boolean var5 = this.GetIsUpsideDown(var1, var2, var3, var4);
        return this.HasStickySnowNeighborInContact(var1, var2, var3, var4, var5);
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int var1)
    {
        var1 = this.SetIsUpsideDown(var1, false);
        return super.createStackedBlock(var1);
    }

    public boolean CanMobsSpawnOn(World var1, int var2, int var3, int var4)
    {
        return this.blockMaterial.GetMobsCanSpawnOn(var1.provider.dimensionId);
    }

    public float MobSpawnOnVerticalOffset(World var1, int var2, int var3, int var4)
    {
        return !this.GetIsUpsideDown(var1, var2, var3, var4) ? -0.5F : 0.0F;
    }

    protected boolean HasNeighborWithMortarInContact(World var1, int var2, int var3, int var4, boolean var5)
    {
        if (var5)
        {
            if (FCUtilsWorld.HasNeighborWithMortarInFullFaceContactToFacing(var1, var2, var3, var4, 1))
            {
                return true;
            }
        }
        else if (FCUtilsWorld.HasNeighborWithMortarInFullFaceContactToFacing(var1, var2, var3, var4, 0))
        {
            return true;
        }

        for (int var6 = 2; var6 < 6; ++var6)
        {
            if (FCUtilsWorld.HasNeighborWithMortarInSlabSideContactToFacing(var1, var2, var3, var4, var6, var5))
            {
                return true;
            }
        }

        return false;
    }

    protected boolean HasStickySnowNeighborInContact(World var1, int var2, int var3, int var4, boolean var5)
    {
        if (var5)
        {
            if (FCUtilsWorld.HasStickySnowNeighborInFullFaceContactToFacing(var1, var2, var3, var4, 1))
            {
                return true;
            }
        }
        else if (FCUtilsWorld.HasStickySnowNeighborInFullFaceContactToFacing(var1, var2, var3, var4, 0))
        {
            return true;
        }

        for (int var6 = 2; var6 < 6; ++var6)
        {
            if (FCUtilsWorld.HasStickySnowNeighborInSlabSideContactToFacing(var1, var2, var3, var4, var6, var5))
            {
                return true;
            }
        }

        return false;
    }

    public boolean CanBePlacedUpsideDownAtLocation(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetIsUpsideDown(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsUpsideDown(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean GetIsUpsideDown(int var1)
    {
        return (var1 & 1) > 0;
    }

    public void SetIsUpsideDown(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        var1.setBlockMetadataWithNotify(var2, var3, var4, this.SetIsUpsideDown(var6, var5));
    }

    public int SetIsUpsideDown(int var1, boolean var2)
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

    public boolean ConvertToFullBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var1.setBlockAndMetadataWithNotify(var2, var3, var4, this.GetCombinedBlockID(var5), this.GetCombinedMetadata(var5));
    }

    public abstract int GetCombinedBlockID(int var1);

    public int GetCombinedMetadata(int var1)
    {
        return 0;
    }

    protected AxisAlignedBB GetBlockBoundsFromPoolFromMetadata(int var1)
    {
        return this.GetIsUpsideDown(var1) ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, GetOppositeFacing(var5));
        boolean var7 = this.GetIsUpsideDown(var1, var6.i, var6.j, var6.k);
        return var5 >= 2 ? FCClientUtilsRender.ShouldRenderNeighborHalfSlabSide(var1, var2, var3, var4, var5, var7) : (var5 == 0 ? var7 || !var1.isBlockOpaqueCube(var2, var3, var4) : !var7 || !var1.isBlockOpaqueCube(var2, var3, var4));
    }

    public boolean ShouldRenderNeighborHalfSlabSide(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return this.GetIsUpsideDown(var1, var2, var3, var4) != var6;
    }

    public boolean ShouldRenderNeighborFullFaceSide(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (var5 < 2)
        {
            boolean var6 = this.GetIsUpsideDown(var1, var2, var3, var4);
            return var5 == 0 ? !var6 : var6;
        }
        else
        {
            return true;
        }
    }
}
