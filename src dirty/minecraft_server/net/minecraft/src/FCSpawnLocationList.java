package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FCSpawnLocationList
{
    public List m_SpawnLocations = new ArrayList();

    public void loadFromNBT(NBTTagList var1)
    {
        this.m_SpawnLocations.clear();

        for (int var2 = 0; var2 < var1.tagCount(); ++var2)
        {
            NBTTagCompound var3 = (NBTTagCompound)var1.tagAt(var2);
            FCSpawnLocation var4 = new FCSpawnLocation(var3);
            this.m_SpawnLocations.add(var4);
        }
    }

    public NBTTagList saveToNBT()
    {
        NBTTagList var1 = new NBTTagList("SpawnLocations");
        Iterator var2 = this.m_SpawnLocations.iterator();

        while (var2.hasNext())
        {
            NBTTagCompound var3 = new NBTTagCompound();
            FCSpawnLocation var4 = (FCSpawnLocation)var2.next();
            var4.WriteToNBT(var3);
            var1.appendTag(var3);
        }

        return var1;
    }

    public void AddPoint(int var1, int var2, int var3, long var4)
    {
        FCSpawnLocation var6 = new FCSpawnLocation(var1, var2, var3, var4);
        this.m_SpawnLocations.add(var6);
    }

    public FCSpawnLocation GetClosestSpawnLocationForPosition(double var1, double var3)
    {
        FCSpawnLocation var5 = null;
        double var6 = 0.0D;
        Iterator var8 = this.m_SpawnLocations.iterator();

        while (var8.hasNext())
        {
            FCSpawnLocation var9 = (FCSpawnLocation)var8.next();
            double var10 = (double)var9.m_iIPos - var1;
            double var12 = (double)var9.m_iKPos - var3;
            double var14 = var10 * var10 + var12 * var12;

            if (var5 == null || var14 < var6)
            {
                var5 = var9;
                var6 = var14;
            }
        }

        return var5;
    }

    public FCSpawnLocation GetMostRecentSpawnLocation()
    {
        FCSpawnLocation var1 = null;
        Iterator var2 = this.m_SpawnLocations.iterator();

        while (var2.hasNext())
        {
            FCSpawnLocation var3 = (FCSpawnLocation)var2.next();

            if (var1 == null || var3.m_lSpawnTime > var1.m_lSpawnTime)
            {
                var1 = var3;
            }
        }

        return var1;
    }

    public boolean DoesListContainPoint(int var1, int var2, int var3, long var4)
    {
        Iterator var6 = this.m_SpawnLocations.iterator();
        FCSpawnLocation var7;

        do
        {
            if (!var6.hasNext())
            {
                return false;
            }

            var7 = (FCSpawnLocation)var6.next();
        }
        while (var7.m_iIPos != var1 || var7.m_iKPos != var3 || var7.m_iJPos != var2 || var7.m_lSpawnTime != var4);

        return true;
    }

    public void AddPointIfNotAlreadyPresent(int var1, int var2, int var3, long var4)
    {
        if (!this.DoesListContainPoint(var1, var2, var3, var4))
        {
            this.AddPoint(var1, var2, var3, var4);
        }
    }
}
