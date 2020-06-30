package net.minecraft.src;

public class AddonEntitySquid extends FCEntitySquid {
	public AddonEntitySquid(World var1) {
		super(var1);
	}

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getLivingSound()
    {
    	return AddonManager.getNewSoundsInstalled() ? "deco.mob.squid.say" : "";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getHurtSound()
    {
    	return AddonManager.getNewSoundsInstalled() ? "deco.mob.squid.hurt" : "";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
    	return AddonManager.getNewSoundsInstalled() ? "deco.mob.squid.death" : "";
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
	@Override
    protected void dropFewItems(boolean var1, int var2)
    {
        int var3 = this.rand.nextInt(3 + var2) + 1;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            this.entityDropItem(new ItemStack(Item.dyePowder, 1, 0), 0.0F);
        }

        var3 = this.rand.nextInt(3 + var2) + 1;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            this.entityDropItem(new ItemStack(AddonDefs.prismarineShard, 1, 0), 0.0F);
        }

        if (this.rand.nextInt(8) - var2 <= 0)
        {
            this.dropItem(FCBetterThanWolves.fcItemMysteriousGland.itemID, 1);
        }
    }
}