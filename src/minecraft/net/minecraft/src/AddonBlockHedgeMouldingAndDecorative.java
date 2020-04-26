package net.minecraft.src;

public class AddonBlockHedgeMouldingAndDecorative extends FCBlockMouldingAndDecorative {
	private boolean shouldColor;

	protected AddonBlockHedgeMouldingAndDecorative(int var1, Material var2, String var3, String var4, int var5, float var6, float var7, StepSound var8, String var9, boolean shouldColor) {
		super(var1, var2, var3, var4, var5, var6, var7, var8, var9);
		this.blockMaterial = AddonDefs.materialHedge;
		this.SetBuoyant();
		this.SetFireProperties(FCEnumFlammability.LEAVES);
		this.shouldColor = shouldColor;
	}
	
}
