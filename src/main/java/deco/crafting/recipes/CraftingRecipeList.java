package deco.crafting.recipes;

import btw.block.BTWBlocks;
import btw.block.blocks.AestheticOpaqueBlock;
import btw.block.blocks.MouldingAndDecorativeBlock;
import btw.block.blocks.SidingAndCornerAndDecorativeBlock;
import btw.block.blocks.SidingAndCornerBlock;
import btw.crafting.recipe.RecipeManager;
import btw.inventory.util.InventoryUtils;
import btw.item.BTWItems;
import btw.item.blockitems.WoodMouldingDecorativeStubBlockItem;
import btw.item.blockitems.WoodSidingDecorativeStubBlockItem;
import deco.block.DecoBlocks;
import deco.block.blocks.DecoLogBlock;
import deco.block.blocks.FilledBarrelBlock;
import deco.block.util.WoodTypeHelper;
import deco.item.DecoItems;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class CraftingRecipeList {
	public static void initRecipes() {
		initGeneralRecipes();
		initWoodTypeRecipes();
	}
	
	private static void initGeneralRecipes() {
		RecipeManager.addShapelessRecipe(new ItemStack(DecoItems.plainBook),
				new ItemStack[] {
						new ItemStack(Item.paper)
				});
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
		
		initOakWoodRecipes();
		initSpruceWoodRecipes();
		initBirchWoodRecipes();
		initJungleWoodRecipes();
		initBloodWoodRecipes();
		initCherryWoodRecipes();
		initAcaciaWoodRecipes();
		initMahoganyWoodRecipes();
		initMangroveWoodRecipes();
		
		initPaintedWoodRecipes();
	}
	
	private static void initOakWoodRecipes() {
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(Block.trapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.OAK_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(Block.trapdoor),
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
	}
	
	private static void initSpruceWoodRecipes() {
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.spruceTrapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.SPRUCE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.spruceTrapdoor),
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
	}
	
	private static void initBirchWoodRecipes() {
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.birchTrapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.BIRCH_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.birchTrapdoor),
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
	}
	
	private static void initJungleWoodRecipes() {
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.jungleTrapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.JUNGLE_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.jungleTrapdoor),
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
	}
	
	private static void initBloodWoodRecipes() {
		// Trapdoor
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.bloodTrapdoor),
				new Object[] {
						"SPP",
						"SPP",
						'S', Item.stick,
						'P', new ItemStack(Block.planks, 1, WoodTypeHelper.BLOOD_WOOD_TYPE)
				});
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.bloodTrapdoor),
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
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.cherryTrapdoor),
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
		
		// Bark
		RecipeManager.addCauldronRecipe(new ItemStack(BTWItems.tannedLeather),
				new ItemStack[] {
						new ItemStack(BTWItems.scouredLeather),
						new ItemStack(BTWItems.dung),
						new ItemStack(BTWItems.bark, 3, WoodTypeHelper.CHERRY_WOOD_TYPE)
				});
		RecipeManager.addCauldronRecipe(new ItemStack(BTWItems.cutTannedLeather, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.cutScouredLeather, 2),
						new ItemStack(BTWItems.dung),
						new ItemStack(BTWItems.bark, 3, WoodTypeHelper.CHERRY_WOOD_TYPE)
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
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.acaciaTrapdoor),
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
		
		// Bark
		RecipeManager.addCauldronRecipe(new ItemStack(BTWItems.tannedLeather),
				new ItemStack[] {
						new ItemStack(BTWItems.scouredLeather),
						new ItemStack(BTWItems.dung),
						new ItemStack(BTWItems.bark, 8, WoodTypeHelper.ACACIA_WOOD_TYPE)
				});
		RecipeManager.addCauldronRecipe(new ItemStack(BTWItems.cutTannedLeather, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.cutScouredLeather, 2),
						new ItemStack(BTWItems.dung),
						new ItemStack(BTWItems.bark, 8, WoodTypeHelper.ACACIA_WOOD_TYPE)
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
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mahoganyTrapdoor),
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
		
		// Bark
		RecipeManager.addCauldronRecipe(new ItemStack(BTWItems.tannedLeather),
				new ItemStack[] {
						new ItemStack(BTWItems.scouredLeather),
						new ItemStack(BTWItems.dung),
						new ItemStack(BTWItems.bark, 8, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
				});
		RecipeManager.addCauldronRecipe(new ItemStack(BTWItems.cutTannedLeather, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.cutScouredLeather, 2),
						new ItemStack(BTWItems.dung),
						new ItemStack(BTWItems.bark, 8, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
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
		RecipeManager.addRecipe(new ItemStack(DecoBlocks.mangroveTrapdoor),
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
		
		// Bark
		RecipeManager.addCauldronRecipe(new ItemStack(BTWItems.tannedLeather),
				new ItemStack[] {
						new ItemStack(BTWItems.scouredLeather),
						new ItemStack(BTWItems.dung),
						new ItemStack(BTWItems.bark, 8, WoodTypeHelper.MANGROVE_WOOD_TYPE)
				});
		RecipeManager.addCauldronRecipe(new ItemStack(BTWItems.cutTannedLeather, 2),
				new ItemStack[] {
						new ItemStack(BTWItems.cutScouredLeather, 2),
						new ItemStack(BTWItems.dung),
						new ItemStack(BTWItems.bark, 8, WoodTypeHelper.MANGROVE_WOOD_TYPE)
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
}