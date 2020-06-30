package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class EntityAIHurtByTarget extends EntityAITarget
{
    private boolean m_bNearbyEntitiesOfSameTypeAttack;

    /** The PathNavigate of our entity. */
    EntityLiving entityPathNavigate;

    public EntityAIHurtByTarget(EntityLiving par1EntityLiving, boolean par2)
    {
        super(par1EntityLiving, 16.0F, false);
        this.m_bNearbyEntitiesOfSameTypeAttack = par2;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return this.isSuitableTarget(this.taskOwner.getAITarget(), false);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.taskOwner.getAITarget() != null && this.taskOwner.getAITarget().isEntityAlive() && this.taskOwner.getAITarget() == this.entityPathNavigate && this.taskOwner.getAttackTarget() != null && this.taskOwner.getAttackTarget() == this.entityPathNavigate;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.taskOwner.getAITarget());
        this.entityPathNavigate = this.taskOwner.getAITarget();

        if (this.m_bNearbyEntitiesOfSameTypeAttack)
        {
            List var1 = this.taskOwner.worldObj.getEntitiesWithinAABB(this.taskOwner.getClass(), AxisAlignedBB.getAABBPool().getAABB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D).expand((double)this.targetDistance, 10.0D, (double)this.targetDistance));
            Iterator var2 = var1.iterator();

            while (var2.hasNext())
            {
                EntityLiving var3 = (EntityLiving)var2.next();

                if (this.taskOwner != var3 && var3.getAttackTarget() == null && var3.getAITarget() == null)
                {
                    var3.setRevengeTarget(this.taskOwner.getAITarget());
                }
            }
        }

        super.startExecuting();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        if (this.taskOwner.getAttackTarget() != null && this.entityPathNavigate == this.taskOwner.getAttackTarget())
        {
            this.taskOwner.setAttackTarget((EntityLiving)null);
        }

        if (this.taskOwner.getAITarget() != null && this.entityPathNavigate == this.taskOwner.getAITarget())
        {
            this.taskOwner.setRevengeTarget((EntityLiving)null);
        }
    }
}
