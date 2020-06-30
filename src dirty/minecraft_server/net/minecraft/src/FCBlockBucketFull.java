package net.minecraft.src;

import java.util.Random;

public abstract class FCBlockBucketFull extends FCBlockBucket
{
    public FCBlockBucketFull(int var1)
    {
        super(var1);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.CheckForFall(var1, var2, var3, var4))
        {
            this.CheckForSpillContents(var1, var2, var3, var4);
        }
    }

    protected void InitModels()
    {
        this.m_model = new FCModelBlockBucketFull();
        this.m_modelTransformed = this.m_model;
    }

    public void CheckForSpillContents(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);

        if (var5 >= 2)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);

            if (this.AttemptToSpillIntoBlock(var1, var6.i, var6.j, var6.k) && var1.getBlockId(var2, var3, var4) == this.blockID)
            {
                var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockBucketEmpty.blockID, this.SetFacing(0, var5));
            }
        }
    }

    public abstract boolean AttemptToSpillIntoBlock(World var1, int var2, int var3, int var4);
}
