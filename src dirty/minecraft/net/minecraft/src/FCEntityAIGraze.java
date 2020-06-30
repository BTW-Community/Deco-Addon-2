package net.minecraft.src;

public class FCEntityAIGraze extends EntityAIBase
{
    private EntityAnimal m_myAnimal;
    private int m_iGrazeCooldown = 0;

    public FCEntityAIGraze(EntityAnimal var1)
    {
        this.m_myAnimal = var1;
        this.setMutexBits(7);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.m_iGrazeCooldown > 0)
        {
            --this.m_iGrazeCooldown;
            return false;
        }
        else
        {
            return this.m_myAnimal.IsSubjectToHunger() ? this.m_myAnimal.IsHungryEnoughToGraze() && this.m_myAnimal.GetGrazeBlockForPos() != null : this.m_myAnimal.getRNG().nextInt(this.m_myAnimal.isChild() ? 50 : 1000) == 0 && this.m_myAnimal.GetGrazeBlockForPos() != null;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.m_iGrazeCooldown = 10;
        this.m_myAnimal.m_iGrazeProgressCounter = this.m_myAnimal.GetGrazeDuration();
        this.m_myAnimal.worldObj.setEntityState(this.m_myAnimal, (byte)10);
        this.m_myAnimal.getNavigator().clearPathEntity();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.m_myAnimal.m_iGrazeProgressCounter = 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.m_myAnimal.m_iGrazeProgressCounter > 0;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.m_myAnimal.m_iGrazeProgressCounter = Math.max(0, this.m_myAnimal.m_iGrazeProgressCounter - 1);

        if (this.m_myAnimal.m_iGrazeProgressCounter == 4)
        {
            FCUtilsBlockPos var1 = this.m_myAnimal.GetGrazeBlockForPos();

            if (var1 != null)
            {
                this.m_myAnimal.OnGrazeBlock(var1.i, var1.j, var1.k);
                FCBlockGroundCover.ClearAnyGroundCoverRestingOnBlock(this.m_myAnimal.worldObj, var1.i, var1.j, var1.k);

                if (this.m_myAnimal.ShouldNotifyBlockOnGraze())
                {
                    int var2 = this.m_myAnimal.worldObj.getBlockId(var1.i, var1.j, var1.k);

                    if (var2 != 0)
                    {
                        this.m_myAnimal.PlayGrazeFX(var1.i, var1.j, var1.k, var2);
                        Block.blocksList[var2].OnGrazed(this.m_myAnimal.worldObj, var1.i, var1.j, var1.k, this.m_myAnimal);
                    }
                }
            }
        }
    }
}
