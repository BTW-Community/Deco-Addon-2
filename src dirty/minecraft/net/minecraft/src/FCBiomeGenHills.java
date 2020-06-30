package net.minecraft.src;

import java.util.Random;

public class FCBiomeGenHills extends BiomeGenBase
{
    private static final int m_iNumSilverfishClustersPerChunk = 7;
    private static final int m_iNumSilverfishBlocksPerCluster = 8;
    protected WorldGenerator m_generatorSilverfish;

    protected FCBiomeGenHills(int var1)
    {
        super(var1);
        this.m_generatorSilverfish = new WorldGenMinable(Block.silverfish.blockID, 8);
    }

    public void decorate(World var1, Random var2, int var3, int var4)
    {
        super.decorate(var1, var2, var3, var4);
        this.AddEmeralds(var1, var2, var3, var4);
        this.AddSilverfishBlocks(var1, var2, var3, var4);
    }

    public void AddEmeralds(World var1, Random var2, int var3, int var4)
    {
        int var5 = 3 + var2.nextInt(6);

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int var7 = var3 + var2.nextInt(16);
            int var8 = var2.nextInt(28) + 4;
            int var9 = var4 + var2.nextInt(16);

            if (var1.getBlockId(var7, var8, var9) == Block.stone.blockID)
            {
                int var10 = 0;

                if (var8 <= 48 + var1.rand.nextInt(2))
                {
                    byte var11 = 1;

                    if (var8 <= 24 + var1.rand.nextInt(2))
                    {
                        var11 = 2;
                    }

                    var10 = Block.oreEmerald.GetMetadataConversionForStrataLevel(var11, 0);
                }

                var1.setBlock(var7, var8, var9, Block.oreEmerald.blockID, var10, 2);
            }
        }
    }

    public void AddSilverfishBlocks(World var1, Random var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < 7; ++var5)
        {
            int var6 = var3 + var2.nextInt(16);
            int var7 = var2.nextInt(64);
            int var8 = var4 + var2.nextInt(16);
            this.m_generatorSilverfish.generate(var1, var2, var6, var7, var8);
        }
    }
}
