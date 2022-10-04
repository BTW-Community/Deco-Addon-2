package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.BloodWoodLogBlock;
import btw.client.fx.BTWEffectManager;
import btw.item.BTWItems;
import deco.block.DecoBlockIDs;
import deco.block.util.WoodTypeHelper;
import net.minecraft.src.World;

public class DecoBloodLogBlock extends DecoLogBlock {
	public DecoBloodLogBlock(int blockID) {
		super(blockID,
				new int[] {WoodTypeHelper.BLOOD_WOOD_TYPE, WoodTypeHelper.BLOOD_WOOD_TYPE, WoodTypeHelper.BLOOD_WOOD_TYPE},
				new int[] {DecoBlockIDs.CHEWED_BLOOD_LOG_ID, DecoBlockIDs.CHEWED_BLOOD_LOG_ID, DecoBlockIDs.CHEWED_BLOOD_LOG_ID},
				new boolean[] {true, false, true},
				new String[] {"decoBlockStrippedBlood_top", "fcBlockBloodWood_side", "decoBlockStrippedBlood_side"},
				new String[] {"decoBlockStrippedBlood_side", "fcBlockBloodWood_side", "decoBlockStrippedBlood_side"});
		this.setUnlocalizedName("fcBlockBloodWood");
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int blockID, int metadata) {
		world.playAuxSFX(BTWEffectManager.GHAST_SCREAM_EFFECT_ID, x, y, z, 0);
		((BloodWoodLogBlock) BTWBlocks.bloodWoodLog).notifySurroundingBloodLeavesOfBlockRemoval(world, x, y, z);
		super.breakBlock(world, x, y, z, blockID, metadata);
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 4, 0, chanceOfDrop);
		dropItemsIndividually(world, x, y, z, BTWItems.soulDust.itemID, 1, 0, chanceOfDrop);
		
		if (this.isStripped(metadata)) {
			dropItemsIndividually(world, x, y, z, BTWItems.bark.itemID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE, chanceOfDrop);
		}
		
		return true;
	}
}
