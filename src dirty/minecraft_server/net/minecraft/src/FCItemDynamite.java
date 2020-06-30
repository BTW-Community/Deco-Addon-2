package net.minecraft.src;

public class FCItemDynamite extends Item
{
    public FCItemDynamite(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.setUnlocalizedName("fcItemDynamite");
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        int var4 = -1;

        for (int var5 = 0; var5 < var3.inventory.mainInventory.length; ++var5)
        {
            if (var3.inventory.mainInventory[var5] != null && var3.inventory.mainInventory[var5].itemID == Item.flintAndSteel.itemID)
            {
                var4 = var5;
                break;
            }
        }

        if (!var2.isRemote)
        {
            boolean var7 = false;

            if (var4 >= 0)
            {
                var7 = true;
                ItemStack var6 = var3.inventory.getStackInSlot(var4);
                var6.damageItem(1, var3);

                if (var6.stackSize <= 0)
                {
                    var3.inventory.mainInventory[var4] = null;
                }
            }

            --var1.stackSize;
            FCEntityDynamite var8 = new FCEntityDynamite(var2, var3, this.itemID, var7);
            var2.spawnEntityInWorld(var8);

            if (var7)
            {
                var2.playSoundAtEntity(var8, "random.fuse", 1.0F, 1.0F);
            }
            else
            {
                var2.playSoundAtEntity(var8, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            }
        }

        return var1;
    }

    public boolean OnItemUsedByBlockDispenser(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(0, 0, 0, var6);
        double var8 = (double)var3 + (double)var7.i * 0.6D + 0.5D;
        double var10 = (double)var4 + (double)var7.j * 0.6D + 0.5D;
        double var12 = (double)var5 + (double)var7.k * 0.6D + 0.5D;
        double var14;

        if (var6 > 2)
        {
            var14 = 0.10000000149011612D;
        }
        else
        {
            var14 = (double)var7.j;
        }

        FCEntityDynamite var16 = new FCEntityDynamite(var2, var8, var10, var12, this.itemID);
        var16.setThrowableHeading((double)var7.i, var14, (double)var7.k, 1.1F, 6.0F);
        var2.spawnEntityInWorld(var16);
        var2.playAuxSFX(1002, var3, var4, var5, 0);
        return true;
    }
}
