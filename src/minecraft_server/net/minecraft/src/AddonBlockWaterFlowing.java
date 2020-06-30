package net.minecraft.src;

import java.util.Random;

public class AddonBlockWaterFlowing extends FCBlockWaterFlowing {
	public AddonBlockWaterFlowing(int id, Material material) {
		super(id, material);
	}

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        int var6;
        double var11;

        if (this.blockMaterial == Material.water)
        {
            if (rand.nextInt(10) == 0)
            {
                var6 = world.getBlockMetadata(x, y, z);

                if (var6 <= 0 || var6 >= 8)
                {
                    world.spawnParticle("suspended", (double)((float)x + rand.nextFloat()), (double)((float)y + rand.nextFloat()), (double)((float)z + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
                }
            }

            for (var6 = 0; var6 < 0; ++var6)
            {
                int var7 = rand.nextInt(4);
                int var8 = x;
                int var9 = z;

                if (var7 == 0)
                {
                    var8 = x - 1;
                }

                if (var7 == 1)
                {
                    ++var8;
                }

                if (var7 == 2)
                {
                    var9 = z - 1;
                }

                if (var7 == 3)
                {
                    ++var9;
                }

                if (world.getBlockMaterial(var8, y, var9) == Material.air && (world.getBlockMaterial(var8, y - 1, var9).blocksMovement() || world.getBlockMaterial(var8, y - 1, var9).isLiquid()))
                {
                    float var10 = 0.0625F;
                    var11 = (double)((float)x + rand.nextFloat());
                    double var13 = (double)((float)y + rand.nextFloat());
                    double var15 = (double)((float)z + rand.nextFloat());

                    if (var7 == 0)
                    {
                        var11 = (double)((float)x - var10);
                    }

                    if (var7 == 1)
                    {
                        var11 = (double)((float)(x + 1) + var10);
                    }

                    if (var7 == 2)
                    {
                        var15 = (double)((float)z - var10);
                    }

                    if (var7 == 3)
                    {
                        var15 = (double)((float)(z + 1) + var10);
                    }

                    double var17 = 0.0D;
                    double var19 = 0.0D;

                    if (var7 == 0)
                    {
                        var17 = (double)(-var10);
                    }

                    if (var7 == 1)
                    {
                        var17 = (double)var10;
                    }

                    if (var7 == 2)
                    {
                        var19 = (double)(-var10);
                    }

                    if (var7 == 3)
                    {
                        var19 = (double)var10;
                    }

                    world.spawnParticle("splash", var11, var13, var15, var17, 0.0D, var19);
                }
            }
        }

        if (this.blockMaterial == Material.water && rand.nextInt(64) == 0)
        {
            var6 = world.getBlockMetadata(x, y, z);

            if (var6 > 0 && var6 < 8)
            {
                world.playSound((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "liquid.water", rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() * 1.0F + 0.5F, false);
            }
        }

        double var21;
        double var22;

        if (this.blockMaterial == Material.lava && world.getBlockMaterial(x, y + 1, z) == Material.air && !world.isBlockOpaqueCube(x, y + 1, z))
        {
            if (rand.nextInt(100) == 0)
            {
                var21 = (double)((float)x + rand.nextFloat());
                var11 = (double)y + this.maxY;
                var22 = (double)((float)z + rand.nextFloat());
                world.spawnParticle("lava", var21, var11, var22, 0.0D, 0.0D, 0.0D);
                world.playSound(var21, var11, var22, "liquid.lavapop", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }

            if (rand.nextInt(200) == 0)
            {
                world.playSound((double)x, (double)y, (double)z, "liquid.lava", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
        }

        if (rand.nextInt(10) == 0 && world.doesBlockHaveSolidTopSurface(x, y - 1, z) && !world.getBlockMaterial(x, y - 2, z).blocksMovement())
        {
            var21 = (double)((float)x + rand.nextFloat());
            var11 = (double)y - AddonUtilsBlock.getFluidDripOffsetForBlockType(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y - 1, z));
            var22 = (double)((float)z + rand.nextFloat());

            if (this.blockMaterial == Material.water)
            {
                world.spawnParticle("dripWater", var21, var11, var22, 0.0D, 0.0D, 0.0D);
            }
            else
            {
                world.spawnParticle("dripLava", var21, var11, var22, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}