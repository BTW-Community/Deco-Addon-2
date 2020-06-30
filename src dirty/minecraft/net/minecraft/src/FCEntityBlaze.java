package net.minecraft.src;

public class FCEntityBlaze extends EntityBlaze
{
    public FCEntityBlaze(World var1)
    {
        super(var1);
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        super.dropFewItems(true, var2);
    }

    public void CheckForScrollDrop()
    {
        if (this.rand.nextInt(500) == 0)
        {
            ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.flame.effectId);
            this.entityDropItem(var1, 0.0F);
        }
    }
}
