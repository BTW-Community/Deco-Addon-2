package net.minecraft.src;

public class AddonRecipes {
	public static final AddonRecipes instance = new AddonRecipes();
	
	private AddonRecipes() {
		
	}
	
	public void addAllAddonRecipes() {
		addClayRecipes();
		addChairRecipes();
		addGlassRecipes();
		addWhiteStoneRecipes();
		addFlowerRecipes();
		addDecoRecipes();
		addMortarRecipes();
		addToolRecipes();
	}
	
	private void addClayRecipes() {
		AddonDefs.unfiredTerracotta.SetCanBeCookedByKiln(true).SetItemIndexDroppedWhenCookedByKiln(AddonDefs.terracotta.blockID);
		for (int i = 0; i < 32; i++)
		{
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stainedTerracotta, 1, i), new ItemStack[] { new ItemStack(AddonDefs.terracotta), new ItemStack(Item.dyePowder, 1, i) });
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
		
		for (int Index = 0; Index < 16; Index++) {
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stainedGlassItem, 1, Index), new ItemStack[] {new ItemStack(Block.glass), new ItemStack(Item.dyePowder, 1, Index+16)});
		}
	}
	
	private void addWhiteStoneRecipes() {
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.whiteStoneBrick,4,0), new Object[]{"XX","XX",'X',new ItemStack(FCBetterThanWolves.fcAestheticOpaque,1,9)});
		FCRecipes.AddAnvilRecipe(new ItemStack(AddonDefs.whiteStoneBrick, 12, 3), new Object[] {"####", "#  #", "#  #", "####", '#', AddonDefs.whiteStoneBrick});
		FCRecipes.AddSubBlockRecipesOfType(AddonDefs.whiteStoneBrick, 0, AddonDefs.whiteBrickSidingAndCorner, AddonDefs.whiteBrickMouldingAndDecorative, true);
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.whiteBrickStairs), new Object[] {"# ", "##", '#', new ItemStack(AddonDefs.whiteBrickMouldingAndDecorative)});
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
			FCRecipes.AddShapelessRecipe(new ItemStack(Block.cloth,1,BlockCloth.getDyeFromBlock(Index)),
				new Object[]{new ItemStack(Item.dyePowder,1,Index+16),new ItemStack(Item.itemsList[Block.cloth.blockID], 1,0)});
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
	
	private void addDecoRecipes() {
		AddonManager.MakeStorage(FCBetterThanWolves.fcItemIngotDiamond, AddonDefs.blockDiamondium);
		AddonManager.MakeStorage(Item.wheat, AddonDefs.hayBale);
		
		//Lanterns
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.paperWall, 4), new Object[] { "ppp", "pwp", "ppp", 'p', Item.paper, 'w', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767) });
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Item.ingotIron, 1), new ItemStack[] { new ItemStack(AddonDefs.fenceSteel, 1) });
		FCRecipes.AddAnvilRecipe(new ItemStack(AddonDefs.fenceSteel, 10), new Object[] { " X X", "XXXX", " X X", " X X", 'X', new ItemStack(Item.ingotIron) });

		FCRecipes.AddShapelessRecipe(new ItemStack(AddonDefs.bottleHempOil,1), new Object[]{Item.glassBottle, FCBetterThanWolves.fcItemHempSeeds});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.lanternPaper,1),new Object[]{"pwp","wcw","pwp",'c', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767), 'p', Item.paper, 'w', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767)});
		FCRecipes.AddAnvilRecipe(new ItemStack(AddonDefs.chandelier,2), new Object[]{" ss "," gg ","cggc","cggc",'s',Block.stone,'g',Item.goldNugget,'c', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767)});
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.lanternSteel,1),new Object[]{" s ","shs"," s ",'s',AddonDefs.fenceSteel,'h',AddonDefs.bottleHempOil});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Item.ingotIron,4), new ItemStack[]{new ItemStack(AddonDefs.lanternSteel,1)});
		
		//Paintings
        FCRecipes.RemoveVanillaRecipe(new ItemStack(Item.painting, 1), new Object[] {"###", "#X#", "###", '#', Item.stick, 'X', Block.cloth});
        FCRecipes.AddRecipe(new ItemStack(Item.painting, 1), new Object[] {"###", "#X#", "###", '#', Item.stick, 'X', FCBetterThanWolves.fcItemWool});
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

		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypes), new ItemStack[] {new ItemStack(AddonDefs.graniteCobbleLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypes), new ItemStack[] {new ItemStack(AddonDefs.graniteCobbleLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypes), new ItemStack[] {new ItemStack(AddonDefs.graniteCobbleLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});

		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypes, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.andesiteCobbleLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypes, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.andesiteCobbleLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypes, 1, 1), new ItemStack[] {new ItemStack(AddonDefs.andesiteCobbleLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});

		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypes, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.dioriteCobbleLoose), new ItemStack(Item.clay)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypes, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.dioriteCobbleLoose), new ItemStack(Item.slimeBall)});
		FCRecipes.AddStokedCauldronRecipe(new ItemStack(AddonDefs.stoneTypes, 1, 2), new ItemStack[] {new ItemStack(AddonDefs.dioriteCobbleLoose), new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
	}
	
	private void addToolRecipes() {
		FCRecipes.AddRecipe(new ItemStack(AddonDefs.chiselDiamond), new Object[] {"X", 'X', FCBetterThanWolves.fcItemIngotDiamond});
		FCRecipes.AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemStone, 2), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(FCBetterThanWolves.fcItemStoneBrick)});
        FCRecipes.AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 4), new Object[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767), new ItemStack(Block.stone)});
        FCRecipes.AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 1), new ItemStack[] {new ItemStack(AddonDefs.chiselDiamond, 1, 32767)});
	}
}
