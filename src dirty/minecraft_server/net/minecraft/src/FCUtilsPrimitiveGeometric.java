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
}
