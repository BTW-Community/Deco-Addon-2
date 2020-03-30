package net.minecraft.src;

public class AddonBlockFenceGate extends FCBlockFenceGate {
	private String iconName;

	public AddonBlockFenceGate(int ID, String texture) {
		super(ID);
		this.setUnlocalizedName(texture);
		iconName = texture;
	}
}