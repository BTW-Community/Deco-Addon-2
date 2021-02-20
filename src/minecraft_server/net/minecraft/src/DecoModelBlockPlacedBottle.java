package net.minecraft.src;

public class DecoModelBlockPlacedBottle extends FCModelBlock {
	@Override
	public void InitModel() {
		//Sides
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.34375, .375, .34375),
				Vec3.createVectorHelper(.34375, 0, .34375),
				Vec3.createVectorHelper(.34375, 0, .65625),
				Vec3.createVectorHelper(.34375, .375, .65625))
				.SetUVFractions(0.0F, .625F, .3125F, 1.0F));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.34375, .375, .65625),
				Vec3.createVectorHelper(.34375, 0, .65625),
				Vec3.createVectorHelper(.65625, 0, .65625),
				Vec3.createVectorHelper(.65625, .375, .65625))
				.SetUVFractions(0.0F, .625F, .3125F, 1.0F));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.65625, .375, .65625),
				Vec3.createVectorHelper(.65625, 0, .65625),
				Vec3.createVectorHelper(.65625, 0, .34375),
				Vec3.createVectorHelper(.65625, .375, .34375))
				.SetUVFractions(0.0F, .625F, .3125F, 1.0F));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.65625, .375, .34375),
				Vec3.createVectorHelper(.65625, 0, .34375),
				Vec3.createVectorHelper(.34375, 0, .34375),
				Vec3.createVectorHelper(.34375, .375, .34375))
				.SetUVFractions(0.0F, .625F, .3125F, 1.0F));
		
		//Top and bottom
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.34375, .375, .34375),
				Vec3.createVectorHelper(.34375, .375, .65625),
				Vec3.createVectorHelper(.65625, .375, .65625),
				Vec3.createVectorHelper(.65625, .375, .34375))
				.SetUVFractions(0.0F, .3125F, .3125F, .625F));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.65625, 0, .34375),
				Vec3.createVectorHelper(.65625, 0, .65625),
				Vec3.createVectorHelper(.34375, 0, .65625),
				Vec3.createVectorHelper(.34375, 0, .34375))
				.SetUVFractions(0.0F, .3125F, .3125F, .625F));

		//Neck
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.40625, .5, .40625),
				Vec3.createVectorHelper(.40625, .375, .40625),
				Vec3.createVectorHelper(.40625, .375, .59375),
				Vec3.createVectorHelper(.40625, .5, .59375))
				.SetUVFractions(.375F, .875F, .5625F, 1.0F));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.40625, .5, .59375),
				Vec3.createVectorHelper(.40625, .375, .59375),
				Vec3.createVectorHelper(.59375, .375, .59375),
				Vec3.createVectorHelper(.59375, .5, .59375))
				.SetUVFractions(.375F, .875F, .5625F, 1.0F));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.59375, .5, .59375),
				Vec3.createVectorHelper(.59375, .375, .59375),
				Vec3.createVectorHelper(.59375, .375, .40625),
				Vec3.createVectorHelper(.59375, .5, .40625))
				.SetUVFractions(.375F, .875F, .5625F, 1.0F));
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.59375, .5, .40625),
				Vec3.createVectorHelper(.59375, .375, .40625),
				Vec3.createVectorHelper(.40625, .375, .40625),
				Vec3.createVectorHelper(.40625, .5, .40625))
				.SetUVFractions(.375F, .875F, .5625F, 1.0F));
		
		//Cap
		this.AddPrimitive(new FCUtilsPrimitiveQuad(
				Vec3.createVectorHelper(.40625, .5, .40625),
				Vec3.createVectorHelper(.40625, .5, .59375),
				Vec3.createVectorHelper(.59375, .5, .59375),
				Vec3.createVectorHelper(.59375, .5, .40625))
				.SetUVFractions(.375F, .6875F, .5625F, .875F));
	}
}
