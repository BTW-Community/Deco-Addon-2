package deco.block.blocks;

import btw.block.blocks.LavaReceiverBlock;
import btw.client.fx.BTWEffectManager;
import deco.block.DecoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class LooseCobblestoneVariantsBlock extends LavaReceiverBlock {
	protected int stoneType;
	protected String lavaOverlayTexture;
	
	public LooseCobblestoneVariantsBlock(int blockID, int stoneType, String lavaOverlayTexture) {
		super(blockID, Material.rock);
		setHardness(1F);
		setResistance(5F);
		setPicksEffectiveOn();
		setChiselsEffectiveOn();
		setStepSound(Block.soundStoneFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
		
		this.stoneType = stoneType;
		this.lavaOverlayTexture = lavaOverlayTexture;
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!checkForFall(world, x, y, z)) {
			if (getHasLavaInCracks(world, x, y, z)) {
				if (hasWaterAbove(world, x, y, z)) {
					world.playAuxSFX(BTWEffectManager.FIRE_FIZZ_EFFECT_ID, x, y, z, 0);
					this.setToStone(world, x, y, z);
				}
			}
			else if (hasLavaAbove(world, x, y, z)) {
				setHasLavaInCracks(world, x, y, z, true);
			}
		}
	}
	
	@Override
	public void randomUpdateTick(World world, int x, int y, int z, Random rand) {
		if (getHasLavaInCracks(world, x, y, z)) {
			if (world.isRainingAtPos(x, y + 1, z)) {
				world.playAuxSFX(BTWEffectManager.FIRE_FIZZ_EFFECT_ID, x, y, z, 0);
				this.setToStone(world, x, y, z);
			}
		}
	}
	
	@Override
	public boolean onMortarApplied(World world, int i, int j, int k) {
		world.setBlockAndMetadataWithNotify(i, j, k, DecoBlocks.cobblestoneVariants.blockID, this.stoneType);
		return true;
	}
	
	//------------- Class Specific Methods ------------//
	
	public void setToStone(World world, int x, int y, int z) {
		world.setBlockAndMetadataWithNotify(x, y, z, DecoBlocks.stoneVariants.blockID, this.stoneType);
	}
	
	// ------------ Client Side Functionality ----------//
	
	@Environment(EnvType.CLIENT)
	private Icon lavaOverlayIcon;
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.lavaOverlayIcon = register.registerIcon(lavaOverlayTexture);
		
		super.registerIcons(register);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	protected Icon getLavaCracksOverlay() {
		return lavaOverlayIcon;
	}
}
