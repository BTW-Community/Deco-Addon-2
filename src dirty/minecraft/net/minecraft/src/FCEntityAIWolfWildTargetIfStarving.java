package net.minecraft.src;

public class FCEntityAIWolfWildTargetIfStarving extends EntityAINearestAttackableTarget
{
    private FCEntityWolf m_AssociatedWolf;

    public FCEntityAIWolfWildTargetIfStarving(FCEntityWolf var1, Class var2, float var3, int var4, boolean var5)
    {
        super(var1, var2, var3, var4, var5);
        this.m_AssociatedWolf = var1;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.m_AssociatedWolf.IsWildAndStarving() ? false : super.continueExecuting();
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return !this.m_AssociatedWolf.IsWildAndStarving() ? false : super.shouldExecute();
    }
}
