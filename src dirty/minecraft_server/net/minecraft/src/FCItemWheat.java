package net.minecraft.src;

public class FCItemWheat extends Item
{
    public FCItemWheat(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
        this.SetBellowsBlowDistance(1);
        this.SetFilterableProperties(4);
        this.SetAsBasicHerbivoreFood();
        this.SetAsBasicPigFood();
        this.setUnlocalizedName("fcItemWheat");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }
}
