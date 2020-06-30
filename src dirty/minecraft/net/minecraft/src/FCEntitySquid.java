package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.List;

public class FCEntitySquid extends EntityWaterMob
{
    public static final float fBrightnessAgressionThreshold = 0.1F;
    private static final float m_fSafeAttackDepth = 0.5F;
    private static final int m_fSafeAttackDepthTestMaximum = 1;
    private static final float m_fSafePassiveDepth = 3.0F;
    private static final int m_fSafePassiveDepthTestMaximum = 4;
    private static final double m_dAgressionRange = 16.0D;
    private static final int m_iChanceOfLosingAttackTargetInLight = 400;
    private static final int m_iTentacleAttackTicksToCooldown = 100;
    private static final double m_dTentacleAttackRange = 6.0D;
    public static final int m_iTentacleAttackDuration = 20;
    private static final double m_dTentacleAttackTipCollisionWidth = 0.2D;
    private static final double m_dTentacleAttackTipCollisionHalfWidth = 0.1D;
    private int m_iTentacleAttackCooldownTimer = 100;
    public int m_iTentacleAttackInProgressCounter = -1;
    private double m_dTentacleAttackTargetX = 0.0D;
    private double m_dTentacleAttackTargetY = 0.0D;
    private double m_dTentacleAttackTargetZ = 0.0D;
    private static final int m_iHeadCrabDamageInitialDelay = 40;
    private static final int m_iHeadCrabDamagePeriod = 40;
    private int m_iHeadCrabDamageCounter = 40;
    public float m_fSquidPitch = 0.0F;
    public float m_fPrevSquidPitch = 0.0F;
    public float m_fSquidYaw = 0.0F;
    public float m_fPrevSquidYaw = 0.0F;
    private float m_fSquidYawSpeed = 0.0F;
    public float m_fTentacleAngle = 0.0F;
    public float m_fPrevTentacleAngle = 0.0F;
    private float m_fTentacleAnimProgress = 0.0F;
    private float m_fPrevTentacleAnimProgress = 0.0F;
    private float m_fTentacleAnimSpeed = 0.0F;
    private float m_fRandomMotionSpeed = 0.0F;
    private float m_fRandomMotionVecX = 0.0F;
    private float m_fRandomMotionVecY = 0.0F;
    private float m_fRandomMotionVecZ = 0.0F;
    private Entity m_entityToNotRecrab = null;
    private int m_iReCrabEntityCountdown = 0;
    private static final int m_iReCrabEntityTicks = 5;
    private static final float m_fPossessedLeapDepth = 0.5F;
    private static final int m_iPossessedLeapCountdownDuration = 200;
    private static final int m_iPossessedLeapPropulsionDuration = 10;
    private int m_iPossessedLeapCountdown = 0;
    private int m_iPossessedLeapPropulsionCountdown = 0;
    private final float m_fPossessedLeapGhastConversionChance = 0.25F;
    private float m_fPossessedLeapGhastConversionDiceRoll = 1.0F;
    private static final int m_iSquidPossessionMaxCount = 50;

    public FCEntitySquid(World var1)
    {
        super(var1);
        this.texture = "/mob/squid.png";
        this.setSize(0.95F, 0.95F);
        this.m_fTentacleAnimSpeed = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
    }

    public int getMaxHealth()
    {
        return 20;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return null;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return 0;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        int var3 = this.rand.nextInt(3 + var2) + 1;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            this.entityDropItem(new ItemStack(Item.dyePowder, 1, 0), 0.0F);
        }

