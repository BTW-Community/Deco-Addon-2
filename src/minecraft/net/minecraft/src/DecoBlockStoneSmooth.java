package net.minecraft.src;

public class DecoBlockStoneSmooth extends Block{
	public DecoBlockStoneSmooth(int ID) {
		super(ID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.SetChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockStoneSmooth");
		this.setCreativeTab(CreativeTabs.tabBlock);

		DecoManager.Register(this, new String[] {"granite", "andesite", "diorite"});
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
		Icons[0] = Register.registerIcon("decoBlockGraniteSmooth");
		Icons[1] = Register.registerIcon("decoBlockAndesiteSmooth");
		Icons[2] = Register.registerIcon("decoBlockDioriteSmooth");
	}
	//
}
