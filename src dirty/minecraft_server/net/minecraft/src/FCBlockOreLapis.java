package net.minecraft.src;

import java.util.Random;

public class FCBlockOreLapis extends FCBlockOreStaged
{
    public FCBlockOreLapis(int var1)
    {
        super(var1);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.dyePowder.itemID;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 4 + var1.nextInt(5);
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return 4;
    }

    public int IdDroppedOnConversion(int var1)
    {
        return Item.dyePowder.itemID;
    }

    public int QuantityDroppedOnConversion(Random var1)
    {
        return 4 + var1.nextInt(5);
    }

    public int DamageDroppedOnConversion(int var1)
    {
        return 4;
    }

    public int GetRequiredToolLevelForOre(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 1;
    }
}
