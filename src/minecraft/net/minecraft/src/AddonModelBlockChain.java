package net.minecraft.src;

public class AddonModelBlockChain extends FCModelBlock {
	@Override
	public void InitModel() {
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.5625, 0, .5625),
				Vec3.createVectorHelper(.5625, 1, .5625),
				Vec3.createVectorHelper(.4375, 1, .4375),
				Vec3.createVectorHelper(.4375, 0, .4375))
				.SetUVFractions(0.0F, 0.0F, .1875F, 1.0F));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.4375, 0, .4375),
				Vec3.createVectorHelper(.4375, 1, .4375),
				Vec3.createVectorHelper(.5625, 1, .5625),
				Vec3.createVectorHelper(.5625, 0, .5625))
				.SetUVFractions(0.0F, 0.0F, .1875F, 1.0F));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.5625, 0, .4375),
				Vec3.createVectorHelper(.5625, 1, .4375),
				Vec3.createVectorHelper(.4375, 1, .5625),
				Vec3.createVectorHelper(.4375, 0, .5625))
				.SetUVFractions(.1875F, 0.0F, .375F, 1.0F));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.4375, 0, .5625),
				Vec3.createVectorHelper(.4375, 1, .5625),
				Vec3.createVectorHelper(.5625, 1, .4375),
				Vec3.createVectorHelper(.5625, 0, .4375))
				.SetUVFractions(.1875F, 0.0F, .375F, 1.0F));
	}
}
