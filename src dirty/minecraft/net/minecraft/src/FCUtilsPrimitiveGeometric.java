package net.minecraft.src;

import java.util.List;

public abstract class FCUtilsPrimitiveGeometric
{
    public abstract FCUtilsPrimitiveGeometric MakeTemporaryCopy();

    public abstract void RotateAroundJToFacing(int var1);

    public abstract void TiltToFacingAlongJ(int var1);

    public abstract void AddToRayTrace(FCUtilsRayTraceVsComplexBlock var1);

    public abstract void Translate(double var1, double var3, double var5);

    public void AddIntersectingBoxesToCollisionList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6) {}

    public int GetAssemblyID()
    {
        return -1;
    }

    public abstract boolean RenderAsBlock(RenderBlocks var1, Block var2, int var3, int var4, int var5);

    public abstract boolean RenderAsBlockWithColorMultiplier(RenderBlocks var1, Block var2, int var3, int var4, int var5, float var6, float var7, float var8);

    public boolean RenderAsBlockWithColorMultiplier(RenderBlocks var1, Block var2, int var3, int var4, int var5)
    {
        int var6 = var2.colorMultiplier(var1.blockAccess, var3, var4, var5);
        float var7 = (float)(var6 >> 16 & 255) / 255.0F;
        float var8 = (float)(var6 >> 8 & 255) / 255.0F;
        float var9 = (float)(var6 & 255) / 255.0F;
        return this.RenderAsBlockWithColorMultiplier(var1, var2, var3, var4, var5, var7, var8, var9);
    }

    public abstract boolean RenderAsBlockWithTexture(RenderBlocks var1, Block var2, int var3, int var4, int var5, Icon var6);

    public abstract boolean RenderAsBlockFullBrightWithTexture(RenderBlocks var1, Block var2, int var3, int var4, int var5, Icon var6);

    public abstract void RenderAsItemBlock(RenderBlocks var1, Block var2, int var3);

    public abstract void RenderAsFallingBlock(RenderBlocks var1, Block var2, int var3, int var4, int var5, int var6);
}
