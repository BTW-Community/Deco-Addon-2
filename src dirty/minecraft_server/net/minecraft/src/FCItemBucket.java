package net.minecraft.src;

public abstract class FCItemBucket extends FCItemPlacesAsBlock
{
    public FCItemBucket(int var1)
    {
        super(var1);
        this.setMaxStackSize(1);
        this.setContainerItem(Item.bucketEmpty);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    /**
     * Returns the blockID for this Item
     */
    public abstract int getBlockID();

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        return this.DoesContextOverridePlacingAsBlock(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10) ? false : super.onItemUse(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
    }

    public boolean IsMultiUsePerClick()
    {
        return false;
    }

    public boolean DoesContextOverridePlacingAsBlock(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        return true;
    }
}
