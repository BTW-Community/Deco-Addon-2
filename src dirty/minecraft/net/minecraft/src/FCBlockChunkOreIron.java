package net.minecraft.src;

import java.util.Random;

public class FCBlockChunkOreIron extends FCBlockChunkOre
{
    protected FCBlockChunkOreIron(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockChunkOreIron");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcItemChunkIronOre.itemID;
    }

    public int GetItemIndexDroppedWhenCookedByKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return FCBetterThanWolves.fcItemNuggetIron.itemID;
    }
}
