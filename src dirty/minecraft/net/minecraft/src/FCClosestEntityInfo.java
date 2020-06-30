package net.minecraft.src;

public class FCClosestEntityInfo
{
    public double m_dSourcePosX;
    public double m_dSourcePosY;
    public double m_dSourcePosZ;
    public double m_dClosestDistanceSq;
    public Entity m_closestEntity;
    public FCClosestEntitySelectionCriteria m_criteria;
    public int m_iChunkEntityListMinVerticalIndex;
    public int m_iChunkEntityListMaxVerticalIndex;

    public FCClosestEntityInfo(double var1, double var3, double var5, double var7, Entity var9, FCClosestEntitySelectionCriteria var10, int var11, int var12)
    {
        this.m_dSourcePosX = var1;
        this.m_dSourcePosY = var3;
        this.m_dSourcePosZ = var5;
        this.m_dClosestDistanceSq = var7;
        this.m_closestEntity = var9;
        this.m_criteria = var10;
        this.m_iChunkEntityListMinVerticalIndex = var11;
        this.m_iChunkEntityListMaxVerticalIndex = var12;
    }
}
