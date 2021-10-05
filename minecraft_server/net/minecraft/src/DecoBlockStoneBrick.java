package net.minecraft.src;

import java.util.List;

public class DecoBlockStoneBrick extends FCBlockStoneBrick {
	public DecoBlockStoneBrick(int id) {
		super(id);
	}

	@Override
    public int getItemIDDroppedOnStonecutter(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        
        switch (meta) {
        default:
        case 0:
        	return FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner.blockID;
        case 1:
        	return DecoDefs.stoneBrickMossySidingAndCorner.blockID;
        case 2:
        	return DecoDefs.stoneBrickCrackedSidingAndCorner.blockID;
        }
    }

	@Override
    public int getItemCountDroppedOnStonecutter(World world, int x, int y, int z) {
        return 2;
    }

	//CLIENT ONLY
    public static final String[] textureStrings = new String[] {"stonebricksmooth", "stonebricksmooth_mossy", "stonebricksmooth_cracked", "stonebricksmooth_carved"};
    private Icon[] icons;
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 >= textureStrings.length)
        {
            par2 = 0;
        }

        return this.icons[par2];
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }
}