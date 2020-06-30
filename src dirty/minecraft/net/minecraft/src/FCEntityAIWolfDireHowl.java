package net.minecraft.src;

public class FCEntityAIWolfDireHowl extends EntityAIBase
{
    protected FCEntityWolfDire m_AssociatedWolf;
    protected World m_World;
    protected int m_iHowlCounter;
    protected static final int m_iChanceOfHowling = 2400;
    protected static final int m_iHowlDuration = 80;

    public FCEntityAIWolfDireHowl(FCEntityWolfDire var1)
    {
        this.m_AssociatedWolf = var1;
        this.m_World = var1.worldObj;
        this.setMutexBits(7);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        int var1 = (int)(this.m_World.worldInfo.getWorldTime() % 24000L);
        return var1 > 13500 && var1 < 22500 ? this.m_AssociatedWolf.getRNG().nextInt(2400) == 0 : false;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.m_iHowlCounter < 80;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.m_iHowlCounter = 0;
        this.m_World.setEntityState(this.m_AssociatedWolf, (byte)10);
        this.m_AssociatedWolf.getNavigator().clearPathEntity();
        this.NotifyOtherWolvesInAreaOfHowl();
        this.m_AssociatedWolf.m_iHeardHowlCountdown = 0;
        int var1 = MathHelper.floor_double(this.m_AssociatedWolf.posX);
        int var2 = MathHelper.floor_double(this.m_AssociatedWolf.posY) + 1;
        int var3 = MathHelper.floor_double(this.m_AssociatedWolf.posZ);
        this.m_World.func_82739_e(2256, var1, var2, var3, 1);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.m_iHowlCounter = 0;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        ++this.m_iHowlCounter;
    }

    private void NotifyOtherWolvesInAreaOfHowl()
    {
        for (int var1 = 0; var1 < this.m_World.loadedEntityList.size(); ++var1)
        {
            Entity var2 = (Entity)this.m_World.loadedEntityList.get(var1);
            var2.NotifyOfWolfHowl(this.m_AssociatedWolf);
        }
    }
}
