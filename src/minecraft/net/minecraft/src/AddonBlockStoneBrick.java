package net.minecraft.src;

public class AddonBlockStoneBrick extends Block {

	protected AddonBlockStoneBrick(int ID) {
		super(ID, Material.rock);
        this.setHardness(2.25F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("addonStone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		AddonManager.Register(this, new String[] {"granitBrick", "andesiteBrick", "dioriteBrick"}, new String[] {"Granite Brick", "Andesite Brick", "Diorite Brick"});
	}

	public int damageDropped(int Meta)
	{
		return Meta;
	}

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }
	
	//CLIENT ONLY METHODS
	public static Icon[] Icons = new Icon[3];
	public Icon getIcon(int Side, int Meta)
	{
		return Icons[Meta];
	}
	public void registerIcons(IconRegister Register)
	{
		Icons[0] = Register.registerIcon("ginger_graniteBrick");
		Icons[1] = Register.registerIcon("ginger_andesiteBrick");
		Icons[2] = Register.registerIcon("ginger_dioriteBrick");
	}
	//
}
