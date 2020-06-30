package net.minecraft.src;

import java.util.Random;

public class AddonBlockNetherrackSuperheated extends FCBlockNetherrack {
	public AddonBlockNetherrackSuperheated(int id) {
		super(id);
		this.setUnlocalizedName("ginger_netherrackSuperheated");
		this.setTickRandomly(true);
		this.setCreativeTab(null);
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
				world.setBlockMetadataWithNotify(x, y, z, 0);
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
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
    	int idBelow = world.getBlockId(x, y - 1, z);
    	if (idBelow != FCBetterThanWolves.fcBlockFireStoked.blockID) {
			world.setBlockWithNotify(x, y, z, Block.netherrack.blockID);
    	}
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return false;
    }
}
