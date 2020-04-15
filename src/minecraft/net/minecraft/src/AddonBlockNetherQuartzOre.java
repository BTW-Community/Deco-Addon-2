package net.minecraft.src;

import java.util.Random;

public class AddonBlockNetherQuartzOre extends FCBlockNetherQuartzOre {
	public AddonBlockNetherQuartzOre(int id) {
		super(id);
        this.setTickRandomly(true);
	}

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
    	//Spreads quartz to nearby magma blocks in the nether
        if (!world.isRemote && world.provider.isHellWorld && rand.nextInt(4) == 0 && world.checkChunksExist(x - 1, y - 1, z - 1, x + 1, y + 1, z + 1)) {
        	int randFacing = rand.nextInt(6);
        	FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z);
        	blockPos.AddFacingAsOffset(randFacing);

            if (world.getBlockId(blockPos.i, blockPos.j, blockPos.k) == AddonDefs.magma.blockID)
            {
                world.setBlockWithNotify(blockPos.i, blockPos.j, blockPos.k, Block.oreNetherQuartz.blockID);
            }
        }
    }
}
