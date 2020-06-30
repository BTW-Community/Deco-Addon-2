package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.List;

public abstract class EntityCreature extends EntityLiving
{
    private PathEntity pathToEntity;

    /** The Entity this EntityCreature is set to attack. */
    protected Entity entityToAttack;

    /**
     * returns true if a creature has attacked recently only used for creepers and skeletons
     */
    protected boolean hasAttacked = false;

    /** Used to make a creature speed up and wander away when hit. */
    protected int fleeingTick = 0;
    private static final int m_iIsPossessedDataWatcherID = 24;
    protected int m_iPossessionTimer = -1;

    public EntityCreature(World par1World)
    {
        super(par1World);
    }

    /**
     * Disables a mob's ability to move on its own while true.
     */
    protected boolean isMovementCeased()
    {
        return false;
    }

    protected void updateEntityActionState()
    {
        this.worldObj.theProfiler.startSection("ai");

        if (this.fleeingTick > 0)
        {
            --this.fleeingTick;
        }

        this.hasAttacked = this.isMovementCeased();
        float var1 = 16.0F;

        if (this.entityToAttack == null)
        {
            this.entityToAttack = this.findPlayerToAttack();

            if (this.entityToAttack != null)
            {
                this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.entityToAttack, var1, true, false, false, true);
            }
        }
        else if (this.entityToAttack.isEntityAlive())
        {
            float var2 = this.entityToAttack.getDistanceToEntity(this);

            if (this.ShouldContinueAttacking(var2))
            {
                if (this.canEntityBeSeen(this.entityToAttack))
                {
                    this.attackEntity(this.entityToAttack, var2);
                }
            }
            else
            {
                this.entityToAttack = null;
            }
        }
        else
        {
            this.entityToAttack = null;
        }

        this.worldObj.theProfiler.endSection();

