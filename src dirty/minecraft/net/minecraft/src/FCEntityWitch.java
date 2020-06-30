package net.minecraft.src;

public class FCEntityWitch extends EntityWitch
{
    private static final int[] m_itemDrops = new int[] {FCBetterThanWolves.fcItemWitchWart.itemID, Item.glassBottle.itemID, Item.stick.itemID};

    public FCEntityWitch(World var1)
    {
        super(var1);
        this.tasks.RemoveAllTasksOfClass(EntityAIWander.class);
        this.tasks.addTask(2, new FCEntityAIWanderSimple(this, this.moveSpeed));
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        int var3 = this.rand.nextInt(3) + 1;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            int var5 = m_itemDrops[this.rand.nextInt(m_itemDrops.length)];
            int var6 = this.rand.nextInt(3);

            if (var2 > 0)
            {
                var6 += this.rand.nextInt(var2 + 1);
            }

            while (var6 > 0)
            {
                this.dropItem(var5, 1);
                --var6;
            }
        }
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return (int)this.posY >= this.worldObj.provider.getAverageGroundLevel() - 5 ? super.getCanSpawnHere() : false;
    }

    public void CheckForScrollDrop()
    {
        if (this.rand.nextInt(1000) == 0)
        {
            ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.aquaAffinity.effectId);
            this.entityDropItem(var1, 0.0F);
        }
    }

    /**
     * Plays living's sound at its position
     */
    public void playLivingSound()
    {
        String var1 = "mob.ghast.affectionate scream";

        if (var1 != null)
        {
            this.playSound(var1, this.getSoundVolume() * 0.25F, 0.5F + this.rand.nextFloat() * 0.25F);
        }
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLiving var1, float var2)
    {
        if (!this.IsConsumingPotion())
        {
            super.attackEntityWithRangedAttack(var1, var2);
            this.worldObj.playSoundAtEntity(this, "mob.wither.shoot", 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));
        }
    }

    /**
     * Set whether this witch is aggressive at an entity.
     */
    public void setAggressive(boolean var1)
    {
        super.setAggressive(var1);

        if (var1)
        {
            this.worldObj.playSoundAtEntity(this, "mob.wither.shoot", 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));
        }
    }

    public boolean IsConsumingPotion()
    {
        return this.getAggressive();
    }
}
