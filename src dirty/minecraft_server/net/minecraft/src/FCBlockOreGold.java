package net.minecraft.src;

import java.util.Random;

public class FCBlockOreGold extends FCBlockOreStaged
{
    public FCBlockOreGold(int var1)
    {
        super(var1);
        this.SetCanBeCookedByKiln(true);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcItemChunkGoldOre.itemID;
    }

    public int IdDroppedOnConversion(int var1)
    {
        return FCBetterThanWolves.fcItemPileGoldOre.itemID;
    }

    public int GetRequiredToolLevelForOre(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public int GetItemIndexDroppedWhenCookedByKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return Item.goldNugget.itemID;
    }

    public int GetCookTimeMultiplierInKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 8;
    }
}
