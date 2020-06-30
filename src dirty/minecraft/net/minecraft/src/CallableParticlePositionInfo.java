package net.minecraft.src;

import java.util.concurrent.Callable;

class CallableParticlePositionInfo implements Callable
{
    final double field_85101_a;

    final double field_85099_b;

    final double field_85100_c;

    final RenderGlobal globalRenderer;

    CallableParticlePositionInfo(RenderGlobal par1RenderGlobal, double par2, double par4, double par6)
    {
        this.globalRenderer = par1RenderGlobal;
        this.field_85101_a = par2;
        this.field_85099_b = par4;
        this.field_85100_c = par6;
    }

    public String callParticlePositionInfo()
    {
        return CrashReportCategory.func_85074_a(this.field_85101_a, this.field_85099_b, this.field_85100_c);
    }

    public Object call()
    {
        return this.callParticlePositionInfo();
    }
}
