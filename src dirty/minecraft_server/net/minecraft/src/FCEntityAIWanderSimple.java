package net.minecraft.src;

public class FCEntityAIWanderSimple extends EntityAIBase
{
    private EntityCreature m_myEntity;
    private float m_fMoveSpeed;
    protected FCUtilsBlockPos m_destPos = new FCUtilsBlockPos();

    public FCEntityAIWanderSimple(EntityCreature var1, float var2)
    {
        this.m_myEntity = var1;
        this.m_fMoveSpeed = var2;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return this.m_myEntity.getRNG().nextInt(120) == 0 && FCUtilsRandomPositionGenerator.FindSimpleRandomTargetBlock(this.m_myEntity, 10, 7, this.m_destPos);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.m_myEntity.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.m_myEntity.getNavigator().tryMoveToXYZ(this.m_destPos.i, this.m_destPos.j, this.m_destPos.k, this.m_fMoveSpeed);
    }
}
