package deco.block.mixins;

import btw.block.blocks.CocoaPlantBlock;
import deco.block.util.BlockInterface;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CocoaPlantBlock.class)
public abstract class CocoaPlantBlockMixin extends BlockCocoa {
	public CocoaPlantBlockMixin(int blockID) {
		super(blockID);
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		int direction = getDirection(world.getBlockMetadata(x, y, z));
		x += Direction.offsetX[direction];
		z += Direction.offsetZ[direction];
		int blockID = world.getBlockId(x, y, z);
		return blockID != 0 && ((BlockInterface) Block.blocksList[blockID]).canCocoaBeansGrowOnBlock(world, x, y, z);
	}
}
