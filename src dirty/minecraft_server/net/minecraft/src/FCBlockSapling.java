package net.minecraft.src;

import java.util.Random;

public class FCBlockSapling extends BlockSapling
{
    private static final double m_dWidth = 0.8D;
    private static final double m_dHalfWidth = 0.4D;

    protected FCBlockSapling(int var1)
    {
        super(var1);
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.KINDLING);
        this.SetFilterableProperties(0);
        this.InitBlockBounds(0.09999999999999998D, 0.0D, 0.09999999999999998D, 0.9D, 0.8D, 0.9D);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.checkFlowerChange(var1, var2, var3, var4);

        if (var1.provider.dimensionId != 1 && var1.getBlockId(var2, var3, var4) == this.blockID && var1.getBlockLightValue(var2, var3 + 1, var4) >= 9 && var5.nextInt(64) == 0)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            int var7 = (var6 & -4) >> 2;

            if (var7 < 3)
            {
                ++var7;
                var6 = var6 & 3 | var7 << 2;
                var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
            }
            else
            {
                this.growTree(var1, var2, var3, var4, var5);
            }
        }
    }

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & 3;
        boolean var7 = false;
        int var8 = var1.getBlockId(var2, var3 - 1, var4);
        int var9;

        if (var8 == FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID)
        {
            var9 = var1.getBlockMetadata(var2, var3 - 1, var4);

            if (((FCBlockAestheticOpaqueEarth)FCBetterThanWolves.fcBlockAestheticOpaqueEarth).IsBlightFromMetadata(var9))
            {
                ;
            }
        }

        var9 = 0;
        int var10 = 0;
        boolean var11 = false;

        if (var6 != 3)
        {
            var1.setBlock(var2, var3, var4, 0);
        }

        if (var6 == 1)
        {
            var7 = FCUtilsTrees.GenerateTaiga2(var1, var5, var2, var3, var4);
        }
        else if (var6 == 2)
        {
            var7 = FCUtilsTrees.GenerateForest(var1, var5, var2, var3, var4);
        }
        else if (var6 == 3)
        {
            while (true)
            {
                if (var9 >= -1)
                {
                    for (var10 = 0; var10 >= -1; --var10)
                    {
                        if (this.isSameSapling(var1, var2 + var9, var3, var4 + var10, 3) && this.isSameSapling(var1, var2 + var9 + 1, var3, var4 + var10, 3) && this.isSameSapling(var1, var2 + var9, var3, var4 + var10 + 1, 3) && this.isSameSapling(var1, var2 + var9 + 1, var3, var4 + var10 + 1, 3))
                        {
                            if (this.GetSaplingGrowthStage(var1, var2 + var9, var3, var4 + var10) != 3 || this.GetSaplingGrowthStage(var1, var2 + var9 + 1, var3, var4 + var10) != 3 || this.GetSaplingGrowthStage(var1, var2 + var9, var3, var4 + var10 + 1) != 3 || this.GetSaplingGrowthStage(var1, var2 + var9 + 1, var3, var4 + var10 + 1) != 3)
                            {
                                return;
                            }

                            var1.setBlock(var2 + var9, var3, var4 + var10, 0);
                            var1.setBlock(var2 + var9 + 1, var3, var4 + var10, 0);
                            var1.setBlock(var2 + var9, var3, var4 + var10 + 1, 0);
                            var1.setBlock(var2 + var9 + 1, var3, var4 + var10 + 1, 0);
                            FCUtilsGenHugeTree var12 = new FCUtilsGenHugeTree(true, 10 + var5.nextInt(20), 3, 3);
                            var7 = var12.generate(var1, var5, var2 + var9, var3, var4 + var10);
                            var11 = true;
                            break;
                        }
                    }

                    if (!var11)
                    {
                        --var9;
                        continue;
                    }
                }

                if (!var11)
                {
                    var10 = 0;
                    var9 = 0;
                    var1.setBlock(var2, var3, var4, 0);
                    var7 = FCUtilsTrees.GenerateTrees(var1, var5, var2, var3, var4, 4 + var5.nextInt(7), 3, 3, false);
                }

                break;
            }
        }
        else if (var5.nextInt(10) == 0)
        {
            FCUtilsGenBigTree var13 = new FCUtilsGenBigTree(true);
            var7 = var13.generate(var1, var5, var2, var3, var4);
        }
        else
        {
            var7 = FCUtilsTrees.GenerateTrees(var1, var5, var2, var3, var4);
        }

        if (!var7)
        {
            int var14 = var6 + 12;

            if (var11)
            {
                var1.setBlockAndMetadata(var2 + var9, var3, var4 + var10, this.blockID, var14);
                var1.setBlockAndMetadata(var2 + var9 + 1, var3, var4 + var10, this.blockID, var14);
                var1.setBlockAndMetadata(var2 + var9, var3, var4 + var10 + 1, this.blockID, var14);
                var1.setBlockAndMetadata(var2 + var9 + 1, var3, var4 + var10 + 1, this.blockID, var14);
            }
            else
            {
                var1.setBlockAndMetadata(var2, var3, var4, this.blockID, var14);
            }
        }
    }

    public boolean OnBlockSawed(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    protected boolean CanGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3, var4)];
        return var5 != null && var5.CanSaplingsGrowOnBlock(var1, var2, var3, var4);
    }

    public int GetSaplingGrowthStage(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        int var6 = (var5 & -4) >> 2;
        return var6;
    }
}
