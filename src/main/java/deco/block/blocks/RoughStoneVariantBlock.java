package deco.block.blocks;

import btw.block.blocks.RoughStoneBlock;
import btw.client.fx.BTWEffectManager;
import btw.item.BTWItems;
import btw.item.util.ItemUtils;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Explosion;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class RoughStoneVariantBlock extends RoughStoneBlock {
	private final int stoneType;
	public static RoughStoneBlock[] stoneTypeBlockArray = new RoughStoneBlock[StoneVariantsBlock.NUM_TYPES];
	
	public RoughStoneVariantBlock(int blockID, int strataLevel, int stoneType) {
		super(blockID, strataLevel);
		
		this.stoneType = stoneType;
		stoneTypeBlockArray[stoneType] = this;
		
		this.setUnlocalizedName("decoBlock" + StoneVariantsBlock.namesCapital[stoneType] + "Rough");
	}
	
	@Override
	public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (metadata < 15) {
			metadata++;
			
			if (!world.isRemote && isEffectiveItemConversionTool(stack, world, x, y, z)) {
				if (metadata <= 8) {
					if ((metadata & 1) == 0) {
						world.playAuxSFX(BTWEffectManager.STONE_RIPPED_OFF_EFFECT_ID, x, y, z, 0);
						ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z,
								new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.getStoneItemMetadataForDrop(this.stoneType)), side);
					}
					else if (metadata <= 5 && isUberItemConversionTool(stack, world, x, y, z)) {
						// iron or better chisel on top two strata ejects bricks instead
						// of loose stones
						metadata += 3;
						world.playAuxSFX(BTWEffectManager.STONE_RIPPED_OFF_EFFECT_ID, x, y, z, 0);
						ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z,
								new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.getStoneItemMetadataForDrop(this.stoneType)), side);
					}
				}
				else if (metadata == 12) {
					world.playAuxSFX(BTWEffectManager.GRAVEL_RIPPED_OFF_EFFECT_ID, x, y, z, 0);
					ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(BTWItems.gravelPile, 1), side);
				}
			}
			
			world.setBlockMetadataWithNotify(x, y, z, metadata);
			return true;
		}
		else {
			// final stage resulting in destruction
			if (!world.isRemote && isEffectiveItemConversionTool(stack, world, x, y, z)) {
				world.playAuxSFX(BTWEffectManager.GRAVEL_RIPPED_OFF_EFFECT_ID, x, y, z, 0);
				ItemUtils.dropStackAsIfBlockHarvested(world, x, y, z, new ItemStack(BTWItems.gravelPile, 1));
			}
			
			return false;
		}
	}
	
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chanceOfDrop, int fortuneModifier) {
		if (!world.isRemote) {
			int itemIDDropped = BTWItems.stone.itemID;
			int numDropped = 1;
			int metadataDropped = StoneVariantsBlock.getStoneItemMetadataForDrop(this.stoneType);
			
			if (metadata < 8) {
				numDropped = 8 - (metadata / 2);
			}
			else {
				itemIDDropped = BTWItems.gravelPile.itemID;
				metadataDropped = 0;
				
				if (metadata < 12) {
					numDropped = 2;
				}
			}
			
			for (int i = 0; i < numDropped; i++) {
				dropBlockAsItem_do(world, x, y, z, new ItemStack(itemIDDropped, 1, metadataDropped));
			}
		}
	}
	
	@Override
	public void onBlockDestroyedWithImproperTool(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		world.playAuxSFX(BTWEffectManager.GRAVEL_RIPPED_OFF_EFFECT_ID, x, y, z, 0);
		dropComponentItemsWithChance(world, x, y, z, metadata, 1F);
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
		int metadata = world.getBlockMetadata(x, y, z);
		float chanceOfPileDrop = 1.0F;
		
		if (explosion != null) {
			chanceOfPileDrop = 1.0F / explosion.explosionSize;
		}
		
		dropComponentItemsWithChance(world, x, y, z, metadata, chanceOfPileDrop);
	}
	
	//------------- Class Specific Methods ------------//
	
	protected void dropComponentItemsWithChance(World world, int x, int y, int z, int metadata, float chanceOfItemDrop) {
		if (metadata < 8) {
			int numStoneDropped = 4 - (metadata / 2);
			dropItemsIndividually(world, x, y, z, BTWItems.stone.itemID, numStoneDropped, StoneVariantsBlock.getStoneItemMetadataForDrop(this.stoneType),
					chanceOfItemDrop);
		}
		
		int numGravelDropped = 1;
		
		if (metadata < 12) {
			numGravelDropped = 2;
		}
		
		dropItemsIndividually(world, x, y, z, BTWItems.gravelPile.itemID, numGravelDropped, 0, chanceOfItemDrop);
	}
}
