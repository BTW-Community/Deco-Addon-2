package net.minecraft.src;

import java.util.Random;

public class WorldGenTaiga1 extends WorldGenerator
{
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var6 = par2Random.nextInt(5) + 7;
        int var7 = var6 - par2Random.nextInt(2) - 3;
        int var8 = var6 - var7;
        int var9 = 1 + par2Random.nextInt(var8 + 1);
        boolean var10 = true;

        if (par4 >= 1 && par4 + var6 + 1 <= 128)
        {
            int var11;
            int var12;
            int var13;
            int var14;
            int var15;

            for (var11 = par4; var11 <= par4 + 1 + var6 && var10; ++var11)
            {
                boolean var16 = true;

                if (var11 - par4 < var7)
                {
                    var15 = 0;
                }
                else
                {
                    var15 = var9;
                }

                for (var12 = par3 - var15; var12 <= par3 + var15 && var10; ++var12)
                {
                    for (var13 = par5 - var15; var13 <= par5 + var15 && var10; ++var13)
                    {
                        if (var11 >= 0 && var11 < 128)
                        {
                            var14 = par1World.getBlockId(var12, var11, var13);

                            if (var14 != 0 && var14 != Block.leaves.blockID)
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
                var11 = par1World.getBlockId(par3, par4 - 1, par5);

                if ((var11 == Block.grass.blockID || var11 == Block.dirt.blockID) && par4 < 128 - var6 - 1)
                {
                    this.setBlock(par1World, par3, par4 - 1, par5, Block.dirt.blockID);
                    var15 = 0;
                    int var17;
                    int var18;

                    for (var12 = par4 + var6; var12 >= par4 + var7; --var12)
                    {
                        for (var13 = par3 - var15; var13 <= par3 + var15; ++var13)
                        {
                            var14 = var13 - par3;

                            for (var18 = par5 - var15; var18 <= par5 + var15; ++var18)
                            {
                                var17 = var18 - par5;

                                if ((Math.abs(var14) != var15 || Math.abs(var17) != var15 || var15 <= 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var13, var12, var18)])
                                {
                                    this.setBlockAndMetadata(par1World, var13, var12, var18, Block.leaves.blockID, 1);
                                }
                            }
                        }

                        if (var15 >= 1 && var12 == par4 + var7 + 1)
                        {
                            --var15;
                        }
                        else if (var15 < var9)
                        {
                            ++var15;
                        }
                    }

                    for (var12 = 0; var12 < var6 - 1; ++var12)
                    {
                        var13 = par1World.getBlockId(par3, par4 + var12, par5);

                        if (var13 == 0 || var13 == Block.leaves.blockID)
                        {
                            this.setBlockAndMetadata(par1World, par3, par4 + var12, par5, Block.wood.blockID, 1);
                        }
                    }

                    if (var6 > 2)
                    {
                        var18 = par1World.getBlockId(par3, par4, par5);

                        if (var18 == Block.wood.blockID)
                        {
                            var17 = par1World.getBlockMetadata(par3, par4, par5);

                            if (var17 == 1)
                            {
                                par1World.setBlockMetadataWithClient(par3, par4, par5, var17 | 12);
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
}
