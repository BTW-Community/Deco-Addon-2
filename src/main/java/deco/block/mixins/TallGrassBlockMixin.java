package deco.block.mixins;

import btw.AddonHandler;
import btw.block.blocks.TallGrassBlock;
import deco.block.DecoBlockIDs;
import deco.block.DecoBlocks;
import deco.block.blocks.DoubleTallGrassBlock;
import deco.block.blocks.TallPlantBlock;
import net.minecraft.src.BlockTallGrass;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TallGrassBlock.class)
public abstract class TallGrassBlockMixin extends BlockTallGrass {
	protected TallGrassBlockMixin(int blockID) {
		super(blockID);
	}
	
	@Override
	public boolean attemptToApplyFertilizerTo(World world, int x, int y, int z) {
		if (AddonHandler.isModInstalled("Sock's Crops")) {
			return false;
		}
		
		if (world.getBlockId(x, y + 1, z) == 0) {
			int metadata = world.getBlockMetadata(x, y, z);
			
			if (metadata == 1) { // Grass
				world.setBlockAndMetadataWithNotify(x, y, z, DecoBlocks.tallGrass.blockID, DoubleTallGrassBlock.GRASS_TYPE);
				world.setBlockAndMetadataWithNotify(x, y + 1, z, DecoBlocks.tallGrass.blockID,
						((TallPlantBlock) DecoBlocks.tallGrass).setTopBlock(DoubleTallGrassBlock.GRASS_TYPE, true));
				return true;
			}
			else if (metadata == 2) { // Fern
				world.setBlockAndMetadataWithNotify(x, y, z, DecoBlocks.tallGrass.blockID, DoubleTallGrassBlock.FERN_TYPE);
				world.setBlockAndMetadataWithNotify(x, y + 1, z, DecoBlocks.tallGrass.blockID,
						((TallPlantBlock) DecoBlocks.tallGrass).setTopBlock(DoubleTallGrassBlock.FERN_TYPE, true));
				return true;
			}
		}
		
		return false;
	}
}