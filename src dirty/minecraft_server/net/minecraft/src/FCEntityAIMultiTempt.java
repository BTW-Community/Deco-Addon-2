package net.minecraft.src;

public class FCEntityAIMultiTempt extends EntityAIBase
{
    protected EntityAnimal m_myAnimal;
    protected float m_fMoveSpeed;
    private EntityPlayer temptingPlayer;
    private int m_delayBetweenCounter = 0;
    private boolean m_bDoesAnimalNormallAvoidWater;

    public FCEntityAIMultiTempt(EntityAnimal var1, float var2)
    {
        this.m_myAnimal = var1;
        this.m_fMoveSpeed = var2;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.m_delayBetweenCounter <= 0)
        {
            this.temptingPlayer = this.m_myAnimal.worldObj.getClosestPlayerToEntity(this.m_myAnimal, 10.0D);

            if (this.temptingPlayer != null)
            {
                ItemStack var1 = this.temptingPlayer.getCurrentEquippedItem();

                if (var1 != null)
                {
                    return this.m_myAnimal.IsTemptingItem(var1);
                }
            }
        }
        else
        {
            --this.m_delayBetweenCounter;
        }

        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.m_bDoesAnimalNormallAvoidWater = this.m_myAnimal.getNavigator().getAvoidsWater();
        this.m_myAnimal.getNavigator().setAvoidsWater(false);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.temptingPlayer = null;
        this.m_myAnimal.getNavigator().clearPathEntity();
        this.m_delayBetweenCounter = 33;
        this.m_myAnimal.getNavigator().setAvoidsWater(this.m_bDoesAnimalNormallAvoidWater);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.m_myAnimal.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, 30.0F, (float)this.m_myAnimal.getVerticalFaceSpeed());

        if (this.m_myAnimal.getDistanceSqToEntity(this.temptingPlayer) < 6.25D)
        {
            this.m_myAnimal.getNavigator().clearPathEntity();
        }
        else
        {
            this.m_myAnimal.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.m_fMoveSpeed);
        }
    }
}
