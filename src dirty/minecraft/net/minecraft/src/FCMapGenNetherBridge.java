package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FCMapGenNetherBridge extends MapGenNetherBridge
{
    protected List m_mobSpawnList = new ArrayList();

    public FCMapGenNetherBridge()
    {
        this.m_mobSpawnList.add(new SpawnListEntry(FCEntityBlaze.class, 10, 2, 3));
        this.m_mobSpawnList.add(new SpawnListEntry(FCEntityPigZombie.class, 5, 4, 4));
        this.m_mobSpawnList.add(new SpawnListEntry(FCEntitySkeleton.class, 10, 4, 4));
        this.m_mobSpawnList.add(new SpawnListEntry(FCEntityMagmaCube.class, 3, 4, 4));
    }

    public List getSpawnList()
    {
        return this.m_mobSpawnList;
    }

    public boolean HasStructureAtLoose(int var1, int var2, int var3)
    {
        Iterator var4 = this.structureMap.values().iterator();
        StructureStart var5;
        StructureBoundingBox var6;

        do
        {
            if (!var4.hasNext())
            {
                return false;
            }

            var5 = (StructureStart)var4.next();
            var6 = var5.getBoundingBox();
        }
        while (!var5.isSizeableStructure() || !var6.intersectsWith(var1, var3, var1, var3) || var2 < var6.minY || var2 > var6.maxY);

        return true;
    }

    public StructureStart GetClosestStructureWithinRangeSq(double var1, double var3, double var5)
    {
        StructureStart var7 = null;
        double var8 = var5;
        Iterator var10 = this.structureMap.values().iterator();

        while (var10.hasNext())
        {
            StructureStart var11 = (StructureStart)var10.next();
            StructureBoundingBox var12 = var11.getBoundingBox();
            double var13 = (double)var12.getCenterX();
            double var15 = (double)var12.getCenterZ();
            double var17 = var1 - var13;
            double var19 = var3 - var15;
            double var21 = var17 * var17 + var19 * var19;

            if (var21 < var8)
            {
                var7 = var11;
                var8 = var21;
            }
        }

        return var7;
    }
}
