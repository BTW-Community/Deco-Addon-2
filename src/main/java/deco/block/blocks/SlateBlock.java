package deco.block.blocks;

import deco.block.DecoBlockIDs;
import deco.block.DecoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class SlateBlock extends StoneVariantsBlock {
	public SlateBlock(int blockID) {
		super(blockID, false);
		this.setUnlocalizedName("decoBlockSlate");
	}
	
	@Override
	public int idDropped(int metadata, Random random, int fortuneModifier) {
		return DecoBlocks.looseSlateCobblestone.blockID;
	}
	
	@Override
	public int getStrata(int metadata) {
		return StoneVariantsBlock.SLATE_STRATA;
	}
	
	@Override
	public int getType(int metadata) {
		return StoneVariantsBlock.SLATE_TYPE;
	}
	
	@Override
	public int getBlockIDOnInfest(EntityLiving entity, int metadata) {
		return DecoBlocks.infestedSlate.blockID;
	}
	
	@Override
	public void setIsCracked(World world, int x, int y, int z, boolean setCracked) {
		if (this.isCracked != setCracked) {
			if (setCracked) {
				world.setBlockAndMetadata(x, y, z, DecoBlockIDs.CRACKED_STONE_VARIANTS_ID, StoneVariantsBlock.SLATE_TYPE);
			}
			else {
				world.setBlockAndMetadata(x, y, z, this.blockID, 0);
			}
		}
	}
	
	//------ Pillar Block Functionality ------//
	
	@Override
	public boolean canRotateOnTurntable(IBlockAccess blockAccess, int x, int y, int z) {
		return (blockAccess.getBlockMetadata(x,y,z) & 12) != 0;
	}
	
	@Override
	public int rotateMetadataAroundJAxis(int metadata, boolean isReversed) {
		int directionMeta = metadata & 12;
		
		if (directionMeta != 0) {
			if (directionMeta == 4) {
				directionMeta = 8;
			}
			else if (directionMeta == 8) {
				directionMeta = 4;
			}
			
			metadata = metadata & -13 | directionMeta;
		}
		
		return metadata;
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		int type = metadata & 3;
		byte directionMeta = 0;
		
		switch (side) {
			case 0:
			case 1:
				directionMeta = 0;
				break;
			case 2:
			case 3:
				directionMeta = 8;
				break;
			case 4:
			case 5:
				directionMeta = 4;
		}
		
		return type | directionMeta;
	}
	
	//------------- Class Specific Methods ------------//
	
	@Environment(EnvType.CLIENT)
	public Icon topIcon;
	@Environment(EnvType.CLIENT)
	public Icon sideIcon;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		int directionMeta = metadata & 12;
		
		if (directionMeta == 0 && (side == 0 || side == 1) ||
				directionMeta == 4 && (side == 4 || side == 5) ||
				directionMeta == 8 && (side == 2 || side == 3)) {
			return topIcon;
		}
		else {
			return sideIcon;
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		topIcon = register.registerIcon("decoBlockSlate_top");
		sideIcon = register.registerIcon("decoBlockSlate_side");
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean renderBlock(RenderBlocks render, int x, int y, int z) {
		int meta = render.blockAccess.getBlockMetadata(x, y, z);
		int directionMeta = meta & 12;
		
		if (directionMeta == 4) {
			render.setUVRotateTop(1);
			render.setUVRotateBottom(1);
			render.setUVRotateWest(1);
			render.setUVRotateEast(1);
		}
		else if (directionMeta == 8) {
			render.setUVRotateNorth(1);
			render.setUVRotateSouth(1);
		}
		
		render.setRenderBounds(0D,0D,0D,1D,1D,1D);
		render.renderStandardBlock(this, x, y, z);
		render.clearUVRotation();
		return true;
	}
}
