package net.minecraft.src;

import java.util.Random;

public class WorldGenSwamp extends WorldGenerator
{
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var6;

        for (var6 = par2Random.nextInt(4) + 5; par1World.getBlockMaterial(par3, par4 - 1, par5) == Material.water; --par4)
        {
            ;
        }

        boolean var7 = true;

        if (par4 >= 1 && par4 + var6 + 1 <= 128)
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
                    var12 = 3;
                }

                for (var9 = par3 - var12; var9 <= par3 + var12 && var7; ++var9)
                {
                    for (var10 = par5 - var12; var10 <= par5 + var12 && var7; ++var10)
                    {
                        if (var8 >= 0 && var8 < 128)
                        {
                            var11 = par1World.getBlockId(var9, var8, var10);

                            if (var11 != 0 && var11 != Block.leaves.blockID)
                            {
                                if (var11 != Block.waterStill.blockID && var11 != Block.waterMoving.blockID)
                                {
                                    var7 = false;
                                }
                                else if (var8 > par4)
                                {
                                    var7 = false;
                                }
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

                if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID) && par4 < 128 - var6 - 1)
                {
                    this.setBlock(par1World, par3, par4 - 1, par5, Block.dirt.blockID);
                    int var13;
                    int var14;
                    int var15;
                    int var16;

                    for (var13 = par4 - 3 + var6; var13 <= par4 + var6; ++var13)
                    {
                        var9 = var13 - (par4 + var6);
                        var10 = 2 - var9 / 2;

                        for (var11 = par3 - var10; var11 <= par3 + var10; ++var11)
                        {
                            var16 = var11 - par3;

                            for (var14 = par5 - var10; var14 <= par5 + var10; ++var14)
                            {
                                var15 = var14 - par5;

                                if ((Math.abs(var16) != var10 || Math.abs(var15) != var10 || par2Random.nextInt(2) != 0 && var9 != 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var11, var13, var14)])
                                {
                                    this.setBlock(par1World, var11, var13, var14, Block.leaves.blockID);
                                }
                            }
                        }
                    }

                    for (var13 = 0; var13 < var6; ++var13)
                    {
                        var9 = par1World.getBlockId(par3, par4 + var13, par5);

                        if (var9 == 0 || var9 == Block.leaves.blockID || var9 == Block.waterMoving.blockID || var9 == Block.waterStill.blockID)
                        {
                            this.setBlock(par1World, par3, par4 + var13, par5, Block.wood.blockID);
                        }
                    }

                    for (var13 = par4 - 3 + var6; var13 <= par4 + var6; ++var13)
                    {
                        var9 = var13 - (par4 + var6);
                        var10 = 2 - var9 / 2;

                        for (var11 = par3 - var10; var11 <= par3 + var10; ++var11)
                        {
                            for (var16 = par5 - var10; var16 <= par5 + var10; ++var16)
                            {
                                if (par1World.getBlockId(var11, var13, var16) == Block.leaves.blockID)
                                {
                                    if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var11 - 1, var13, var16) == 0)
                                    {
                                        this.generateVines(par1World, var11 - 1, var13, var16, 8);
                                    }

                                    if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var11 + 1, var13, var16) == 0)
                                    {
                                        this.generateVines(par1World, var11 + 1, var13, var16, 2);
                                    }

                                    if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var11, var13, var16 - 1) == 0)
                                    {
                                        this.generateVines(par1World, var11, var13, var16 - 1, 1);
                                    }

                                    if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var11, var13, var16 + 1) == 0)
                                    {
                                        this.generateVines(par1World, var11, var13, var16 + 1, 4);
                                    }
                                }
                            }
                        }
                    }

                    if (var6 > 2)
                    {
                        var14 = par1World.getBlockId(par3, par4, par5);

                        if (var14 == Block.wood.blockID)
                        {
                            var15 = par1World.getBlockMetadata(par3, par4, par5);

                            if (var15 == 0)
                            {
                                par1World.setBlockMetadataWithClient(par3, par4, par5, var15 | 12);
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

    /**
     * Generates vines at the given position until it hits a block.
     */
    private void generateVines(World par1World, int par2, int par3, int par4, int par5)
    {
        this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
        int var6 = 4;

        while (true)
        {
            --par3;

            if (par1World.getBlockId(par2, par3, par4) != 0 || var6 <= 0)
            {
                return;
            }

            this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
            --var6;
        }
    }
}
