package net.minecraft.src;

public class FCItemBlockPlanter extends ItemBlock
{
    public FCItemBlockPlanter(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("fcBlockPlanter");
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1;
    }

    public String getItemDisplayName(ItemStack var1)
    {
        String var2 = super.getItemDisplayName(var1);
        int var3 = var1.getItemDamage();
        return var3 != 1 && var3 != 2 ? var2 : "Old " + var2;
    }
}
