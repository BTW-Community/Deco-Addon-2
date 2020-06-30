package net.minecraft.src;

import java.util.Random;

public class FCUtilsTrees
{
    public static boolean GenerateTaiga2(World var0, Random var1, int var2, int var3, int var4)
    {
        int var5 = var1.nextInt(4) + 6;
        int var6 = 1 + var1.nextInt(2);
        int var7 = var5 - var6;
        int var8 = 2 + var1.nextInt(2);
        boolean var9 = true;

        if (var3 >= 1)
        {
            var0.getClass();

            if (var3 + var5 + 1 <= 128)
            {
                int var10;
                int var13;
                int var14;
                int var19;

                for (var10 = var3; var10 <= var3 + 1 + var5 && var9; ++var10)
                {
                    boolean var11 = true;

                    if (var10 - var3 < var6)
                    {
                        var19 = 0;
                    }
                    else
                    {
                        var19 = var8;
                    }

                    for (int var12 = var2 - var19; var12 <= var2 + var19 && var9; ++var12)
                    {
                        for (var13 = var4 - var19; var13 <= var4 + var19 && var9; ++var13)
                        {
                            if (var10 >= 0)
                            {
                                var0.getClass();

                                if (var10 < 128)
                                {
                                    var14 = var0.getBlockId(var12, var10, var13);

                                    if (!var0.isAirBlock(var12, var10, var13) && var14 != Block.leaves.blockID && var14 != Block.snow.blockID)
                                    {
                                        var9 = false;
                                    }

                                    continue;
                                }
                            }

                            var9 = false;
                        }
                    }
                }

                if (!var9)
                {
                    return false;
                }

                var0.getBlockId(var2, var3 - 1, var4);

                if (CanSaplingGrowOnBlock(var0, var2, var3 - 1, var4))
                {
                    var0.getClass();

                    if (var3 < 128 - var5 - 1)
                    {
                        if (var0.getBlockId(var2, var3 - 1, var4) == Block.grass.blockID)
                        {
                            var0.setBlockWithNotify(var2, var3 - 1, var4, Block.dirt.blockID);
                        }

                        var10 = var1.nextInt(2);
                        var19 = 1;
                        boolean var20 = false;
                        int var15;

                        for (var13 = 0; var13 <= var7; ++var13)
                        {
                            var14 = var3 + var5 - var13;

                            for (var15 = var2 - var10; var15 <= var2 + var10; ++var15)
                            {
                                int var16 = var15 - var2;

                                for (int var17 = var4 - var10; var17 <= var4 + var10; ++var17)
                                {
                                    int var18 = var17 - var4;

                                    if ((Math.abs(var16) != var10 || Math.abs(var18) != var10 || var10 <= 0) && !Block.opaqueCubeLookup[var0.getBlockId(var15, var14, var17)])
                                    {
                                        var0.setBlockAndMetadataWithNotify(var15, var14, var17, Block.leaves.blockID, 1);
                                    }
                                }
                            }

                            if (var10 >= var19)
                            {
                                var10 = var20 ? 1 : 0;
                                var20 = true;
                                ++var19;

                                if (var19 > var8)
                                {
                                    var19 = var8;
                                }
                            }
                            else
                            {
                                ++var10;
                            }
                        }

                        var13 = var1.nextInt(3);

                        for (var14 = 0; var14 < var5 - var13; ++var14)
                        {
                            var15 = var0.getBlockId(var2, var3 + var14, var4);

                            if (var0.isAirBlock(var2, var3 + var14, var4) || var15 == Block.leaves.blockID || var15 == Block.snow.blockID)
                            {
                                var0.setBlockAndMetadataWithNotify(var2, var3 + var14, var4, Block.wood.blockID, 1);
                            }
                        }

                        if (var5 > 2)
                        {
                            var14 = var0.getBlockId(var2, var3, var4);

                            if (var14 == Block.wood.blockID)
                            {
                                var15 = var0.getBlockMetadata(var2, var3, var4);

                                if (var15 == 1)
                                {
                                    var0.setBlockMetadataWithClient(var2, var3, var4, 13);
                                }
                            }
                        }

                        return true;
                    }
                }

                return false;
            }
        }

        return false;
    }

