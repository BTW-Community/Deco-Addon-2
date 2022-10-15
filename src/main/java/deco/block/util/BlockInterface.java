package deco.block.util;

import net.minecraft.src.IBlockAccess;

public interface BlockInterface {
	default boolean canCocoaBeansGrowOnBlock(IBlockAccess blockAccess, int x, int y, int z) {
		return false;
	}
}
