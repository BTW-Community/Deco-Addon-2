package net.minecraft.src;

import java.util.List;

public class FCBlockMoulding extends Block
{
    protected static final double m_dMouldingWidth = 0.5D;
    protected static final double m_dMouldingHalfWidth = 0.25D;
    protected static final double m_dMouldingLength = 1.0D;
    private static final int[][] m_iFacingOfConnections = new int[][] {{ -1, 4, -1, 5, 5, 4, 4, 5, -1, 4, -1, 5}, {1, 1, 1, 1, -1, -1, -1, -1, 0, 0, 0, 0}, {3, -1, 2, -1, 3, 3, 2, 2, 3, -1, 2, -1}};
    private static final int[][] m_iAlignmentOffsetAlongAxis = new int[][] {{0, 1, 0, -1, -1, 1, 1, -1, 0, 1, 0, -1}, { -1, -1, -1, -1, 0, 0, 0, 0, 1, 1, 1, 1}, { -1, 0, 1, 0, -1, -1, 1, 1, -1, 0, 1, 0}};
    protected int m_iMatchingCornerBlockID;
    String m_sTextureName;

    protected FCBlockMoulding(int var1, Material var2, String var3, int var4, float var5, float var6, StepSound var7, String var8)
    {
        super(var1, var2);
        this.setHardness(var5);
        this.setResistance(var6);
        this.InitBlockBounds(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 1.0D);
        this.setStepSound(var7);
        this.setUnlocalizedName(var8);
        this.m_iMatchingCornerBlockID = var4;
        this.m_sTextureName = var3;
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
        int var7 = this.GetMouldingAlignment(var1, var2, var3, var4);
        FCUtilsRayTraceVsComplexBlock var8 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        var8.AddBoxWithLocalCoordsToIntersectionList(this.GetBlockBoundsFromPoolForAlignment(var7));

        for (int var9 = 0; var9 <= 2; ++var9)
        {
            AxisAlignedBB var10 = this.GetBlockBoundsFromPoolForConnectingBlocksAlongAxis(var1, var2, var3, var4, var9);

            if (var10 != null)
            {
                var8.AddBoxWithLocalCoordsToIntersectionList(var10);
            }
        }

        return var8.GetFirstIntersection();
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        int var8 = this.GetMouldingAlignment(var1, var2, var3, var4);
        this.GetBlockBoundsFromPoolForAlignment(var8).offset((double)var2, (double)var3, (double)var4).AddToListIfIntersects(var5, var6);

        for (int var9 = 0; var9 <= 2; ++var9)
        {
            AxisAlignedBB var10 = this.GetBlockBoundsFromPoolForConnectingBlocksAlongAxis(var1, var2, var3, var4, var9);

            if (var10 != null)
            {
                var10.offset((double)var2, (double)var3, (double)var4).AddToListIfIntersects(var5, var6);
            }
        }
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        boolean var10 = false;
        float var11 = Math.abs(var6 - 0.5F);
        float var12 = Math.abs(var7 - 0.5F);
        float var13 = Math.abs(var8 - 0.5F);
        byte var14;

        switch (var5)
        {
            case 0:
                if (var11 > var13)
                {
                    if (var6 > 0.5F)
                    {
                        var14 = 9;
                    }
                    else
                    {
                        var14 = 11;
                    }
                }
                else if (var8 > 0.5F)
                {
                    var14 = 10;
                }
                else
                {
                    var14 = 8;
                }

                break;

            case 1:
                if (var11 > var13)
                {
                    if (var6 > 0.5F)
                    {
                        var14 = 1;
                    }
                    else
                    {
                        var14 = 3;
                    }
                }
                else if (var8 > 0.5F)
                {
                    var14 = 2;
                }
                else
                {
                    var14 = 0;
                }

                break;

            case 2:
                if (var11 > var12)
                {
                    if (var6 > 0.5F)
                    {
                        var14 = 6;
                    }
                    else
                    {
                        var14 = 7;
                    }
                }
                else if (var7 > 0.5F)
                {
                    var14 = 10;
                }
                else
                {
                    var14 = 2;
                }

                break;

            case 3:
                if (var11 > var12)
                {
                    if (var6 > 0.5F)
                    {
                        var14 = 5;
                    }
                    else
                    {
                        var14 = 4;
                    }
                }
                else if (var7 > 0.5F)
                {
                    var14 = 8;
                }
                else
                {
                    var14 = 0;
                }

                break;

            case 4:
                if (var13 > var12)
                {
                    if (var8 > 0.5F)
                    {
                        var14 = 6;
                    }
                    else
                    {
                        var14 = 5;
                    }
                }
                else if (var7 > 0.5F)
                {
                    var14 = 9;
                }
                else
                {
                    var14 = 1;
                }

                break;

            default:
                if (var13 > var12)
                {
                    if (var8 > 0.5F)
                    {
                        var14 = 7;
                    }
                    else
                    {
                        var14 = 4;
                    }
                }
                else if (var7 > 0.5F)
                {
                    var14 = 11;
                }
                else
                {
                    var14 = 3;
                }
        }

        return this.SetMouldingAlignmentInMetadata(var9, var14);
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetMouldingAlignment(var1, var2, var3, var4);
        return var5 < 8;
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        int var3 = this.GetMouldingAlignmentFromMetadata(var1);

        if (var2)
        {
            ++var3;

            if (var3 == 4)
            {
                var3 = 0;
            }
            else if (var3 == 8)
            {
                var3 = 4;
            }
            else if (var3 >= 12)
            {
                var3 = 8;
            }
        }
        else
        {
            --var3;

            if (var3 < 0)
            {
                var3 = 3;
            }
            else if (var3 == 3)
            {
                var3 = 7;
            }
            else if (var3 == 7)
            {
                var3 = 11;
            }
        }

        var1 = this.SetMouldingAlignmentInMetadata(var1, var3);
        return var1;
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.GetMouldingAlignment(var1, var2, var3, var4);

        if (!var5)
        {
            ++var6;

            if (var6 > 11)
            {
                var6 = 0;
            }
        }
        else
        {
            --var6;

            if (var6 < 0)
            {
                var6 = 11;
            }
        }

        this.SetMouldingAlignment(var1, var2, var3, var4, var6);
        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        return true;
    }

