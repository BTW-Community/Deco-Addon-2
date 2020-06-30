package net.minecraft.src;

public class FCEntityAIAnimalFlee extends EntityAIBase
{
    private EntityAnimal m_theAnimal;
    private float m_fSpeed;
    private double m_dTargetPosX;
    private double m_dTargetPosY;
    private double m_dTargetPosZ;

    public FCEntityAIAnimalFlee(EntityAnimal var1, float var2)
    {
        this.m_theAnimal = var1;
        this.m_fSpeed = var2;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        Vec3 var1 = null;

        if (this.m_theAnimal.isBurning())
        {
            var1 = RandomPositionGenerator.findRandomTarget(this.m_theAnimal, 5, 4);
        }
        else if (this.m_theAnimal.getAITarget() != null)
        {
            var1 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.m_theAnimal, 5, 4, this.m_theAnimal.worldObj.getWorldVec3Pool().getVecFromPool(this.m_theAnimal.getAITarget().posX, this.m_theAnimal.getAITarget().posY, this.m_theAnimal.getAITarget().posZ));
        }

        if (var1 != null)
        {
            this.m_dTargetPosX = var1.xCoord;
            this.m_dTargetPosY = var1.yCoord;
            this.m_dTargetPosZ = var1.zCoord;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.m_theAnimal.getNavigator().tryMoveToXYZ(this.m_dTargetPosX, this.m_dTargetPosY, this.m_dTargetPosZ, this.m_fSpeed);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        if (!this.m_theAnimal.getNavigator().noPath() && this.m_theAnimal.getAITarget() != null)
        {
            EntityLiving var1 = this.m_theAnimal.getAITarget();

            if (var1 == null)
            {
                return true;
            }

            double var2 = this.m_theAnimal.getDistanceSq(this.m_dTargetPosX, this.m_dTargetPosY, this.m_dTargetPosZ);

            if (var2 > 4.0D)
            {
                double var4 = this.m_theAnimal.getDistanceSqToEntity(var1);
                double var6 = var1.getDistanceSq(this.m_dTargetPosX, this.m_dTargetPosY, this.m_dTargetPosZ);

                if (var4 < var6)
                {
                    return true;
                }
            }
        }

        return false;
    }
}