        if (this.rand.nextInt(8) - var2 <= 0)
        {
            this.dropItem(FCBetterThanWolves.fcItemMysteriousGland.itemID, 1);
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.m_fPrevSquidPitch = this.m_fSquidPitch;
        this.m_fPrevSquidYaw = this.m_fSquidYaw;
        this.m_fPrevTentacleAnimProgress = this.m_fTentacleAnimProgress;
        this.m_fPrevTentacleAngle = this.m_fTentacleAngle;
        this.UpdateTentacleAttack();

        if (!this.isEntityAlive())
        {
            if (!this.worldObj.isRemote)
            {
                this.motionX = 0.0D;

                if (this.isInWater())
                {
                    this.motionY -= 0.02D;
                    this.motionY *= 0.8D;
                }
                else
                {
                    this.motionY -= 0.08D;
                    this.motionY *= 0.9800000190734863D;
                }

                this.motionZ = 0.0D;
            }
        }
        else
        {
            this.m_fTentacleAnimProgress += this.m_fTentacleAnimSpeed;

            if (this.m_fTentacleAnimProgress > ((float)Math.PI * 2F))
            {
                this.m_fTentacleAnimProgress -= ((float)Math.PI * 2F);

                if (this.rand.nextInt(10) == 0)
                {
                    this.m_fTentacleAnimSpeed = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
                }
            }

            if (this.ridingEntity != null && !this.ridingEntity.isEntityAlive())
            {
                this.mountEntity((Entity)null);

                if (!this.worldObj.isRemote)
                {
                    this.worldObj.playAuxSFX(2226, MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 0);
                }
            }

            if (!this.inWater && this.getAir() % 100 == 0 && (this.IsPossessed() || this.IsHeadCrab() || this.IsBeingRainedOn()))
            {
                this.setAir(300);
            }

            if (this.IsHeadCrab())
            {
                this.UpdateHeadCrab();
            }
            else
            {
                if (this.isInWater())
                {
                    if (this.m_fTentacleAnimProgress < (float)Math.PI)
                    {
                        float var1 = this.m_fTentacleAnimProgress / (float)Math.PI;
                        this.m_fTentacleAngle = MathHelper.sin(var1 * var1 * (float)Math.PI) * (float)Math.PI * 0.25F;

                        if ((double)var1 > 0.75D)
                        {
                            this.m_fRandomMotionSpeed = 1.0F;
                            this.m_fSquidYawSpeed = 1.0F;
                        }
                        else
                        {
                            this.m_fSquidYawSpeed *= 0.8F;
                        }
                    }
                    else
                    {
                        this.m_fTentacleAngle = 0.0F;
                        this.m_fRandomMotionSpeed *= 0.9F;
                        this.m_fSquidYawSpeed *= 0.99F;
                    }

                    if (!this.worldObj.isRemote)
                    {
                        this.motionX = (double)(this.m_fRandomMotionVecX * this.m_fRandomMotionSpeed);
                        this.motionY = (double)(this.m_fRandomMotionVecY * this.m_fRandomMotionSpeed);
                        this.motionZ = (double)(this.m_fRandomMotionVecZ * this.m_fRandomMotionSpeed);

                        if (this.m_iPossessedLeapPropulsionCountdown > 0)
                        {
                            this.motionY = 1.0D;
                        }
                    }

                    if (this.m_iPossessedLeapPropulsionCountdown > 0)
                    {
                        --this.m_iPossessedLeapPropulsionCountdown;
                    }

                    if (this.m_iTentacleAttackInProgressCounter >= 0)
                    {
                        this.OrientToTentacleAttackPoint();
                    }
                    else if (this.entityToAttack != null)
                    {
                        this.OrientToEntity(this.entityToAttack);
                    }
                    else
                    {
                        this.OrientToMotion();
                    }
                }
                else
                {
                    this.m_iPossessedLeapPropulsionCountdown = 0;
                    this.m_fTentacleAngle = MathHelper.abs(MathHelper.sin(this.m_fTentacleAnimProgress)) * (float)Math.PI * 0.25F;

                    if (!this.worldObj.isRemote)
                    {
                        this.motionX = 0.0D;
                        this.motionY -= 0.08D;
                        this.motionY *= 0.9800000190734863D;
                        this.motionZ = 0.0D;
                    }

                    if (this.m_iTentacleAttackInProgressCounter >= 0)
                    {
                        this.OrientToTentacleAttackPoint();
                    }
                    else if (this.motionY > 0.5D)
                    {
                        this.m_fSquidPitch = 0.0F;
                    }
                    else
                    {
                        this.m_fSquidPitch = (float)((double)this.m_fSquidPitch + (double)(-90.0F - this.m_fSquidPitch) * 0.02D);
                    }
                }
            }
        }
    }

    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    public void moveEntityWithHeading(float var1, float var2)
    {
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
    }

