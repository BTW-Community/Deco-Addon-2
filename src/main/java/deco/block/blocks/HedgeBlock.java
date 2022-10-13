package deco.block.blocks;

import btw.block.blocks.LeavesBlock;
import deco.block.DecoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;

public class HedgeBlock extends LeavesBlock {
	public HedgeBlock(int blockID) {
		super(blockID);
		this.blockMaterial = DecoBlocks.hedgeMaterial;
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setUnlocalizedName("decoBlockHedge");
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean shouldRenderNeighborHalfSlabSide(IBlockAccess blockAccess, int x, int y, int z, int side, boolean isUpsideDown) {
		return true;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean shouldRenderNeighborFullFaceSide(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return true;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return true;
	}
}
