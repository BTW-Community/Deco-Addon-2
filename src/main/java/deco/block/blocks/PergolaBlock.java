package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.model.BlockModel;
import btw.block.util.Flammability;
import btw.item.BTWItems;
import btw.util.MiscUtils;
import deco.block.model.PergolaBlockModel;
import net.minecraft.src.*;

public class PergolaBlock extends Block {
	private final BlockModel blockModel = new PergolaBlockModel();
	
	public PergolaBlock(int blockID) {
		super(blockID, BTWBlocks.plankMaterial);
		
		this.setHardness(1.0F);
		this.setResistance(5.0F);
		this.setFireProperties(Flammability.PLANKS);
		this.setAxesEffectiveOn();
		this.setBuoyant();
		this.setStepSound(soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.initBlockBounds(this.getFixedBlockBoundsFromPool());
		this.setUnlocalizedName("decoBlockPergola");
		this.initBlockBounds(0, 0, 0, 1, 0.5, 1);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean canGroundCoverRestOnBlock(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public float groundCoverRestingOnVisualOffset(IBlockAccess blockAccess, int x, int y, int z) {
		return -1.0F;
	}
	
	@Override
	public AxisAlignedBB getFixedBlockBoundsFromPool() {
		return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 1, PergolaBlockModel.BLOCK_HEIGHT, 1);
	}
	
	@Override
	public int getHarvestToolLevel(IBlockAccess blockAccess, int x, int y, int z) {
		return 2;
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 2, 0, chanceOfDrop);
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack) {
		int facing = MiscUtils.convertOrientationToFlatBlockFacingReversed(entity);
		this.setFacing(world, x, y, z, facing);
	}
	
	@Override
	public int getFacing(int metadata) {
		return metadata;
	}
	
	@Override
	public int setFacing(int metadata, int facing) {
		return facing;
	}
	
	//----------- Client Side Functionality -----------//
	
	private Icon icon_top;
	private Icon icon_bottom;
	private Icon icon_sides_0;
	private Icon icon_sides_1;
	
	@Override
	public Icon getIcon(int side, int metadata) {
		int facing = this.getFacing(metadata);
		
		if ((side == 0 && facing <= 3) || (side == 1 && facing >= 4)) {
			return icon_bottom;
		}
		else if ((side == 1 && facing <= 3) || (side == 0 && facing >= 4)) {
			return icon_top;
		}
		else if (((side == 2 || side == 3) && facing <= 3) || ((side == 4 || side == 5) && facing >= 4)) {
			return icon_sides_0;
		}
		else {
			return icon_sides_1;
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		icon_top = register.registerIcon("decoBlockPergola_top");
		icon_bottom = register.registerIcon("decoBlockPergola_bottom");
		icon_sides_0 = register.registerIcon("decoBlockPergola_sides_0");
		icon_sides_1 = register.registerIcon("decoBlockPergola_sides_1");
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return true;
	}
	
	@Override
	public boolean renderBlock(RenderBlocks renderBlocks, int x, int y, int z) {
		int facing = this.getFacing(renderBlocks.blockAccess, x, y, z);
		BlockModel model = this.blockModel.makeTemporaryCopy();
		model.rotateAroundYToFacing(facing);
		return model.renderAsBlock(renderBlocks, this, x, y, z);
	}
	
	@Override
	public void renderBlockAsItem(RenderBlocks renderBlocks, int metadata, float var3) {
		this.blockModel.renderAsItemBlock(renderBlocks, this, metadata);
	}
}