    public float MobSpawnOnVerticalOffset(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetMouldingAlignment(var1, var2, var3, var4);
        return var5 < 4 ? -0.5F : 0.0F;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetBlockBoundsFromPoolForAlignment(this.GetMouldingAlignment(var1, var2, var3, var4));
    }

    public AxisAlignedBB GetBlockBoundsFromPoolForAlignment(int var1)
    {
        AxisAlignedBB var2 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

        if (var1 == 0)
        {
            var2.maxY = var2.minY + 0.5D;
            var2.maxZ = var2.minZ + 0.5D;
        }
        else if (var1 == 1)
        {
            var2.minX += 0.5D;
            var2.maxY = var2.minY + 0.5D;
        }
        else if (var1 == 2)
        {
            var2.maxY = var2.minY + 0.5D;
            var2.minZ += 0.5D;
        }
        else if (var1 == 3)
        {
            var2.maxX = var2.minX + 0.5D;
            var2.maxY = var2.minY + 0.5D;
        }
        else if (var1 == 4)
        {
            var2.maxX = var2.minX + 0.5D;
            var2.maxZ = var2.minZ + 0.5D;
        }
        else if (var1 == 5)
        {
            var2.minX += 0.5D;
            var2.maxZ = var2.minZ + 0.5D;
        }
        else if (var1 == 6)
        {
            var2.minX += 0.5D;
            var2.minZ += 0.5D;
        }
        else if (var1 == 7)
        {
            var2.maxX = var2.minX + 0.5D;
            var2.minZ += 0.5D;
        }
        else if (var1 == 8)
        {
            var2.minY += 0.5D;
            var2.maxZ = var2.minZ + 0.5D;
        }
        else if (var1 == 9)
        {
            var2.minX += 0.5D;
            var2.minY += 0.5D;
        }
        else if (var1 == 10)
        {
            var2.minY += 0.5D;
            var2.minZ += 0.5D;
        }
        else
        {
            var2.maxX = var2.minX + 0.5D;
            var2.minY += 0.5D;
        }

        return var2;
    }

