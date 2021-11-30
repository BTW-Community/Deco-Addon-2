package net.minecraft.src;

public class DecoBlockInfusedStone extends Block {
	public static final int typeDefault = 0;
	public static final int typeSmooth = 1;
	public static final int typeBrick = 2;
	public static final int typeChiseled = 3;
	
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
