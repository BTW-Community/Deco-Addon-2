package net.minecraft.src;

public class FCItemCraftingProgressive extends Item
{
    public static final int m_iProgressTimeInterval = 4;
    public static final int m_iDefaultMaxDamage = 600;

    public FCItemCraftingProgressive(int var1)
    {
        super(var1);
        this.maxStackSize = 1;
        this.setMaxDamage(this.GetProgressiveCraftingMaxDamage());
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
        return var1;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack var1)
    {
        return EnumAction.miscUse;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack var1)
    {
        return 72000;
    }

    public void UpdateUsingItem(ItemStack var1, World var2, EntityPlayer var3)
    {
        int var4 = var3.getItemInUseCount();

        if (this.getMaxItemUseDuration(var1) - var4 > this.GetItemUseWarmupDuration())
        {
            if (var4 % 4 == 0)
            {
                this.PlayCraftingFX(var1, var2, var3);
            }

            if (!var2.isRemote && var4 % 4 == 0)
            {
                int var5 = var1.getItemDamage();
                --var5;

                if (var5 > 0)
                {
                    var1.setItemDamage(var5);
                }
                else
                {
                    var3.SetItemInUseCount(1);
                }
            }
        }
    }

    public boolean IgnoreDamageWhenComparingDuringUse()
    {
        return true;
    }

    protected void PlayCraftingFX(ItemStack var1, World var2, EntityPlayer var3) {}

    protected int GetProgressiveCraftingMaxDamage()
    {
        return 600;
    }
}
