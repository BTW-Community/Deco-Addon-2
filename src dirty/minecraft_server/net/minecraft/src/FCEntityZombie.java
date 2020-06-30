package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class FCEntityZombie extends EntityZombie
{
    public int m_iVillagerClass = -1;
    private IEntitySelector m_targetEntitySelector;

    public FCEntityZombie(World var1)
    {
        super(var1);
        this.getNavigator().setBreakDoors(false);
        this.tasks.RemoveAllTasksOfClass(EntityAIBreakDoor.class);
        this.tasks.RemoveAllTasksOfClass(EntityAIAttackOnCollide.class);
        this.tasks.RemoveAllTasksOfClass(EntityAIWander.class);
        this.tasks.addTask(1, new FCEntityAIZombieBreakBarricades(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.moveSpeed, false));
        this.tasks.addTask(3, new FCEntityAIZombieSecondaryAttack(this));
        this.tasks.addTask(6, new FCEntityAIWanderSimple(this, this.moveSpeed));
        this.targetTasks.RemoveAllTasksOfClass(EntityAINearestAttackableTarget.class);
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
        this.m_targetEntitySelector = new FCEntityZombieSecondaryTargetFilter(this);
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCreature.class, 16.0F, 0, false, false, this.m_targetEntitySelector));
    }

    protected int func_96121_ay()
    {
        return 16;
    }

    /**
     * This method returns a value to be applied directly to entity speed, this factor is less than 1 when a slowdown
     * potion effect is applied, more than 1 when a haste potion effect is applied and 2 for fleeing entities.
     */
    public float getSpeedModifier()
    {
        return this.isVillager() ? 1.5F : super.getSpeedModifier();
    }

    /**
     * Set whether this zombie is a villager.
     */
    public void setVillager(boolean var1)
    {
        super.setVillager(var1);
        this.getNavigator().setBreakDoors(var1);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (!this.isVillager())
        {
            this.CheckForCatchFireInSun();
        }

        this.EntityMobOnLivingUpdate();
    }

    public boolean attackEntityAsMob(Entity var1)
    {
        return this.MeleeAttack(var1);
    }

    protected void dropRareDrop(int var1) {}

    /**
     * Makes entity wear random armor based on difficulty
     */
    protected void addRandomArmor()
    {
        this.EntityLivingAddRandomArmor();

        if (this.rand.nextFloat() < 0.05F)
        {
            int var1 = this.rand.nextInt(3);

            if (var1 == 0)
            {
                this.setCurrentItemOrArmor(0, new ItemStack(Item.swordIron));
            }
            else
            {
                this.setCurrentItemOrArmor(0, new ItemStack(Item.shovelIron));
            }

            this.equipmentDropChances[0] = 0.99F;
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setInteger("fcVillagerClass", this.m_iVillagerClass);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);

        if (var1.hasKey("fcVillagerClass"))
        {
            this.m_iVillagerClass = var1.getInteger("fcVillagerClass");
        }
    }

    /**
     * This method gets called when the entity kills another one.
     */
    public void onKillEntity(EntityLiving var1)
    {
        if (this.rand.nextInt(4) != 0 && var1 instanceof FCEntityVillager)
        {
            FCEntityZombie var2 = new FCEntityZombie(this.worldObj);
            var2.func_82149_j(var1);
            this.worldObj.removeEntity(var1);
            var2.SetPersistent(true);
            var2.setVillager(true);
            var2.m_iVillagerClass = ((FCEntityVillager)var1).getProfession();

            if (var1.isChild())
            {
                var2.setChild(true);
            }

            this.worldObj.spawnEntityInWorld(var2);
            this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1016, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
        }
    }

    /**
     * Initialize this creature.
     */
    public void initCreature()
    {
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.15F);
        this.addRandomArmor();
        this.func_82162_bC();
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
        return false;
    }

    /**
     * Convert this zombie into a villager.
     */
    protected void convertToVillager()
    {
        FCEntityVillager var1 = new FCEntityVillager(this.worldObj);
        var1.func_82149_j(this);
        var1.initCreature();

        if (this.m_iVillagerClass >= 0)
        {
            var1.setProfession(this.m_iVillagerClass);
        }

        if (this.m_iVillagerClass == 0)
        {
            var1.SetDirtyPeasant(1);
        }

        var1.func_82187_q();

        if (this.isChild())
        {
            var1.setGrowingAge(-var1.GetTicksForChildToGrow());
        }

        this.worldObj.removeEntity(this);
        this.worldObj.spawnEntityInWorld(var1);
        var1.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1017, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
    }

    /**
     * Return the amount of time decremented from conversionTime every tick.
     */
    protected int getConversionTimeBoost()
    {
        return 1;
    }

    protected void ModSpecificOnLivingUpdate()
    {
        super.ModSpecificOnLivingUpdate();
        this.CheckForLooseFood();

        if (!this.worldObj.isRemote && this.isVillager() && !this.isDead && this.m_iVillagerClass < 0)
        {
            this.setVillager(false);
        }
    }

    public void CheckForScrollDrop()
    {
        if (this.rand.nextInt(1000) == 0)
        {
            ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.smite.effectId);
            this.entityDropItem(var1, 0.0F);
        }
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity var1, float var2)
    {
        if (var1 instanceof EntityAnimal)
        {
            if (this.attackTime <= 0 && var2 < 4.0F)
            {
                this.attackTime = 20;
                this.attackEntityAsMob(var1);
            }
        }
        else
        {
            super.attackEntity(var1, var2);
        }
    }

    protected void dropHead()
    {
        this.entityDropItem(new ItemStack(Item.skull.itemID, 1, 2), 0.0F);
    }

    public void SpawnerInitCreature()
    {
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.15F);
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch()
    {
        return !this.isChild() && this.isVillager() ? (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.7F : super.getSoundPitch();
    }

    public boolean GetCanBeHeadCrabbed(boolean var1)
    {
        return !var1 && this.riddenByEntity == null && this.isEntityAlive() && !this.isChild();
    }

    public void OnHeadCrabbedBySquid(FCEntitySquid var1)
    {
        this.playSound(this.getDeathSound(), this.getSoundVolume(), this.getSoundPitch());
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)this.height;
    }

    protected boolean IsWeightedByHeadCrab()
    {
        return false;
    }

    public Entity GetHeadCrabSharedAttackTarget()
    {
        return this.getAttackTarget();
    }

    public boolean IsImmuneToHeadCrabDamage()
    {
        return true;
    }

    private void CheckForLooseFood()
    {
        if (!this.worldObj.isRemote && !this.isLivingDead)
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

                    if (var6.DoZombiesConsume())
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

    public boolean AttemptToStartCure()
    {
        if (!this.isLivingDead && this.isVillager() && !this.isConverting())
        {
            this.startConversion(this.rand.nextInt(2401) + 3600);
            return true;
        }
        else
        {
            return false;
        }
    }
}
