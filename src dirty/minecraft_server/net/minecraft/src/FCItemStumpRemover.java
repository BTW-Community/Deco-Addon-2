package net.minecraft.src;

public class FCItemStumpRemover extends Item
{
    public FCItemStumpRemover(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(false);
        this.maxStackSize = 16;
        this.SetBuoyant();
        this.SetBellowsBlowDistance(1);
        this.SetFilterableProperties(2);
        this.setUnlocalizedName("fcItemStumpRemover");
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var2 != null && !var2.canPlayerEdit(var4, var5, var6, var7, var1))
        {
            return false;
        }
        else if (var1.stackSize == 0)
        {
            return false;
        }
        else
        {
            int var11 = var3.getBlockId(var4, var5, var6);
            Block var12 = Block.blocksList[var11];

            if (var12 != null && var12.GetDoesStumpRemoverWorkOnBlock(var3, var4, var5, var6))
            {
                if (!var3.isRemote)
                {
                    var3.setBlockWithNotify(var4, var5, var6, 0);
                    var3.playAuxSFX(2267, var4, var5, var6, 0);
                }

                --var1.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
