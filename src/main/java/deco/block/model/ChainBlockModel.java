package deco.block.model;

import btw.block.model.BlockModel;
import btw.util.PrimitiveQuad;
import net.minecraft.src.Vec3;

public class ChainBlockModel extends BlockModel {
	@Override
	public void initModel() {
		this.addPrimitive(new PrimitiveQuad(
				Vec3.createVectorHelper(.625, 0, .625),
				Vec3.createVectorHelper(.625, 1, .625),
				Vec3.createVectorHelper(.375, 1, .375),
				Vec3.createVectorHelper(.375, 0, .375))
				.setUVFractions(0.0F, 0.0F, .4375F, 1.0F));
		this.addPrimitive(new PrimitiveQuad(
				Vec3.createVectorHelper(.375, 0, .375),
				Vec3.createVectorHelper(.375, 1, .375),
				Vec3.createVectorHelper(.625, 1, .625),
				Vec3.createVectorHelper(.625, 0, .625))
				.setUVFractions(0.0F, 0.0F, .4375F, 1.0F));
		this.addPrimitive(new PrimitiveQuad(
				Vec3.createVectorHelper(.625, 0, .375),
				Vec3.createVectorHelper(.625, 1, .375),
				Vec3.createVectorHelper(.375, 1, .625),
				Vec3.createVectorHelper(.375, 0, .625))
				.setUVFractions(.5625F, 0.0F, 1.0F, 1.0F));
		this.addPrimitive(new PrimitiveQuad(
				Vec3.createVectorHelper(.375, 0, .625),
				Vec3.createVectorHelper(.375, 1, .625),
				Vec3.createVectorHelper(.625, 1, .375),
				Vec3.createVectorHelper(.625, 0, .375))
				.setUVFractions(.5625F, 0.0F, 1.0F, 1.0F));
	}
}
