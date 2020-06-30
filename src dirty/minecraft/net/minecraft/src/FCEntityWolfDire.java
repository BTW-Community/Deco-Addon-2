package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class FCEntityWolfDire extends EntityCreature implements IAnimals
{
    private static final float m_fMoveSpeedAggressive = 0.45F;
    private static final float m_fMoveSpeedPassive = 0.3F;
    public int m_iHowlingCountdown = 0;
    public int m_iHeardHowlCountdown = 0;

    public FCEntityWolfDire(World var1)
    {
        super(var1);
        this.texture = "/btwmodtex/fcWolfDire.png";
        this.setSize(0.9F, 1.2F);
        this.moveSpeed = 0.45F;
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new FCEntityAIZombieBreakBarricades(this));
        this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, this.moveSpeed, true));
        this.tasks.addTask(3, new EntityAIRestrictSun(this));
        this.tasks.addTask(4, new EntityAIFleeSun(this, this.moveSpeed));
        this.tasks.addTask(5, new FCEntityAIWolfDireHowl(this));
        this.tasks.addTask(7, new FCEntityAIWanderSimple(this, 0.3F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 32.0F, 0, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, FCEntityVillager.class, 16.0F, 0, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, FCEntityChicken.class, 16.0F, 0, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, FCEntityCow.class, 16.0F, 0, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, FCEntityPig.class, 16.0F, 0, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, FCEntitySheep.class, 16.0F, 0, false));
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    public int getMaxHealth()
    {
        return 40;
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int var1, int var2, int var3, int var4)
    {
        this.playSound("mob.wolf.step", 0.15F, 1.0F);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.wolf.growl";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.wolf.growl";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 3.0F;
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch()
    {
        return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F + 0.55F;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return !this.worldObj.isRemote ? Item.rottenFlesh.itemID : -1;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        super.dropFewItems(var1, var2);
        this.dropItem(FCBetterThanWolves.fcItemBeastLiverRaw.itemID, 1);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        this.CheckForLooseFood();

        if (this.worldObj.isRemote)
        {
            this.m_iHowlingCountdown = Math.max(0, this.m_iHowlingCountdown - 1);
        }
        else
        {
            this.m_iHeardHowlCountdown = Math.max(0, this.m_iHeardHowlCountdown - 1);

            if (this.worldObj.isDaytime())
            {
                float var1 = this.getBrightness(1.0F);

                if (var1 > 0.5F && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
                {
                    this.setFire(8);
                }
            }
        }

        super.onLivingUpdate();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
    }

    /**
     * knocks back this entity
     */
    public void knockBack(Entity var1, int var2, double var3, double var5) {}

    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }

    public int GetMeleeAttackStrength(Entity var1)
    {
        return 6;
    }

    /**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
    public float getBlockPathWeight(int var1, int var2, int var3)
    {
        return 0.5F - this.worldObj.getLightBrightness(var1, var2, var3);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    /**
     * Gets the username of the entity.
     */
    public String getEntityName()
    {
        return "The Beast";
    }

    public float getTailRotation()
    {
        return 1.5393804F;
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

                    if (var6.DoZombiesConsume() || var6.itemID == Item.chickenRaw.itemID)
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

    public float GetHeadRotationPointOffset(float var1)
    {
        if (this.m_iHowlingCountdown > 0)
        {
            float var2 = 1.0F;

            if (this.m_iHowlingCountdown < 5)
            {
                var2 = (float)this.m_iHowlingCountdown / 5.0F;
            }
            else if (this.m_iHowlingCountdown > 70)
            {
                var2 = (float)(81 - this.m_iHowlingCountdown) / 10.0F;
            }

            return var2 * -0.5F;
        }
        else
        {
            return 0.0F;
        }
    }

    public float GetHeadRotation(float var1)
    {
        if (this.m_iHowlingCountdown > 0)
        {
            float var2 = 1.0F;

            if (this.m_iHowlingCountdown < 5)
            {
                var2 = (float)this.m_iHowlingCountdown / 5.0F;
            }
            else if (this.m_iHowlingCountdown > 70)
            {
                var2 = (float)(81 - this.m_iHowlingCountdown) / 10.0F;
            }

            return var2 * -((float)Math.PI / 5F);
        }
        else
        {
            return this.rotationPitch / (180F / (float)Math.PI);
        }
    }

    public void handleHealthUpdate(byte var1)
    {
        if (var1 == 10)
        {
            this.m_iHowlingCountdown = 80;
        }
        else
        {
            super.handleHealthUpdate(var1);
        }
    }
}
