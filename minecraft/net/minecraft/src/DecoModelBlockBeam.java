package net.minecraft.src;

public class DecoModelBlockBeam extends FCModelBlock {
	public static final double beamWidth = .375;
	public static final double beamHeight = .25;
	public static final double crossBeamOffset = 0.0625;
	public static final double crossBeamHeight = .1875;
	
	protected void InitModel() {
		this.AddBox(0.0F, 0.0F, .5 - beamWidth / 2, beamHeight, 1.0F, .5 + beamWidth / 2);
		this.AddBox(beamHeight, 1 - beamHeight, .5 - beamWidth / 2, 1.0F, 1.0F, .5 + beamWidth / 2);
		//this.AddBox(beamHeight, 0.0F, .5 - beamWidth / 2 + .0001, 1.0F, 1.0F - beamHeight, .5 + beamWidth / 2 - 0.0001);
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(beamHeight, 0.0F, .5 - beamWidth / 2 + crossBeamOffset), 
				Vec3.createVectorHelper(beamHeight - crossBeamHeight * Math.sqrt(2) / 2, crossBeamHeight * Math.sqrt(2) / 2, .5 - beamWidth / 2 + crossBeamOffset), 
				Vec3.createVectorHelper(1 - crossBeamHeight * Math.sqrt(2) / 2, 1 - beamHeight + crossBeamHeight * Math.sqrt(2) / 2, .5 - beamWidth / 2 + crossBeamOffset), 
				Vec3.createVectorHelper(1.0F, 1 - beamHeight, .5 - beamWidth / 2 + crossBeamOffset))
				.SetUVFractions(0.0F, 0.0F, 1.0F, (float) crossBeamHeight));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(1.0F, 1 - beamHeight, .5 + beamWidth / 2 - crossBeamOffset),
				Vec3.createVectorHelper(1 - crossBeamHeight * Math.sqrt(2) / 2, 1 - beamHeight + crossBeamHeight * Math.sqrt(2) / 2, .5 + beamWidth / 2 - crossBeamOffset), 
				Vec3.createVectorHelper(beamHeight - crossBeamHeight * Math.sqrt(2) / 2, crossBeamHeight * Math.sqrt(2) / 2, .5 + beamWidth / 2 - crossBeamOffset), 
				Vec3.createVectorHelper(beamHeight, 0.0F, .5 + beamWidth / 2 - crossBeamOffset))
				.SetUVFractions(0.0F, 0.0F, 1.0F, (float) crossBeamHeight));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(beamHeight, 0, .5 + beamWidth / 2 - crossBeamOffset),
				Vec3.createVectorHelper(beamHeight, 0, .5 - beamWidth / 2 + crossBeamOffset),
				Vec3.createVectorHelper(1, 1 - beamHeight, .5 - beamWidth / 2 + crossBeamOffset),
				Vec3.createVectorHelper(1, 1 - beamHeight, .5 + beamWidth / 2 - crossBeamOffset))
				.SetUVFractions(0.0F, 0.0F, 1.0F, (float) (beamWidth - 2 * crossBeamOffset)));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(beamHeight - crossBeamHeight * Math.sqrt(2) / 2, crossBeamHeight * Math.sqrt(2) / 2, .5 - beamWidth / 2 + crossBeamOffset),
				Vec3.createVectorHelper(beamHeight - crossBeamHeight * Math.sqrt(2) / 2, crossBeamHeight * Math.sqrt(2) / 2, .5 + beamWidth / 2 - crossBeamOffset),
				Vec3.createVectorHelper(1 - crossBeamHeight * Math.sqrt(2) / 2, 1 - beamHeight + crossBeamHeight * Math.sqrt(2) / 2, .5 + beamWidth / 2 - crossBeamOffset),
				Vec3.createVectorHelper(1 - crossBeamHeight * Math.sqrt(2) / 2, 1 - beamHeight + crossBeamHeight * Math.sqrt(2) / 2, .5 - beamWidth / 2 + crossBeamOffset))
				.SetUVFractions(0.0F, 0.0F, 1.0F, (float) (beamWidth - 2 * crossBeamOffset)));
	}
}
