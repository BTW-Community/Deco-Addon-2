package net.minecraft.src;

public class FCModelBlockBucket extends FCModelBlock
{
    public static final int m_iAssemblyIDBase = 0;
    public static final int m_iAssemblyIDBody = 1;
    public static final int m_iAssemblyIDRim = 2;
    public static final int m_iAssemblyIDInterior = 3;
    public static final int m_iAssemblyIDContents = 4;
    public static final double m_dHeight = 0.5D;
    public static final double m_dWidth = 0.5D;
    public static final double m_dHalfWidth = 0.25D;
    public static final double m_dBaseHeight = 0.0625D;
    public static final double m_dBaseWidth = 0.375D;
    public static final double m_dBaseHalfWidth = 0.1875D;
    public static final double m_dBodyWidth = 0.4375D;
    public static final double m_dBodyHalfWidth = 0.21875D;
    public static final double m_dRimHeight = 0.0625D;
    public static final double m_dInteriorHeight = 0.34375D;
    public static final double m_dInteriorWidth = 0.375D;
    public static final double m_dInteriorHalfWidth = 0.1875D;
    private static final double m_dMindTheGap = 0.001D;

    protected void InitModel()
    {
        FCModelBlock var1 = new FCModelBlock(0);
        var1.AddBox(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.0625D, 0.6875D);
        this.AddPrimitive(var1);
        var1 = new FCModelBlock(1);
        var1.AddBox(0.28125D, 0.0625D, 0.28125D, 0.71875D, 0.4375D, 0.71875D);
        this.AddPrimitive(var1);
        FCUtilsPrimitiveAABBWithBenefits var2 = new FCUtilsPrimitiveAABBWithBenefits(0.25D, 0.4375D, 0.25D, 0.75D, 0.5D, 0.75D, 2);
        this.AddPrimitive(var2);
        var2 = new FCUtilsPrimitiveAABBWithBenefits(0.6885D, 0.5D, 0.6885D, 0.3115D, 0.15625D, 0.3115D, 3);
        var2.SetForceRenderWithColorMultiplier(true);
        this.AddPrimitive(var2);
    }

    public static void OffsetModelForFacing(FCModelBlock var0, int var1)
    {
        if (var1 != 1)
        {
            Vec3 var2 = GetOffsetForFacing(var1);
            var0.Translate(var2.xCoord, var2.yCoord, var2.zCoord);
        }
    }

    public static Vec3 GetOffsetForFacing(int var0)
    {
        if (var0 == 0)
        {
            return Vec3.createVectorHelper(0.0D, -0.5D, 0.0D);
        }
        else
        {
            Vec3 var1 = Vec3.createVectorHelper(0.0D, -0.25D, -0.5D);
            var1.RotateAsVectorAroundJToFacing(var0);
            return var1;
        }
    }
}
