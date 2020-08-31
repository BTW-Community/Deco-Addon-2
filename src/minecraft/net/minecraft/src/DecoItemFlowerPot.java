package net.minecraft.src;

public class DecoItemFlowerPot extends FCItemPlacesAsBlock {
	public DecoItemFlowerPot(int id) {
		super(id, DecoDefs.flowerPot.blockID);
        this.setUnlocalizedName("flowerPot");
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.SetFilterableProperties(1);
        this.SetBuoyant();
	}
}
