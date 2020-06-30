package net.minecraft.src;

import java.util.Random;

public class WorldGenPumpkin extends WorldGenerator
{
    private static final double m_dDistForFreshPumpkins = 2500.0D;
    private static final double m_dDistSquaredForFreshPumpkins = 6250000.0D;

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        boolean var6 = par1World.getBiomeGenForCoords(par3, par5) == BiomeGenBase.plains;
        int var7 = 0;
        boolean var8 = this.CheckIfFresh(par1World, par3, par5);

        for (int var9 = 0; var9 < 64; ++var9)
        {
            int var10 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int var11 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            int var12 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.isAirBlock(var10, var11, var12) && par1World.getBlockId(var10, var11 - 1, var12) == Block.grass.blockID && Block.pumpkin.canPlaceBlockAt(par1World, var10, var11, var12))
            {
                int var13 = par2Random.nextInt(4);

                if (var6 && var7 < 3)
                {
                    if (var8)
                    {
                        par1World.setBlock(var10, var11, var12, FCBetterThanWolves.fcBlockPumpkinFresh.blockID, var13, 2);
                    }
                    else
                    {
                        par1World.setBlock(var10, var11, var12, Block.pumpkin.blockID, var13, 2);
                    }

                    ++var7;
                }
            }
        }

        return true;
    }

    public boolean CheckIfFresh(World var1, int var2, int var3)
    {
        int var4 = var1.getWorldInfo().getSpawnX();
        int var5 = var1.getWorldInfo().getSpawnZ();
        double var6 = (double)(var4 - var2);
        double var8 = (double)(var5 - var3);
        double var10 = var6 * var6 + var8 * var8;
        return var10 > 6250000.0D;
    }
}
