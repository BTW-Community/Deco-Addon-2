package deco.block.blocks;

import btw.block.blocks.GlassBlock;
import btw.world.util.BlockPos;
import deco.block.util.WoodTypeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class WoodFramedGlassBlock extends DecoGlassBlock {
	public WoodFramedGlassBlock(int blockID, String name) {
		super(blockID, name);
	}
	
	// ------------ Client Side Functionality ----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] icons = new Icon[WoodTypeHelper.NUM_TOTAL_WOOD];
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int neightborX, int neighborY, int neighborZ, int side) {
		int blockID = blockAccess.getBlockId(neightborX, neighborY, neighborZ);
		
		if (blockID == this.blockID) {
			return this.shouldRenderSideTowardsNeighborFramedGlass(blockAccess, neightborX, neighborY, neighborZ, side);
		}
		else {
			return super.shouldSideBeRendered(blockAccess, neightborX, neighborY, neighborZ, side);
		}
	}
	
	@Environment(EnvType.CLIENT)
	public boolean shouldRenderSideTowardsNeighborFramedGlass(IBlockAccess blockAccess, int x, int y, int z, int neighborSide) {
		int thisMetadata = blockAccess.getBlockMetadata(x, y, z);
		
		BlockPos neighborPos = new BlockPos(x, y, z, Facing.oppositeSide[neighborSide]);
		int neighborMetadata = blockAccess.getBlockMetadata(neighborPos.x, neighborPos.y, neighborPos.z);
		
		return thisMetadata != neighborMetadata;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		return icons[meta];
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		for (int woodType = 0; woodType < WoodTypeHelper.NUM_TOTAL_WOOD; woodType++) {
			this.icons[woodType] = register.registerIcon(name + WoodTypeHelper.woodNamesCapital[woodType]);
		}
	}
}
