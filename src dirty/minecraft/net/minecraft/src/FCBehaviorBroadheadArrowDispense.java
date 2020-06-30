package net.minecraft.src;

public class FCBehaviorBroadheadArrowDispense extends BehaviorProjectileDispense
{
    /**
     * Return the projectile entity spawned by this dispense behavior.
     */
    protected IProjectile getProjectileEntity(World var1, IPosition var2)
    {
        FCEntityBroadheadArrow var3 = new FCEntityBroadheadArrow(var1, var2.getX(), var2.getY(), var2.getZ());
        var3.canBePickedUp = 1;
        return var3;
    }
}
