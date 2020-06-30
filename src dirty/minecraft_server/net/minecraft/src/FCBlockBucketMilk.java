package net.minecraft.src;

import java.util.Random;

public class FCBlockBucketMilk extends FCBlockBucketFull
{
    public FCBlockBucketMilk(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockBucketMilk");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.bucketMilk.itemID;
    }

    public boolean AttemptToSpillIntoBlock(World var1, int var2, int var3, int var4)
    {
        if (!var1.isAirBlock(var2, var3, var4) && var1.getBlockMaterial(var2, var3, var4).isSolid())
        {
            return false;
        }
        else
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockMilk.blockID);
            return true;
        }
    }
}
