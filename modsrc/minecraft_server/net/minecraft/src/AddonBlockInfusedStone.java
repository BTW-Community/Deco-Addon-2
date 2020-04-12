package net.minecraft.src;

public class AddonBlockInfusedStone extends Block {
	AddonBlockInfusedStone(int id) {
		super(id, Material.rock);
		this.SetPicksEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
}
