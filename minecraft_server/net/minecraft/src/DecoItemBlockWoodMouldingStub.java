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
            case 6:
            	return DecoDefs.acaciaMouldingAndDecorative.blockID;
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
    	int meta = var1.getItemDamage();
    	String name = super.getUnlocalizedName() + ".";
    	
    	switch (meta) {
    	case 0:
    		return name + "oak";
    	case 1:
    		return name + "spruce";
    	case 2:
    		return name + "birch";
    	case 3:
    		return name + "jungle";
    	case 4:
    		return name + "blood";
    	case 5:
    		return name + "cherry";
    	case 6:
    		return name + "acacia";
    	default:
    		return name + "oak";
    	}
    }

    public int GetFurnaceBurnTime(int var1)
    {
        return DecoBlockPlanks.GetFurnaceBurnTimeByWoodType(var1) / 4;
    }
}
