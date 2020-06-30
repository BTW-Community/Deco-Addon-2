package net.minecraft.src;

public class FCSpawnLocation
{
    public int m_iIPos;
    public int m_iJPos;
    public int m_iKPos;
    public long m_lSpawnTime;

    public FCSpawnLocation()
    {
        this.m_iIPos = 0;
        this.m_iJPos = 0;
        this.m_iKPos = 0;
        this.m_lSpawnTime = 0L;
    }

    public FCSpawnLocation(int var1, int var2, int var3, long var4)
    {
        this.m_iIPos = var1;
        this.m_iJPos = var2;
        this.m_iKPos = var3;
        this.m_lSpawnTime = var4;
    }

    public FCSpawnLocation(NBTTagCompound var1)
    {
        this.LoadFromNBT(var1);
    }

    public void LoadFromNBT(NBTTagCompound var1)
    {
        this.m_iIPos = var1.getInteger("IPos");
        this.m_iJPos = var1.getShort("JPos");
        this.m_iKPos = var1.getInteger("KPos");
        this.m_lSpawnTime = var1.getLong("SpawnTime");
    }

    public NBTTagCompound WriteToNBT(NBTTagCompound var1)
    {
        var1.setInteger("IPos", this.m_iIPos);
        var1.setShort("JPos", (short)this.m_iJPos);
        var1.setInteger("KPos", this.m_iKPos);
        var1.setLong("SpawnTime", this.m_lSpawnTime);
        return var1;
    }
}
