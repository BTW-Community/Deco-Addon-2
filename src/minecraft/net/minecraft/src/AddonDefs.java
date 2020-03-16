package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class AddonDefs {
	public static final AddonDefs instance = new AddonDefs();
	
	public static final int
		id_paperWall=3000,
		id_fenceSteel=3001,
		id_flower=3002,
		id_glassStained=3003,
		id_glassPaneStained=3004,
		id_tulip=3006,
		id_blockDiamondium=3007,
		id_whiteStoneBrick=3008,
		id_whiteBrickSidingAndCorner=3009,
		id_whiteBrickMouldingAndDecorative=3010,
		id_whiteBrickStairs=3011,
		id_hayBale=3025,
		id_lanternPaper=3027,
		id_chandelier=3028,
		id_lanternSteel=3029,
		id_oakWoodChair=3036,
		id_spruceWoodChair=3037,
		id_birchWoodChair=3038,
		id_jungleWoodChair=3039,
		id_bloodWoodChair=3040,
		id_terracotta=3044,
		id_stainedTerracotta=3045,
		id_unfiredTerracotta=3046,
		id_clay_sub_start=3049,
		//end 3099
		id_stoneType=3100,
		id_stoneTypeCobble=3101,
		id_graniteCobbleLoose=3102,
		id_andesiteCobbleLoose=3103,
		id_dioriteCobbleLoose=3104,
		id_stoneTypeStoneBrick=3105,
		id_graniteStoneBrickLoose=3106,
		id_andesiteStoneBrickLoose=3107,
		id_dioriteStoneBrickLoose=3108,
		id_stoneTypeSmooth=3109,
		id_glazedTerracottaStart=3134,
		//end 3149
		id_strippedLog=3200,
		id_workbench=3210,
		id_trapdoorSpruce=3220,
		id_trapdoorBirch=3221,
		id_trapdoorJungle=3222,
		id_trapdoorBlood=3223,
		id_doorSpruce=3224,
		id_doorBirch=3225,
		id_doorJungle=3226,
		id_doorBlood=3227,
		id_gateSpruce=3228,
		id_gateBirch=3229,
		id_gateJungle=3230,
		id_gateBlood=3231,
		id_planksPainted=3232,
		id_pergola=3240,
		id_coarseDirt=3300,
		id_coarseDirtSlab=3301,
		id_podzol=3302,
		id_podzolSlab=3303,
		
		id_flag_start=4000;
	
	public static final int
		id_glassChunk = 30002,
		id_fertilizer = 30003,
		id_bottleHempOil = 30007,
		id_glassStainedItem = 30008,
		id_itemDoorSpruce = 30020,
		id_itemDoorBirch = 30021,
		id_itemDoorJungle = 30022,
		id_itemDoorBlood = 30023,
		id_chiselDiamond = 30050;
	
	//Clay
	public static Block terracotta, stainedTerracotta, unfiredTerracotta;
	public static Block terracottaSidingAndCorner, terracottaMouldingAndDecorative, terracottaStairs;
	public static Block[] stainedTerracottaSidingAndCorner, stainedTerracottaMouldingAndDecorative,stainedTerracottaStairs;
	public static Block[] glazedTerracotta;
	
	//Chairs
	public static Block birchWoodChair, spruceWoodChair, jungleWoodChair, oakWoodChair, bloodWoodChair;
	
	//Glass
	public static Block glassStained, glassPaneStained;
	public static Item glassChunk;
	public static AddonItemGlassStained stainedGlassItem;
	
	//White Stone
	public static Block whiteStoneBrick, whiteBrickMouldingAndDecorative, whiteBrickSidingAndCorner, whiteBrickStairs;
	
	//Flowers
	public static Block flower, tulip;
	public static Item fertilizer;
	
	//Stone
	public static Block stoneTypes;
	public static Block stoneTypesCobble;
	public static Block graniteCobbleLoose, andesiteCobbleLoose, dioriteCobbleLoose;
	public static Block stoneTypesStoneBrick;
	public static Block graniteStoneBrickLoose, andesiteStoneBrickLoose, dioriteStoneBrickLoose;
	public static Block stoneTypesSmooth;
	
	//Wood
	public static Block strippedLog;
	public static BlockTrapDoor trapdoorSpruce, trapdoorBirch, trapdoorJungle, trapdoorBlood;
	public static BlockDoor doorSpruce, doorBirch, doorJungle, doorBlood;
	public static FCItemDoor itemDoorSpruce, itemDoorBirch, itemDoorJungle, itemDoorBlood;
	public static BlockFenceGate gateSpruce, gateBirch, gateJungle, gateBlood;
	public static Block planksPainted;
	public static Block pergola;
	
	//Deco
	public static Block blockDiamondium;
	public static Block hayBale;
	public static BlockPane paperWall, fenceSteel;
	public static Block lanternPaper, lanternSteel, chandelier;
	public static Block workbench;
	public static Item bottleHempOil;
	public static Block coarseDirt;
	public static Block coarseDirtSlab;
	public static Block podzol;
	public static Block podzolSlab;
	
	//Tools
	public static AddonItemChiselDiamond chiselDiamond;
	
	private AddonDefs() {
		
	}
	
	public void addDefinitions() {
		addClayDefs();
		addChairDefs();
		addGlassDefs();
		addWhiteStoneDefs();
		addFlowerDefs();
		addStoneDefs();
		addWoodDefs();
		addDecoDefs();
		addToolDefs();
	}
	
	private void addClayDefs() {
		terracotta = (new Block(id_terracotta, Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ginger_clay").setCreativeTab(CreativeTabs.tabBlock);
		stainedTerracotta = (new AddonBlockTerracottaStained(id_stainedTerracotta, "ginger_clay", "Terracotta", Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabBlock);
		unfiredTerracotta = new AddonBlockTerracottaUnfired(id_unfiredTerracotta);
		AddonManager.Register(terracotta, "Terracotta");
        AddonManager.Register(unfiredTerracotta, "Unfired Terracotta");

        //Sub blocks
		int start = id_clay_sub_start,
				end = start+51,
				id = start,
				i = 0;
		final String main = "Terracotta";
		terracottaSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id++, Material.rock, "ginger_clay", 2.0F, 5.0F, Block.soundWoodFootstep, "claySiding", "Hardened Clay");
		terracottaMouldingAndDecorative = new FCBlockMouldingAndDecorative(id++, Material.rock, "ginger_clay", "ginger_clay_column", 3042, 2.0F, 5.0F, Block.soundWoodFootstep, "clayMolding");
		terracottaStairs = new FCBlockStairs(id++, terracotta, i).setUnlocalizedName("stairsHardenedClay");

		Item.itemsList[terracottaSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(terracottaSidingAndCorner.blockID - 256);
		Item.itemsList[terracottaMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(terracottaMouldingAndDecorative.blockID - 256);
		AddonManager.Register(terracottaStairs, main+" Stairs");
		AddonManager.NameSubBlocks_Wall(terracottaSidingAndCorner, terracottaMouldingAndDecorative, main);

		stainedTerracottaSidingAndCorner = new Block[16];
		stainedTerracottaMouldingAndDecorative = new Block[16];
		stainedTerracottaStairs = new Block[16];
		
		while(i < 16)
		{
			stainedTerracottaSidingAndCorner[i] = new AddonBlockSidingAndCornerDecorativeWall(id++, Material.rock, "ginger_clay_"+i, 2.0F, 5.0F, Block.soundStoneFootstep, "stainedClaySiding_"+i, "Stained Clay");
			stainedTerracottaMouldingAndDecorative[i] = new FCBlockMouldingAndDecorative(id++, Material.rock, "ginger_clay_"+i, "ginger_clay_column_"+i, 3042, 2.0F, 5.0F, Block.soundWoodFootstep, "stainedClayMoulding_"+i);
			stainedTerracottaStairs[i] = new FCBlockStairs(id++, stainedTerracotta, i).setUnlocalizedName("stairsStainedClay_"+i);

			Item.itemsList[stainedTerracottaSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stainedTerracottaSidingAndCorner[i].blockID - 256);
			Item.itemsList[stainedTerracottaMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stainedTerracottaMouldingAndDecorative[i].blockID - 256);
			AddonManager.Register(stainedTerracottaStairs[i], AddonBlockTerracottaStained.names[i]+" "+main+" Stairs");
			AddonManager.NameSubBlocks_Wall(stainedTerracottaSidingAndCorner[i], stainedTerracottaMouldingAndDecorative[i], AddonBlockTerracottaStained.names[i]+" "+main);

			i++;//i is metadata from original 16 color set
		}
		
		//Glazed Terracotta
		start = id_glazedTerracottaStart;
		end = start+16;
		id = start;
		i = 0;
		
		glazedTerracotta = new Block[16];
		String[] tags = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "lightGrey", "grey", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" };
		String[] names = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Grey", "Grey", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White" };
		
		while(i < 16)
		{
			glazedTerracotta[i] = new AddonBlockTerracottaGlazed(start + i, i, "ginger_glazedTerracotta_" + i, names + " Glazed Terracotta", Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabBlock);
			AddonManager.Register(glazedTerracotta[i], names[i] + " Glazed Terracotta");
			i++;
		}
	}
	
	private void addChairDefs() {
		oakWoodChair = new AddonBlockChairWood(id_oakWoodChair, "oak", "Oak");
		birchWoodChair = new AddonBlockChairWood(id_spruceWoodChair, "birch", "Birch");
		spruceWoodChair = new AddonBlockChairWood(id_birchWoodChair, "spruce", "Spruce");
		jungleWoodChair = new AddonBlockChairWood(id_jungleWoodChair, "jungle", "Jungle");
		jungleWoodChair = new AddonBlockChairWood(id_bloodWoodChair, "blood", "Blood Wood");
	}
	
	private void addGlassDefs() {
		glassChunk = new Item(id_glassChunk).setUnlocalizedName("ginger_glassball").setCreativeTab(CreativeTabs.tabMaterials);
		glassStained = new AddonBlockGlassStained(id_glassStained);
		stainedGlassItem = new AddonItemGlassStained(id_glassStainedItem);
		AddonManager.Name(glassChunk, "Piece of Glass");
		AddonManager.Name(stainedGlassItem, "Stained Glass");
	}
	
	private void addWhiteStoneDefs() {
		whiteStoneBrick = new AddonBlockWhiteStoneBrick(id_whiteStoneBrick);
		whiteBrickSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_whiteBrickSidingAndCorner, Material.rock, "ginger_bricks_white_0_decorative", 2.0F, 5.0F, Block.soundWoodFootstep, "whiteBrickSiding", "White Brick");
		whiteBrickMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_whiteBrickMouldingAndDecorative, Material.rock, "ginger_bricks_white_0_decorative", "ginger_bricks_white_0_column", 3042, 2.0F, 5.0F, Block.soundWoodFootstep, "whiteBrickMolding");
		whiteBrickStairs = new FCBlockStairs(id_whiteBrickStairs, whiteStoneBrick, 0).setUnlocalizedName("stairsWhiteBrick");

		Item.itemsList[whiteBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(whiteBrickSidingAndCorner.blockID - 256);
		Item.itemsList[whiteBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(whiteBrickMouldingAndDecorative.blockID - 256);
		AddonManager.Register(whiteBrickStairs, "White Stone Brick Stairs");

		AddonManager.NameSubBlocks_Wall(whiteBrickSidingAndCorner, whiteBrickMouldingAndDecorative, "White Stone Brick");
	}
	
	private void addFlowerDefs() {
		String[]
				flowers = { "yucca", "hyacinth", "birdsParadise", "azalea", "cornFlower", "lavender", "honeysuckle","allium", "orchidBlue", "poppy", "azureBluet", "daisy", "peony","lilac","rosebush", "roseBlue"},
				tulips = { "red","pink", "orange", "white", "blue"},
				flowerNames = { "Yucca", "Hyacinth", "Birds of Paradise", "Azaleas", "Cornflower", "Lavender", "Honeysuckle", "Allium","Blue Orchid", "Poppy", "Azure Bluet", "Daisy", "Peony", "Lilac", "Rose Bush", "Blue Rose"},
				tulipNames = { "Red", "Pink", "Orange", "White", "Blue"};
		
		Item.m_bSuppressConflictWarnings=true;
		Item.dyePowder = new AddonItemDye(95);
		Item.m_bSuppressConflictWarnings=false;

		List recipes = CraftingManager.getInstance().getRecipeList();
		ArrayList<RecipeFireworks> fireworks = new ArrayList<RecipeFireworks>();
		for(Object o: recipes)
			if(o instanceof RecipeFireworks)
				fireworks.add((RecipeFireworks)o);
		for(RecipeFireworks rf: fireworks)
			recipes.remove(rf);
		recipes.add(new AddonRecipeFireworksColor());

		FCBetterThanWolves.fcPlanter = new AddonBlockPlanter(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcPlanter));
		flower = new AddonBlockFlowers(id_flower, "flower",flowers,flowerNames);
		tulip = new AddonBlockFlowers(id_tulip, "tulip", tulips, tulipNames, " Tulip");

		fertilizer = new AddonItemFertilizer(id_fertilizer);
	}
	
	private void addStoneDefs() {
		AddonManager.Name(Block.stairsCobblestone, "Cobblestone Stairs");
		
		stoneTypes = new AddonBlockStone(id_stoneType);
		stoneTypesCobble = new AddonBlockCobble(id_stoneTypeCobble);
		graniteCobbleLoose = new AddonBlockCobbleLooseGranite(id_graniteCobbleLoose);
		andesiteCobbleLoose = new AddonBlockCobbleLooseAndesite(id_andesiteCobbleLoose);
		dioriteCobbleLoose = new AddonBlockCobbleLooseDiorite(id_dioriteCobbleLoose);
		stoneTypesStoneBrick = new AddonBlockStoneBrick(id_stoneTypeStoneBrick);
		graniteStoneBrickLoose = new AddonBlockStoneBrickLooseGranite(id_graniteStoneBrickLoose);
		andesiteStoneBrickLoose = new AddonBlockStoneBrickLooseAndesite(id_andesiteStoneBrickLoose);
		dioriteStoneBrickLoose = new AddonBlockStoneBrickLooseDiorite(id_dioriteStoneBrickLoose);
		stoneTypesSmooth = new AddonBlockStoneSmooth(id_stoneTypeSmooth);
	}
	
	private void addWoodDefs() {
		//Logs
		//BlockLog addonLog = new AddonBlockLogReplace(AddonManager.ReplaceBlockID(Block.wood));
		//AddonManager.SetVanillaBlockFinal("wood", Block.wood, addonLog);
		
		AddonManager.Name(new ItemStack(Block.wood, 1, 0), "Oak Log");
		AddonManager.Name(new ItemStack(Block.wood, 1, 1), "Spruce Log");
		AddonManager.Name(new ItemStack(Block.wood, 1, 2), "Birch Log");
		AddonManager.Name(new ItemStack(Block.wood, 1, 3), "Jungle Log");
		
		AddonManager.Name(FCBetterThanWolves.fcBloodWood, "Blood Wood Log");
		
		//Stripped Logs
		
		//Trapdoors
		//Forces oak trap doors to inherit texture rotations and eventually better placement
		AddonManager.SetVanillaBlockFinal("trapdoor", Block.trapdoor, new AddonBlockTrapDoor(AddonManager.ReplaceBlockID(Block.trapdoor)));
		AddonManager.Name(Block.trapdoor, "Oak Trap Door");
		
		trapdoorSpruce = (BlockTrapDoor) new AddonBlockTrapDoor(id_trapdoorSpruce).setUnlocalizedName("ginger_trapdoorSpruce");
		trapdoorBirch = (BlockTrapDoor) new AddonBlockTrapDoor(id_trapdoorBirch).setUnlocalizedName("ginger_trapdoorBirch");
		trapdoorJungle = (BlockTrapDoor) new AddonBlockTrapDoor(id_trapdoorJungle).setUnlocalizedName("ginger_trapdoorJungle");
		trapdoorBlood = (BlockTrapDoor) new AddonBlockTrapDoor(id_trapdoorBlood).setUnlocalizedName("ginger_trapdoorBlood");
		
		AddonManager.Register(trapdoorSpruce, "Spruce Trap Door");
		AddonManager.Register(trapdoorBirch, "Birch Trap Door");
		AddonManager.Register(trapdoorJungle, "Jungle Trap Door");
		AddonManager.Register(trapdoorBlood, "Blood Wood Trap Door");
		
		//Doors
		AddonManager.Name(Block.doorWood, "Oak Door");
		
		doorSpruce = new AddonBlockDoorWood(id_doorSpruce, new String[] {"ginger_doorSpruce_lower", "ginger_doorSpruce_upper"});
		doorBirch = new AddonBlockDoorWood(id_doorBirch, new String[] {"ginger_doorBirch_lower", "ginger_doorBirch_upper"});
		doorJungle = new AddonBlockDoorWood(id_doorJungle, new String[] {"ginger_doorJungle_lower", "ginger_doorJungle_upper"});
		doorBlood = new AddonBlockDoorWood(id_doorBlood, new String[] {"ginger_doorBlood_lower", "ginger_doorBlood_upper"});
		
		AddonManager.Register(doorSpruce, "Spruce Door");
		AddonManager.Register(doorBirch, "Birch Door");
		AddonManager.Register(doorJungle, "Jungle Door");
		AddonManager.Register(doorBlood, "Blood Wood Door");
		
		itemDoorSpruce = new AddonItemDoor(id_itemDoorSpruce, "ginger_doorSpruceItem", "Spruce Door", doorSpruce);
		itemDoorBirch = new AddonItemDoor(id_itemDoorBirch, "ginger_doorBirchItem", "Birch Door", doorBirch);
		itemDoorJungle = new AddonItemDoor(id_itemDoorJungle, "ginger_doorJungleItem", "Jungle Door", doorJungle);
		itemDoorBlood = new AddonItemDoor(id_itemDoorBlood, "ginger_doorBloodItem", "Blood Wood Door", doorBlood);
		
		//Fence gates
		AddonManager.Name(Block.fenceGate, "Oak Fence Gate");
		
		gateSpruce = (BlockFenceGate) new AddonBlockFenceGate(id_gateSpruce, "ginger_gateSpruce");
		gateBirch = (BlockFenceGate) new AddonBlockFenceGate(id_gateBirch, "ginger_gateBirch");
		gateJungle = (BlockFenceGate) new AddonBlockFenceGate(id_gateJungle, "ginger_gateJungle");
		gateBlood = (BlockFenceGate) new AddonBlockFenceGate(id_gateBlood, "ginger_gateBlood");

		AddonManager.Register(gateSpruce, "Spruce Fence Gate");
		AddonManager.Register(gateBirch, "Birch Fence Gate");
		AddonManager.Register(gateJungle, "Jungle Fence Gate");
		AddonManager.Register(gateBlood, "Blood Wood Fence Gate");
		
		//Painted planks
		planksPainted = (new AddonBlockPlanksPainted(id_planksPainted, "ginger_planksPainted", "Painted Planks")).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabBlock);
	
		//Pergola
		pergola = new AddonBlockPergola(id_pergola);
		AddonManager.Register(pergola, "Pergola");
	}
	
	private void addDecoDefs() {
		//Diamondium
		blockDiamondium = new Block(id_blockDiamondium, Material.iron).setHardness(10.0F).setResistance(2000.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockDiamond").setCreativeTab(CreativeTabs.tabBlock);
		Block.blockDiamond.setUnlocalizedName("ginger_solid_diamond");

		AddonManager.Register(blockDiamondium, "Block of Diamondium");
		
		//Haybale
		hayBale = new AddonBlockHayBale(id_hayBale);
		FCBetterThanWolves.fcItemWheat = new AddonItemWheat(AddonManager.ReplaceItemID(FCBetterThanWolves.fcItemWheat));
		
		//Walls
		FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner),  Material.rock, "fcBlockDecorativeStoneBrick", 1.5F, 10.0F, Block.soundStoneFootstep, "fcStoneBrickSiding", "Stone Brick");
		FCBetterThanWolves.fcBlockBrickSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockBrickSidingAndCorner), Material.rock, "fcBlockDecorativeBrick", 2.0F, 10.0F, Block.soundStoneFootstep, "fcBrickSiding","Brick");
		
		//Lanterns
		paperWall = new AddonBlockPaperWall(id_paperWall);
		fenceSteel = new AddonBlockWroughtBars(id_fenceSteel);

		lanternPaper = new AddonBlockLantern(id_lanternPaper,Material.wood,.3F,"paper","Firefly Lantern",true).setStepSound(Block.soundWoodFootstep);
		lanternPaper.SetAxesEffectiveOn(true);
		chandelier = new AddonBlockChandelier(id_chandelier);
		lanternSteel = new AddonBlockLantern(id_lanternSteel,Material.iron,.5F,"steel","Wrought Iron Lantern").setStepSound(Block.soundStoneFootstep);
		lanternSteel.SetPicksEffectiveOn(true);

		bottleHempOil = new Item(id_bottleHempOil).setUnlocalizedName("ginger_bottle_hempoil").setCreativeTab(CreativeTabs.tabMaterials);

		AddonManager.Register(paperWall, "Paper Wall");
		AddonManager.Register(fenceSteel, "Wrought Iron Bars");
		AddonManager.Name(bottleHempOil, "Hemp Oil");
		
		//Workbench
		workbench = new AddonBlockWorkbench(id_workbench);
		AddonManager.Register(workbench, "Workbench");
		
		//Coarse Dirt
		coarseDirt = new AddonBlockDirtCoarse(id_coarseDirt);
		AddonManager.Register(coarseDirt, "Coarse Dirt");
		coarseDirtSlab = new AddonBlockDirtSlab(id_coarseDirtSlab).setUnlocalizedName("ginger_coarseDirt");;
		AddonManager.Register(coarseDirtSlab, "Coarse Dirt Slab");
		
		//Podzol
		podzol = new AddonBlockPodzol(id_podzol);
		AddonManager.Register(podzol, "Podzol");
		//Ran into weird issue where dirt got renamed, so this fixes that
		AddonManager.Name(Block.dirt, "Dirt");
		//podzolSlab = new AddonBlockDirtSlab(id_podzolSlab));
	}
	
	private void addToolDefs() {
		chiselDiamond = new AddonItemChiselDiamond(id_chiselDiamond);
		AddonManager.Name(chiselDiamond, "Diamondium Chisel");
		
		AddonManager.Name(Block.blockDiamond, "Block of Diamond");
		AddonManager.Name(FCBetterThanWolves.fcItemIngotDiamond, "Diamondium Ingot");
		AddonManager.Name(Item.pickaxeDiamond, "Diamondium Pickaxe");
		AddonManager.Name(Item.axeDiamond, "Diamondium Axe");
		AddonManager.Name(Item.shovelDiamond, "Diamondium Spade");
		AddonManager.Name(Item.hoeDiamond, "Diamondium Hoe");
		AddonManager.Name(Item.swordDiamond, "Diamondium Sword");
	}
}
