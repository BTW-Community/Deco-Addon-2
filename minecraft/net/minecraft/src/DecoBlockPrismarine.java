package net.minecraft.src;

public class DecoBlockPrismarine extends Block {
	public static final int typeDefault = 0;
	public static final int typeBrick = 1;
	public static final int typeDark = 2;
	
	public DecoBlockPrismarine(int ID) {
		super(ID, Material.rock);
        this.setHardness(2.25F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockPrismarine");
		this.setCreativeTab(CreativeTabs.tabBlock);

		DecoManager.register(this, new String[] {"prismarine", "prismarineBrick", "prismarineDark"});
	}

	public int damageDropped(int meta)
	{
		return meta;
	}
	
	//CLIENT ONLY METHODS
	public static Icon[] Icons = new Icon[3];
	public Icon getIcon(int Side, int Meta)
	{
		return Icons[Meta];
	}
	public void registerIcons(IconRegister Register)
	{
		Icons[typeDefault] = Register.registerIcon("decoBlockPrismarine");
		Icons[typeBrick] = Register.registerIcon("decoBlockPrismarineBrick");
		Icons[typeDark] = Register.registerIcon("decoBlockPrismarineDark");
	}
	//
}
