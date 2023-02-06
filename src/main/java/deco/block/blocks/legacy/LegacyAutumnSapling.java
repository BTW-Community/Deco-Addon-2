package deco.block.blocks.legacy;

import deco.block.DecoBlocks;
import deco.block.util.WoodTypeHelper;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class LegacyAutumnSapling extends LegacyDecoSaplingBlock {
	public static final int RED_TYPE = 0;
	public static final int ORANGE_TYPE = 1;
	public static final int YELLOW_TYPE = 2;
	
	public static final String[] saplingTypes = new String[] {
			"red", "orange", "yellow", "",
			"red", "orange", "yellow", "",
			"red", "orange", "yellow", "",
			"redMature", "orangeMature", "yellowMature", ""
	};
	
	public static String[] names = new String[] {"red", "orange", "yellow"};
	
	public LegacyAutumnSapling(int blockID) {
		super(blockID, new String[] {
				"decoBlockSaplingAutumnRed_0",
				"decoBlockSaplingAutumnOrange_0",
				"decoBlockSaplingAutumnYellow_0"
		});
		this.setUnlocalizedName("decoBlockSaplingAutumnOld");
	}
	
	@Override
	public boolean generateTree(World world, Random rand, int x, int y, int z, int type) {
		switch (type) {
			default:
			case 0:
				// Cherry
				return this.generateStandardTree(world, rand, x, y, z, DecoBlocks.cherryLog.blockID, 0, DecoBlocks.cherryStump.blockID, 0,
						DecoBlocks.autumnLeaves.blockID, RED_TYPE);
			case 1:
				return this.generateStandardTree(world, rand, x, y, z, Block.wood.blockID, WoodTypeHelper.OAK_WOOD_TYPE, Block.wood.blockID,
						WoodTypeHelper.OAK_WOOD_TYPE | 12, DecoBlocks.autumnLeaves.blockID, ORANGE_TYPE);
			case 2:
				return this.generateStandardTree(world, rand, x, y, z, Block.wood.blockID, WoodTypeHelper.BIRCH_WOOD_TYPE, Block.wood.blockID,
						WoodTypeHelper.BIRCH_WOOD_TYPE | 12, DecoBlocks.autumnLeaves.blockID, YELLOW_TYPE);
		}
	}
	
	@Override
	public int getLogID(int type) {
		switch (type) {
			case 0:
				// Cherry
				return DecoBlocks.cherryLog.blockID;
			default:
				return Block.wood.blockID;
		}
	}
	
	@Override
	public int getLogMetadata(int type) {
		switch (type) {
			default:
			case 0:
				// Cherry
				return 0;
			case 1:
				return WoodTypeHelper.OAK_WOOD_TYPE;
			case 2:
				return WoodTypeHelper.BIRCH_WOOD_TYPE;
		}
	}
	
	@Override
	public int getStumpID(int type) {
		switch (type) {
			case 0:
				// Cherry
				return 0;
			default:
				return Block.wood.blockID;
		}
	}
	
	@Override
	public int getStumpMetadata(int type) {
		switch (type) {
			default:
			case 0:
				// Cherry
				return 0;
			case 1:
				return WoodTypeHelper.OAK_WOOD_TYPE | 12;
			case 2:
				return WoodTypeHelper.BIRCH_WOOD_TYPE | 12;
		}
	}
}