        if (!this.hasAttacked && this.entityToAttack != null && (this.pathToEntity == null || this.rand.nextInt(20) == 0))
        {
            this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.entityToAttack, var1, true, false, false, true);
        }
        else if (!this.hasAttacked && (this.pathToEntity == null && this.rand.nextInt(180) == 0 || this.rand.nextInt(120) == 0 || this.fleeingTick > 0))
        {
            this.updateWanderPath();
        }

        int var21 = MathHelper.floor_double(this.boundingBox.minY + 0.5D);
        boolean var3 = this.isInWater();
        boolean var4 = this.handleLavaMovement();
        this.rotationPitch = 0.0F;

        if (this.pathToEntity != null && this.rand.nextInt(100) != 0)
        {
            this.worldObj.theProfiler.startSection("followpath");
            Vec3 var5 = this.pathToEntity.getPosition(this);
            double var6 = (double)(this.width * 2.0F);

            while (var5 != null && var5.squareDistanceTo(this.posX, var5.yCoord, this.posZ) < var6 * var6)
            {
                this.pathToEntity.incrementPathIndex();

                if (this.pathToEntity.isFinished())
                {
                    var5 = null;
                    this.pathToEntity = null;
                }
                else
                {
                    var5 = this.pathToEntity.getPosition(this);
                }
            }

            this.isJumping = false;

            if (var5 != null)
            {
                double var8 = var5.xCoord - this.posX;
                double var10 = var5.zCoord - this.posZ;
                double var12 = var5.yCoord - (double)var21;
                float var14 = (float)(Math.atan2(var10, var8) * 180.0D / Math.PI) - 90.0F;
                float var15 = MathHelper.wrapAngleTo180_float(var14 - this.rotationYaw);
                this.moveForward = this.moveSpeed;

                if (var15 > 30.0F)
                {
                    var15 = 30.0F;
                }

                if (var15 < -30.0F)
                {
                    var15 = -30.0F;
                }

                this.rotationYaw += var15;

                if (this.hasAttacked && this.entityToAttack != null)
                {
                    double var16 = this.entityToAttack.posX - this.posX;
                    double var18 = this.entityToAttack.posZ - this.posZ;
                    float var20 = this.rotationYaw;
                    this.rotationYaw = (float)(Math.atan2(var18, var16) * 180.0D / Math.PI) - 90.0F;
                    var15 = (var20 - this.rotationYaw + 90.0F) * (float)Math.PI / 180.0F;
                    this.moveStrafing = -MathHelper.sin(var15) * this.moveForward * 1.0F;
                    this.moveForward = MathHelper.cos(var15) * this.moveForward * 1.0F;
                }

                if (var12 > 0.0D)
                {
                    this.isJumping = true;
                }
            }

            if (this.entityToAttack != null)
            {
                this.faceEntity(this.entityToAttack, 30.0F, 30.0F);
            }

            if (this.isCollidedHorizontally && !this.hasPath())
            {
                this.isJumping = true;
            }

            if (this.rand.nextFloat() < 0.8F && (var3 || var4))
            {
                this.isJumping = true;
            }

            this.worldObj.theProfiler.endSection();
            ++this.entityAge;
            this.despawnEntity();
        }
        else
        {
            super.updateEntityActionState();
            this.pathToEntity = null;
        }
    }

    /**
     * Time remaining during which the Animal is sped up and flees.
     */
    protected void updateWanderPath()
    {
        this.worldObj.theProfiler.startSection("stroll");
        boolean var1 = false;
        int var2 = -1;
        int var3 = -1;
        int var4 = -1;
        float var5 = -99999.0F;

        for (int var6 = 0; var6 < 10; ++var6)
        {
            int var7 = MathHelper.floor_double(this.posX + (double)this.rand.nextInt(13) - 6.0D);
            int var8 = MathHelper.floor_double(this.posY + (double)this.rand.nextInt(7) - 3.0D);
            int var9 = MathHelper.floor_double(this.posZ + (double)this.rand.nextInt(13) - 6.0D);
            float var10 = this.getBlockPathWeight(var7, var8, var9);

            if (var10 > var5)
            {
                var5 = var10;
                var2 = var7;
                var3 = var8;
                var4 = var9;
                var1 = true;
            }
        }

        if (var1)
        {
            this.pathToEntity = this.worldObj.getEntityPathToXYZ(this, var2, var3, var4, 10.0F, true, false, false, true);
        }

        this.worldObj.theProfiler.endSection();
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity par1Entity, float par2) {}

    /**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
    public float getBlockPathWeight(int par1, int par2, int par3)
    {
        return 0.0F;
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        return null;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.boundingBox.minY);
        int var3 = MathHelper.floor_double(this.posZ);
        return super.getCanSpawnHere() && this.getBlockPathWeight(var1, var2, var3) >= 0.0F;
    }

    /**
     * Returns true if entity has a path to follow
     */
    public boolean hasPath()
    {
        return this.pathToEntity != null;
    }

    /**
     * sets the Entities walk path in EntityCreature
     */
    public void setPathToEntity(PathEntity par1PathEntity)
    {
        this.pathToEntity = par1PathEntity;
    }

    /**
     * Returns current entities target
     */
    public Entity getEntityToAttack()
    {
        return this.entityToAttack;
    }

    /**
     * Sets the entity which is to be attacked.
     */
    public void setTarget(Entity par1Entity)
    {
        this.entityToAttack = par1Entity;
    }

    /**
     * This method returns a value to be applied directly to entity speed, this factor is less than 1 when a slowdown
     * potion effect is applied, more than 1 when a haste potion effect is applied and 2 for fleeing entities.
     */
    public float getSpeedModifier()
    {
        float var1 = super.getSpeedModifier();

        if (this.fleeingTick > 0 && !this.isAIEnabled())
        {
            var1 *= 2.0F;
        }

        return var1;
    }

    protected void entityInit()
    {
        this.EntityCreatureEntityInit();
    }

    protected void EntityCreatureEntityInit()
    {
        super.entityInit();

        if (this.GetCanCreatureTypeBePossessed())
        {
            this.dataWatcher.addObject(24, new Byte((byte)0));
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);

        if (this.GetCanCreatureTypeBePossessed())
        {
            var1.setInteger("fcPossessionTimer", this.m_iPossessionTimer);
            var1.setByte("fcPossessionLevel", (byte)this.GetPossessionLevel());
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);

        if (this.GetCanCreatureTypeBePossessed())
        {
            if (var1.hasKey("fcPossessionTimer"))
            {
                this.m_iPossessionTimer = var1.getInteger("fcPossessionTimer");

                if (this.m_iPossessionTimer >= 0)
                {
                    this.SetPossessionLevel(1);
                }
                else
                {
                    this.SetPossessionLevel(0);
                }
            }
            else
            {
                this.m_iPossessionTimer = -1;
                this.SetPossessionLevel(0);
            }

            if (var1.hasKey("fcPossessionLevel"))
            {
                this.SetPossessionLevel(var1.getByte("fcPossessionLevel"));
            }
        }
    }

    protected void ModSpecificOnLivingUpdate()
    {
        super.ModSpecificOnLivingUpdate();

        if (this.GetCanCreatureTypeBePossessed())
        {
            this.HandlePossession();
        }
    }

    protected boolean GetCanCreatureTypeBePossessed()
    {
        return false;
    }

    protected boolean GetCanCreatureBePossessedFromDistance(boolean var1)
    {
        return this.GetCanCreatureTypeBePossessed() && this.isEntityAlive() && !this.IsPossessed();
    }

    protected boolean IsPossessed()
    {
        return this.GetCanCreatureTypeBePossessed() && this.dataWatcher.getWatchableObjectByte(24) != 0;
    }

    protected boolean IsFullyPossessed()
    {
        return this.GetCanCreatureTypeBePossessed() && this.dataWatcher.getWatchableObjectByte(24) > 1;
    }

    protected void SetPossessionLevel(int var1)
    {
        if (this.GetCanCreatureTypeBePossessed())
        {
            byte var2 = (byte)var1;
            this.dataWatcher.updateObject(24, Byte.valueOf(var2));
        }
    }

    protected int GetPossessionLevel()
    {
        return this.GetCanCreatureTypeBePossessed() ? this.dataWatcher.getWatchableObjectByte(24) : 0;
    }

    protected int GetInitialPossessionChance()
    {
        return 1000;
    }

    protected int GetTimeToFullPossession()
    {
        return 2400 + this.worldObj.rand.nextInt(2400);
    }

    protected void OnInitialPossession()
    {
        this.worldObj.playSoundAtEntity(this, this.getDeathSound(), this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        this.worldObj.playAuxSFX(2228, MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 0);
    }

    protected void OnFullPossession() {}

    public void InitiatePossession()
    {
        this.SetPossessionLevel(1);
        this.m_iPossessionTimer = this.GetTimeToFullPossession();
        this.OnInitialPossession();
    }

    protected void HandlePossession()
    {
        if (this.worldObj.getWorldInfo().getGameType() != EnumGameType.CREATIVE)
        {
            if (!this.worldObj.isRemote)
            {
                if (!this.IsPossessed())
                {
                    if (this.worldObj.provider.dimensionId == -1 && this.worldObj.rand.nextInt(this.GetInitialPossessionChance()) == 0)
                    {
                        this.InitiatePossession();
                    }
                }
                else if (!this.isChild() && this.GetPossessionLevel() == 1)
                {
                    --this.m_iPossessionTimer;

                    if (this.m_iPossessionTimer < 0)
                    {
                        this.m_iPossessionTimer = 0;
                    }

                    if (this.m_iPossessionTimer == 0)
                    {
                        this.SetPossessionLevel(2);
                        this.OnFullPossession();
                    }
                }
            }
        }
    }

    protected boolean AttemptToPossessNearbyCreature(double var1, boolean var3)
    {
        List var4 = this.worldObj.getEntitiesWithinAABB(EntityCreature.class, this.boundingBox.expand(var1, var1, var1));
        Iterator var5 = var4.iterator();
        EntityCreature var6;

        do
        {
            if (!var5.hasNext())
            {
                return false;
            }

            var6 = (EntityCreature)var5.next();
        }
        while (!var6.GetCanCreatureBePossessedFromDistance(var3) || var6 == this);

        var6.InitiatePossession();
        return true;
    }

    public static int AttemptToPossessCreaturesAroundBlock(World var0, int var1, int var2, int var3, int var4, int var5)
    {
        AxisAlignedBB var6 = AxisAlignedBB.getAABBPool().getAABB((double)(var1 - var5), (double)(var2 - var5), (double)(var3 - var5), (double)(var1 + 1 + var5), (double)(var2 + 1 + var5), (double)(var3 + 1 + var5));
        List var7 = var0.getEntitiesWithinAABB(EntityCreature.class, var6);
        Iterator var8 = var7.iterator();

        while (var8.hasNext() && var4 > 0)
        {
            EntityCreature var9 = (EntityCreature)var8.next();

            if (var9.GetCanCreatureBePossessedFromDistance(false))
            {
                var9.InitiatePossession();
                --var4;
            }
        }

        return var4;
    }

    protected void AttemptToPossessNearbyCreatureOnDeath()
    {
        this.AttemptToPossessNearbyCreature(16.0D, false);
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource var1)
    {
        super.onDeath(var1);

        if (!this.worldObj.isRemote && this.IsPossessed() && (this.riddenByEntity == null || !this.riddenByEntity.OnPossesedRidingEntityDeath()))
        {
            this.AttemptToPossessNearbyCreatureOnDeath();
        }
    }

    protected boolean ShouldContinueAttacking(float var1)
    {
        return true;
    }

    protected void TransmitAttackTargetToClients()
    {
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        DataOutputStream var2 = new DataOutputStream(var1);

        try
        {
            var2.writeInt(this.entityId);
            var2.writeByte(0);

            if (this.entityToAttack != null)
            {
                var2.writeInt(this.entityToAttack.entityId);
            }
            else
            {
                var2.writeInt(-1);
            }
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        Packet250CustomPayload var3 = new Packet250CustomPayload("FC|EV", var1.toByteArray());
        FCUtilsWorld.SendPacketToAllPlayersTrackingEntity((WorldServer)this.worldObj, this, var3);
    }
}
