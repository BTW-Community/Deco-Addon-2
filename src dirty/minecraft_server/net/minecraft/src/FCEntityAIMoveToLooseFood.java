package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class FCEntityAIMoveToLooseFood extends EntityAIBase
{
    protected EntityAnimal m_myAnimal;
    protected float m_fMoveSpeed;
    private EntityItem temptingItem = null;
    private static final int m_iUpdatesBetweenChecks = 20;
    private int m_iDelayBetweenChecksCount = 20;
    private boolean m_bFailedToPath = false;

    public FCEntityAIMoveToLooseFood(EntityAnimal var1, float var2)
    {
        this.m_myAnimal = var1;
        this.m_fMoveSpeed = var2;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        boolean var1 = false;

        if (this.m_iDelayBetweenChecksCount <= 0)
        {
            this.m_iDelayBetweenChecksCount = 20 + this.m_myAnimal.rand.nextInt(3) - 1;

            if (this.m_myAnimal.IsReadyToEatLooseFood())
            {
                List var2 = this.m_myAnimal.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getAABBPool().getAABB(this.m_myAnimal.posX - 10.0D, this.m_myAnimal.posY - 7.0D, this.m_myAnimal.posZ - 10.0D, this.m_myAnimal.posX + 10.0D, this.m_myAnimal.posY + 7.0D, this.m_myAnimal.posZ + 10.0D));

                if (!var2.isEmpty())
                {
                    double var3 = 0.0D;
                    this.temptingItem = null;
                    Iterator var5 = var2.iterator();

                    while (var5.hasNext())
                    {
                        EntityItem var6 = (EntityItem)var5.next();

                        if (var6.isEntityAlive() && this.m_myAnimal.IsReadyToEatLooseItem(var6.getEntityItem()))
                        {
                            double var7 = this.m_myAnimal.getDistanceSqToEntity(var6);

                            if (this.temptingItem == null || var7 < var3)
                            {
                                this.temptingItem = var6;
                                var3 = var7;
                                var1 = true;
                            }
                        }
                    }
                }
            }
        }
        else
        {
            --this.m_iDelayBetweenChecksCount;
        }

        return var1;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.m_bFailedToPath && this.temptingItem != null && this.temptingItem.isEntityAlive() && this.m_myAnimal.IsReadyToEatLooseItem(this.temptingItem.getEntityItem());
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.m_myAnimal.getLookHelper().setLookPositionWithEntity(this.temptingItem, 30.0F, (float)this.m_myAnimal.getVerticalFaceSpeed());

        if (this.IsWithinEatBox())
        {
            this.m_myAnimal.getNavigator().clearPathEntity();
        }
        else if (!this.m_myAnimal.getNavigator().TryMoveToEntity(this.temptingItem, this.m_fMoveSpeed))
        {
            this.m_bFailedToPath = true;
        }
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.temptingItem = null;
        this.m_bFailedToPath = false;
        this.m_myAnimal.getNavigator().clearPathEntity();
    }

    public boolean IsWithinEatBox()
    {
        AxisAlignedBB var1 = AxisAlignedBB.getAABBPool().getAABB(this.m_myAnimal.boundingBox.minX - 1.4500000476837158D, this.m_myAnimal.boundingBox.minY - 0.949999988079071D, this.m_myAnimal.boundingBox.minZ - 1.4500000476837158D, this.m_myAnimal.boundingBox.maxX + 1.4500000476837158D, this.m_myAnimal.boundingBox.maxY + 0.949999988079071D, this.m_myAnimal.boundingBox.maxZ + 1.4500000476837158D);
        return var1.intersectsWith(this.temptingItem.boundingBox);
    }
}
