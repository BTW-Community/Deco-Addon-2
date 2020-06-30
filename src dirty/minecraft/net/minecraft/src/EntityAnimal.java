package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public abstract class EntityAnimal extends EntityAgeable implements IAnimals
{
    /**
     * This is representation of a counter for reproduction progress. (Note that this is different from the inLove which
     * represent being in Love-Mode)
     */
    protected int breeding = 0;
    protected static final int m_iHungerLevelDataWatcherID = 21;
    protected static final int m_iInLoveDataWatcherID = 22;
    protected static final int m_iWearingBreedingHarnessDataWatcherID = 23;
    protected static final int m_iFullHungerCount = 24000;
    protected static final int m_iLevelUpHungerCount = 25500;
    protected static final int m_iMaxHealingCount = 24000;
    protected int m_iHungerCountdown = 24000;
    protected int m_iHealingCountdown = 24000;
    public static final int m_iBaseGrazeFoodValue = 200;
    protected static final int m_iDelayBetweenEatLoose = 10;
    protected static final int m_iDelayBetweenEatLooseVariance = 10;
    protected int m_iEatLooseCooldownCounter = 20;
    protected int m_iGrazeProgressCounter = 0;

    public EntityAnimal(World par1World)
    {
        super(par1World);
    }

    /**
     * main AI tick function, replaces updateEntityActionState
     */
    protected void updateAITick()
    {
        if (this.getGrowingAge() != 0)
        {
            this.resetInLove();
        }

        super.updateAITick();
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.getGrowingAge() != 0)
        {
            this.resetInLove();
        }

        if (this.isInLove())
        {
            this.setInLove(this.getInLove() - 1);
            String var1 = "heart";

            if (this.getInLove() % 10 == 0)
            {
                double var2 = this.rand.nextGaussian() * 0.02D;
                double var4 = this.rand.nextGaussian() * 0.02D;
                double var6 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle(var1, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, var2, var4, var6);
            }
        }
        else
        {
            this.breeding = 0;
        }
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity par1Entity, float par2)
    {
        if (par1Entity instanceof EntityPlayer)
        {
            if (par2 < 3.0F)
            {
                double var3 = par1Entity.posX - this.posX;
                double var5 = par1Entity.posZ - this.posZ;
                this.rotationYaw = (float)(Math.atan2(var5, var3) * 180.0D / Math.PI) - 90.0F;
                this.hasAttacked = true;
            }

            EntityPlayer var7 = (EntityPlayer)par1Entity;

            if (var7.getCurrentEquippedItem() == null || !this.isBreedingItem(var7.getCurrentEquippedItem()))
            {
                this.entityToAttack = null;
            }
        }
        else if (par1Entity instanceof EntityAnimal)
        {
            EntityAnimal var8 = (EntityAnimal)par1Entity;

            if (this.getGrowingAge() > 0 && var8.getGrowingAge() < 0)
            {
                if ((double)par2 < 2.5D)
                {
                    this.hasAttacked = true;
                }
            }
            else if (this.isInLove() && var8.isInLove())
            {
                if (var8.entityToAttack == null)
                {
                    var8.entityToAttack = this;
                }

                if (var8.entityToAttack == this && (double)par2 < 3.5D)
                {
                    this.setInLove(this.getInLove() + 1);
                    var8.setInLove(var8.getInLove() + 1);
                    ++this.breeding;

                    if (this.breeding % 4 == 0)
                    {
                        this.worldObj.spawnParticle("heart", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, 0.0D, 0.0D, 0.0D);
                    }

                    if (this.breeding == 60)
                    {
                        this.procreate((EntityAnimal)par1Entity);
                    }
                }
                else
                {
                    this.breeding = 0;
                }
            }
            else
            {
                this.breeding = 0;
                this.entityToAttack = null;
            }
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            this.fleeingTick = 60;
            this.entityToAttack = null;
            this.resetInLove();
            this.PanickNearbyAnimals(par1DamageSource);
            return super.attackEntityFrom(par1DamageSource, par2);
        }
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        if (this.fleeingTick > 0)
        {
            return null;
        }
        else
        {
            float var1 = 8.0F;

            if (this.isInLove())
            {
                double var13 = 0.0D;
                EntityAnimal var7 = null;
                List var8 = this.worldObj.getEntitiesWithinAABB(this.getClass(), this.boundingBox.expand((double)var1, (double)var1, (double)var1));

                for (int var9 = 0; var9 < var8.size(); ++var9)
                {
                    EntityAnimal var10 = (EntityAnimal)var8.get(var9);

                    if (var10 != this && var10.isInLove())
                    {
                        double var11 = this.getDistanceSqToEntity(var10);

                        if (var7 == null || var11 < var13)
                        {
                            var13 = var11;
                            var7 = var10;
                        }
                    }
                }

                return var7;
            }
            else
            {
                List var2;
                int var3;

                if (this.getGrowingAge() == 0)
                {
                    var2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand((double)var1, (double)var1, (double)var1));

                    for (var3 = 0; var3 < var2.size(); ++var3)
                    {
                        EntityPlayer var5 = (EntityPlayer)var2.get(var3);

                        if (var5.getCurrentEquippedItem() != null && this.isBreedingItem(var5.getCurrentEquippedItem()))
                        {
                            return var5;
                        }
                    }
                }
                else if (this.getGrowingAge() > 0)
                {
                    var2 = this.worldObj.getEntitiesWithinAABB(this.getClass(), this.boundingBox.expand((double)var1, (double)var1, (double)var1));

                    for (var3 = 0; var3 < var2.size(); ++var3)
                    {
                        EntityAnimal var4 = (EntityAnimal)var2.get(var3);

                        if (var4 != this && var4.getGrowingAge() < 0)
                        {
                            return var4;
                        }
                    }
                }

                return null;
            }
        }
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.boundingBox.minY);
        int var3 = MathHelper.floor_double(this.posZ);
        return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID && this.worldObj.getFullBlockLightValue(var1, var2, var3) > 8 && super.getCanSpawnHere();
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return 120;
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }

    /**
     * Get the experience points the entity currently has.
     */
    protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
    {
        return 1 + this.worldObj.rand.nextInt(3);
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return par1ItemStack.itemID == Item.wheat.itemID;
    }

    /**
     * Returns if the entity is currently in 'love mode'.
     */
    public boolean isInLove()
    {
        return this.getInLove() > 0;
    }

    public void resetInLove()
    {
        this.setInLove(0);
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    public boolean canMateWith(EntityAnimal par1EntityAnimal)
    {
        return par1EntityAnimal == this ? false : (par1EntityAnimal.getClass() != this.getClass() ? false : this.isInLove() && par1EntityAnimal.isInLove());
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(21, new Byte((byte)0));
        this.dataWatcher.addObject(22, new Integer(0));
        this.dataWatcher.addObject(23, Byte.valueOf((byte)0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("InLove", this.getInLove());
        par1NBTTagCompound.setByte("fcHungerLvl", (byte)this.GetHungerLevel());
        par1NBTTagCompound.setBoolean("BreedingHarness", this.getWearingBreedingHarness());
        par1NBTTagCompound.setInteger("fcHungerCnt", this.m_iHungerCountdown);
        par1NBTTagCompound.setInteger("fcHealCnt", this.m_iHealingCountdown);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setInLove(par1NBTTagCompound.getInteger("InLove"));

        if (par1NBTTagCompound.hasKey("BreedingHarness"))
        {
            this.setWearingBreedingHarness(par1NBTTagCompound.getBoolean("BreedingHarness"));
        }

        if (par1NBTTagCompound.hasKey("fcHungerLvl"))
        {
            this.SetHungerLevel(par1NBTTagCompound.getByte("fcHungerLvl"));
        }

        if (par1NBTTagCompound.hasKey("fcHungerCnt"))
        {
            this.m_iHungerCountdown = par1NBTTagCompound.getInteger("fcHungerCnt");
        }
        else
        {
            this.ResetHungerCountdown();
        }

        if (par1NBTTagCompound.hasKey("fcHealCnt"))
        {
            this.m_iHealingCountdown = par1NBTTagCompound.getInteger("fcHealCnt");
        }
        else
        {
            this.ResetHealingCountdown();
        }
    }

    protected void ModSpecificOnLivingUpdate()
    {
        super.ModSpecificOnLivingUpdate();

        if (!this.worldObj.isRemote)
        {
            if (this.isEntityAlive())
            {
                this.CheckForLooseFood();
                this.CheckForIntersectingBreedingHarnesses();
                this.UpdateHealing();
                this.UpdateHungerState();
            }
        }
        else if (this.m_iGrazeProgressCounter > 0)
        {
            --this.m_iGrazeProgressCounter;
        }

        if (this.isInLove() && this.entityToAttack != null && this.entityToAttack instanceof EntityAnimal)
        {
            EntityAnimal var1 = (EntityAnimal)this.entityToAttack;

            if (!var1.isInLove())
            {
                this.entityToAttack = null;
            }
        }
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    protected void jump()
    {
        if (this.isChild())
        {
            this.motionY = 0.21D;
            this.isAirBorne = true;
        }
        else
        {
            super.jump();
        }
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource var1)
    {
        super.onDeath(var1);

        if (!this.worldObj.isRemote && this.getWearingBreedingHarness())
        {
            this.dropItem(FCBetterThanWolves.fcItemBreedingHarness.itemID, 1);
        }
    }

    protected void updateEntityActionState()
    {
        super.updateEntityActionState();

        if (this.getWearingBreedingHarness())
        {
            this.moveStrafing = 0.0F;
            this.moveForward = 0.0F;
        }
    }

    public void CheckForScrollDrop() {}

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch()
    {
        float var1 = super.getSoundPitch();

        if (this.IsPossessed())
        {
            var1 *= 0.6F;
        }

        return var1;
    }

    public void setRevengeTarget(EntityLiving var1)
    {
        this.entityToAttack = var1;

        if (this.entityToAttack != null)
        {
            this.revengeTimer = 300;
        }
        else
        {
            this.revengeTimer = 0;
        }
    }

    /**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
    public float getBlockPathWeight(int par1, int par2, int par3)
    {
        return !this.CanGrazeOnBlock(par1, par2 - 1, par3) && !this.CanGrazeOnBlock(par1, par2, par3) ? this.worldObj.GetNaturalLightBrightness(par1, par2, par3) - 0.5F : 10.0F;
    }

    public boolean IsSecondaryTargetForSquid()
    {
        return true;
    }

    public void OnFlungBySquidTentacle(FCEntitySquid var1)
    {
        DamageSource var2 = DamageSource.causeMobDamage(var1);
        this.attackEntityFrom(var2, 0);
    }

    public void OnHeadCrabbedBySquid(FCEntitySquid var1)
    {
        DamageSource var2 = DamageSource.causeMobDamage(var1);
        this.attackEntityFrom(var2, 0);
    }

    protected void AttemptToPossessNearbyCreatureOnDeath()
    {
        this.AttemptToPossessNearbyCreature(16.0D, true);
    }

    /**
     * This method returns a value to be applied directly to entity speed, this factor is less than 1 when a slowdown
     * potion effect is applied, more than 1 when a haste potion effect is applied and 2 for fleeing entities.
     */
    public float getSpeedModifier()
    {
        return super.getSpeedModifier() * this.GetHungerSpeedModifier();
    }

    public boolean CanChildGrow()
    {
        return super.CanChildGrow() && !this.IsTooHungryToGrow();
    }

    public boolean CanLoveJuiceRegenerate()
    {
        return this.IsFullyFed();
    }

    protected int GetTicksForChildToGrow()
    {
        return 48000;
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        return this.EntityAnimalInteract(par1EntityPlayer);
    }

    /**
     * Initialize this creature.
     */
    public void initCreature()
    {
        this.InitHungerWithVariance();
    }

    public int getInLove()
    {
        return this.dataWatcher.getWatchableObjectInt(22);
    }

    public void setInLove(int var1)
    {
        this.dataWatcher.updateObject(22, Integer.valueOf(var1));
    }

    public boolean getWearingBreedingHarness()
    {
        return this.dataWatcher.getWatchableObjectByte(23) > 0;
    }

    public void setWearingBreedingHarness(boolean var1)
    {
        Byte var2 = Byte.valueOf((byte)0);

        if (var1)
        {
            var2 = Byte.valueOf((byte)1);
        }

        this.dataWatcher.updateObject(23, Byte.valueOf(var2.byteValue()));
    }

    public void CheckForIntersectingBreedingHarnesses()
    {
        if (this.getWearingBreedingHarness())
        {
            AxisAlignedBB var1 = this.boundingBox.copy();
            var1.contract(0.1D, 0.1D, 0.1D);
            List var2 = this.worldObj.getEntitiesWithinAABB(EntityAnimal.class, var1);

            if (!var2.isEmpty())
            {
                for (int var3 = 0; var3 < var2.size(); ++var3)
                {
                    EntityAnimal var4 = (EntityAnimal)var2.get(var3);

                    if (var4 != this && var4.getWearingBreedingHarness() && !var4.isLivingDead)
                    {
                        this.attackEntityFrom(DamageSource.inWall, 1);
                        break;
                    }
                }
            }
        }
    }

    public void PanickNearbyAnimals(DamageSource var1)
    {
        Entity var2 = var1.getEntity();

        if (var2 != null && var2 instanceof EntityLiving)
        {
            EntityLiving var3 = (EntityLiving)var2;
            List var4 = this.worldObj.getEntitiesWithinAABB(EntityAnimal.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D));
            Iterator var5 = var4.iterator();

            while (var5.hasNext())
            {
                EntityAnimal var6 = (EntityAnimal)var5.next();

                if (!var6.isLivingDead && var6 != this && var6 != var3)
                {
                    var6.OnNearbyAnimalAttacked(this, var3);
                }
            }
        }
    }

    public void OnNearbyAnimalAttacked(EntityAnimal var1, EntityLiving var2)
    {
        if (this.entityToAttack == null)
        {
            this.entityToAttack = var2;
            this.revengeTimer = 150;
        }
        else if (this.revengeTimer < 150)
        {
            this.revengeTimer = 150;
        }
    }

    public void OnNearbyFireStartAttempt(EntityPlayer var1)
    {
        this.OnNearbyPlayerStartles(var1);
    }

    public void OnNearbyPlayerBlockAddOrRemove(EntityPlayer var1)
    {
        this.OnNearbyPlayerStartles(var1);
    }

    protected void OnNearbyPlayerStartles(EntityPlayer var1)
    {
        if (this.entityToAttack == null)
        {
            this.entityToAttack = var1;
            this.revengeTimer = 150;
        }
        else if (this.revengeTimer < 150)
        {
            this.revengeTimer = 150;
        }
    }

    /**
     * Creates a baby animal according to the animal type of the target at the actual position and spawns 'love'
     * particles.
     */
    protected void procreate(EntityAnimal par1EntityAnimal)
    {
        double var2 = this.posX;
        double var4 = this.posY;
        double var6 = this.posZ;

        if (this.getWearingBreedingHarness())
        {
            var2 = (this.posX + par1EntityAnimal.posX) / 2.0D;
            var4 = (this.posY + par1EntityAnimal.posY) / 2.0D;
            var6 = (this.posZ + par1EntityAnimal.posZ) / 2.0D;
        }

        this.GiveBirthAtTargetLocation(par1EntityAnimal, var2, var4, var6);
        this.ResetMatingStateOfBothParents(par1EntityAnimal);
        this.SpawnBirthHeartParticles();
        this.worldObj.playSoundAtEntity(this, this.getDeathSound(), this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        this.worldObj.playAuxSFX(2222, MathHelper.floor_double(var2), MathHelper.floor_double(var4), MathHelper.floor_double(var6), 0);
    }

    protected void GiveBirthAtTargetLocation(EntityAnimal var1, double var2, double var4, double var6)
    {
        EntityAgeable var8 = this.createChild(var1);

        if (var8 != null)
        {
            var8.setGrowingAge(-this.GetTicksForChildToGrow());
            var8.setLocationAndAngles(var2, var4, var6, this.rotationYaw, this.rotationPitch);
            this.worldObj.spawnEntityInWorld(var8);
        }
    }

    protected void ResetMatingStateOfBothParents(EntityAnimal var1)
    {
        this.setGrowingAge(this.GetTicksToRegenerateLoveJuice());
        var1.setGrowingAge(var1.GetTicksToRegenerateLoveJuice());
        this.resetInLove();
        this.breeding = 0;
        this.entityToAttack = null;
        var1.resetInLove();
        var1.entityToAttack = null;
        var1.breeding = 0;
    }

    protected void SpawnBirthHeartParticles()
    {
        for (int var1 = 0; var1 < 7; ++var1)
        {
            double var2 = this.rand.nextGaussian() * 0.02D;
            double var4 = this.rand.nextGaussian() * 0.02D;
            double var6 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle("heart", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, var2, var4, var6);
        }
    }

    public void InitHungerWithVariance()
    {
        if (this.IsSubjectToHunger())
        {
            this.m_iHungerCountdown = 24000 - this.rand.nextInt(this.GetGrazeHungerGain());
        }
    }

    protected int GetHungerLevel()
    {
        return this.dataWatcher.getWatchableObjectByte(21);
    }

    protected void SetHungerLevel(int var1)
    {
        this.dataWatcher.updateObject(21, Byte.valueOf((byte)var1));
    }

    protected boolean IsFullyFed()
    {
        return this.GetHungerLevel() == 0;
    }

    protected boolean IsFamished()
    {
        return this.GetHungerLevel() == 1;
    }

    protected boolean IsStarving()
    {
        return this.GetHungerLevel() >= 2;
    }

    protected void OnBecomeFamished()
    {
        this.SetHungerLevel(1);
    }

    public void OnBecomeStarving()
    {
        this.SetHungerLevel(2);
    }

    protected void OnStarvingCountExpired()
    {
        this.attackEntityFrom(DamageSource.starve, 5);
    }

    public boolean IsSubjectToHunger()
    {
        return false;
    }

    protected void UpdateHungerState()
    {
        if (this.IsSubjectToHunger())
        {
            if (!this.isChild())
            {
                --this.m_iHungerCountdown;
            }
            else
            {
                this.m_iHungerCountdown -= 2;
            }

            if (this.m_iHungerCountdown <= 0)
            {
                if (!this.isChild())
                {
                    if (this.IsFullyFed())
                    {
                        this.OnBecomeFamished();
                    }
                    else if (this.IsFamished())
                    {
                        this.OnBecomeStarving();
                    }
                    else
                    {
                        this.OnStarvingCountExpired();
                    }

                    this.ResetHungerCountdown();
                }
                else
                {
                    this.attackEntityFrom(DamageSource.starve, 1);
                }
            }
        }
    }

    protected void ResetHungerCountdown()
    {
        this.m_iHungerCountdown = 24000;
    }

    protected void AddToHungerCount(int var1)
    {
        this.m_iHungerCountdown += var1;

        if (this.m_iHungerCountdown > 25500)
        {
            int var2 = this.GetHungerLevel();

            if (var2 > 0)
            {
                this.m_iHungerCountdown -= 24000;
                this.SetHungerLevel(var2 - 1);
            }
        }
    }

    protected int GetGrazeHungerGain()
    {
        return 200 * this.GetFoodValueMultiplier();
    }

    protected int GetFoodValueMultiplier()
    {
        return 2;
    }

    public void OnGrazeBlock(int var1, int var2, int var3)
    {
        this.AddToHungerCount(this.GetGrazeHungerGain());
    }

    public boolean ShouldNotifyBlockOnGraze()
    {
        return true;
    }

    public void PlayGrazeFX(int var1, int var2, int var3, int var4)
    {
        this.worldObj.playAuxSFX(2001, var1, var2, var3, var4);
    }

    public int GetGrazeDuration()
    {
        return 40;
    }

    public boolean IsHungryEnoughToGraze()
    {
        return !this.IsFullyFed() || this.m_iHungerCountdown + this.GetGrazeHungerGain() <= 24000;
    }

    public boolean IsHungryEnoughToForceMoveToGraze()
    {
        return this.isChild() || !this.IsFullyFed() || this.m_iHungerCountdown < 12000;
    }

    protected boolean IsTooHungryToGrow()
    {
        return !this.IsFullyFed() || this.m_iHungerCountdown < 18000;
    }

    protected boolean IsTooHungryToHeal()
    {
        return !this.IsFullyFed() || this.m_iHungerCountdown < 18000;
    }

    public boolean CanGrazeMycelium()
    {
        return false;
    }

    public boolean GetDisruptsEarthOnGraze()
    {
        return false;
    }

    public boolean CanGrazeOnRoughVegetation()
    {
        return false;
    }

    public FCUtilsBlockPos GetGrazeBlockForPos()
    {
        FCUtilsBlockPos var1 = new FCUtilsBlockPos(MathHelper.floor_double(this.posX), (int)this.boundingBox.minY, MathHelper.floor_double(this.posZ));

        if (this.CanGrazeOnBlock(var1.i, var1.j, var1.k))
        {
            return var1;
        }
        else
        {
            --var1.j;
            return this.CanGrazeOnBlock(var1.i, var1.j, var1.k) ? var1 : null;
        }
    }

    public boolean ShouldStayInPlaceToGraze()
    {
        return this.GetGrazeBlockForPos() != null;
    }

    public boolean CanGrazeOnBlock(int var1, int var2, int var3)
    {
        Block var4 = Block.blocksList[this.worldObj.getBlockId(var1, var2, var3)];
        return var4 != null ? var4.CanBeGrazedOn(this.worldObj, var1, var2, var3, this) : false;
    }

    protected float GetHungerSpeedModifier()
    {
        return this.IsStarving() ? 0.5F : (this.IsFamished() ? 0.75F : 1.0F);
    }

    public boolean IsTemptingItem(ItemStack var1)
    {
        return this.GetItemFoodValue(var1) > 0 || this.isBreedingItem(var1) && this.IsReadyToEatBreedingItem();
    }

    public boolean IsEdibleItem(ItemStack var1)
    {
        return this.isBreedingItem(var1) || this.GetItemFoodValue(var1) > 0;
    }

    protected boolean IsHungryEnoughToEatLooseFood()
    {
        return !this.IsFullyFed() || this.m_iHungerCountdown <= 24000;
    }

    protected boolean IsReadyToEatBreedingItem()
    {
        return this.IsFullyFed() && this.getGrowingAge() == 0 && !this.isInLove();
    }

    protected int GetItemFoodValue(ItemStack var1)
    {
        return var1.getItem().GetHerbivoreFoodValue(var1.getItemDamage()) * this.GetFoodValueMultiplier();
    }

    protected boolean AttemptToEatItemForBreeding(ItemStack var1)
    {
        if (this.isBreedingItem(var1) && this.IsReadyToEatBreedingItem())
        {
            this.OnEatBreedingItem();
            return true;
        }
        else
        {
            return false;
        }
    }

    protected void OnEatBreedingItem()
    {
        this.setInLove(600);
        this.entityToAttack = null;

        for (int var1 = 0; var1 < 7; ++var1)
        {
            this.worldObj.spawnParticle("heart", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F - this.width), this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F - this.width), this.rand.nextGaussian() * 0.02D, this.rand.nextGaussian() * 0.02D, this.rand.nextGaussian() * 0.02D);
        }
    }

    protected boolean AttemptToEatItem(ItemStack var1)
    {
        int var2 = this.GetItemFoodValue(var1);

        if (!this.AttemptToEatItemForBreeding(var1) && (var2 <= 0 || !this.IsHungryEnoughToEatLooseFood()))
        {
            return false;
        }
        else
        {
            this.AddToHungerCount(var2);
            this.worldObj.setEntityState(this, (byte)10);
            this.worldObj.playAuxSFX(2283, MathHelper.floor_double(this.posX), (int)(this.posY + (double)this.height), MathHelper.floor_double(this.posZ), 0);
            return true;
        }
    }

    public boolean AttemptToBeHandFedItem(ItemStack var1)
    {
        return this.AttemptToEatItem(var1);
    }

    protected boolean AttemptToEatLooseItem(ItemStack var1)
    {
        return this.AttemptToEatItem(var1);
    }

    public boolean IsReadyToEatLooseFood()
    {
        return this.IsHungryEnoughToEatLooseFood() || this.IsReadyToEatBreedingItem();
    }

    public boolean IsReadyToEatLooseItem(ItemStack var1)
    {
        return this.GetItemFoodValue(var1) > 0 && this.IsHungryEnoughToEatLooseFood() || this.isBreedingItem(var1) && this.IsReadyToEatBreedingItem();
    }

    protected void CheckForLooseFood()
    {
        if (this.m_iEatLooseCooldownCounter > 0)
        {
            --this.m_iEatLooseCooldownCounter;
        }
        else if (this.IsReadyToEatLooseFood())
        {
            List var1 = this.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX - 1.5D, this.boundingBox.minY - 1.0D, this.boundingBox.minZ - 1.5D, this.boundingBox.maxX + 1.5D, this.boundingBox.maxY + 1.0D, this.boundingBox.maxZ + 1.5D));

            if (!var1.isEmpty())
            {
                Iterator var2 = var1.iterator();

                while (var2.hasNext())
                {
                    EntityItem var3 = (EntityItem)var2.next();

                    if (var3.delayBeforeCanPickup == 0 && var3.isEntityAlive())
                    {
                        ItemStack var4 = var3.getEntityItem();

                        if (this.AttemptToEatLooseItem(var3.getEntityItem()))
                        {
                            --var4.stackSize;

                            if (var4.stackSize <= 0)
                            {
                                var3.setDead();
                            }
                            else
                            {
                                var3.delayBeforeCanPickup = 2;
                            }

                            this.m_iEatLooseCooldownCounter = 10 + this.rand.nextInt(11);
                            break;
                        }
                    }
                }
            }
        }
    }

    public boolean EntityAnimalInteract(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null && this.IsEdibleItem(var2))
        {
            if (!this.worldObj.isRemote && this.AttemptToBeHandFedItem(var2))
            {
                --var2.stackSize;

                if (var2.stackSize <= 0)
                {
                    var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
                }
            }

            return true;
        }
        else
        {
            return super.interact(var1);
        }
    }

    protected int GetTicksToRegenerateLoveJuice()
    {
        return 24000;
    }

    public float GetGrazeHeadVerticalOffset(float var1)
    {
        int var2 = this.GetGrazeDuration();
        return !this.getWearingBreedingHarness() && this.m_iGrazeProgressCounter > 0 ? (this.m_iGrazeProgressCounter >= 4 && this.m_iGrazeProgressCounter <= var2 - 4 ? 1.0F : (this.m_iGrazeProgressCounter < 4 ? ((float)this.m_iGrazeProgressCounter - var1) / 4.0F : -((float)(this.m_iGrazeProgressCounter - var2) - var1) / 4.0F)) : 0.0F;
    }

    public float GetGrazeHeadRotation(float var1)
    {
        int var2 = this.GetGrazeDuration();

        if (!this.getWearingBreedingHarness() && this.m_iGrazeProgressCounter > 0)
        {
            if (this.m_iGrazeProgressCounter > 4 && this.m_iGrazeProgressCounter <= var2 - 4)
            {
                float var3 = ((float)(this.m_iGrazeProgressCounter - 4) - var1) / (float)(var2 - 8);
                return (float)Math.PI / this.GetGrazeHeadRotationMagnitudeDivisor() + ((float)Math.PI * 7F / 100F) * MathHelper.sin(var3 * this.GetGrazeHeadRotationRateMultiplier());
            }
            else
            {
                return (float)Math.PI / this.GetGrazeHeadRotationMagnitudeDivisor();
            }
        }
        else
        {
            return this.rotationPitch / (180F / (float)Math.PI);
        }
    }

    protected float GetGrazeHeadRotationMagnitudeDivisor()
    {
        return 5.0F;
    }

    protected float GetGrazeHeadRotationRateMultiplier()
    {
        return 28.7F;
    }

    protected void UpdateHealing()
    {
        if (this.IsSubjectToHunger() && !this.isChild())
        {
            if (this.IsTooHungryToHeal())
            {
                this.ResetHealingCountdown();
            }
            else
            {
                --this.m_iHealingCountdown;

                if (this.m_iHealingCountdown <= 0)
                {
                    this.heal(1);
                    this.ResetHealingCountdown();
                }
            }
        }
    }

    protected void ResetHealingCountdown()
    {
        this.m_iHealingCountdown = 24000;
    }

    public void handleHealthUpdate(byte var1)
    {
        if (var1 == 10)
        {
            this.m_iGrazeProgressCounter = this.GetGrazeDuration();
        }
        else
        {
            super.handleHealthUpdate(var1);
        }
    }
}
