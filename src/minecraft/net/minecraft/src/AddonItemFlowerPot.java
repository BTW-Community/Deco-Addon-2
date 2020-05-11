package net.minecraft.src;

public class AddonItemFlowerPot extends FCItemPlacesAsBlock {
	public AddonItemFlowerPot(int id) {
		super(id, AddonDefs.flowerPot.blockID);
        this.setUnlocalizedName("flowerPot");
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.SetFilterableProperties(1);
        this.SetBuoyant();
	}
}
