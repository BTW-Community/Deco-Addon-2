package net.minecraft.src;

import java.util.Random;

public class WorldGenForest extends WorldGenerator
{
    public WorldGenForest(boolean par1)
    {
        super(par1);
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var6 = par2Random.nextInt(3) + 5;
        boolean var7 = true;

        if (par4 >= 1 && par4 + var6 + 1 <= 256)
        {
            int var8;
            int var9;
            int var10;
            int var11;

            for (var8 = par4; var8 <= par4 + 1 + var6; ++var8)
            {
                byte var12 = 1;

                if (var8 == par4)
                {
                    var12 = 0;
                }

                if (var8 >= par4 + 1 + var6 - 2)
                {
                    var12 = 2;
                }

                for (var9 = par3 - var12; var9 <= par3 + var12 && var7; ++var9)
                {
                    for (var10 = par5 - var12; var10 <= par5 + var12 && var7; ++var10)
                    {
                        if (var8 >= 0 && var8 < 256)
                        {
                            var11 = par1World.getBlockId(var9, var8, var10);

                            if (var11 != 0 && var11 != Block.leaves.blockID)
                            {
                                var7 = false;
                            }
                        }
                        else
                        {
                            var7 = false;
                        }
                    }
                }
            }

            if (!var7)
            {
                return false;
            }
            else
            {
                var8 = par1World.getBlockId(par3, par4 - 1, par5);

                if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID) && par4 < 256 - var6 - 1)
                {
                    this.setBlock(par1World, par3, par4 - 1, par5, Block.dirt.blockID);
                    int var13;
                    int var14;
                    int var17;

                    for (var17 = par4 - 3 + var6; var17 <= par4 + var6; ++var17)
                    {
                        var9 = var17 - (par4 + var6);
                        var10 = 1 - var9 / 2;

                        for (var11 = par3 - var10; var11 <= par3 + var10; ++var11)
                        {
                            var13 = var11 - par3;

                            for (var14 = par5 - var10; var14 <= par5 + var10; ++var14)
                            {
                                int var15 = var14 - par5;

                                if (Math.abs(var13) != var10 || Math.abs(var15) != var10 || par2Random.nextInt(2) != 0 && var9 != 0)
                                {
                                    int var16 = par1World.getBlockId(var11, var17, var14);

                                    if (var16 == 0 || var16 == Block.leaves.blockID)
                                    {
                                        this.setBlockAndMetadata(par1World, var11, var17, var14, Block.leaves.blockID, 2);
                                    }
                                }
                            }
                        }
                    }

                    for (var17 = 0; var17 < var6; ++var17)
                    {
                        var9 = par1World.getBlockId(par3, par4 + var17, par5);

                        if (var9 == 0 || var9 == Block.leaves.blockID)
                        {
                            this.setBlockAndMetadata(par1World, par3, par4 + var17, par5, Block.wood.blockID, 2);
                        }
                    }

                    if (var6 > 2)
                    {
                        var13 = par1World.getBlockId(par3, par4, par5);

                        if (var13 == Block.wood.blockID)
                        {
                            var14 = par1World.getBlockMetadata(par3, par4, par5);

                            if (var14 == 2)
                            {
                                par1World.setBlockMetadataWithClient(par3, par4, par5, var14 | 12);
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
