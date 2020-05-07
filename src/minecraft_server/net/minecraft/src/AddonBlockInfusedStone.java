package net.minecraft.src;

public class AddonBlockInfusedStone extends Block {
	AddonBlockInfusedStone(int id) {
		super(id, Material.rock);
		this.SetPicksEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(10F);
	}
	
	public int damageDropped(int meta) {
		return meta;
	}
}
