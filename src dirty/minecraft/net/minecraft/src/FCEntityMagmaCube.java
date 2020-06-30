package net.minecraft.src;

public class FCEntityMagmaCube extends EntityMagmaCube
{
    public FCEntityMagmaCube(World var1)
    {
        super(var1);
        this.landMovementFactor = 0.5F;
    }

    /**
     * Indicates weather the slime is able to damage the player (based upon the slime's size)
     */
    protected boolean canDamagePlayer()
    {
        return this.isEntityAlive() && this.attackTime <= 0;
    }

    public void CheckForScrollDrop()
    {
        if (this.getSlimeSize() == 1 && this.rand.nextInt(250) == 0)
        {
            ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.fireAspect.effectId);
            this.entityDropItem(var1, 0.0F);
        }
    }

    protected EntitySlime createInstance()
    {
        return new FCEntityMagmaCube(this.worldObj);
    }
}
