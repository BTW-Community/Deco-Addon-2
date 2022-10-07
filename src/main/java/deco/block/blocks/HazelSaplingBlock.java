package deco.block.blocks;

import deco.block.DecoBlockIDs;
import net.minecraft.src.World;

import java.util.Random;

public class HazelSaplingBlock extends DecoSaplingBlock {
	public static final String[] saplingTypes = new String[]{
			"hazel", "", "", "",
			"hazel", "", "", "",
			"hazel", "", "", "",
			"hazelMature", "", "", ""
	};
	
	public HazelSaplingBlock(int blockID) {
		super(blockID, new String[]{"decoBlockSaplingHazel_0"});
		this.setUnlocalizedName("decoBlockSaplingHazel");
	}
	
	@Override
	public boolean generateTree(World world, Random rand, int x, int y, int z) {
		return false;
	}
	
	@Override
	public int getLogID() {
		return DecoBlockIDs.HAZEL_LOG_ID;
	}
	
	@Override
	public int getStumpID() {
		return DecoBlockIDs.HAZEL_STUMP_ID;
	}
}
