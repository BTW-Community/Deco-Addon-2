package net.minecraft.src;

public class FCEntityBlaze extends EntityBlaze
{
    public FCEntityBlaze(World var1)
    {
        super(var1);
    }

    /**
     * Drop 0-2 items of this living's type
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
