package net.minecraft.src;

public class DecoItemPileRedSand extends Item {
    public DecoItemPileRedSand(int var1) {
        super(var1);
        this.SetBellowsBlowDistance(2);
        this.SetFilterableProperties(8);
        this.setUnlocalizedName("decoItemPileRedSand");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }
}