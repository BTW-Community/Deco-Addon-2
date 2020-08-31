package net.minecraft.src;

public class DecoBlockStoneSmooth extends Block{
	public DecoBlockStoneSmooth(int ID) {
		super(ID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.SetChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("addonStone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		DecoManager.Register(this, new String[] {"graniteSmooth", "andesiteSmooth", "dioriteSmooth"}, new String[] {"Polished Granite", "Polished Andesite", "Polished Diorite"});
	}

	public int damageDropped(int Meta)
	{
		return Meta;
	}
	
	//CLIENT ONLY METHODS
	public static Icon[] Icons = new Icon[3];
	public Icon getIcon(int Side, int Meta)
	{
		return Icons[Meta];
	}
	public void registerIcons(IconRegister Register)
	{
		Icons[0] = Register.registerIcon("ginger_graniteSmooth");
		Icons[1] = Register.registerIcon("ginger_andesiteSmooth");
		Icons[2] = Register.registerIcon("ginger_dioriteSmooth");
	}
	//
}
