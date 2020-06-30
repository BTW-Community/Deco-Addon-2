package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.List;

public class FCEntityCow extends EntityCow
{
    protected static final int m_iGotMilkDataWatcherID = 26;
    private static final int m_iFullMilkAccumulationCount = 24000;
    private static final int m_iKickAttackTicksToCooldown = 40;
    private static final double m_dKickAttackRange = 1.75D;
    public static final int m_iKickAttackDuration = 20;
    public static final double m_dKickAttackTipCollisionWidth = 2.75D;
    public static final double m_dKickAttackTipCollisionHalfWidth = 1.375D;
    public static final double m_dKickAttackTipCollisionHeight = 2.0D;
    public static final double m_dKickAttackTipCollisionHalfHeight = 1.0D;
    private int m_iMilkAccumulationCount = 0;
    private int m_iKickAttackCooldownTimer = 40;
    public int m_iKickAttackInProgressCounter = -1;
    public int m_iKickAttackLegUsed = 0;

    public FCEntityCow(World var1)
    {
        super(var1);
        this.tasks.RemoveAllTasks();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new FCEntityAIAnimalFlee(this, 0.38F));
        this.tasks.addTask(2, new EntityAIMate(this, 0.2F));
        this.tasks.addTask(3, new FCEntityAIMultiTempt(this, 0.25F));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 0.25F));
        this.tasks.addTask(5, new FCEntityAIGraze(this));
        this.tasks.addTask(6, new FCEntityAIMoveToLooseFood(this, 0.2F));
        this.tasks.addTask(7, new FCEntityAIMoveToGraze(this, 0.2F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(26, new Byte((byte)0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setBoolean("fcGotMilk", this.GotMilk());
        var1.setInteger("fcMilkCount", this.m_iMilkAccumulationCount);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);

        if (var1.hasKey("fcGotMilk"))
        {
            this.SetGotMilk(var1.getBoolean("fcGotMilk"));
        }

        if (var1.hasKey("fcMilkCount"))
        {
            this.m_iMilkAccumulationCount = var1.getInteger("fcMilkCount");
        }
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return !this.getWearingBreedingHarness();
    }

    public int getMaxHealth()
    {
        return 15;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        this.UpdateKickAttack();
        super.onLivingUpdate();
    }

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

            int var4;

            for (var4 = 0; var4 < var3; ++var4)
            {
                this.dropItem(Item.leather.itemID, 1);
            }

            if (!this.HasHeadCrabbedSquid())
            {
                var3 = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + var2);

                if (this.IsFamished())
                {
                    var3 /= 2;
                }

                for (var4 = 0; var4 < var3; ++var4)
                {
                    if (this.isBurning())
                    {
                        this.dropItem(FCBetterThanWolves.fcItemMeatBurned.itemID, 1);
                    }
                    else
                    {
                        this.dropItem(Item.beefRaw.itemID, 1);
                    }
                }
            }
        }
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack var1)
    {
        return var1.itemID == Item.cake.itemID;
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null && var2.itemID == Item.bucketEmpty.itemID)
        {
            if (this.GotMilk())
            {
                --var2.stackSize;

                if (var2.stackSize <= 0)
                {
                    var1.inventory.setInventorySlotContents(var1.inventory.currentItem, new ItemStack(Item.bucketMilk));
                }
                else if (!var1.inventory.addItemStackToInventory(new ItemStack(Item.bucketMilk)))
                {
                    var1.dropPlayerItem(new ItemStack(Item.bucketMilk.itemID, 1, 0));
                }

                this.attackEntityFrom(DamageSource.generic, 0);

                if (!this.worldObj.isRemote)
                {
                    this.SetGotMilk(false);
                    this.worldObj.playAuxSFX(2254, MathHelper.floor_double(this.posX), (int)this.posY, MathHelper.floor_double(this.posZ), 0);
                }
            }
            else
            {
                this.attackEntityFrom(DamageSource.causePlayerDamage(var1), 0);
            }

            return true;
        }
        else
        {
            return this.EntityAnimalInteract(var1);
        }
    }

    public void OnGrazeBlock(int var1, int var2, int var3)
    {
        super.OnGrazeBlock(var1, var2, var3);

        if (!this.getWearingBreedingHarness())
        {
            this.CheckForGrazeSideEffects(var1, var2, var3);
        }
    }

    public boolean IsSubjectToHunger()
    {
        return true;
    }

    public void OnBecomeFamished()
    {
        super.OnBecomeFamished();

        if (this.GotMilk())
        {
            this.SetGotMilk(false);
        }

        this.m_iMilkAccumulationCount = 0;
    }

    public boolean CanGrazeMycelium()
    {
        return true;
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)this.height * 1.2D;
    }

    protected boolean GetCanCreatureTypeBePossessed()
    {
        return true;
    }

    protected void GiveBirthAtTargetLocation(EntityAnimal var1, double var2, double var4, double var6)
    {
        if ((this.IsFullyPossessed() || var1.IsFullyPossessed()) && this.rand.nextInt(8) != 0)
        {
            if (this.worldObj.provider.dimensionId != 1 && this.worldObj.getWorldInfo().getGameType() != EnumGameType.CREATIVE && this.worldObj.rand.nextInt(2) == 0)
            {
                this.BirthMutant(var1, var2, var4, var6);
            }
            else
            {
                this.StillBirth(var1, var2, var4, var6);
            }
        }
        else
        {
            super.GiveBirthAtTargetLocation(var1, var2, var4, var6);
        }
    }

    /**
     * Initialize this creature.
     */
    public void initCreature()
    {
        this.InitHungerWithVariance();

        if (!this.isChild())
        {
            this.m_iMilkAccumulationCount = this.worldObj.rand.nextInt(30001);

            if (this.m_iMilkAccumulationCount >= 24000)
            {
                this.m_iMilkAccumulationCount = 0;
                this.SetGotMilk(true);
            }
        }
    }

    public boolean IsValidZombieSecondaryTarget(EntityZombie var1)
    {
        return true;
    }

    public FCEntityCow spawnBabyAnimal(EntityAgeable var1)
    {
        return new FCEntityCow(this.worldObj);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return !this.IsStarving() ? "mob.cow.say" : "mob.cow.hurt";
    }

    protected void UpdateHungerState()
    {
        if (!this.GotMilk() && this.IsFullyFed() && !this.isChild() && !this.getWearingBreedingHarness())
        {
            --this.m_iHungerCountdown;
            ++this.m_iMilkAccumulationCount;

            if (this.m_iMilkAccumulationCount >= 24000)
            {
                this.SetGotMilk(true);
                this.m_iMilkAccumulationCount = 0;
                this.worldObj.playAuxSFX(2253, MathHelper.floor_double(this.posX), (int)this.posY + 1, MathHelper.floor_double(this.posZ), 0);
            }
        }

        super.UpdateHungerState();
    }

    public float KnockbackMagnitude()
    {
        return 0.3F;
    }

    public void CheckForGrazeSideEffects(int var1, int var2, int var3)
    {
        int var4 = this.worldObj.getBlockId(var1, var2, var3);

        if (var4 == Block.mycelium.blockID)
        {
            this.ConvertToMooshroom();
        }
    }

    public void ConvertToMooshroom()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY) + 1;
        int var3 = MathHelper.floor_double(this.posZ);
        byte var4 = 0;

        if (this.isChild())
        {
            var4 = 1;
        }

        this.worldObj.playAuxSFX(2255, var1, var2, var3, var4);
        this.setDead();
        EntityMooshroom var5 = new EntityMooshroom(this.worldObj);
        var5.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        var5.setEntityHealth(this.getHealth());
        var5.renderYawOffset = this.renderYawOffset;
        var5.setGrowingAge(this.getGrowingAge());
        this.worldObj.spawnEntityInWorld(var5);
    }

    public boolean GotMilk()
    {
        byte var1 = this.dataWatcher.getWatchableObjectByte(26);
        return var1 != 0;
    }

    protected void SetGotMilk(boolean var1)
    {
        byte var2 = 0;

        if (var1)
        {
            var2 = 1;
        }

        this.dataWatcher.updateObject(26, Byte.valueOf(var2));
    }

    private void UpdateKickAttack()
    {
        if (this.m_iKickAttackInProgressCounter >= 0)
        {
            ++this.m_iKickAttackInProgressCounter;

            if (this.m_iKickAttackInProgressCounter >= 20)
            {
                this.m_iKickAttackInProgressCounter = -1;
            }
        }
        else if (!this.worldObj.isRemote)
        {
            --this.m_iKickAttackCooldownTimer;

            if (this.isEntityAlive() && !this.isChild() && !this.getWearingBreedingHarness() && this.m_iKickAttackCooldownTimer <= 0 && (this.isBurning() || this.getAITarget() != null))
            {
                Vec3 var1 = this.ComputeKickAttackCenter();
                AxisAlignedBB var2 = AxisAlignedBB.getAABBPool().getAABB(var1.xCoord - 1.375D, var1.yCoord - 1.0D, var1.zCoord - 1.375D, var1.xCoord + 1.375D, var1.yCoord + 1.0D, var1.zCoord + 1.375D);
                List var3 = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, var2);

                if (!var3.isEmpty())
                {
                    boolean var4 = false;
                    Vec3 var5 = Vec3.createVectorHelper(this.posX, this.posY + (double)(this.height / 2.0F), this.posZ);
                    Iterator var6 = var3.iterator();

                    while (var6.hasNext())
                    {
                        EntityLiving var7 = (EntityLiving)var6.next();

                        if (!(var7 instanceof FCEntityCow) && var7.isEntityAlive() && var7.ridingEntity != this && this.CanEntityBeSeenForAttackToCenterOfMass(var7, var5))
                        {
                            var4 = true;
                            this.KickAttackHitTarget(var7);
                        }
                    }

                    if (var4)
                    {
                        this.LaunchKickAttack();
                    }
                }
            }
        }
    }

    public boolean CanEntityBeSeenForAttackToCenterOfMass(Entity var1, Vec3 var2)
    {
        return this.worldObj.rayTraceBlocks_do_do(var2, this.worldObj.getWorldVec3Pool().getVecFromPool(var1.posX, var1.posY + (double)(var1.height / 2.0F), var1.posZ), false, true) == null;
    }

    public Vec3 ComputeKickAttackCenter()
    {
        float var1 = MathHelper.wrapAngleTo180_float(this.rotationYaw + 180.0F);
        double var2 = (double)(-MathHelper.sin(var1 / 180.0F * (float)Math.PI)) * 1.75D;
        double var4 = (double)(this.height / 2.0F);
        double var6 = (double)MathHelper.cos(var1 / 180.0F * (float)Math.PI) * 1.75D;
        var2 += this.posX;
        var4 += this.posY;
        var6 += this.posZ;
        return Vec3.createVectorHelper(var2, var4, var6);
    }

    private void LaunchKickAttack()
    {
        this.m_iKickAttackInProgressCounter = 0;
        this.m_iKickAttackCooldownTimer = 40;
        this.TransmitKickAttackToClients();
    }

    private void TransmitKickAttackToClients()
    {
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        DataOutputStream var2 = new DataOutputStream(var1);

        try
        {
            var2.writeInt(this.entityId);
            var2.writeByte(2);
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        Packet250CustomPayload var3 = new Packet250CustomPayload("FC|EV", var1.toByteArray());
        FCUtilsWorld.SendPacketToAllPlayersTrackingEntity((WorldServer)this.worldObj, this, var3);
    }

    public void OnClientNotifiedOfKickAttack()
    {
        this.m_iKickAttackInProgressCounter = 0;
        this.m_iKickAttackLegUsed = this.rand.nextInt(2);
        this.worldObj.playSound(this.posX, this.posY, this.posZ, "random.bow", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.5F);
    }

    private void KickAttackHitTarget(Entity var1)
    {
        DamageSource var2 = DamageSource.causeMobDamage(this);

        if (var1.attackEntityFrom(var2, 7))
        {
            if (this.isBurning() && this.rand.nextFloat() < 0.6F)
            {
                var1.setFire(4);
            }

            var1.OnKickedByCow(this);
        }
    }

    private boolean BirthMutant(EntityAnimal var1, double var2, double var4, double var6)
    {
        int var8 = this.rand.nextInt(20);

        if (var8 == 0)
        {
            FCEntityCaveSpider var9 = new FCEntityCaveSpider(this.worldObj);

            if (var9 != null)
            {
                var9.setLocationAndAngles(var2, var4, var6, this.rotationYaw, this.rotationPitch);
                this.worldObj.spawnEntityInWorld(var9);
            }
        }
        else
        {
            int var13;

            if (var8 < 4)
            {
                for (var13 = 0; var13 < 10; ++var13)
                {
                    FCEntityBat var10 = new FCEntityBat(this.worldObj);

                    if (var10 != null)
                    {
                        var10.setLocationAndAngles(var2, var4, var6, this.rotationYaw, this.rotationPitch);
                        this.worldObj.spawnEntityInWorld(var10);
                    }
                }
            }
            else if (var8 < 7)
            {
                for (var13 = 0; var13 < 5; ++var13)
                {
                    EntitySilverfish var11 = new EntitySilverfish(this.worldObj);

                    if (var11 != null)
                    {
                        var11.setLocationAndAngles(var2, var4, var6, this.rotationYaw, this.rotationPitch);
                        this.worldObj.spawnEntityInWorld(var11);
                    }
                }
            }
            else
            {
                FCEntitySquid var12 = new FCEntitySquid(this.worldObj);

                if (var12 != null)
                {
                    var12.setLocationAndAngles(var2, var4, var6, this.rotationYaw, this.rotationPitch);
                    this.worldObj.spawnEntityInWorld(var12);
                }
            }
        }

        return true;
    }

    protected void StillBirth(EntityAnimal var1, double var2, double var4, double var6)
    {
        EntityAgeable var8 = this.createChild(var1);

        if (var8 != null)
        {
            var8.setGrowingAge(-var8.GetTicksForChildToGrow());
            var8.setLocationAndAngles(var2, var4, var6, this.rotationYaw, this.rotationPitch);
            this.worldObj.spawnEntityInWorld(var8);
            var8.attackEntityFrom(DamageSource.generic, 20);
        }
    }
}
