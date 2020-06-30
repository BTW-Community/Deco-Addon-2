package net.minecraft.src;

public class FCEntityChicken extends EntityChicken
{
    protected long m_lTimeToLayEgg = 0L;

    public FCEntityChicken(World var1)
    {
        super(var1);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.RemoveAllTasks();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
        this.tasks.addTask(2, new EntityAIMate(this, 0.25F));
        this.tasks.addTask(3, new FCEntityAIMultiTempt(this, 0.25F));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 0.28F));
        this.tasks.addTask(5, new FCEntityAIGraze(this));
        this.tasks.addTask(6, new FCEntityAIMoveToLooseFood(this, 0.25F));
        this.tasks.addTask(7, new FCEntityAIMoveToGraze(this, 0.25F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.renderDistanceWeight = 2.0D;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        this.timeUntilNextEgg = 6000;
        super.onLivingUpdate();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setLong("fcTimeToLayEgg", this.m_lTimeToLayEgg);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);

        if (var1.hasKey("fcTimeToLayEgg"))
        {
            this.m_lTimeToLayEgg = var1.getLong("fcTimeToLayEgg");
        }
        else
        {
            this.m_lTimeToLayEgg = 0L;
        }
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int var1, int var2, int var3, int var4) {}

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        if (!this.IsStarving())
        {
            int var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + var2);

            if (this.IsFamished())
            {
                var3 /= 2;
            }

            for (int var4 = 0; var4 < var3; ++var4)
            {
                this.dropItem(Item.feather.itemID, 1);
            }

            if (this.IsFullyFed() && !this.HasHeadCrabbedSquid())
            {
                if (this.isBurning())
                {
                    this.dropItem(FCBetterThanWolves.fcItemMeatBurned.itemID, 1);
                }
                else
                {
                    this.dropItem(Item.chickenRaw.itemID, 1);
                }
            }
        }
    }

    public FCEntityChicken spawnBabyAnimal(EntityAgeable var1)
    {
        return new FCEntityChicken(this.worldObj);
    }

    protected boolean IsReadyToEatBreedingItem()
    {
        return this.IsFullyFed() && this.getGrowingAge() == 0 && this.m_lTimeToLayEgg == 0L;
    }

    protected void OnEatBreedingItem()
    {
        long var1 = FCUtilsWorld.GetOverworldTimeServerOnly();
        this.m_lTimeToLayEgg = ((var1 + 12000L) / 24000L + 1L) * 24000L;
        this.m_lTimeToLayEgg += (long)(-1450 + this.rand.nextInt(600));
        this.worldObj.playSoundAtEntity(this, this.getDeathSound(), this.getSoundVolume(), this.rand.nextFloat() * 0.2F + 1.5F);
    }

    public boolean IsAffectedByMovementModifiers()
    {
        return false;
    }

    protected boolean GetCanCreatureTypeBePossessed()
    {
        return true;
    }

    protected void OnFullPossession()
    {
        this.worldObj.playAuxSFX(2243, MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 0);
        int var1 = this.rand.nextInt(3) + 2;

        for (int var2 = 0; var2 < var1; ++var2)
        {
            ItemStack var3 = new ItemStack(Item.feather.itemID, 1, 0);
            double var4 = this.posX + (this.worldObj.rand.nextDouble() - 0.5D) * 2.0D;
            double var6 = this.posY + 0.5D;
            double var8 = this.posZ + (this.worldObj.rand.nextDouble() - 0.5D) * 2.0D;
            EntityItem var10 = new EntityItem(this.worldObj, var4, var6, var8, var3);
            var10.motionX = (this.worldObj.rand.nextDouble() - 0.5D) * 0.5D;
            var10.motionY = 0.2D + this.worldObj.rand.nextDouble() * 0.3D;
            var10.motionZ = (this.worldObj.rand.nextDouble() - 0.5D) * 0.5D;
            var10.delayBeforeCanPickup = 10;
            this.worldObj.spawnEntityInWorld(var10);
        }

        this.AttemptToPossessNearbyCreatureOnDeath();
        this.setDead();
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)this.height * 1.2999999523162842D;
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack var1)
    {
        return var1.itemID == FCBetterThanWolves.fcItemChickenFeed.itemID;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return !this.IsStarving() ? "mob.chicken.say" : "mob.chicken.hurt";
    }

    public boolean IsSubjectToHunger()
    {
        return true;
    }

    protected int GetFoodValueMultiplier()
    {
        return 1;
    }

    public boolean ShouldNotifyBlockOnGraze()
    {
        return this.rand.nextInt(8) == 0;
    }

    public void PlayGrazeFX(int var1, int var2, int var3, int var4) {}

    public FCUtilsBlockPos GetGrazeBlockForPos()
    {
        FCUtilsBlockPos var1 = super.GetGrazeBlockForPos();
        return var1 != null && FCBlockGroundCover.IsGroundCoverRestingOnBlock(this.worldObj, var1.i, var1.j, var1.k) ? null : var1;
    }

    public int GetGrazeDuration()
    {
        return 20;
    }

    public boolean ShouldStayInPlaceToGraze()
    {
        return this.rand.nextInt(10) != 0 ? super.ShouldStayInPlaceToGraze() : false;
    }

    public boolean IsHungryEnoughToForceMoveToGraze()
    {
        return this.isChild() || !this.IsFullyFed() || this.m_iHungerCountdown + this.GetGrazeHungerGain() * 3 / 4 <= 24000;
    }

    protected int GetItemFoodValue(ItemStack var1)
    {
        return var1.getItem().GetChickenFoodValue(var1.getItemDamage()) * this.GetFoodValueMultiplier();
    }

    public void OnBecomeFamished()
    {
        super.OnBecomeFamished();
        this.m_lTimeToLayEgg = 0L;
    }

    protected void UpdateHungerState()
    {
        if (!this.isChild() && this.IsFullyFed() && this.m_lTimeToLayEgg > 0L && this.ValidateTimeToLayEgg() && FCUtilsWorld.GetOverworldTimeServerOnly() > this.m_lTimeToLayEgg)
        {
            this.playSound("mob.slime.attack", 1.0F, this.getSoundPitch());
            this.playSound(this.getDeathSound(), this.getSoundVolume(), (this.getSoundPitch() + 0.25F) * (this.getSoundPitch() + 0.25F));
            this.dropItem(Item.egg.itemID, 1);
            this.m_lTimeToLayEgg = 0L;
        }

        super.UpdateHungerState();
    }

    private boolean ValidateTimeToLayEgg()
    {
        long var1 = FCUtilsWorld.GetOverworldTimeServerOnly();
        long var3 = this.m_lTimeToLayEgg - var1;

        if (var3 > 48000L)
        {
            this.m_lTimeToLayEgg = 0L;
            return false;
        }
        else
        {
            return true;
        }
    }

    protected float GetGrazeHeadRotationMagnitudeDivisor()
    {
        return 3.0F;
    }

    protected float GetGrazeHeadRotationRateMultiplier()
    {
        return 14.35F;
    }
}
