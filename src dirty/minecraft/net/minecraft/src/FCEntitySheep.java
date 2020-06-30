package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class FCEntitySheep extends EntitySheep
{
    private static final int m_iFullWoolAccumulationCount = 24000;
    private int m_iOriginalFleeceColor = 0;
    private int m_iWoolAccumulationCount = 0;

    public FCEntitySheep(World var1)
    {
        super(var1);
        this.tasks.RemoveAllTasks();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new FCEntityAIAnimalFlee(this, 0.38F));
        this.tasks.addTask(2, new EntityAIMate(this, 0.23F));
        this.tasks.addTask(3, new FCEntityAIMultiTempt(this, 0.25F));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 0.25F));
        this.tasks.addTask(5, new FCEntityAIGraze(this));
        this.tasks.addTask(6, new FCEntityAIMoveToLooseFood(this, 0.23F));
        this.tasks.addTask(7, new FCEntityAIMoveToGraze(this, 0.23F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return !this.getWearingBreedingHarness();
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        if (!this.getSheared() && this.IsFullyFed())
        {
            this.entityDropItem(new ItemStack(FCBetterThanWolves.fcItemWool.itemID, 1, BlockCloth.getDyeFromBlock(this.getFleeceColor())), 0.0F);
        }

        this.DropMutton(var2);
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null && var2.itemID == Item.shears.itemID && !this.getSheared() && !this.isChild())
        {
            if (!this.worldObj.isRemote)
            {
                this.setSheared(true);
                int var3 = 1 + this.rand.nextInt(3);

                for (int var4 = 0; var4 < var3; ++var4)
                {
                    EntityItem var5 = this.entityDropItem(new ItemStack(FCBetterThanWolves.fcItemWool.itemID, 1, BlockCloth.getDyeFromBlock(this.getFleeceColor())), 1.0F);
                    var5.motionY += (double)(this.rand.nextFloat() * 0.05F);
                    var5.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
                    var5.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
                }
            }

            var2.damageItem(1, var1);
            this.playSound("mob.sheep.shear", 1.0F, 1.0F);
            this.attackEntityFrom(DamageSource.generic, 0);

            if (var2.stackSize <= 0)
            {
                var1.inventory.mainInventory[var1.inventory.currentItem] = null;
            }
        }

        return this.EntityAnimalInteract(var1);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return FCBetterThanWolves.fcItemWool.itemID;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setByte("fcOrgClr", (byte)this.m_iOriginalFleeceColor);
        var1.setInteger("fcWoolCount", this.m_iWoolAccumulationCount);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);

        if (var1.hasKey("fcOrgClr"))
        {
            this.m_iOriginalFleeceColor = var1.getByte("fcOrgClr");
        }

        if (var1.hasKey("fcWoolCount"))
        {
            this.m_iWoolAccumulationCount = var1.getInteger("fcWoolCount");
        }
    }

    protected boolean GetCanCreatureTypeBePossessed()
    {
        return true;
    }

    protected void ModSpecificOnLivingUpdate()
    {
        super.ModSpecificOnLivingUpdate();

        if (!this.isLivingDead && this.IsFullyPossessed() && !this.getSheared() && !this.isInWater() && !this.handleLavaMovement())
        {
            if (this.posY < 125.0D)
            {
                this.motionY += 0.08341D;
            }
            else
            {
                this.motionY += 0.0725D;
            }

            if (!this.onGround && !this.isCollidedHorizontally && this.worldObj.provider.dimensionId == 0 && this.posY > 100.0D && !this.CheckForWolfBomb() && !this.getSheared() && this.motionX > -0.012000000104308128D)
            {
                this.motionX -= 0.005D;
            }
        }
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float var1)
    {
        if (!this.IsFullyPossessed() || this.getSheared())
        {
            super.fall(var1);
        }
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)this.height;
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack var1)
    {
        return var1.itemID == Item.pumpkinPie.itemID;
    }

    public boolean IsValidZombieSecondaryTarget(EntityZombie var1)
    {
        return true;
    }

    /**
     * Initialize this creature.
     */
    public void initCreature()
    {
        this.InitHungerWithVariance();
        int var1 = getRandomFleeceColor(this.worldObj.rand);

        if (var1 == 0)
        {
            int var2 = this.worldObj.rand.nextInt(500);

            if (var2 == 0)
            {
                var1 = 3;
            }
            else if (var2 == 1)
            {
                var1 = 5;
            }
        }

        this.setFleeceColor(var1);
    }

    public void setFleeceColor(int var1)
    {
        super.setFleeceColor(var1);
        this.m_iOriginalFleeceColor = var1;
    }

    public EntityAgeable createChild(EntityAgeable var1)
    {
        return this.SpawnHardcoreBaby(var1);
    }

    public boolean IsSubjectToHunger()
    {
        return true;
    }

    protected int GetFoodValueMultiplier()
    {
        return 3;
    }

    public void OnBecomeFamished()
    {
        super.OnBecomeFamished();

        if (!this.getSheared())
        {
            this.setSheared(true);
        }

        this.m_iWoolAccumulationCount = 0;
    }

    protected void UpdateHungerState()
    {
        if (this.getSheared() && this.IsFullyFed() && !this.isChild() && !this.getWearingBreedingHarness())
        {
            --this.m_iHungerCountdown;
            ++this.m_iWoolAccumulationCount;

            if (this.m_iWoolAccumulationCount >= 24000)
            {
                this.setFleeceColor(this.m_iOriginalFleeceColor);
                this.setSheared(false);
                this.m_iWoolAccumulationCount = 0;
                this.worldObj.playAuxSFX(2261, MathHelper.floor_double(this.posX), (int)this.posY + 1, MathHelper.floor_double(this.posZ), 0);
            }
        }

        super.UpdateHungerState();
    }

    public void setSuperficialFleeceColor(int var1)
    {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 240 | var1 & 15)));
    }

    public FCEntitySheep SpawnHardcoreBaby(EntityAgeable var1)
    {
        FCEntitySheep var2 = (FCEntitySheep)var1;
        FCEntitySheep var3 = new FCEntitySheep(this.worldObj);
        int var4 = this.rand.nextInt(100);
        int var5;

        if (var4 == 0)
        {
            var5 = this.getMutantColor(this, var2);
            var3.setFleeceColor(var5);
        }
        else if (var4 <= 3)
        {
            byte var6 = 15;
            var3.setFleeceColor(var6);
        }
        else if (var4 <= 23)
        {
            var5 = this.blendParentColors(this, var2);
            var3.setFleeceColor(var5);
        }
        else if (this.rand.nextBoolean())
        {
            var3.setFleeceColor(this.m_iOriginalFleeceColor);
        }
        else
        {
            var3.setFleeceColor(var2.m_iOriginalFleeceColor);
        }

        return var3;
    }

    public int blendParentColors(FCEntitySheep var1, FCEntitySheep var2)
    {
        int var3;
        int var4;
        int var5;
        var3 = BlockCloth.getBlockFromDye(var1.m_iOriginalFleeceColor);
        var4 = BlockCloth.getBlockFromDye(var2.m_iOriginalFleeceColor);
        var5 = var3;
        label131:

        switch (var3)
        {
            case 0:
                switch (var4)
                {
                    case 7:
                        var5 = 8;

                    case 8:
                    case 11:
                    case 14:
                    default:
                        break label131;

                    case 9:
                        var5 = 1;
                        break label131;

                    case 10:
                        var5 = 2;
                        break label131;

                    case 12:
                        var5 = 4;
                        break label131;

                    case 13:
                        var5 = 5;
                        break label131;

                    case 15:
                        var5 = 7;
                        break label131;
                }

            case 1:
                switch (var4)
                {
                    case 2:
                        var5 = 11;
                        break label131;

                    case 3:
                        var5 = 14;
                        break label131;

                    case 4:
                        var5 = 5;

                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 13:
                    case 14:
                    default:
                        break label131;

                    case 6:
                        var5 = 3;
                        break label131;

                    case 11:
                        var5 = 14;
                        break label131;

                    case 12:
                        var5 = 13;
                        break label131;

                    case 15:
                        var5 = 9;
                        break label131;
                }

            case 2:
                switch (var4)
                {
                    case 1:
                        var5 = 11;
                        break label131;

                    case 4:
                        var5 = 6;
                        break label131;

                    case 14:
                        var5 = 11;
                        break label131;

                    case 15:
                        var5 = 10;

                    default:
                        break label131;
                }

            case 3:
                switch (var4)
                {
                    case 1:
                        var5 = 14;

                    default:
                        break label131;
                }

            case 4:
                switch (var4)
                {
                    case 1:
                        var5 = 5;
                        break label131;

                    case 2:
                        var5 = 6;

                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                    case 13:
                    case 14:
                    default:
                        break label131;

                    case 9:
                        var5 = 13;
                        break label131;

                    case 11:
                        var5 = 2;
                        break label131;

                    case 15:
                        var5 = 12;
                        break label131;
                }

            case 5:
                switch (var4)
                {
                    case 15:
                        var5 = 13;

                    default:
                        break label131;
                }

            case 6:
                switch (var4)
                {
                    case 1:
                        var5 = 3;

                    default:
                        break label131;
                }

            case 7:
                switch (var4)
                {
                    case 0:
                        var5 = 8;

                    default:
                        break label131;
                }

            case 8:
                switch (var4)
                {
                    case 15:
                        var5 = 7;

                    default:
                        break label131;
                }

            case 9:
                switch (var4)
                {
                    case 0:
                        var5 = 1;
                        break label131;

                    case 4:
                        var5 = 13;

                    default:
                        break label131;
                }

            case 10:
                switch (var4)
                {
                    case 0:
                        var5 = 2;

                    default:
                        break label131;
                }

            case 11:
                switch (var4)
                {
                    case 1:
                        var5 = 14;
                        break label131;

                    case 4:
                        var5 = 2;
                        break label131;

                    case 12:
                        var5 = 10;

                    default:
                        break label131;
                }

            case 12:
                switch (var4)
                {
                    case 0:
                        var5 = 4;
                        break label131;

                    case 1:
                        var5 = 13;
                        break label131;

                    case 9:
                        var5 = 13;
                        break label131;

                    case 11:
                        var5 = 13;

                    default:
                        break label131;
                }

            case 13:
                switch (var4)
                {
                    case 0:
                        var5 = 5;
                }

            case 14:
            default:
                break;

            case 15:
                switch (var4)
                {
                    case 0:
                        var5 = 8;
                        break;

                    case 1:
                        var5 = 9;
                        break;

                    case 2:
                        var5 = 10;

                    case 3:
                    case 6:
                    case 7:
                    default:
                        break;

                    case 4:
                        var5 = 12;
                        break;

                    case 5:
                        var5 = 13;
                        break;

                    case 8:
                        var5 = 7;
                }
        }

        if (var5 == var3 && this.rand.nextBoolean())
        {
            var5 = var4;
        }

        return BlockCloth.getBlockFromDye(var5);
    }

    public int getMutantColor(FCEntitySheep var1, FCEntitySheep var2)
    {
        int var3 = this.rand.nextInt(3);

        switch (var3)
        {
            case 0:
                return 3;

            case 1:
                return 5;

            default:
                return 6;
        }
    }

    private void DropMutton(int var1)
    {
        if (!this.HasHeadCrabbedSquid() && !this.IsStarving())
        {
            int var2 = this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + var1);

            if (this.IsFamished())
            {
                var2 /= 2;
            }

            for (int var3 = 0; var3 < var2; ++var3)
            {
                if (this.isBurning())
                {
                    this.dropItem(FCBetterThanWolves.fcItemMeatBurned.itemID, 1);
                }
                else
                {
                    this.dropItem(FCBetterThanWolves.fcItemMuttonRaw.itemID, 1);
                }
            }
        }
    }

    private boolean CheckForWolfBomb()
    {
        if (!this.worldObj.isRemote && this.worldObj.worldInfo.getWorldTime() % 20L == 0L)
        {
            int var1 = MathHelper.floor_double(this.posX);
            int var2 = MathHelper.floor_double(this.posY);
            int var3 = MathHelper.floor_double(this.posZ);
            int var4 = this.worldObj.getPrecipitationHeight(var1, var3) - 1;

            if (var2 - var4 >= 16)
            {
                int var5 = this.worldObj.getBlockId(var1, var4, var3);
                Block var6 = Block.blocksList[var5];

                if (var6 != null && !var6.blockMaterial.isLiquid() && this.IsPossessableWolfWithinRangeOfBlock(var1, var4, var3, 8))
                {
                    this.InitiateWolfBomb();
                    this.worldObj.playSoundAtEntity(this, this.getDeathSound(), this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                    this.worldObj.playSoundAtEntity(this, "mob.slime.attack", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.6F);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean IsPossessableWolfWithinRangeOfBlock(int var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = AxisAlignedBB.getAABBPool().getAABB((double)(var1 - var4), (double)(var2 - var4), (double)(var3 - var4), (double)(var1 + 1 + var4), (double)(var2 + 1 + var4), (double)(var3 + 1 + var4));
        List var6 = this.worldObj.getEntitiesWithinAABB(FCEntityWolf.class, var5);
        Iterator var7 = var6.iterator();
        FCEntityWolf var8;

        do
        {
            if (!var7.hasNext())
            {
                return false;
            }

            var8 = (FCEntityWolf)var7.next();
        }
        while (var8.isLivingDead || var8.IsPossessed());

        return true;
    }

    private void InitiateWolfBomb()
    {
        this.setSheared(true);
        int var1 = 1 + this.rand.nextInt(3);

        for (int var2 = 0; var2 < var1; ++var2)
        {
            EntityItem var3 = this.entityDropItem(new ItemStack(FCBetterThanWolves.fcItemWool.itemID, 1, BlockCloth.getDyeFromBlock(this.getFleeceColor())), 1.0F);
            var3.motionY += (double)(this.rand.nextFloat() * 0.05F);
            var3.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
            var3.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
        }
    }

    protected boolean IsTooHungryToProduceWool()
    {
        return this.m_iHungerCountdown < 18000;
    }

    public void handleHealthUpdate(byte var1)
    {
        if (var1 == 10)
        {
            this.m_iGrazeProgressCounter = this.GetGrazeDuration();
        }
        else
        {
            super.handleHealthUpdate(var1);
        }
    }

    /**
     * Returns the texture's file path as a String.
     */
    public String getTexture()
    {
        if (this.getWearingBreedingHarness())
        {
            return "/btwmodtex/fc_mr_sheep.png";
        }
        else
        {
            int var1 = this.GetHungerLevel();
            return var1 == 1 ? "/btwmodtex/fcSheepFamished.png" : (var1 == 2 ? "/btwmodtex/fcSheepStarving.png" : super.getTexture());
        }
    }

    public float func_70894_j(float var1)
    {
        return this.GetGrazeHeadVerticalOffset(var1);
    }

    public float func_70890_k(float var1)
    {
        return this.GetGrazeHeadRotation(var1);
    }
}
