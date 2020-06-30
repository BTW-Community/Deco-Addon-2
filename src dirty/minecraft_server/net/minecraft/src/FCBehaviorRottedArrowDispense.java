package net.minecraft.src;

public class FCBehaviorRottedArrowDispense extends BehaviorProjectileDispense
{
    /**
     * Return the projectile entity spawned by this dispense behavior.
     */
    protected IProjectile getProjectileEntity(World var1, IPosition var2)
    {
        FCEntityRottenArrow var3 = new FCEntityRottenArrow(var1, var2.getX(), var2.getY(), var2.getZ());
        var3.canBePickedUp = 2;
        return var3;
    }
}
