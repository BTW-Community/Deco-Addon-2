package net.minecraft.src;

public abstract class FCItemDoor extends Item
{
    public FCItemDoor(int var1)
    {
        super(var1);
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var7 == 1)
        {
            ++var5;

            if (var2.canPlayerEdit(var4, var5, var6, var7, var1) && var2.canPlayerEdit(var4, var5 + 1, var6, var7, var1) && this.GetDoorBlock().canPlaceBlockAt(var3, var4, var5, var6))
            {
                int var11 = MathHelper.floor_double((double)((var2.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
                ItemDoor.placeDoorBlock(var3, var4, var5, var6, var11, this.GetDoorBlock());
                --var1.stackSize;
                return true;
            }
        }

        return false;
    }

    public abstract Block GetDoorBlock();
}
