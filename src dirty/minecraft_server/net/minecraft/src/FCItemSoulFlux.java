package net.minecraft.src;

public class FCItemSoulFlux extends Item
{
    public FCItemSoulFlux(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetBellowsBlowDistance(3);
        this.SetFilterableProperties(8);
        this.setPotionEffect(PotionHelper.glowstoneEffect);
        this.setUnlocalizedName("fcItemSoulFlux");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }
}
