package net.minecraft.src;

public class FCItemBlockMoulding extends ItemBlock
{
    public FCItemBlockMoulding(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Block.blocksList[this.getBlockID()].getUnlocalizedName());
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack var1)
    {
        return super.getUnlocalizedName() + "." + "moulding";
    }
}
