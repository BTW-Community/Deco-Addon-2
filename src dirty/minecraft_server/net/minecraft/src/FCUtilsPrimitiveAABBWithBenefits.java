package net.minecraft.src;

public class FCUtilsPrimitiveAABBWithBenefits extends AxisAlignedBB
{
    private int m_iAssemblyID = -1;
    private boolean m_bForceRenderWithColorMultiplier = false;

    protected FCUtilsPrimitiveAABBWithBenefits(double var1, double var3, double var5, double var7, double var9, double var11)
    {
        super(var1, var3, var5, var7, var9, var11);
    }

    protected FCUtilsPrimitiveAABBWithBenefits(double var1, double var3, double var5, double var7, double var9, double var11, int var13)
    {
        super(var1, var3, var5, var7, var9, var11);
        this.m_iAssemblyID = var13;
    }

    public FCUtilsPrimitiveAABBWithBenefits MakeTemporaryCopy()
    {
        FCUtilsPrimitiveAABBWithBenefits var1 = new FCUtilsPrimitiveAABBWithBenefits(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ, this.m_iAssemblyID);
        var1.m_bForceRenderWithColorMultiplier = this.m_bForceRenderWithColorMultiplier;
        return var1;
    }

    public int GetAssemblyID()
    {
        return this.m_iAssemblyID;
    }

    public void SetForceRenderWithColorMultiplier(boolean var1)
    {
        this.m_bForceRenderWithColorMultiplier = var1;
    }
}
