package net.minecraft.src;

public class FCEntityAIWolfHowlSitting extends FCEntityAIWolfHowl
{
    public FCEntityAIWolfHowlSitting(FCEntityWolf var1)
    {
        super(var1);
        this.setMutexBits(2);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.m_AssociatedWolf.isSitting() && !this.m_AssociatedWolf.isChild())
        {
            int var1 = (int)(this.m_World.worldInfo.getWorldTime() % 24000L);

            if (var1 > 13500 && var1 < 22500 && this.m_AssociatedWolf.m_iHeardHowlCountdown > 0 && this.m_AssociatedWolf.m_iHeardHowlCountdown <= 80)
            {
                this.m_iHowlingGroupInitiator = false;
                return this.m_AssociatedWolf.getRNG().nextInt(240) == 0;
            }
        }

        return false;
    }
}
