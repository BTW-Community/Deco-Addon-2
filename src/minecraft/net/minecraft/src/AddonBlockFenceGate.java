package net.minecraft.src;

public class AddonBlockFenceGate extends FCBlockFenceGate {
	private String iconName;

	public AddonBlockFenceGate(int ID, String texture) {
		super(ID);
		this.setUnlocalizedName(texture);
		iconName = texture;
	}
	
	//CLIENT ONLY
	@Override
	public Icon getIcon(int par1, int par2) {
		return blockIcon;
	}
	
	@Override public void registerIcons(IconRegister r)
	{
		blockIcon = r.registerIcon(iconName);
	}
}
