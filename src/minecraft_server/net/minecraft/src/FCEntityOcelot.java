package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class FCEntityOcelot extends EntityOcelot
{
    private static final int m_iTabbySkinID = 22;

    public FCEntityOcelot(World var1)
    {
        super(var1);
        this.tasks.RemoveAllTasksOfClass(EntityAIWander.class);
        this.targetTasks.RemoveAllTasksOfClass(EntityAITargetNonTamed.class);
        this.tasks.addTask(10, new FCEntityAIWanderSimple(this, 0.23F));
        this.targetTasks.addTask(1, new EntityAITargetNonTamed(this, FCEntityChicken.class, 14.0F, 750, false));
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
        boolean var2 = this.isTamed();
        boolean var3 = super.interact(var1);

        if (!this.worldObj.isRemote && !var2 && this.isTamed() && this.worldObj.rand.nextInt(4) == 0)
        {
            this.setTameSkin(22);
        }

        return var3;
    }

    protected void CheckForLooseFood()
    {
        if (!this.worldObj.isRemote && this.isEntityAlive())
        {
            boolean var1 = false;
            List var2 = this.worldObj.getEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(2.5D, 1.0D, 2.5D));
            Iterator var3 = var2.iterator();

            while (var3.hasNext())
            {
                EntityItem var4 = (EntityItem)var3.next();

                if (var4.delayBeforeCanPickup == 0 && !var4.isDead)
                {
                    ItemStack var5 = var4.getEntityItem();
                    Item var6 = var5.getItem();

                    if (var6.itemID == Item.chickenRaw.itemID || var6.itemID == Item.fishRaw.itemID)
                    {
                        var4.setDead();
                        var1 = true;
                    }
                }
            }

            if (var1)
            {
                this.worldObj.playAuxSFX(2226, MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 0);
            }
        }
    }

    public void OnNearbyAnimalAttacked(EntityAnimal var1, EntityLiving var2) {}

    public void OnNearbyPlayerStartles(EntityPlayer var1) {}

    public boolean IsAffectedByMovementModifiers()
    {
        return false;
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
                FCEntityOcelot var2 = new FCEntityOcelot(this.worldObj);
                var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                var2.setGrowingAge(-var2.GetTicksForChildToGrow());
                this.worldObj.spawnEntityInWorld(var2);
            }
        }
    }

    public FCEntityOcelot spawnBabyAnimal(EntityAgeable var1)
    {
        FCEntityOcelot var2 = new FCEntityOcelot(this.worldObj);

        if (this.isTamed())
        {
            var2.setOwner(this.getOwnerName());
            var2.setTamed(true);
            var2.setTameSkin(this.getTameSkin());
        }

        return var2;
    }

    protected int GetItemFoodValue(ItemStack var1)
    {
        return 0;
    }

    protected boolean IsTooHungryToHeal()
    {
        return true;
    }

    public int GetMeleeAttackStrength(Entity var1)
    {
        return 3;
    }

    public boolean attackEntityAsMob(Entity var1)
    {
        return this.MeleeAttack(var1);
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
