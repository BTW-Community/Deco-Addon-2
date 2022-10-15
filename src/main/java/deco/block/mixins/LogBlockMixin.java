package deco.block.mixins;

import btw.block.blocks.LogBlock;
import deco.block.util.BlockInterface;
import deco.block.util.WoodTypeHelper;
import net.minecraft.src.BlockLog;
import net.minecraft.src.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LogBlock.class)
public class LogBlockMixin extends BlockLog implements BlockInterface {
	protected LogBlockMixin(int blockID) {
		super(blockID);
	}
	
	@Override
	public boolean canCocoaBeansGrowOnBlock(IBlockAccess blockAccess, int x, int y, int z) {
		int logType = blockAccess.getBlockMetadata(x, y, z) & 3;
		return WoodTypeHelper.canWoodTypeGrowCocoa(logType);
	}
}
