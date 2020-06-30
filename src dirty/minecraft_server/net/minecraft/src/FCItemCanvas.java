package net.minecraft.src;

public class FCItemCanvas extends Item
{
    public FCItemCanvas(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.setUnlocalizedName("fcItemCanvas");
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var7 != 0 && var7 != 1)
        {
            if (!var2.canPlayerEdit(var4, var5, var6, var7, var1))
            {
                return false;
            }
            else
            {
                byte var11 = 0;

                if (var7 == 4)
                {
                    var11 = 1;
                }
                else if (var7 == 3)
                {
                    var11 = 2;
                }
                else if (var7 == 5)
                {
                    var11 = 3;
                }

                FCEntityCanvas var12 = new FCEntityCanvas(var3, var4, var5, var6, var11);

                if (var12.onValidSurface())
                {
                    if (!var3.isRemote)
                    {
                        var3.spawnEntityInWorld(var12);
                    }

                    --var1.stackSize;
                }

                return true;
            }
        }
        else
        {
            return false;
        }
    }
}
