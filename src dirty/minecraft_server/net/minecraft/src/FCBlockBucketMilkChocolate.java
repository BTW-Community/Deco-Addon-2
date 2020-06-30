package net.minecraft.src;

import java.util.Random;

public class FCBlockBucketMilkChocolate extends FCBlockBucketFull
{
    public FCBlockBucketMilkChocolate(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockBucketMilkChocolate");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcItemBucketMilkChocolate.itemID;
    }

    public boolean AttemptToSpillIntoBlock(World var1, int var2, int var3, int var4)
    {
        if (!var1.isAirBlock(var2, var3, var4) && var1.getBlockMaterial(var2, var3, var4).isSolid())
        {
            return false;
        }
        else
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockMilkChocolate.blockID);
            return true;
        }
    }
}
