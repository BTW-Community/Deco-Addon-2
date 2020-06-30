package net.minecraft.src;

import java.util.Random;

public class WorldGenTaiga2 extends WorldGenerator
{
    public WorldGenTaiga2(boolean par1)
    {
        super(par1);
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var6 = par2Random.nextInt(4) + 6;
        int var7 = 1 + par2Random.nextInt(2);
        int var8 = var6 - var7;
        int var9 = 2 + par2Random.nextInt(2);
        boolean var10 = true;

        if (par4 >= 1 && par4 + var6 + 1 <= 256)
        {
            int var11;
            int var12;
            int var13;
            int var14;
            int var16;

            for (var11 = par4; var11 <= par4 + 1 + var6 && var10; ++var11)
            {
                boolean var15 = true;

                if (var11 - par4 < var7)
                {
                    var14 = 0;
                }
                else
                {
                    var14 = var9;
                }

                for (var12 = par3 - var14; var12 <= par3 + var14 && var10; ++var12)
                {
                    for (var16 = par5 - var14; var16 <= par5 + var14 && var10; ++var16)
                    {
                        if (var11 >= 0 && var11 < 256)
                        {
                            var13 = par1World.getBlockId(var12, var11, var16);

                            if (var13 != 0 && var13 != Block.leaves.blockID)
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

                if ((var11 == Block.grass.blockID || var11 == Block.dirt.blockID) && par4 < 256 - var6 - 1)
                {
                    this.setBlock(par1World, par3, par4 - 1, par5, Block.dirt.blockID);
                    var14 = par2Random.nextInt(2);
                    var12 = 1;
                    byte var21 = 0;
                    int var17;
                    int var18;
                    int var19;

                    for (var13 = 0; var13 <= var8; ++var13)
                    {
                        var17 = par4 + var6 - var13;

                        for (var16 = par3 - var14; var16 <= par3 + var14; ++var16)
                        {
                            var18 = var16 - par3;

                            for (var19 = par5 - var14; var19 <= par5 + var14; ++var19)
                            {
                                int var20 = var19 - par5;

                                if ((Math.abs(var18) != var14 || Math.abs(var20) != var14 || var14 <= 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var16, var17, var19)])
                                {
                                    this.setBlockAndMetadata(par1World, var16, var17, var19, Block.leaves.blockID, 1);
                                }
                            }
                        }

                        if (var14 >= var12)
                        {
                            var14 = var21;
                            var21 = 1;
                            ++var12;

                            if (var12 > var9)
                            {
                                var12 = var9;
                            }
                        }
                        else
                        {
                            ++var14;
                        }
                    }

                    var13 = par2Random.nextInt(3);

                    for (var17 = 0; var17 < var6 - var13; ++var17)
                    {
                        var16 = par1World.getBlockId(par3, par4 + var17, par5);

                        if (var16 == 0 || var16 == Block.leaves.blockID)
                        {
                            this.setBlockAndMetadata(par1World, par3, par4 + var17, par5, Block.wood.blockID, 1);
                        }
                    }

                    if (var6 > 2)
                    {
                        var18 = par1World.getBlockId(par3, par4, par5);

                        if (var18 == Block.wood.blockID)
                        {
                            var19 = par1World.getBlockMetadata(par3, par4, par5);

                            if (var19 == 1)
                            {
                                par1World.setBlockMetadataWithClient(par3, par4, par5, var19 | 12);
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
