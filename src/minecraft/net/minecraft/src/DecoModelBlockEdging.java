package net.minecraft.src;

public class DecoModelBlockEdging extends FCModelBlock {
	/*
	@Override
	public void InitModel() {
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(0, 0, 0),
				Vec3.createVectorHelper(0, 1, 0),
				Vec3.createVectorHelper(1, 1, 0),
				Vec3.createVectorHelper(1, 0, 0))
				.SetUVFractions(1, 1, 0, 0));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(1, 0, 0),
				Vec3.createVectorHelper(1, 1, 0),
				Vec3.createVectorHelper(1, 1, 1),
				Vec3.createVectorHelper(1, 0, 1))
				.SetUVFractions(1, 1, 0, 0));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(1, 0, 1),
				Vec3.createVectorHelper(1, 1, 1),
				Vec3.createVectorHelper(.5, 1, 1),
				Vec3.createVectorHelper(.5, 0, 1))
				.SetUVFractions(1, 1, .5F, 0));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.5, 0, .5),
				Vec3.createVectorHelper(.5, 1, .5),
				Vec3.createVectorHelper(0, 1, .5),
				Vec3.createVectorHelper(0, 0, .5))
				.SetUVFractions(1, 1, .5F, 0));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(0, 0, .5),
				Vec3.createVectorHelper(0, 1, .5),
				Vec3.createVectorHelper(0, 1, 0),
				Vec3.createVectorHelper(0, 0, 0))
				.SetUVFractions(.5F, 1, 0, 0));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.5, 0, 1),
				Vec3.createVectorHelper(.5, 1, 1),
				Vec3.createVectorHelper(.5, 1, .5),
				Vec3.createVectorHelper(.5, 0, .5))
				.SetUVFractions(.5F, 1, 0, 0));

		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(1, 1, .5),
				Vec3.createVectorHelper(1, 1, 0),
				Vec3.createVectorHelper(0, 1, 0),
				Vec3.createVectorHelper(0, 1, .5))
				.SetUVFractions(1, .5F, 0, 0));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(1, 1, 1),
				Vec3.createVectorHelper(1, 1, .5),
				Vec3.createVectorHelper(.5, 1, .5),
				Vec3.createVectorHelper(.5, 1, 1))
				.SetUVFractions(.5F, .5F, 0, 0));

		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(0, 0, .5),
				Vec3.createVectorHelper(0, 0, 0),
				Vec3.createVectorHelper(1, 0, 0),
				Vec3.createVectorHelper(1, 0, .5))
				.SetUVFractions(1, .5F, 0, 0));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.5, 0, 1),
				Vec3.createVectorHelper(.5, 0, .5),
				Vec3.createVectorHelper(1, 0, .5),
				Vec3.createVectorHelper(1, 0, 1))
				.SetUVFractions(.5F, 1, 0, .5F));
	}
	*/
	
	public void InitModel() {
		this.AddBox(0, 0, 0, 1, 1, .5);
		this.AddBox(.5, 0, .5, 1, 1, 1);
	}
}
