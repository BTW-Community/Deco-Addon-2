package net.minecraft.src;

public class FCBlockGroundCover extends Block
{
    public static final float m_fVisualHeight = 0.125F;

    protected FCBlockGroundCover(int var1, Material var2)
    {
        super(var1, var2);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
        this.setHardness(0.1F);
        this.SetShovelsEffectiveOn();
        this.SetBuoyant();
        this.setLightOpacity(0);
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
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 - 1, var4);
        Block var6 = Block.blocksList[var5];
        return var6 != null ? var6.CanGroundCoverRestOnBlock(var1, var2, var3 - 1, var4) : false;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!this.canPlaceBlockAt(var1, var2, var3, var4))
        {
            var1.setBlockToAir(var2, var3, var4);
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        float var7 = 0.0F;
        int var8 = var1.getBlockId(var2, var3 - 1, var4);
        Block var9 = Block.blocksList[var8];

        if (var9 != null)
        {
            var7 = var9.GroundCoverRestingOnVisualOffset(var1, var2, var3 - 1, var4);
        }

        FCUtilsRayTraceVsComplexBlock var10 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        var10.AddBoxWithLocalCoordsToIntersectionList(0.0D, (double)var7, 0.0D, 1.0D, (double)(0.125F + var7), 1.0D);
        return var10.GetFirstIntersection();
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    public boolean IsGroundCover()
    {
        return true;
    }

    public static void ClearAnyGroundCoverRestingOnBlock(World var0, int var1, int var2, int var3)
    {
        Block var4 = Block.blocksList[var0.getBlockId(var1, var2 + 1, var3)];

        if (var4 != null)
        {
            if (var4.IsGroundCover())
            {
                var0.setBlockToAir(var1, var2 + 1, var3);
            }
            else if (var4.GroundCoverRestingOnVisualOffset(var0, var1, var2 + 1, var3) < -0.99F)
            {
                Block var5 = Block.blocksList[var0.getBlockId(var1, var2 + 2, var3)];

                if (var5 != null && var5.IsGroundCover())
                {
                    var0.setBlockToAir(var1, var2 + 2, var3);
                }
            }
        }
    }

    public static boolean IsGroundCoverRestingOnBlock(World var0, int var1, int var2, int var3)
    {
        Block var4 = Block.blocksList[var0.getBlockId(var1, var2 + 1, var3)];

        if (var4 != null)
        {
            if (var4.IsGroundCover())
            {
                return true;
            }

            if (var4.GroundCoverRestingOnVisualOffset(var0, var1, var2 + 1, var3) < -0.99F)
            {
                Block var5 = Block.blocksList[var0.getBlockId(var1, var2 + 2, var3)];

                if (var5 != null && var5.IsGroundCover())
                {
                    return true;
                }
            }
        }

        return false;
    }

    public void ClientBreakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        var1.markBlockRangeForRenderUpdate(var2, var3 - 1, var4, var2, var3 - 2, var4);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        float var5 = 0.0F;
        int var6 = var1.getBlockId(var2, var3 - 1, var4);
        Block var7 = Block.blocksList[var6];

        if (var7 != null)
        {
            var5 = var7.GroundCoverRestingOnVisualOffset(var1, var2, var3 - 1, var4);
        }

        return this.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4).offset((double)var2, (double)((float)var3 + var5), (double)var4);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;

        if (var5.getBlockId(var2, var3 - 1, var4) != 0)
        {
            float var6 = 0.0F;
            int var7 = var5.getBlockId(var2, var3 - 1, var4);
            Block var8 = Block.blocksList[var7];
            int var9 = (var5.getBlockMetadata(var2, var3, var4) & 7) + 1;

            if (var8 != null)
            {
                var6 = var8.GroundCoverRestingOnVisualOffset(var5, var2, var3 - 1, var4);

                if (var6 < 0.0F)
                {
                    --var3;
                    ++var6;
                }
            }

            float var10 = 0.125F * (float)var9;
            var1.setRenderBounds(0.0D, (double)var6, 0.0D, 1.0D, (double)(var10 + var6), 1.0D);
            FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, this.blockIcon);
        }

        return true;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (var5 >= 2)
        {
            if (var1.isBlockOpaqueCube(var2, var3, var4))
            {
                return false;
            }
            else
            {
                if (var1.getBlockId(var2, var3, var4) == this.blockID)
                {
                    FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, Block.GetOppositeFacing(var5));

                    if (var1.getBlockId(var6.i, var6.j, var6.k) == this.blockID)
                    {
                        int var7 = var1.getBlockId(var2, var3 - 1, var4);

                        if (var7 != 0 && blocksList[var7].GroundCoverRestingOnVisualOffset(var1, var2, var3 - 1, var4) > -0.01F)
                        {
                            return false;
                        }
                    }
                }

                return true;
            }
        }
        else
        {
            return var5 == 1 ? true : super.shouldSideBeRendered(var1, var2, var3, var4, var5);
        }
    }

    public boolean ShouldRenderNeighborFullFaceSide(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (var5 == 1)
        {
            int var6 = var1.getBlockId(var2, var3 - 1, var4);

            if (var6 != 0 && blocksList[var6].GroundCoverRestingOnVisualOffset(var1, var2, var3 - 1, var4) > -0.125F)
            {
                return false;
            }
        }

        return true;
    }
}
