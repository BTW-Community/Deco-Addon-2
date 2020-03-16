package net.minecraft.src;

public class AddonBlockPlanksPainted extends FCBlockPlanks {
	public static final String[] tags = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "lightGrey", "grey", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" };
	public static final String[] names = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Grey", "Grey", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White" };
	
	private String nameTag;
	private Icon[] icons = new Icon[16];

	public AddonBlockPlanksPainted(int ID, String tag, String name) {
		super(ID);
		this.nameTag = tag;
		this.setUnlocalizedName(tag);
		AddonManager.Register(this,tags, names," " + name);
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
			this.icons[i] = iconRegister.registerIcon(nameTag+"_" + i);
	}
}
