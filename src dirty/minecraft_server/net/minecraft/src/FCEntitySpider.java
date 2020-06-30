package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class FCEntitySpider extends EntitySpider
{
    private static final double m_dSpiderAttackRange = 16.0D;
    private static final int m_iTimeBetweenWebs = 24000;
    protected int m_iTimeToNextWeb = 0;

    public FCEntitySpider(World var1)
    {
        super(var1);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setInteger("timeToWeb", this.m_iTimeToNextWeb);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);

        if (var1.hasKey("timeToWeb"))
        {
            this.m_iTimeToNextWeb = var1.getInteger("timeToWeb");
        }
    }

    /**
     * Initialize this creature.
     */
    public void initCreature()
    {
        if (this.worldObj.rand.nextInt(100) == 0)
        {
            FCEntitySkeleton var1 = new FCEntitySkeleton(this.worldObj);
            var1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            var1.initCreature();
            this.worldObj.spawnEntityInWorld(var1);
            var1.mountEntity(this);
        }
    }

    public void SpawnerInitCreature()
    {
        this.m_iTimeToNextWeb = 24000;
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        Object var1 = null;

        if (!this.DoesLightAffectAggessiveness() || this.getBrightness(1.0F) < 0.5F)
        {
            var1 = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
        }

        if (var1 == null)
        {
            List var2 = this.worldObj.getEntitiesWithinAABB(FCEntityChicken.class, this.boundingBox.expand(16.0D, 4.0D, 16.0D));
            Iterator var3 = var2.iterator();
            double var4 = 257.0D;

            while (var3.hasNext())
            {
                FCEntityChicken var6 = (FCEntityChicken)var3.next();

                if (!var6.isLivingDead)
                {
                    double var7 = this.posX - var6.posX;
                    double var9 = this.posY - var6.posY;
                    double var11 = this.posZ - var6.posZ;
                    double var13 = var7 * var7 + var9 * var9 + var11 * var11;

                    if (var13 < var4)
                    {
                        var1 = var6;
                        var4 = var13;
                    }
                }
            }
        }

        return (Entity)var1;
    }

    public void setRevengeTarget(EntityLiving var1)
    {
        this.entityLivingToAttack = var1;

        if (this.entityLivingToAttack != null)
        {
            this.revengeTimer = 200;
        }
        else
        {
            this.revengeTimer = 0;
        }
    }

    protected boolean ShouldContinueAttacking(float var1)
    {
        return this.revengeTimer > 0 || !this.DoesLightAffectAggessiveness() || this.rand.nextInt(600) != 0 || this.getBrightness(1.0F) <= 0.5F || !(this.entityToAttack instanceof EntityPlayer) || this.canEntityBeSeen(this.entityToAttack);
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity var1, float var2)
    {
        if (var2 < 2.0F)
        {
            if (var1 instanceof EntityAnimal)
            {
                if (this.attackTime <= 0)
                {
                    this.attackTime = 20;
                    this.attackEntityAsMob(var1);
                }
            }
            else
            {
                this.EntityMobAttackEntity(var1, var2);
            }
        }
        else if (var2 < 6.0F)
        {
            if (this.onGround && this.rand.nextInt(10) == 0)
            {
                double var3 = var1.posX - this.posX;
                double var5 = var1.posZ - this.posZ;
                float var7 = MathHelper.sqrt_double(var3 * var3 + var5 * var5);
                this.motionX = var3 / (double)var7 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                this.motionZ = var5 / (double)var7 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                this.motionY = 0.4000000059604645D;
            }
        }
        else if (var2 < 10.0F && this.HasWeb() && !this.IsEntityInWeb(var1) && this.rand.nextInt(10) == 0 && !(var1 instanceof FCEntitySpider))
        {
            this.SpitWeb(var1);
        }
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        if (this.HasWeb())
        {
            this.EntityLivingDropFewItems(var1, var2);
        }

        if (this.DropsSpiderEyes() && this.rand.nextInt(16) - (var2 << 1) <= 0)
        {
            this.dropItem(Item.spiderEye.itemID, 1);
        }
    }

    public boolean isPotionApplicable(PotionEffect var1)
    {
        return true;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        this.CheckForLooseFood();
        this.CheckForSpiderSkeletonMounting();

        if (this.m_iTimeToNextWeb > 0)
        {
            --this.m_iTimeToNextWeb;
        }

        super.onLivingUpdate();
    }

    public void CheckForScrollDrop()
    {
        if (this.rand.nextInt(1000) == 0)
        {
            ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.baneOfArthropods.effectId);
            this.entityDropItem(var1, 0.0F);
        }
    }

    public boolean IsAffectedByMovementModifiers()
    {
        return false;
    }

    protected boolean DropsSpiderEyes()
    {
        return true;
    }

    public boolean HasWeb()
    {
        return this.m_iTimeToNextWeb <= 0;
    }

    public boolean IsEntityInWeb(Entity var1)
    {
        return this.worldObj.isMaterialInBB(var1.boundingBox, Material.web);
    }

    private void SpitWeb(Entity var1)
    {
        if (!this.worldObj.isRemote)
        {
            this.worldObj.spawnEntityInWorld(new FCEntitySpiderWeb(this.worldObj, this, var1));
            this.m_iTimeToNextWeb = 24000;
        }
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

                if (var4.delayBeforeCanPickup == 0 && var4.isEntityAlive())
                {
                    ItemStack var5 = var4.getEntityItem();
                    Item var6 = var5.getItem();

                    if (var6.itemID == Item.chickenRaw.itemID || var6.itemID == FCBetterThanWolves.fcItemRawMysteryMeat.itemID)
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

    public boolean DoesLightAffectAggessiveness()
    {
        return true;
    }

    public boolean DoEyesGlow()
    {
        return true;
    }

    protected void CheckForSpiderSkeletonMounting()
    {
        if (!this.worldObj.isRemote && this.isEntityAlive() && this.riddenByEntity == null)
        {
            List var1 = this.worldObj.getEntitiesWithinAABB(FCEntitySkeleton.class, this.GetSpiderJockeyCollisionBoxFromPool());
            Iterator var2 = var1.iterator();

            while (var2.hasNext())
            {
                FCEntitySkeleton var3 = (FCEntitySkeleton)var2.next();

                if (var3 != this.entityToAttack && var3.entityToAttack != this && var3.ridingEntity == null)
                {
                    var3.mountEntity(this);
                }
            }
        }
    }

    private AxisAlignedBB GetSpiderJockeyCollisionBoxFromPool()
    {
        double var1 = (double)(this.width / 16.0F);
        return AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX + var1, this.boundingBox.maxY, this.boundingBox.minZ + var1, this.boundingBox.maxX - var1, this.boundingBox.maxY + 0.10000000149011612D, this.boundingBox.maxZ - var1);
    }
}
