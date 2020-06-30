package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class FCItemPotion extends ItemPotion
{
    public FCItemPotion(int var1)
    {
        super(var1);
        this.SetNeutralBuoyant();
        this.setMaxStackSize(8);
        this.setUnlocalizedName("potion");
    }

    public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (!var3.capabilities.isCreativeMode)
        {
            --var1.stackSize;
        }

        if (!var2.isRemote)
        {
            List var4 = this.getEffects(var1);

            if (var4 != null)
            {
                Iterator var5 = var4.iterator();

                while (var5.hasNext())
                {
                    PotionEffect var6 = (PotionEffect)var5.next();
                    var3.addPotionEffect(new PotionEffect(var6));
                }
            }
        }

        if (!var3.capabilities.isCreativeMode)
        {
            ItemStack var7 = new ItemStack(Item.glassBottle);

            if (!var3.inventory.addItemStackToInventory(var7))
            {
                var3.dropPlayerItem(var7);
            }
        }

        return var1;
    }

    public boolean IsMultiUsePerClick()
    {
        return false;
    }

    public boolean OnItemUsedByBlockDispenser(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        if (ItemPotion.isSplash(var1.getItemDamage()))
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

            EntityPotion var16 = new EntityPotion(var2, var8, var10, var12, new ItemStack(Item.potion, 1, var1.getItemDamage()));
            var16.setThrowableHeading((double)var7.i, var14, (double)var7.k, 1.375F, 3.0F);
            var2.spawnEntityInWorld(var16);
            var2.playAuxSFX(1002, var3, var4, var5, 0);
            return true;
        }
        else
        {
            return super.OnItemUsedByBlockDispenser(var1, var2, var3, var4, var5, var6);
        }
    }
}
