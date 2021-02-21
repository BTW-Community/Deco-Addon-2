package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockSandStone extends FCBlockSandStone {
	public DecoBlockSandStone(int id) {
		super(id);
        this.setTickRandomly(true);
	}

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
        par3List.add(new ItemStack(par1, 1, 8));
        par3List.add(new ItemStack(par1, 1, 9));
    }
	
	public void updateTick(World world, int x, int y, int z, Random rand)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == 0 && !world.getBlockMaterial(x, y - 1, z).blocksMovement())
        {
            int idAbove = world.getBlockId(x, y + 1, z);

            if (idAbove != Block.waterMoving.blockID && idAbove != Block.waterStill.blockID)
            {
                if ((idAbove == Block.lavaMoving.blockID || idAbove == Block.lavaStill.blockID) && rand.nextInt(15) == 0)
                {
                	world.setBlockMetadataWithNotify(x, y, z, 8);
                    world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
                }
            }
            else if (rand.nextInt(15) == 0)
            {
            	world.setBlockMetadataWithNotify(x, y, z, 5);
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
        }

        if (meta == 6 && !world.getBlockMaterial(x, y - 1, z).blocksMovement())
        {
            int idAbove = world.getBlockId(x, y + 1, z);

            if (idAbove != Block.waterMoving.blockID && idAbove != Block.waterStill.blockID)
            {
                if ((idAbove == Block.lavaMoving.blockID || idAbove == Block.lavaStill.blockID) && rand.nextInt(15) == 0)
                {
                	world.setBlockMetadataWithNotify(x, y, z, 9);
                    world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
                }
            }
            else if (rand.nextInt(15) == 0)
            {
            	world.setBlockMetadataWithNotify(x, y, z, 7);
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
        }
    }
}
