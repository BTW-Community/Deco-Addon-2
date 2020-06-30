package net.minecraft.src;

public class EntityXPOrb extends Entity
{
    public boolean m_bNotPlayerOwned;

    /**
     * A constantly increasing value that RenderXPOrb uses to control the colour shifting (Green / yellow)
     */
    public int xpColor;

    /** The age of the XP orb in ticks. */
    public int xpOrbAge;
    public int field_70532_c;

    /** The health of this XP orb. */
    private int xpOrbHealth;

    /** This is how much XP this orb has. */
    public int xpValue;

    /** The closest EntityPlayer to this orb. */
    private EntityPlayer closestPlayer;

    /** Threshold color for tracking players */
    private int xpTargetColor;

    public EntityXPOrb(World var1, double var2, double var4, double var6, int var8, boolean var9)
    {
        this(var1, var2, var4, var6, var8);
        this.m_bNotPlayerOwned = var9;
    }

    public EntityXPOrb(World par1World, double par2, double par4, double par6, int par8)
    {
        super(par1World);
        this.m_bNotPlayerOwned = false;
        this.xpOrbAge = 0;
        this.xpOrbHealth = 5;
        this.setSize(0.25F, 0.25F);
        this.yOffset = this.height / 2.0F;
        this.setPosition(par2, par4, par6);
        this.rotationYaw = (float)(Math.random() * 360.0D);
        this.motionX = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
        this.motionY = (double)((float)(Math.random() * 0.2D) * 2.0F);
        this.motionZ = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
        this.xpValue = par8;
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    public EntityXPOrb(World par1World)
    {
        super(par1World);
        this.m_bNotPlayerOwned = false;
        this.xpOrbAge = 0;
        this.xpOrbHealth = 5;
        this.setSize(0.25F, 0.25F);
        this.yOffset = this.height / 2.0F;
    }

    protected void entityInit() {}

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.field_70532_c > 0)
        {
            --this.field_70532_c;
        }

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.029999999329447746D;

        if (this.worldObj.getBlockMaterial(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) == Material.lava)
        {
            this.motionY = 0.20000000298023224D;
            this.motionX = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
            this.motionZ = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
            this.playSound("random.fizz", 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
        }

        if (!this.worldObj.isRemote)
        {
            this.pushOutOfBlocks(this.posX, (this.boundingBox.minY + this.boundingBox.maxY) / 2.0D, this.posZ);
        }

        double var1 = 8.0D;

        if (this.xpTargetColor < this.xpColor - 20 + this.entityId % 100)
        {
            if ((this.closestPlayer == null || this.closestPlayer.getDistanceSqToEntity(this) > var1 * var1) && !this.m_bNotPlayerOwned)
            {
                this.closestPlayer = this.worldObj.getClosestPlayerToEntity(this, var1);
            }

            this.xpTargetColor = this.xpColor;
        }

        if (this.closestPlayer != null)
        {
            double var3 = (this.closestPlayer.posX - this.posX) / var1;
            double var5 = (this.closestPlayer.posY + (double)this.closestPlayer.getEyeHeight() - this.posY) / var1;
            double var7 = (this.closestPlayer.posZ - this.posZ) / var1;
            double var9 = var3 * var3 + var5 * var5 + var7 * var7;

            if (var9 < 1.0D)
            {
                double var11 = Math.sqrt(var9);
                double var13 = 1.0D - var11;
                var13 *= var13;
                this.motionX += var3 / var11 * var13 * 0.1D;
                this.motionY += var5 / var11 * var13 * 0.1D;
                this.motionZ += var7 / var11 * var13 * 0.1D;
            }
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        float var15 = 0.98F;

        if (this.onGround)
        {
            var15 = 0.58800006F;
            int var4 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

            if (var4 > 0)
            {
                var15 = Block.blocksList[var4].slipperiness * 0.98F;
            }
        }

        this.motionX *= (double)var15;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= (double)var15;

        if (this.onGround)
        {
            this.motionY *= -0.8999999761581421D;
        }

        ++this.xpColor;
        ++this.xpOrbAge;

        if (this.xpOrbAge >= 6000)
        {
            this.setDead();
        }
    }

    /**
     * Returns if this entity is in water and will end up adding the waters velocity to the entity
     */
    public boolean handleWaterMovement()
    {
        return this.worldObj.handleMaterialAcceleration(this.boundingBox, Material.water, this);
    }

    /**
     * Will deal the specified amount of damage to the entity if the entity isn't immune to fire damage. Args:
     * amountDamage
     */
    protected void dealFireDamage(int par1)
    {
        this.attackEntityFrom(DamageSource.inFire, par1);
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
            this.setBeenAttacked();
            this.xpOrbHealth -= par2;

            if (this.xpOrbHealth <= 0)
            {
                this.setDead();
            }

            return false;
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setShort("Health", (short)((byte)this.xpOrbHealth));
        par1NBTTagCompound.setShort("Age", (short)this.xpOrbAge);
        par1NBTTagCompound.setShort("Value", (short)this.xpValue);
        par1NBTTagCompound.setBoolean("m_bNotPlayerOwned", this.m_bNotPlayerOwned);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.xpOrbHealth = par1NBTTagCompound.getShort("Health") & 255;
        this.xpOrbAge = par1NBTTagCompound.getShort("Age");
        this.xpValue = par1NBTTagCompound.getShort("Value");

        if (par1NBTTagCompound.hasKey("m_bNotPlayerOwned"))
        {
            this.m_bNotPlayerOwned = par1NBTTagCompound.getBoolean("m_bNotPlayerOwned");
        }
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
        if (!this.m_bNotPlayerOwned)
        {
            if (!this.worldObj.isRemote && this.field_70532_c == 0 && par1EntityPlayer.xpCooldown == 0)
            {
                par1EntityPlayer.xpCooldown = 2;
                this.playSound("random.orb", 0.1F, 0.5F * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.8F));
                par1EntityPlayer.onItemPickup(this, 1);
                par1EntityPlayer.addExperience(this.xpValue);
                this.setDead();
            }
        }
    }

