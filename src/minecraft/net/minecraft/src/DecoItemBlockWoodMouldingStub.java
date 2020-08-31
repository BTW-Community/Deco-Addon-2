package net.minecraft.src;

public class DecoItemBlockWoodMouldingStub extends ItemBlock
{
    public DecoItemBlockWoodMouldingStub(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
    }

    public int GetBlockIDToPlace(int var1, int var2, float var3, float var4, float var5)
    {
        switch (var1)
        {
            case 0:
                return FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID;
            case 1:
                return FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID;
            case 2:
                return FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative.blockID;
            case 3:
                return FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative.blockID;
            case 4:
                return FCBetterThanWolves.fcBlockWoodBloodMouldingAndDecorative.blockID;
            case 5:
            	return DecoDefs.cherryMouldingAndDecorative.blockID;
            default:
                return FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID;
        }
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
        return DecoBlockPlanks.GetFurnaceBurnTimeByWoodType(var1) / 4;
    }
}
