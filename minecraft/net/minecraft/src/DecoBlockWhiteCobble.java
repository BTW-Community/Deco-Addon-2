package net.minecraft.src;

import java.util.Random;

public class DecoBlockWhiteCobble extends Block {
	public static final int lavaFillTickRate = 20;
	public static final int lavaHardenTickRate = 2;

	public DecoBlockWhiteCobble(int id) {
		super(id, Material.rock);

		setHardness(2F);
		SetAxesEffectiveOn(true);
		SetPicksEffectiveOn(true);

		setStepSound(soundStoneFootstep);

		setCreativeTab(CreativeTabs.tabBlock);

		setUnlocalizedName("decoBlockWhiteCobble");
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (!scheduleUpdatesForLavaAndWaterContact(world, x, y, z)) {
			super.onBlockAdded(world, x, y, z);
		}    	
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (hasLavaInCracks(world, x, y, z)) {
			if (hasWaterAbove(world, x, y, z)) {
				world.playAuxSFX(FCBetterThanWolves.m_iFireFizzSoundAuxFXID, x, y, z, 0);
				world.setBlockAndMetadataWithNotify(x, y, z, FCBetterThanWolves.fcAestheticOpaque.blockID, FCBlockAestheticOpaque.m_iSubtypeWhiteStone);

				return;
			}
		}
		else if (hasLavaAbove(world, x, y, z)) {
			setHasLavaInCracks(world, x, y, z, true);
		}
	}

	@Override
	public void RandomUpdateTick(World world, int x, int y, int z, Random rand) {
		if (hasLavaInCracks(world, x, y, z)) {
			if (world.IsRainingAtPos(x, y + 1, z)) {
				world.playAuxSFX(FCBetterThanWolves.m_iFireFizzSoundAuxFXID, x, y, z, 0);
				world.setBlockAndMetadataWithNotify(x, y, z, FCBetterThanWolves.fcAestheticOpaque.blockID, FCBlockAestheticOpaque.m_iSubtypeWhiteStone);
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockID) {
		if (!scheduleUpdatesForLavaAndWaterContact(world, x, y, z)) {
			super.onNeighborBlockChange(world, x, y, z, neighborBlockID);
		}
	}

	@Override
	protected boolean canSilkHarvest() {
		// prevent havest of version with lava in cracks
		return false;
	}

	@Override
	public boolean GetIsBlockWarm(IBlockAccess blockAccess, int x, int y, int z) {
		return hasLavaInCracks(blockAccess, x, y, z);
	}

	//------------- Class Specific Methods ------------//

	protected boolean getHasLavaInCracks(int metadata) {
		return (metadata & 1) != 0;
	}

	protected boolean hasLavaInCracks(IBlockAccess blockAccess, int x, int y, int z) {
		return getHasLavaInCracks(blockAccess.getBlockMetadata(x, y, z));
	}

	protected int setHasLavaInCracks(int metadata, boolean hasLava) {
		if (hasLava) {
			metadata |= 1;
		}
		else {
			metadata &= (~1);
		}

		return metadata;
	}

	protected void setHasLavaInCracks(World world, int x, int y, int z, boolean hasLava) {
		int metadata = setHasLavaInCracks(world.getBlockMetadata(x, y, z), hasLava);
		world.setBlockMetadataWithNotify(x, y, z, metadata);
	}    	

	protected boolean hasLavaAbove(IBlockAccess blockAccess, int x, int y, int z) {
		Block blockAbove = Block.blocksList[blockAccess.getBlockId(x, y + 1, z)];
		return blockAbove != null && blockAbove.blockMaterial == Material.lava;
	}

	protected boolean hasWaterAbove(IBlockAccess blockAccess, int x, int y, int z) {
		Block blockAbove = Block.blocksList[blockAccess.getBlockId(x, y + 1, z)];
		return blockAbove != null && blockAbove.blockMaterial == Material.water;
	}

	protected boolean scheduleUpdatesForLavaAndWaterContact(World world, int x, int y, int z) {
		if (hasLavaInCracks(world, x, y, z)) {
			if (hasWaterAbove(world, x, y, z)) {
				if (!world.IsUpdatePendingThisTickForBlock(x, y, z, blockID)) {
					world.scheduleBlockUpdate(x, y, z, blockID, lavaHardenTickRate);
				}

				return true;
			}
		}
		else if (hasLavaAbove(world, x, y, z)) {
			if (!world.IsUpdatePendingThisTickForBlock(x, y, z, blockID)) {
				world.scheduleBlockUpdate(x, y, z, blockID, lavaFillTickRate);
			}

			return true;
		}

		return false;
	}

	//------------ Client Side Functionality ----------//

	private Icon lavaOverlay;

	@Override
	public void registerIcons(IconRegister register) {
		super.registerIcons(register);
		this.lavaOverlay = register.registerIcon("decoOverlayWhiteCobbleLava");
	}

	protected Icon getLavaCracksOverlay() {
		return this.lavaOverlay;
	}

	@Override
	public void RenderBlockSecondPass(RenderBlocks renderBlocks, int x, int y, int z, boolean firstPassResult) {
		if (firstPassResult && hasLavaInCracks(renderBlocks.blockAccess, x, y, z)) {
			FCClientUtilsRender.RenderBlockFullBrightWithTexture(renderBlocks, renderBlocks.blockAccess, x, y, z, getLavaCracksOverlay());
		}
	}
}