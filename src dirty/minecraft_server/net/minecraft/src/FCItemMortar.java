package net.minecraft.src;

public class FCItemMortar extends Item
{
    public FCItemMortar(int var1)
    {
        super(var1);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var2 != null && var2.canPlayerEdit(var4, var5, var6, var7, var1))
        {
            Block var11 = Block.blocksList[var3.getBlockId(var4, var5, var6)];

            if (var11 != null && var11.OnMortarApplied(var3, var4, var5, var6))
            {
                if (!var3.isRemote)
                {
                    var3.playAuxSFX(2274, var4, var5, var6, 0);
                }

                --var1.stackSize;
                return true;
            }
        }

        return false;
    }
}
