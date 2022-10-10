package deco.block.blocks;

import deco.block.DecoBlocks;
import net.minecraft.src.World;

public class LooseSlateCobblestoneBlock extends LooseCobblestoneVariantsBlock {
	public LooseSlateCobblestoneBlock(int blockID) {
		super(blockID, StoneVariantsBlock.SLATE_TYPE, "decoOverlaySlateCobbleLava");
		this.setUnlocalizedName("decoBlockSlateCobbleLoose");
	}
	
	@Override
	public void setToStone(World world, int x, int y, int z) {
		world.setBlockAndMetadataWithNotify(x, y, z, DecoBlocks.slate.blockID, 0);
	}
}
