package net.minecraft.src;

import java.util.Random;

public class FCBlockBucketWater extends FCBlockBucketFull
{
    public FCBlockBucketWater(int var1)
    {
        super(var1);
        this.setUnlocalizedName("bucketWater");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.bucketWater.itemID;
    }

    public boolean AttemptToSpillIntoBlock(World var1, int var2, int var3, int var4)
    {
        if (!var1.isAirBlock(var2, var3, var4) && var1.getBlockMaterial(var2, var3, var4).isSolid())
        {
            return false;
        }
        else
        {
            if (var1.provider.isHellWorld)
            {
                var1.playAuxSFX(2278, var2, var3, var4, 0);
            }
            else
            {
                int var5 = var1.getBlockId(var2, var3, var4);
                int var6 = var1.getBlockMetadata(var2, var3, var4);

                if (var5 != Block.lavaMoving.blockID && var5 != Block.lavaStill.blockID)
                {
                    if (var5 != Block.waterMoving.blockID && var5 != Block.waterStill.blockID || var6 != 0)
                    {
                        if (var1.provider.dimensionId == 1)
                        {
                            var1.setBlockWithNotify(var2, var3, var4, Block.waterMoving.blockID);
                        }
                        else
                        {
                            var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.waterMoving.blockID, 6);
                        }
                    }
                }
                else
                {
                    var1.playAuxSFX(2278, var2, var3, var4, 0);

                    if (var6 == 0)
                    {
                        var1.setBlockWithNotify(var2, var3, var4, Block.obsidian.blockID);
                    }
                    else
                    {
                        var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockLavaPillow.blockID);
                    }
                }
            }

            return true;
        }
    }
}
