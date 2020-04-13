package net.minecraft.src;

import java.util.Random;

public class AddonBlockNetherrackSuperheated extends FCBlockNetherrack {
	public AddonBlockNetherrackSuperheated(int id) {
		super(id);
		this.setUnlocalizedName("ginger_netherrackSuperheated");
		this.setTickRandomly(true);
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int var1, Random var2, int var3)
	{
		return Block.netherrack.blockID;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		int meta = world.getBlockMetadata(x, y, z);
		int idBelow = world.getBlockId(x, y - 1, z);
		if (idBelow == FCBetterThanWolves.fcBlockFireStoked.blockID) {
			if (meta == 1) {
				world.setBlock(x, y, z, AddonDefs.magma.blockID);
				world.setBlockMetadataWithNotify(x, y, z, 0, 4);
			}
			else {
				world.setBlockMetadataWithClient(x, y, z, meta + 1);
			}
		}
		else {
			world.setBlockWithNotify(x, y, z, Block.netherrack.blockID);
		}
	}

	/**
	 * How many world ticks before ticking
	 */
	public int tickRate(World par1World)
	{
		return 10;
	}

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
    	int idBelow = world.getBlockId(x, y - 1, z);
    	if (idBelow != FCBetterThanWolves.fcBlockFireStoked.blockID) {
			world.setBlockWithNotify(x, y, z, Block.netherrack.blockID);
    	}
    }
    
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
    	for (int i = 2; i < 6; i++)
        {
    		double var8 = (double)var2 + 0.5D;
    		double var10 = (double)var3 + var5.nextDouble() * 0.75D;
    		double var12 = (double)var4 + 0.5D;
            double var14 = 0.75D;
            double var16 = -0.75D + var5.nextDouble() * 1.5D;

            if (i == 2)
            {
                var8 += var16;
                var12 -= var14;
            }
            else if (i == 3)
            {
                var8 += var16;
                var12 += var14;
            }
            else if (i == 4)
            {
                var8 -= var14;
                var12 += var16;
            }
            else if (i == 5)
            {
                var8 += var14;
                var12 += var16;
            }

            var1.spawnParticle("fcwhitesmoke", var8, var10, var12, 0.0D, 0.0D, 0.0D);
        }
    }
}