    protected void updateEntityActionState()
    {
        --this.m_iTentacleAttackCooldownTimer;
        this.CheckForHeadCrab();

        if (this.IsHeadCrab())
        {
            this.UpdateHeadCrabActionState();
        }
        else
        {
            float var1 = this.getBrightness(1.0F);
            boolean var2 = var1 < 0.1F;

            if (!this.worldObj.isDaytime())
            {
                var2 = true;
            }

            if (this.entityToAttack == null)
            {
                if (var2)
                {
                    Entity var3 = this.FindClosestValidAttackTargetWithinRange(16.0D);

                    if (var3 != null)
                    {
                        this.setTarget(var3);
                    }
                }
            }
            else if (!var2 && this.rand.nextInt(400) == 0)
            {
                this.setTarget((Entity)null);
            }
            else if (!this.entityToAttack.IsValidOngoingAttackTargetForSquid() || (double)this.getDistanceToEntity(this.entityToAttack) > 16.0D || this.worldObj.isDaytime() && this.entityToAttack.getBrightness(1.0F) > 0.1F && this.rand.nextInt(400) == 0)
            {
                this.setTarget((Entity)null);
            }

            if (this.entityToAttack != null)
            {
                double var21 = this.entityToAttack.posX - this.posX;
                double var5 = this.entityToAttack.posY + (double)this.entityToAttack.getEyeHeight() - (this.posY + (double)(this.height / 2.0F));
                double var7 = this.entityToAttack.posZ - this.posZ;
                double var9 = var21 * var21 + var5 * var5 + var7 * var7;

                if (var9 > 0.25D)
                {
                    double var11 = (double)MathHelper.sqrt_double(var9);
                    double var13 = var21 / var11;
                    double var15 = var5 / var11;
                    double var17 = var7 / var11;
                    this.m_fRandomMotionVecX = (float)(var13 * 0.4D);
                    this.m_fRandomMotionVecY = (float)(var15 * 0.4D);
                    this.m_fRandomMotionVecZ = (float)(var17 * 0.4D);

                    if (!this.IsFullyPossessed())
                    {
                        float var19 = this.GetDepthBeneathSurface(1.0F);

                        if (var19 < 0.5F)
                        {
                            if (this.m_fRandomMotionVecY > -0.1F)
                            {
                                this.m_fRandomMotionVecY = -0.1F;
                            }
                        }
                        else if (this.m_fRandomMotionVecY > 0.0F)
                        {
                            float var20 = var19 - 0.5F;

                            if (this.m_fRandomMotionVecY > var20)
                            {
                                this.m_fRandomMotionVecY = var20;
                            }
                        }
                    }

                    if (this.inWater && (!this.entityToAttack.inWater || this.entityToAttack.ridingEntity != null) && this.m_iTentacleAttackInProgressCounter < 0 && this.m_iTentacleAttackCooldownTimer <= 0 && this.rand.nextInt(20) == 0)
                    {
                        this.AttemptTentacleAttackOnTarget();
                    }
                }
                else
                {
                    this.m_fRandomMotionVecX = this.m_fRandomMotionVecY = this.m_fRandomMotionVecZ = 0.0F;
                }
            }
            else if (this.rand.nextInt(50) == 0 || !this.inWater || this.m_fRandomMotionVecX == 0.0F && this.m_fRandomMotionVecY == 0.0F && this.m_fRandomMotionVecZ == 0.0F)
            {
                float var22 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                this.m_fRandomMotionVecZ = MathHelper.sin(var22) * 0.2F;
                this.m_fRandomMotionVecX = MathHelper.cos(var22) * 0.2F;
                float var4 = this.GetDepthBeneathSurface(4.0F);

                if (this.IsFullyPossessed() && this.inWater)
                {
                    this.m_fRandomMotionVecY = 0.1F;

                    if (var4 < 0.5F && this.m_iPossessedLeapCountdown <= 0)
                    {
                        this.PossessedLeap();
                    }
                }
                else if (var4 >= 3.0F)
                {
                    if (var1 < 0.1F)
                    {
                        int var23 = this.worldObj.skylightSubtracted;

                        if (!this.worldObj.isDaytime())
                        {
                            this.m_fRandomMotionVecY = 0.1F;
                        }
                        else
                        {
                            this.m_fRandomMotionVecY = this.rand.nextFloat() * 0.15F - 0.1F;
                        }
                    }
                    else
                    {
                        this.m_fRandomMotionVecY = -0.1F;
                    }
                }
                else
                {
                    this.m_fRandomMotionVecY = -0.1F;
                }
            }

            ++this.entityAge;
            this.despawnEntity();
        }
    }

    protected double MinDistFromPlayerForDespawn()
    {
        return 144.0D;
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return !this.IsHeadCrab();
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);

