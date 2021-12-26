package net.minecraft.src;

import java.util.Random;

public class DecoBlockEnchantmentTable extends FCBlockEnchantmentTable {
	protected DecoBlockEnchantmentTable(int blockID) {
		super(blockID);
	}

	//------ Client Only Methods ------//
	
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        super.randomDisplayTick(world, x, y, z, rand);

        for (int i = x - 2; i <= x + 2; i++) {
            for (int k = z - 2; k <= z + 2; k++) {
                if (i > x - 2 && i < x + 2 && k == z - 1) {
                    k = z + 2;
                }

                if (rand.nextInt(16) == 0) {
                    for (int j = y - 1; j <= y + 2; j++) {
                        if (world.getBlockId(i, j, k) == Block.bookShelf.blockID) {
                            if (!world.isAirBlock((i - x) / 2 + x, j, (k - z) / 2 + z)) {
                                break;
                            }

                            world.spawnParticle("enchantmenttable", 
                            		x + 0.5D, y + 2.0D, z + 0.5D, 
                            		(i - x) + rand.nextFloat() - 0.5D, (j - y) - rand.nextFloat() - 1.0F, (k - z) + rand.nextFloat() - 0.5D);
                        }
                    }
                }
            }
        }
    }
}