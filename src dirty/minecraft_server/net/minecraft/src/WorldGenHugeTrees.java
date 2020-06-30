package net.minecraft.src;

import java.util.Random;

public class WorldGenHugeTrees extends WorldGenerator
{
    /** The base height of the tree */
    private final int baseHeight;

    /** Sets the metadata for the wood blocks used */
    private final int woodMetadata;

    /** Sets the metadata for the leaves used in huge trees */
    private final int leavesMetadata;

    public WorldGenHugeTrees(boolean par1, int par2, int par3, int par4)
    {
        super(par1);
        this.baseHeight = par2;
        this.woodMetadata = par3;
        this.leavesMetadata = par4;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var6 = par2Random.nextInt(3) + this.baseHeight;
        boolean var7 = true;

        if (par4 >= 1 && par4 + var6 + 1 <= 256)
        {
            int var8;
            int var9;
            int var10;
            int var11;

            for (var8 = par4; var8 <= par4 + 1 + var6; ++var8)
            {
                byte var12 = 2;

                if (var8 == par4)
                {
                    var12 = 1;
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

                            if (var11 != 0 && var11 != Block.leaves.blockID && var11 != Block.grass.blockID && var11 != Block.dirt.blockID && var11 != Block.wood.blockID && var11 != Block.sapling.blockID)
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
                    par1World.setBlock(par3, par4 - 1, par5, Block.dirt.blockID, 0, 2);
                    par1World.setBlock(par3 + 1, par4 - 1, par5, Block.dirt.blockID, 0, 2);
                    par1World.setBlock(par3, par4 - 1, par5 + 1, Block.dirt.blockID, 0, 2);
                    par1World.setBlock(par3 + 1, par4 - 1, par5 + 1, Block.dirt.blockID, 0, 2);
                    this.growLeaves(par1World, par3, par5, par4 + var6, 2, par2Random);

                    for (int var15 = par4 + var6 - 2 - par2Random.nextInt(4); var15 > par4 + var6 / 2; var15 -= 2 + par2Random.nextInt(4))
                    {
                        float var13 = par2Random.nextFloat() * (float)Math.PI * 2.0F;
                        var10 = par3 + (int)(0.5F + MathHelper.cos(var13) * 4.0F);
                        var11 = par5 + (int)(0.5F + MathHelper.sin(var13) * 4.0F);
                        this.growLeaves(par1World, var10, var11, var15, 0, par2Random);

                        for (int var14 = 0; var14 < 5; ++var14)
                        {
                            var10 = par3 + (int)(1.5F + MathHelper.cos(var13) * (float)var14);
                            var11 = par5 + (int)(1.5F + MathHelper.sin(var13) * (float)var14);
                            this.setBlockAndMetadata(par1World, var10, var15 - 3 + var14 / 2, var11, Block.wood.blockID, this.woodMetadata);
                        }
                    }

                    for (var9 = 0; var9 < var6; ++var9)
                    {
                        var10 = par1World.getBlockId(par3, par4 + var9, par5);

                        if (var10 == 0 || var10 == Block.leaves.blockID)
                        {
                            this.setBlockAndMetadata(par1World, par3, par4 + var9, par5, Block.wood.blockID, this.woodMetadata);

                            if (var9 > 0)
                            {
                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 - 1, par4 + var9, par5))
                                {
                                    this.setBlockAndMetadata(par1World, par3 - 1, par4 + var9, par5, Block.vine.blockID, 8);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var9, par5 - 1))
                                {
                                    this.setBlockAndMetadata(par1World, par3, par4 + var9, par5 - 1, Block.vine.blockID, 1);
                                }
                            }
                        }

                        if (var9 < var6 - 1)
                        {
                            var10 = par1World.getBlockId(par3 + 1, par4 + var9, par5);

                            if (var10 == 0 || var10 == Block.leaves.blockID)
                            {
                                this.setBlockAndMetadata(par1World, par3 + 1, par4 + var9, par5, Block.wood.blockID, this.woodMetadata);

                                if (var9 > 0)
                                {
                                    if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 2, par4 + var9, par5))
                                    {
                                        this.setBlockAndMetadata(par1World, par3 + 2, par4 + var9, par5, Block.vine.blockID, 2);
                                    }

                                    if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 1, par4 + var9, par5 - 1))
                                    {
                                        this.setBlockAndMetadata(par1World, par3 + 1, par4 + var9, par5 - 1, Block.vine.blockID, 1);
                                    }
                                }
                            }

                            var10 = par1World.getBlockId(par3 + 1, par4 + var9, par5 + 1);

                            if (var10 == 0 || var10 == Block.leaves.blockID)
                            {
                                this.setBlockAndMetadata(par1World, par3 + 1, par4 + var9, par5 + 1, Block.wood.blockID, this.woodMetadata);

                                if (var9 > 0)
                                {
                                    if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 2, par4 + var9, par5 + 1))
                                    {
                                        this.setBlockAndMetadata(par1World, par3 + 2, par4 + var9, par5 + 1, Block.vine.blockID, 2);
                                    }

                                    if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 1, par4 + var9, par5 + 2))
                                    {
                                        this.setBlockAndMetadata(par1World, par3 + 1, par4 + var9, par5 + 2, Block.vine.blockID, 4);
                                    }
                                }
                            }

                            var10 = par1World.getBlockId(par3, par4 + var9, par5 + 1);

                            if (var10 == 0 || var10 == Block.leaves.blockID)
                            {
                                this.setBlockAndMetadata(par1World, par3, par4 + var9, par5 + 1, Block.wood.blockID, this.woodMetadata);

                                if (var9 > 0)
                                {
                                    if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 - 1, par4 + var9, par5 + 1))
                                    {
                                        this.setBlockAndMetadata(par1World, par3 - 1, par4 + var9, par5 + 1, Block.vine.blockID, 8);
                                    }

                                    if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var9, par5 + 2))
                                    {
                                        this.setBlockAndMetadata(par1World, par3, par4 + var9, par5 + 2, Block.vine.blockID, 4);
                                    }
                                }
                            }
                        }
                    }

                    this.AttemptToPlaceStump(par1World, par3, par4, par5);
                    this.AttemptToPlaceStump(par1World, par3, par4, par5 + 1);
                    this.AttemptToPlaceStump(par1World, par3 + 1, par4, par5);
                    this.AttemptToPlaceStump(par1World, par3 + 1, par4, par5 + 1);
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

    private void AttemptToPlaceStump(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (var5 == Block.wood.blockID)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (var6 == this.woodMetadata)
            {
                var1.setBlockMetadataWithClient(var2, var3, var4, var6 | 12);
            }
        }
    }

    private void growLeaves(World par1World, int par2, int par3, int par4, int par5, Random par6Random)
    {
        byte var7 = 2;

        for (int var8 = par4 - var7; var8 <= par4; ++var8)
        {
            int var9 = var8 - par4;
            int var10 = par5 + 1 - var9;

            for (int var11 = par2 - var10; var11 <= par2 + var10 + 1; ++var11)
            {
                int var12 = var11 - par2;

                for (int var13 = par3 - var10; var13 <= par3 + var10 + 1; ++var13)
                {
                    int var14 = var13 - par3;

                    if ((var12 >= 0 || var14 >= 0 || var12 * var12 + var14 * var14 <= var10 * var10) && (var12 <= 0 && var14 <= 0 || var12 * var12 + var14 * var14 <= (var10 + 1) * (var10 + 1)) && (par6Random.nextInt(4) != 0 || var12 * var12 + var14 * var14 <= (var10 - 1) * (var10 - 1)))
                    {
                        int var15 = par1World.getBlockId(var11, var8, var13);

                        if (var15 == 0 || var15 == Block.leaves.blockID)
                        {
                            this.setBlockAndMetadata(par1World, var11, var8, var13, Block.leaves.blockID, this.leavesMetadata);
                        }
                    }
                }
            }
        }
    }
}
