package net.minecraft.src;

public class DecoItemBlockWoodSidingStub extends ItemBlock {
    public DecoItemBlockWoodSidingStub(int var1)
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
            case 4:
                return FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner.blockID;
            case 5:
            	return DecoDefs.cherrySidingAndCorner.blockID;
            default:
                return FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID;
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
        return var1.getItemDamage() == 0 ? super.getUnlocalizedName() + "." + "oak" : (var1.getItemDamage() == 1 ? super.getUnlocalizedName() + "." + "spruce" : (var1.getItemDamage() == 2 ? super.getUnlocalizedName() + "." + "birch" : (var1.getItemDamage() == 3 ? super.getUnlocalizedName() + "." + "jungle" : (var1.getItemDamage() == 4 ? super.getUnlocalizedName() + "." + "blood" : super.getUnlocalizedName() + "." + "cherry"))));
    }

    public int GetFurnaceBurnTime(int var1)
    {
        return DecoBlockPlanks.GetFurnaceBurnTimeByWoodType(var1) / 2;
    }
}
