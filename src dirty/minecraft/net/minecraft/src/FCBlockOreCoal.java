package net.minecraft.src;

import java.util.Random;

public class FCBlockOreCoal extends FCBlockOreStaged
{
    public FCBlockOreCoal(int var1)
    {
        super(var1);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.coal.itemID;
    }

    public int IdDroppedOnConversion(int var1)
    {
        return FCBetterThanWolves.fcItemCoalDust.itemID;
    }
}
