package deco.block.blocks;

import deco.block.DecoBlocks;
import net.minecraft.src.World;

import java.util.Random;

public class FirSaplingBlock extends DecoSaplingBlock {
	public static final String[] saplingTypes = new String[] {
			"fir", "", "", "",
			"fir", "", "", "",
			"fir", "", "", "",
			"firMature", "", "",  ""
	};
	
	public FirSaplingBlock(int blockID) {
		super(blockID, new String[] {"decoBlockSaplingFir_0"});
		this.setUnlocalizedName("decoBlockSaplingFir");
	}
	
	@Override
	public boolean generateTree(World world, Random rand, int x, int y, int z, int treeType) {
		return false;
	}
	
	@Override
	public int getLogID(int type) {
		return DecoBlocks.firLog.blockID;
	}
	
	@Override
	public int getStumpID(int type) {
		return DecoBlocks.firStump.blockID;
	}
}
