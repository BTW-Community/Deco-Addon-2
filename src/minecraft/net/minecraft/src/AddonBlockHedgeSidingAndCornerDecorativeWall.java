package net.minecraft.src;

public class AddonBlockHedgeSidingAndCornerDecorativeWall extends AddonBlockSidingAndCornerDecorativeWall {
	private boolean shouldColor;

	public AddonBlockHedgeSidingAndCornerDecorativeWall(int var1, Material var2, String var3, float var4, float var5, StepSound var6, String var7, String originalName, boolean shouldColor) {
		super(var1, var2, var3, var4, var5, var6, var7, originalName);
		this.blockMaterial = AddonDefs.materialHedge;
		this.SetBuoyant();
		this.SetFireProperties(FCEnumFlammability.LEAVES);
		this.shouldColor = shouldColor;
	}

}
