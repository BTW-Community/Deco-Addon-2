package net.minecraft.src;

public class FCItemBook extends ItemBook
{
    public FCItemBook(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
        this.setUnlocalizedName("book");
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 0;
    }
}
