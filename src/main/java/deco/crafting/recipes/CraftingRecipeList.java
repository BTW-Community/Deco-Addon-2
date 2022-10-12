package deco.crafting.recipes;

import btw.block.BTWBlocks;
import btw.block.blocks.AestheticOpaqueBlock;
import btw.block.blocks.MouldingAndDecorativeBlock;
import btw.block.blocks.SidingAndCornerAndDecorativeBlock;
import btw.crafting.manager.CrucibleCraftingManager;
import btw.crafting.manager.CrucibleStokedCraftingManager;
import btw.crafting.recipe.RecipeManager;
import btw.inventory.util.InventoryUtils;
import btw.item.BTWItems;
import btw.item.blockitems.WoodMouldingDecorativeStubBlockItem;
import btw.item.blockitems.WoodSidingDecorativeStubBlockItem;
import btw.util.ColorUtils;
import deco.block.DecoBlocks;
import deco.block.blocks.*;
import deco.block.util.SandHelper;
import deco.block.util.SlabHelper;
import deco.block.util.StoneHelper;
import deco.block.util.WoodTypeHelper;
import deco.item.DecoItems;
import net.minecraft.src.Block;
import net.minecraft.src.BlockCloth;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class CraftingRecipeList {
	public static void initRecipes() {
		initGeneralRecipes();
		initStoneRecipes();
		initWhiteStoneRecipes();
		initPrismarineRecipes();
		initSandstoneRecipes();
		initNetherRecipes();
		initEndRecipes();
		initTerracottaRecipes();
		initConcreteRecipes();
		initSoilRecipes();
		initPlantRecipes();
		initWoodTypeRecipes();
	}
	
	private static void initGeneralRecipes() {
		RecipeManager.addShapelessRecipe(new ItemStack(DecoItems.plainBook),
				new ItemStack[] {
						new ItemStack(Item.paper)
				});
		
		RecipeManager.addSoulforgeRecipe(new ItemStack(BTWItems.wickerPane, 2),
				new Object[] {
						"X X ",
						" X X",
						"X X ",
						" X X",
						'X', Item.reed
				});
		
		RecipeManager.removeVanillaRecipe(new ItemStack(Item.painting, 1),
				new Object[] {
						"###",
						"#X#",
						"###",
						'#', Item.stick,
						'X', Block.cloth
				});
		RecipeManager.addRecipe(new ItemStack(Item.painting, 1),
				new Object[] {
						"###",
						"#X#",
						"###",
						'#', Item.stick,
						'X', new ItemStack(BTWItems.wool, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addCauldronRecipe(new ItemStack(DecoItems.fertilizer, 2),
				new ItemStack[] {
						new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID),
						new ItemStack(BTWItems.potash)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.bonePillar),
				new Object[] {
						"B",
						"B",
						'B', new ItemStack(BTWBlocks.aestheticOpaque, 1, AestheticOpaqueBlock.SUBTYPE_BONE)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(Item.bone, 9),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bonePillar)
				});
		
		RecipeManager.removeVanillaRecipe(new ItemStack(BTWBlocks.aestheticOpaque, 1, AestheticOpaqueBlock.SUBTYPE_ROPE),
				new Object[] {
						"RRR",
						"RRR",
						"RRR",
						'R', BTWItems.rope
				});
		addStorage(new ItemStack(DecoBlocks.ropeCoil), new ItemStack(BTWItems.rope));
		
		RecipeManager.addRecipe(new ItemStack(DecoItems.chain),
				new Object[] {
						"N",
						"N",
						"N",
						'N', BTWItems.ironNugget
				});
		addStorage(new ItemStack(DecoBlocks.chainCoil), new ItemStack(DecoItems.chain));
		
		RecipeManager.addSoulforgeRecipe(new ItemStack(DecoBlocks.wroughtIronBars, 20),
				new Object[] {
						"i i ",
						"iiii",
						"i i ",
						"i i ",
						'i', Item.ingotIron
				});
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(BTWItems.ironNugget, 3),
				new ItemStack[] {
						new ItemStack(DecoBlocks.wroughtIronBars)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.paperWall, 8),
				new Object[] {
						"ppp",
						"pmp",
						"ppp",
						'p', Item.paper,
						'm', new ItemStack(BTWItems.woodMouldingStubID, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.ironTrapdoor, 2),
				new Object[] {
						"LII",
						"LII",
						'L', BTWItems.redstoneLatch,
						'I', Item.ingotIron
				});
		RecipeManager.addStokedCrucibleRecipe(
				new ItemStack[] {
						new ItemStack(BTWItems.ironNugget, 12),
						new ItemStack(Item.goldNugget, 2)
				},
				new ItemStack[] {
						new ItemStack(DecoBlocks.ironTrapdoor)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.ironLadder, 7),
				new Object[] {
						"# #",
						"###",
						"# #",
						'#', new ItemStack(Item.ingotIron)
				});
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(BTWItems.ironNugget, 6),
				new ItemStack[] {
						new ItemStack(DecoBlocks.ironLadder)
				});
		
		//------ Hay Bales ------//
		
		RecipeManager.addPistonPackingRecipe(DecoBlocks.hayBale,
				new ItemStack(BTWItems.wheat, 8));
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.wheat, 8),
				new ItemStack[] {
						new ItemStack(DecoBlocks.hayBale)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.hayStairs, 8),
				new Object[] {
						"H  ",
						"HH ",
						"HHH",
						'H', DecoBlocks.hayBale
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.hayStairs, 4),
				new Object[] {
						"H ",
						"HH",
						'H', DecoBlocks.hayBale
				});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.wheat, 6),
				new ItemStack[] {
						new ItemStack(DecoBlocks.hayStairs)
				});
		
		RecipeManager.addPistonPackingRecipe(DecoBlocks.thatch,
				new ItemStack(BTWItems.straw, 8));
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.straw, 8),
				new ItemStack[] {
						new ItemStack(DecoBlocks.thatch)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.thatchStairs, 8),
				new Object[] {
						"H  ",
						"HH ",
						"HHH",
						'H', DecoBlocks.thatch
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.thatchStairs, 4),
				new Object[] {
						"H ",
						"HH",
						'H', DecoBlocks.thatch
				});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.straw, 6),
				new ItemStack[] {
						new ItemStack(DecoBlocks.thatchStairs)
				});
		
		//------ Lanterns ------//
		
		RecipeManager.addRecipe(new ItemStack(DecoItems.paperLantern),
				new Object[] {
						"pwp",
						"wcw",
						"pwp",
						'p', Item.paper,
						'w', new ItemStack(BTWItems.woodMouldingStubID, 1, InventoryUtils.IGNORE_METADATA),
						'c', new ItemStack(BTWItems.candle, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoItems.paperLantern),
				new ItemStack[] {
						new ItemStack(DecoBlocks.paperLantern)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoItems.brokenPaperLantern),
				new Object[] {
						"pwp",
						"w w",
						"pwp",
						'p', Item.paper,
						'w', new ItemStack(BTWItems.woodMouldingStubID, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoItems.brokenPaperLantern),
				new ItemStack[] {
						new ItemStack(DecoBlocks.brokenPaperLantern)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoItems.lantern),
				new Object[] {
						"NNN",
						"NTN",
						"NNN",
						'N', BTWItems.ironNugget,
						'T', BTWBlocks.infiniteBurningTorch
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoItems.lantern),
				new ItemStack[] {
						new ItemStack(DecoBlocks.lantern)
				});
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(BTWItems.ironNugget, 5),
				new ItemStack[] {
						new ItemStack(DecoBlocks.lantern)
				});
		
		RecipeManager.addSoulforgeRecipe(new ItemStack(DecoBlocks.chandelier,2),
				new Object[]{
						" ss ",
						" gg ",
						"cggc",
						"cggc",
						's',Block.stone,
						'g',Item.goldNugget,
						'c', new ItemStack(BTWItems.candle, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		//------ Glass ------//
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(Block.glass, 1), new ItemStack[] {
				new ItemStack(DecoItems.glassShard, 4)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoItems.glassShard, 4), new Object[] {
				new ItemStack(Block.glass, 1)});
		
		RecipeManager.removeVanillaRecipe(new ItemStack(Item.glassBottle, 3), new Object[] {
				"# #",
				" # ",
				'#', Block.glass});
		
		RecipeManager.addRecipe(new ItemStack(Item.glassBottle, 6), new Object[] {
				" # ",
				"# #",
				"###",
				'#', DecoItems.glassShard});
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoItems.glassShard, 1), new ItemStack[] {
				new ItemStack(Item.glassBottle, 1)});
		
		for (int color = 0; color < 16; color++) {
			RecipeManager.addCauldronRecipe(new ItemStack(DecoItems.stainedGlass, 8, color), new ItemStack[] {
					new ItemStack(Block.glass, 8),
					new ItemStack(Item.dyePowder, 1, color)});
			RecipeManager.addCauldronRecipe(new ItemStack(DecoItems.stainedGlass, 8, color), new ItemStack[] {
					new ItemStack(Block.glass, 8),
					new ItemStack(Item.dyePowder, 1, color + 16)});
		}
		
		RecipeManager.addCauldronRecipe(new ItemStack(DecoItems.stainedGlass, 8, ColorUtils.BROWN.colorID), new ItemStack[] {
				new ItemStack(Block.glass, 8),
				new ItemStack(BTWItems.dung)});
		
		CrucibleStokedCraftingManager.getInstance().removeRecipe(new ItemStack(Block.glass, 4), new ItemStack[] {
				new ItemStack(Block.sand, 8),
				new ItemStack(Item.netherQuartz)});
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(Block.glass, 16), new ItemStack[] {
				new ItemStack(Block.sand, 16),
				new ItemStack(Item.netherQuartz)});
		
		for (int color = 0; color < WoodTypeHelper.NUM_TOTAL_WOOD; color++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.woodFramedGlass, 4, color), new Object[] {
					"WGW",
					"GWG",
					"WGW",
					'W', new ItemStack(BTWItems.woodMouldingStubID, 1, color),
					'G', DecoItems.glassShard});
		}
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.ironFramedGlass, 4), new Object[] {
				"IGI",
				"GIG",
				"IGI",
				'I', BTWItems.ironNugget,
				'G', DecoItems.glassShard});
		
		//------ Carpets ------//
		
		addStorage(new ItemStack(DecoBlocks.coalBlock), new ItemStack(Item.coal, 1, 0));
		addStorage(new ItemStack(DecoBlocks.netherCoalBlock), new ItemStack(BTWItems.nethercoal));
		
		addStorage(new ItemStack(DecoBlocks.diamondiumBlock), new ItemStack(BTWItems.diamondIngot));
		
		for (int color = 0; color < 16; color++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.carpet, 1, color),
					new Object[] {
							"WWW",
							'W', new ItemStack(BTWItems.wool, 1, color)
					});
			
			RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.carpet, 12, color),
					new ItemStack[] {
							new ItemStack(DecoBlocks.carpet, 12, ColorUtils.WHITE.colorID),
							new ItemStack(Item.dyePowder, 1, color)
					});
			RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.carpet, 12, color),
					new ItemStack[] {
							new ItemStack(DecoBlocks.carpet, 12, ColorUtils.WHITE.colorID),
							new ItemStack(Item.dyePowder, 1, color + 16)
					});
			
			RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.carpet, 1, color),
					new ItemStack[] {
							new ItemStack(DecoBlocks.carpet, 1, ColorUtils.WHITE.colorID),
							new ItemStack(Item.dyePowder, 1, color)
					});
			RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.carpet, 1, color),
					new ItemStack[] {
							new ItemStack(DecoBlocks.carpet, 1, ColorUtils.WHITE.colorID),
							new ItemStack(Item.dyePowder, 1, color + 16)
					});
		}
		
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.carpet, 12, ColorUtils.BROWN.colorID),
				new ItemStack[] {
						new ItemStack(DecoBlocks.carpet, 12, ColorUtils.WHITE.colorID),
						new ItemStack(BTWItems.dung)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.carpet, 1, ColorUtils.BROWN.colorID),
				new ItemStack[] {
						new ItemStack(DecoBlocks.carpet, 1, ColorUtils.WHITE.colorID),
						new ItemStack(BTWItems.dung)
				});
		
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.carpet, 12, ColorUtils.WHITE.colorID),
				new ItemStack[] {
						new ItemStack(DecoBlocks.carpet, 12, InventoryUtils.IGNORE_METADATA),
						new ItemStack(BTWItems.soap)
				});
	}
	
	private static void initStoneRecipes() {
		
		//------ Stone ------//
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.STONE_SLAB_UPPER_STRATA_ID, 6, SlabHelper.STONE_SLAB_UPPER_STRATA_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(Block.stone, 1, PolishedStoneBlock.UPPER_STRATA_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(Block.stone, 1, PolishedStoneBlock.UPPER_STRATA_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.STONE_SLAB_UPPER_STRATA_ID, 6, SlabHelper.STONE_SLAB_UPPER_STRATA_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.STONE_SLAB_MID_STRATA_ID, 6, SlabHelper.STONE_SLAB_MID_STRATA_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(Block.stone, 1, PolishedStoneBlock.MID_STRATA_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(Block.stone, 1, PolishedStoneBlock.MID_STRATA_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.STONE_SLAB_MID_STRATA_ID, 6, SlabHelper.STONE_SLAB_MID_STRATA_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.STONE_SLAB_DEEP_STRATA_ID, 6, SlabHelper.STONE_SLAB_DEEP_STRATA_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(Block.stone, 1, PolishedStoneBlock.DEEP_STRATA_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(Block.stone, 1, PolishedStoneBlock.DEEP_STRATA_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.STONE_SLAB_DEEP_STRATA_ID, 6, SlabHelper.STONE_SLAB_DEEP_STRATA_TYPE)
				});
		
		//------ Polished Stone ------//
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.polishedStone, 1, PolishedStoneBlock.UPPER_STRATA_TYPE),
				new ItemStack[] {
						new ItemStack(Block.stone, 1, PolishedStoneBlock.UPPER_STRATA_TYPE)
				});
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.polishedStone, 1, PolishedStoneBlock.MID_STRATA_TYPE),
				new ItemStack[] {
						new ItemStack(Block.stone, 1, PolishedStoneBlock.MID_STRATA_TYPE)
				});
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.polishedStone, 1, PolishedStoneBlock.DEEP_STRATA_TYPE),
				new ItemStack[] {
						new ItemStack(Block.stone, 1, PolishedStoneBlock.DEEP_STRATA_TYPE)
				});
		
		addSubBlockRecipes(DecoBlocks.polishedStone, PolishedStoneBlock.UPPER_STRATA_TYPE, DecoBlocks.polishedStoneSidingAndCornerUpperStrata,
				DecoBlocks.polishedStoneMouldingUpperStrata, DecoBlocks.polishedStoneStairsUpperStrata, BTWBlocks.stoneSlab, PolishedStoneBlock.UPPER_STRATA_TYPE);
		
		addSubBlockRecipes(DecoBlocks.polishedStone, PolishedStoneBlock.MID_STRATA_TYPE, DecoBlocks.polishedStoneSidingAndCornerMidStrata,
				DecoBlocks.polishedStoneMouldingMidStrata, DecoBlocks.polishedStoneStairsMidStrata, BTWBlocks.stoneSlab, PolishedStoneBlock.MID_STRATA_TYPE);
		
		addSubBlockRecipes(DecoBlocks.polishedStone, PolishedStoneBlock.DEEP_STRATA_TYPE, DecoBlocks.polishedStoneSidingAndCornerDeepStrata,
				DecoBlocks.polishedStoneMouldingDeepStrata, DecoBlocks.polishedStoneStairsDeepStrata, BTWBlocks.stoneSlab, PolishedStoneBlock.DEEP_STRATA_TYPE);
		
		//------ Cobblestone ------//
		
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(Block.cobblestone, StoneHelper.COBBLESTONE_UPPER_STRATA_TYPE,
				DecoBlocks.cobblestoneSidingAndCornerUpperStrata, DecoBlocks.cobblestoneMouldingUpperStrata, false);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(Block.cobblestone, StoneHelper.COBBLESTONE_MID_STRATA_TYPE,
				DecoBlocks.cobblestoneSidingAndCornerMidStrata, DecoBlocks.cobblestoneMouldingMidStrata, false);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(Block.cobblestone, StoneHelper.COBBLESTONE_DEEP_STRATA_TYPE,
				DecoBlocks.cobblestoneSidingAndCornerDeepStrata, DecoBlocks.cobblestoneMouldingDeepStrata, false);
		
		addSubBlockRecipes(Block.cobblestoneMossy, StoneHelper.MOSSY_COBBLESTONE_UPPER_STRATA_TYPE, DecoBlocks.mossyCobblestoneSidingAndCornerUpperStrata,
				DecoBlocks.mossyCobblestoneMouldingUpperStrata, DecoBlocks.mossyCobblestoneStairsUpperStrata, SlabHelper.MOSSY_COBBLESTONE_UPPER_STRATA_SLAB_ID,
				SlabHelper.MOSSY_COBBLESTONE_UPPER_STRATA_SLAB_TYPE, false);
		addSubBlockRecipes(Block.cobblestoneMossy, StoneHelper.MOSSY_COBBLESTONE_MID_STRATA_TYPE, DecoBlocks.mossyCobblestoneSidingAndCornerMidStrata,
				DecoBlocks.mossyCobblestoneMouldingMidStrata, DecoBlocks.mossyCobblestoneStairsMidStrata, SlabHelper.MOSSY_COBBLESTONE_MID_STRATA_SLAB_ID,
				SlabHelper.MOSSY_COBBLESTONE_MID_STRATA_SLAB_TYPE, false);
		addSubBlockRecipes(Block.cobblestoneMossy, StoneHelper.MOSSY_COBBLESTONE_DEEP_STRATA_TYPE, DecoBlocks.mossyCobblestoneSidingAndCornerDeepStrata,
				DecoBlocks.mossyCobblestoneMouldingDeepStrata, DecoBlocks.mossyCobblestoneStairsDeepStrata, SlabHelper.MOSSY_COBBLESTONE_DEEP_STRATA_SLAB_ID,
				SlabHelper.MOSSY_COBBLESTONE_DEEP_STRATA_SLAB_TYPE, false);
		
		//------ Stone Brick ------//
		
		RecipeManager.removeSoulforgeRecipe(new ItemStack(Block.stoneBrick, 12, 3),
				new Object[] {
						"####",
						"#  #",
						"#  #",
						"####",
						'#', Block.stoneBrick
				});
		
		addChiselingRecipe(new ItemStack(Block.stoneBrick, 1, 3), new ItemStack(Block.stoneBrick, 1, 0));
		
		addSubBlockRecipes(Block.stoneBrick, StoneHelper.MOSSY_STONE_BRICK_UPPER_STRATA_TYPE, DecoBlocks.mossyStoneBrickSidingAndCornerUpperStrata,
				DecoBlocks.mossyStoneBrickMouldingUpperStrata, DecoBlocks.mossyStoneBrickStairsUpperStrata, SlabHelper.MOSSY_STONE_BRICK_UPPER_STRATA_SLAB_ID,
				SlabHelper.MOSSY_STONE_BRICK_UPPER_STRATA_SLAB_TYPE);
		addSubBlockRecipes(Block.stoneBrick, StoneHelper.MOSSY_STONE_BRICK_MID_STRATA_TYPE, DecoBlocks.mossyStoneBrickSidingAndCornerMidStrata,
				DecoBlocks.mossyStoneBrickMouldingMidStrata, DecoBlocks.mossyStoneBrickStairsMidStrata, SlabHelper.MOSSY_STONE_BRICK_MID_STRATA_SLAB_ID,
				SlabHelper.MOSSY_STONE_BRICK_MID_STRATA_SLAB_TYPE);
		addSubBlockRecipes(Block.stoneBrick, StoneHelper.MOSSY_STONE_BRICK_DEEP_STRATA_TYPE, DecoBlocks.mossyStoneBrickSidingAndCornerDeepStrata,
				DecoBlocks.mossyStoneBrickMouldingDeepStrata, DecoBlocks.mossyStoneBrickStairsDeepStrata, SlabHelper.MOSSY_STONE_BRICK_DEEP_STRATA_SLAB_ID,
				SlabHelper.MOSSY_STONE_BRICK_DEEP_STRATA_SLAB_TYPE);
		
		addSubBlockRecipes(Block.stoneBrick, StoneHelper.CRACKED_STONE_BRICK_UPPER_STRATA_TYPE, DecoBlocks.crackedStoneBrickSidingAndCornerUpperStrata,
				DecoBlocks.crackedStoneBrickMouldingUpperStrata, DecoBlocks.crackedStoneBrickStairsUpperStrata, SlabHelper.CRACKED_STONE_BRICK_UPPER_STRATA_SLAB_ID,
				SlabHelper.CRACKED_STONE_BRICK_UPPER_STRATA_SLAB_TYPE);
		addSubBlockRecipes(Block.stoneBrick, StoneHelper.CRACKED_STONE_BRICK_MID_STRATA_TYPE, DecoBlocks.crackedStoneBrickSidingAndCornerMidStrata,
				DecoBlocks.crackedStoneBrickMouldingMidStrata, DecoBlocks.crackedStoneBrickStairsMidStrata, SlabHelper.CRACKED_STONE_BRICK_MID_STRATA_SLAB_ID,
				SlabHelper.CRACKED_STONE_BRICK_MID_STRATA_SLAB_TYPE);
		addSubBlockRecipes(Block.stoneBrick, StoneHelper.CRACKED_STONE_BRICK_DEEP_STRATA_TYPE, DecoBlocks.crackedStoneBrickSidingAndCornerDeepStrata,
				DecoBlocks.crackedStoneBrickMouldingDeepStrata, DecoBlocks.crackedStoneBrickStairsDeepStrata, SlabHelper.CRACKED_STONE_BRICK_DEEP_STRATA_SLAB_ID,
				SlabHelper.CRACKED_STONE_BRICK_DEEP_STRATA_SLAB_TYPE);
		
		//------ Mortaring ------//
		
		// Cobblestone
		addMortaringRecipe(new ItemStack(Block.cobblestone, 1, StoneHelper.COBBLESTONE_UPPER_STRATA_TYPE),
				new ItemStack(BTWBlocks.looseCobblestone, 1, StoneHelper.LOOSE_COBBLESTONE_UPPER_STRATA_TYPE));
		addMortaringRecipe(new ItemStack(Block.stairsCobblestone),
				new ItemStack(BTWBlocks.looseCobblestoneStairs));
		addMortaringRecipe(new ItemStack(BTWBlocks.cobblestoneSlab, 1, StoneHelper.COBBLESTONE_UPPER_STRATA_TYPE),
				new ItemStack(BTWBlocks.looseCobblestoneSlab, 1, StoneHelper.LOOSE_COBBLESTONE_UPPER_STRATA_TYPE));
		
		addMortaringRecipe(new ItemStack(Block.cobblestone, 1, StoneHelper.COBBLESTONE_MID_STRATA_TYPE),
				new ItemStack(BTWBlocks.looseCobblestone, 1, StoneHelper.LOOSE_COBBLESTONE_MID_STRATA_TYPE));
		addMortaringRecipe(new ItemStack(BTWBlocks.midStrataCobblestoneStairs),
				new ItemStack(BTWBlocks.looseMidStrataCobblestoneStairs));
		addMortaringRecipe(new ItemStack(BTWBlocks.cobblestoneSlab, 1, StoneHelper.COBBLESTONE_MID_STRATA_TYPE),
				new ItemStack(BTWBlocks.looseCobblestoneSlab, 1, StoneHelper.LOOSE_COBBLESTONE_MID_STRATA_TYPE));
		
		addMortaringRecipe(new ItemStack(Block.cobblestone, 1, StoneHelper.COBBLESTONE_DEEP_STRATA_TYPE),
				new ItemStack(BTWBlocks.looseCobblestone, 1, StoneHelper.LOOSE_COBBLESTONE_DEEP_STRATA_TYPE));
		addMortaringRecipe(new ItemStack(BTWBlocks.deepStrataCobblestoneStairs),
				new ItemStack(BTWBlocks.looseDeepStrataCobblestoneStairs));
		addMortaringRecipe(new ItemStack(BTWBlocks.cobblestoneSlab, 1, StoneHelper.COBBLESTONE_DEEP_STRATA_TYPE),
				new ItemStack(BTWBlocks.looseCobblestoneSlab, 1, StoneHelper.LOOSE_COBBLESTONE_DEEP_STRATA_TYPE));
		
		// StoneBrick
		addMortaringRecipe(new ItemStack(Block.stoneBrick, 1, StoneHelper.STONE_BRICK_UPPER_STRATA_TYPE),
				new ItemStack(BTWBlocks.looseStoneBrick, 1, StoneHelper.LOOSE_STONE_BRICK_UPPER_STRATA_TYPE));
		addMortaringRecipe(new ItemStack(Block.stairsStoneBrick),
				new ItemStack(BTWBlocks.looseStoneBrickStairs));
		addMortaringRecipe(new ItemStack(BTWBlocks.stoneBrickSlab, 1, StoneHelper.STONE_BRICK_UPPER_STRATA_TYPE),
				new ItemStack(BTWBlocks.looseStoneBrickSlab, 1, StoneHelper.LOOSE_STONE_BRICK_UPPER_STRATA_TYPE));
		
		addMortaringRecipe(new ItemStack(Block.stoneBrick, 1, StoneHelper.STONE_BRICK_MID_STRATA_TYPE),
				new ItemStack(BTWBlocks.looseStoneBrick, 1, StoneHelper.LOOSE_STONE_BRICK_MID_STRATA_TYPE));
		addMortaringRecipe(new ItemStack(BTWBlocks.midStrataStoneBrickStairs),
				new ItemStack(BTWBlocks.looseMidStrataStoneBrickStairs));
		addMortaringRecipe(new ItemStack(BTWBlocks.stoneBrickSlab, 1, StoneHelper.STONE_BRICK_MID_STRATA_TYPE),
				new ItemStack(BTWBlocks.looseStoneBrickSlab, 1, StoneHelper.LOOSE_STONE_BRICK_MID_STRATA_TYPE));
		
		addMortaringRecipe(new ItemStack(Block.stoneBrick, 1, StoneHelper.STONE_BRICK_DEEP_STRATA_TYPE),
				new ItemStack(BTWBlocks.looseStoneBrick, 1, StoneHelper.LOOSE_STONE_BRICK_DEEP_STRATA_TYPE));
		addMortaringRecipe(new ItemStack(BTWBlocks.deepStrataStoneBrickStairs),
				new ItemStack(BTWBlocks.looseDeepStrataStoneBrickStairs));
		addMortaringRecipe(new ItemStack(BTWBlocks.stoneBrickSlab, 1, StoneHelper.STONE_BRICK_DEEP_STRATA_TYPE),
				new ItemStack(BTWBlocks.looseStoneBrickSlab, 1, StoneHelper.LOOSE_STONE_BRICK_DEEP_STRATA_TYPE));
		
		initGraniteRecipes();
		initAndesiteRecipes();
		initDioriteRecipes();
		initSlateRecipes();
	}
	
	private static void initGraniteRecipes() {
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.looseGraniteCobblestone, 16),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteCobblestone, 16),
						new ItemStack(Item.netherQuartz)
				});
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE)
				});
		
		//------ Loose ------//
		
		// Blocks - Stone
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseGraniteCobblestone),
				new ItemStack[] {
						new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseGraniteCobblestone),
				new ItemStack[] {
						new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_ID, 4, SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_TYPE),
				new Object[] {
						"FF",
						'F', new ItemStack(DecoBlocks.looseGraniteCobblestone)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseGraniteCobblestone, 1),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseGraniteCobblestoneStairs, 4),
				new Object[] {
						"F ",
						"FF",
						'F', new ItemStack(DecoBlocks.looseGraniteCobblestone)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseGraniteCobblestoneStairs, 2),
				new Object[] {
						"S ",
						"SS",
						'S', new ItemStack(SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_TYPE)
				});
		
		// Items - Stone
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stone, 8, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseGraniteCobblestone)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stone, 6, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseGraniteCobblestoneStairs)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stone, 4, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_TYPE)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseGraniteCobblestone),
				new ItemStack[] {
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		// Blocks - Stone Brick
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseGraniteBrick),
				new ItemStack[] {
						new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseGraniteBrick),
				new ItemStack[] {
						new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.LOOSE_GRANITE_BRICK_SLAB_ID, 4, SlabHelper.LOOSE_GRANITE_BRICK_SLAB_TYPE),
				new Object[] {
						"FF",
						'F', new ItemStack(DecoBlocks.looseGraniteBrick)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseGraniteBrick, 1),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.LOOSE_GRANITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_BRICK_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseGraniteBrickStairs, 4),
				new Object[] {
						"F ",
						"FF",
						'F', new ItemStack(DecoBlocks.looseGraniteBrick)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseGraniteBrickStairs, 2),
				new Object[] {
						"S ",
						"SS",
						'S', new ItemStack(SlabHelper.LOOSE_GRANITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_BRICK_SLAB_TYPE)
				});
		
		// Items - Stone brick
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stoneBrick, 8, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseGraniteBrick)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stoneBrick, 6, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseGraniteBrickStairs)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stoneBrick, 4, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(SlabHelper.LOOSE_GRANITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_BRICK_SLAB_TYPE)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(SlabHelper.LOOSE_GRANITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseGraniteBrick),
				new ItemStack[] {
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.GRANITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		//------ Mortar ------//
		
		// Clay
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseGraniteCobblestone),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.graniteCobblestoneStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseGraniteCobblestoneStairs),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.GRANITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.GRANITE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_TYPE),
						new ItemStack(Item.clay)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseGraniteBrick),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.graniteBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseGraniteBrickStairs),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.GRANITE_BRICK_SLAB_ID, 1, SlabHelper.GRANITE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_GRANITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_BRICK_SLAB_TYPE),
						new ItemStack(Item.clay)
				});
		
		// Slime
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseGraniteCobblestone),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.graniteCobblestoneStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseGraniteCobblestoneStairs),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.GRANITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.GRANITE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_TYPE),
						new ItemStack(Item.slimeBall)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseGraniteBrick),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.graniteBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseGraniteBrickStairs),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.GRANITE_BRICK_SLAB_ID, 1, SlabHelper.GRANITE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_GRANITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_BRICK_SLAB_TYPE),
						new ItemStack(Item.slimeBall)
				});
		
		// Nether Sludge
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseGraniteCobblestone),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.graniteCobblestoneStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseGraniteCobblestoneStairs),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.GRANITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.GRANITE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_TYPE),
						new ItemStack(BTWItems.netherSludge)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseGraniteBrick),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.graniteBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseGraniteBrickStairs),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.GRANITE_BRICK_SLAB_ID, 1, SlabHelper.GRANITE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_GRANITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_GRANITE_BRICK_SLAB_TYPE),
						new ItemStack(BTWItems.netherSludge)
				});
		
		//------ Sub blocks ------//
		
		// Sub blocks
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.stoneVariants, StoneVariantsBlock.GRANITE_TYPE,
				DecoBlocks.graniteSidingAndCorner, DecoBlocks.graniteMoulding, true);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.polishedStoneVariants, StoneVariantsBlock.GRANITE_TYPE,
				DecoBlocks.polishedGraniteSidingAndCorner, DecoBlocks.polishedGraniteMoulding, true);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.cobblestoneVariants, StoneVariantsBlock.GRANITE_TYPE,
				DecoBlocks.graniteCobblestoneSidingAndCorner, DecoBlocks.graniteCobblestoneMoulding, true);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.stoneBrickVariants, StoneVariantsBlock.GRANITE_TYPE,
				DecoBlocks.graniteBrickSidingAndCorner, DecoBlocks.graniteBrickMoulding, true);
		
		// Stairs
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.graniteStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.graniteStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.graniteMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedGraniteStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedGraniteStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.polishedGraniteMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.graniteCobblestoneStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.graniteCobblestoneStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.graniteCobblestoneMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.graniteBrickStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.GRANITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.graniteBrickStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.graniteBrickMoulding)
				});
		
		// Slabs
		RecipeManager.addRecipe(new ItemStack(SlabHelper.GRANITE_SLAB_ID, 6, SlabHelper.GRANITE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.GRANITE_SLAB_ID, 1, SlabHelper.GRANITE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.POLISHED_GRANITE_SLAB_ID, 6, SlabHelper.POLISHED_GRANITE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.POLISHED_GRANITE_SLAB_ID, 1, SlabHelper.POLISHED_GRANITE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.GRANITE_COBBLESTONE_SLAB_ID, 6, SlabHelper.GRANITE_COBBLESTONE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.GRANITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.GRANITE_COBBLESTONE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.GRANITE_BRICK_SLAB_ID, 6, SlabHelper.GRANITE_BRICK_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.GRANITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.GRANITE_BRICK_SLAB_ID, 1, SlabHelper.GRANITE_BRICK_SLAB_TYPE)
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.graniteButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.GRANITE_TYPE),
						'R', Item.redstone
				});
	}
	
	private static void initAndesiteRecipes() {
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.looseAndesiteCobblestone, 16),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteCobblestone, 16),
						new ItemStack(BTWBlocks.looseCobblestone, 1)
				});
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE)
				});
		
		//------ Loose ------//
		
		// Blocks - Stone
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseAndesiteCobblestone),
				new ItemStack[] {
						new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseAndesiteCobblestone),
				new ItemStack[] {
						new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_ID, 4, SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_TYPE),
				new Object[] {
						"FF",
						'F', new ItemStack(DecoBlocks.looseAndesiteCobblestone)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseAndesiteCobblestone, 1),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseAndesiteCobblestoneStairs, 4),
				new Object[] {
						"F ",
						"FF",
						'F', new ItemStack(DecoBlocks.looseAndesiteCobblestone)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseAndesiteCobblestoneStairs, 2),
				new Object[] {
						"S ",
						"SS",
						'S', new ItemStack(SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_TYPE)
				});
		
		// Items - Stone
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stone, 8, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseAndesiteCobblestone)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stone, 6, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseAndesiteCobblestoneStairs)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stone, 4, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_TYPE)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseAndesiteCobblestone),
				new ItemStack[] {
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		// Blocks - Stone Brick
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseAndesiteBrick),
				new ItemStack[] {
						new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseAndesiteBrick),
				new ItemStack[] {
						new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_ID, 4, SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_TYPE),
				new Object[] {
						"FF",
						'F', new ItemStack(DecoBlocks.looseAndesiteBrick)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseAndesiteBrick, 1),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseAndesiteBrickStairs, 4),
				new Object[] {
						"F ",
						"FF",
						'F', new ItemStack(DecoBlocks.looseAndesiteBrick)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseAndesiteBrickStairs, 2),
				new Object[] {
						"S ",
						"SS",
						'S', new ItemStack(SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_TYPE)
				});
		
		// Items - Stone brick
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stoneBrick, 8, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseAndesiteBrick)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stoneBrick, 6, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseAndesiteBrickStairs)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stoneBrick, 4, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_TYPE)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseAndesiteBrick),
				new ItemStack[] {
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.ANDESITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		//------ Mortar ------//
		
		// Clay
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseAndesiteCobblestone),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.andesiteCobblestoneStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseAndesiteCobblestoneStairs),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.ANDESITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.ANDESITE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_TYPE),
						new ItemStack(Item.clay)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseAndesiteBrick),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.andesiteBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseAndesiteBrickStairs),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.ANDESITE_BRICK_SLAB_ID, 1, SlabHelper.ANDESITE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_TYPE),
						new ItemStack(Item.clay)
				});
		
		// Slime
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseAndesiteCobblestone),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.andesiteCobblestoneStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseAndesiteCobblestoneStairs),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.ANDESITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.ANDESITE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_TYPE),
						new ItemStack(Item.slimeBall)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseAndesiteBrick),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.andesiteBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseAndesiteBrickStairs),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.ANDESITE_BRICK_SLAB_ID, 1, SlabHelper.ANDESITE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_TYPE),
						new ItemStack(Item.slimeBall)
				});
		
		// Nether Sludge
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseAndesiteCobblestone),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.andesiteCobblestoneStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseAndesiteCobblestoneStairs),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.ANDESITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.ANDESITE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_TYPE),
						new ItemStack(BTWItems.netherSludge)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseAndesiteBrick),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.andesiteBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseAndesiteBrickStairs),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.ANDESITE_BRICK_SLAB_ID, 1, SlabHelper.ANDESITE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_TYPE),
						new ItemStack(BTWItems.netherSludge)
				});
		
		//------ Sub blocks ------//
		
		// Sub blocks
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.stoneVariants, StoneVariantsBlock.ANDESITE_TYPE,
				DecoBlocks.andesiteSidingAndCorner, DecoBlocks.andesiteMoulding, true);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.polishedStoneVariants, StoneVariantsBlock.ANDESITE_TYPE,
				DecoBlocks.polishedAndesiteSidingAndCorner, DecoBlocks.polishedAndesiteMoulding, true);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.cobblestoneVariants, StoneVariantsBlock.ANDESITE_TYPE,
				DecoBlocks.andesiteCobblestoneSidingAndCorner, DecoBlocks.andesiteCobblestoneMoulding, true);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.stoneBrickVariants, StoneVariantsBlock.ANDESITE_TYPE,
				DecoBlocks.andesiteBrickSidingAndCorner, DecoBlocks.andesiteBrickMoulding, true);
		
		// Stairs
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.andesiteStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.andesiteStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.andesiteMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedAndesiteStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedAndesiteStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.polishedAndesiteMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.andesiteCobblestoneStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.andesiteCobblestoneStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.andesiteCobblestoneMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.andesiteBrickStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.ANDESITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.andesiteBrickStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.andesiteBrickMoulding)
				});
		
		// Slabs
		RecipeManager.addRecipe(new ItemStack(SlabHelper.ANDESITE_SLAB_ID, 6, SlabHelper.ANDESITE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.ANDESITE_SLAB_ID, 1, SlabHelper.ANDESITE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.POLISHED_ANDESITE_SLAB_ID, 6, SlabHelper.POLISHED_ANDESITE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.POLISHED_ANDESITE_SLAB_ID, 1, SlabHelper.POLISHED_ANDESITE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.ANDESITE_COBBLESTONE_SLAB_ID, 6, SlabHelper.ANDESITE_COBBLESTONE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.ANDESITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.ANDESITE_COBBLESTONE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.ANDESITE_BRICK_SLAB_ID, 6, SlabHelper.ANDESITE_BRICK_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.ANDESITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.ANDESITE_BRICK_SLAB_ID, 1, SlabHelper.ANDESITE_BRICK_SLAB_TYPE)
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.andesiteButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.ANDESITE_TYPE),
						'R', Item.redstone
				});
	}
	
	private static void initDioriteRecipes() {
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.looseDioriteCobblestone, 16),
				new ItemStack[] {
						new ItemStack(BTWBlocks.looseCobblestone, 16),
						new ItemStack(Item.netherQuartz)
				});
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE)
				});
		
		//------ Loose ------//
		
		// Blocks - Stone
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseDioriteCobblestone),
				new ItemStack[] {
						new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseDioriteCobblestone),
				new ItemStack[] {
						new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_ID, 4, SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_TYPE),
				new Object[] {
						"FF",
						'F', new ItemStack(DecoBlocks.looseDioriteCobblestone)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseDioriteCobblestone, 1),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseDioriteCobblestoneStairs, 4),
				new Object[] {
						"F ",
						"FF",
						'F', new ItemStack(DecoBlocks.looseDioriteCobblestone)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseDioriteCobblestoneStairs, 2),
				new Object[] {
						"S ",
						"SS",
						'S', new ItemStack(SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_TYPE)
				});
		
		// Items - Stone
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stone, 8, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseDioriteCobblestone)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stone, 6, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseDioriteCobblestoneStairs)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stone, 4, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_TYPE)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseDioriteCobblestone),
				new ItemStack[] {
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		// Blocks - Stone Brick
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseDioriteBrick),
				new ItemStack[] {
						new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseDioriteBrick),
				new ItemStack[] {
						new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.LOOSE_DIORITE_BRICK_SLAB_ID, 4, SlabHelper.LOOSE_DIORITE_BRICK_SLAB_TYPE),
				new Object[] {
						"FF",
						'F', new ItemStack(DecoBlocks.looseDioriteBrick)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseDioriteBrick, 1),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.LOOSE_DIORITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_BRICK_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseDioriteBrickStairs, 4),
				new Object[] {
						"F ",
						"FF",
						'F', new ItemStack(DecoBlocks.looseDioriteBrick)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseDioriteBrickStairs, 2),
				new Object[] {
						"S ",
						"SS",
						'S', new ItemStack(SlabHelper.LOOSE_DIORITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_BRICK_SLAB_TYPE)
				});
		
		// Items - Stone brick
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stoneBrick, 8, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseDioriteBrick)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stoneBrick, 6, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseDioriteBrickStairs)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stoneBrick, 4, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(SlabHelper.LOOSE_DIORITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_BRICK_SLAB_TYPE)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(SlabHelper.LOOSE_DIORITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseDioriteBrick),
				new ItemStack[] {
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.DIORITE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		//------ Mortar ------//
		
		// Clay
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteCobblestone),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.dioriteCobblestoneStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteCobblestoneStairs),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.DIORITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.DIORITE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_TYPE),
						new ItemStack(Item.clay)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteBrick),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.dioriteBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteBrickStairs),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.DIORITE_BRICK_SLAB_ID, 1, SlabHelper.DIORITE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_DIORITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_BRICK_SLAB_TYPE),
						new ItemStack(Item.clay)
				});
		
		// Slime
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteCobblestone),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.dioriteCobblestoneStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteCobblestoneStairs),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.DIORITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.DIORITE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_TYPE),
						new ItemStack(Item.slimeBall)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteBrick),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.dioriteBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteBrickStairs),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.DIORITE_BRICK_SLAB_ID, 1, SlabHelper.DIORITE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_DIORITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_BRICK_SLAB_TYPE),
						new ItemStack(Item.slimeBall)
				});
		
		// Nether Sludge
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteCobblestone),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.dioriteCobblestoneStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteCobblestoneStairs),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.DIORITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.DIORITE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_TYPE),
						new ItemStack(BTWItems.netherSludge)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteBrick),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.dioriteBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseDioriteBrickStairs),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.DIORITE_BRICK_SLAB_ID, 1, SlabHelper.DIORITE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_DIORITE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_DIORITE_BRICK_SLAB_TYPE),
						new ItemStack(BTWItems.netherSludge)
				});
		
		//------ Sub blocks ------//
		
		// Sub blocks
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.stoneVariants, StoneVariantsBlock.DIORITE_TYPE,
				DecoBlocks.dioriteSidingAndCorner, DecoBlocks.dioriteMoulding, true);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.polishedStoneVariants, StoneVariantsBlock.DIORITE_TYPE,
				DecoBlocks.polishedDioriteSidingAndCorner, DecoBlocks.polishedDioriteMoulding, true);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.cobblestoneVariants, StoneVariantsBlock.DIORITE_TYPE,
				DecoBlocks.dioriteCobblestoneSidingAndCorner, DecoBlocks.dioriteCobblestoneMoulding, true);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.stoneBrickVariants, StoneVariantsBlock.DIORITE_TYPE,
				DecoBlocks.dioriteBrickSidingAndCorner, DecoBlocks.dioriteBrickMoulding, true);
		
		// Stairs
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.dioriteStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.dioriteStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.dioriteMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedDioriteStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedDioriteStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.polishedDioriteMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.dioriteCobblestoneStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.dioriteCobblestoneStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.dioriteCobblestoneMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.dioriteBrickStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.DIORITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.dioriteBrickStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.dioriteBrickMoulding)
				});
		
		// Slabs
		RecipeManager.addRecipe(new ItemStack(SlabHelper.DIORITE_SLAB_ID, 6, SlabHelper.DIORITE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.DIORITE_SLAB_ID, 1, SlabHelper.DIORITE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.POLISHED_DIORITE_SLAB_ID, 6, SlabHelper.POLISHED_DIORITE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.POLISHED_DIORITE_SLAB_ID, 1, SlabHelper.POLISHED_DIORITE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.DIORITE_COBBLESTONE_SLAB_ID, 6, SlabHelper.DIORITE_COBBLESTONE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.DIORITE_COBBLESTONE_SLAB_ID, 1, SlabHelper.DIORITE_COBBLESTONE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.DIORITE_BRICK_SLAB_ID, 6, SlabHelper.DIORITE_BRICK_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.DIORITE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.DIORITE_BRICK_SLAB_ID, 1, SlabHelper.DIORITE_BRICK_SLAB_TYPE)
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.dioriteButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(DecoBlocks.stoneVariants, 1, StoneVariantsBlock.DIORITE_TYPE),
						'R', Item.redstone
				});
	}
	
	private static void initSlateRecipes() {
		RecipeManager.addPistonPackingRecipe(DecoBlocks.slate,
				new ItemStack(Block.stone, 2, InventoryUtils.IGNORE_METADATA));
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.SLATE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.slate)
				});
		
		//------ Loose ------//
		
		// Blocks - Stone
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseSlateCobblestone),
				new ItemStack[] {
						new ItemStack(DecoBlocks.slate),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseSlateCobblestone),
				new ItemStack[] {
						new ItemStack(DecoBlocks.slate),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_ID, 4, SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_TYPE),
				new Object[] {
						"FF",
						'F', new ItemStack(DecoBlocks.looseSlateCobblestone)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseSlateCobblestone, 1),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseSlateCobblestoneStairs, 4),
				new Object[] {
						"F ",
						"FF",
						'F', new ItemStack(DecoBlocks.looseSlateCobblestone)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseSlateCobblestoneStairs, 2),
				new Object[] {
						"S ",
						"SS",
						'S', new ItemStack(SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_TYPE)
				});
		
		// Items - Stone
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stone, 8, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseSlateCobblestone)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stone, 6, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseSlateCobblestoneStairs)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stone, 4, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_TYPE)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseSlateCobblestone),
				new ItemStack[] {
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stone, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		// Blocks - Stone Brick
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseSlateBrick),
				new ItemStack[] {
						new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.SLATE_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseSlateBrick),
				new ItemStack[] {
						new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.SLATE_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.slateTiles, 4),
				new Object[] {
						"BB",
						"BB",
						'B', new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.SLATE_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.LOOSE_SLATE_BRICK_SLAB_ID, 4, SlabHelper.LOOSE_SLATE_BRICK_SLAB_TYPE),
				new Object[] {
						"FF",
						'F', new ItemStack(DecoBlocks.looseSlateBrick)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseSlateBrick, 1),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.LOOSE_SLATE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_BRICK_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseSlateBrickStairs, 4),
				new Object[] {
						"F ",
						"FF",
						'F', new ItemStack(DecoBlocks.looseSlateBrick)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseSlateBrickStairs, 2),
				new Object[] {
						"S ",
						"SS",
						'S', new ItemStack(SlabHelper.LOOSE_SLATE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_BRICK_SLAB_TYPE)
				});
		
		// Items - Stone brick
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stoneBrick, 8, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseSlateBrick)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stoneBrick, 6, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(DecoBlocks.looseSlateBrickStairs)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.stoneBrick, 4, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
				new ItemStack[] {new ItemStack(SlabHelper.LOOSE_SLATE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_BRICK_SLAB_TYPE)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(SlabHelper.LOOSE_SLATE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseSlateBrick),
				new ItemStack[] {
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES),
						new ItemStack(BTWItems.stoneBrick, 1, StoneVariantsBlock.SLATE_TYPE + StoneVariantsBlock.NUM_VANILLA_TYPES)
				});
		
		//------ Mortar ------//
		
		// Clay
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.SLATE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseSlateCobblestone),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.slateCobblestoneStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseSlateCobblestoneStairs),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.SLATE_COBBLESTONE_SLAB_ID, 1, SlabHelper.SLATE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_TYPE),
						new ItemStack(Item.clay)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.SLATE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseSlateBrick),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.slateBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseSlateBrickStairs),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.SLATE_BRICK_SLAB_ID, 1, SlabHelper.SLATE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_SLATE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_BRICK_SLAB_TYPE),
						new ItemStack(Item.clay)
				});
		
		// Slime
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.SLATE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseSlateCobblestone),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.slateCobblestoneStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseSlateCobblestoneStairs),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.SLATE_COBBLESTONE_SLAB_ID, 1, SlabHelper.SLATE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_TYPE),
						new ItemStack(Item.slimeBall)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.SLATE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseSlateBrick),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.slateBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseSlateBrickStairs),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.SLATE_BRICK_SLAB_ID, 1, SlabHelper.SLATE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_SLATE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_BRICK_SLAB_TYPE),
						new ItemStack(Item.slimeBall)
				});
		
		// Nether Sludge
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.SLATE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseSlateCobblestone),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.slateCobblestoneStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseSlateCobblestoneStairs),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.SLATE_COBBLESTONE_SLAB_ID, 1, SlabHelper.SLATE_COBBLESTONE_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_TYPE),
						new ItemStack(BTWItems.netherSludge)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.SLATE_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseSlateBrick),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.slateBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseSlateBrickStairs),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.SLATE_BRICK_SLAB_ID, 1, SlabHelper.SLATE_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.LOOSE_SLATE_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_SLATE_BRICK_SLAB_TYPE),
						new ItemStack(BTWItems.netherSludge)
				});
		
		//------ Sub blocks ------//
		
		// Sub blocks
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.stoneVariants, StoneVariantsBlock.SLATE_TYPE,
				DecoBlocks.slateSidingAndCorner, DecoBlocks.slateMoulding, true);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.polishedStoneVariants, StoneVariantsBlock.SLATE_TYPE,
				DecoBlocks.polishedSlateSidingAndCorner, DecoBlocks.polishedSlateMoulding, true);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.cobblestoneVariants, StoneVariantsBlock.SLATE_TYPE,
				DecoBlocks.slateCobblestoneSidingAndCorner, DecoBlocks.slateCobblestoneMoulding, true);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.stoneBrickVariants, StoneVariantsBlock.SLATE_TYPE,
				DecoBlocks.slateBrickSidingAndCorner, DecoBlocks.slateBrickMoulding, true);
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.slateTiles, StoneVariantsBlock.SLATE_TYPE,
				DecoBlocks.slateTilesSidingAndCorner, DecoBlocks.slateTilesMoulding, true);
		
		// Stairs
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.slateStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.slate)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.slateStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.slateMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedSlateStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.SLATE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedSlateStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.polishedSlateMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.slateCobblestoneStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.SLATE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.slateCobblestoneStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.slateCobblestoneMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.slateBrickStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.SLATE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.slateBrickStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.slateBrickMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.slateTilesStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.slateTiles)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.slateTilesStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.slateTilesMoulding)
				});
		
		// Slabs
		RecipeManager.addRecipe(new ItemStack(SlabHelper.SLATE_SLAB_ID, 6, SlabHelper.SLATE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.slate)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.slate),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.SLATE_SLAB_ID, 1, SlabHelper.SLATE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.POLISHED_SLATE_SLAB_ID, 6, SlabHelper.POLISHED_SLATE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.SLATE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedStoneVariants, 1, StoneVariantsBlock.SLATE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.POLISHED_SLATE_SLAB_ID, 1, SlabHelper.POLISHED_SLATE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.SLATE_COBBLESTONE_SLAB_ID, 6, SlabHelper.SLATE_COBBLESTONE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.SLATE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.cobblestoneVariants, 1, StoneVariantsBlock.SLATE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.SLATE_COBBLESTONE_SLAB_ID, 1, SlabHelper.SLATE_COBBLESTONE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.SLATE_BRICK_SLAB_ID, 6, SlabHelper.SLATE_BRICK_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.SLATE_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.stoneBrickVariants, 1, StoneVariantsBlock.SLATE_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.SLATE_BRICK_SLAB_ID, 1, SlabHelper.SLATE_BRICK_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.SLATE_TILES_SLAB_ID, 6, SlabHelper.SLATE_TILES_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.slateTiles)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.slateTiles),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.SLATE_TILES_SLAB_ID, 1, SlabHelper.SLATE_TILES_SLAB_TYPE)
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.slateButton),
				new Object[] {
						"C",
						"R",
						'C', DecoBlocks.slate,
						'R', Item.redstone
				});
	}
	
	private static void initWhiteStoneRecipes() {
		RecipeManager.addRecipe(new ItemStack(SlabHelper.WHITE_STONE_SLAB_ID, 6, SlabHelper.WHITE_STONE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(BTWBlocks.aestheticOpaque, 1, AestheticOpaqueBlock.SUBTYPE_WHITE_STONE)
				});
		RecipeManager.addRecipe(new ItemStack(BTWBlocks.aestheticOpaque, 1, AestheticOpaqueBlock.SUBTYPE_WHITE_STONE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.WHITE_STONE_SLAB_ID, 6, SlabHelper.WHITE_STONE_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.whiteStoneBricks, 4),
				new Object[] {
						"FF",
						"FF",
						'F', new ItemStack(BTWBlocks.aestheticOpaque, 1, AestheticOpaqueBlock.SUBTYPE_WHITE_STONE)
				});
		
		addChiselingRecipe(new ItemStack(DecoBlocks.whiteStoneBricks, 1, WhiteStoneBrickBlock.TYPE_CHISELED),
				new ItemStack(DecoBlocks.whiteStoneBricks, 1, WhiteStoneBrickBlock.TYPE_DEFAULT));
		
		addSubBlockRecipes(DecoBlocks.whiteStoneBricks, WhiteStoneBrickBlock.TYPE_DEFAULT, DecoBlocks.whiteStoneBrickSidingAndCorner,
				DecoBlocks.whiteStoneBrickMoulding, DecoBlocks.whiteStoneBrickStairs, SlabHelper.WHITE_STONE_BRICK_SLAB_ID, SlabHelper.WHITE_STONE_BRICK_SLAB_TYPE);
	}
	
	private static void initPrismarineRecipes() {
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.prismarine, 1, PrismarineBlock.DEFAULT_TYPE),
				new Object[] {
						"SS",
						"SS",
						'S', DecoItems.prismarineShard
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.prismarine, 4, PrismarineBlock.BRICK_TYPE),
				new Object[] {
						"PP",
						"PP",
						'P', new ItemStack(DecoBlocks.prismarine, 1, PrismarineBlock.DEFAULT_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.prismarine, 1, PrismarineBlock.DEFAULT_TYPE),
				new Object[] {
						"SSS",
						"SDS",
						"SSS",
						'S', DecoItems.prismarineShard,
						'D', new ItemStack(Item.dyePowder, 1, ColorUtils.BLACK.colorID)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.prismarine, 1, PrismarineBlock.DEFAULT_TYPE),
				new Object[] {
						"SSS",
						"SDS",
						"SSS",
						'S', DecoItems.prismarineShard,
						'D', new ItemStack(Item.dyePowder, 1, ColorUtils.BLACK.colorID + 16)
				});
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoItems.prismarineCrystal),
				new ItemStack[] {
						new ItemStack(DecoItems.prismarineShard),
						new ItemStack(Item.lightStoneDust)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.prismarineLantern),
				new Object[] {
						"SSS",
						"SCS",
						"SSS",
						'S', DecoItems.prismarineShard,
						'C', DecoItems.prismarineCrystal
				});
		
		addSubBlockRecipes(DecoBlocks.prismarine, PrismarineBlock.DEFAULT_TYPE, DecoBlocks.prismarineSidingAndCorner, DecoBlocks.prismarineMoulding,
				DecoBlocks.prismarineStairs, SlabHelper.PRISMARINE_SLAB_ID, SlabHelper.PRISMARINE_SLAB_TYPE);
		
		addSubBlockRecipes(DecoBlocks.prismarine, PrismarineBlock.BRICK_TYPE, DecoBlocks.prismarineBrickSidingAndCorner, DecoBlocks.prismarineBrickMoulding,
				DecoBlocks.prismarineBrickStairs, SlabHelper.PRISMARINE_BRICK_SLAB_ID, SlabHelper.PRISMARINE_BRICK_SLAB_TYPE);
		
		addSubBlockRecipes(DecoBlocks.prismarine, PrismarineBlock.DARK_TYPE, DecoBlocks.darkPrismarineSidingAndCorner, DecoBlocks.darkPrismarineMoulding,
				DecoBlocks.darkPrismarineStairs, SlabHelper.DARK_PRISMARINE_SLAB_ID, SlabHelper.DARK_PRISMARINE_SLAB_TYPE);
	}
	
	private static void initSandstoneRecipes() {
		
		//------ Sandstone ------//
	
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.sandStone, 4, SandHelper.SANDSTONE_TYPE_CUT), new Object[] {
				"##",
				"##",
				'#', Block.sandStone});
		RecipeManager.addRecipe(new ItemStack(Block.sandStone, 4, SandHelper.SANDSTONE_TYPE_CUT), new Object[] {
				"##",
				"##",
				'#', new ItemStack(Block.sandStone, 1, SandHelper.SANDSTONE_TYPE_DEFAULT)});
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(Block.sandStone, 1, SandHelper.SANDSTONE_TYPE_POLISHED),
				new ItemStack[] {
					new ItemStack(Block.sandStone, 1, SandHelper.SANDSTONE_TYPE_DEFAULT)
				});
		
		RecipeManager.addRecipe(new ItemStack(Block.sandStone, 4, SandHelper.SANDSTONE_TYPE_BRICK), new Object[] {
				"XX",
				"XX",
				'X', new ItemStack(Block.sandStone, 1, SandHelper.SANDSTONE_TYPE_POLISHED)});
		
		RecipeManager.addRecipe(new ItemStack(Block.sandStone, 4, SandHelper.SANDSTONE_TYPE_LARGE_BRICK), new Object[] {
				"XX",
				"XX",
				'X', new ItemStack(Block.sandStone, 1, SandHelper.SANDSTONE_TYPE_CUT)});
		
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.sandStone, 1, SandHelper.SANDSTONE_TYPE_CHISELED),
				new Object[] {
						"#",
						"#",
						'#', new ItemStack(Block.stoneSingleSlab, 1, SlabHelper.SANDSTONE_SLAB_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(Block.sandStone),
				new Object[] {
						"#",
						"#",
						'#', new ItemStack(Block.stoneSingleSlab, 1, SlabHelper.SANDSTONE_SLAB_TYPE)
				});
		
		addChiselingRecipe(new ItemStack(Block.sandStone, 1, SandHelper.SANDSTONE_TYPE_CHISELED),
				new ItemStack(Block.sandStone, 1, SandHelper.SANDSTONE_TYPE_DEFAULT));
		
		// Sub blocks
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.stoneSingleSlab, 6, 1), new Object[] {
				"XXX",
				'X', Block.sandStone});
		RecipeManager.addRecipe(new ItemStack(Block.stoneSingleSlab, 6, 1), new Object[] {
				"XXX",
				'X', new ItemStack(Block.sandStone, 1, SandHelper.SANDSTONE_TYPE_DEFAULT)});
		
		removeSubBlockRecipes(Block.sandStone, InventoryUtils.IGNORE_METADATA, SandHelper.SANDSTONE_TYPE_DEFAULT, BTWBlocks.sandstoneSidingAndCorner,
				BTWBlocks.sandstoneMouldingAndDecorative, true);
		
		addSubBlockRecipes(Block.sandStone, SandHelper.SANDSTONE_TYPE_DEFAULT, BTWBlocks.sandstoneSidingAndCorner, BTWBlocks.sandstoneMouldingAndDecorative,
				Block.stairsSandStone, SlabHelper.SANDSTONE_SLAB_ID, SlabHelper.SANDSTONE_SLAB_TYPE);
		
		addSubBlockRecipes(Block.sandStone, SandHelper.SANDSTONE_TYPE_CUT, DecoBlocks.cutSandstoneSiding, DecoBlocks.cutSandstoneMoulding,
				DecoBlocks.cutSandstoneStairs, Block.blocksList[SlabHelper.CUT_SANDSTONE_SLAB_ID], SlabHelper.CUT_SANDSTONE_SLAB_TYPE);
		
		addSubBlockRecipes(Block.sandStone, SandHelper.SANDSTONE_TYPE_POLISHED, DecoBlocks.polishedSandstoneSiding, DecoBlocks.polishedSandstoneMoulding,
				DecoBlocks.polishedSandstoneStairs, Block.blocksList[SlabHelper.POLISHED_SANDSTONE_SLAB_ID], SlabHelper.POLISHED_SANDSTONE_SLAB_TYPE);
		
		addSubBlockRecipes(Block.sandStone, SandHelper.SANDSTONE_TYPE_BRICK, DecoBlocks.sandstoneBrickSiding, DecoBlocks.sandstoneBrickMoulding,
				DecoBlocks.sandstoneBrickStairs, Block.blocksList[SlabHelper.SANDSTONE_BRICK_SLAB_ID], SlabHelper.SANDSTONE_BRICK_SLAB_TYPE);
		
		addSubBlockRecipes(Block.sandStone, SandHelper.SANDSTONE_TYPE_MOSSY, DecoBlocks.mossySandstoneSiding, DecoBlocks.mossySandstoneMoulding,
				DecoBlocks.mossySandstoneStairs, Block.blocksList[SlabHelper.MOSSY_SANDSTONE_SLAB_ID], SlabHelper.MOSSY_SANDSTONE_SLAB_TYPE);
		
		addSubBlockRecipes(Block.sandStone, SandHelper.SANDSTONE_TYPE_LARGE_BRICK, DecoBlocks.largeSandstoneBrickSiding, DecoBlocks.largeSandstoneBrickMoulding,
				DecoBlocks.largeSandstoneBrickStairs, Block.blocksList[SlabHelper.LARGE_SANDSTONE_BRICK_SLAB_ID], SlabHelper.LARGE_SANDSTONE_BRICK_SLAB_TYPE);
		
		addSubBlockRecipes(Block.sandStone, SandHelper.SANDSTONE_TYPE_LARGE_BRICK_MOSSY, DecoBlocks.mossyLargeSandstoneBrickSiding, DecoBlocks.mossyLargeSandstoneBrickMoulding,
				DecoBlocks.mossyLargeSandstoneBrickStairs, Block.blocksList[SlabHelper.MOSSY_LARGE_SANDSTONE_BRICK_SLAB_ID], SlabHelper.MOSSY_LARGE_SANDSTONE_BRICK_SLAB_TYPE);
		
		addSubBlockRecipes(Block.sandStone, SandHelper.SANDSTONE_TYPE_CRACKED, DecoBlocks.crackedSandstoneSiding, DecoBlocks.crackedSandstoneMoulding,
				DecoBlocks.crackedSandstoneStairs, Block.blocksList[SlabHelper.CRACKED_SANDSTONE_SLAB_ID], SlabHelper.CRACKED_SANDSTONE_SLAB_TYPE);
		
		addSubBlockRecipes(Block.sandStone, SandHelper.SANDSTONE_TYPE_LARGE_BRICK_CRACKED, DecoBlocks.crackedLargeSandstoneBrickSiding, DecoBlocks.crackedLargeSandstoneBrickMoulding,
				DecoBlocks.crackedLargeSandstoneBrickStairs, Block.blocksList[SlabHelper.CRACKED_LARGE_SANDSTONE_BRICK_SLAB_ID], SlabHelper.CRACKED_LARGE_SANDSTONE_BRICK_SLAB_TYPE);
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.sandstoneButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(Block.sandStone, 1, InventoryUtils.IGNORE_METADATA),
						'R', Item.redstone
				});
		
		//------ Red Sandstone ------//
		
		RecipeManager.addPistonPackingRecipe(DecoBlocks.redSandstone,
				new ItemStack(Block.sand, 2, SandHelper.RED_SAND_TYPE));
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.redSandstone, 4, SandHelper.SANDSTONE_TYPE_CUT), new Object[] {
				"##",
				"##",
				'#', new ItemStack(DecoBlocks.redSandstone, 1, SandHelper.SANDSTONE_TYPE_DEFAULT)});
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.redSandstone, 1, SandHelper.SANDSTONE_TYPE_POLISHED),
				new ItemStack[] {
					new ItemStack(DecoBlocks.redSandstone, 1, SandHelper.SANDSTONE_TYPE_DEFAULT)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.redSandstone, 4, SandHelper.SANDSTONE_TYPE_BRICK), new Object[] {
				"XX",
				"XX",
				'X', new ItemStack(DecoBlocks.redSandstone, 1, SandHelper.SANDSTONE_TYPE_POLISHED)});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.redSandstone, 4, SandHelper.SANDSTONE_TYPE_LARGE_BRICK), new Object[] {
				"XX",
				"XX",
				'X', new ItemStack(DecoBlocks.redSandstone, 1, SandHelper.SANDSTONE_TYPE_CUT)});
		
		addChiselingRecipe(new ItemStack(DecoBlocks.redSandstone, 1, SandHelper.SANDSTONE_TYPE_CHISELED),
				new ItemStack(DecoBlocks.redSandstone, 1, SandHelper.SANDSTONE_TYPE_DEFAULT));
		
		// Sub blocks
		addSubBlockRecipes(DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_DEFAULT, DecoBlocks.redSandstoneSiding, DecoBlocks.redSandstoneMoulding,
				DecoBlocks.redSandstoneStairs, Block.blocksList[SlabHelper.RED_SANDSTONE_SLAB_ID], SlabHelper.RED_SANDSTONE_SLAB_TYPE);
		
		addSubBlockRecipes(DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_CUT, DecoBlocks.cutRedSandstoneSiding, DecoBlocks.cutRedSandstoneMoulding,
				DecoBlocks.cutRedSandstoneStairs, Block.blocksList[SlabHelper.CUT_RED_SANDSTONE_SLAB_ID], SlabHelper.CUT_RED_SANDSTONE_SLAB_TYPE);
		
		addSubBlockRecipes(DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_POLISHED, DecoBlocks.polishedRedSandstoneSiding, DecoBlocks.polishedRedSandstoneMoulding,
				DecoBlocks.polishedRedSandstoneStairs, Block.blocksList[SlabHelper.POLISHED_RED_SANDSTONE_SLAB_ID], SlabHelper.POLISHED_RED_SANDSTONE_SLAB_TYPE);
		
		addSubBlockRecipes(DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_BRICK, DecoBlocks.redSandstoneBrickSiding, DecoBlocks.redSandstoneBrickMoulding,
				DecoBlocks.redSandstoneBrickStairs, Block.blocksList[SlabHelper.RED_SANDSTONE_BRICK_SLAB_ID], SlabHelper.RED_SANDSTONE_BRICK_SLAB_TYPE);
		
		addSubBlockRecipes(DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_MOSSY, DecoBlocks.mossyRedSandstoneSiding, DecoBlocks.mossyRedSandstoneMoulding,
				DecoBlocks.mossyRedSandstoneStairs, Block.blocksList[SlabHelper.MOSSY_RED_SANDSTONE_SLAB_ID], SlabHelper.MOSSY_RED_SANDSTONE_SLAB_TYPE);
		
		addSubBlockRecipes(DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_LARGE_BRICK, DecoBlocks.largeRedSandstoneBrickSiding, DecoBlocks.largeRedSandstoneBrickMoulding,
				DecoBlocks.largeRedSandstoneBrickStairs, Block.blocksList[SlabHelper.LARGE_RED_SANDSTONE_BRICK_SLAB_ID], SlabHelper.LARGE_RED_SANDSTONE_BRICK_SLAB_TYPE);
		
		addSubBlockRecipes(DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_LARGE_BRICK_MOSSY, DecoBlocks.mossyLargeRedSandstoneBrickSiding, DecoBlocks.mossyLargeRedSandstoneBrickMoulding,
				DecoBlocks.mossyLargeRedSandstoneBrickStairs, Block.blocksList[SlabHelper.MOSSY_LARGE_RED_SANDSTONE_BRICK_SLAB_ID], SlabHelper.MOSSY_LARGE_RED_SANDSTONE_BRICK_SLAB_TYPE);
		
		addSubBlockRecipes(DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_CRACKED, DecoBlocks.crackedRedSandstoneSiding, DecoBlocks.crackedRedSandstoneMoulding,
				DecoBlocks.crackedRedSandstoneStairs, Block.blocksList[SlabHelper.CRACKED_RED_SANDSTONE_SLAB_ID], SlabHelper.CRACKED_RED_SANDSTONE_SLAB_TYPE);
		
		addSubBlockRecipes(DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_LARGE_BRICK_CRACKED, DecoBlocks.crackedLargeRedSandstoneBrickSiding, DecoBlocks.crackedLargeRedSandstoneBrickMoulding,
				DecoBlocks.crackedLargeRedSandstoneBrickStairs, Block.blocksList[SlabHelper.CRACKED_LARGE_RED_SANDSTONE_BRICK_SLAB_ID], SlabHelper.CRACKED_LARGE_RED_SANDSTONE_BRICK_SLAB_TYPE);
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.redSandstoneButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(DecoBlocks.redSandstone, 1, InventoryUtils.IGNORE_METADATA),
						'R', Item.redstone
				});
	}
	
	private static void initNetherRecipes() {
		
		//------ General Nether Recipes ------//
		
		RecipeManager.addKilnRecipe(new ItemStack(DecoBlocks.magma), Block.netherrack);
		RecipeManager.addKilnRecipe(new ItemStack(DecoBlocks.magma), BTWBlocks.fallingNetherrack);
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.basalt, 1, 1), // Polished
				new ItemStack[] {
						new ItemStack(DecoBlocks.basalt, 1, 0) // Regular
				});
		
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.blockNetherQuartz, 1, 1),
				new Object[] {
						"#",
						"#",
						'#', new ItemStack(Block.stoneSingleSlab, 1, 7)
				});
		RecipeManager.addRecipe(new ItemStack(Block.blockNetherQuartz),
				new Object[] {
						"#",
						"#",
						'#', new ItemStack(Block.stoneSingleSlab, 1, 7)
				});
		
		addChiselingRecipe(new ItemStack(Block.blockNetherQuartz, 1, 1), new ItemStack(Block.blockNetherQuartz, 1, 0));
		
		//------ Nether Brick ------//
		
		RecipeManager.addKilnRecipe(new ItemStack(BTWBlocks.looseBrick), Block.netherBrick);
		RecipeManager.addKilnRecipe(new ItemStack(BTWBlocks.looseBrick), DecoBlocks.netherBrick, InventoryUtils.IGNORE_METADATA);
		RecipeManager.addKilnRecipe(new ItemStack(BTWBlocks.looseBrick), BTWBlocks.looseNetherBrick);
		RecipeManager.addKilnRecipe(new ItemStack(BTWBlocks.looseBrick), DecoBlocks.looseRedNetherBrick);
		
		// Red
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.netherBrick, 8, DecoNetherBrickBlock.RED_TYPE),
				new ItemStack[] {
						new ItemStack(Block.netherBrick),
						new ItemStack(Block.netherBrick),
						new ItemStack(Block.netherBrick),
						new ItemStack(Block.netherBrick),
						new ItemStack(Block.netherBrick),
						new ItemStack(Block.netherBrick),
						new ItemStack(Block.netherBrick),
						new ItemStack(Block.netherBrick),
						new ItemStack(BTWItems.hellfireDust)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.looseRedNetherBrick, 8),
				new ItemStack[] {
						new ItemStack(BTWBlocks.looseNetherBrick),
						new ItemStack(BTWBlocks.looseNetherBrick),
						new ItemStack(BTWBlocks.looseNetherBrick),
						new ItemStack(BTWBlocks.looseNetherBrick),
						new ItemStack(BTWBlocks.looseNetherBrick),
						new ItemStack(BTWBlocks.looseNetherBrick),
						new ItemStack(BTWBlocks.looseNetherBrick),
						new ItemStack(BTWBlocks.looseNetherBrick),
						new ItemStack(BTWItems.hellfireDust)
				});
		
		// Chiseled
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.netherBrick, 1, DecoNetherBrickBlock.CHISELED_TYPE),
				new ItemStack[] {
						new ItemStack(Block.netherBrick),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.netherBrick, 1, DecoNetherBrickBlock.CHISELED_TYPE),
				new ItemStack[] {
						new ItemStack(Block.netherBrick),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.netherBrick, 1, DecoNetherBrickBlock.RED_CHISELED_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.netherBrick, 1, DecoNetherBrickBlock.RED_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.netherBrick, 1, DecoNetherBrickBlock.RED_CHISELED_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.netherBrick, 1, DecoNetherBrickBlock.RED_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		// Sub blocks
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.netherBrick, DecoNetherBrickBlock.RED_TYPE,
				DecoBlocks.redNetherBrickSidingAndCorner, DecoBlocks.redNetherBrickMoulding, true);
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.redNetherBrickStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.netherBrick, 1, DecoNetherBrickBlock.RED_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.redNetherBrickStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.redNetherBrickMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.RED_NETHER_BRICK_SLAB_ID, 6, SlabHelper.RED_NETHER_BRICK_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.netherBrick, 1, DecoNetherBrickBlock.RED_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.netherBrick, 1, DecoNetherBrickBlock.RED_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.RED_NETHER_BRICK_SLAB_ID, 1, SlabHelper.RED_NETHER_BRICK_SLAB_TYPE)
				});
		
		// Loose
		RecipeManager.addRecipe(new ItemStack(SlabHelper.LOOSE_RED_NETHER_BRICK_SLAB_ID, 4, SlabHelper.LOOSE_RED_NETHER_BRICK_SLAB_TYPE),
				new Object[] {
						"FF",
						'F', new ItemStack(DecoBlocks.looseRedNetherBrick)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseRedNetherBrick, 1),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.LOOSE_RED_NETHER_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_RED_NETHER_BRICK_SLAB_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseRedNetherBrickStairs, 4),
				new Object[] {
						"F ",
						"FF",
						'F', new ItemStack(DecoBlocks.looseRedNetherBrick)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.looseRedNetherBrickStairs, 2),
				new Object[] {
						"S ",
						"SS",
						'S', new ItemStack(SlabHelper.LOOSE_RED_NETHER_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_RED_NETHER_BRICK_SLAB_TYPE)
				});
		
		// Mortaring
		// Clay
		RecipeManager.addStokedCauldronRecipe(new ItemStack(Block.netherBrick),
				new ItemStack[] {
						new ItemStack(BTWBlocks.looseNetherBrick),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.netherBrick, 1, DecoNetherBrickBlock.RED_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseRedNetherBrick),
						new ItemStack(Item.clay)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(Block.stairsNetherBrick),
				new ItemStack[] {
						new ItemStack(BTWBlocks.looseNEtherBrickStairs),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.redNetherBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseRedNetherBrickStairs),
						new ItemStack(Item.clay)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(Block.stoneDoubleSlab, 1, 6), // Nether brick slab
				new ItemStack[] {
						new ItemStack(BTWBlocks.looseNetherBrickSlab),
						new ItemStack(Item.clay)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.LOOSE_RED_NETHER_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_RED_NETHER_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.RED_NETHER_BRICK_SLAB_ID, 1, SlabHelper.RED_NETHER_BRICK_SLAB_TYPE),
						new ItemStack(Item.clay)
				});
		
		// Slime
		RecipeManager.addStokedCauldronRecipe(new ItemStack(Block.netherBrick),
				new ItemStack[] {
						new ItemStack(BTWBlocks.looseNetherBrick),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.netherBrick, 1, DecoNetherBrickBlock.RED_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseRedNetherBrick),
						new ItemStack(Item.slimeBall)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(Block.stairsNetherBrick),
				new ItemStack[] {
						new ItemStack(BTWBlocks.looseNEtherBrickStairs),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.redNetherBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseRedNetherBrickStairs),
						new ItemStack(Item.slimeBall)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(Block.stoneDoubleSlab, 1, 6), // Nether brick slab
				new ItemStack[] {
						new ItemStack(BTWBlocks.looseNetherBrickSlab),
						new ItemStack(Item.slimeBall)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.LOOSE_RED_NETHER_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_RED_NETHER_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.RED_NETHER_BRICK_SLAB_ID, 1, SlabHelper.RED_NETHER_BRICK_SLAB_TYPE),
						new ItemStack(Item.slimeBall)
				});
		
		// Nether sludge
		RecipeManager.addStokedCauldronRecipe(new ItemStack(Block.netherBrick),
				new ItemStack[] {
						new ItemStack(BTWBlocks.looseNetherBrick),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.netherBrick, 1, DecoNetherBrickBlock.RED_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseRedNetherBrick),
						new ItemStack(BTWItems.netherSludge)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(Block.stairsNetherBrick),
				new ItemStack[] {
						new ItemStack(BTWBlocks.looseNEtherBrickStairs),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(DecoBlocks.redNetherBrickStairs),
				new ItemStack[] {
						new ItemStack(DecoBlocks.looseRedNetherBrickStairs),
						new ItemStack(BTWItems.netherSludge)
				});
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(Block.stoneDoubleSlab, 1, 6), // Nether brick slab
				new ItemStack[] {
						new ItemStack(BTWBlocks.looseNetherBrickSlab),
						new ItemStack(BTWItems.netherSludge)
				});
		RecipeManager.addStokedCauldronRecipe(new ItemStack(SlabHelper.LOOSE_RED_NETHER_BRICK_SLAB_ID, 1, SlabHelper.LOOSE_RED_NETHER_BRICK_SLAB_TYPE),
				new ItemStack[] {
						new ItemStack(SlabHelper.RED_NETHER_BRICK_SLAB_ID, 1, SlabHelper.RED_NETHER_BRICK_SLAB_TYPE),
						new ItemStack(BTWItems.netherSludge)
				});
		
		//------ Infused Stone ------//
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.infusedStone),
				new ItemStack[] {
						new ItemStack(DecoBlocks.basalt),
						new ItemStack(BTWItems.soulFlux)
				});
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_POLISHED),
				new ItemStack[] {
						new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_DEFAULT)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_BRICK),
				new Object[] {
						"FF",
						"FF",
						'F', new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_DEFAULT)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_CHISELED),
				new ItemStack[] {
						new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_BRICK),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_CHISELED),
				new ItemStack[] {
						new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_BRICK),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		// Sub blocks
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.infusedStone, InfusedStoneBlock.TYPE_DEFAULT,
				DecoBlocks.infusedStoneSidingAndCorner, DecoBlocks.infusedStoneMoulding, true);
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.infusedStoneStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_DEFAULT)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.infusedStoneStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.infusedStoneMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.INFUSED_STONE_SLAB_ID, 6, SlabHelper.INFUSED_STONE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_DEFAULT)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_DEFAULT),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.INFUSED_STONE_SLAB_ID, 1, SlabHelper.INFUSED_STONE_SLAB_TYPE)
				});
		
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.infusedStone, InfusedStoneBlock.TYPE_POLISHED,
				DecoBlocks.polishedInfusedStoneSidingAndCorner, DecoBlocks.polishedInfusedStoneMoulding, true);
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedInfusedStoneStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_BRICK)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.polishedInfusedStoneStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.infusedStoneMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.POLISHED_INFUSED_STONE_SLAB_ID, 6, SlabHelper.POLISHED_INFUSED_STONE_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_BRICK)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_BRICK),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.POLISHED_INFUSED_STONE_SLAB_ID, 1, SlabHelper.POLISHED_INFUSED_STONE_SLAB_TYPE)
				});
		
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.infusedStone, InfusedStoneBlock.TYPE_BRICK,
				DecoBlocks.infusedStoneBrickSidingAndCorner, DecoBlocks.infusedStoneBrickMoulding, true);
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.infusedStoneBrickStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_BRICK)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.infusedStoneBrickStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.infusedStoneBrickMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.INFUSED_STONE_BRICK_SLAB_ID, 6, SlabHelper.INFUSED_STONE_BRICK_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_BRICK)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.infusedStone, 1, InfusedStoneBlock.TYPE_BRICK),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.INFUSED_STONE_BRICK_SLAB_ID, 1, SlabHelper.INFUSED_STONE_BRICK_SLAB_TYPE)
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.infusedStoneButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(DecoBlocks.infusedStone, 1, InventoryUtils.IGNORE_METADATA),
						'R', Item.redstone
				});
	}
	
	private static void initEndRecipes() {
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.endStoneBrick, 4),
				new Object[] {
						"EE",
						"EE",
						'E', new ItemStack(Block.whiteStone)
				});
		
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.endStoneBrick, 0, DecoBlocks.endStoneBrickSidingAndCorner,
				DecoBlocks.endStoneBrickMoulding, true);
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.endStoneBrickStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.endStoneBrick)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.endStoneBrickStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.endStoneBrickMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.END_STONE_BRICK_SLAB_ID, 6, SlabHelper.END_STONE_BRICK_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.endStoneBrick)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.endStoneBrick),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.END_STONE_BRICK_SLAB_ID, 1, SlabHelper.END_STONE_BRICK_SLAB_TYPE)
				});
	}
	
	private static void initTerracottaRecipes() {
		
		//------ Terracotta ------//
		
		// Unfired terracotta
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.unfiredTerracotta),
				new ItemStack[] {
						new ItemStack(Block.sand, 1, InventoryUtils.IGNORE_METADATA),
						new ItemStack(Item.clay)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.unfiredTerracotta),
				new ItemStack[] {
						new ItemStack(Block.sand, 1, InventoryUtils.IGNORE_METADATA),
						new ItemStack(BTWItems.netherSludge)
				});
		
		// Terracotta
		
		RecipeManager.addKilnRecipe(new ItemStack(DecoBlocks.terracotta), DecoBlocks.unfiredTerracotta);
		
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.terracotta, 0, DecoBlocks.terracottaSidingAndCorner,
				DecoBlocks.terracottaMoulding, true);
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.terracottaStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.terracotta)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.terracottaStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.terracottaMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.TERRACOTTA_SLAB_ID, 6, SlabHelper.TERRACOTTA_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.terracotta)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.terracotta),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.TERRACOTTA_SLAB_ID, 1, SlabHelper.TERRACOTTA_SLAB_TYPE)
				});
		
		// Stained terracotta
		for (int color = 0; color < 16; color++) {
			RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.stainedTerracotta, 8, color),
					new ItemStack[] {
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(Item.dyePowder, 1, color)
					});
			RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.stainedTerracotta, 8, color),
					new ItemStack[] {
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(DecoBlocks.terracotta),
							new ItemStack(Item.dyePowder, 1, color + 16)
					});
			
			btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.stainedTerracotta, color, DecoBlocks.stainedTerracottaSidingAndCorner[color],
					DecoBlocks.stainedTerracottaMoulding[color], true);
			
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.stainedTerracottaStairs[color], 6),
					new Object[] {
							"F  ",
							"FF ",
							"FFF",
							'F', new ItemStack(DecoBlocks.stainedTerracotta, 1, color)
					});
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.stainedTerracottaStairs[color], 1),
					new Object[] {
							"M ",
							"MM",
							'M', new ItemStack(DecoBlocks.stainedTerracottaMoulding[color])
					});
		}
		
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.stainedTerracotta, 8, ColorUtils.BROWN.colorID),
				new ItemStack[] {
						new ItemStack(DecoBlocks.terracotta),
						new ItemStack(DecoBlocks.terracotta),
						new ItemStack(DecoBlocks.terracotta),
						new ItemStack(DecoBlocks.terracotta),
						new ItemStack(DecoBlocks.terracotta),
						new ItemStack(DecoBlocks.terracotta),
						new ItemStack(DecoBlocks.terracotta),
						new ItemStack(DecoBlocks.terracotta),
						new ItemStack(BTWItems.dung)
				});
		
		for (int color = 0; color < 8; color++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.stainedTerracottaSlab, 6, color),
					new Object[] {
							"CCC",
							'C', new ItemStack(DecoBlocks.stainedTerracotta, 1, color)
					});
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.stainedTerracottaSlab2, 6, color),
					new Object[] {
							"CCC",
							'C', new ItemStack(DecoBlocks.stainedTerracotta, 1, color + 8)
					});
			
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.stainedTerracotta, 1, color),
					new Object[] {
							"S",
							"S",
							'S', new ItemStack(DecoBlocks.stainedTerracottaSlab, 1, color)
					});
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.stainedTerracotta, 1, color + 8),
					new Object[] {
							"S",
							"S",
							'S', new ItemStack(DecoBlocks.stainedTerracottaSlab2, 1, color)
					});
		}
		
		//------ Shingles ------//
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.shingles),
				new ItemStack[] {
						new ItemStack(DecoBlocks.terracotta),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.shingles),
				new ItemStack[] {
						new ItemStack(DecoBlocks.terracotta),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.shingles, 0, DecoBlocks.shinglesSidingAndCorner,
				DecoBlocks.shinglesMoulding, true);
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.shinglesStairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(DecoBlocks.shingles)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.shinglesStairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(DecoBlocks.shinglesMoulding)
				});
		
		RecipeManager.addRecipe(new ItemStack(SlabHelper.SHINGLES_SLAB_ID, 6, SlabHelper.SHINGLES_SLAB_TYPE),
				new Object[] {
						"FFF",
						'F', new ItemStack(DecoBlocks.shingles)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.shingles),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(SlabHelper.SHINGLES_SLAB_ID, 1, SlabHelper.SHINGLES_SLAB_TYPE)
				});
		
		// Colored shingles
		for (int color = 0; color < 16; color++) {
			RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.coloredShingles, 8, color),
					new ItemStack[] {
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(Item.dyePowder, 1, color)
					});
			RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.coloredShingles, 8, color),
					new ItemStack[] {
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(DecoBlocks.shingles),
							new ItemStack(Item.dyePowder, 1, color + 16)
					});
			
			RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.coloredShingles, 1, color),
					new ItemStack[] {
							new ItemStack(DecoBlocks.stainedTerracotta, 1, color),
							new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
					});
			RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.coloredShingles, 1, color),
					new ItemStack[] {
							new ItemStack(DecoBlocks.stainedTerracotta, 1, color),
							new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
					});
			
			btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.coloredShingles, color, DecoBlocks.coloredShinglesSidingAndCorner[color],
					DecoBlocks.coloredShinglesMoulding[color], true);
			
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.coloredShinglesStairs[color], 6),
					new Object[] {
							"F  ",
							"FF ",
							"FFF",
							'F', new ItemStack(DecoBlocks.coloredShingles, 1, color)
					});
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.coloredShinglesStairs[color], 1),
					new Object[] {
							"M ",
							"MM",
							'M', new ItemStack(DecoBlocks.coloredShinglesMoulding[color])
					});
		}
		
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.coloredShingles, 8, ColorUtils.BROWN.colorID),
				new ItemStack[] {
						new ItemStack(DecoBlocks.shingles),
						new ItemStack(DecoBlocks.shingles),
						new ItemStack(DecoBlocks.shingles),
						new ItemStack(DecoBlocks.shingles),
						new ItemStack(DecoBlocks.shingles),
						new ItemStack(DecoBlocks.shingles),
						new ItemStack(DecoBlocks.shingles),
						new ItemStack(DecoBlocks.shingles),
						new ItemStack(BTWItems.dung)
				});
		
		for (int color = 0; color < 8; color++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.coloredShinglesSlab, 6, color),
					new Object[] {
							"CCC",
							'C', new ItemStack(DecoBlocks.coloredShingles, 1, color)
					});
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.coloredShinglesSlab2, 6, color),
					new Object[] {
							"CCC",
							'C', new ItemStack(DecoBlocks.coloredShingles, 1, color + 8)
					});
			
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.coloredShingles, 1, color),
					new Object[] {
							"S",
							"S",
							'S', new ItemStack(DecoBlocks.coloredShinglesSlab, 1, color)
					});
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.coloredShingles, 1, color + 8),
					new Object[] {
							"S",
							"S",
							'S', new ItemStack(DecoBlocks.coloredShinglesSlab2, 1, color)
					});
		}
		
		//------ Glazed Terracotta ------//
		
		for (int color = 0; color < 16; color++) {
			RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.glazedTerracotta[color]),
					new ItemStack[] {
							new ItemStack(DecoBlocks.stainedTerracotta, 1, color)
					});
			
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.glazedTerracottaPillars[color]),
					new Object[] {
							"G",
							"G",
							'G', DecoBlocks.glazedTerracotta[color]
					});
		}
	}
	
	private static void initConcreteRecipes() {
		for (int color = 0; color < 16; color++) {
			RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.concretePowder, 8, color),
					new ItemStack[] {
							new ItemStack(Block.sand, 4, InventoryUtils.IGNORE_METADATA),
							new ItemStack(Block.gravel, 4),
							new ItemStack(Item.dyePowder, 1, color)
					});
			RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.concretePowder, 8, color),
					new ItemStack[] {
							new ItemStack(Block.sand, 4, InventoryUtils.IGNORE_METADATA),
							new ItemStack(Block.gravel, 4),
							new ItemStack(Item.dyePowder, 1, color + 16)
					});
			
			btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(DecoBlocks.concrete, color, DecoBlocks.concreteSidingAndCorner[color],
					DecoBlocks.concreteMoulding[color], true);
			
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.concreteStairs[color], 6),
					new Object[] {
							"F  ",
							"FF ",
							"FFF",
							'F', new ItemStack(DecoBlocks.concrete, 1, color)
					});
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.concreteStairs[color], 1),
					new Object[] {
							"M ",
							"MM",
							'M', new ItemStack(DecoBlocks.concreteMoulding[color])
					});
		}
		
		RecipeManager.addStokedCrucibleRecipe(new ItemStack(DecoBlocks.concretePowder, 8, ColorUtils.BROWN.colorID),
				new ItemStack[] {
						new ItemStack(Block.sand, 4, InventoryUtils.IGNORE_METADATA),
						new ItemStack(Block.gravel, 4),
						new ItemStack(BTWItems.dung)
				});
		
		for (int color = 0; color < 8; color++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.concreteSlab, 6, color),
					new Object[] {
							"CCC",
							'C', new ItemStack(DecoBlocks.concrete, 1, color)
					});
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.concreteSlab2, 6, color),
					new Object[] {
							"CCC",
							'C', new ItemStack(DecoBlocks.concrete, 1, color + 8)
					});
			
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.concrete, 1, color),
					new Object[] {
							"S",
							"S",
							'S', new ItemStack(DecoBlocks.concreteSlab, 1, color)
					});
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.concrete, 1, color + 8),
					new Object[] {
							"S",
							"S",
							'S', new ItemStack(DecoBlocks.concreteSlab2, 1, color)
					});
		}
	}
	
	private static void initSoilRecipes() {
		
		//------ Red Sand ------//
		
		RecipeManager.addShapelessRecipe(new ItemStack(Block.sand, 8, SandHelper.RED_SAND_TYPE),
				new ItemStack[] {
						new ItemStack(Block.sand),
						new ItemStack(Block.sand),
						new ItemStack(Block.sand),
						new ItemStack(Block.sand),
						new ItemStack(Block.sand),
						new ItemStack(Block.sand),
						new ItemStack(Block.sand),
						new ItemStack(Block.sand),
						new ItemStack(Item.dyePowder, 1, ColorUtils.RED.colorID)
				});
		
		RecipeManager.addPistonPackingRecipe(Block.sand, SandHelper.RED_SAND_TYPE,
				new ItemStack(BTWItems.sandPile, 8, SandHelper.RED_SAND_TYPE));
		
		RecipeManager.addShapelessRecipe(new ItemStack(Block.sand, 1, SandHelper.RED_SAND_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sandPile, 1, SandHelper.RED_SAND_TYPE),
						new ItemStack(BTWItems.sandPile, 1, SandHelper.RED_SAND_TYPE),
						new ItemStack(BTWItems.sandPile, 1, SandHelper.RED_SAND_TYPE),
						new ItemStack(BTWItems.sandPile, 1, SandHelper.RED_SAND_TYPE),
						new ItemStack(BTWItems.sandPile, 1, SandHelper.RED_SAND_TYPE),
						new ItemStack(BTWItems.sandPile, 1, SandHelper.RED_SAND_TYPE),
						new ItemStack(BTWItems.sandPile, 1, SandHelper.RED_SAND_TYPE),
						new ItemStack(BTWItems.sandPile, 1, SandHelper.RED_SAND_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.sandPile, 8, SandHelper.RED_SAND_TYPE),
				new ItemStack[] {
						new ItemStack(Block.sand, 1, SandHelper.RED_SAND_TYPE)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.sandPile, 4, SandHelper.RED_SAND_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.redSandSlab)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.redSandSlab, 4),
				new Object[] {
						"FF",
						'F', new ItemStack(Block.sand, 1, SandHelper.RED_SAND_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.redSandSlab, 4),
				new Object[] {
						"PP",
						"PP",
						'P', new ItemStack(BTWItems.sandPile, 1, SandHelper.RED_SAND_TYPE)
				});
		
		RecipeManager.addRecipe(new ItemStack(Block.sand, 1, SandHelper.RED_SAND_TYPE),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(DecoBlocks.redSandSlab)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(Block.sand, 1, SandHelper.RED_SAND_TYPE), new ItemStack[] {new ItemStack(DecoBlocks.legacyRedSand)});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.sandPile, 1, SandHelper.RED_SAND_TYPE), new ItemStack[] {new ItemStack(DecoItems.legacyRedSandPile)});
		
		//------ Mud ------//
		
		RecipeManager.addPistonPackingRecipe(DecoBlocks.packedMud,
				new ItemStack(DecoBlocks.mud, 2));
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mudBrick, 4),
				new Object[] {
						"FF",
						"FF",
						'F', new ItemStack(DecoBlocks.packedMud)
				});
		
		addSubBlockRecipes(DecoBlocks.mudBrick, 0, DecoBlocks.mudBrickSidingAndCorner, DecoBlocks.mudBrickMoulding, DecoBlocks.mudBrickStairs,
				SlabHelper.MUD_BRICK_SLAB_ID, SlabHelper.MUD_BRICK_SLAB_TYPE);
		
		//------ Coarse Dirt ------//
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.coarseDirt, 2),
				new ItemStack[] {
						new ItemStack(Block.dirt),
						new ItemStack(Block.gravel)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.coarseDirtSlab, 4),
				new Object[] {
						"FF",
						'F', new ItemStack(DecoBlocks.coarseDirt)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.coarseDirt),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(DecoBlocks.coarseDirtSlab)
				});
		
		//------ Podzol ------//
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.podzol, 8),
				new ItemStack[] {
						new ItemStack(DecoBlocks.coarseDirt),
						new ItemStack(DecoBlocks.coarseDirt),
						new ItemStack(DecoBlocks.coarseDirt),
						new ItemStack(DecoBlocks.coarseDirt),
						new ItemStack(DecoBlocks.coarseDirt),
						new ItemStack(DecoBlocks.coarseDirt),
						new ItemStack(DecoBlocks.coarseDirt),
						new ItemStack(DecoBlocks.coarseDirt),
						new ItemStack(Block.leaves, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
	}
	
	private static void initPlantRecipes() {
		
		//------ Sapling recipes ------//
		
		// Cherry
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.cherrySapling),
				new ItemStack[] {
						new ItemStack(Block.sapling, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						new ItemStack(Item.dyePowder, 1, ColorUtils.PINK.colorID),
						new ItemStack(BTWItems.dung)
				});
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.cherrySapling),
				new ItemStack[] {
						new ItemStack(Block.sapling, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						new ItemStack(Item.dyePowder, 1, ColorUtils.PINK.colorID),
						new ItemStack(DecoItems.fertilizer)
				});
		
		// Acacia
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.acaciaSapling),
				new ItemStack[] {
						new ItemStack(Block.sapling, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						new ItemStack(Item.dyePowder, 1, ColorUtils.ORANGE.colorID),
						new ItemStack(BTWItems.dung)
				});
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.acaciaSapling),
				new ItemStack[] {
						new ItemStack(Block.sapling, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						new ItemStack(Item.dyePowder, 1, ColorUtils.ORANGE.colorID),
						new ItemStack(DecoItems.fertilizer)
				});
		
		// Mahogany
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.mahoganySapling),
				new ItemStack[] {
						new ItemStack(Block.sapling, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						new ItemStack(Item.dyePowder, 1, ColorUtils.RED.colorID),
						new ItemStack(BTWItems.dung)
				});
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.mahoganySapling),
				new ItemStack[] {
						new ItemStack(Block.sapling, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						new ItemStack(Item.dyePowder, 1, ColorUtils.RED.colorID),
						new ItemStack(DecoItems.fertilizer)
				});
		
		// Mangrove
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.mangroveSapling),
				new ItemStack[] {
						new ItemStack(Block.sapling, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						new ItemStack(Item.dyePowder, 1, ColorUtils.ORANGE.colorID),
						new ItemStack(BTWItems.dung)
				});
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.mangroveSapling),
				new ItemStack[] {
						new ItemStack(Block.sapling, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						new ItemStack(Item.dyePowder, 1, ColorUtils.ORANGE.colorID),
						new ItemStack(DecoItems.fertilizer)
				});
		
		// Hazel
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.hazelSapling),
				new ItemStack[] {
						new ItemStack(Block.sapling, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						new ItemStack(Item.dyePowder, 1, ColorUtils.BROWN.colorID),
						new ItemStack(BTWItems.dung)
				});
		RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.hazelSapling),
				new ItemStack[] {
						new ItemStack(Block.sapling, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						new ItemStack(Item.dyePowder, 1, ColorUtils.BROWN.colorID),
						new ItemStack(DecoItems.fertilizer)
				});
		
		//------ Flower Recipes ------//
		
		RecipeManager.removeVanillaRecipe(new ItemStack(Item.flowerPot, 1), new Object[] {
				"# #",
				" # ",
				'#', Item.brick});
		
		RecipeManager.addShapelessRecipe(new ItemStack(Item.flowerPot, 1), new ItemStack[]{
				new ItemStack(BTWItems.dirtPile),
				new ItemStack(BTWItems.urn)});
		
		
		// Yucca
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.LIME.colorID),
				new ItemStack(DecoBlocks.flower, 1, 0));
		
		// Hyacinth
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.LIGHT_BLUE.colorID),
				new ItemStack(DecoBlocks.flower, 1, 1));
		
		// Birds of Paradise
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.ORANGE.colorID),
				new ItemStack(DecoBlocks.flower, 1, 2));
		
		// Azalea
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.PINK.colorID),
				new ItemStack(DecoBlocks.flower, 1, 3));
		
		// Cornflower
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.BLUE.colorID + 16),
				new ItemStack(DecoBlocks.flower, 1, 4));
		
		// Lavender
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.PURPLE.colorID),
				new ItemStack(DecoBlocks.flower, 1, 5));
		
		// Honeysuckle
		RecipeManager.addMillStoneRecipe(new ItemStack[]{
						new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16),
						new ItemStack(Item.dyePowder, 1, ColorUtils.YELLOW.colorID)
				},
				new ItemStack[] {
						new ItemStack(DecoBlocks.flower, 1, 6)
				});
		
		// Allium
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.MAGENTA.colorID),
				new ItemStack(DecoBlocks.flower, 1, 7));
		
		// Blue Orchid
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.LIGHT_BLUE.colorID),
				new ItemStack(DecoBlocks.flower, 1, 8));
		
		// Poppy
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.RED.colorID),
				new ItemStack(DecoBlocks.flower, 1, 9));
		
		// Azure Bluet
		RecipeManager.addMillStoneRecipe(new ItemStack[] {
						new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16),
						new ItemStack(Item.dyePowder, 1, ColorUtils.YELLOW.colorID)
				},
				new ItemStack[] {
						new ItemStack(DecoBlocks.flower, 1, 10)
				});
		
		// Daisy
		RecipeManager.addMillStoneRecipe(new ItemStack[] {
						new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16),
						new ItemStack(Item.dyePowder, 1, ColorUtils.YELLOW.colorID)
				},
				new ItemStack[]{
						new ItemStack(DecoBlocks.flower, 1, 11)
				});
		
		// Peony
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.MAGENTA.colorID),
				new ItemStack(DecoBlocks.flower, 1, 12));
		
		// Lilac
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.MAGENTA.colorID),
				new ItemStack(DecoBlocks.flower, 1, 13));
		
		// Rose Bush
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 3, ColorUtils.RED.colorID),
				new ItemStack(DecoBlocks.flower, 1, 14));
		
		// Blue Rose
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.BLUE.colorID + 16),
				new ItemStack(DecoBlocks.flower, 1, 15));
		
		//Black Rose
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.BLACK.colorID + 16),
				new ItemStack(DecoBlocks.flower2, 1, 0));
		
		//Lily of the valley
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.WHITE.colorID + 16),
				new ItemStack(DecoBlocks.flower2, 1, 1));
		
		// Tulips
		RecipeManager.addMillStoneRecipe(new ItemStack[] {
						new ItemStack(Item.dyePowder, 1, ColorUtils.ORANGE.colorID),
						new ItemStack(Item.dyePowder, 1, ColorUtils.RED.colorID)
				},
				new ItemStack[]{
						new ItemStack(DecoBlocks.tulip, 1, 0)
				});
		
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.PINK.colorID),
				new ItemStack(DecoBlocks.tulip, 1, 1));
		
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.ORANGE.colorID),
				new ItemStack(DecoBlocks.tulip, 1, 2));
		
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.WHITE.colorID + 16),
				new ItemStack(DecoBlocks.tulip, 1, 3));
		
		RecipeManager.addMillStoneRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.BLUE.colorID + 16),
				new ItemStack(DecoBlocks.tulip, 1, 4));
		
		// Vanilla Dyeing
		for (int color = 0; color < 16; color++) {
			if (color != ColorUtils.WHITE.colorID) {
				RecipeManager.addCauldronRecipe(new ItemStack(Block.cloth, 8, BlockCloth.getDyeFromBlock(color)),
						new ItemStack[] {
								new ItemStack(Item.dyePowder, 1, color + 16),
								new ItemStack(Block.cloth, 8, ColorUtils.WHITE.colorID)
						});
				
				RecipeManager.addCauldronRecipe(new ItemStack(BTWItems.wool, 32, color),
						new ItemStack[] {
								new ItemStack(Item.dyePowder, 1, color + 16),
								new ItemStack(BTWItems.wool, 32, ColorUtils.WHITE.colorID)
						});
				
				RecipeManager.addShapelessRecipe(new ItemStack(BTWBlocks.vase, 1, color),
						new ItemStack[] {
								new ItemStack(Item.dyePowder, 1, color + 16),
								new ItemStack(BTWBlocks.vase, 1, ColorUtils.WHITE.colorID)
						});
			}
			
			RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.candle, 1, color),
					new ItemStack[] {
							new ItemStack(Item.dyePowder, 1, color + 16),
							new ItemStack(BTWItems.candle, 1, 16) // Plain candle
					});
		}
		
		//Mixing dyes
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.LIME.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.GREEN.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 15)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.PINK.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.RED.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.CYAN.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLUE.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.GREEN.colorID)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.PURPLE.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLUE.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.RED.colorID)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.LIGHT_BLUE.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLUE.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID)});
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.LIGHT_BLUE.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLUE.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.LIGHT_GRAY.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.GRAY.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16)});
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 3, ColorUtils.LIGHT_GRAY.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLACK.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID)});
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 3, ColorUtils.LIGHT_GRAY.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLACK.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16)});
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 3, ColorUtils.LIGHT_GRAY.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLACK.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID)});
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 3, ColorUtils.LIGHT_GRAY.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLACK.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID)});
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 3, ColorUtils.LIGHT_GRAY.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLACK.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.GRAY.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLACK.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16)});
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.GRAY.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLACK.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16)});
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, ColorUtils.GRAY.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLACK.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID)});
		
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 3, ColorUtils.MAGENTA.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLUE.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.RED.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.PINK.colorID)});
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 4, ColorUtils.MAGENTA.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLUE.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.RED.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.RED.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID)});
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 4, ColorUtils.MAGENTA.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLUE.colorID + 16),
				new ItemStack(Item.dyePowder, 1, ColorUtils.RED.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.RED.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16)});
		RecipeManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 4, ColorUtils.MAGENTA.colorID), new Object[] {
				new ItemStack(Item.dyePowder, 1, ColorUtils.BLUE.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.RED.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.RED.colorID),
				new ItemStack(Item.dyePowder, 1, ColorUtils.WHITE.colorID + 16)});
	}
	
	private static void initWoodTypeRecipes() {
		
		//------ General Wood ------//
		
		// Trapdoors
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.trapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', Block.planks
				});
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.trapdoor, 2),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(BTWItems.woodSidingStubID, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		// Doors
		RecipeManager.removeVanillaRecipe(new ItemStack(Item.doorWood),
				new Object[] {
						"##",
						"##",
						"##",
						'#', Block.planks
				});
		RecipeManager.removeVanillaRecipe(new ItemStack(Item.doorWood),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		// Fence gates
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.fenceGate),
				new Object[] {
						"#W#",
						"#W#",
						'#', Item.stick,
						'W', Block.planks
				});
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.fenceGate),
				new Object[] {
						"#X#",
						'#', new ItemStack(BTWItems.woodMouldingStubID, 1, InventoryUtils.IGNORE_METADATA),
						'X', new ItemStack(BTWItems.woodSidingStubID, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		// Signs
		RecipeManager.removeVanillaRecipe(new ItemStack(Item.sign, 3),
				new Object[] {
						"###",
						"###",
						" X ",
						'#', Block.planks,
						'X', Item.stick
				});
		RecipeManager.removeVanillaRecipe(new ItemStack(Item.sign, 3),
				new Object[] {
						"#",
						"X",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, InventoryUtils.IGNORE_METADATA),
						'X', new ItemStack(BTWItems.woodMouldingStubID, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		for (int woodType = 0; woodType < WoodTypeHelper.NUM_TOTAL_WOOD; woodType++) {
			RecipeManager.addRecipe(new ItemStack(Item.sign, 3, woodType),
					new Object[] {
							"###",
							"###",
							" X ",
							'#', new ItemStack(Block.planks, 1, woodType),
							'X', Item.stick
					});
			RecipeManager.addRecipe(new ItemStack(Item.sign, 3, woodType),
					new Object[] {
							"#",
							"X",
							'#', new ItemStack(BTWItems.woodSidingStubID, 1, woodType),
							'X', new ItemStack(BTWItems.woodMouldingStubID, 1, woodType)
					});
		}
		
		// Barrels
		RecipeManager.removeVanillaRecipe(new ItemStack(BTWBlocks.aestheticOpaque, 2, AestheticOpaqueBlock.SUBTYPE_BARREL),
				new Object[] {
						"###",
						"#X#",
						"###",
						'#', new ItemStack(BTWItems.woodMouldingStubID, 1, InventoryUtils.IGNORE_METADATA),
						'X', BTWItems.glue
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.barrel, 1, WoodTypeHelper.SPRUCE_BARREL_TYPE),
				new ItemStack[] {
						new ItemStack(BTWBlocks.aestheticOpaque, 2, AestheticOpaqueBlock.SUBTYPE_BARREL)
				});
		
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.tnt, 1),
				new Object[] {
						"GFG",
						"GBG",
						"GGG",
						'B', new ItemStack(BTWBlocks.aestheticOpaque, 2, AestheticOpaqueBlock.SUBTYPE_BARREL),
						'G', Item.gunpowder,
						'F', BTWItems.fuse
				});
		RecipeManager.addRecipe(new ItemStack(Block.tnt, 1),
				new Object[] {
						"GFG",
						"GBG",
						"GgG",
						'B', new ItemStack(DecoBlocks.barrel, 1, InventoryUtils.IGNORE_METADATA),
						'G', Item.gunpowder,
						'F', BTWItems.fuse,
						'g', BTWItems.glue
				});
		RecipeManager.addRecipe(new ItemStack(Block.tnt, 1),
				new Object[] {
						"GFG",
						"GBG",
						"GgG",
						'B', new ItemStack(DecoBlocks.barrel2, 1, InventoryUtils.IGNORE_METADATA),
						'G', Item.gunpowder,
						'F', BTWItems.fuse,
						'g', BTWItems.glue
				});
		
		// Bookshelves
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.bookShelf),
				new Object[] {
						"###",
						"XXX",
						"###",
						'#', new ItemStack(Block.planks, 1, InventoryUtils.IGNORE_METADATA),
						'X', Item.book
				});
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.bookShelf),
				new Object[] {
						"###",
						"XXX",
						"###",
						'#', new ItemStack(BTWItems.woodMouldingStubID, 1, InventoryUtils.IGNORE_METADATA),
						'X', Item.book
				});
		RecipeManager.removeVanillaShapelessRecipe(new ItemStack(Item.book, 3),
				new ItemStack[] {
						new ItemStack(Block.bookShelf)
				});
		
		// Crates
		for (int woodType = 0; woodType < WoodTypeHelper.NUM_TOTAL_WOOD; woodType++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.crate, 1, woodType),
					new Object[] {
							" S ",
							"S S",
							" S ",
							'S', new ItemStack(BTWItems.woodSidingStubID, 1, woodType)
					});
		}
		
		//Note block
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.music, 1),
				new Object[] {
						"###",
						"#X#",
						"###",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, InventoryUtils.IGNORE_METADATA),
						'X', BTWItems.redstoneLatch
				});
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.music, 1),
				new Object[] {
						"###",
						"#X#",
						"###",
						'#', Block.planks,
						'X', BTWItems.redstoneLatch
				});
		
		CrucibleCraftingManager.getInstance().removeRecipe(new ItemStack(Item.goldNugget, 2),
				new ItemStack[] {
						new ItemStack(Block.music)
				});
		
		RecipeManager.addRecipe(new ItemStack(Block.music, 1),
				new Object[] {
						"###",
						"#X#",
						"###",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, InventoryUtils.IGNORE_METADATA),
						'X', Item.redstone
				});
		RecipeManager.addRecipe(new ItemStack(Block.music, 1),
				new Object[] {
						"###",
						"#X#",
						"###",
						'#', Block.planks,
						'X', Item.redstone});
		
		// Tanning
		for (int woodType = WoodTypeHelper.NUM_VANILLA_WOOD; woodType < WoodTypeHelper.NUM_TOTAL_WOOD; woodType++) {
			RecipeManager.addCauldronRecipe(new ItemStack(BTWItems.tannedLeather),
					new ItemStack[] {
							new ItemStack(BTWItems.scouredLeather),
							new ItemStack(BTWItems.dung),
							new ItemStack(BTWItems.bark, WoodTypeHelper.getBarkCountForTanning(woodType), woodType)
					});
			RecipeManager.addCauldronRecipe(new ItemStack(BTWItems.cutTannedLeather, 2),
					new ItemStack[] {
							new ItemStack(BTWItems.cutScouredLeather, 2),
							new ItemStack(BTWItems.dung),
							new ItemStack(BTWItems.bark, WoodTypeHelper.getBarkCountForTanning(woodType), woodType)
					});
		}
		
		//------ Wood type recipes ------//
		
		initOakWoodRecipes();
		initSpruceWoodRecipes();
		initBirchWoodRecipes();
		initJungleWoodRecipes();
		initBloodWoodRecipes();
		initCherryWoodRecipes();
		initAcaciaWoodRecipes();
		initMahoganyWoodRecipes();
		initMangroveWoodRecipes();
		initHazelWoodRecipes();
		
		initPaintedWoodRecipes();
	}
	
	private static void initOakWoodRecipes() {
		// Logs
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.wood, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.wood, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedWood, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.wood, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedWood, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.wood, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.wood, 4, WoodTypeHelper.OAK_WOOD_TYPE),
				new Object[] {
						"LL",
						"LL",
						'L', new ItemStack(Block.wood, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.strippedWood, 4, WoodTypeHelper.OAK_WOOD_TYPE),
				new Object[] {
						"LL",
						"LL",
						'L', new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.OAK_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4)
				},
				new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.OAK_WOOD_TYPE));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.OAK_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.wood, 1, WoodTypeHelper.OAK_WOOD_TYPE));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.OAK_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4)
				},
				new ItemStack(DecoBlocks.strippedWood, 1, WoodTypeHelper.OAK_WOOD_TYPE));
		
		for (int direction = 0; direction <= 8; direction += 4) {
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.OAK_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.strippedLog, WoodTypeHelper.OAK_WOOD_TYPE | direction);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.OAK_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.OAK_WOOD_TYPE)
					},
					DecoBlocks.wood, WoodTypeHelper.OAK_WOOD_TYPE | direction);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.OAK_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.strippedWood, WoodTypeHelper.OAK_WOOD_TYPE | direction);
		}
		
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(Block.trapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(Block.trapdoor, 2),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		
		// Door
		RecipeManager.addRecipe(new ItemStack(Item.doorWood),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(Block.planks, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(Item.doorWood),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		
		// Fence gate
		RecipeManager.addRecipe(new ItemStack(Block.fenceGate),
				new Object[] {
						"#W#",
						"#W#",
						'#', Item.stick,
						'W', new ItemStack(Block.planks, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(Block.fenceGate),
				new Object[] {
						"#X#",
						'#', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						'X', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		
		// Chair
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.oakChair, 4),
				new Object[] {
						"S  ",
						"SSS",
						"M M",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		
		// Barrel
		RecipeManager.addRecipe(new ItemStack(WoodTypeHelper.OAK_BARREL_ID, 2, WoodTypeHelper.OAK_BARREL_TYPE),
				new Object[] {
						"MMM",
						"M M",
						"MMM",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		
		for (int type = 0; type < FilledBarrelBlock.types.length; type++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.filledOakBarrel, 1, type),
					new Object[]{
							"###",
							"#X#",
							"###",
							'#', new ItemStack(FilledBarrelBlock.types[type]),
							'X', new ItemStack(WoodTypeHelper.OAK_BARREL_ID, 1, WoodTypeHelper.OAK_BARREL_TYPE)});
			
			RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(
					new ItemStack(FilledBarrelBlock.types[type], 8),
					new ItemStack(WoodTypeHelper.OAK_BARREL_ID, 1, WoodTypeHelper.OAK_BARREL_TYPE),
					new ItemStack[] {
							new ItemStack(DecoBlocks.filledOakBarrel, 1, type)
					});
		}
		
		// Bookshelves
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new Object[] {
						"PPP",
						"P P",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new Object[] {
						"SSS",
						"M M",
						"SSS",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bookshelf, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(Block.bookShelf, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						new ItemStack(Item.book),
						new ItemStack(Item.book),
						new ItemStack(Item.book)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(DecoItems.plainBook, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bookshelf)
				});
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.book, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.bookShelf)
				});
		
		// Bottle Racks
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new Object[] {
						"PPP",
						" P ",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new Object[] {
						"MSM",
						" M ",
						"MSM",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bottleRack, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.glassBottle, 3),
				new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.OAK_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bottleRack)
				});
		
		// Ladders
		RecipeManager.addRecipe(new ItemStack(BTWBlocks.ladder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						'F', BTWItems.hempFibers
				});
		RecipeManager.addRecipe(new ItemStack(BTWBlocks.ladder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						'F', Item.silk
				});
		
		// Button
		RecipeManager.removeVanillaRecipe(new ItemStack(Block.woodenButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(BTWItems.woodCornerStubID, 1, InventoryUtils.IGNORE_METADATA),
						'R', Item.redstone
				});
		RecipeManager.addRecipe(new ItemStack(Block.woodenButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(BTWItems.woodCornerStubID, 1, WoodTypeHelper.OAK_WOOD_TYPE),
						'R', Item.redstone
				});
	}
	
	private static void initSpruceWoodRecipes() {
		// Logs
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.wood, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.wood, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedWood, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.wood, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedWood, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.wood, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.wood, 4, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new Object[] {
						"LL",
						"LL",
						'L', new ItemStack(Block.wood, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.strippedWood, 4, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new Object[] {
						"LL",
						"LL",
						'L', new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4)
				},
				new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.wood, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4)
				},
				new ItemStack(DecoBlocks.strippedWood, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE));
		
		for (int direction = 0; direction <= 8; direction += 4) {
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.SPRUCE_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.strippedLog, WoodTypeHelper.SPRUCE_WOOD_TYPE | direction);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.SPRUCE_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
					},
					DecoBlocks.wood, WoodTypeHelper.SPRUCE_WOOD_TYPE | direction);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.SPRUCE_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.strippedWood, WoodTypeHelper.SPRUCE_WOOD_TYPE | direction);
		}
		
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.spruceTrapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.spruceTrapdoor, 2),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		
		// Door
		RecipeManager.addRecipe(new ItemStack(DecoItems.spruceDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(Block.planks, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoItems.spruceDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		
		// Fence gate
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.spruceGate),
				new Object[] {
						"#W#",
						"#W#",
						'#', Item.stick,
						'W', new ItemStack(Block.planks, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.spruceGate),
				new Object[] {
						"#X#",
						'#', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						'X', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		
		// Chair
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.spruceChair, 4),
				new Object[] {
						"S  ",
						"SSS",
						"M M",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		
		// Barrel
		RecipeManager.addRecipe(new ItemStack(WoodTypeHelper.SPRUCE_BARREL_ID, 2, WoodTypeHelper.SPRUCE_BARREL_TYPE),
				new Object[] {
						"MMM",
						"M M",
						"MMM",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		
		for (int type = 0; type < FilledBarrelBlock.types.length; type++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.filledSpruceBarrel, 1, type),
					new Object[]{
							"###",
							"#X#",
							"###",
							'#', new ItemStack(FilledBarrelBlock.types[type]),
							'X', new ItemStack(WoodTypeHelper.SPRUCE_BARREL_ID, 1, WoodTypeHelper.SPRUCE_BARREL_TYPE)});
			
			RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(
					new ItemStack(FilledBarrelBlock.types[type], 8),
					new ItemStack(WoodTypeHelper.SPRUCE_BARREL_ID, 1, WoodTypeHelper.SPRUCE_BARREL_TYPE),
					new ItemStack[] {
							new ItemStack(DecoBlocks.filledSpruceBarrel, 1, type)
					});
		}
		
		// Bookshelves
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new Object[] {
						"PPP",
						"P P",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new Object[] {
						"SSS",
						"M M",
						"SSS",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bookshelf, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(Block.bookShelf, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						new ItemStack(Item.book),
						new ItemStack(Item.book),
						new ItemStack(Item.book)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(DecoItems.plainBook, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bookshelf)
				});
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.book, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.bookShelf)
				});
		
		// Bottle Racks
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new Object[] {
						"PPP",
						" P ",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new Object[] {
						"MSM",
						" M ",
						"MSM",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bottleRack, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.glassBottle, 3),
				new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bottleRack)
				});
		
		// Ladders
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.spruceLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						'F', BTWItems.hempFibers
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.spruceLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						'F', Item.silk
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.spruceButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(BTWItems.woodCornerStubID, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE),
						'R', Item.redstone
				});
	}
	
	private static void initBirchWoodRecipes() {
		// Logs
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.wood, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.wood, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedWood, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.wood, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedWood, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.wood, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.wood, 4, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new Object[] {
						"LL",
						"LL",
						'L', new ItemStack(Block.wood, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.strippedWood, 4, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new Object[] {
						"LL",
						"LL",
						'L', new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4)
				},
				new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.BIRCH_WOOD_TYPE));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.wood, 1, WoodTypeHelper.BIRCH_WOOD_TYPE));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4)
				},
				new ItemStack(DecoBlocks.strippedWood, 1, WoodTypeHelper.BIRCH_WOOD_TYPE));
		
		for (int direction = 0; direction <= 8; direction += 4) {
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.BIRCH_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.strippedLog, WoodTypeHelper.BIRCH_WOOD_TYPE | direction);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.BIRCH_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
					},
					DecoBlocks.wood, WoodTypeHelper.BIRCH_WOOD_TYPE | direction);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.BIRCH_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.strippedWood, WoodTypeHelper.BIRCH_WOOD_TYPE | direction);
		}
		
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.birchTrapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.birchTrapdoor, 2),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		
		// Door
		RecipeManager.addRecipe(new ItemStack(DecoItems.birchDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(Block.planks, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoItems.birchDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		
		// Fence gate
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.birchGate),
				new Object[] {
						"#W#",
						"#W#",
						'#', Item.stick,
						'W', new ItemStack(Block.planks, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.birchGate),
				new Object[] {
						"#X#",
						'#', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						'X', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		
		// Chair
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.birchChair, 4),
				new Object[] {
						"S  ",
						"SSS",
						"M M",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		
		// Barrel
		RecipeManager.addRecipe(new ItemStack(WoodTypeHelper.BIRCH_BARREL_ID, 2, WoodTypeHelper.BIRCH_BARREL_TYPE),
				new Object[] {
						"MMM",
						"M M",
						"MMM",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		
		for (int type = 0; type < FilledBarrelBlock.types.length; type++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.filledBirchBarrel, 1, type),
					new Object[]{
							"###",
							"#X#",
							"###",
							'#', new ItemStack(FilledBarrelBlock.types[type]),
							'X', new ItemStack(WoodTypeHelper.BIRCH_BARREL_ID, 1, WoodTypeHelper.BIRCH_BARREL_TYPE)});
			
			RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(
					new ItemStack(FilledBarrelBlock.types[type], 8),
					new ItemStack(WoodTypeHelper.BIRCH_BARREL_ID, 1, WoodTypeHelper.BIRCH_BARREL_TYPE),
					new ItemStack[] {
							new ItemStack(DecoBlocks.filledBirchBarrel, 1, type)
					});
		}
		
		// Bookshelves
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new Object[] {
						"PPP",
						"P P",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new Object[] {
						"SSS",
						"M M",
						"SSS",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bookshelf, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(Block.bookShelf, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						new ItemStack(Item.book),
						new ItemStack(Item.book),
						new ItemStack(Item.book)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(DecoItems.plainBook, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bookshelf)
				});
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.book, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.bookShelf)
				});
		
		// Bottle Racks
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new Object[] {
						"PPP",
						" P ",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new Object[] {
						"MSM",
						" M ",
						"MSM",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bottleRack, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.glassBottle, 3),
				new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bottleRack)
				});
		
		// Ladders
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.birchLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						'F', BTWItems.hempFibers
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.birchLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						'F', Item.silk
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.birchButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(BTWItems.woodCornerStubID, 1, WoodTypeHelper.BIRCH_WOOD_TYPE),
						'R', Item.redstone
				});
	}
	
	private static void initJungleWoodRecipes() {
		// Logs
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.wood, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.wood, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedWood, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.wood, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.strippedWood, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.wood, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.wood, 4, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new Object[] {
						"LL",
						"LL",
						'L', new ItemStack(Block.wood, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.strippedWood, 4, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new Object[] {
						"LL",
						"LL",
						'L', new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4)
				},
				new ItemStack(DecoBlocks.strippedLog, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.wood, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4)
				},
				new ItemStack(DecoBlocks.strippedWood, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE));
		
		for (int direction = 0; direction <= 8; direction += 4) {
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.JUNGLE_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.strippedLog, WoodTypeHelper.JUNGLE_WOOD_TYPE | direction);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.JUNGLE_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
					},
					DecoBlocks.wood, WoodTypeHelper.JUNGLE_WOOD_TYPE | direction);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.JUNGLE_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.strippedWood, WoodTypeHelper.JUNGLE_WOOD_TYPE | direction);
		}
		
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.jungleTrapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.jungleTrapdoor, 2),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		
		// Door
		RecipeManager.addRecipe(new ItemStack(DecoItems.jungleDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(Block.planks, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoItems.jungleDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		
		// Fence gate
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.jungleGate),
				new Object[] {
						"#W#",
						"#W#",
						'#', Item.stick,
						'W', new ItemStack(Block.planks, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.jungleGate),
				new Object[] {
						"#X#",
						'#', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						'X', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		
		// Chair
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.jungleChair, 4),
				new Object[] {
						"S  ",
						"SSS",
						"M M",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		
		// Barrel
		RecipeManager.addRecipe(new ItemStack(WoodTypeHelper.JUNGLE_BARREL_ID, 2, WoodTypeHelper.JUNGLE_BARREL_TYPE),
				new Object[] {
						"MMM",
						"M M",
						"MMM",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		
		for (int type = 0; type < FilledBarrelBlock.types.length; type++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.filledJungleBarrel, 1, type),
					new Object[]{
							"###",
							"#X#",
							"###",
							'#', new ItemStack(FilledBarrelBlock.types[type]),
							'X', new ItemStack(WoodTypeHelper.JUNGLE_BARREL_ID, 1, WoodTypeHelper.JUNGLE_BARREL_TYPE)});
			
			RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(
					new ItemStack(FilledBarrelBlock.types[type], 8),
					new ItemStack(WoodTypeHelper.JUNGLE_BARREL_ID, 1, WoodTypeHelper.JUNGLE_BARREL_TYPE),
					new ItemStack[] {
							new ItemStack(DecoBlocks.filledJungleBarrel, 1, type)
					});
		}
		
		// Bookshelves
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new Object[] {
						"PPP",
						"P P",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new Object[] {
						"SSS",
						"M M",
						"SSS",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bookshelf, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(Block.bookShelf, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						new ItemStack(Item.book),
						new ItemStack(Item.book),
						new ItemStack(Item.book)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(DecoItems.plainBook, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bookshelf)
				});
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.book, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.bookShelf)
				});
		
		// Bottle Racks
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new Object[] {
						"PPP",
						" P ",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new Object[] {
						"MSM",
						" M ",
						"MSM",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bottleRack, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.glassBottle, 3),
				new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bottleRack)
				});
		
		// Ladders
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.jungleLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						'F', BTWItems.hempFibers
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.jungleLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						'F', Item.silk
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.jungleButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(BTWItems.woodCornerStubID, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE),
						'R', Item.redstone
				});
	}
	
	private static void initBloodWoodRecipes() {
		// Logs
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bloodLog, 1, 0),
				new ItemStack[] {
						new ItemStack(BTWBlocks.bloodWoodLog),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bloodLog, 1, 0),
				new ItemStack[] {
						new ItemStack(BTWBlocks.bloodWoodLog),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bloodLog, 1, 2),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bloodLog, 1, 1),
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bloodLog, 1, 2),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bloodLog, 1, 1),
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.BLOOD_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4)
				},
				new ItemStack(DecoBlocks.bloodLog, 1, 0));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.BLOOD_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.bloodLog, 1, 1));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 2, WoodTypeHelper.BLOOD_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4)
				},
				new ItemStack(DecoBlocks.bloodLog, 1, 2));
		
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.bloodLog, 4, 1),
				new Object[] {
						"LL",
						"LL",
						'L', new ItemStack(BTWBlocks.bloodWoodLog)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.bloodLog, 4, 2),
				new Object[] {
						"LL",
						"LL",
						'L', new ItemStack(DecoBlocks.bloodLog, 1, 0)
				});
		
		for (int direction = 0; direction <= 8; direction += 4) {
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.BLOOD_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust),
							new ItemStack(BTWItems.soulDust)
					},
					DecoBlocks.bloodLog, direction);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.BLOOD_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust),
							new ItemStack(BTWItems.soulDust),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
					},
					DecoBlocks.bloodLog, 1 | direction);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.BLOOD_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust),
							new ItemStack(BTWItems.soulDust)
					},
					DecoBlocks.bloodLog, 2 | direction);
			}
		
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.bloodTrapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.bloodTrapdoor, 2),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				});
		
		// Door
		RecipeManager.addRecipe(new ItemStack(DecoItems.bloodDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(Block.planks, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoItems.bloodDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				});
		
		// Fence gate
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.bloodGate),
				new Object[] {
						"#W#",
						"#W#",
						'#', Item.stick,
						'W', new ItemStack(Block.planks, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.bloodGate),
				new Object[] {
						"#X#",
						'#', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
						'X', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				});
		
		// Chair
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.bloodChair, 4),
				new Object[] {
						"S  ",
						"SSS",
						"M M",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				});
		
		// Barrel
		RecipeManager.addRecipe(new ItemStack(WoodTypeHelper.BLOOD_BARREL_ID, 2, WoodTypeHelper.BLOOD_BARREL_TYPE),
				new Object[] {
						"MMM",
						"M M",
						"MMM",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				});
		
		for (int type = 0; type < FilledBarrelBlock.types.length; type++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.filledBloodBarrel, 1, type),
					new Object[]{
							"###",
							"#X#",
							"###",
							'#', new ItemStack(FilledBarrelBlock.types[type]),
							'X', new ItemStack(WoodTypeHelper.BLOOD_BARREL_ID, 1, WoodTypeHelper.BLOOD_BARREL_TYPE)});
			
			RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(
					new ItemStack(FilledBarrelBlock.types[type], 8),
					new ItemStack(WoodTypeHelper.BLOOD_BARREL_ID, 1, WoodTypeHelper.BLOOD_BARREL_TYPE),
					new ItemStack[] {
							new ItemStack(DecoBlocks.filledBloodBarrel, 1, type)
					});
		}
		
		// Bookshelves
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
				new Object[] {
						"PPP",
						"P P",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
				new Object[] {
						"SSS",
						"M M",
						"SSS",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bookshelf, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(Block.bookShelf, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
						new ItemStack(Item.book),
						new ItemStack(Item.book),
						new ItemStack(Item.book)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(DecoItems.plainBook, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bookshelf)
				});
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.book, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.bookShelf)
				});
		
		// Bottle Racks
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
				new Object[] {
						"PPP",
						" P ",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
				new Object[] {
						"MSM",
						" M ",
						"MSM",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bottleRack, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.glassBottle, 3),
				new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bottleRack)
				});
		
		// Ladders
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.bloodLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
						'F', BTWItems.hempFibers
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.bloodLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
						'F', Item.silk
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.bloodButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(BTWItems.woodCornerStubID, 1, WoodTypeHelper.BLOOD_WOOD_TYPE),
						'R', Item.redstone
				});
	}
	
	private static void initCherryWoodRecipes() {
		// Logs
		for (int facing = 0; facing <= 8; facing += 4) {
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.CHERRY_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
					},
					DecoBlocks.cherryLog, DecoLogBlock.TYPE_LOG | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.CHERRY_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.cherryLog, DecoLogBlock.TYPE_STRIPPED | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.CHERRY_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
					},
					DecoBlocks.cherryLog, DecoLogBlock.TYPE_WOOD | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.CHERRY_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.cherryLog, DecoLogBlock.TYPE_STRIPPED_WOOD | facing);
		}
		
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.cherryLog, 1, DecoLogBlock.TYPE_LOG));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.cherryLog, 1, DecoLogBlock.TYPE_STRIPPED));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.cherryLog, 1, DecoLogBlock.TYPE_WOOD));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.cherryLog, 1, DecoLogBlock.TYPE_STRIPPED));
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(BTWItems.potash),
				new ItemStack[] {
						new ItemStack(DecoBlocks.cherryLog)
				});
		
		RecipeManager.addKilnRecipe(new ItemStack(Item.coal, 1, 1), DecoBlocks.cherryLog);
		
		// Subblocks
		addWoodenSubBlockRecipes(WoodTypeHelper.CHERRY_WOOD_TYPE, DecoBlocks.cherrySidingAndCorner, DecoBlocks.cherryMoulding, DecoBlocks.cherryStairs,
				Block.woodSingleSlab, WoodTypeHelper.CHERRY_WOOD_TYPE);
		
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.cherryTrapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.cherryTrapdoor, 2),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				});
		
		// Door
		RecipeManager.addRecipe(new ItemStack(DecoItems.cherryDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(Block.planks, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoItems.cherryDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				});
		
		// Fence gate
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.cherryGate),
				new Object[] {
						"#W#",
						"#W#",
						'#', Item.stick,
						'W', new ItemStack(Block.planks, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.cherryGate),
				new Object[] {
						"#X#",
						'#', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
						'X', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				});
		
		// Chair
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.cherryChair, 4),
				new Object[] {
						"S  ",
						"SSS",
						"M M",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				});
		
		// Barrel
		RecipeManager.addRecipe(new ItemStack(WoodTypeHelper.CHERRY_BARREL_ID, 2, WoodTypeHelper.CHERRY_BARREL_TYPE),
				new Object[] {
						"MMM",
						"M M",
						"MMM",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				});
		
		for (int type = 0; type < FilledBarrelBlock.types.length; type++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.filledCherryBarrel, 1, type),
					new Object[]{
							"###",
							"#X#",
							"###",
							'#', new ItemStack(FilledBarrelBlock.types[type]),
							'X', new ItemStack(WoodTypeHelper.CHERRY_BARREL_ID, 1, WoodTypeHelper.CHERRY_BARREL_TYPE)});
			
			RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(
					new ItemStack(FilledBarrelBlock.types[type], 8),
					new ItemStack(WoodTypeHelper.CHERRY_BARREL_ID, 1, WoodTypeHelper.CHERRY_BARREL_TYPE),
					new ItemStack[] {
							new ItemStack(DecoBlocks.filledCherryBarrel, 1, type)
					});
		}
		
		// Bookshelves
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new Object[] {
						"PPP",
						"P P",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new Object[] {
						"SSS",
						"M M",
						"SSS",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bookshelf, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(Block.bookShelf, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
						new ItemStack(Item.book),
						new ItemStack(Item.book),
						new ItemStack(Item.book)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(DecoItems.plainBook, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bookshelf)
				});
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.book, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.bookShelf)
				});
		
		// Bottle Racks
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new Object[] {
						"PPP",
						" P ",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new Object[] {
						"MSM",
						" M ",
						"MSM",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bottleRack, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.glassBottle, 3),
				new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bottleRack)
				});
		
		// Ladders
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.cherryLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
						'F', BTWItems.hempFibers
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.cherryLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
						'F', Item.silk
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.cherryButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(BTWItems.woodCornerStubID, 1, WoodTypeHelper.CHERRY_WOOD_TYPE),
						'R', Item.redstone
				});
	}
	
	private static void initAcaciaWoodRecipes() {
		// Logs
		for (int facing = 0; facing <= 8; facing += 4) {
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.ACACIA_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
					},
					DecoBlocks.acaciaLog, DecoLogBlock.TYPE_LOG | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.ACACIA_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.acaciaLog, DecoLogBlock.TYPE_STRIPPED | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.ACACIA_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
					},
					DecoBlocks.acaciaLog, DecoLogBlock.TYPE_WOOD | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.ACACIA_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.acaciaLog, DecoLogBlock.TYPE_STRIPPED_WOOD | facing);
		}
		
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.acaciaLog, 1, DecoLogBlock.TYPE_LOG));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.acaciaLog, 1, DecoLogBlock.TYPE_STRIPPED));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.acaciaLog, 1, DecoLogBlock.TYPE_WOOD));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.acaciaLog, 1, DecoLogBlock.TYPE_STRIPPED));
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(BTWItems.potash),
				new ItemStack[] {
						new ItemStack(DecoBlocks.acaciaLog)
				});
		
		RecipeManager.addKilnRecipe(new ItemStack(Item.coal, 1, 1), DecoBlocks.acaciaLog);
		
		// Subblocks
		addWoodenSubBlockRecipes(WoodTypeHelper.ACACIA_WOOD_TYPE, DecoBlocks.acaciaSidingAndCorner, DecoBlocks.acaciaMoulding, DecoBlocks.acaciaStairs,
				Block.woodSingleSlab, WoodTypeHelper.ACACIA_WOOD_TYPE);
		
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.acaciaTrapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.acaciaTrapdoor, 2),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				});
		
		// Door
		RecipeManager.addRecipe(new ItemStack(DecoItems.acaciaDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(Block.planks, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoItems.acaciaDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				});
		
		// Fence gate
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.acaciaGate),
				new Object[] {
						"#W#",
						"#W#",
						'#', Item.stick,
						'W', new ItemStack(Block.planks, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.acaciaGate),
				new Object[] {
						"#X#",
						'#', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
						'X', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				});
		
		// Chair
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.acaciaChair, 4),
				new Object[] {
						"S  ",
						"SSS",
						"M M",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				});
		
		// Barrel
		RecipeManager.addRecipe(new ItemStack(WoodTypeHelper.ACACIA_BARREL_ID, 2, WoodTypeHelper.ACACIA_BARREL_TYPE),
				new Object[] {
						"MMM",
						"M M",
						"MMM",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				});
		
		for (int type = 0; type < FilledBarrelBlock.types.length; type++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.filledAcaciaBarrel, 1, type),
					new Object[]{
							"###",
							"#X#",
							"###",
							'#', new ItemStack(FilledBarrelBlock.types[type]),
							'X', new ItemStack(WoodTypeHelper.ACACIA_BARREL_ID, 1, WoodTypeHelper.ACACIA_BARREL_TYPE)});
			
			RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(
					new ItemStack(FilledBarrelBlock.types[type], 8),
					new ItemStack(WoodTypeHelper.ACACIA_BARREL_ID, 1, WoodTypeHelper.ACACIA_BARREL_TYPE),
					new ItemStack[] {
							new ItemStack(DecoBlocks.filledAcaciaBarrel, 1, type)
					});
		}
		
		// Bookshelves
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new Object[] {
						"PPP",
						"P P",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new Object[] {
						"SSS",
						"M M",
						"SSS",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bookshelf, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(Block.bookShelf, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
						new ItemStack(Item.book),
						new ItemStack(Item.book),
						new ItemStack(Item.book)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(DecoItems.plainBook, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bookshelf)
				});
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.book, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.bookShelf)
				});
		
		// Bottle Racks
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new Object[] {
						"PPP",
						" P ",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new Object[] {
						"MSM",
						" M ",
						"MSM",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bottleRack, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.glassBottle, 3),
				new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bottleRack)
				});
		
		// Ladders
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.acaciaLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
						'F', BTWItems.hempFibers
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.acaciaLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
						'F', Item.silk
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.acaciaButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(BTWItems.woodCornerStubID, 1, WoodTypeHelper.ACACIA_WOOD_TYPE),
						'R', Item.redstone
				});
	}
	
	private static void initMahoganyWoodRecipes() {
		// Logs
		for (int facing = 0; facing <= 8; facing += 4) {
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
					},
					DecoBlocks.mahoganyLog, DecoLogBlock.TYPE_LOG | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.mahoganyLog, DecoLogBlock.TYPE_STRIPPED | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
					},
					DecoBlocks.mahoganyLog, DecoLogBlock.TYPE_WOOD | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.mahoganyLog, DecoLogBlock.TYPE_STRIPPED_WOOD | facing);
		}
		
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.mahoganyLog, 1, DecoLogBlock.TYPE_LOG));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.mahoganyLog, 1, DecoLogBlock.TYPE_STRIPPED));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.mahoganyLog, 1, DecoLogBlock.TYPE_WOOD));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.mahoganyLog, 1, DecoLogBlock.TYPE_STRIPPED));
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(BTWItems.potash),
				new ItemStack[] {
						new ItemStack(DecoBlocks.mahoganyLog)
				});
		
		RecipeManager.addKilnRecipe(new ItemStack(Item.coal, 1, 1), DecoBlocks.mahoganyLog);
		
		// Subblocks
		addWoodenSubBlockRecipes(WoodTypeHelper.MAHOGANY_WOOD_TYPE, DecoBlocks.mahoganySidingAndCorner, DecoBlocks.mahoganyMoulding, DecoBlocks.mahoganyStairs,
				Block.woodSingleSlab, WoodTypeHelper.MAHOGANY_WOOD_TYPE);
		
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mahoganyTrapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mahoganyTrapdoor, 2),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				});
		
		// Door
		RecipeManager.addRecipe(new ItemStack(DecoItems.mahoganyDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(Block.planks, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoItems.mahoganyDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				});
		
		// Fence gate
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mahoganyGate),
				new Object[] {
						"#W#",
						"#W#",
						'#', Item.stick,
						'W', new ItemStack(Block.planks, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mahoganyGate),
				new Object[] {
						"#X#",
						'#', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
						'X', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				});
		
		// Chair
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mahoganyChair, 4),
				new Object[] {
						"S  ",
						"SSS",
						"M M",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				});
		
		// Barrel
		RecipeManager.addRecipe(new ItemStack(WoodTypeHelper.MAHOGANY_BARREL_ID, 2, WoodTypeHelper.MAHOGANY_BARREL_TYPE),
				new Object[] {
						"MMM",
						"M M",
						"MMM",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				});
		
		for (int type = 0; type < FilledBarrelBlock.types.length; type++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.filledMahoganyBarrel, 1, type),
					new Object[]{
							"###",
							"#X#",
							"###",
							'#', new ItemStack(FilledBarrelBlock.types[type]),
							'X', new ItemStack(WoodTypeHelper.MAHOGANY_BARREL_ID, 1, WoodTypeHelper.MAHOGANY_BARREL_TYPE)});
			
			RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(
					new ItemStack(FilledBarrelBlock.types[type], 8),
					new ItemStack(WoodTypeHelper.MAHOGANY_BARREL_ID, 1, WoodTypeHelper.MAHOGANY_BARREL_TYPE),
					new ItemStack[] {
							new ItemStack(DecoBlocks.filledMahoganyBarrel, 1, type)
					});
		}
		
		// Bookshelves
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new Object[] {
						"PPP",
						"P P",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new Object[] {
						"SSS",
						"M M",
						"SSS",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bookshelf, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(Block.bookShelf, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
						new ItemStack(Item.book),
						new ItemStack(Item.book),
						new ItemStack(Item.book)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(DecoItems.plainBook, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bookshelf)
				});
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.book, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.bookShelf)
				});
		
		// Bottle Racks
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new Object[] {
						"PPP",
						" P ",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new Object[] {
						"MSM",
						" M ",
						"MSM",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bottleRack, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.glassBottle, 3),
				new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bottleRack)
				});
		
		// Ladders
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mahoganyLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
						'F', BTWItems.hempFibers
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mahoganyLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
						'F', Item.silk
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mahoganyButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(BTWItems.woodCornerStubID, 1, WoodTypeHelper.MAHOGANY_WOOD_TYPE),
						'R', Item.redstone
				});
	}
	
	private static void initMangroveWoodRecipes() {
		// Logs
		for (int facing = 0; facing <= 8; facing += 4) {
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.MANGROVE_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
					},
					DecoBlocks.mangroveLog, DecoLogBlock.TYPE_LOG | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.MANGROVE_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.mangroveLog, DecoLogBlock.TYPE_STRIPPED | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.MANGROVE_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
					},
					DecoBlocks.mangroveLog, DecoLogBlock.TYPE_WOOD | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.MANGROVE_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.mangroveLog, DecoLogBlock.TYPE_STRIPPED_WOOD | facing);
		}
		
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.mangroveLog, 1, DecoLogBlock.TYPE_LOG));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.mangroveLog, 1, DecoLogBlock.TYPE_STRIPPED));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.mangroveLog, 1, DecoLogBlock.TYPE_WOOD));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.mangroveLog, 1, DecoLogBlock.TYPE_STRIPPED));
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(BTWItems.potash),
				new ItemStack[] {
						new ItemStack(DecoBlocks.mangroveLog)
				});
		
		RecipeManager.addKilnRecipe(new ItemStack(Item.coal, 1, 1), DecoBlocks.mangroveLog);
		
		// Subblocks
		addWoodenSubBlockRecipes(WoodTypeHelper.MANGROVE_WOOD_TYPE, DecoBlocks.mangroveSidingAndCorner, DecoBlocks.mangroveMoulding, DecoBlocks.mangroveStairs,
				DecoBlocks.woodSingleSlab, WoodTypeHelper.MANGROVE_SLAB_TYPE);
		
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mangroveTrapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mangroveTrapdoor, 2),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				});
		
		// Door
		RecipeManager.addRecipe(new ItemStack(DecoItems.mangroveDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(Block.planks, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoItems.mangroveDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				});
		
		// Fence gate
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mangroveGate),
				new Object[] {
						"#W#",
						"#W#",
						'#', Item.stick,
						'W', new ItemStack(Block.planks, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mangroveGate),
				new Object[] {
						"#X#",
						'#', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
						'X', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				});
		
		// Chair
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mangroveChair, 4),
				new Object[] {
						"S  ",
						"SSS",
						"M M",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				});
		
		// Barrel
		RecipeManager.addRecipe(new ItemStack(WoodTypeHelper.MANGROVE_BARREL_ID, 2, WoodTypeHelper.MANGROVE_BARREL_TYPE),
				new Object[] {
						"MMM",
						"M M",
						"MMM",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				});
		
		for (int type = 0; type < FilledBarrelBlock.types.length; type++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.filledMangroveBarrel, 1, type),
					new Object[]{
							"###",
							"#X#",
							"###",
							'#', new ItemStack(FilledBarrelBlock.types[type]),
							'X', new ItemStack(WoodTypeHelper.MANGROVE_BARREL_ID, 1, WoodTypeHelper.MANGROVE_BARREL_TYPE)});
			
			RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(
					new ItemStack(FilledBarrelBlock.types[type], 8),
					new ItemStack(WoodTypeHelper.MANGROVE_BARREL_ID, 1, WoodTypeHelper.MANGROVE_BARREL_TYPE),
					new ItemStack[] {
							new ItemStack(DecoBlocks.filledMangroveBarrel, 1, type)
					});
		}
		
		// Bookshelves
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new Object[] {
						"PPP",
						"P P",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new Object[] {
						"SSS",
						"M M",
						"SSS",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bookshelf, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(Block.bookShelf, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
						new ItemStack(Item.book),
						new ItemStack(Item.book),
						new ItemStack(Item.book)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(DecoItems.plainBook, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bookshelf)
				});
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.book, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.bookShelf)
				});
		
		// Bottle Racks
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new Object[] {
						"PPP",
						" P ",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new Object[] {
						"MSM",
						" M ",
						"MSM",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bottleRack, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.glassBottle, 3),
				new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bottleRack)
				});
		
		// Ladders
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mangroveLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
						'F', BTWItems.hempFibers
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mangroveLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
						'F', Item.silk
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mangroveButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(BTWItems.woodCornerStubID, 1, WoodTypeHelper.MANGROVE_WOOD_TYPE),
						'R', Item.redstone
				});
	}
	
	private static void initHazelWoodRecipes() {
		// Logs
		for (int facing = 0; facing <= 8; facing += 4) {
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.HAZEL_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
					},
					DecoBlocks.hazelLog, DecoLogBlock.TYPE_LOG | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.HAZEL_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.hazelLog, DecoLogBlock.TYPE_STRIPPED | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.HAZEL_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2),
							new ItemStack(BTWItems.bark, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
					},
					DecoBlocks.hazelLog, DecoLogBlock.TYPE_WOOD | facing);
			RecipeManager.addSawRecipe(
					new ItemStack[] {
							new ItemStack(Block.planks, 4, WoodTypeHelper.HAZEL_WOOD_TYPE),
							new ItemStack(BTWItems.sawDust, 2)
					},
					DecoBlocks.hazelLog, DecoLogBlock.TYPE_STRIPPED_WOOD | facing);
		}
		
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.hazelLog, 1, DecoLogBlock.TYPE_LOG));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.hazelLog, 1, DecoLogBlock.TYPE_STRIPPED));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.hazelLog, 1, DecoLogBlock.TYPE_WOOD));
		RecipeManager.addLogChoppingRecipe(new ItemStack(Block.planks, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 2),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				},
				new ItemStack(Item.stick, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.sawDust, 4),
						new ItemStack(BTWItems.bark, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				},
				new ItemStack(DecoBlocks.hazelLog, 1, DecoLogBlock.TYPE_STRIPPED));
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(BTWItems.potash),
				new ItemStack[] {
						new ItemStack(DecoBlocks.hazelLog)
				});
		
		RecipeManager.addKilnRecipe(new ItemStack(Item.coal, 1, 1), DecoBlocks.hazelLog);
		
		// Subblocks
		addWoodenSubBlockRecipes(WoodTypeHelper.HAZEL_WOOD_TYPE, DecoBlocks.hazelSidingAndCorner, DecoBlocks.hazelMoulding, DecoBlocks.hazelStairs,
				DecoBlocks.woodSingleSlab, WoodTypeHelper.HAZEL_SLAB_TYPE);
		
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.hazelTrapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.hazelTrapdoor, 2),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				});
		
		// Door
		RecipeManager.addRecipe(new ItemStack(DecoItems.hazelDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(Block.planks, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoItems.hazelDoor),
				new Object[] {
						"##",
						"##",
						"##",
						'#', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				});
		
		// Fence gate
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.hazelGate),
				new Object[] {
						"#W#",
						"#W#",
						'#', Item.stick,
						'W', new ItemStack(Block.planks, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.hazelGate),
				new Object[] {
						"#X#",
						'#', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
						'X', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				});
		
		// Chair
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.hazelChair, 4),
				new Object[] {
						"S  ",
						"SSS",
						"M M",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				});
		
		// Barrel
		RecipeManager.addRecipe(new ItemStack(WoodTypeHelper.HAZEL_BARREL_ID, 2, WoodTypeHelper.HAZEL_BARREL_TYPE),
				new Object[] {
						"MMM",
						"M M",
						"MMM",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				});
		
		for (int type = 0; type < FilledBarrelBlock.types.length; type++) {
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.filledHazelBarrel, 1, type),
					new Object[]{
							"###",
							"#X#",
							"###",
							'#', new ItemStack(FilledBarrelBlock.types[type]),
							'X', new ItemStack(WoodTypeHelper.HAZEL_BARREL_ID, 1, WoodTypeHelper.HAZEL_BARREL_TYPE)});
			
			RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(
					new ItemStack(FilledBarrelBlock.types[type], 8),
					new ItemStack(WoodTypeHelper.HAZEL_BARREL_ID, 1, WoodTypeHelper.HAZEL_BARREL_TYPE),
					new ItemStack[] {
							new ItemStack(DecoBlocks.filledHazelBarrel, 1, type)
					});
		}
		
		// Bookshelves
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new Object[] {
						"PPP",
						"P P",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new Object[] {
						"SSS",
						"M M",
						"SSS",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bookshelf, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook),
						new ItemStack(DecoItems.plainBook)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(Block.bookShelf, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
						new ItemStack(Item.book),
						new ItemStack(Item.book),
						new ItemStack(Item.book)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(DecoItems.plainBook, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bookshelf)
				});
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.book, 3),
				new ItemStack(DecoBlocks.emptyBookshelf, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(Block.bookShelf)
				});
		
		// Bottle Racks
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new Object[] {
						"PPP",
						" P ",
						"PPP",
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new Object[] {
						"MSM",
						" M ",
						"MSM",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE)
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.bottleRack, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle),
						new ItemStack(Item.glassBottle)
				});
		
		RecipeManager.addShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.glassBottle, 3),
				new ItemStack(DecoBlocks.emptyBottleRack, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
				new ItemStack[] {
						new ItemStack(DecoBlocks.bottleRack)
				});
		
		// Ladders
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.hazelLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
						'F', BTWItems.hempFibers
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.hazelLadder, 1, 3),
				new Object[] {
						"M M",
						"MFM",
						"M M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
						'F', Item.silk
				});
		
		// Button
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.hazelButton),
				new Object[] {
						"C",
						"R",
						'C', new ItemStack(BTWItems.woodCornerStubID, 1, WoodTypeHelper.HAZEL_WOOD_TYPE),
						'R', Item.redstone
				});
	}
	
	private static void initPaintedWoodRecipes() {
		
		//------ Pastel Planks ------//
		
		RecipeManager.addStokedCauldronRecipe(new ItemStack(BTWItems.potash),
				new ItemStack[] {
						new ItemStack(DecoBlocks.pastelPlanks, 6, InventoryUtils.IGNORE_METADATA)
				});
		
		for (int color = 0; color < 16; color++) {
			RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.pastelPlanks, 8, color),
					new ItemStack[] {
							new ItemStack(Block.planks, 8, InventoryUtils.IGNORE_METADATA),
							new ItemStack(Item.dyePowder, 1, color)
					});
			RecipeManager.addCauldronRecipe(new ItemStack(DecoBlocks.pastelPlanks, 8, color),
					new ItemStack[] {
							new ItemStack(Block.planks, 8, InventoryUtils.IGNORE_METADATA),
							new ItemStack(Item.dyePowder, 1, color + 16)
					});
			
			// Sub Blocks
			RecipeManager.addSubBlockRecipesToSaw(DecoBlocks.pastelPlanks, color,
					DecoBlocks.pastelPlanksSidingAndCorner[color],
					DecoBlocks.pastelPlanksMoulding[color]);
			
			// Combining
			RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.pastelPlanks, 1, color),
					new ItemStack[] {
							new ItemStack(DecoBlocks.pastelPlanksSidingAndCorner[color]),
							new ItemStack(DecoBlocks.pastelPlanksSidingAndCorner[color])
					});
			RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.pastelPlanksSidingAndCorner[color]),
					new ItemStack[] {
							new ItemStack(DecoBlocks.pastelPlanksMoulding[color]),
							new ItemStack(DecoBlocks.pastelPlanksMoulding[color])
					});
			RecipeManager.addShapelessRecipe(new ItemStack(DecoBlocks.pastelPlanksMoulding[color]),
					new ItemStack[] {
							new ItemStack(DecoBlocks.pastelPlanksSidingAndCorner[color], 1, 1), // corner
							new ItemStack(DecoBlocks.pastelPlanksSidingAndCorner[color], 1, 1)  // corner
					});
			
			Block slabBlock = color < 8 ? DecoBlocks.pastelPlanksSlab : DecoBlocks.pastelPlanksSlab2;
			
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.pastelPlanks, 1, color),
					new Object[] {
							"S",
							"S",
							'S', new ItemStack(slabBlock, 1, color % 8)
					});
			
			// Stairs and slabs
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.pastelPlanksStairs[color], 6),
					new Object[] {
							"F  ",
							"FF ",
							"FFF",
							'F', new ItemStack(DecoBlocks.pastelPlanks, 1, color)
					});
			RecipeManager.addRecipe(new ItemStack(DecoBlocks.pastelPlanksStairs[color], 1),
					new Object[] {
							"M ",
							"MM",
							'M', new ItemStack(DecoBlocks.pastelPlanksMoulding[color])
					});
			
			RecipeManager.addRecipe(new ItemStack(slabBlock, 6, color % 8),
					new Object[] {
							"FFF",
							'F', new ItemStack(DecoBlocks.pastelPlanks, 1, color)
					});
			
			// Decorative
			RecipeManager.addRecipe(
					new ItemStack(DecoBlocks.pastelPlanksMoulding[color], 6, MouldingAndDecorativeBlock.SUBTYPE_PEDESTAL_UP),
					new Object[] {
							" S ",
							"FFF",
							"FFF",
							'S', new ItemStack(DecoBlocks.pastelPlanksMoulding[color]),
							'F', new ItemStack(DecoBlocks.pastelPlanks, 1, color)
					});
			RecipeManager.addRecipe(
					new ItemStack(DecoBlocks.pastelPlanksMoulding[color], 1, MouldingAndDecorativeBlock.SUBTYPE_COLUMN),
					new Object[] {
							"M",
							"M",
							"M",
							'M', new ItemStack(DecoBlocks.pastelPlanksMoulding[color])
					});
			RecipeManager.addRecipe(
					new ItemStack(DecoBlocks.pastelPlanksMoulding[color], 4, MouldingAndDecorativeBlock.SUBTYPE_TABLE),
					new Object[] {
							"SSS",
							" M ",
							" M ",
							'S', new ItemStack(DecoBlocks.pastelPlanksSidingAndCorner[color]),
							'M', new ItemStack(DecoBlocks.pastelPlanksMoulding[color])
					});
			
			RecipeManager.addRecipe(
					new ItemStack(DecoBlocks.pastelPlanksSidingAndCorner[color], 4, SidingAndCornerAndDecorativeBlock.SUBTYPE_BENCH),
					new Object[] {
							"SSS",
							" M ",
							'S', new ItemStack(DecoBlocks.pastelPlanksSidingAndCorner[color]),
							'M', new ItemStack(DecoBlocks.pastelPlanksMoulding[color])
					});
			RecipeManager.addRecipe(
					new ItemStack(DecoBlocks.pastelPlanksSidingAndCorner[color], 6, SidingAndCornerAndDecorativeBlock.SUBTYPE_FENCE),
					new Object[] {
							"FFF",
							"FFF",
							'F', new ItemStack(DecoBlocks.pastelPlanks, 1, color)
					});
			RecipeManager.addRecipe(
					new ItemStack(DecoBlocks.pastelPlanksSidingAndCorner[color], 2, SidingAndCornerAndDecorativeBlock.SUBTYPE_FENCE),
					new Object[] {
							"MMM",
							'M', new ItemStack(DecoBlocks.pastelPlanksMoulding[color])
					});
		}
	}
	
	private static void addStorage(ItemStack block, ItemStack item) {
		RecipeManager.addRecipe(block,
				new Object[] {
						"III",
						"III",
						"III",
						'I', item
				});
		
		RecipeManager.addShapelessRecipe(new ItemStack(item.itemID, 9, item.getItemDamage()),
				new ItemStack[] {
						block
				});
	}
	
	private static void addMortaringRecipe(ItemStack output, ItemStack input) {
		RecipeManager.addStokedCauldronRecipe(output,
				new ItemStack[] {
						input,
						new ItemStack(Item.clay)
				});
		
		RecipeManager.addStokedCauldronRecipe(output,
				new ItemStack[] {
						input,
						new ItemStack(Item.slimeBall)
				});
		
		RecipeManager.addStokedCauldronRecipe(output,
				new ItemStack[] {
						input,
						new ItemStack(BTWItems.netherSludge)
				});
	}
	
	private static void addChiselingRecipe(ItemStack output, ItemStack input) {
		RecipeManager.addShapelessRecipe(input,
				new ItemStack[] {
						output,
						new ItemStack(BTWItems.ironChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
		
		RecipeManager.addShapelessRecipe(input,
				new ItemStack[] {
						output,
						new ItemStack(BTWItems.diamondChisel, 1, InventoryUtils.IGNORE_METADATA)
				});
	}
	
	private static void addSubBlockRecipes(Block baseBlock, int baseMetadata, Block sidingAndCorner, Block moulding, Block stairs, int slab, int slabMetadata) {
		addSubBlockRecipes(baseBlock, baseMetadata, sidingAndCorner, moulding, stairs, Block.blocksList[slab], slabMetadata, true);
	}
	
	private static void addSubBlockRecipes(Block baseBlock, int baseMetadata, Block sidingAndCorner, Block moulding, Block stairs, Block slab, int slabMetadata) {
		addSubBlockRecipes(baseBlock, baseMetadata, sidingAndCorner, moulding, stairs, slab, slabMetadata, true);
	}
	
	private static void addSubBlockRecipes(Block baseBlock, int baseMetadata, Block sidingAndCorner, Block moulding, Block stairs, int slab, int slabMetadata,
			boolean includeFence)
	{
		addSubBlockRecipes(baseBlock, baseMetadata, sidingAndCorner, moulding, stairs, Block.blocksList[slab], slabMetadata, includeFence);
	}
	
	private static void addSubBlockRecipes(Block baseBlock, int baseMetadata, Block sidingAndCorner, Block moulding, Block stairs, Block slab, int slabMetadata,
			boolean includeFence)
	{
		btw.crafting.recipe.CraftingRecipeList.addSubBlockRecipesOfType(baseBlock, baseMetadata, sidingAndCorner, moulding, true);
		
		RecipeManager.addRecipe(new ItemStack(stairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(baseBlock, 1, baseMetadata)
				});
		RecipeManager.addRecipe(new ItemStack(stairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(baseBlock, 1, baseMetadata)
				});
		
		RecipeManager.addRecipe(new ItemStack(slab, 6, slabMetadata),
				new Object[] {
						"FFF",
						'F', new ItemStack(baseBlock, 1, baseMetadata)
				});
		RecipeManager.addRecipe(new ItemStack(baseBlock, 1, baseMetadata),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(slab, 1, slabMetadata)
				});
	}
	
	private static void addWoodenSubBlockRecipes(int woodType, Block sidingAndCorner, Block moulding, Block stairs, Block slab, int slabMetadata) {
		// Base sub blocks
		RecipeManager.addSubBlockRecipesToSaw(Block.planks, woodType,
				sidingAndCorner, moulding,
				BTWItems.woodSidingStubID, BTWItems.woodMouldingStubID, BTWItems.woodCornerStubID,
				woodType);
		RecipeManager.addSawRecipe(
				new ItemStack[] {
						new ItemStack(BTWItems.woodMouldingStubID, 1, woodType),
						new ItemStack(BTWItems.woodSidingStubID, 1, woodType)
				},
				stairs);
		RecipeManager.addSawRecipe(new ItemStack(BTWItems.woodMouldingStubID, 1, woodType),
				slab, slabMetadata);
		RecipeManager.addSawRecipe(new ItemStack(BTWItems.woodMouldingStubID, 1, woodType),
				slab, slabMetadata | 8);
		
		// Combining
		RecipeManager.addShapelessRecipe(new ItemStack(Block.planks, 1, woodType),
				new ItemStack[] {
						new ItemStack(BTWItems.woodSidingStubID, 1, woodType),
						new ItemStack(BTWItems.woodSidingStubID, 1, woodType)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.woodSidingStubID, 1, woodType),
				new ItemStack[] {
						new ItemStack(BTWItems.woodMouldingStubID, 1, woodType),
						new ItemStack(BTWItems.woodMouldingStubID, 1, woodType)
				});
		RecipeManager.addShapelessRecipe(new ItemStack(BTWItems.woodMouldingStubID, 1, woodType),
				new ItemStack[] {
						new ItemStack(BTWItems.woodCornerStubID, 1, woodType),
						new ItemStack(BTWItems.woodCornerStubID, 1, woodType)
				});
		RecipeManager.addRecipe(new ItemStack(Block.planks, 1, woodType),
				new Object[] {
						"S",
						"S",
						'S', new ItemStack(slab, 1, slabMetadata)
				});
		
		// Stairs and slabs
		RecipeManager.addRecipe(new ItemStack(stairs, 6),
				new Object[] {
						"F  ",
						"FF ",
						"FFF",
						'F', new ItemStack(Block.planks, 1, woodType)
				});
		RecipeManager.addRecipe(new ItemStack(stairs, 1),
				new Object[] {
						"M ",
						"MM",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, woodType)
				});
		
		RecipeManager.addRecipe(new ItemStack(Block.woodSingleSlab, 6, woodType),
				new Object[] {
						"FFF",
						'F', new ItemStack(Block.planks, 1, woodType)
				});
		
		// Decorative
		RecipeManager.addRecipe(
				new ItemStack(BTWItems.woodMouldingDecorativeStubID, 6,
						WoodMouldingDecorativeStubBlockItem.getItemDamageForType(woodType, WoodMouldingDecorativeStubBlockItem.TYPE_PEDESTAL)),
				new Object[] {
						" S ",
						"FFF",
						"FFF",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, woodType),
						'F', new ItemStack(Block.planks, 1, woodType)
				});
		RecipeManager.addRecipe(
				new ItemStack(BTWItems.woodMouldingDecorativeStubID, 1,
						WoodMouldingDecorativeStubBlockItem.getItemDamageForType(woodType, WoodMouldingDecorativeStubBlockItem.TYPE_COLUMN)),
				new Object[] {
						"M",
						"M",
						"M",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, woodType)
				});
		RecipeManager.addRecipe(
				new ItemStack(BTWItems.woodMouldingDecorativeStubID, 4,
						WoodMouldingDecorativeStubBlockItem.getItemDamageForType(woodType, WoodMouldingDecorativeStubBlockItem.TYPE_TABLE)),
				new Object[] {
						"SSS",
						" M ",
						" M ",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, woodType),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, woodType)
				});
		
		RecipeManager.addRecipe(
				new ItemStack(BTWItems.woodSidingDecorativeStubID, 4,
						WoodSidingDecorativeStubBlockItem.getItemDamageForType(woodType, WoodSidingDecorativeStubBlockItem.TYPE_BENCH)),
				new Object[] {
						"SSS",
						" M ",
						'S', new ItemStack(BTWItems.woodSidingStubID, 1, woodType),
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, woodType)
				});
		RecipeManager.addRecipe(
				new ItemStack(BTWItems.woodSidingDecorativeStubID, 6,
						WoodSidingDecorativeStubBlockItem.getItemDamageForType(woodType, WoodSidingDecorativeStubBlockItem.TYPE_FENCE)),
				new Object[] {
						"FFF",
						"FFF",
						'F', new ItemStack(Block.planks, 1, woodType)
				});
		RecipeManager.addRecipe(
				new ItemStack(BTWItems.woodSidingDecorativeStubID, 2,
						WoodSidingDecorativeStubBlockItem.getItemDamageForType(woodType, WoodSidingDecorativeStubBlockItem.TYPE_FENCE)),
				new Object[] {
						"MMM",
						'M', new ItemStack(BTWItems.woodMouldingStubID, 1, woodType)
				});
	}
	
	private static void removeSubBlockRecipes(Block baseBlock, int inputMetadata, int outputMetadata, Block sidingAndCorner, Block moulding, boolean useFence) {
		RecipeManager.removeSoulforgeRecipe(new ItemStack(sidingAndCorner, 8, 0), new Object[] {"####", '#', new ItemStack(baseBlock, 1, inputMetadata)});
		RecipeManager.removeSoulforgeRecipe(new ItemStack(moulding, 8, 0), new Object[] {"####", '#', new ItemStack(sidingAndCorner, 1, 0)});
		RecipeManager.removeSoulforgeRecipe(new ItemStack(sidingAndCorner, 8, 1), new Object[] {"####", '#', new ItemStack(moulding, 1, 0)});
		RecipeManager.removeVanillaRecipe(new ItemStack(moulding, 1, 12), new Object[] {"M", "M", "M", 'M', new ItemStack(moulding, 1, 0)});
		RecipeManager.removeVanillaRecipe(new ItemStack(moulding, 6, 13), new Object[] {" S ", "###", "###", '#', new ItemStack(baseBlock, 1, inputMetadata), 'S', new ItemStack(sidingAndCorner, 8, 0)});
		RecipeManager.removeVanillaRecipe(new ItemStack(moulding, 4, 15), new Object[] {"###", " X ", " X ", '#', new ItemStack(sidingAndCorner, 1, 0), 'X', new ItemStack(moulding, 1, 0)});
		RecipeManager.removeVanillaRecipe(new ItemStack(sidingAndCorner, 4, 12), new Object[] {"###", " X ", '#', new ItemStack(sidingAndCorner, 1, 0), 'X', new ItemStack(moulding, 1, 0)});
		
		if (useFence) {
			RecipeManager.removeVanillaRecipe(new ItemStack(sidingAndCorner, 6, 14), new Object[] {"###", "###", '#', new ItemStack(baseBlock, 1, inputMetadata)});
			RecipeManager.removeVanillaRecipe(new ItemStack(sidingAndCorner, 2, 14), new Object[] {"###", '#', new ItemStack(moulding, 1, 0)});
		}
		
		RecipeManager.removeVanillaShapelessRecipe(new ItemStack(baseBlock, 1, outputMetadata), new Object[] {new ItemStack(sidingAndCorner, 1, 0), new ItemStack(sidingAndCorner, 1, 0)});
		RecipeManager.removeVanillaShapelessRecipe(new ItemStack(sidingAndCorner, 1, 0), new Object[] {new ItemStack(moulding, 1, 0), new ItemStack(moulding, 1, 0)});
		RecipeManager.removeVanillaShapelessRecipe(new ItemStack(moulding, 1, 0), new Object[] {new ItemStack(sidingAndCorner, 1, 1), new ItemStack(sidingAndCorner, 1, 1)});
	}
}