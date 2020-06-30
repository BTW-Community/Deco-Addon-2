package net.minecraft.src;

import java.util.List;

public class FCItemArcaneScroll extends Item
{
    public FCItemArcaneScroll(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.SetBuoyant();
        this.SetBellowsBlowDistance(3);
        this.SetFilterableProperties(18);
        this.setUnlocalizedName("fcItemScrollArcane");
        this.setCreativeTab(CreativeTabs.tabBrewing);
    }

    public boolean hasEffect(ItemStack var1)
    {
        return true;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4)
    {
        int var5 = MathHelper.clamp_int(var1.getItemDamage(), 0, Enchantment.enchantmentsList.length - 1);
        Enchantment var6 = Enchantment.enchantmentsList[var5];

        if (var6 != null)
        {
            var3.add(StatCollector.translateToLocal(var6.getName()));
        }
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < Enchantment.enchantmentsList.length; ++var4)
        {
            if (Enchantment.enchantmentsList[var4] != null)
            {
                var3.add(new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.enchantmentsList[var4].effectId));
            }
        }
    }
}
