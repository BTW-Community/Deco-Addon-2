package net.minecraft.src;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;

public class DecoDefs {
	public static final DecoDefs instance = new DecoDefs();

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
		id_trapdoorIron=3247,
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
		id_spiderEyeBlock=3307,
		id_spiderEyeSlab=3308,
		
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
		id_slateRough=3478,
		id_slateCobbleLoose=3479,
		id_slateBrickLoose=3480,
		id_slateStairs=3481,
		id_slateSmoothStairs=3482,
		id_slateBrickStairs=3483,
		id_slateCobbleStairs=3484,
		id_slateTilesStairs=3485,
		id_slateBrickLooseStairs=3486,
		id_slateCobbleLooseStairs=3487,
		id_acaciaLog=3488,
		id_acaciaStump=3489,
		id_acaciaLeaves=3490,
		id_logDamagedAcacia=3491,
		id_logSpikeAcacia=3492,
		id_acaciaSidingAndCorner=3493,
		id_acaciaMouldingAndDecorative=3494,
		id_acaciaStairs=3495,
		id_gateAcacia=3496,
		id_autumnLeaves=3497,
		id_autumnSapling=3498,
		id_acaciaSapling=3499,
		id_trapdoorAcacia=3500,
		id_doorAcacia=3501,
		id_acaciaWoodChair=3502,
		id_signAcacia=3503,
		id_signAcaciaWall=3504,
		id_barrelFullAcacia=3505,
		id_stoneSlab7=3506,
		
		id_lanternPaperBroken=3510,
		id_slateSidingAndCorner=3511,
		id_slateMouldingAndDecorative=3512,
		id_slateSmoothSidingAndCorner=3513,
		id_slateSmoothMouldingAndDecorative=3514,
		id_slateCobbleSidingAndCorner=3515,
		id_slateCobbleMouldingAndDecorative=3516,
		id_slateBrickSidingAndCorner=3517,
		id_slateBrickMouldingAndDecorative=3518,
		id_slateTilesSidingAndCorner=3519,
		id_slateTilesMouldingAndDecorative=3520,
		
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
		id_slateStone = 30009,
		id_slateBrickItem = 30010,
	
		id_itemDoorSpruce = 30020,
		id_itemDoorBirch = 30021,
		id_itemDoorJungle = 30022,
		id_itemDoorBlood = 30023,
		id_itemDoorCherry = 30024,
		id_itemDoorAcacia = 30025,
	
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

	//Clay
	public static Block terracotta, stainedTerracotta, unfiredTerracotta;
	public static Block terracottaSidingAndCorner, terracottaMouldingAndDecorative, terracottaStairs;
	public static Block[] stainedTerracottaSidingAndCorner, stainedTerracottaMouldingAndDecorative,stainedTerracottaStairs;
	public static Block[] glazedTerracotta;
	public static Block terracottaSlab, terracottaSlab2;

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
	public static Block scaffolding;
	public static Item woodBleach, woodStain;

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

	//Ground Cover
	public static Block layerDirt, layerGrass, layerGravel, layerSand, layerRedSand, layerCoarseDirt, layerPodzol, layerPackedEarth, layerDirtLoose;

	//Tools
	public static DecoItemChiselDiamond chiselDiamond;
	public static DecoItemShearsDiamond shearsDiamond;
	public static DecoItemNameTag nameTag;

	//Extra SubBlocks
	public static Block stoneBrickEdging;

	private DecoDefs() {}

	public void addDefinitions() {
		Item.m_bSuppressConflictWarnings=true;
		addAllSoundsToPool();
		//AddonManager.installResource("fail");
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

	private void addAllSoundsToPool() {
		DecoManager.installResource("dig/lantern1");
		DecoManager.installResource("dig/lantern2");
		DecoManager.installResource("dig/lantern3");
		DecoManager.installResource("dig/lantern4");
		DecoManager.installResource("dig/lantern5");
		DecoManager.installResource("dig/lantern6");
		DecoManager.installResource("step/lantern1");
		DecoManager.installResource("step/lantern2");
		DecoManager.installResource("step/lantern3");
		DecoManager.installResource("step/lantern4");
		DecoManager.installResource("step/lantern5");
		DecoManager.installResource("step/lantern6");

		DecoManager.installResource("dig/chain1");
		DecoManager.installResource("dig/chain2");
		DecoManager.installResource("dig/chain3");
		DecoManager.installResource("dig/chain4");
		DecoManager.installResource("step/chain1");
		DecoManager.installResource("step/chain2");
		DecoManager.installResource("step/chain3");
		DecoManager.installResource("step/chain4");

		DecoManager.installResource("dig/netherrack1");
		DecoManager.installResource("dig/netherrack2");
		DecoManager.installResource("dig/netherrack3");
		DecoManager.installResource("dig/netherrack4");
		DecoManager.installResource("dig/netherrack5");
		DecoManager.installResource("dig/netherrack6");
		DecoManager.installResource("step/netherrack1");
		DecoManager.installResource("step/netherrack2");
		DecoManager.installResource("step/netherrack3");
		DecoManager.installResource("step/netherrack4");
		DecoManager.installResource("step/netherrack5");
		DecoManager.installResource("step/netherrack6");

		DecoManager.installResource("dig/netherbrick1");
		DecoManager.installResource("dig/netherbrick2");
		DecoManager.installResource("dig/netherbrick3");
		DecoManager.installResource("dig/netherbrick4");
		DecoManager.installResource("dig/netherbrick5");
		DecoManager.installResource("dig/netherbrick6");
		DecoManager.installResource("step/netherbrick1");
		DecoManager.installResource("step/netherbrick2");
		DecoManager.installResource("step/netherbrick3");
		DecoManager.installResource("step/netherbrick4");
		DecoManager.installResource("step/netherbrick5");
		DecoManager.installResource("step/netherbrick6");

		DecoManager.installResource("dig/bone1");
		DecoManager.installResource("dig/bone2");
		DecoManager.installResource("dig/bone3");
		DecoManager.installResource("dig/bone4");
		DecoManager.installResource("dig/bone5");
		DecoManager.installResource("step/bone1");
		DecoManager.installResource("step/bone2");
		DecoManager.installResource("step/bone3");
		DecoManager.installResource("step/bone4");
		DecoManager.installResource("step/bone5");

		DecoManager.installResource("dig/soulsand1");
		DecoManager.installResource("dig/soulsand2");
		DecoManager.installResource("dig/soulsand3");
		DecoManager.installResource("dig/soulsand4");
		DecoManager.installResource("dig/soulsand5");
		DecoManager.installResource("dig/soulsand6");
		DecoManager.installResource("dig/soulsand7");
		DecoManager.installResource("dig/soulsand8");
		DecoManager.installResource("dig/soulsand9");
		DecoManager.installResource("step/soulsand1");
		DecoManager.installResource("step/soulsand2");
		DecoManager.installResource("step/soulsand3");
		DecoManager.installResource("step/soulsand4");
		DecoManager.installResource("step/soulsand5");

		DecoManager.installResource("dig/soulsteel1");
		DecoManager.installResource("dig/soulsteel2");
		DecoManager.installResource("dig/soulsteel3");
		DecoManager.installResource("dig/soulsteel4");
		DecoManager.installResource("step/soulsteel1");
		DecoManager.installResource("step/soulsteel2");
		DecoManager.installResource("step/soulsteel3");
		DecoManager.installResource("step/soulsteel4");
		DecoManager.installResource("step/soulsteel5");
		DecoManager.installResource("step/soulsteel6");

		DecoManager.installResource("step/vine1");
		DecoManager.installResource("step/vine2");
		DecoManager.installResource("step/vine3");
		DecoManager.installResource("step/vine4");
		DecoManager.installResource("step/vine5");

		DecoManager.installResource("dig/bloodLog1");
		DecoManager.installResource("dig/bloodLog2");
		DecoManager.installResource("dig/bloodLog3");
		DecoManager.installResource("dig/bloodLog4");
		DecoManager.installResource("dig/bloodLog5");
		DecoManager.installResource("dig/bloodLog6");
		DecoManager.installResource("step/bloodLog1");
		DecoManager.installResource("step/bloodLog2");
		DecoManager.installResource("step/bloodLog3");
		DecoManager.installResource("step/bloodLog4");
		DecoManager.installResource("step/bloodLog5");
		DecoManager.installResource("step/bloodLog6");

		DecoManager.installResource("dig/groth1");
		DecoManager.installResource("dig/groth2");
		DecoManager.installResource("dig/groth3");
		DecoManager.installResource("dig/groth4");
		DecoManager.installResource("dig/groth5");
		DecoManager.installResource("dig/groth6");
		DecoManager.installResource("step/groth1");
		DecoManager.installResource("step/groth2");
		DecoManager.installResource("step/groth3");
		DecoManager.installResource("step/groth4");
		DecoManager.installResource("step/groth5");
		
		/*
		DecoManager.installResource("dig/nylium1");
		DecoManager.installResource("dig/nylium2");
		DecoManager.installResource("dig/nylium3");
		DecoManager.installResource("dig/nylium4");
		DecoManager.installResource("dig/nylium5");
		DecoManager.installResource("dig/nylium6");
		DecoManager.installResource("step/nylium1");
		DecoManager.installResource("step/nylium2");
		DecoManager.installResource("step/nylium3");
		DecoManager.installResource("step/nylium4");
		DecoManager.installResource("step/nylium5");
		DecoManager.installResource("step/nylium6");
		*/

		DecoManager.installResource("random/doorClose1");
		DecoManager.installResource("random/doorClose2");
		DecoManager.installResource("random/doorClose3");
		DecoManager.installResource("random/doorClose4");
		DecoManager.installResource("random/doorClose5");
		DecoManager.installResource("random/doorClose6");
		DecoManager.installResource("random/doorOpen1");
		DecoManager.installResource("random/doorOpen2");
		DecoManager.installResource("random/doorOpen3");
		DecoManager.installResource("random/doorOpen4");

		DecoManager.installResource("random/doorIronClose1");
		DecoManager.installResource("random/doorIronClose2");
		DecoManager.installResource("random/doorIronClose3");
		DecoManager.installResource("random/doorIronClose4");
		DecoManager.installResource("random/doorIronOpen1");
		DecoManager.installResource("random/doorIronOpen2");
		DecoManager.installResource("random/doorIronOpen3");
		DecoManager.installResource("random/doorIronOpen4");

		DecoManager.installResource("random/trapdoorClose1");
		DecoManager.installResource("random/trapdoorClose2");
		DecoManager.installResource("random/trapdoorClose3");
		DecoManager.installResource("random/trapdoorOpen1");
		DecoManager.installResource("random/trapdoorOpen2");
		DecoManager.installResource("random/trapdoorOpen3");
		DecoManager.installResource("random/trapdoorOpen4");

		DecoManager.installResource("random/trapdoorIronClose1");
		DecoManager.installResource("random/trapdoorIronClose2");
		DecoManager.installResource("random/trapdoorIronClose3");
		DecoManager.installResource("random/trapdoorIronClose4");
		DecoManager.installResource("random/trapdoorIronOpen1");
		DecoManager.installResource("random/trapdoorIronOpen2");
		DecoManager.installResource("random/trapdoorIronOpen3");
		DecoManager.installResource("random/trapdoorIronOpen4");

		DecoManager.installResource("random/gateClose1");
		DecoManager.installResource("random/gateClose2");
		DecoManager.installResource("random/gateOpen1");
		DecoManager.installResource("random/gateOpen2");

		DecoManager.installResource("random/chestClose1");
		DecoManager.installResource("random/chestClose2");
		DecoManager.installResource("random/chestClose3");
		DecoManager.installResource("random/chestOpen1");

		DecoManager.installResource("random/strip1");
		DecoManager.installResource("random/strip2");
		DecoManager.installResource("random/strip3");
		DecoManager.installResource("random/strip4");

		DecoManager.installResource("random/pumpkinCarve1");
		DecoManager.installResource("random/pumpkinCarve2");

		DecoManager.installResource("random/till1");
		DecoManager.installResource("random/till2");
		DecoManager.installResource("random/till3");
		DecoManager.installResource("random/till4");

		DecoManager.installResource("mob/squid/say1");
		DecoManager.installResource("mob/squid/say2");
		DecoManager.installResource("mob/squid/say3");
		DecoManager.installResource("mob/squid/say4");
		DecoManager.installResource("mob/squid/say5");
		DecoManager.installResource("mob/squid/death1");
		DecoManager.installResource("mob/squid/death2");
		DecoManager.installResource("mob/squid/death3");
		DecoManager.installResource("mob/squid/hurt1");
		DecoManager.installResource("mob/squid/hurt2");
		DecoManager.installResource("mob/squid/hurt3");

		DecoManager.installResource("misc/itemFrame/addItem1");
		DecoManager.installResource("misc/itemFrame/addItem2");
		DecoManager.installResource("misc/itemFrame/addItem3");
		DecoManager.installResource("misc/itemFrame/addItem4");
		DecoManager.installResource("misc/itemFrame/break1");
		DecoManager.installResource("misc/itemFrame/break2");
		DecoManager.installResource("misc/itemFrame/break3");
		DecoManager.installResource("misc/itemFrame/place1");
		DecoManager.installResource("misc/itemFrame/place2");
		DecoManager.installResource("misc/itemFrame/place3");
		DecoManager.installResource("misc/itemFrame/place4");
		DecoManager.installResource("misc/itemFrame/removeItem1");
		DecoManager.installResource("misc/itemFrame/removeItem2");
		DecoManager.installResource("misc/itemFrame/removeItem3");
		DecoManager.installResource("misc/itemFrame/removeItem4");
		DecoManager.installResource("misc/itemFrame/rotateItem1");
		DecoManager.installResource("misc/itemFrame/rotateItem2");
		DecoManager.installResource("misc/itemFrame/rotateItem3");
		DecoManager.installResource("misc/itemFrame/rotateItem4");

		DecoManager.installResource("misc/painting/break1");
		DecoManager.installResource("misc/painting/break2");
		DecoManager.installResource("misc/painting/break3");
		DecoManager.installResource("misc/painting/place1");
		DecoManager.installResource("misc/painting/place2");
		DecoManager.installResource("misc/painting/place3");
		DecoManager.installResource("misc/painting/place4");

		if (DecoManager.getNewSoundsInstalled()) {
			FCAddOnHandler.LogMessage("Deco Addon Sounds Successfully Loaded");
		}
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
		terracotta = new DecoBlockTerracotta(id_terracotta).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockTerracotta").setCreativeTab(CreativeTabs.tabBlock);
		stainedTerracotta = (new DecoBlockTerracottaStained(id_stainedTerracotta)).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("decoBlockTerracottaStained");
		unfiredTerracotta = new DecoBlockTerracottaUnfired(id_unfiredTerracotta).setUnlocalizedName("decoBlockTerracottaUnfired");
		DecoManager.Register(terracotta);
		Item.itemsList[stainedTerracotta.blockID] = new DecoItemBlockColored(stainedTerracotta.blockID - 256, stainedTerracotta);
		DecoManager.Register(unfiredTerracotta);

		//Sub blocks
		int start = id_clay_sub_start,
				end = start+51,
				id = start,
				i = 0;
		terracottaSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id++, Material.rock, "decoBlockTerracotta", 2.0F, 5.0F, Block.soundStoneFootstep, "decoBlockTerracottaSiding", "Terracotta");
		terracottaMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id++, Material.rock, "decoBlockTerracotta", "decoBlockTerracottaColumn", terracottaSidingAndCorner.blockID, 2.0F, 5.0F, Block.soundStoneFootstep, "decoBlockTerracottaMoulding");
		terracottaStairs = new FCBlockStairs(id++, terracotta, i).setUnlocalizedName("decoBlockTerracottaStairs");

