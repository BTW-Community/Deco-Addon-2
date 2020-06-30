package net.minecraft.src;

public class FCBehaviorDispenseDynamite extends BehaviorProjectileDispense
{
    /**
     * Return the projectile entity spawned by this dispense behavior.
     */
    protected IProjectile getProjectileEntity(World var1, IPosition var2)
    {
        return new FCEntityDynamite(var1, var2.getX(), var2.getY(), var2.getZ(), FCBetterThanWolves.fcItemDynamite.itemID);
    }
}
