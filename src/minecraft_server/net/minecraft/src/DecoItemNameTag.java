package net.minecraft.src;

public class DecoItemNameTag extends Item {
	protected DecoItemNameTag(int id) {
		super(id);
		this.setUnlocalizedName("decoItemNameTag");
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setMaxStackSize(1);
	}
}