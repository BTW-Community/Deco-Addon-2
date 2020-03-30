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

	@Override
    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
		int metadata = var1.getBlockMetadata(var2, var3, var4);
        return AddonDefs.paintedPlanksSidingAndCorner[metadata].blockID;
    }

	@Override
    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 2;
    }

	@Override
    public int GetItemDamageDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 0;
    }
}
