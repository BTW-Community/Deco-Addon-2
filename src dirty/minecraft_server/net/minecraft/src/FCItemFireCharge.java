package net.minecraft.src;

public class FCItemFireCharge extends ItemFireball
{
    public FCItemFireCharge(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fireball");
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (!var3.isRemote)
        {
            Block var11 = Block.blocksList[var3.getBlockId(var4, var5, var6)];

            if (var11 != null && var11.GetCanBeSetOnFireDirectlyByItem(var3, var4, var5, var6))
            {
                var11.SetOnFireDirectly(var3, var4, var5, var6);

                if (!var2.capabilities.isCreativeMode)
                {
                    --var1.stackSize;
                }

                return true;
            }
        }

        return super.onItemUse(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
    }
}
