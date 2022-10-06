package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.MouldingAndDecorativeBlock;
import btw.client.fx.BTWEffectManager;
import btw.item.BTWItems;
import net.minecraft.src.*;

public class DecoWoodMouldingAndDecorativeBlock extends MouldingAndDecorativeBlock {
	public DecoWoodMouldingAndDecorativeBlock(int blockID, String textureName, String columnSideTextureName, int matchingCornerBlockID, String name) {
		super(blockID, BTWBlocks.plankMaterial, textureName, columnSideTextureName, matchingCornerBlockID,
				Block.planks.blockHardness, Block.planks.blockResistance, Block.soundWoodFootstep, name);
	}
	
	@Override
	public int getHarvestToolLevel(IBlockAccess blockAccess, int x, int y, int z) {
		return 2; // iron or better
	}
	
	@Override
	public void onBlockDestroyedWithImproperTool(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		world.playAuxSFX(BTWEffectManager.WOOD_BLOCK_DESTROYED_EFFECT_ID, x, y, z, 0);
		int numDropped = getNumSawDustDroppedForType(metadata);
		dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, numDropped, 0, 1F);
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosion) {
		return false;
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
		float chanceOfPileDrop = 1.0F;
		
		if (explosion != null) {
			chanceOfPileDrop = 1.0F / explosion.explosionSize;
		}
		
		int numDropped = getNumSawDustDroppedForType(world.getBlockMetadata(x, y, z));
		dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, numDropped, 0, chanceOfPileDrop);
	}
	
	//------------- Class Specific Methods ------------//
	
	public int getNumSawDustDroppedForType(int metadata) {
		if (isDecorative(metadata)) {
			return 2;
		}
		else {
			return 1;
		}
	}
}
