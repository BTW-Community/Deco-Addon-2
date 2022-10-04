package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.PlanterBlockBase;
import deco.block.DecoBlocks;
import deco.world.util.TreeGenerator;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class MahoganySaplingBlock extends DecoSaplingBlock {
	public static final String[] saplingTypes = new String[] {
			"mahogany", "", "", "",
			"mahogany", "", "", "",
			"mahogany", "", "", "",
			"mahoganyMature", "", "",  ""
	};
	
	public MahoganySaplingBlock(int blockID) {
		super(blockID, new String[] {"decoBlockSaplingMahogany_0"});
		this.setUnlocalizedName("decoBlockSaplingMahogany");
	}
	
	@Override
	public void growTree(World world, int x, int y, int z, Random rand) {
		int treeType = world.getBlockMetadata(x, y, z) & 3;
		boolean planter = Block.blocksList[world.getBlockId(x, y - 1, z)] instanceof PlanterBlockBase;
		
		world.setBlock(x, y, z, 0);
		
		boolean success = TreeGenerator.generateMahogany(world, rand, x, y, z);
		
		if (!success) {
			world.setBlockAndMetadataWithNotify(x, y, z, this.blockID, treeType | 3 << 2);
		}
		else if (planter) {
			world.setBlock(x, y, z, DecoBlocks.mahoganyLog.blockID);
			
			//Block break sfx
			world.playAuxSFX(2001, x, y - 1, z, BTWBlocks.planterWithSoil.blockID);
			
			world.setBlockAndMetadata(x, y - 1, z, DecoBlocks.mahoganyStump.blockID, 0);
		}
	}
}
