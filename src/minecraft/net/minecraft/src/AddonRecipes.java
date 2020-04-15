package net.minecraft.src;

public class AddonRecipes {
	public static final AddonRecipes instance = new AddonRecipes();
	
	private AddonRecipes() {
		
	}
	
	public void addAllAddonRecipes() {
		addClayRecipes();
		addChairRecipes();
		addGlassRecipes();
		addFlowerRecipes();
		addStoneRecipes();
		addWoodRecipes();
		addDecoRecipes();
		addMortarRecipes();
		addToolRecipes();
		addCustomRecipeClasses();
	}
	
	private void addClayRecipes() {
		AddonDefs.unfiredTerracotta.SetCanBeCookedByKiln(true).SetItemIndexDroppedWhenCookedByKiln(AddonDefs.terracotta.blockID);
		for (int i = 0; i < 32; i++)
		{
			FCRecipes.AddCauldronRecipe(new ItemStack(AddonDefs.stainedTerracotta, 8, i), new ItemStack[] { new ItemStack(AddonDefs.terracotta, 8), new ItemStack(Item.dyePowder, 1, i) });
		}
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.unfiredTerracotta, 1), new Object[] {"###", "#@#", "###", '#', FCBetterThanWolves.fcItemPileSand, '@', Item.clay});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.unfiredTerracotta, 1), new Object[] {"###", "#@#", "###", '#', FCBetterThanWolves.fcItemPileSand, '@', FCBetterThanWolves.fcItemNetherSludge});
		
		//Sub-blocks
		FCRecipes.AddAnvilRecipe(new ItemStack(AddonDefs.terracottaSidingAndCorner, 8, 0), new Object[] {"####", '#', new ItemStack(AddonDefs.terracotta, 1, 0)});
		FCRecipes.AddAnvilRecipe(new ItemStack(AddonDefs.terracottaMouldingAndDecorative, 8, 0), new Object[] {"####", '#', new ItemStack(AddonDefs.terracottaSidingAndCorner, 1, 0)});
		FCRecipes.AddAnvilRecipe(new ItemStack(AddonDefs.terracottaSidingAndCorner, 8, 1), new Object[] {"####", '#', new ItemStack(AddonDefs.terracottaMouldingAndDecorative, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.terracottaMouldingAndDecorative, 1, 12), new Object[] {"M", "M", "M", 'M', new ItemStack(AddonDefs.terracottaMouldingAndDecorative, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.terracottaMouldingAndDecorative, 6, 13), new Object[] {" S ", "###", "###", '#', new ItemStack(AddonDefs.terracotta, 1, 0), 'S', new ItemStack(AddonDefs.terracottaSidingAndCorner, 8, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.terracottaMouldingAndDecorative, 4, 15), new Object[] {"###", " X ", " X ", '#', new ItemStack(AddonDefs.terracottaSidingAndCorner, 1, 0), 'X', new ItemStack(AddonDefs.terracottaMouldingAndDecorative, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.terracottaSidingAndCorner, 4, 12), new Object[] {"###", " X ", '#', new ItemStack(AddonDefs.terracottaSidingAndCorner, 1, 0), 'X', new ItemStack(AddonDefs.terracottaMouldingAndDecorative, 1, 0)});

		FCRecipes.AddRecipe(new ItemStack(AddonDefs.terracottaSidingAndCorner, 2, 14), new Object[] {"###", "###", '#', new ItemStack(AddonDefs.terracotta, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.terracottaSidingAndCorner, 2, 14), new Object[] {"###", '#', new ItemStack(AddonDefs.terracottaMouldingAndDecorative, 1, 0)});

		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.terracotta, 1, 0), new Object[] {new ItemStack(AddonDefs.terracottaSidingAndCorner, 1, 0), new ItemStack(AddonDefs.terracottaSidingAndCorner, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.terracottaSidingAndCorner, 1, 0), new Object[] {new ItemStack(AddonDefs.terracottaMouldingAndDecorative, 1, 0), new ItemStack(AddonDefs.terracottaMouldingAndDecorative, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.terracottaMouldingAndDecorative, 1, 0), new Object[] {new ItemStack(AddonDefs.terracottaSidingAndCorner, 1, 1), new ItemStack(AddonDefs.terracottaSidingAndCorner, 1, 1)});

		//Stairs
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.terracottaStairs, 4, 0), new Object[]{"#  ","## ","###",'#',new ItemStack(AddonDefs.terracotta, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.terracottaStairs, 1, 0), new Object[]{"# ","##",'#',new ItemStack(AddonDefs.terracottaMouldingAndDecorative, 1, 0)});
		
		for(int i = 0; i < 16; i++){
			//Sub blocks
			FCRecipes.AddAnvilRecipe(new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 8, 0), new Object[] {"####", '#', new ItemStack(AddonDefs.stainedTerracotta, 1, i)});
			FCRecipes.AddAnvilRecipe(new ItemStack(AddonDefs.stainedTerracottaMouldingAndDecorative[i], 8, 0), new Object[] {"####", '#', new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 1, 0)});
			FCRecipes.AddAnvilRecipe(new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 8, 1), new Object[] {"####", '#', new ItemStack(AddonDefs.stainedTerracottaMouldingAndDecorative[i], 1, 0)});

			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stainedTerracottaMouldingAndDecorative[i], 1, 12), new Object[] {"M", "M", "M", 'M', new ItemStack(AddonDefs.stainedTerracottaMouldingAndDecorative[i], 1, 0)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stainedTerracottaMouldingAndDecorative[i], 6, 13), new Object[] {" S ", "###", "###", '#', new ItemStack(AddonDefs.stainedTerracotta, 1, i), 'S', new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 8, 0)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stainedTerracottaMouldingAndDecorative[i], 4, 15), new Object[] {"###", " X ", " X ", '#', new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 1, 0), 'X', new ItemStack(AddonDefs.stainedTerracottaMouldingAndDecorative[i], 1, 0)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 4, 12), new Object[] {"###", " X ", '#', new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 1, 0), 'X', new ItemStack(AddonDefs.stainedTerracottaMouldingAndDecorative[i], 1, 0)});

			//Fences
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 2, 14), new Object[] {"###", "###", '#', new ItemStack(AddonDefs.stainedTerracotta, 1, i)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 2, 14), new Object[] {"###", '#', new ItemStack(AddonDefs.stainedTerracottaMouldingAndDecorative[i], 1, 0)});

			FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.stainedTerracotta, 1, i), new Object[] {new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 1, 0), new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 1, 0)});
			FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 1, 0), new Object[] {new ItemStack(AddonDefs.stainedTerracottaMouldingAndDecorative[i], 1, 0), new ItemStack(AddonDefs.stainedTerracottaMouldingAndDecorative[i], 1, 0)});
			FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.stainedTerracottaMouldingAndDecorative[i], 1, 0), new Object[] {new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 1, 1), new ItemStack(AddonDefs.stainedTerracottaSidingAndCorner[i], 1, 1)});

			//Stairs
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stainedTerracottaStairs[i], 4, 0), new Object[]{"#  ","## ","###",'#',new ItemStack(AddonDefs.stainedTerracotta, 1, i)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stainedTerracottaStairs[i], 1, 0), new Object[]{"# ","##",'#',new ItemStack(AddonDefs.stainedTerracottaMouldingAndDecorative[i], 1, 0)});
			
			//Glazed Terracotta
			FCRecipes.AddStokedCrucibleRecipe(new ItemStack(AddonDefs.glazedTerracotta[i]), new ItemStack[] {new ItemStack(AddonDefs.stainedTerracotta, 1, i)});
		}
	}
	
	private void addChairRecipes() {
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.oakWoodChair, 4), new Object[] {"#  ", "###","X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.birchWoodChair, 4), new Object[] {"#  ", "###","X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 2)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.spruceWoodChair, 4), new Object[] {"#  ", "###","X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.jungleWoodChair, 4), new Object[] {"#  ", "###","X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 3)});
	}
	
	private void addGlassRecipes() {
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Block.glass, 1), new ItemStack[] {new ItemStack(AddonDefs.glassChunk, 4)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.glassChunk, 4), new Object[]{new ItemStack(Block.glass, 1)});

		FCRecipes.RemoveVanillaRecipe(new ItemStack(Item.glassBottle, 3), new Object[] {"# #", " # ", '#', Block.glass});
		FCRecipes.AddRecipe(new ItemStack(Item.glassBottle, 3), new Object[] {" # ", "# #", "###", '#', AddonDefs.glassChunk});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(AddonDefs.glassChunk, 2), new ItemStack[] {new ItemStack(Item.glassBottle, 1)});
		
		for (int Index = 0; Index < 32; Index++) {
			FCRecipes.AddCauldronRecipe(new ItemStack(AddonDefs.stainedGlassItem, 8, Index % 16), new ItemStack[] {new ItemStack(Block.glass, 8), new ItemStack(Item.dyePowder, 1, Index)});
		}
	}
	
	private void addFlowerRecipes() {
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Item.flowerPot, 1), new Object[] {"# #", " # ", '#', Item.brick});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.flowerPot, 1), new ItemStack[]{new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcItemUrn)});
		for (int i = 0; i < 16; ++i)
			FCRecipes.AddShapelessRecipe(new ItemStack(Block.cloth, 1, BlockCloth.getDyeFromBlock(i)), new Object[] {new ItemStack(Item.dyePowder, 1, i+16), new ItemStack(Item.itemsList[Block.cloth.blockID], 1, 0)});

		//Fertilizer
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.fertilizer, 2), new ItemStack[] { new ItemStack(Item.dyePowder, 1, 15), new ItemStack(FCBetterThanWolves.fcItemDung) });

		//Flower Recipes

		// Yucca
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 10)},	new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 0)});
		// Hyacinth
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 12)},	new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 1)});
		// Birds of Paridise
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 14)},	new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 2)});
		// Azalea
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 9) },	new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 3)});
		// Cornflower
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 20)},	new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 4)});
		// Lavender
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 5) },	new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 5)});
		// Honeysuckle
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 1, 31),new ItemStack(Item.dyePowder, 1, 11)},new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 6)});
		// Allium
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 13)},	new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 7)});
		// Blue Orchid
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 12)},	new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 8)});
		// Poppy
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 1) },	new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 9)});
		// Azure Bluet
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 1, 31),new ItemStack(Item.dyePowder, 1, 11)},new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 10)});
		// Daisy
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 1, 31),new ItemStack(Item.dyePowder, 1, 11)},new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 11)});
		// Peony
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 13)},	new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 12)});
		// Lilac
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 13)},	new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 13)});
		// Rose Bush
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 3, 1)},	new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 14)});
		// Blue Rose
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 20)},	new ItemStack[]{new ItemStack(AddonDefs.flower, 1, 15)});
		// Tulips
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 1, 14),new ItemStack(Item.dyePowder, 1, 1)},	new ItemStack[]{new ItemStack(AddonDefs.tulip, 1, 0)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 9)},	new ItemStack[]{new ItemStack(AddonDefs.tulip, 1, 1)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 14)},	new ItemStack[]{new ItemStack(AddonDefs.tulip, 1, 2)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 31)},	new ItemStack[]{new ItemStack(AddonDefs.tulip, 1, 3)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 20)},	new ItemStack[]{new ItemStack(AddonDefs.tulip, 1, 4)});

		//Cooking with dyes
		for (int Index = 0; Index < 16; Index++)
		{
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.cloth,1,BlockCloth.getDyeFromBlock(Index)),
				new ItemStack[]{new ItemStack(Item.dyePowder,1,Index+16),new ItemStack(Item.itemsList[Block.cloth.blockID], 1,0)});
			//FCRecipes.AddRecipe(new ItemStack(stainedGlassPane, 16, Index), new Object[] { "GGG", "GGG", 'G', new ItemStack(stainedGlass, 1, Index) });
			//FCRecipes.AddStokedCrucibleRecipe(new ItemStack(stainedGlass, 3, Index), new ItemStack[] { new ItemStack(stainedGlassPane, 8, Index) });
		}
		//Mixing dyes
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 2, 10), new Object[] {new ItemStack(Item.dyePowder, 1, 2), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 2, 8), new Object[] {new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 2, 7), new Object[] {new ItemStack(Item.dyePowder, 1, 8), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 2, 9), new Object[] {new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 2, 6), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 2)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 2, 5), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 1)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 3, 13), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 9)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 15)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 15)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 3, 7), new Object[] {new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.dyePowder, 1, 31), new ItemStack(Item.dyePowder, 1, 15)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 3, 7), new Object[] {new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.dyePowder, 1, 31), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 4, 13), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 15)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 4, 13), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Item.dyePowder, 4, 13), new Object[] {new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 31)});
	}
	
	private void addStoneRecipes() {
		//Stone types
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(AddonDefs.andesiteCobbleLoose, 8), new ItemStack[] {new ItemStack(AddonDefs.dioriteCobbleLoose, 8), new ItemStack(FCBetterThanWolves.fcItemStone, 8)});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(AddonDefs.andesiteCobbleLoose, 1), new ItemStack[] {new ItemStack(AddonDefs.dioriteCobbleLoose, 1), new ItemStack(FCBetterThanWolves.fcItemStone, 1)});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(AddonDefs.dioriteCobbleLoose, 8), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLoose, 8), new ItemStack(Item.netherQuartz)});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(AddonDefs.graniteCobbleLoose, 8), new ItemStack[] {new ItemStack(AddonDefs.dioriteCobbleLoose, 8), new ItemStack(Item.netherQuartz)});
		
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(AddonDefs.stoneTypesSmooth, 1, 0), new ItemStack[] {new ItemStack(AddonDefs.stoneTypes, 1, 0)});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(AddonDefs.stoneTypesSmooth, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.stoneTypes, 1, 1)});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(AddonDefs.stoneTypesSmooth, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.stoneTypes, 1, 2)});
		
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.graniteCobbleLoose), new ItemStack[] {new ItemStack(AddonDefs.stoneTypes, 1, 0), new ItemStack(AddonDefs.chiselDiamond, 1, 32767)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.graniteCobbleLoose), new ItemStack[] {new ItemStack(AddonDefs.stoneTypes, 1, 0), new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.andesiteCobbleLoose), new ItemStack[] {new ItemStack(AddonDefs.stoneTypes, 1, 1), new ItemStack(AddonDefs.chiselDiamond, 1, 32767)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.andesiteCobbleLoose), new ItemStack[] {new ItemStack(AddonDefs.stoneTypes, 1, 1), new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.dioriteCobbleLoose), new ItemStack[] {new ItemStack(AddonDefs.stoneTypes, 1, 2), new ItemStack(AddonDefs.chiselDiamond, 1, 32767)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.dioriteCobbleLoose), new ItemStack[] {new ItemStack(AddonDefs.stoneTypes, 1, 2), new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.graniteStoneBrickLoose), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesSmooth, 1, 0), new ItemStack(AddonDefs.chiselDiamond, 1, 32767)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.graniteStoneBrickLoose), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesSmooth, 1, 0), new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.andesiteStoneBrickLoose), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesSmooth, 1, 1), new ItemStack(AddonDefs.chiselDiamond, 1, 32767)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.andesiteStoneBrickLoose), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesSmooth, 1, 1), new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.dioriteStoneBrickLoose), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesSmooth, 1, 2), new ItemStack(AddonDefs.chiselDiamond, 1, 32767)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.dioriteStoneBrickLoose), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesSmooth, 1, 2), new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767)});
		
		for (int i = 0; i < 3; i++) {
			FCRecipes.AddSubBlockRecipesOfType(AddonDefs.stoneTypes, i, AddonDefs.stoneTypesSidingAndCorner[i], AddonDefs.stoneTypesMouldingAndDecorative[i], true);
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesStairs[i]), new Object[] {"# ", "##", '#', new ItemStack(AddonDefs.stoneTypesMouldingAndDecorative[i], 1, 0)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesStairs[i], 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(AddonDefs.stoneTypes, 1, i)});
			FCRecipes.AddSubBlockRecipesOfType(AddonDefs.stoneTypesSmooth, i, AddonDefs.stoneTypesSmoothSidingAndCorner[i], AddonDefs.stoneTypesSmoothMouldingAndDecorative[i], true);
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesSmoothStairs[i]), new Object[] {"# ", "##", '#', new ItemStack(AddonDefs.stoneTypesSmoothMouldingAndDecorative[i], 1, 0)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesSmoothStairs[i], 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(AddonDefs.stoneTypesSmooth, 1, i)});
			FCRecipes.AddSubBlockRecipesOfType(AddonDefs.stoneTypesCobble, i, AddonDefs.stoneTypesCobblestoneSidingAndCorner[i], AddonDefs.stoneTypesCobblestoneMouldingAndDecorative[i], true);
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesCobblestoneStairs[i]), new Object[] {"# ", "##", '#', new ItemStack(AddonDefs.stoneTypesCobblestoneMouldingAndDecorative[i], 1, 0)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesCobblestoneStairs[i], 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(AddonDefs.stoneTypesCobble, 1, i)});
			FCRecipes.AddSubBlockRecipesOfType(AddonDefs.stoneTypesStoneBrick, i, AddonDefs.stoneTypesStoneBrickSidingAndCorner[i], AddonDefs.stoneTypesStoneBrickMouldingAndDecorative[i], true);
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrickStairs[i]), new Object[] {"# ", "##", '#', new ItemStack(AddonDefs.stoneTypesStoneBrickMouldingAndDecorative[i], 1, 0)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrickStairs[i], 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(AddonDefs.stoneTypesStoneBrick, 1, i)});
		}
		
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab2, 6, 0), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.stoneTypes, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab2, 6, 1), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.stoneTypes, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab2, 6, 2), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.stoneTypes, 1, 2)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab2, 6, 3), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.stoneTypesSmooth, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab2, 6, 4), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.stoneTypesSmooth, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab2, 6, 5), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.stoneTypesSmooth, 1, 2)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab2, 6, 6), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.stoneTypesCobble, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab2, 6, 7), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.stoneTypesCobble, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab3, 6, 0), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.stoneTypesCobble, 1, 2)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab3, 6, 1), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.stoneTypesStoneBrick, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab3, 6, 2), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.stoneTypesStoneBrick, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab3, 6, 3), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.stoneTypesStoneBrick, 1, 2)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypes, 1, 0), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab2, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypes, 1, 1), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab2, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypes, 1, 2), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab2, 1, 2)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesSmooth, 1, 0), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab2, 1, 3)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesSmooth, 1, 1), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab2, 1, 4)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesSmooth, 1, 2), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab2, 1, 5)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesCobble, 1, 0), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab2, 1, 6)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesCobble, 1, 1), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab2, 1, 7)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesCobble, 1, 2), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab3, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrick, 1, 0), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab3, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrick, 1, 1), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab3, 1, 2)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrick, 1, 2), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab3, 1, 3)});
		
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseStairs[0], 4), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.graniteCobbleLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseStairs[0], 8), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.graniteCobbleLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseSlab, 4, 0), new Object[] {"XX", 'X', new ItemStack(AddonDefs.graniteCobbleLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseStairs[1], 4), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.andesiteCobbleLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseStairs[1], 8), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.andesiteCobbleLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseSlab, 4, 1), new Object[] {"XX", 'X', new ItemStack(AddonDefs.andesiteCobbleLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseStairs[2], 4), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.dioriteCobbleLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseStairs[2], 8), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.dioriteCobbleLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseSlab, 4, 2), new Object[] {"XX", 'X', new ItemStack(AddonDefs.dioriteCobbleLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseStairs[3], 4), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.graniteStoneBrickLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseStairs[3], 8), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.graniteStoneBrickLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseSlab, 4, 3), new Object[] {"XX", 'X', new ItemStack(AddonDefs.graniteStoneBrickLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseStairs[4], 4), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.andesiteStoneBrickLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseStairs[4], 8), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.andesiteStoneBrickLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseSlab, 4, 4), new Object[] {"XX", 'X', new ItemStack(AddonDefs.andesiteStoneBrickLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseStairs[5], 4), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.dioriteStoneBrickLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseStairs[5], 8), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.dioriteStoneBrickLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneTypesLooseSlab, 4, 5), new Object[] {"XX", 'X', new ItemStack(AddonDefs.dioriteStoneBrickLoose)});
		
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.graniteCobbleLoose), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.andesiteCobbleLoose), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.dioriteCobbleLoose), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 2)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.graniteStoneBrickLoose), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 3)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.andesiteStoneBrickLoose), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 4)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.dioriteStoneBrickLoose), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 5)});
		
		//White stone brick
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.whiteStoneBrick,4,0), new Object[]{"XX","XX",'X',new ItemStack(FCBetterThanWolves.fcAestheticOpaque,1,9)});
		FCRecipes.AddSubBlockRecipesOfType(AddonDefs.whiteStoneBrick, 0, AddonDefs.whiteBrickSidingAndCorner, AddonDefs.whiteBrickMouldingAndDecorative, true);
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.whiteBrickStairs), new Object[] {"# ", "##", '#', new ItemStack(AddonDefs.whiteBrickMouldingAndDecorative, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.whiteBrickStairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(AddonDefs.whiteStoneBrick)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab, 6, 5), new Object[] {"###", '#', new ItemStack(AddonDefs.whiteStoneBrick)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.whiteStoneBrick, 1, 0), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab, 1, 5)});
		
		//Red sand and sandstone
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.redSand),  new Object[] {"sss", "shs", "sss", 's', FCBetterThanWolves.fcItemPileSand, 'h', FCBetterThanWolves.fcItemHellfireDust});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.redSandSlab, 4), new Object[] {"XX", 'X', AddonDefs.redSand});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.redSand), new Object[] {"X", "X", 'X', AddonDefs.redSandSlab});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.redSand), new ItemStack[] {new ItemStack(AddonDefs.pileRedSand), new ItemStack(AddonDefs.pileRedSand), new ItemStack(AddonDefs.pileRedSand), new ItemStack(AddonDefs.pileRedSand), new ItemStack(AddonDefs.pileRedSand), new ItemStack(AddonDefs.pileRedSand), new ItemStack(AddonDefs.pileRedSand), new ItemStack(AddonDefs.pileRedSand)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pileRedSand, 8), new ItemStack[] {new ItemStack(AddonDefs.redSand)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.redSandSlab), new ItemStack[] {new ItemStack(AddonDefs.pileRedSand), new ItemStack(AddonDefs.pileRedSand), new ItemStack(AddonDefs.pileRedSand), new ItemStack(AddonDefs.pileRedSand)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pileRedSand, 4), new ItemStack[] {new ItemStack(AddonDefs.redSandSlab)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.redSandStone, 4, 2), new Object[] {"XX", "XX", 'X', new ItemStack(AddonDefs.redSandStone, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab, 6, 0), new Object[] {"###", '#', new ItemStack(AddonDefs.redSandStone)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.redSandStone), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab, 1, 0)});
		FCRecipes.AddSubBlockRecipesOfType(AddonDefs.redSandStone, 0, AddonDefs.redSandStoneSidingAndCorner, AddonDefs.redSandStoneMouldingAndDecorative, true);
		FCRecipes.AddSubBlockRecipesOfType(AddonDefs.redSandStone, 2, AddonDefs.redSandStoneSmoothSidingAndCorner, AddonDefs.redSandStoneSmoothMouldingAndDecorative, true);
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.redSandStoneStairs), new Object[] {"# ", "##", '#', new ItemStack(AddonDefs.redSandStoneMouldingAndDecorative, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.redSandStoneStairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(AddonDefs.redSandStone)});
		
		//Stone chiseling
        FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.sandStone, 1, 1), new Object[] {"#", "#", '#', new ItemStack(Block.stoneSingleSlab, 1, 1)});
        FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.blockNetherQuartz, 1, 1), new Object[] {"#", "#", '#', new ItemStack(Block.stoneSingleSlab, 1, 7)});
        FCRecipes.RemoveAnvilRecipe(new ItemStack(Block.stoneBrick, 12, 3), new Object[] {"####", "#  #", "#  #", "####", '#', Block.stoneBrick});

		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.whiteStoneBrick, 1, 3), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767), new ItemStack(AddonDefs.whiteStoneBrick, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.whiteStoneBrick, 1, 3), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(AddonDefs.whiteStoneBrick, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.redSandStone, 1, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767), new ItemStack(AddonDefs.redSandStone, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.redSandStone, 1, 1), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(AddonDefs.redSandStone, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Block.stoneBrick, 1, 3), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767), new ItemStack(Block.stoneBrick, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Block.stoneBrick, 1, 3), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(Block.stoneBrick, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Block.blockNetherQuartz, 1, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767), new ItemStack(Block.blockNetherQuartz, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Block.blockNetherQuartz, 1, 1), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(Block.blockNetherQuartz, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Block.sandStone, 1, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767), new ItemStack(Block.sandStone, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(Block.sandStone, 1, 1), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(Block.sandStone, 1, 0)});
		
		FCRecipes.AddRecipe(new ItemStack(Block.sandStone), new Object[] {"X", "X", 'X', new ItemStack(Block.stoneSingleSlab, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(Block.blockNetherQuartz), new Object[] {"X", "X", 'X', new ItemStack(Block.stoneSingleSlab, 1, 7)});
		
		//Sandstone subblocks
		this.RemoveSubBlockRecipes(Block.sandStone, 32767, 0, FCBetterThanWolves.fcBlockSandstoneSidingAndCorner, FCBetterThanWolves.fcBlockSandstoneMouldingAndDecorative, true);
		FCRecipes.AddSubBlockRecipesOfType(Block.sandStone, 0, FCBetterThanWolves.fcBlockSandstoneSidingAndCorner, FCBetterThanWolves.fcBlockSandstoneMouldingAndDecorative, true);
		FCRecipes.AddSubBlockRecipesOfType(Block.sandStone, 2, AddonDefs.sandstoneSmoothSidingAndCorner, AddonDefs.sandstoneSmoothMouldingAndDecorative, true);
		
		//Prismarine
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.prismarine, 1, 0), new Object[] {"XX", "XX", 'X', AddonDefs.prismarineShard});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.prismarine, 4, 1), new Object[] {"XX", "XX", 'X', new ItemStack(AddonDefs.prismarine, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.prismarine, 2, 2), new Object[] {"XXX", "X#X", "XXX", 'X', AddonDefs.prismarineShard, '#', new ItemStack(Item.dyePowder, 1, 0)});
		FCRecipes.AddSubBlockRecipesOfType(AddonDefs.prismarine, 0, AddonDefs.prismarineSidingAndCorner, AddonDefs.prismarineMouldingAndDecorative, true);
		FCRecipes.AddSubBlockRecipesOfType(AddonDefs.prismarine, 1, AddonDefs.prismarineBrickSidingAndCorner, AddonDefs.prismarineBrickMouldingAndDecorative, true);
		FCRecipes.AddSubBlockRecipesOfType(AddonDefs.prismarine, 2, AddonDefs.prismarineDarkSidingAndCorner, AddonDefs.prismarineDarkMouldingAndDecorative, true);
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.prismarineStairs,  4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.prismarine, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.prismarineBrickStairs,  4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.prismarine, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.prismarineDarkStairs,  4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.prismarine, 1, 2)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.prismarineStairs), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.prismarineMouldingAndDecorative, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.prismarineBrickStairs), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.prismarineBrickMouldingAndDecorative, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.prismarineDarkStairs), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.prismarineDarkMouldingAndDecorative, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab, 6, 1), new Object[] {"###", '#', new ItemStack(AddonDefs.prismarine, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab, 6, 2), new Object[] {"###", '#', new ItemStack(AddonDefs.prismarine, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab, 6, 3), new Object[] {"###", '#', new ItemStack(AddonDefs.prismarine, 1, 2)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.prismarine, 1, 0), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.prismarine, 1, 1), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab, 1, 2)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.prismarine, 1, 2), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab, 1, 3)});
		
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(AddonDefs.prismarineCrystal), new ItemStack[] {new ItemStack(AddonDefs.prismarineShard), new ItemStack(Item.lightStoneDust)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.prismarineLantern), new Object[] {"XXX", "X#X", "XXX", 'X', AddonDefs.prismarineShard, '#', new ItemStack(AddonDefs.prismarineCrystal)});
		
		//Nether Brick
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.netherBrickLoose), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemHellfireDust)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.netherBrickLooseStairs, 4), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.netherBrickLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.netherBrickLooseStairs, 8), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.netherBrickLoose)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.netherBrickLooseSlab, 4), new Object[] {"XX", 'X', new ItemStack(AddonDefs.netherBrickLoose)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.netherBrick, 1, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767), new ItemStack(AddonDefs.netherBrick, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.netherBrick, 1, 1), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(AddonDefs.netherBrick, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.netherBrick, 1, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767), new ItemStack(Block.netherBrick, 1, 0)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.netherBrick, 1, 2), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(Block.netherBrick, 1, 0)});
		FCRecipes.AddSubBlockRecipesOfType(AddonDefs.netherBrick, 0, AddonDefs.netherBrickSidingAndCorner, AddonDefs.netherBrickMouldingAndDecorative, true);
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.netherBrickStairs, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.netherBrick, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.netherBrickStairs, 1), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.netherBrickMouldingAndDecorative, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab, 6, 7), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.netherBrick)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.netherBrick, 1, 0), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab, 1, 7)});
		
		//Basalt
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(AddonDefs.basalt, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.basalt, 1, 0)});
		
		//Infused stone
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.infusedStone, 1, 0), new ItemStack[] {new ItemStack(AddonDefs.basalt, 1, 0), new ItemStack(FCBetterThanWolves.fcItemSoulFlux)});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(AddonDefs.infusedStone, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.infusedStone, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.infusedStone, 1, 2), new Object[] {"XX", "XX", 'X', new ItemStack(AddonDefs.infusedStone, 1, 1)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.infusedStone, 1, 3), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767), new ItemStack(AddonDefs.infusedStone, 1, 1)});
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.infusedStone, 1, 3), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(AddonDefs.infusedStone, 1, 1)});
		FCRecipes.AddSubBlockRecipesOfType(AddonDefs.infusedStone, 0, AddonDefs.infusedStoneSidingAndCorner, AddonDefs.infusedStoneMouldingAndDecorative, true);
		FCRecipes.AddSubBlockRecipesOfType(AddonDefs.infusedStone, 1, AddonDefs.infusedStoneSmoothSidingAndCorner, AddonDefs.infusedStoneSmoothMouldingAndDecorative, true);
		FCRecipes.AddSubBlockRecipesOfType(AddonDefs.infusedStone, 2, AddonDefs.infusedStoneBrickSidingAndCorner, AddonDefs.infusedStoneBrickMouldingAndDecorative, true);
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab3, 6, 4), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.infusedStone)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.infusedStone, 1, 0), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab3, 1, 4)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab3, 6, 5), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.infusedStone, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.infusedStone, 1, 1), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab3, 1, 5)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab3, 6, 6), new Object[] {"XXX", 'X', new ItemStack(AddonDefs.infusedStone, 1, 2)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.infusedStone, 1, 2), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab3, 1, 6)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.infusedStoneStairs, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.infusedStone, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.infusedStoneStairs, 1), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.infusedStoneMouldingAndDecorative, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.infusedStoneSmoothStairs, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.infusedStone, 1, 1)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.infusedStoneSmoothStairs, 1), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.infusedStoneSmoothMouldingAndDecorative, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.infusedStoneBrickStairs, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.infusedStone, 1, 2)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.infusedStoneBrickStairs, 1), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.infusedStoneBrickMouldingAndDecorative, 1, 0)});
		
		//Polished Stone
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(AddonDefs.polishedStone), new ItemStack[] {new ItemStack(Block.stone)});
		FCRecipes.AddRecipe(new ItemStack(Block.stoneSingleSlab, 6, 0), new Object[] {"###", '#', new ItemStack(AddonDefs.polishedStone)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.polishedStoneStairs, 4), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(AddonDefs.polishedStone, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.polishedStoneStairs, 1), new Object[] {"X ", "XX", 'X', new ItemStack(AddonDefs.polishedStoneMouldingAndDecorative, 1, 0)});
		FCRecipes.AddSubBlockRecipesOfType(AddonDefs.polishedStone, 0, AddonDefs.polishedStoneSidingAndCorner, AddonDefs.polishedStoneMouldingAndDecorative, true);
		
		//Misc Subblocks
		FCRecipes.AddSubBlockRecipesOfType(Block.cobblestone, 0, AddonDefs.cobblestoneSidingAndCorner, AddonDefs.cobblestoneMouldingAndDecorative, false);
		FCRecipes.AddSubBlockRecipesOfType(Block.cobblestoneMossy, 0, AddonDefs.mossyCobblestoneSidingAndCorner, AddonDefs.mossyCobblestoneMouldingAndDecorative, false);
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab, 6, 4), new Object[] {"###", '#', new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 9)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab, 6, 6), new Object[] {"###", '#', new ItemStack(Block.cobblestoneMossy)});
		FCRecipes.AddRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 9), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab, 1, 4)});
		FCRecipes.AddRecipe(new ItemStack(Block.cobblestoneMossy, 1, 0), new Object[] {"X", "X", 'X', new ItemStack(AddonDefs.stoneSlab, 1, 6)});
		FCRecipes.AddRecipe(new ItemStack(Block.stairsCobblestone), new Object[] {"# ", "##", '#', new ItemStack(AddonDefs.cobblestoneMouldingAndDecorative, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.mossyCobblestoneStairs), new Object[] {"# ", "##", '#', new ItemStack(AddonDefs.mossyCobblestoneMouldingAndDecorative, 1, 0)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.mossyCobblestoneStairs, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Block.cobblestoneMossy)});
        FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.stoneSingleSlab, 6, 0), new Object[] {"###", '#', Block.stone});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.stoneSlab3, 6, 7), new Object[] {"###", '#', new ItemStack(Block.stone)});
	}
	
	private void addWoodRecipes() {
		//Stripped Logs
		for (int i = 0; i < 4; i++) {
			FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(AddonDefs.strippedLog, 1, i), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767), new ItemStack(Block.wood, 1, i)});
			FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(AddonDefs.strippedLog, 1, i), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(Block.wood, 1, i)});
			FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(AddonDefs.barkLogStripped, 1, i), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767), new ItemStack(AddonDefs.barkLog, 1, i)});
			FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(AddonDefs.barkLogStripped, 1, i), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(AddonDefs.barkLog, 1, i)});
			
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.barkLog, 4, i), new Object[] {"XX", "XX", 'X', new ItemStack(Block.wood, 1, i)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.barkLogStripped, 4, i), new Object[] {"XX", "XX", 'X', new ItemStack(AddonDefs.strippedLog, 1, i)});
		}
		
		//Trapdoors
        FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.trapdoor, 1), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', Block.planks});
        FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.trapdoor, 2), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767)});
        
        FCRecipes.AddRecipe(new ItemStack(Block.trapdoor, 1), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', new ItemStack(Block.planks, 1, 0)});
        FCRecipes.AddRecipe(new ItemStack(Block.trapdoor, 2), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.trapdoorSpruce, 1), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', new ItemStack(Block.planks, 1, 1)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.trapdoorSpruce, 2), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.trapdoorBirch, 1), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', new ItemStack(Block.planks, 1, 2)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.trapdoorBirch, 2), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.trapdoorJungle, 1), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', new ItemStack(Block.planks, 1, 3)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.trapdoorJungle, 2), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.trapdoorBlood, 1), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', new ItemStack(Block.planks, 1, 4)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.trapdoorBlood, 2), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 4)});
        
        //Doors
        FCRecipes.RemoveVanillaRecipe(new ItemStack(Item.doorWood, 1), new Object[] {"##", "##", "##", '#', Block.planks});
        FCRecipes.RemoveVanillaRecipe(new ItemStack(Item.doorWood, 1), new Object[] {"##", "##", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767)});

        FCRecipes.AddRecipe(new ItemStack(Item.doorWood, 1), new Object[] {"##", "##", "##", '#', new ItemStack(Block.planks, 1, 0)});
        FCRecipes.AddRecipe(new ItemStack(Item.doorWood, 1), new Object[] {"##", "##", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.itemDoorSpruce, 1), new Object[] {"##", "##", "##", '#', new ItemStack(Block.planks, 1, 1)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.itemDoorSpruce, 1), new Object[] {"##", "##", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.itemDoorBirch, 1), new Object[] {"##", "##", "##", '#', new ItemStack(Block.planks, 1, 2)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.itemDoorBirch, 1), new Object[] {"##", "##", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.itemDoorJungle, 1), new Object[] {"##", "##", "##", '#', new ItemStack(Block.planks, 1, 3)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.itemDoorJungle, 1), new Object[] {"##", "##", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.itemDoorBlood, 1), new Object[] {"##", "##", "##", '#', new ItemStack(Block.planks, 1, 4)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.itemDoorBlood, 1), new Object[] {"##", "##", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 4)});
        
        //Fence Gates
        FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.fenceGate, 1), new Object[] {"#W#", "#W#", '#', Item.stick, 'W', Block.planks});
        FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.fenceGate, 1), new Object[] {"#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767)});
        
        FCRecipes.AddRecipe(new ItemStack(Block.fenceGate, 1), new Object[] {"#W#", "#W#", '#', Item.stick, 'W', new ItemStack(Block.planks, 1, 0)});
        FCRecipes.AddRecipe(new ItemStack(Block.fenceGate, 1), new Object[] {"#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.gateSpruce, 1), new Object[] {"#W#", "#W#", '#', Item.stick, 'W', new ItemStack(Block.planks, 1, 1)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.gateSpruce, 1), new Object[] {"#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 1), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.gateBirch, 1), new Object[] {"#W#", "#W#", '#', Item.stick, 'W', new ItemStack(Block.planks, 1, 2)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.gateBirch, 1), new Object[] {"#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 2), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.gateJungle, 1), new Object[] {"#W#", "#W#", '#', Item.stick, 'W', new ItemStack(Block.planks, 1, 3)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.gateJungle, 1), new Object[] {"#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 3), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.gateBlood, 1), new Object[] {"#W#", "#W#", '#', Item.stick, 'W', new ItemStack(Block.planks, 1, 4)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.gateBlood, 1), new Object[] {"#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 4), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 4)});
        
        //Painted Planks
		for (int Index = 0; Index < 32; Index++)
		{
			FCRecipes.AddCauldronRecipe(new ItemStack(AddonDefs.planksPainted,8,Index % 16), new ItemStack[]{new ItemStack(Item.dyePowder,1,Index),new ItemStack(Block.planks, 8)});
		}
		
		for (int i = 0; i < 16; i++) {
			FCRecipes.AddCauldronRecipe(new ItemStack(Block.planks,  8), new ItemStack[] {new ItemStack(AddonDefs.woodBleach), new ItemStack(AddonDefs.planksPainted, 8)});
			
			//Sub blocks
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.paintedPlanksMouldingAndDecorative[i], 1, 12), new Object[] {"M", "M", "M", 'M', new ItemStack(AddonDefs.paintedPlanksMouldingAndDecorative[i], 1, 0)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.paintedPlanksMouldingAndDecorative[i], 6, 13), new Object[] {" S ", "###", "###", '#', new ItemStack(AddonDefs.planksPainted, 1, i), 'S', new ItemStack(AddonDefs.paintedPlanksSidingAndCorner[i], 8, 0)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.paintedPlanksMouldingAndDecorative[i], 4, 15), new Object[] {"###", " X ", " X ", '#', new ItemStack(AddonDefs.paintedPlanksSidingAndCorner[i], 1, 0), 'X', new ItemStack(AddonDefs.paintedPlanksMouldingAndDecorative[i], 1, 0)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.paintedPlanksSidingAndCorner[i], 4, 12), new Object[] {"###", " X ", '#', new ItemStack(AddonDefs.paintedPlanksSidingAndCorner[i], 1, 0), 'X', new ItemStack(AddonDefs.paintedPlanksMouldingAndDecorative[i], 1, 0)});

			//Fences
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.paintedPlanksSidingAndCorner[i], 2, 14), new Object[] {"###", "###", '#', new ItemStack(AddonDefs.planksPainted, 1, i)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.paintedPlanksSidingAndCorner[i], 2, 14), new Object[] {"###", '#', new ItemStack(AddonDefs.paintedPlanksMouldingAndDecorative[i], 1, 0)});

			FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.planksPainted, 1, i), new Object[] {new ItemStack(AddonDefs.paintedPlanksSidingAndCorner[i], 1, 0), new ItemStack(AddonDefs.paintedPlanksSidingAndCorner[i], 1, 0)});
			FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.paintedPlanksSidingAndCorner[i], 1, 0), new Object[] {new ItemStack(AddonDefs.paintedPlanksMouldingAndDecorative[i], 1, 0), new ItemStack(AddonDefs.paintedPlanksMouldingAndDecorative[i], 1, 0)});
			FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.paintedPlanksMouldingAndDecorative[i], 1, 0), new Object[] {new ItemStack(AddonDefs.paintedPlanksSidingAndCorner[i], 1, 1), new ItemStack(AddonDefs.paintedPlanksSidingAndCorner[i], 1, 1)});

			//Stairs
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.paintedPlanksStairs[i], 4, 0), new Object[]{"#  ","## ","###",'#',new ItemStack(AddonDefs.planksPainted, 1, i)});
			FCRecipes.AddRecipe(new ItemStack(AddonDefs.paintedPlanksStairs[i], 1, 0), new Object[]{"# ","##",'#',new ItemStack(AddonDefs.paintedPlanksMouldingAndDecorative[i], 1, 0)});
		}
		
		//Pergola
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.pergola), new Object[] {" X ", "X X", " X ", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767)});
		
		//Wood bleach
		FCRecipes.AddCauldronRecipe(new ItemStack(AddonDefs.woodBleach), new ItemStack[] {new ItemStack(AddonDefs.bottleHempOil), new ItemStack(Item.dyePowder, 1, 15)});
		
		//Barrels
        FCRecipes.RemoveVanillaRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 2, 11), new Object[] {"###", "#X#", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767), 'X', FCBetterThanWolves.fcItemGlue});
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.barrelEmpty, 1, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 11)});
        
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.barrelEmpty, 2, 0), new Object[] {"###", "#X#", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0), 'X', FCBetterThanWolves.fcItemGlue});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.barrelEmpty, 2, 1), new Object[] {"###", "#X#", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 1), 'X', FCBetterThanWolves.fcItemGlue});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.barrelEmpty, 2, 2), new Object[] {"###", "#X#", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 2), 'X', FCBetterThanWolves.fcItemGlue});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.barrelEmpty, 2, 3), new Object[] {"###", "#X#", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 3), 'X', FCBetterThanWolves.fcItemGlue});

        for (int i = 0; i < AddonBlockBarrelFilled.types.length; i++) {
        	FCRecipes.AddRecipe(new ItemStack(AddonDefs.barrelFullOak, 1, i), new Object[] {"###", "#X#", "###", '#', new ItemStack(AddonBlockBarrelFilled.types[i], 1, 0), 'X', new ItemStack(AddonDefs.barrelEmpty, 1, 0)});
        	FCRecipes.AddRecipe(new ItemStack(AddonDefs.barrelFullSpruce, 1, i), new Object[] {"###", "#X#", "###", '#', new ItemStack(AddonBlockBarrelFilled.types[i], 1, 0), 'X', new ItemStack(AddonDefs.barrelEmpty, 1, 1)});
        	FCRecipes.AddRecipe(new ItemStack(AddonDefs.barrelFullBirch, 1, i), new Object[] {"###", "#X#", "###", '#', new ItemStack(AddonBlockBarrelFilled.types[i], 1, 0), 'X', new ItemStack(AddonDefs.barrelEmpty, 1, 2)});
        	FCRecipes.AddRecipe(new ItemStack(AddonDefs.barrelFullJungle, 1, i), new Object[] {"###", "#X#", "###", '#', new ItemStack(AddonBlockBarrelFilled.types[i], 1, 0), 'X', new ItemStack(AddonDefs.barrelEmpty, 1, 3)});
        	
        	FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(AddonBlockBarrelFilled.types[i], 8), new ItemStack[] {new ItemStack(AddonDefs.barrelFullOak, 1, i)});
        	FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(AddonBlockBarrelFilled.types[i], 8), new ItemStack[] {new ItemStack(AddonDefs.barrelFullSpruce, 1, i)});
        	FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(AddonBlockBarrelFilled.types[i], 8), new ItemStack[] {new ItemStack(AddonDefs.barrelFullBirch, 1, i)});
        	FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(AddonBlockBarrelFilled.types[i], 8), new ItemStack[] {new ItemStack(AddonDefs.barrelFullJungle, 1, i)});
        }

        FCRecipes.AddRecipe(new ItemStack(Block.tnt, 1), new Object[] {"GFG", "GBG", "GGG", 'B', new ItemStack(AddonDefs.barrelEmpty, 1, 32767), 'G', Item.gunpowder, 'F', FCBetterThanWolves.fcItemFuse});
        
        //Crates
        for (int i = 0; i < 4; i++) {
        	FCRecipes.AddRecipe(new ItemStack(AddonDefs.crate, 2, i), new Object[] {" # ", "#X#", " # ", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, i), 'X', FCBetterThanWolves.fcItemGlue});
        }
	}
	
	private void addDecoRecipes() {
		AddonManager.MakeStorage(FCBetterThanWolves.fcItemIngotDiamond, AddonDefs.blockDiamondium);
		
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemWheat, 9), new ItemStack[]{new ItemStack(AddonDefs.hayBale)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.hayBaleStairs, 8), new Object[] {"X  ", "XX ", "XXX", 'X', AddonDefs.hayBale});
		
		//Lanterns
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.paperWall, 4), new Object[] { "ppp", "pwp", "ppp", 'p', Item.paper, 'w', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767) });
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 6), new ItemStack[] { new ItemStack(AddonDefs.fenceSteel, 1) });
		FCRecipes.AddAnvilRecipe(new ItemStack(AddonDefs.fenceSteel, 10), new Object[] { " X X", "XXXX", " X X", " X X", 'X', new ItemStack(Item.ingotIron) });

		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.bottleHempOil,1), new Object[]{Item.glassBottle, FCBetterThanWolves.fcItemHempSeeds});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.lanternPaper,1),new Object[]{"pwp","wcw","pwp",'c', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767), 'p', Item.paper, 'w', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767)});
		FCRecipes.AddAnvilRecipe(new ItemStack(AddonDefs.chandelier,2), new Object[]{" ss "," gg ","cggc","cggc",'s',Block.stone,'g',Item.goldNugget,'c', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.lanternSteel,1),new Object[]{"nnn","ntn","nnn",'n',FCBetterThanWolves.fcItemNuggetIron,'t',FCBetterThanWolves.fcBlockTorchNetherBurning});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 5), new ItemStack[]{new ItemStack(AddonDefs.lanternSteel,1)});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 2), new ItemStack[] {new ItemStack(AddonDefs.chandelier)});
		
		//Paintings
        FCRecipes.RemoveVanillaRecipe(new ItemStack(Item.painting, 1), new Object[] {"###", "#X#", "###", '#', Item.stick, 'X', Block.cloth});
        FCRecipes.AddRecipe(new ItemStack(Item.painting, 1), new Object[] {"###", "#X#", "###", '#', Item.stick, 'X', new ItemStack(FCBetterThanWolves.fcItemWool, 1, 32767)});
        
        //Workbench
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.workbench), new Object[] {"XX","XX", 'X', Block.planks});
        
        //Wicker
        FCRecipes.AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemWickerPiece, 2), new Object[] {"X X ", " X X", "X X ", " X X", 'X', Item.reed});
        
        //Coarse Dirt
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.coarseDirt, 1), new ItemStack[] {new ItemStack(Block.dirt), new ItemStack(Block.gravel)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.coarseDirt), new Object[] {"X", "X", 'X', AddonDefs.coarseDirtSlab});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.coarseDirtSlab, 4), new Object[] {"XX", 'X', AddonDefs.coarseDirt});
        
        //Podzol
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.podzol, 9), new ItemStack[] {new ItemStack(Block.grass), new ItemStack(AddonDefs.coarseDirt), new ItemStack(AddonDefs.coarseDirt), new ItemStack(AddonDefs.coarseDirt), new ItemStack(AddonDefs.coarseDirt), new ItemStack(AddonDefs.coarseDirt), new ItemStack(AddonDefs.coarseDirt), new ItemStack(AddonDefs.coarseDirt), new ItemStack(AddonDefs.coarseDirt)});
        
        //Pumpkin
        FCRecipes.RemoveVanillaShapelessRecipe(new ItemStack(Block.pumpkin), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockPumpkinFresh)});
        FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Block.pumpkin), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockPumpkinFresh), new ItemStack(FCBetterThanWolves.fcItemChiselWood, 1, 32767)});
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pumpkin, 1, 0), new ItemStack[] {new ItemStack(Block.pumpkin), new ItemStack(FCBetterThanWolves.fcItemChiselWood, 1, 32767)});
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pumpkin, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.pumpkin, 1, 0), new ItemStack(FCBetterThanWolves.fcItemChiselWood, 1, 32767)});
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pumpkin, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.pumpkin, 1, 1), new ItemStack(FCBetterThanWolves.fcItemChiselWood, 1, 32767)});
        FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Block.pumpkin), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockPumpkinFresh), new ItemStack(FCBetterThanWolves.fcItemChiselStone, 1, 32767)});
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pumpkin, 1, 0), new ItemStack[] {new ItemStack(Block.pumpkin), new ItemStack(FCBetterThanWolves.fcItemChiselStone, 1, 32767)});
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pumpkin, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.pumpkin, 1, 0), new ItemStack(FCBetterThanWolves.fcItemChiselStone, 1, 32767)});
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pumpkin, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.pumpkin, 1, 1), new ItemStack(FCBetterThanWolves.fcItemChiselStone, 1, 32767)});
        FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Block.pumpkin), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockPumpkinFresh), new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767)});
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pumpkin, 1, 0), new ItemStack[] {new ItemStack(Block.pumpkin), new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767)});
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pumpkin, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.pumpkin, 1, 0), new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767)});
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pumpkin, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.pumpkin, 1, 1), new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767)});
        FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Block.pumpkin), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockPumpkinFresh), new ItemStack(AddonDefs.chiselDiamond, 1, 32767)});
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pumpkin, 1, 0), new ItemStack[] {new ItemStack(Block.pumpkin), new ItemStack(AddonDefs.chiselDiamond, 1, 32767)});
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pumpkin, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.pumpkin, 1, 0), new ItemStack(AddonDefs.chiselDiamond, 1, 32767)});
        FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.pumpkin, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.pumpkin, 1, 1), new ItemStack(AddonDefs.chiselDiamond, 1, 32767)});
        
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.pumpkinLit, 1, 0), new Object[] {"X", "#", 'X', new ItemStack(AddonDefs.pumpkin, 1, 0), '#', new ItemStack(FCBetterThanWolves.fcItemCandle, 1, 32767)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.pumpkinLit, 1, 1), new Object[] {"X", "#", 'X', new ItemStack(AddonDefs.pumpkin, 1, 1), '#', new ItemStack(FCBetterThanWolves.fcItemCandle, 1, 32767)});
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.pumpkinLit, 1, 2), new Object[] {"X", "#", 'X', new ItemStack(AddonDefs.pumpkin, 1, 2), '#', new ItemStack(FCBetterThanWolves.fcItemCandle, 1, 32767)});
        
        //Carpets
        for (int i = 0; i < 16; i++) {
        	FCRecipes.AddRecipe(new ItemStack(AddonDefs.carpet, 1, i), new Object[] {"XX", 'X', new ItemStack(FCBetterThanWolves.fcItemWool, 1, i)});
        	FCRecipes.AddCauldronRecipe(new ItemStack(AddonDefs.carpet, 16, i), new ItemStack[] {new ItemStack(AddonDefs.carpet, 16, 15), new ItemStack(Item.dyePowder, 1, i)});
        	FCRecipes.AddCauldronRecipe(new ItemStack(AddonDefs.carpet, 16, i), new ItemStack[] {new ItemStack(AddonDefs.carpet, 16, 15), new ItemStack(Item.dyePowder, 1, i + 16)});
        }
        
        //Coal Block
        AddonManager.MakeStorage(Item.coal, AddonDefs.coalBlock);
        
        //Bone Pillars
        FCRecipes.AddRecipe(new ItemStack(AddonDefs.bonePillar, 2), new Object[] {"X", "X", 'X', new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 15)});
        FCRecipes.AddShapelessRecipe(new ItemStack(Item.bone, 9), new ItemStack[] {new ItemStack(AddonDefs.bonePillar)});
	}
	
	private void addMortarRecipes() {
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.cobblestone), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.cobblestone), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.cobblestone), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stairsCobblestone), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseStairs), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stairsCobblestone), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseStairs), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stairsCobblestone), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseStairs), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneSingleSlab, 1, 3), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseSlab), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneSingleSlab, 1, 3), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseSlab), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneSingleSlab, 1, 3), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseSlab), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});

		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stairsStoneBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseStairs), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stairsStoneBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseStairs), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stairsStoneBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseStairs), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneSingleSlab, 1, 5), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseSlab), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneSingleSlab, 1, 5), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseSlab), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneSingleSlab, 1, 5), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseSlab), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});

		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.brick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockBrickLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.brick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockBrickLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.brick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockBrickLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stairsBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockBrickLooseStairs), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stairsBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockBrickLooseStairs), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stairsBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockBrickLooseStairs), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneSingleSlab, 1, 4), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockBrickLooseStairs), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneSingleSlab, 1, 4), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockBrickLooseStairs), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneSingleSlab, 1, 4), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockBrickLooseStairs), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});

		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.netherBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.netherBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.netherBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stairsNetherBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseStairs), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stairsNetherBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseStairs), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stairsNetherBrick), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseStairs), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneSingleSlab, 1, 7), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseSlab), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneSingleSlab, 1, 7), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseSlab), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.stoneSingleSlab, 1, 7), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseSlab), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.netherBrick), new ItemStack[] {new ItemStack(AddonDefs.netherBrickLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.netherBrick), new ItemStack[] {new ItemStack(AddonDefs.netherBrickLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.netherBrick), new ItemStack[] {new ItemStack(AddonDefs.netherBrickLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.netherBrickStairs), new ItemStack[] {new ItemStack(AddonDefs.netherBrickLooseStairs), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.netherBrickStairs), new ItemStack[] {new ItemStack(AddonDefs.netherBrickLooseStairs), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.netherBrickStairs), new ItemStack[] {new ItemStack(AddonDefs.netherBrickLooseStairs), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab, 1, 7), new ItemStack[] {new ItemStack(AddonDefs.netherBrickLooseSlab), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab, 1, 7), new ItemStack[] {new ItemStack(AddonDefs.netherBrickLooseSlab), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab, 1, 7), new ItemStack[] {new ItemStack(AddonDefs.netherBrickLooseSlab), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});

		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobble), new ItemStack[] {new ItemStack(AddonDefs.graniteCobbleLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobble), new ItemStack[] {new ItemStack(AddonDefs.graniteCobbleLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobble), new ItemStack[] {new ItemStack(AddonDefs.graniteCobbleLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobblestoneStairs[0]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[0]), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobblestoneStairs[0]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[0]), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobblestoneStairs[0]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[0]), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab2, 1, 6), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 0), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab2, 1, 6), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 0), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab2, 1, 6), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 0), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});

		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobble, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.andesiteCobbleLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobble, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.andesiteCobbleLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobble, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.andesiteCobbleLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobblestoneStairs[1]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[1]), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobblestoneStairs[1]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[1]), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobblestoneStairs[1]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[1]), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab2, 1, 7), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 1), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab2, 1, 7), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 1), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab2, 1, 7), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 1), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});

		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobble, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.dioriteCobbleLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobble, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.dioriteCobbleLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobble, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.dioriteCobbleLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobblestoneStairs[2]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[2]), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobblestoneStairs[2]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[2]), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesCobblestoneStairs[2]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[2]), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab3, 1, 0), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 2), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab3, 1, 0), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 2), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab3, 1, 0), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 2), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});

		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrick), new ItemStack[] {new ItemStack(AddonDefs.graniteStoneBrickLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrick), new ItemStack[] {new ItemStack(AddonDefs.graniteStoneBrickLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrick), new ItemStack[] {new ItemStack(AddonDefs.graniteStoneBrickLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrickStairs[0]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[3]), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrickStairs[0]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[3]), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrickStairs[0]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[3]), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab3, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 3), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab3, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 3), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab3, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 3), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});

		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrick, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.andesiteStoneBrickLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrick, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.andesiteStoneBrickLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrick, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.andesiteStoneBrickLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrickStairs[1]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[4]), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrickStairs[1]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[4]), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrickStairs[1]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[4]), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab3, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 4), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab3, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 4), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab3, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 4), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});

		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrick, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.dioriteStoneBrickLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrick, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.dioriteStoneBrickLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrick, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.dioriteStoneBrickLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrickStairs[2]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[5]), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrickStairs[2]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[5]), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypesStoneBrickStairs[2]), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseStairs[5]), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab3, 1, 3), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 5), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab3, 1, 3), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 5), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneSlab3, 1, 3), new ItemStack[] {new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 5), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
	}
	
	private void addToolRecipes() {
		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.chiselDiamond), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemIngotDiamond)});
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemStone, 2), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(FCBetterThanWolves.fcItemStoneBrick)});
        FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLoose), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(Block.stone)});
        FCRecipes.AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 1), new ItemStack[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767)});
	}
	
	private void addCustomRecipeClasses() {
        CraftingManager.getInstance().getRecipeList().add(new AddonRecipesLogChopping());
	}
	
	private void RemoveSubBlockRecipes(Block var0, int var1, int var2, Block var3, Block var4, boolean var5) {
        FCRecipes.RemoveAnvilRecipe(new ItemStack(var3, 8, 0), new Object[] {"####", '#', new ItemStack(var0, 1, var1)});
        FCRecipes.RemoveAnvilRecipe(new ItemStack(var4, 8, 0), new Object[] {"####", '#', new ItemStack(var3, 1, 0)});
        FCRecipes.RemoveAnvilRecipe(new ItemStack(var3, 8, 1), new Object[] {"####", '#', new ItemStack(var4, 1, 0)});
        FCRecipes.RemoveVanillaRecipe(new ItemStack(var4, 1, 12), new Object[] {"M", "M", "M", 'M', new ItemStack(var4, 1, 0)});
        FCRecipes.RemoveVanillaRecipe(new ItemStack(var4, 6, 13), new Object[] {" S ", "###", "###", '#', new ItemStack(var0, 1, var1), 'S', new ItemStack(var3, 8, 0)});
        FCRecipes.RemoveVanillaRecipe(new ItemStack(var4, 4, 15), new Object[] {"###", " X ", " X ", '#', new ItemStack(var3, 1, 0), 'X', new ItemStack(var4, 1, 0)});
        FCRecipes.RemoveVanillaRecipe(new ItemStack(var3, 4, 12), new Object[] {"###", " X ", '#', new ItemStack(var3, 1, 0), 'X', new ItemStack(var4, 1, 0)});

        if (var5)
        {
        	FCRecipes.RemoveVanillaRecipe(new ItemStack(var3, 6, 14), new Object[] {"###", "###", '#', new ItemStack(var0, 1, var1)});
        	FCRecipes.RemoveVanillaRecipe(new ItemStack(var3, 2, 14), new Object[] {"###", '#', new ItemStack(var4, 1, 0)});
        }

        FCRecipes.RemoveVanillaShapelessRecipe(new ItemStack(var0, 1, var2), new Object[] {new ItemStack(var3, 1, 0), new ItemStack(var3, 1, 0)});
        FCRecipes.RemoveVanillaShapelessRecipe(new ItemStack(var3, 1, 0), new Object[] {new ItemStack(var4, 1, 0), new ItemStack(var4, 1, 0)});
        FCRecipes.RemoveVanillaShapelessRecipe(new ItemStack(var4, 1, 0), new Object[] {new ItemStack(var3, 1, 1), new ItemStack(var3, 1, 1)});
	}
}
