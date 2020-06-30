package net.minecraft.src;

public class FCItemBow extends ItemBow
{
    public FCItemBow(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetFurnaceBurnTime(3 * FCEnumFurnaceBurnTime.SHAFT.m_iBurnTime);
        this.SetIncineratedInCrucible();
        this.SetInfernalMaxEnchantmentCost(30);
        this.SetInfernalMaxNumEnchants(3);
        this.setUnlocalizedName("bow");
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack var1, World var2, EntityPlayer var3, int var4)
    {
        ItemStack var5 = this.GetFirstArrowStackInHotbar(var3);
        boolean var6 = var3.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, var1) > 0;

        if (var5 != null || var6)
        {
            float var7 = this.GetCurrentPullStrength(var3, var1, var4);

            if (var7 < 0.1F)
            {
                return;
            }

            Object var8;

            if (var5 != null)
            {
                var8 = this.CreateArrowEntityForItem(var2, var3, var5.itemID, var7);
                var3.inventory.consumeInventoryItem(var5.itemID);
            }
            else
            {
                var8 = new FCEntityInfiniteArrow(var2, var3, var7 * this.GetPullStrengthToArrowVelocityMultiplier());
            }

            if (var8 != null)
            {
                if (var7 == 1.0F)
                {
                    ((EntityArrow)var8).setIsCritical(true);
                }

                this.ApplyBowEnchantmentsToArrow(var1, (EntityArrow)var8);

                if (!var2.isRemote)
                {
                    var2.spawnEntityInWorld((Entity)var8);
                }
            }

            var1.damageItem(1, var3);
            this.PlayerBowSound(var2, var3, var7);

            if (var1.stackSize == 0)
            {
                var3.inventory.mainInventory[var3.inventory.currentItem] = null;
            }
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.capabilities.isCreativeMode || this.GetFirstArrowStackInHotbar(var3) != null || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, var1) > 0)
        {
            var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
        }

        return var1;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 0;
    }

    public boolean IsEnchantmentApplicable(Enchantment var1)
    {
        return var1.type == EnumEnchantmentType.bow ? true : super.IsEnchantmentApplicable(var1);
    }

    public void OnUsedInCrafting(EntityPlayer var1, ItemStack var2)
    {
        if (var2.itemID == Item.stick.itemID)
        {
            if (!var1.worldObj.isRemote)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(var1.worldObj, var1.posX, var1.posY, var1.posZ, new ItemStack(Item.silk));
            }

            if (var1.m_iTimesCraftedThisTick == 0)
            {
                var1.playSound("random.bow", 0.25F, var1.worldObj.rand.nextFloat() * 0.25F + 1.5F);
            }
        }
    }

    protected float GetCurrentPullStrength(EntityPlayer var1, ItemStack var2, int var3)
    {
        int var4 = this.getMaxItemUseDuration(var2) - var3;
        float var5 = (float)var4 / 20.0F;
        var5 = (var5 * var5 + var5 * 2.0F) / 3.0F;

        if (var5 > 1.0F)
        {
            var5 = 1.0F;
        }

        var5 *= var1.GetBowPullStrengthModifier();
        return var5;
    }

    public ItemStack GetFirstArrowStackInHotbar(EntityPlayer var1)
    {
        for (int var2 = 0; var2 < 9; ++var2)
        {
            ItemStack var3 = var1.inventory.getStackInSlot(var2);

            if (var3 != null && this.CanItemBeFiredAsArrow(var3.itemID))
            {
                return var3;
            }
        }

        return null;
    }

    public boolean CanItemBeFiredAsArrow(int var1)
    {
        return var1 == Item.arrow.itemID || var1 == FCBetterThanWolves.fcItemRottenArrow.itemID;
    }

    public float GetPullStrengthToArrowVelocityMultiplier()
    {
        return 2.0F;
    }

    protected EntityArrow CreateArrowEntityForItem(World var1, EntityPlayer var2, int var3, float var4)
    {
        Object var5 = null;

        if (var3 == FCBetterThanWolves.fcItemRottenArrow.itemID)
        {
            var5 = new FCEntityRottenArrow(var1, var2, var4 * 0.55F * this.GetPullStrengthToArrowVelocityMultiplier());
        }
        else if (var3 == Item.arrow.itemID)
        {
            var5 = new EntityArrow(var1, var2, var4 * this.GetPullStrengthToArrowVelocityMultiplier());
        }

        return (EntityArrow)var5;
    }

    protected void ApplyBowEnchantmentsToArrow(ItemStack var1, EntityArrow var2)
    {
        int var3 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, var1);

        if (var3 > 0)
        {
            var2.setDamage(var2.getDamage() + (double)var3 * 0.5D + 0.5D);
        }

        int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, var1);

        if (var4 > 0)
        {
            var2.setKnockbackStrength(var4);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, var1) > 0)
        {
            var2.setFire(100);
        }
    }

    protected void PlayerBowSound(World var1, EntityPlayer var2, float var3)
    {
        var1.playSoundAtEntity(var2, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var3 * 0.5F);
    }
}
