package net.minecraft.src;

public class FCEntityBat extends EntityBat
{
    public FCEntityBat(World var1)
    {
        super(var1);
    }

    public void CheckForScrollDrop()
    {
        if (this.rand.nextInt(250) == 0)
        {
            ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.featherFalling.effectId);
            this.entityDropItem(var1, 0.0F);
        }
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        byte var3 = 1;

        if (this.rand.nextInt(4) - var2 <= 0)
        {
            var3 = 2;
        }

        for (int var4 = 0; var4 < var3; ++var4)
        {
            this.dropItem(FCBetterThanWolves.fcItemBatWing.itemID, 1);
        }
    }

    public boolean AttractsLightning()
    {
        return false;
    }
}
