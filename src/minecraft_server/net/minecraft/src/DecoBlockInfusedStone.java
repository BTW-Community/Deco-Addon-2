package net.minecraft.src;

public class DecoBlockInfusedStone extends Block {
	public DecoBlockInfusedStone(int id) {
		super(id, Material.rock);
		this.SetPicksEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(10F);
		this.setUnlocalizedName("decoBlockInfusedStone");
	}
	
	public int damageDropped(int meta) {
		return meta;
	}
}
