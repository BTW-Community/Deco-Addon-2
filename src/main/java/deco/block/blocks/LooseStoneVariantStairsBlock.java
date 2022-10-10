package deco.block.blocks;

import btw.block.blocks.MortarReceiverStairsBlock;
import net.minecraft.src.Block;
import net.minecraft.src.World;

public class LooseStoneVariantStairsBlock extends MortarReceiverStairsBlock {
	private int mortaredID;
	
	public LooseStoneVariantStairsBlock(int blockID, Block referenceBlock, int referenceBlockMetadata, int mortaredID) {
		super(blockID, referenceBlock, referenceBlockMetadata);
		this.mortaredID = mortaredID;
	}
	
	@Override
	public boolean onMortarApplied(World world, int x, int y, int z) {
		world.setBlockAndMetadataWithNotify(x, y, z, mortaredID, world.getBlockMetadata(x, y, z));
		return true;
	}
}
