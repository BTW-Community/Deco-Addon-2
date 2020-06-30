package net.minecraft.src;

public class FCBehaviorSoulUrnDispense extends BehaviorProjectileDispense
{
    /**
     * Return the projectile entity spawned by this dispense behavior.
     */
    protected IProjectile getProjectileEntity(World var1, IPosition var2)
    {
        return new FCEntityUrn(var1, var2.getX(), var2.getY(), var2.getZ(), FCBetterThanWolves.fcItemSoulUrn.itemID);
    }
}
