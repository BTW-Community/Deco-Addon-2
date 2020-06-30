package net.minecraft.src;

public class FCEntityWolf extends EntityWolf
{
    private static final int m_iIsEngagedInPossessionAttemptDataWatcherID = 26;
    private static final float m_fMoveSpeedAggressive = 0.45F;
    private static final float m_fMoveSpeedPassive = 0.3F;
    public int m_iHowlingCountdown = 0;
    public int m_iHeardHowlCountdown = 0;
    public int m_iInfectionCountdown = -1;
    private static final int m_iMinimumInfectionTime = 12000;
    private static final int m_iInfectionTimeVariance = 12000;
    private float m_iPossessionHeadRotation = 0.0F;
    private boolean m_bIsDoingHeadSpin = false;
    private boolean m_bHasHeadSpunOnThisPossessionAttempt = false;
    private int m_iPossessionAttemptCountdown = 0;
    private static final int m_iChanceOfPossessionAttempt = 12000;
    private static final int m_iPossessionAttemptTime = 200;
    protected static final int m_iHungerCountVariance = 1200;

    public FCEntityWolf(World var1)
    {
        super(var1);
        this.moveSpeed = 0.45F;
        this.tasks.RemoveAllTasks();
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new FCEntityAIPanicOnHeadCrab(this, 0.45F));
        this.tasks.addTask(3, this.aiSit);
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(5, new EntityAIAttackOnCollide(this, 0.3F, true));
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 0.3F, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIMate(this, 0.3F));
        this.tasks.addTask(8, new FCEntityAIWolfHowl(this));
        this.tasks.addTask(8, new FCEntityAIWolfHowlSitting(this));
        this.tasks.addTask(9, new FCEntityAIMoveToLooseFood(this, 0.23F));
        this.tasks.addTask(10, new FCEntityAIWanderSimple(this, 0.3F));
        this.tasks.addTask(11, new EntityAIBeg(this, 8.0F));
        this.tasks.addTask(12, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(13, new EntityAILookIdle(this));
        this.targetTasks.RemoveAllTasks();
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new FCEntityAIWolfWildTargetIfStarvingOrHostile(this, FCEntityVillager.class, 16.0F, 0, false));
        this.targetTasks.addTask(4, new FCEntityAIWolfWildTargetIfStarvingOrHostile(this, EntityPlayer.class, 16.0F, 0, false));
        this.targetTasks.addTask(4, new FCEntityAIWolfWildTargetIfHungry(this, FCEntityChicken.class, 16.0F, 0, false));
        this.targetTasks.addTask(4, new FCEntityAIWolfWildTargetIfHungry(this, FCEntitySheep.class, 16.0F, 0, false));
        this.targetTasks.addTask(4, new FCEntityAIWolfWildTargetIfHungry(this, FCEntityPig.class, 16.0F, 0, false));
        this.targetTasks.addTask(4, new FCEntityAIWolfWildTargetIfStarving(this, FCEntityCow.class, 16.0F, 0, false));
    }

    /**
     * Sets the active target the Task system uses for tracking
     */
    public void setAttackTarget(EntityLiving var1)
    {
        this.EntityLivingSetAttackTarget(var1);
    }

    public int getMaxHealth()
    {
        return 20;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(26, new Byte((byte)0));
        this.ResetHungerCountdown();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setInteger("fcInfection", this.m_iInfectionCountdown);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);

        if (var1.hasKey("bIsFed"))
        {
            boolean var2 = var1.getBoolean("bIsFed");

            if (var2)
            {
                this.SetHungerLevel(0);
            }
            else
            {
                this.SetHungerLevel(1);
            }
        }

        if (var1.hasKey("fcInfection"))
        {
            this.m_iInfectionCountdown = var1.getInteger("fcInfection");
        }
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
        return this.IsWildAndHostile() ? "mob.wolf.growl" : (this.rand.nextInt(3) != 0 ? "mob.wolf.bark" : (this.isTamed() && (this.dataWatcher.getWatchableObjectInt(18) < 10 || !this.IsFullyFed()) ? (this.IsStarving() ? "mob.wolf.growl" : "mob.wolf.whine") : "mob.wolf.panting"));
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return !this.worldObj.isRemote ? (this.isBurning() ? FCBetterThanWolves.fcItemMeatBurned.itemID : FCBetterThanWolves.fcItemWolfRaw.itemID) : -1;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.worldObj.isRemote)
        {
            this.m_iHowlingCountdown = Math.max(0, this.m_iHowlingCountdown - 1);
        }
        else
        {
            this.m_iHeardHowlCountdown = Math.max(0, this.m_iHeardHowlCountdown - 1);

            if (this.m_iInfectionCountdown > 0)
            {
                --this.m_iInfectionCountdown;

                if (this.m_iInfectionCountdown <= 0)
                {
                    this.TransformToDire();
                    return;
                }
            }
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        if (!this.isEntityInvulnerable() && !this.worldObj.isRemote && !this.isTamed() && var1.getEntity() instanceof EntityPlayer)
        {
            this.setAngry(true);
        }

        return super.attackEntityFrom(var1, var2);
    }

    public int GetMeleeAttackStrength(Entity var1)
    {
        return 4;
    }

    public boolean attackEntityAsMob(Entity var1)
    {
        return this.MeleeAttack(var1);
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null)
        {
            if (var2.itemID == FCBetterThanWolves.fcItemWolfRaw.itemID || var2.itemID == FCBetterThanWolves.fcItemWolfCooked.itemID)
            {
                this.worldObj.playSoundAtEntity(this, "mob.wolf.growl", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                this.setAngry(true);
                this.setTamed(false);
                this.setOwner("");
                this.setAttackTarget(var1);
                return true;
            }

            if (this.AttemptUseStackOn(var1, var2))
            {
                if (!var1.capabilities.isCreativeMode)
                {
                    --var2.stackSize;

                    if (var2.stackSize <= 0)
                    {
                        var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
                    }
                }

                return true;
            }
        }

        if (this.EntityAnimalInteract(var1))
        {
            return true;
        }
        else
        {
            if (!this.worldObj.isRemote && this.isTamed() && var1.username.equalsIgnoreCase(this.getOwnerName()) && (var2 == null || !this.IsEdibleItem(var2)))
            {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.setPathToEntity((PathEntity)null);
            }

            return false;
        }
    }

    public boolean GetCanBeHeadCrabbed(boolean var1)
    {
        return !var1 && this.riddenByEntity == null && this.isEntityAlive() && !this.isChild();
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)this.height * 1.2D;
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack var1)
    {
        if (var1 != null && this.isTamed())
        {
            Item var2 = var1.getItem();

            if (var2.IsWolfFood())
            {
                int var3 = var1.itemID;

                if (var3 != Item.rottenFlesh.itemID && var3 != FCBetterThanWolves.fcItemChocolate.itemID)
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Sets whether this wolf is angry or not.
     */
    public void setAngry(boolean var1)
    {
        super.setAngry(var1);

        if (var1)
        {
            this.setSitting(false);
        }
    }

    public boolean IsReadyToEatLooseFood()
    {
        return !this.IsFullyFed();
    }

    public boolean IsReadyToEatLooseItem(ItemStack var1)
    {
        Item var2 = var1.getItem();
        return this.IsReadyToEatLooseFood() && var2.IsWolfFood() && (var2.itemID != Item.rottenFlesh.itemID || this.IsStarving());
    }

    protected boolean AttemptToEatLooseItem(ItemStack var1)
    {
        if (this.IsReadyToEatLooseItem(var1))
        {
            this.OnEat(var1.getItem());
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean IsEdibleItem(ItemStack var1)
    {
        return var1.getItem().IsWolfFood();
    }

    public boolean AttemptToBeHandFedItem(ItemStack var1)
    {
        if (this.isTamed() && (this.dataWatcher.getWatchableObjectInt(18) < 20 || !this.IsFullyFed()))
        {
            this.OnEat(var1.getItem());
            return true;
        }
        else
        {
            return false;
        }
    }

    public void OnNearbyPlayerBlockAddOrRemove(EntityPlayer var1) {}

    protected void OnNearbyPlayerStartles(EntityPlayer var1)
    {
        if (!this.isTamed() && this.getAttackTarget() == null)
        {
            this.setAttackTarget(var1);
        }
    }

    protected boolean GetCanCreatureTypeBePossessed()
    {
        return true;
    }

    protected void HandlePossession()
    {
        super.HandlePossession();

        if (this.IsFullyPossessed())
        {
            if (this.IsEngangedInPossessionAttempt())
            {
                if (!this.m_bHasHeadSpunOnThisPossessionAttempt)
                {
                    this.m_bHasHeadSpunOnThisPossessionAttempt = true;
                    this.m_bIsDoingHeadSpin = true;

                    if (!this.worldObj.isRemote)
                    {
                        this.playSound("portal.portal", 3.0F, this.rand.nextFloat() * 0.1F + 0.75F);
                    }
                }

                if (!this.worldObj.isRemote)
                {
                    --this.m_iPossessionAttemptCountdown;

                    if (this.m_iPossessionAttemptCountdown <= 0)
                    {
                        this.SetEngangedInPossessionAttempt(false);
                        this.AttemptToPossessNearbyCreature(16.0D, false);
                    }
                }
            }
            else
            {
                this.m_bHasHeadSpunOnThisPossessionAttempt = false;

                if (!this.worldObj.isRemote && this.rand.nextInt(12000) == 0)
                {
                    this.SetEngangedInPossessionAttempt(true);
                    this.m_iPossessionAttemptCountdown = 200;
                }
            }

            this.UpdateHeadSpin();
        }
    }

    public void OnNearbyAnimalAttacked(EntityAnimal var1, EntityLiving var2) {}

    public void NotifyOfWolfHowl(Entity var1)
    {
        if (!this.isLivingDead)
        {
            double var2 = this.posX - var1.posX;
            double var4 = this.posZ - var1.posZ;
            double var6 = var2 * var2 + var4 * var4;

            if (var6 < 102400.0D && this != var1)
            {
                this.m_iHeardHowlCountdown = 95;
            }
        }
    }

    public FCEntityWolf spawnBabyAnimal(EntityAgeable var1)
    {
        FCEntityWolf var2 = new FCEntityWolf(this.worldObj);
        String var3 = this.getOwnerName();

        if (var3 != null && var3.trim().length() > 0)
        {
            var2.setOwner(var3);
            var2.setTamed(true);
        }

        return var2;
    }

    public boolean IsSubjectToHunger()
    {
        return true;
    }

    protected void UpdateHungerState()
    {
        super.UpdateHungerState();
        this.UpdateShitState();
    }

    protected void OnStarvingCountExpired()
    {
        super.OnStarvingCountExpired();

        if (this.isEntityAlive() && !this.IsFullyPossessed())
        {
            this.setAngry(true);
            this.setTamed(false);
            this.setOwner("");
        }
    }

    protected void ResetHungerCountdown()
    {
        this.m_iHungerCountdown = 24000 + (this.rand.nextInt(2400) - 1200);
    }

    protected float GetHungerSpeedModifier()
    {
        return 1.0F;
    }

    protected boolean IsTooHungryToGrow()
    {
        return !this.IsFullyFed();
    }

    protected boolean IsTooHungryToHeal()
    {
        return true;
    }

    protected int GetItemFoodValue(ItemStack var1)
    {
        return 0;
    }

    public boolean IsEngangedInPossessionAttempt()
    {
        return this.dataWatcher.getWatchableObjectByte(26) > 0;
    }

    public void SetEngangedInPossessionAttempt(boolean var1)
    {
        Byte var2 = Byte.valueOf((byte)0);

        if (var1)
        {
            var2 = Byte.valueOf((byte)1);
        }

        this.dataWatcher.updateObject(26, Byte.valueOf(var2.byteValue()));
    }

    private void UpdateShitState()
    {
        if (this.IsFullyFed())
        {
            int var1 = 1;

            if (this.IsDarkEnoughToAffectShitting())
            {
                var1 *= 2;
            }

            if (this.worldObj.rand.nextInt(24000) < var1)
            {
                this.AttemptToShit();
            }
        }
    }

    private boolean AttemptUseStackOn(EntityPlayer var1, ItemStack var2)
    {
        if (this.isTamed())
        {
            if (var2.itemID == Item.dyePowder.itemID)
            {
                int var3 = BlockCloth.getBlockFromDye(var2.getItemDamage());

                if (var3 != this.getCollarColor())
                {
                    this.setCollarColor(var3);
                    return true;
                }
            }
            else if (var2.itemID == FCBetterThanWolves.fcItemDung.itemID)
            {
                byte var4 = 12;

                if (var4 != this.getCollarColor())
                {
                    this.setCollarColor(var4);

                    if (!this.worldObj.isRemote)
                    {
                        this.worldObj.playAuxSFX(2266, MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 0);
                    }

                    return true;
                }
            }
        }
        else if (var2.itemID == Item.bone.itemID && !this.IsWildAndHostile())
        {
            if (!this.worldObj.isRemote)
            {
                if (this.rand.nextInt(3) == 0)
                {
                    this.setTamed(true);
                    this.setPathToEntity((PathEntity)null);
                    this.setAttackTarget((EntityLiving)null);
                    this.aiSit.setSitting(true);
                    this.setEntityHealth(20);
                    this.setOwner(var1.username);
                    this.playTameEffect(true);
                    this.worldObj.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return false;
    }

    private void OnEat(Item var1)
    {
        this.heal(var1.GetWolfHealAmount());
        int var2 = this.GetHungerLevel();

        if (var2 > 0)
        {
            this.SetHungerLevel(var2 - 1);
        }

        this.ResetHungerCountdown();

        if (!this.worldObj.isRemote)
        {
            this.worldObj.playAuxSFX(2284, MathHelper.floor_double(this.posX), (int)(this.posY + (double)this.height), MathHelper.floor_double(this.posZ), 0);
        }

        if (var1.itemID == Item.rottenFlesh.itemID)
        {
            this.OnRottenFleshEaten();
        }
        else if (var1.itemID == FCBetterThanWolves.fcItemChocolate.itemID)
        {
            this.OnChocolateEaten();
        }
    }

    private void OnChocolateEaten()
    {
        if (!this.worldObj.isRemote)
        {
            this.addPotionEffect(new PotionEffect(Potion.wither.id, 800, 0));
            this.worldObj.setEntityState(this, (byte)11);
        }
    }

    private void OnRottenFleshEaten()
    {
        if (this.worldObj.getWorldInfo().getGameType() != EnumGameType.CREATIVE && this.m_iInfectionCountdown < 0)
        {
            this.m_iInfectionCountdown = 12000 + this.rand.nextInt(12000);
        }
    }

    public boolean IsDarkEnoughToAffectShitting()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);
        int var4 = this.worldObj.getBlockLightValue(var1, var2, var3);
        return var4 <= 5;
    }

    public boolean AttemptToShit()
    {
        float var1 = MathHelper.sin(this.rotationYawHead / 180.0F * (float)Math.PI);
        float var2 = -MathHelper.cos(this.rotationYawHead / 180.0F * (float)Math.PI);
        double var3 = this.posX + (double)var1;
        double var5 = this.posY + 0.25D;
        double var7 = this.posZ + (double)var2;
        int var9 = MathHelper.floor_double(var3);
        int var10 = MathHelper.floor_double(var5);
        int var11 = MathHelper.floor_double(var7);

        if (!this.IsPathToBlockOpenToShitting(var9, var10, var11))
        {
            return false;
        }
        else
        {
            EntityItem var12 = new EntityItem(this.worldObj, var3, var5, var7, new ItemStack(FCBetterThanWolves.fcItemDung));
            float var13 = 0.05F;
            var12.motionX = (double)(var1 * 10.0F * var13);
            var12.motionZ = (double)(var2 * 10.0F * var13);
            var12.motionY = (double)((float)this.worldObj.rand.nextGaussian() * var13 + 0.2F);
            var12.delayBeforeCanPickup = 10;
            this.worldObj.spawnEntityInWorld(var12);
            this.worldObj.playSoundAtEntity(this, "random.explode", 0.2F, 1.25F);
            this.worldObj.playSoundAtEntity(this, "mob.wolf.growl", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);

            for (int var14 = 0; var14 < 5; ++var14)
            {
                double var15 = this.posX + (double)(var1 * 0.5F) + this.worldObj.rand.nextDouble() * 0.25D;
                double var17 = this.posY + this.worldObj.rand.nextDouble() * 0.5D + 0.25D;
                double var19 = this.posZ + (double)(var2 * 0.5F) + this.worldObj.rand.nextDouble() * 0.25D;
                this.worldObj.spawnParticle("smoke", var15, var17, var19, 0.0D, 0.0D, 0.0D);
            }

            return true;
        }
    }

    private boolean IsPathToBlockOpenToShitting(int var1, int var2, int var3)
    {
        if (!this.IsBlockOpenToShitting(var1, var2, var3))
        {
            return false;
        }
        else
        {
            int var4 = MathHelper.floor_double(this.posX);
            int var5 = MathHelper.floor_double(this.posZ);
            int var6 = var1 - var4;
            int var7 = var3 - var5;
            return var6 == 0 || var7 == 0 || this.IsBlockOpenToShitting(var4, var2, var3) || this.IsBlockOpenToShitting(var1, var2, var5);
        }
    }

    private boolean IsBlockOpenToShitting(int var1, int var2, int var3)
    {
        Block var4 = Block.blocksList[this.worldObj.getBlockId(var1, var2, var3)];

        if (var4 != null && (var4 == Block.waterMoving || var4 == Block.waterStill || var4 == Block.lavaMoving || var4 == Block.lavaStill || var4 == Block.fire || var4.blockMaterial.isReplaceable() || var4 == FCBetterThanWolves.fcBlockDetectorLogic || var4 == FCBetterThanWolves.fcBlockDetectorGlowingLogic || var4 == FCBetterThanWolves.fcBlockFireStoked))
        {
            var4 = null;
        }

        return var4 == null;
    }

    private void TransformToDire()
    {
        if (this.worldObj.getWorldInfo().getGameType() != EnumGameType.CREATIVE)
        {
            int var1 = MathHelper.floor_double(this.posX);
            int var2 = MathHelper.floor_double(this.posY) + 1;
            int var3 = MathHelper.floor_double(this.posZ);
            this.worldObj.func_82739_e(2257, var1, var2, var3, 0);
            this.setDead();
            FCEntityWolfDire var4 = new FCEntityWolfDire(this.worldObj);
            var4.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            var4.renderYawOffset = this.renderYawOffset;
            this.worldObj.spawnEntityInWorld(var4);
        }
    }

    public boolean IsWildAndHostile()
    {
        if (!this.isTamed())
        {
            if (this.IsStarving() || this.isAngry())
            {
                return true;
            }

            int var1 = (int)(this.worldObj.worldInfo.getWorldTime() % 24000L);

            if (var1 > 13500 && var1 < 22500)
            {
                int var2 = this.worldObj.getMoonPhase();

                if (var2 == 0 && this.worldObj.worldInfo.getWorldTime() > 24000L)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean IsWildAndHungry()
    {
        return !this.isTamed() && !this.IsFullyFed();
    }

    public boolean IsWildAndStarving()
    {
        return !this.isTamed() && this.IsStarving();
    }

    public float GetGrazeHeadVerticalOffset(float var1)
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

            return !this.isSitting() ? var2 * -0.5F : var2 * -0.25F;
        }
        else
        {
            return 0.0F;
        }
    }

    public float GetGrazeHeadRotation(float var1)
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

    public boolean AreEyesGlowing()
    {
        return this.m_bIsDoingHeadSpin;
    }

    public float GetPossessionHeadRotation()
    {
        return this.m_iPossessionHeadRotation * 2.0F * (float)Math.PI;
    }

    private void UpdateHeadSpin()
    {
        if (this.m_bIsDoingHeadSpin)
        {
            this.m_iPossessionHeadRotation += 0.008F;

            if (this.m_iPossessionHeadRotation >= 1.0F)
            {
                this.m_iPossessionHeadRotation = 0.0F;
                this.m_bIsDoingHeadSpin = false;
            }
        }
    }
}
