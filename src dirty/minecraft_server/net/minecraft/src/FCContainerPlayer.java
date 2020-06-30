package net.minecraft.src;

public class FCContainerPlayer extends ContainerPlayer
{
    public FCContainerPlayer(InventoryPlayer var1, boolean var2, EntityPlayer var3)
    {
        super(var1, var2, var3);
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    public ItemStack transferStackInSlot(EntityPlayer var1, int var2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(var2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (var2 == 0)
            {
                if (!this.mergeItemStack(var5, 9, 45, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (var2 >= 1 && var2 < 5)
            {
                if (!this.mergeItemStack(var5, 9, 45, true))
                {
                    return null;
                }
            }
            else if (var2 >= 5 && var2 < 9)
            {
                if (!this.mergeItemStack(var5, 9, 45, true))
                {
                    return null;
                }
            }
            else if (var3.getItem() instanceof ItemArmor && !((Slot)this.inventorySlots.get(5 + ((ItemArmor)var3.getItem()).armorType)).getHasStack())
            {
                int var6 = 5 + ((ItemArmor)var3.getItem()).armorType;

                if (!this.mergeItemStack(var5, var6, var6 + 1, false))
                {
                    return null;
                }
            }
            else if (var2 >= 9 && var2 < 36)
            {
                if (!this.mergeItemStack(var5, 36, 45, false))
                {
                    return null;
                }
            }
            else if (var2 >= 36 && var2 < 45)
            {
                if (!this.mergeItemStack(var5, 9, 36, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 9, 45, true))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(var1, var5);
        }

        return var3;
    }
}
