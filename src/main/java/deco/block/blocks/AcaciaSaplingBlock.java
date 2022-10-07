package deco.block.blocks;

import deco.block.DecoBlocks;
import net.minecraft.src.World;

import java.util.Random;

public class AcaciaSaplingBlock extends DecoSaplingBlock {
	public static final String[] saplingTypes = new String[] {
			"acacia", "", "", "",
			"acacia", "", "", "",
			"acacia", "", "", "",
			"acaciaMature", "", "",  ""
	};
	
	public AcaciaSaplingBlock(int blockID) {
		super(blockID, new String[] {"decoBlockSaplingAcacia_0"});
		this.setUnlocalizedName("decoBlockSaplingAcacia");
	}
	
	@Override
	public boolean generateTree(World world, Random rand, int x, int y, int z) {
		int baseHeight = 4 + rand.nextInt(3);
		
		//Base tree
		for(int i = 1; i < baseHeight + 4; i++) {
			int blockID = world.getBlockId(x, y + i, z);
			
			//Checks trunk space
			if (!(world.isAirBlock(x, y + i, z) || blockID == DecoBlocks.acaciaLeaves.blockID)) {
				return false;
			}
		}
		
		for(int i = 0; i < baseHeight + 3; i++) {
			world.setBlock(x, y + i, z, DecoBlocks.acaciaLog.blockID);
		}
		
		createAcaciaLeaves(world, rand, x + 0, y + baseHeight + 3, z - 0, 3);
		createAcaciaLeaves(world, rand, x + 0, y + baseHeight + 4, z - 0, 2);
		
		world.setBlock(x, y, z, DecoBlocks.acaciaStump.blockID);
		
		//Branches
		if(rand.nextInt(4) == 0 &&
				(world.isAirBlock(x + 0, y + baseHeight + 1, z + 1) || world.getBlockId(x + 0, y + baseHeight + 1, z + 1) == DecoBlocks.acaciaLeaves.blockID) &&
				(world.isAirBlock(x + 1, y + baseHeight + 2, z + 2) || world.getBlockId(x + 1, y + baseHeight + 2, z + 2) == DecoBlocks.acaciaLeaves.blockID))
		{
			world.setBlock(x + 0, y + baseHeight + 1, z + 1, DecoBlocks.acaciaLog.blockID);
			world.setBlock(x + 1, y + baseHeight + 2, z + 2, DecoBlocks.acaciaLog.blockID);
			createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 2, z + 2, 3);
			createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 3, z + 2, 2);
		}
		
		if(rand.nextInt(4) == 0 &&
				(world.isAirBlock(x + 1, y + baseHeight + 0, z + 0) || world.getBlockId(x + 1, y + baseHeight + 0, z + 0) == DecoBlocks.acaciaLeaves.blockID) &&
				(world.isAirBlock(x + 2, y + baseHeight + 1, z + 0) || world.getBlockId(x + 2, y + baseHeight + 1, z + 0) == DecoBlocks.acaciaLeaves.blockID) &&
				(world.isAirBlock(x + 3, y + baseHeight + 2, z + 1) || world.getBlockId(x + 3, y + baseHeight + 2, z + 1) == DecoBlocks.acaciaLeaves.blockID))
		{
			world.setBlock(x + 1, y + baseHeight + 0, z + 0, DecoBlocks.acaciaLog.blockID);
			world.setBlock(x + 2, y + baseHeight + 1, z + 0, DecoBlocks.acaciaLog.blockID);
			world.setBlock(x + 3, y + baseHeight + 2, z - 1, DecoBlocks.acaciaLog.blockID);
			createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 3, z - 1, 3);
			createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 4, z - 1, 2);
		}
		
		if(rand.nextInt(4) == 0 &&
				(world.isAirBlock(x - 1, y + baseHeight + 0, z + 0) || world.getBlockId(x - 1, y + baseHeight + 0, z + 0) == DecoBlocks.acaciaLeaves.blockID) &&
				(world.isAirBlock(x - 2, y + baseHeight + 1, z + 0) || world.getBlockId(x - 2, y + baseHeight + 1, z + 0) == DecoBlocks.acaciaLeaves.blockID) &&
				(world.isAirBlock(x - 3, y + baseHeight + 2, z - 1) || world.getBlockId(x - 3, y + baseHeight + 2, z - 1) == DecoBlocks.acaciaLeaves.blockID) &&
				(world.isAirBlock(x - 4, y + baseHeight + 3, z - 2) || world.getBlockId(x - 4, y + baseHeight + 3, z - 2) == DecoBlocks.acaciaLeaves.blockID))
		{
			world.setBlock(x - 1, y + baseHeight + 0, z + 0, DecoBlocks.acaciaLog.blockID);
			world.setBlock(x - 2, y + baseHeight + 1, z + 0, DecoBlocks.acaciaLog.blockID);
			world.setBlock(x - 3, y + baseHeight + 2, z - 1, DecoBlocks.acaciaLog.blockID);
			world.setBlock(x - 4, y + baseHeight + 3, z - 2, DecoBlocks.acaciaLog.blockID);
			createAcaciaLeaves(world, rand, x - 4, y + baseHeight + 4, z - 2, 3);
			createAcaciaLeaves(world, rand, x - 4, y + baseHeight + 5, z - 2, 2);
		}
		
		if(rand.nextInt(4) == 0 &&
				(world.isAirBlock(x + 0, y + baseHeight + 0, z - 1) || world.getBlockId(x + 0, y + baseHeight + 0, z - 1) == DecoBlocks.acaciaLeaves.blockID) &&
				(world.isAirBlock(x + 1, y + baseHeight + 1, z - 1) || world.getBlockId(x + 1, y + baseHeight + 1, z - 1) == DecoBlocks.acaciaLeaves.blockID) &&
				(world.isAirBlock(x + 2, y + baseHeight + 2, z - 2) || world.getBlockId(x + 2, y + baseHeight + 2, z - 2) == DecoBlocks.acaciaLeaves.blockID) &&
				(world.isAirBlock(x + 3, y + baseHeight + 3, z - 2) || world.getBlockId(x + 3, y + baseHeight + 3, z - 2) == DecoBlocks.acaciaLeaves.blockID))
		{
			world.setBlock(x + 0, y + baseHeight + 0, z - 1, DecoBlocks.acaciaLog.blockID);
			world.setBlock(x + 1, y + baseHeight + 1, z - 2, DecoBlocks.acaciaLog.blockID);
			world.setBlock(x + 2, y + baseHeight + 2, z - 2, DecoBlocks.acaciaLog.blockID);
			world.setBlock(x + 3, y + baseHeight + 3, z - 2, DecoBlocks.acaciaLog.blockID);
			createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 3, z - 2, 3);
			createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 4, z - 2, 2);
		}
		
		if(rand.nextInt(4) == 0 &&
				(world.isAirBlock(x + 0, y + baseHeight + 0, z - 1) || world.getBlockId(x + 0, y + baseHeight + 0, z - 1) == DecoBlocks.acaciaLeaves.blockID) &&
				(world.isAirBlock(x + 0, y + baseHeight + 0, z - 2) || world.getBlockId(x + 0, y + baseHeight + 0, z - 2) == DecoBlocks.acaciaLeaves.blockID) &&
				(world.isAirBlock(x + 1, y + baseHeight + 1, z - 3) || world.getBlockId(x + 1, y + baseHeight + 0, z - 3) == DecoBlocks.acaciaLeaves.blockID))
		{
			world.setBlock(x + 0, y + baseHeight + 0, z - 1, DecoBlocks.acaciaLog.blockID);
			world.setBlock(x + 0, y + baseHeight + 0, z - 2, DecoBlocks.acaciaLog.blockID);
			world.setBlock(x + 1, y + baseHeight + 1, z - 3, DecoBlocks.acaciaLog.blockID);
			createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 1, z - 3, 3);
			createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 2, z - 3, 2);
		}
		
		return true;
	}
	
	@Override
	public int getLogID() {
		return DecoBlocks.acaciaLog.blockID;
	}
	
	@Override
	public int getStumpID() {
		return DecoBlocks.acaciaStump.blockID;
	}
	
	//------------- Class Specific Methods ------------//
	
	private static void createAcaciaLeaves(World world, Random rand, int x, int y, int z, int size) {
		for (int i = -size + x; i < size + 1 + x; i++) {
			for (int k = -size + z; k < size + 1 + z; k++) {
				int currentID = world.getBlockId(i, y, k);
				
				if (currentID == 0) {
					if (!((i == -size + x && k == -size + z) ||
							(i == -size + x && k == size + z) ||
							(i == size + x && k == -size + z) ||
							(i == size + x && k == size + z)))
					{
						world.setBlock(i, y, k, DecoBlocks.acaciaLeaves.blockID);
					}
				}
			}
		}
		
		if (size == 3) {
			world.setBlock(x, y, z, DecoBlocks.acaciaLog.blockID);
		}
	}
}
