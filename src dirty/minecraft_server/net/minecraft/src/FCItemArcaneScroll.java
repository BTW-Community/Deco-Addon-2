package net.minecraft.src;

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
}
