package net.minecraft.src;

import org.lwjgl.opengl.GL11;

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

    private void AddVertices(int var1, int var2, int var3, Icon var4)
    {
        double var5 = (double)(var4.getMaxU() - var4.getMinU());
        double var7 = (double)(var4.getMaxV() - var4.getMinV());
        double var9 = (double)var4.getMinU() + var5 * (double)this.m_fMinUFrac;
        double var11 = (double)var4.getMinV() + var7 * (double)this.m_fMinVFrac;
        double var13 = (double)var4.getMinU() + var5 * (double)this.m_fMaxUFrac;
        double var15 = (double)var4.getMinV() + var7 * (double)this.m_fMaxVFrac;
        Tessellator.instance.addVertexWithUV((double)var1 + this.m_vertices[0].xCoord, (double)var2 + this.m_vertices[0].yCoord, (double)var3 + this.m_vertices[0].zCoord, var9, var11);
        Tessellator.instance.addVertexWithUV((double)var1 + this.m_vertices[1].xCoord, (double)var2 + this.m_vertices[1].yCoord, (double)var3 + this.m_vertices[1].zCoord, var9, var15);
        Tessellator.instance.addVertexWithUV((double)var1 + this.m_vertices[2].xCoord, (double)var2 + this.m_vertices[2].yCoord, (double)var3 + this.m_vertices[2].zCoord, var13, var15);
        Tessellator.instance.addVertexWithUV((double)var1 + this.m_vertices[3].xCoord, (double)var2 + this.m_vertices[3].yCoord, (double)var3 + this.m_vertices[3].zCoord, var13, var11);
    }

    public boolean RenderAsBlock(RenderBlocks var1, Block var2, int var3, int var4, int var5)
    {
        Icon var6 = var2.GetIconByIndex(this.m_iIconIndex);
        Tessellator.instance.setBrightness(var2.getMixedBrightnessForBlock(var1.blockAccess, var3, var4, var5));
        Tessellator.instance.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        this.AddVertices(var3, var4, var5, var6);
        return true;
    }

    public boolean RenderAsBlockWithColorMultiplier(RenderBlocks var1, Block var2, int var3, int var4, int var5, float var6, float var7, float var8)
    {
        Icon var9 = var2.GetIconByIndex(this.m_iIconIndex);
        Tessellator.instance.setBrightness(var2.getMixedBrightnessForBlock(var1.blockAccess, var3, var4, var5));
        Tessellator.instance.setColorOpaque_F(var6, var7, var8);
        this.AddVertices(var3, var4, var5, var9);
        return true;
    }

    public boolean RenderAsBlockWithTexture(RenderBlocks var1, Block var2, int var3, int var4, int var5, Icon var6)
    {
        Tessellator.instance.setBrightness(var2.getMixedBrightnessForBlock(var1.blockAccess, var3, var4, var5));
        Tessellator.instance.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        this.AddVertices(var3, var4, var5, var6);
        return true;
    }

    public boolean RenderAsBlockFullBrightWithTexture(RenderBlocks var1, Block var2, int var3, int var4, int var5, Icon var6)
    {
        Tessellator.instance.setBrightness(var1.blockAccess.getLightBrightnessForSkyBlocks(var3, var4, var5, 15));
        Tessellator.instance.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        this.AddVertices(var3, var4, var5, var6);
        return true;
    }

    public void RenderAsItemBlock(RenderBlocks var1, Block var2, int var3)
    {
        Tessellator var4 = Tessellator.instance;
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        var4.startDrawingQuads();
        Vec3 var5 = this.ComputeNormal().normalize();
        var4.setNormal((float)var5.xCoord, (float)var5.yCoord, (float)var5.zCoord);
        Icon var6 = var2.GetIconByIndex(this.m_iIconIndex);
        double var7 = (double)(var6.getMaxU() - var6.getMinU());
        double var9 = (double)(var6.getMaxV() - var6.getMinV());
        double var11 = (double)var6.getMinU() + var7 * (double)this.m_fMinUFrac;
        double var13 = (double)var6.getMinV() + var9 * (double)this.m_fMinVFrac;
        double var15 = (double)var6.getMinU() + var7 * (double)this.m_fMaxUFrac;
        double var17 = (double)var6.getMinV() + var9 * (double)this.m_fMaxVFrac;
        var4.addVertexWithUV(this.m_vertices[0].xCoord, this.m_vertices[0].yCoord, this.m_vertices[0].zCoord, var11, var13);
        var4.addVertexWithUV(this.m_vertices[1].xCoord, this.m_vertices[1].yCoord, this.m_vertices[1].zCoord, var11, var17);
        var4.addVertexWithUV(this.m_vertices[2].xCoord, this.m_vertices[2].yCoord, this.m_vertices[2].zCoord, var15, var17);
        var4.addVertexWithUV(this.m_vertices[3].xCoord, this.m_vertices[3].yCoord, this.m_vertices[3].zCoord, var15, var13);
        var4.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    public void RenderAsFallingBlock(RenderBlocks var1, Block var2, int var3, int var4, int var5, int var6)
    {
        this.RenderAsBlock(var1, var2, var3, var4, var5);
    }
}
