package net.minecraft.src;

public class FCItemBlockStairsWhiteStone extends ItemBlock
{
    public FCItemBlockStairsWhiteStone(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("fcBlockWhiteStoneStairs");
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack var1)
    {
        int var2 = var1.getItemDamage();
        return (var2 & 8) > 0 ? super.getUnlocalizedName() + "." + "cobble" : super.getUnlocalizedName() + "." + "smooth";
    }
}
