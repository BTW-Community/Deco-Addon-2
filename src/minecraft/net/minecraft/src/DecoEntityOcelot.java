package net.minecraft.src;

public class DecoEntityOcelot extends FCEntityOcelot {
	public DecoEntityOcelot(World world) {
		super(world);
	}

    /**
     * Initialize this creature.
     */
    public void initCreature()
    {
        if (this.worldObj.rand.nextInt(7) == 0)
        {
            for (int var1 = 0; var1 < 2; ++var1)
            {
                FCEntityOcelot var2 = new DecoEntityOcelot(this.worldObj);
                var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                var2.setGrowingAge(-var2.GetTicksForChildToGrow());
                this.worldObj.spawnEntityInWorld(var2);
            }
        }
    }

    public EntityOcelot spawnBabyAnimal(EntityAgeable var1)
    {
        FCEntityOcelot var2 = new DecoEntityOcelot(this.worldObj);

        if (this.isTamed())
        {
            var2.setOwner(this.getOwnerName());
            var2.setTamed(true);
            var2.setTameSkin(this.getTameSkin());
        }

        return var2;
    }

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		if (this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
		{
			int var1 = MathHelper.floor_double(this.posX);
			int var2 = MathHelper.floor_double(this.boundingBox.minY);
			int var3 = MathHelper.floor_double(this.posZ);

			if (var2 < 63)
			{
				return false;
			}

			int var4 = this.worldObj.getBlockId(var1, var2 - 1, var3);

			if (var4 == Block.leaves.blockID)
			{
				return true;
			}
		}

		return false;
	}
}