    /**
     * Returns the XP value of this XP orb.
     */
    public int getXpValue()
    {
        return this.xpValue;
    }

    /**
     * Get a fragment of the maximum experience points value for the supplied value of experience points value.
     */
    public static int getXPSplit(int par0)
    {
        return par0 >= 2477 ? 2477 : (par0 >= 1237 ? 1237 : (par0 >= 617 ? 617 : (par0 >= 307 ? 307 : (par0 >= 149 ? 149 : (par0 >= 73 ? 73 : (par0 >= 37 ? 37 : (par0 >= 17 ? 17 : (par0 >= 7 ? 7 : (par0 >= 3 ? 3 : 1)))))))));
    }

    /**
     * If returns false, the item will not inflict any damage against entities.
     */
    public boolean canAttackWithItem()
    {
        return false;
    }

    /**
     * Checks for block collisions, and calls the associated onBlockCollided method for the collided block.
     */
    protected void doBlockCollisions()
    {
        int var1 = MathHelper.floor_double(this.boundingBox.minX + 0.001D);
        int var2 = MathHelper.floor_double(this.boundingBox.minY - 0.01D);
        int var3 = MathHelper.floor_double(this.boundingBox.minZ + 0.001D);
        int var4 = MathHelper.floor_double(this.boundingBox.maxX - 0.001D);
        int var5 = MathHelper.floor_double(this.boundingBox.maxY - 0.001D);
        int var6 = MathHelper.floor_double(this.boundingBox.maxZ - 0.001D);

        if (this.worldObj.checkChunksExist(var1, var2, var3, var4, var5, var6))
        {
            for (int var7 = var1; var7 <= var4; ++var7)
            {
                for (int var8 = var2; var8 <= var5; ++var8)
                {
                    for (int var9 = var3; var9 <= var6; ++var9)
                    {
                        int var10 = this.worldObj.getBlockId(var7, var8, var9);

                        if (var10 > 0)
                        {
                            Block.blocksList[var10].onEntityCollidedWithBlock(this.worldObj, var7, var8, var9, this);
                        }
                    }
                }
            }
        }
    }
}
