package deco.block.blocks;

import deco.block.DecoBlocks;
import net.minecraft.src.World;

import java.util.Random;

public class MangroveSaplingBlock extends DecoSaplingBlock {
	public static final String[] saplingTypes = new String[] {
			"mangrove", "", "", "",
			"mangrove", "", "", "",
			"mangrove", "", "", "",
			"mangroveMature", "", "",  ""
	};
	
	public MangroveSaplingBlock(int blockID) {
		super(blockID, new String[] {"decoBlockSaplingMangrove_0"});
		this.setUnlocalizedName("decoBlockSaplingMangrove");
	}
	
	@Override
	public boolean generateTree(World world, Random rand, int x, int y, int z) {
		return false;
	}
	
	@Override
	public int getLogID() {
		return DecoBlocks.mangroveLog.blockID;
	}
	
	@Override
	public int getStumpID() {
		return DecoBlocks.mangroveStump.blockID;
	}
}
