package net.minecraft.src;

public abstract class FCItemBucketDrinkable extends FCItemBucket
{
    private int m_iHungerHealed;
    private float m_fSaturationModifier;

    public FCItemBucketDrinkable(int var1, int var2, float var3)
    {
        super(var1);
        this.m_iHungerHealed = var2;
        this.m_fSaturationModifier = var3;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack var1)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack var1)
    {
        return EnumAction.drink;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
        return var1;
    }

    public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (!var3.capabilities.isCreativeMode)
        {
            --var1.stackSize;
        }

        if (!var2.isRemote)
        {
            var3.getFoodStats().addStats(this.m_iHungerHealed, this.m_fSaturationModifier);
        }

        return var1.stackSize <= 0 ? new ItemStack(Item.bucketEmpty) : var1;
    }
}
