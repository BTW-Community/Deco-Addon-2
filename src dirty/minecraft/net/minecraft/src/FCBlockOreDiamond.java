package net.minecraft.src;

import java.util.Random;

public class FCBlockOreDiamond extends FCBlockOreStaged
{
    public FCBlockOreDiamond(int var1)
    {
        super(var1);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.diamond.itemID;
    }

    public int IdDroppedOnConversion(int var1)
    {
        return Item.diamond.itemID;
    }

    public int GetRequiredToolLevelForOre(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }
}
