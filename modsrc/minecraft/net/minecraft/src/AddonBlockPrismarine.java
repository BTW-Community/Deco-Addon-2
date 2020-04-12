package net.minecraft.src;

public class AddonBlockPrismarine extends Block {
	public AddonBlockPrismarine(int ID) {
		super(ID, Material.rock);
        this.setHardness(2.25F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("addonStone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		AddonManager.Register(this, new String[] {"prismarine", "prismarineBrick", "prismarineDark"}, new String[] {"Prismarine", "Prismarine Bricks", "Dark Prismarine"});
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
		Icons[0] = Register.registerIcon("ginger_prismarine");
		Icons[1] = Register.registerIcon("ginger_prismarineBrick");
		Icons[2] = Register.registerIcon("ginger_prismarineDark");
	}
	//
}
