package net.minecraft.src;

public class FCEntityAIZombieSecondaryAttack extends EntityAIAttackOnCollide
{
    private EntityZombie m_zombie;

    public FCEntityAIZombieSecondaryAttack(EntityZombie var1)
    {
        super(var1, EntityCreature.class, var1.moveSpeed, true);
        this.m_zombie = var1;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        EntityLiving var1 = this.attacker.getAttackTarget();
        return var1 != null && var1.IsValidZombieSecondaryTarget(this.m_zombie) ? super.continueExecuting() : false;
    }
}
