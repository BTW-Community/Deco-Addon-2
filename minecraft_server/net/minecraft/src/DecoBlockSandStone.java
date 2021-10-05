package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockSandStone extends FCBlockSandStone {
    public static final int typeDefault = 0;
    public static final int typeChiseled = 1;
    public static final int typeSmooth = 2;
    public static final int typePolished = 3;
    public static final int typeBrick = 4;
    public static final int typeMossy = 5;
    public static final int typeLargeBrick = 6;
    public static final int typeLargeBrickMossy = 7;
    public static final int typeCracked = 8;
    public static final int typeLargeBrickCracked = 9;
    
	public DecoBlockSandStone(int id) {
		super(id);
        this.setTickRandomly(true);
	}
	
	public void updateTick(World world, int x, int y, int z, Random rand)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == typeDefault && !world.getBlockMaterial(x, y - 1, z).blocksMovement())
        {
            int idAbove = world.getBlockId(x, y + 1, z);

            if (idAbove != Block.waterMoving.blockID && idAbove != Block.waterStill.blockID)
            {
                if ((idAbove == Block.lavaMoving.blockID || idAbove == Block.lavaStill.blockID) && rand.nextInt(15) == 0)
                {
                	world.setBlockMetadataWithNotify(x, y, z, typeCracked);
                    world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
                }
            }
            else if (rand.nextInt(15) == 0)
            {
            	world.setBlockMetadataWithNotify(x, y, z, typeMossy);
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
        }

        if (meta == typeLargeBrick && !world.getBlockMaterial(x, y - 1, z).blocksMovement())
        {
            int idAbove = world.getBlockId(x, y + 1, z);

            if (idAbove != Block.waterMoving.blockID && idAbove != Block.waterStill.blockID)
            {
                if ((idAbove == Block.lavaMoving.blockID || idAbove == Block.lavaStill.blockID) && rand.nextInt(15) == 0)
                {
                	world.setBlockMetadataWithNotify(x, y, z, typeLargeBrickCracked);
                    world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
                }
            }
            else if (rand.nextInt(15) == 0)
            {
            	world.setBlockMetadataWithNotify(x, y, z, typeLargeBrickMossy);
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
        }
    }

	@Override
    public int getItemIDDroppedOnStonecutter(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        
        switch (meta) {
        default:
        case typeDefault:
        	return FCBetterThanWolves.fcBlockSandstoneSidingAndCorner.blockID;
        case typeChiseled:
        case typeSmooth:
        	return DecoDefs.sandStoneSmoothSidingAndCorner.blockID;
        case typePolished:
        	return DecoDefs.sandStonePolishedSidingAndCorner.blockID;
        case typeBrick:
        	return DecoDefs.sandStoneBrickSidingAndCorner.blockID;
        case typeMossy:
        	return DecoDefs.sandStoneMossySidingAndCorner.blockID;
        case typeLargeBrick:
        	return DecoDefs.sandStoneBrickLargeSidingAndCorner.blockID;
        case typeLargeBrickMossy:
        	return DecoDefs.sandStoneBrickLargeMossySidingAndCorner.blockID;
        case typeCracked:
        	return DecoDefs.sandStoneCrackedSidingAndCorner.blockID;
        case typeLargeBrickCracked:
        	return DecoDefs.sandStoneBrickLargeCrackedSidingAndCorner.blockID;
        }
    }

	@Override
    public int getItemCountDroppedOnStonecutter(World world, int x, int y, int z) {
        return 2;
    }
}
