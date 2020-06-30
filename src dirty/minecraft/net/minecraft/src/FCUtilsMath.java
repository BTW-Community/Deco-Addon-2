package net.minecraft.src;

public class FCUtilsMath
{
    public static double ClampDouble(double var0, double var2, double var4)
    {
        return var0 < var2 ? var2 : (var0 > var4 ? var4 : var0);
    }

    public static double ClampDoubleTop(double var0, double var2)
    {
        return var0 > var2 ? var2 : var0;
    }

    public static double ClampDoubleBottom(double var0, double var2)
    {
        return var0 < var2 ? var2 : var0;
    }

    public static double AbsDouble(double var0)
    {
        return var0 >= 0.0D ? var0 : -var0;
    }
}
