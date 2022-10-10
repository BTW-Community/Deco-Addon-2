package deco.block.blocks;

import deco.block.DecoBlocks;
import net.minecraft.src.World;

public class LooseSlateBrickBlock extends LooseStoneBrickVariantsBlock {
	public LooseSlateBrickBlock(int blockID) {
		super(blockID, StoneVariantsBlock.SLATE_TYPE, "decoOverlaySlateBrickLava");
		this.setUnlocalizedName("decoBlockSlateBrickLoose");
	}
	
	@Override
	public void setToStone(World world, int x, int y, int z) {
		world.setBlockAndMetadataWithNotify(x, y, z, DecoBlocks.slate.blockID, 0);
	}
}
