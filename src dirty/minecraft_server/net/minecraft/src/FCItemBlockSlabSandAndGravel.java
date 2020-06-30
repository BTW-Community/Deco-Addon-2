package net.minecraft.src;

public class FCItemBlockSlabSandAndGravel extends FCItemBlockSlab
{
    public FCItemBlockSlabSandAndGravel(int var1)
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
            case 1:
                return super.getUnlocalizedName() + "." + "sand";

            default:
                return super.getUnlocalizedName();
        }
    }
}
