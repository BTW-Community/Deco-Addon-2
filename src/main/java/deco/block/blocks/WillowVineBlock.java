package deco.block.blocks;

import btw.block.util.Flammability;
import btw.world.util.WorldUtils;
import com.prupe.mcpatcher.cc.ColorizeBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class WillowVineBlock extends Block {
	public WillowVineBlock(int blockID) {
		super(blockID, Material.plants);
		this.setTickRandomly(true);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(0.2F);
		this.setAxesEffectiveOn(true);
		this.setBuoyant();
		this.setFireProperties(Flammability.VINES);
		this.initBlockBounds(0D, 0D, 0D, 1D, 1D, 1D);
		this.setStepSound(soundGrassFootstep);
		this.setUnlocalizedName("decoBlockWillowVine");
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		this.checkFlowerChange(world, x, y, z);
		
		if (world.rand.nextInt(10) == 0) {
			if (world.isAirBlock(x, y - 1, z)) {
				world.setBlockAndMetadataWithNotify(x, y - 1, z, this.blockID, 0);
			}
		}
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}
	
	@Override
	public AxisAlignedBB getBlockBoundsFromPoolBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		if (blockAccess.getBlockId(x, y - 1, z) != this.blockID) {
			return AxisAlignedBB.getAABBPool().getAABB(0, 0.375, 0, 1, 1, 1);
		}
		else {
			return this.getFixedBlockBoundsFromPool();
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		super.onNeighborBlockChange(world, x, y, z, neighborID);
		this.checkFlowerChange(world, x, y, z);
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		int blockIDAbove = world.getBlockId(x, y + 1, z);
		return blockIDAbove == this.blockID || WorldUtils.doesBlockHaveCenterHardpointToFacing(world, x, y + 1, z, 0, true);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return canBlockStay(world, x, y, z);
	}
	
	@Override
	public boolean isBlockClimbable(World world, int i, int j, int k) {
		return true;
	}
	
	//------------- Class Specific Methods ------------//
	
	protected final void checkFlowerChange(World world, int x, int y, int z) {
		if (!this.canBlockStay(world, x, y, z)) {
			world.setBlockToAir(x, y, z);
		}
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon midIcon;
	@Environment(EnvType.CLIENT)
	private Icon bottomIcon;
	
	@Override
	@Environment(EnvType.CLIENT)
	public boolean renderBlock(RenderBlocks renderer, int x, int y, int z) {
		return renderer.renderCrossedSquares(this, x, y, z);
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon(this.getUnlocalizedName2() + "_top");
		this.midIcon = register.registerIcon(this.getUnlocalizedName2() + "_mid");
		this.bottomIcon = register.registerIcon(this.getUnlocalizedName2() + "_bottom");
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
		if (blockAccess.getBlockId(x, y - 1, z) == this.blockID) {
			if (blockAccess.getBlockId(x, y - 2, z) == this.blockID) {
				return this.blockIcon;
			}
			else {
				return this.midIcon;
			}
		}
		else {
			return this.bottomIcon;
		}
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public int getRenderType() {
		return 1;
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public int getBlockColor() {
		return ColorizeBlock.colorizeBlock(this) ? ColorizeBlock.blockColor : ColorizerFoliage.getFoliageColorBasic();
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public int getRenderColor(int par1) {
		return ColorizeBlock.colorizeBlock(this, par1) ? ColorizeBlock.blockColor : ColorizerFoliage.getFoliageColorBasic();
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		return ColorizeBlock.colorizeBlock(this, par1IBlockAccess, par2, par3, par4) ? ColorizeBlock.blockColor :
				par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeFoliageColor();
	}
}
