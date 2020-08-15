package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class AddonDefs {
	public static final AddonDefs instance = new AddonDefs();

	private static final int
		id_paperWall=3000,
		id_fenceSteel=3001,
		id_flower=3002,
		id_glassStained=3003,
		id_glassPaneStained=3004,
		id_coalBlock=3005,
		id_tulip=3006,
		id_blockDiamondium=3007,
		id_whiteStoneBrick=3008,
		id_whiteBrickSidingAndCorner=3009,
		id_whiteBrickMouldingAndDecorative=3010,
		id_whiteBrickStairs=3011,
		id_polishedStone=3012,
		id_polishedStoneStairs=3013,
		id_polishedStoneSidingAndCorner=3014,
		id_polishedStoneMouldingAndDecorative=3015,
		id_bonePillar=3016,
		id_flower2=3017,
		id_concrete=3018,
		id_concretePowder=3019,
		id_netherCoalBlock=3020,
		id_cherrySapling=3021,
		id_cherryLeaves=3022,
		id_thatch=3023,
		id_thatchStairs=3024,
		id_hayBale=3025,
		id_hayBaleStairs=3026,
		id_lanternPaper=3027,
		id_chandelier=3028,
		id_lanternSteel=3029,
		id_chain=3030,
		id_logSpikeSpruce=3031,
		id_logSpikeBirch=3032,
		id_logSpikeJungle=3033,
		id_logSpikeCherry=3034,
		id_cherryWoodChair=3035,
		id_oakWoodChair=3036,
		id_spruceWoodChair=3037,
		id_birchWoodChair=3038,
		id_jungleWoodChair=3039,
		id_bloodWoodChair=3040,
		id_paintedPlanksSlab=3041,
		id_paintedPlanksSlab2=3042,
		id_woodSlab=3043,
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
		id_stoneSlab=3110,
		id_cobblestoneSidingAndCorner=3111,
		id_cobblestoneMouldingAndDecorative=3112,
		id_mossyCobblestoneSidingAndCorner=3113,
		id_mossyCobblestoneMouldingAndDecorative=3114,
		id_mossyCobblestoneStairs=3115,
		id_stoneSlab2=3116,
		id_stoneSlab3=3117,
		id_netherBrick=3118,
		id_netherBrickLoose=3119,
		id_infusedStone=3120,
		id_basalt=3121,
		id_netherBrickStairs=3122,
		id_netherBrickSidingAndCorner=3123,
		id_netherBrickMouldingAndDecorative=3124,
		id_infusedStoneStairs=3125,
		id_infusedStoneSidingAndCorner=3126,
		id_infusedStoneMouldingAndDecorative=3127,
		id_infusedStoneSmoothStairs=3128,
		id_infusedStoneSmoothSidingAndCorner=3129,
		id_infusedStoneSmoothMouldingAndDecorative=3130,
		id_infusedStoneBrickStairs=3131,
		id_infusedStoneBrickSidingAndCorner=3132,
		id_infusedStoneBrickMouldingAndDecorative=3133,
		id_glazedTerracottaStart=3134,
		//end 3149
		id_stoneTypeSubStart=3150,
		//end 3158
		id_stoneTypeSmoothSubStart=3159,
		//end 3167
		id_stoneTypeCobbleSubStart=3168,
		//end 3176
		id_stoneTypeBrickSubStart=3177,
		//end 3185
		id_graniteCobbleLooseStairs=3186,
		id_andesiteCobbleLooseStairs=3187,
		id_dioriteCobbleLooseStairs=3188,
		id_graniteStoneBrickLooseStairs=3189,
		id_andesiteStoneBrickLooseStairs=3190,
		id_dioriteStoneBrickLooseStairs=3191,
		id_stoneTypeLooseSlab=3192,
		id_netherBrickLooseStairs=3193,
		id_netherBrickLooseSlab=3194,
		id_netherrackSuperheated=3195,
		id_magma=3196,
		id_netherBrickSuperheated=3197,
		id_stoneSlab4=3198,
		id_stoneSlab5=3199,
		id_strippedLog=3200,
		id_barkLog=3201,
		id_barkLogStripped=3202,
		id_bloodLog=3203,
		id_cherryLog=3204,
		id_cherryStump=3205,
		id_logDamagedSpruce=3206,
		id_logDamagedBirch=3207,
		id_logDamagedJungle=3208,
		id_logDamagedCherry=3209,
		id_workbench=3210,
		id_barrelEmpty2=3211,
		id_barrelFullBlood=3212,
		id_barrelFullCherry=3213,
		id_cherryStairs=3214,
		id_cherrySidingAndCorner=3215,
		id_cherryMouldingAndDecorative=3216,
		id_gateCherry=3217,
		id_doorCherry=3218,
		id_trapdoorCherry=3219,
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
		id_barrelEmpty=3233,
		id_barrelFullOak=3234,
		id_barrelFullSpruce=3235,
		id_barrelFullBirch=3236,
		id_barrelFullJungle=3237,
		id_crate=3238,
		id_flowerPot=3239,
		id_pergola=3240,
		id_barrelFilling=3241,
		id_scaffolding=3242,
		id_logDamagedBlood=3243,
		id_logSpikeBlood=3244,
		id_ropeCoil=3245,
		id_chainCoil=3246,
	
		id_paintedPlanksSubStart=3248,
		//end 3296
		id_terracottaSlab=3297,
		id_terracottaSlab2=3298,
	
		id_coarseDirt=3300,
		id_coarseDirtSlab=3301,
		id_podzol=3302,
		id_podzolSlab=3303,
		id_hedge=3304,
		id_pumpkin=3305,
		id_pumpkinLit=3306,
	
		id_redSand=3310,
		id_redSandSlab=3311,
		id_redSandstone=3312,
		id_redSandstoneSlab=3313,
		id_redSandstoneStairs=3314,
		id_redSandstoneSidingAndCorner=3315,
		id_redSandstoneMouldingAndDecorative=3316,
		id_redSandstoneSmoothSidingAndCorner=3317,
		id_redSandstoneSmoothMouldingAndDecorative=3318,
		id_sandstoneSmoothSidingAndCorner=3319,
		id_sandstoneSmoothMouldingAndDecorative=3320,
		id_prismarine=3321,
		id_prismarineLantern=3322,
		id_prismarineSidingAndCorner=3323,
		id_prismarineMouldingAndDecorative=3324,
		id_prismarineStairs=3325,
		id_prismarineBrickSidingAndCorner=3326,
		id_prismarineBrickMouldingAndDecorative=3327,
		id_prismarineBrickStairs=3328,
		id_prismarineDarkSidingAndCorner=3329,
		id_prismarineDarkMouldingAndDecorative=3330,
		id_prismarineDarkStairs=3331,
		id_carpet=3332,
		id_polishedSandstoneStairs=3333,
		id_polishedSandstoneSidingAndCorner=3334,
		id_polishedSandstoneMouldingAndDecorative=3335,
		id_polishedRedSandstoneStairs=3336,
		id_polishedRedSandstoneSidingAndCorner=3337,
		id_polishedRedSandstoneMouldingAndDecorative=3338,
		id_sandstoneBrickStairs=3339,
		id_sandstoneBrickSidingAndCorner=3340,
		id_sandstoneBrickMouldingAndDecorative=3341,
		id_redSandstoneBrickStairs=3342,
		id_redSandstoneBrickSidingAndCorner=3343,
		id_redSandstoneBrickMouldingAndDecorative=3344,
		id_sandstoneSmoothStairs=3345,
		id_redSandstoneSmoothStairs=3346,
		id_sandstoneMossyStairs=3347,
		id_sandstoneMossySidingAndCorner=3348,
		id_sandstoneMossyMouldingAndDecorative=3349,
		id_sandstoneBrickLargeStairs=3350,
		id_sandstoneBrickLargeSidingAndCorner=3351,
		id_sandstoneBrickLargeMouldingAndDecorative=3352,
		id_sandstoneBrickLargeMossyStairs=3353,
		id_sandstoneBrickLargeMossySidingAndCorner=3354,
		id_sandstoneBrickLargeMossyMouldingAndDecorative=3355,
		id_redSandstoneMossyStairs=3356,
		id_redSandstoneMossySidingAndCorner=3357,
		id_redSandstoneMossyMouldingAndDecorative=3358,
		id_redSandstoneBrickLargeStairs=3359,
		id_redSandstoneBrickLargeSidingAndCorner=3360,
		id_redSandstoneBrickLargeMouldingAndDecorative=3361,
		id_redSandstoneBrickLargeMossyStairs=3362,
		id_redSandstoneBrickLargeMossySidingAndCorner=3363,
		id_redSandstoneBrickLargeMossyMouldingAndDecorative=3364,
		id_sandstoneCrackedStairs=3365,
		id_sandstoneCrackedSidingAndCorner=3366,
		id_sandstoneCrackedMouldingAndDecorative=3367,
		id_sandstoneBrickLargeCrackedStairs=3368,
		id_sandstoneBrickLargeCrackedSidingAndCorner=3369,
		id_sandstoneBrickLargeCrackedMouldingAndDecorative=3370,
		id_redSandstoneCrackedStairs=3371,
		id_redSandstoneCrackedSidingAndCorner=3372,
		id_redSandstoneCrackedMouldingAndDecorative=3373,
		id_redSandstoneBrickLargeCrackedStairs=3374,
		id_redSandstoneBrickLargeCrackedSidingAndCorner=3375,
		id_redSandstoneBrickLargeCrackedMouldingAndDecorative=3376,
		id_stoneSlab6 = 3377,
		id_stoneBrickMossyStairs=3378,
		id_stoneBrickMossySidingAndCorner=3379,
		id_stoneBrickMossyMouldingAndDecorative=3380,
		id_stoneBrickCrackedStairs=3381,
		id_stoneBrickCrackedSidingAndCorner=3382,
		id_stoneBrickCrackedMouldingAndDecorative=3383,
		id_concreteSubStart=3384,
		//end 3431
		id_concreteSlab=3432,
		id_concreteSlab2=3433,
		id_endStoneBrick=3434,
		id_endStoneBrickStairs=3435,
		id_endStoneBrickSidingAndCorner=3436,
		id_endStoneBrickMouldingAndDecorative=3437,
		id_signSpruce=3438,
		id_signSpruceWall=3439,
		id_signBirch=3440,
		id_signBirchWall=3441,
		id_signJungle=3442,
		id_signJungleWall=3443,
		id_signBlood=3445,
		id_signBloodWall=3446,
		id_signCherry=3447,
		id_signCherryWall=3448,
		id_hedgeOakStairs=3449,
		id_hedgeOakSidingandCorner=3450,
		id_hedgeOakMoulingAndDecorative=3451,
		id_hedgeSpruceStairs=3452,
		id_hedgeSpruceSidingAndCorner=3453,
		id_hedgeSpruceMouldingAndDecorative=3454,
		id_hedgeBirchStairs=3455,
		id_hedgeBirchSidingAndCorner=3456,
		id_hedgeBirchMouldingAndDecorative=3457,
		id_hedgeJungleStairs=3458,
		id_hedgeJungleSidingAndCorner=3459,
		id_hedgeJungleMouldingAndDecorative=3460,
		id_hedgeBloodStairs=3461,
		id_hedgeBloodSidingAndCorner=3462,
		id_hedgeBloodMouldingAndDecorative=3463,
		id_hedgeCherryStairs=3464,
		id_hedgeCherrySidingAndCorner=3465,
		id_hedgeCherryMouldingAndDecorative=3466,
		id_buttonSpruce=3467,
		id_buttonBirch=3468,
		id_buttonJungle=3469,
		id_buttonBlood=3470,
		id_buttonCherry=3471,
		id_buttonInfusedStone=3472,
		id_buttonGranite=3473,
		id_buttonAndesite=3474,
		id_buttonDiorite=3475,
		id_buttonSandstone=3476,
		id_buttonRedSandstone=3477,
		id_nylium=3478,
		id_stemCrimson=3479,
		id_stemWarped=3480,
		id_wartBlock=3481,
		id_netherRoots=3482,
		id_netherVines=3483,
		id_stemDamagedCrimson=3484,
		id_stemDamagedWarped=3485,
		id_stemSpikeCrimson=3486,
		id_stemSpikeWarped=3487,
	
		id_layerDirt=3550,
		id_layerGrass=3551,
		id_layerGravel=3552,
		id_layerSand=3553,
		id_layerRedSand=3554,
		id_layerCoarseDirt=3555,
		id_layerPodzol=3556,
		id_layerPackedEarth=3557,
		id_layerDirtLoose=3558,
	
		maxID = 4095;

	private static final int
		id_glassChunk = 30002,
		id_fertilizer = 30003,
		id_chainItem = 30004,
		id_nameTag=30005,
		id_shearsDiamond=30006,
		id_bottleHempOil = 30007,
		id_glassStainedItem = 30008,
	
		id_itemDoorSpruce = 30020,
		id_itemDoorBirch = 30021,
		id_itemDoorJungle = 30022,
		id_itemDoorBlood = 30023,
		id_itemDoorCherry = 30024,
	
		id_chiselDiamond = 30050,
		id_woodBleach=30051,
		id_pileRedSand=30052,
		id_woodStain=30053,
	
		id_prismarineShard=30060,
		id_prismarineCrystal=30061;

	private static final int
		id_entityFallingConcrete=3000;

	public static Material materialHedge;
	public static Material materialHay;
	public static Material materialWart;
	public static Material materialCarpet;

	public static final StepSound stepSoundLantern = new AddonStepSound("lantern", 1, 1, "stone", Block.soundMetalFootstep.getVolume(), Block.soundMetalFootstep.getPitch());
	public static final StepSound stepSoundChain = new AddonStepSound("chain", 1, 1, "stone", Block.soundMetalFootstep.getVolume(), Block.soundMetalFootstep.getPitch());
	public static final StepSound stepSoundChainDeep = new AddonStepSound("chain", 1, 0.33F, "stone", Block.soundMetalFootstep.getVolume(), Block.soundMetalFootstep.getPitch());
	public static final StepSound stepSoundNetherrack = new AddonStepSound("netherrack", 1, 1, "stone", Block.soundStoneFootstep.getVolume(), Block.soundStoneFootstep.getPitch());
	public static final StepSound stepSoundNetherBrick = new AddonStepSound("netherbrick", 1, 1, "stone", Block.soundStoneFootstep.getVolume(), Block.soundStoneFootstep.getPitch());
	public static final StepSound stepSoundBone = new AddonStepSound("bone", 1, 1, "stone", Block.soundStoneFootstep.getVolume(), Block.soundStoneFootstep.getPitch());
	public static final StepSound stepSoundSoulSand = new AddonStepSound("soulsand", 1, 1, "stone", Block.soundSandFootstep.getVolume(), Block.soundSandFootstep.getPitch());
	public static final StepSound stepSoundSteel = new AddonStepSound("soulsteel", 1.5F, 1, "stone", Block.soundMetalFootstep.getVolume(), Block.soundMetalFootstep.getPitch());
	public static final StepSound stepSoundVine = new AddonStepSoundVine(1, 1);
	public static final StepSound stepSoundBloodLog = new AddonStepSound("bloodLog", 1, 1, "wood", Block.soundWoodFootstep.getVolume(), Block.soundWoodFootstep.getPitch());
	public static final StepSound stepSoundGroth = new AddonStepSound("groth", 1, 1, "grass", Block.soundGrassFootstep.getVolume(), Block.soundGrassFootstep.getPitch());
	//public static final StepSound stepSoundNylium = new AddonStepSound("nylium", 1, 1, "dirt", Block.soundGravelFootstep.getVolume(), Block.soundGravelFootstep.getPitch());
	//public static final StepSound stepSoundWart = new AddonStepSound("netherWart", 1, 1, "grass", Block.soundGrassFootstep.getVolume(), Block.soundGrassFootstep.getPitch());

	//Clay
	public static Block terracotta, stainedTerracotta, unfiredTerracotta;
	public static Block terracottaSidingAndCorner, terracottaMouldingAndDecorative, terracottaStairs;
	public static Block[] stainedTerracottaSidingAndCorner, stainedTerracottaMouldingAndDecorative,stainedTerracottaStairs;
	public static Block[] glazedTerracotta;
	public static Block terracottaSlab, terracottaSlab2;

	//Chairs
	public static Block birchWoodChair, spruceWoodChair, jungleWoodChair, oakWoodChair, bloodWoodChair, cherryWoodChair;

	//Glass
	public static Block glassStained, glassPaneStained;
	public static Item glassChunk;
	public static AddonItemGlassStained stainedGlassItem;

	//White Stone
	public static Block whiteStoneBrick, whiteBrickMouldingAndDecorative, whiteBrickSidingAndCorner, whiteBrickStairs;

	//Flowers
	public static Block flower, flower2, tulip;
	public static Block flowerPot;
	public static Item fertilizer;
	public static Block cherrySapling, cherryLeaves;

	//Stone
	public static Block stoneTypes;
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

	public static AddonBlockSlabStone stoneSlab, stoneSlab2, stoneSlab3, stoneSlab4, stoneSlab5, stoneSlab6;

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
	public static Block netherBrickSidingAndCorner, netherBrickMouldingAndDecorative, netherBrickStairs;
	public static Block infusedStoneSidingAndCorner, infusedStoneMouldingAndDecorative, infusedStoneStairs;
	public static Block infusedStoneSmoothSidingAndCorner, infusedStoneSmoothMouldingAndDecorative, infusedStoneSmoothStairs;
	public static Block infusedStoneBrickSidingAndCorner, infusedStoneBrickMouldingAndDecorative, infusedStoneBrickStairs;
	public static Block netherBrickLoose, netherBrickLooseStairs, netherBrickLooseSlab;

	public static Block netherrackSuperheated, magma, netherBrickSuperheated;

	public static Block endStoneBrick, endStoneBrickStairs, endStoneBrickSidingAndCorner, endStoneBrickMouldingAndDecorative;

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
	public static AddonBlockSlabStone concreteSlab, concreteSlab2;

	//Wood
	public static Block strippedLog, barkLog, barkLogStripped, bloodLog, cherryLog, cherryStump;
	public static Block logDamagedSpruce, logDamagedBirch, logDamagedJungle, logDamagedBlood, logDamagedCherry;
	public static Block logSpikeSpruce, logSpikeBirch, logSpikeJungle, logSpikeBlood, logSpikeCherry;
	public static BlockTrapDoor trapdoorSpruce, trapdoorBirch, trapdoorJungle, trapdoorBlood, trapdoorCherry;
	public static BlockDoor doorSpruce, doorBirch, doorJungle, doorBlood, doorCherry;
	public static FCItemDoor itemDoorSpruce, itemDoorBirch, itemDoorJungle, itemDoorBlood, itemDoorCherry;
	public static BlockFenceGate gateSpruce, gateBirch, gateJungle, gateBlood, gateCherry;
	public static Block cherryStairs, cherrySidingAndCorner, cherryMouldingAndDecorative;
	public static Block planksPainted;
	public static Block[] paintedPlanksSidingAndCorner, paintedPlanksMouldingAndDecorative, paintedPlanksStairs;
	public static Block pergola;
	public static Block barrelEmpty, barrelEmpty2, barrelFilling, barrelFullOak, barrelFullSpruce, barrelFullBirch, barrelFullJungle, barrelFullBlood, barrelFullCherry;
	public static Block crate;
	public static Block signSpruce, signSpruceWall, signBirch, signBirchWall, signJungle, signJungleWall, signBlood, signBloodWall, signCherry, signCherryWall;
	public static Block scaffolding;
	public static Item woodBleach, woodStain;

	public static AddonBlockWoodSlab paintedPlanksSlab, paintedPlanksSlab2, woodSlab;

	//Deco
	public static Block blockDiamondium;
	public static Block hayBale, hayBaleStairs, thatch, thatchStairs;
	public static BlockPane paperWall, fenceSteel;
	public static Block lanternPaper, lanternSteel, chandelier;
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

	//Ground Cover
	public static Block layerDirt, layerGrass, layerGravel, layerSand, layerRedSand, layerCoarseDirt, layerPodzol, layerPackedEarth, layerDirtLoose;

	//Tools
	public static AddonItemChiselDiamond chiselDiamond;
	public static AddonItemShearsDiamond shearsDiamond;
	public static AddonItemNameTag nameTag;

	//Extra SubBlocks
	public static Block stoneBrickEdging;

	private AddonDefs() {}

	public void addDefinitions() {
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
		addlayerDefs();
		addSubBlockReplaceDefs();
		addExtraSubBlockDefs();
		addEntityDefs();
		Item.m_bSuppressConflictWarnings=false;
	}

	private void addMaterialDefs() {
		materialHedge = (new Material(MapColor.foliageColor)).setBurning().setTranslucent().setNoPushMobility().SetAxesEfficientOn().SetAxesTreatAsVegetation().SetMobsCantSpawnOn();
		materialHay = (new Material(MapColor.clothColor)).setBurning().SetAxesEfficientOn().SetAxesTreatAsVegetation().SetMobsCantSpawnOn();
		materialWart = (new Material(MapColor.foliageColor)).setBurning().SetAxesEfficientOn().SetAxesTreatAsVegetation().SetMobsCantSpawnOn();
	    materialCarpet = (new Material(MapColor.clothColor)).setBurning().SetAxesEfficientOn().setRequiresTool();

		Material.glass.SetMobsCantSpawnOn();
		//FCBetterThanWolves.fcMaterialWicker.SetMobsCantSpawnOn();
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
		terracottaSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id++, Material.rock, "ginger_clay", 2.0F, 5.0F, Block.soundWoodFootstep, "claySiding", "Terracotta");
		terracottaMouldingAndDecorative = new FCBlockMouldingAndDecorative(id++, Material.rock, "ginger_clay", "ginger_clay_column", 3042, 2.0F, 5.0F, Block.soundWoodFootstep, "clayMoulding");
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
			stainedTerracottaSidingAndCorner[i] = new AddonBlockSidingAndCornerDecorativeWall(id++, Material.rock, "ginger_clay_"+i, 2.0F, 5.0F, Block.soundStoneFootstep, "stainedClaySiding_"+i, "Stained Terracotta");
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

		//Slabs
		terracottaSlab = new AddonBlockSlabStone(id_terracottaSlab, new Block[] {AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta}, new int[] {0, 1, 2, 3, 4, 5, 6, 7});
		Item.itemsList[AddonDefs.terracottaSlab.blockID] = new AddonItemBlockSlab(AddonDefs.terracottaSlab.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab, 1, 0), "Black Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab, 1, 1), "Red Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab, 1, 2), "Green Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab, 1, 3), "Brown Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab, 1, 4), "Blue Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab, 1, 5), "Purple Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab, 1, 6), "Cyan Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab, 1, 7), "Light Grey Terracotta Slab");

		terracottaSlab2 = new AddonBlockSlabStone(id_terracottaSlab2, new Block[] {AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta, AddonDefs.stainedTerracotta}, new int[] {8, 9, 10, 11, 12, 13, 14, 15});
		Item.itemsList[AddonDefs.terracottaSlab2.blockID] = new AddonItemBlockSlab(AddonDefs.terracottaSlab2.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab2, 1, 0), "Gray Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab2, 1, 1), "Pink Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab2, 1, 2), "Lime Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab2, 1, 3), "Yellow Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab2, 1, 4), "Light Blue Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab2, 1, 5), "Magenta Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab2, 1, 6), "Orange Terracotta Slab");
		AddonManager.Name(new ItemStack(AddonDefs.terracottaSlab2, 1, 7), "White Terracotta Slab");
	}

	private void addGlassDefs() {
		glassChunk = new Item(id_glassChunk).setUnlocalizedName("ginger_glassball").setCreativeTab(CreativeTabs.tabMaterials).SetFilterableProperties(2);
		glassStained = new AddonBlockGlassStained(id_glassStained);
		stainedGlassItem = new AddonItemGlassStained(id_glassStainedItem);
		AddonManager.Name(glassChunk, "Piece of Glass");
		AddonManager.Name(stainedGlassItem, "Stained Glass");
	}

	private void addWhiteStoneDefs() {
		whiteStoneBrick = new AddonBlockWhiteStoneBrick(id_whiteStoneBrick);
		whiteBrickSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_whiteBrickSidingAndCorner, Material.rock, "ginger_bricks_white_0_decorative", 2.0F, 5.0F, Block.soundStoneFootstep, "whiteBrickSiding", "White Brick");
		whiteBrickMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_whiteBrickMouldingAndDecorative, Material.rock, "ginger_bricks_white_0_decorative", "ginger_bricks_white_0_column", 3042, 2.0F, 5.0F, Block.soundStoneFootstep, "whiteBrickMolding");
		whiteBrickStairs = new FCBlockStairs(id_whiteBrickStairs, whiteStoneBrick, 0).setUnlocalizedName("stairsWhiteBrick");

		Item.itemsList[whiteBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(whiteBrickSidingAndCorner.blockID - 256);
		Item.itemsList[whiteBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(whiteBrickMouldingAndDecorative.blockID - 256);
		AddonManager.Register(whiteBrickStairs, "White Stone Brick Stairs");

		AddonManager.NameSubBlocks_Wall(whiteBrickSidingAndCorner, whiteBrickMouldingAndDecorative, "White Stone Brick");
	}

	private void addFlowerDefs() {
		String[]
				flowers = { "yucca", "hyacinth", "birdsParadise", "azalea", "cornFlower", "lavender", "honeysuckle","allium", "orchidBlue", "poppy", "azureBluet", "daisy", "peony","lilac","rosebush", "roseBlue"},
				flowers2 = {"blackRose"},
				tulips = { "red","pink", "orange", "white", "blue"},
				flowerNames = { "Yucca", "Hyacinth", "Birds of Paradise", "Azaleas", "Cornflower", "Lavender", "Honeysuckle", "Allium","Blue Orchid", "Poppy", "Azure Bluet", "Daisy", "Peony", "Lilac", "Rose Bush", "Blue Rose"},
				flowerNames2 = {"Black Rose"},
				tulipNames = { "Red", "Pink", "Orange", "White", "Blue"};

		Item.dyePowder = new AddonItemDye(95);
		//Item.m_bSuppressConflictWarnings=false;

		List recipes = CraftingManager.getInstance().getRecipeList();
		ArrayList<RecipeFireworks> fireworks = new ArrayList<RecipeFireworks>();
		for(Object o: recipes)
			if(o instanceof RecipeFireworks)
				fireworks.add((RecipeFireworks)o);
		for(RecipeFireworks rf: fireworks)
			recipes.remove(rf);
		recipes.add(new AddonRecipeFireworksColor());

		//FCBetterThanWolves.fcPlanter = new AddonBlockPlanter(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcPlanter));
		flower = new AddonBlockFlowers(id_flower, "flower",flowers,flowerNames);
		flower2 = new AddonBlockFlowers(id_flower2, "flower2", flowers2, flowerNames2);
		tulip = new AddonBlockFlowers(id_tulip, "tulip", tulips, tulipNames, " Tulip");

		fertilizer = new AddonItemFertilizer(id_fertilizer);

		AddonManager.Name(Block.plantRed, "Red Rose");

		//Flower pot
		flowerPot = new AddonBlockFlowerPot(id_flowerPot);
		TileEntity.addMapping(AddonTileEntityFlowerPot.class, "AddonFlowerPot");
		//AddonManager.AddCustomTileEntityRenderer(AddonTileEntityFlowerPot.class, new AddonTileEntityFlowerPotRenderer());
		Item flowerPotItem = new AddonItemFlowerPot(Item.flowerPot.itemID - 256);
		AddonManager.SetVanillaItemFinal("flowerPot", Item.flowerPot, flowerPotItem);
	}

	private void addStoneDefs() {
		AddonManager.Name(Block.stairsCobblestone, "Cobblestone Stairs");
		AddonManager.Name(Block.cobblestoneMossy, "Mossy Cobblestone");

		cobblestoneSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_cobblestoneSidingAndCorner, Material.rock, "ginger_cobblestoneDecorative", 1.5F, 10.0F, Block.soundStoneFootstep, "cobblestoneSiding", "Cobblestone").SetPicksEffectiveOn();
		cobblestoneMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_cobblestoneMouldingAndDecorative, Material.rock, "ginger_cobblestoneDecorative", "ginger_cobblestoneDecorative_column", 3042, 1.5F, 10.0F, Block.soundStoneFootstep, "cobblestoneMoulding").SetPicksEffectiveOn();
		mossyCobblestoneSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_mossyCobblestoneSidingAndCorner, Material.rock, "ginger_mossyCobblestoneDecorative", 1.5F, 10.0F, Block.soundStoneFootstep, "mossyCobblestoneSiding", "Mossy Cobblestone").SetPicksEffectiveOn();
		mossyCobblestoneMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_mossyCobblestoneMouldingAndDecorative, Material.rock, "ginger_mossyCobblestoneDecorative", "ginger_mossyCobblestoneDecorative_column", 3042, 1.5F, 10.0F, Block.soundStoneFootstep, "mossyCobblestoneMoulding").SetPicksEffectiveOn();
		mossyCobblestoneStairs = new FCBlockStairs(id_mossyCobblestoneStairs, Block.cobblestoneMossy, 0).setUnlocalizedName("stairsMossyCobblestone").SetPicksEffectiveOn();

		Item.itemsList[cobblestoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(cobblestoneSidingAndCorner.blockID - 256);
		Item.itemsList[cobblestoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(cobblestoneMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(cobblestoneSidingAndCorner, cobblestoneMouldingAndDecorative, "Cobblestone");
		Item.itemsList[mossyCobblestoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(mossyCobblestoneSidingAndCorner.blockID - 256);
		Item.itemsList[mossyCobblestoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(mossyCobblestoneMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(mossyCobblestoneSidingAndCorner, mossyCobblestoneMouldingAndDecorative, "Mossy Cobblestone");
		AddonManager.Register(mossyCobblestoneStairs, "Mossy Cobblestone Stairs");

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

		String[] stoneTextures = {"ginger_graniteDecorative", "ginger_andesiteDecorative", "ginger_dioriteDecorative", "ginger_graniteDecorative_column", "ginger_andesiteDecorative_column", "ginger_dioriteDecorative_column"};
		String[] stoneSmoothTextures = {"ginger_graniteSmoothDecorative", "ginger_andesiteSmoothDecorative", "ginger_dioriteSmoothDecorative", "ginger_graniteSmoothDecorative_column", "ginger_andesiteSmoothDecorative_column", "ginger_dioriteSmoothDecorative_column"};
		String[] stoneCobblestoneTextures = {"ginger_graniteCobbleDecorative", "ginger_andesiteCobbleDecorative", "ginger_dioriteCobbleDecorative", "ginger_graniteCobbleDecorative_column", "ginger_andesiteCobbleDecorative_column", "ginger_dioriteCobbleDecorative_column"};
		String[] stoneBrickTextures = {"ginger_graniteBrickDecorative", "ginger_andesiteBrickDecorative", "ginger_dioriteBrickDecorative", "ginger_graniteBrickDecorative_column", "ginger_andesiteBrickDecorative_column", "ginger_dioriteBrickDecorative_column"};

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
			stoneTypesSidingAndCorner[i] = new AddonBlockSidingAndCornerDecorativeWall(id_stoneTypeSubStart + i, Material.rock, stoneTextures[i], 1.5F, 10.0F, Block.soundStoneFootstep, "stoneTypesSiding_" + i, "Stone").SetPicksEffectiveOn();
			stoneTypesMouldingAndDecorative[i] = new FCBlockMouldingAndDecorative(id_stoneTypeSubStart + 3 + i, Material.rock, stoneTextures[i], stoneTextures[i + 3], 3042, 1.5F, 10.0F, Block.soundStoneFootstep, "stoneTypesMoulding_" + i);
			stoneTypesStairs[i] = new FCBlockStairs(id_stoneTypeSubStart + 6 + i, stoneTypes, i).setUnlocalizedName("stairsStoneTypes_" + i).SetPicksEffectiveOn();
			stoneTypesSmoothSidingAndCorner[i] = new AddonBlockSidingAndCornerDecorativeWall(id_stoneTypeSmoothSubStart + i, Material.rock, stoneSmoothTextures[i], 1.5F, 10.0F, Block.soundStoneFootstep, "stoneTypesSmoothSiding_" + i, "StoneSmooth").SetPicksEffectiveOn();
			stoneTypesSmoothMouldingAndDecorative[i] = new FCBlockMouldingAndDecorative(id_stoneTypeSmoothSubStart + 3 + i, Material.rock, stoneSmoothTextures[i], stoneSmoothTextures[i + 3], 3042, 1.5F, 10.0F, Block.soundStoneFootstep, "stoneTypesSmoothMoulding_" + i);
			stoneTypesSmoothStairs[i] = new FCBlockStairs(id_stoneTypeSmoothSubStart + 6 + i, stoneTypesSmooth, i).setUnlocalizedName("stairsStoneTypesSmooth_" + i).SetPicksEffectiveOn();
			stoneTypesCobblestoneSidingAndCorner[i] = new AddonBlockSidingAndCornerDecorativeWall(id_stoneTypeCobbleSubStart + i, Material.rock, stoneCobblestoneTextures[i], 1.5F, 10.0F, Block.soundStoneFootstep, "stoneTypesCobblestoneSiding_" + i, "StoneCobblestone").SetPicksEffectiveOn();
			stoneTypesCobblestoneMouldingAndDecorative[i] = new FCBlockMouldingAndDecorative(id_stoneTypeCobbleSubStart + 3 + i, Material.rock, stoneCobblestoneTextures[i], stoneCobblestoneTextures[i + 3], 3042, 1.5F, 10.0F, Block.soundStoneFootstep, "stoneTypesCobblestoneMoulding_" + i);
			stoneTypesCobblestoneStairs[i] = new AddonBlockStairsMortared(id_stoneTypeCobbleSubStart + 6 + i, stoneTypesCobble, i, id_graniteCobbleLooseStairs + i).setUnlocalizedName("stairsStoneTypesCobblestone_" + i).SetPicksEffectiveOn();
			stoneTypesStoneBrickSidingAndCorner[i] = new AddonBlockSidingAndCornerDecorativeWall(id_stoneTypeBrickSubStart + i, Material.rock, stoneBrickTextures[i], 1.5F, 10.0F, Block.soundStoneFootstep, "stoneTypesStoneBrickSiding_" + i, "StoneBrick").SetPicksEffectiveOn();
			stoneTypesStoneBrickMouldingAndDecorative[i] = new FCBlockMouldingAndDecorative(id_stoneTypeBrickSubStart + 3 + i, Material.rock, stoneBrickTextures[i], stoneBrickTextures[i + 3], 3042, 1.5F, 10.0F, Block.soundStoneFootstep, "stoneTypesStoneBrickMoulding_" + i);
			stoneTypesStoneBrickStairs[i] = new AddonBlockStairsMortared(id_stoneTypeBrickSubStart + 6 + i, stoneTypesStoneBrick, i, id_graniteCobbleLooseStairs + i + 3).setUnlocalizedName("stairsStoneTypesStoneBrick_" + i).SetPicksEffectiveOn();

			Item.itemsList[stoneTypesSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stoneTypesSidingAndCorner[i].blockID - 256);
			Item.itemsList[stoneTypesMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stoneTypesMouldingAndDecorative[i].blockID - 256);
			AddonManager.NameSubBlocks_Wall(stoneTypesSidingAndCorner[i], stoneTypesMouldingAndDecorative[i], names[i]);
			AddonManager.Register(stoneTypesStairs[i], names[i] + " Stairs");
			Item.itemsList[stoneTypesSmoothSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stoneTypesSmoothSidingAndCorner[i].blockID - 256);
			Item.itemsList[stoneTypesSmoothMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stoneTypesSmoothMouldingAndDecorative[i].blockID - 256);
			AddonManager.NameSubBlocks_Wall(stoneTypesSmoothSidingAndCorner[i], stoneTypesSmoothMouldingAndDecorative[i], "Polished " + names[i]);
			AddonManager.Register(stoneTypesSmoothStairs[i], "Polished " + names[i] + " Stairs");
			Item.itemsList[stoneTypesCobblestoneSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stoneTypesCobblestoneSidingAndCorner[i].blockID - 256);
			Item.itemsList[stoneTypesCobblestoneMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stoneTypesCobblestoneMouldingAndDecorative[i].blockID - 256);
			AddonManager.NameSubBlocks_Wall(stoneTypesCobblestoneSidingAndCorner[i], stoneTypesCobblestoneMouldingAndDecorative[i], names[i] + " Cobblestone");
			AddonManager.Register(stoneTypesCobblestoneStairs[i], names[i] + " Cobblestone Stairs");
			Item.itemsList[stoneTypesStoneBrickSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stoneTypesStoneBrickSidingAndCorner[i].blockID - 256);
			Item.itemsList[stoneTypesStoneBrickMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stoneTypesStoneBrickMouldingAndDecorative[i].blockID - 256);
			AddonManager.NameSubBlocks_Wall(stoneTypesStoneBrickSidingAndCorner[i], stoneTypesStoneBrickMouldingAndDecorative[i], names[i] + " Stone Bricks");
			AddonManager.Register(stoneTypesStoneBrickStairs[i], names[i] + " Stone Brick Stairs");
		}

		stoneTypesLooseStairs = new Block[6];
		stoneTypesLooseStairs[0] = new AddonBlockStoneLooseStairs(id_graniteCobbleLooseStairs, graniteCobbleLoose, stoneTypesCobblestoneStairs[0]).setUnlocalizedName("stoneTypesLooseStairs0");
		stoneTypesLooseStairs[1] = new AddonBlockStoneLooseStairs(id_andesiteCobbleLooseStairs, andesiteCobbleLoose, stoneTypesCobblestoneStairs[1]).setUnlocalizedName("stoneTypesLooseStairs1");
		stoneTypesLooseStairs[2] = new AddonBlockStoneLooseStairs(id_dioriteCobbleLooseStairs, dioriteCobbleLoose, stoneTypesCobblestoneStairs[2]).setUnlocalizedName("stoneTypesLooseStairs2");
		stoneTypesLooseStairs[3] = new AddonBlockStoneLooseStairs(id_graniteStoneBrickLooseStairs, graniteStoneBrickLoose, stoneTypesStoneBrickStairs[0]).setUnlocalizedName("stoneTypesLooseStairs3");
		stoneTypesLooseStairs[4] = new AddonBlockStoneLooseStairs(id_andesiteStoneBrickLooseStairs, andesiteStoneBrickLoose, stoneTypesStoneBrickStairs[1]).setUnlocalizedName("stoneTypesLooseStairs4");
		stoneTypesLooseStairs[5] = new AddonBlockStoneLooseStairs(id_dioriteStoneBrickLooseStairs, dioriteStoneBrickLoose, stoneTypesStoneBrickStairs[2]).setUnlocalizedName("stoneTypesLooseStairs5");

		AddonManager.Register(stoneTypesLooseStairs[0], "Loose Granite Cobblestone Stairs");
		AddonManager.Register(stoneTypesLooseStairs[1], "Loose Andesite Cobblestone Stairs");
		AddonManager.Register(stoneTypesLooseStairs[2], "Loose Diorite Cobblestone Stairs");
		AddonManager.Register(stoneTypesLooseStairs[3], "Loose Granite Stone Brick Stairs");
		AddonManager.Register(stoneTypesLooseStairs[4], "Loose Andesite Stone Brick Stairs");
		AddonManager.Register(stoneTypesLooseStairs[5], "Loose Diorite Stone Brick Stairs");

		polishedStone = new Block(id_polishedStone, Material.rock).setHardness(2.25F).setResistance(10.0F).SetPicksEffectiveOn().setUnlocalizedName("stoneslab_top").setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabBlock);
		polishedStoneStairs = new FCBlockStairs(id_polishedStoneStairs, polishedStone, 0).setHardness(2.25F).setResistance(10.0F).SetPicksEffectiveOn().setUnlocalizedName("polishedStoneStairs").setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabBlock);
		polishedStoneSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_polishedStoneSidingAndCorner, Material.rock, "stoneslab_top", 2.25F, 10.0F, Block.soundStoneFootstep, "polishedStoneSiding", "Polished Stone").SetPicksEffectiveOn();
		polishedStoneMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_polishedStoneMouldingAndDecorative, Material.rock, "stoneslab_top", "stoneslab_top", 3042, 2.25F, 10.0F, Block.soundStoneFootstep, "polishedStoneMoulding").SetPicksEffectiveOn();

		AddonManager.Register(polishedStone, "Polished Stone");
		AddonManager.Register(polishedStoneStairs, "Polished Stone Stairs");
		Item.itemsList[polishedStoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(polishedStoneSidingAndCorner.blockID - 256);
		Item.itemsList[polishedStoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(polishedStoneMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(polishedStoneSidingAndCorner, polishedStoneMouldingAndDecorative, "Polished Stone");

		stoneBrickMossyStairs = new FCBlockStairs(id_stoneBrickMossyStairs, Block.stoneBrick, 1).setUnlocalizedName("stoneBrickMossyStairs");
		stoneBrickMossySidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_stoneBrickMossySidingAndCorner, Material.rock, "ginger_stoneBrickMossyDecorative", 2.25F, 10.0F, Block.soundStoneFootstep, "stoneBrickMossySiding", "Mossy Stone Brick").SetPicksEffectiveOn();
		stoneBrickMossyMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_stoneBrickMossyMouldingAndDecorative, Material.rock, "ginger_stoneBrickMossyDecorative", "ginger_stoneBrickMossyDecorative_column", 3042, 2.25F, 10.0F, Block.soundStoneFootstep, "stoneBrickMossyMoulding").SetPicksEffectiveOn();
		stoneBrickCrackedStairs = new FCBlockStairs(id_stoneBrickCrackedStairs, Block.stoneBrick, 2).setUnlocalizedName("stoneBrickCrackedStairs");
		stoneBrickCrackedSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_stoneBrickCrackedSidingAndCorner, Material.rock, "ginger_stoneBrickCrackedDecorative", 2.25F, 10.0F, Block.soundStoneFootstep, "stoneBrickCrackedSiding", "Cracked Stone Brick").SetPicksEffectiveOn();
		stoneBrickCrackedMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_stoneBrickCrackedMouldingAndDecorative, Material.rock, "ginger_stoneBrickCrackedDecorative", "ginger_stoneBrickCrackedDecorative_column", 3042, 2.25F, 10.0F, Block.soundStoneFootstep, "stoneBrickCrackedMoulding").SetPicksEffectiveOn();

		AddonManager.Register(stoneBrickMossyStairs,  "Mossy Stone Brick Stairs");
		AddonManager.Register(stoneBrickCrackedStairs, "Cracked Stone Brick Stairs");
		Item.itemsList[stoneBrickMossySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(stoneBrickMossySidingAndCorner.blockID - 256);
		Item.itemsList[stoneBrickMossyMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(stoneBrickMossyMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(stoneBrickMossySidingAndCorner, stoneBrickMossyMouldingAndDecorative, " Mossy Stone Brick");
		Item.itemsList[stoneBrickCrackedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(stoneBrickCrackedSidingAndCorner.blockID - 256);
		Item.itemsList[stoneBrickCrackedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(stoneBrickCrackedMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(stoneBrickCrackedSidingAndCorner, stoneBrickCrackedMouldingAndDecorative, "Cracked Stone Brick");

		//Sandstone
		redSand = new AddonBlockRedSand(id_redSand);
		redSandSlab = new AddonBlockRedSandSlab(id_redSandSlab);
		redSandStone = new AddonBlockRedSandStone(id_redSandstone);
		redSandStoneStairs = new AddonBlockStairsRedSandStone(id_redSandstoneStairs, redSandStone, 0).setUnlocalizedName("redSandStoneStairs");
		redSandStoneSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_redSandstoneSidingAndCorner, new String[] {"ginger_redSandstoneDecorative_bottom", "ginger_redSandstoneDecorative_top", "ginger_redSandstoneDecorative_side", "ginger_redSandstoneDecorative_column"}, "redSandstoneSiding", "Red Sandstone");
		redSandStoneMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_redSandstoneMouldingAndDecorative, new String[] {"ginger_redSandstoneDecorative_bottom", "ginger_redSandstoneDecorative_top", "ginger_redSandstoneDecorative_side", "ginger_redSandstoneDecorative_column"}, redSandStoneSidingAndCorner.blockID, "redSandstoneMoulding");
		redSandStoneSmoothStairs = new AddonBlockStairsRedSandStone(id_redSandstoneSmoothStairs, redSandStone, 2).setUnlocalizedName("redSandStoneStairsSmooth");
		redSandStoneSmoothSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_redSandstoneSmoothSidingAndCorner, new String[] {"ginger_redSandstoneDecorative_top", "ginger_redSandstoneDecorative_top", "ginger_redSandstoneSmoothDecorative_side", "ginger_redSandstoneSmoothDecorative_column"}, "redSandstoneSmoothSiding", "Smooth Red Sandstone");
		redSandStoneSmoothMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_redSandstoneSmoothMouldingAndDecorative, new String[] {"ginger_redSandstoneDecorative_top", "ginger_redSandstoneDecorative_top", "ginger_redSandstoneSmoothDecorative_side", "ginger_redSandstoneSmoothDecorative_column"}, redSandStoneSmoothSidingAndCorner.blockID, "redSandstoneSmoothMoulding");
		redSandStonePolishedStairs = new AddonBlockStairsRedSandStone(id_polishedRedSandstoneStairs, redSandStone, 3).setUnlocalizedName("redSandStoneStairsPolished");
		redSandStonePolishedSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_polishedRedSandstoneSidingAndCorner, new String[] {"ginger_redSandstoneDecorative_top", "ginger_redSandstoneDecorative_top", "ginger_redSandstoneDecorative_top", "ginger_redSandstoneDecorative_top"}, "redSandstonePolishedSiding", "Polished Red Sandstone");
		redSandStonePolishedMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_polishedRedSandstoneMouldingAndDecorative, new String[] {"ginger_redSandstoneDecorative_top", "ginger_redSandstoneDecorative_top", "ginger_redSandstoneDecorative_top", "ginger_redSandstoneDecorative_top"}, redSandStonePolishedSidingAndCorner.blockID, "redSandstonePolishedMoulding");
		redSandStoneBrickStairs = new AddonBlockStairsRedSandStone(id_redSandstoneBrickStairs, redSandStone, 4).setUnlocalizedName("redSandStoneStairsBrick");
		redSandStoneBrickSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_redSandstoneBrickSidingAndCorner, new String[] {"ginger_redSandstoneBrickDecorative", "ginger_redSandstoneBrickDecorative", "ginger_redSandstoneBrickDecorative", "ginger_redSandstoneBrickDecorative_column"}, "redSandstoneBrickSiding", "Red Sandstone Brick");
		redSandStoneBrickMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_redSandstoneBrickMouldingAndDecorative, new String[] {"ginger_redSandstoneBrickDecorative", "ginger_redSandstoneBrickDecorative", "ginger_redSandstoneBrickDecorative", "ginger_redSandstoneBrickDecorative_column"}, redSandStoneBrickSidingAndCorner.blockID, "redSandstoneBrickMoulding");
		redSandStoneMossyStairs = new AddonBlockStairsRedSandStone(id_redSandstoneMossyStairs, redSandStone, 5).setUnlocalizedName("redSandStoneMossyStairs");
		redSandStoneMossySidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_redSandstoneMossySidingAndCorner, new String[] {"ginger_redSandstoneMossyDecorative_bottom", "ginger_redSandstoneMossyDecorative_top", "ginger_redSandstoneMossyDecorative_side", "ginger_redSandstoneMossyDecorative_column"}, "redSandstoneMossySiding", "Mossy Red Sandstone");
		redSandStoneMossyMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_redSandstoneMossyMouldingAndDecorative, new String[] {"ginger_redSandstoneMossyDecorative_bottom", "ginger_redSandstoneMossyDecorative_top", "ginger_redSandstoneMossyDecorative_side", "ginger_redSandstoneMossyDecorative_column"}, redSandStoneMossySidingAndCorner.blockID, "redSandstoneMossyMoulding");
		redSandStoneBrickLargeStairs = new AddonBlockStairsRedSandStone(id_redSandstoneBrickLargeStairs, redSandStone, 6).setUnlocalizedName("redSandStoneBrickLargeStairs");
		redSandStoneBrickLargeSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_redSandstoneBrickLargeSidingAndCorner, new String[] {"ginger_redSandstoneBrickLargeDecorative", "ginger_redSandstoneBrickLargeDecorative", "ginger_redSandstoneBrickLargeDecorative", "ginger_redSandstoneBrickLargeDecorative_column"}, "redSandstoneBrickLargeSiding", "Large Red Sandstone Brick");
		redSandStoneBrickLargeMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_redSandstoneBrickLargeMouldingAndDecorative, new String[] {"ginger_redSandstoneBrickLargeDecorative", "ginger_redSandstoneBrickLargeDecorative", "ginger_redSandstoneBrickLargeDecorative", "ginger_redSandstoneBrickLargeDecorative_column"}, redSandStoneBrickLargeSidingAndCorner.blockID, "redSandstoneBrickLargeMoulding");
		redSandStoneBrickLargeMossyStairs = new AddonBlockStairsRedSandStone(id_redSandstoneBrickLargeMossyStairs, redSandStone, 7).setUnlocalizedName("redSandStoneBrickLargeMossyStairs");
		redSandStoneBrickLargeMossySidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_redSandstoneBrickLargeMossySidingAndCorner, new String[] {"ginger_redSandstoneBrickLargeMossyDecorative", "ginger_redSandstoneBrickLargeMossyDecorative", "ginger_redSandstoneBrickLargeMossyDecorative", "ginger_redSandstoneBrickLargeMossyDecorative_column"}, "redSandstoneBrickLargeMossySiding", "Large Mossy Red Sandstone Brick");
		redSandStoneBrickLargeMossyMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_redSandstoneBrickLargeMossyMouldingAndDecorative, new String[] {"ginger_redSandstoneBrickLargeMossyDecorative", "ginger_redSandstoneBrickLargeMossyDecorative", "ginger_redSandstoneBrickLargeMossyDecorative", "ginger_redSandstoneBrickLargeMossyDecorative_column"}, redSandStoneBrickLargeMossySidingAndCorner.blockID, "redSandstoneBrickLargeMossyMoulding");
		redSandStoneCrackedStairs = new AddonBlockStairsRedSandStone(id_redSandstoneCrackedStairs, redSandStone, 8).setUnlocalizedName("redSandStoneCrackedStairs");
		redSandStoneCrackedSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_redSandstoneCrackedSidingAndCorner, new String[] {"ginger_redSandstoneDecorative_bottom", "ginger_redSandstoneDecorative_bottom", "ginger_redSandstoneDecorative_bottom", "ginger_redSandstoneDecorative_bottom"}, "redSandstoneCrackedSiding", "Cracked Red Sandstone");
		redSandStoneCrackedMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_redSandstoneCrackedMouldingAndDecorative, new String[] {"ginger_redSandstoneDecorative_bottom", "ginger_redSandstoneDecorative_bottom", "ginger_redSandstoneDecorative_bottom", "ginger_redSandstoneDecorative_bottom"}, redSandStoneCrackedSidingAndCorner.blockID, "redSandstoneCrackedMoulding");
		redSandStoneBrickLargeCrackedStairs = new AddonBlockStairsRedSandStone(id_redSandstoneBrickLargeCrackedStairs, redSandStone, 9).setUnlocalizedName("redSandStoneBrickLargeCrackedStairs");
		redSandStoneBrickLargeCrackedSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_redSandstoneBrickLargeCrackedSidingAndCorner, new String[] {"ginger_redSandstoneBrickLargeCrackedDecorative", "ginger_redSandstoneBrickLargeCrackedDecorative", "ginger_redSandstoneBrickLargeCrackedDecorative", "ginger_redSandstoneBrickLargeCrackedDecorative_column"}, "redSandstoneBrickLargeCrackedSiding", "Cracked Large Red Sandstone Brick");
		redSandStoneBrickLargeCrackedMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_redSandstoneBrickLargeCrackedMouldingAndDecorative, new String[] {"ginger_redSandstoneBrickLargeCrackedDecorative", "ginger_redSandstoneBrickLargeCrackedDecorative", "ginger_redSandstoneBrickLargeCrackedDecorative", "ginger_redSandstoneBrickLargeCrackedDecorative_column"}, redSandStoneBrickLargeCrackedSidingAndCorner.blockID, "redSandstoneBrickLargeCrackedMoulding");

		AddonManager.Register(redSand, "Red Sand");
		AddonManager.Register(redSandSlab, "Red Sand Slab");
		AddonManager.Register(redSandStone, new String[] {"redSandtone", "chiseledRedSandstone", "smoothRedSandstone", "polishedRedSandstone", "redSandstoneBrick", "redSandstoneMossy", "redSandstoneLargeBrick", "redSandstoneLargeBrickMossy", "redSandstoneCracked", "redSandstoneLargeBrickCracked"}, 
				new String[] {"Red Sandstone", "Chiseled Red Sandstone", "Cut Red Sandstone", "Polished Red Sandstone", "Red Sandstone Brick", "Mossy Red Sandstone", "Large Red Sandstone Brick", "Large Mossy Red Sandstone Brick", "Cracked Red Sandstone", "Cracked Large Red Sandstone Brick"});
		AddonManager.Register(redSandStoneStairs, "Red Sandstone Stairs");
		AddonManager.Register(redSandStoneSmoothStairs, "Cut Red Sandstone Stairs");
		AddonManager.Register(redSandStonePolishedStairs, "Polished Red Sandstone Stairs");
		AddonManager.Register(redSandStoneBrickStairs, "Red Sandstone Brick Stairs");
		AddonManager.Register(redSandStoneMossyStairs, "Mossy Red Sandstone Stairs");
		AddonManager.Register(redSandStoneBrickLargeStairs, "Large Red Sandstone Brick Stairs");
		AddonManager.Register(redSandStoneBrickLargeMossyStairs, "Large Mossy Red Sandstone Brick Stairs");
		AddonManager.Register(redSandStoneCrackedStairs, "Cracked Red Sandstone Stairs");
		AddonManager.Register(redSandStoneBrickLargeCrackedStairs, "Cracked Large Red Sandstone Brick Stairs");

		Item.itemsList[redSandStoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(redSandStoneSidingAndCorner, redSandStoneMouldingAndDecorative, "Red Sandstone");
		Item.itemsList[redSandStoneSmoothSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneSmoothSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneSmoothMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneSmoothMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(redSandStoneSmoothSidingAndCorner, redSandStoneSmoothMouldingAndDecorative, "Cut Red Sandstone");
		Item.itemsList[redSandStonePolishedSidingAndCorner.blockID]= new FCItemBlockSidingAndCorner(redSandStonePolishedSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStonePolishedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStonePolishedMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(redSandStonePolishedSidingAndCorner, redSandStonePolishedMouldingAndDecorative, "Polished Red Sandstone");
		Item.itemsList[redSandStoneBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneBrickSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneBrickMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(redSandStoneBrickSidingAndCorner, redSandStoneBrickMouldingAndDecorative, "Red Sandstone Brick");
		Item.itemsList[redSandStoneMossySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneMossySidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneMossyMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneMossyMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(redSandStoneMossySidingAndCorner, redSandStoneMossyMouldingAndDecorative, "Mossy Red Sandstone");
		Item.itemsList[redSandStoneBrickLargeSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneBrickLargeSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneBrickLargeMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneBrickLargeMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(redSandStoneBrickLargeSidingAndCorner, redSandStoneBrickLargeMouldingAndDecorative, "Large Red Sandstone Brick");
		Item.itemsList[redSandStoneBrickLargeMossySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneBrickLargeMossySidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneBrickLargeMossyMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneBrickLargeMossyMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(redSandStoneBrickLargeMossySidingAndCorner, redSandStoneBrickLargeMossyMouldingAndDecorative, "Large Mossy Red Sandstone Brick");
		Item.itemsList[redSandStoneCrackedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneCrackedSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneCrackedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneCrackedMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(redSandStoneCrackedSidingAndCorner, redSandStoneCrackedMouldingAndDecorative, "Cracked Red Sandstone");
		Item.itemsList[redSandStoneBrickLargeCrackedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(redSandStoneBrickLargeCrackedSidingAndCorner.blockID - 256);
		Item.itemsList[redSandStoneBrickLargeCrackedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(redSandStoneBrickLargeCrackedMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(redSandStoneBrickLargeCrackedSidingAndCorner, redSandStoneBrickLargeCrackedMouldingAndDecorative, "Cracked Large Red Sandstone Brick");

		pileRedSand = new AddonItemPileRedSand(id_pileRedSand);
		AddonManager.Name(pileRedSand,  "Red Sand Pile");

		Block sandStone = new AddonBlockSandStone(AddonManager.ReplaceBlockID(Block.sandStone));
		AddonManager.SetVanillaBlockFinal("sandStone", Block.sandStone, sandStone);
		AddonManager.Register(Block.sandStone, new String[] {"sandstone", "sandstoneChiseled", "sandstoneCut", "sandstonePolished", "sandstoneBrick", "sandstoneMossy", "sandstoneLargeBrick", "sandstoneLargeBrickMossy", "sandstoneCracked", "sandstoneLargeBrickCracked"}, 
				new String[] {"Sandstone", "Chiseled Sandstone", "Cut Sandstone", "Polished Sandstone", "Sandstone Brick", "Mossy Sandstone", "Large Sandstone Brick", "Large Mossy Sandstone Brick", "Cracked Sandstone", "Cracked Large Sandstone Brick"});
		Block sandStoneStairs = (new AddonBlockStairsSandStone(AddonManager.ReplaceBlockID(Block.stairsSandStone), Block.sandStone, 0)).setUnlocalizedName("stairsSandStone");
		AddonManager.SetVanillaBlockFinal("stairsSandStone", Block.stairsSandStone, sandStoneStairs);

		sandStoneSmoothStairs = new AddonBlockStairsSandStone(id_sandstoneSmoothStairs, Block.sandStone, 2).setUnlocalizedName("stairsSandStoneSmooth");
		sandStoneSmoothSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_sandstoneSmoothSidingAndCorner, new String[] {"fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "ginger_sandstoneSmoothDecorative_side", "ginger_sandstoneSmoothDecorative_column"}, "sandstoneSmoothSiding", "Smooth Sandstone");
		sandStoneSmoothMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_sandstoneSmoothMouldingAndDecorative, new String[] {"fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "ginger_sandstoneSmoothDecorative_side", "ginger_sandstoneSmoothDecorative_column"}, sandStoneSmoothSidingAndCorner.blockID, "sandstoneSmoothMoulding");
		sandStonePolishedStairs = new AddonBlockStairsSandStone(id_polishedSandstoneStairs, Block.sandStone, 3).setUnlocalizedName("stairsSandStonePolished");
		sandStonePolishedSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_polishedSandstoneSidingAndCorner, new String[] {"fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top"}, "sandstonePolishedSiding", "Polished Sandstone");
		sandStonePolishedMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_polishedSandstoneMouldingAndDecorative, new String[] {"fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top"}, sandStonePolishedSidingAndCorner.blockID, "sandstonePolishedMoulding");
		sandStoneBrickStairs = new AddonBlockStairsSandStone(id_sandstoneBrickStairs, Block.sandStone, 4).setUnlocalizedName("stairsSandStoneBrick");
		sandStoneBrickSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_sandstoneBrickSidingAndCorner, new String[] {"ginger_sandstoneBrickDecorative", "ginger_sandstoneBrickDecorative", "ginger_sandstoneBrickDecorative", "ginger_sandstoneBrickDecorative_column"}, "sandstoneBrickSiding", "Brick Sandstone");
		sandStoneBrickMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_sandstoneBrickMouldingAndDecorative, new String[] {"ginger_sandstoneBrickDecorative", "ginger_sandstoneBrickDecorative", "ginger_sandstoneBrickDecorative", "ginger_sandstoneBrickDecorative_column"}, sandStoneBrickSidingAndCorner.blockID, "sandstoneBrickMoulding");
		sandStoneMossyStairs = new AddonBlockStairsSandStone(id_sandstoneMossyStairs, Block.sandStone, 5).setUnlocalizedName("stairsSandStoneMossy");
		sandStoneMossySidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_sandstoneMossySidingAndCorner, new String[] {"ginger_sandstoneMossyDecorative_top", "ginger_sandstoneMossyDecorative_top", "ginger_sandstoneMossyDecorative_side", "ginger_sandstoneMossyDecorative_column"}, "sandstoneMossySiding", "Mossy Sandstone");
		sandStoneMossyMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_sandstoneMossyMouldingAndDecorative, new String[] {"ginger_sandstoneMossyDecorative_top", "ginger_sandstoneMossyDecorative_top", "ginger_sandstoneMossyDecorative_side", "ginger_sandstoneMossyDecorative_column"}, sandStoneMossySidingAndCorner.blockID, "sandstoneMossyMoulding");
		sandStoneBrickLargeStairs = new AddonBlockStairsSandStone(id_sandstoneBrickLargeStairs, Block.sandStone, 6).setUnlocalizedName("stairsSandStoneBrickLarge");
		sandStoneBrickLargeSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_sandstoneBrickLargeSidingAndCorner, new String[] {"ginger_sandstoneBrickLargeDecorative", "ginger_sandstoneBrickLargeDecorative", "ginger_sandstoneBrickLargeDecorative", "ginger_sandstoneBrickLargeDecorative_column"}, "sandstoneBrickLargeSiding", "Large Sandstone Brick");
		sandStoneBrickLargeMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_sandstoneBrickLargeMouldingAndDecorative, new String[] {"ginger_sandstoneBrickLargeDecorative", "ginger_sandstoneBrickLargeDecorative", "ginger_sandstoneBrickLargeDecorative", "ginger_sandstoneBrickLargeDecorative_column"}, sandStoneBrickLargeSidingAndCorner.blockID, "sandstoneBrickLargeMoulding");
		sandStoneBrickLargeMossyStairs = new AddonBlockStairsSandStone(id_sandstoneBrickLargeMossyStairs, Block.sandStone, 7).setUnlocalizedName("stairsSandStoneBrickLargeMossy");
		sandStoneBrickLargeMossySidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_sandstoneBrickLargeMossySidingAndCorner, new String[] {"ginger_sandstoneBrickLargeMossyDecorative", "ginger_sandstoneBrickLargeMossyDecorative", "ginger_sandstoneBrickLargeMossyDecorative", "ginger_sandstoneBrickLargeMossyDecorative_column"}, "sandstoneBrickLargeMossySiding", "Large Mossy Sandstone Brick");
		sandStoneBrickLargeMossyMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_sandstoneBrickLargeMossyMouldingAndDecorative, new String[] {"ginger_sandstoneBrickLargeMossyDecorative", "ginger_sandstoneBrickLargeMossyDecorative", "ginger_sandstoneBrickLargeMossyDecorative", "ginger_sandstoneBrickLargeMossyDecorative_column"}, sandStoneBrickLargeMossySidingAndCorner.blockID, "sandstoneBrickLargeMossyMoulding");
		sandStoneCrackedStairs = new AddonBlockStairsSandStone(id_sandstoneCrackedStairs, Block.sandStone, 8).setUnlocalizedName("stairsSandStoneCracked");
		sandStoneCrackedSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_sandstoneCrackedSidingAndCorner, new String[] {"fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom"}, "sandstoneCrackedSiding", "Cracked Sandstone");
		sandStoneCrackedMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_sandstoneCrackedMouldingAndDecorative, new String[] {"fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom"}, sandStoneCrackedSidingAndCorner.blockID, "sandstoneCrackedMoulding");
		sandStoneBrickLargeCrackedStairs = new AddonBlockStairsSandStone(id_sandstoneBrickLargeCrackedStairs, Block.sandStone, 9).setUnlocalizedName("stairsSandStoneBrickLargeCracked");
		sandStoneBrickLargeCrackedSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(id_sandstoneBrickLargeCrackedSidingAndCorner, new String[] {"ginger_sandstoneBrickLargeCrackedDecorative", "ginger_sandstoneBrickLargeCrackedDecorative", "ginger_sandstoneBrickLargeCrackedDecorative", "ginger_sandstoneBrickLargeCrackedDecorative_column"}, "sandstoneBrickLargeCrackedSiding", "Cracked Large Sandstone Brick");
		sandStoneBrickLargeCrackedMouldingAndDecorative = new AddonBlockSandStoneMouldingAndDecorative(id_sandstoneBrickLargeCrackedMouldingAndDecorative, new String[] {"ginger_sandstoneBrickLargeCrackedDecorative", "ginger_sandstoneBrickLargeCrackedDecorative", "ginger_sandstoneBrickLargeCrackedDecorative", "ginger_sandstoneBrickLargeCrackedDecorative_column"}, sandStoneBrickLargeCrackedSidingAndCorner.blockID, "sandstoneBrickLargeCrackedMoulding");

		AddonManager.Register(sandStoneSmoothStairs, "Cut Sandstone Stairs");
		AddonManager.Register(sandStonePolishedStairs, "Polished Sandstone Stairs");
		AddonManager.Register(sandStoneBrickStairs, "Sandstone Brick Stairs");
		AddonManager.Register(sandStoneMossyStairs, "Mossy Sandstone Stairs");
		AddonManager.Register(sandStoneBrickLargeStairs, "Large Sandstone Brick Stairs");
		AddonManager.Register(sandStoneBrickLargeMossyStairs, "Large Mossy Sandstone Brick Stairs");
		AddonManager.Register(sandStoneCrackedStairs, "Cracked Sandstone Stairs");
		AddonManager.Register(sandStoneBrickLargeCrackedStairs, "Cracked Large Sandstone Brick Stairs");

		Item.itemsList[sandStoneSmoothSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneSmoothSidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneSmoothMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneSmoothMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(sandStoneSmoothSidingAndCorner, sandStoneSmoothMouldingAndDecorative, "Cut Sandstone");
		Item.itemsList[sandStonePolishedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStonePolishedSidingAndCorner.blockID - 256);
		Item.itemsList[sandStonePolishedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStonePolishedMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(sandStonePolishedSidingAndCorner, sandStonePolishedMouldingAndDecorative, "Polished Sandstone");
		Item.itemsList[sandStoneBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneBrickSidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneBrickMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(sandStoneBrickSidingAndCorner, sandStoneBrickMouldingAndDecorative, "Sandstone Brick");
		Item.itemsList[sandStoneMossySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneMossySidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneMossyMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneMossyMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(sandStoneMossySidingAndCorner, sandStoneMossyMouldingAndDecorative, "Mossy Sandstone");
		Item.itemsList[sandStoneBrickLargeSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneBrickLargeSidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneBrickLargeMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneBrickLargeMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(sandStoneBrickLargeSidingAndCorner, sandStoneBrickLargeMouldingAndDecorative, "Large Sandstone Brick");
		Item.itemsList[sandStoneBrickLargeMossySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneBrickLargeMossySidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneBrickLargeMossyMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneBrickLargeMossyMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(sandStoneBrickLargeMossySidingAndCorner, sandStoneBrickLargeMossyMouldingAndDecorative, "Large Mossy Sandstone Brick");
		Item.itemsList[sandStoneCrackedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneCrackedSidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneCrackedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneCrackedMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(sandStoneCrackedSidingAndCorner, sandStoneCrackedMouldingAndDecorative, "Cracked Sandstone");
		Item.itemsList[sandStoneBrickLargeCrackedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(sandStoneBrickLargeCrackedSidingAndCorner.blockID - 256);
		Item.itemsList[sandStoneBrickLargeCrackedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(sandStoneBrickLargeCrackedMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(sandStoneBrickLargeCrackedSidingAndCorner, sandStoneBrickLargeCrackedMouldingAndDecorative, "Cracked Large Sandstone Brick");

		//Prismarine
		prismarine = new AddonBlockPrismarine(id_prismarine);
		prismarineLantern = new AddonBlockPrismarineLantern(id_prismarineLantern);
		AddonManager.Register(prismarineLantern, "Prismarine Lantern");
		FCTileEntityBeacon.AddBeaconEffect(prismarineLantern.blockID, Potion.nightVision.getId());

		prismarineSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_prismarineSidingAndCorner,  Material.rock, "ginger_prismarineDecorative", 1.5F, 10.0F, Block.soundStoneFootstep, "prismarineSiding", "Prismarine").SetPicksEffectiveOn();
		prismarineMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_prismarineMouldingAndDecorative, Material.rock, "ginger_prismarineDecorative", "ginger_prismarineDecorative_column", 3042, 1.5F, 10.0F, Block.soundStoneFootstep, "prismarineMoulding").SetPicksEffectiveOn();
		prismarineStairs = new FCBlockStairs(id_prismarineStairs, prismarine, 0).setUnlocalizedName("stairsPrismarine");
		prismarineBrickSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_prismarineBrickSidingAndCorner,  Material.rock, "ginger_prismarineBrickDecorative", 1.5F, 10.0F, Block.soundStoneFootstep, "prismarineBrickSiding", "Prismarine Brick").SetPicksEffectiveOn();
		prismarineBrickMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_prismarineBrickMouldingAndDecorative, Material.rock, "ginger_prismarineBrickDecorative", "ginger_prismarineBrickDecorative_column", 3042, 1.5F, 10.0F, Block.soundStoneFootstep, "prismarineBrickMoulding").SetPicksEffectiveOn();
		prismarineBrickStairs = new FCBlockStairs(id_prismarineBrickStairs, prismarine, 1).setUnlocalizedName("stairsPrismarineBrick");
		prismarineDarkSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_prismarineDarkSidingAndCorner,  Material.rock, "ginger_prismarineDarkDecorative", 1.5F, 10.0F, Block.soundStoneFootstep, "prismarineDarkSiding", "Dark Prismarine").SetPicksEffectiveOn();
		prismarineDarkMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_prismarineDarkMouldingAndDecorative, Material.rock, "ginger_prismarineDarkDecorative", "ginger_prismarineDarkDecorative_column", 3042, 1.5F, 10.0F, Block.soundStoneFootstep, "prismarineDarkMoulding").SetPicksEffectiveOn();
		prismarineDarkStairs = new FCBlockStairs(id_prismarineDarkStairs, prismarine, 2).setUnlocalizedName("stairsPrismarineDark");

		Item.itemsList[prismarineSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(prismarineSidingAndCorner.blockID - 256);
		Item.itemsList[prismarineMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(prismarineMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(prismarineSidingAndCorner, prismarineMouldingAndDecorative, "Prismarine");
		AddonManager.Register(prismarineStairs, "Prismarine Stairs");
		Item.itemsList[prismarineBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(prismarineBrickSidingAndCorner.blockID - 256);
		Item.itemsList[prismarineBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(prismarineBrickMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(prismarineBrickSidingAndCorner, prismarineBrickMouldingAndDecorative, "Prismarine Brick");
		AddonManager.Register(prismarineBrickStairs, "Prismarine Brick Stairs");
		Item.itemsList[prismarineDarkSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(prismarineDarkSidingAndCorner.blockID - 256);
		Item.itemsList[prismarineDarkMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(prismarineDarkMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(prismarineDarkSidingAndCorner, prismarineDarkMouldingAndDecorative, "Dark Prismarine");
		AddonManager.Register(prismarineDarkStairs, "Dark Prismarine Stairs");

		prismarineShard = new Item(id_prismarineShard).setUnlocalizedName("ginger_prismarineShard").setCreativeTab(CreativeTabs.tabMaterials).SetFilterableProperties(4);
		AddonManager.Name(prismarineShard, "Prismarine Shard");
		prismarineCrystal = new Item(id_prismarineCrystal).setUnlocalizedName("ginger_prismarineCrystal").setCreativeTab(CreativeTabs.tabMaterials).SetFilterableProperties(4);
		AddonManager.Name(prismarineCrystal, "Prismarine Crystal");

		//Nether brick
		netherBrick = new AddonBlockNetherBrickRed(id_netherBrick);
		netherBrickStairs = new FCBlockStairs(id_netherBrickStairs, netherBrick, 0).setUnlocalizedName("netherBrickStairs");
		netherBrickLoose = new AddonBlockNetherBrickRedLoose(id_netherBrickLoose);
		netherBrickLooseSlab = new AddonBlockNetherBrickRedLooseSlab(id_netherBrickLooseSlab);
		netherBrickLooseStairs = new AddonBlockNetherBrickRedLooseStairs(id_netherBrickLooseStairs);
		netherBrickSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_netherBrickSidingAndCorner, FCBetterThanWolves.fcMaterialNetherRock, "ginger_netherBrickRedDecorative", 2.0F, 10.0F, Block.soundStoneFootstep, "netherBrickRedSiding", "Red Nether Brick");
		netherBrickMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_netherBrickMouldingAndDecorative, FCBetterThanWolves.fcMaterialNetherRock, "ginger_netherBrickRedDecorative", "ginger_netherBrickRedDecorative_column", 3042, 2.0F, 10.0F, Block.soundStoneFootstep, "netherBrickRedMoulding");

		AddonManager.Register(netherBrick, new String[] {"netherBrickRed",  "netherBrickRedChiseled", "netherBrickChiseled"}, new String[] {"Red Nether Brick", "Chiseled Red Nether Brick", "Chiseled Nether Brick"});
		AddonManager.Register(netherBrickStairs, "Red Nether Brick Stairs");
		AddonManager.Register(netherBrickLoose, "Loose Red Nether Brick");
		AddonManager.Register(netherBrickLooseSlab, "Loose Red Nether Brick Slab");
		AddonManager.Register(netherBrickLooseStairs, "Loose Red Nether Brick Stairs");
		Item.itemsList[netherBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(netherBrickSidingAndCorner.blockID - 256);
		Item.itemsList[netherBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(netherBrickMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(netherBrickSidingAndCorner, netherBrickMouldingAndDecorative, "Red Nether Brick");

		netherBrickSuperheated = new AddonBlockNetherBrickSuperheated(id_netherBrickSuperheated);
		AddonManager.Register(netherBrickSuperheated);

		Block.netherBrick.setStepSound(stepSoundNetherBrick);
		Block.netherFence.setStepSound(stepSoundNetherBrick);
		Block.stairsNetherBrick.setStepSound(stepSoundNetherBrick);
		FCBetterThanWolves.fcBlockNetherBrickLoose.setStepSound(stepSoundNetherBrick);
		FCBetterThanWolves.fcBlockNetherBrickLooseSlab.setStepSound(stepSoundNetherBrick);
		FCBetterThanWolves.fcBlockNetherBrickLooseStairs.setStepSound(stepSoundNetherBrick);
		FCBetterThanWolves.fcBlockNetherBrickMouldingAndDecorative.setStepSound(stepSoundNetherBrick);
		FCBetterThanWolves.fcBlockNetherBrickSidingAndCorner.setStepSound(stepSoundNetherBrick);
		netherBrick.setStepSound(stepSoundNetherBrick);
		netherBrickLoose.setStepSound(stepSoundNetherBrick);
		netherBrickStairs.setStepSound(stepSoundNetherBrick);
		netherBrickSuperheated.setStepSound(stepSoundNetherBrick);
		netherBrickLooseSlab.setStepSound(stepSoundNetherBrick);
		netherBrickLooseStairs.setStepSound(stepSoundNetherBrick);
		netherBrickSidingAndCorner.setStepSound(stepSoundNetherBrick);
		netherBrickMouldingAndDecorative.setStepSound(stepSoundNetherBrick);

		//Netherrack
		netherrackSuperheated = new AddonBlockNetherrackSuperheated(id_netherrackSuperheated);
		AddonManager.Register(netherrackSuperheated);
		magma = new AddonBlockMagma(id_magma);
		AddonManager.Register(magma, "Magma Block");

		//Nylium
		nylium = new AddonBlockNylium(id_nylium);
		//AddonManager.Register(nylium, new String[] {"crimsonNylium", "warpedNylium"}, new String[] {"Crimson Nylium", "Warped Nylium"});

		//Basalt
		basalt = new AddonBlockDirectional(id_basalt, FCBetterThanWolves.fcMaterialNetherRock, new String[] {"ginger_basalt_top", "ginger_basaltSmooth_top"}, new String[] {"ginger_basalt_side", "ginger_basaltSmooth_side"})
				.SetPicksEffectiveOn()
				.setCreativeTab(CreativeTabs.tabBlock)
				.setHardness(2.0F)
				.setHardness(10.0F);
		AddonManager.Register(basalt, new String[] {"basalt", "basaltSmooth"}, new String[] {"Basalt", "Polished Basalt"});

		Block.netherrack.setStepSound(stepSoundNetherrack);
		Block.oreNetherQuartz.setStepSound(stepSoundNetherrack);
		FCBetterThanWolves.fcBlockNetherrackFalling.setStepSound(stepSoundNetherrack);
		netherrackSuperheated.setStepSound(stepSoundNetherrack);
		basalt.setStepSound(stepSoundNetherrack);

		//Infused stone
		infusedStone = new AddonBlockInfusedStone(id_infusedStone);
		infusedStoneSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_infusedStoneSidingAndCorner, Material.rock, "ginger_infusedStoneDecorative", 2.0F, 10.0F, Block.soundStoneFootstep, "infusedStoneSiding", "Infused Stone").SetPicksEffectiveOn();
		infusedStoneMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_infusedStoneMouldingAndDecorative, Material.rock, "ginger_infusedStoneDecorative", "ginger_infusedStoneDecorative_column", 3042, 2.0F, 10.0F, Block.soundStoneFootstep, "infusedStoneMoulding").SetPicksEffectiveOn();
		infusedStoneStairs = new FCBlockStairs(id_infusedStoneStairs, infusedStone, 0).setUnlocalizedName("infusedStoneStairs");
		infusedStoneSmoothSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_infusedStoneSmoothSidingAndCorner, Material.rock, "ginger_infusedStoneSmoothDecorative", 2.0F, 10.0F, Block.soundStoneFootstep, "infusedStoneSmoothSiding", "Polished Infused Stone").SetPicksEffectiveOn();
		infusedStoneSmoothMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_infusedStoneSmoothMouldingAndDecorative, Material.rock, "ginger_infusedStoneSmoothDecorative", "ginger_infusedStoneSmoothDecorative_column", 3042, 2.0F, 10.0F, Block.soundStoneFootstep, "infusedStoneSmoothMoulding").SetPicksEffectiveOn();
		infusedStoneSmoothStairs = new FCBlockStairs(id_infusedStoneSmoothStairs, infusedStone, 1).setUnlocalizedName("infusedStoneSmoothStairs");
		infusedStoneBrickSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_infusedStoneBrickSidingAndCorner, Material.rock, "ginger_infusedStoneBrickDecorative", 2.0F, 10.0F, Block.soundStoneFootstep, "infusedStoneBrickSiding", "Infused Stone Brick").SetPicksEffectiveOn();
		infusedStoneBrickMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_infusedStoneBrickMouldingAndDecorative, Material.rock, "ginger_infusedStoneBrickDecorative", "ginger_infusedStoneBrickDecorative_column", 3042, 2.0F, 10.0F, Block.soundStoneFootstep, "infusedStoneBrickMoulding").SetPicksEffectiveOn();
		infusedStoneBrickStairs = new FCBlockStairs(id_infusedStoneBrickStairs, infusedStone, 2).setUnlocalizedName("infusedStoneBrickStairs");

		AddonManager.Register(infusedStone, new String[] {"infusedStone", "infusedStoneSmooth", "infusedStoneBrick", "infusedStoneChiseled"}, new String[] {"Infused Stone", "Polished Infused Stone", "Infused Stone Brick", "Chiseled Infused Stone"});
		Item.itemsList[infusedStoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(infusedStoneSidingAndCorner.blockID - 256);
		Item.itemsList[infusedStoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(infusedStoneMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(infusedStoneSidingAndCorner, infusedStoneMouldingAndDecorative, "Infused Stone");
		AddonManager.Register(infusedStoneStairs, "Infused Stone Stairs");
		Item.itemsList[infusedStoneSmoothSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(infusedStoneSmoothSidingAndCorner.blockID - 256);
		Item.itemsList[infusedStoneSmoothMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(infusedStoneSmoothMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(infusedStoneSmoothSidingAndCorner, infusedStoneSmoothMouldingAndDecorative, "Polished Infused Stone");
		AddonManager.Register(infusedStoneSmoothStairs, "Polished Infused Stone Stairs");
		Item.itemsList[infusedStoneBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(infusedStoneBrickSidingAndCorner.blockID - 256);
		Item.itemsList[infusedStoneBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(infusedStoneBrickMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(infusedStoneBrickSidingAndCorner, infusedStoneBrickMouldingAndDecorative, "Infused Stone Brick");
		AddonManager.Register(infusedStoneBrickStairs, "Infused Stone Brick Stairs");

		//Concrete
		concrete = new AddonBlockConcrete(id_concrete, "ginger_concrete", "Concrete");
		concretePowder = new AddonBlockConcretePowder(id_concretePowder, "ginger_concretePowder", "Concrete Powder");

		concreteSidingAndCorner = new Block[16];
		concreteMouldingAndDecorative = new Block[16];
		concreteStairs = new Block[16];

		int i = 0;
		int id = id_concreteSubStart;
		final String main = "Concrete";

		while(i < 16)
		{
			concreteSidingAndCorner[i] = new AddonBlockSidingAndCornerDecorativeWall(id++, Material.rock, "ginger_concrete_"+i, 2.0F, 5.0F, Block.soundStoneFootstep, "concreteSiding_"+i, "Concrete");
			concreteMouldingAndDecorative[i] = new FCBlockMouldingAndDecorative(id++, Material.rock, "ginger_concrete_"+i, "ginger_concrete_column_"+i, 3042, 2.0F, 5.0F, Block.soundWoodFootstep, "concreteMoulding_"+i);
			concreteStairs[i] = new FCBlockStairs(id++, concrete, i).setUnlocalizedName("stairsConcrete_"+i);

			Item.itemsList[concreteSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(concreteSidingAndCorner[i].blockID - 256);
			Item.itemsList[concreteMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(concreteMouldingAndDecorative[i].blockID - 256);
			AddonManager.Register(concreteStairs[i], AddonBlockConcrete.names[i]+" "+main+" Stairs");
			AddonManager.NameSubBlocks_Wall(concreteSidingAndCorner[i], concreteMouldingAndDecorative[i], AddonBlockConcrete.names[i]+" "+main);

			i++;//i is metadata from original 16 color set
		}

		//End Stone Brick
		endStoneBrick = new Block(id_endStoneBrick, Material.rock).setHardness(3.0F).setResistance(15.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ginger_endStoneBrick").setCreativeTab(CreativeTabs.tabBlock);
		endStoneBrickStairs = new FCBlockStairs(id_endStoneBrickStairs, endStoneBrick, 0).setUnlocalizedName("endStoneBrickStairs");
		endStoneBrickSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(id_endStoneBrickSidingAndCorner, Material.rock, "ginger_endStoneBrickDecorative", 3.0F, 15.0F, Block.soundStoneFootstep, "endStoneBrickSiding", "End Stone Brick").SetPicksEffectiveOn();
		endStoneBrickMouldingAndDecorative = new FCBlockMouldingAndDecorative(id_endStoneBrickMouldingAndDecorative, Material.rock, "ginger_endStoneBrickDecorative", "ginger_endStoneBrickDecorative_column", 3042, 2.0F, 10.0F, Block.soundStoneFootstep, "endStoneBrickMoulding").SetPicksEffectiveOn();

		AddonManager.Register(endStoneBrick, "End Stone Brick");
		AddonManager.Register(endStoneBrickStairs, "End stone Brick Stairs");
		Item.itemsList[endStoneBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(endStoneBrickSidingAndCorner.blockID - 256);
		Item.itemsList[endStoneBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(endStoneBrickMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(endStoneBrickSidingAndCorner, endStoneBrickMouldingAndDecorative, "End Stone Brick");

		//Obsidian
		Block obsidian = new AddonBlockObsidian(AddonManager.ReplaceBlockID(Block.obsidian));
		AddonManager.SetVanillaBlockFinal("obsidian", Block.obsidian, obsidian);
		AddonManager.Register(obsidian, new String[] {"obsidian", "infusedObsidian"}, new String[] {"Obsidian", "Infused Obsidian"});

		//MUST BE LAST OR NULL POINTER
		//Has to be after reference blocks are declared
		stoneTypesLooseSlab = new AddonBlockStoneLooseSlab(id_stoneTypeLooseSlab, new int[] {id_stoneSlab2, id_stoneSlab2, id_stoneSlab3, id_stoneSlab3, id_stoneSlab3, id_stoneSlab3});
		Item.itemsList[AddonDefs.stoneTypesLooseSlab.blockID] = new AddonItemBlockSlabLoose(AddonDefs.stoneTypesLooseSlab.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 0), "Loose Granite Cobblestone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 1), "Loose Andesite Cobblestone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 2), "Loose Diorite Cobblestone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 3), "Loose Granite Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 4), "Loose Andesite Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneTypesLooseSlab, 1, 5), "Loose Diorite Brick Slab");

		AddonManager.Name(new ItemStack(Block.stoneSingleSlab, 1, 0), "Polished Stone Slab");

		stoneSlab = new AddonBlockSlabStone(id_stoneSlab, new Block[] {AddonDefs.redSandStone, AddonDefs.prismarine, AddonDefs.prismarine, AddonDefs.prismarine, FCBetterThanWolves.fcAestheticOpaque, AddonDefs.whiteStoneBrick, Block.cobblestoneMossy, AddonDefs.netherBrick}, new int[] {0, 0, 1, 2, 9, 0, 0, 0},
				new boolean[] {false, false, false, false, false, false, false, true}, new Block[] {null, null, null, null, null, null, null, netherBrickLooseSlab}, new int[] {0, 0, 0, 0, 0, 0, 0, 0});
		Item.itemsList[AddonDefs.stoneSlab.blockID] = new AddonItemBlockSlab(AddonDefs.stoneSlab.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab, 1, 0), "Red Sandstone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab, 1, 1), "Prismarine Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab, 1, 2), "Prismarine Bricks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab, 1, 3), "Dark Prismarine Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab, 1, 4), "White Stone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab, 1, 5), "White Stone Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab, 1, 6), "Mossy Cobblestone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab, 1, 7), "Red Nether Brick Slab");

		stoneSlab2 = new AddonBlockSlabStone(id_stoneSlab2, new Block[] {AddonDefs.stoneTypes, AddonDefs.stoneTypes, AddonDefs.stoneTypes, AddonDefs.stoneTypesSmooth, AddonDefs.stoneTypesSmooth, AddonDefs.stoneTypesSmooth, AddonDefs.stoneTypesCobble, AddonDefs.stoneTypesCobble}, new int[] {0, 1, 2, 0, 1, 2, 0, 1},
				new boolean[] {false, false, false, false, false, false, true, true}, new Block[] {null, null, null, null, null, null, stoneTypesLooseSlab, stoneTypesLooseSlab}, new int[] {0, 0, 0, 0, 0, 0, 0, 1});
		Item.itemsList[AddonDefs.stoneSlab2.blockID] = new AddonItemBlockSlab(AddonDefs.stoneSlab2.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab2, 1, 0), "Granite Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab2, 1, 1), "Andesite Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab2, 1, 2), "Diorite Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab2, 1, 3), "Polished Granite Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab2, 1, 4), "Polished Andesite Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab2, 1, 5), "Polished Diorite Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab2, 1, 6), "Granite Cobblestone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab2, 1, 7), "Andesite Cobblestone Slab");

		stoneSlab3 = new AddonBlockSlabStone(id_stoneSlab3, new Block[] {AddonDefs.stoneTypesCobble, AddonDefs.stoneTypesStoneBrick, AddonDefs.stoneTypesStoneBrick, AddonDefs.stoneTypesStoneBrick, AddonDefs.infusedStone, AddonDefs.infusedStone, AddonDefs.infusedStone, Block.stone}, new int[] {2, 0, 1, 2, 0, 1, 2, 0},
				new boolean[] {true, true, true, true, false, false, false, false}, new Block[] {stoneTypesLooseSlab, stoneTypesLooseSlab, stoneTypesLooseSlab, stoneTypesLooseSlab, null, null, null, null}, new int[] {2, 3, 4, 5, 0, 0, 0, 0});
		Item.itemsList[AddonDefs.stoneSlab3.blockID] = new AddonItemBlockSlab(AddonDefs.stoneSlab3.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab3, 1, 0), "Diorite Cobblestone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab3, 1, 1), "Granite Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab3, 1, 2), "Andesite Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab3, 1, 3), "Diorite Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab3, 1, 4), "Infused Stone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab3, 1, 5), "Polished Infused Stone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab3, 1, 6), "Infused Stone Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab3, 1, 7), "Stone Slab");

		stoneSlab4 = new AddonBlockSlabStone(id_stoneSlab4, new Block[] {Block.sandStone, Block.sandStone, Block.sandStone, AddonDefs.redSandStone, AddonDefs.redSandStone, AddonDefs.redSandStone, Block.sandStone, Block.sandStone}, new int[] {2, 3, 4, 2, 3, 4, 5, 6});
		Item.itemsList[AddonDefs.stoneSlab4.blockID] = new AddonItemBlockSlab(AddonDefs.stoneSlab4.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab4, 1, 0), "Cut Sandstone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab4, 1, 1), "Polished Sandstone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab4, 1, 2), "Sandstone Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab4, 1, 3), "Cut Red Sandstone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab4, 1, 4), "Polished Red Sandstone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab4, 1, 5), "Red Sandstone Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab4, 1, 6), "Mossy Sandstone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab4, 1, 7), "Large Sandstone Brick Slab");

		stoneSlab5 = new AddonBlockSlabStone(id_stoneSlab5, new Block[] {Block.sandStone, AddonDefs.redSandStone, AddonDefs.redSandStone, AddonDefs.redSandStone, Block.sandStone, Block.sandStone, AddonDefs.redSandStone, AddonDefs.redSandStone}, new int[] {7, 5, 6, 7, 8, 9, 8, 9});
		Item.itemsList[AddonDefs.stoneSlab5.blockID] = new AddonItemBlockSlab(AddonDefs.stoneSlab5.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab5, 1, 0), "Large Mossy Sandstone Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab5, 1, 1), "Mossy Red Sandstone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab5, 1, 2), "Large Red Sandstone Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab5, 1, 3), "Large Mossy Red Sandstone Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab5, 1, 4), "Cracked Sandstone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab5, 1, 5), "Cracked Large Sandstone Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab5, 1, 6), "Cracked Red Sandstone Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab5, 1, 7), "Cracked Large Red Sandstone Brick Slab");

		stoneSlab6 = new AddonBlockSlabStone(id_stoneSlab6, new Block[] {Block.stoneBrick, Block.stoneBrick, endStoneBrick, terracotta}, new int[] {1, 2, 0, 0});
		Item.itemsList[AddonDefs.stoneSlab6.blockID] = new AddonItemBlockSlab(AddonDefs.stoneSlab6.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab6, 1, 0), "Mossy Stone Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab6, 1, 1), "Cracked Stone Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab6, 1, 2), "End Stone Brick Slab");
		AddonManager.Name(new ItemStack(AddonDefs.stoneSlab6, 1, 3), "Terracotta Slab");

		concreteSlab = new AddonBlockSlabStone(id_concreteSlab, new Block[] {AddonDefs.concrete, AddonDefs.concrete, AddonDefs.concrete, AddonDefs.concrete, AddonDefs.concrete, AddonDefs.concrete, AddonDefs.concrete, AddonDefs.concrete}, new int[] {0, 1, 2, 3, 4, 5, 6, 7});
		Item.itemsList[AddonDefs.concreteSlab.blockID] = new AddonItemBlockSlab(AddonDefs.concreteSlab.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab, 1, 0), "Black Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab, 1, 1), "Red Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab, 1, 2), "Green Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab, 1, 3), "Brown Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab, 1, 4), "Blue Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab, 1, 5), "Purple Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab, 1, 6), "Cyan Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab, 1, 7), "Light Grey Concrete Slab");

		concreteSlab2 = new AddonBlockSlabStone(id_concreteSlab2, new Block[] {AddonDefs.concrete, AddonDefs.concrete, AddonDefs.concrete, AddonDefs.concrete, AddonDefs.concrete, AddonDefs.concrete, AddonDefs.concrete, AddonDefs.concrete}, new int[] {8, 9, 10, 11, 12, 13, 14, 15});
		Item.itemsList[AddonDefs.concreteSlab2.blockID] = new AddonItemBlockSlab(AddonDefs.concreteSlab2.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab2, 1, 0), "Gray Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab2, 1, 1), "Pink Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab2, 1, 2), "Lime Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab2, 1, 3), "Yellow Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab2, 1, 4), "Light Blue Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab2, 1, 5), "Magenta Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab2, 1, 6), "Orange Concrete Slab");
		AddonManager.Name(new ItemStack(AddonDefs.concreteSlab2, 1, 7), "White Concrete Slab");
	}

	private void addWoodDefs() {
		//Logs
		FCBetterThanWolves.fcItemBark = new AddonItemBark(FCBetterThanWolves.fcItemBark.itemID - 256);
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcItemBark, 1, 5), "Cherry Bark");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcItemBark, 1, 6), "Crimson Bark");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcItemBark, 1, 7), "Warped Bark");
		BlockLog addonLog = new AddonBlockLogReplace(AddonManager.ReplaceBlockID(Block.wood));
		AddonManager.SetVanillaBlockFinal("wood", Block.wood, addonLog);
		Item.itemsList[Block.wood.blockID] = new AddonItemBlockLog(Block.wood.blockID - 256, Block.wood, new String[] {"oakLog", "spruceLog", "birchLog", "jungleLog"});

		AddonManager.Name(new ItemStack(Block.wood, 1, 0), "Oak Log");
		AddonManager.Name(new ItemStack(Block.wood, 1, 1), "Spruce Log");
		AddonManager.Name(new ItemStack(Block.wood, 1, 2), "Birch Log");
		AddonManager.Name(new ItemStack(Block.wood, 1, 3), "Jungle Log");

		strippedLog = new AddonBlockLogStripped(id_strippedLog);
		Item.itemsList[AddonDefs.strippedLog.blockID] = new AddonItemBlockLogStripped(AddonDefs.strippedLog.blockID - 256, AddonDefs.strippedLog, new String[] {"oakLogStripped", "spruceLogStripped", "brichLogStripped", "jungleLogStripped"});
		barkLog = new AddonBlockLogBark(id_barkLog);
		Item.itemsList[barkLog.blockID] = new AddonItemBlockLog(barkLog.blockID - 256, barkLog, new String[] {"barkOak", "barkSpruce", "barkBirch", "barkJungle"});
		barkLogStripped = new AddonBlockLogBarkStripped(id_barkLogStripped);
		Item.itemsList[barkLogStripped.blockID] = new AddonItemBlockLogStripped(barkLogStripped.blockID - 256, barkLogStripped, new String[] {"barkOakStripped", "barkSpruceStripped", "barkBirchStripped", "barkJungleStripped"});

		FCBetterThanWolves.fcBloodWood = new AddonBlockLogBloodReplace(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBloodWood));
		Item.itemsList[FCBetterThanWolves.fcBloodWood.blockID] = new AddonItemBlockBloodLogReplace(FCBetterThanWolves.fcBloodWood.blockID - 256);
		AddonManager.Name(FCBetterThanWolves.fcBloodWood, "Blood Wood Log");
		bloodLog = new AddonBlockLogBlood(id_bloodLog);
		Item.itemsList[bloodLog.blockID] = new AddonItemBlockLogBlood(bloodLog.blockID - 256, bloodLog, new String[] {"strippedBloodLog", "bloodWood", "strippedBloodWood"});
		AddonManager.Name(new ItemStack(bloodLog, 1, 0), "Stripped Blood Wood Log");
		AddonManager.Name(new ItemStack(bloodLog, 1, 1), "Blood Wood");
		AddonManager.Name(new ItemStack(bloodLog, 1, 2), "Stripped Blood Wood");

		FCBetterThanWolves.fcBloodWood.setStepSound(stepSoundBloodLog);
		bloodLog.setStepSound(stepSoundBloodLog);

		cherryLog = new AddonBlockLogCherry(id_cherryLog);
		Item.itemsList[cherryLog.blockID] = new AddonItemBlockLogCherry(cherryLog.blockID - 256, cherryLog, new String[] {"logCherry", "strippedLogCherry", "woodCherry", "strippedWoodCherry"});
		AddonManager.Name(new ItemStack(cherryLog, 1, 0), "Cherry Log");
		AddonManager.Name(new ItemStack(cherryLog, 1, 1), "Stripped Cherry Log");
		AddonManager.Name(new ItemStack(cherryLog, 1, 2), "Cherry Wood");
		AddonManager.Name(new ItemStack(cherryLog, 1, 3), "Stripped Cherry Wood");

		cherryStump = new AddonBlockLogCherryStump(id_cherryStump);
		AddonManager.Register(cherryStump);
		FCBetterThanWolves.fcBlockWorkStump = new AddonBlockWorkStump(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWorkStump));

		FCBetterThanWolves.fcBlockLogDamaged = (FCBlockLogDamaged) new AddonBlockLogDamaged(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockLogDamaged), "ginger_strippedOakSide", "ginger_strippedOakTop", "fcBlockTrunkTop").setUnlocalizedName("chewedOak");
		logDamagedSpruce = new AddonBlockLogDamaged(id_logDamagedSpruce, "ginger_strippedSpruceSide", "ginger_strippedSpruceTop", "ginger_trunkSpruceTop").setUnlocalizedName("chewedSpruce");
		logDamagedBirch = new AddonBlockLogDamaged(id_logDamagedBirch, "ginger_strippedBirchSide", "ginger_strippedBirchTop", "ginger_trunkBirchTop").setUnlocalizedName("chewedBirch");
		logDamagedJungle = new AddonBlockLogDamaged(id_logDamagedJungle, "ginger_strippedJungleSide", "ginger_strippedJungleTop", "ginger_trunkJungleTop").setUnlocalizedName("chewedJungle");
		logDamagedBlood = new AddonBlockLogDamaged(id_logDamagedBlood, "ginger_strippedBloodSide", "ginger_strippedBloodTop", "ginger_trunkJungleTop").setUnlocalizedName("chewedBlood");
		logDamagedCherry = new AddonBlockLogDamaged(id_logDamagedCherry, "ginger_strippedCherrySide", "ginger_strippedCherryTop", "ginger_trunkCherryTop").setUnlocalizedName("chewedCherry");
		stemDamagedCrimson = new AddonBlockLogDamaged(id_stemDamagedCrimson, "ginger_strippedCrimsonSide", "ginger_strippedCrimsonTop", "ginger_trunkJungleTop").setUnlocalizedName("chewedCrimson");
		stemDamagedWarped = new AddonBlockLogDamaged(id_stemDamagedWarped, "ginger_strippedWarpedSide", "ginger_strippedWarpedTop", "ginger_trunkJungleTop").setUnlocalizedName("chewedWarped");
		AddonManager.Name(FCBetterThanWolves.fcBlockLogDamaged, "Chewed Oak Log");
		AddonManager.Register(logDamagedSpruce, "Chewed Spruce Log");
		AddonManager.Register(logDamagedBirch, "Chewed Birch Log");
		AddonManager.Register(logDamagedJungle, "Chewed Jungle Log");
		AddonManager.Register(logDamagedBlood, "Chewed Blood Wood Log");
		AddonManager.Register(logDamagedCherry, "Chewed Cherry Log");
		//AddonManager.Register(stemDamagedCrimson, "Chewed Crimson Stem");
		//AddonManager.Register(stemDamagedWarped, "Chewed Warped Stem");

		FCBetterThanWolves.fcBlockLogSpike = (FCBlockLogSpike) new AddonBlockLogSpike(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockLogSpike), "ginger_strippedOakSide", "ginger_strippedOakTop").setUnlocalizedName("oakSpike");
		logSpikeSpruce = new AddonBlockLogSpike(id_logSpikeSpruce, "ginger_strippedSpruceSide", "ginger_strippedSpruceTop").setUnlocalizedName("spruceSpike");
		logSpikeBirch = new AddonBlockLogSpike(id_logSpikeBirch, "ginger_strippedBirchSide", "ginger_strippedBirchTop").setUnlocalizedName("birchSpike");
		logSpikeJungle = new AddonBlockLogSpike(id_logSpikeJungle, "ginger_strippedJungleSide", "ginger_strippedJungleTop").setUnlocalizedName("jungleSpike");
		logSpikeBlood = new AddonBlockLogSpike(id_logSpikeBlood, "ginger_strippedBloodSide", "ginger_strippedBloodTop").setUnlocalizedName("bloodSpike");
		logSpikeCherry = new AddonBlockLogSpike(id_logSpikeCherry, "ginger_strippedCherrySide", "ginger_strippedCherryTop").setUnlocalizedName("cherrySpike");
		stemSpikeCrimson = new AddonBlockLogSpike(id_stemSpikeCrimson, "ginger_strippedCrimsonSide", "ginger_strippedCrimsonTop").setUnlocalizedName("crimsonSpike");
		stemSpikeWarped = new AddonBlockLogSpike(id_stemSpikeWarped, "ginger_strippedWarpedSide", "ginger_strippedWarpedTop").setUnlocalizedName("warpedSpike");
		AddonManager.Name(FCBetterThanWolves.fcBlockLogSpike, "Oak Log Spike");
		AddonManager.Register(logSpikeSpruce, "Spruce Log Spike");
		AddonManager.Register(logSpikeBirch, "Birch Log Spike");
		AddonManager.Register(logSpikeJungle, "Jungle Log Spike");
		AddonManager.Register(logSpikeBlood, "Blood Wood Log Spike");
		AddonManager.Register(logSpikeCherry, "Cherry Log Spike");
		//AddonManager.Register(stemSpikeCrimson, "Crimson Stem Spike");
		//AddonManager.Register(stemSpikeWarped, "Warped Stem Spike");

		stemCrimson = new AddonBlockStem(id_stemCrimson, new String[] {"ginger_stemCrimsonTop", "ginger_strippedCrimsonTop", "ginger_stemCrimsonSide", "ginger_strippedCrimsonSide"}, new String[] {"ginger_stemCrimsonSide", "ginger_strippedCrimsonSide", "ginger_stemCrimsonSide", "ginger_strippedCrimsonSide"}, 6, stemDamagedCrimson.blockID)
				.setHardness(1.25F)
				.setResistance(3.33F)
				.SetAxesEffectiveOn()
				.SetChiselsEffectiveOn()
				.SetBuoyant()
				.SetCanBeCookedByKiln(true)
				.SetItemIndexDroppedWhenCookedByKiln(263)
				.SetItemDamageDroppedWhenCookedByKiln(1)
				.SetFireProperties(FCEnumFlammability.LOGS)
				.setUnlocalizedName("crimsonStem")
				.setCreativeTab(CreativeTabs.tabBlock);
		//Item.itemsList[stemCrimson.blockID] = new AddonItemBlockStem(stemCrimson.blockID - 256, stemCrimson, new String[] {"stemCrimson", "strippedStemCrimson", "woodCrimson", "strippedWoodCrimson"}, 6);
		//AddonManager.Name(new ItemStack(stemCrimson, 1, 0), "Crimson Stem");
		//AddonManager.Name(new ItemStack(stemCrimson, 1, 1), "Stripped Crimson Stem");
		//AddonManager.Name(new ItemStack(stemCrimson, 1, 2), "Crimson Hyphae");
		//AddonManager.Name(new ItemStack(stemCrimson, 1, 3), "Stripped Crimson Hyphae");

		stemWarped = new AddonBlockStem(id_stemWarped, new String[] {"ginger_stemWarpedTop", "ginger_strippedWarpedTop", "ginger_stemWarpedSide", "ginger_strippedWarpedSide"}, new String[] {"ginger_stemWarpedSide", "ginger_strippedWarpedSide", "ginger_stemWarpedSide", "ginger_strippedWarpedSide"}, 7, stemDamagedWarped.blockID)
				.setHardness(1.25F)
				.setResistance(3.33F)
				.SetAxesEffectiveOn()
				.SetChiselsEffectiveOn()
				.SetBuoyant()
				.SetCanBeCookedByKiln(true)
				.SetItemIndexDroppedWhenCookedByKiln(263)
				.SetItemDamageDroppedWhenCookedByKiln(1)
				.SetFireProperties(FCEnumFlammability.LOGS)
				.setUnlocalizedName("warpedStem")
				.setCreativeTab(CreativeTabs.tabBlock);
		//Item.itemsList[stemWarped.blockID] = new AddonItemBlockStem(stemWarped.blockID - 256, stemCrimson, new String[] {"stemWarped", "strippedStemWarped", "woodWarped", "strippedWoodWarped"}, 7);
		//AddonManager.Name(new ItemStack(stemWarped, 1, 0), "Warped Stem");
		//AddonManager.Name(new ItemStack(stemWarped, 1, 1), "Stripped Warped Stem");
		//AddonManager.Name(new ItemStack(stemWarped, 1, 2), "Warped Hyphae");
		//AddonManager.Name(new ItemStack(stemWarped, 1, 3), "Stripped Warped Hyphae");

		stemCrimson.setStepSound(stepSoundBloodLog);
		stemWarped.setStepSound(stepSoundBloodLog);

		//Planks
		Block planks = new AddonBlockPlanks(AddonManager.ReplaceBlockID(Block.planks));
		AddonManager.SetVanillaBlockFinal("planks", Block.planks, planks);
		AddonManager.Register(Block.planks, new String[] {"oakPlanks", "sprucePlanks", "birchPlanks", "junglePlanks", "bloodPlanks", "cherryPlanks"},
				new String[] {"Oak Planks", "Spruce Planks", "Birch Planks", "Jungle Planks", "Blood Wood Planks", "Cherry Planks"});
		//AddonManager.Register(Block.planks, new String[] {"oakPlanks", "sprucePlanks", "birchPlanks", "junglePlanks", "bloodPlanks", "cherryPlanks", "crimsonPlanks", "warpedPlanks"},
		//		new String[] {"Oak Planks", "Spruce Planks", "Birch Planks", "Jungle Planks", "Blood Wood Planks", "Cherry Planks", "Crimson Planks", "Warped Planks"});
		Block stairsWoodOak = (new FCBlockStairsWood(AddonManager.ReplaceBlockID(Block.stairsWoodOak), Block.planks, 0)).setUnlocalizedName("stairsWood");
		AddonManager.SetVanillaBlockFinal("stairsWoodOak", Block.stairsWoodOak, stairsWoodOak);
		AddonManager.Name(stairsWoodOak, "Oak Stairs");
		Block stairsWoodSpruce = (new FCBlockStairsWood(AddonManager.ReplaceBlockID(Block.stairsWoodSpruce), Block.planks, 1)).setUnlocalizedName("stairsWoodSpruce");
		AddonManager.SetVanillaBlockFinal("stairsWoodSpruce", Block.stairsWoodSpruce, stairsWoodSpruce);
		AddonManager.Name(stairsWoodSpruce, "Spruce Stairs");
		Block stairsWoodBirch = (new FCBlockStairsWood(AddonManager.ReplaceBlockID(Block.stairsWoodBirch), Block.planks, 2)).setUnlocalizedName("stairsWoodBirch");
		AddonManager.SetVanillaBlockFinal("stairsWoodBirch", Block.stairsWoodBirch, stairsWoodBirch);
		AddonManager.Name(stairsWoodBirch, "Birch Stairs");
		Block stairsWoodJungle = (new FCBlockStairsWood(AddonManager.ReplaceBlockID(Block.stairsWoodJungle), Block.planks, 3)).setUnlocalizedName("stairsWoodJungle");
		AddonManager.SetVanillaBlockFinal("stairsWoodJungle", Block.stairsWoodJungle, stairsWoodJungle);
		AddonManager.Name(stairsWoodJungle, "Jungle Stairs");
		FCBetterThanWolves.fcBlockWoodBloodStairs = (new FCBlockStairsWood(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodBloodStairs), Block.planks, 4)).setUnlocalizedName("fcBlockWoodBloodStairs");
		AddonManager.Name(FCBetterThanWolves.fcBlockWoodBloodStairs, "Blood Wood Stairs");
		cherryStairs = new FCBlockStairsWood(id_cherryStairs, Block.planks, 5).setUnlocalizedName("cherryStairs");
		AddonManager.Register(cherryStairs, "Cherry Stairs");

		//Wood Sub Replace
		Block fence = new AddonBlockFenceWood(AddonManager.ReplaceBlockID(Block.fence));
		AddonManager.SetVanillaBlockFinal("fence", Block.fence, fence);
		AddonManager.Name(Block.fence, "Oak Fence");
		FCBetterThanWolves.fcBlockWoodOakSidingAndCorner = new AddonBlockWoodSidingAndCornerAndDecorative(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodOakSidingAndCorner),"FCBlockDecorativeWoodOak", "fcWoodOakSiding");
		FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner = new AddonBlockWoodSidingAndCornerAndDecorative(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner),"fcBlockDecorativeWoodSpruce", "fcWoodSpruceSiding").setUnlocalizedName("fcBlockSpruceSiding");
		FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner = new AddonBlockWoodSidingAndCornerAndDecorative(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner),"fcBlockDecorativeWoodBirch", "fcWoodBirchSiding").setUnlocalizedName("fcBlockBirchSiding");
		FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner = new AddonBlockWoodSidingAndCornerAndDecorative(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner),"fcBlockDecorativeWoodJungle", "fcWoodJungleSiding").setUnlocalizedName("fcBlockJungleSiding");
		FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner = new AddonBlockWoodSidingAndCornerAndDecorative(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner),"fcBlockDecorativeWoodBlood", "fcWoodBloodSiding").setUnlocalizedName("fcBlockBloodSiding");
		cherrySidingAndCorner = new AddonBlockWoodSidingAndCornerAndDecorative(id_cherrySidingAndCorner, "ginger_planks_cherryDecorative", "cherrySiding").setUnlocalizedName("cherrySiding");

		FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative = new AddonBlockWoodMouldingAndDecorative(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative), "FCBlockDecorativeWoodOak", "fcBlockColumnWoodOak_side", FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID, "fcBlockWoodOakMoulding");
		FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative = new AddonBlockWoodMouldingAndDecorative(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative), "fcBlockDecorativeWoodSpruce", "fcBlockColumnWoodSpruce_side", FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, "fcWoodSpruceMoulding");
		FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative = new AddonBlockWoodMouldingAndDecorative(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative), "fcBlockDecorativeWoodBirch", "fcBlockColumnWoodBirch_side", FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID, "fcWoodBirchMoulding");
		FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative = new AddonBlockWoodMouldingAndDecorative(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative), "fcBlockDecorativeWoodJungle", "fcBlockColumnWoodJungle_side", FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID, "fcWoodJungleMoulding");
		FCBetterThanWolves.fcBlockWoodBloodMouldingAndDecorative = new AddonBlockWoodMouldingAndDecorative(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodBloodMouldingAndDecorative), "fcBlockDecorativeWoodBlood", "fcBlockColumnWoodBlood_side", FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner.blockID, "fcWoodBloodMoulding");
		cherryMouldingAndDecorative = new AddonBlockWoodMouldingAndDecorative(id_cherryMouldingAndDecorative, "ginger_planks_cherryDecorative", "ginger_planks_cherryDecorative_column", cherrySidingAndCorner.blockID, "cherryMoulding").setUnlocalizedName("cherryMoulding");

		Item.itemsList[FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID] = new AddonItemBlockWoodSidingStub(FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID - 256);
		Item.itemsList[FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID] = new AddonItemBlockWoodMouldingStub(FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID - 256);
		Item.itemsList[FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID] = new AddonItemBlockWoodCornerStub(FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID - 256);
		Item.itemsList[FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative.blockID] = new AddonItemBlockWoodMouldingDecorativeStub(FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative.blockID - 256);
		Item.itemsList[FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID] = new AddonItemBlockWoodSidingDecorativeStub(FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID - 256);

		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0), "Oak Siding");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1), "Spruce Siding");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2), "Birch Siding");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3), "Jungle Siding");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 4), "Blood Wood Siding");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 5), "Cherry Siding");

		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0), "Oak Moulding");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 1), "Spruce Moulding");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 2), "Birch Moulding");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 3), "Jungle Moulding");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 4), "Blood Wood Moulding");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 5), "Cherry Moulding");

		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, 0), "Oak Corner");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, 1), "Spruce Corner");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, 2), "Birch Corner");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, 3), "Jungle Corner");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, 4), "Blood Wood Corner");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, 5), "Cherry Corner");

		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 0), "Oak Column");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 1), "Spruce Column");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 2), "Birch Column");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 3), "Jungle Column");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 4), "Oak Pedastal");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 5), "Spruce Pedastal");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 6), "Birch Pedastal");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 7), "Jungle Pedastal");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 8), "Oak Table");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 9), "Spruce Table");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 10), "Birch Table");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 11), "Jungle Table");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 16), "Blood Wood Column");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 17), "Cherry Column");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 20), "Blood Wood Pedastal");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 21), "Cherry Pedastal");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 24), "Blood Wood Table");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 25), "Cherry Table");

		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 1, 0), "Oak Bench");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 1, 1), "Spruce Bench");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 1, 2), "Birch Bench");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 1, 3), "Jungle Bench");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 1, 4), "Oak Fence");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 1, 5), "Spruce Fence");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 1, 6), "Birch Fence");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 1, 7), "Jungle Fence");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 1, 16), "Blood Wood Bench");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 1, 17), "Cherry Bench");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 1, 20), "Blood Wood Fence");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 1, 21), "Cherry Fence");

		//Ladders (used for block bounds change)
		FCBetterThanWolves.fcBlockLadder = new AddonBlockLadder(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockLadder));
		FCBetterThanWolves.fcBlockLadderOnFire = new AddonBlockLadderOnFire(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockLadderOnFire));

		//Trapdoors
		//Forces oak trap doors to inherit texture rotations and eventually better placement
		AddonManager.SetVanillaBlockFinal("trapdoor", Block.trapdoor, new AddonBlockTrapDoor(AddonManager.ReplaceBlockID(Block.trapdoor)));
		AddonManager.Name(Block.trapdoor, "Oak Trap Door");

		trapdoorSpruce = (BlockTrapDoor) new AddonBlockTrapDoor(id_trapdoorSpruce).setUnlocalizedName("ginger_trapdoorSpruce");
		trapdoorBirch = (BlockTrapDoor) new AddonBlockTrapDoor(id_trapdoorBirch).setUnlocalizedName("ginger_trapdoorBirch");
		trapdoorJungle = (BlockTrapDoor) new AddonBlockTrapDoor(id_trapdoorJungle).setUnlocalizedName("ginger_trapdoorJungle");
		trapdoorBlood = (BlockTrapDoor) new AddonBlockTrapDoor(id_trapdoorBlood).setUnlocalizedName("ginger_trapdoorBlood");
		trapdoorCherry = (BlockTrapDoor) new AddonBlockTrapDoor(id_trapdoorCherry).setUnlocalizedName("ginger_trapdoorCherry");

		AddonManager.Register(trapdoorSpruce, "Spruce Trap Door");
		AddonManager.Register(trapdoorBirch, "Birch Trap Door");
		AddonManager.Register(trapdoorJungle, "Jungle Trap Door");
		AddonManager.Register(trapdoorBlood, "Blood Wood Trap Door");
		AddonManager.Register(trapdoorCherry, "Cherry Trap Door");

		//Doors
		Item itemDoorOak = new AddonItemDoor(Item.doorWood.itemID - 256, "doorWood", "Oak Door", Block.doorWood.blockID, true);
		AddonManager.SetVanillaItemFinal("doorWood", Item.doorWood, itemDoorOak);
		itemDoorSpruce = (FCItemDoor) new AddonItemDoor(id_itemDoorSpruce, "ginger_doorSpruceItem", "Spruce Door", id_doorSpruce, true);
		itemDoorBirch = (FCItemDoor) new AddonItemDoor(id_itemDoorBirch, "ginger_doorBirchItem", "Birch Door", id_doorBirch, true);
		itemDoorJungle = (FCItemDoor) new AddonItemDoor(id_itemDoorJungle, "ginger_doorJungleItem", "Jungle Door", id_doorJungle, true);
		itemDoorBlood = (FCItemDoor) new AddonItemDoor(id_itemDoorBlood, "ginger_doorBloodItem", "Blood Wood Door", id_doorBlood, true);
		itemDoorCherry = (FCItemDoor) new AddonItemDoor(id_itemDoorCherry, "ginger_doorCherryItem", "Cherry Door", id_doorCherry, true);

		BlockDoor doorIron = (BlockDoor) new AddonBlockDoorIron(AddonManager.ReplaceBlockID(Block.doorIron)).setHardness(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("doorIron").disableStats();
		AddonManager.SetVanillaBlockFinal("doorIron", Block.doorIron, doorIron);

		Item itemDoorIron = new AddonItemDoor(Item.doorIron.itemID - 256, "doorIron", "Iron Door", Block.doorIron.blockID, false);
		AddonManager.SetVanillaItemFinal("doorIron", Item.doorIron, itemDoorIron);

		BlockDoor doorOak = new AddonBlockDoorWood(AddonManager.ReplaceBlockID(Block.doorWood), new String[] {"doorWood_lower", "doorWood_upper"}, Item.doorWood.itemID);
		AddonManager.SetVanillaBlockFinal("doorWood", Block.doorWood, doorOak);
		doorSpruce = new AddonBlockDoorWood(id_doorSpruce, new String[] {"ginger_doorSpruce_lower", "ginger_doorSpruce_upper"}, itemDoorSpruce.itemID);
		doorBirch = new AddonBlockDoorWood(id_doorBirch, new String[] {"ginger_doorBirch_lower", "ginger_doorBirch_upper"}, itemDoorBirch.itemID);
		doorJungle = new AddonBlockDoorWood(id_doorJungle, new String[] {"ginger_doorJungle_lower", "ginger_doorJungle_upper"}, itemDoorJungle.itemID);
		doorBlood = new AddonBlockDoorWood(id_doorBlood, new String[] {"ginger_doorBlood_lower", "ginger_doorBlood_upper"}, itemDoorBlood.itemID);
		doorCherry = new AddonBlockDoorWood(id_doorCherry, new String[] {"ginger_doorCherry_lower", "ginger_doorCherry_upper"}, itemDoorCherry.itemID);

		AddonManager.Register(doorSpruce, "Spruce Door");
		AddonManager.Register(doorBirch, "Birch Door");
		AddonManager.Register(doorJungle, "Jungle Door");
		AddonManager.Register(doorBlood, "Blood Wood Door");
		AddonManager.Register(doorCherry, "Cherry Door");

		//Fence gates
		BlockFenceGate gateOak = new AddonBlockFenceGate(AddonManager.ReplaceBlockID(Block.fenceGate), "wood");
		AddonManager.SetVanillaBlockFinal("fenceGate", Block.fenceGate, gateOak);
		AddonManager.Name(Block.fenceGate, "Oak Fence Gate");

		gateSpruce = (BlockFenceGate) new AddonBlockFenceGate(id_gateSpruce, "ginger_gateSpruce");
		gateBirch = (BlockFenceGate) new AddonBlockFenceGate(id_gateBirch, "ginger_gateBirch");
		gateJungle = (BlockFenceGate) new AddonBlockFenceGate(id_gateJungle, "ginger_gateJungle");
		gateBlood = (BlockFenceGate) new AddonBlockFenceGate(id_gateBlood, "ginger_gateBlood");
		gateCherry = (BlockFenceGate) new AddonBlockFenceGate(id_gateCherry, "ginger_gateCherry");

		AddonManager.Register(gateSpruce, "Spruce Fence Gate");
		AddonManager.Register(gateBirch, "Birch Fence Gate");
		AddonManager.Register(gateJungle, "Jungle Fence Gate");
		AddonManager.Register(gateBlood, "Blood Wood Fence Gate");
		AddonManager.Register(gateCherry, "Cherry Fence Gate");

		//Chairs
		oakWoodChair = new AddonBlockChairWood(id_oakWoodChair, "oak", "Oak");
		birchWoodChair = new AddonBlockChairWood(id_spruceWoodChair, "birch", "Birch");
		spruceWoodChair = new AddonBlockChairWood(id_birchWoodChair, "spruce", "Spruce");
		jungleWoodChair = new AddonBlockChairWood(id_jungleWoodChair, "jungle", "Jungle");
		bloodWoodChair = new AddonBlockChairWood(id_bloodWoodChair, "blood", "Blood Wood");
		cherryWoodChair = new AddonBlockChairWood(id_cherryWoodChair, "cherry", "Cherry");

		//Painted planks
		planksPainted = (new AddonBlockPlanksPainted(id_planksPainted, "ginger_planksPainted", "Painted Planks")).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(CreativeTabs.tabBlock);

		paintedPlanksSidingAndCorner = new Block[16];
		paintedPlanksMouldingAndDecorative = new Block[16];
		paintedPlanksStairs = new Block[16];

		//Sub blocks
		int start = id_paintedPlanksSubStart,
				end = start+48,
				id = start,
				i = 0;
		final String main = "Painted Planks";

		while(i < 16)
		{
			paintedPlanksSidingAndCorner[i] = new AddonBlockPlanksPaintedSidingAndCornerAndDecorative(id++, "ginger_planksPainted_"+i, "paintedPlanksSiding_"+i).setCreativeTab(CreativeTabs.tabDecorations);
			paintedPlanksMouldingAndDecorative[i] = new AddonBlockPlanksPaintedMouldingAndDecorative(id++, "ginger_planksPainted_"+i, "ginger_planksPainted_column_"+i, 3042, "paintedPlanksMoulding_"+i).setCreativeTab(CreativeTabs.tabDecorations);;
			paintedPlanksStairs[i] = new AddonBlockStairsPaintedPlanks(id++, planksPainted, i, i).setUnlocalizedName("stairsPaintedPlanks"+i);

			Item.itemsList[paintedPlanksSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(paintedPlanksSidingAndCorner[i].blockID - 256);
			Item.itemsList[paintedPlanksMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(paintedPlanksMouldingAndDecorative[i].blockID - 256);
			AddonManager.Register(paintedPlanksStairs[i], AddonBlockPlanksPainted.names[i]+" "+main+" Stairs");
			AddonManager.NameSubBlocks(paintedPlanksSidingAndCorner[i], paintedPlanksMouldingAndDecorative[i], AddonBlockPlanksPainted.names[i]+" "+main);

			i++;//i is metadata from original 16 color set
		}

		bottleHempOil = new Item(id_bottleHempOil).setUnlocalizedName("ginger_bottle_hempoil").setCreativeTab(CreativeTabs.tabMaterials).SetBuoyant();
		woodBleach = new Item(id_woodBleach).setUnlocalizedName("ginger_woodBleach").setCreativeTab(CreativeTabs.tabMaterials).SetBuoyant();
		AddonManager.Name(woodBleach, "Wood Bleach");
		woodStain = new Item(id_woodStain).setUnlocalizedName("ginger_woodStain").setCreativeTab(CreativeTabs.tabMaterials).SetBuoyant();
		AddonManager.Name(woodStain, "Wood Stain");

		//Pergola
		pergola = new AddonBlockPergola(id_pergola);
		AddonManager.Register(pergola, "Pergola");

		//Barrels
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 11), "Old Barrel");

		barrelEmpty = new AddonBlockBarrelEmpty(id_barrelEmpty, new String[] {"ginger_barrelOak_top", "ginger_barrelSpruce_top", "ginger_barrelBirch_top", "ginger_barrelJungle_top"}, new String[] {"ginger_barrelOak_side", "ginger_barrelSpruce_side", "ginger_barrelBirch_side", "ginger_barrelJungle_side"});
		barrelEmpty2 = new AddonBlockBarrelEmpty(id_barrelEmpty2, new String[] {"ginger_barrelBlood_top", "ginger_barrelCherry_top"}, new String[] {"ginger_barrelBlood_side", "ginger_barrelCherry_side"});
		AddonManager.Register(barrelEmpty, new String[] {"oakBarrel", "spruceBarrel", "birchBarrel", "jungleBarrel"}, new String[] {"Oak Barrel", "Spruce Barrel", "Birch Barrel", "Jungle Barrel"});
		AddonManager.Register(barrelEmpty2, new String[] {"bloodBarrel", "cherryBarrel"}, new String[] {"Blood Wood Barrel", "Cherry Barrel"});

		barrelFullOak = new AddonBlockBarrelFilled(id_barrelFullOak, "barrelOak", "Oak Barrel");
		barrelFullSpruce = new AddonBlockBarrelFilled(id_barrelFullSpruce, "barrelSpruce", "Spruce Barrel");
		barrelFullBirch = new AddonBlockBarrelFilled(id_barrelFullBirch, "barrelBirch", "Birch Barrel");
		barrelFullJungle = new AddonBlockBarrelFilled(id_barrelFullJungle, "barrelJungle", "Jungle Barrel");
		barrelFullBlood = new AddonBlockBarrelFilled(id_barrelFullBlood, "barrelBlood", "Blood Wood Barrel");
		barrelFullCherry = new AddonBlockBarrelFilled(id_barrelFullCherry, "barrelCherry", "Cherry Barrel");

		Item.itemsList[barrelFullOak.blockID] = new AddonItemBlockBarrelFilled(barrelFullOak.blockID - 256, barrelFullOak, new String[] {"barrelOak_wheat", "barrelOak_hemp", "barrelOak_potato", "barrelOak_carrot", "barrelOak_fish"}, "Oak Barrel");
		Item.itemsList[barrelFullSpruce.blockID] = new AddonItemBlockBarrelFilled(barrelFullSpruce.blockID - 256, barrelFullSpruce, new String[] {"barrelSpruce_wheat", "barrelSpruce_hemp", "barrelSpruce_potato", "barrelSpruce_carrot", "barrelSpruce_fish"}, "Spruce Barrel");
		Item.itemsList[barrelFullBirch.blockID] = new AddonItemBlockBarrelFilled(barrelFullBirch.blockID - 256, barrelFullBirch, new String[] {"barrelBirch_wheat", "barrelBirch_hemp", "barrelBirch_potato", "barrelBirch_carrot", "barrelBirch_fish"}, "Birch Barrel");
		Item.itemsList[barrelFullJungle.blockID] = new AddonItemBlockBarrelFilled(barrelFullJungle.blockID - 256, barrelFullJungle, new String[] {"barrelJungle_wheat", "barrelJungle_hemp", "barrelJungle_potato", "barrelJungle_carrot", "barrelJungle_fish"}, "Jungle Barrel");
		Item.itemsList[barrelFullBlood.blockID] = new AddonItemBlockBarrelFilled(barrelFullBlood.blockID - 256, barrelFullBlood, new String[] {"barrelBlood_wheat", "barrelBlood_hemp", "barrelBlood_potato", "barrelBlood_carrot", "barrelBlood_fish"}, "Blood Wood Barrel");
		Item.itemsList[barrelFullCherry.blockID] = new AddonItemBlockBarrelFilled(barrelFullCherry.blockID - 256, barrelFullCherry, new String[] {"barrelCherry_wheat", "barrelCherry_hemp", "barrelCherry_potato", "barrelCherry_carrot", "barrelCherry_fish"}, "Cherry Barrel");

		AddonItemBlockBarrelFilled[] barrelsFull = new AddonItemBlockBarrelFilled[6];
		barrelsFull[0] = (AddonItemBlockBarrelFilled) Item.itemsList[barrelFullOak.blockID];
		barrelsFull[1] = (AddonItemBlockBarrelFilled) Item.itemsList[barrelFullSpruce.blockID];
		barrelsFull[2] = (AddonItemBlockBarrelFilled) Item.itemsList[barrelFullBirch.blockID];
		barrelsFull[3] = (AddonItemBlockBarrelFilled) Item.itemsList[barrelFullJungle.blockID];
		barrelsFull[4] = (AddonItemBlockBarrelFilled) Item.itemsList[barrelFullBlood.blockID];
		barrelsFull[5] = (AddonItemBlockBarrelFilled) Item.itemsList[barrelFullCherry.blockID];

		for (int j = 0; j < AddonBlockBarrelFilled.types.length; j++) {
			AddonManager.Name(new ItemStack(barrelsFull[0], 1, j), "Oak Barrel of " + AddonBlockBarrelFilled.typeNames[j]);
			AddonManager.Name(new ItemStack(barrelsFull[1], 1, j), "Spruce Barrel of " + AddonBlockBarrelFilled.typeNames[j]);
			AddonManager.Name(new ItemStack(barrelsFull[2], 1, j), "Birch Barrel of " + AddonBlockBarrelFilled.typeNames[j]);
			AddonManager.Name(new ItemStack(barrelsFull[3], 1, j), "Jungle Barrel of " + AddonBlockBarrelFilled.typeNames[j]);
			AddonManager.Name(new ItemStack(barrelsFull[4], 1, j), "Blood Wood Barrel of " + AddonBlockBarrelFilled.typeNames[j]);
			AddonManager.Name(new ItemStack(barrelsFull[5], 1, j), "Cherry Barrel of " + AddonBlockBarrelFilled.typeNames[j]);
		}

		//Crates
		crate = new AddonBlockCrate(id_crate);
		AddonManager.Register(crate, new String[] {"crateOak", "crateSpruce", "crateBirch", "crateJungle", "crateBlood", "crateCherry"}, new String[] {"Oak Crate", "Spruce Crate", "Birch Crate", "Jungle Crate", "Blood Wood Crate", "Cherry Crate"});

		//Signs
		Block signPost = new AddonBlockSign(AddonManager.ReplaceBlockID(Block.signPost), true, 0, "wood");
		AddonManager.SetVanillaBlockFinal("signPost", Block.signPost, signPost);
		signSpruce = new AddonBlockSign(id_signSpruce, true, 1, "wood_spruce");
		signBirch = new AddonBlockSign(id_signBirch, true, 2, "wood_birch");
		signJungle = new AddonBlockSign(id_signJungle, true, 3, "wood_jungle");
		signBlood = new AddonBlockSign(id_signBlood, true, 4, "fcBlockPlanks_blood");
		signCherry = new AddonBlockSign(id_signCherry, true, 5, "ginger_planks_cherry");

		Block signWall = new AddonBlockSignWall(AddonManager.ReplaceBlockID(Block.signWall), 0, "wood");
		AddonManager.SetVanillaBlockFinal("signWall", Block.signWall, signWall);
		signSpruceWall = new AddonBlockSignWall(id_signSpruceWall, 1, "wood_spruce");
		signBirchWall = new AddonBlockSignWall(id_signBirchWall, 2, "wood_birch");
		signJungleWall = new AddonBlockSignWall(id_signJungleWall, 3, "wood_jungle");
		signBloodWall = new AddonBlockSignWall(id_signBloodWall, 4, "fcBlockPlanks_blood");
		signCherryWall = new AddonBlockSignWall(id_signCherryWall, 5, "ginger_planks_cherry");

		Item sign = new AddonItemSign(Item.sign.itemID - 256).SetBuoyant().SetIncineratedInCrucible().setUnlocalizedName("sign");
		AddonManager.SetVanillaItemFinal("sign", Item.sign, sign);
		AddonManager.Name(new ItemStack(Item.sign, 1, 0), "Oak Sign");
		AddonManager.Name(new ItemStack(Item.sign, 1, 1), "Spruce Sign");
		AddonManager.Name(new ItemStack(Item.sign, 1, 2), "Birch Sign");
		AddonManager.Name(new ItemStack(Item.sign, 1, 3), "Jungle Sign");
		AddonManager.Name(new ItemStack(Item.sign, 1, 4), "Blood Wood Sign");
		AddonManager.Name(new ItemStack(Item.sign, 1, 5), "Cherry Sign");

		//Slabs
		paintedPlanksSlab = new AddonBlockWoodSlab(id_paintedPlanksSlab, new Block[] {planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted}, new int[] {0, 1, 2, 3, 4, 5, 6, 7},
				new int[] {paintedPlanksMouldingAndDecorative[0].blockID, paintedPlanksMouldingAndDecorative[1].blockID, paintedPlanksMouldingAndDecorative[2].blockID, paintedPlanksMouldingAndDecorative[3].blockID, paintedPlanksMouldingAndDecorative[4].blockID, paintedPlanksMouldingAndDecorative[5].blockID, paintedPlanksMouldingAndDecorative[6].blockID, paintedPlanksMouldingAndDecorative[7].blockID},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0});
		Item.itemsList[AddonDefs.paintedPlanksSlab.blockID] = new AddonItemBlockSlab(AddonDefs.paintedPlanksSlab.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab, 1, 0), "Black Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab, 1, 1), "Red Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab, 1, 2), "Green Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab, 1, 3), "Brown Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab, 1, 4), "Blue Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab, 1, 5), "Purple Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab, 1, 6), "Cyan Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab, 1, 7), "Light Grey Painted Planks Slab");

		paintedPlanksSlab2 = new AddonBlockWoodSlab(id_paintedPlanksSlab2, new Block[] {planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted}, new int[] {8, 9, 10, 11, 12, 13, 14, 15},
				new int[] {paintedPlanksMouldingAndDecorative[8].blockID, paintedPlanksMouldingAndDecorative[9].blockID, paintedPlanksMouldingAndDecorative[10].blockID, paintedPlanksMouldingAndDecorative[11].blockID, paintedPlanksMouldingAndDecorative[12].blockID, paintedPlanksMouldingAndDecorative[13].blockID, paintedPlanksMouldingAndDecorative[14].blockID, paintedPlanksMouldingAndDecorative[15].blockID},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0});
		Item.itemsList[AddonDefs.paintedPlanksSlab2.blockID] = new AddonItemBlockSlab(AddonDefs.paintedPlanksSlab2.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab2, 1, 0), "Grey Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab2, 1, 1), "Pink Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab2, 1, 2), "Lime Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab2, 1, 3), "Yellow Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab2, 1, 4), "Light Blue Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab2, 1, 5), "Magenta Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab2, 1, 6), "Orange Painted Planks Slab");
		AddonManager.Name(new ItemStack(AddonDefs.paintedPlanksSlab2, 1, 7), "White Painted Planks Slab");

		woodSlab = new AddonBlockWoodSlab(id_woodSlab, new Block[] {Block.planks}, new int[] {5},
				new int[] {FCBetterThanWolves.fcBlockWoodMouldingItemStubID}, new int[] {5});
		Item.itemsList[AddonDefs.woodSlab.blockID] = new AddonItemBlockSlab(AddonDefs.woodSlab.blockID - 256);
		AddonManager.Name(new ItemStack(AddonDefs.woodSlab, 1, 0), "Cherry Slab");

		AddonManager.Name(new ItemStack(Block.woodSingleSlab, 1, 0), "Oak Slab");
		AddonManager.Name(new ItemStack(Block.woodSingleSlab, 1, 1), "Spruce Slab");
		AddonManager.Name(new ItemStack(Block.woodSingleSlab, 1, 2), "Birch Slab");
		AddonManager.Name(new ItemStack(Block.woodSingleSlab, 1, 3), "Jungle Slab");
		AddonManager.Name(new ItemStack(Block.woodSingleSlab, 1, 4), "Blood Wood Slab");
	}

	private void addDecoDefs() {
		//Diamondium
		blockDiamondium = new Block(id_blockDiamondium, Material.iron).setHardness(10.0F).setResistance(2000.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockDiamond").setCreativeTab(CreativeTabs.tabBlock);
		Block.blockDiamond.setUnlocalizedName("ginger_solid_diamond");
		FCTileEntityBeacon.AddBeaconEffect(blockDiamondium.blockID, FCBetterThanWolves.potionFortune.getId());

		AddonManager.Register(blockDiamondium, "Block of Diamondium");

		//Haybale and thatch
		hayBale = new AddonBlockHayBale(id_hayBale);
		hayBaleStairs = new AddonBlockStairsHay(id_hayBaleStairs);
		FCBetterThanWolves.fcItemWheat = new AddonItemWheat(FCBetterThanWolves.fcItemWheat.itemID - 256);
		thatch = new AddonBlockThatch(id_thatch);
		thatchStairs = new AddonBlockStairsThatch(id_thatchStairs);
		FCBetterThanWolves.fcItemStraw = new AddonItemStraw(FCBetterThanWolves.fcItemStraw.itemID - 256);

		//Lanterns
		paperWall = new AddonBlockPaperWall(id_paperWall);
		fenceSteel = new AddonBlockWroughtBars(id_fenceSteel);

		lanternPaper = new AddonBlockLantern(id_lanternPaper,Material.wood,.3F,"paper","Firefly Lantern",true);
		lanternPaper.SetAxesEffectiveOn(true);
		chandelier = new AddonBlockChandelier(id_chandelier);
		lanternSteel = new AddonBlockLantern(id_lanternSteel,Material.iron,.5F,"steel","Wrought Iron Lantern", false);
		lanternSteel.SetPicksEffectiveOn(true);

		AddonManager.Register(paperWall, "Paper Wall");
		AddonManager.Register(fenceSteel, "Wrought Iron Bars");
		AddonManager.Name(bottleHempOil, "Hemp Oil");

		//Workbench
		workbench = new AddonBlockWorkbench(id_workbench);
		AddonManager.Register(workbench, "Workbench");

		//Coarse Dirt
		coarseDirt = new AddonBlockDirtCoarse(id_coarseDirt);
		AddonManager.Register(coarseDirt, "Coarse Dirt");
		coarseDirtSlab = new AddonBlockDirtCoarseSlab(id_coarseDirtSlab);
		AddonManager.Register(coarseDirtSlab, "Coarse Dirt Slab");

		//Podzol
		podzol = new AddonBlockPodzol(id_podzol);
		AddonManager.Register(podzol, "Podzol");
		//podzolSlab = new AddonBlockDirtSlab(id_podzolSlab));

		//Dirt Replace
		Block grass = new AddonBlockGrass(AddonManager.ReplaceBlockID(Block.grass));
		AddonManager.SetVanillaBlockFinal("grass", Block.grass, grass);
		Block dirt = new AddonBlockDirt(AddonManager.ReplaceBlockID(Block.dirt));
		AddonManager.SetVanillaBlockFinal("dirt", Block.dirt, dirt);
		Block mycelium = new AddonBlockMycelium(AddonManager.ReplaceBlockID(Block.mycelium));
		AddonManager.SetVanillaBlockFinal("mycelium", Block.mycelium, mycelium);
		FCBetterThanWolves.fcBlockDirtLoose = new AddonBlockDirtLoose(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockDirtLoose));

		//Nether portal
		BlockPortal addonPortal = (BlockPortal) new AddonBlockPortal(AddonManager.ReplaceBlockID(Block.portal));
		AddonManager.SetVanillaBlockFinal("portal", Block.portal, addonPortal);

		//Pumpkins
		pumpkin = new AddonBlockPumpkinCarved(id_pumpkin);
		AddonManager.Register(pumpkin, new String[] {"pumpkinCarved1", "pumpkinCarved2", "pumpkinCarved3"}, new String[] {"Carved Pumpkin", "Carved Pumpkin", "Carved Pumpkin"});
		pumpkinLit = new AddonBlockPumpkinLit(id_pumpkinLit);
		AddonManager.Register(pumpkinLit, new String[] {"pumpkinLit1", "pumpkinLit2", "pumpkinLit3"}, new String[] {"Jack 'o' Lantern", "Jack 'o' Lantern", "Jack 'o' Lantern"});
		Item.itemsList[FCBetterThanWolves.fcBlockPumpkinFresh.blockID] = new AddonItemBlockPumpkinFresh(FCBetterThanWolves.fcBlockPumpkinFresh.blockID - 256);

		//Carpets
		carpet = new AddonBlockCarpet(id_carpet);
		AddonManager.Register(carpet, new String[] {"carpet_0", "carpet_1", "carpet_2", "carpet_3", "carpet_4", "carpet_5", "carpet_6", "carpet_7", "carpet_8", "carpet_9", "carpet_10", "carpet_11", "carpet_12", "carpet_13", "carpet_14", "carpet_15"}, 
				new String[] {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Grey", "Grey", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White"}, " Carpet");

		//Coal block
		Item coal = new AddonItemCoal(Item.coal.itemID - 256).SetIncineratedInCrucible().SetFurnaceBurnTime(FCEnumFurnaceBurnTime.COAL).SetFilterableProperties(2).setUnlocalizedName("coal");
		AddonManager.SetVanillaItemFinal("coal", Item.coal, coal);
		FCBetterThanWolves.fcItemNethercoal = new AddonItemNethercoal(FCBetterThanWolves.fcItemNethercoal.itemID - 256).SetFurnaceBurnTime(2 * FCEnumFurnaceBurnTime.COAL.m_iBurnTime).SetFilterableProperties(2).setUnlocalizedName("fcItemNethercoal").setCreativeTab(CreativeTabs.tabMaterials);

		coalBlock = new Block(id_coalBlock, Material.rock).setUnlocalizedName("ginger_coalBlock").SetPicksEffectiveOn().SetFireProperties(FCEnumFlammability.EXTREME).setHardness(1.5F).setResistance(10.0F).setCreativeTab(CreativeTabs.tabBlock);
		AddonManager.Register(coalBlock, "Block of Coal");
		netherCoalBlock = new AddonBlockNetherCoal(id_netherCoalBlock);
		AddonManager.Register(netherCoalBlock, "Block of Nethercoal");

		//Fire
		BlockFire fire = new AddonBlockFire(AddonManager.ReplaceBlockID(Block.fire));
		AddonManager.SetVanillaBlockFinal("fire", Block.fire, fire);
		FCBetterThanWolves.fcBlockFireStoked = new AddonBlockFireStoked(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockFireStoked));

		//Bone
		FCBetterThanWolves.fcAestheticOpaque = new AddonBlockAestheticOpaque(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcAestheticOpaque));
		Item.itemsList[FCBetterThanWolves.fcAestheticOpaque.blockID] = new AddonItemBlockAestheticOpaque(FCBetterThanWolves.fcAestheticOpaque.blockID - 256);
		bonePillar = new AddonBlockDirectional(id_bonePillar, FCBetterThanWolves.fcMaterialMiscellaneous, new String[] {"ginger_bonePillar_top"}, new String[] {"ginger_bonePillar_side"})
				.setHardness(2.0F)
				.SetPicksEffectiveOn()
				.SetBuoyancy(1.0F)
				.setStepSound(Block.soundStoneFootstep)
				.setCreativeTab(CreativeTabs.tabBlock)
				.setUnlocalizedName("bonePillar");
		Item.itemsList[bonePillar.blockID] = new AddonItemBlockWithCustomSound(bonePillar.blockID - 256);
		AddonManager.Name(bonePillar, "Bone Pillar");

		bonePillar.setStepSound(stepSoundBone);
		FCBetterThanWolves.fcBlockBoneSlab.setStepSound(stepSoundBone);

		//Ender Pearl
		Item enderPearl = new AddonItemEnderPearl(Item.enderPearl.itemID - 256).SetFilterableProperties(2).setUnlocalizedName("enderPearl");
		AddonManager.SetVanillaItemFinal("enderPearl", Item.enderPearl, enderPearl);

		//Buttons
		Block woodButton = new AddonBlockButtonWood(AddonManager.ReplaceBlockID(Block.woodenButton), Block.planks, 0).setHardness(0.5F).setStepSound(Block.soundWoodFootstep);
		AddonManager.SetVanillaBlockFinal("woodenButton", Block.woodenButton, woodButton);
		Block stoneButton = new AddonBlockButtonStone(AddonManager.ReplaceBlockID(Block.stoneButton), Block.stone, 0).setHardness(0.5F).setStepSound(Block.soundStoneFootstep);
		AddonManager.SetVanillaBlockFinal("stoneButton", Block.stoneButton, stoneButton);

		buttonSpruce = new AddonBlockButtonWood(id_buttonSpruce, Block.planks, 1).setHardness(0.5F).setStepSound(Block.soundWoodFootstep);
		buttonBirch = new AddonBlockButtonWood(id_buttonBirch, Block.planks, 2).setHardness(0.5F).setStepSound(Block.soundWoodFootstep);
		buttonJungle = new AddonBlockButtonWood(id_buttonJungle, Block.planks, 3).setHardness(0.5F).setStepSound(Block.soundWoodFootstep);
		buttonBlood = new AddonBlockButtonWood(id_buttonBlood, Block.planks, 4).setHardness(0.5F).setStepSound(Block.soundWoodFootstep);
		buttonCherry = new AddonBlockButtonWood(id_buttonCherry, Block.planks, 5).setHardness(0.5F).setStepSound(Block.soundWoodFootstep);

		buttonInfusedStone = new AddonBlockButtonStone(id_buttonInfusedStone, infusedStone, 0).setHardness(0.5F).setStepSound(Block.soundStoneFootstep);
		buttonGranite = new AddonBlockButtonStone(id_buttonGranite, stoneTypes, 0).setHardness(0.5F).setStepSound(Block.soundStoneFootstep);
		buttonAndesite = new AddonBlockButtonStone(id_buttonAndesite, stoneTypes, 1).setHardness(0.5F).setStepSound(Block.soundStoneFootstep);
		buttonDiorite = new AddonBlockButtonStone(id_buttonDiorite, stoneTypes, 2).setHardness(0.5F).setStepSound(Block.soundStoneFootstep);
		buttonSandstone = new AddonBlockButtonStone(id_buttonSandstone, Block.sandStone, 3).setHardness(0.5F).setStepSound(Block.soundStoneFootstep);
		buttonRedSandstone = new AddonBlockButtonStone(id_buttonRedSandstone, redSandStone, 3).setHardness(0.5F).setStepSound(Block.soundStoneFootstep);

		AddonManager.Name(Block.stoneButton, "Stone Button");
		AddonManager.Name(Block.woodenButton, "Oak Button");

		AddonManager.Register(buttonSpruce, "Spruce Button");
		AddonManager.Register(buttonBirch, "Birch Button");
		AddonManager.Register(buttonJungle, "Jungle Button");
		AddonManager.Register(buttonBlood, "Blood Wood Button");
		AddonManager.Register(buttonCherry, "Cherry Button");

		AddonManager.Register(buttonInfusedStone, "Infused Stone Button");
		AddonManager.Register(buttonGranite, "Granite Button");
		AddonManager.Register(buttonAndesite, "AndesiteButton");
		AddonManager.Register(buttonDiorite, "Diorite Button");
		AddonManager.Register(buttonSandstone, "Sandstone Button");
		AddonManager.Register(buttonRedSandstone, "Red Sandstone Button");

		//Cherry Tree
		cherrySapling = new AddonBlockSaplingCherry(id_cherrySapling);
		AddonManager.Register(cherrySapling, "Cherry Sapling");
		cherryLeaves = new AddonBlockLeavesCherry(id_cherryLeaves);
		AddonManager.Register(cherryLeaves, "Cherry Leaves");

		//Leaves, vines, and webs
		Block leaves = new AddonBlockLeaves(AddonManager.ReplaceBlockID(Block.leaves));
		AddonManager.SetVanillaBlockFinal("leaves", Block.leaves, leaves);
		Block vine = new AddonBlockVine(AddonManager.ReplaceBlockID(Block.vine));
		AddonManager.SetVanillaBlockFinal("vine", Block.vine, vine);
		Item.itemsList[Block.vine.blockID] = new ItemColored(Block.vine.blockID - 256, false);
		FCBetterThanWolves.fcBlockBloodLeaves = new AddonBlockLeavesBlood(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockBloodLeaves));
		FCBetterThanWolves.fcBlockWeb = new AddonBlockWeb(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWeb));

		//Hedge
		hedge = new AddonBlockHedge(id_hedge);
		AddonManager.Register(hedge, new String[] {"oakLeavesDeco", "spruceLeavesDeco", "birchLeavesDeco", "jungleLeavesDeco"}, new String[] {"Oak Hedge", "Spruce Hedge", "Birch Hedge", "Jungle Hedge"});

		hedgeOakStairs = new AddonBlockStairsHedge(id_hedgeOakStairs, hedge, 0, true).setUnlocalizedName("hedgeOakStairs");
		hedgeSpruceStairs = new AddonBlockStairsHedge(id_hedgeSpruceStairs, hedge, 1, true).setUnlocalizedName("hedgeSpruceStairs");
		hedgeBirchStairs = new AddonBlockStairsHedge(id_hedgeBirchStairs, hedge, 2, true).setUnlocalizedName("hedgeBirchStairs");
		hedgeJungleStairs = new AddonBlockStairsHedge(id_hedgeJungleStairs, hedge, 3, true).setUnlocalizedName("hedgeJungleStairs");
		hedgeBloodStairs = new AddonBlockStairsHedge(id_hedgeBloodStairs, FCBetterThanWolves.fcBlockBloodLeaves, 0, true).setUnlocalizedName("hedgeBloodStairs");
		hedgeCherryStairs = new AddonBlockStairsHedge(id_hedgeCherryStairs, cherryLeaves, 0, false).setUnlocalizedName("hedgeCherryStairs");

		hedgeOakSidingAndCorner = new AddonBlockHedgeSidingAndCornerDecorativeWall(id_hedgeOakSidingandCorner, materialHedge, "leaves", 0.2F, 0.2F, Block.soundGrassFootstep, "hedgeOakSiding", "Oak Hedge", true, hedge, 0);
		hedgeOakMouldingAndDecorative = new AddonBlockHedgeMouldingAndDecorative(id_hedgeOakMoulingAndDecorative, materialHedge, "leaves", "leaves", 3042, 0.2F, 0.2F, Block.soundGrassFootstep, "hedgeOakMoulding", true, hedge, 0);
		hedgeSpruceSidingAndCorner = new AddonBlockHedgeSidingAndCornerDecorativeWall(id_hedgeSpruceSidingAndCorner, materialHedge, "leaves", 0.2F, 0.2F, Block.soundGrassFootstep, "hedgeSpruceSiding", "Spruce Hedge", true, hedge, 1);
		hedgeSpruceMouldingAndDecorative = new AddonBlockHedgeMouldingAndDecorative(id_hedgeSpruceMouldingAndDecorative, materialHedge, "leaves", "leaves", 3042, 0.2F, 0.2F, Block.soundGrassFootstep, "hedgeSpruceMoulding", true, hedge, 1);
		hedgeBirchSidingAndCorner = new AddonBlockHedgeSidingAndCornerDecorativeWall(id_hedgeBirchSidingAndCorner, materialHedge, "leaves_spruce", 0.2F, 0.2F, Block.soundGrassFootstep, "hedgeBirchSiding", "Birch Hedge", true, hedge, 2);
		hedgeBirchMouldingAndDecorative = new AddonBlockHedgeMouldingAndDecorative(id_hedgeBirchMouldingAndDecorative, materialHedge, "leaves_spruce", "leaves_spruce", 3042, 0.2F, 0.2F, Block.soundGrassFootstep, "hedgeBirchMoulding", true, hedge, 2);
		hedgeJungleSidingAndCorner = new AddonBlockHedgeSidingAndCornerDecorativeWall(id_hedgeJungleSidingAndCorner, materialHedge, "leaves_jungle", 0.2F, 0.2F, Block.soundGrassFootstep, "hedgeJungleSiding", "Jungle Hedge", true, hedge, 3);
		hedgeJungleMouldingAndDecorative = new AddonBlockHedgeMouldingAndDecorative(id_hedgeJungleMouldingAndDecorative, materialHedge, "leaves_jungle", "leaves_jungle", 3042, 0.2F, 0.2F, Block.soundGrassFootstep, "hedgeJungleMoulding", true, hedge, 3);
		hedgeBloodSidingAndCorner = new AddonBlockHedgeSidingAndCornerDecorativeWall(id_hedgeBloodSidingAndCorner, materialHedge, "leaves", 0.2F, 0.2F, Block.soundGrassFootstep, "hedgeBloodSiding", "Blood Hedge", true, FCBetterThanWolves.fcBlockBloodLeaves, 0);
		hedgeBloodMouldingAndDecorative = new AddonBlockHedgeMouldingAndDecorative(id_hedgeBloodMouldingAndDecorative, materialHedge, "leaves", "leaves", 3042, 0.2F, 0.2F, Block.soundGrassFootstep, "hedgeBloodMoulding", true, FCBetterThanWolves.fcBlockBloodLeaves, 0);
		hedgeCherrySidingAndCorner = new AddonBlockHedgeSidingAndCornerDecorativeWall(id_hedgeCherrySidingAndCorner, materialHedge, "ginger_leavesCherry", 0.2F, 0.2F, Block.soundGrassFootstep, "hedgeCherrySiding", "Cherry Hedge", false, cherryLeaves, 0);
		hedgeCherryMouldingAndDecorative = new AddonBlockHedgeMouldingAndDecorative(id_hedgeCherryMouldingAndDecorative, materialHedge, "ginger_leavesCherry", "ginger_leavesCherry", 3042, 0.2F, 0.2F, Block.soundGrassFootstep, "hedgeCherryMoulding", false, cherryLeaves, 0);

		AddonManager.Register(hedgeOakStairs, "Oak Hedge Stairs");
		AddonManager.Register(hedgeSpruceStairs, "Spruce Hedge Stairs");
		AddonManager.Register(hedgeBirchStairs, "Birch Hedge Stairs");
		AddonManager.Register(hedgeJungleStairs, "Jungle Hedge Stairs");
		AddonManager.Register(hedgeBloodStairs, "Blood Wood Hedge Stairs");
		AddonManager.Register(hedgeCherryStairs, "Cherry Hedge Stairs");

		Item.itemsList[hedgeOakSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(hedgeOakSidingAndCorner.blockID - 256);
		Item.itemsList[hedgeOakMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(hedgeOakMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(hedgeOakSidingAndCorner, hedgeOakMouldingAndDecorative, "Oak Hedge");
		Item.itemsList[hedgeSpruceSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(hedgeSpruceSidingAndCorner.blockID - 256);
		Item.itemsList[hedgeSpruceMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(hedgeSpruceMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(hedgeSpruceSidingAndCorner, hedgeSpruceMouldingAndDecorative, "Spruce Hedge");
		Item.itemsList[hedgeBirchSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(hedgeBirchSidingAndCorner.blockID - 256);
		Item.itemsList[hedgeBirchMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(hedgeBirchMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(hedgeBirchSidingAndCorner, hedgeBirchMouldingAndDecorative, "Birch Hedge");
		Item.itemsList[hedgeJungleSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(hedgeJungleSidingAndCorner.blockID - 256);
		Item.itemsList[hedgeJungleMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(hedgeJungleMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(hedgeJungleSidingAndCorner, hedgeJungleMouldingAndDecorative, "Jungle Hedge");
		Item.itemsList[hedgeBloodSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(hedgeBloodSidingAndCorner.blockID - 256);
		Item.itemsList[hedgeBloodMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(hedgeBloodMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(hedgeBloodSidingAndCorner, hedgeBloodMouldingAndDecorative, "Blood Wood Hedge");
		Item.itemsList[hedgeCherrySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(hedgeCherrySidingAndCorner.blockID - 256);
		Item.itemsList[hedgeCherryMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(hedgeCherryMouldingAndDecorative.blockID - 256);
		AddonManager.NameSubBlocks_Wall(hedgeCherrySidingAndCorner, hedgeCherryMouldingAndDecorative, "Cherry Hedge");

		//Panes replace
		Block thinGlass = new AddonBlockPane(AddonManager.ReplaceBlockID(Block.thinGlass), "glass", "thinglass_top", Material.glass, false).setHardness(0.3F).SetPicksEffectiveOn().setStepSound(Block.soundGlassFootstep).setUnlocalizedName("thinGlass");
		AddonManager.SetVanillaBlockFinal("thinGlass", Block.thinGlass, thinGlass);
		Block fenceIron = new AddonBlockIronBars(AddonManager.ReplaceBlockID(Block.fenceIron));
		AddonManager.SetVanillaBlockFinal("fenceIron", Block.fenceIron, fenceIron);
		FCBetterThanWolves.fcBlockGrate = new AddonBlockGrate(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockGrate));
		FCBetterThanWolves.fcBlockWickerPane = new AddonBlockWickerPane(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWickerPane));
		FCBetterThanWolves.fcBlockSlats = new AddonBlockSlats(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockSlats));

		//Rope
		ropeCoil = new AddonBlockDirectional(id_ropeCoil, FCBetterThanWolves.fcMaterialMiscellaneous, new String[] {"fcBlockRope_top"}, new String[] {"fcBlockRope_side"})
				.setHardness(2.0F)
				.SetAxesEffectiveOn(true)
				.setStepSound(Block.soundGrassFootstep)
				.setCreativeTab(CreativeTabs.tabBlock)
				.setUnlocalizedName("ropeCoil");
		AddonManager.Register(ropeCoil, "Coil of Rope");
		AddonManager.Name(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 6), "Old Coil of Rope");

		//Chain
		chain = new AddonBlockChain(id_chain);
		AddonManager.Register(chain, "Chain");
		chainItem = new AddonItemChain(id_chainItem);
		AddonManager.Name(chainItem, "Chain");
		chainCoil = new AddonBlockDirectional(id_chainCoil, Material.iron, new String[] {"ginger_chainCoil_top"}, new String[] {"ginger_chainCoil_side"})
				.setHardness(2.0F)
				.SetPicksEffectiveOn(true)
				.setStepSound(Block.soundMetalFootstep)
				.setCreativeTab(CreativeTabs.tabBlock)
				.setUnlocalizedName("chainCoil");
		Item.itemsList[chainCoil.blockID] = new AddonItemBlockWithCustomSound(chainCoil.blockID - 256);
		AddonManager.Name(chainCoil, "Coil of Chain");

		//Cocoa
		FCBetterThanWolves.fcItemCocoaBeans = new AddonItemCocoaBeans(FCBetterThanWolves.fcItemCocoaBeans.itemID - 256);
		Block cocoaPlant = new AddonBlockCocoa(AddonManager.ReplaceBlockID(Block.cocoaPlant)).setHardness(0.2F).setResistance(5.0F).SetBuoyant().setStepSound(Block.soundWoodFootstep).setUnlocalizedName("cocoa");;
		AddonManager.SetVanillaBlockFinal("cocoaPlant", Block.cocoaPlant, cocoaPlant);

		//Hemp
		FCBetterThanWolves.fcBlockHempCrop = new AddonBlockHempCrop(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockHempCrop));

		//Lily pads
		Item.itemsList[Block.waterlily.blockID] = new AddonItemBlockLilyPad(Block.waterlily.blockID - 256);

		//Tall grass
		Block tallGrass = new AddonBlockTallGrass(AddonManager.ReplaceBlockID(Block.tallGrass));
		AddonManager.SetVanillaBlockFinal("tallGrass", Block.tallGrass, tallGrass);
		Item.itemsList[tallGrass.blockID] = (new ItemColored(tallGrass.blockID - 256, true)).setBlockNames(new String[] {"shrub", "grass", "fern"});

		//Fluids
		BlockFluid waterStill = (BlockFluid) new AddonBlockWaterStationary(AddonManager.ReplaceBlockID(Block.waterStill), Material.water).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats();
		AddonManager.SetVanillaBlockFinal("waterStill", Block.waterStill, waterStill);
		BlockFluid waterMoving = (BlockFluid) new AddonBlockWaterFlowing(AddonManager.ReplaceBlockID(Block.waterMoving), Material.water).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats();
		AddonManager.SetVanillaBlockFinal("waterMoving", Block.waterMoving, waterMoving);
		BlockFluid lavaStill = (BlockFluid) new AddonBlockLavaStationary(AddonManager.ReplaceBlockID(Block.lavaStill), Material.lava).setHardness(100.0F).setLightValue(1.0F).setUnlocalizedName("lava").disableStats();
		AddonManager.SetVanillaBlockFinal("lavaStill", Block.lavaStill, lavaStill);
		BlockFluid lavaMoving = (BlockFluid) new AddonBlockLavaFlowing(AddonManager.ReplaceBlockID(Block.lavaMoving), Material.lava).setHardness(100.0F).setLightValue(1.0F).setUnlocalizedName("lava").disableStats();
		AddonManager.SetVanillaBlockFinal("lavaMoving", Block.lavaMoving, lavaMoving);

		//Extra sounds
		Block.slowSand.setStepSound(stepSoundSoulSand);
		Item.itemsList[Block.slowSand.blockID] = new AddonItemBlockWithCustomSound(Block.slowSand.blockID - 256);
		Block.vine.setStepSound(stepSoundVine);
		FCBetterThanWolves.fcSoulforgedSteelBlock.setStepSound(stepSoundSteel);
		Item.itemsList[FCBetterThanWolves.fcSoulforgedSteelBlock.blockID] = new AddonItemBlockWithCustomSound(FCBetterThanWolves.fcSoulforgedSteelBlock.blockID - 256);
		FCBetterThanWolves.fcAnvil.setStepSound(stepSoundSteel);
		Item.itemsList[FCBetterThanWolves.fcAnvil.blockID] = new AddonItemBlockWithCustomSound(FCBetterThanWolves.fcAnvil.blockID - 256);
		FCBetterThanWolves.fcBlockBloodMoss.setStepSound(stepSoundGroth);
		FCBetterThanWolves.fcItemBloodMossSpores = new AddonItemBloodMossSpores(FCBetterThanWolves.fcItemBloodMossSpores.itemID - 256);
		chainCoil.setStepSound(stepSoundChainDeep);

		//Scaffolding
		//scaffolding = new AddonBlockScaffolding(id_scaffolding);
		//AddonManager.Register(scaffolding, "Scaffolding");
	}

	private void addToolDefs() {
		chiselDiamond = new AddonItemChiselDiamond(id_chiselDiamond);
		AddonManager.Name(chiselDiamond, "Diamondium Chisel");
		FCBetterThanWolves.fcItemChiselIron = new AddonItemChiselIron(FCBetterThanWolves.fcItemChiselIron.itemID - 256);

		AddonManager.Name(Block.blockDiamond, "Block of Diamond");
		AddonManager.Name(FCBetterThanWolves.fcItemIngotDiamond, "Diamondium Ingot");
		AddonManager.Name(Item.pickaxeDiamond, "Diamondium Pickaxe");
		AddonManager.Name(Item.axeDiamond, "Diamondium Axe");
		AddonManager.Name(Item.shovelDiamond, "Diamondium Shovel");
		AddonManager.Name(Item.hoeDiamond, "Diamondium Hoe");
		AddonManager.Name(Item.swordDiamond, "Diamondium Sword");
		AddonManager.Name(Item.helmetDiamond, "Diamondium Helmet");
		AddonManager.Name(Item.plateDiamond, "Diamondium Chestplate");
		AddonManager.Name(Item.legsDiamond, "Diamondium Leggings");
		AddonManager.Name(Item.bootsDiamond,  "Diamondium Boots");

		//Allows custom saw recipes
		FCBetterThanWolves.fcSaw = new AddonBlockSaw((FCBlockSaw) FCBetterThanWolves.fcSaw, AddonManager.ReplaceBlockID(FCBetterThanWolves.fcSaw));

		//Hopper
		//FCBetterThanWolves.fcHopper = new AddonBlockHopper(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcHopper));
		//TileEntity.ReplaceVanillaMapping(FCTileEntityHopper.class, AddonTileEntityHopper.class, "Hopper");

		//Shears - for added efficient blocks
		Item shears = new AddonItemShears(Item.shears.itemID - 256).setUnlocalizedName("shears");
		AddonManager.SetVanillaItemFinal("shears", Item.shears, shears);

		shearsDiamond = (AddonItemShearsDiamond) new AddonItemShearsDiamond(id_shearsDiamond).setUnlocalizedName("ginger_shearsDiamond");
		AddonManager.Name(shearsDiamond, "Diamondium Shears");

		//Name Tags
		nameTag = new AddonItemNameTag(id_nameTag);
		AddonManager.Name(nameTag, "Name Tag");
	}

	private void addlayerDefs() {
		//public static Block layerDirt, layerGrass, layerGravel, layerSand, layerRedSand, layerCoarseDirt, layerPodzol, layerPackedEarth;
		layerDirt = new AddonBlockLayer(id_layerDirt, Block.dirt).SetShovelsEffectiveOn().setUnlocalizedName("dirtLayer");
		layerGrass = new AddonBlockLayer(id_layerGrass, Block.grass).SetShovelsEffectiveOn().setUnlocalizedName("grassLayer");
		layerGravel = new AddonBlockLayer(id_layerGravel, Block.gravel).SetShovelsEffectiveOn().setUnlocalizedName("gravelLayer");
		layerSand = new AddonBlockLayer(id_layerSand, Block.sand).SetShovelsEffectiveOn().setUnlocalizedName("sandLayer");
		layerRedSand = new AddonBlockLayer(id_layerRedSand, redSand).SetShovelsEffectiveOn().setUnlocalizedName("redSandLayer");
		layerCoarseDirt = new AddonBlockLayer(id_layerCoarseDirt, coarseDirt).SetShovelsEffectiveOn().setUnlocalizedName("coarseDirtLayer");
		layerPodzol = new AddonBlockLayer(id_layerPodzol, podzol).SetShovelsEffectiveOn().setUnlocalizedName("podzolLayer");
		layerPackedEarth = new AddonBlockLayer(id_layerPackedEarth, FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 6).SetShovelsEffectiveOn().setUnlocalizedName("packedEarthLayer");
		layerDirtLoose = new AddonBlockLayer(id_layerDirtLoose, FCBetterThanWolves.fcBlockDirtLoose).SetShovelsEffectiveOn().setUnlocalizedName("looseDirtLayer");
		/*
		Item.itemsList[layerDirt.blockID] = new AddonItemBlockLayer(layerDirt.blockID - 256);
		Item.itemsList[layerGrass.blockID] = new AddonItemBlockLayer(layerGrass.blockID - 256);
		Item.itemsList[layerGravel.blockID] = new AddonItemBlockLayer(layerGravel.blockID - 256);
		Item.itemsList[layerSand.blockID] = new AddonItemBlockLayer(layerSand.blockID - 256);
		Item.itemsList[layerRedSand.blockID] = new AddonItemBlockLayer(layerRedSand.blockID - 256);
		Item.itemsList[layerCoarseDirt.blockID] = new AddonItemBlockLayer(layerCoarseDirt.blockID - 256);
		Item.itemsList[layerPodzol.blockID] = new AddonItemBlockLayer(layerPodzol.blockID - 256);
		Item.itemsList[layerPackedEarth.blockID] = new AddonItemBlockLayer(layerPackedEarth.blockID - 256);
		Item.itemsList[layerDirtLoose.blockID] = new AddonItemBlockLayer(layerDirtLoose.blockID - 256);
		 */
		AddonManager.Name(layerDirt, "Dirt Layer");
		//AddonManager.Name(layerGrass, "Grass Layer");
		AddonManager.Name(layerGravel, "Gravel Layer");
		AddonManager.Name(layerSand, "Sand Layer");
		AddonManager.Name(layerRedSand, "Red Sand Layer");
		AddonManager.Name(layerCoarseDirt, "Coarse Dirt Layer");
		//AddonManager.Name(layerPodzol, "Podzol Layer");
		AddonManager.Name(layerPackedEarth, "Packed Earth Layer");
		AddonManager.Name(layerDirtLoose, "Loose Dirt Layer");
	}

	private void addSubBlockReplaceDefs() {
		//Walls
		FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner),  Material.rock, "fcBlockDecorativeStoneBrick", 1.5F, 10.0F, Block.soundStoneFootstep, "fcStoneBrickSiding", "Stone Brick").SetPicksEffectiveOn();
		FCBetterThanWolves.fcBlockBrickSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockBrickSidingAndCorner), Material.rock, "fcBlockDecorativeBrick", 2.0F, 10.0F, Block.soundStoneFootstep, "fcBrickSiding","Brick").SetPicksEffectiveOn();
		FCBetterThanWolves.fcBlockSandstoneSidingAndCorner = new AddonBlockSandStoneSidingAndCornerDecorativeWall(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockSandstoneSidingAndCorner), new String[] {"fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_side"}, "fcSandstoneSiding", "Sandstone");
		FCBetterThanWolves.fcBlockWhiteStoneSidingAndCorner = new AddonBlockSidingAndCornerDecorativeWall(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWhiteStoneSidingAndCorner),  Material.rock, "fcBlockDecorativeWhiteStone", 1.5F, 10.0F, Block.soundStoneFootstep, "fcWhiteStoneSiding", "White Stone").SetPicksEffectiveOn();
		FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner = (new AddonBlockSidingAndCornerDecorativeWall(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner), Material.rock, "fcBlockDecorativeStone", 1.5F, 10.0F, Block.soundStoneFootstep, "fcStoneSiding", "Stone")).SetPicksEffectiveOn();

		Block netherFence = new AddonBlockFence(AddonManager.ReplaceBlockID(Block.netherFence), "netherBrick", FCBetterThanWolves.fcMaterialNetherRock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("netherFence");
		AddonManager.SetVanillaBlockFinal("netherFence", Block.netherFence, netherFence);

		Block wall = (new AddonBlockWall(AddonManager.ReplaceBlockID(Block.cobblestoneWall), Block.cobblestone)).setUnlocalizedName("cobbleWall");
		AddonManager.SetVanillaBlockFinal("cobblestoneWall", Block.cobblestoneWall, wall);
	}

	private void addExtraSubBlockDefs() {
		//Edging
		//stoneBrickEdging = new AddonBlockEdging(id_stoneBrickEdging, Block.stoneBrick, 0).setUnlocalizedName("stoneBrickEdging");
		//AddonManager.Register(stoneBrickEdging, "Stone Brick Edging");
	}

	private void addEntityDefs() {
		//Vanilla entities
		AddonManager.ReplaceSpawnableEntity("Squid", FCEntitySquid.class, AddonEntitySquid.class, false);
		AddonManager.ReplaceSpawnableEntity("Ozelot", FCEntityOcelot.class, AddonEntityOcelot.class, true);
		AddonManager.ReplaceSpawnableEntity("Creeper", FCEntityCreeper.class, AddonEntityCreeper.class, false);
		AddonManager.ReplaceSpawnableEntity("Sheep", FCEntitySheep.class, AddonEntitySheep.class, true);
		AddonManager.replaceEntityMappingWithAllowanceForOldClass(FCEntityVillager.class, AddonEntityVillager.class, "Villager");

		//Custom entities
		EntityList.AddMapping(AddonEntityFallingConcrete.class, "FallingConcrete", id_entityFallingConcrete);

		//Item frame
		Item itemFrame = new AddonItemFrame(Item.itemFrame.itemID - 256).SetBuoyant().SetIncineratedInCrucible().SetFilterableProperties(1).setUnlocalizedName("frame");
		AddonManager.SetVanillaItemFinal("itemFrame", Item.itemFrame, itemFrame);
		EntityList.ReplaceExistingMapping(AddonEntityItemFrame.class, "ItemFrame");

		//Painting
		Item painting = new AddonItemPainting(Item.painting.itemID - 256).SetBuoyant().SetIncineratedInCrucible().setUnlocalizedName("painting");
		AddonManager.SetVanillaItemFinal("painting", Item.painting, painting);
		EntityList.ReplaceExistingMapping(AddonEntityPainting.class, "Painting");

		//Canvas
		FCBetterThanWolves.fcItemCanvas = new AddonItemCanvas(FCBetterThanWolves.fcItemCanvas.itemID - 256);
		EntityList.ReplaceExistingMapping(AddonEntityCanvas.class, "fcCanvas");
	}
}