package net.minecraft.src;

public class DecoEntityOcelot extends FCEntityOcelot {
	public DecoEntityOcelot(World world) {
		super(world);
	}

	@Override
	public boolean getCanSpawnHere() {
		if (this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox)) {
			int var1 = MathHelper.floor_double(this.posX);
			int var2 = MathHelper.floor_double(this.boundingBox.minY);
			int var3 = MathHelper.floor_double(this.posZ);

			if (var2 < 63) {
				return false;
			}

			int var4 = this.worldObj.getBlockId(var1, var2 - 1, var3);

			if (var4 == Block.leaves.blockID) {
				return true;
			}
		}

		return false;
	}
}