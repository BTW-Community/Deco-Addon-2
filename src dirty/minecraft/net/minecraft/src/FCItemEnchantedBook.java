package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCItemEnchantedBook extends ItemEnchantedBook
{
    public FCItemEnchantedBook(int var1)
    {
        super(var1);
        this.SetBuoyant();
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var2.isRemote)
        {
            var3.addChatMessage("The language of this text is unfamiliar to you");
        }

        return var1;
    }

    public void InitializeStackOnGiveCommand(Random var1, ItemStack var2) {}

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4) {}
}
