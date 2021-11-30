package net.minecraft.src;

public class DecoBlockConcrete extends Block{
	private Icon[] icons = new Icon[16];
	
	public DecoBlockConcrete(int id) {
		super(id, Material.rock);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(Block.soundStoneFootstep);
		this.setUnlocalizedName("decoBlockConcrete");
		DecoManager.register(this, DecoUtilsMisc.colorOrder);
	}
	
	@Override
	public int damageDropped(int metadata){
		return metadata;
	}
}
