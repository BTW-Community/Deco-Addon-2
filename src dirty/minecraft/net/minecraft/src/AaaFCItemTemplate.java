package net.minecraft.src;

public class AaaFCItemTemplate extends Item
{
    public AaaFCItemTemplate(int var1)
    {
        super(var1);
        this.SetNonBuoyant();
        this.SetBellowsBlowDistance(0);
        this.SetNotIncineratedInCrucible();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.NONE);
        this.SetFilterableProperties(0);
        this.setUnlocalizedName("fcItemTemplate");
        this.setCreativeTab(CreativeTabs.tabMisc);
    }
}
