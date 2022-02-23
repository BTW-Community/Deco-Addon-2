package net.minecraft.src;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.prupe.mcpatcher.mal.block.RenderBlocksUtils;

import net.minecraft.client.Minecraft;

public class DecoDefs {
	public static final DecoDefs instance = new DecoDefs();
	private static DecoManager decoManager;

	public static Material materialHedge;
	public static Material materialHay;
	public static Material materialWart;
	public static Material materialCarpet;
	public static Material materialAshBlock;

	public static final StepSound stepSoundLantern = new DecoStepSound("lantern", 1, 1, "stone", Block.soundMetalFootstep.getVolume(), Block.soundMetalFootstep.getPitch());
	public static final StepSound stepSoundChain = new DecoStepSound("chain", 1, 1, "stone", Block.soundMetalFootstep.getVolume(), Block.soundMetalFootstep.getPitch());
	public static final StepSound stepSoundChainDeep = new DecoStepSound("chain", 1, 0.33F, "stone", Block.soundMetalFootstep.getVolume(), Block.soundMetalFootstep.getPitch());
	public static final StepSound stepSoundNetherrack = new DecoStepSound("netherrack", 1, 1, "stone", Block.soundStoneFootstep.getVolume(), Block.soundStoneFootstep.getPitch());
	public static final StepSound stepSoundNetherBrick = new DecoStepSound("netherbrick", 1, 1, "stone", Block.soundStoneFootstep.getVolume(), Block.soundStoneFootstep.getPitch());
	public static final StepSound stepSoundBone = new DecoStepSound("bone", 1, 1, "stone", Block.soundStoneFootstep.getVolume(), Block.soundStoneFootstep.getPitch());
	public static final StepSound stepSoundSoulSand = new DecoStepSound("soulsand", 1, 1, "sand", Block.soundSandFootstep.getVolume(), Block.soundSandFootstep.getPitch());
	public static final StepSound stepSoundSteel = new DecoStepSound("soulsteel", 1.5F, 1, "stone", Block.soundMetalFootstep.getVolume(), Block.soundMetalFootstep.getPitch());
	public static final StepSound stepSoundVine = new DecoStepSoundVine(1, 1);
	public static final StepSound stepSoundBloodLog = new DecoStepSound("bloodLog", 1, 1, "wood", Block.soundWoodFootstep.getVolume(), Block.soundWoodFootstep.getPitch());
	public static final StepSound stepSoundGroth = new DecoStepSound("groth", 1, 1, "grass", Block.soundGrassFootstep.getVolume(), Block.soundGrassFootstep.getPitch());
	public static final StepSound stepSoundNylium = new DecoStepSound("nylium", 1, 1, "dirt", Block.soundGravelFootstep.getVolume(), Block.soundGravelFootstep.getPitch());
	public static final StepSound stepSoundWart = new DecoStepSound("netherWart", 1, 1, "grass", Block.soundGrassFootstep.getVolume(), Block.soundGrassFootstep.getPitch());
	public static final StepSound stepSoundLadderIron = new DecoStepSoundLadderIron(1, 1.5F);

	//Clay
	public static Block terracotta, stainedTerracotta, unfiredTerracotta, shingles, shinglesColored;
	public static Block terracottaSidingAndCorner, terracottaMouldingAndDecorative, terracottaStairs;
	public static Block shingleSidingAndCorner, shingleMouldingAndDecorative, shingleStairs;
	public static Block[] stainedTerracottaSidingAndCorner, stainedTerracottaMouldingAndDecorative,stainedTerracottaStairs;
	public static Block[] shingleColoredSidingAndCorner, shingleColoredMouldingAndDecorative, shingleColoredStairs;
	public static Block[] glazedTerracotta;
	public static Block terracottaSlab, terracottaSlab2;
	public static Block shingleSlab, shingleSlab2;

	//Chairs
	public static Block birchWoodChair, spruceWoodChair, jungleWoodChair, oakWoodChair, bloodWoodChair, cherryWoodChair, acaciaWoodChair;

	//Glass
	public static Block glassStained, glassPaneStained;
	public static Item glassChunk;
	public static DecoItemGlassStained stainedGlassItem;

	//White Stone
	public static Block whiteStoneBrick, whiteBrickMouldingAndDecorative, whiteBrickSidingAndCorner, whiteBrickStairs;

	//Flowers
	public static Block flower, flower2, tulip;
	public static Block flowerPot;
	public static Item fertilizer;
	public static Block cherrySapling, cherryLeaves;

	//Stone
	public static Block stoneTypes;
	public static Block infestedGranite, infestedAndesite, infestedDiorite, infestedSlate;
	public static Block stoneTypesCobble;
	public static Block graniteCobbleLoose, andesiteCobbleLoose, dioriteCobbleLoose;
	public static Block stoneTypesStoneBrick;
	public static Block graniteStoneBrickLoose, andesiteStoneBrickLoose, dioriteStoneBrickLoose;
	public static Block stoneTypesSmooth;
	public static Block[] stoneTypesSidingAndCorner, stoneTypesMouldingAndDecorative, stoneTypesStairs;
	public static Block[] stoneTypesSmoothSidingAndCorner, stoneTypesSmoothMouldingAndDecorative, stoneTypesSmoothStairs;
	public static Block[] stoneTypesCobblestoneSidingAndCorner, stoneTypesCobblestoneMouldingAndDecorative, stoneTypesCobblestoneStairs;
	public static Block[] stoneTypesStoneBrickSidingAndCorner, stoneTypesStoneBrickMouldingAndDecorative, stoneTypesStoneBrickStairs;
	public static Block[] stoneTypesLooseStairs;
	public static Block stoneTypesLooseSlab;

	public static Block polishedStone, polishedStoneStairs, polishedStoneSidingAndCorner, polishedStoneMouldingAndDecorative;

	public static Block stoneBrickMossyStairs, stoneBrickMossySidingAndCorner, stoneBrickMossyMouldingAndDecorative;
	public static Block stoneBrickCrackedStairs, stoneBrickCrackedSidingAndCorner, stoneBrickCrackedMouldingAndDecorative;

	public static DecoBlockSlabStone stoneSlab, stoneSlab2, stoneSlab3, stoneSlab4, stoneSlab5, stoneSlab6, stoneSlab7;

	public static Block cobblestoneSidingAndCorner, cobblestoneMouldingAndDecorative;
	public static Block mossyCobblestoneSidingAndCorner, mossyCobblestoneMouldingAndDecorative, mossyCobblestoneStairs;

	public static Block prismarine;
	public static Block prismarineLantern;
	public static Block prismarineSidingAndCorner, prismarineMouldingAndDecorative, prismarineStairs;
	public static Block prismarineBrickSidingAndCorner, prismarineBrickMouldingAndDecorative, prismarineBrickStairs;
	public static Block prismarineDarkSidingAndCorner, prismarineDarkMouldingAndDecorative, prismarineDarkStairs;
	public static Item prismarineShard;
	public static Item prismarineCrystal;

	public static Block netherBrick, infusedStone, basalt;
	public static Block netherBrickRedSidingAndCorner, netherBrickRedMouldingAndDecorative, netherBrickRedStairs;
	public static Block infusedStoneSidingAndCorner, infusedStoneMouldingAndDecorative, infusedStoneStairs;
	public static Block infusedStoneSmoothSidingAndCorner, infusedStoneSmoothMouldingAndDecorative, infusedStoneSmoothStairs;
	public static Block infusedStoneBrickSidingAndCorner, infusedStoneBrickMouldingAndDecorative, infusedStoneBrickStairs;
	public static Block netherBrickRedLoose, netherBrickRedLooseStairs, netherBrickRedLooseSlab;

	public static Block netherrackSuperheated, magma, netherBrickSuperheated;
	public static Item slateStone, slateBrickItem;

	public static Block endStoneBrick, endStoneBrickStairs, endStoneBrickSidingAndCorner, endStoneBrickMouldingAndDecorative;
	
	public static Block slateRough, slateCobbleLoose, slateBrickLoose;
	public static Block slateStairs, slateSmoothStairs, slateBrickStairs, slateCobbleStairs, slateTilesStairs, slateBrickLooseStairs, slateCobbleLooseStairs;
	public static Block slateSidingAndCorner, slateMouldingAndDecorative;
	public static Block slateSmoothSidingAndCorner, slateSmoothMouldingAndDecorative;
	public static Block slateCobbleSidingAndCorner, slateCobbleMouldingAndDecorative;
	public static Block slateBrickSidingAndCorner, slateBrickMouldingAndDecorative;
	public static Block slateTilesSidingAndCorner, slateTilesMouldingAndDecorative;

	//Sandstone
	public static Block redSand, redSandSlab;
	public static Item pileRedSand;

	public static Block redSandStone, redSandStoneStairs, redSandStoneSidingAndCorner, redSandStoneMouldingAndDecorative;
	public static Block redSandStoneSmoothSidingAndCorner, redSandStoneSmoothMouldingAndDecorative, redSandStoneSmoothStairs;
	public static Block redSandStonePolishedSidingAndCorner, redSandStonePolishedMouldingAndDecorative, redSandStonePolishedStairs;
	public static Block redSandStoneBrickSidingAndCorner, redSandStoneBrickMouldingAndDecorative, redSandStoneBrickStairs;
	public static Block redSandStoneMossySidingAndCorner, redSandStoneMossyMouldingAndDecorative, redSandStoneMossyStairs;
	public static Block redSandStoneBrickLargeStairs, redSandStoneBrickLargeSidingAndCorner, redSandStoneBrickLargeMouldingAndDecorative;
	public static Block redSandStoneBrickLargeMossyStairs, redSandStoneBrickLargeMossySidingAndCorner, redSandStoneBrickLargeMossyMouldingAndDecorative;
	public static Block redSandStoneCrackedStairs, redSandStoneCrackedSidingAndCorner, redSandStoneCrackedMouldingAndDecorative;
	public static Block redSandStoneBrickLargeCrackedStairs, redSandStoneBrickLargeCrackedSidingAndCorner, redSandStoneBrickLargeCrackedMouldingAndDecorative;

	public static Block sandStoneSmoothSidingAndCorner, sandStoneSmoothMouldingAndDecorative, sandStoneSmoothStairs;
	public static Block sandStonePolishedSidingAndCorner, sandStonePolishedMouldingAndDecorative, sandStonePolishedStairs;
	public static Block sandStoneBrickSidingAndCorner, sandStoneBrickMouldingAndDecorative, sandStoneBrickStairs;
	public static Block sandStoneMossySidingAndCorner, sandStoneMossyMouldingAndDecorative, sandStoneMossyStairs;
	public static Block sandStoneBrickLargeStairs, sandStoneBrickLargeSidingAndCorner, sandStoneBrickLargeMouldingAndDecorative;
	public static Block sandStoneBrickLargeMossyStairs, sandStoneBrickLargeMossySidingAndCorner, sandStoneBrickLargeMossyMouldingAndDecorative;
	public static Block sandStoneCrackedStairs, sandStoneCrackedSidingAndCorner, sandStoneCrackedMouldingAndDecorative;
	public static Block sandStoneBrickLargeCrackedStairs, sandStoneBrickLargeCrackedSidingAndCorner, sandStoneBrickLargeCrackedMouldingAndDecorative;

	//Concrete
	public static Block concrete, concretePowder;
	public static Block[] concreteStairs, concreteSidingAndCorner, concreteMouldingAndDecorative;
	public static DecoBlockSlabStone concreteSlab, concreteSlab2;

	//Wood
	public static Block strippedLog, barkLog, barkLogStripped, bloodLog, cherryLog, cherryStump, acaciaLog, acaciaStump;
	public static Block logDamagedSpruce, logDamagedBirch, logDamagedJungle, logDamagedBlood, logDamagedCherry, logDamagedAcacia;
	public static Block logSpikeSpruce, logSpikeBirch, logSpikeJungle, logSpikeBlood, logSpikeCherry, logSpikeAcacia;
	public static BlockTrapDoor trapdoorSpruce, trapdoorBirch, trapdoorJungle, trapdoorBlood, trapdoorCherry, trapdoorAcacia, trapdoorIron;
	public static BlockDoor doorSpruce, doorBirch, doorJungle, doorBlood, doorCherry, doorAcacia;
	public static FCItemDoor itemDoorSpruce, itemDoorBirch, itemDoorJungle, itemDoorBlood, itemDoorCherry, itemDoorAcacia;
	public static BlockFenceGate gateSpruce, gateBirch, gateJungle, gateBlood, gateCherry, gateAcacia;
	public static Block cherryStairs, cherrySidingAndCorner, cherryMouldingAndDecorative;
	public static Block acaciaStairs, acaciaSidingAndCorner, acaciaMouldingAndDecorative;
	public static Block planksPainted;
	public static Block[] paintedPlanksSidingAndCorner, paintedPlanksMouldingAndDecorative, paintedPlanksStairs;
	public static Block pergola;
	public static Block barrelEmpty, barrelEmpty2, barrelFilling, barrelFullOak, barrelFullSpruce, barrelFullBirch, barrelFullJungle, barrelFullBlood, barrelFullCherry, barrelFullAcacia;
	public static Block crate;
	public static Block signSpruce, signSpruceWall, signBirch, signBirchWall, signJungle, signJungleWall, signBlood, signBloodWall, signCherry, signCherryWall, signAcacia, signAcaciaWall;
	public static Block ladderSpruce, ladderSpruceOnFire, ladderBirch, ladderBirchOnFire, ladderJungle, ladderJungleOnFire, ladderBlood, ladderBloodOnFire, ladderCherry, ladderCherryOnFire, ladderAcacia, ladderAcaciaOnFire, ladderIron;
	public static Block bookshelf, bookshelfEmpty, bottleRack, bottleRackEmpty;
	public static Item woodBleach, woodStain;
	public static Item bookPlain;

	public static DecoBlockWoodSlab planksPaintedSlab, planksPaintedSlab2, woodSlab;

	//Deco
	public static Block blockDiamondium;
	public static Block hayBale, hayBaleStairs, thatch, thatchStairs;
	public static BlockPane paperWall, fenceSteel;
	public static Block lanternPaper, lanternPaperBroken, lanternSteel, chandelier;
	public static Block chain;
	public static Item chainItem;
	public static Block workbench;
	public static Item bottleHempOil;
	public static Block coarseDirt;
	public static Block coarseDirtSlab;
	public static Block podzol;
	public static Block podzolSlab;
	public static BlockLeaves hedge;
	public static Block hedgeOakStairs, hedgeOakSidingAndCorner, hedgeOakMouldingAndDecorative;
	public static Block hedgeSpruceStairs, hedgeSpruceSidingAndCorner, hedgeSpruceMouldingAndDecorative;
	public static Block hedgeBirchStairs, hedgeBirchSidingAndCorner, hedgeBirchMouldingAndDecorative;
	public static Block hedgeJungleStairs, hedgeJungleSidingAndCorner, hedgeJungleMouldingAndDecorative;
	public static Block hedgeBloodStairs, hedgeBloodSidingAndCorner, hedgeBloodMouldingAndDecorative;
	public static Block hedgeCherryStairs, hedgeCherrySidingAndCorner, hedgeCherryMouldingAndDecorative;
	public static Block pumpkin, pumpkinLit;
	public static Block carpet;
	public static Block coalBlock, netherCoalBlock;
	public static Block bonePillar;
	public static Block buttonSpruce, buttonBirch, buttonJungle, buttonBlood, buttonCherry;
	public static Block buttonInfusedStone, buttonGranite, buttonAndesite, buttonDiorite, buttonSandstone, buttonRedSandstone;
	public static Block ropeCoil, chainCoil;
	public static Block nylium;
	public static Block stemCrimson, stemWarped, stemDamagedCrimson, stemDamagedWarped, stemSpikeCrimson, stemSpikeWarped, wartBlock;
	public static Block netherRoots, netherVines;
	public static Block spiderEyeBlock, spiderEyeSlab;
	public static Block acaciaLeaves, acaciaSapling;
	public static Block autumnLeaves, autumnSapling;
	public static Block ashBlock, pumice;
	public static Block amethyst, amethystShardBlock;
	public static Item amethystShard;
	public static Block framedGlass, framedGlassIron;
	public static Block farmlandSalted;

	//Ground Cover
	public static Block layerDirt, layerGrass, layerGravel, layerSand, layerRedSand, layerCoarseDirt, layerPodzol, layerPackedEarth, layerDirtLoose;

	//Extra SubBlocks
	public static Block stoneBrickEdging;

	private DecoDefs() {}

	public void addDefinitions() {
		decoManager = DecoManager.getInstance();
		
		Item.m_bSuppressConflictWarnings=true;
		addMaterialDefs();
		addClayDefs();
		addGlassDefs();
		addWhiteStoneDefs();
		addFlowerDefs();
		addStoneDefs();
		addWoodDefs();
		addDecoDefs();
		addToolDefs();
		addSubBlockReplaceDefs();
		addEntityDefs();
		Item.m_bSuppressConflictWarnings=false;
	}

	private void addMaterialDefs() {
		materialHedge = (new Material(MapColor.foliageColor))
				.setBurning()
				.setTranslucent()
				.setNoPushMobility()
				.SetAxesEfficientOn()
				.SetAxesTreatAsVegetation()
				.SetMobsCantSpawnOn();
		
		materialHay = (new Material(MapColor.clothColor))
				.setBurning()
				.SetAxesEfficientOn()
				.SetMobsCantSpawnOn();
		
	    materialCarpet = (new Material(MapColor.clothColor))
	    		.setBurning()
	    		.SetAxesEfficientOn()
	    		.setRequiresTool();

		Material.glass.SetMobsCantSpawnOn();
		//FCBetterThanWolves.fcMaterialWicker.SetMobsCantSpawnOn();
	}

	private void addClayDefs() {
		terracotta = new DecoBlockTerracotta(DecoDefsIDs.id_terracotta)
				.setHardness(1.0F)
				.setResistance(5.0F)
				.setStepSound(Block.soundStoneFootstep)
				.setUnlocalizedName("decoBlockTerracotta")
				.setCreativeTab(CreativeTabs.tabBlock);
		
		stainedTerracotta = new DecoBlockTerracottaStained(DecoDefsIDs.id_stainedTerracotta)
				.setHardness(1.0F)
				.setResistance(5.0F)
				.setStepSound(Block.soundStoneFootstep)
				.setCreativeTab(CreativeTabs.tabBlock)
				.setUnlocalizedName("decoBlockTerracottaStained");
		
		unfiredTerracotta = new DecoBlockTerracottaUnfired(DecoDefsIDs.id_unfiredTerracotta)
				.setUnlocalizedName("decoBlockTerracottaUnfired");
		
		DecoManager.register(terracotta);
		Item.itemsList[stainedTerracotta.blockID] = new DecoItemBlockColored(stainedTerracotta.blockID - 256, stainedTerracotta);
		DecoManager.register(unfiredTerracotta);

		//Sub blocks
		int start = DecoDefsIDs.id_clay_sub_start,
				end = start+51,
				id = start,
				i = 0;
		terracottaSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id++, 
				Material.rock, 
				"decoBlockTerracotta", 
				2.0F, 5.0F, 
				Block.soundStoneFootstep, 
				"decoBlockTerracottaSiding", "Terracotta");
		
		terracottaMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id++, 
				Material.rock, 
				"decoBlockTerracotta", "decoBlockTerracottaColumn", 
				terracottaSidingAndCorner.blockID, 
				2.0F, 5.0F, 
				Block.soundStoneFootstep, 
				"decoBlockTerracottaMoulding");
		
		terracottaStairs = new FCBlockStairs(id++, terracotta, 0)
				.setUnlocalizedName("decoBlockTerracottaStairs");

		Item.itemsList[terracottaSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(terracottaSidingAndCorner.blockID - 256);
		Item.itemsList[terracottaMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(terracottaMouldingAndDecorative.blockID - 256);
		DecoManager.register(terracottaStairs);

		stainedTerracottaSidingAndCorner = new Block[16];
		stainedTerracottaMouldingAndDecorative = new Block[16];
		stainedTerracottaStairs = new Block[16];

		while(i < 16) {
			stainedTerracottaSidingAndCorner[i] = new DecoBlockSidingAndCornerDecorativeWall(id++, 
					Material.rock, 
					"decoBlockTerracottaStained_" + FCUtilsColor.colorOrder[i], 
					2.0F, 5.0F, 
					Block.soundStoneFootstep, 
					"decoBlockTerracottaStainedSiding." + FCUtilsColor.colorOrder[i], "Stained Terracotta");
			
			stainedTerracottaMouldingAndDecorative[i] = new DecoBlockMouldingAndDecorativeWall(id++, 
					Material.rock, 
					"decoBlockTerracottaStained_" + FCUtilsColor.colorOrder[i], 
					"decoBlockTerracottaStainedColumn_" + DecoUtilsMisc.colorOrder[i], 
					stainedTerracottaSidingAndCorner[i].blockID, 
					2.0F, 5.0F, 
					Block.soundStoneFootstep, 
					"decoBlockTerracottaStainedMoulding." + FCUtilsColor.colorOrder[i]);
			
			stainedTerracottaStairs[i] = new FCBlockStairs(id++, stainedTerracotta, i)
					.setUnlocalizedName("decoBlockTerracottaStainedStairs." + FCUtilsColor.colorOrder[i]);

			Item.itemsList[stainedTerracottaSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stainedTerracottaSidingAndCorner[i].blockID - 256);
			Item.itemsList[stainedTerracottaMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stainedTerracottaMouldingAndDecorative[i].blockID - 256);
			DecoManager.register(stainedTerracottaStairs[i]);
			
			i++;
		}
		
		//Shingles
		shingles = new DecoBlockTerracotta(DecoDefsIDs.id_shingles)
				.setHardness(1.0F)
				.setResistance(5.0F)
				.setStepSound(Block.soundStoneFootstep)
				.setUnlocalizedName("decoBlockShingles")
				.setCreativeTab(CreativeTabs.tabBlock);
		
		shinglesColored = new DecoBlockTerracottaStained(DecoDefsIDs.id_shinglesColored)
				.setHardness(1.0F)
				.setResistance(5.0F)
				.setStepSound(Block.soundStoneFootstep)
				.setCreativeTab(CreativeTabs.tabBlock)
				.setUnlocalizedName("decoBlockShinglesColored");
		
		DecoManager.register(shingles);
		Item.itemsList[shinglesColored.blockID] = new DecoItemBlockColored(shinglesColored.blockID - 256, shinglesColored);
		
		start = DecoDefsIDs.id_shingleSubStart;
		end = start + 51;
		id = start;
		i = 0;
		
		shingleSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id++, 
				Material.rock, 
				"decoBlockShingles", 
				2.0F, 5.0F, 
				Block.soundStoneFootstep, 
				"decoBlockShinglesSiding", "Shingles");
		
		shingleMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id++, 
				Material.rock, 
				"decoBlockShingles", "decoBlockShinglesColumn", 
				shingleSidingAndCorner.blockID, 
				2.0F, 5.0F, 
				Block.soundStoneFootstep, 
				"decoBlockShinglesMoulding");
		
		shingleStairs = new FCBlockStairs(id++, shingles, i)
				.setUnlocalizedName("decoBlockShingleStairs");

		Item.itemsList[shingleSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(shingleSidingAndCorner.blockID - 256);
		Item.itemsList[shingleMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(shingleMouldingAndDecorative.blockID - 256);
		DecoManager.register(shingleStairs);

		shingleColoredSidingAndCorner = new Block[16];
		shingleColoredMouldingAndDecorative = new Block[16];
		shingleColoredStairs = new Block[16];

		while(i < 16) {
			shingleColoredSidingAndCorner[i] = new DecoBlockSidingAndCornerDecorativeWall(id++, 
					Material.rock, 
					"decoBlockShinglesColored_" + FCUtilsColor.colorOrder[i], 
					2.0F, 5.0F, 
					Block.soundStoneFootstep, 
					"decoBlockShinglesColoredSiding_" + FCUtilsColor.colorOrder[i], "Colored Shingles");
			
			shingleColoredMouldingAndDecorative[i] = new DecoBlockMouldingAndDecorativeWall(id++, 
					Material.rock, 
					"decoBlockShinglesColored_" + FCUtilsColor.colorOrder[i], 
					"decoBlockShinglesColoredColumn_" + FCUtilsColor.colorOrder[i], 
					shingleColoredSidingAndCorner[i].blockID, 
					2.0F, 5.0F, 
					Block.soundStoneFootstep, 
					"decoBlockShinglesColoredMoulding." + FCUtilsColor.colorOrder[i]);
			
			shingleColoredStairs[i] = new FCBlockStairs(id++, shinglesColored, i)
					.setUnlocalizedName("decoBlockShinglesColoredStairs." + FCUtilsColor.colorOrder[i]);

			Item.itemsList[shingleColoredSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(shingleColoredSidingAndCorner[i].blockID - 256);
			Item.itemsList[shingleColoredMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(shingleColoredMouldingAndDecorative[i].blockID - 256);
			DecoManager.register(shingleColoredStairs[i]);
			
			i++;
		}

		//Glazed Terracotta
		start = DecoDefsIDs.id_glazedTerracottaStart;
		end = start+16;
		id = start;
		i = 0;

		glazedTerracotta = new Block[16];

		while(i < 16) {
			glazedTerracotta[i] = new DecoBlockTerracottaGlazed(start + i, i)
					.setHardness(2.0F)
					.setResistance(10.0F)
					.setStepSound(Block.soundStoneFootstep)
					.setCreativeTab(CreativeTabs.tabBlock)
					.setUnlocalizedName("decoBlockGlazedTerracotta." + FCUtilsColor.colorOrder[i]);
			
			DecoManager.register(glazedTerracotta[i]);
			i++;
		}

		//Slabs
		terracottaSlab = new DecoBlockSlabStone(DecoDefsIDs.id_terracottaSlab, 
				new Block[] {
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta}, 
				new int[] {0, 1, 2, 3, 4, 5, 6, 7})
				.setUnlocalizedName("decoBlockTerracottaSlab");
		Item.itemsList[DecoDefs.terracottaSlab.blockID] = new DecoItemBlockSlab(DecoDefs.terracottaSlab.blockID - 256);

		terracottaSlab2 = new DecoBlockSlabStone(DecoDefsIDs.id_terracottaSlab2, 
				new Block[] {
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta, 
						DecoDefs.stainedTerracotta}, 
				new int[] {8, 9, 10, 11, 12, 13, 14, 15})
				.setUnlocalizedName("decoBlockTerracottaSlab2");
		Item.itemsList[DecoDefs.terracottaSlab2.blockID] = new DecoItemBlockSlab(DecoDefs.terracottaSlab2.blockID - 256);

		shingleSlab = new DecoBlockSlabStone(DecoDefsIDs.id_shingleSlab, 
				new Block[] {
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored}, 
				new int[] {0, 1, 2, 3, 4, 5, 6, 7})
				.setUnlocalizedName("decoBlockshinglesSlab");
		Item.itemsList[DecoDefs.shingleSlab.blockID] = new DecoItemBlockSlab(DecoDefs.shingleSlab.blockID - 256);

		shingleSlab2 = new DecoBlockSlabStone(DecoDefsIDs.id_shingleSlab2, 
				new Block[] {
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored, 
						DecoDefs.shinglesColored}, 
				new int[] {8, 9, 10, 11, 12, 13, 14, 15})
				.setUnlocalizedName("decoBlockshinglesSlab2");
		Item.itemsList[DecoDefs.shingleSlab2.blockID] = new DecoItemBlockSlab(DecoDefs.shingleSlab2.blockID - 256);
	}

	private void addGlassDefs() {
		glassChunk = new Item(DecoDefsIDs.id_glassChunk)
				.setCreativeTab(CreativeTabs.tabMaterials)
				.SetFilterableProperties(2)
				.setUnlocalizedName("decoItemGlassPiece");
		
		glassStained = new DecoBlockGlassStained(DecoDefsIDs.id_glassStained);
		
		DecoManager.register(glassStained);
		stainedGlassItem = (DecoItemGlassStained) new DecoItemGlassStained(DecoDefsIDs.id_glassStainedItem)
				.setUnlocalizedName("decoItemStainedGlass");
		
		framedGlass = new DecoBlockFramedGlass(DecoDefsIDs.id_framedGlass, "decoBlockGlassFramed");
		Item.itemsList[framedGlass.blockID] = new DecoItemBlockMulti(framedGlass, new String[] {
				"oak", 
				"spruce", 
				"birch", 
				"jungle", 
				"blood", 
				"cherry", 
				"acacia"});
		
		framedGlassIron = new DecoBlockFramedGlassIron(DecoDefsIDs.id_framedGlassIron);
		DecoManager.register(framedGlassIron);
	}

	private void addWhiteStoneDefs() {
		whiteStoneBrick = new DecoBlockWhiteStoneBrick(DecoDefsIDs.id_whiteStoneBrick)
				.setUnlocalizedName("decoBlockWhiteBricks");
		
		whiteBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_whiteBrickSidingAndCorner, 
				Material.rock, 
				"decoBlockWhiteBricks", 
				2.0F, 5.0F, 
				Block.soundStoneFootstep, 
				"decoBlockWhiteBricksSiding", "White Brick");
		
		whiteBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_whiteBrickMouldingAndDecorative, 
				Material.rock, 
				"decoBlockWhiteBricks", "decoBlockWhiteBricksColumn", 
				whiteBrickSidingAndCorner.blockID, 
				2.0F, 5.0F, 
				Block.soundStoneFootstep, 
				"decoBlockWhiteBricksMoulding");
		
		whiteBrickStairs = new FCBlockStairs(DecoDefsIDs.id_whiteBrickStairs, whiteStoneBrick, 0)
				.setUnlocalizedName("decoBlockWhiteBricksStairs");

		Item.itemsList[whiteStoneBrick.blockID] = new DecoItemBlockWhiteBrick(whiteStoneBrick.blockID - 256, whiteStoneBrick);
		Item.itemsList[whiteBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(whiteBrickSidingAndCorner.blockID - 256);
		Item.itemsList[whiteBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(whiteBrickMouldingAndDecorative.blockID - 256);
		DecoManager.register(whiteBrickStairs);
	}

	private void addFlowerDefs() {
		Item.dyePowder = new DecoItemDye(Item.dyePowder.itemID - 256);
		
		flower = new DecoBlockFlowers(DecoDefsIDs.id_flower, "decoBlockFlower", 16);
		flower2 = new DecoBlockFlowers(DecoDefsIDs.id_flower2, "decoBlockFlower2", 2);
		tulip = new DecoBlockFlowers(DecoDefsIDs.id_tulip, "decoBlockTulip", 5);
		
		Item.itemsList[flower.blockID] = new DecoItemBlockMulti(flower, new String[] {
				"yucca", 
				"hyacinth", 
				"birdsParadise", 
				"azalea", 
				"cornflower", 
				"lavender", 
				"honeysuckle",
				"allium", 
				"orchidBlue", 
				"poppy", 
				"azureBluet", 
				"daisy", 
				"peony",
				"lilac",
				"rosebush", 
				"roseBlue"});
		
		Item.itemsList[flower2.blockID] = new DecoItemBlockMulti(flower2, new String[] {
				"blackRose", 
				"lilyOfTheValley"});
		
		Item.itemsList[tulip.blockID] = new DecoItemBlockMulti(tulip, new String[] {
				"red",
				"pink", 
				"orange", 
				"white", 
				"blue"});

		fertilizer = new DecoItemFertilizer(DecoDefsIDs.id_fertilizer);

		//Flower pot
		flowerPot = new DecoBlockFlowerPot(DecoDefsIDs.id_flowerPot);
		TileEntity.addMapping(DecoTileEntityFlowerPot.class, "AddonFlowerPot");
		Item.replaceItem(Item.flowerPot.itemID, 
				DecoItemFlowerPot.class, 
				decoManager);
	}

	private void addStoneDefs() {
		Block.stone = Block.replaceBlock(Block.stone.blockID, 
				DecoBlockStone.class, 
				decoManager);
		Item.itemsList[Block.stone.blockID] = new DecoItemBlockMulti(Block.stone, new String[] {
				"strata1", 
				"strata2", 
				"strata3", 
				"slate", 
				"rough1", 
				"rough2", 
				"rough3", 
				"roughSlate"});
		
		FCBetterThanWolves.fcBlockSmoothstoneStairs = blockDiamondium.replaceBlock(FCBetterThanWolves.fcBlockSmoothstoneStairs.blockID, FCBlockStairs.class, decoManager, Block.stone, 0);
		
		FCBetterThanWolves.fcBlockSilverfishStone = (FCBlockSilverfish) Block.replaceBlock(FCBetterThanWolves.fcBlockSilverfishStone.blockID, FCBlockSilverfish.class, decoManager, Block.stone, 0);
		FCBetterThanWolves.fcBlockSilverfishStoneSecondStrata = (FCBlockSilverfish) Block.replaceBlock(FCBetterThanWolves.fcBlockSilverfishStoneSecondStrata.blockID, FCBlockSilverfish.class, decoManager, Block.stone, 1);
		FCBetterThanWolves.fcBlockSilverfishStoneThirdStrata = (FCBlockSilverfish) Block.replaceBlock(FCBetterThanWolves.fcBlockSilverfishStoneThirdStrata.blockID, FCBlockSilverfish.class, decoManager, Block.stone, 2);
		
		cobblestoneSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_cobblestoneSidingAndCorner, 
				Material.rock, 
				"stonebrick", 
				1.5F, 10.0F, 
				Block.soundStoneFootstep, 
				"decoBlockCobblestoneSiding", "Cobblestone");
		
		cobblestoneMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_cobblestoneMouldingAndDecorative, 
				Material.rock, 
				"stonebrick", 
				"decoBlockCobblestoneColumn", 
				cobblestoneSidingAndCorner.blockID, 
				1.5F, 10.0F, 
				Block.soundStoneFootstep, 
				"decoBlockCobblestoneMoulding");
		
		mossyCobblestoneSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_mossyCobblestoneSidingAndCorner, 
				Material.rock, 
				"stoneMoss", 
				1.5F, 10.0F, 
				Block.soundStoneFootstep, 
				"decoBlockMossyCobbleSiding", 
				"Mossy Cobblestone");
		
		mossyCobblestoneMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_mossyCobblestoneMouldingAndDecorative, 
				Material.rock, 
				"stoneMoss", "decoBlockMossyCobbleColumn", 
				mossyCobblestoneSidingAndCorner.blockID, 
				1.5F, 10.0F, 
				Block.soundStoneFootstep, 
				"decoBlockMossyCobbleMoulding");
		
		mossyCobblestoneStairs = new FCBlockStairs(DecoDefsIDs.id_mossyCobblestoneStairs, Block.cobblestoneMossy, 0)
				.setUnlocalizedName("decoBlockStairsMossyCobble");

		Item.itemsList[cobblestoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(cobblestoneSidingAndCorner.blockID - 256);
		Item.itemsList[cobblestoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(cobblestoneMouldingAndDecorative.blockID - 256);
		Item.itemsList[mossyCobblestoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(mossyCobblestoneSidingAndCorner.blockID - 256);
		Item.itemsList[mossyCobblestoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(mossyCobblestoneMouldingAndDecorative.blockID - 256);
		DecoManager.register(mossyCobblestoneStairs);

		stoneTypes = new DecoBlockStoneVariants(DecoDefsIDs.id_stoneType);
		stoneTypesCobble = new DecoBlockStoneVariantCobble(DecoDefsIDs.id_stoneTypeCobble);
		graniteCobbleLoose = new DecoBlockCobbleLooseGranite(DecoDefsIDs.id_graniteCobbleLoose);
		andesiteCobbleLoose = new DecoBlockCobbleLooseAndesite(DecoDefsIDs.id_andesiteCobbleLoose);
		dioriteCobbleLoose = new DecoBlockCobbleLooseDiorite(DecoDefsIDs.id_dioriteCobbleLoose);
		stoneTypesStoneBrick = new DecoBlockStoneVariantBrick(DecoDefsIDs.id_stoneTypeStoneBrick);
		graniteStoneBrickLoose = new DecoBlockStoneBrickLooseGranite(DecoDefsIDs.id_graniteStoneBrickLoose);
		andesiteStoneBrickLoose = new DecoBlockStoneBrickLooseAndesite(DecoDefsIDs.id_andesiteStoneBrickLoose);
		dioriteStoneBrickLoose = new DecoBlockStoneBrickLooseDiorite(DecoDefsIDs.id_dioriteStoneBrickLoose);
		stoneTypesSmooth = new DecoBlockStoneVariantSmooth(DecoDefsIDs.id_stoneTypeSmooth);

		String[] stoneTextures = {
				"decoBlockGranite", 
				"decoBlockAndesite", 
				"decoBlockDiorite", 
				"decoBlockGraniteColumn", 
				"decoBlockAndesiteColumn", 
				"decoBlockDioriteColumn"};
		
		String[] stoneSmoothTextures = {
				"decoBlockGraniteSmooth", 
				"decoBlockAndesiteSmooth", 
				"decoBlockDioriteSmooth", 
				"decoBlockGraniteSmoothColumn", 
				"decoBlockAndesiteSmoothColumn", 
				"decoBlockDioriteSmoothColumn"};
		
		String[] stoneCobblestoneTextures = {
				"decoBlockGraniteCobble", 
				"decoBlockAndesite", 
				"decoBlockDioriteCobble", 
				"decoBlockGraniteCobbleColumn", 
				"decoBlockAndesiteColumn", 
				"decoBlockDioriteCobbleColumn"};
		
		String[] stoneBrickTextures = {
				"decoBlockGraniteBrick", 
				"decoBlockAndesiteBrick", 
				"decoBlockDioriteBrick", 
				"decoBlockGraniteBrickColumn", 
				"decoBlockAndesiteBrickColumn", 
				"decoBlockDioriteBrickColumn"};

		String[] names = {"Granite", "Andesite", "Diorite"};

		stoneTypesSidingAndCorner = new Block[3];
		stoneTypesMouldingAndDecorative = new Block[3];
		stoneTypesStairs = new Block[3];
		stoneTypesSmoothSidingAndCorner = new Block[3];
		stoneTypesSmoothMouldingAndDecorative = new Block[3];
		stoneTypesSmoothStairs = new Block[3];
		stoneTypesCobblestoneSidingAndCorner = new Block[3];
		stoneTypesCobblestoneMouldingAndDecorative = new Block[3];
		stoneTypesCobblestoneStairs = new Block[3];
		stoneTypesStoneBrickSidingAndCorner = new Block[3];
		stoneTypesStoneBrickMouldingAndDecorative = new Block[3];
		stoneTypesStoneBrickStairs = new Block[3];

		for (int i = 0; i < 3; i++) {
			stoneTypesSidingAndCorner[i] = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_stoneTypeSubStart + i, 
					Material.rock, 
					stoneTextures[i], 
					1.5F, 10.0F, 
					Block.soundStoneFootstep, 
					"decoBlock" + names[i] + "Siding", "Stone");
			
			stoneTypesMouldingAndDecorative[i] = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_stoneTypeSubStart + 3 + i, 
					Material.rock, 
					stoneTextures[i], stoneTextures[i + 3], 
					stoneTypesSidingAndCorner[i].blockID, 
					1.5F, 10.0F, 
					Block.soundStoneFootstep, 
					"decoBlock" + names[i] + "Moulding");
			
			stoneTypesStairs[i] = new FCBlockStairs(DecoDefsIDs.id_stoneTypeSubStart + 6 + i, stoneTypes, i).setUnlocalizedName("decoBlock" + names[i] + "Stairs").SetPicksEffectiveOn();
			stoneTypesSmoothSidingAndCorner[i] = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_stoneTypeSmoothSubStart + i, Material.rock, stoneSmoothTextures[i], 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "SmoothSiding", "StoneSmooth").SetPicksEffectiveOn();
			stoneTypesSmoothMouldingAndDecorative[i] = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_stoneTypeSmoothSubStart + 3 + i, Material.rock, stoneSmoothTextures[i], stoneSmoothTextures[i + 3], stoneTypesSmoothSidingAndCorner[i].blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "SmoothMoulding");
			stoneTypesSmoothStairs[i] = new FCBlockStairs(DecoDefsIDs.id_stoneTypeSmoothSubStart + 6 + i, stoneTypesSmooth, i).setUnlocalizedName("decoBlock" + names[i] + "SmoothStairs").SetPicksEffectiveOn();
			stoneTypesCobblestoneSidingAndCorner[i] = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_stoneTypeCobbleSubStart + i, Material.rock, stoneCobblestoneTextures[i], 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "CobbleSiding", "StoneCobblestone").SetPicksEffectiveOn();
			stoneTypesCobblestoneMouldingAndDecorative[i] = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_stoneTypeCobbleSubStart + 3 + i, Material.rock, stoneCobblestoneTextures[i], stoneCobblestoneTextures[i + 3], stoneTypesCobblestoneSidingAndCorner[i].blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "CobbleMoulding");
			stoneTypesCobblestoneStairs[i] = new DecoBlockStairsMortared(DecoDefsIDs.id_stoneTypeCobbleSubStart + 6 + i, stoneTypesCobble, i, DecoDefsIDs.id_graniteCobbleLooseStairs + i).setUnlocalizedName("decoBlock" + names[i] + "CobbleStairs").SetPicksEffectiveOn();
			stoneTypesStoneBrickSidingAndCorner[i] = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_stoneTypeBrickSubStart + i, Material.rock, stoneBrickTextures[i], 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "BrickSiding", "StoneBrick").SetPicksEffectiveOn();
			stoneTypesStoneBrickMouldingAndDecorative[i] = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_stoneTypeBrickSubStart + 3 + i, Material.rock, stoneBrickTextures[i], stoneBrickTextures[i + 3], stoneTypesStoneBrickSidingAndCorner[i].blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "BrickMoulding");
			stoneTypesStoneBrickStairs[i] = new DecoBlockStairsMortared(DecoDefsIDs.id_stoneTypeBrickSubStart + 6 + i, stoneTypesStoneBrick, i, DecoDefsIDs.id_graniteCobbleLooseStairs + i + 3).setUnlocalizedName("decoBlock" + names[i] + "BrickStairs").SetPicksEffectiveOn();

			Item.itemsList[stoneTypesSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stoneTypesSidingAndCorner[i].blockID - 256);
			Item.itemsList[stoneTypesMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stoneTypesMouldingAndDecorative[i].blockID - 256);
			DecoManager.register(stoneTypesStairs[i]);
			Item.itemsList[stoneTypesSmoothSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stoneTypesSmoothSidingAndCorner[i].blockID - 256);
			Item.itemsList[stoneTypesSmoothMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stoneTypesSmoothMouldingAndDecorative[i].blockID - 256);
			DecoManager.register(stoneTypesSmoothStairs[i]);
			Item.itemsList[stoneTypesCobblestoneSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stoneTypesCobblestoneSidingAndCorner[i].blockID - 256);
			Item.itemsList[stoneTypesCobblestoneMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stoneTypesCobblestoneMouldingAndDecorative[i].blockID - 256);
			DecoManager.register(stoneTypesCobblestoneStairs[i]);
			Item.itemsList[stoneTypesStoneBrickSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stoneTypesStoneBrickSidingAndCorner[i].blockID - 256);
			Item.itemsList[stoneTypesStoneBrickMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stoneTypesStoneBrickMouldingAndDecorative[i].blockID - 256);
			DecoManager.register(stoneTypesStoneBrickStairs[i]);
		}

		stoneTypesLooseStairs = new Block[6];
		stoneTypesLooseStairs[0] = new DecoBlockStoneLooseStairs(DecoDefsIDs.id_graniteCobbleLooseStairs, graniteCobbleLoose, stoneTypesCobblestoneStairs[0]).setUnlocalizedName("decoBlockGraniteCobblestoneStairsLoose");
		stoneTypesLooseStairs[1] = new DecoBlockStoneLooseStairs(DecoDefsIDs.id_andesiteCobbleLooseStairs, andesiteCobbleLoose, stoneTypesCobblestoneStairs[1]).setUnlocalizedName("decoBlockAndesiteCobblestoneStairsLoose");
		stoneTypesLooseStairs[2] = new DecoBlockStoneLooseStairs(DecoDefsIDs.id_dioriteCobbleLooseStairs, dioriteCobbleLoose, stoneTypesCobblestoneStairs[2]).setUnlocalizedName("decoBlockDioriteCobblestoneStairsLoose");
		stoneTypesLooseStairs[3] = new DecoBlockStoneLooseStairs(DecoDefsIDs.id_graniteStoneBrickLooseStairs, graniteStoneBrickLoose, stoneTypesStoneBrickStairs[0]).setUnlocalizedName("decoBlockGraniteBrickStairsLoose");
		stoneTypesLooseStairs[4] = new DecoBlockStoneLooseStairs(DecoDefsIDs.id_andesiteStoneBrickLooseStairs, andesiteStoneBrickLoose, stoneTypesStoneBrickStairs[1]).setUnlocalizedName("decoBlockAndesiteBrickStairsLoose");
		stoneTypesLooseStairs[5] = new DecoBlockStoneLooseStairs(DecoDefsIDs.id_dioriteStoneBrickLooseStairs, dioriteStoneBrickLoose, stoneTypesStoneBrickStairs[2]).setUnlocalizedName("decoBlockDioriteBrickStairsLoose");

		DecoManager.register(stoneTypesLooseStairs[0]);
		DecoManager.register(stoneTypesLooseStairs[1]);
		DecoManager.register(stoneTypesLooseStairs[2]);
		DecoManager.register(stoneTypesLooseStairs[3]);
		DecoManager.register(stoneTypesLooseStairs[4]);
		DecoManager.register(stoneTypesLooseStairs[5]);

		polishedStone = new Block(DecoDefsIDs.id_polishedStone, Material.rock).setHardness(2.25F).setResistance(10.0F).SetPicksEffectiveOn().setUnlocalizedName("decoBlockPolishedStone").setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabBlock);
		polishedStoneStairs = new FCBlockStairs(DecoDefsIDs.id_polishedStoneStairs, polishedStone, 0).setHardness(2.25F).setResistance(10.0F).SetPicksEffectiveOn().setUnlocalizedName("decoBlockPolishedStoneStairs").setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabBlock);
		polishedStoneSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_polishedStoneSidingAndCorner, Material.rock, "stoneslab_top", 2.25F, 10.0F, Block.soundStoneFootstep, "decoBlockPolishedStoneSiding", "Polished Stone").SetPicksEffectiveOn();
		polishedStoneMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_polishedStoneMouldingAndDecorative, Material.rock, "stoneslab_top", "decoBlockPolishedStoneColumn", polishedStoneSidingAndCorner.blockID, 2.25F, 10.0F, Block.soundStoneFootstep, "decoBlockPolishedStoneMoulding").SetPicksEffectiveOn();

		DecoManager.register(polishedStone);
		DecoManager.register(polishedStoneStairs);
		Item.itemsList[polishedStoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(polishedStoneSidingAndCorner.blockID - 256);
		Item.itemsList[polishedStoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(polishedStoneMouldingAndDecorative.blockID - 256);

		stoneBrickMossyStairs = new FCBlockStairs(DecoDefsIDs.id_stoneBrickMossyStairs, Block.stoneBrick, 1).setUnlocalizedName("decoBlockStoneBrickMossyStairs");
		stoneBrickMossySidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_stoneBrickMossySidingAndCorner, Material.rock, "stonebricksmooth_mossy", 2.25F, 10.0F, Block.soundStoneFootstep, "decoBlockStoneBrickMossySiding", "Mossy Stone Brick").SetPicksEffectiveOn();
		stoneBrickMossyMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_stoneBrickMossyMouldingAndDecorative, Material.rock, "stonebricksmooth_mossy", "decoBlockStoneBrickMossyColumn", stoneBrickMossySidingAndCorner.blockID, 2.25F, 10.0F, Block.soundStoneFootstep, "decoBlockStoneBrickMossyMoulding").SetPicksEffectiveOn();
		stoneBrickCrackedStairs = new FCBlockStairs(DecoDefsIDs.id_stoneBrickCrackedStairs, Block.stoneBrick, 2).setUnlocalizedName("decoBlockStoneBrickCrackedStairs");
		stoneBrickCrackedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_stoneBrickCrackedSidingAndCorner, Material.rock, "stonebricksmooth_cracked", 2.25F, 10.0F, Block.soundStoneFootstep, "decoBlockStoneBrickCrackedSiding", "Cracked Stone Brick").SetPicksEffectiveOn();
		stoneBrickCrackedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_stoneBrickCrackedMouldingAndDecorative, Material.rock, "stonebricksmooth_cracked", "decoBlockStoneBrickCrackedColumn", stoneBrickCrackedSidingAndCorner.blockID, 2.25F, 10.0F, Block.soundStoneFootstep, "decoBlockStoneBrickCrackedMoulding").SetPicksEffectiveOn();

		DecoManager.register(stoneBrickMossyStairs);
		DecoManager.register(stoneBrickCrackedStairs);
		Item.itemsList[stoneBrickMossySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(stoneBrickMossySidingAndCorner.blockID - 256);
		Item.itemsList[stoneBrickMossyMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(stoneBrickMossyMouldingAndDecorative.blockID - 256);
		Item.itemsList[stoneBrickCrackedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(stoneBrickCrackedSidingAndCorner.blockID - 256);
		Item.itemsList[stoneBrickCrackedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(stoneBrickCrackedMouldingAndDecorative.blockID - 256);
		
		infestedGranite = new FCBlockSilverfish(DecoDefsIDs.id_infestedGranite, stoneTypes, 0);
		infestedAndesite = new FCBlockSilverfish(DecoDefsIDs.id_infestedAndesite, stoneTypes, 1);
		infestedDiorite = new FCBlockSilverfish(DecoDefsIDs.id_infestedDiorite, stoneTypes, 2);
		infestedSlate = new FCBlockSilverfish(DecoDefsIDs.id_infestedSlate, Block.stone, 4);

		//Sandstone
		redSand = new DecoBlockRedSand(DecoDefsIDs.id_redSand);
		redSandSlab = new DecoBlockRedSandSlab(DecoDefsIDs.id_redSandSlab);
		redSandStone = new DecoBlockRedSandStone(DecoDefsIDs.id_redSandstone);
		redSandStoneStairs = new DecoBlockStairsRedSandStone(DecoDefsIDs.id_redSandstoneStairs, redSandStone, 0).setUnlocalizedName("decoBlockRedSandStoneStairs");
		redSandStoneSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneSidingAndCorner, Material.rock, new String[] {"decoBlockRedSandstone_bottom", "decoBlockRedSandstone_top", "decoBlockRedSandstone_side", "decoBlockRedSandstoneColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneSiding", "Red Sandstone");
		redSandStoneMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneMouldingAndDecorative, Material.rock, new String[] {"decoBlockRedSandstone_bottom", "decoBlockRedSandstone_top", "decoBlockRedSandstone_side", "decoBlockRedSandstoneColumn"}, redSandStoneSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneMoulding");
		redSandStoneSmoothStairs = new DecoBlockStairsRedSandStone(DecoDefsIDs.id_redSandstoneSmoothStairs, redSandStone, 2).setUnlocalizedName("decoBlockRedSandStoneStairsSmooth");
		redSandStoneSmoothSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneSmoothSidingAndCorner, Material.rock, new String[] {"decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstoneSmooth", "decoBlockRedSandstoneSmoothColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneSmoothSiding", "Cut Red Sandstone");
		redSandStoneSmoothMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneSmoothMouldingAndDecorative, Material.rock, new String[] {"decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstoneSmooth", "decoBlockRedSandstoneSmoothColumn"}, redSandStoneSmoothSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneSmoothMoulding");
		redSandStonePolishedStairs = new DecoBlockStairsRedSandStone(DecoDefsIDs.id_polishedRedSandstoneStairs, redSandStone, 3).setUnlocalizedName("decoBlockRedSandStoneStairsPolished");
		redSandStonePolishedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_polishedRedSandstoneSidingAndCorner, Material.rock, new String[] {"decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstone_top"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstonePolishedSiding", "Polished Red Sandstone");
		redSandStonePolishedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_polishedRedSandstoneMouldingAndDecorative, Material.rock, new String[] {"decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstone_top"}, redSandStonePolishedSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstonePolishedMoulding");
		redSandStoneBrickStairs = new DecoBlockStairsRedSandStone(DecoDefsIDs.id_redSandstoneBrickStairs, redSandStone, 4).setUnlocalizedName("decoBlockRedSandStoneStairsBrick");
		redSandStoneBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneBrickSidingAndCorner, Material.rock, new String[] {"decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrickColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickSiding", "Red Sandstone Brick");
		redSandStoneBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneBrickMouldingAndDecorative, Material.rock, new String[] {"decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrickColumn"}, redSandStoneBrickSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickMoulding");
		redSandStoneMossyStairs = new DecoBlockStairsRedSandStone(DecoDefsIDs.id_redSandstoneMossyStairs, redSandStone, 5).setUnlocalizedName("decoBlockRedSandStoneMossyStairs");
		redSandStoneMossySidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneMossySidingAndCorner, Material.rock, new String[] {"decoBlockRedSandstoneMossy_bottom", "decoBlockRedSandstoneMossy_top", "decoBlockRedSandstoneMossy_side", "decoBlockRedSandstoneMossyColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneMossySiding", "Mossy Red Sandstone");
		redSandStoneMossyMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneMossyMouldingAndDecorative, Material.rock, new String[] {"decoBlockRedSandstoneMossy_bottom", "decoBlockRedSandstoneMossy_top", "decoBlockRedSandstoneMossy_side", "decoBlockRedSandstoneMossyColumn"}, redSandStoneMossySidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneMossyMoulding");
		redSandStoneBrickLargeStairs = new DecoBlockStairsRedSandStone(DecoDefsIDs.id_redSandstoneBrickLargeStairs, redSandStone, 6).setUnlocalizedName("decoBlockRedSandStoneBrickLargeStairs");
		redSandStoneBrickLargeSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneBrickLargeSidingAndCorner, Material.rock, new String[] {"decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLargeColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickLargeSiding", "Large Red Sandstone Brick");
		redSandStoneBrickLargeMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneBrickLargeMouldingAndDecorative, Material.rock, new String[] {"decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLargeColumn"}, redSandStoneBrickLargeSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickLargeMoulding");
		redSandStoneBrickLargeMossyStairs = new DecoBlockStairsRedSandStone(DecoDefsIDs.id_redSandstoneBrickLargeMossyStairs, redSandStone, 7).setUnlocalizedName("decoBlockRedSandStoneBrickLargeMossyStairs");
		redSandStoneBrickLargeMossySidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneBrickLargeMossySidingAndCorner, Material.rock, new String[] {"decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossyColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickLargeMossySiding", "Large Mossy Red Sandstone Brick");
		redSandStoneBrickLargeMossyMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneBrickLargeMossyMouldingAndDecorative, Material.rock, new String[] {"decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossyColumn"}, redSandStoneBrickLargeMossySidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickLargeMossyMoulding");
		redSandStoneCrackedStairs = new DecoBlockStairsRedSandStone(DecoDefsIDs.id_redSandstoneCrackedStairs, redSandStone, 8).setUnlocalizedName("decoBlockRedSandStoneCrackedStairs");
		redSandStoneCrackedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneCrackedSidingAndCorner, Material.rock, new String[] {"decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneCrackedSiding", "Cracked Red Sandstone");
		redSandStoneCrackedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneCrackedMouldingAndDecorative, Material.rock, new String[] {"decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom"}, redSandStoneCrackedSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneCrackedMoulding");
		redSandStoneBrickLargeCrackedStairs = new DecoBlockStairsRedSandStone(DecoDefsIDs.id_redSandstoneBrickLargeCrackedStairs, redSandStone, 9).setUnlocalizedName("decoBlockRedSandStoneBrickLargeCrackedStairs");
		redSandStoneBrickLargeCrackedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneBrickLargeCrackedSidingAndCorner, Material.rock, new String[] {"decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCrackedColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickLargeCrackedSiding", "Cracked Large Red Sandstone Brick");
		redSandStoneBrickLargeCrackedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_redSandstoneBrickLargeCrackedMouldingAndDecorative, Material.rock, new String[] {"decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCrackedColumn"}, redSandStoneBrickLargeCrackedSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickLargeCrackedMoulding");

		DecoManager.register(redSand);
		DecoManager.register(redSandSlab);
		DecoManager.register(redSandStone, ((DecoBlockRedSandStone) redSandStone).SAND_STONE_TYPES);
		DecoManager.register(redSandStoneStairs);
		DecoManager.register(redSandStoneSmoothStairs);
		DecoManager.register(redSandStonePolishedStairs);
		DecoManager.register(redSandStoneBrickStairs);
		DecoManager.register(redSandStoneMossyStairs);
		DecoManager.register(redSandStoneBrickLargeStairs);
		DecoManager.register(redSandStoneBrickLargeMossyStairs);
		DecoManager.register(redSandStoneCrackedStairs);
		DecoManager.register(redSandStoneBrickLargeCrackedStairs);

		Item.itemsList[redSandStoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneMouldingAndDecorative.blockID - 256);
		Item.itemsList[redSandStoneSmoothSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneSmoothSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneSmoothMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneSmoothMouldingAndDecorative.blockID - 256);
		Item.itemsList[redSandStonePolishedSidingAndCorner.blockID]= new FCItemBlockSidingAndCorner(redSandStonePolishedSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStonePolishedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStonePolishedMouldingAndDecorative.blockID - 256);
		Item.itemsList[redSandStoneBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneBrickSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneBrickMouldingAndDecorative.blockID - 256);
		Item.itemsList[redSandStoneMossySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneMossySidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneMossyMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneMossyMouldingAndDecorative.blockID - 256);
		Item.itemsList[redSandStoneBrickLargeSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneBrickLargeSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneBrickLargeMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneBrickLargeMouldingAndDecorative.blockID - 256);
		Item.itemsList[redSandStoneBrickLargeMossySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneBrickLargeMossySidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneBrickLargeMossyMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneBrickLargeMossyMouldingAndDecorative.blockID - 256);
		Item.itemsList[redSandStoneCrackedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneCrackedSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneCrackedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneCrackedMouldingAndDecorative.blockID - 256);
		Item.itemsList[redSandStoneBrickLargeCrackedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneBrickLargeCrackedSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneBrickLargeCrackedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneBrickLargeCrackedMouldingAndDecorative.blockID - 256);

		pileRedSand = new DecoItemPileRedSand(DecoDefsIDs.id_pileRedSand);
		Block.sandStone = Block.replaceBlock(Block.sandStone.blockID, 
				DecoBlockSandStone.class, 
				decoManager);
		DecoManager.register(Block.sandStone, new String[] {"default", "chiseled", "smooth", "polished", "brick", "mossy", "largeBrick", "largeBrickMossy", "cracked", "largeBrickCracked"});
		Block.stairsSandStone = Block.replaceBlock(Block.stairsSandStone.blockID, 
				DecoBlockStairsSandStone.class, 
				decoManager, 
				Block.sandStone, 0);

		sandStoneSmoothStairs = new DecoBlockStairsSandStone(DecoDefsIDs.id_sandstoneSmoothStairs, Block.sandStone, 2).setUnlocalizedName("decoBlockStairsSandStoneSmooth");
		sandStoneSmoothSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneSmoothSidingAndCorner, Material.rock, new String[] {"fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "sandstone_smooth", "decoBlockSandstoneSmoothColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneSmoothSiding", "Smooth Sandstone");
		sandStoneSmoothMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneSmoothMouldingAndDecorative, Material.rock, new String[] {"fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "sandstone_smooth", "decoBlockSandstoneSmoothColumn"}, sandStoneSmoothSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneSmoothMoulding");
		sandStonePolishedStairs = new DecoBlockStairsSandStone(DecoDefsIDs.id_polishedSandstoneStairs, Block.sandStone, 3).setUnlocalizedName("decoBlockStairsSandStonePolished");
		sandStonePolishedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_polishedSandstoneSidingAndCorner, Material.rock, new String[] {"fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstonePolishedSiding", "Polished Sandstone");
		sandStonePolishedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_polishedSandstoneMouldingAndDecorative, Material.rock, new String[] {"fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top"}, sandStonePolishedSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstonePolishedMoulding");
		sandStoneBrickStairs = new DecoBlockStairsSandStone(DecoDefsIDs.id_sandstoneBrickStairs, Block.sandStone, 4).setUnlocalizedName("decoBlockStairsSandStoneBrick");
		sandStoneBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneBrickSidingAndCorner, Material.rock, new String[] {"decoBlockSandstoneBrick", "decoBlockSandstoneBrick", "decoBlockSandstoneBrick", "decoBlockSandstoneBrickColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickSiding", "Brick Sandstone");
		sandStoneBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneBrickMouldingAndDecorative, Material.rock, new String[] {"decoBlockSandstoneBrick", "decoBlockSandstoneBrick", "decoBlockSandstoneBrick", "decoBlockSandstoneBrickColumn"}, sandStoneBrickSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickMoulding");
		sandStoneMossyStairs = new DecoBlockStairsSandStone(DecoDefsIDs.id_sandstoneMossyStairs, Block.sandStone, 5).setUnlocalizedName("decoBlockStairsSandStoneMossy");
		sandStoneMossySidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneMossySidingAndCorner, Material.rock, new String[] {"decoBlockSandstoneMossy_top", "decoBlockSandstoneMossy_top", "decoBlockSandstoneMossy_side", "decoBlockSandstoneMossyColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneMossySiding", "Mossy Sandstone");
		sandStoneMossyMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneMossyMouldingAndDecorative, Material.rock, new String[] {"decoBlockSandstoneMossy_top", "decoBlockSandstoneMossy_top", "decoBlockSandstoneMossy_side", "decoBlockSandstoneMossyColumn"}, sandStoneMossySidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneMossyMoulding");
		sandStoneBrickLargeStairs = new DecoBlockStairsSandStone(DecoDefsIDs.id_sandstoneBrickLargeStairs, Block.sandStone, 6).setUnlocalizedName("decoBlockStairsSandStoneBrickLarge");
		sandStoneBrickLargeSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneBrickLargeSidingAndCorner, Material.rock, new String[] {"decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLargeColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickLargeSiding", "Large Sandstone Brick");
		sandStoneBrickLargeMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneBrickLargeMouldingAndDecorative, Material.rock, new String[] {"decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLargeColumn"}, sandStoneBrickLargeSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickLargeMoulding");
		sandStoneBrickLargeMossyStairs = new DecoBlockStairsSandStone(DecoDefsIDs.id_sandstoneBrickLargeMossyStairs, Block.sandStone, 7).setUnlocalizedName("decoBlockStairsSandStoneBrickLargeMossy");
		sandStoneBrickLargeMossySidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneBrickLargeMossySidingAndCorner, Material.rock, new String[] {"decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossyColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickLargeMossySiding", "Large Mossy Sandstone Brick");
		sandStoneBrickLargeMossyMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneBrickLargeMossyMouldingAndDecorative, Material.rock, new String[] {"decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossyColumn"}, sandStoneBrickLargeMossySidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickLargeMossyMoulding");
		sandStoneCrackedStairs = new DecoBlockStairsSandStone(DecoDefsIDs.id_sandstoneCrackedStairs, Block.sandStone, 8).setUnlocalizedName("decoBlockStairsSandStoneCracked");
		sandStoneCrackedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneCrackedSidingAndCorner, Material.rock, new String[] {"fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneCrackedSiding", "Cracked Sandstone");
		sandStoneCrackedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneCrackedMouldingAndDecorative, Material.rock, new String[] {"fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom"}, sandStoneCrackedSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneCrackedMoulding");
		sandStoneBrickLargeCrackedStairs = new DecoBlockStairsSandStone(DecoDefsIDs.id_sandstoneBrickLargeCrackedStairs, Block.sandStone, 9).setUnlocalizedName("decoBlockStairsSandStoneBrickLargeCracked");
		sandStoneBrickLargeCrackedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneBrickLargeCrackedSidingAndCorner, Material.rock, new String[] {"decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCrackedColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickLargeCrackedSiding", "Cracked Large Sandstone Brick");
		sandStoneBrickLargeCrackedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_sandstoneBrickLargeCrackedMouldingAndDecorative, Material.rock, new String[] {"decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCrackedColumn"}, sandStoneBrickLargeCrackedSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickLargeCrackedMoulding");

		DecoManager.register(sandStoneSmoothStairs);
		DecoManager.register(sandStonePolishedStairs);
		DecoManager.register(sandStoneBrickStairs);
		DecoManager.register(sandStoneMossyStairs);
		DecoManager.register(sandStoneBrickLargeStairs);
		DecoManager.register(sandStoneBrickLargeMossyStairs);
		DecoManager.register(sandStoneCrackedStairs);
		DecoManager.register(sandStoneBrickLargeCrackedStairs);

		Item.itemsList[sandStoneSmoothSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneSmoothSidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneSmoothMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneSmoothMouldingAndDecorative.blockID - 256);
		Item.itemsList[sandStonePolishedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStonePolishedSidingAndCorner.blockID - 256);
		Item.itemsList[sandStonePolishedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStonePolishedMouldingAndDecorative.blockID - 256);
		Item.itemsList[sandStoneBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneBrickSidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneBrickMouldingAndDecorative.blockID - 256);
		Item.itemsList[sandStoneMossySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneMossySidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneMossyMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneMossyMouldingAndDecorative.blockID - 256);
		Item.itemsList[sandStoneBrickLargeSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneBrickLargeSidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneBrickLargeMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneBrickLargeMouldingAndDecorative.blockID - 256);
		Item.itemsList[sandStoneBrickLargeMossySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneBrickLargeMossySidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneBrickLargeMossyMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneBrickLargeMossyMouldingAndDecorative.blockID - 256);
		Item.itemsList[sandStoneCrackedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneCrackedSidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneCrackedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneCrackedMouldingAndDecorative.blockID - 256);
		Item.itemsList[sandStoneBrickLargeCrackedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneBrickLargeCrackedSidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneBrickLargeCrackedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneBrickLargeCrackedMouldingAndDecorative.blockID - 256);

		//Prismarine
		prismarine = new DecoBlockPrismarine(DecoDefsIDs.id_prismarine);
		prismarineLantern = new DecoBlockPrismarineLantern(DecoDefsIDs.id_prismarineLantern);
		DecoManager.register(prismarineLantern);
		FCTileEntityBeacon.AddBeaconEffect(prismarineLantern.blockID, Potion.nightVision.getId());

		prismarineSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_prismarineSidingAndCorner,  Material.rock, "decoBlockPrismarine", 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockPrismarineSiding", "Prismarine").SetPicksEffectiveOn();
		prismarineMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_prismarineMouldingAndDecorative, Material.rock, "decoBlockPrismarine", "decoBlockPrismarineColumn", prismarineSidingAndCorner.blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockPrismarineMoulding").SetPicksEffectiveOn();
		prismarineStairs = new FCBlockStairs(DecoDefsIDs.id_prismarineStairs, prismarine, 0).setUnlocalizedName("decoBlockStairsPrismarine");
		prismarineBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_prismarineBrickSidingAndCorner,  Material.rock, "decoBlockPrismarineBrick", 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockPrismarineBrickSiding", "Prismarine Brick").SetPicksEffectiveOn();
		prismarineBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_prismarineBrickMouldingAndDecorative, Material.rock, "decoBlockPrismarineBrick", "decoBlockPrismarineBrickColumn", prismarineBrickSidingAndCorner.blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockPrismarineBrickMoulding").SetPicksEffectiveOn();
		prismarineBrickStairs = new FCBlockStairs(DecoDefsIDs.id_prismarineBrickStairs, prismarine, 1).setUnlocalizedName("decoBlockStairsPrismarineBrick");
		prismarineDarkSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_prismarineDarkSidingAndCorner,  Material.rock, "decoBlockPrismarineDark", 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockPrismarineDarkSiding", "Dark Prismarine").SetPicksEffectiveOn();
		prismarineDarkMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_prismarineDarkMouldingAndDecorative, Material.rock, "decoBlockPrismarineDark", "decoBlockPrismarineDarkColumn", prismarineDarkSidingAndCorner.blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockPrismarineDarkMoulding").SetPicksEffectiveOn();
		prismarineDarkStairs = new FCBlockStairs(DecoDefsIDs.id_prismarineDarkStairs, prismarine, 2).setUnlocalizedName("decoBlockStairsPrismarineDark");

		Item.itemsList[prismarineSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(prismarineSidingAndCorner.blockID - 256);
		Item.itemsList[prismarineMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(prismarineMouldingAndDecorative.blockID - 256);
		DecoManager.register(prismarineStairs);
		Item.itemsList[prismarineBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(prismarineBrickSidingAndCorner.blockID - 256);
		Item.itemsList[prismarineBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(prismarineBrickMouldingAndDecorative.blockID - 256);
		DecoManager.register(prismarineBrickStairs);
		Item.itemsList[prismarineDarkSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(prismarineDarkSidingAndCorner.blockID - 256);
		Item.itemsList[prismarineDarkMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(prismarineDarkMouldingAndDecorative.blockID - 256);
		DecoManager.register(prismarineDarkStairs);

		prismarineShard = new Item(DecoDefsIDs.id_prismarineShard).setUnlocalizedName("decoItemPrismarineShard").setCreativeTab(CreativeTabs.tabMaterials).SetFilterableProperties(4);
		prismarineCrystal = new Item(DecoDefsIDs.id_prismarineCrystal).setUnlocalizedName("decoItemPrismarineCrystal").setCreativeTab(CreativeTabs.tabMaterials).SetFilterableProperties(4);

		//Nether brick
		netherBrick = new DecoBlockNetherBrick(DecoDefsIDs.id_netherBrick);
		netherBrickRedStairs = new FCBlockStairs(DecoDefsIDs.id_netherBrickStairs, netherBrick, 0).setUnlocalizedName("decoBlockNetherBrickRedStairs");
		netherBrickRedLoose = new DecoBlockNetherBrickRedLoose(DecoDefsIDs.id_netherBrickLoose);
		netherBrickRedLooseSlab = new DecoBlockNetherBrickRedLooseSlab(DecoDefsIDs.id_netherBrickLooseSlab);
		netherBrickRedLooseStairs = new DecoBlockNetherBrickRedLooseStairs(DecoDefsIDs.id_netherBrickLooseStairs);
		netherBrickRedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_netherBrickSidingAndCorner, FCBetterThanWolves.fcMaterialNetherRock, "decoBlockNetherBrickRed", 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockNetherBrickRedSiding", "Red Nether Brick");
		netherBrickRedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_netherBrickMouldingAndDecorative, FCBetterThanWolves.fcMaterialNetherRock, "decoBlockNetherBrickRed", "decoBlockNetherBrickRedColumn", netherBrickRedSidingAndCorner.blockID, 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockNetherBrickRedMoulding");

		DecoManager.register(netherBrickRedStairs);
		DecoManager.register(netherBrickRedLoose);
		DecoManager.register(netherBrickRedLooseSlab);
		DecoManager.register(netherBrickRedLooseStairs);
		Item.itemsList[netherBrickRedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(netherBrickRedSidingAndCorner.blockID - 256);
		Item.itemsList[netherBrickRedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(netherBrickRedMouldingAndDecorative.blockID - 256);

		netherBrickSuperheated = new DecoBlockNetherBrickSuperheated(DecoDefsIDs.id_netherBrickSuperheated);

		Block.netherBrick.setStepSound(stepSoundNetherBrick);
		Block.netherFence.setStepSound(stepSoundNetherBrick);
		Block.stairsNetherBrick.setStepSound(stepSoundNetherBrick);
		FCBetterThanWolves.fcBlockNetherBrickLoose.setStepSound(stepSoundNetherBrick);
		FCBetterThanWolves.fcBlockNetherBrickLooseSlab.setStepSound(stepSoundNetherBrick);
		FCBetterThanWolves.fcBlockNetherBrickLooseStairs.setStepSound(stepSoundNetherBrick);
		FCBetterThanWolves.fcBlockNetherBrickMouldingAndDecorative.setStepSound(stepSoundNetherBrick);
		FCBetterThanWolves.fcBlockNetherBrickSidingAndCorner.setStepSound(stepSoundNetherBrick);
		netherBrick.setStepSound(stepSoundNetherBrick);
		netherBrickRedLoose.setStepSound(stepSoundNetherBrick);
		netherBrickRedStairs.setStepSound(stepSoundNetherBrick);
		netherBrickSuperheated.setStepSound(stepSoundNetherBrick);
		netherBrickRedLooseSlab.setStepSound(stepSoundNetherBrick);
		netherBrickRedLooseStairs.setStepSound(stepSoundNetherBrick);
		netherBrickRedSidingAndCorner.setStepSound(stepSoundNetherBrick);
		netherBrickRedMouldingAndDecorative.setStepSound(stepSoundNetherBrick);

		//Netherrack
		netherrackSuperheated = new DecoBlockNetherrackSuperheated(DecoDefsIDs.id_netherrackSuperheated);
		magma = new DecoBlockMagma(DecoDefsIDs.id_magma);
		DecoManager.register(magma);

		//Basalt
		basalt = new FCBlockDirectional(DecoDefsIDs.id_basalt, FCBetterThanWolves.fcMaterialNetherRock, new String[] {"decoBlockBasalt_top", "decoBlockBasaltSmooth_top"}, new String[] {"decoBlockBasalt_side", "decoBlockBasaltSmooth_side"})
				.SetPicksEffectiveOn()
				.setCreativeTab(CreativeTabs.tabBlock)
				.setHardness(2.0F)
				.setHardness(10.0F)
				.setUnlocalizedName("decoBlockBasalt");
		DecoManager.register(basalt, new String[] {"basalt", "basaltSmooth"});

		Block.netherrack.setStepSound(stepSoundNetherrack);
		Block.oreNetherQuartz.setStepSound(stepSoundNetherrack);
		FCBetterThanWolves.fcBlockNetherrackFalling.setStepSound(stepSoundNetherrack);
		netherrackSuperheated.setStepSound(stepSoundNetherrack);
		basalt.setStepSound(stepSoundNetherrack);

		//Infused stone
		infusedStone = new DecoBlockInfusedStone(DecoDefsIDs.id_infusedStone);
		infusedStoneSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_infusedStoneSidingAndCorner, Material.rock, "decoBlockInfusedStone", 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockInfusedStoneSiding", "Infused Stone").SetPicksEffectiveOn();
		infusedStoneMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_infusedStoneMouldingAndDecorative, Material.rock, "decoBlockInfusedStone", "decoBlockInfusedStoneColumn", infusedStoneSidingAndCorner.blockID, 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockInfusedStoneMoulding").SetPicksEffectiveOn();
		infusedStoneStairs = new FCBlockStairs(DecoDefsIDs.id_infusedStoneStairs, infusedStone, 0).setUnlocalizedName("decoBlockInfusedStoneStairs");
		infusedStoneSmoothSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_infusedStoneSmoothSidingAndCorner, Material.rock, "decoBlockInfusedStoneSmooth", 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockInfusedStoneSmoothSiding", "Polished Infused Stone").SetPicksEffectiveOn();
		infusedStoneSmoothMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_infusedStoneSmoothMouldingAndDecorative, Material.rock, "decoBlockInfusedStoneSmooth", "decoBlockInfusedStoneSmoothColumn", infusedStoneSmoothSidingAndCorner.blockID, 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockInfusedStoneSmoothMoulding").SetPicksEffectiveOn();
		infusedStoneSmoothStairs = new FCBlockStairs(DecoDefsIDs.id_infusedStoneSmoothStairs, infusedStone, 1).setUnlocalizedName("decoBlockInfusedStoneSmoothStairs");
		infusedStoneBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_infusedStoneBrickSidingAndCorner, Material.rock, "decoBlockInfusedStoneBrick", 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockInfusedStoneBrickSiding", "Infused Stone Brick").SetPicksEffectiveOn();
		infusedStoneBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_infusedStoneBrickMouldingAndDecorative, Material.rock, "decoBlockInfusedStoneBrick", "decoBlockInfusedStoneBrickColumn", infusedStoneBrickSidingAndCorner.blockID, 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockInfusedStoneBrickMoulding").SetPicksEffectiveOn();
		infusedStoneBrickStairs = new FCBlockStairs(DecoDefsIDs.id_infusedStoneBrickStairs, infusedStone, 2).setUnlocalizedName("decoBlockInfusedStoneBrickStairs");

		DecoManager.register(infusedStone, new String[] {"default", "smooth", "brick", "chiseled"});
		Item.itemsList[infusedStoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(infusedStoneSidingAndCorner.blockID - 256);
		Item.itemsList[infusedStoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(infusedStoneMouldingAndDecorative.blockID - 256);
		DecoManager.register(infusedStoneStairs);
		Item.itemsList[infusedStoneSmoothSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(infusedStoneSmoothSidingAndCorner.blockID - 256);
		Item.itemsList[infusedStoneSmoothMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(infusedStoneSmoothMouldingAndDecorative.blockID - 256);
		DecoManager.register(infusedStoneSmoothStairs);
		Item.itemsList[infusedStoneBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(infusedStoneBrickSidingAndCorner.blockID - 256);
		Item.itemsList[infusedStoneBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(infusedStoneBrickMouldingAndDecorative.blockID - 256);
		DecoManager.register(infusedStoneBrickStairs);

		//Concrete
		concrete = new DecoBlockConcrete(DecoDefsIDs.id_concrete);
		concretePowder = new DecoBlockConcretePowder(DecoDefsIDs.id_concretePowder);

		concreteSidingAndCorner = new Block[16];
		concreteMouldingAndDecorative = new Block[16];
		concreteStairs = new Block[16];

		int id = DecoDefsIDs.id_concreteSubStart;
		final String main = "Concrete";

		for (int i = 0; i < 16; i++)
		{
			concreteSidingAndCorner[i] = new DecoBlockSidingAndCornerDecorativeWall(id++, Material.rock, "decoBlockConcrete_" + DecoUtilsMisc.colorOrder[i], 2.0F, 5.0F, Block.soundStoneFootstep, "decoBlockConcreteSiding." + DecoUtilsMisc.colorOrder[i], "Concrete");
			concreteMouldingAndDecorative[i] = new DecoBlockMouldingAndDecorativeWall(id++, Material.rock, "decoBlockConcrete_" + DecoUtilsMisc.colorOrder[i], "decoBlockConcreteColumn_" + DecoUtilsMisc.colorOrder[i], concreteSidingAndCorner[i].blockID, 2.0F, 5.0F, Block.soundWoodFootstep, "decoBlockConcreteMoulding." + DecoUtilsMisc.colorOrder[i]);
			concreteStairs[i] = new FCBlockStairs(id++, concrete, i).setUnlocalizedName("decoBlockConcreteStairs." + DecoUtilsMisc.colorOrder[i]);

			Item.itemsList[concreteSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(concreteSidingAndCorner[i].blockID - 256);
			Item.itemsList[concreteMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(concreteMouldingAndDecorative[i].blockID - 256);
			DecoManager.register(concreteStairs[i]);
		}

		//End Stone Brick
		endStoneBrick = new Block(DecoDefsIDs.id_endStoneBrick, Material.rock).setHardness(3.0F).setResistance(15.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockEndStoneBrick").setCreativeTab(CreativeTabs.tabBlock);
		endStoneBrickStairs = new FCBlockStairs(DecoDefsIDs.id_endStoneBrickStairs, endStoneBrick, 0).setUnlocalizedName("decoBlockEndStoneBrickStairs");
		endStoneBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_endStoneBrickSidingAndCorner, Material.rock, "decoBlockEndStoneBrick", 3.0F, 15.0F, Block.soundStoneFootstep, "decoBlockEndStoneBrickSiding", "End Stone Brick").SetPicksEffectiveOn();
		endStoneBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_endStoneBrickMouldingAndDecorative, Material.rock, "decoBlockEndStoneBrick", "decoBlockEndStoneBrickColumn", endStoneBrickSidingAndCorner.blockID, 3.0F, 15.0F, Block.soundStoneFootstep, "decoBlockEndStoneBrickMoulding").SetPicksEffectiveOn();

		DecoManager.register(endStoneBrick);
		DecoManager.register(endStoneBrickStairs);
		Item.itemsList[endStoneBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(endStoneBrickSidingAndCorner.blockID - 256);
		Item.itemsList[endStoneBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(endStoneBrickMouldingAndDecorative.blockID - 256);

		//Obsidian
		Block.obsidian = Block.replaceBlock(Block.obsidian.blockID, 
				DecoBlockObsidian.class, 
				decoManager);
		DecoManager.register(Block.obsidian, new String[] {"obsidian", "infused"});
		
		//Slate
		FCBlockStoneRough[] roughStoneStrataArray = new FCBlockStoneRough[4];
		
		for (int i = 0; i < 3; i++) {
			roughStoneStrataArray[i] = FCBlockStoneRough.m_startaLevelBlockArray[i];
		}
		
		FCBlockStoneRough.m_startaLevelBlockArray = roughStoneStrataArray;
		
		slateRough = new DecoBlockSlateRough(DecoDefsIDs.id_slateRough);
		DecoManager.register(slateRough);
		slateCobbleLoose = new DecoBlockSlateCobbleLoose(DecoDefsIDs.id_slateCobbleLoose);
		slateBrickLoose = new DecoBlockSlateBrickLoose(DecoDefsIDs.id_slateBrickLoose);
		
		slateStairs = new FCBlockStairs(DecoDefsIDs.id_slateStairs, Block.stone, 3).setUnlocalizedName("decoBlockSlateStairs");
		slateSmoothStairs = new FCBlockStairs(DecoDefsIDs.id_slateSmoothStairs, stoneTypesSmooth, 3).setUnlocalizedName("decoBlockSlateSmoothStairs");
		slateBrickStairs = new DecoBlockStairsMortared(DecoDefsIDs.id_slateBrickStairs, stoneTypesStoneBrick, 3, DecoDefsIDs.id_slateBrickLooseStairs).setUnlocalizedName("decoBlockSlateBrickStairs");
		slateCobbleStairs = new DecoBlockStairsMortared(DecoDefsIDs.id_slateCobbleStairs, stoneTypesCobble, 3, DecoDefsIDs.id_slateCobbleLooseStairs).setUnlocalizedName("decoBlockSlateCobblestoneStairs");
		slateTilesStairs = new DecoBlockStairsMortared(DecoDefsIDs.id_slateTilesStairs, stoneTypesStoneBrick, 4, DecoDefsIDs.id_slateBrickLooseStairs).setUnlocalizedName("decoBlockSlateTilesStairs");

		slateBrickLooseStairs = new DecoBlockStoneLooseStairs(DecoDefsIDs.id_slateBrickLooseStairs, slateCobbleLoose, slateBrickStairs).setUnlocalizedName("decoBlockSlateBrickStairsLoose");
		slateCobbleLooseStairs = new DecoBlockStoneLooseStairs(DecoDefsIDs.id_slateCobbleLooseStairs, slateBrickLoose, slateCobbleStairs).setUnlocalizedName("decoBlockSlateCobblestoneStairsLoose");
		
		DecoManager.register(slateStairs);
		DecoManager.register(slateSmoothStairs);
		DecoManager.register(slateBrickStairs);
		DecoManager.register(slateCobbleStairs);
		DecoManager.register(slateTilesStairs);
		DecoManager.register(slateBrickLooseStairs);
		DecoManager.register(slateCobbleLooseStairs);

		slateSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoDefsIDs.id_slateSidingAndCorner, Material.rock, new String[] {"decoBlockSlate_top", "decoBlockSlate_top", "decoBlockSlate_side"}, 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateSiding", "Slate").SetPicksEffectiveOn();
		slateMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(DecoDefsIDs.id_slateMouldingAndDecorative, Material.rock, new String[] {"decoBlockSlate_top", "decoBlockSlate_top", "decoBlockSlate_side", "decoBlockSlateColumn"}, slateSidingAndCorner.blockID, 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateMoulding").SetPicksEffectiveOn();
		slateSmoothSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_slateSmoothSidingAndCorner, Material.rock, "decoBlockSlateSmooth", 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateSmoothSiding", "Slate").SetPicksEffectiveOn();
		slateSmoothMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_slateSmoothMouldingAndDecorative, Material.rock, "decoBlockSlateSmooth", "decoBlockSlateSmoothColumn", slateSmoothSidingAndCorner.blockID, 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateSmoothMoulding").SetPicksEffectiveOn();
		slateCobbleSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_slateCobbleSidingAndCorner, Material.rock, "decoBlockSlateCobble", 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateCobbleSiding", "Slate").SetPicksEffectiveOn();
		slateCobbleMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_slateCobbleMouldingAndDecorative, Material.rock, "decoBlockSlateCobble", "decoBlockSlateCobbleColumn", slateCobbleSidingAndCorner.blockID, 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateCobbleMoulding").SetPicksEffectiveOn();
		slateBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_slateBrickSidingAndCorner, Material.rock, "decoBlockSlateBrick", 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateBrickSiding", "Slate").SetPicksEffectiveOn();
		slateBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_slateBrickMouldingAndDecorative, Material.rock, "decoBlockSlateBrick", "decoBlockSlateBrickColumn", slateBrickSidingAndCorner.blockID, 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateBrickMoulding").SetPicksEffectiveOn();
		slateTilesSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoDefsIDs.id_slateTilesSidingAndCorner, Material.rock, "decoBlockSlateTiles", 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateTilesSiding", "Slate").SetPicksEffectiveOn();
		slateTilesMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(DecoDefsIDs.id_slateTilesMouldingAndDecorative, Material.rock, "decoBlockSlateTiles", "decoBlockSlateColumn", slateTilesSidingAndCorner.blockID, 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateTilesMoulding").SetPicksEffectiveOn();

		Item.itemsList[slateSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(slateSidingAndCorner.blockID - 256);
		Item.itemsList[slateMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(slateMouldingAndDecorative.blockID - 256);
		Item.itemsList[slateSmoothSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(slateSmoothSidingAndCorner.blockID - 256);
		Item.itemsList[slateSmoothMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(slateSmoothMouldingAndDecorative.blockID - 256);
		Item.itemsList[slateCobbleSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(slateCobbleSidingAndCorner.blockID - 256);
		Item.itemsList[slateCobbleMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(slateCobbleMouldingAndDecorative.blockID - 256);
		Item.itemsList[slateBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(slateBrickSidingAndCorner.blockID - 256);
		Item.itemsList[slateBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(slateBrickMouldingAndDecorative.blockID - 256);
		Item.itemsList[slateTilesSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(slateTilesSidingAndCorner.blockID - 256);
		Item.itemsList[slateTilesMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(slateTilesMouldingAndDecorative.blockID - 256);
		
		slateStone = new DecoItemSlateStone(DecoDefsIDs.id_slateStone);
        slateBrickItem = (new Item(DecoDefsIDs.id_slateBrickItem)).setUnlocalizedName("decoItemSlateBrick").setCreativeTab(CreativeTabs.tabMaterials);

		//MUST BE LAST OR NULL POINTER
		//Has to be after reference blocks are declared
		stoneTypesLooseSlab = new DecoBlockStoneLooseSlab(DecoDefsIDs.id_stoneTypeLooseSlab, 
				new Block[] {
						graniteCobbleLoose, 
						andesiteCobbleLoose, 
						dioriteCobbleLoose, 
						graniteStoneBrickLoose, 
						andesiteStoneBrickLoose, 
						dioriteStoneBrickLoose, 
						slateCobbleLoose, 
						slateBrickLoose},
				new int[] {
						DecoDefsIDs.id_stoneSlab2, 
						DecoDefsIDs.id_stoneSlab2, 
						DecoDefsIDs.id_stoneSlab3, 
						DecoDefsIDs.id_stoneSlab3, 
						DecoDefsIDs.id_stoneSlab3, 
						DecoDefsIDs.id_stoneSlab3, 
						DecoDefsIDs.id_stoneSlab6, 
						DecoDefsIDs.id_stoneSlab6}, 
				new int[] {6, 7, 0, 1, 2, 3, 6, 7})
				.setUnlocalizedName("decoBlockStoneSlabLoose");
		Item.itemsList[DecoDefs.stoneTypesLooseSlab.blockID] = new DecoItemBlockSlab(DecoDefs.stoneTypesLooseSlab.blockID - 256);

		stoneSlab = (DecoBlockSlabStone) new DecoBlockSlabStone(DecoDefsIDs.id_stoneSlab, 
				new Block[] {
						DecoDefs.redSandStone, 
						DecoDefs.prismarine, 
						DecoDefs.prismarine, 
						DecoDefs.prismarine, 
						FCBetterThanWolves.fcAestheticOpaque, 
						DecoDefs.whiteStoneBrick, 
						Block.cobblestoneMossy, 
						DecoDefs.netherBrick}, 
				new int[] {0, 0, 1, 2, 9, 0, 0, 0},
				new boolean[] {
						false, 
						false, 
						false, 
						false, 
						false, 
						false, 
						false, 
						true}, 
				new Block[] {
						null, 
						null, 
						null, 
						null, 
						null, 
						null, 
						null, 
						netherBrickRedLooseSlab}, 
				new int[] {0, 0, 0, 0, 0, 0, 0, 0})
				.setUnlocalizedName("decoBlockStoneSlab");
		Item.itemsList[DecoDefs.stoneSlab.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab.blockID - 256);

		stoneSlab2 = (DecoBlockSlabStone) new DecoBlockSlabStone(DecoDefsIDs.id_stoneSlab2, 
				new Block[] {
						DecoDefs.stoneTypes, 
						DecoDefs.stoneTypes, 
						DecoDefs.stoneTypes, 
						DecoDefs.stoneTypesSmooth, 
						DecoDefs.stoneTypesSmooth, 
						DecoDefs.stoneTypesSmooth, 
						DecoDefs.stoneTypesCobble, 
						DecoDefs.stoneTypesCobble}, 
				new int[] {0, 1, 2, 0, 1, 2, 0, 1},
				new boolean[] {
						false, 
						false, 
						false, 
						false, 
						false, 
						false, 
						true, 
						true}, 
				new Block[] {
						null, 
						null, 
						null, 
						null, 
						null, 
						null, 
						stoneTypesLooseSlab, 
						stoneTypesLooseSlab}, 
				new int[] {0, 0, 0, 0, 0, 0, 0, 1})
				.setUnlocalizedName("decoBlockStoneSlab2");
		Item.itemsList[DecoDefs.stoneSlab2.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab2.blockID - 256);

		stoneSlab3 = (DecoBlockSlabStone) new DecoBlockSlabStone(DecoDefsIDs.id_stoneSlab3, 
				new Block[] {
						DecoDefs.stoneTypesCobble, 
						DecoDefs.stoneTypesStoneBrick, 
						DecoDefs.stoneTypesStoneBrick, 
						DecoDefs.stoneTypesStoneBrick, 
						DecoDefs.infusedStone, 
						DecoDefs.infusedStone, 
						DecoDefs.infusedStone, 
						Block.stone}, 
				new int[] {2, 0, 1, 2, 0, 1, 2, 0},
				new boolean[] {
						true, 
						true, 
						true, 
						true, 
						false, 
						false, 
						false, 
						false}, 
				new Block[] {
						stoneTypesLooseSlab, 
						stoneTypesLooseSlab, 
						stoneTypesLooseSlab, 
						stoneTypesLooseSlab, 
						null, 
						null, 
						null, 
						null}, 
				new int[] {2, 3, 4, 5, 0, 0, 0, 0})
				.setUnlocalizedName("decoBlockStoneSlab3");
		Item.itemsList[DecoDefs.stoneSlab3.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab3.blockID - 256);

		stoneSlab4 = (DecoBlockSlabStone) new DecoBlockSlabStone(DecoDefsIDs.id_stoneSlab4, 
				new Block[] {
						Block.sandStone, 
						Block.sandStone, 
						Block.sandStone, 
						DecoDefs.redSandStone, 
						DecoDefs.redSandStone, 
						DecoDefs.redSandStone, 
						Block.sandStone, 
						Block.sandStone}, 
				new int[] {2, 3, 4, 2, 3, 4, 5, 6})
				.setUnlocalizedName("decoBlockStoneSlab4");
		Item.itemsList[DecoDefs.stoneSlab4.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab4.blockID - 256);

		stoneSlab5 = (DecoBlockSlabStone) new DecoBlockSlabStone(DecoDefsIDs.id_stoneSlab5, 
				new Block[] {
						Block.sandStone, 
						DecoDefs.redSandStone, 
						DecoDefs.redSandStone, 
						DecoDefs.redSandStone, 
						Block.sandStone, 
						Block.sandStone, 
						DecoDefs.redSandStone, 
						DecoDefs.redSandStone}, 
				new int[] {7, 5, 6, 7, 8, 9, 8, 9})
				.setUnlocalizedName("decoBlockStoneSlab5");
		Item.itemsList[DecoDefs.stoneSlab5.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab5.blockID - 256);

		stoneSlab6 = (DecoBlockSlabStone) new DecoBlockSlabStone(DecoDefsIDs.id_stoneSlab6, 
				new Block[] {
						Block.stoneBrick, 
						Block.stoneBrick, 
						endStoneBrick, 
						terracotta, 
						Block.stone, 
						stoneTypesSmooth, 
						stoneTypesStoneBrick, 
						stoneTypesCobble}, 
				new int[] {1, 2, 0, 0, 3, 3, 3, 3},
				new boolean[] {
						true, 
						true, 
						false, 
						false, 
						false, 
						false, 
						true, 
						true}, 
				new Block[] {
						FCBetterThanWolves.fcBlockStoneBrickLooseSlab, 
						FCBetterThanWolves.fcBlockStoneBrickLooseSlab, 
						null, 
						null, 
						null, 
						null, 
						stoneTypesLooseSlab, 
						stoneTypesLooseSlab}, 
				new int[] {0, 0, 0, 0, 0, 0, 6, 7})
				.setUnlocalizedName("decoBlockStoneSlab6");
		Item.itemsList[DecoDefs.stoneSlab6.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab6.blockID - 256);
		
		stoneSlab7 = (DecoBlockSlabStone) new DecoBlockSlabStone(DecoDefsIDs.id_stoneSlab7, 
				new Block[] {
						stoneTypesStoneBrick, 
						shingles}, 
				new int[] {4, 0}, 
				new boolean[] {
						true, 
						false}, 
				new Block[] {
						stoneTypesLooseSlab, 
						null}, 
				new int[] {7, 0})
				.setUnlocalizedName("decoBlockStoneSlab7");
		Item.itemsList[DecoDefs.stoneSlab7.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab7.blockID - 256);

		concreteSlab = (DecoBlockSlabStone) new DecoBlockSlabStone(DecoDefsIDs.id_concreteSlab, 
				new Block[] {
						DecoDefs.concrete, 
						DecoDefs.concrete, 
						DecoDefs.concrete, 
						DecoDefs.concrete, 
						DecoDefs.concrete, 
						DecoDefs.concrete, 
						DecoDefs.concrete, 
						DecoDefs.concrete}, 
				new int[] {0, 1, 2, 3, 4, 5, 6, 7})
				.setUnlocalizedName("decoBlockConcreteSlab");
		Item.itemsList[DecoDefs.concreteSlab.blockID] = new DecoItemBlockSlab(DecoDefs.concreteSlab.blockID - 256);

		concreteSlab2 = (DecoBlockSlabStone) new DecoBlockSlabStone(DecoDefsIDs.id_concreteSlab2, 
				new Block[] {
						DecoDefs.concrete, 
						DecoDefs.concrete, 
						DecoDefs.concrete, 
						DecoDefs.concrete, 
						DecoDefs.concrete, 
						DecoDefs.concrete, 
						DecoDefs.concrete, 
						DecoDefs.concrete}, 
				new int[] {8, 9, 10, 11, 12, 13, 14, 15})
				.setUnlocalizedName("decoBlockConcreteSlab2");
		Item.itemsList[DecoDefs.concreteSlab2.blockID] = new DecoItemBlockSlab(DecoDefs.concreteSlab2.blockID - 256);
	}

	private void addWoodDefs() {
		//Logs
		FCBetterThanWolves.fcItemBark = Item.replaceItem(FCBetterThanWolves.fcItemBark.itemID, 
				DecoItemBark.class,
				decoManager);
		
		Block.wood = Block.replaceBlock(Block.wood.blockID, 
				DecoBlockLogReplace.class,
				decoManager);
		DecoManager.register(Block.wood, new String[] {"oakLog", "spruceLog", "birchLog", "jungleLog"});

		strippedLog = new DecoBlockLogStripped(DecoDefsIDs.id_strippedLog);
		DecoManager.register(strippedLog, new String[] {"oak", "spruce", "birch", "jungle"});
		
		barkLog = new DecoBlockLogBark(DecoDefsIDs.id_barkLog);
		DecoManager.register(barkLog, new String[] {"oak", "spruce", "birch", "jungle"});
		
		barkLogStripped = new DecoBlockLogBarkStripped(DecoDefsIDs.id_barkLogStripped);
		DecoManager.register(barkLogStripped, new String[] {"oak", "spruce", "birch", "jungle"});

		FCBetterThanWolves.fcBloodWood = Block.replaceBlock(FCBetterThanWolves.fcBloodWood.blockID,
				DecoBlockLogBloodReplace.class,
				decoManager);
		Item.itemsList[FCBetterThanWolves.fcBloodWood.blockID] = new DecoItemBlockBloodLogReplace(FCBetterThanWolves.fcBloodWood.blockID - 256);
		bloodLog = new DecoBlockLogBlood(DecoDefsIDs.id_bloodLog);
		Item.itemsList[bloodLog.blockID] = new DecoItemBlockLogBlood(bloodLog.blockID - 256, bloodLog, new String[] {"stripped", "wood", "strippedWood"});

		FCBetterThanWolves.fcBloodWood.setStepSound(stepSoundBloodLog);
		bloodLog.setStepSound(stepSoundBloodLog);

		cherryLog = new DecoBlockLogCherry(DecoDefsIDs.id_cherryLog);
		DecoManager.register(cherryLog, new String[] {"log", "stripped", "wood", "strippedWood"});
		acaciaLog = new DecoBlockLogAcacia(DecoDefsIDs.id_acaciaLog);
		DecoManager.register(acaciaLog, new String[] {"log", "stripped", "wood", "strippedWood"});

		cherryStump = new DecoBlockLogCherryStump(DecoDefsIDs.id_cherryStump);
		DecoManager.register(cherryStump);
		acaciaStump = new DecoBlockLogAcaciaStump(DecoDefsIDs.id_acaciaStump);
		DecoManager.register(acaciaStump);
		FCBetterThanWolves.fcBlockWorkStump = Block.replaceBlock(FCBetterThanWolves.fcBlockWorkStump.blockID, 
				DecoBlockWorkStump.class, 
				decoManager);
		
		FCBetterThanWolves.fcBlockLogDamaged = (FCBlockLogDamaged) Block.replaceBlock(FCBetterThanWolves.fcBlockLogDamaged.blockID, 
				DecoBlockLogDamaged.class, 
				decoManager, 
				"decoBlockStrippedOak_side", "decoBlockStrippedOak_top", "fcBlockTrunkTop")
				.setUnlocalizedName("decoBlockChewedOak");
		logDamagedSpruce = new DecoBlockLogDamaged(DecoDefsIDs.id_logDamagedSpruce, "decoBlockStrippedSpruce_side", "decoBlockStrippedSpruce_top", "decoBlockTrunkSpruce_top").setUnlocalizedName("decoBlockChewedSpruce");
		logDamagedBirch = new DecoBlockLogDamaged(DecoDefsIDs.id_logDamagedBirch, "decoBlockStrippedBirch_side", "decoBlockStrippedBirch_top", "decoBlockTrunkBirch_top").setUnlocalizedName("decoBlockChewedBirch");
		logDamagedJungle = new DecoBlockLogDamaged(DecoDefsIDs.id_logDamagedJungle, "decoBlockStrippedJungle_side", "decoBlockStrippedJungle_top", "decoBlockTrunkJungle_top").setUnlocalizedName("decoBlockChewedJungle");
		logDamagedBlood = new DecoBlockLogDamaged(DecoDefsIDs.id_logDamagedBlood, "decoBlockStrippedBlood_side", "decoBlockStrippedBlood_top", "decoBlockTrunkJungle_top").setUnlocalizedName("decoBlockChewedBlood");
		logDamagedCherry = new DecoBlockLogDamaged(DecoDefsIDs.id_logDamagedCherry, "decoBlockStrippedCherry_side", "decoBlockStrippedCherry_top", "decoBlockTrunkCherry_top").setUnlocalizedName("decoBlockChewedCherry");
		logDamagedAcacia = new DecoBlockLogDamaged(DecoDefsIDs.id_logDamagedAcacia, "decoBlockStrippedAcacia_side", "decoBlockStrippedAcacia_top", "decoBlockTrunkAcacia_top").setUnlocalizedName("decoBlockChewedAcacia");
		DecoManager.register(logDamagedSpruce);
		DecoManager.register(logDamagedBirch);
		DecoManager.register(logDamagedJungle);
		DecoManager.register(logDamagedBlood);
		DecoManager.register(logDamagedCherry);
		DecoManager.register(logDamagedAcacia);
		
		FCBetterThanWolves.fcBlockLogSpike = (FCBlockLogSpike) Block.replaceBlock(FCBetterThanWolves.fcBlockLogSpike.blockID, 
				DecoBlockLogSpike.class, 
				decoManager, 
				"decoBlockStrippedOak_side", "decoBlockStrippedOak_top")
				.setUnlocalizedName("decoBlockSpikeOak");
		logSpikeSpruce = new DecoBlockLogSpike(DecoDefsIDs.id_logSpikeSpruce, "decoBlockStrippedSpruce_side", "decoBlockStrippedSpruce_top").setUnlocalizedName("decoBlockSpikeSpruce");
		logSpikeBirch = new DecoBlockLogSpike(DecoDefsIDs.id_logSpikeBirch, "decoBlockStrippedBirch_side", "decoBlockStrippedBirch_top").setUnlocalizedName("decoBlockSpikeBirch");
		logSpikeJungle = new DecoBlockLogSpike(DecoDefsIDs.id_logSpikeJungle, "decoBlockStrippedJungle_side", "decoBlockStrippedJungle_top").setUnlocalizedName("decoBlockSpikeJungle");
		logSpikeBlood = new DecoBlockLogSpike(DecoDefsIDs.id_logSpikeBlood, "decoBlockStrippedBlood_side", "decoBlockStrippedBlood_top").setUnlocalizedName("decoBlockSpikeBlood");
		logSpikeCherry = new DecoBlockLogSpike(DecoDefsIDs.id_logSpikeCherry, "decoBlockStrippedCherry_side", "decoBlockStrippedCherry_top").setUnlocalizedName("decoBlockSpikeCherry");
		logSpikeAcacia = new DecoBlockLogSpike(DecoDefsIDs.id_logSpikeAcacia, "decoBlockStrippedAcacia_side", "decoBlockStrippedAcacia_top").setUnlocalizedName("decoBlockSpikeAcacia");
		DecoManager.register(logSpikeSpruce);
		DecoManager.register(logSpikeBirch);
		DecoManager.register(logSpikeJungle);
		DecoManager.register(logSpikeBlood);
		DecoManager.register(logSpikeCherry);
		DecoManager.register(logSpikeAcacia);

		//Planks
		Block.planks = Block.replaceBlock(Block.planks.blockID, 
				DecoBlockPlanks.class, 
				decoManager);
		DecoManager.register(Block.planks, new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry", "acacia"});
		Block.stairsWoodOak = Block.replaceBlock(Block.stairsWoodOak.blockID, 
				FCBlockStairsWood.class, 
				decoManager, 
				Block.planks, 0);
		Block.stairsWoodSpruce = Block.replaceBlock(Block.stairsWoodSpruce.blockID, 
				FCBlockStairsWood.class, 
				decoManager, 
				Block.planks, 1);
		Block.stairsWoodBirch = Block.replaceBlock(Block.stairsWoodBirch.blockID, 
				FCBlockStairsWood.class, 
				decoManager, 
				Block.planks, 2);
		Block.stairsWoodJungle = Block.replaceBlock(Block.stairsWoodJungle.blockID, 
				FCBlockStairsWood.class, 
				decoManager, 
				Block.planks, 3);
		FCBetterThanWolves.fcBlockWoodBloodStairs = Block.replaceBlock(FCBetterThanWolves.fcBlockWoodBloodStairs.blockID, 
				FCBlockStairsWood.class, 
				decoManager, 
				Block.planks, 4);
		
		cherryStairs = new FCBlockStairsWood(DecoDefsIDs.id_cherryStairs, Block.planks, 5).setUnlocalizedName("decoBlockCherryStairs");
		DecoManager.register(cherryStairs);
		acaciaStairs = new FCBlockStairsWood(DecoDefsIDs.id_acaciaStairs, Block.planks, 6).setUnlocalizedName("decoBlockAcaciaStairs");
		DecoManager.register(acaciaStairs);

		//Wood Sub Replace
		Block.fence = Block.replaceBlock(Block.fence.blockID, 
				DecoBlockFenceWood.class,
				decoManager);
		FCBetterThanWolves.fcBlockWoodOakSidingAndCorner = Block.replaceBlock(FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID, 
				DecoBlockWoodSidingAndCornerAndDecorative.class, 
				decoManager, 
				"FCBlockDecorativeWoodOak", "fcWoodOakSiding");
		FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner = Block.replaceBlock(FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, 
				DecoBlockWoodSidingAndCornerAndDecorative.class, 
				decoManager, 
				"fcBlockDecorativeWoodSpruce", "fcWoodSpruceSiding");
		FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner = Block.replaceBlock(FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID, 
				DecoBlockWoodSidingAndCornerAndDecorative.class, 
				decoManager, 
				"fcBlockDecorativeWoodBirch", "fcWoodBirchSiding");
		FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner = Block.replaceBlock(FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID, 
				DecoBlockWoodSidingAndCornerAndDecorative.class, 
				decoManager, 
				"fcBlockDecorativeWoodJungle", "fcWoodJungleSiding");
		FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner = Block.replaceBlock(FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner.blockID, 
				DecoBlockWoodSidingAndCornerAndDecorative.class, 
				decoManager, 
				"fcBlockDecorativeWoodBlood", "fcWoodBloodSiding");
		cherrySidingAndCorner = new DecoBlockWoodSidingAndCornerAndDecorative(DecoDefsIDs.id_cherrySidingAndCorner, 
				"decoBlockPlanksCherry", "cherrySiding")
				.setUnlocalizedName("decoBlockCherrySiding");
		acaciaSidingAndCorner = new DecoBlockWoodSidingAndCornerAndDecorative(DecoDefsIDs.id_acaciaSidingAndCorner, 
				"decoBlockPlanksAcacia", "acaciaSiding")
				.setUnlocalizedName("decoBlockAcaciaSiding");

		FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative = Block.replaceBlock(
				FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID, 
				DecoBlockWoodMouldingAndDecorative.class, 
				decoManager, 
				"FCBlockDecorativeWoodOak", "fcBlockColumnWoodOak_side", 
				FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID, "fcWoodOakMoulding");
		FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative = Block.replaceBlock(
				FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID, 
				DecoBlockWoodMouldingAndDecorative.class, 
				decoManager, 
				"fcBlockDecorativeWoodSpruce", "fcBlockColumnWoodSpruce_side", 
				FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, "fcWoodSpruceMoulding");
		FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative = Block.replaceBlock(
				FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative.blockID, 
				DecoBlockWoodMouldingAndDecorative.class, 
				decoManager, 
				"fcBlockDecorativeWoodBirch", "fcBlockColumnWoodBirch_side", 
				FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID, "fcWoodBirchMoulding");
		FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative = Block.replaceBlock(
				FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative.blockID, 
				DecoBlockWoodMouldingAndDecorative.class, 
				decoManager, 
				"fcBlockDecorativeWoodJungle", "fcBlockColumnWoodJungle_side", 
				FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID, "fcWoodJungleMoulding");
		FCBetterThanWolves.fcBlockWoodBloodMouldingAndDecorative = Block.replaceBlock(
				FCBetterThanWolves.fcBlockWoodBloodMouldingAndDecorative.blockID, 
				DecoBlockWoodMouldingAndDecorative.class, 
				decoManager, 
				"fcBlockDecorativeWoodBlood", "fcBlockColumnWoodBlood_side", 
				FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner.blockID, "fcWoodBloodMoulding");
		cherryMouldingAndDecorative = new DecoBlockWoodMouldingAndDecorative(DecoDefsIDs.id_cherryMouldingAndDecorative, 
				"decoBlockPlanksCherry", "decoBlockPlanksCherryColumn", cherrySidingAndCorner.blockID, "decoBlockCherryMoulding");
		acaciaMouldingAndDecorative = new DecoBlockWoodMouldingAndDecorative(DecoDefsIDs.id_acaciaMouldingAndDecorative, 
				"decoBlockPlanksAcacia", "decoBlockPlanksAcaciaColumn", acaciaSidingAndCorner.blockID, "decoBlockAcaciaMoulding");

		Item.itemsList[FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID] = new DecoItemBlockWoodSidingStub(FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID - 256);
		Item.itemsList[FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID] = new DecoItemBlockWoodMouldingStub(FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID - 256);
		Item.itemsList[FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID] = new DecoItemBlockWoodCornerStub(FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID - 256);
		Item.itemsList[FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative.blockID] = new DecoItemBlockWoodMouldingDecorativeStub(FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative.blockID - 256);
		Item.itemsList[FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID] = new DecoItemBlockWoodSidingDecorativeStub(FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID - 256);

		//Trapdoors
		//Forces oak trap doors to inherit texture rotations and better placement
		Block.trapdoor = Block.replaceBlock(Block.trapdoor.blockID, 
				DecoBlockTrapDoor.class, 
				decoManager);

		trapdoorSpruce = (BlockTrapDoor) new DecoBlockTrapDoor(DecoDefsIDs.id_trapdoorSpruce).setUnlocalizedName("decoBlockTrapdoorSpruce");
		trapdoorBirch = (BlockTrapDoor) new DecoBlockTrapDoor(DecoDefsIDs.id_trapdoorBirch).setUnlocalizedName("decoBlockTrapdoorBirch");
		trapdoorJungle = (BlockTrapDoor) new DecoBlockTrapDoor(DecoDefsIDs.id_trapdoorJungle).setUnlocalizedName("decoBlockTrapdoorJungle");
		trapdoorBlood = (BlockTrapDoor) new DecoBlockTrapDoor(DecoDefsIDs.id_trapdoorBlood).setUnlocalizedName("decoBlockTrapdoorBlood");
		trapdoorCherry = (BlockTrapDoor) new DecoBlockTrapDoor(DecoDefsIDs.id_trapdoorCherry).setUnlocalizedName("decoBlockTrapdoorCherry");
		trapdoorAcacia = (BlockTrapDoor) new DecoBlockTrapDoor(DecoDefsIDs.id_trapdoorAcacia).setUnlocalizedName("decoBlockTrapdoorAcacia");
		
		trapdoorIron = new DecoBlockTrapDoorIron(DecoDefsIDs.id_trapdoorIron); 

		DecoManager.register(trapdoorSpruce);
		DecoManager.register(trapdoorBirch);
		DecoManager.register(trapdoorJungle);
		DecoManager.register(trapdoorBlood);
		DecoManager.register(trapdoorCherry);
		DecoManager.register(trapdoorAcacia);
		
		DecoManager.register(trapdoorIron);

		//Doors
		Item.doorWood = Item.replaceItem(Item.doorWood.itemID, 
				DecoItemDoor.class, 
				decoManager, 
				"doorWood", Block.doorWood.blockID, true);
		Item.doorIron = Item.replaceItem(Item.doorIron.itemID, 
				DecoItemDoor.class, 
				decoManager, 
				"doorIron", Block.doorIron.blockID, false);
		itemDoorSpruce = (FCItemDoor) new DecoItemDoor(DecoDefsIDs.id_itemDoorSpruce, "decoItemDoorSpruce", DecoDefsIDs.id_doorSpruce, true);
		itemDoorBirch = (FCItemDoor) new DecoItemDoor(DecoDefsIDs.id_itemDoorBirch, "decoItemDoorBirch", DecoDefsIDs.id_doorBirch, true);
		itemDoorJungle = (FCItemDoor) new DecoItemDoor(DecoDefsIDs.id_itemDoorJungle, "decoItemDoorJungle", DecoDefsIDs.id_doorJungle, true);
		itemDoorBlood = (FCItemDoor) new DecoItemDoor(DecoDefsIDs.id_itemDoorBlood, "decoItemDoorBlood", DecoDefsIDs.id_doorBlood, true);
		itemDoorCherry = (FCItemDoor) new DecoItemDoor(DecoDefsIDs.id_itemDoorCherry, "decoItemDoorCherry", DecoDefsIDs.id_doorCherry, true);
		itemDoorAcacia = (FCItemDoor) new DecoItemDoor(DecoDefsIDs.id_itemDoorAcacia, "decoItemDoorAcacia", DecoDefsIDs.id_doorAcacia, true);
		
		Block.doorWood = Block.replaceBlock(Block.doorWood.blockID, 
				DecoBlockDoorWood.class, 
				decoManager, 
				new String[] {"doorWood_lower", "doorWood_upper"}, Item.doorWood.itemID);
		Block.doorIron = Block.replaceBlock(Block.doorIron.blockID, 
				DecoBlockDoorIron.class,
				decoManager);
		doorSpruce = (BlockDoor) new DecoBlockDoorWood(DecoDefsIDs.id_doorSpruce, 
				new String[] {"decoBlockDoorSpruce_lower", "decoBlockDoorSpruce_upper"}, itemDoorSpruce.itemID)
				.setUnlocalizedName("decoBlockDoorSpruce");
		doorBirch = (BlockDoor) new DecoBlockDoorWood(DecoDefsIDs.id_doorBirch, 
				new String[] {"decoBlockDoorBirch_lower", "decoBlockDoorBirch_upper"}, itemDoorBirch.itemID)
				.setUnlocalizedName("decoBlockDoorBirch");
		doorJungle = (BlockDoor) new DecoBlockDoorWood(DecoDefsIDs.id_doorJungle, 
				new String[] {"decoBlockDoorJungle_lower", "decoBlockDoorJungle_upper"}, itemDoorJungle.itemID)
				.setUnlocalizedName("decoBlockDoorJungle");
		doorBlood = (BlockDoor) new DecoBlockDoorWood(DecoDefsIDs.id_doorBlood, 
				new String[] {"decoBlockDoorBlood_lower", "decoBlockDoorBlood_upper"}, itemDoorBlood.itemID)
				.setUnlocalizedName("decoBlockDoorBlood");
		doorCherry = (BlockDoor) new DecoBlockDoorWood(DecoDefsIDs.id_doorCherry, 
				new String[] {"decoBlockDoorCherry_lower", "decoBlockDoorCherry_upper"}, itemDoorCherry.itemID)
				.setUnlocalizedName("decoBlockDoorCherry");
		doorAcacia = (BlockDoor) new DecoBlockDoorWood(DecoDefsIDs.id_doorAcacia, 
				new String[] {"decoBlockDoorAcacia_lower", "decoBlockDoorAcacia_upper"}, itemDoorAcacia.itemID)
				.setUnlocalizedName("decoBlockDoorAcacia");

		DecoManager.register(doorSpruce);
		DecoManager.register(doorBirch);
		DecoManager.register(doorJungle);
		DecoManager.register(doorBlood);
		DecoManager.register(doorCherry);
		DecoManager.register(doorAcacia);

		//Fence gates
		Block.fenceGate = Block.replaceBlock(Block.fenceGate.blockID, 
				DecoBlockFenceGate.class, 
				decoManager,
				"wood");
		gateSpruce = (BlockFenceGate) new DecoBlockFenceGate(DecoDefsIDs.id_gateSpruce, "decoBlockGateSpruce");
		gateBirch = (BlockFenceGate) new DecoBlockFenceGate(DecoDefsIDs.id_gateBirch, "decoBlockGateBirch");
		gateJungle = (BlockFenceGate) new DecoBlockFenceGate(DecoDefsIDs.id_gateJungle, "decoBlockGateJungle");
		gateBlood = (BlockFenceGate) new DecoBlockFenceGate(DecoDefsIDs.id_gateBlood, "decoBlockGateBlood");
		gateCherry = (BlockFenceGate) new DecoBlockFenceGate(DecoDefsIDs.id_gateCherry, "decoBlockGateCherry");
		gateAcacia = (BlockFenceGate) new DecoBlockFenceGate(DecoDefsIDs.id_gateAcacia, "decoBlockGateAcacia");

		DecoManager.register(gateSpruce);
		DecoManager.register(gateBirch);
		DecoManager.register(gateJungle);
		DecoManager.register(gateBlood);
		DecoManager.register(gateCherry);
		DecoManager.register(gateAcacia);

		//Chairs
		oakWoodChair = new DecoBlockChairWood(DecoDefsIDs.id_oakWoodChair, "Oak");
		birchWoodChair = new DecoBlockChairWood(DecoDefsIDs.id_spruceWoodChair, "Birch");
		spruceWoodChair = new DecoBlockChairWood(DecoDefsIDs.id_birchWoodChair, "Spruce");
		jungleWoodChair = new DecoBlockChairWood(DecoDefsIDs.id_jungleWoodChair, "Jungle");
		bloodWoodChair = new DecoBlockChairWood(DecoDefsIDs.id_bloodWoodChair, "Blood");
		cherryWoodChair = new DecoBlockChairWood(DecoDefsIDs.id_cherryWoodChair, "Cherry");
		acaciaWoodChair = new DecoBlockChairWood(DecoDefsIDs.id_acaciaWoodChair, "Acacia");

		//Painted planks
		planksPainted = (new DecoBlockPlanksPainted(DecoDefsIDs.id_planksPainted, "decoBlockPlanksPainted")).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(CreativeTabs.tabBlock);
		Item.itemsList[planksPainted.blockID] = new DecoItemBlockColored(planksPainted.blockID - 256, planksPainted);

		paintedPlanksSidingAndCorner = new Block[16];
		paintedPlanksMouldingAndDecorative = new Block[16];
		paintedPlanksStairs = new Block[16];

		//Sub blocks
		int start = DecoDefsIDs.id_paintedPlanksSubStart,
				end = start + 48,
				id = start;

		for (int i = 0; i < 16; i++) {
			paintedPlanksSidingAndCorner[i] = new DecoBlockPlanksPaintedSidingAndCornerAndDecorative(id++, "decoBlockPlanksPainted_"+ DecoUtilsMisc.colorOrder[i], "decoBlockPlanksPaintedSiding."+ DecoUtilsMisc.colorOrder[i]);
			paintedPlanksMouldingAndDecorative[i] = new DecoBlockPlanksPaintedMouldingAndDecorative(id++, "decoBlockPlanksPainted_"+ DecoUtilsMisc.colorOrder[i], "decoBlockPlanksPaintedColumn_"+ DecoUtilsMisc.colorOrder[i], paintedPlanksSidingAndCorner[i].blockID, "decoBlockPlanksPaintedMoulding."+ DecoUtilsMisc.colorOrder[i]);
			paintedPlanksStairs[i] = new DecoBlockStairsPaintedPlanks(id++, planksPainted, i, i).setUnlocalizedName("decoBlockPlanksPaintedStairs." + DecoUtilsMisc.colorOrder[i]).setCreativeTab(CreativeTabs.tabBlock);

			Item.itemsList[paintedPlanksSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(paintedPlanksSidingAndCorner[i].blockID - 256);
			Item.itemsList[paintedPlanksMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(paintedPlanksMouldingAndDecorative[i].blockID - 256);
			DecoManager.register(paintedPlanksStairs[i]);
		}

		bottleHempOil = new Item(DecoDefsIDs.id_bottleHempOil).setUnlocalizedName("decoItemHempOil").setCreativeTab(CreativeTabs.tabMaterials).SetBuoyant();
		woodBleach = new Item(DecoDefsIDs.id_woodBleach).setUnlocalizedName("decoItemWoodBleach").setCreativeTab(CreativeTabs.tabMaterials).SetBuoyant();
		woodStain = new Item(DecoDefsIDs.id_woodStain).setUnlocalizedName("decoItemWoodStain").setCreativeTab(CreativeTabs.tabMaterials).SetBuoyant();

		//Pergola
		pergola = new DecoBlockPergola(DecoDefsIDs.id_pergola);
		DecoManager.register(pergola);

		//Barrels
		barrelEmpty = new DecoBlockBarrelEmpty(DecoDefsIDs.id_barrelEmpty, new String[] {"decoBlockBarrelOak_top", "decoBlockBarrelSpruce_top", "decoBlockBarrelBirch_top", "decoBlockBarrelJungle_top"},
				new String[] {"decoBlockBarrelOak_side", "decoBlockBarrelSpruce_side", "decoBlockBarrelBirch_side", "decoBlockBarrelJungle_side"}).setUnlocalizedName("decoBlockBarrel1");
		barrelEmpty2 = new DecoBlockBarrelEmpty(DecoDefsIDs.id_barrelEmpty2, new String[] {"decoBlockBarrelBlood_top", "decoBlockBarrelCherry_top", "decoBlockBarrelAcacia_top"}, new String[] {"decoBlockBarrelBlood_side", "decoBlockBarrelCherry_side", "decoBlockBarrelAcacia_side"}).setUnlocalizedName("decoBlockBarrel2");
		DecoManager.register(barrelEmpty, new String[] {"oak", "spruce", "birch", "jungle"});
		DecoManager.register(barrelEmpty2, new String[] {"blood", "cherry", "acacia"});

		barrelFullOak = new DecoBlockBarrelFilled(DecoDefsIDs.id_barrelFullOak, "decoBlockBarrelOak");
		barrelFullSpruce = new DecoBlockBarrelFilled(DecoDefsIDs.id_barrelFullSpruce, "decoBlockBarrelSpruce");
		barrelFullBirch = new DecoBlockBarrelFilled(DecoDefsIDs.id_barrelFullBirch, "decoBlockBarrelBirch");
		barrelFullJungle = new DecoBlockBarrelFilled(DecoDefsIDs.id_barrelFullJungle, "decoBlockBarrelJungle");
		barrelFullBlood = new DecoBlockBarrelFilled(DecoDefsIDs.id_barrelFullBlood, "decoBlockBarrelBlood");
		barrelFullCherry = new DecoBlockBarrelFilled(DecoDefsIDs.id_barrelFullCherry, "decoBlockBarrelCherry");
		barrelFullAcacia = new DecoBlockBarrelFilled(DecoDefsIDs.id_barrelFullAcacia, "decoBlockBarrelAcacia");

		Item.itemsList[barrelFullOak.blockID] = new DecoItemBlockBarrelFilled(barrelFullOak.blockID - 256, barrelFullOak, DecoBlockBarrelFilled.typeTags, barrelEmpty, 0);
		Item.itemsList[barrelFullSpruce.blockID] = new DecoItemBlockBarrelFilled(barrelFullSpruce.blockID - 256, barrelFullSpruce, DecoBlockBarrelFilled.typeTags, barrelEmpty, 1);
		Item.itemsList[barrelFullBirch.blockID] = new DecoItemBlockBarrelFilled(barrelFullBirch.blockID - 256, barrelFullBirch, DecoBlockBarrelFilled.typeTags, barrelEmpty, 2);
		Item.itemsList[barrelFullJungle.blockID] = new DecoItemBlockBarrelFilled(barrelFullJungle.blockID - 256, barrelFullJungle, DecoBlockBarrelFilled.typeTags, barrelEmpty, 3);
		Item.itemsList[barrelFullBlood.blockID] = new DecoItemBlockBarrelFilled(barrelFullBlood.blockID - 256, barrelFullBlood, DecoBlockBarrelFilled.typeTags, barrelEmpty2, 0);
		Item.itemsList[barrelFullCherry.blockID] = new DecoItemBlockBarrelFilled(barrelFullCherry.blockID - 256, barrelFullCherry, DecoBlockBarrelFilled.typeTags, barrelEmpty2, 1);
		Item.itemsList[barrelFullAcacia.blockID] = new DecoItemBlockBarrelFilled(barrelFullAcacia.blockID - 256, barrelFullAcacia, DecoBlockBarrelFilled.typeTags, barrelEmpty2, 2);

		//Crates
		crate = new DecoBlockCrate(DecoDefsIDs.id_crate);
		DecoManager.register(crate, new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry", "acacia"});

		//Signs
		Block.signPost = Block.replaceBlock(Block.signPost.blockID, 
				DecoBlockSign.class, 
				decoManager,
				true, 0, "wood");
		signSpruce = new DecoBlockSign(DecoDefsIDs.id_signSpruce, true, 1, "wood_spruce");
		signBirch = new DecoBlockSign(DecoDefsIDs.id_signBirch, true, 2, "wood_birch");
		signJungle = new DecoBlockSign(DecoDefsIDs.id_signJungle, true, 3, "wood_jungle");
		signBlood = new DecoBlockSign(DecoDefsIDs.id_signBlood, true, 4, "fcBlockPlanks_blood");
		signCherry = new DecoBlockSign(DecoDefsIDs.id_signCherry, true, 5, "decoBlockPlanksCherry");
		signAcacia = new DecoBlockSign(DecoDefsIDs.id_signAcacia, true, 6, "decoBlockPlanksAcacia");

		Block.signWall = Block.replaceBlock(Block.signWall.blockID, 
				DecoBlockSignWall.class, 
				decoManager,
				0, "wood");
		signSpruceWall = new DecoBlockSignWall(DecoDefsIDs.id_signSpruceWall, 1, "wood_spruce");
		signBirchWall = new DecoBlockSignWall(DecoDefsIDs.id_signBirchWall, 2, "wood_birch");
		signJungleWall = new DecoBlockSignWall(DecoDefsIDs.id_signJungleWall, 3, "wood_jungle");
		signBloodWall = new DecoBlockSignWall(DecoDefsIDs.id_signBloodWall, 4, "fcBlockPlanks_blood");
		signCherryWall = new DecoBlockSignWall(DecoDefsIDs.id_signCherryWall, 5, "decoBlockPlanksCherry");
		signAcaciaWall = new DecoBlockSignWall(DecoDefsIDs.id_signAcaciaWall, 6, "decoBlockPlanksAcacia");

		Item.sign = Item.replaceItem(Item.sign.itemID, 
				DecoItemSign.class,
				decoManager)
				.setUnlocalizedName("sign");
		
		//Bookshelves
		Block.bookShelf = Block.replaceBlock(Block.bookShelf.blockID, 
				DecoBlockBookshelf.class, 
				decoManager,
				"decoBlockBookshelf", DecoBlockBookshelf.Type.ENCHANTED)
				.setUnlocalizedName("bookshelf");
		bookshelf = new DecoBlockBookshelf(DecoDefsIDs.id_bookshelf, "decoBlockBookshelf", DecoBlockBookshelf.Type.FULL);
		bookshelfEmpty = new DecoBlockBookshelf(DecoDefsIDs.id_bookshelfEmpty, "decoBlockBookshelfEmpty", DecoBlockBookshelf.Type.EMPTY);
		bottleRack = new DecoBlockBookshelf(DecoDefsIDs.id_bottleRack, "decoBlockBottleRack", DecoBlockBookshelf.Type.RACK);
		bottleRackEmpty = new DecoBlockBookshelf(DecoDefsIDs.id_bottleRackEmpty, "decoBlockBottleRackEmpty", DecoBlockBookshelf.Type.RACK_EMPTY);

		Item.itemsList[Block.bookShelf.blockID] = new DecoItemBlockBookshelf(Block.bookShelf, new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry", "acacia"});
		Item.itemsList[bookshelf.blockID] = new DecoItemBlockBookshelf(bookshelf, new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry", "acacia"});
		Item.itemsList[bookshelfEmpty.blockID] = new DecoItemBlockBookshelf(bookshelfEmpty, new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry", "acacia"});
		Item.itemsList[bottleRack.blockID] = new DecoItemBlockBookshelf(bottleRack, new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry", "acacia"});
		Item.itemsList[bottleRackEmpty.blockID] = new DecoItemBlockBookshelf(bottleRackEmpty, new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry", "acacia"});
		
		Item.book = Item.replaceItem(Item.book.itemID, 
				DecoItemBookEnchanted.class,
				decoManager)
				.setUnlocalizedName("decoItemBookEnchanted");
		bookPlain = new FCItemBook(DecoDefsIDs.id_book);
		
		//Ladders
		FCBetterThanWolves.fcBlockLadder = (FCBlockLadder) Block.replaceBlock(FCBetterThanWolves.fcBlockLadder.blockID, 
				DecoBlockLadder.class, 
				decoManager,
				FCBetterThanWolves.fcBlockLadderOnFire.blockID);
		FCBetterThanWolves.fcBlockLadderOnFire = (FCBlockLadderOnFire) Block.replaceBlock(FCBetterThanWolves.fcBlockLadderOnFire.blockID,
				DecoBlockLadderOnFire.class, 
				decoManager,
				FCBetterThanWolves.fcBlockLadder.blockID);
		
		ladderSpruce = new DecoBlockLadder(DecoDefsIDs.id_ladderSpruce, DecoDefsIDs.id_ladderSpruceOnFire).setUnlocalizedName("decoBlockLadderSpruce");
		ladderSpruceOnFire = new DecoBlockLadderOnFire(DecoDefsIDs.id_ladderSpruceOnFire, DecoDefsIDs.id_ladderSpruce).setUnlocalizedName("decoBlockLadderSpruce");
		
		ladderBirch = new DecoBlockLadder(DecoDefsIDs.id_ladderBirch, DecoDefsIDs.id_ladderBirchOnFire).setUnlocalizedName("decoBlockLadderBirch");
		ladderBirchOnFire = new DecoBlockLadderOnFire(DecoDefsIDs.id_ladderBirchOnFire, DecoDefsIDs.id_ladderBirch).setUnlocalizedName("decoBlockLadderBirch");
		
		ladderJungle = new DecoBlockLadder(DecoDefsIDs.id_ladderJungle, DecoDefsIDs.id_ladderJungleOnFire).setUnlocalizedName("decoBlockLadderJungle");
		ladderJungleOnFire = new DecoBlockLadderOnFire(DecoDefsIDs.id_ladderJungleOnFire, DecoDefsIDs.id_ladderJungle).setUnlocalizedName("decoBlockLadderJungle");
		
		ladderBlood = new DecoBlockLadder(DecoDefsIDs.id_ladderBlood, DecoDefsIDs.id_ladderBloodOnFire).setUnlocalizedName("decoBlockLadderBlood");
		ladderBloodOnFire = new DecoBlockLadderOnFire(DecoDefsIDs.id_ladderBloodOnFire, DecoDefsIDs.id_ladderBlood).setUnlocalizedName("decoBlockLadderBlood");
		
		ladderCherry = new DecoBlockLadder(DecoDefsIDs.id_ladderCherry, DecoDefsIDs.id_ladderCherryOnFire).setUnlocalizedName("decoBlockLadderCherry");
		ladderCherryOnFire = new DecoBlockLadderOnFire(DecoDefsIDs.id_ladderCherryOnFire, DecoDefsIDs.id_ladderCherry).setUnlocalizedName("decoBlockLadderCherry");
		
		ladderAcacia = new DecoBlockLadder(DecoDefsIDs.id_ladderAcacia, DecoDefsIDs.id_ladderAcaciaOnFire).setUnlocalizedName("decoBlockLadderAcacia");
		ladderAcaciaOnFire = new DecoBlockLadderOnFire(DecoDefsIDs.id_ladderAcaciaOnFire, DecoDefsIDs.id_ladderAcacia).setUnlocalizedName("decoBlockLadderAcacia");
		
		DecoManager.register(ladderSpruce);
		DecoManager.register(ladderBirch);
		DecoManager.register(ladderJungle);
		DecoManager.register(ladderBlood);
		DecoManager.register(ladderCherry);
		DecoManager.register(ladderAcacia);
		
		ladderIron = new DecoBlockLadderIron(DecoDefsIDs.id_ladderIron);
		DecoManager.register(ladderIron);

		//Slabs
		planksPaintedSlab = (DecoBlockWoodSlab) new DecoBlockWoodSlab(DecoDefsIDs.id_paintedPlanksSlab, new Block[] {planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted}, new int[] {0, 1, 2, 3, 4, 5, 6, 7},
				new int[] {paintedPlanksMouldingAndDecorative[0].blockID, paintedPlanksMouldingAndDecorative[1].blockID, paintedPlanksMouldingAndDecorative[2].blockID, paintedPlanksMouldingAndDecorative[3].blockID, paintedPlanksMouldingAndDecorative[4].blockID, paintedPlanksMouldingAndDecorative[5].blockID, paintedPlanksMouldingAndDecorative[6].blockID, paintedPlanksMouldingAndDecorative[7].blockID},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0}).setUnlocalizedName("decoBlockPlanksPaintedSlab");
		Item.itemsList[DecoDefs.planksPaintedSlab.blockID] = new DecoItemBlockSlab(DecoDefs.planksPaintedSlab.blockID - 256);

		planksPaintedSlab2 = (DecoBlockWoodSlab) new DecoBlockWoodSlab(DecoDefsIDs.id_paintedPlanksSlab2, new Block[] {planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted}, new int[] {8, 9, 10, 11, 12, 13, 14, 15},
				new int[] {paintedPlanksMouldingAndDecorative[8].blockID, paintedPlanksMouldingAndDecorative[9].blockID, paintedPlanksMouldingAndDecorative[10].blockID, paintedPlanksMouldingAndDecorative[11].blockID, paintedPlanksMouldingAndDecorative[12].blockID, paintedPlanksMouldingAndDecorative[13].blockID, paintedPlanksMouldingAndDecorative[14].blockID, paintedPlanksMouldingAndDecorative[15].blockID},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0}).setUnlocalizedName("decoBlockPlanksPaintedSlab2");
		Item.itemsList[DecoDefs.planksPaintedSlab2.blockID] = new DecoItemBlockSlab(DecoDefs.planksPaintedSlab2.blockID - 256);

		woodSlab = (DecoBlockWoodSlab) new DecoBlockWoodSlab(DecoDefsIDs.id_woodSlab, new Block[] {Block.planks, Block.planks}, new int[] {5, 6},
				new int[] {FCBetterThanWolves.fcBlockWoodMouldingItemStubID, FCBetterThanWolves.fcBlockWoodMouldingItemStubID}, new int[] {5, 6}).setUnlocalizedName("decoBlockWoodSlab").setCreativeTab(null);
		Item.itemsList[DecoDefs.woodSlab.blockID] = new DecoItemBlockSlab(DecoDefs.woodSlab.blockID - 256);
		
		Block.woodSingleSlab = (BlockHalfSlab) Block.replaceBlock(Block.woodSingleSlab.blockID, 
				DecoBlockWoodSlabReplace.class, 
				decoManager,
				false);
		Block.woodDoubleSlab = (BlockHalfSlab) Block.replaceBlock(Block.woodDoubleSlab.blockID, 
				DecoBlockWoodSlabReplace.class, 
				decoManager,
				true);
        Item.itemsList[Block.woodSingleSlab.blockID] = (new ItemSlab(Block.woodSingleSlab.blockID - 256, Block.woodSingleSlab, Block.woodDoubleSlab, false)).setUnlocalizedName("woodSlab");
        Item.itemsList[Block.woodDoubleSlab.blockID] = (new ItemSlab(Block.woodDoubleSlab.blockID - 256, Block.woodSingleSlab, Block.woodDoubleSlab, true)).setUnlocalizedName("woodSlab");
	}

	private void addDecoDefs() {
		//Aesthetic Opaque
		FCBetterThanWolves.fcAestheticOpaque = Block.replaceBlock(FCBetterThanWolves.fcAestheticOpaque.blockID, 
				DecoBlockAestheticOpaque.class,
				decoManager);
		Item.itemsList[FCBetterThanWolves.fcAestheticOpaque.blockID] = new DecoItemBlockAestheticOpaque(FCBetterThanWolves.fcAestheticOpaque.blockID - 256);
		
		//Diamondium
		blockDiamondium = new Block(DecoDefsIDs.id_blockDiamondium, Material.iron).setHardness(10.0F).setResistance(2000.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockDiamond").setCreativeTab(CreativeTabs.tabBlock);
		Block.blockDiamond.setUnlocalizedName("decoBlockDiamond");
		FCTileEntityBeacon.AddBeaconEffect(blockDiamondium.blockID, FCBetterThanWolves.potionFortune.getId());

		DecoManager.register(blockDiamondium);

		//Haybale and thatch
		hayBale = new DecoBlockHayBale(DecoDefsIDs.id_hayBale);
		hayBaleStairs = new DecoBlockStairsHay(DecoDefsIDs.id_hayBaleStairs);
		thatch = new DecoBlockThatch(DecoDefsIDs.id_thatch);
		thatchStairs = new DecoBlockStairsThatch(DecoDefsIDs.id_thatchStairs);

		//Lanterns
		paperWall = new DecoBlockPaperWall(DecoDefsIDs.id_paperWall);
		fenceSteel = new DecoBlockWroughtBars(DecoDefsIDs.id_fenceSteel);
		DecoManager.register(fenceSteel);
		DecoManager.register(paperWall);

		lanternPaper = new DecoBlockLantern(DecoDefsIDs.id_lanternPaper,Material.wood,.3F,"Paper",true).SetAxesEffectiveOn(true);
		lanternPaperBroken = new DecoBlockLantern(DecoDefsIDs.id_lanternPaperBroken,Material.wood,.3F,"PaperBroken",false).setLightValue(0).SetAxesEffectiveOn(true);
		chandelier = new DecoBlockChandelier(DecoDefsIDs.id_chandelier);
		lanternSteel = new DecoBlockLantern(DecoDefsIDs.id_lanternSteel,Material.iron,.5F,"Iron", false).SetPicksEffectiveOn(true);

		//Workbench
		workbench = new DecoBlockWorkbench(DecoDefsIDs.id_workbench);
		DecoManager.register(workbench);

		//Coarse Dirt
		coarseDirt = new DecoBlockDirtCoarse(DecoDefsIDs.id_coarseDirt);
		DecoManager.register(coarseDirt);
		coarseDirtSlab = new DecoBlockDirtCoarseSlab(DecoDefsIDs.id_coarseDirtSlab);
		DecoManager.register(coarseDirtSlab);

		//Podzol
		podzol = new DecoBlockPodzol(DecoDefsIDs.id_podzol);
		DecoManager.register(podzol);
		//podzolSlab = new AddonBlockDirtSlab(DecoDefsIDs.id_podzolSlab));

		//Dirt Replace
		Block.grass = (BlockGrass) Block.replaceBlock(Block.grass.blockID, 
				DecoBlockGrass.class, 
				decoManager);
		Block.dirt = Block.replaceBlock(Block.dirt.blockID, 
				DecoBlockDirt.class,
				decoManager);
		Block.mycelium = (BlockMycelium) Block.replaceBlock(Block.mycelium.blockID, 
				DecoBlockMycelium.class,
				decoManager);
		FCBetterThanWolves.fcBlockDirtLoose = Block.replaceBlock(FCBetterThanWolves.fcBlockDirtLoose.blockID, 
				DecoBlockDirtLoose.class, 
				decoManager);
		FCBetterThanWolves.fcBlockDirtSlab = (FCBlockDirtSlab) Block.replaceBlock(FCBetterThanWolves.fcBlockDirtSlab.blockID, 
				DecoBlockDirtSlab.class, 
				decoManager);
		FCBetterThanWolves.fcBlockGrassSlab = (FCBlockGrassSlab) Block.replaceBlock(FCBetterThanWolves.fcBlockGrassSlab.blockID, 
				DecoBlockGrassSlab.class, 
				decoManager);
		
		RenderBlocksUtils.grassBlock = Block.grass;
		RenderBlocksUtils.fcDirtSlab = FCBetterThanWolves.fcBlockDirtSlab;

		//Nether portal
		Block.portal = (BlockPortal) Block.replaceBlock(Block.portal.blockID, 
				DecoBlockPortal.class,
				decoManager);

		//Pumpkins
		pumpkin = new DecoBlockPumpkinCarved(DecoDefsIDs.id_pumpkin);
		DecoManager.register(pumpkin, new String[] {"0", "1", "2"});
		pumpkinLit = new DecoBlockPumpkinLit(DecoDefsIDs.id_pumpkinLit);
		DecoManager.register(pumpkinLit, new String[] {"0", "1", "2"});
		Item.itemsList[FCBetterThanWolves.fcBlockPumpkinFresh.blockID] = new DecoItemBlockPumpkinFresh(FCBetterThanWolves.fcBlockPumpkinFresh.blockID - 256);

		//Carpets
		carpet = new DecoBlockCarpet(DecoDefsIDs.id_carpet);
		DecoManager.register(carpet, DecoUtilsMisc.colorOrder);

		//Coal block
		coalBlock = new Block(DecoDefsIDs.id_coalBlock, Material.rock).setUnlocalizedName("decoBlockCoal").SetPicksEffectiveOn().SetFireProperties(FCEnumFlammability.EXTREME).setHardness(1.5F).setResistance(10.0F).setCreativeTab(CreativeTabs.tabBlock);
		DecoManager.register(coalBlock);
		netherCoalBlock = new DecoBlockNetherCoal(DecoDefsIDs.id_netherCoalBlock);
		DecoManager.register(netherCoalBlock);

		//Fire
		Block.fire = (BlockFire) Block.replaceBlock(Block.fire.blockID, 
				DecoBlockFire.class,
				decoManager);
		FCBetterThanWolves.fcBlockFireStoked = Block.replaceBlock(FCBetterThanWolves.fcBlockFireStoked.blockID, 
				DecoBlockFireStoked.class, 
				decoManager);

		//Bone
		bonePillar = new FCBlockDirectional(DecoDefsIDs.id_bonePillar, FCBetterThanWolves.fcMaterialMiscellaneous, new String[] {"decoBlockBonePillar_top"}, new String[] {"decoBlockBonePillar_side"})
				.setHardness(2.0F)
				.SetPicksEffectiveOn()
				.SetBuoyancy(1.0F)
				.setStepSound(Block.soundStoneFootstep)
				.setCreativeTab(CreativeTabs.tabBlock)
				.setUnlocalizedName("decoBlockBonePillar");
		Item.itemsList[bonePillar.blockID] = new DecoItemBlockWithCustomSound(bonePillar.blockID - 256);

		bonePillar.setStepSound(stepSoundBone);
		FCBetterThanWolves.fcBlockBoneSlab.setStepSound(stepSoundBone);

		//Buttons
		Block.woodenButton = Block.replaceBlock(Block.woodenButton.blockID, 
				DecoBlockButtonWood.class, 
				decoManager,
				Block.planks, 0);
		Block.stoneButton = Block.replaceBlock(Block.stoneButton.blockID, 
				DecoBlockButtonStone.class, 
				decoManager,
				Block.stone, 0);

		buttonSpruce = new DecoBlockButtonWood(DecoDefsIDs.id_buttonSpruce, Block.planks, 1).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("decoBlockButtonSpruce");
		buttonBirch = new DecoBlockButtonWood(DecoDefsIDs.id_buttonBirch, Block.planks, 2).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("decoBlockButtonBirch");
		buttonJungle = new DecoBlockButtonWood(DecoDefsIDs.id_buttonJungle, Block.planks, 3).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("decoBlockButtonJungle");
		buttonBlood = new DecoBlockButtonWood(DecoDefsIDs.id_buttonBlood, Block.planks, 4).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("decoBlockButtonBlood");
		buttonCherry = new DecoBlockButtonWood(DecoDefsIDs.id_buttonCherry, Block.planks, 5).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("decoBlockButtonCherry");

		buttonInfusedStone = new DecoBlockButtonStone(DecoDefsIDs.id_buttonInfusedStone, infusedStone, 0).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockButtonInfusedStone");
		buttonGranite = new DecoBlockButtonStone(DecoDefsIDs.id_buttonGranite, stoneTypes, 0).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockButtonGranite");
		buttonAndesite = new DecoBlockButtonStone(DecoDefsIDs.id_buttonAndesite, stoneTypes, 1).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockButtonAndesite");
		buttonDiorite = new DecoBlockButtonStone(DecoDefsIDs.id_buttonDiorite, stoneTypes, 2).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockButtonDiorite");
		buttonSandstone = new DecoBlockButtonStone(DecoDefsIDs.id_buttonSandstone, Block.sandStone, 3).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockButtonSandstone");
		buttonRedSandstone = new DecoBlockButtonStone(DecoDefsIDs.id_buttonRedSandstone, redSandStone, 3).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockButtonRedSandstone");

		DecoManager.register(buttonSpruce);
		DecoManager.register(buttonBirch);
		DecoManager.register(buttonJungle);
		DecoManager.register(buttonBlood);
		DecoManager.register(buttonCherry);

		DecoManager.register(buttonInfusedStone);
		DecoManager.register(buttonGranite);
		DecoManager.register(buttonAndesite);
		DecoManager.register(buttonDiorite);
		DecoManager.register(buttonSandstone);
		DecoManager.register(buttonRedSandstone);

		//Trees
		cherrySapling = new DecoBlockSaplingCherry(DecoDefsIDs.id_cherrySapling);
		Item.itemsList[cherrySapling.blockID] = new ItemMultiTextureTile(DecoDefsIDs.id_cherrySapling - 256, cherrySapling, DecoBlockSaplingCherry.SAPLING_TYPES);
		cherryLeaves = new DecoBlockLeavesCherry(DecoDefsIDs.id_cherryLeaves);
		DecoManager.register(cherryLeaves);
		
		acaciaSapling = new DecoBlockSaplingAcacia(DecoDefsIDs.id_acaciaSapling);
		Item.itemsList[acaciaSapling.blockID] = new ItemMultiTextureTile(DecoDefsIDs.id_acaciaSapling - 256, acaciaSapling, DecoBlockSaplingAcacia.SAPLING_TYPES);
		acaciaLeaves = new DecoBlockLeavesAcacia(DecoDefsIDs.id_acaciaLeaves);
		DecoManager.register(acaciaLeaves);
		
		autumnLeaves = new DecoBlockLeavesAutumn(DecoDefsIDs.id_autumnLeaves);
		Item.itemsList[autumnLeaves.blockID] = new DecoItemBlockMulti(autumnLeaves, DecoBlockLeavesAutumn.LEAF_TYPES);
		autumnSapling = new DecoBlockSaplingAutumn(DecoDefsIDs.id_autumnSapling);
		Item.itemsList[autumnSapling.blockID] = new ItemMultiTextureTile(DecoDefsIDs.id_autumnSapling - 256, autumnSapling, DecoBlockSaplingAutumn.SAPLING_TYPES);

		//Hedge
		hedge = (BlockLeaves) new DecoBlockHedge(DecoDefsIDs.id_hedge).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("decoBlockHedge");
		DecoManager.register(hedge, new String[] {"oak", "spruce", "birch", "jungle"});

		hedgeOakStairs = new DecoBlockStairsHedge(DecoDefsIDs.id_hedgeOakStairs, hedge, 0, true).setUnlocalizedName("decoBlockHedgeOakStairs");
		hedgeSpruceStairs = new DecoBlockStairsHedge(DecoDefsIDs.id_hedgeSpruceStairs, hedge, 1, true).setUnlocalizedName("decoBlockHedgeSpruceStairs");
		hedgeBirchStairs = new DecoBlockStairsHedge(DecoDefsIDs.id_hedgeBirchStairs, hedge, 2, true).setUnlocalizedName("decoBlockHedgeBirchStairs");
		hedgeJungleStairs = new DecoBlockStairsHedge(DecoDefsIDs.id_hedgeJungleStairs, hedge, 3, true).setUnlocalizedName("decoBlockHedgeJungleStairs");
		hedgeBloodStairs = new DecoBlockStairsHedge(DecoDefsIDs.id_hedgeBloodStairs, FCBetterThanWolves.fcBlockBloodLeaves, 0, true).setUnlocalizedName("decoBlockHedgeBloodStairs");
		hedgeCherryStairs = new DecoBlockStairsHedge(DecoDefsIDs.id_hedgeCherryStairs, cherryLeaves, 0, false).setUnlocalizedName("decoBlockHedgeCherryStairs");

		hedgeOakSidingAndCorner = new DecoBlockHedgeSidingAndCornerDecorativeWall(DecoDefsIDs.id_hedgeOakSidingandCorner, materialHedge, "leaves", 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeOakSiding", "Oak Hedge", true, hedge, 0);
		hedgeOakMouldingAndDecorative = new DecoBlockHedgeMouldingAndDecorative(DecoDefsIDs.id_hedgeOakMoulingAndDecorative, materialHedge, "leaves", "leaves", hedgeOakSidingAndCorner.blockID, 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeOakMoulding", true, hedge, 0);
		hedgeSpruceSidingAndCorner = new DecoBlockHedgeSidingAndCornerDecorativeWall(DecoDefsIDs.id_hedgeSpruceSidingAndCorner, materialHedge, "leaves", 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeSpruceSiding", "Spruce Hedge", true, hedge, 1);
		hedgeSpruceMouldingAndDecorative = new DecoBlockHedgeMouldingAndDecorative(DecoDefsIDs.id_hedgeSpruceMouldingAndDecorative, materialHedge, "leaves", "leaves", hedgeSpruceSidingAndCorner.blockID, 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeSpruceMoulding", true, hedge, 1);
		hedgeBirchSidingAndCorner = new DecoBlockHedgeSidingAndCornerDecorativeWall(DecoDefsIDs.id_hedgeBirchSidingAndCorner, materialHedge, "leaves_spruce", 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeBirchSiding", "Birch Hedge", true, hedge, 2);
		hedgeBirchMouldingAndDecorative = new DecoBlockHedgeMouldingAndDecorative(DecoDefsIDs.id_hedgeBirchMouldingAndDecorative, materialHedge, "leaves_spruce", "leaves_spruce", hedgeBirchSidingAndCorner.blockID, 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeBirchMoulding", true, hedge, 2);
		hedgeJungleSidingAndCorner = new DecoBlockHedgeSidingAndCornerDecorativeWall(DecoDefsIDs.id_hedgeJungleSidingAndCorner, materialHedge, "leaves_jungle", 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeJungleSiding", "Jungle Hedge", true, hedge, 3);
		hedgeJungleMouldingAndDecorative = new DecoBlockHedgeMouldingAndDecorative(DecoDefsIDs.id_hedgeJungleMouldingAndDecorative, materialHedge, "leaves_jungle", "leaves_jungle", hedgeJungleSidingAndCorner.blockID, 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeJungleMoulding", true, hedge, 3);
		hedgeBloodSidingAndCorner = new DecoBlockHedgeSidingAndCornerDecorativeWall(DecoDefsIDs.id_hedgeBloodSidingAndCorner, materialHedge, "leaves", 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeBloodSiding", "Blood Hedge", true, FCBetterThanWolves.fcBlockBloodLeaves, 0);
		hedgeBloodMouldingAndDecorative = new DecoBlockHedgeMouldingAndDecorative(DecoDefsIDs.id_hedgeBloodMouldingAndDecorative, materialHedge, "leaves", "leaves", hedgeBloodSidingAndCorner.blockID, 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeBloodMoulding", true, FCBetterThanWolves.fcBlockBloodLeaves, 0);
		hedgeCherrySidingAndCorner = new DecoBlockHedgeSidingAndCornerDecorativeWall(DecoDefsIDs.id_hedgeCherrySidingAndCorner, materialHedge, "decoBlockLeavesCherry", 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeCherrySiding", "Cherry Hedge", false, cherryLeaves, 0);
		hedgeCherryMouldingAndDecorative = new DecoBlockHedgeMouldingAndDecorative(DecoDefsIDs.id_hedgeCherryMouldingAndDecorative, materialHedge, "decoBlockLeavesCherry", "decoBlockLeavesCherry", hedgeCherrySidingAndCorner.blockID, 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeCherryMoulding", false, cherryLeaves, 0);

		DecoManager.register(hedgeOakStairs);
		DecoManager.register(hedgeSpruceStairs);
		DecoManager.register(hedgeBirchStairs);
		DecoManager.register(hedgeJungleStairs);
		DecoManager.register(hedgeBloodStairs);
		DecoManager.register(hedgeCherryStairs);

		Item.itemsList[hedgeOakSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(hedgeOakSidingAndCorner.blockID - 256);
		Item.itemsList[hedgeOakMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(hedgeOakMouldingAndDecorative.blockID - 256);
		Item.itemsList[hedgeSpruceSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(hedgeSpruceSidingAndCorner.blockID - 256);
		Item.itemsList[hedgeSpruceMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(hedgeSpruceMouldingAndDecorative.blockID - 256);
		Item.itemsList[hedgeBirchSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(hedgeBirchSidingAndCorner.blockID - 256);
		Item.itemsList[hedgeBirchMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(hedgeBirchMouldingAndDecorative.blockID - 256);
		Item.itemsList[hedgeJungleSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(hedgeJungleSidingAndCorner.blockID - 256);
		Item.itemsList[hedgeJungleMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(hedgeJungleMouldingAndDecorative.blockID - 256);
		Item.itemsList[hedgeBloodSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(hedgeBloodSidingAndCorner.blockID - 256);
		Item.itemsList[hedgeBloodMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(hedgeBloodMouldingAndDecorative.blockID - 256);
		Item.itemsList[hedgeCherrySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(hedgeCherrySidingAndCorner.blockID - 256);
		Item.itemsList[hedgeCherryMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(hedgeCherryMouldingAndDecorative.blockID - 256);

		//Panes replace
		Block.thinGlass = Block.replaceBlock(Block.thinGlass.blockID, 
				DecoBlockPane.class, 
				decoManager,
				"glass", "thinglass_top", Material.glass, false);
		Block.fenceIron = Block.replaceBlock(Block.fenceIron.blockID, 
				DecoBlockIronBars.class,
				decoManager);
		FCBetterThanWolves.fcBlockGrate = (FCBlockGrate) Block.replaceBlock(FCBetterThanWolves.fcBlockGrate.blockID, 
				DecoBlockGrate.class, 
				decoManager);
		FCBetterThanWolves.fcBlockWickerPane = (FCBlockWickerPane) Block.replaceBlock(FCBetterThanWolves.fcBlockWickerPane.blockID, 
				DecoBlockWickerPane.class, 
				decoManager);
		FCBetterThanWolves.fcBlockSlats = (FCBlockSlats) Block.replaceBlock(FCBetterThanWolves.fcBlockSlats.blockID, 
				DecoBlockSlats.class, 
				decoManager);

		//Rope
		ropeCoil = new FCBlockDirectional(DecoDefsIDs.id_ropeCoil, FCBetterThanWolves.fcMaterialMiscellaneous, new String[] {"fcBlockRope_top"}, new String[] {"fcBlockRope_side"})
				.setHardness(2.0F)
				.SetAxesEffectiveOn(true)
				.setStepSound(Block.soundGrassFootstep)
				.setCreativeTab(CreativeTabs.tabBlock)
				.setUnlocalizedName("decoBlockRopeCoil");
		DecoManager.register(ropeCoil);

		//Chain
		chain = new DecoBlockChain(DecoDefsIDs.id_chain);
		DecoManager.register(chain);
		chainItem = new DecoItemChain(DecoDefsIDs.id_chainItem);
		chainCoil = new FCBlockDirectional(DecoDefsIDs.id_chainCoil, Material.iron, new String[] {"decoBlockChainCoil_top"}, new String[] {"decoBlockChainCoil_side"})
				.setHardness(2.0F)
				.SetPicksEffectiveOn(true)
				.setStepSound(Block.soundMetalFootstep)
				.setCreativeTab(CreativeTabs.tabBlock)
				.setUnlocalizedName("decoBlockChainCoil");
		Item.itemsList[chainCoil.blockID] = new DecoItemBlockWithCustomSound(chainCoil.blockID - 256);

		//Cocoa
		FCBetterThanWolves.fcItemCocoaBeans = new DecoItemCocoaBeans(FCBetterThanWolves.fcItemCocoaBeans.itemID - 256);
		Block.cocoaPlant = Block.replaceBlock(Block.cocoaPlant.blockID, 
				DecoBlockCocoa.class, 
				decoManager);

		//Lily pads
		Item.itemsList[Block.waterlily.blockID] = new DecoItemBlockLilyPad(Block.waterlily.blockID - 256);
		
		//Spider eyes
		Item.spiderEye = Item.replaceItem(Item.spiderEye.itemID, 
				DecoItemSpiderEye.class, 
				decoManager);
		spiderEyeBlock = new DecoBlockSpiderEye(DecoDefsIDs.id_spiderEyeBlock);
		DecoManager.register(spiderEyeBlock);
		spiderEyeSlab = new DecoBlockSpiderEyeSlab(DecoDefsIDs.id_spiderEyeSlab);
		DecoManager.register(spiderEyeSlab);
		
		//Ash
		materialAshBlock = new Material(MapColor.stoneColor).setRequiresTool().SetNetherMobsCanSpawnOn();
		ashBlock = new DecoBlockAsh(DecoDefsIDs.id_ashBlock);
		pumice = new Block(DecoDefsIDs.id_pumice, FCBetterThanWolves.fcMaterialNetherRock).setHardness(0.8F).setResistance(1.5F).setUnlocalizedName("decoBlockPumice").setCreativeTab(CreativeTabs.tabBlock);
		
		DecoManager.register(ashBlock);
		DecoManager.register(pumice);
		
		//Amethyst
		amethyst = new DecoBlockAmethyst(DecoDefsIDs.id_amethyst);
		amethystShardBlock = new DecoBlockAmethystShard(DecoDefsIDs.id_amethystShardBlock);
		
		DecoManager.register(amethyst);
		DecoManager.register(amethystShardBlock);
		
		amethystShard = new FCItemPlacesAsBlock(DecoDefsIDs.id_amethystShard, amethystShardBlock.blockID).setCreativeTab(CreativeTabs.tabMaterials).SetFilterableProperties(2).setUnlocalizedName("decoItemAmethystShard").setCreativeTab(CreativeTabs.tabMaterials);
		
		//Farmland and crops
		//FCBetterThanWolves.fcItemNitre = Item.replaceItem(FCBetterThanWolves.fcItemNitre.itemID, DecoItemNitre.class);
		
		//farmlandSalted = new DecoBlockFarmlandSalted(DecoDefsIDs.id_farmlandSalted);
		//DecoManager.Register(farmlandSalted);

		//Fluids
		Block.waterStill = (BlockFluid) Block.replaceBlock(Block.waterStill.blockID, 
				DecoBlockWaterStationary.class, 
				decoManager, 
				Material.water)
				.setLightOpacity(3);
		Block.waterMoving = (BlockFluid) Block.replaceBlock(Block.waterMoving.blockID, 
				DecoBlockWaterFlowing.class, 
				decoManager, 
				Material.water)
				.setLightOpacity(3);
		Block.lavaStill = (BlockFluid) Block.replaceBlock(Block.lavaStill.blockID, 
				DecoBlockLavaStationary.class,
				decoManager, 
				Material.lava);
		Block.lavaMoving = (BlockFluid) Block.replaceBlock(Block.lavaMoving.blockID, 
				DecoBlockLavaFlowing.class, 
				decoManager, 
				Material.lava);
		
		//Enchanting table
		Block.enchantmentTable = Block.replaceBlock(Block.enchantmentTable.blockID, DecoBlockEnchantmentTable.class, decoManager);

		//Extra sounds
		Block.slowSand.setStepSound(stepSoundSoulSand);
		Item.itemsList[Block.slowSand.blockID] = new DecoItemBlockWithCustomSound(Block.slowSand.blockID - 256);
		Block.vine.setStepSound(stepSoundVine);
		FCBetterThanWolves.fcSoulforgedSteelBlock.setStepSound(stepSoundSteel);
		Item.itemsList[FCBetterThanWolves.fcSoulforgedSteelBlock.blockID] = new DecoItemBlockWithCustomSound(FCBetterThanWolves.fcSoulforgedSteelBlock.blockID - 256);
		FCBetterThanWolves.fcAnvil.setStepSound(stepSoundSteel);
		Item.itemsList[FCBetterThanWolves.fcAnvil.blockID] = new DecoItemBlockWithCustomSound(FCBetterThanWolves.fcAnvil.blockID - 256);
		FCBetterThanWolves.fcBlockBloodMoss.setStepSound(stepSoundGroth);
		FCBetterThanWolves.fcItemBloodMossSpores = new DecoItemBloodMossSpores(FCBetterThanWolves.fcItemBloodMossSpores.itemID - 256);
		chainCoil.setStepSound(stepSoundChainDeep);

		//Scaffolding
		//scaffolding = new AddonBlockScaffolding(DecoDefsIDs.id_scaffolding);
		//AddonManager.Register(scaffolding, "Scaffolding");
	}

	private void addToolDefs() {
		FCBetterThanWolves.fcItemChiselIron = Item.replaceItem(FCBetterThanWolves.fcItemChiselIron.itemID, 
				DecoItemChiselIron.class, 
				decoManager);
		FCBetterThanWolves.fcItemChiselDiamond = Item.replaceItem(FCBetterThanWolves.fcItemChiselDiamond.itemID, 
				DecoItemChiselDiamond.class, 
				decoManager);
		
		FCBetterThanWolves.fcBlockDispenser = (FCBlockBlockDispenser) Block.replaceBlock(FCBetterThanWolves.fcBlockDispenser.blockID, 
				DecoBlockBlockDispenser.class, 
				decoManager);
		
		FCBetterThanWolves.fcItemNameTag.setCreativeTab(CreativeTabs.tabTools);
	}

	private void addSubBlockReplaceDefs() {
		//Walls
		FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner = Block.replaceBlock(FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner.blockID, 
				DecoBlockSidingAndCornerDecorativeWall.class, 
				decoManager, 
				Material.rock, "fcBlockDecorativeStoneBrick", 1.5F, 10.0F, 
				Block.soundStoneFootstep, "fcStoneBrickSiding", "Stone Brick");
		
		FCBetterThanWolves.fcBlockBrickSidingAndCorner = Block.replaceBlock(FCBetterThanWolves.fcBlockBrickSidingAndCorner.blockID, 
				DecoBlockSidingAndCornerDecorativeWall.class, 
				decoManager, 
				Material.rock, "fcBlockDecorativeBrick", 2.0F, 10.0F, 
				Block.soundStoneFootstep, "fcBrickSiding","Brick");
		
		FCBetterThanWolves.fcBlockSandstoneSidingAndCorner = Block.replaceBlock(FCBetterThanWolves.fcBlockSandstoneSidingAndCorner.blockID, 
				DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom.class, 
				decoManager, 
				Material.rock,
				new String[] {
						"fcBlockDecorativeSandstone_bottom", 
						"fcBlockDecorativeSandstone_top", 
						"fcBlockDecorativeSandstone_side"},
				0.8F, 1.34F, Block.soundStoneFootstep, "fcSandstoneSiding", "Sandstone");
		
		FCBetterThanWolves.fcBlockWhiteStoneSidingAndCorner = Block.replaceBlock(FCBetterThanWolves.fcBlockWhiteStoneSidingAndCorner.blockID, 
				DecoBlockSidingAndCornerDecorativeWall.class, 
				decoManager, 
				Material.rock, "fcBlockDecorativeWhiteStone", 1.5F, 10.0F, 
				Block.soundStoneFootstep, "fcWhiteStoneSiding", "White Stone");
		
		FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner = Block.replaceBlock(FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner.blockID, 
				DecoBlockSidingAndCornerDecorativeWall.class, 
				decoManager, 
				Material.rock, "fcBlockDecorativeStone", 1.5F, 10.0F, 
				Block.soundStoneFootstep, "fcStoneSiding", "Stone");
		
		Block.netherFence = Block.replaceBlock(Block.netherFence.blockID, 
				DecoBlockFence.class, 
				decoManager,
				"netherBrick", FCBetterThanWolves.fcMaterialNetherRock);
		
		Block.cobblestoneWall = Block.replaceBlock(Block.cobblestoneWall.blockID, 
				DecoBlockWall.class, 
				decoManager,
				Block.cobblestone);
	}

	private void addEntityDefs() {
		//Custom entities
		EntityList.AddMapping(DecoEntityFallingConcrete.class, "FallingConcrete", DecoDefsIDs.id_entityFallingConcrete);
		//EntityList.addMapping(DecoEntityVillagerMason.class, "DecoVillagerMason", DecoDefsIDs.id_entityVillagerMason, 12422002, 4802889);
		
		//FCEntityVillager.professionMap.put(DecoDefsIDs.id_masonProfession, DecoEntityVillagerMason.class);
		
		//Replaced mappings
		EntityList.replaceExistingMappingSafe(DecoEntitySquid.class, "Squid");
		EntityList.replaceExistingMappingSafe(DecoEntityOcelot.class, "Ozelot");

		Item.itemFrame = Item.replaceItem(Item.itemFrame.itemID, 
				DecoItemFrame.class,
				decoManager)
				.setUnlocalizedName("frame");
		EntityList.replaceExistingMappingSafe(DecoEntityItemFrame.class, "ItemFrame");

		Item.painting = Item.replaceItem(Item.painting.itemID, 
				DecoItemPainting.class,
				decoManager)
				.setUnlocalizedName("painting");
		EntityList.replaceExistingMappingSafe(DecoEntityPainting.class, "Painting");

		FCBetterThanWolves.fcItemCanvas = Item.replaceItem(FCBetterThanWolves.fcItemCanvas.itemID, 
				DecoItemCanvas.class, 
				decoManager);
		EntityList.replaceExistingMappingSafe(DecoEntityCanvas.class, "fcCanvas");
	}
}