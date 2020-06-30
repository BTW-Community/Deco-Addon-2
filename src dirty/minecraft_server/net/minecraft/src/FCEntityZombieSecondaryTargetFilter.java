package net.minecraft.src;

public class FCEntityZombieSecondaryTargetFilter implements IEntitySelector
{
    EntityZombie m_zombie;

    public FCEntityZombieSecondaryTargetFilter(EntityZombie var1)
    {
        this.m_zombie = var1;
    }

    /**
     * Return whether the specified entity is applicable to this filter.
     */
    public boolean isEntityApplicable(Entity var1)
    {
        return var1.IsValidZombieSecondaryTarget(this.m_zombie);
    }
}
