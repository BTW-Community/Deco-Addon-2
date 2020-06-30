package net.minecraft.src;

public class FCUtilsPrimitiveQuad extends FCUtilsPrimitiveGeometric
{
    private Vec3[] m_vertices = new Vec3[4];
    private float m_fMinUFrac = 0.0F;
    private float m_fMinVFrac = 0.0F;
    private float m_fMaxUFrac = 1.0F;
    private float m_fMaxVFrac = 1.0F;
    private int m_iIconIndex = 0;
    private static final double m_dMindTheGap = 1.0E-4D;

    public FCUtilsPrimitiveQuad(Vec3 var1, Vec3 var2, Vec3 var3, Vec3 var4)
    {
        this.m_vertices[0] = var1;
        this.m_vertices[1] = var2;
        this.m_vertices[2] = var3;
        this.m_vertices[3] = var4;
    }

    public void RotateAroundJToFacing(int var1)
    {
        this.m_vertices[0].RotateAsBlockPosAroundJToFacing(var1);
        this.m_vertices[1].RotateAsBlockPosAroundJToFacing(var1);
        this.m_vertices[2].RotateAsBlockPosAroundJToFacing(var1);
        this.m_vertices[3].RotateAsBlockPosAroundJToFacing(var1);
    }

    public void TiltToFacingAlongJ(int var1)
    {
        this.m_vertices[0].TiltAsBlockPosToFacingAlongJ(var1);
        this.m_vertices[1].TiltAsBlockPosToFacingAlongJ(var1);
        this.m_vertices[2].TiltAsBlockPosToFacingAlongJ(var1);
        this.m_vertices[3].TiltAsBlockPosToFacingAlongJ(var1);
    }

    public void Translate(double var1, double var3, double var5)
    {
        this.m_vertices[0].addVector(var1, var3, var5);
        this.m_vertices[1].addVector(var1, var3, var5);
        this.m_vertices[2].addVector(var1, var3, var5);
        this.m_vertices[3].addVector(var1, var3, var5);
    }

    public void AddToRayTrace(FCUtilsRayTraceVsComplexBlock var1)
    {
        var1.AddQuadWithLocalCoordsToIntersectionList(this, this.m_vertices[0]);
    }

    public FCUtilsPrimitiveQuad MakeTemporaryCopy()
    {
        FCUtilsPrimitiveQuad var1 = new FCUtilsPrimitiveQuad(Vec3.createVectorHelper(this.m_vertices[0]), Vec3.createVectorHelper(this.m_vertices[1]), Vec3.createVectorHelper(this.m_vertices[2]), Vec3.createVectorHelper(this.m_vertices[3]));
        var1.SetUVFractions(this.m_fMinUFrac, this.m_fMinVFrac, this.m_fMaxUFrac, this.m_fMaxVFrac);
        var1.SetIconIndex(this.m_iIconIndex);
        return var1;
    }

    public boolean IsPointOnPlaneWithinBounds(Vec3 var1)
    {
        Vec3 var2 = Vec3.createVectorHelper(this.m_vertices[0]);
        Vec3 var3 = Vec3.createVectorHelper(this.m_vertices[0]);
        this.ComputeBounds(var2, var3);
        return (var3.xCoord - var2.xCoord < 1.0E-4D || var1.xCoord >= var2.xCoord && var1.xCoord <= var3.xCoord) && (var3.yCoord - var2.yCoord < 1.0E-4D || var1.yCoord >= var2.yCoord && var1.yCoord <= var3.yCoord) && (var3.zCoord - var2.zCoord < 1.0E-4D || var1.zCoord >= var2.zCoord && var1.zCoord <= var3.zCoord);
    }

    public void ComputeBounds(Vec3 var1, Vec3 var2)
    {
        for (int var3 = 1; var3 <= 3; ++var3)
        {
            Vec3 var4 = this.m_vertices[var3];

            if (var4.xCoord < var1.xCoord)
            {
                var1.xCoord = var4.xCoord;
            }
            else if (var4.xCoord > var2.xCoord)
            {
                var2.xCoord = var4.xCoord;
            }

            if (var4.yCoord < var1.yCoord)
            {
                var1.yCoord = var4.yCoord;
            }
            else if (var4.yCoord > var2.yCoord)
            {
                var2.yCoord = var4.yCoord;
            }

            if (var4.zCoord < var1.zCoord)
            {
                var1.zCoord = var4.zCoord;
            }
            else if (var4.zCoord > var2.zCoord)
            {
                var2.zCoord = var4.zCoord;
            }
        }
    }

    public Vec3 ComputeNormal()
    {
        Vec3 var1 = this.m_vertices[0].SubtractFrom(this.m_vertices[1]);
        Vec3 var2 = this.m_vertices[0].SubtractFrom(this.m_vertices[3]);
        return var1.crossProduct(var2);
    }

    public FCUtilsPrimitiveQuad SetUVFractions(float var1, float var2, float var3, float var4)
    {
        this.m_fMinUFrac = var1;
        this.m_fMinVFrac = var2;
        this.m_fMaxVFrac = var3;
        this.m_fMaxUFrac = var4;
        return this;
    }

    public FCUtilsPrimitiveQuad SetIconIndex(int var1)
    {
        this.m_iIconIndex = var1;
        return this;
    }
}