    public static boolean GenerateForest(World var0, Random var1, int var2, int var3, int var4)
    {
        int var5 = var1.nextInt(3) + 5;
        boolean var6 = true;

        if (var3 >= 1)
        {
            var0.getClass();

            if (var3 + var5 + 1 <= 128)
            {
                int var7;
                int var9;
                int var10;
                int var11;

                for (var7 = var3; var7 <= var3 + 1 + var5; ++var7)
                {
                    byte var8 = 1;

                    if (var7 == var3)
                    {
                        var8 = 0;
                    }

                    if (var7 >= var3 + 1 + var5 - 2)
                    {
                        var8 = 2;
                    }

                    for (var9 = var2 - var8; var9 <= var2 + var8 && var6; ++var9)
                    {
                        for (var10 = var4 - var8; var10 <= var4 + var8 && var6; ++var10)
                        {
                            if (var7 >= 0)
                            {
                                var0.getClass();

                                if (var7 < 128)
                                {
                                    var11 = var0.getBlockId(var9, var7, var10);

                                    if (!var0.isAirBlock(var9, var7, var10) && var11 != Block.leaves.blockID)
                                    {
                                        var6 = false;
                                    }

                                    continue;
                                }
                            }

                            var6 = false;
                        }
                    }
                }

                if (!var6)
                {
                    return false;
                }

                var0.getBlockId(var2, var3 - 1, var4);

                if (CanSaplingGrowOnBlock(var0, var2, var3 - 1, var4))
                {
                    var0.getClass();

                    if (var3 < 128 - var5 - 1)
                    {
                        if (var0.getBlockId(var2, var3 - 1, var4) == Block.grass.blockID)
                        {
                            var0.setBlockWithNotify(var2, var3 - 1, var4, Block.dirt.blockID);
                        }

                        int var14;

                        for (var7 = var3 - 3 + var5; var7 <= var3 + var5; ++var7)
                        {
                            var14 = var7 - (var3 + var5);
                            var9 = 1 - var14 / 2;

                            for (var10 = var2 - var9; var10 <= var2 + var9; ++var10)
                            {
                                var11 = var10 - var2;

                                for (int var12 = var4 - var9; var12 <= var4 + var9; ++var12)
                                {
                                    int var13 = var12 - var4;

                                    if ((Math.abs(var11) != var9 || Math.abs(var13) != var9 || var1.nextInt(2) != 0 && var14 != 0) && !Block.opaqueCubeLookup[var0.getBlockId(var10, var7, var12)])
                                    {
                                        var0.setBlockAndMetadataWithNotify(var10, var7, var12, Block.leaves.blockID, 2);
                                    }
                                }
                            }
                        }

                        for (var7 = 0; var7 < var5; ++var7)
                        {
                            var14 = var0.getBlockId(var2, var3 + var7, var4);

                            if (var0.isAirBlock(var2, var3 + var7, var4) || var14 == Block.leaves.blockID)
                            {
                                var0.setBlockAndMetadataWithNotify(var2, var3 + var7, var4, Block.wood.blockID, 2);
                            }
                        }

                        if (var5 > 2)
                        {
                            var7 = var0.getBlockId(var2, var3, var4);

                            if (var7 == Block.wood.blockID)
                            {
                                var14 = var0.getBlockMetadata(var2, var3, var4);

                                if (var14 == 2)
                                {
                                    var0.setBlockMetadataWithClient(var2, var3, var4, 14);
                                }
                            }
                        }

                        return true;
                    }
                }

                return false;
            }
        }

        return false;
    }

