package net.minecraft.src;

public class DecoItemBlockWoodMouldingDecorativeStub extends ItemBlock
{
    public static final int m_iTypeColumn = 0;
    public static final int m_iTypePedestal = 1;
    public static final int m_iTypeTable = 2;

    public DecoItemBlockWoodMouldingDecorativeStub(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
    }

    public int GetBlockIDToPlace(int var1, int var2, float var3, float var4, float var5)
    {
        int var6 = GetWoodType(var1);

        switch (var6)
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
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        int var2 = GetBlockType(var1);
        return var2 == 0 ? 12 : (var2 == 1 ? 13 : 15);
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack var1)
    {
        int var2 = GetWoodType(var1.getItemDamage());
        String var3 = "oak";

        if (var2 == 0)
        {
            var3 = "oak";
        }
        else if (var2 == 1)
        {
            var3 = "spruce";
        }
        else if (var2 == 2)
        {
            var3 = "birch";
        }
        else if (var2 == 3)
        {
            var3 = "jungle";
        }
        else if (var2 == 4)
        {
            var3 = "blood";
        }
        else if (var2 == 5)
        {
        	var3 = "cherry";
        }

        int var4 = GetBlockType(var1.getItemDamage());
        String var5;

        if (var4 == 0)
        {
            var5 = "column";
        }
        else if (var4 == 1)
        {
            var5 = "pedestal";
        }
        else
        {
            var5 = "table";
        }

        return super.getUnlocalizedName() + "." + var3 + "." + var5;
    }

    public int GetFurnaceBurnTime(int var1)
    {
        return DecoBlockPlanks.GetFurnaceBurnTimeByWoodType(GetWoodType(var1)) / 2;
    }

    public static int GetWoodType(int var0)
    {
        int var1 = var0 & 3 | var0 >> 4 << 2;
        return var1;
    }

    public static int GetBlockType(int var0)
    {
        int var1 = var0 >> 2 & 3;
        return var1;
    }

    public static int GetItemDamageForType(int var0, int var1)
    {
        int var2 = var0 & 3 | var0 >> 2 << 4 | var1 << 2;
        return var2;
    }
}
