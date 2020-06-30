package net.minecraft.src;

public class FCItemBucketMilkChocolate extends FCItemBucketDrinkable
{
    public FCItemBucketMilkChocolate(int var1)
    {
        super(var1, 9, 0.25F);
        this.setUnlocalizedName("fcItemBucketChocolateMilk");
    }

    /**
     * Returns the blockID for this Item
     */
    public int getBlockID()
    {
        return FCBetterThanWolves.fcBlockBucketMilkChocolate.blockID;
    }

    public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (!var2.isRemote)
        {
            var3.clearActivePotions();
        }

        return super.onEaten(var1, var2, var3);
    }

    public boolean DoesConsumeContainerItemWhenCrafted(Item var1)
    {
        return var1.itemID == Item.bucketEmpty.itemID;
    }
}
