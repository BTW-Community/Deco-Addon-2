package net.minecraft.src;

import java.util.Random;

public class WorldGenTrees extends WorldGenerator
{
    /** The minimum height of a generated tree. */
    private final int minTreeHeight;

    /** True if this tree should grow Vines. */
    private final boolean vinesGrow;

    /** The metadata value of the wood to use in tree generation. */
    private final int metaWood;

    /** The metadata value of the leaves to use in tree generation. */
    private final int metaLeaves;

    public WorldGenTrees(boolean par1)
    {
        this(par1, 4, 0, 0, false);
    }

    public WorldGenTrees(boolean par1, int par2, int par3, int par4, boolean par5)
    {
        super(par1);
        this.minTreeHeight = par2;
        this.metaWood = par3;
        this.metaLeaves = par4;
        this.vinesGrow = par5;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var6 = par2Random.nextInt(3) + this.minTreeHeight;
        boolean var7 = true;

        if (par4 >= 1 && par4 + var6 + 1 <= 256)
        {
            int var8;
            byte var9;
            int var10;
            int var11;

            for (var8 = par4; var8 <= par4 + 1 + var6; ++var8)
            {
                var9 = 1;

                if (var8 == par4)
                {
                    var9 = 0;
                }

                if (var8 >= par4 + 1 + var6 - 2)
                {
                    var9 = 2;
                }

                for (int var12 = par3 - var9; var12 <= par3 + var9 && var7; ++var12)
                {
                    for (var10 = par5 - var9; var10 <= par5 + var9 && var7; ++var10)
                    {
                        if (var8 >= 0 && var8 < 256)
                        {
                            var11 = par1World.getBlockId(var12, var8, var10);

                            if (var11 != 0 && var11 != Block.leaves.blockID && var11 != Block.grass.blockID && var11 != Block.dirt.blockID && var11 != Block.wood.blockID)
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
                    var9 = 3;
                    byte var19 = 0;
                    int var13;
                    int var14;
                    int var15;
                    int var16;
                    int var17;

                    for (var10 = par4 - var9 + var6; var10 <= par4 + var6; ++var10)
                    {
                        var11 = var10 - (par4 + var6);
                        var13 = var19 + 1 - var11 / 2;

                        for (var14 = par3 - var13; var14 <= par3 + var13; ++var14)
                        {
                            var15 = var14 - par3;

                            for (var16 = par5 - var13; var16 <= par5 + var13; ++var16)
                            {
                                var17 = var16 - par5;

                                if (Math.abs(var15) != var13 || Math.abs(var17) != var13 || par2Random.nextInt(2) != 0 && var11 != 0)
                                {
                                    int var18 = par1World.getBlockId(var14, var10, var16);

                                    if (var18 == 0 || var18 == Block.leaves.blockID)
                                    {
                                        this.setBlockAndMetadata(par1World, var14, var10, var16, Block.leaves.blockID, this.metaLeaves);
                                    }
                                }
                            }
                        }
                    }

                    for (var10 = 0; var10 < var6; ++var10)
                    {
                        var11 = par1World.getBlockId(par3, par4 + var10, par5);

                        if (var11 == 0 || var11 == Block.leaves.blockID)
                        {
                            this.setBlockAndMetadata(par1World, par3, par4 + var10, par5, Block.wood.blockID, this.metaWood);

                            if (this.vinesGrow && var10 > 0)
                            {
                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 - 1, par4 + var10, par5))
                                {
                                    this.setBlockAndMetadata(par1World, par3 - 1, par4 + var10, par5, Block.vine.blockID, 8);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 1, par4 + var10, par5))
                                {
                                    this.setBlockAndMetadata(par1World, par3 + 1, par4 + var10, par5, Block.vine.blockID, 2);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var10, par5 - 1))
                                {
                                    this.setBlockAndMetadata(par1World, par3, par4 + var10, par5 - 1, Block.vine.blockID, 1);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var10, par5 + 1))
                                {
                                    this.setBlockAndMetadata(par1World, par3, par4 + var10, par5 + 1, Block.vine.blockID, 4);
                                }
                            }
                        }
                    }

                    if (this.vinesGrow)
                    {
                        for (var10 = par4 - 3 + var6; var10 <= par4 + var6; ++var10)
                        {
                            var11 = var10 - (par4 + var6);
                            var13 = 2 - var11 / 2;

                            for (var14 = par3 - var13; var14 <= par3 + var13; ++var14)
                            {
                                for (var15 = par5 - var13; var15 <= par5 + var13; ++var15)
                                {
                                    if (par1World.getBlockId(var14, var10, var15) == Block.leaves.blockID)
                                    {
                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 - 1, var10, var15) == 0)
                                        {
                                            this.growVines(par1World, var14 - 1, var10, var15, 8);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 + 1, var10, var15) == 0)
                                        {
                                            this.growVines(par1World, var14 + 1, var10, var15, 2);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var10, var15 - 1) == 0)
                                        {
                                            this.growVines(par1World, var14, var10, var15 - 1, 1);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var10, var15 + 1) == 0)
                                        {
                                            this.growVines(par1World, var14, var10, var15 + 1, 4);
                                        }
                                    }
                                }
                            }
                        }

                        if (par2Random.nextInt(5) == 0 && var6 > 5)
                        {
                            for (var10 = 0; var10 < 2; ++var10)
                            {
                                for (var11 = 0; var11 < 4; ++var11)
                                {
                                    if (par2Random.nextInt(4 - var10) == 0)
                                    {
                                        var13 = par2Random.nextInt(3);
                                        this.setBlockAndMetadata(par1World, par3 + Direction.offsetX[Direction.footInvisibleFaceRemap[var11]], par4 + var6 - 5 + var10, par5 + Direction.offsetZ[Direction.footInvisibleFaceRemap[var11]], Block.cocoaPlant.blockID, var13 << 2 | var11);
                                    }
                                }
                            }
                        }
                    }

                    if (var6 > 2)
                    {
                        var16 = par1World.getBlockId(par3, par4, par5);

                        if (var16 == Block.wood.blockID)
                        {
                            var17 = par1World.getBlockMetadata(par3, par4, par5);

                            if (var17 == this.metaWood)
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

    /**
     * Grows vines downward from the given block for a given length. Args: World, x, starty, z, vine-length
     */
    private void growVines(World par1World, int par2, int par3, int par4, int par5)
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
