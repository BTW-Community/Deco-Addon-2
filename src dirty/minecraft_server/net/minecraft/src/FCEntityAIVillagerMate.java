package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class FCEntityAIVillagerMate extends EntityAIBase
{
    static final double m_dDistanceToCheckForMate = 8.0D;
    private FCEntityVillager m_villager;
    private FCEntityVillager m_mate;
    private World m_world;
    private int m_iSpawnBabyDelay = 0;
    private int m_iThrustDelay = 0;

    public FCEntityAIVillagerMate(FCEntityVillager var1)
    {
        this.m_villager = var1;
        this.m_world = var1.worldObj;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.m_villager.GetInLove() <= 0)
        {
            return false;
        }
        else
        {
            this.m_mate = this.getNearbyMate();
            return this.m_mate != null;
        }
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.m_mate = null;
        this.m_iSpawnBabyDelay = 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.m_mate != null && this.m_mate.isEntityAlive() && this.m_mate.GetInLove() > 0 && this.m_iSpawnBabyDelay < 100;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.m_villager.getLookHelper().setLookPositionWithEntity(this.m_mate, 10.0F, 30.0F);

        if (this.m_villager.getDistanceSqToEntity(this.m_mate) > 4.0D)
        {
            this.m_villager.getNavigator().tryMoveToEntityLiving(this.m_mate, 0.25F);
            this.m_iSpawnBabyDelay = 0;
            this.m_iThrustDelay = this.m_villager.rand.nextInt(5) + 15;
        }
        else
        {
            ++this.m_iSpawnBabyDelay;

            if (this.m_iSpawnBabyDelay == 100)
            {
                this.giveBirth();
            }
            else
            {
                --this.m_iThrustDelay;

                if (this.m_iThrustDelay <= 0)
                {
                    this.m_world.playSoundAtEntity(this.m_villager, "random.classic_hurt", 1.0F + this.m_villager.rand.nextFloat() * 0.25F, this.m_villager.getSoundPitch() * 2.0F);
                    this.m_iThrustDelay = this.m_villager.rand.nextInt(5) + 15;

                    if (this.m_villager.onGround)
                    {
                        this.m_villager.jump();
                    }
                }
            }
        }
    }

    private void giveBirth()
    {
        FCEntityVillager var1 = this.m_villager.func_90012_b(this.m_mate);
        int var2 = this.m_villager.getProfession();

        if (var1.rand.nextInt(2) == 0)
        {
            var1.setProfession(this.m_mate.getProfession());
        }

        if (var2 != 0 && var1.rand.nextInt(4) == 0)
        {
            switch (var2)
            {
                case 1:
                    var2 = 2;
                    break;

                case 2:
                    var2 = 1;
                    break;

                case 3:
                    var2 = 4;
                    break;

                case 4:
                    var2 = 3;
            }
        }

        var1.setProfession(var2);
        this.m_mate.setGrowingAge(6000);
        this.m_villager.setGrowingAge(6000);
        this.m_mate.SetInLove(0);
        this.m_villager.SetInLove(0);
        var1.setGrowingAge(-var1.GetTicksForChildToGrow());
        var1.setLocationAndAngles(this.m_villager.posX, this.m_villager.posY, this.m_villager.posZ, 0.0F, 0.0F);
        this.m_world.spawnEntityInWorld(var1);
        this.m_world.setEntityState(var1, (byte)12);
        this.m_world.playAuxSFX(2222, MathHelper.floor_double(var1.posX), MathHelper.floor_double(var1.posY), MathHelper.floor_double(var1.posZ), 0);
    }

    private FCEntityVillager getNearbyMate()
    {
        List var1 = this.m_world.getEntitiesWithinAABB(FCEntityVillager.class, this.m_villager.boundingBox.expand(8.0D, 8.0D, 8.0D));
        Iterator var2 = var1.iterator();
        Object var3 = null;
        FCEntityVillager var4;

        do
        {
            if (!var2.hasNext())
            {
                return null;
            }

            var4 = (FCEntityVillager)var2.next();
        }
        while (!this.CanMateWith(var4));

        return var4;
    }

    private boolean CanMateWith(FCEntityVillager var1)
    {
        return var1 != this.m_villager && var1.GetInLove() > 0 && !var1.isLivingDead;
    }
}
