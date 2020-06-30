package net.minecraft.src;

public class FCItemMushroomSoup extends ItemFood
{
    public FCItemMushroomSoup(int var1, int var2)
    {
        super(var1, var2, 0.25F, false);
    }

    public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
    {
        super.onEaten(var1, var2, var3);
        var3.inventory.addItemStackToInventory(new ItemStack(Item.bowlEmpty));
        return var1;
    }
}
