package net.minecraft.src;

import java.util.Random;

public class FCBlockBucketMilk extends FCBlockBucketFull
{
    private Icon m_iconContents;

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

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_iconContents = var1.registerIcon("fcBlockBucket_milk");
    }

    protected Icon GetContentsIcon()
    {
        return this.m_iconContents;
    }
}
