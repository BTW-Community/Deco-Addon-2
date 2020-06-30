package net.minecraft.src;

public class FCEntityAICreeperSwell extends EntityAICreeperSwell
{
    private FCEntityCreeper m_myCreeper;

    public FCEntityAICreeperSwell(FCEntityCreeper var1)
    {
        super(var1);
        this.m_myCreeper = var1;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return this.m_myCreeper.getCreeperState() <= 0 && this.m_myCreeper.GetNeuteredState() > 0 ? false : (this.m_myCreeper.GetIsDeterminedToExplode() ? true : super.shouldExecute());
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (this.m_myCreeper.GetNeuteredState() > 0)
        {
            this.m_myCreeper.setCreeperState(-1);
        }
        else if (!this.m_myCreeper.GetIsDeterminedToExplode() && (this.creeperAttackTarget == null || this.m_myCreeper.getDistanceSqToEntity(this.creeperAttackTarget) > 36.0D || !this.m_myCreeper.getEntitySenses().canSee(this.creeperAttackTarget)))
        {
            this.m_myCreeper.setCreeperState(-1);
        }
        else
        {
            this.m_myCreeper.setCreeperState(1);
        }
    }
}
