package net.minecraft.src;

public class FCItemFoodCured extends FCItemFood
{
    public FCItemFoodCured(int var1, int var2, float var3, String var4)
    {
        super(var1, var2, var3, false, var4);
        this.maxStackSize = 32;
        this.setUnlocalizedName(var4);
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            var3.playSound("random.fizz", 0.25F, 2.5F + (var2.rand.nextFloat() - var2.rand.nextFloat()) * 0.2F);
        }

        super.onCreated(var1, var2, var3);
    }
}
