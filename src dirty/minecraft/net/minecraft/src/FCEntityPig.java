package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class FCEntityPig extends EntityPig
{
    public FCEntityPig(World var1)
    {
        super(var1);
        this.tasks.RemoveAllTasks();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new FCEntityAIAnimalFlee(this, 0.38F));
        this.tasks.addTask(2, this.getAIControlledByPlayer());
        this.tasks.addTask(3, new EntityAIMate(this, 0.25F));
        this.tasks.addTask(4, new FCEntityAIMultiTempt(this, 0.3F));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 0.28F));
        this.tasks.addTask(6, new FCEntityAIGraze(this));
        this.tasks.addTask(7, new FCEntityAIMoveToLooseFood(this, 0.25F));
        this.tasks.addTask(8, new FCEntityAIMoveToGraze(this, 0.25F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(10, new EntityAILookIdle(this));
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
        if (!this.IsStarving() && !this.HasHeadCrabbedSquid())
        {
            int var3 = this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + var2);

            if (this.IsFamished())
            {
                var3 /= 2;
            }

            for (int var4 = 0; var4 < var3; ++var4)
            {
                if (this.isBurning())
                {
                    this.dropItem(FCBetterThanWolves.fcItemMeatBurned.itemID, 1);
                }
                else
                {
                    this.dropItem(Item.porkRaw.itemID, 1);
                }
            }
        }

        if (this.getSaddled())
        {
            this.dropItem(Item.saddle.itemID, 1);
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity var3 = var1.getEntity();

            if (var3 != null && var3 instanceof EntityPlayer)
            {
                EntityPlayer var4 = (EntityPlayer)var3;
                List var5 = this.worldObj.getEntitiesWithinAABB(FCEntityPigZombie.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D));
                Iterator var6 = var5.iterator();

                while (var6.hasNext())
                {
                    FCEntityPigZombie var7 = (FCEntityPigZombie)var6.next();

                    if (!var7.isLivingDead)
                    {
                        var7.BecomeAngryWhenPigAttacked(var4);
                    }
                }
            }

            return super.attackEntityFrom(var1, var2);
        }
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return this.HasHeadCrabbedSquid() ? (double)this.height * 1.2D : super.getMountedYOffset();
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack var1)
    {
        return var1.itemID == FCBetterThanWolves.fcItemChocolate.itemID;
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
        this.worldObj.playAuxSFX(2259, MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 0);
        this.setDead();
        FCEntityPigZombie var1 = new FCEntityPigZombie(this.worldObj);
        var1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        var1.renderYawOffset = this.renderYawOffset;
        var1.SetPersistent(true);
        var1.setCanPickUpLoot(true);
        this.worldObj.spawnEntityInWorld(var1);
    }

    public boolean IsValidZombieSecondaryTarget(EntityZombie var1)
    {
        return true;
    }

    public EntityPig spawnBabyAnimal(EntityAgeable var1)
    {
        return new FCEntityPig(this.worldObj);
    }

    public void OnStruckByLightning(FCEntityLightningBolt var1)
    {
        if (!this.worldObj.isRemote)
        {
            FCEntityPigZombie var2 = new FCEntityPigZombie(this.worldObj);
            var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.worldObj.spawnEntityInWorld(var2);
            this.setDead();
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return !this.IsStarving() ? "mob.pig.say" : "mob.pig.death";
    }

    public boolean IsSubjectToHunger()
    {
        return true;
    }

    protected int GetFoodValueMultiplier()
    {
        return 3;
    }

    public boolean GetDisruptsEarthOnGraze()
    {
        return true;
    }

    public boolean CanGrazeOnRoughVegetation()
    {
        return true;
    }

    public int GetGrazeDuration()
    {
        return 80;
    }

    protected int GetItemFoodValue(ItemStack var1)
    {
        return var1.getItem().GetPigFoodValue(var1.getItemDamage()) * this.GetFoodValueMultiplier();
    }

    protected float GetGrazeHeadRotationMagnitudeDivisor()
    {
        return 3.0F;
    }

    protected float GetGrazeHeadRotationRateMultiplier()
    {
        return 50.225002F;
    }

    /**
     * Returns the texture's file path as a String.
     */
    public String getTexture()
    {
        if (this.getWearingBreedingHarness())
        {
            return "/btwmodtex/fc_mr_pig.png";
        }
        else
        {
            int var1 = this.GetHungerLevel();
            return var1 == 1 ? "/btwmodtex/fcPigFamished.png" : (var1 == 2 ? "/btwmodtex/fcPigStarving.png" : super.getTexture());
        }
    }
}
