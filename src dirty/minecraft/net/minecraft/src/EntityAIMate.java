package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class EntityAIMate extends EntityAIBase
{
    protected EntityAnimal theAnimal;
    World theWorld;
    protected EntityAnimal targetMate;

    /**
     * Delay preventing a baby from spawning immediately when two mate-able animals find each other.
     */
    int spawnBabyDelay = 0;

    /** The speed the creature moves at during mating behavior. */
    float moveSpeed;

    public EntityAIMate(EntityAnimal par1EntityAnimal, float par2)
    {
        this.theAnimal = par1EntityAnimal;
        this.theWorld = par1EntityAnimal.worldObj;
        this.moveSpeed = par2;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.theAnimal.isInLove())
        {
            return false;
        }
        else
        {
            this.targetMate = this.getNearbyMate();
            return this.targetMate != null;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.theAnimal.isInLove() ? false : this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60;
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.targetMate = null;
        this.spawnBabyDelay = 0;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
        this.theAnimal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
        ++this.spawnBabyDelay;

        if (this.spawnBabyDelay >= 60 && this.theAnimal.getDistanceSqToEntity(this.targetMate) < 9.0D)
        {
            this.theAnimal.procreate(this.targetMate);
        }
    }

    /**
     * Loops through nearby animals and finds another animal of the same type that can be mated with. Returns the first
     * valid mate found.
     */
    private EntityAnimal getNearbyMate()
    {
        float var1 = 8.0F;
        List var2 = this.theWorld.getEntitiesWithinAABB(this.theAnimal.getClass(), this.theAnimal.boundingBox.expand((double)var1, (double)var1, (double)var1));
        double var3 = Double.MAX_VALUE;
        EntityAnimal var5 = null;
        Iterator var6 = var2.iterator();

        while (var6.hasNext())
        {
            EntityAnimal var7 = (EntityAnimal)var6.next();

            if (this.theAnimal.canMateWith(var7) && this.theAnimal.getDistanceSqToEntity(var7) < var3)
            {
                var5 = var7;
                var3 = this.theAnimal.getDistanceSqToEntity(var7);
            }
        }

        return var5;
    }
}
