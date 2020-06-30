package net.minecraft.src;

public class FCItemBlockAestheticOpaqueEarth extends ItemBlock
{
    public FCItemBlockAestheticOpaqueEarth(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("fcAestheticOpaqueEarth");
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
            case 0:
            case 1:
            case 2:
                return super.getUnlocalizedName() + "." + "blight";

            case 3:
            case 5:
            default:
                return super.getUnlocalizedName();

            case 4:
                return super.getUnlocalizedName() + "." + "blight3";

            case 6:
                return super.getUnlocalizedName() + "." + "packed";

            case 7:
                return super.getUnlocalizedName() + "." + "dung";
        }
    }
}