		Item.itemsList[terracottaSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(terracottaSidingAndCorner.blockID - 256);
		Item.itemsList[terracottaMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(terracottaMouldingAndDecorative.blockID - 256);
		DecoManager.Register(terracottaStairs);

		stainedTerracottaSidingAndCorner = new Block[16];
		stainedTerracottaMouldingAndDecorative = new Block[16];
		stainedTerracottaStairs = new Block[16];

		while(i < 16)
		{
			stainedTerracottaSidingAndCorner[i] = new DecoBlockSidingAndCornerDecorativeWall(id++, Material.rock, "decoBlockTerracottaStained_" + DecoUtilsMisc.colorOrder[i], 2.0F, 5.0F, Block.soundStoneFootstep, "decoBlockTerracottaStainedSiding." + DecoUtilsMisc.colorOrder[i], "Stained Terracotta");
			stainedTerracottaMouldingAndDecorative[i] = new DecoBlockMouldingAndDecorativeWall(id++, Material.rock, "decoBlockTerracottaStained_" + DecoUtilsMisc.colorOrder[i], "decoBlockTerracottaStainedColumn_" + DecoUtilsMisc.colorOrder[i], stainedTerracottaSidingAndCorner[i].blockID, 2.0F, 5.0F, Block.soundWoodFootstep, "decoBlockTerracottaStainedMoulding." + DecoUtilsMisc.colorOrder[i]);
			stainedTerracottaStairs[i] = new FCBlockStairs(id++, stainedTerracotta, i).setUnlocalizedName("decoBlockTerracottaStainedStairs." + DecoUtilsMisc.colorOrder[i]);

			Item.itemsList[stainedTerracottaSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stainedTerracottaSidingAndCorner[i].blockID - 256);
			Item.itemsList[stainedTerracottaMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stainedTerracottaMouldingAndDecorative[i].blockID - 256);
			DecoManager.Register(stainedTerracottaStairs[i]);

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
			glazedTerracotta[i] = new DecoBlockTerracottaGlazed(start + i, i).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("decoBlockGlazedTerracotta." + DecoUtilsMisc.colorOrder[i]);
			DecoManager.Register(glazedTerracotta[i]);
			i++;
		}

		//Slabs
		terracottaSlab = new DecoBlockSlabStone(id_terracottaSlab, new Block[] {DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta}, new int[] {0, 1, 2, 3, 4, 5, 6, 7}).setUnlocalizedName("decoBlockTerracottaSlab");
		Item.itemsList[DecoDefs.terracottaSlab.blockID] = new DecoItemBlockSlab(DecoDefs.terracottaSlab.blockID - 256);

		terracottaSlab2 = new DecoBlockSlabStone(id_terracottaSlab2, new Block[] {DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta, DecoDefs.stainedTerracotta}, new int[] {8, 9, 10, 11, 12, 13, 14, 15}).setUnlocalizedName("decoBlockTerracottaSlab2");
		Item.itemsList[DecoDefs.terracottaSlab2.blockID] = new DecoItemBlockSlab(DecoDefs.terracottaSlab2.blockID - 256);
	}

	private void addGlassDefs() {
		glassChunk = new Item(id_glassChunk).setCreativeTab(CreativeTabs.tabMaterials).SetFilterableProperties(2).setUnlocalizedName("decoItemGlassPiece");
		glassStained = new DecoBlockGlassStained(id_glassStained);
		DecoManager.Register(glassStained);
		stainedGlassItem = (DecoItemGlassStained) new DecoItemGlassStained(id_glassStainedItem).setUnlocalizedName("decoItemStainedGlass");
	}

	private void addWhiteStoneDefs() {
		whiteStoneBrick = new DecoBlockWhiteStoneBrick(id_whiteStoneBrick).setUnlocalizedName("decoBlockWhiteBricks");
		whiteBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_whiteBrickSidingAndCorner, Material.rock, "decoBlockWhiteBricks", 2.0F, 5.0F, Block.soundStoneFootstep, "decoBlockWhiteBricksSiding", "White Brick");
		whiteBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_whiteBrickMouldingAndDecorative, Material.rock, "decoBlockWhiteBricks", "decoBlockWhiteBricksColumn", whiteBrickSidingAndCorner.blockID, 2.0F, 5.0F, Block.soundStoneFootstep, "decoBlockWhiteBricksMoulding");
		whiteBrickStairs = new FCBlockStairs(id_whiteBrickStairs, whiteStoneBrick, 0).setUnlocalizedName("decoBlockWhiteBricksStairs");

		Item.itemsList[whiteStoneBrick.blockID] = new DecoItemBlockBrick(whiteStoneBrick.blockID - 256, whiteStoneBrick);
		Item.itemsList[whiteBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(whiteBrickSidingAndCorner.blockID - 256);
		Item.itemsList[whiteBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(whiteBrickMouldingAndDecorative.blockID - 256);
		DecoManager.Register(whiteBrickStairs);
	}

	private void addFlowerDefs() {
		Item.dyePowder = new DecoItemDye(Item.dyePowder.itemID - 256);

		List recipes = CraftingManager.getInstance().getRecipeList();
		ArrayList<RecipeFireworks> fireworks = new ArrayList<RecipeFireworks>();
		for(Object o: recipes)
			if(o instanceof RecipeFireworks)
				fireworks.add((RecipeFireworks)o);
		for(RecipeFireworks rf: fireworks)
			recipes.remove(rf);
		recipes.add(new DecoRecipeFireworksColor());
		
		flower = new DecoBlockFlowers(id_flower, "decoBlockFlower", 16);
		flower2 = new DecoBlockFlowers(id_flower2, "decoBlockFlower2", 2);
		tulip = new DecoBlockFlowers(id_tulip, "decoBlockTulip", 5);
		
		Item.itemsList[flower.blockID] = new DecoItemBlockMulti(flower, new String[] {"yucca", "hyacinth", "birdsParadise", "azalea", "cornflower", "lavender", "honeysuckle","allium", "orchidBlue", "poppy", "azureBluet", "daisy", "peony","lilac","rosebush", "roseBlue"});
		Item.itemsList[flower2.blockID] = new DecoItemBlockMulti(flower2, new String[] {"blackRose", "lilyOfTheValley"});
		Item.itemsList[tulip.blockID] = new DecoItemBlockMulti(tulip, new String[] {"red","pink", "orange", "white", "blue"});

		fertilizer = new DecoItemFertilizer(id_fertilizer);

		DecoManager.Name(Block.plantRed, "Red Rose");

		//Flower pot
		flowerPot = new DecoBlockFlowerPot(id_flowerPot);
		TileEntity.addMapping(DecoTileEntityFlowerPot.class, "AddonFlowerPot");
		Item.replaceItem(Item.flowerPot.itemID, DecoItemFlowerPot.class);
	}

	private void addStoneDefs() {
		Item.itemsList[Block.stone.blockID] = new DecoItemBlockMulti(Block.stone, new String[] {"strata1", "strata2", "strata3", "slate", "rough1", "rough2", "rough3", "roughSlate"});
		
		//Block.stoneBrick = Block.replaceBlock(Block.stoneBrick.blockID, DecoBlockStoneBrick.class);
		
		cobblestoneSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_cobblestoneSidingAndCorner, Material.rock, "stonebrick", 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockCobblestoneSiding", "Cobblestone").SetPicksEffectiveOn();
		cobblestoneMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_cobblestoneMouldingAndDecorative, Material.rock, "stonebrick", "decoBlockCobblestoneColumn", cobblestoneSidingAndCorner.blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockCobblestoneMoulding").SetPicksEffectiveOn();
		mossyCobblestoneSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_mossyCobblestoneSidingAndCorner, Material.rock, "stoneMoss", 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockMossyCobbleSiding", "Mossy Cobblestone").SetPicksEffectiveOn();
		mossyCobblestoneMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_mossyCobblestoneMouldingAndDecorative, Material.rock, "stoneMoss", "decoBlockMossyCobbleColumn", mossyCobblestoneSidingAndCorner.blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockMossyCobbleMoulding").SetPicksEffectiveOn();
		mossyCobblestoneStairs = new FCBlockStairs(id_mossyCobblestoneStairs, Block.cobblestoneMossy, 0).setUnlocalizedName("decoBlockStairsMossyCobble").SetPicksEffectiveOn();

		Item.itemsList[cobblestoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(cobblestoneSidingAndCorner.blockID - 256);
		Item.itemsList[cobblestoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(cobblestoneMouldingAndDecorative.blockID - 256);
		Item.itemsList[mossyCobblestoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(mossyCobblestoneSidingAndCorner.blockID - 256);
		Item.itemsList[mossyCobblestoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(mossyCobblestoneMouldingAndDecorative.blockID - 256);
		DecoManager.Register(mossyCobblestoneStairs);

		stoneTypes = new DecoBlockStoneVariants(id_stoneType);
		stoneTypesCobble = new DecoBlockStoneVariantCobble(id_stoneTypeCobble);
		graniteCobbleLoose = new DecoBlockCobbleLooseGranite(id_graniteCobbleLoose);
		andesiteCobbleLoose = new DecoBlockCobbleLooseAndesite(id_andesiteCobbleLoose);
		dioriteCobbleLoose = new DecoBlockCobbleLooseDiorite(id_dioriteCobbleLoose);
		stoneTypesStoneBrick = new DecoBlockStoneVariantBrick(id_stoneTypeStoneBrick);
		graniteStoneBrickLoose = new DecoBlockStoneBrickLooseGranite(id_graniteStoneBrickLoose);
		andesiteStoneBrickLoose = new DecoBlockStoneBrickLooseAndesite(id_andesiteStoneBrickLoose);
		dioriteStoneBrickLoose = new DecoBlockStoneBrickLooseDiorite(id_dioriteStoneBrickLoose);
		stoneTypesSmooth = new DecoBlockStoneVariantSmooth(id_stoneTypeSmooth);

		String[] stoneTextures = {"decoBlockGranite", "decoBlockAndesite", "decoBlockDiorite", "decoBlockGraniteColumn", "decoBlockAndesiteColumn", "decoBlockDioriteColumn"};
		String[] stoneSmoothTextures = {"decoBlockGraniteSmooth", "decoBlockAndesiteSmooth", "decoBlockDioriteSmooth", "decoBlockGraniteSmoothColumn", "decoBlockAndesiteSmoothColumn", "decoBlockDioriteSmoothColumn"};
		String[] stoneCobblestoneTextures = {"decoBlockGraniteCobble", "decoBlockAndesite", "decoBlockDioriteCobble", "decoBlockGraniteCobbleColumn", "decoBlockAndesiteColumn", "decoBlockDioriteCobbleColumn"};
		String[] stoneBrickTextures = {"decoBlockGraniteBrick", "decoBlockAndesiteBrick", "decoBlockDioriteBrick", "decoBlockGraniteBrickColumn", "decoBlockAndesiteBrickColumn", "decoBlockDioriteBrickColumn"};

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
			stoneTypesSidingAndCorner[i] = new DecoBlockSidingAndCornerDecorativeWall(id_stoneTypeSubStart + i, Material.rock, stoneTextures[i], 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "Siding", "Stone").SetPicksEffectiveOn();
			stoneTypesMouldingAndDecorative[i] = new DecoBlockMouldingAndDecorativeWall(id_stoneTypeSubStart + 3 + i, Material.rock, stoneTextures[i], stoneTextures[i + 3], stoneTypesSidingAndCorner[i].blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "Moulding");
			stoneTypesStairs[i] = new FCBlockStairs(id_stoneTypeSubStart + 6 + i, stoneTypes, i).setUnlocalizedName("decoBlock" + names[i] + "Stairs").SetPicksEffectiveOn();
			stoneTypesSmoothSidingAndCorner[i] = new DecoBlockSidingAndCornerDecorativeWall(id_stoneTypeSmoothSubStart + i, Material.rock, stoneSmoothTextures[i], 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "SmoothSiding", "StoneSmooth").SetPicksEffectiveOn();
			stoneTypesSmoothMouldingAndDecorative[i] = new DecoBlockMouldingAndDecorativeWall(id_stoneTypeSmoothSubStart + 3 + i, Material.rock, stoneSmoothTextures[i], stoneSmoothTextures[i + 3], stoneTypesSmoothSidingAndCorner[i].blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "SmoothMoulding");
			stoneTypesSmoothStairs[i] = new FCBlockStairs(id_stoneTypeSmoothSubStart + 6 + i, stoneTypesSmooth, i).setUnlocalizedName("decoBlock" + names[i] + "SmoothStairs").SetPicksEffectiveOn();
			stoneTypesCobblestoneSidingAndCorner[i] = new DecoBlockSidingAndCornerDecorativeWall(id_stoneTypeCobbleSubStart + i, Material.rock, stoneCobblestoneTextures[i], 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "CobbleSiding", "StoneCobblestone").SetPicksEffectiveOn();
			stoneTypesCobblestoneMouldingAndDecorative[i] = new DecoBlockMouldingAndDecorativeWall(id_stoneTypeCobbleSubStart + 3 + i, Material.rock, stoneCobblestoneTextures[i], stoneCobblestoneTextures[i + 3], stoneTypesCobblestoneSidingAndCorner[i].blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "CobbleMoulding");
			stoneTypesCobblestoneStairs[i] = new DecoBlockStairsMortared(id_stoneTypeCobbleSubStart + 6 + i, stoneTypesCobble, i, id_graniteCobbleLooseStairs + i).setUnlocalizedName("decoBlock" + names[i] + "CobbleStairs").SetPicksEffectiveOn();
			stoneTypesStoneBrickSidingAndCorner[i] = new DecoBlockSidingAndCornerDecorativeWall(id_stoneTypeBrickSubStart + i, Material.rock, stoneBrickTextures[i], 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "BrickSiding", "StoneBrick").SetPicksEffectiveOn();
			stoneTypesStoneBrickMouldingAndDecorative[i] = new DecoBlockMouldingAndDecorativeWall(id_stoneTypeBrickSubStart + 3 + i, Material.rock, stoneBrickTextures[i], stoneBrickTextures[i + 3], stoneTypesStoneBrickSidingAndCorner[i].blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlock" + names[i] + "BrickMoulding");
			stoneTypesStoneBrickStairs[i] = new DecoBlockStairsMortared(id_stoneTypeBrickSubStart + 6 + i, stoneTypesStoneBrick, i, id_graniteCobbleLooseStairs + i + 3).setUnlocalizedName("decoBlock" + names[i] + "BrickStairs").SetPicksEffectiveOn();

			Item.itemsList[stoneTypesSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stoneTypesSidingAndCorner[i].blockID - 256);
			Item.itemsList[stoneTypesMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stoneTypesMouldingAndDecorative[i].blockID - 256);
			DecoManager.Register(stoneTypesStairs[i]);
			Item.itemsList[stoneTypesSmoothSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stoneTypesSmoothSidingAndCorner[i].blockID - 256);
			Item.itemsList[stoneTypesSmoothMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stoneTypesSmoothMouldingAndDecorative[i].blockID - 256);
			DecoManager.Register(stoneTypesSmoothStairs[i]);
			Item.itemsList[stoneTypesCobblestoneSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stoneTypesCobblestoneSidingAndCorner[i].blockID - 256);
			Item.itemsList[stoneTypesCobblestoneMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stoneTypesCobblestoneMouldingAndDecorative[i].blockID - 256);
			DecoManager.Register(stoneTypesCobblestoneStairs[i]);
			Item.itemsList[stoneTypesStoneBrickSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stoneTypesStoneBrickSidingAndCorner[i].blockID - 256);
			Item.itemsList[stoneTypesStoneBrickMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stoneTypesStoneBrickMouldingAndDecorative[i].blockID - 256);
			DecoManager.Register(stoneTypesStoneBrickStairs[i]);
		}

		stoneTypesLooseStairs = new Block[6];
		stoneTypesLooseStairs[0] = new DecoBlockStoneLooseStairs(id_graniteCobbleLooseStairs, graniteCobbleLoose, stoneTypesCobblestoneStairs[0]).setUnlocalizedName("decoBlockGraniteCobblestoneStairsLoose");
		stoneTypesLooseStairs[1] = new DecoBlockStoneLooseStairs(id_andesiteCobbleLooseStairs, andesiteCobbleLoose, stoneTypesCobblestoneStairs[1]).setUnlocalizedName("decoBlockAndesiteCobblestoneStairsLoose");
		stoneTypesLooseStairs[2] = new DecoBlockStoneLooseStairs(id_dioriteCobbleLooseStairs, dioriteCobbleLoose, stoneTypesCobblestoneStairs[2]).setUnlocalizedName("decoBlockDioriteCobblestoneStairsLoose");
		stoneTypesLooseStairs[3] = new DecoBlockStoneLooseStairs(id_graniteStoneBrickLooseStairs, graniteStoneBrickLoose, stoneTypesStoneBrickStairs[0]).setUnlocalizedName("decoBlockGraniteBrickStairsLoose");
		stoneTypesLooseStairs[4] = new DecoBlockStoneLooseStairs(id_andesiteStoneBrickLooseStairs, andesiteStoneBrickLoose, stoneTypesStoneBrickStairs[1]).setUnlocalizedName("decoBlockAndesiteBrickStairsLoose");
		stoneTypesLooseStairs[5] = new DecoBlockStoneLooseStairs(id_dioriteStoneBrickLooseStairs, dioriteStoneBrickLoose, stoneTypesStoneBrickStairs[2]).setUnlocalizedName("decoBlockDioriteBrickStairsLoose");

		DecoManager.Register(stoneTypesLooseStairs[0]);
		DecoManager.Register(stoneTypesLooseStairs[1]);
		DecoManager.Register(stoneTypesLooseStairs[2]);
		DecoManager.Register(stoneTypesLooseStairs[3]);
		DecoManager.Register(stoneTypesLooseStairs[4]);
		DecoManager.Register(stoneTypesLooseStairs[5]);

		polishedStone = new Block(id_polishedStone, Material.rock).setHardness(2.25F).setResistance(10.0F).SetPicksEffectiveOn().setUnlocalizedName("decoBlockPolishedStone").setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabBlock);
		polishedStoneStairs = new FCBlockStairs(id_polishedStoneStairs, polishedStone, 0).setHardness(2.25F).setResistance(10.0F).SetPicksEffectiveOn().setUnlocalizedName("decoBlockPolishedStoneStairs").setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabBlock);
		polishedStoneSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_polishedStoneSidingAndCorner, Material.rock, "stoneslab_top", 2.25F, 10.0F, Block.soundStoneFootstep, "decoBlockPolishedStoneSiding", "Polished Stone").SetPicksEffectiveOn();
		polishedStoneMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_polishedStoneMouldingAndDecorative, Material.rock, "stoneslab_top", "decoBlockPolishedStoneColumn", polishedStoneSidingAndCorner.blockID, 2.25F, 10.0F, Block.soundStoneFootstep, "decoBlockPolishedStoneMoulding").SetPicksEffectiveOn();

		DecoManager.Register(polishedStone);
		DecoManager.Register(polishedStoneStairs);
		Item.itemsList[polishedStoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(polishedStoneSidingAndCorner.blockID - 256);
		Item.itemsList[polishedStoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(polishedStoneMouldingAndDecorative.blockID - 256);

		stoneBrickMossyStairs = new FCBlockStairs(id_stoneBrickMossyStairs, Block.stoneBrick, 1).setUnlocalizedName("decoBlockStoneBrickMossyStairs");
		stoneBrickMossySidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_stoneBrickMossySidingAndCorner, Material.rock, "stonebricksmooth_mossy", 2.25F, 10.0F, Block.soundStoneFootstep, "decoBlockStoneBrickMossySiding", "Mossy Stone Brick").SetPicksEffectiveOn();
		stoneBrickMossyMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_stoneBrickMossyMouldingAndDecorative, Material.rock, "stonebricksmooth_mossy", "decoBlockStoneBrickMossyColumn", stoneBrickMossySidingAndCorner.blockID, 2.25F, 10.0F, Block.soundStoneFootstep, "decoBlockStoneBrickMossyMoulding").SetPicksEffectiveOn();
		stoneBrickCrackedStairs = new FCBlockStairs(id_stoneBrickCrackedStairs, Block.stoneBrick, 2).setUnlocalizedName("decoBlockStoneBrickCrackedStairs");
		stoneBrickCrackedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_stoneBrickCrackedSidingAndCorner, Material.rock, "stonebricksmooth_cracked", 2.25F, 10.0F, Block.soundStoneFootstep, "decoBlockStoneBrickCrackedSiding", "Cracked Stone Brick").SetPicksEffectiveOn();
		stoneBrickCrackedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_stoneBrickCrackedMouldingAndDecorative, Material.rock, "stonebricksmooth_cracked", "decoBlockStoneBrickCrackedColumn", stoneBrickCrackedSidingAndCorner.blockID, 2.25F, 10.0F, Block.soundStoneFootstep, "decoBlockStoneBrickCrackedMoulding").SetPicksEffectiveOn();

