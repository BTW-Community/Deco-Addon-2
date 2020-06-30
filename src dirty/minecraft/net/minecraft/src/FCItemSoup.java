package net.minecraft.src;

public class FCItemSoup extends FCItemFood
{
    public FCItemSoup(int var1, int var2, float var3, boolean var4, String var5)
    {
        super(var1, var2, var3, var4, var5);
    }

    public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
    {
        super.onEaten(var1, var2, var3);
        ItemStack var4 = new ItemStack(Item.bowlEmpty);

        if (!var3.inventory.addItemStackToInventory(var4))
        {
            var3.dropPlayerItem(var4);
        }

        return var1;
    }
}
