package net.minecraft.src;

public class FCItemStraw extends Item
{
    public FCItemStraw(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
        this.SetBellowsBlowDistance(2);
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.KINDLING);
        this.SetFilterableProperties(4);
        this.SetHerbivoreFoodValue(200);
        this.setUnlocalizedName("fcItemStraw");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }
}
