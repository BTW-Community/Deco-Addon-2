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
		DecoManager.Register(this, DecoUtilsMisc.colorOrder);
	}
	
	@Override
	public int damageDropped(int metadata){
		return metadata;
	}
	
	//CLIENT ONLY
	@Override
	public Icon getIcon(int side, int metadata){
		return this.icons[metadata];
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		for (int i = 0; i < 16; i++)
			this.icons[i] = iconRegister.registerIcon("decoBlockConcrete_" + DecoUtilsMisc.colorOrder[i]);
	}
}
