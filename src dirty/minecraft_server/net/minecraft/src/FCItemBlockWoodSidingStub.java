package net.minecraft.src;

public class FCItemBlockWoodSidingStub extends ItemBlock
{
    public FCItemBlockWoodSidingStub(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
    }

    public int GetBlockIDToPlace(int var1, int var2, float var3, float var4, float var5)
    {
        switch (var1)
        {
            case 0:
                return FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID;

            case 1:
                return FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID;

            case 2:
                return FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID;

            case 3:
                return FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID;

            default:
                return FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner.blockID;
        }
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return 0;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack var1)
    {
        return var1.getItemDamage() == 0 ? super.getUnlocalizedName() + "." + "oak" : (var1.getItemDamage() == 1 ? super.getUnlocalizedName() + "." + "spruce" : (var1.getItemDamage() == 2 ? super.getUnlocalizedName() + "." + "birch" : (var1.getItemDamage() == 3 ? super.getUnlocalizedName() + "." + "jungle" : super.getUnlocalizedName() + "." + "blood")));
    }

    public int GetFurnaceBurnTime(int var1)
    {
        return FCBlockPlanks.GetFurnaceBurnTimeByWoodType(var1) / 2;
    }
}
