package net.minecraft.src;

public class FCClosestEntitySelectionCriteria
{
    public static FCClosestEntitySelectionCriteria m_secondarySquidTarget = new FCClosestEntitySelectionCriteriaSquidSecondaryTarget();

    public void ProcessEntity(FCClosestEntityInfo var1, Entity var2)
    {
        double var3 = var2.posX - var1.m_dSourcePosX;
        double var5 = var2.posY - var1.m_dSourcePosY;
        double var7 = var2.posZ - var1.m_dSourcePosZ;
        double var9 = var3 * var3 + var5 * var5 + var7 * var7;

        if (var9 < var1.m_dClosestDistanceSq)
        {
            var1.m_closestEntity = var2;
            var1.m_dClosestDistanceSq = var9;
        }
    }
}
