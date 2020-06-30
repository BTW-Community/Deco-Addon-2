package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class FCEntityPigZombie extends EntityPigZombie
{
    public FCEntityPigZombie(World var1)
    {
        super(var1);
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        boolean var3 = this.EntityMobAttackEntityFrom(var1, var2);

        if (!this.isEntityInvulnerable())
        {
            Entity var4 = var1.getEntity();

            if (var4 instanceof EntityPlayer)
            {
                this.becomeAngryAt(var4);
                this.AngerNearbyPigmen(var4);
            }
            else if (var1.isExplosion() || var4 instanceof FCEntityGhast)
            {
                EntityPlayer var5 = this.worldObj.getClosestPlayerToEntity(this, 16.0D);

                if (var5 != null)
                {
                    this.becomeAngryAt(var5);
                    this.AngerNearbyPigmen(var5);
                }
            }
        }

        return var3;
    }

    public void CheckForScrollDrop()
    {
        if (this.rand.nextInt(1000) == 0)
        {
            ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.fireProtection.effectId);
            this.entityDropItem(var1, 0.0F);
        }
    }

    protected void dropHead() {}

    /**
     * Makes entity wear random armor based on difficulty
     */
    protected void addRandomArmor()
    {
        if (this.rand.nextFloat() < 0.05F)
        {
            this.setCurrentItemOrArmor(0, new ItemStack(Item.swordGold));
            this.equipmentDropChances[0] = 0.99F;
        }
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        int var3 = this.rand.nextInt(2 + var2);
        int var4;

        for (var4 = 0; var4 < var3; ++var4)
        {
            this.dropItem(Item.rottenFlesh.itemID, 1);
        }

        if (this.rand.nextFloat() <= 0.1F || this.worldObj.provider.dimensionId == -1)
        {
            var4 = this.rand.nextInt(2 + var2);

            for (int var5 = 0; var5 < var4; ++var5)
            {
                this.dropItem(Item.goldNugget.itemID, 1);
            }
        }
    }

    protected void dropRareDrop(int var1) {}

    public void BecomeAngryWhenPigAttacked(Entity var1)
    {
        this.becomeAngryAt(var1);
    }

    protected void AngerNearbyPigmen(Entity var1)
    {
        List var2 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));
        Iterator var3 = var2.iterator();

        while (var3.hasNext())
        {
            Entity var4 = (Entity)var3.next();

            if (var4 instanceof FCEntityPigZombie)
            {
                FCEntityPigZombie var5 = (FCEntityPigZombie)var4;
                var5.becomeAngryAt(var1);
            }
        }
    }
}
