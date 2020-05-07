package net.minecraft.src;

public class AddonBlockConcrete extends Block{
	public static final String[] tags = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "lightGrey", "grey", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" };
	public static final String[] names = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Grey", "Grey", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White" };
	
	private String nameTag;
	private Icon[] icons = new Icon[16];
	
	public AddonBlockConcrete(int id, String tag, String name) {
		super(id, Material.rock);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(Block.soundStoneFootstep);
		this.nameTag = tag;
		this.setUnlocalizedName(tag);
		AddonManager.Register(this,tags, names," " + name);
	}
	
	@Override
	public int damageDropped(int metadata){
		return metadata;
	}
}
