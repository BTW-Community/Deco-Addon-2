package net.minecraft.src;

import java.util.List;

public class FCItemEmptyMap extends ItemEmptyMap
{
    protected FCItemEmptyMap(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
        this.SetBuoyant();
        this.SetFilterableProperties(16);
        this.setUnlocalizedName("emptyMap");
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        ItemStack var4 = new ItemStack(Item.map, 1, var2.getUniqueDataId("map"));
        String var5 = "map_" + var4.getItemDamage();
        MapData var6 = new MapData(var5);
        var2.setItemData(var5, var6);
        var6.scale = (byte)var1.getItemDamage();
        int var7 = 128 * (1 << var6.scale);
        var6.xCenter = (int)(Math.round(var3.posX / (double)var7) * (long)var7);
        var6.zCenter = (int)(Math.round(var3.posZ / (double)var7) * (long)var7);
        var6.dimension = (byte)var2.provider.dimensionId;
        var6.markDirty();
        --var1.stackSize;

        if (var1.stackSize <= 0)
        {
            return var4;
        }
        else
        {
            if (!var3.inventory.addItemStackToInventory(var4.copy()))
            {
                var3.dropPlayerItem(var4);
            }

            return var1;
        }
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4)
    {
        var3.add("Scale: x" + (1 << var1.getItemDamage()));
    }
}
