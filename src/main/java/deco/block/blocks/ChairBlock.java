package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import btw.client.render.util.RenderUtils;
import btw.item.BTWItems;
import btw.util.MiscUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class ChairBlock extends Block {
	public ChairBlock(int id, String tag) {
		super(id, BTWBlocks.plankMaterial);
		this.setUnlocalizedName("decoBlockChair" + tag);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.initBlockBounds(.0625F, 0, .0625F, 0.9375F, .625F, 0.9375F);
		this.setAxesEffectiveOn(true);
		this.setHardness(1.0F);
		this.setResistance(5.0F);
		this.setFireProperties(Flammability.PLANKS);
		this.setBuoyant();
		this.setStepSound(soundWoodFootstep);
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
	public boolean canGroundCoverRestOnBlock(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public float groundCoverRestingOnVisualOffset(IBlockAccess blockAccess, int x, int y, int z) {
		return -1.0F;
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
		return true;
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
	public boolean rotateAroundJAxis(World world, int x, int y, int z, boolean isReversed) {
		return MiscUtils.standardRotateAroundY(this, world, x, y, z, isReversed);
	}
	
	@Override
	public int rotateMetadataAroundJAxis(int metadata, boolean isReversed) {
		return MiscUtils.standardRotateMetadataAroundY(this, metadata, isReversed);
	}
	
	@Override
	public boolean toggleFacing(World world, int x, int y, int z, boolean var5) {
		this.rotateAroundJAxis(world, x, y, z, var5);
		return true;
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
	public int onBlockPlaced(World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ, int metadata) {
		return setFacing(metadata, facing);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack) {
		int facing = MiscUtils.convertOrientationToFlatBlockFacingReversed(entity);
		this.setFacing(world, x, y, z, facing);
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return true;
	}
	
	@Environment(EnvType.CLIENT)
	protected void setRenderBoundsRotatedAboutJToFacing(RenderBlocks renderBlocks, float x, float y, float z, float var5, float var6, float var7, int var8) {
		float var9;
		float var10;
		float var11;
		float var12;
		if (var8 == 4) {
			var9 = 1.0F - var5;
			var10 = 1.0F - var7;
			var11 = 1.0F - x;
			var12 = 1.0F - z;
		}
		else if (var8 == 3) {
			var9 = z;
			var10 = x;
			var11 = var7;
			var12 = var5;
		}
		else if (var8 == 2) {
			var9 = 1.0F - var7;
			var10 = 1.0F - var5;
			var11 = 1.0F - z;
			var12 = 1.0F - x;
		}
		else {
			var9 = x;
			var10 = z;
			var11 = var5;
			var12 = var7;
		}
		renderBlocks.setRenderBounds((double) var9, (double) y, (double) var10, (double) var11, (double) var6, (double) var12);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean renderBlock(RenderBlocks renderBlocks, int x, int y, int z) {
		int facing = getFacing(renderBlocks.blockAccess, x, y, z);
		setRenderBoundsRotatedAboutJToFacing(renderBlocks, .0625F, 0F, .0625F, .1875F, 0.5F, .1875F, facing);
		renderBlocks.renderStandardBlock(this, x, y, z);
		setRenderBoundsRotatedAboutJToFacing(renderBlocks, .8125F, 0F, .0625F, .9375F, 0.5F, .1875F, facing);
		renderBlocks.renderStandardBlock(this, x, y, z);
		setRenderBoundsRotatedAboutJToFacing(renderBlocks, .0625F, 0F, .8125F, .1875F, 0.5F, .9375F, facing);
		renderBlocks.renderStandardBlock(this, x, y, z);
		setRenderBoundsRotatedAboutJToFacing(renderBlocks, .8125F, 0F, .8125F, .9375F, 0.5F, .9375F, facing);
		renderBlocks.renderStandardBlock(this, x, y, z);
		setRenderBoundsRotatedAboutJToFacing(renderBlocks, .0625F, .5F, .0625F, .9375F, .625F, .9375F, facing);
		renderBlocks.renderStandardBlock(this, x, y, z);
		setRenderBoundsRotatedAboutJToFacing(renderBlocks, .0625F, .625F, .0625F, .1875F, 1.25F, .9375F, facing);
		renderBlocks.renderStandardBlock(this, x, y, z);
		return true;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void renderBlockAsItem(RenderBlocks renderBlocks, int var2, float var3) {
		renderBlocks.setRenderBounds(.0625D, 0D, .0625D, .1875D, 0.5D, .1875D);
		RenderUtils.renderInvBlockWithTexture(renderBlocks, this, -0.5F, -0.5F, -0.5F, blockIcon);
		renderBlocks.setRenderBounds(.8125D, 0D, .0625D, .9375D, 0.5D, .1875D);
		RenderUtils.renderInvBlockWithTexture(renderBlocks, this, -0.5F, -0.5F, -0.5F, blockIcon);
		renderBlocks.setRenderBounds(.0625D, 0D, .8125D, .1875D, 0.5D, .9375D);
		RenderUtils.renderInvBlockWithTexture(renderBlocks, this, -0.5F, -0.5F, -0.5F, blockIcon);
		renderBlocks.setRenderBounds(.8125D, 0D, .8125D, .9375D, 0.5D, .9375D);
		RenderUtils.renderInvBlockWithTexture(renderBlocks, this, -0.5F, -0.5F, -0.5F, blockIcon);
		renderBlocks.setRenderBounds(.0625D, .5D, .0625D, .9375D, .625D, .9375D);
		RenderUtils.renderInvBlockWithTexture(renderBlocks, this, -0.5F, -0.5F, -0.5F, blockIcon);
		renderBlocks.setRenderBounds(.0625D, .625D, .0625D, .1875D, 1.25D, .9375D);
		RenderUtils.renderInvBlockWithTexture(renderBlocks, this, -0.5F, -0.5F, -0.5F, blockIcon);
	}
}
