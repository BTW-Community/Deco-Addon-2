package deco.block.blocks;

import net.minecraft.src.IBlockAccess;

public interface SlabInterface {
	boolean getIsUpsideDown(IBlockAccess blockAccess, int x, int y, int z);
	
	int setIsUpsideDown(int metadata, boolean isUpsideDown);
	
	int getNumTypes();
}
