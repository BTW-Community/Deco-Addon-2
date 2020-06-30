package net.minecraft.src;

public class FCItemFishingRodBaited extends FCItemFishingRod
{
    public FCItemFishingRodBaited(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcItemFishingRodBaited");
        this.setCreativeTab((CreativeTabs)null);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.fishEntity != null)
        {
            int var4 = var3.fishEntity.catchFish();
            var1 = var3.getCurrentEquippedItem();
            var1.damageItem(var4, var3);
            var3.swingItem();
        }
        else
        {
            var2.playSoundAtEntity(var3, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!var2.isRemote)
            {
                var2.spawnEntityInWorld(new EntityFishHook(var2, var3, true));
            }

            var3.swingItem();
        }

        return var1;
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            var3.playSound("mob.slime.attack", 0.25F, (var2.rand.nextFloat() - var2.rand.nextFloat()) * 0.1F + 0.7F);
        }
    }
}
