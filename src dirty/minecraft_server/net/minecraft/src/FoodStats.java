package net.minecraft.src;

public class FoodStats
{
    /** The player's food level. */
    private int foodLevel = 60;

    /** The player's food saturation. */
    private float foodSaturationLevel = 0.0F;

    /** The player's food exhaustion. */
    private float foodExhaustionLevel;

    /** The player's food timer value. */
    private int foodTimer = 0;
    private int prevFoodLevel = 60;

    /**
     * Eat some food.
     */
    public void addStats(ItemFood par1ItemFood)
    {
        this.addStats(par1ItemFood.GetHungerRestored(), par1ItemFood.getSaturationModifier());
    }

    /**
     * Reads the food data for the player.
     */
    public void readNBT(NBTTagCompound par1NBTTagCompound)
    {
        if (par1NBTTagCompound.hasKey("foodLevel"))
        {
            this.foodLevel = par1NBTTagCompound.getInteger("foodLevel");
            this.foodTimer = par1NBTTagCompound.getInteger("foodTickTimer");
            this.foodSaturationLevel = par1NBTTagCompound.getFloat("foodSaturationLevel");
            this.foodExhaustionLevel = par1NBTTagCompound.getFloat("foodExhaustionLevel");

            if (!par1NBTTagCompound.hasKey("fcFoodLevelAdjusted"))
            {
                this.foodLevel *= 3;
                this.foodSaturationLevel = 0.0F;
            }

            if (this.foodLevel > 60 || this.foodLevel < 0)
            {
                this.foodLevel = 60;
            }

            if (this.foodSaturationLevel > 20.0F || this.foodSaturationLevel < 0.0F)
            {
                this.foodSaturationLevel = 20.0F;
            }
        }
    }

    /**
     * Writes the food data for the player.
     */
    public void writeNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setInteger("foodLevel", this.foodLevel);
        par1NBTTagCompound.setInteger("foodTickTimer", this.foodTimer);
        par1NBTTagCompound.setFloat("foodSaturationLevel", this.foodSaturationLevel);
        par1NBTTagCompound.setFloat("foodExhaustionLevel", this.foodExhaustionLevel);
        par1NBTTagCompound.setBoolean("fcFoodLevelAdjusted", true);
    }

    /**
     * Get the player's food level.
     */
    public int getFoodLevel()
    {
        return this.foodLevel;
    }

    /**
     * Get whether the player must eat food.
     */
    public boolean needFood()
    {
        return this.foodLevel < 60;
    }

    /**
     * adds input to foodExhaustionLevel to a max of 40
     */
    public void addExhaustion(float par1)
    {
        this.foodExhaustionLevel = Math.min(this.foodExhaustionLevel + par1, 40.0F);
    }

    /**
     * Get the player's food saturation level.
     */
    public float getSaturationLevel()
    {
        return this.foodSaturationLevel;
    }

    public void setFoodLevel(int var1)
    {
        this.foodLevel = var1;
    }

    public void setFoodSaturationLevel(float var1)
    {
        this.foodSaturationLevel = var1;
    }

    /**
     * Args: int foodLevel, float foodSaturationModifier
     */
    public void addStats(int par1, float par2)
    {
        int var3 = this.foodLevel;
        this.foodLevel = Math.min(par1 + this.foodLevel, 60);
        int var4 = par1 - (this.foodLevel - var3);

        if (var4 > 0)
        {
            this.foodSaturationLevel = Math.min(this.foodSaturationLevel + (float)var4 * par2 / 3.0F, 20.0F);
        }
    }

    /**
     * Handles the food game logic.
     */
    public void onUpdate(EntityPlayer par1EntityPlayer)
    {
        int var2 = par1EntityPlayer.worldObj.difficultySetting;
        this.prevFoodLevel = this.foodLevel;

        if (var2 > 0)
        {
            while (this.foodLevel > 0 && this.foodExhaustionLevel >= 1.33F && !this.ShouldBurnFatBeforeHunger())
            {
                --this.foodExhaustionLevel;
                this.foodLevel = Math.max(this.foodLevel - 1, 0);
            }

            while (this.foodExhaustionLevel >= 0.5F && this.ShouldBurnFatBeforeHunger())
            {
                this.foodExhaustionLevel -= 0.5F;
                this.foodSaturationLevel = Math.max(this.foodSaturationLevel - 0.125F, 0.0F);
            }
        }
        else
        {
            this.foodExhaustionLevel = 0.0F;
        }

        if (this.foodLevel > 24 && par1EntityPlayer.shouldHeal())
        {
            ++this.foodTimer;

            if (this.foodTimer >= 600)
            {
                par1EntityPlayer.heal(1);
                this.foodTimer = 0;
            }
        }
        else if (this.foodLevel <= 0 && this.foodSaturationLevel <= 0.01F)
        {
            ++this.foodTimer;

            if (this.foodTimer >= 80)
            {
                if (var2 > 0)
                {
                    par1EntityPlayer.attackEntityFrom(DamageSource.starve, 1);
                }

                this.foodTimer = 0;
            }

            this.foodExhaustionLevel = 0.0F;
        }
        else
        {
            this.foodTimer = 0;
        }
    }

    private boolean ShouldBurnFatBeforeHunger()
    {
        return this.foodSaturationLevel > (float)((this.foodLevel + 5) / 6) * 2.0F;
    }
}
