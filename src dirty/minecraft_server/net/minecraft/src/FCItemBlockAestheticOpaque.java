package net.minecraft.src;

public class FCItemBlockAestheticOpaque extends ItemBlock
{
    public FCItemBlockAestheticOpaque(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("fcAestheticOpaque");
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1 == 2 ? 0 : var1;
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
                return super.getUnlocalizedName() + "." + "wicker";

            case 1:
                return super.getUnlocalizedName() + "." + "dung";

            case 2:
                return super.getUnlocalizedName() + "." + "steel";

            case 3:
                return super.getUnlocalizedName() + "." + "hellfire";

            case 4:
                return super.getUnlocalizedName() + "." + "padding";

            case 5:
                return super.getUnlocalizedName() + "." + "soap";

            case 6:
                return super.getUnlocalizedName() + "." + "rope";

            case 7:
                return super.getUnlocalizedName() + "." + "flint";

            case 8:
            default:
                return super.getUnlocalizedName();

            case 9:
                return super.getUnlocalizedName() + "." + "whitestone";

            case 10:
                return super.getUnlocalizedName() + "." + "whitecobble";

            case 11:
                return super.getUnlocalizedName() + "." + "barrel";

            case 12:
            case 13:
                return super.getUnlocalizedName() + "." + "choppingblock";

            case 14:
                return super.getUnlocalizedName() + "." + "enderblock";

            case 15:
                return super.getUnlocalizedName() + "." + "bone";
        }
    }

    public int GetBlockIDToPlace(int var1, int var2, float var3, float var4, float var5)
    {
        return var1 == 2 ? FCBetterThanWolves.fcSoulforgedSteelBlock.blockID : super.GetBlockIDToPlace(var1, var2, var3, var4, var5);
    }

    public float GetBuoyancy(int var1)
    {
        switch (var1)
        {
            case 0:
            case 1:
            case 4:
            case 5:
            case 6:
            case 15:
                return 1.0F;

            case 2:
            case 3:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            default:
                return super.GetBuoyancy(var1);
        }
    }
}
