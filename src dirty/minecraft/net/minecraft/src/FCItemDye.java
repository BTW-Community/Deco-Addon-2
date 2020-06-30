package net.minecraft.src;

public class FCItemDye extends ItemDye
{
    private Icon m_cocoaPowderIcon;

    public FCItemDye(int var1)
    {
        super(var1);
        this.SetBellowsBlowDistance(2);
        this.setUnlocalizedName("dyePowder");
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var1.getItemDamage() == 15 && this.ApplyBoneMeal(var3, var4, var5, var6))
        {
            --var1.stackSize;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Called when a player right clicks an entity with an item.
     */
    public boolean itemInteractionForEntity(ItemStack var1, EntityLiving var2)
    {
        if (var2 instanceof FCEntitySheep)
        {
            FCEntitySheep var3 = (FCEntitySheep)var2;
            int var4 = BlockCloth.getBlockFromDye(var1.getItemDamage());

            if (!var3.getSheared() && var3.getFleeceColor() != var4)
            {
                var3.setSuperficialFleeceColor(var4);
                --var1.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public int GetFilterableProperties(ItemStack var1)
    {
        return var1.getItemDamage() == 0 ? 2 : 8;
    }

    private boolean ApplyBoneMeal(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3, var4)];
        return var5 != null && var5.AttemptToApplyFertilizerTo(var1, var2, var3, var4);
    }

    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_cocoaPowderIcon = var1.registerIcon("fcItemCocoaPowder");
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int var1)
    {
        return var1 == 3 ? this.m_cocoaPowderIcon : super.getIconFromDamage(var1);
    }
}
