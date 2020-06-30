package net.minecraft.src;

public class FCEntityCreeper extends EntityCreeper
{
    private static final int m_iNeuteredStateDataWatcherID = 25;
    private boolean m_bDeterminedToExplode = false;

    public FCEntityCreeper(World var1)
    {
        super(var1);
        this.tasks.RemoveAllTasksOfClass(EntityAICreeperSwell.class);
        this.tasks.RemoveAllTasksOfClass(EntityAIWander.class);
        this.tasks.addTask(2, new FCEntityAICreeperSwell(this));
        this.tasks.addTask(5, new FCEntityAIWanderSimple(this, 0.2F));
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float var1)
    {
        this.EntityLivingFall(var1);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(25, new Byte((byte)0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setByte("fcNeuteredState", (byte)this.GetNeuteredState());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);

        if (var1.hasKey("fcNeuteredState"))
        {
            this.SetNeuteredState(var1.getByte("fcNeuteredState"));
        }
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource var1)
    {
        if (this.GetNeuteredState() == 0)
        {
            super.onDeath(var1);
        }
        else
        {
            this.EntityLivingOnDeath(var1);
        }
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return FCBetterThanWolves.fcItemNitre.itemID;
    }

    protected void dropHead()
    {
        this.entityDropItem(new ItemStack(Item.skull.itemID, 1, 4), 0.0F);
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        super.dropFewItems(var1, var2);

        if (this.GetNeuteredState() == 0 && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + var2) > 0))
        {
            this.dropItem(FCBetterThanWolves.fcItemCreeperOysters.itemID, 1);
        }
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null && var2.itemID == Item.shears.itemID && this.GetNeuteredState() == 0)
        {
            if (!this.worldObj.isRemote)
            {
                this.SetNeuteredState(1);
                EntityItem var3 = this.entityDropItem(new ItemStack(FCBetterThanWolves.fcItemCreeperOysters, 1), 0.25F);
                var3.motionY += (double)(this.rand.nextFloat() * 0.025F);
                var3.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
                var3.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
                int var4 = MathHelper.floor_double(this.posX);
                int var5 = MathHelper.floor_double(this.posY);
                int var6 = MathHelper.floor_double(this.posZ);
                this.worldObj.playAuxSFX(2258, var4, var5, var6, 0);
            }

            var2.damageItem(10, var1);

            if (var2.stackSize <= 0)
            {
                var1.inventory.mainInventory[var1.inventory.currentItem] = null;
            }

            return true;
        }
        else
        {
            return super.interact(var1);
        }
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return 120;
    }

    /**
     * Plays living's sound at its position
     */
    public void playLivingSound()
    {
        if (this.GetNeuteredState() > 0)
        {
            String var1 = this.getLivingSound();

            if (var1 != null)
            {
                this.playSound(var1, 0.25F, this.getSoundPitch() + 0.25F);
            }
        }
        else
        {
            super.playLivingSound();
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return this.GetNeuteredState() > 0 ? "mob.creeper.say" : super.getLivingSound();
    }

    public void OnKickedByCow(FCEntityCow var1)
    {
        this.m_bDeterminedToExplode = true;
    }

    public void CheckForScrollDrop()
    {
        if (this.rand.nextInt(1000) == 0)
        {
            ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.blastProtection.effectId);
            this.entityDropItem(var1, 0.0F);
        }
    }

    public void OnStruckByLightning(FCEntityLightningBolt var1)
    {
        this.dataWatcher.updateObject(17, Byte.valueOf((byte)1));
    }

    public boolean GetIsDeterminedToExplode()
    {
        return this.m_bDeterminedToExplode;
    }

    public int GetNeuteredState()
    {
        return this.dataWatcher.getWatchableObjectByte(25);
    }

    public void SetNeuteredState(int var1)
    {
        this.dataWatcher.updateObject(25, Byte.valueOf((byte)var1));
    }
}
