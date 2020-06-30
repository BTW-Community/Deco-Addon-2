package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FCUtilsRayTraceVsComplexBlock
{
    private World m_World;
    private int m_iBlockI;
    private int m_iBlockJ;
    private int m_iBlockK;
    private Vec3 m_StartRay;
    private Vec3 m_EndRay;
    List m_IntersectionPointList = new ArrayList();

    public FCUtilsRayTraceVsComplexBlock(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        this.m_World = var1;
        this.m_iBlockI = var2;
        this.m_iBlockJ = var3;
        this.m_iBlockK = var4;
        this.m_StartRay = Vec3.createVectorHelper(var5.xCoord, var5.yCoord, var5.zCoord);
        this.m_EndRay = Vec3.createVectorHelper(var6.xCoord, var6.yCoord, var6.zCoord);
    }

    public void AddBoxWithLocalCoordsToIntersectionList(AxisAlignedBB var1)
    {
        this.AddBoxWithLocalCoordsToIntersectionList(var1.minX, var1.minY, var1.minZ, var1.maxX, var1.maxY, var1.maxZ);
    }

    public void AddBoxWithLocalCoordsToIntersectionList(double var1, double var3, double var5, double var7, double var9, double var11)
    {
        Vec3 var13 = this.m_StartRay.addVector((double)(-this.m_iBlockI), (double)(-this.m_iBlockJ), (double)(-this.m_iBlockK));
        Vec3 var14 = this.m_EndRay.addVector((double)(-this.m_iBlockI), (double)(-this.m_iBlockJ), (double)(-this.m_iBlockK));
        Vec3 var15 = Vec3.createVectorHelper(var1, var3, var5);
        Vec3 var16 = Vec3.createVectorHelper(var7, var9, var11);
        Vec3 var17 = var13.getIntermediateWithXValue(var14, var15.xCoord);
        Vec3 var18 = var13.getIntermediateWithXValue(var14, var16.xCoord);
        Vec3 var19 = var13.getIntermediateWithYValue(var14, var15.yCoord);
        Vec3 var20 = var13.getIntermediateWithYValue(var14, var16.yCoord);
        Vec3 var21 = var13.getIntermediateWithZValue(var14, var15.zCoord);
        Vec3 var22 = var13.getIntermediateWithZValue(var14, var16.zCoord);

        if (!this.isVecInsideYZBounds(var17, var15, var16))
        {
            var17 = null;
        }

        if (!this.isVecInsideYZBounds(var18, var15, var16))
        {
            var18 = null;
        }

        if (!this.isVecInsideXZBounds(var19, var15, var16))
        {
            var19 = null;
        }

        if (!this.isVecInsideXZBounds(var20, var15, var16))
        {
            var20 = null;
        }

        if (!this.isVecInsideXYBounds(var21, var15, var16))
        {
            var21 = null;
        }

        if (!this.isVecInsideXYBounds(var22, var15, var16))
        {
            var22 = null;
        }

        Vec3 var23 = null;

        if (var17 != null && (var23 == null || var13.squareDistanceTo(var17) < var13.squareDistanceTo(var23)))
        {
            var23 = var17;
        }

        if (var18 != null && (var23 == null || var13.squareDistanceTo(var18) < var13.squareDistanceTo(var23)))
        {
            var23 = var18;
        }

        if (var19 != null && (var23 == null || var13.squareDistanceTo(var19) < var13.squareDistanceTo(var23)))
        {
            var23 = var19;
        }

        if (var20 != null && (var23 == null || var13.squareDistanceTo(var20) < var13.squareDistanceTo(var23)))
        {
            var23 = var20;
        }

        if (var21 != null && (var23 == null || var13.squareDistanceTo(var21) < var13.squareDistanceTo(var23)))
        {
            var23 = var21;
        }

        if (var22 != null && (var23 == null || var13.squareDistanceTo(var22) < var13.squareDistanceTo(var23)))
        {
            var23 = var22;
        }

        if (var23 != null)
        {
            byte var24 = -1;

            if (var23 == var17)
            {
                var24 = 4;
            }

            if (var23 == var18)
            {
                var24 = 5;
            }

            if (var23 == var19)
            {
                var24 = 0;
            }

            if (var23 == var20)
            {
                var24 = 1;
            }

            if (var23 == var21)
            {
                var24 = 2;
            }

            if (var23 == var22)
            {
                var24 = 3;
            }

            this.m_IntersectionPointList.add(new MovingObjectPosition(this.m_iBlockI, this.m_iBlockJ, this.m_iBlockK, var24, var23.addVector((double)this.m_iBlockI, (double)this.m_iBlockJ, (double)this.m_iBlockK)));
        }
    }

    public void AddQuadWithLocalCoordsToIntersectionList(FCUtilsPrimitiveQuad var1, Vec3 var2)
    {
        Vec3 var3 = this.m_EndRay.SubtractFrom(this.m_StartRay);
        Vec3 var4 = this.m_StartRay.addVector((double)(-this.m_iBlockI), (double)(-this.m_iBlockJ), (double)(-this.m_iBlockK));
        Vec3 var5 = var1.ComputeNormal();
        double var6 = var5.dotProduct(var3);

        if (var6 != 0.0D)
        {
            Vec3 var8 = var4.SubtractFrom(var2);
            double var9 = var5.dotProduct(var8);

            if (var9 != 0.0D)
            {
                double var11 = var9 / var6;
                Vec3 var13 = Vec3.createVectorHelper(var11 * var3.xCoord, var11 * var3.yCoord, var11 * var3.zCoord).AddVector(var4);

                if (var1.IsPointOnPlaneWithinBounds(var13))
                {
                    int var14 = this.ConvertVectorToApproximateBlockFacing(var5);

                    if (var6 < 0.0D)
                    {
                        var14 = Block.GetOppositeFacing(var14);
                    }

                    this.m_IntersectionPointList.add(new MovingObjectPosition(this.m_iBlockI, this.m_iBlockJ, this.m_iBlockK, var14, var13.addVector((double)this.m_iBlockI, (double)this.m_iBlockJ, (double)this.m_iBlockK)));
                }
            }
        }
    }

    public int ConvertVectorToApproximateBlockFacing(Vec3 var1)
    {
        double var2 = FCUtilsMath.AbsDouble(var1.xCoord);
        double var4 = FCUtilsMath.AbsDouble(var1.yCoord);
        double var6 = FCUtilsMath.AbsDouble(var1.zCoord);
        return var2 >= var6 && var2 >= var4 ? (var1.xCoord < 0.0D ? 4 : 5) : (var6 >= var4 ? (var1.zCoord < 0.0D ? 2 : 3) : (var1.yCoord < 0.0D ? 0 : 1));
    }

    public MovingObjectPosition GetFirstIntersection()
    {
        MovingObjectPosition var1 = null;
        Iterator var2 = this.m_IntersectionPointList.iterator();
        double var3 = 0.0D;

        while (var2.hasNext())
        {
            MovingObjectPosition var5 = (MovingObjectPosition)var2.next();
            double var6 = var5.hitVec.squareDistanceTo(this.m_EndRay);

            if (var6 > var3)
            {
                var1 = var5;
                var3 = var6;
            }
        }

        return var1;
    }

    private boolean isVecInsideYZBounds(Vec3 var1, Vec3 var2, Vec3 var3)
    {
        return var1 == null ? false : var1.yCoord >= var2.yCoord && var1.yCoord <= var3.yCoord && var1.zCoord >= var2.zCoord && var1.zCoord <= var3.zCoord;
    }

    private boolean isVecInsideXZBounds(Vec3 var1, Vec3 var2, Vec3 var3)
    {
        return var1 == null ? false : var1.xCoord >= var2.xCoord && var1.xCoord <= var3.xCoord && var1.zCoord >= var2.zCoord && var1.zCoord <= var3.zCoord;
    }

    private boolean isVecInsideXYBounds(Vec3 var1, Vec3 var2, Vec3 var3)
    {
        return var1 == null ? false : var1.xCoord >= var2.xCoord && var1.xCoord <= var3.xCoord && var1.yCoord >= var2.yCoord && var1.yCoord <= var3.yCoord;
    }
}
