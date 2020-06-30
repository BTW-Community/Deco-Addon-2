package net.minecraft.src;

public class FCItemBucketCement extends FCItemBucketFull
{
    public FCItemBucketCement(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcItemBucketCement");
    }

    /**
     * Returns the blockID for this Item
     */
    public int getBlockID()
    {
        return FCBetterThanWolves.fcBlockBucketCement.blockID;
    }

    protected boolean AttemptPlaceContentsAtLocation(World var1, int var2, int var3, int var4)
    {
        if (!var1.isAirBlock(var2, var3, var4) && var1.getBlockMaterial(var2, var3, var4).isSolid())
        {
            return false;
        }
        else
        {
            if (!var1.isRemote)
            {
                var1.playSoundEffect((double)var2, (double)var3, (double)var4, "mob.ghast.moan", 0.5F, 2.6F + (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.8F);
                var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcCement.blockID);
            }

            return true;
        }
    }
}
