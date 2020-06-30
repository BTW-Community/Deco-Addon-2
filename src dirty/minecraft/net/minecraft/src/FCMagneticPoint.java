package net.minecraft.src;

public class FCMagneticPoint
{
    private static double[] m_dFieldStrengthMultipliersByLevel = new double[] {0.0D, 1.0D, 8.0D, 27.0D, 64.0D, 125.0D, 216.0D, 343.0D, 4096.0D};
    private static double[] m_dMaxRangeSquaredForLevelWithNoise = new double[] {0.0D, 100.0D, 400.0D, 1600.0D, 6400.0D, 25600.0D, 102400.0D, 409600.0D, Double.POSITIVE_INFINITY};
    public int m_iIPos;
    public int m_iJPos;
    public int m_iKPos;
    public int m_iFieldLevel;

    public FCMagneticPoint()
    {
        this.m_iIPos = 0;
        this.m_iJPos = 0;
        this.m_iKPos = 0;
        this.m_iFieldLevel = 0;
    }

    public FCMagneticPoint(int var1, int var2, int var3, int var4)
    {
        this.m_iIPos = var1;
        this.m_iJPos = var2;
        this.m_iKPos = var3;
        this.m_iFieldLevel = var4;
    }

    public FCMagneticPoint(NBTTagCompound var1)
    {
        this.LoadFromNBT(var1);
    }

    public void LoadFromNBT(NBTTagCompound var1)
    {
        this.m_iIPos = var1.getInteger("IPos");
        this.m_iJPos = var1.getShort("JPos");
        this.m_iKPos = var1.getInteger("KPos");
        this.m_iFieldLevel = var1.getByte("Lvl");
    }

    public NBTTagCompound WriteToNBT(NBTTagCompound var1)
    {
        var1.setInteger("IPos", this.m_iIPos);
        var1.setShort("JPos", (short)this.m_iJPos);
        var1.setInteger("KPos", this.m_iKPos);
        var1.setByte("Lvl", (byte)this.m_iFieldLevel);
        return var1;
    }

    public double GetFieldStrengthRelativeToPosition(double var1, double var3)
    {
        double var5 = (double)this.m_iIPos - var1;
        double var7 = (double)this.m_iKPos - var3;
        double var9 = var5 * var5 + var7 * var7;
        return m_dFieldStrengthMultipliersByLevel[this.m_iFieldLevel] / var9;
    }

    public double GetFieldStrengthRelativeToPositionWithBackgroundNoise(double var1, double var3)
    {
        double var5 = (double)this.m_iIPos - var1;
        double var7 = (double)this.m_iKPos - var3;
        double var9 = var5 * var5 + var7 * var7;
        return var9 <= m_dMaxRangeSquaredForLevelWithNoise[this.m_iFieldLevel] ? m_dFieldStrengthMultipliersByLevel[this.m_iFieldLevel] / var9 : -1.0D;
    }
}