    protected boolean IsMouldingOfSameType(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2, var3, var4) == this.blockID;
    }

    public int GetMouldingAlignment(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetMouldingAlignmentFromMetadata(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetMouldingAlignmentFromMetadata(int var1)
    {
        return var1;
    }

    public void SetMouldingAlignment(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        var6 = this.SetMouldingAlignmentInMetadata(var6, var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetMouldingAlignmentInMetadata(int var1, int var2)
    {
        return var2;
    }

    private float ClickOffsetFromCenter(float var1)
    {
        return Math.abs(var1 - 0.5F);
    }

    private static void OffsetCornerBoundingBoxAlongAxis(int var0, int var1, Vec3 var2, Vec3 var3)
    {
        if (var1 > 0)
        {
            if (var0 == 0)
            {
                var2.xCoord += 0.5D;
                var3.xCoord += 0.5D;
            }
            else if (var0 == 1)
            {
                var2.yCoord += 0.5D;
                var3.yCoord += 0.5D;
            }
            else
            {
                var2.zCoord += 0.5D;
                var3.zCoord += 0.5D;
            }
        }
    }

    private boolean IsAlignedAlongAxis(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetMouldingAlignment(var1, var2, var3, var4);
        return m_iFacingOfConnections[var5][var6] < 0;
    }

    private int GetAlignmentOfConnectingMouldingAtLocation(IBlockAccess var1, int var2, int var3, int var4, int var5, int var6)
    {
        var1.getBlockId(var2, var3, var4);

        if (this.IsMouldingOfSameType(var1, var2, var3, var4) && this.IsAlignedAlongAxis(var1, var2, var3, var4, var6))
        {
            int var8 = this.GetMouldingAlignment(var1, var2, var3, var4);

            for (int var9 = 0; var9 <= 2; ++var9)
            {
                if (var9 != var6 && m_iFacingOfConnections[var9][var8] == m_iFacingOfConnections[var9][var5])
                {
                    return var8;
                }
            }
        }

        return -1;
    }

    private int GetConnectingCornerFacingAtLocation(IBlockAccess var1, int var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var1.getBlockId(var2, var3, var4);

        if (var7 == this.m_iMatchingCornerBlockID)
        {
            FCBlockSidingAndCorner var8 = (FCBlockSidingAndCorner)Block.blocksList[var7];
            int var9 = var1.getBlockMetadata(var2, var3, var4);

            if (var8.GetIsCorner(var9))
            {
                int var10 = var8.GetFacing(var9);

                if (m_iAlignmentOffsetAlongAxis[var6][var5] == FCBlockSidingAndCorner.GetCornerAlignmentOffsetAlongAxis(var10, var6))
                {
                    int var11 = -1;

                    for (int var12 = 0; var12 <= 2; ++var12)
                    {
                        if (var12 != var6)
                        {
                            if (m_iAlignmentOffsetAlongAxis[var12][var5] == 0)
                            {
                                var11 = var10;
                            }
                            else if (m_iAlignmentOffsetAlongAxis[var12][var5] != FCBlockSidingAndCorner.GetCornerAlignmentOffsetAlongAxis(var10, var12))
                            {
                                return -1;
                            }
                        }
                    }

                    return var11;
                }
            }
        }

        return -1;
    }

    private AxisAlignedBB GetBlockBoundsFromPoolForConnectingBlocksAlongAxis(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetMouldingAlignment(var1, var2, var3, var4);
        int var7 = m_iFacingOfConnections[var5][var6];

        if (var7 >= 0)
        {
            FCUtilsBlockPos var8 = new FCUtilsBlockPos(var2, var3, var4, var7);
            int var9 = this.GetAlignmentOfConnectingMouldingAtLocation(var1, var8.i, var8.j, var8.k, var6, var5);

            if (var9 >= 0)
            {
                return this.GetBlockBoundsFromPoolForConnectingMoulding(var7, var9);
            }

            int var10 = this.GetConnectingCornerFacingAtLocation(var1, var8.i, var8.j, var8.k, var6, var5);

            if (var10 >= 0)
            {
                return this.GetBlockBoundsFromPoolForConnectingCorner(var5, m_iAlignmentOffsetAlongAxis[var5][var6], var10);
            }
        }

        return null;
    }

    private AxisAlignedBB GetBlockBoundsFromPoolForConnectingMoulding(int var1, int var2)
    {
        AxisAlignedBB var3 = this.GetBlockBoundsFromPoolForAlignment(var2);

        if (var1 == 0)
        {
            var3.maxY = 0.5D;
        }
        else if (var1 == 1)
        {
            var3.minY = 0.5D;
        }
        else if (var1 == 2)
        {
            var3.maxZ = 0.5D;
        }
        else if (var1 == 3)
        {
            var3.minZ = 0.5D;
        }
        else if (var1 == 4)
        {
            var3.maxX = 0.5D;
        }
        else
        {
            var3.minX = 0.5D;
        }

        return var3;
    }

    private AxisAlignedBB GetBlockBoundsFromPoolForConnectingCorner(int var1, int var2, int var3)
    {
        Vec3 var4 = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
        Vec3 var5 = Vec3.createVectorHelper(0.5D, 0.5D, 0.5D);
        this.OffsetBoundingBoxForConnectingCorner(var1, var2, var3, var4, var5);
        return AxisAlignedBB.getAABBPool().getAABB(var4.xCoord, var4.yCoord, var4.zCoord, var5.xCoord, var5.yCoord, var5.zCoord);
    }

    private void OffsetBoundingBoxForConnectingCorner(int var1, int var2, int var3, Vec3 var4, Vec3 var5)
    {
        for (int var6 = 0; var6 <= 2; ++var6)
        {
            if (var6 == var1)
            {
                OffsetCornerBoundingBoxAlongAxis(var6, -var2, var4, var5);
            }
            else
            {
                OffsetCornerBoundingBoxAlongAxis(var6, FCBlockSidingAndCorner.GetCornerAlignmentOffsetAlongAxis(var3, var6), var4, var5);
            }
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon(this.m_sTextureName);
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
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetMouldingAlignment(var1, var2, var3, var4);
        AxisAlignedBB var6 = this.GetBlockBoundsFromPoolForAlignment(var5);

        for (int var7 = 0; var7 <= 2; ++var7)
        {
            AxisAlignedBB var8 = this.GetBlockBoundsFromPoolForConnectingBlocksAlongAxis(var1, var2, var3, var4, var7);

            if (var8 != null)
            {
                var6.ExpandToInclude(var8);
            }
        }

        return var6.offset((double)var2, (double)var3, (double)var4);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = this.GetMouldingAlignment(var5, var2, var3, var4);
        var1.setRenderBounds(this.GetBlockBoundsFromPoolForAlignment(var6));
        var1.renderStandardBlock(this, var2, var3, var4);

        for (int var7 = 0; var7 <= 2; ++var7)
        {
            AxisAlignedBB var8 = this.GetBlockBoundsFromPoolForConnectingBlocksAlongAxis(var5, var2, var3, var4, var7);

            if (var8 != null)
            {
                var1.setRenderBounds(var8);
                var1.renderStandardBlock(this, var2, var3, var4);
            }
        }

        return true;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        return this.idDropped(var1.getBlockMetadata(var2, var3, var4), var1.rand, 0);
    }
}
