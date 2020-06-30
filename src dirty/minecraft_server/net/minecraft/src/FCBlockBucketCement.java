package net.minecraft.src;

import java.util.Random;

public class FCBlockBucketCement extends FCBlockBucketFull
{
    public FCBlockBucketCement(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcItemBucketCement");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcItemBucketCement.itemID;
    }

    public boolean AttemptToSpillIntoBlock(World var1, int var2, int var3, int var4)
    {
        if (!var1.isAirBlock(var2, var3, var4) && var1.getBlockMaterial(var2, var3, var4).isSolid())
        {
            return false;
        }
        else
        {
            var1.playSoundEffect((double)var2, (double)var3, (double)var4, "mob.ghast.moan", 0.5F, 2.6F + (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.8F);

            if (var1.isAirBlock(var2, var3 - 1, var4) || !var1.getBlockMaterial(var2, var3 - 1, var4).isSolid())
            {
                --var3;
            }

            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcCement.blockID);
            return true;
        }
    }
}
