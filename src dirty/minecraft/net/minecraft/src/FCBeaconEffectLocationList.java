package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FCBeaconEffectLocationList
{
    public List m_EffectLocations = new ArrayList();

    public void loadFromNBT(NBTTagList var1)
    {
        this.m_EffectLocations.clear();

        for (int var2 = 0; var2 < var1.tagCount(); ++var2)
        {
            NBTTagCompound var3 = (NBTTagCompound)var1.tagAt(var2);
            FCBeaconEffectLocation var4 = new FCBeaconEffectLocation(var3);
            this.m_EffectLocations.add(var4);
        }
    }

    public NBTTagList saveToNBT()
    {
        NBTTagList var1 = new NBTTagList("EffectLocations");
        Iterator var2 = this.m_EffectLocations.iterator();

        while (var2.hasNext())
        {
            NBTTagCompound var3 = new NBTTagCompound();
            FCBeaconEffectLocation var4 = (FCBeaconEffectLocation)var2.next();
            var4.WriteToNBT(var3);
            var1.appendTag(var3);
        }

        return var1;
    }

    public void RemovePointAt(int var1, int var2, int var3)
    {
        Iterator var4 = this.m_EffectLocations.iterator();
        FCBeaconEffectLocation var5;

        do
        {
            if (!var4.hasNext())
            {
                return;
            }

            var5 = (FCBeaconEffectLocation)var4.next();
        }
        while (var5.m_iIPos != var1 || var5.m_iKPos != var3 || var5.m_iJPos != var2);

        var4.remove();
    }

    public void AddPoint(int var1, int var2, int var3, int var4, int var5)
    {
        FCBeaconEffectLocation var6 = new FCBeaconEffectLocation(var1, var2, var3, var4, var5);
        this.m_EffectLocations.add(var6);
    }

    public void ChangeEffectLevelOfPointAt(int var1, int var2, int var3, int var4, int var5)
    {
        FCBeaconEffectLocation var6 = this.GetEffectAtLocation(var1, var2, var3);

        if (var6 != null)
        {
            var6.m_iEffectLevel = var4;
            var6.m_iRange = var5;
        }
    }

    public FCBeaconEffectLocation GetEffectAtLocation(int var1, int var2, int var3)
    {
        Iterator var4 = this.m_EffectLocations.iterator();
        FCBeaconEffectLocation var5;

        do
        {
            if (!var4.hasNext())
            {
                return null;
            }

            var5 = (FCBeaconEffectLocation)var4.next();
        }
        while (var5.m_iIPos != var1 || var5.m_iKPos != var3 || var5.m_iJPos != var2);

        return var5;
    }

    public int GetMostPowerfulBeaconEffectForLocation(int var1, int var2)
    {
        byte var3 = 0;
        Iterator var4 = this.m_EffectLocations.iterator();
        FCBeaconEffectLocation var5;

        do
        {
            if (!var4.hasNext())
            {
                return var3;
            }

            var5 = (FCBeaconEffectLocation)var4.next();
        }
        while (var1 < var5.m_iIPos - var5.m_iRange || var1 > var5.m_iIPos + var5.m_iRange || var2 < var5.m_iKPos - var5.m_iRange || var2 > var5.m_iKPos + var5.m_iRange || var5.m_iEffectLevel <= var3);

        int var6 = var5.m_iEffectLevel;
        return var5.m_iEffectLevel;
    }
}
