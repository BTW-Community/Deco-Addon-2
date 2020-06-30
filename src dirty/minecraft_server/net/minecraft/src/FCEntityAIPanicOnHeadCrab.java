package net.minecraft.src;

public class FCEntityAIPanicOnHeadCrab extends EntityAIBase
{
    private EntityCreature m_owningEntity;
    private float m_fMoveSpeed;
    private double m_fRandPosX;
    private double m_fRandPosY;
    private double m_fRandPosZ;

    public FCEntityAIPanicOnHeadCrab(EntityCreature var1, float var2)
    {
        this.m_owningEntity = var1;
        this.m_fMoveSpeed = var2;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.m_owningEntity.HasHeadCrabbedSquid())
        {
            Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.m_owningEntity, 5, 4);

            if (var1 != null)
            {
                this.m_fRandPosX = var1.xCoord;
                this.m_fRandPosY = var1.yCoord;
                this.m_fRandPosZ = var1.zCoord;
                return true;
            }
        }

        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.m_owningEntity.getNavigator().tryMoveToXYZ(this.m_fRandPosX, this.m_fRandPosY, this.m_fRandPosZ, this.m_fMoveSpeed);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.m_owningEntity.getNavigator().noPath() && this.m_owningEntity.HasHeadCrabbedSquid();
    }
}
