package net.minecraft.src;

public class FCClosestEntitySelectionCriteriaSquidSecondaryTarget extends FCClosestEntitySelectionCriteria
{
    public void ProcessEntity(FCClosestEntityInfo var1, Entity var2)
    {
        if (var2.IsSecondaryTargetForSquid() && var2.isEntityAlive())
        {
            double var3 = var2.posX - var1.m_dSourcePosX;
            double var5 = var2.posY - var1.m_dSourcePosY;
            double var7 = var2.posZ - var1.m_dSourcePosZ;
            double var9 = var3 * var3 + var5 * var5 + var7 * var7;

            if (var9 < var1.m_dClosestDistanceSq && (!var2.worldObj.isDaytime() || var2.getBrightness(1.0F) < 0.1F) && (var2.inWater || this.CanEntityBeSeenBySource(var1, var2)))
            {
                var1.m_closestEntity = var2;
                var1.m_dClosestDistanceSq = var9;
            }
        }
    }

    private boolean CanEntityBeSeenBySource(FCClosestEntityInfo var1, Entity var2)
    {
        return var2.worldObj.rayTraceBlocks_do_do(var2.worldObj.getWorldVec3Pool().getVecFromPool(var1.m_dSourcePosX, var1.m_dSourcePosY, var1.m_dSourcePosZ), var2.worldObj.getWorldVec3Pool().getVecFromPool(var2.posX, var2.posY + (double)(var2.height / 2.0F), var2.posZ), false, true) == null;
    }
}
