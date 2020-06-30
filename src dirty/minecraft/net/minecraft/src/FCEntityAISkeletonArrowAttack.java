package net.minecraft.src;

public class FCEntityAISkeletonArrowAttack extends EntityAIBase
{
    private final EntityLiving m_EntityOwner;
    private final IRangedAttackMob m_EntityRangedAttackOwner;
    private EntityLiving m_EntityAttackTarget;
    private int m_AttackCooldownCounter;
    private float m_fEntityMoveSpeed;
    private int m_iCanSeeTargetCounter = 0;
    private int m_iMinRangedAttackTime;
    private int m_iAttackInterval;
    private double m_dAttackRange;
    private double m_dAttackRangeSq;

    public FCEntityAISkeletonArrowAttack(IRangedAttackMob var1, float var2, int var3, float var4)
    {
        this.m_EntityRangedAttackOwner = var1;
        this.m_EntityOwner = (EntityLiving)var1;
        this.m_fEntityMoveSpeed = var2;
        this.m_iAttackInterval = var3;
        this.m_AttackCooldownCounter = var3 >> 1;
        this.m_dAttackRange = (double)var4;
        this.m_dAttackRangeSq = (double)(var4 * var4);
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLiving var1 = this.m_EntityOwner.getAttackTarget();

        if (var1 == null)
        {
            return false;
        }
        else
        {
            this.m_EntityAttackTarget = var1;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.shouldExecute() || !this.m_EntityOwner.getNavigator().noPath();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.m_EntityAttackTarget = null;
        this.m_iCanSeeTargetCounter = 0;
        this.m_AttackCooldownCounter = this.m_iAttackInterval;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        double var1 = this.m_EntityOwner.getDistanceSq(this.m_EntityAttackTarget.posX, this.m_EntityAttackTarget.boundingBox.minY, this.m_EntityAttackTarget.posZ);
        boolean var3 = this.m_EntityOwner.getEntitySenses().canSee(this.m_EntityAttackTarget);

        if (var3)
        {
            ++this.m_iCanSeeTargetCounter;
        }
        else
        {
            this.m_iCanSeeTargetCounter = 0;
        }

        if (var1 <= this.m_dAttackRangeSq && this.m_iCanSeeTargetCounter >= 20)
        {
            this.m_EntityOwner.getNavigator().clearPathEntity();
        }
        else
        {
            this.m_EntityOwner.getNavigator().tryMoveToEntityLiving(this.m_EntityAttackTarget, this.m_fEntityMoveSpeed);
        }

        this.m_EntityOwner.getLookHelper().setLookPositionWithEntity(this.m_EntityAttackTarget, 30.0F, 30.0F);

        if (this.m_AttackCooldownCounter > 1)
        {
            --this.m_AttackCooldownCounter;
        }
        else if (var1 <= this.m_dAttackRangeSq && var3)
        {
            this.m_EntityRangedAttackOwner.attackEntityWithRangedAttack(this.m_EntityAttackTarget, 1.0F);
            this.m_AttackCooldownCounter = this.m_iAttackInterval;
        }
    }
}
