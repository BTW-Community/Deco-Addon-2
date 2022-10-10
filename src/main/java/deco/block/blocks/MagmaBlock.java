package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.client.fx.BTWEffectManager;
import btw.client.render.util.RenderUtils;
import btw.world.util.BlockPos;
import deco.block.DecoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class MagmaBlock extends Block {
	public MagmaBlock(int blockID) {
		super(blockID, BTWBlocks.netherRockMaterial);
		this.setHardness(0.6F);
		this.setResistance(0.6666667F);
		this.setPicksEffectiveOn();
		this.setLightValue(.375F);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setUnlocalizedName("decoBlockMagma");
		this.setTickRandomly(true);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (this.hasWaterToSidesOrTop(world, x, y, z)) {
			world.setBlockWithNotify(x, y, z, DecoBlocks.basalt.blockID);
			world.playAuxSFX(BTWEffectManager.FIRE_FIZZ_EFFECT_ID, x, y, z, 0);
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		if (this.hasWaterToSidesOrTop(world, x, y, z)) {
			world.setBlockWithNotify(x, y, z, DecoBlocks.basalt.blockID);
			world.playAuxSFX(BTWEffectManager.FIRE_FIZZ_EFFECT_ID, x, y, z, 0);
		}
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		//Spreads quartz to nearby magma blocks in the nether
		if (!world.isRemote && world.provider.isHellWorld && rand.nextInt(4) == 0 && world.checkChunksExist(x - 1, y - 1, z - 1, x + 1, y + 1, z + 1)) {
			int randFacing = rand.nextInt(6);
			BlockPos pos = new BlockPos(x, y, z, randFacing);
			
			if (world.getBlockId(pos.x, pos.y, pos.z) == Block.oreNetherQuartz.blockID) {
				world.setBlockWithNotify(x, y, z, Block.oreNetherQuartz.blockID);
			}
		}
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon overlay;
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		super.registerIcons(register);
		overlay = register.registerIcon("decoOverlayMagma");
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void renderBlockSecondPass(RenderBlocks renderBlocks, int x, int y, int z, boolean firstPassResult) {
		RenderUtils.renderBlockFullBrightWithTexture(renderBlocks, renderBlocks.blockAccess, x, y, z, this.overlay);
	}
}
