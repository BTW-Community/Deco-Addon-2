package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.model.BlockModel;
import deco.block.DecoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class LanternBlock extends Block {
	public static final float WIDTH = 6/16F;
	public static final float HALF_WIDTH = WIDTH / 2;
	
	public static final float UPPER_WIDTH = 4/16F;
	public static final float UPPER_HALF_WIDTH = UPPER_WIDTH / 2;
	public static final float UPPER_OFFSET = 1/16F;
	
	public static final float BASE_HEIGHT = 7/16F;
	public static final float FULL_HEIGHT = 9/16F;
	
	private String name;
	private int itemID;
	
	public LanternBlock(int blockID, Material material, String name, int itemID) {
		super(blockID, material);
		this.name = name;
		this.itemID = itemID;
		
		setUnlocalizedName("decoBlockLantern" + name);
		setHardness(0.5F);
		setLightValue(1F);
		
		this.initBlockBounds(0.5F - HALF_WIDTH, 0.0D, 0.5F - HALF_WIDTH, 0.5F + HALF_WIDTH, FULL_HEIGHT, 0.5F + HALF_WIDTH);
		
		if (material == Material.iron) {
			this.setStepSound(Block.soundMetalFootstep);
			this.setPicksEffectiveOn();
		}
		else {
			this.setStepSound(Block.soundWoodFootstep);
			this.setAxesEffectiveOn();
		}
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		this.getBlockBoundsFromPoolBasedOnState(world, x, y, z);
		return this.setFacing(metadata, side);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean canGroundCoverRestOnBlock(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public float groundCoverRestingOnVisualOffset(IBlockAccess Access, int x, int y, int z) {
		return -1.0F;
	}
	
	@Override
	public boolean getCanBlockLightItemOnFire(IBlockAccess Access, int x, int y, int z) {
		return true;
	}
	
	@Override
	public AxisAlignedBB getBlockBoundsFromPoolBasedOnState(IBlockAccess Access, int x, int y, int z) {
		double[] bounds;
		
		switch (this.getFacing(Access, x, y, z)) {
			default:
			case 0: // Bottom
				bounds = new double[] {
						0.5F - HALF_WIDTH, 0.0D, 0.5F - HALF_WIDTH,
						0.5F + HALF_WIDTH, 1D, 0.5F + HALF_WIDTH
				};
				break;
			case 1: // Top
				bounds = new double[] {
						0.5F - HALF_WIDTH, 0.0D, 0.5F - HALF_WIDTH,
						0.5F + HALF_WIDTH, FULL_HEIGHT, 0.5F + HALF_WIDTH
				};
				break;
			case 2: // Pos Z
				bounds = new double[] {
						0.5F - HALF_WIDTH, 0.0D, 1 - WIDTH,
						0.5F + HALF_WIDTH, FULL_HEIGHT, 1D
				};
				break;
			case 3: // Neg Z
				bounds = new double[] {
						0.5F - HALF_WIDTH, 0.0D, 0.0D,
						0.5F + HALF_WIDTH, FULL_HEIGHT, WIDTH
				};
				break;
			case 4: // Pos X
				bounds = new double[] {
						1 - WIDTH, 0.0D, 0.5F - HALF_WIDTH,
						1D, FULL_HEIGHT, 0.5F + HALF_WIDTH
				};
				break;
			case 5: // Neg X
				bounds = new double[] {
						0.0D, 0.0D, 0.5F - HALF_WIDTH,
						WIDTH, FULL_HEIGHT, 0.5F + HALF_WIDTH
				};
		}
		
		return AxisAlignedBB.getAABBPool().getAABB(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
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
	public boolean canRotateOnTurntable(IBlockAccess blockAccess, int x, int y, int z) {
		return false;
	}
	
	@Override
	public boolean canTransmitRotationHorizontallyOnTurntable(IBlockAccess blockAccess, int x, int y, int z) {
		return false;
	}
	
	@Override
	public boolean canTransmitRotationVerticallyOnTurntable(IBlockAccess blockAccess, int x, int y, int z) {
		return false;
	}
	
	@Override
	public boolean hasCenterHardPointToFacing(IBlockAccess blockAccess, int x, int y, int z, int facing, boolean ignoreTransparency) {
		if (this.getFacing(blockAccess, x, y, z) == 0 && facing == 1) {
			return true;
		}
		else if (this.getFacing(blockAccess, x, y, z) == 1 && facing == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortuneModifier) {
		return this.itemID;
	}
	
	@Override
	public boolean getPreventsFluidFlow(World world, int x, int y, int z, Block fluidBlock) {
		return false;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	Icon TopIcon;
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return true;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon("decoBlockLantern" + name + "_side");
		TopIcon = register.registerIcon("decoBlockLantern" + name + "_top");
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		return side < 2 ? TopIcon : blockIcon;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean renderBlock(RenderBlocks renderBlocks, int x, int y, int z) {
		int facing = this.getFacing(renderBlocks.blockAccess, x, y, z);
		
		// Lower section
		switch (facing) {
			default:
			case 0: // Bottom
			case 1: // Top
				renderBlocks.setRenderBounds(
						0.5F - HALF_WIDTH, 0.0D, 0.5F - HALF_WIDTH,
						0.5F + HALF_WIDTH, BASE_HEIGHT, 0.5F + HALF_WIDTH);
				break;
			case 2: // Pos Z
				renderBlocks.setRenderBounds(
						0.5F - HALF_WIDTH, 0.0D, 1 - WIDTH,
						0.5F + HALF_WIDTH, BASE_HEIGHT, 1D);
				break;
			case 3: // Neg Z
				renderBlocks.setRenderBounds(
						0.5F - HALF_WIDTH, 0.0D, 0.0D,
						0.5F + HALF_WIDTH, BASE_HEIGHT, WIDTH);
				break;
			case 4: // Pos X
				renderBlocks.setRenderBounds(
						1 - WIDTH, 0.0D, 0.5F - HALF_WIDTH,
						1D, BASE_HEIGHT, 0.5F + HALF_WIDTH);
				break;
			case 5: // Neg X
				renderBlocks.setRenderBounds(
						0.0D, 0.0D, 0.5F - HALF_WIDTH,
						WIDTH, BASE_HEIGHT, 0.5F + HALF_WIDTH);
		}
		
		renderBlocks.renderStandardBlock(this, x, y, z);
		
		// Upper section
		switch (facing) {
			default:
			case 0: // Bottom
			case 1: // Top
				renderBlocks.setRenderBounds(
						0.5F - UPPER_HALF_WIDTH, BASE_HEIGHT, 0.5F - UPPER_HALF_WIDTH,
						0.5F + UPPER_HALF_WIDTH, FULL_HEIGHT, 0.5F + UPPER_HALF_WIDTH);
				break;
			case 2: // Pos Z
				renderBlocks.setRenderBounds(
						0.5F - UPPER_HALF_WIDTH, BASE_HEIGHT, 1 - UPPER_WIDTH - UPPER_OFFSET,
						0.5F + UPPER_HALF_WIDTH, FULL_HEIGHT, 1 - UPPER_OFFSET);
				break;
			case 3: // Neg Z
				renderBlocks.setRenderBounds(
						0.5F - UPPER_HALF_WIDTH, BASE_HEIGHT, UPPER_OFFSET,
						0.5F + UPPER_HALF_WIDTH, FULL_HEIGHT, UPPER_WIDTH + UPPER_OFFSET);
				break;
			case 4: // Pos X
				renderBlocks.setRenderBounds(
						1 - UPPER_WIDTH - UPPER_OFFSET, BASE_HEIGHT, 0.5F - UPPER_HALF_WIDTH,
						1 - UPPER_OFFSET, FULL_HEIGHT, 0.5F + UPPER_HALF_WIDTH);
				break;
			case 5: // Neg X
				renderBlocks.setRenderBounds(
						UPPER_OFFSET, BASE_HEIGHT, 0.5F - UPPER_HALF_WIDTH,
						UPPER_WIDTH + UPPER_OFFSET, FULL_HEIGHT, 0.5F + UPPER_HALF_WIDTH);
		}
		
		renderBlocks.renderStandardBlock(this, x, y, z);
		
		// Support
		if (facing == 0) {
			if (this.blockMaterial == Material.iron) {
				BlockModel chainModel = ((ChainBlock) DecoBlocks.chain).blockModel.makeTemporaryCopy();
				chainModel.renderAsBlock(renderBlocks, DecoBlocks.chain, x, y, z);
			}
			else {
				AxisAlignedBB ropeBounds = BTWBlocks.ropeBlock.getBlockBoundsFromPoolBasedOnState(renderBlocks.blockAccess, x, y, z);
				ropeBounds.minY += 1/16F;
				renderBlocks.setRenderBounds(ropeBounds);
				renderBlocks.renderStandardBlock(BTWBlocks.ropeBlock, x, y, z);
			}
		}
		
		return true;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public int idPicked(World world, int x, int y, int z) {
		return this.itemID;
	}
}
