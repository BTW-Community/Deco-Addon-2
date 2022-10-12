package deco.block.blocks;

import btw.util.MiscUtils;
import deco.block.DecoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class HayBaleBlock extends Block {
	public HayBaleBlock(int id) {
		super(id, DecoBlocks.hayMaterial);
		this.setStepSound(soundGrassFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setAxesEffectiveOn();
		this.setHardness(0.5F);
		this.setResistance(2.0F);
	}
	
	@Override
	public boolean canBePistonShoveled(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return true;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return true;
	}
	
	@Override
	public int getFacing(int metadata) {
		return metadata;
	}
	
	@Override
	public int setFacing(int metadata, int facing) {
		return facing;
	}
	
	@Override
	public boolean canRotateOnTurntable(IBlockAccess access, int x, int y, int z) {
		return access.getBlockMetadata(x, y, z) != 0;
	}
	
	@Override
	public boolean canTransmitRotationHorizontallyOnTurntable(IBlockAccess access, int X, int Y, int Z) {
		return true;
	}
	
	@Override
	public boolean canTransmitRotationVerticallyOnTurntable(IBlockAccess access, int X, int Y, int Z) {
		return true;
	}
	
	@Override
	public int rotateMetadataAroundJAxis(int metadata, boolean isReverse) {
		return MiscUtils.standardRotateMetadataAroundY(this, metadata, isReverse);
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		if (side < 2) {
			return 0;
		}
		else if (side < 4) {
			return 1;
		}
		else {
			return 2;
		}
	}
	
	@Override
	public boolean canToolsStickInBlock(IBlockAccess blockAccess, int x, int y, int z) {
		return false;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	static Icon topIcon;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		switch (meta) {
			case 0:
				return (side < 2) ? topIcon : blockIcon;
			case 1:
				return (side < 4 && side > 1) ? topIcon : blockIcon;
			default:
				return (side > 3) ? topIcon : blockIcon;
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister r) {
		blockIcon = r.registerIcon("decoBlockHay_side");
		topIcon = r.registerIcon("decoBlockHay_top");
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean renderBlock(RenderBlocks renderBlocks, int x, int y, int z) {
		int facing = this.getFacing(renderBlocks.blockAccess, x, y, z);
		
		switch (facing) {
			case 1:
				renderBlocks.setUVRotateNorth(1);
				renderBlocks.setUVRotateSouth(1);
				break;
			case 2:
				renderBlocks.setUVRotateTop(1);
				renderBlocks.setUVRotateBottom(1);
				renderBlocks.setUVRotateWest(1);
				renderBlocks.setUVRotateEast(1);
			default:
			
		}
		
		renderBlocks.setRenderBounds(0D, 0D, 0D, 1D, 1D, 1D);
		renderBlocks.renderStandardBlock(this, x, y, z);
		renderBlocks.clearUVRotation();
		return true;
	}
}