    public static boolean GenerateTrees(World var0, Random var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8)
    {
        int var9 = var1.nextInt(3) + var5;
        boolean var10 = true;

        if (var3 >= 1 && var3 + var9 + 1 <= 256)
        {
            int var11;
            byte var12;
            int var14;
            int var15;

            for (var11 = var3; var11 <= var3 + 1 + var9; ++var11)
            {
                var12 = 1;

                if (var11 == var3)
                {
                    var12 = 0;
                }

                if (var11 >= var3 + 1 + var9 - 2)
                {
                    var12 = 2;
                }

                for (int var13 = var2 - var12; var13 <= var2 + var12 && var10; ++var13)
                {
                    for (var14 = var4 - var12; var14 <= var4 + var12 && var10; ++var14)
                    {
                        if (var11 >= 0 && var11 < 256)
                        {
                            var15 = var0.getBlockId(var13, var11, var14);

                            if (!var0.isAirBlock(var13, var11, var14) && var15 != Block.leaves.blockID && var15 != Block.grass.blockID && var15 != Block.dirt.blockID && var15 != Block.wood.blockID)
                            {
                                var10 = false;
                            }
                        }
                        else
                        {
                            var10 = false;
                        }
                    }
                }
            }

            if (!var10)
            {
                return false;
            }
            else
            {
                var11 = var0.getBlockId(var2, var3 - 1, var4);

                if (CanSaplingGrowOnBlock(var0, var2, var3 - 1, var4) && var3 < 256 - var9 - 1)
                {
                    if (var11 == Block.grass.blockID)
                    {
                        var0.setBlockWithNotify(var2, var3 - 1, var4, Block.dirt.blockID);
                    }

                    var12 = 3;
                    byte var21 = 0;
                    int var16;
                    int var17;
                    int var18;

                    for (var14 = var3 - var12 + var9; var14 <= var3 + var9; ++var14)
                    {
                        var15 = var14 - (var3 + var9);
                        var16 = var21 + 1 - var15 / 2;

                        for (var17 = var2 - var16; var17 <= var2 + var16; ++var17)
                        {
                            var18 = var17 - var2;

                            for (int var19 = var4 - var16; var19 <= var4 + var16; ++var19)
                            {
                                int var20 = var19 - var4;

                                if ((Math.abs(var18) != var16 || Math.abs(var20) != var16 || var1.nextInt(2) != 0 && var15 != 0) && !Block.opaqueCubeLookup[var0.getBlockId(var17, var14, var19)])
                                {
                                    var0.setBlockAndMetadataWithNotify(var17, var14, var19, Block.leaves.blockID, var7);
                                }
                            }
                        }
                    }

                    for (var14 = 0; var14 < var9; ++var14)
                    {
                        var15 = var0.getBlockId(var2, var3 + var14, var4);

                        if (var0.isAirBlock(var2, var3 + var14, var4) || var15 == Block.leaves.blockID)
                        {
                            var0.setBlockAndMetadataWithNotify(var2, var3 + var14, var4, Block.wood.blockID, var6);

                            if (var8 && var14 > 0)
                            {
                                if (var1.nextInt(3) > 0 && var0.isAirBlock(var2 - 1, var3 + var14, var4))
                                {
                                    var0.setBlockAndMetadataWithNotify(var2 - 1, var3 + var14, var4, Block.vine.blockID, 8);
                                }

                                if (var1.nextInt(3) > 0 && var0.isAirBlock(var2 + 1, var3 + var14, var4))
                                {
                                    var0.setBlockAndMetadataWithNotify(var2 + 1, var3 + var14, var4, Block.vine.blockID, 2);
                                }

                                if (var1.nextInt(3) > 0 && var0.isAirBlock(var2, var3 + var14, var4 - 1))
                                {
                                    var0.setBlockAndMetadataWithNotify(var2, var3 + var14, var4 - 1, Block.vine.blockID, 1);
                                }

                                if (var1.nextInt(3) > 0 && var0.isAirBlock(var2, var3 + var14, var4 + 1))
                                {
                                    var0.setBlockAndMetadataWithNotify(var2, var3 + var14, var4 + 1, Block.vine.blockID, 4);
                                }
                            }
                        }
                    }

                    if (var8)
                    {
                        for (var14 = var3 - 3 + var9; var14 <= var3 + var9; ++var14)
                        {
                            var15 = var14 - (var3 + var9);
                            var16 = 2 - var15 / 2;

                            for (var17 = var2 - var16; var17 <= var2 + var16; ++var17)
                            {
                                for (var18 = var4 - var16; var18 <= var4 + var16; ++var18)
                                {
                                    if (var0.getBlockId(var17, var14, var18) == Block.leaves.blockID)
                                    {
                                        if (var1.nextInt(4) == 0 && var0.isAirBlock(var17 - 1, var14, var18))
                                        {
                                            PlaceVine(var0, var17 - 1, var14, var18, 8);
                                        }

                                        if (var1.nextInt(4) == 0 && var0.isAirBlock(var17 + 1, var14, var18))
                                        {
                                            PlaceVine(var0, var17 + 1, var14, var18, 2);
                                        }

                                        if (var1.nextInt(4) == 0 && var0.isAirBlock(var17, var14, var18 - 1))
                                        {
                                            PlaceVine(var0, var17, var14, var18 - 1, 1);
                                        }

                                        if (var1.nextInt(4) == 0 && var0.isAirBlock(var17, var14, var18 + 1))
                                        {
                                            PlaceVine(var0, var17, var14, var18 + 1, 4);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (var9 > 2)
                    {
                        var14 = var0.getBlockId(var2, var3, var4);

                        if (var14 == Block.wood.blockID)
                        {
                            var15 = var0.getBlockMetadata(var2, var3, var4);

                            if (var15 == var6)
                            {
                                var0.setBlockMetadataWithClient(var2, var3, var4, var6 | 12);
                            }
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    private static void PlaceVine(World var0, int var1, int var2, int var3, int var4)
    {
        var0.setBlockAndMetadataWithNotify(var1, var2, var3, Block.vine.blockID, var4);
        int var5 = 4;

        while (true)
        {
            --var2;

            if (var0.getBlockId(var1, var2, var3) != 0 || var5 <= 0)
            {
                return;
            }

            var0.setBlockAndMetadataWithNotify(var1, var2, var3, Block.vine.blockID, var4);
            --var5;
        }
    }

    public static boolean GenerateTrees(World var0, Random var1, int var2, int var3, int var4)
    {
        return GenerateTrees(var0, var1, var2, var3, var4, 4, 0, 0, false);
    }

    public static boolean CanSaplingGrowOnBlock(World var0, int var1, int var2, int var3)
    {
        Block var4 = Block.blocksList[var0.getBlockId(var1, var2, var3)];
        return var4 != null && var4.CanSaplingsGrowOnBlock(var0, var1, var2, var3);
    }
}
