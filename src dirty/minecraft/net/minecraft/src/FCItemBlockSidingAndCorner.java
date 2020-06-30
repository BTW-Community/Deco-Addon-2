package net.minecraft.src;

public class FCItemBlockSidingAndCorner extends ItemBlock
{
    public FCItemBlockSidingAndCorner(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Block.blocksList[this.getBlockID()].getUnlocalizedName());
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
        return var1.getItemDamage() == 12 ? super.getUnlocalizedName() + "." + "bench" : (var1.getItemDamage() == 14 ? super.getUnlocalizedName() + "." + "fence" : (var1.getItemDamage() == 0 ? super.getUnlocalizedName() + "." + "siding" : super.getUnlocalizedName() + "." + "corner"));
    }
}
