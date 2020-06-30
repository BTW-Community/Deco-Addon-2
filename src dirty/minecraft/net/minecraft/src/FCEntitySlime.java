package net.minecraft.src;

public class FCEntitySlime extends EntitySlime
{
    private int m_iJumpCountdown = 0;

    public FCEntitySlime(World var1)
    {
        super(var1);
        this.m_iJumpCountdown = this.getJumpDelay();
    }

    protected void setSlimeSize(int var1)
    {
        this.dataWatcher.updateObject(16, new Byte((byte)var1));

        if (var1 == 1)
        {
            this.setSize(0.6F, 0.4F);
        }
        else
        {
            this.setSize(0.4F * (float)var1, 0.4F * (float)var1);
        }

        this.setPosition(this.posX, this.posY, this.posZ);
        this.setEntityHealth(this.getMaxHealth());
        this.experienceValue = var1;
    }

    /**
     * Returns true if the slime makes a sound when it lands after a jump (based upon the slime's size)
     */
    protected boolean makesSoundOnLand()
    {
        if (super.makesSoundOnLand() && !this.inWater)
        {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), this.getSoundPitch() * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F));
        }

        return false;
    }

    protected void updateEntityActionState()
    {
        ++this.entityAge;
        this.despawnEntity();
        EntityPlayer var1 = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);

        if (var1 != null)
        {
            this.faceEntity(var1, 10.0F, 20.0F);
        }

        this.isJumping = false;

        if (this.onGround)
        {
            --this.m_iJumpCountdown;

            if (this.m_iJumpCountdown <= 0)
            {
                this.PlayJumpSound();
                this.isJumping = true;
                this.m_iJumpCountdown = this.getJumpDelay();
                this.moveForward = (float)this.getSlimeSize();

                if (var1 != null)
                {
                    this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
                    this.m_iJumpCountdown /= 6;
                }
                else
                {
                    this.moveStrafing = 0.0F;

                    if (this.rand.nextInt(4) == 0)
                    {
                        this.rotationYaw = MathHelper.wrapAngleTo180_float((float)this.rand.nextInt(4) * 90.0F);
                    }
                }
            }
            else
            {
                this.moveStrafing = this.moveForward = 0.0F;
            }
        }
    }

    protected double MinDistFromPlayerForDespawn()
    {
        return 64.0D;
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void onCollideWithPlayer(EntityPlayer var1)
    {
        if (this.canDamagePlayer() && this.canEntityBeSeen(var1) && var1.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackStrength()))
        {
            this.attackTime = 20;
            this.playSound("mob.slime.attack", 1.0F, this.getSoundPitch() * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F));
        }
    }

    /**
     * Indicates weather the slime is able to damage the player (based upon the slime's size)
     */
    protected boolean canDamagePlayer()
    {
        return this.isEntityAlive() && this.attackTime <= 0;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return super.getCanSpawnHere() ? (this.posY < 40.0D ? this.CanSpawnOnBlockInSlimeChunk(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)) : true) : false;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.1F * (float)this.getSlimeSize();
    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }

    public void CheckForScrollDrop()
    {
        if (this.getSlimeSize() == 1 && this.rand.nextInt(1000) == 0)
        {
            ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.protection.effectId);
            this.entityDropItem(var1, 0.0F);
        }
    }

    public boolean IsAffectedByMovementModifiers()
    {
        return false;
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    protected void jump()
    {
        this.motionY = 0.5249999836087227D;
        this.isAirBorne = true;
    }

    public boolean CanSwim()
    {
        return false;
    }

    public float GetDefaultSlipperinessOnGround()
    {
        return 0.819F;
    }

    public float GetSlipperinessRelativeToBlock(int var1)
    {
        return this.GetDefaultSlipperinessOnGround();
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float var1) {}

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch()
    {
        int var1 = this.getSlimeSize();
        return var1 == 4 ? 0.75F : (var1 == 2 ? 1.0F : 1.25F);
    }

    protected EntitySlime createInstance()
    {
        return new FCEntitySlime(this.worldObj);
    }

    private boolean CanSpawnOnBlockInSlimeChunk(int var1, int var2, int var3)
    {
        int var4 = this.worldObj.getBlockId(var1, var2, var3);
        return var4 == Block.dirt.blockID || var4 == Block.stone.blockID || var4 == Block.grass.blockID || var4 == Block.gravel.blockID || var4 == Block.sand.blockID;
    }

    private void PlayJumpSound()
    {
        if (this.makesSoundOnJump())
        {
            if (!this.inWater)
            {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), this.getSoundPitch() * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F));
            }
            else
            {
                this.playSound("liquid.swim", 0.25F, this.getSoundPitch() * (1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F));
            }
        }
    }
}
