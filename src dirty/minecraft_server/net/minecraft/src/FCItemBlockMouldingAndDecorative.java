package net.minecraft.src;

public class FCItemBlockMouldingAndDecorative extends FCItemBlockMoulding
{
    public FCItemBlockMouldingAndDecorative(int var1)
    {
        super(var1);
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
        switch (var1.getItemDamage())
        {
            case 12:
                return super.getUnlocalizedName() + "." + "column";

            case 13:
            case 14:
                return super.getUnlocalizedName() + "." + "pedestal";

            case 15:
                return super.getUnlocalizedName() + "." + "table";

            default:
                return super.getUnlocalizedName(var1);
        }
    }
}