		DecoManager.Register(stoneBrickMossyStairs);
		DecoManager.Register(stoneBrickCrackedStairs);
		Item.itemsList[stoneBrickMossySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(stoneBrickMossySidingAndCorner.blockID - 256);
		Item.itemsList[stoneBrickMossyMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(stoneBrickMossyMouldingAndDecorative.blockID - 256);
		Item.itemsList[stoneBrickCrackedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(stoneBrickCrackedSidingAndCorner.blockID - 256);
		Item.itemsList[stoneBrickCrackedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(stoneBrickCrackedMouldingAndDecorative.blockID - 256);

		//Sandstone
		redSand = new DecoBlockRedSand(id_redSand);
		redSandSlab = new DecoBlockRedSandSlab(id_redSandSlab);
		redSandStone = new DecoBlockRedSandStone(id_redSandstone);
		redSandStoneStairs = new DecoBlockStairsRedSandStone(id_redSandstoneStairs, redSandStone, 0).setUnlocalizedName("decoBlockRedSandStoneStairs");
		redSandStoneSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_redSandstoneSidingAndCorner, new String[] {"decoBlockRedSandstone_bottom", "decoBlockRedSandstone_top", "decoBlockRedSandstone_side", "decoBlockRedSandstoneColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneSiding", "Red Sandstone");
		redSandStoneMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_redSandstoneMouldingAndDecorative, new String[] {"decoBlockRedSandstone_bottom", "decoBlockRedSandstone_top", "decoBlockRedSandstone_side", "decoBlockRedSandstoneColumn"}, redSandStoneSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneMoulding");
		redSandStoneSmoothStairs = new DecoBlockStairsRedSandStone(id_redSandstoneSmoothStairs, redSandStone, 2).setUnlocalizedName("decoBlockRedSandStoneStairsSmooth");
		redSandStoneSmoothSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_redSandstoneSmoothSidingAndCorner, new String[] {"decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstoneSmooth", "decoBlockRedSandstoneSmoothColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneSmoothSiding", "Cut Red Sandstone");
		redSandStoneSmoothMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_redSandstoneSmoothMouldingAndDecorative, new String[] {"decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstoneSmooth", "decoBlockRedSandstoneSmoothColumn"}, redSandStoneSmoothSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneSmoothMoulding");
		redSandStonePolishedStairs = new DecoBlockStairsRedSandStone(id_polishedRedSandstoneStairs, redSandStone, 3).setUnlocalizedName("decoBlockRedSandStoneStairsPolished");
		redSandStonePolishedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_polishedRedSandstoneSidingAndCorner, new String[] {"decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstone_top"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstonePolishedSiding", "Polished Red Sandstone");
		redSandStonePolishedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_polishedRedSandstoneMouldingAndDecorative, new String[] {"decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstone_top"}, redSandStonePolishedSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstonePolishedMoulding");
		redSandStoneBrickStairs = new DecoBlockStairsRedSandStone(id_redSandstoneBrickStairs, redSandStone, 4).setUnlocalizedName("decoBlockRedSandStoneStairsBrick");
		redSandStoneBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_redSandstoneBrickSidingAndCorner, new String[] {"decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrickColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickSiding", "Red Sandstone Brick");
		redSandStoneBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_redSandstoneBrickMouldingAndDecorative, new String[] {"decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrickColumn"}, redSandStoneBrickSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickMoulding");
		redSandStoneMossyStairs = new DecoBlockStairsRedSandStone(id_redSandstoneMossyStairs, redSandStone, 5).setUnlocalizedName("decoBlockRedSandStoneMossyStairs");
		redSandStoneMossySidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_redSandstoneMossySidingAndCorner, new String[] {"decoBlockRedSandstoneMossy_bottom", "decoBlockRedSandstoneMossy_top", "decoBlockRedSandstoneMossy_side", "decoBlockRedSandstoneMossyColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneMossySiding", "Mossy Red Sandstone");
		redSandStoneMossyMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_redSandstoneMossyMouldingAndDecorative, new String[] {"decoBlockRedSandstoneMossy_bottom", "decoBlockRedSandstoneMossy_top", "decoBlockRedSandstoneMossy_side", "decoBlockRedSandstoneMossyColumn"}, redSandStoneMossySidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneMossyMoulding");
		redSandStoneBrickLargeStairs = new DecoBlockStairsRedSandStone(id_redSandstoneBrickLargeStairs, redSandStone, 6).setUnlocalizedName("decoBlockRedSandStoneBrickLargeStairs");
		redSandStoneBrickLargeSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_redSandstoneBrickLargeSidingAndCorner, new String[] {"decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLargeColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickLargeSiding", "Large Red Sandstone Brick");
		redSandStoneBrickLargeMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_redSandstoneBrickLargeMouldingAndDecorative, new String[] {"decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLargeColumn"}, redSandStoneBrickLargeSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickLargeMoulding");
		redSandStoneBrickLargeMossyStairs = new DecoBlockStairsRedSandStone(id_redSandstoneBrickLargeMossyStairs, redSandStone, 7).setUnlocalizedName("decoBlockRedSandStoneBrickLargeMossyStairs");
		redSandStoneBrickLargeMossySidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_redSandstoneBrickLargeMossySidingAndCorner, new String[] {"decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossyColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickLargeMossySiding", "Large Mossy Red Sandstone Brick");
		redSandStoneBrickLargeMossyMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_redSandstoneBrickLargeMossyMouldingAndDecorative, new String[] {"decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossyColumn"}, redSandStoneBrickLargeMossySidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickLargeMossyMoulding");
		redSandStoneCrackedStairs = new DecoBlockStairsRedSandStone(id_redSandstoneCrackedStairs, redSandStone, 8).setUnlocalizedName("decoBlockRedSandStoneCrackedStairs");
		redSandStoneCrackedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_redSandstoneCrackedSidingAndCorner, new String[] {"decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneCrackedSiding", "Cracked Red Sandstone");
		redSandStoneCrackedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_redSandstoneCrackedMouldingAndDecorative, new String[] {"decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom"}, redSandStoneCrackedSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneCrackedMoulding");
		redSandStoneBrickLargeCrackedStairs = new DecoBlockStairsRedSandStone(id_redSandstoneBrickLargeCrackedStairs, redSandStone, 9).setUnlocalizedName("decoBlockRedSandStoneBrickLargeCrackedStairs");
		redSandStoneBrickLargeCrackedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_redSandstoneBrickLargeCrackedSidingAndCorner, new String[] {"decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCrackedColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickLargeCrackedSiding", "Cracked Large Red Sandstone Brick");
		redSandStoneBrickLargeCrackedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_redSandstoneBrickLargeCrackedMouldingAndDecorative, new String[] {"decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCrackedColumn"}, redSandStoneBrickLargeCrackedSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockRedSandstoneBrickLargeCrackedMoulding");

		DecoManager.Register(redSand);
		DecoManager.Register(redSandSlab);
		DecoManager.Register(redSandStone, ((DecoBlockRedSandStone) redSandStone).SAND_STONE_TYPES);
		DecoManager.Register(redSandStoneStairs);
		DecoManager.Register(redSandStoneSmoothStairs);
		DecoManager.Register(redSandStonePolishedStairs);
		DecoManager.Register(redSandStoneBrickStairs);
		DecoManager.Register(redSandStoneMossyStairs);
		DecoManager.Register(redSandStoneBrickLargeStairs);
		DecoManager.Register(redSandStoneBrickLargeMossyStairs);
		DecoManager.Register(redSandStoneCrackedStairs);
		DecoManager.Register(redSandStoneBrickLargeCrackedStairs);

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

		pileRedSand = new DecoItemPileRedSand(id_pileRedSand);
		Block.sandStone = Block.replaceBlock(Block.sandStone.blockID, DecoBlockSandStone.class);
		DecoManager.Register(Block.sandStone, new String[] {"default", "chiseled", "smooth", "polished", "brick", "mossy", "largeBrick", "largeBrickMossy", "cracked", "largeBrickCracked"});
		Block.stairsSandStone = Block.replaceBlock(Block.stairsSandStone.blockID, DecoBlockStairsSandStone.class, Block.sandStone, 0);

		sandStoneSmoothStairs = new DecoBlockStairsSandStone(id_sandstoneSmoothStairs, Block.sandStone, 2).setUnlocalizedName("decoBlockStairsSandStoneSmooth");
		sandStoneSmoothSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_sandstoneSmoothSidingAndCorner, new String[] {"fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "sandstone_smooth", "decoBlockSandstoneSmoothColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneSmoothSiding", "Smooth Sandstone");
		sandStoneSmoothMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_sandstoneSmoothMouldingAndDecorative, new String[] {"fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "sandstone_smooth", "decoBlockSandstoneSmoothColumn"}, sandStoneSmoothSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneSmoothMoulding");
		sandStonePolishedStairs = new DecoBlockStairsSandStone(id_polishedSandstoneStairs, Block.sandStone, 3).setUnlocalizedName("decoBlockStairsSandStonePolished");
		sandStonePolishedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_polishedSandstoneSidingAndCorner, new String[] {"fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstonePolishedSiding", "Polished Sandstone");
		sandStonePolishedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_polishedSandstoneMouldingAndDecorative, new String[] {"fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_top"}, sandStonePolishedSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstonePolishedMoulding");
		sandStoneBrickStairs = new DecoBlockStairsSandStone(id_sandstoneBrickStairs, Block.sandStone, 4).setUnlocalizedName("decoBlockStairsSandStoneBrick");
		sandStoneBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_sandstoneBrickSidingAndCorner, new String[] {"decoBlockSandstoneBrick", "decoBlockSandstoneBrick", "decoBlockSandstoneBrick", "decoBlockSandstoneBrickColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickSiding", "Brick Sandstone");
		sandStoneBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_sandstoneBrickMouldingAndDecorative, new String[] {"decoBlockSandstoneBrick", "decoBlockSandstoneBrick", "decoBlockSandstoneBrick", "decoBlockSandstoneBrickColumn"}, sandStoneBrickSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickMoulding");
		sandStoneMossyStairs = new DecoBlockStairsSandStone(id_sandstoneMossyStairs, Block.sandStone, 5).setUnlocalizedName("decoBlockStairsSandStoneMossy");
		sandStoneMossySidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_sandstoneMossySidingAndCorner, new String[] {"decoBlockSandstoneMossy_top", "decoBlockSandstoneMossy_top", "decoBlockSandstoneMossy_side", "decoBlockSandstoneMossyColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneMossySiding", "Mossy Sandstone");
		sandStoneMossyMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_sandstoneMossyMouldingAndDecorative, new String[] {"decoBlockSandstoneMossy_top", "decoBlockSandstoneMossy_top", "decoBlockSandstoneMossy_side", "decoBlockSandstoneMossyColumn"}, sandStoneMossySidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneMossyMoulding");
		sandStoneBrickLargeStairs = new DecoBlockStairsSandStone(id_sandstoneBrickLargeStairs, Block.sandStone, 6).setUnlocalizedName("decoBlockStairsSandStoneBrickLarge");
		sandStoneBrickLargeSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_sandstoneBrickLargeSidingAndCorner, new String[] {"decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLargeColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickLargeSiding", "Large Sandstone Brick");
		sandStoneBrickLargeMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_sandstoneBrickLargeMouldingAndDecorative, new String[] {"decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLargeColumn"}, sandStoneBrickLargeSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickLargeMoulding");
		sandStoneBrickLargeMossyStairs = new DecoBlockStairsSandStone(id_sandstoneBrickLargeMossyStairs, Block.sandStone, 7).setUnlocalizedName("decoBlockStairsSandStoneBrickLargeMossy");
		sandStoneBrickLargeMossySidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_sandstoneBrickLargeMossySidingAndCorner, new String[] {"decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossyColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickLargeMossySiding", "Large Mossy Sandstone Brick");
		sandStoneBrickLargeMossyMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_sandstoneBrickLargeMossyMouldingAndDecorative, new String[] {"decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossyColumn"}, sandStoneBrickLargeMossySidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickLargeMossyMoulding");
		sandStoneCrackedStairs = new DecoBlockStairsSandStone(id_sandstoneCrackedStairs, Block.sandStone, 8).setUnlocalizedName("decoBlockStairsSandStoneCracked");
		sandStoneCrackedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_sandstoneCrackedSidingAndCorner, new String[] {"fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneCrackedSiding", "Cracked Sandstone");
		sandStoneCrackedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_sandstoneCrackedMouldingAndDecorative, new String[] {"fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_bottom"}, sandStoneCrackedSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneCrackedMoulding");
		sandStoneBrickLargeCrackedStairs = new DecoBlockStairsSandStone(id_sandstoneBrickLargeCrackedStairs, Block.sandStone, 9).setUnlocalizedName("decoBlockStairsSandStoneBrickLargeCracked");
		sandStoneBrickLargeCrackedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_sandstoneBrickLargeCrackedSidingAndCorner, new String[] {"decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCrackedColumn"}, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickLargeCrackedSiding", "Cracked Large Sandstone Brick");
		sandStoneBrickLargeCrackedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_sandstoneBrickLargeCrackedMouldingAndDecorative, new String[] {"decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCrackedColumn"}, sandStoneBrickLargeCrackedSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "decoBlockSandstoneBrickLargeCrackedMoulding");

		DecoManager.Register(sandStoneSmoothStairs);
		DecoManager.Register(sandStonePolishedStairs);
		DecoManager.Register(sandStoneBrickStairs);
		DecoManager.Register(sandStoneMossyStairs);
		DecoManager.Register(sandStoneBrickLargeStairs);
		DecoManager.Register(sandStoneBrickLargeMossyStairs);
		DecoManager.Register(sandStoneCrackedStairs);
		DecoManager.Register(sandStoneBrickLargeCrackedStairs);

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
		prismarine = new DecoBlockPrismarine(id_prismarine);
		prismarineLantern = new DecoBlockPrismarineLantern(id_prismarineLantern);
		DecoManager.Register(prismarineLantern);
		FCTileEntityBeacon.AddBeaconEffect(prismarineLantern.blockID, Potion.nightVision.getId());

		prismarineSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_prismarineSidingAndCorner,  Material.rock, "decoBlockPrismarine", 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockPrismarineSiding", "Prismarine").SetPicksEffectiveOn();
		prismarineMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_prismarineMouldingAndDecorative, Material.rock, "decoBlockPrismarine", "decoBlockPrismarineColumn", prismarineSidingAndCorner.blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockPrismarineMoulding").SetPicksEffectiveOn();
		prismarineStairs = new FCBlockStairs(id_prismarineStairs, prismarine, 0).setUnlocalizedName("decoBlockStairsPrismarine");
		prismarineBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_prismarineBrickSidingAndCorner,  Material.rock, "decoBlockPrismarineBrick", 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockPrismarineBrickSiding", "Prismarine Brick").SetPicksEffectiveOn();
		prismarineBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_prismarineBrickMouldingAndDecorative, Material.rock, "decoBlockPrismarineBrick", "decoBlockPrismarineBrickColumn", prismarineBrickSidingAndCorner.blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockPrismarineBrickMoulding").SetPicksEffectiveOn();
		prismarineBrickStairs = new FCBlockStairs(id_prismarineBrickStairs, prismarine, 1).setUnlocalizedName("decoBlockStairsPrismarineBrick");
		prismarineDarkSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_prismarineDarkSidingAndCorner,  Material.rock, "decoBlockPrismarineDark", 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockPrismarineDarkSiding", "Dark Prismarine").SetPicksEffectiveOn();
		prismarineDarkMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_prismarineDarkMouldingAndDecorative, Material.rock, "decoBlockPrismarineDark", "decoBlockPrismarineDarkColumn", prismarineDarkSidingAndCorner.blockID, 1.5F, 10.0F, Block.soundStoneFootstep, "decoBlockPrismarineDarkMoulding").SetPicksEffectiveOn();
		prismarineDarkStairs = new FCBlockStairs(id_prismarineDarkStairs, prismarine, 2).setUnlocalizedName("decoBlockStairsPrismarineDark");

		Item.itemsList[prismarineSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(prismarineSidingAndCorner.blockID - 256);
		Item.itemsList[prismarineMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(prismarineMouldingAndDecorative.blockID - 256);
		DecoManager.Register(prismarineStairs);
		Item.itemsList[prismarineBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(prismarineBrickSidingAndCorner.blockID - 256);
		Item.itemsList[prismarineBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(prismarineBrickMouldingAndDecorative.blockID - 256);
		DecoManager.Register(prismarineBrickStairs);
		Item.itemsList[prismarineDarkSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(prismarineDarkSidingAndCorner.blockID - 256);
		Item.itemsList[prismarineDarkMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(prismarineDarkMouldingAndDecorative.blockID - 256);
		DecoManager.Register(prismarineDarkStairs);

		prismarineShard = new Item(id_prismarineShard).setUnlocalizedName("decoItemPrismarineShard").setCreativeTab(CreativeTabs.tabMaterials).SetFilterableProperties(4);
		prismarineCrystal = new Item(id_prismarineCrystal).setUnlocalizedName("decoItemPrismarineCrystal").setCreativeTab(CreativeTabs.tabMaterials).SetFilterableProperties(4);

		//Nether brick
		netherBrick = new DecoBlockNetherBrick(id_netherBrick);
		netherBrickRedStairs = new FCBlockStairs(id_netherBrickStairs, netherBrick, 0).setUnlocalizedName("decoBlockNetherBrickRedStairs");
		netherBrickRedLoose = new DecoBlockNetherBrickRedLoose(id_netherBrickLoose);
		netherBrickRedLooseSlab = new DecoBlockNetherBrickRedLooseSlab(id_netherBrickLooseSlab);
		netherBrickRedLooseStairs = new DecoBlockNetherBrickRedLooseStairs(id_netherBrickLooseStairs);
		netherBrickRedSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_netherBrickSidingAndCorner, FCBetterThanWolves.fcMaterialNetherRock, "decoBlockNetherBrickRed", 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockNetherBrickRedSiding", "Red Nether Brick");
		netherBrickRedMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_netherBrickMouldingAndDecorative, FCBetterThanWolves.fcMaterialNetherRock, "decoBlockNetherBrickRed", "decoBlockNetherBrickRedColumn", netherBrickRedSidingAndCorner.blockID, 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockNetherBrickRedMoulding");

		DecoManager.Register(netherBrickRedStairs);
		DecoManager.Register(netherBrickRedLoose);
		DecoManager.Register(netherBrickRedLooseSlab);
		DecoManager.Register(netherBrickRedLooseStairs);
		Item.itemsList[netherBrickRedSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(netherBrickRedSidingAndCorner.blockID - 256);
		Item.itemsList[netherBrickRedMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(netherBrickRedMouldingAndDecorative.blockID - 256);

		netherBrickSuperheated = new DecoBlockNetherBrickSuperheated(id_netherBrickSuperheated);

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
		netherrackSuperheated = new DecoBlockNetherrackSuperheated(id_netherrackSuperheated);
		magma = new DecoBlockMagma(id_magma);
		DecoManager.Register(magma);

		//Basalt
		basalt = new AddonBlockDirectional(id_basalt, FCBetterThanWolves.fcMaterialNetherRock, new String[] {"decoBlockBasalt_top", "decoBlockBasaltSmooth_top"}, new String[] {"decoBlockBasalt_side", "decoBlockBasaltSmooth_side"})
				.SetPicksEffectiveOn()
				.setCreativeTab(CreativeTabs.tabBlock)
				.setHardness(2.0F)
				.setHardness(10.0F)
				.setUnlocalizedName("decoBlockBasalt");
		DecoManager.Register(basalt, new String[] {"basalt", "basaltSmooth"});

		Block.netherrack.setStepSound(stepSoundNetherrack);
		Block.oreNetherQuartz.setStepSound(stepSoundNetherrack);
		FCBetterThanWolves.fcBlockNetherrackFalling.setStepSound(stepSoundNetherrack);
		netherrackSuperheated.setStepSound(stepSoundNetherrack);
		basalt.setStepSound(stepSoundNetherrack);

		//Infused stone
		infusedStone = new DecoBlockInfusedStone(id_infusedStone);
		infusedStoneSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_infusedStoneSidingAndCorner, Material.rock, "decoBlockInfusedStone", 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockInfusedStoneSiding", "Infused Stone").SetPicksEffectiveOn();
		infusedStoneMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_infusedStoneMouldingAndDecorative, Material.rock, "decoBlockInfusedStone", "decoBlockInfusedStoneColumn", infusedStoneSidingAndCorner.blockID, 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockInfusedStoneMoulding").SetPicksEffectiveOn();
		infusedStoneStairs = new FCBlockStairs(id_infusedStoneStairs, infusedStone, 0).setUnlocalizedName("decoBlockInfusedStoneStairs");
		infusedStoneSmoothSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_infusedStoneSmoothSidingAndCorner, Material.rock, "decoBlockInfusedStoneSmooth", 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockInfusedStoneSmoothSiding", "Polished Infused Stone").SetPicksEffectiveOn();
		infusedStoneSmoothMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_infusedStoneSmoothMouldingAndDecorative, Material.rock, "decoBlockInfusedStoneSmooth", "decoBlockInfusedStoneSmoothColumn", infusedStoneSmoothSidingAndCorner.blockID, 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockInfusedStoneSmoothMoulding").SetPicksEffectiveOn();
		infusedStoneSmoothStairs = new FCBlockStairs(id_infusedStoneSmoothStairs, infusedStone, 1).setUnlocalizedName("decoBlockInfusedStoneSmoothStairs");
		infusedStoneBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_infusedStoneBrickSidingAndCorner, Material.rock, "decoBlockInfusedStoneBrick", 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockInfusedStoneBrickSiding", "Infused Stone Brick").SetPicksEffectiveOn();
		infusedStoneBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_infusedStoneBrickMouldingAndDecorative, Material.rock, "decoBlockInfusedStoneBrick", "decoBlockInfusedStoneBrickColumn", infusedStoneBrickSidingAndCorner.blockID, 2.0F, 10.0F, Block.soundStoneFootstep, "decoBlockInfusedStoneBrickMoulding").SetPicksEffectiveOn();
		infusedStoneBrickStairs = new FCBlockStairs(id_infusedStoneBrickStairs, infusedStone, 2).setUnlocalizedName("decoBlockInfusedStoneBrickStairs");

		DecoManager.Register(infusedStone, new String[] {"default", "smooth", "brick", "chiseled"});
		Item.itemsList[infusedStoneSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(infusedStoneSidingAndCorner.blockID - 256);
		Item.itemsList[infusedStoneMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(infusedStoneMouldingAndDecorative.blockID - 256);
		DecoManager.Register(infusedStoneStairs);
		Item.itemsList[infusedStoneSmoothSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(infusedStoneSmoothSidingAndCorner.blockID - 256);
		Item.itemsList[infusedStoneSmoothMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(infusedStoneSmoothMouldingAndDecorative.blockID - 256);
		DecoManager.Register(infusedStoneSmoothStairs);
		Item.itemsList[infusedStoneBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(infusedStoneBrickSidingAndCorner.blockID - 256);
		Item.itemsList[infusedStoneBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(infusedStoneBrickMouldingAndDecorative.blockID - 256);
		DecoManager.Register(infusedStoneBrickStairs);

		//Concrete
		concrete = new DecoBlockConcrete(id_concrete);
		concretePowder = new DecoBlockConcretePowder(id_concretePowder);

		concreteSidingAndCorner = new Block[16];
		concreteMouldingAndDecorative = new Block[16];
		concreteStairs = new Block[16];

		int id = id_concreteSubStart;
		final String main = "Concrete";

		for (int i = 0; i < 16; i++)
		{
			concreteSidingAndCorner[i] = new DecoBlockSidingAndCornerDecorativeWall(id++, Material.rock, "decoBlockConcrete_" + DecoUtilsMisc.colorOrder[i], 2.0F, 5.0F, Block.soundStoneFootstep, "decoBlockConcreteSiding." + DecoUtilsMisc.colorOrder[i], "Concrete");
			concreteMouldingAndDecorative[i] = new DecoBlockMouldingAndDecorativeWall(id++, Material.rock, "decoBlockConcrete_" + DecoUtilsMisc.colorOrder[i], "decoBlockConcreteColumn_" + DecoUtilsMisc.colorOrder[i], concreteSidingAndCorner[i].blockID, 2.0F, 5.0F, Block.soundWoodFootstep, "decoBlockConcreteMoulding." + DecoUtilsMisc.colorOrder[i]);
			concreteStairs[i] = new FCBlockStairs(id++, concrete, i).setUnlocalizedName("decoBlockConcreteStairs." + DecoUtilsMisc.colorOrder[i]);

			Item.itemsList[concreteSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(concreteSidingAndCorner[i].blockID - 256);
			Item.itemsList[concreteMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(concreteMouldingAndDecorative[i].blockID - 256);
			DecoManager.Register(concreteStairs[i]);
		}

		//End Stone Brick
		endStoneBrick = new Block(id_endStoneBrick, Material.rock).setHardness(3.0F).setResistance(15.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockEndStoneBrick").setCreativeTab(CreativeTabs.tabBlock);
		endStoneBrickStairs = new FCBlockStairs(id_endStoneBrickStairs, endStoneBrick, 0).setUnlocalizedName("decoBlockEndStoneBrickStairs");
		endStoneBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_endStoneBrickSidingAndCorner, Material.rock, "decoBlockEndStoneBrick", 3.0F, 15.0F, Block.soundStoneFootstep, "decoBlockEndStoneBrickSiding", "End Stone Brick").SetPicksEffectiveOn();
		endStoneBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_endStoneBrickMouldingAndDecorative, Material.rock, "decoBlockEndStoneBrick", "decoBlockEndStoneBrickColumn", endStoneBrickSidingAndCorner.blockID, 3.0F, 15.0F, Block.soundStoneFootstep, "decoBlockEndStoneBrickMoulding").SetPicksEffectiveOn();

		DecoManager.Register(endStoneBrick);
		DecoManager.Register(endStoneBrickStairs);
		Item.itemsList[endStoneBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(endStoneBrickSidingAndCorner.blockID - 256);
		Item.itemsList[endStoneBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(endStoneBrickMouldingAndDecorative.blockID - 256);

		//Obsidian
		Block.obsidian = Block.replaceBlock(Block.obsidian.blockID, DecoBlockObsidian.class);
		DecoManager.Register(Block.obsidian, new String[] {"obsidian", "infused"});
		
		//Slate
		slateRough = new DecoBlockSlateRough(id_slateRough);
		FCBlockStoneRough.m_startaLevelBlockArray[3] = (FCBlockStoneRough) slateRough;
		DecoManager.Register(slateRough);
		slateCobbleLoose = new DecoBlockSlateCobbleLoose(id_slateCobbleLoose);
		slateBrickLoose = new DecoBlockSlateBrickLoose(id_slateBrickLoose);
		
		slateStairs = new FCBlockStairs(id_slateStairs, Block.stone, 3).setUnlocalizedName("decoBlockSlateStairs");
		slateSmoothStairs = new FCBlockStairs(id_slateSmoothStairs, stoneTypesSmooth, 3).setUnlocalizedName("decoBlockSlateSmoothStairs");
		slateBrickStairs = new DecoBlockStairsMortared(id_slateBrickStairs, stoneTypesStoneBrick, 3, id_slateBrickLooseStairs).setUnlocalizedName("decoBlockSlateBrickStairs");
		slateCobbleStairs = new DecoBlockStairsMortared(id_slateCobbleStairs, stoneTypesCobble, 3, id_slateCobbleLooseStairs).setUnlocalizedName("decoBlockSlateCobblestoneStairs");
		slateTilesStairs = new DecoBlockStairsMortared(id_slateTilesStairs, stoneTypesStoneBrick, 4, id_slateBrickLooseStairs).setUnlocalizedName("decoBlockSlateTilesStairs");

		slateBrickLooseStairs = new DecoBlockStoneLooseStairs(id_slateBrickLooseStairs, slateCobbleLoose, slateBrickStairs).setUnlocalizedName("decoBlockSlateBrickStairsLoose");
		slateCobbleLooseStairs = new DecoBlockStoneLooseStairs(id_slateCobbleLooseStairs, slateBrickLoose, slateCobbleStairs).setUnlocalizedName("decoBlockSlateCobblestoneStairsLoose");
		
		DecoManager.Register(slateStairs);
		DecoManager.Register(slateSmoothStairs);
		DecoManager.Register(slateBrickStairs);
		DecoManager.Register(slateCobbleStairs);
		DecoManager.Register(slateTilesStairs);
		DecoManager.Register(slateBrickLooseStairs);
		DecoManager.Register(slateCobbleLooseStairs);

		slateSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(id_slateSidingAndCorner, new String[] {"decoBlockSlate_top", "decoBlockSlate_top", "decoBlockSlate_side"}, 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateSiding", "Slate").SetPicksEffectiveOn();
		slateMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWallWithTopAndBottom(id_slateMouldingAndDecorative, new String[] {"decoBlockSlate_top", "decoBlockSlate_top", "decoBlockSlate_side", "decoBlockSlateColumn"}, slateSidingAndCorner.blockID, 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateMoulding").SetPicksEffectiveOn();
		slateSmoothSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_slateSmoothSidingAndCorner, Material.rock, "decoBlockSlateSmooth", 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateSmoothSiding", "Slate").SetPicksEffectiveOn();
		slateSmoothMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_slateSmoothMouldingAndDecorative, Material.rock, "decoBlockSlateSmooth", "decoBlockSlateSmoothColumn", slateSmoothSidingAndCorner.blockID, 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateSmoothMoulding").SetPicksEffectiveOn();
		slateCobbleSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_slateCobbleSidingAndCorner, Material.rock, "decoBlockSlateCobble", 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateCobbleSiding", "Slate").SetPicksEffectiveOn();
		slateCobbleMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_slateCobbleMouldingAndDecorative, Material.rock, "decoBlockSlateCobble", "decoBlockSlateCobbleColumn", slateCobbleSidingAndCorner.blockID, 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateCobbleMoulding").SetPicksEffectiveOn();
		slateBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_slateBrickSidingAndCorner, Material.rock, "decoBlockSlateBrick", 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateBrickSiding", "Slate").SetPicksEffectiveOn();
		slateBrickMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_slateBrickMouldingAndDecorative, Material.rock, "decoBlockSlateBrick", "decoBlockSlateBrickColumn", slateBrickSidingAndCorner.blockID, 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateBrickMoulding").SetPicksEffectiveOn();
		slateTilesSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(id_slateTilesSidingAndCorner, Material.rock, "decoBlockSlateTiles", 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateTilesSiding", "Slate").SetPicksEffectiveOn();
		slateTilesMouldingAndDecorative = new DecoBlockMouldingAndDecorativeWall(id_slateTilesMouldingAndDecorative, Material.rock, "decoBlockSlateTiles", "decoBlockSlateColumn", slateTilesSidingAndCorner.blockID, 2.0F, 15.0F, Block.soundStoneFootstep, "decoBlockSlateTilesMoulding").SetPicksEffectiveOn();

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
		
		slateStone = new DecoItemSlateStone(id_slateStone);
        slateBrickItem = (new Item(id_slateBrickItem)).setUnlocalizedName("decoItemSlateBrick").setCreativeTab(CreativeTabs.tabMaterials);

		//MUST BE LAST OR NULL POINTER
		//Has to be after reference blocks are declared
		stoneTypesLooseSlab = new DecoBlockStoneLooseSlab(id_stoneTypeLooseSlab, new Block[] {graniteCobbleLoose, andesiteCobbleLoose, dioriteCobbleLoose, graniteStoneBrickLoose, andesiteStoneBrickLoose, dioriteStoneBrickLoose, slateCobbleLoose, slateBrickLoose},
				new int[] {id_stoneSlab2, id_stoneSlab2, id_stoneSlab3, id_stoneSlab3, id_stoneSlab3, id_stoneSlab3, id_stoneSlab6, id_stoneSlab6}, new int[] {6, 7, 0, 1, 2, 3, 6, 7}).setUnlocalizedName("decoBlockStoneSlabLoose");
		Item.itemsList[DecoDefs.stoneTypesLooseSlab.blockID] = new DecoItemBlockSlab(DecoDefs.stoneTypesLooseSlab.blockID - 256);

		stoneSlab = (DecoBlockSlabStone) new DecoBlockSlabStone(id_stoneSlab, new Block[] {DecoDefs.redSandStone, DecoDefs.prismarine, DecoDefs.prismarine, DecoDefs.prismarine, FCBetterThanWolves.fcAestheticOpaque, DecoDefs.whiteStoneBrick, Block.cobblestoneMossy, DecoDefs.netherBrick}, new int[] {0, 0, 1, 2, 9, 0, 0, 0},
				new boolean[] {false, false, false, false, false, false, false, true}, new Block[] {null, null, null, null, null, null, null, netherBrickRedLooseSlab}, new int[] {0, 0, 0, 0, 0, 0, 0, 0}).setUnlocalizedName("decoBlockStoneSlab");
		Item.itemsList[DecoDefs.stoneSlab.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab.blockID - 256);

		stoneSlab2 = (DecoBlockSlabStone) new DecoBlockSlabStone(id_stoneSlab2, new Block[] {DecoDefs.stoneTypes, DecoDefs.stoneTypes, DecoDefs.stoneTypes, DecoDefs.stoneTypesSmooth, DecoDefs.stoneTypesSmooth, DecoDefs.stoneTypesSmooth, DecoDefs.stoneTypesCobble, DecoDefs.stoneTypesCobble}, new int[] {0, 1, 2, 0, 1, 2, 0, 1},
				new boolean[] {false, false, false, false, false, false, true, true}, new Block[] {null, null, null, null, null, null, stoneTypesLooseSlab, stoneTypesLooseSlab}, new int[] {0, 0, 0, 0, 0, 0, 0, 1}).setUnlocalizedName("decoBlockStoneSlab2");
		Item.itemsList[DecoDefs.stoneSlab2.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab2.blockID - 256);

		stoneSlab3 = (DecoBlockSlabStone) new DecoBlockSlabStone(id_stoneSlab3, new Block[] {DecoDefs.stoneTypesCobble, DecoDefs.stoneTypesStoneBrick, DecoDefs.stoneTypesStoneBrick, DecoDefs.stoneTypesStoneBrick, DecoDefs.infusedStone, DecoDefs.infusedStone, DecoDefs.infusedStone, Block.stone}, new int[] {2, 0, 1, 2, 0, 1, 2, 0},
				new boolean[] {true, true, true, true, false, false, false, false}, new Block[] {stoneTypesLooseSlab, stoneTypesLooseSlab, stoneTypesLooseSlab, stoneTypesLooseSlab, null, null, null, null}, new int[] {2, 3, 4, 5, 0, 0, 0, 0}).setUnlocalizedName("decoBlockStoneSlab3");
		Item.itemsList[DecoDefs.stoneSlab3.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab3.blockID - 256);

		stoneSlab4 = (DecoBlockSlabStone) new DecoBlockSlabStone(id_stoneSlab4, new Block[] {Block.sandStone, Block.sandStone, Block.sandStone, DecoDefs.redSandStone, DecoDefs.redSandStone, DecoDefs.redSandStone, Block.sandStone, Block.sandStone}, new int[] {2, 3, 4, 2, 3, 4, 5, 6}).setUnlocalizedName("decoBlockStoneSlab4");
		Item.itemsList[DecoDefs.stoneSlab4.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab4.blockID - 256);

		stoneSlab5 = (DecoBlockSlabStone) new DecoBlockSlabStone(id_stoneSlab5, new Block[] {Block.sandStone, DecoDefs.redSandStone, DecoDefs.redSandStone, DecoDefs.redSandStone, Block.sandStone, Block.sandStone, DecoDefs.redSandStone, DecoDefs.redSandStone}, new int[] {7, 5, 6, 7, 8, 9, 8, 9}).setUnlocalizedName("decoBlockStoneSlab5");
		Item.itemsList[DecoDefs.stoneSlab5.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab5.blockID - 256);

		stoneSlab6 = (DecoBlockSlabStone) new DecoBlockSlabStone(id_stoneSlab6, new Block[] {Block.stoneBrick, Block.stoneBrick, endStoneBrick, terracotta, Block.stone, stoneTypesSmooth, stoneTypesStoneBrick, stoneTypesCobble}, new int[] {1, 2, 0, 0, 3, 3, 3, 3},
				new boolean[] {true, true, false, false, false, false, true, true}, new Block[] {FCBetterThanWolves.fcBlockStoneBrickLooseSlab, FCBetterThanWolves.fcBlockStoneBrickLooseSlab, null, null, null, null, stoneTypesLooseSlab, stoneTypesLooseSlab}, new int[] {0, 0, 0, 0, 0, 0, 6, 7}).setUnlocalizedName("decoBlockStoneSlab6");
		Item.itemsList[DecoDefs.stoneSlab6.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab6.blockID - 256);
		
		stoneSlab7 = (DecoBlockSlabStone) new DecoBlockSlabStone(id_stoneSlab7, new Block[] {stoneTypesStoneBrick}, new int[] {4}, new boolean[] {true}, new Block[] {stoneTypesLooseSlab}, new int[] {7}).setUnlocalizedName("decoBlockStoneSlab7");
		Item.itemsList[DecoDefs.stoneSlab7.blockID] = new DecoItemBlockSlab(DecoDefs.stoneSlab7.blockID - 256);

		concreteSlab = (DecoBlockSlabStone) new DecoBlockSlabStone(id_concreteSlab, new Block[] {DecoDefs.concrete, DecoDefs.concrete, DecoDefs.concrete, DecoDefs.concrete, DecoDefs.concrete, DecoDefs.concrete, DecoDefs.concrete, DecoDefs.concrete}, new int[] {0, 1, 2, 3, 4, 5, 6, 7}).setUnlocalizedName("decoBlockConcreteSlab");
		Item.itemsList[DecoDefs.concreteSlab.blockID] = new DecoItemBlockSlab(DecoDefs.concreteSlab.blockID - 256);

		concreteSlab2 = (DecoBlockSlabStone) new DecoBlockSlabStone(id_concreteSlab2, new Block[] {DecoDefs.concrete, DecoDefs.concrete, DecoDefs.concrete, DecoDefs.concrete, DecoDefs.concrete, DecoDefs.concrete, DecoDefs.concrete, DecoDefs.concrete}, new int[] {8, 9, 10, 11, 12, 13, 14, 15}).setUnlocalizedName("decoBlockConcreteSlab2");
		Item.itemsList[DecoDefs.concreteSlab2.blockID] = new DecoItemBlockSlab(DecoDefs.concreteSlab2.blockID - 256);
	}

	private void addWoodDefs() {
		//Logs
		FCBetterThanWolves.fcItemBark = new DecoItemBark(FCBetterThanWolves.fcItemBark.itemID - 256);
		Block.wood = Block.replaceBlock(Block.wood.blockID, DecoBlockLogReplace.class);
		Item.itemsList[Block.wood.blockID] = new DecoItemBlockLog(Block.wood.blockID - 256, Block.wood, new String[] {"oakLog", "spruceLog", "birchLog", "jungleLog"});

		strippedLog = new DecoBlockLogStripped(id_strippedLog);
		Item.itemsList[DecoDefs.strippedLog.blockID] = new DecoItemBlockLogStripped(DecoDefs.strippedLog.blockID - 256, DecoDefs.strippedLog, new String[] {"oak", "spruce", "birch", "jungle"});
		barkLog = new DecoBlockLogBark(id_barkLog);
		Item.itemsList[barkLog.blockID] = new DecoItemBlockLog(barkLog.blockID - 256, barkLog, new String[] {"oak", "spruce", "birch", "jungle"});
		barkLogStripped = new DecoBlockLogBarkStripped(id_barkLogStripped);
		Item.itemsList[barkLogStripped.blockID] = new DecoItemBlockLogStripped(barkLogStripped.blockID - 256, barkLogStripped, new String[] {"oak", "spruce", "birch", "jungle"});

		FCBetterThanWolves.fcBloodWood = new DecoBlockLogBloodReplace(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBloodWood));
		Item.itemsList[FCBetterThanWolves.fcBloodWood.blockID] = new DecoItemBlockBloodLogReplace(FCBetterThanWolves.fcBloodWood.blockID - 256);
		bloodLog = new DecoBlockLogBlood(id_bloodLog);
		Item.itemsList[bloodLog.blockID] = new DecoItemBlockLogBlood(bloodLog.blockID - 256, bloodLog, new String[] {"stripped", "wood", "strippedWood"});

		FCBetterThanWolves.fcBloodWood.setStepSound(stepSoundBloodLog);
		bloodLog.setStepSound(stepSoundBloodLog);

		cherryLog = new DecoBlockLogCherry(id_cherryLog);
		Item.itemsList[cherryLog.blockID] = new DecoItemBlockLogCherry(cherryLog.blockID - 256, cherryLog, new String[] {"logCherry", "strippedLogCherry", "woodCherry", "strippedWoodCherry"});
		acaciaLog = new DecoBlockLogAcacia(id_acaciaLog);
		Item.itemsList[acaciaLog.blockID] = new DecoItemBlockLogAcacia(acaciaLog.blockID - 256, cherryLog, new String[] {"logAcacia", "strippedLogAcacia", "woodAcacia", "strippedWoodAcacia"});

		cherryStump = new DecoBlockLogCherryStump(id_cherryStump);
		DecoManager.Register(cherryStump);
		acaciaStump = new DecoBlockLogAcaciaStump(id_acaciaStump);
		DecoManager.Register(acaciaStump);
		FCBetterThanWolves.fcBlockWorkStump = new DecoBlockWorkStump(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWorkStump)).setStepSound(Block.soundWoodFootstep);

		FCBetterThanWolves.fcBlockLogDamaged = (FCBlockLogDamaged) new DecoBlockLogDamaged(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockLogDamaged), "decoBlockStrippedOak_side", "decoBlockStrippedOak_top", "fcBlockTrunkTop").setUnlocalizedName("decoBlockChewedOak");
		logDamagedSpruce = new DecoBlockLogDamaged(id_logDamagedSpruce, "decoBlockStrippedSpruce_side", "decoBlockStrippedSpruce_top", "decoBlockTrunkSpruce_top").setUnlocalizedName("decoBlockChewedSpruce");
		logDamagedBirch = new DecoBlockLogDamaged(id_logDamagedBirch, "decoBlockStrippedBirch_side", "decoBlockStrippedBirch_top", "decoBlockTrunkBirch_top").setUnlocalizedName("decoBlockChewedBirch");
		logDamagedJungle = new DecoBlockLogDamaged(id_logDamagedJungle, "decoBlockStrippedJungle_side", "decoBlockStrippedJungle_top", "decoBlockTrunkJungle_top").setUnlocalizedName("decoBlockChewedJungle");
		logDamagedBlood = new DecoBlockLogDamaged(id_logDamagedBlood, "decoBlockStrippedBlood_side", "decoBlockStrippedBlood_top", "decoBlockTrunkJungle_top").setUnlocalizedName("decoBlockChewedBlood");
		logDamagedCherry = new DecoBlockLogDamaged(id_logDamagedCherry, "decoBlockStrippedCherry_side", "decoBlockStrippedCherry_top", "decoBlockTrunkCherry_top").setUnlocalizedName("decoBlockChewedCherry");
		logDamagedAcacia = new DecoBlockLogDamaged(id_logDamagedAcacia, "decoBlockStrippedAcacia_side", "decoBlockStrippedAcacia_top", "decoBlockTrunkAcacia_top").setUnlocalizedName("decoBlockChewedAcacia");
		DecoManager.Register(logDamagedSpruce);
		DecoManager.Register(logDamagedBirch);
		DecoManager.Register(logDamagedJungle);
		DecoManager.Register(logDamagedBlood);
		DecoManager.Register(logDamagedCherry);
		DecoManager.Register(logDamagedAcacia);

		FCBetterThanWolves.fcBlockLogSpike = (FCBlockLogSpike) new DecoBlockLogSpike(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockLogSpike), "decoBlockStrippedOak_side", "decoBlockStrippedOak_top").setUnlocalizedName("decoBlockSpikeOak");
		logSpikeSpruce = new DecoBlockLogSpike(id_logSpikeSpruce, "decoBlockStrippedSpruce_side", "decoBlockStrippedSpruce_top").setUnlocalizedName("decoBlockSpikeSpruce");
		logSpikeBirch = new DecoBlockLogSpike(id_logSpikeBirch, "decoBlockStrippedBirch_side", "decoBlockStrippedBirch_top").setUnlocalizedName("decoBlockSpikeBirch");
		logSpikeJungle = new DecoBlockLogSpike(id_logSpikeJungle, "decoBlockStrippedJungle_side", "decoBlockStrippedJungle_top").setUnlocalizedName("decoBlockSpikeJungle");
		logSpikeBlood = new DecoBlockLogSpike(id_logSpikeBlood, "decoBlockStrippedBlood_side", "decoBlockStrippedBlood_top").setUnlocalizedName("decoBlockSpikeBlood");
		logSpikeCherry = new DecoBlockLogSpike(id_logSpikeCherry, "decoBlockStrippedCherry_side", "decoBlockStrippedCherry_top").setUnlocalizedName("decoBlockSpikeCherry");
		logSpikeAcacia = new DecoBlockLogSpike(id_logSpikeAcacia, "decoBlockStrippedAcacia_side", "decoBlockStrippedAcacia_top").setUnlocalizedName("decoBlockSpikeAcacia");
		DecoManager.Register(logSpikeSpruce);
		DecoManager.Register(logSpikeBirch);
		DecoManager.Register(logSpikeJungle);
		DecoManager.Register(logSpikeBlood);
		DecoManager.Register(logSpikeCherry);
		DecoManager.Register(logSpikeAcacia);

		//Planks
		Block.planks = Block.replaceBlock(Block.planks.blockID, DecoBlockPlanks.class);
		DecoManager.Register(Block.planks, new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry", "acacia"});
		Block.stairsWoodOak = Block.replaceBlock(Block.stairsWoodOak.blockID, FCBlockStairsWood.class, Block.planks, 0);
		Block.stairsWoodSpruce = Block.replaceBlock(Block.stairsWoodSpruce.blockID, FCBlockStairsWood.class, Block.planks, 1);
		Block.stairsWoodBirch = Block.replaceBlock(Block.stairsWoodBirch.blockID, FCBlockStairsWood.class, Block.planks, 2);
		Block.stairsWoodJungle = Block.replaceBlock(Block.stairsWoodJungle.blockID, FCBlockStairsWood.class, Block.planks, 3);
		FCBetterThanWolves.fcBlockWoodBloodStairs = Block.replaceBlock(FCBetterThanWolves.fcBlockWoodBloodStairs.blockID, FCBlockStairsWood.class, Block.planks, 4);
		
		cherryStairs = new FCBlockStairsWood(id_cherryStairs, Block.planks, 5).setUnlocalizedName("decoBlockCherryStairs");
		DecoManager.Register(cherryStairs);
		acaciaStairs = new FCBlockStairsWood(id_acaciaStairs, Block.planks, 6).setUnlocalizedName("decoBlockAcaciaStairs");
		DecoManager.Register(acaciaStairs);

		//Wood Sub Replace
		Block.fence = Block.replaceBlock(Block.fence.blockID, DecoBlockFenceWood.class);
		FCBetterThanWolves.fcBlockWoodOakSidingAndCorner = new DecoBlockWoodSidingAndCornerAndDecorative(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodOakSidingAndCorner),"FCBlockDecorativeWoodOak", "fcWoodOakSiding");
		FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner = new DecoBlockWoodSidingAndCornerAndDecorative(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner),"fcBlockDecorativeWoodSpruce", "fcWoodSpruceSiding").setUnlocalizedName("fcWoodSpruceSiding");
		FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner = new DecoBlockWoodSidingAndCornerAndDecorative(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner),"fcBlockDecorativeWoodBirch", "fcWoodBirchSiding").setUnlocalizedName("fcWoodBirchSiding");
		FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner = new DecoBlockWoodSidingAndCornerAndDecorative(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner),"fcBlockDecorativeWoodJungle", "fcWoodJungleSiding").setUnlocalizedName("fcWoodJungleSiding");
		FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner = new DecoBlockWoodSidingAndCornerAndDecorative(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner),"fcBlockDecorativeWoodBlood", "fcWoodBloodSiding").setUnlocalizedName("fcWoodBloodSiding");
		cherrySidingAndCorner = new DecoBlockWoodSidingAndCornerAndDecorative(id_cherrySidingAndCorner, "decoBlockPlanksCherry", "cherrySiding").setUnlocalizedName("decoBlockCherrySiding");
		acaciaSidingAndCorner = new DecoBlockWoodSidingAndCornerAndDecorative(id_acaciaSidingAndCorner, "decoBlockPlanksAcacia", "acaciaSiding").setUnlocalizedName("decoBlockAcaciaSiding");

		FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative = new DecoBlockWoodMouldingAndDecorative(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative), "FCBlockDecorativeWoodOak", "fcBlockColumnWoodOak_side", FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID, "fcWoodOakMoulding");
		FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative = new DecoBlockWoodMouldingAndDecorative(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative), "fcBlockDecorativeWoodSpruce", "fcBlockColumnWoodSpruce_side", FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID, "fcWoodSpruceMoulding");
		FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative = new DecoBlockWoodMouldingAndDecorative(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative), "fcBlockDecorativeWoodBirch", "fcBlockColumnWoodBirch_side", FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID, "fcWoodBirchMoulding");
		FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative = new DecoBlockWoodMouldingAndDecorative(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative), "fcBlockDecorativeWoodJungle", "fcBlockColumnWoodJungle_side", FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID, "fcWoodJungleMoulding");
		FCBetterThanWolves.fcBlockWoodBloodMouldingAndDecorative = new DecoBlockWoodMouldingAndDecorative(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWoodBloodMouldingAndDecorative), "fcBlockDecorativeWoodBlood", "fcBlockColumnWoodBlood_side", FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner.blockID, "fcWoodBloodMoulding");
		cherryMouldingAndDecorative = new DecoBlockWoodMouldingAndDecorative(id_cherryMouldingAndDecorative, "decoBlockPlanksCherry", "decoBlockPlanksCherryColumn", cherrySidingAndCorner.blockID, "decoBlockCherryMoulding").setUnlocalizedName("decoBlockCherryMoulding");
		acaciaMouldingAndDecorative = new DecoBlockWoodMouldingAndDecorative(id_acaciaMouldingAndDecorative, "decoBlockPlanksAcacia", "decoBlockPlanksAcaciaColumn", cherrySidingAndCorner.blockID, "decoBlockAcaciaMoulding").setUnlocalizedName("decoBlockAcaciaMoulding");

		Item.itemsList[FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID] = new DecoItemBlockWoodSidingStub(FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockID - 256);
		Item.itemsList[FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID] = new DecoItemBlockWoodMouldingStub(FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID - 256);
		Item.itemsList[FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID] = new DecoItemBlockWoodCornerStub(FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockID - 256);
		Item.itemsList[FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative.blockID] = new DecoItemBlockWoodMouldingDecorativeStub(FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative.blockID - 256);
		Item.itemsList[FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID] = new DecoItemBlockWoodSidingDecorativeStub(FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockID - 256);

		//Ladders (used for block bounds change)
		FCBetterThanWolves.fcBlockLadder = new DecoBlockLadder(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockLadder));
		FCBetterThanWolves.fcBlockLadderOnFire = new DecoBlockLadderOnFire(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockLadderOnFire));

		//Trapdoors
		//Forces oak trap doors to inherit texture rotations and eventually better placement
		Block.trapdoor = Block.replaceBlock(Block.trapdoor.blockID, DecoBlockTrapDoor.class);
		DecoManager.Name(Block.trapdoor, "Oak Trap Door");

		trapdoorSpruce = (BlockTrapDoor) new DecoBlockTrapDoor(id_trapdoorSpruce).setUnlocalizedName("decoBlockTrapdoorSpruce");
		trapdoorBirch = (BlockTrapDoor) new DecoBlockTrapDoor(id_trapdoorBirch).setUnlocalizedName("decoBlockTrapdoorBirch");
		trapdoorJungle = (BlockTrapDoor) new DecoBlockTrapDoor(id_trapdoorJungle).setUnlocalizedName("decoBlockTrapdoorJungle");
		trapdoorBlood = (BlockTrapDoor) new DecoBlockTrapDoor(id_trapdoorBlood).setUnlocalizedName("decoBlockTrapdoorBlood");
		trapdoorCherry = (BlockTrapDoor) new DecoBlockTrapDoor(id_trapdoorCherry).setUnlocalizedName("decoBlockTrapdoorCherry");
		trapdoorAcacia = (BlockTrapDoor) new DecoBlockTrapDoor(id_trapdoorAcacia).setUnlocalizedName("decoBlockTrapdoorAcacia");
		
		trapdoorIron = new DecoBlockTrapDoorIron(id_trapdoorIron); 

		DecoManager.Register(trapdoorSpruce);
		DecoManager.Register(trapdoorBirch);
		DecoManager.Register(trapdoorJungle);
		DecoManager.Register(trapdoorBlood);
		DecoManager.Register(trapdoorCherry);
		DecoManager.Register(trapdoorAcacia);
		
		DecoManager.Register(trapdoorIron);

		//Doors
		Item.doorWood = Item.replaceItem(Item.doorWood.itemID, DecoItemDoor.class, "doorWood", Block.doorWood.blockID, true);
		Item.doorIron = Item.replaceItem(Item.doorIron.itemID, DecoItemDoor.class, "doorIron", Block.doorIron.blockID, false);
		itemDoorSpruce = (FCItemDoor) new DecoItemDoor(id_itemDoorSpruce, "decoItemDoorSpruce", id_doorSpruce, true);
		itemDoorBirch = (FCItemDoor) new DecoItemDoor(id_itemDoorBirch, "decoItemDoorBirch", id_doorBirch, true);
		itemDoorJungle = (FCItemDoor) new DecoItemDoor(id_itemDoorJungle, "decoItemDoorJungle", id_doorJungle, true);
		itemDoorBlood = (FCItemDoor) new DecoItemDoor(id_itemDoorBlood, "decoItemDoorBlood", id_doorBlood, true);
		itemDoorCherry = (FCItemDoor) new DecoItemDoor(id_itemDoorCherry, "decoItemDoorCherry", id_doorCherry, true);
		itemDoorAcacia = (FCItemDoor) new DecoItemDoor(id_itemDoorAcacia, "decoItemDoorAcacia", id_doorAcacia, true);
		
		Block.doorWood = Block.replaceBlock(Block.doorWood.blockID, DecoBlockDoorWood.class, new String[] {"doorWood_lower", "doorWood_upper"}, Item.doorWood.itemID);
		Block.doorIron = Block.replaceBlock(Block.doorIron.blockID, DecoBlockDoorIron.class);
		doorSpruce = (BlockDoor) new DecoBlockDoorWood(id_doorSpruce, new String[] {"decoBlockDoorSpruce_lower", "decoBlockDoorSpruce_upper"}, itemDoorSpruce.itemID).setUnlocalizedName("decoBlockDoorSpruce");
		doorBirch = (BlockDoor) new DecoBlockDoorWood(id_doorBirch, new String[] {"decoBlockDoorBirch_lower", "decoBlockDoorBirch_upper"}, itemDoorBirch.itemID).setUnlocalizedName("decoBlockDoorBirch");
		doorJungle = (BlockDoor) new DecoBlockDoorWood(id_doorJungle, new String[] {"decoBlockDoorJungle_lower", "decoBlockDoorJungle_upper"}, itemDoorJungle.itemID).setUnlocalizedName("decoBlockDoorJungle");
		doorBlood = (BlockDoor) new DecoBlockDoorWood(id_doorBlood, new String[] {"decoBlockDoorBlood_lower", "decoBlockDoorBlood_upper"}, itemDoorBlood.itemID).setUnlocalizedName("decoBlockDoorBlood");
		doorCherry = (BlockDoor) new DecoBlockDoorWood(id_doorCherry, new String[] {"decoBlockDoorCherry_lower", "decoBlockDoorCherry_upper"}, itemDoorCherry.itemID).setUnlocalizedName("decoBlockDoorCherry");
		doorAcacia = (BlockDoor) new DecoBlockDoorWood(id_doorAcacia, new String[] {"decoBlockDoorAcacia_lower", "decoBlockDoorAcacia_upper"}, itemDoorAcacia.itemID).setUnlocalizedName("decoBlockDoorAcacia");

		DecoManager.Register(doorSpruce);
		DecoManager.Register(doorBirch);
		DecoManager.Register(doorJungle);
		DecoManager.Register(doorBlood);
		DecoManager.Register(doorCherry);
		DecoManager.Register(doorAcacia);

		//Fence gates
		Block.fenceGate = Block.replaceBlock(Block.fenceGate.blockID, DecoBlockFenceGate.class, "wood");
		gateSpruce = (BlockFenceGate) new DecoBlockFenceGate(id_gateSpruce, "decoBlockGateSpruce");
		gateBirch = (BlockFenceGate) new DecoBlockFenceGate(id_gateBirch, "decoBlockGateBirch");
		gateJungle = (BlockFenceGate) new DecoBlockFenceGate(id_gateJungle, "decoBlockGateJungle");
		gateBlood = (BlockFenceGate) new DecoBlockFenceGate(id_gateBlood, "decoBlockGateBlood");
		gateCherry = (BlockFenceGate) new DecoBlockFenceGate(id_gateCherry, "decoBlockGateCherry");
		gateAcacia = (BlockFenceGate) new DecoBlockFenceGate(id_gateAcacia, "decoBlockGateAcacia");

		DecoManager.Register(gateSpruce);
		DecoManager.Register(gateBirch);
		DecoManager.Register(gateJungle);
		DecoManager.Register(gateBlood);
		DecoManager.Register(gateCherry);
		DecoManager.Register(gateAcacia);

		//Chairs
		oakWoodChair = new DecoBlockChairWood(id_oakWoodChair, "Oak");
		birchWoodChair = new DecoBlockChairWood(id_spruceWoodChair, "Birch");
		spruceWoodChair = new DecoBlockChairWood(id_birchWoodChair, "Spruce");
		jungleWoodChair = new DecoBlockChairWood(id_jungleWoodChair, "Jungle");
		bloodWoodChair = new DecoBlockChairWood(id_bloodWoodChair, "Blood");
		cherryWoodChair = new DecoBlockChairWood(id_cherryWoodChair, "Cherry");
		acaciaWoodChair = new DecoBlockChairWood(id_acaciaWoodChair, "Acacia");

		//Painted planks
		planksPainted = (new DecoBlockPlanksPainted(id_planksPainted, "decoBlockPlanksPainted")).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(CreativeTabs.tabBlock);
		Item.itemsList[planksPainted.blockID] = new DecoItemBlockColored(planksPainted.blockID - 256, planksPainted);

		paintedPlanksSidingAndCorner = new Block[16];
		paintedPlanksMouldingAndDecorative = new Block[16];
		paintedPlanksStairs = new Block[16];

		//Sub blocks
		int start = id_paintedPlanksSubStart,
				end = start + 48,
				id = start;

		for (int i = 0; i < 16; i++) {
			paintedPlanksSidingAndCorner[i] = new DecoBlockPlanksPaintedSidingAndCornerAndDecorative(id++, "decoBlockPlanksPainted_"+ DecoUtilsMisc.colorOrder[i], "decoBlockPlanksPaintedSiding."+ DecoUtilsMisc.colorOrder[i]);
			paintedPlanksMouldingAndDecorative[i] = new DecoBlockPlanksPaintedMouldingAndDecorative(id++, "decoBlockPlanksPainted_"+ DecoUtilsMisc.colorOrder[i], "decoBlockPlanksPaintedColumn_"+ DecoUtilsMisc.colorOrder[i], paintedPlanksSidingAndCorner[i].blockID, "decoBlockPlanksPaintedMoulding."+ DecoUtilsMisc.colorOrder[i]);
			paintedPlanksStairs[i] = new DecoBlockStairsPaintedPlanks(id++, planksPainted, i, i).setUnlocalizedName("decoBlockPlanksPaintedStairs." + DecoUtilsMisc.colorOrder[i]).setCreativeTab(CreativeTabs.tabBlock);

			Item.itemsList[paintedPlanksSidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(paintedPlanksSidingAndCorner[i].blockID - 256);
			Item.itemsList[paintedPlanksMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(paintedPlanksMouldingAndDecorative[i].blockID - 256);
			DecoManager.Register(paintedPlanksStairs[i]);
		}

		bottleHempOil = new Item(id_bottleHempOil).setUnlocalizedName("decoItemHempOil").setCreativeTab(CreativeTabs.tabMaterials).SetBuoyant();
		woodBleach = new Item(id_woodBleach).setUnlocalizedName("decoItemWoodBleach").setCreativeTab(CreativeTabs.tabMaterials).SetBuoyant();
		woodStain = new Item(id_woodStain).setUnlocalizedName("decoItemWoodStain").setCreativeTab(CreativeTabs.tabMaterials).SetBuoyant();

		//Pergola
		pergola = new DecoBlockPergola(id_pergola);
		DecoManager.Register(pergola);

		//Barrels
		barrelEmpty = new DecoBlockBarrelEmpty(id_barrelEmpty, new String[] {"decoBlockBarrelOak_top", "decoBlockBarrelSpruce_top", "decoBlockBarrelBirch_top", "decoBlockBarrelJungle_top"},
				new String[] {"decoBlockBarrelOak_side", "decoBlockBarrelSpruce_side", "decoBlockBarrelBirch_side", "decoBlockBarrelJungle_side"}).setUnlocalizedName("decoBlockBarrel1");
		barrelEmpty2 = new DecoBlockBarrelEmpty(id_barrelEmpty2, new String[] {"decoBlockBarrelBlood_top", "decoBlockBarrelCherry_top", "decoBlockBarrelAcacia_top"}, new String[] {"decoBlockBarrelBlood_side", "decoBlockBarrelCherry_side", "decoBlockBarrelAcacia_side"}).setUnlocalizedName("decoBlockBarrel2");
		DecoManager.Register(barrelEmpty, new String[] {"oak", "spruce", "birch", "jungle"});
		DecoManager.Register(barrelEmpty2, new String[] {"blood", "cherry", "acacia"});

		barrelFullOak = new DecoBlockBarrelFilled(id_barrelFullOak, "decoBlockBarrelOak");
		barrelFullSpruce = new DecoBlockBarrelFilled(id_barrelFullSpruce, "decoBlockBarrelSpruce");
		barrelFullBirch = new DecoBlockBarrelFilled(id_barrelFullBirch, "decoBlockBarrelBirch");
		barrelFullJungle = new DecoBlockBarrelFilled(id_barrelFullJungle, "decoBlockBarrelJungle");
		barrelFullBlood = new DecoBlockBarrelFilled(id_barrelFullBlood, "decoBlockBarrelBlood");
		barrelFullCherry = new DecoBlockBarrelFilled(id_barrelFullCherry, "decoBlockBarrelCherry");
		barrelFullAcacia = new DecoBlockBarrelFilled(id_barrelFullAcacia, "decoBlockBarrelAcacia");

		Item.itemsList[barrelFullOak.blockID] = new DecoItemBlockBarrelFilled(barrelFullOak.blockID - 256, barrelFullOak, DecoBlockBarrelFilled.typeTags, barrelEmpty, 0);
		Item.itemsList[barrelFullSpruce.blockID] = new DecoItemBlockBarrelFilled(barrelFullSpruce.blockID - 256, barrelFullSpruce, DecoBlockBarrelFilled.typeTags, barrelEmpty, 1);
		Item.itemsList[barrelFullBirch.blockID] = new DecoItemBlockBarrelFilled(barrelFullBirch.blockID - 256, barrelFullBirch, DecoBlockBarrelFilled.typeTags, barrelEmpty, 2);
		Item.itemsList[barrelFullJungle.blockID] = new DecoItemBlockBarrelFilled(barrelFullJungle.blockID - 256, barrelFullJungle, DecoBlockBarrelFilled.typeTags, barrelEmpty, 3);
		Item.itemsList[barrelFullBlood.blockID] = new DecoItemBlockBarrelFilled(barrelFullBlood.blockID - 256, barrelFullBlood, DecoBlockBarrelFilled.typeTags, barrelEmpty2, 0);
		Item.itemsList[barrelFullCherry.blockID] = new DecoItemBlockBarrelFilled(barrelFullCherry.blockID - 256, barrelFullCherry, DecoBlockBarrelFilled.typeTags, barrelEmpty2, 1);
		Item.itemsList[barrelFullAcacia.blockID] = new DecoItemBlockBarrelFilled(barrelFullAcacia.blockID - 256, barrelFullAcacia, DecoBlockBarrelFilled.typeTags, barrelEmpty2, 2);

		//Crates
		crate = new DecoBlockCrate(id_crate);
		DecoManager.Register(crate, new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry", "acacia"});

		//Signs
		Block.signPost = Block.replaceBlock(Block.signPost.blockID, DecoBlockSign.class, true, 0, "wood");
		signSpruce = new DecoBlockSign(id_signSpruce, true, 1, "wood_spruce");
		signBirch = new DecoBlockSign(id_signBirch, true, 2, "wood_birch");
		signJungle = new DecoBlockSign(id_signJungle, true, 3, "wood_jungle");
		signBlood = new DecoBlockSign(id_signBlood, true, 4, "fcBlockPlanks_blood");
		signCherry = new DecoBlockSign(id_signCherry, true, 5, "decoBlockPlanksCherry");
		signAcacia = new DecoBlockSign(id_signAcacia, true, 6, "decoBlockPlanksAcacia");

		Block.signWall = Block.replaceBlock(Block.signWall.blockID, DecoBlockSignWall.class, 0, "wood");
		signSpruceWall = new DecoBlockSignWall(id_signSpruceWall, 1, "wood_spruce");
		signBirchWall = new DecoBlockSignWall(id_signBirchWall, 2, "wood_birch");
		signJungleWall = new DecoBlockSignWall(id_signJungleWall, 3, "wood_jungle");
		signBloodWall = new DecoBlockSignWall(id_signBloodWall, 4, "fcBlockPlanks_blood");
		signCherryWall = new DecoBlockSignWall(id_signCherryWall, 5, "decoBlockPlanksCherry");
		signAcaciaWall = new DecoBlockSignWall(id_signAcaciaWall, 6, "decoBlockPlanksAcacia");

		Item.sign = Item.replaceItem(Item.sign.itemID, DecoItemSign.class).setUnlocalizedName("sign");

		//Slabs
		planksPaintedSlab = (DecoBlockWoodSlab) new DecoBlockWoodSlab(id_paintedPlanksSlab, new Block[] {planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted}, new int[] {0, 1, 2, 3, 4, 5, 6, 7},
				new int[] {paintedPlanksMouldingAndDecorative[0].blockID, paintedPlanksMouldingAndDecorative[1].blockID, paintedPlanksMouldingAndDecorative[2].blockID, paintedPlanksMouldingAndDecorative[3].blockID, paintedPlanksMouldingAndDecorative[4].blockID, paintedPlanksMouldingAndDecorative[5].blockID, paintedPlanksMouldingAndDecorative[6].blockID, paintedPlanksMouldingAndDecorative[7].blockID},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0}).setUnlocalizedName("decoBlockPlanksPaintedSlab");
		Item.itemsList[DecoDefs.planksPaintedSlab.blockID] = new DecoItemBlockSlab(DecoDefs.planksPaintedSlab.blockID - 256);

		planksPaintedSlab2 = (DecoBlockWoodSlab) new DecoBlockWoodSlab(id_paintedPlanksSlab2, new Block[] {planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted, planksPainted}, new int[] {8, 9, 10, 11, 12, 13, 14, 15},
				new int[] {paintedPlanksMouldingAndDecorative[8].blockID, paintedPlanksMouldingAndDecorative[9].blockID, paintedPlanksMouldingAndDecorative[10].blockID, paintedPlanksMouldingAndDecorative[11].blockID, paintedPlanksMouldingAndDecorative[12].blockID, paintedPlanksMouldingAndDecorative[13].blockID, paintedPlanksMouldingAndDecorative[14].blockID, paintedPlanksMouldingAndDecorative[15].blockID},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0}).setUnlocalizedName("decoBlockPlanksPaintedSlab2");
		Item.itemsList[DecoDefs.planksPaintedSlab2.blockID] = new DecoItemBlockSlab(DecoDefs.planksPaintedSlab2.blockID - 256);

		woodSlab = (DecoBlockWoodSlab) new DecoBlockWoodSlab(id_woodSlab, new Block[] {Block.planks, Block.planks}, new int[] {5, 6},
				new int[] {FCBetterThanWolves.fcBlockWoodMouldingItemStubID, FCBetterThanWolves.fcBlockWoodMouldingItemStubID}, new int[] {5, 6}).setUnlocalizedName("decoBlockWoodSlab").setCreativeTab(null);
		Item.itemsList[DecoDefs.woodSlab.blockID] = new DecoItemBlockSlab(DecoDefs.woodSlab.blockID - 256);
		
		Block.woodSingleSlab = (BlockHalfSlab) Block.replaceBlock(Block.woodSingleSlab.blockID, DecoBlockWoodSlabReplace.class, false);
		Block.woodDoubleSlab = (BlockHalfSlab) Block.replaceBlock(Block.woodDoubleSlab.blockID, DecoBlockWoodSlabReplace.class, true);
        Item.itemsList[Block.woodSingleSlab.blockID] = (new ItemSlab(Block.woodSingleSlab.blockID - 256, Block.woodSingleSlab, Block.woodDoubleSlab, false)).setUnlocalizedName("woodSlab");
        Item.itemsList[Block.woodDoubleSlab.blockID] = (new ItemSlab(Block.woodDoubleSlab.blockID - 256, Block.woodSingleSlab, Block.woodDoubleSlab, true)).setUnlocalizedName("woodSlab");
	}

	private void addDecoDefs() {
		//Aesthetic Opaque
		FCBetterThanWolves.fcAestheticOpaque = Block.replaceBlock(FCBetterThanWolves.fcAestheticOpaque.blockID, DecoBlockAestheticOpaque.class);
		
		//Diamondium
		blockDiamondium = new Block(id_blockDiamondium, Material.iron).setHardness(10.0F).setResistance(2000.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockDiamond").setCreativeTab(CreativeTabs.tabBlock);
		Block.blockDiamond.setUnlocalizedName("decoBlockDiamond");
		FCTileEntityBeacon.AddBeaconEffect(blockDiamondium.blockID, FCBetterThanWolves.potionFortune.getId());

		DecoManager.Register(blockDiamondium);

		//Haybale and thatch
		hayBale = new DecoBlockHayBale(id_hayBale);
		hayBaleStairs = new DecoBlockStairsHay(id_hayBaleStairs);
		FCBetterThanWolves.fcItemWheat = new DecoItemWheat(FCBetterThanWolves.fcItemWheat.itemID - 256);
		thatch = new DecoBlockThatch(id_thatch);
		thatchStairs = new DecoBlockStairsThatch(id_thatchStairs);
		FCBetterThanWolves.fcItemStraw = new DecoItemStraw(FCBetterThanWolves.fcItemStraw.itemID - 256);

		//Lanterns
		paperWall = new DecoBlockPaperWall(id_paperWall);
		fenceSteel = new DecoBlockWroughtBars(id_fenceSteel);
		DecoManager.Register(fenceSteel);
		DecoManager.Register(paperWall);

		lanternPaper = new DecoBlockLantern(id_lanternPaper,Material.wood,.3F,"Paper",true).SetAxesEffectiveOn(true);
		lanternPaperBroken = new DecoBlockLantern(id_lanternPaperBroken,Material.wood,.3F,"PaperBroken",false).setLightValue(0).SetAxesEffectiveOn(true);
		chandelier = new DecoBlockChandelier(id_chandelier);
		lanternSteel = new DecoBlockLantern(id_lanternSteel,Material.iron,.5F,"Iron", false).SetPicksEffectiveOn(true);

		//Workbench
		workbench = new DecoBlockWorkbench(id_workbench);
		DecoManager.Register(workbench, "Workbench");

		//Coarse Dirt
		coarseDirt = new DecoBlockDirtCoarse(id_coarseDirt);
		DecoManager.Register(coarseDirt, "Coarse Dirt");
		coarseDirtSlab = new DecoBlockDirtCoarseSlab(id_coarseDirtSlab);
		DecoManager.Register(coarseDirtSlab, "Coarse Dirt Slab");

		//Podzol
		podzol = new DecoBlockPodzol(id_podzol);
		DecoManager.Register(podzol, "Podzol");
		//podzolSlab = new AddonBlockDirtSlab(id_podzolSlab));

		//Dirt Replace
		Block.dirt = Block.replaceBlock(Block.dirt.blockID, DecoBlockDirt.class);
		Block.mycelium = (BlockMycelium) Block.replaceBlock(Block.mycelium.blockID, DecoBlockMycelium.class);
		FCBetterThanWolves.fcBlockDirtLoose = new DecoBlockDirtLoose(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockDirtLoose));
		//FCBetterThanWolves.fcBlockDirtSlab = new DecoBlockDirtSlab(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockDirtSlab));

		//Nether portal
		Block.portal = (BlockPortal) Block.replaceBlock(Block.portal.blockID, DecoBlockPortal.class);

		//Pumpkins
		pumpkin = new DecoBlockPumpkinCarved(id_pumpkin);
		DecoManager.Register(pumpkin, new String[] {"0", "1", "2"});
		pumpkinLit = new DecoBlockPumpkinLit(id_pumpkinLit);
		DecoManager.Register(pumpkinLit, new String[] {"0", "1", "2"});
		Item.itemsList[FCBetterThanWolves.fcBlockPumpkinFresh.blockID] = new DecoItemBlockPumpkinFresh(FCBetterThanWolves.fcBlockPumpkinFresh.blockID - 256);

		//Carpets
		carpet = new DecoBlockCarpet(id_carpet);
		DecoManager.Register(carpet, DecoUtilsMisc.colorOrder);

		//Coal block
		Item.coal = Item.replaceItem(Item.coal.itemID, DecoItemCoal.class).setUnlocalizedName("coal");
		FCBetterThanWolves.fcItemNethercoal = new DecoItemNethercoal(FCBetterThanWolves.fcItemNethercoal.itemID - 256).SetFurnaceBurnTime(2 * FCEnumFurnaceBurnTime.COAL.m_iBurnTime).SetFilterableProperties(2).setUnlocalizedName("fcItemNethercoal").setCreativeTab(CreativeTabs.tabMaterials);

		coalBlock = new Block(id_coalBlock, Material.rock).setUnlocalizedName("decoBlockCoal").SetPicksEffectiveOn().SetFireProperties(FCEnumFlammability.EXTREME).setHardness(1.5F).setResistance(10.0F).setCreativeTab(CreativeTabs.tabBlock);
		DecoManager.Register(coalBlock);
		netherCoalBlock = new DecoBlockNetherCoal(id_netherCoalBlock);
		DecoManager.Register(netherCoalBlock);

		//Fire
		Block.fire = (BlockFire) Block.replaceBlock(Block.fire.blockID, DecoBlockFire.class);
		FCBetterThanWolves.fcBlockFireStoked = new DecoBlockFireStoked(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockFireStoked));

		//Bone
		FCBetterThanWolves.fcAestheticOpaque = new DecoBlockAestheticOpaque(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcAestheticOpaque));
		Item.itemsList[FCBetterThanWolves.fcAestheticOpaque.blockID] = new DecoItemBlockAestheticOpaque(FCBetterThanWolves.fcAestheticOpaque.blockID - 256);
		bonePillar = new AddonBlockDirectional(id_bonePillar, FCBetterThanWolves.fcMaterialMiscellaneous, new String[] {"decoBlockBonePillar_top"}, new String[] {"decoBlockBonePillar_side"})
				.setHardness(2.0F)
				.SetPicksEffectiveOn()
				.SetBuoyancy(1.0F)
				.setStepSound(Block.soundStoneFootstep)
				.setCreativeTab(CreativeTabs.tabBlock)
				.setUnlocalizedName("decoBlockBonePillar");
		Item.itemsList[bonePillar.blockID] = new DecoItemBlockWithCustomSound(bonePillar.blockID - 256);

		bonePillar.setStepSound(stepSoundBone);
		FCBetterThanWolves.fcBlockBoneSlab.setStepSound(stepSoundBone);

		//Ender Pearl
		Item.enderPearl = Item.replaceItem(Item.enderPearl.itemID, DecoItemEnderPearl.class).setUnlocalizedName("enderPearl");

		//Buttons
		Block.woodenButton = Block.replaceBlock(Block.woodenButton.blockID, DecoBlockButtonWood.class, Block.planks, 0);
		Block.stoneButton = Block.replaceBlock(Block.stoneButton.blockID, DecoBlockButtonStone.class, Block.stone, 0);

		buttonSpruce = new DecoBlockButtonWood(id_buttonSpruce, Block.planks, 1).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("decoBlockButtonSpruce");
		buttonBirch = new DecoBlockButtonWood(id_buttonBirch, Block.planks, 2).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("decoBlockButtonBirch");
		buttonJungle = new DecoBlockButtonWood(id_buttonJungle, Block.planks, 3).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("decoBlockButtonJungle");
		buttonBlood = new DecoBlockButtonWood(id_buttonBlood, Block.planks, 4).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("decoBlockButtonBlood");
		buttonCherry = new DecoBlockButtonWood(id_buttonCherry, Block.planks, 5).setHardness(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("decoBlockButtonCherry");

		buttonInfusedStone = new DecoBlockButtonStone(id_buttonInfusedStone, infusedStone, 0).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockButtonInfusedStone");
		buttonGranite = new DecoBlockButtonStone(id_buttonGranite, stoneTypes, 0).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockButtonGranite");
		buttonAndesite = new DecoBlockButtonStone(id_buttonAndesite, stoneTypes, 1).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockButtonAndesite");
		buttonDiorite = new DecoBlockButtonStone(id_buttonDiorite, stoneTypes, 2).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockButtonDiorite");
		buttonSandstone = new DecoBlockButtonStone(id_buttonSandstone, Block.sandStone, 3).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockButtonSandstone");
		buttonRedSandstone = new DecoBlockButtonStone(id_buttonRedSandstone, redSandStone, 3).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decoBlockButtonRedSandstone");

		DecoManager.Register(buttonSpruce);
		DecoManager.Register(buttonBirch);
		DecoManager.Register(buttonJungle);
		DecoManager.Register(buttonBlood);
		DecoManager.Register(buttonCherry);

		DecoManager.Register(buttonInfusedStone);
		DecoManager.Register(buttonGranite);
		DecoManager.Register(buttonAndesite);
		DecoManager.Register(buttonDiorite);
		DecoManager.Register(buttonSandstone);
		DecoManager.Register(buttonRedSandstone);

		//Trees
		cherrySapling = new DecoBlockSaplingCherry(id_cherrySapling);
		DecoManager.Register(cherrySapling);
		cherryLeaves = new DecoBlockLeavesCherry(id_cherryLeaves);
		DecoManager.Register(cherryLeaves);
		acaciaSapling = new DecoBlockSaplingAcacia(id_acaciaSapling);
		DecoManager.Register(acaciaSapling);
		acaciaLeaves = new DecoBlockLeavesAcacia(id_acaciaLeaves);
		DecoManager.Register(acaciaLeaves);
		
		autumnLeaves = new DecoBlockLeavesAutumn(id_autumnLeaves);
		Item.itemsList[autumnLeaves.blockID] = new DecoItemBlockMulti(autumnLeaves, DecoBlockLeavesAutumn.LEAF_TYPES);
		autumnSapling = new DecoBlockSaplingAutumn(id_autumnSapling);
		Item.itemsList[autumnSapling.blockID] = new DecoItemBlockMulti(autumnSapling, DecoBlockLeavesAutumn.LEAF_TYPES);

		//Leaves, vines, and webs
		Block.leaves = (BlockLeaves) Block.replaceBlock(Block.leaves.blockID, DecoBlockLeaves.class);
		Block.vine = Block.replaceBlock(Block.vine.blockID, DecoBlockVine.class);
		Item.itemsList[Block.vine.blockID] = new ItemColored(Block.vine.blockID - 256, false);
		FCBetterThanWolves.fcBlockBloodLeaves = new DecoBlockLeavesBlood(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockBloodLeaves));
		FCBetterThanWolves.fcBlockWeb = new DecoBlockWeb(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWeb));

		//Hedge
		hedge = (BlockLeaves) new DecoBlockHedge(id_hedge).setCreativeTab(CreativeTabs.tabDecorations).setUnlocalizedName("decoBlockHedge");
		DecoManager.Register(hedge, new String[] {"oak", "spruce", "birch", "jungle"});

		hedgeOakStairs = new DecoBlockStairsHedge(id_hedgeOakStairs, hedge, 0, true).setUnlocalizedName("decoBlockHedgeOakStairs");
		hedgeSpruceStairs = new DecoBlockStairsHedge(id_hedgeSpruceStairs, hedge, 1, true).setUnlocalizedName("decoBlockHedgeSpruceStairs");
		hedgeBirchStairs = new DecoBlockStairsHedge(id_hedgeBirchStairs, hedge, 2, true).setUnlocalizedName("decoBlockHedgeBirchStairs");
		hedgeJungleStairs = new DecoBlockStairsHedge(id_hedgeJungleStairs, hedge, 3, true).setUnlocalizedName("decoBlockHedgeJungleStairs");
		hedgeBloodStairs = new DecoBlockStairsHedge(id_hedgeBloodStairs, FCBetterThanWolves.fcBlockBloodLeaves, 0, true).setUnlocalizedName("decoBlockHedgeBloodStairs");
		hedgeCherryStairs = new DecoBlockStairsHedge(id_hedgeCherryStairs, cherryLeaves, 0, false).setUnlocalizedName("decoBlockHedgeCherryStairs");

		hedgeOakSidingAndCorner = new DecoBlockHedgeSidingAndCornerDecorativeWall(id_hedgeOakSidingandCorner, materialHedge, "leaves", 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeOakSiding", "Oak Hedge", true, hedge, 0);
		hedgeOakMouldingAndDecorative = new DecoBlockHedgeMouldingAndDecorative(id_hedgeOakMoulingAndDecorative, materialHedge, "leaves", "leaves", hedgeOakSidingAndCorner.blockID, 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeOakMoulding", true, hedge, 0);
		hedgeSpruceSidingAndCorner = new DecoBlockHedgeSidingAndCornerDecorativeWall(id_hedgeSpruceSidingAndCorner, materialHedge, "leaves", 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeSpruceSiding", "Spruce Hedge", true, hedge, 1);
		hedgeSpruceMouldingAndDecorative = new DecoBlockHedgeMouldingAndDecorative(id_hedgeSpruceMouldingAndDecorative, materialHedge, "leaves", "leaves", hedgeSpruceSidingAndCorner.blockID, 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeSpruceMoulding", true, hedge, 1);
		hedgeBirchSidingAndCorner = new DecoBlockHedgeSidingAndCornerDecorativeWall(id_hedgeBirchSidingAndCorner, materialHedge, "leaves_spruce", 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeBirchSiding", "Birch Hedge", true, hedge, 2);
		hedgeBirchMouldingAndDecorative = new DecoBlockHedgeMouldingAndDecorative(id_hedgeBirchMouldingAndDecorative, materialHedge, "leaves_spruce", "leaves_spruce", hedgeBirchSidingAndCorner.blockID, 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeBirchMoulding", true, hedge, 2);
		hedgeJungleSidingAndCorner = new DecoBlockHedgeSidingAndCornerDecorativeWall(id_hedgeJungleSidingAndCorner, materialHedge, "leaves_jungle", 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeJungleSiding", "Jungle Hedge", true, hedge, 3);
		hedgeJungleMouldingAndDecorative = new DecoBlockHedgeMouldingAndDecorative(id_hedgeJungleMouldingAndDecorative, materialHedge, "leaves_jungle", "leaves_jungle", hedgeJungleSidingAndCorner.blockID, 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeJungleMoulding", true, hedge, 3);
		hedgeBloodSidingAndCorner = new DecoBlockHedgeSidingAndCornerDecorativeWall(id_hedgeBloodSidingAndCorner, materialHedge, "leaves", 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeBloodSiding", "Blood Hedge", true, FCBetterThanWolves.fcBlockBloodLeaves, 0);
		hedgeBloodMouldingAndDecorative = new DecoBlockHedgeMouldingAndDecorative(id_hedgeBloodMouldingAndDecorative, materialHedge, "leaves", "leaves", hedgeBloodSidingAndCorner.blockID, 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeBloodMoulding", true, FCBetterThanWolves.fcBlockBloodLeaves, 0);
		hedgeCherrySidingAndCorner = new DecoBlockHedgeSidingAndCornerDecorativeWall(id_hedgeCherrySidingAndCorner, materialHedge, "decoBlockLeavesCherry", 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeCherrySiding", "Cherry Hedge", false, cherryLeaves, 0);
		hedgeCherryMouldingAndDecorative = new DecoBlockHedgeMouldingAndDecorative(id_hedgeCherryMouldingAndDecorative, materialHedge, "decoBlockLeavesCherry", "decoBlockLeavesCherry", hedgeCherrySidingAndCorner.blockID, 0.2F, 0.2F, Block.soundGrassFootstep, "decoBlockHedgeCherryMoulding", false, cherryLeaves, 0);

		DecoManager.Register(hedgeOakStairs);
		DecoManager.Register(hedgeSpruceStairs);
		DecoManager.Register(hedgeBirchStairs);
		DecoManager.Register(hedgeJungleStairs);
		DecoManager.Register(hedgeBloodStairs);
		DecoManager.Register(hedgeCherryStairs);

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
		Block.thinGlass = Block.replaceBlock(Block.thinGlass.blockID, DecoBlockPane.class, "glass", "thinglass_top", Material.glass, false);
		Block.fenceIron = Block.replaceBlock(Block.fenceIron.blockID, DecoBlockIronBars.class);
		FCBetterThanWolves.fcBlockGrate = new DecoBlockGrate(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockGrate));
		FCBetterThanWolves.fcBlockWickerPane = new DecoBlockWickerPane(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWickerPane));
		FCBetterThanWolves.fcBlockSlats = new DecoBlockSlats(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockSlats));

		//Rope
		ropeCoil = new AddonBlockDirectional(id_ropeCoil, FCBetterThanWolves.fcMaterialMiscellaneous, new String[] {"fcBlockRope_top"}, new String[] {"fcBlockRope_side"})
				.setHardness(2.0F)
				.SetAxesEffectiveOn(true)
				.setStepSound(Block.soundGrassFootstep)
				.setCreativeTab(CreativeTabs.tabBlock)
				.setUnlocalizedName("decoBlockRopeCoil");
		DecoManager.Register(ropeCoil);

		//Chain
		chain = new DecoBlockChain(id_chain);
		DecoManager.Register(chain, "Chain");
		chainItem = new DecoItemChain(id_chainItem);
		DecoManager.Name(chainItem, "Chain");
		chainCoil = new AddonBlockDirectional(id_chainCoil, Material.iron, new String[] {"decoBlockChainCoil_top"}, new String[] {"decoBlockChainCoil_side"})
				.setHardness(2.0F)
				.SetPicksEffectiveOn(true)
				.setStepSound(Block.soundMetalFootstep)
				.setCreativeTab(CreativeTabs.tabBlock)
				.setUnlocalizedName("decoBlockChainCoil");
		Item.itemsList[chainCoil.blockID] = new DecoItemBlockWithCustomSound(chainCoil.blockID - 256);

		//Cocoa
		FCBetterThanWolves.fcItemCocoaBeans = new DecoItemCocoaBeans(FCBetterThanWolves.fcItemCocoaBeans.itemID - 256);
		Block.cocoaPlant = Block.replaceBlock(Block.cocoaPlant.blockID, DecoBlockCocoa.class);

		//Hemp
		FCBetterThanWolves.fcBlockHempCrop = new DecoBlockHempCrop(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockHempCrop));

		//Lily pads
		Item.itemsList[Block.waterlily.blockID] = new DecoItemBlockLilyPad(Block.waterlily.blockID - 256);

		//Tall grass
		Block.tallGrass = (BlockTallGrass) Block.replaceBlock(Block.tallGrass.blockID, DecoBlockTallGrass.class);
		Item.itemsList[Block.tallGrass.blockID] = (new ItemColored(Block.tallGrass.blockID - 256, true)).setBlockNames(new String[] {"shrub", "grass", "fern"});
		
		//Spider eyes
		Item.spiderEye = Item.replaceItem(Item.spiderEye.itemID, DecoItemSpiderEye.class);
		spiderEyeBlock = new DecoBlockSpiderEye(id_spiderEyeBlock);
		DecoManager.Register(spiderEyeBlock);
		spiderEyeSlab = new DecoBlockSpiderEyeSlab(id_spiderEyeSlab);
		DecoManager.Register(spiderEyeSlab);

		//Fluids
		Block.waterStill = (BlockFluid) Block.replaceBlock(Block.waterStill.blockID, DecoBlockWaterStationary.class, Material.water).setLightOpacity(3);
		Block.waterMoving = (BlockFluid) Block.replaceBlock(Block.waterMoving.blockID, DecoBlockWaterFlowing.class, Material.water).setLightOpacity(3);
		Block.lavaStill = (BlockFluid) Block.replaceBlock(Block.lavaStill.blockID, DecoBlockLavaStationary.class, Material.lava);
		Block.lavaMoving = (BlockFluid) Block.replaceBlock(Block.lavaMoving.blockID, DecoBlockLavaFlowing.class, Material.lava);

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
		//scaffolding = new AddonBlockScaffolding(id_scaffolding);
		//AddonManager.Register(scaffolding, "Scaffolding");
	}

	private void addToolDefs() {
		chiselDiamond = new DecoItemChiselDiamond(id_chiselDiamond);
		FCBetterThanWolves.fcItemChiselIron = new DecoItemChiselIron(FCBetterThanWolves.fcItemChiselIron.itemID - 256);

		//Allows custom saw recipes
		FCBetterThanWolves.fcSaw = new DecoBlockSaw((FCBlockSaw) FCBetterThanWolves.fcSaw, DecoManager.ReplaceBlockID(FCBetterThanWolves.fcSaw));
		
		FCBetterThanWolves.fcBlockDispenser = new DecoBlockBlockDispenser(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockDispenser));

		//Hopper
		//FCBetterThanWolves.fcHopper = new AddonBlockHopper(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcHopper));
		//TileEntity.ReplaceVanillaMapping(FCTileEntityHopper.class, AddonTileEntityHopper.class, "Hopper");

		//Shears - for added efficient blocks
		Item.shears = (ItemShears) Item.replaceItem(Item.shears.itemID, DecoItemShears.class).setUnlocalizedName("shears");

		shearsDiamond = (DecoItemShearsDiamond) new DecoItemShearsDiamond(id_shearsDiamond).setUnlocalizedName("decoItemShearsDiamond");

		//Name Tags
		nameTag = new DecoItemNameTag(id_nameTag);
	}

	private void addlayerDefs() {
		//public static Block layerDirt, layerGrass, layerGravel, layerSand, layerRedSand, layerCoarseDirt, layerPodzol, layerPackedEarth;
		layerDirt = new DecoBlockLayer(id_layerDirt, Block.dirt).SetShovelsEffectiveOn().setUnlocalizedName("dirtLayer");
		layerGrass = new DecoBlockLayer(id_layerGrass, Block.grass).SetShovelsEffectiveOn().setUnlocalizedName("grassLayer");
		layerGravel = new DecoBlockLayer(id_layerGravel, Block.gravel).SetShovelsEffectiveOn().setUnlocalizedName("gravelLayer");
		layerSand = new DecoBlockLayer(id_layerSand, Block.sand).SetShovelsEffectiveOn().setUnlocalizedName("sandLayer");
		layerRedSand = new DecoBlockLayer(id_layerRedSand, redSand).SetShovelsEffectiveOn().setUnlocalizedName("redSandLayer");
		layerCoarseDirt = new DecoBlockLayer(id_layerCoarseDirt, coarseDirt).SetShovelsEffectiveOn().setUnlocalizedName("coarseDirtLayer");
		layerPodzol = new DecoBlockLayer(id_layerPodzol, podzol).SetShovelsEffectiveOn().setUnlocalizedName("podzolLayer");
		layerPackedEarth = new DecoBlockLayer(id_layerPackedEarth, FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 6).SetShovelsEffectiveOn().setUnlocalizedName("packedEarthLayer");
		layerDirtLoose = new DecoBlockLayer(id_layerDirtLoose, FCBetterThanWolves.fcBlockDirtLoose).SetShovelsEffectiveOn().setUnlocalizedName("looseDirtLayer");
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
		DecoManager.Name(layerDirt, "Dirt Layer");
		//AddonManager.Name(layerGrass, "Grass Layer");
		DecoManager.Name(layerGravel, "Gravel Layer");
		DecoManager.Name(layerSand, "Sand Layer");
		DecoManager.Name(layerRedSand, "Red Sand Layer");
		DecoManager.Name(layerCoarseDirt, "Coarse Dirt Layer");
		//AddonManager.Name(layerPodzol, "Podzol Layer");
		DecoManager.Name(layerPackedEarth, "Packed Earth Layer");
		DecoManager.Name(layerDirtLoose, "Loose Dirt Layer");
	}

	private void addSubBlockReplaceDefs() {
		//Walls
		FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner),  Material.rock, "fcBlockDecorativeStoneBrick", 1.5F, 10.0F, Block.soundStoneFootstep, "fcStoneBrickSiding", "Stone Brick").SetPicksEffectiveOn();
		FCBetterThanWolves.fcBlockBrickSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockBrickSidingAndCorner), Material.rock, "fcBlockDecorativeBrick", 2.0F, 10.0F, Block.soundStoneFootstep, "fcBrickSiding","Brick").SetPicksEffectiveOn();
		FCBetterThanWolves.fcBlockSandstoneSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockSandstoneSidingAndCorner), new String[] {"fcBlockDecorativeSandstone_bottom", "fcBlockDecorativeSandstone_top", "fcBlockDecorativeSandstone_side"}, 0.8F, 1.34F, Block.soundStoneFootstep, "fcSandstoneSiding", "Sandstone");
		FCBetterThanWolves.fcBlockWhiteStoneSidingAndCorner = new DecoBlockSidingAndCornerDecorativeWall(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockWhiteStoneSidingAndCorner),  Material.rock, "fcBlockDecorativeWhiteStone", 1.5F, 10.0F, Block.soundStoneFootstep, "fcWhiteStoneSiding", "White Stone").SetPicksEffectiveOn();
		FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner = (new DecoBlockSidingAndCornerDecorativeWall(DecoManager.ReplaceBlockID(FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner), Material.rock, "fcBlockDecorativeStone", 1.5F, 10.0F, Block.soundStoneFootstep, "fcStoneSiding", "Stone")).SetPicksEffectiveOn();

		Block.netherFence = Block.replaceBlock(Block.netherFence.blockID, DecoBlockFence.class, "netherBrick", FCBetterThanWolves.fcMaterialNetherRock);
		
		Block.cobblestoneWall = Block.replaceBlock(Block.cobblestoneWall.blockID, DecoBlockWall.class, Block.cobblestone);
	}

	private void addExtraSubBlockDefs() {
		//Edging
		//stoneBrickEdging = new AddonBlockEdging(id_stoneBrickEdging, Block.stoneBrick, 0).setUnlocalizedName("stoneBrickEdging");
		//AddonManager.Register(stoneBrickEdging, "Stone Brick Edging");
	}

	private void addEntityDefs() {
		//Custom entities
		EntityList.AddMapping(DecoEntityFallingConcrete.class, "FallingConcrete", id_entityFallingConcrete);

		//Item frame
		Item.itemFrame = Item.replaceItem(Item.itemFrame.itemID, DecoItemFrame.class).setUnlocalizedName("frame");
		EntityList.ReplaceExistingMapping(DecoEntityItemFrame.class, "ItemFrame");

		//Painting
		Item.painting = Item.replaceItem(Item.painting.itemID, DecoItemPainting.class).setUnlocalizedName("painting");
		EntityList.ReplaceExistingMapping(DecoEntityPainting.class, "Painting");

		//Canvas
		FCBetterThanWolves.fcItemCanvas = new DecoItemCanvas(FCBetterThanWolves.fcItemCanvas.itemID - 256);
		EntityList.ReplaceExistingMapping(DecoEntityCanvas.class, "fcCanvas");
	}
}