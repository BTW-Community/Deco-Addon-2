package net.minecraft.src;

public class FCItemSlimeball extends FCItemMortar
{
    public FCItemSlimeball(int var1)
    {
        super(var1);
        this.SetNeutralBuoyant();
        this.SetFilterableProperties(2);
        this.setUnlocalizedName("slimeball");
        this.setCreativeTab(CreativeTabs.tabMisc);
    }
}
