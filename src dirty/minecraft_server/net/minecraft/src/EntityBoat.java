package net.minecraft.src;

import java.util.List;

public class EntityBoat extends Entity
{
    private boolean field_70279_a;
    private double speedMultiplier;
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;

    public EntityBoat(World par1World)
    {
        super(par1World);
        this.field_70279_a = true;
        this.speedMultiplier = 0.07D;
        this.preventEntitySpawning = true;
        this.setSize(1.5F, 0.6F);
        this.yOffset = this.height / 2.0F;
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected void entityInit()
    {
        this.dataWatcher.addObject(17, new Integer(0));
        this.dataWatcher.addObject(18, new Integer(1));
        this.dataWatcher.addObject(19, new Integer(0));
    }

    /**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
     * pushable on contact, like boats or minecarts.
     */
    public AxisAlignedBB getCollisionBox(Entity par1Entity)
    {
        return par1Entity.boundingBox;
    }

    /**
     * returns the bounding box for this entity
     */
    public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return true;
    }

    public EntityBoat(World par1World, double par2, double par4, double par6)
    {
        this(par1World);
        this.setPosition(par2, par4 + (double)this.yOffset, par6);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = par2;
        this.prevPosY = par4;
        this.prevPosZ = par6;
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)this.height * 0.0D - 0.30000001192092896D;
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
        else if (!this.worldObj.isRemote && !this.isDead)
        {
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + par2 * 10);
            this.setBeenAttacked();
            boolean var3 = par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)par1DamageSource.getEntity()).capabilities.isCreativeMode;

            if (var3 || this.getDamageTaken() > 40)
            {
                if (this.riddenByEntity != null)
                {
                    this.riddenByEntity.mountEntity(this);
                }

                if (!var3)
                {
                    this.dropItemWithOffset(Item.boat.itemID, 1, 0.0F);
                }

                this.setDead();
            }

            return true;
        }
        else
        {
            return true;
        }
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.getTimeSinceHit() > 0)
        {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }

        if (this.getDamageTaken() > 0)
        {
            this.setDamageTaken(this.getDamageTaken() - 1);
        }

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        byte var1 = 5;
        double var2 = 0.0D;

        for (int var4 = 0; var4 < var1; ++var4)
        {
            double var5 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(var4 + 0) / (double)var1 - 0.125D;
            double var7 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(var4 + 1) / (double)var1 - 0.125D;
            AxisAlignedBB var9 = AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, var5, this.boundingBox.minZ, this.boundingBox.maxX, var7, this.boundingBox.maxZ);

            if (this.worldObj.isAABBInMaterial(var9, Material.water))
            {
                var2 += 1.0D / (double)var1;
            }
        }

        if (var2 > 0.10000000149011612D)
        {
            this.fallDistance = 0.0F;
            this.extinguish();
        }

        double var26 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        double var6;
        double var8;

        if (var26 > 0.26249999999999996D)
        {
            var6 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D);
            var8 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D);

            for (int var10 = 0; (double)var10 < 1.0D + var26 * 60.0D; ++var10)
            {
                double var11 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
                double var13 = (double)(this.rand.nextInt(2) * 2 - 1) * 0.7D;
                double var15;
                double var17;

                if (this.rand.nextBoolean())
                {
                    var15 = this.posX - var6 * var11 * 0.8D + var8 * var13;
                    var17 = this.posZ - var8 * var11 * 0.8D - var6 * var13;
                    this.worldObj.spawnParticle("splash", var15, this.posY - 0.125D, var17, this.motionX, this.motionY, this.motionZ);
                }
                else
                {
                    var15 = this.posX + var6 + var8 * var11 * 0.7D;
                    var17 = this.posZ + var8 - var6 * var11 * 0.7D;
                    this.worldObj.spawnParticle("splash", var15, this.posY - 0.125D, var17, this.motionX, this.motionY, this.motionZ);
                }
            }
        }

        double var12;
        double var27;

        if (this.worldObj.isRemote && this.field_70279_a)
        {
            if (this.boatPosRotationIncrements > 0)
            {
                var6 = this.posX + (this.boatX - this.posX) / (double)this.boatPosRotationIncrements;
                var8 = this.posY + (this.boatY - this.posY) / (double)this.boatPosRotationIncrements;
                var12 = this.posZ + (this.boatZ - this.posZ) / (double)this.boatPosRotationIncrements;
                var27 = MathHelper.wrapAngleTo180_double(this.boatYaw - (double)this.rotationYaw);
                this.rotationYaw = (float)((double)this.rotationYaw + var27 / (double)this.boatPosRotationIncrements);
                this.rotationPitch = (float)((double)this.rotationPitch + (this.boatPitch - (double)this.rotationPitch) / (double)this.boatPosRotationIncrements);
                --this.boatPosRotationIncrements;
                this.setPosition(var6, var8, var12);
                this.setRotation(this.rotationYaw, this.rotationPitch);
            }
            else
            {
                var6 = this.posX + this.motionX;
                var8 = this.posY + this.motionY;
                var12 = this.posZ + this.motionZ;
                this.setPosition(var6, var8, var12);

                if (this.onGround)
                {
                    this.motionX *= 0.5D;
                    this.motionY *= 0.5D;
                    this.motionZ *= 0.5D;
                }

                this.motionX *= 0.9900000095367432D;
                this.motionY *= 0.949999988079071D;
                this.motionZ *= 0.9900000095367432D;
            }
        }
        else
        {
            if (var2 < 1.0D)
            {
                var6 = var2 * 2.0D - 1.0D;
                this.motionY += 0.03999999910593033D * var6;
            }
            else
            {
                if (this.motionY < 0.0D)
                {
                    this.motionY /= 2.0D;
                }

                this.motionY += 0.007000000216066837D;
            }

            double var14 = 0.35D;

            if (this.riddenByEntity != null)
            {
                var14 *= this.riddenByEntity.MovementModifierWhenRidingBoat();
                this.motionX += this.riddenByEntity.motionX * this.speedMultiplier;
                this.motionZ += this.riddenByEntity.motionZ * this.speedMultiplier;

                if (this.riddenByEntity.AppliesConstantForceWhenRidingBoat())
                {
                    this.motionX -= Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * var14 * 0.02D;
                    this.motionZ -= Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * var14 * 0.02D;
                }
            }

            var6 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            double var16;

            if (var6 > var14)
            {
                var16 = var14 / var6;

                if (var16 < 0.9D)
                {
                    var16 = 0.9D;
                }

                this.motionX *= var16;
                this.motionZ *= var16;
                var6 *= var16;
            }

            if (var6 > var26 && this.speedMultiplier < 0.35D)
            {
                this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D;

                if (this.speedMultiplier > 0.35D)
                {
                    this.speedMultiplier = 0.35D;
                }
            }
            else
            {
                this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D;

                if (this.speedMultiplier < 0.07D)
                {
                    this.speedMultiplier = 0.07D;
                }
            }

            if (this.onGround)
            {
                this.motionX *= 0.5D;
                this.motionY *= 0.5D;
                this.motionZ *= 0.5D;
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);

            if (this.isCollidedHorizontally && var26 > 0.2D)
            {
                this.BreakBoat();
            }
            else
            {
                this.motionX *= 0.9900000095367432D;
                this.motionY *= 0.949999988079071D;
                this.motionZ *= 0.9900000095367432D;
            }

            this.rotationPitch = 0.0F;
            var8 = (double)this.rotationYaw;
            var12 = this.prevPosX - this.posX;
            var27 = this.prevPosZ - this.posZ;

            if (var12 * var12 + var27 * var27 > 0.001D)
            {
                var8 = (double)((float)(Math.atan2(var27, var12) * 180.0D / Math.PI));
            }

            var16 = MathHelper.wrapAngleTo180_double(var8 - (double)this.rotationYaw);

            if (var16 > 20.0D)
            {
                var16 = 20.0D;
            }

            if (var16 < -20.0D)
            {
                var16 = -20.0D;
            }

            this.rotationYaw = (float)((double)this.rotationYaw + var16);
            this.setRotation(this.rotationYaw, this.rotationPitch);

            if (!this.worldObj.isRemote)
            {
                List var18 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
                int var19;

                if (var18 != null && !var18.isEmpty())
                {
                    for (var19 = 0; var19 < var18.size(); ++var19)
                    {
                        Entity var20 = (Entity)var18.get(var19);

                        if (var20 != this.riddenByEntity && var20.canBePushed() && var20 instanceof EntityBoat)
                        {
                            var20.applyEntityCollision(this);
                        }
                    }
                }

                for (var19 = 0; var19 < 4; ++var19)
                {
                    int var28 = MathHelper.floor_double(this.posX + ((double)(var19 % 2) - 0.5D) * 0.8D);
                    int var21 = MathHelper.floor_double(this.posZ + ((double)(var19 / 2) - 0.5D) * 0.8D);

                    for (int var22 = 0; var22 < 2; ++var22)
                    {
                        int var23 = MathHelper.floor_double(this.posY) + var22;
                        int var24 = this.worldObj.getBlockId(var28, var23, var21);
                        Block var25 = Block.blocksList[var24];

                        if (var25 != null && var25.IsGroundCover())
                        {
                            this.worldObj.setBlockToAir(var28, var23, var21);
                        }
                        else if (var24 == Block.waterlily.blockID)
                        {
                            this.worldObj.destroyBlock(var28, var23, var21, true);
                        }
                    }
                }

                if (this.riddenByEntity != null && this.riddenByEntity.isDead)
                {
                    this.riddenByEntity = null;
                }
            }
        }
    }

    public void updateRiderPosition()
    {
        if (this.riddenByEntity != null)
        {
            double var1 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
            double var3 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
            this.riddenByEntity.setPosition(this.posX + var1, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + var3);
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != par1EntityPlayer)
        {
            return true;
        }
        else
        {
            if (!this.worldObj.isRemote)
            {
                par1EntityPlayer.mountEntity(this);
            }

            return true;
        }
    }

    /**
     * Sets the damage taken from the last hit.
     */
    public void setDamageTaken(int par1)
    {
        this.dataWatcher.updateObject(19, Integer.valueOf(par1));
    }

    /**
     * Gets the damage taken from the last hit.
     */
    public int getDamageTaken()
    {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    /**
     * Sets the time to count down from since the last time entity was hit.
     */
    public void setTimeSinceHit(int par1)
    {
        this.dataWatcher.updateObject(17, Integer.valueOf(par1));
    }

    /**
     * Gets the time since the last hit.
     */
    public int getTimeSinceHit()
    {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    /**
     * Sets the forward direction of the entity.
     */
    public void setForwardDirection(int par1)
    {
        this.dataWatcher.updateObject(18, Integer.valueOf(par1));
    }

    /**
     * Gets the forward direction of the entity.
     */
    public int getForwardDirection()
    {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    public boolean CanCollideWithEntity(Entity var1)
    {
        return !var1.IsItemEntity();
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float var1)
    {
        super.fall(var1);

        if (var1 > 5.0F)
        {
            this.BreakBoat();
        }
    }

    public void BreakBoat()
    {
        if (!this.worldObj.isRemote && !this.isDead)
        {
            this.setDead();

            for (int var1 = 0; var1 < 4; ++var1)
            {
                this.dropItemWithOffset(Item.stick.itemID, 1, 0.0F);
                this.dropItemWithOffset(FCBetterThanWolves.fcItemSawDust.itemID, 1, 0.0F);
            }

            this.playSound("mob.zombie.woodbreak", 0.5F, 0.5F + this.rand.nextFloat() * 0.25F);
        }
    }
}
