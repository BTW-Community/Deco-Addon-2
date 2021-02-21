package net.minecraft.src;

public class DecoModelBlockChain extends FCModelBlock {
	@Override
	public void InitModel() {
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.625, 0, .625),
				Vec3.createVectorHelper(.625, 1, .625),
				Vec3.createVectorHelper(.375, 1, .375),
				Vec3.createVectorHelper(.375, 0, .375))
				.SetUVFractions(0.0F, 0.0F, .4375F, 1.0F));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.375, 0, .375),
				Vec3.createVectorHelper(.375, 1, .375),
				Vec3.createVectorHelper(.625, 1, .625),
				Vec3.createVectorHelper(.625, 0, .625))
				.SetUVFractions(0.0F, 0.0F, .4375F, 1.0F));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.625, 0, .375),
				Vec3.createVectorHelper(.625, 1, .375),
				Vec3.createVectorHelper(.375, 1, .625),
				Vec3.createVectorHelper(.375, 0, .625))
				.SetUVFractions(.5625F, 0.0F, 1.0F, 1.0F));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.375, 0, .625),
				Vec3.createVectorHelper(.375, 1, .625),
				Vec3.createVectorHelper(.625, 1, .375),
				Vec3.createVectorHelper(.625, 0, .375))
				.SetUVFractions(.5625F, 0.0F, 1.0F, 1.0F));
	}
}
