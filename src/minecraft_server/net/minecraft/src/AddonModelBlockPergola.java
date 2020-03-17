package net.minecraft.src;

public class AddonModelBlockPergola extends FCModelBlock {
	private static final float beamWidth = .25F;
	private static final float beamHeight = .25F;
	private static final float beamLevelHeight = .1875F;
	private static final float beamSpacing = .375F;
	private static final float beamSpacingFromEdge = (1.0F - 2 * beamWidth - beamSpacing)/2;
	public static final float blockHeight = .4375F;
	
	//private ModelRenderer renderer = new ModelRenderer(this, 0, 0).setTextureSize(32, 32);
	
	/*public AddonModelBlockPergola() {
		this.renderer.addBox(0.0F, 0.0F, beamSpacingFromEdge, 16, beamHeight, beamWidth, 1);
		this.renderer.addBox(0.0F, 0.0F, 1 - (beamSpacingFromEdge + beamWidth/16.0F), 16, beamHeight, beamWidth, 1);
		this.renderer.addBox(beamSpacingFromEdge, beamLevelHeight, 0.0F, 1, beamHeight, beamWidth);
		this.renderer.addBox(1 - (beamSpacingFromEdge + beamWidth), beamLevelHeight, 0.0F, 1, beamHeight, beamWidth);
	}*/
	
	@Override
	public void InitModel() {
		this.AddBox(0, 0, (float) beamSpacingFromEdge, 1.0F, (float) beamHeight, (float) beamSpacingFromEdge + beamWidth);
		this.AddBox(0, 0, 1 - (beamSpacingFromEdge + beamWidth), 1.0, beamHeight, 1 - beamSpacingFromEdge);
		this.AddBox(beamSpacingFromEdge, beamLevelHeight, 0, beamSpacingFromEdge + beamWidth, beamHeight + beamLevelHeight, 1.0);
		this.AddBox(1 - (beamSpacingFromEdge + beamWidth), beamLevelHeight, 0, 1 - beamSpacingFromEdge, beamHeight + beamLevelHeight, 1.0);
	}
	
	/*public void renderAll() {
		renderer.render(.0625F);
	}*/
}
