package net.minecraft.src;

import java.util.List;

public class FCBlockMouldingAndDecorative extends FCBlockMoulding
{
    public static final int m_iSubtypeColumn = 12;
    public static final int m_iSubtypePedestalUp = 13;
    public static final int m_iSubtypePedestalDown = 14;
    public static final int m_iSubtypeTable = 15;
    protected static final double m_dColumWidth = 0.625D;
    protected static final double m_dColumHalfWidth = 0.3125D;
    protected static final double m_dPedestalBaseHeight = 0.75D;
    protected static final double m_dPedestalMiddleHeight = 0.125D;
    protected static final double m_dPedestalMiddleWidth = 0.875D;
    protected static final double m_dPedestalMiddleHalfWidth = 0.4375D;
    protected static final double m_dPedestalTopHeight = 0.125D;
    protected static final double m_dPedestalTopWidth = 0.75D;
    protected static final double m_dPedestalTopHalfWidth = 0.375D;
    protected static final double m_dTableTopHeight = 0.125D;
    protected static final double m_dTableLegHeight = 0.875D;
    protected static final double m_dTableLegWidth = 0.25D;
    protected static final double m_dTableLegHalfWidth = 0.125D;
    String m_sColumnSideTextureName;
    String m_sColumnTopAndBottomTextureName;
    String m_sPedestalSideTextureName;
    String m_sPedestalTopAndBottomTextureName;

    protected FCBlockMouldingAndDecorative(int var1, Material var2, String var3, String var4, int var5, float var6, float var7, StepSound var8, String var9)
    {
        super(var1, var2, var3, var5, var6, var7, var8, var9);
        this.m_sColumnSideTextureName = var4;
        this.m_sColumnTopAndBottomTextureName = var3;
        this.m_sPedestalSideTextureName = var3;
        this.m_sPedestalTopAndBottomTextureName = var3;
    }

    protected FCBlockMouldingAndDecorative(int var1, Material var2, String var3, String var4, String var5, String var6, String var7, int var8, float var9, float var10, StepSound var11, String var12)
    {
        super(var1, var2, var3, var8, var9, var10, var11, var12);
        this.m_sColumnSideTextureName = var4;
        this.m_sColumnTopAndBottomTextureName = var5;
        this.m_sPedestalSideTextureName = var6;
        this.m_sPedestalTopAndBottomTextureName = var7;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        if (!this.IsDecorative(var1))
        {
            return super.damageDropped(var1);
        }
        else
        {
            if (var1 == 14)
            {
                var1 = 13;
            }

            return var1;
        }
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        if (var9 == 13)
        {
            if (var5 == 0 || var5 != 1 && (double)var7 > 0.5D)
            {
                return 14;
            }
        }
        else if (!this.IsDecorative(var9))
        {
            return super.onBlockPlaced(var1, var2, var3, var4, var5, var6, var7, var8, var9);
        }

        return var9;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if (this.IsDecorative(var5))
        {
            switch (var5)
            {
                case 12:
                    return AxisAlignedBB.getAABBPool().getAABB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D);

                case 13:
                case 14:
                    return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

                case 15:
                    if (!this.DoesTableHaveLeg(var1, var2, var3, var4))
                    {
                        return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.875D, 0.0D, 1.0D, 1.0D, 1.0D);
                    }

                    return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

                default:
                    return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
        }
        else
        {
            return super.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4);
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        return this.IsDecorative(var1, var2, var3, var4) ? (this.IsBlockTable(var1, var2, var3, var4) && this.DoesTableHaveLeg(var1, var2, var3, var4) ? this.CollisionRayTraceTableWithLeg(var1, var2, var3, var4, var5, var6) : this.CollisionRayTraceVsBlockBounds(var1, var2, var3, var4, var5, var6)) : super.collisionRayTrace(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        if (this.IsDecorative(var1, var2, var3, var4))
        {
            this.getCollisionBoundingBoxFromPool(var1, var2, var3, var4).AddToListIfIntersects(var5, var6);
        }
        else
        {
            super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        int var7 = var1.getBlockMetadata(var2, var3, var4);

        switch (var7)
        {
            case 12:
                return var5 == 0 || var5 == 1;

            case 13:
            case 14:
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
            case 13:
                return var5 == 0;

            case 14:
                return var5 == 1;

            case 15:
                return var5 == 1;

            default:
                return super.HasLargeCenterHardPointToFacing(var1, var2, var3, var4, var5, var6);
        }
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        return !this.IsDecorative(var1) ? super.RotateMetadataAroundJAxis(var1, var2) : var1;
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        return !this.IsDecorative(var1, var2, var3, var4) ? super.ToggleFacing(var1, var2, var3, var4, var5) : false;
    }

    public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return this.IsDecorative(var5) ? var5 == 13 || var5 == 14 : super.CanTransmitRotationHorizontallyOnTurntable(var1, var2, var3, var4);
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return this.IsDecorative(var5) ? var5 != 15 : super.CanTransmitRotationVerticallyOnTurntable(var1, var2, var3, var4);
    }

    public float MobSpawnOnVerticalOffset(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 < 12 ? super.MobSpawnOnVerticalOffset(var1, var2, var3, var4) : 0.0F;
    }

    protected boolean IsMouldingOfSameType(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2, var3, var4) == this.blockID && !this.IsDecorative(var1, var2, var3, var4);
    }

    public boolean IsDecorative(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.IsDecorative(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean IsDecorative(int var1)
    {
        return var1 >= 12;
    }

    public boolean IsBlockTable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2, var3, var4) == this.blockID && var1.getBlockMetadata(var2, var3, var4) == 15;
    }

    public boolean DoesTableHaveLeg(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 - 1, var4);

        if (this.blockID == FCBetterThanWolves.fcBlockNetherBrickMouldingAndDecorative.blockID)
        {
            if (var5 == Block.netherFence.blockID)
            {
                return true;
            }
        }
        else if (var5 == this.m_iMatchingCornerBlockID)
        {
            int var6 = var1.getBlockMetadata(var2, var3 - 1, var4);

            if (var6 == 14)
            {
                return true;
            }
        }

        boolean var10 = this.IsBlockTable(var1, var2 + 1, var3, var4);
        boolean var7 = this.IsBlockTable(var1, var2 - 1, var3, var4);
        boolean var8 = this.IsBlockTable(var1, var2, var3, var4 + 1);
        boolean var9 = this.IsBlockTable(var1, var2, var3, var4 - 1);
        return !var10 && (!var8 || !var9) || !var7 && (!var8 || !var9);
    }

    public MovingObjectPosition CollisionRayTraceTableWithLeg(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.0D, 0.875D, 0.0D, 1.0D, 1.0D, 1.0D);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.375D, 0.0D, 0.375D, 0.625D, 0.875D, 0.625D);
        return var7.GetFirstIntersection();
    }
}
