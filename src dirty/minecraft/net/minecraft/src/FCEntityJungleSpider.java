package net.minecraft.src;

public class FCEntityJungleSpider extends FCEntitySpider
{
    public FCEntityJungleSpider(World var1)
    {
        super(var1);
        this.texture = "/btwmodtex/fcSpiderJungle.png";
        this.setSize(0.7F, 0.5F);
    }

    public int getMaxHealth()
    {
        return 10;
    }

    public boolean attackEntityAsMob(Entity var1)
    {
        if (super.attackEntityAsMob(var1))
        {
            if (var1 instanceof EntityLiving)
            {
                byte var2 = 0;

                if (this.worldObj.difficultySetting > 1)
                {
                    if (this.worldObj.difficultySetting == 2)
                    {
                        var2 = 3;
                    }
                    else if (this.worldObj.difficultySetting == 3)
                    {
                        var2 = 7;
                    }
                }
                else
                {
                    var2 = 1;
                }

                if (var2 > 0)
                {
                    ((EntityLiving)var1).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 20, 0));
                    ((EntityLiving)var1).addPotionEffect(new PotionEffect(Potion.hunger.id, 600, 0));
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
     * Initialize this creature.
     */
    public void initCreature() {}

    public boolean DoesLightAffectAggessiveness()
    {
        return false;
    }

    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    protected boolean isValidLightLevel()
    {
        return true;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return (int)this.posY >= this.worldObj.provider.getAverageGroundLevel() - 5 ? super.getCanSpawnHere() : false;
    }

    /**
     * Gets the username of the entity.
     */
    public String getEntityName()
    {
        return "Jungle Spider";
    }

    protected boolean CanSpawnOnBlock(int var1)
    {
        return var1 == Block.leaves.blockID;
    }

    /**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
    public float getBlockPathWeight(int var1, int var2, int var3)
    {
        return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.leaves.blockID ? 10.0F : super.getBlockPathWeight(var1, var2, var3);
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch()
    {
        return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.7F;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.25F;
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return 80 + this.rand.nextInt(240);
    }

    public boolean DoEyesGlow()
    {
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        return var1 == DamageSource.fall ? false : super.attackEntityFrom(var1, var2);
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        super.dropFewItems(var1, var2);

        if (this.rand.nextInt(16) - (var2 << 1) <= 0)
        {
            this.dropItem(Item.fermentedSpiderEye.itemID, 1);
        }
    }

    protected boolean DropsSpiderEyes()
    {
        return false;
    }

    protected void CheckForSpiderSkeletonMounting() {}

    /**
     * How large the spider should be scaled.
     */
    public float spiderScaleAmount()
    {
        return 0.7F;
    }
}
