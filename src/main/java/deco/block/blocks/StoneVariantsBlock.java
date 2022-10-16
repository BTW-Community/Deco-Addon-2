package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.RoughStoneBlock;
import btw.block.blocks.StoneBlock;
import btw.client.fx.BTWEffectManager;
import btw.item.BTWItems;
import btw.item.util.ItemUtils;
import deco.block.DecoBlockIDs;
import deco.block.DecoBlocks;
import deco.block.mixins.StoneBlockInvoker;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StoneVariantsBlock extends StoneBlock {
	public static final int NUM_VANILLA_TYPES = 3;
	public static final int NUM_TYPES = 6;
	
	public static final int GRANITE_TYPE = 0;
	public static final int ANDESITE_TYPE = 1;
	public static final int DIORITE_TYPE = 2;
	public static final int SLATE_TYPE = 3; // Unused in base stone
	public static final int CALCITE_TYPE = 4;
	public static final int TUFF_TYPE = 5;
	public static final int JASPER_TYPE = 6;
	public static final int LIMESTONE_TYPE = 7;
	
	public static final int GRANITE_STRATA = 0;
	public static final int ANDESITE_STRATA = 0;
	public static final int DIORITE_STRATA = 0;
	public static final int SLATE_STRATA = 2;
	public static final int CALCITE_STRATA = 0;
	public static final int TUFF_STRATA = 1;
	public static final int JASPER_STRATA = 1;
	public static final int LIMESTONE_STRATA = 0;
	
	public static Map<Integer, Integer> strataByType = new HashMap<>();
	
	static {
		strataByType.put(GRANITE_TYPE, GRANITE_STRATA);
		strataByType.put(ANDESITE_TYPE, ANDESITE_STRATA);
		strataByType.put(DIORITE_TYPE, DIORITE_STRATA);
		strataByType.put(SLATE_TYPE, SLATE_STRATA);
		strataByType.put(CALCITE_TYPE, CALCITE_STRATA);
		strataByType.put(TUFF_TYPE, TUFF_STRATA);
		strataByType.put(JASPER_TYPE, JASPER_STRATA);
		strataByType.put(LIMESTONE_TYPE, LIMESTONE_STRATA);
	}
	
	public static final String[] names = new String[]{"granite", "andesite", "diorite", "slate", "calcite", "tuff"};
	
	public static final String[] namesCapital = new String[]{"Granite", "Andesite", "Diorite", "Slate", "Calcite", "Tuff"};
	
	protected boolean isCracked;
	
	public StoneVariantsBlock(int blockID, boolean isCracked) {
		super(blockID);
		this.setUnlocalizedName("decoBlockStone");
		this.isCracked = isCracked;
	}
	
	@Override
	public int idDropped(int metadata, Random random, int fortuneModifier) {
		switch (metadata) {
			case GRANITE_TYPE:
				return DecoBlocks.looseGraniteCobblestone.blockID;
			case ANDESITE_TYPE:
				return DecoBlocks.looseAndesiteCobblestone.blockID;
			case DIORITE_TYPE:
				return DecoBlocks.looseDioriteCobblestone.blockID;
		}
		
		return BTWBlocks.looseCobblestone.blockID;
	}
	
	@Override
	public int getBlockIDOnInfest(EntityLiving entity, int metadata) {
		switch (metadata) {
			case GRANITE_TYPE:
				return DecoBlocks.infestedGranite.blockID;
			case ANDESITE_TYPE:
				return DecoBlocks.infestedAndesite.blockID;
			case DIORITE_TYPE:
				return DecoBlocks.infestedDiorite.blockID;
		}
		
		return Block.stone.getBlockIDOnInfest(entity, 0);
	}
	
	
	@Override
	public int damageDropped(int metadata) {
		return 0;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chacneOfDrop, int fortuneModifier) {
		super.dropBlockAsItemWithChance(world, x, y, z, metadata, chacneOfDrop, fortuneModifier);
		
		if (!world.isRemote) {
			dropBlockAsItem_do(world, x, y, z, new ItemStack(BTWItems.stone, 1, getStoneItemMetadataForDrop(this.blockID, metadata)));
			
			if (!getIsCracked(metadata)) {
				dropBlockAsItem_do(world, x, y, z, new ItemStack(BTWItems.gravelPile));
			}
		}
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		dropItemsIndividually(world, x, y, z, BTWItems.stone.itemID, 5, getStoneItemMetadataForDrop(this.blockID, metadata), chanceOfDrop);
		int numGravel = getIsCracked(metadata) ? 2 : 3;
		dropItemsIndividually(world, x, y, z, BTWItems.gravelPile.itemID, numGravel, 0, chanceOfDrop);
		
		return true;
	}
	
	@Override
	public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
		int metadata = world.getBlockMetadata(x, y, z);
		
		int toolLevel = ((StoneBlockInvoker) this).getConversionLevelForToolAccessor(stack, world, x, y, z);
		
		if (getIsCracked(metadata)) {
			world.setBlockAndMetadataWithNotify(x, y, z, RoughStoneVariantBlock.stoneTypeBlockArray[getType(this.blockID, metadata)].blockID, 0);
			
			if (!world.isRemote && toolLevel > 0) {
				world.playAuxSFX(BTWEffectManager.STONE_RIPPED_OFF_EFFECT_ID, x, y, z, 0);
				ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(BTWItems.stone, 1, getStoneItemMetadataForDrop(this.blockID, metadata)), side);
			}
		}
		else {
			if (toolLevel == 2) {
				// level 2 is stone pick on top strata stone, which has its own thing going on
				world.setBlockAndMetadataWithNotify(x, y, z, RoughStoneVariantBlock.stoneTypeBlockArray[getType(this.blockID, metadata)].blockID, 4);
				
				if (!world.isRemote) {
					world.playAuxSFX(BTWEffectManager.STONE_RIPPED_OFF_EFFECT_ID, x, y, z, 0);
					ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(BTWItems.stone, 3, getStoneItemMetadataForDrop(this.blockID, metadata)), side);
					ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(BTWItems.gravelPile, 1), side);
				}
				
			}
			else if (toolLevel == 3) {
				// level 3 is iron chisel on first two strata, resulting in stone brick
				world.setBlockAndMetadataWithNotify(x, y, z, RoughStoneVariantBlock.stoneTypeBlockArray[getType(this.blockID, metadata)].blockID, 2);
				
				if (!world.isRemote) {
					world.playAuxSFX(BTWEffectManager.STONE_RIPPED_OFF_EFFECT_ID, x, y, z, 0);
					ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(BTWItems.stoneBrick, 1, getStoneItemMetadataForDrop(this.blockID, metadata)), side);
					ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(BTWItems.gravelPile, 1), side);
				}
			}
			else {
				if (!world.isRemote) {
					world.playAuxSFX(BTWEffectManager.GRAVEL_RIPPED_OFF_EFFECT_ID, x, y, z, 0);
					ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(BTWItems.gravelPile, 1), side);
				}
				
				this.setIsCracked(world, x, y, z, true);
			}
		}
		
		return true;
	}
	
	@Override
	public int getStrata(int metadata) {
		return strataByType.get(this.getType(metadata));
	}
	
	@Override
	public void setIsCracked(World world, int x, int y, int z, boolean setCracked) {
		if (this.isCracked != setCracked) {
			if (setCracked) {
				world.setBlockAndMetadata(x, y, z, DecoBlockIDs.CRACKED_STONE_VARIANTS_ID, world.getBlockMetadata(x, y, z));
			}
			else {
				if (this.getType(world.getBlockMetadata(x, y, z)) == SLATE_TYPE) {
					((StoneVariantsBlock) DecoBlocks.slate).setIsCracked(world, x, y, z, false);
				}
				else {
					world.setBlockAndMetadata(x, y, z, DecoBlockIDs.STONE_VARIANTS_ID, world.getBlockMetadata(x, y, z));
				}
			}
		}
	}
	
	@Override
	public boolean getIsCracked(int metadata) {
		return isCracked;
	}
	
	//------------- Class Specific Methods ------------//
	
	public static int getType(int blockID, int metadata) {
		return ((StoneVariantsBlock) Block.blocksList[blockID]).getType(metadata);
	}
	
	public int getType(int metadata) {
		return metadata & 7;
	}
	
	public static int getStoneItemMetadataForDrop(int blockID, int metadata) {
		return getType(blockID, metadata) + NUM_VANILLA_TYPES;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] iconArray;
	
	@Override
	@Environment(EnvType.CLIENT)
	public Icon getIcon(int side, int metadata) {
		return this.iconArray[metadata];
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		if (!this.isCracked) {
			for (int i = 0; i < NUM_TYPES; i++) {
				if (i == SLATE_TYPE) {
					continue;
				}
				
				list.add(new ItemStack(blockID, 1, i));
			}
		}
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister register) {
		iconArray = new Icon[NUM_TYPES];
		
		String baseTexture = "decoBlock";
		
		for (int i = 0; i < iconArray.length; i++) {
			if (i == SLATE_TYPE && !this.isCracked) {
				continue;
			}
			
			if (this.isCracked) {
				iconArray[i] = register.registerIcon(baseTexture + namesCapital[i] + "Cracked");
			}
			else {
				iconArray[i] = register.registerIcon(baseTexture + namesCapital[i]);
			}
		}
	}
}
