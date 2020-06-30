package net.minecraft.src;

public class FCBeaconEffectLocation
{
    public int m_iIPos;
    public int m_iJPos;
    public int m_iKPos;
    public int m_iEffectLevel;
    public int m_iRange;

    public FCBeaconEffectLocation()
    {
        this.m_iIPos = 0;
        this.m_iJPos = 0;
        this.m_iKPos = 0;
        this.m_iEffectLevel = 0;
        this.m_iRange = 0;
    }

    public FCBeaconEffectLocation(int var1, int var2, int var3, int var4, int var5)
    {
        this.m_iIPos = var1;
        this.m_iJPos = var2;
        this.m_iKPos = var3;
        this.m_iEffectLevel = var4;
        this.m_iRange = var5;
    }

    public FCBeaconEffectLocation(NBTTagCompound var1)
    {
        this.LoadFromNBT(var1);
    }

    public void LoadFromNBT(NBTTagCompound var1)
    {
        this.m_iIPos = var1.getInteger("IPos");
        this.m_iJPos = var1.getShort("JPos");
        this.m_iKPos = var1.getInteger("KPos");
        this.m_iEffectLevel = var1.getByte("Lvl");
        this.m_iRange = var1.getInteger("Rng");
    }

    public NBTTagCompound WriteToNBT(NBTTagCompound var1)
    {
        var1.setInteger("IPos", this.m_iIPos);
        var1.setShort("JPos", (short)this.m_iJPos);
        var1.setInteger("KPos", this.m_iKPos);
        var1.setByte("Lvl", (byte)this.m_iEffectLevel);
        var1.setInteger("Rng", this.m_iRange);
        return var1;
    }
}
