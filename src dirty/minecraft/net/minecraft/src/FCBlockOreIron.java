package net.minecraft.src;

import java.util.Random;

public class FCBlockOreIron extends FCBlockOreStaged
{
    public FCBlockOreIron(int var1)
    {
        super(var1);
        this.SetCanBeCookedByKiln(true);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcItemChunkIronOre.itemID;
    }

    public int IdDroppedOnConversion(int var1)
    {
        return FCBetterThanWolves.fcItemPileIronOre.itemID;
    }

    public int GetRequiredToolLevelForOre(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 1;
    }

    public int GetItemIndexDroppedWhenCookedByKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return FCBetterThanWolves.fcItemNuggetIron.itemID;
    }

    public int GetCookTimeMultiplierInKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 8;
    }
}
