package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FCMagneticPointList
{
    public List m_MagneticPoints = new ArrayList();

    public void loadFromNBT(NBTTagList var1)
    {
        this.m_MagneticPoints.clear();

        for (int var2 = 0; var2 < var1.tagCount(); ++var2)
        {
            NBTTagCompound var3 = (NBTTagCompound)var1.tagAt(var2);
            FCMagneticPoint var4 = new FCMagneticPoint(var3);
            this.m_MagneticPoints.add(var4);
        }
    }

    public NBTTagList saveToNBT()
    {
        NBTTagList var1 = new NBTTagList("MagneticPoints");
        Iterator var2 = this.m_MagneticPoints.iterator();

        while (var2.hasNext())
        {
            NBTTagCompound var3 = new NBTTagCompound();
            FCMagneticPoint var4 = (FCMagneticPoint)var2.next();
            var4.WriteToNBT(var3);
            var1.appendTag(var3);
        }

        return var1;
    }

    public void RemovePointAt(int var1, int var2, int var3)
    {
        Iterator var4 = this.m_MagneticPoints.iterator();
        FCMagneticPoint var5;

        do
        {
            if (!var4.hasNext())
            {
                return;
            }

            var5 = (FCMagneticPoint)var4.next();
        }
        while (var5.m_iIPos != var1 || var5.m_iKPos != var3 || var5.m_iJPos != var2);

        var4.remove();
    }

    public void AddPoint(int var1, int var2, int var3, int var4)
    {
        FCMagneticPoint var5 = new FCMagneticPoint(var1, var2, var3, var4);
        this.m_MagneticPoints.add(var5);
    }

    public void ChangePowerLevelOfPointAt(int var1, int var2, int var3, int var4)
    {
        FCMagneticPoint var5 = this.GetMagneticPointAtLocation(var1, var2, var3);

        if (var5 != null)
        {
            var5.m_iFieldLevel = var4;
        }
    }

    public FCMagneticPoint GetMagneticPointAtLocation(int var1, int var2, int var3)
    {
        Iterator var4 = this.m_MagneticPoints.iterator();
        FCMagneticPoint var5;

        do
        {
            if (!var4.hasNext())
            {
                return null;
            }

            var5 = (FCMagneticPoint)var4.next();
        }
        while (var5.m_iIPos != var1 || var5.m_iKPos != var3 || var5.m_iJPos != var2);

        return var5;
    }
}
