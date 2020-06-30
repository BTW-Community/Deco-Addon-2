package net.minecraft.src;

public class FCItemBucketMilk extends FCItemBucketDrinkable
{
    public FCItemBucketMilk(int var1)
    {
        super(var1, 6, 0.25F);
        this.setUnlocalizedName("milk");
    }

    /**
     * Returns the blockID for this Item
     */
    public int getBlockID()
    {
        return FCBetterThanWolves.fcBlockBucketMilk.blockID;
    }

    public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (!var2.isRemote)
        {
            var3.clearActivePotions();
        }

        return super.onEaten(var1, var2, var3);
    }
}
