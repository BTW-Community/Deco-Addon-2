package net.minecraft.src;

public class AddonModelBlockPergola extends FCModelBlock {
	private static final float beamWidth = .25F;
	private static final float beamHeight = .25F;
	private static final float beamLevelHeight = .1875F;
	private static final float beamSpacing = .375F;
	private static final float beamSpacingFromEdge = (1.0F - 2 * beamWidth - beamSpacing)/2;
	public static final float blockHeight = .4375F;
	
	@Override
	public void InitModel() {
		this.AddBox(0, 0, (float) beamSpacingFromEdge, 1.0F, (float) beamHeight, (float) beamSpacingFromEdge + beamWidth);
		this.AddBox(0, 0, 1 - (beamSpacingFromEdge + beamWidth), 1.0, beamHeight, 1 - beamSpacingFromEdge);
		this.AddBox(beamSpacingFromEdge, beamLevelHeight, 0, beamSpacingFromEdge + beamWidth, beamHeight + beamLevelHeight, 1.0);
		this.AddBox(1 - (beamSpacingFromEdge + beamWidth), beamLevelHeight, 0, 1 - beamSpacingFromEdge, beamHeight + beamLevelHeight, 1.0);
	}
}