        if (!this.IsBlockSurroundedByWater(var1, var2, var3) && !this.IsBlockSurroundedByWater(var1 + 1, var2, var3) && !this.IsBlockSurroundedByWater(var1 - 1, var2, var3) && !this.IsBlockSurroundedByWater(var1, var2 + 1, var3) && !this.IsBlockSurroundedByWater(var1, var2 - 1, var3) && !this.IsBlockSurroundedByWater(var1, var2, var3 + 1) && !this.IsBlockSurroundedByWater(var1, var2, var3 - 1))
        {
            return false;
        }
        else
        {
            int var4 = this.worldObj.getBlockLightValue(var1, var2, var3);
            return var4 > 1 ? false : super.getCanSpawnHere();
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        if (this.IsHeadCrab())
        {
            return var1 == DamageSource.inWall ? false : super.attackEntityFrom(var1, var2);
        }
        else if (this.IsPossessed() && var1 == DamageSource.fall)
        {
            return false;
        }
        else if (super.attackEntityFrom(var1, var2))
        {
            if (!this.worldObj.isRemote)
            {
                Entity var3 = var1.getEntity();

                if (var3 != null && var3 != this)
                {
                    this.setTarget(var3);
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int var1, int var2, int var3, int var4) {}

    public void CheckForScrollDrop()
    {
        if (this.rand.nextInt(250) == 0)
        {
            ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.respiration.effectId);
            this.entityDropItem(var1, 0.0F);
        }
    }

    public AxisAlignedBB GetVisualBoundingBox()
    {
        if (this.m_iTentacleAttackInProgressCounter >= 0)
        {
            double var1 = 6.25D;
            return this.boundingBox.expand(var1, var1, var1);
        }
        else
        {
            return this.boundingBox;
        }
    }

    /**
     * Sets the entity which is to be attacked.
     */
    public void setTarget(Entity var1)
    {
        if (!this.worldObj.isRemote && var1 != this.entityToAttack)
        {
            this.entityToAttack = var1;
            this.TransmitAttackTargetToClients();
        }
        else
        {
            this.entityToAttack = var1;
        }
    }

    protected boolean GetCanCreatureTypeBePossessed()
    {
        return true;
    }

    protected boolean GetCanCreatureBePossessedFromDistance(boolean var1)
    {
        return var1 || this.worldObj.GetNumEntitiesThatApplyToSquidPossessionCap() < 50;
    }

    public boolean OnPossesedRidingEntityDeath()
    {
        if (this.isEntityAlive() && !this.IsPossessed())
        {
            this.InitiatePossession();
            return true;
        }
        else
        {
            return false;
        }
    }

    public void InitiatePossession()
    {
        super.InitiatePossession();
        this.SetPersistent(true);
    }

    protected void HandlePossession()
    {
        super.HandlePossession();

        if (this.m_iPossessedLeapCountdown > 0)
        {
            --this.m_iPossessedLeapCountdown;
        }

        if (!this.worldObj.isRemote && this.IsFullyPossessed())
        {
            if (this.ridingEntity == null && !this.inWater && !this.onGround)
            {
                if (this.m_fPossessedLeapGhastConversionDiceRoll <= 0.25F && this.motionY <= 0.0D)
                {
                    FCEntityGhast var1 = new FCEntityGhast(this.worldObj);
                    var1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);

                    if (this.worldObj.checkNoEntityCollision(var1.boundingBox, this) && this.worldObj.getCollidingBoundingBoxes(this, var1.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(var1.boundingBox))
                    {
                        this.worldObj.playAuxSFX(2273, MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 0);
                        this.setDead();
                        var1.SetPersistent(true);
                        this.worldObj.spawnEntityInWorld(var1);
                    }
                }
            }
            else if (!this.inWater || this.motionY <= 0.0D)
            {
                this.m_fPossessedLeapGhastConversionDiceRoll = 1.0F;
            }
        }
    }

    public boolean DoesEntityApplyToSquidPossessionCap()
    {
        return this.isEntityAlive() && this.GetIsPersistent();
    }

    public boolean IsValidZombieSecondaryTarget(EntityZombie var1)
    {
        return !this.inWater && this.ridingEntity == null && var1.riddenByEntity == null;
    }

    public boolean AttractsLightning()
    {
        return false;
    }

    public float getEyeHeight()
    {
        return this.height * 0.5F;
    }

    private void UpdateHeadCrabActionState()
    {
        Entity var1 = this.ridingEntity.GetHeadCrabSharedAttackTarget();

        if (var1 == this)
        {
            var1 = null;
        }

        this.setTarget(var1);

        if (this.entityToAttack != null && this.m_iTentacleAttackInProgressCounter < 0 && this.m_iTentacleAttackCooldownTimer <= 0 && this.rand.nextInt(20) == 0)
        {
            this.AttemptTentacleAttackOnTarget();
        }

        if (this.IsFullyPossessed() && this.m_iPossessedLeapCountdown <= 0 && !this.inWater && this.rand.nextInt(100) == 0)
        {
            this.mountEntity((Entity)null);
            this.PossessedLeap();
        }
    }

    private void OrientToMotion()
    {
        float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.renderYawOffset = this.InterpolateAngle(this.renderYawOffset, -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI, 1.0F);
        this.rotationYaw = this.renderYawOffset;
        this.m_fSquidPitch += (-((float)Math.atan2((double)var1, this.motionY)) * 180.0F / (float)Math.PI - this.m_fSquidPitch) * 0.1F;
        this.m_fSquidYaw += (float)Math.PI * this.m_fSquidYawSpeed * 1.5F;
    }

    private void OrientToEntity(Entity var1)
    {
        double var2 = var1.posX - this.posX;
        double var4 = var1.posY + (double)var1.getEyeHeight() - (this.posY + (double)(this.height / 2.0F));
        double var6 = var1.posZ - this.posZ;
        double var8 = (double)MathHelper.sqrt_double(var2 * var2 + var6 * var6);
        this.renderYawOffset = this.InterpolateAngle(this.renderYawOffset, -((float)Math.atan2(var2, var6)) * 180.0F / (float)Math.PI, 1.0F);
        this.rotationYaw = this.renderYawOffset;
        this.m_fSquidPitch = this.InterpolateAngle(this.m_fSquidPitch, -((float)(Math.atan2(var8, var4) * 180.0D / Math.PI)), 10.0F);
        this.m_fSquidYaw += (float)Math.PI * this.m_fSquidYawSpeed * 1.5F;
    }

    private void OrientToTentacleAttackPoint()
    {
        double var1 = this.m_dTentacleAttackTargetX - this.posX;
        double var3 = this.m_dTentacleAttackTargetY - (this.posY + (double)(this.height / 2.0F));
        double var5 = this.m_dTentacleAttackTargetZ - this.posZ;
        double var7 = (double)MathHelper.sqrt_double(var1 * var1 + var5 * var5);
        this.renderYawOffset = this.InterpolateAngle(this.renderYawOffset, -((float)Math.atan2(var1, var5)) * 180.0F / (float)Math.PI, 50.0F);
        this.rotationYaw = this.renderYawOffset;
        this.m_fSquidPitch = this.InterpolateAngle(this.m_fSquidPitch, -((float)(Math.atan2(var7, var3) * 180.0D / Math.PI - 150.0D)), 50.0F);
        this.m_fSquidYaw = this.InterpolateAngle(this.m_fSquidYaw, 0.0F, 50.0F);
    }

    private Entity FindClosestValidAttackTargetWithinRange(double var1)
    {
        Object var3 = null;
        double var4 = var1 * var1;

        for (int var6 = 0; var6 < this.worldObj.playerEntities.size(); ++var6)
        {
            EntityPlayer var7 = (EntityPlayer)this.worldObj.playerEntities.get(var6);

            if (!var7.capabilities.disableDamage && var7.isEntityAlive())
            {
                double var8 = var7.posX - this.posX;
                double var10 = var7.posY - this.posY;
                double var12 = var7.posZ - this.posZ;
                double var14 = var8 * var8 + var10 * var10 + var12 * var12;

                if (var14 < var4 && (!this.worldObj.isDaytime() || var7.getBrightness(1.0F) < 0.1F) && (var7.inWater || this.canEntityBeSeen(var7)))
                {
                    var3 = var7;
                    var4 = var14;
                }
            }
        }

        if ((this.worldObj.worldInfo.getWorldTime() + (long)this.entityId & 31L) == 0L && var3 == null)
        {
            var3 = this.worldObj.GetClosestEntityMatchingCriteriaWithinRange(this.posX, this.posY, this.posZ, var1, FCClosestEntitySelectionCriteria.m_secondarySquidTarget);
        }

        return (Entity)var3;
    }

    private void CheckForHeadCrab()
    {
        if (this.isEntityAlive())
        {
            if (this.ridingEntity == null)
            {
                if (this.motionY < 0.5D)
                {
                    if (this.m_iReCrabEntityCountdown > 0)
                    {
                        --this.m_iReCrabEntityCountdown;
                    }
                    else
                    {
                        this.m_entityToNotRecrab = null;
                    }

                    EntityLiving var1 = this.GetValidHeadCrabTargetInRange();

                    if (var1 != null)
                    {
                        this.mountEntity(var1);
                        this.playSound("mob.slime.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                        this.m_iHeadCrabDamageCounter = 40;
                        var1.OnHeadCrabbedBySquid(this);
                    }
                }
            }
            else
            {
                this.m_entityToNotRecrab = this.ridingEntity;
                this.m_iReCrabEntityCountdown = 5;
            }
        }
    }

    private EntityLiving GetValidHeadCrabTargetInRange()
    {
        double var1 = 0.25D;

        if (!this.isInWater())
        {
            var1 = 0.5D;
        }

        List var3 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, this.boundingBox.expand(var1, var1, var1));
        Iterator var4 = var3.iterator();
        EntityLiving var5;

        do
        {
            if (!var4.hasNext())
            {
                return null;
            }

            var5 = (EntityLiving)var4.next();
        }
        while (!var5.GetCanBeHeadCrabbed(this.isInWater()) || var5 == this.m_entityToNotRecrab || !this.canEntityBeSeen(var5));

        return var5;
    }

    private void UpdateHeadCrab()
    {
        this.m_fTentacleAnimSpeed = 0.2F;
        this.m_fSquidPitch = 0.0F;
        float var1 = MathHelper.sin(this.m_fTentacleAnimProgress);
        this.m_fTentacleAngle = MathHelper.abs(MathHelper.sin(var1)) * (float)Math.PI * 0.25F;

        if (!this.worldObj.isRemote)
        {
            --this.m_iHeadCrabDamageCounter;

            if (this.m_iHeadCrabDamageCounter <= 0)
            {
                if (!this.ridingEntity.IsImmuneToHeadCrabDamage())
                {
                    DamageSource var2 = DamageSource.causeMobDamage(this);
                    var2.setDamageBypassesArmor();
                    this.ridingEntity.attackEntityFrom(var2, 1);
                }

                this.m_iHeadCrabDamageCounter = 40;
            }

            if (this.ridingEntity.ridingEntity != null)
            {
                this.ridingEntity.mountEntity(this.ridingEntity.ridingEntity);

                if (this.ridingEntity.ridingEntity != null)
                {
                    this.ridingEntity.ridingEntity.riddenByEntity = null;
                    this.ridingEntity.ridingEntity = null;
                }
            }
        }
        else
        {
            float var4 = MathHelper.sin(this.m_fPrevTentacleAnimProgress);

            if (var4 <= 0.0F && var1 > 0.0F || var4 > 0.0F && var1 <= 0.0F)
            {
                if (!this.ridingEntity.IsImmuneToHeadCrabDamage())
                {
                    this.worldObj.playSound(this.posX, this.posY, this.posZ, "random.eat", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.8F);
                    this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.slime.big", 0.5F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.7F);
                }
                else
                {
                    this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.slime.big", 0.025F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.7F);
                }
            }

            if (this.ridingEntity instanceof EntityLiving)
            {
                EntityLiving var3 = (EntityLiving)this.ridingEntity;
                this.m_fSquidYaw = -var3.rotationYawHead;
                this.m_fPrevSquidYaw = -var3.prevRotationYawHead;
                this.renderYawOffset = 0.0F;
                this.rotationYaw = 0.0F;
            }
        }
    }

    private void AttemptTentacleAttackOnTarget()
    {
        double var1 = this.entityToAttack.posX - this.posX;
        double var3 = this.entityToAttack.posY + (double)(this.entityToAttack.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
        double var5 = this.entityToAttack.posZ - this.posZ;
        double var7 = var1 * var1 + var3 * var3 + var5 * var5;

        if (var7 < 36.0D)
        {
            if (!this.CanEntityCenterOfMassBeSeen(this.entityToAttack))
            {
                if (!this.canEntityBeSeen(this.entityToAttack))
                {
                    return;
                }

                var3 = this.entityToAttack.posY + (double)this.entityToAttack.getEyeHeight() - (this.posY + (double)(this.height / 2.0F));
                var7 = var1 * var1 + var3 * var3 + var5 * var5;
            }

            double var9 = (double)MathHelper.sqrt_double(var7);
            double var11 = var1 / var9;
            double var13 = var3 / var9;
            double var15 = var5 / var9;
            this.LaunchTentacleAttackInDirection(var11, var13, var15);
        }
    }

    private void LaunchTentacleAttackInDirection(double var1, double var3, double var5)
    {
        this.m_iTentacleAttackInProgressCounter = 0;
        this.m_iTentacleAttackCooldownTimer = 100;
        this.m_dTentacleAttackTargetX = this.posX + var1 * 6.0D;
        this.m_dTentacleAttackTargetY = this.posY + (double)(this.height / 2.0F) + var3 * 6.0D;
        this.m_dTentacleAttackTargetZ = this.posZ + var5 * 6.0D;
        this.TransmitTentacleAttackToClients();
    }

    private void TransmitTentacleAttackToClients()
    {
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        DataOutputStream var2 = new DataOutputStream(var1);

        try
        {
            var2.writeInt(this.entityId);
            var2.writeByte(1);
            var2.writeInt(MathHelper.floor_double(this.m_dTentacleAttackTargetX * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.m_dTentacleAttackTargetY * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.m_dTentacleAttackTargetZ * 32.0D));
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        Packet250CustomPayload var3 = new Packet250CustomPayload("FC|EV", var1.toByteArray());
        FCUtilsWorld.SendPacketToAllPlayersTrackingEntity((WorldServer)this.worldObj, this, var3);
    }

    public void OnClientNotifiedOfTentacleAttack(double var1, double var3, double var5)
    {
        this.m_iTentacleAttackInProgressCounter = 0;
        this.m_dTentacleAttackTargetX = var1;
        this.m_dTentacleAttackTargetY = var3;
        this.m_dTentacleAttackTargetZ = var5;
        this.worldObj.playSound(this.posX, this.posY, this.posZ, "random.bow", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.5F);
        this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.slime.big", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.5F);

        if (this.inWater)
        {
            int var7;

            for (var7 = 0; var7 < 150; ++var7)
            {
                this.worldObj.spawnParticle("bubble", this.posX + this.rand.nextDouble() * 2.0D - 1.0D, this.posY + this.rand.nextDouble(), this.posZ + this.rand.nextDouble() * 2.0D - 1.0D, 0.0D, 0.0D, 0.0D);
            }

            for (var7 = 0; var7 < 10; ++var7)
            {
                this.worldObj.spawnParticle("splash", this.posX + this.rand.nextDouble() * 2.0D - 1.0D, this.posY + (double)this.height, this.posZ + this.rand.nextDouble() * 2.0D - 1.0D, 0.0D, 0.0D, 0.0D);
            }

            this.worldObj.playSound(this.posX, this.posY, this.posZ, "liquid.splash", 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
        }
    }

    private void UpdateTentacleAttack()
    {
        if (this.m_iTentacleAttackInProgressCounter >= 0)
        {
            ++this.m_iTentacleAttackInProgressCounter;

            if (this.m_iTentacleAttackInProgressCounter >= 20)
            {
                this.m_iTentacleAttackInProgressCounter = -1;
            }
            else if (this.m_iTentacleAttackInProgressCounter <= 10)
            {
                Vec3 var1 = this.ComputeTentacleAttackTip((float)this.m_iTentacleAttackInProgressCounter);
                AxisAlignedBB var2 = AxisAlignedBB.getAABBPool().getAABB(var1.xCoord - 0.1D, var1.yCoord - 0.1D, var1.zCoord - 0.1D, var1.xCoord + 0.1D, var1.yCoord + 0.1D, var1.zCoord + 0.1D);
                List var3 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, var2);

                if (!var3.isEmpty())
                {
                    Iterator var4 = var3.iterator();

                    while (var4.hasNext())
                    {
                        EntityLiving var5 = (EntityLiving)var4.next();

                        if (!(var5 instanceof FCEntitySquid) && var5 != this.ridingEntity)
                        {
                            this.RetractTentacleAttackOnCollision();

                            if (!this.worldObj.isRemote)
                            {
                                this.TentacleAttackFlingTarget(var5, true);
                            }

                            break;
                        }
                    }
                }
            }
        }
    }

    private void RetractTentacleAttackOnCollision()
    {
        byte var1 = 10;

        if (this.m_iTentacleAttackInProgressCounter < var1)
        {
            this.m_iTentacleAttackInProgressCounter = var1 + (var1 - this.m_iTentacleAttackInProgressCounter);
        }
    }

    public Vec3 ComputeTentacleAttackTip(float var1)
    {
        double var2 = this.GetAttackProgressSin(var1);
        double var4 = this.m_dTentacleAttackTargetX - this.posX;
        double var6 = this.m_dTentacleAttackTargetY - (this.posY + (double)(this.height / 2.0F));
        double var8 = this.m_dTentacleAttackTargetZ - this.posZ;
        double var10 = var4 * var2;
        double var12 = var6 * var2;
        double var14 = var8 * var2;
        return Vec3.createVectorHelper(this.posX + var10, this.posY + (double)(this.height / 2.0F) + var12, this.posZ + var14);
    }

    public double GetAttackProgressSin(float var1)
    {
        double var2 = (double)(var1 / 20.0F);
        return (double)MathHelper.sin((float)(var2 * Math.PI));
    }

    private void TentacleAttackFlingTarget(Entity var1, boolean var2)
    {
        Entity var3 = null;

        if (var1.ridingEntity != null)
        {
            var3 = var1.ridingEntity;
            var1.mountEntity((Entity)null);
        }

        if (var2)
        {
            int var4 = MathHelper.floor_double(var1.posX);
            int var5 = MathHelper.floor_double(var1.posY) + 1;
            int var6 = MathHelper.floor_double(var1.posZ);
            this.worldObj.playAuxSFX(2262, var4, var5, var6, 0);
        }

        double var26 = var1.motionX;
        double var27 = var1.motionZ;
        double var8 = var1.posX - this.posX;
        double var10 = var1.posZ - this.posZ;
        double var12 = var8 * var8 + var10 * var10;
        double var14;

        if (var12 > 0.1D)
        {
            var14 = (double)MathHelper.sqrt_double(var12);
            var26 += (double)((float)(-var8 / var14) * 1.0F);
            var27 += (double)((float)(-var10 / var14) * 1.0F);
        }

        if (var1 instanceof EntityPlayer && ((EntityPlayer)var1).isBlocking())
        {
            EntityPlayer var28 = (EntityPlayer)var1;
            ItemStack var15 = var28.inventory.mainInventory[var28.inventory.currentItem];

            if (var15 != null)
            {
                ItemStack var16 = new ItemStack(var15.itemID, var15.stackSize, var15.getItemDamage());
                FCUtilsInventory.CopyEnchantments(var16, var15);
                double var17 = var1.posX;
                double var19 = var1.posY + 1.0D;
                double var21 = var1.posZ;
                EntityItem var23 = new EntityItem(this.worldObj, var17, var19, var21, var16);
                double var24 = var1.motionY + 0.5D;
                var23.motionX = var26;
                var23.motionY = var24;
                var23.motionZ = var27;
                var23.delayBeforeCanPickup = 10;
                this.worldObj.spawnEntityInWorld(var23);
                var15.stackSize = 0;
            }
        }
        else
        {
            var1.isAirBorne = true;
            var14 = var1.motionY + 0.75D;
            var26 *= this.rand.nextDouble() * 0.2D + 0.9D;
            var27 *= this.rand.nextDouble() * 0.2D + 0.9D;
            var1.motionX = var26;
            var1.motionY = var14;
            var1.motionZ = var27;
            this.CapFlingMotionOfEntity(var1);
            var1.setBeenAttacked();
        }

        var1.OnFlungBySquidTentacle(this);

        if (var3 != null)
        {
            this.TentacleAttackFlingTarget(var3, false);
        }
    }

    private void CapFlingMotionOfEntity(Entity var1)
    {
        if (var1.motionY > 0.75D)
        {
            var1.motionY = 0.75D;
        }

        if (var1.motionX > 1.0D)
        {
            var1.motionX = 1.0D;
        }
        else if (var1.motionX < -1.0D)
        {
            var1.motionX = -1.0D;
        }

        if (var1.motionZ > 1.0D)
        {
            var1.motionZ = 1.0D;
        }
        else if (var1.motionZ < -1.0D)
        {
            var1.motionZ = -1.0D;
        }
    }

    public boolean IsHeadCrab()
    {
        return this.ridingEntity != null && this.ridingEntity instanceof EntityLiving;
    }

    private boolean IsBlockSurroundedByWater(int var1, int var2, int var3)
    {
        for (int var4 = var2 - 1; var4 <= var2 + 1; ++var4)
        {
            for (int var5 = var1 - 1; var5 <= var1 + 1; ++var5)
            {
                for (int var6 = var3 - 1; var6 <= var3 + 1; ++var6)
                {
                    int var7 = this.worldObj.getBlockId(var5, var4, var6);

                    if (var7 != Block.waterMoving.blockID && var7 != Block.waterStill.blockID)
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public float GetDepthBeneathSurface(float var1)
    {
        float var2 = -1.0F;
        int var3 = MathHelper.floor_double(this.posX);
        int var4 = (int)this.posY;
        int var5 = MathHelper.floor_double(this.posZ);
        int var6 = this.worldObj.getBlockId(var3, var4, var5);
        int var7 = this.worldObj.getBlockId(var3, var4 + 1, var5);

        if (var6 == Block.waterStill.blockID || var6 == Block.waterMoving.blockID || var7 == Block.waterStill.blockID || var7 == Block.waterMoving.blockID)
        {
            var2 = 0.0F;
            var2 = (float)((double)var2 + (this.posY - (double)((float)var4)));

            for (int var8 = 1; var2 < var1; ++var8)
            {
                var6 = this.worldObj.getBlockId(var3, var4 + var8, var5);

                if (var6 != Block.waterStill.blockID && var6 != Block.waterMoving.blockID)
                {
                    if (var2 == 0.0F && this.posY > 32.0D)
                    {
                        ;
                    }

                    break;
                }

                ++var2;
            }
        }

        return var2;
    }

    private float InterpolateAngle(float var1, float var2, float var3)
    {
        float var4 = MathHelper.wrapAngleTo180_float(var2 - var1);

        if (var4 > var3)
        {
            var4 = var3;
        }
        else if (var4 < -var3)
        {
            var4 = -var3;
        }

        return var1 + var4;
    }

    private void PossessedLeap()
    {
        this.motionY = 1.0D;
        this.isAirBorne = true;
        this.m_iPossessedLeapCountdown = 200;
        this.m_fPossessedLeapGhastConversionDiceRoll = this.rand.nextFloat();

        if (this.inWater)
        {
            this.m_iPossessedLeapPropulsionCountdown = 10;
            this.playSound("liquid.splash", 1.0F, this.rand.nextFloat() * 0.1F + 0.5F);
        }
        else
        {
            this.m_iPossessedLeapPropulsionCountdown = 0;
            this.playSound("mob.slime.big", 1.0F, this.rand.nextFloat() * 0.1F + 0.5F);
        }
    }

    private AxisAlignedBB GetGhastConversionCollisionBoxFromPool()
    {
        double var1 = (double)(this.width / 16.0F);
        return AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX + var1, this.boundingBox.maxY, this.boundingBox.minZ + var1, this.boundingBox.maxX - var1, this.boundingBox.maxY + 0.10000000149011612D, this.boundingBox.maxZ - var1);
    }
}
