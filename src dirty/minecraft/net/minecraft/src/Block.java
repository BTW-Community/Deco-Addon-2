package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class Block
{
    /**
     * used as foreach item, if item.tab = current tab, display it on the screen
     */
    private CreativeTabs displayOnCreativeTab;
    public static final StepSound soundPowderFootstep = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound soundWoodFootstep = new StepSound("wood", 1.0F, 1.0F);
    public static final StepSound soundGravelFootstep = new StepSound("gravel", 1.0F, 1.0F);
    public static final StepSound soundGrassFootstep = new StepSound("grass", 1.0F, 1.0F);
    public static final StepSound soundStoneFootstep = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound soundMetalFootstep = new StepSound("stone", 1.0F, 1.5F);
    public static final StepSound soundGlassFootstep = new StepSoundStone("stone", 1.0F, 1.0F);
    public static final StepSound soundClothFootstep = new StepSound("cloth", 1.0F, 1.0F);
    public static final StepSound soundSandFootstep = new StepSound("sand", 1.0F, 1.0F);
    public static final StepSound soundSnowFootstep = new StepSound("snow", 1.0F, 1.0F);
    public static final StepSound soundLadderFootstep = new StepSoundSand("ladder", 1.0F, 1.0F);
    public static final StepSound soundAnvilFootstep = new StepSoundAnvil("anvil", 0.3F, 1.0F);

    /** List of ly/ff (BlockType) containing the already registered blocks. */
    public static final Block[] blocksList = new Block[4096];

    /**
     * An array of 4096 booleans corresponding to the result of the isOpaqueCube() method for each block ID
     */
    public static final boolean[] opaqueCubeLookup = new boolean[4096];

    /** How much light is subtracted for going through this block */
    public static final int[] lightOpacity = new int[4096];

    /** Array of booleans that tells if a block can grass */
    public static final boolean[] canBlockGrass = new boolean[4096];

    /** Amount of light emitted */
    public static final int[] lightValue = new int[4096];

    /**
     * Flag if block ID should use the brightest neighbor light value as its own
     */
    public static boolean[] useNeighborBrightness = new boolean[4096];
    public static final Block stone = new FCBlockStone(1);
    public static final BlockGrass grass = new FCBlockGrass(2);
    public static final Block dirt = new FCBlockDirt(3);
    public static final Block cobblestone = (new FCBlockCobblestone(4)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("stonebrick").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block planks = new FCBlockPlanks(5);
    public static final Block sapling = (new FCBlockSapling(6)).setHardness(0.0F).SetBuoyant().setStepSound(soundGrassFootstep).setUnlocalizedName("sapling");
    public static final Block bedrock = new FCBlockBedrock(7);
    public static final BlockFluid waterMoving = (BlockFluid)(new FCBlockWaterFlowing(8, Material.water)).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats();
    public static final Block waterStill = (new FCBlockWaterStationary(9, Material.water)).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats();
    public static final BlockFluid lavaMoving = (BlockFluid)(new FCBlockLavaFlowing(10, Material.lava)).setHardness(0.0F).setLightValue(1.0F).setUnlocalizedName("lava").disableStats();

    /** Stationary lava source block */
    public static final Block lavaStill = (new FCBlockLavaStationary(11, Material.lava)).setHardness(100.0F).setLightValue(1.0F).setUnlocalizedName("lava").disableStats();
    public static final Block sand = new FCBlockSand(12);
    public static final Block gravel = new FCBlockGravel(13);
    public static final Block oreGold = (new FCBlockOreGold(14)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreGold");
    public static final Block oreIron = (new FCBlockOreIron(15)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreIron");
    public static final Block oreCoal = (new FCBlockOreCoal(16)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreCoal");
    public static final Block wood = new FCBlockLog(17);
    public static final BlockLeaves leaves = new FCBlockLeaves(18);
    public static final Block sponge = (new BlockSponge(19)).setHardness(0.6F).setStepSound(soundGrassFootstep).setUnlocalizedName("sponge");
    public static final Block glass = (new FCBlockGlass(20, Material.glass, false)).setHardness(0.3F).setStepSound(soundGlassFootstep).setUnlocalizedName("glass");
    public static final Block oreLapis = (new FCBlockOreLapis(21)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreLapis");
    public static final Block blockLapis = (new Block(22, Material.rock)).SetPicksEffectiveOn().setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("blockLapis").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block dispenser = new FCBlockDispenserVanilla(23);
    public static final Block sandStone = new FCBlockSandStone(24);
    public static final Block music = new FCBlockNote(25);
    public static final Block bed = (new FCBlockBed(26)).setHardness(0.2F).SetBuoyant().setUnlocalizedName("bed").disableStats();
    public static final Block railPowered = (new BlockRailPowered(27)).SetPicksEffectiveOn().setHardness(0.7F).setStepSound(soundMetalFootstep).setUnlocalizedName("goldenRail");
    public static final Block railDetector = (new FCBlockDetectorRail(28)).setHardness(0.7F).setStepSound(soundMetalFootstep).setUnlocalizedName("detectorRail");
    public static final BlockPistonBase pistonStickyBase = (BlockPistonBase)(new FCBlockPistonBase(29, true)).setUnlocalizedName("pistonStickyBase");
    public static final Block web = new FCBlockWeb(30);
    public static final BlockTallGrass tallGrass = new FCBlockTallGrass(31);
    public static final BlockDeadBush deadBush = new FCBlockDeadBush(32);
    public static final BlockPistonBase pistonBase = (BlockPistonBase)(new FCBlockPistonBase(33, false)).setUnlocalizedName("pistonBase");
    public static final BlockPistonExtension pistonExtension = new FCBlockPistonExtension(34);
    public static final Block cloth = new FCBlockCloth();
    public static final BlockPistonMoving pistonMoving = new FCBlockPistonMoving(36);
    public static final BlockFlower plantYellow = (BlockFlower)(new FCBlockFlowerBlossom(37)).setUnlocalizedName("flower");
    public static final BlockFlower plantRed = (BlockFlower)(new FCBlockFlowerBlossom(38)).setUnlocalizedName("rose");
    public static final BlockFlower mushroomBrown = (BlockFlower)(new FCBlockMushroomBrown(39, "mushroom_brown")).setHardness(0.0F).SetBuoyant().setStepSound(soundGrassFootstep).setUnlocalizedName("mushroom");
    public static final BlockFlower mushroomRed = (BlockFlower)(new FCBlockMushroom(40, "mushroom_red")).setHardness(0.0F).SetBuoyant().setStepSound(soundGrassFootstep).setUnlocalizedName("mushroom");
    public static final Block blockGold = (new FCBlockOreStorage(41)).setHardness(3.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("blockGold");
    public static final Block blockIron = (new FCBlockOreStorage(42)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("blockIron");

    /** stoneDoubleSlab */
    public static final BlockHalfSlab stoneDoubleSlab = (BlockHalfSlab)(new FCBlockStep(43, true)).SetPicksEffectiveOn().setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("stoneSlab");

    /** stoneSingleSlab */
    public static final BlockHalfSlab stoneSingleSlab = (BlockHalfSlab)(new FCBlockStep(44, false)).SetPicksEffectiveOn().setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("stoneSlab");
    public static final Block brick = (new FCBlockBrick(45)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("brick").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block tnt = new FCBlockPowderKeg(46);
    public static final Block bookShelf = new FCBlockBookshelf(47);
    public static final Block cobblestoneMossy = new FCBlockCobblestoneMossy(48);
    public static final Block obsidian = new FCBlockObsidian(49);
    public static final Block torchWood = new FCBlockTorchLegacy(50);
    public static final BlockFire fire = (BlockFire)(new FCBlockFire(51)).setHardness(0.0F).setLightValue(1.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("fire").disableStats();
    public static final Block mobSpawner = new FCBlockMobSpawner(52);
    public static final Block stairsWoodOak = (new FCBlockStairsWood(53, planks, 0)).setUnlocalizedName("stairsWood");
    public static final BlockChest chest = (BlockChest)(new FCBlockChest(54)).setCreativeTab((CreativeTabs)null);
    public static final BlockRedstoneWire redstoneWire = (BlockRedstoneWire)(new FCBlockRedstoneWire(55)).setHardness(0.0F).setStepSound(soundPowderFootstep).setUnlocalizedName("redstoneDust").disableStats();
    public static final Block oreDiamond = (new FCBlockOreDiamond(56)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreDiamond");
    public static final Block blockDiamond = (new FCBlockOreStorage(57)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("blockDiamond");
    public static final Block workbench = new FCBlockWorkbench(58);
    public static final Block crops = (new FCBlockWheatLegacy(59)).SetBuoyant().setUnlocalizedName("crops");
    public static final Block tilledField = new FCBlockFarmlandLegacyUnfertilized(60);
    public static final Block furnaceIdle = new FCBlockFurnace(61, false);
    public static final Block furnaceBurning = new FCBlockFurnace(62, true);
    public static final Block signPost = new FCBlockSign(63, true);
    public static final Block doorWood = new FCBlockDoorWood(64);
    public static final Block ladder = new FCBlockLegacyLadder(65);
    public static final Block rail = new FCBlockRailRegular(66);
    public static final Block stairsCobblestone = new FCBlockStairsCobblestone(67);
    public static final Block signWall = new FCBlockSignWall(68);
    public static final Block lever = (new FCBlockLever(69)).setHardness(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("lever");
    public static final Block pressurePlateStone = new FCBlockPressurePlateStone(70);
    public static final Block doorIron = (new FCBlockDoor(71, Material.iron)).setHardness(5.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("doorIron").disableStats();
    public static final Block pressurePlatePlanks = new FCBlockPressurePlatePlanks(72);
    public static final Block oreRedstone = (new FCBlockRedstoneOre(73, false)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreRedstone").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block oreRedstoneGlowing = (new FCBlockRedstoneOre(74, true)).setLightValue(0.625F).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreRedstone").setCreativeTab((CreativeTabs)null);
    public static final Block torchRedstoneIdle = (new BlockRedstoneTorch(75, false)).setUnlocalizedName("notGate");
    public static final Block torchRedstoneActive = (new BlockRedstoneTorch(76, true)).setLightValue(0.5F).setUnlocalizedName("notGate").setCreativeTab(CreativeTabs.tabRedstone);
    public static final Block stoneButton = (new FCBlockButtonStone(77)).setHardness(0.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("button");
    public static final Block snow = new FCBlockSnowCover(78);
    public static final Block ice = (new FCBlockIce(79)).setHardness(0.5F).SetBuoyant().setLightOpacity(3).setStepSound(soundGlassFootstep).setUnlocalizedName("ice");
    public static final Block blockSnow = new FCBlockSnowLegacy(80);
    public static final Block cactus = new FCBlockCactus(81);
    public static final Block blockClay = (new FCBlockClay(82)).setHardness(0.6F).setUnlocalizedName("clay");
    public static final Block reed = (new FCBlockReed(83)).setHardness(0.0F).SetBuoyant().setStepSound(soundGrassFootstep).setUnlocalizedName("reeds").disableStats();
    public static final Block jukebox = new FCBlockJukebox(84);
    public static final Block fence = new FCBlockFenceWood(85);
    public static final Block pumpkin = new FCBlockPumpkinCarved(86);
    public static final Block netherrack = new FCBlockNetherrack(87);
    public static final Block slowSand = (new FCBlockSoulSand(88)).setHardness(0.5F).setStepSound(soundSandFootstep).setUnlocalizedName("hellsand");
    public static final Block glowStone = new FCBlockGlowStone(89);

    /** The purple teleport blocks inside the obsidian circle */
    public static final BlockPortal portal = new FCBlockPortal(90);
    public static final Block pumpkinLantern = new FCBlockJackOLantern(91);
    public static final Block cake = (new FCBlockCake(92)).setHardness(0.5F).setStepSound(soundClothFootstep).setUnlocalizedName("cake").disableStats();
    public static final BlockRedstoneRepeater redstoneRepeaterIdle = new FCBlockRedstoneRepeater(93, false);
    public static final BlockRedstoneRepeater redstoneRepeaterActive = new FCBlockRedstoneRepeater(94, true);

    /**
     * April fools secret locked chest, only spawns on new chunks on 1st April.
     */
    public static final Block lockedChest = (new BlockLockedChest(95)).setHardness(0.0F).setLightValue(1.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("lockedchest").setTickRandomly(true);
    public static final Block trapdoor = new FCBlockTrapDoor(96);
    public static final Block silverfish = new FCBlockSilverfish(97);
    public static final Block stoneBrick = new FCBlockStoneBrick(98);
    public static final Block mushroomCapBrown = new FCBlockMushroomCapLegacy(99, 0);
    public static final Block mushroomCapRed = new FCBlockMushroomCapLegacy(100, 1);
    public static final Block fenceIron = new FCBlockIronBars(101);
    public static final Block thinGlass = (new FCBlockPane(102, "glass", "thinglass_top", Material.glass, false)).setHardness(0.3F).SetPicksEffectiveOn().setStepSound(soundGlassFootstep).setUnlocalizedName("thinGlass");
    public static final Block melon = (new FCBlockMelon(103)).setHardness(1.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("melon");
    public static final Block pumpkinStem = new FCBlockStem(104, pumpkin);
    public static final Block melonStem = new FCBlockStem(105, melon);
    public static final Block vine = new FCBlockVine(106);
    public static final Block fenceGate = new FCBlockFenceGate(107);
    public static final Block stairsBrick = (new FCBlockStairsBrick(108)).setUnlocalizedName("stairsBrick");
    public static final Block stairsStoneBrick = new FCBlockStairsStoneBrick(109);
    public static final BlockMycelium mycelium = new FCBlockMycelium(110);
    public static final Block waterlily = (new FCBlockLilyPad(111)).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("waterlily");
    public static final Block netherBrick = new FCBlockNetherBrick(112);
    public static final Block netherFence = (new FCBlockFence(113, "netherBrick", FCBetterThanWolves.fcMaterialNetherRock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("netherFence");
    public static final Block stairsNetherBrick = new FCBlockStairsNetherBrick(114);
    public static final Block netherStalk = (new FCBlockNetherStalk(115)).setUnlocalizedName("netherStalk");
    public static final Block enchantmentTable = (new FCBlockEnchantmentTable(116)).setHardness(5.0F).setResistance(2000.0F).setUnlocalizedName("enchantmentTable");
    public static final Block brewingStand = (new FCBlockBrewingStand(117)).setHardness(0.5F).setLightValue(0.125F).setUnlocalizedName("brewingStand");
    public static final BlockCauldron cauldron = (BlockCauldron)(new FCBlockVanillaCauldron(118)).setHardness(2.0F).setUnlocalizedName("cauldron");
    public static final Block endPortal = (new FCBlockEndPortal(119, Material.portal)).setHardness(-1.0F).setResistance(6000000.0F);
    public static final Block endPortalFrame = (new FCBlockEndPortalFrame(120)).setStepSound(soundGlassFootstep).setLightValue(0.125F).setHardness(-1.0F).setUnlocalizedName("endPortalFrame").setResistance(6000000.0F).setCreativeTab(CreativeTabs.tabDecorations);

    /** The rock found in The End. */
    public static final Block whiteStone = (new FCBlockEndStone(121, Material.rock)).setHardness(3.0F).setResistance(15.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("whiteStone").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block dragonEgg = (new FCBlockDragonEgg(122)).setHardness(3.0F).setResistance(15.0F).setStepSound(soundStoneFootstep).setLightValue(0.125F).setUnlocalizedName("dragonEgg");
    public static final Block redstoneLampIdle = (new FCBlockRedstoneLight(123, false)).setHardness(0.3F).setStepSound(soundGlassFootstep).setUnlocalizedName("redstoneLight").setCreativeTab(CreativeTabs.tabRedstone);
    public static final Block redstoneLampActive = (new FCBlockRedstoneLight(124, true)).setHardness(0.3F).setStepSound(soundGlassFootstep).setUnlocalizedName("redstoneLight");
    public static final BlockHalfSlab woodDoubleSlab = new FCBlockWoodSlab(125, true);
    public static final BlockHalfSlab woodSingleSlab = new FCBlockWoodSlab(126, false);
    public static final Block cocoaPlant = (new FCBlockCocoa(127)).setHardness(0.2F).setResistance(5.0F).SetBuoyant().setStepSound(soundWoodFootstep).setUnlocalizedName("cocoa");
    public static final Block stairsSandStone = (new FCBlockStairsSandStone(128)).setUnlocalizedName("stairsSandStone");
    public static final Block oreEmerald = (new FCBlockOreEmerald(129)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreEmerald");
    public static final Block enderChest = (new FCBlockEnderChest(130)).setHardness(22.5F).setResistance(1000.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("enderChest").setLightValue(0.5F);
    public static final BlockTripWireSource tripWireSource = new FCBlockTripWireSource(131);
    public static final Block tripWire = (new FCBlockTripWire(132)).setUnlocalizedName("tripWire");
    public static final Block blockEmerald = (new FCBlockOreStorage(133)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("blockEmerald");
    public static final Block stairsWoodSpruce = (new FCBlockStairsWood(134, planks, 1)).setUnlocalizedName("stairsWoodSpruce");
    public static final Block stairsWoodBirch = (new FCBlockStairsWood(135, planks, 2)).setUnlocalizedName("stairsWoodBirch");
    public static final Block stairsWoodJungle = (new FCBlockStairsWood(136, planks, 3)).setUnlocalizedName("stairsWoodJungle");
    public static final Block commandBlock = (new BlockCommandBlock(137)).setUnlocalizedName("commandBlock");
    public static final BlockBeacon beacon = (BlockBeacon)(new FCBlockBeacon(138)).setUnlocalizedName("beacon").setLightValue(1.0F);
    public static final Block cobblestoneWall = (new FCBlockWall(139, cobblestone)).setUnlocalizedName("cobbleWall");
    public static final Block flowerPot = (new FCBlockFlowerPot(140)).setHardness(0.0F).setStepSound(soundPowderFootstep).setUnlocalizedName("flowerPot");
    public static final Block carrot = (new FCBlockCarrot(141)).setUnlocalizedName("carrots");
    public static final Block potato = (new FCBlockPotato(142)).setUnlocalizedName("potatoes");
    public static final Block woodenButton = (new FCBlockButtonWood(143)).setHardness(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("button");
    public static final Block skull = new FCBlockSkull(144);
    public static final Block anvil = new FCBlockAnvil(145);
    public static final Block chestTrapped = (new FCBlockStub(146)).setUnlocalizedName("chestTrap");
    public static final Block pressurePlateGold = (new FCBlockStub(147)).setUnlocalizedName("weightedPlate_light");
    public static final Block pressurePlateIron = (new FCBlockStub(148)).setUnlocalizedName("weightedPlate_heavy");
    public static final BlockComparator redstoneComparatorIdle = (BlockComparator)(new FCBlockComparator(149, false)).setHardness(0.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("comparator").disableStats();
    public static final BlockComparator redstoneComparatorActive = (BlockComparator)(new FCBlockComparator(150, true)).setHardness(0.0F).setLightValue(0.625F).setStepSound(soundWoodFootstep).setUnlocalizedName("comparator").disableStats();
    public static final BlockDaylightDetector daylightSensor = (BlockDaylightDetector)(new FCBlockDaylightDetector(151)).setHardness(0.2F).setStepSound(soundWoodFootstep).setUnlocalizedName("daylightDetector");
    public static final Block blockRedstone = (new BlockPoweredOre(152)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("blockRedstone").setLightValue(0.75F);
    public static final Block oreNetherQuartz = new FCBlockNetherQuartzOre(153);
    public static final BlockHopper hopperBlock = new FCBlockVanillaHopper(154);
    public static final Block blockNetherQuartz = new FCBlockBlackStone(155);
    public static final Block stairsNetherQuartz = new FCBlockBlackStoneStairs(156);
    public static final Block railActivator = (new FCBlockStub(157)).setUnlocalizedName("activatorRail");
    public static final Block dropper = (new FCBlockStub(158)).setUnlocalizedName("dropper");

    /** ID of the block. */
    public final int blockID;

    /** Indicates how many hits it takes to break a block. */
    protected float blockHardness;

    /** Indicates the blocks resistance to explosions. */
    protected float blockResistance;

    /**
     * set to true when Block's constructor is called through the chain of super()'s. Note: Never used
     */
    protected boolean blockConstructorCalled = true;

    /**
     * If this field is true, the block is counted for statistics (mined or placed)
     */
    protected boolean enableStats = true;

    /**
     * Flags whether or not this block is of a type that needs random ticking. Ref-counted by ExtendedBlockStorage in
     * order to broadly cull a chunk from the random chunk update list for efficiency's sake.
     */
    protected boolean needsRandomTick;

    /** true if the Block contains a Tile Entity */
    protected boolean isBlockContainer;

    /** minimum X for the block bounds (local coordinates) */
    protected double minX = 0.0D;

    /** minimum Y for the block bounds (local coordinates) */
    protected double minY = 0.0D;

    /** minimum Z for the block bounds (local coordinates) */
    protected double minZ = 0.0D;

    /** maximum X for the block bounds (local coordinates) */
    protected double maxX = 1.0D;

    /** maximum Y for the block bounds (local coordinates) */
    protected double maxY = 1.0D;

    /** maximum Z for the block bounds (local coordinates) */
    protected double maxZ = 1.0D;

    /** Sound of stepping on the block */
    public StepSound stepSound;
    public float blockParticleGravity;

    /** Block material definition. */
    public Material blockMaterial;

    /**
     * Determines how much velocity is maintained while moving on top of this block
     */
    public float slipperiness;

    /** The unlocalized name of this block. */
    private String unlocalizedName;
    protected Icon blockIcon;
    private static final int[] m_iRotatedFacingsAroundJClockwise;
    private static final int[] m_iRotatedFacingsAroundJCounterclockwise;
    private static final int[] m_iCycledFacings;
    private static final int[] m_iCycledFacingsReversed;
    private int m_iDefaultFurnaceBurnTime = 0;
    private boolean m_bCanBeCookedByKiln = false;
    private int m_iItemIndexDroppedWhenCookedByKiln = -1;
    private int m_iItemDamageDroppedWhenCookedByKiln = 0;
    private boolean m_bShovelsEffectiveOn = false;
    private boolean m_bPicksEffectiveOn = false;
    private boolean m_bAxesEffectiveOn = false;
    private boolean m_bHoesEffectiveOn = false;
    private boolean m_bChiselsEffectiveOn = false;
    private boolean m_bChiselsCanHarvest = false;
    private float m_fBuoyancy = -1.0F;
    protected int m_iFilterablePropertiesBitfield = 0;
    private static final int m_iLoadedRangeToCheckFalling = 32;
    private AxisAlignedBB m_fixedBlockBounds = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    private boolean m_bFixedBlockBoundsSet = false;
    private int m_iHerbivoreItemFoodValue = 0;
    private int m_iBirdItemFoodValue = 0;
    private int m_iPigItemFoodValue = 0;
    public RenderBlocks m_currentBlockRenderer = null;

    protected Block(int par1, Material par2Material)
    {
        this.stepSound = soundPowderFootstep;
        this.blockParticleGravity = 1.0F;
        this.slipperiness = 0.6F;

        if (blocksList[par1] != null)
        {
            throw new IllegalArgumentException("Slot " + par1 + " is already occupied by " + blocksList[par1] + " when adding " + this);
        }
        else
        {
            this.blockMaterial = par2Material;
            blocksList[par1] = this;
            this.blockID = par1;
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            opaqueCubeLookup[par1] = this.isOpaqueCube();
            lightOpacity[par1] = this.isOpaqueCube() ? 255 : 0;
            canBlockGrass[par1] = !par2Material.getCanBlockGrass();
            useNeighborBrightness[par1] = false;
            this.SetFilterableProperties(1);
        }
    }

    /**
     * This method is called on a block after all other blocks gets already created. You can use it to reference and
     * configure something on the block that needs the others ones.
     */
    protected void initializeBlock() {}

    /**
     * Sets the footstep sound for the block. Returns the object for convenience in constructing.
     */
    protected Block setStepSound(StepSound par1StepSound)
    {
        this.stepSound = par1StepSound;
        return this;
    }

    /**
     * Sets how much light is blocked going through this block. Returns the object for convenience in constructing.
     */
    protected Block setLightOpacity(int par1)
    {
        lightOpacity[this.blockID] = par1;
        return this;
    }

    /**
     * Sets the amount of light emitted by a block from 0.0f to 1.0f (converts internally to 0-15). Returns the object
     * for convenience in constructing.
     */
    protected Block setLightValue(float par1)
    {
        lightValue[this.blockID] = (int)(15.0F * par1);
        return this;
    }

    /**
     * Sets the the blocks resistance to explosions. Returns the object for convenience in constructing.
     */
    protected Block setResistance(float par1)
    {
        this.blockResistance = par1 * 3.0F;
        return this;
    }

    public static boolean isNormalCube(int par0)
    {
        Block var1 = blocksList[par0];
        return var1 == null ? false : var1.blockMaterial.isOpaque() && var1.renderAsNormalBlock();
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return true;
    }

    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return !this.blockMaterial.blocksMovement();
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 0;
    }

    /**
     * Sets how many hits it takes to break a block.
     */
    protected Block setHardness(float par1)
    {
        this.blockHardness = par1;

        if (this.blockResistance < par1 * 5.0F)
        {
            this.blockResistance = par1 * 5.0F;
        }

        return this;
    }

    /**
     * This method will make the hardness of the block equals to -1, and the block is indestructible.
     */
    protected Block setBlockUnbreakable()
    {
        this.setHardness(-1.0F);
        return this;
    }

    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
        return this.blockHardness;
    }

    /**
     * Sets whether this block type will receive random update ticks
     */
    protected Block setTickRandomly(boolean par1)
    {
        this.needsRandomTick = par1;
        return this;
    }

    /**
     * Returns whether or not this block is of a type that needs random ticking. Called for ref-counting purposes by
     * ExtendedBlockStorage in order to broadly cull a chunk from the random chunk update list for efficiency's sake.
     */
    public boolean getTickRandomly()
    {
        return this.needsRandomTick;
    }

    public boolean hasTileEntity()
    {
        return this.isBlockContainer;
    }

    /**
     * Sets the bounds of the block.  minX, minY, minZ, maxX, maxY, maxZ
     */
    protected final void setBlockBounds(float par1, float par2, float par3, float par4, float par5, float par6) {}

    /**
     * How bright to render this block based on the light its receiving. Args: iBlockAccess, x, y, z
     */
    public float getBlockBrightness(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return par1IBlockAccess.getBrightness(par2, par3, par4, lightValue[par1IBlockAccess.getBlockId(par2, par3, par4)]);
    }

    /**
     * Goes straight to getLightBrightnessForSkyBlocks for Blocks, does some fancy computing for Fluids
     */
    public int getMixedBrightnessForBlock(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return par1IBlockAccess.getLightBrightnessForSkyBlocks(par2, par3, par4, lightValue[par1IBlockAccess.getBlockId(par2, par3, par4)]);
    }

    /**
     * Returns Returns true if the given side of this block type should be rendered (if it's solid or not), if the
     * adjacent block is at the given coordinates. Args: blockAccess, x, y, z, side
     */
    public boolean isBlockSolid(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return par1IBlockAccess.getBlockMaterial(par2, par3, par4).isSolid();
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return this.getIcon(par5, par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return this.blockIcon;
    }

    /**
     * Returns the block texture based on the side being looked at.  Args: side
     */
    public final Icon getBlockTextureFromSide(int par1)
    {
        return this.getIcon(par1, 0);
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
        AxisAlignedBB var8 = this.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);

        if (var8 != null && par5AxisAlignedBB.intersectsWith(var8))
        {
            par6List.add(var8);
        }
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return true;
    }

    /**
     * Returns whether this block is collideable based on the arguments passed in Args: blockMetaData, unknownFlag
     */
    public boolean canCollideCheck(int par1, boolean par2)
    {
        return this.isCollidable();
    }

    /**
     * Returns if this block is collidable (only used by Fire). Args: x, y, z
     */
    public boolean isCollidable()
    {
        return true;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {}

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {}

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5) {}

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {}

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World par1World)
    {
        return 10;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {}

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {}

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID;
    }

    /**
     * Drops the specified block items
     */
    public final void dropBlockAsItem(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        this.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, 1.0F, par6);
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        if (!par1World.isRemote)
        {
            int var8 = this.quantityDroppedWithBonus(par7, par1World.rand);

            for (int var9 = 0; var9 < var8; ++var9)
            {
                if (par1World.rand.nextFloat() <= par6)
                {
                    int var10 = this.idDropped(par5, par1World.rand, par7);

                    if (var10 > 0)
                    {
                        this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(var10, 1, this.damageDropped(par5)));
                    }
                }
            }
        }
    }

    /**
     * Spawns EntityItem in the world for the given ItemStack if the world is not remote.
     */
    protected void dropBlockAsItem_do(World par1World, int par2, int par3, int par4, ItemStack par5ItemStack)
    {
        if (!par1World.isRemote && par1World.getGameRules().getGameRuleBooleanValue("doTileDrops"))
        {
            float var6 = 0.7F;
            double var7 = (double)(par1World.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
            double var9 = (double)(par1World.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
            double var11 = (double)(par1World.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
            EntityItem var13 = new EntityItem(par1World, (double)par2 + var7, (double)par3 + var9, (double)par4 + var11, par5ItemStack);
            var13.delayBeforeCanPickup = 10;
            par1World.spawnEntityInWorld(var13);
        }
    }

    /**
     * called by spawner, ore, redstoneOre blocks
     */
    protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {}

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return 0;
    }

    /**
     * Returns how much this block can resist explosions from the passed in entity.
     */
    public float getExplosionResistance(Entity par1Entity)
    {
        return this.blockResistance / 5.0F;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion) {}

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 0;
    }

    public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5, ItemStack par6ItemStack)
    {
        return this.canPlaceBlockOnSide(par1World, par2, par3, par4, par5);
    }

    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5)
    {
        return this.canPlaceBlockAt(par1World, par2, par3, par4);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        int var5 = par1World.getBlockId(par2, par3, par4);
        return var5 == 0 || blocksList[var5].blockMaterial.isReplaceable();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        return false;
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {}

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        return par9;
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {}

    /**
     * Can add to the passed in vector for a movement vector to be applied to the entity. Args: x, y, z, entity, vec3d
     */
    public void velocityToAddToEntity(World par1World, int par2, int par3, int par4, Entity par5Entity, Vec3 par6Vec3) {}

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {}

    /**
     * returns the block bounderies minX value
     */
    public final double getBlockBoundsMinX()
    {
        return this.minX;
    }

    /**
     * returns the block bounderies maxX value
     */
    public final double getBlockBoundsMaxX()
    {
        return this.maxX;
    }

    /**
     * returns the block bounderies minY value
     */
    public final double getBlockBoundsMinY()
    {
        return this.minY;
    }

    /**
     * returns the block bounderies maxY value
     */
    public final double getBlockBoundsMaxY()
    {
        return this.maxY;
    }

    /**
     * returns the block bounderies minZ value
     */
    public final double getBlockBoundsMinZ()
    {
        return this.minZ;
    }

    /**
     * returns the block bounderies maxZ value
     */
    public final double getBlockBoundsMaxZ()
    {
        return this.maxZ;
    }

    public int getBlockColor()
    {
        return 16777215;
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1)
    {
        return 16777215;
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return 16777215;
    }

    /**
     * Returns true if the block is emitting indirect/weak redstone power on the specified side. If isBlockNormalCube
     * returns true, standard redstone propagation rules will apply instead and this will not be called. Args: World, X,
     * Y, Z, side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return 0;
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return false;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {}

    /**
     * Returns true if the block is emitting direct/strong redstone power on the specified side. Args: World, X, Y, Z,
     * side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return 0;
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender() {}

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
        par2EntityPlayer.AddHarvestBlockExhaustion(this.blockID, par3, par4, par5, par6);

        if (this.canSilkHarvest(par6) && EnchantmentHelper.getSilkTouchModifier(par2EntityPlayer))
        {
            ItemStack var8 = this.createStackedBlock(par6);

            if (var8 != null)
            {
                this.dropBlockAsItem_do(par1World, par3, par4, par5, var8);
            }
        }
        else
        {
            int var7 = EnchantmentHelper.getFortuneModifier(par2EntityPlayer);
            this.dropBlockAsItem(par1World, par3, par4, par5, par6, var7);
        }
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return this.renderAsNormalBlock() && !this.isBlockContainer;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        int var2 = 0;

        if (this.blockID >= 0 && this.blockID < Item.itemsList.length && Item.itemsList[this.blockID].getHasSubtypes())
        {
            var2 = par1;
        }

        return new ItemStack(this.blockID, 1, var2);
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        return this.quantityDropped(par2Random);
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        return true;
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack) {}

    /**
     * Called after a block is placed
     */
    public void onPostBlockPlaced(World par1World, int par2, int par3, int par4, int par5) {}

    public Block setUnlocalizedName(String par1Str)
    {
        this.unlocalizedName = par1Str;
        return this;
    }

    /**
     * Gets the localized name of this block. Used for the statistics page.
     */
    public String getLocalizedName()
    {
        return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
    }

    /**
     * Returns the unlocalized name of this block.
     */
    public String getUnlocalizedName()
    {
        return "tile." + this.unlocalizedName;
    }

    /**
     * Returns the unlocalized name without the tile. prefix. Caution: client-only.
     */
    public String getUnlocalizedName2()
    {
        return this.unlocalizedName;
    }

    /**
     * Called when the block receives a BlockEvent - see World.addBlockEvent. By default, passes it on to the tile
     * entity at this location. Args: world, x, y, z, blockID, EventID, event parameter
     */
    public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        return false;
    }

    /**
     * Return the state of blocks statistics flags - if the block is counted for mined and placed.
     */
    public boolean getEnableStats()
    {
        return this.enableStats;
    }

    /**
     * Disable statistics for the block, the block will no count for mined or placed.
     */
    protected Block disableStats()
    {
        this.enableStats = false;
        return this;
    }

    /**
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    public int getMobilityFlag()
    {
        return this.blockMaterial.getMaterialMobility();
    }

    /**
     * Returns the default ambient occlusion value based on block opacity
     */
    public float getAmbientOcclusionLightValue(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return par1IBlockAccess.isBlockNormalCube(par2, par3, par4) ? 0.2F : 1.0F;
    }

    /**
     * Block's chance to react to an entity falling on it.
     */
    public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6) {}

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return this.blockID;
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return this.damageDropped(par1World.getBlockMetadata(par2, par3, par4));
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    /**
     * Returns the CreativeTab to display the given block on.
     */
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return this.displayOnCreativeTab;
    }

    /**
     * Sets the CreativeTab to display this block on.
     */
    public Block setCreativeTab(CreativeTabs par1CreativeTabs)
    {
        this.displayOnCreativeTab = par1CreativeTabs;
        return this;
    }

    /**
     * Called when the block is attempted to be harvested
     */
    public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {}

    /**
     * Called when this block is set (with meta data).
     */
    public void onSetBlockIDWithMetaData(World par1World, int par2, int par3, int par4, int par5) {}

    /**
     * currently only used by BlockCauldron to incrament meta-data during rain
     */
    public void fillWithRain(World par1World, int par2, int par3, int par4) {}

    /**
     * Returns true only if block is flowerPot
     */
    public boolean isFlowerPot()
    {
        return false;
    }

    public boolean func_82506_l()
    {
        return true;
    }

    /**
     * Return whether this block can drop from an explosion.
     */
    public boolean canDropFromExplosion(Explosion par1Explosion)
    {
        return true;
    }

    /**
     * Returns true if the given block ID is equivalent to this one. Example: redstoneTorchOn matches itself and
     * redstoneTorchOff, and vice versa. Most blocks only match themselves.
     */
    public boolean isAssociatedBlockID(int par1)
    {
        return this.blockID == par1;
    }

    /**
     * Static version of isAssociatedBlockID.
     */
    public static boolean isAssociatedBlockID(int par0, int par1)
    {
        return par0 == par1 ? true : (par0 != 0 && par1 != 0 && blocksList[par0] != null && blocksList[par1] != null ? blocksList[par0].isAssociatedBlockID(par1) : false);
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride()
    {
        return false;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return 0;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(this.unlocalizedName);
    }

    /**
     * Gets the icon name of the ItemBlock corresponding to this block. Used by hoppers.
     */
    public String getItemIconName()
    {
        return null;
    }

    public boolean IsNormalCube(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.blockMaterial.isOpaque() && this.renderAsNormalBlock();
    }

    public int PreBlockPlacedBy(World var1, int var2, int var3, int var4, int var5, EntityLiving var6)
    {
        return var5;
    }

    public void SetBlockMaterial(Material var1)
    {
        this.blockMaterial = var1;
        canBlockGrass[this.blockID] = !var1.getCanBlockGrass();
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.updateTick(var1, var2, var3, var4, var5);
    }

    public void ClientNotificationOfMetadataChange(World var1, int var2, int var3, int var4, int var5, int var6) {}

    public void OnArrowImpact(World var1, int var2, int var3, int var4, EntityArrow var5) {}

    public void OnArrowCollide(World var1, int var2, int var3, int var4, EntityArrow var5) {}

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        float var5 = 1.0F;

        if (this.blockMaterial != Material.ground && this.blockMaterial != Material.grass)
        {
            var5 *= 1.2F;
        }

        return var5;
    }

    public void OnPlayerWalksOnBlock(World var1, int var2, int var3, int var4, EntityPlayer var5) {}

    public boolean DoesBlockHopperEject(World var1, int var2, int var3, int var4)
    {
        return this.blockMaterial.isSolid();
    }

    public boolean DoesBlockHopperInsert(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean GetIsBlockWarm(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public StepSound GetStepSound(World var1, int var2, int var3, int var4)
    {
        return this.stepSound;
    }

    public void ClientBreakBlock(World var1, int var2, int var3, int var4, int var5, int var6) {}

    public void ClientBlockAdded(World var1, int var2, int var3, int var4) {}

    public boolean HasStrata()
    {
        return false;
    }

    public int GetMetadataConversionForStrataLevel(int var1, int var2)
    {
        return var2;
    }

    public float getExplosionResistance(Entity var1, World var2, int var3, int var4, int var5)
    {
        return this.getExplosionResistance(var1);
    }

    public boolean CanBlockStayDuringGenerate(World var1, int var2, int var3, int var4)
    {
        return this.canBlockStay(var1, var2, var3, var4);
    }

    public boolean IsStairBlock()
    {
        return false;
    }

    public boolean ShouldDeleteTileEntityOnBlockChange(int var1)
    {
        return true;
    }

    public boolean IsNaturalStone(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public static AxisAlignedBB GetFulBlockBoundingBoxFromPool(World var0, int var1, int var2, int var3)
    {
        return AxisAlignedBB.getAABBPool().getAABB((double)((float)var1), (double)((float)var2), (double)((float)var3), (double)((float)var1 + 1.0F), (double)((float)var2 + 1.0F), (double)((float)var3 + 1.0F));
    }

    public boolean CanSpitWebReplaceBlock(World var1, int var2, int var3, int var4)
    {
        return this.IsGroundCover() || this.IsAirBlock();
    }

    public boolean IsAirBlock()
    {
        return false;
    }

    public boolean IsReplaceableVegetation(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean HasWaterToSidesOrTop(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 1; var5 <= 5; ++var5)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
            int var7 = var1.getBlockId(var6.i, var6.j, var6.k);
            Block var8 = blocksList[var7];

            if (var8 != null && var8.blockMaterial == Material.water)
            {
                return true;
            }
        }

        return false;
    }

    public boolean GetPreventsFluidFlow(World var1, int var2, int var3, int var4, Block var5)
    {
        return this.blockMaterial == Material.portal ? true : this.blockMaterial.blocksMovement();
    }

    public void OnFluidFlowIntoBlock(World var1, int var2, int var3, int var4, BlockFluid var5)
    {
        this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
    }

    public boolean IsBlockClimbable(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean TriggersBuddy()
    {
        return true;
    }

    protected boolean canSilkHarvest(int var1)
    {
        return this.canSilkHarvest();
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        var1.playAuxSFX(2272, var3, var4, var5, this.blockID + (var6 << 12));
        this.DropComponentItemsOnBadBreak(var1, var3, var4, var5, var6, 1.0F);
    }

    protected void DropItemsIndividualy(World var1, int var2, int var3, int var4, int var5, int var6, int var7, float var8)
    {
        for (int var9 = 0; var9 < var6; ++var9)
        {
            if (var1.rand.nextFloat() <= var8)
            {
                ItemStack var10 = new ItemStack(var5, 1, var7);
                this.dropBlockAsItem_do(var1, var2, var3, var4, var10);
            }
        }
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        return false;
    }

    public void DropItemsOnDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        if (!var1.isRemote && this.canDropFromExplosion(var5))
        {
            float var6 = 1.0F;

            if (var5 != null)
            {
                var6 /= var5.explosionSize;
            }

            int var7 = var1.getBlockMetadata(var2, var3, var4);

            if (!this.DropComponentItemsOnBadBreak(var1, var2, var3, var4, var7, var6))
            {
                this.dropBlockAsItemWithChance(var1, var2, var3, var4, var7, var6, 0);
            }
        }
    }

    protected void OnDirtDugWithImproperTool(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < 6; ++var5)
        {
            this.NotifyNeighborDirtDugWithImproperTool(var1, var2, var3, var4, var5);
        }
    }

    protected void OnDirtSlabDugWithImproperTool(World var1, int var2, int var3, int var4, boolean var5)
    {
        for (int var6 = 0; var6 < 6; ++var6)
        {
            if ((!var5 || var6 != 0) && (var5 || var6 != 1))
            {
                this.NotifyNeighborDirtDugWithImproperTool(var1, var2, var3, var4, var6);
            }
        }
    }

    protected void NotifyNeighborDirtDugWithImproperTool(World var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
        int var7 = var1.getBlockId(var6.i, var6.j, var6.k);
        Block var8 = blocksList[var7];

        if (var8 != null)
        {
            var8.OnNeighborDirtDugWithImproperTool(var1, var6.i, var6.j, var6.k, GetOppositeFacing(var5));
        }
    }

    protected void OnNeighborDirtDugWithImproperTool(World var1, int var2, int var3, int var4, int var5) {}

    public boolean HasSmallCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return this.HasCenterHardPointToFacing(var1, var2, var3, var4, var5, var6);
    }

    public boolean HasSmallCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.HasSmallCenterHardPointToFacing(var1, var2, var3, var4, var5, false);
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return this.HasLargeCenterHardPointToFacing(var1, var2, var3, var4, var5, var6);
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.HasCenterHardPointToFacing(var1, var2, var3, var4, var5, false);
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var1.isBlockNormalCube(var2, var3, var4);
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.HasLargeCenterHardPointToFacing(var1, var2, var3, var4, var5, false);
    }

    public boolean IsBlockRestingOnThatBelow(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean IsBlockAttachedToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return false;
    }

    public void AttachToFacing(World var1, int var2, int var3, int var4, int var5) {}

    public boolean HasContactPointToFullFace(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var1.isBlockNormalCube(var2, var3, var4);
    }

    public boolean HasContactPointToSlabSideFace(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return this.HasContactPointToFullFace(var1, var2, var3, var4, var5);
    }

    public boolean HasContactPointToStairShapedFace(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.HasContactPointToFullFace(var1, var2, var3, var4, var5);
    }

    public boolean HasContactPointToStairNarrowVerticalFace(IBlockAccess var1, int var2, int var3, int var4, int var5, int var6)
    {
        return this.HasContactPointToFullFace(var1, var2, var3, var4, var5);
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean HasNeighborWithMortarInContact(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < 6; ++var5)
        {
            if (FCUtilsWorld.HasNeighborWithMortarInFullFaceContactToFacing(var1, var2, var3, var4, var5))
            {
                return true;
            }
        }

        return false;
    }

    public boolean IsStickyToSnow(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean HasStickySnowNeighborInContact(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < 6; ++var5)
        {
            if (FCUtilsWorld.HasStickySnowNeighborInFullFaceContactToFacing(var1, var2, var3, var4, var5))
            {
                return true;
            }
        }

        return false;
    }

    public int GetFurnaceBurnTime(int var1)
    {
        return this.m_iDefaultFurnaceBurnTime;
    }

    public void SetFurnaceBurnTime(int var1)
    {
        this.m_iDefaultFurnaceBurnTime = var1;
    }

    public void SetFurnaceBurnTime(FCEnumFurnaceBurnTime var1)
    {
        this.SetFurnaceBurnTime(var1.m_iBurnTime);
    }

    public boolean DoesInfiniteBurnToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return false;
    }

    public boolean DoesExtinguishFireAbove(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        if (!var6 && (var1.rand.nextInt(var5 + 10) >= 5 || var1.IsRainingAtPos(var2, var3, var4)))
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
        else
        {
            int var7 = var5 + var1.rand.nextInt(5) / 4;

            if (var7 > 15)
            {
                var7 = 15;
            }

            var1.setBlockAndMetadataWithNotify(var2, var3, var4, fire.blockID, var7);
        }
    }

    public Block SetFireProperties(int var1, int var2)
    {
        BlockFire.chanceToEncourageFire[this.blockID] = var1;
        BlockFire.abilityToCatchFire[this.blockID] = var2;
        return this;
    }

    public Block SetFireProperties(FCEnumFlammability var1)
    {
        return this.SetFireProperties(var1.m_iChanceToEncourageFire, var1.m_iAbilityToCatchFire);
    }

    public boolean GetCanBeSetOnFireDirectly(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean GetCanBeSetOnFireDirectlyByItem(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetCanBeSetOnFireDirectly(var1, var2, var3, var4);
    }

    public boolean SetOnFireDirectly(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public int GetChanceOfFireSpreadingDirectlyTo(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 0;
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean GetDoesFireDamageToEntities(World var1, int var2, int var3, int var4, Entity var5)
    {
        return this.GetDoesFireDamageToEntities(var1, var2, var3, var4);
    }

    public boolean GetDoesFireDamageToEntities(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean GetCanBlockBeIncinerated(World var1, int var2, int var3, int var4)
    {
        return fire.canBlockCatchFire(var1, var2, var3, var4) || !this.blockMaterial.blocksMovement();
    }

    public boolean GetCanBlockBeReplacedByFire(World var1, int var2, int var3, int var4)
    {
        return this.IsAirBlock();
    }

    public boolean IsIncineratedInCrucible()
    {
        return FCBlockFire.CanBlockBeDestroyedByFire(this.blockID);
    }

    public boolean CanPathThroughBlock(IBlockAccess var1, int var2, int var3, int var4, Entity var5, PathFinder var6)
    {
        return this.getBlocksMovement(var1, var2, var3, var4);
    }

    public boolean ShouldOffsetPositionIfPathingOutOf(IBlockAccess var1, int var2, int var3, int var4, Entity var5, PathFinder var6)
    {
        return !this.CanPathThroughBlock(var1, var2, var3, var4, var5, var6);
    }

    public int GetWeightOnPathBlocked(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 0;
    }

    public int AdjustPathWeightOnNotBlocked(int var1)
    {
        return var1;
    }

    public boolean IsBreakableBarricade(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean IsBreakableBarricadeOpen(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public Block SetCanBeCookedByKiln(boolean var1)
    {
        this.m_bCanBeCookedByKiln = var1;
        return this;
    }

    public boolean GetCanBeCookedByKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.m_bCanBeCookedByKiln;
    }

    public int GetCookTimeMultiplierInKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 1;
    }

    public Block SetItemIndexDroppedWhenCookedByKiln(int var1)
    {
        this.m_iItemIndexDroppedWhenCookedByKiln = var1;
        return this;
    }

    public int GetItemIndexDroppedWhenCookedByKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.m_iItemIndexDroppedWhenCookedByKiln;
    }

    public Block SetItemDamageDroppedWhenCookedByKiln(int var1)
    {
        this.m_iItemDamageDroppedWhenCookedByKiln = var1;
        return this;
    }

    public int GetItemDamageDroppedWhenCookedByKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.m_iItemDamageDroppedWhenCookedByKiln;
    }

    public void OnCookedByKiln(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetItemIndexDroppedWhenCookedByKiln(var1, var2, var3, var4);

        if (var5 >= 0)
        {
            int var6 = this.GetItemDamageDroppedWhenCookedByKiln(var1, var2, var3, var4);
            var1.setBlockToAir(var2, var3, var4);

            if (var5 > 0)
            {
                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, var5, var6);
            }
        }
    }

    public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        return this.blockMaterial.isSolid() && this.blockMaterial != Material.wood && this.blockMaterial != Material.cactus && this.blockMaterial != Material.pumpkin && this.blockMaterial != Material.leaves && this.blockMaterial != Material.plants && this.blockMaterial != Material.vine && this.blockMaterial != Material.snow && this.blockMaterial != Material.craftedSnow && this.blockMaterial != FCBetterThanWolves.fcMaterialLog && this.blockMaterial != FCBetterThanWolves.fcMaterialPlanks && this.blockMaterial != FCBetterThanWolves.fcMaterialAsh;
    }

    public boolean OnBlockSawed(World var1, int var2, int var3, int var4, int var5, int var6, int var7)
    {
        return this.OnBlockSawed(var1, var2, var3, var4);
    }

    public boolean OnBlockSawed(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetItemIDDroppedOnSaw(var1, var2, var3, var4);

        if (var5 >= 0)
        {
            int var6 = this.GetItemCountDroppedOnSaw(var1, var2, var3, var4);
            int var7 = this.GetItemDamageDroppedOnSaw(var1, var2, var3, var4);

            for (int var8 = 0; var8 < var6; ++var8)
            {
                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, var5, var7);
            }
        }
        else
        {
            if (!this.DoesBlockDropAsItemOnSaw(var1, var2, var3, var4))
            {
                return false;
            }

            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
        }

        var1.setBlockToAir(var2, var3, var4);
        return true;
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return -1;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 0;
    }

    public int GetItemDamageDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 0;
    }

    public boolean DoesBlockDropAsItemOnSaw(World var1, int var2, int var3, int var4)
    {
        return this.blockMaterial.isSolid();
    }

    public int GetMechanicalPowerLevelProvidedToAxleAtFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return 0;
    }

    public boolean AreShovelsEffectiveOn()
    {
        return this.m_bShovelsEffectiveOn;
    }

    public boolean ArePicksEffectiveOn()
    {
        return this.m_bPicksEffectiveOn;
    }

    public boolean AreAxesEffectiveOn()
    {
        return this.m_bAxesEffectiveOn;
    }

    public boolean AreHoesEffectiveOn()
    {
        return this.m_bHoesEffectiveOn;
    }

    public boolean AreChiselsEffectiveOn()
    {
        return this.m_bChiselsEffectiveOn;
    }

    public boolean AreChiselsEffectiveOn(World var1, int var2, int var3, int var4)
    {
        return this.AreChiselsEffectiveOn();
    }

    public boolean CanChiselsHarvest()
    {
        return this.m_bChiselsCanHarvest;
    }

    public Block SetShovelsEffectiveOn()
    {
        return this.SetShovelsEffectiveOn(true);
    }

    public Block SetShovelsEffectiveOn(boolean var1)
    {
        this.m_bShovelsEffectiveOn = var1;
        return this;
    }

    public Block SetPicksEffectiveOn()
    {
        return this.SetPicksEffectiveOn(true);
    }

    public Block SetPicksEffectiveOn(boolean var1)
    {
        this.m_bPicksEffectiveOn = var1;
        return this;
    }

    public Block SetAxesEffectiveOn()
    {
        return this.SetAxesEffectiveOn(true);
    }

    public Block SetAxesEffectiveOn(boolean var1)
    {
        this.m_bAxesEffectiveOn = var1;
        return this;
    }

    public Block SetHoesEffectiveOn()
    {
        return this.SetHoesEffectiveOn(true);
    }

    public Block SetHoesEffectiveOn(boolean var1)
    {
        this.m_bHoesEffectiveOn = var1;
        return this;
    }

    public Block SetChiselsEffectiveOn()
    {
        return this.SetChiselsEffectiveOn(true);
    }

    public Block SetChiselsEffectiveOn(boolean var1)
    {
        this.m_bChiselsEffectiveOn = var1;
        return this;
    }

    public Block SetChiselsCanHarvest()
    {
        return this.SetChiselsCanHarvest(true);
    }

    public Block SetChiselsCanHarvest(boolean var1)
    {
        this.m_bChiselsCanHarvest = var1;
        return this;
    }

    /**
     * Gets the hardness of block at the given coordinates in the given world, relative to the ability of the given
     * EntityPlayer.
     */
    public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5)
    {
        float var6 = this.getBlockHardness(par2World, par3, par4, par5);

        if (var6 >= 0.0F)
        {
            float var7 = par1EntityPlayer.getCurrentPlayerStrVsBlock(this, par3, par4, par5) / var6;
            return par1EntityPlayer.IsCurrentToolEffectiveOnBlock(this, par3, par4, par5) ? var7 / 30.0F : var7 / 200.0F;
        }
        else
        {
            return 0.0F;
        }
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return false;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        return false;
    }

    public int GetEfficientToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 0;
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetEfficientToolLevel(var1, var2, var3, var4);
    }

    public boolean GetIsProblemToRemove(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean GetDoesStumpRemoverWorkOnBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public Block SetBuoyancy(float var1)
    {
        this.m_fBuoyancy = var1;
        return this;
    }

    public Block SetBuoyant()
    {
        return this.SetBuoyancy(1.0F);
    }

    public Block SetNonBuoyant()
    {
        return this.SetBuoyancy(-1.0F);
    }

    public Block SetNeutralBuoyant()
    {
        return this.SetBuoyancy(0.0F);
    }

    public float GetBuoyancy(int var1)
    {
        return this.m_fBuoyancy;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 0.0F;
    }

    public boolean IsGroundCover()
    {
        return false;
    }

    public boolean AttempToSpreadGrassToBlock(World var1, int var2, int var3, int var4)
    {
        return this.GetCanGrassSpreadToBlock(var1, var2, var3, var4) && var1.GetBlockNaturalLightValueMaximum(var2, var3 + 1, var4) >= 11 && lightOpacity[var1.getBlockId(var2, var3 + 1, var4)] <= 2 && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(var1, var2, var3, var4) ? this.SpreadGrassToBlock(var1, var2, var3, var4) : false;
    }

    public boolean GetCanGrassSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean SpreadGrassToBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean GetCanGrassGrowUnderBlock(World var1, int var2, int var3, int var4, boolean var5)
    {
        return !var5 ? !this.HasLargeCenterHardPointToFacing(var1, var2, var3, var4, 0) : true;
    }

    public boolean AttempToSpreadMyceliumToBlock(World var1, int var2, int var3, int var4)
    {
        return this.GetCanMyceliumSpreadToBlock(var1, var2, var3, var4) && var1.getBlockLightValue(var2, var3 + 1, var4) >= 4 && lightOpacity[var1.getBlockId(var2, var3 + 1, var4)] <= 2 && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(var1, var2, var3, var4) ? this.SpreadMyceliumToBlock(var1, var2, var3, var4) : false;
    }

    public boolean GetCanMyceliumSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean SpreadMyceliumToBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean GetCanBlightSpreadToBlock(World var1, int var2, int var3, int var4, int var5)
    {
        return false;
    }

    public boolean IsSnowCoveringTopSurface(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 + 1, var4);

        if (var5 != 0)
        {
            Block var6 = blocksList[var5];
            Material var7 = var6.blockMaterial;

            if (var7 == Material.snow || var7 == Material.craftedSnow && var6.HasCenterHardPointToFacing(var1, var2, var3 + 1, var4, 0))
            {
                return true;
            }

            if (var6.GroundCoverRestingOnVisualOffset(var1, var2, var3 + 1, var4) < -0.99F && var1.getBlockId(var2, var3 + 2, var4) == snow.blockID)
            {
                return true;
            }
        }

        return false;
    }

    public int OnPreBlockPlacedByPiston(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        return var5;
    }

    public boolean CanBlockBePulledByPiston(World var1, int var2, int var3, int var4, int var5)
    {
        return this.getMobilityFlag() != 1 ? this.CanBlockBePushedByPiston(var1, var2, var3, var4, var5) : false;
    }

    public boolean CanBlockBePushedByPiston(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.getMobilityFlag();
        return var6 == 1 || var6 != 2 && !(this instanceof ITileEntityProvider);
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return this.AreShovelsEffectiveOn();
    }

    public int GetPistonShovelEjectDirection(World var1, int var2, int var3, int var4, int var5)
    {
        return -1;
    }

    public AxisAlignedBB GetAsPistonMovingBoundingBox(World var1, int var2, int var3, int var4)
    {
        return this.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
    }

    public int AdjustMetadataForPistonMove(int var1)
    {
        return var1;
    }

    public boolean CanContainPistonPackingToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return this.HasLargeCenterHardPointToFacing(var1, var2, var3, var4, var5, true);
    }

    public boolean IsPistonPackable(ItemStack var1)
    {
        return false;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 0;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return 0;
    }

    public int GetResultingBlockMetadataOnPistonPack(ItemStack var1)
    {
        return 0;
    }

    public void OnBrokenByPistonPush(World var1, int var2, int var3, int var4, int var5)
    {
        this.dropBlockAsItem(var1, var2, var3, var4, var5, 0);
    }

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        return true;
    }

    public int GetFilterableProperties(ItemStack var1)
    {
        return this.m_iFilterablePropertiesBitfield;
    }

    public void SetFilterableProperties(int var1)
    {
        this.m_iFilterablePropertiesBitfield = var1;
    }

    public boolean CanTransformItemIfFilter(ItemStack var1)
    {
        return false;
    }

    public boolean IsFallingBlock()
    {
        return false;
    }

    protected boolean CheckForFall(World var1, int var2, int var3, int var4)
    {
        if (this.CanFallIntoBlockAtPos(var1, var2, var3 - 1, var4) && var3 >= 0)
        {
            if (!BlockSand.fallInstantly && var1.checkChunksExist(var2 - 32, var3 - 32, var4 - 32, var2 + 32, var3 + 32, var4 + 32))
            {
                if (!var1.isRemote)
                {
                    FCEntityFallingBlock var5 = new FCEntityFallingBlock(var1, (double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, this.blockID, var1.getBlockMetadata(var2, var3, var4));
                    this.onStartFalling(var5);
                    var1.spawnEntityInWorld(var5);
                }

                return true;
            }
            else
            {
                var1.setBlockToAir(var2, var3, var4);

                while (this.CanFallIntoBlockAtPos(var1, var2, var3 - 1, var4) && var3 > 0)
                {
                    --var3;
                }

                if (var3 > 0)
                {
                    var1.setBlock(var2, var3, var4, this.blockID);
                }

                return true;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Called when the falling block entity for this block is created
     */
    protected void onStartFalling(EntityFallingSand var1) {}

    /**
     * Called when the falling block entity for this block hits the ground and turns back into a block
     */
    public void onFinishFalling(World var1, int var2, int var3, int var4, int var5)
    {
        this.NotifyNearbyAnimalsFinishedFalling(var1, var2, var3, var4);
    }

    protected void OnFallingUpdate(FCEntityFallingBlock var1) {}

    public void NotifyNearbyAnimalsFinishedFalling(World var1, int var2, int var3, int var4)
    {
        if (!var1.isRemote)
        {
            EntityPlayer var5 = var1.getClosestPlayer((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), 64.0D);

            if (var5 != null)
            {
                var1.NotifyNearbyAnimalsOfPlayerBlockAddOrRemove(var5, this, var2, var3, var4);
            }
        }
    }

    public boolean OnFinishedFalling(EntityFallingSand var1, float var2)
    {
        return true;
    }

    public boolean AttemptToCombineWithFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        return false;
    }

    public boolean CanBeCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        return false;
    }

    public void OnCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5) {}

    protected boolean CanFallIntoBlockAtPos(World var1, int var2, int var3, int var4)
    {
        Block var5 = blocksList[var1.getBlockId(var2, var3, var4)];
        return var5 == null || !var5.CanSupportFallingBlocks(var1, var2, var3, var4);
    }

    public boolean CanSupportFallingBlocks(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.HasCenterHardPointToFacing(var1, var2, var3, var4, 1, true);
    }

    protected void CheckForUnstableGround(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 1; var5 <= 16; ++var5)
        {
            int var6 = var3 - var5;

            if (var6 <= 0)
            {
                break;
            }

            if (var1.isAirBlock(var2, var6, var4))
            {
                var1.notifyBlockOfNeighborChange(var2, var6 + 1, var4, 0);
                break;
            }

            int var7 = var1.getBlockId(var2, var6, var4);

            if (var7 == fire.blockID)
            {
                var1.notifyBlockOfNeighborChange(var2, var6 + 1, var4, 0);
                break;
            }

            Block var8 = blocksList[var7];

            if (var8.blockMaterial == Material.water || var8.blockMaterial == Material.lava)
            {
                var1.notifyBlockOfNeighborChange(var2, var6 + 1, var4, 0);
                break;
            }

            if (!var8.IsFallingBlock())
            {
                break;
            }
        }
    }

    protected void ScheduleCheckForFall(World var1, int var2, int var3, int var4)
    {
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    public void OnBlockDestroyedLandingFromFall(World var1, int var2, int var3, int var4, int var5)
    {
        this.OnBlockDestroyedWithImproperTool(var1, (EntityPlayer)null, var2, var3, var4, var5);
    }

    public boolean HasFallingBlockRestingOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        Block var5 = blocksList[var1.getBlockId(var2, var3 + 1, var4)];
        return var5 != null && var5.IsFallingBlock() && var5.HasCenterHardPointToFacing(var1, var2, var3 + 1, var4, 0);
    }

    public int GetFacing(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetFacing(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetFacing(int var1)
    {
        return 0;
    }

    public void SetFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        int var7 = this.SetFacing(var6, var5);

        if (var7 != var6)
        {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var7);
        }
    }

    public int SetFacing(int var1, int var2)
    {
        return var1;
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        return this.RotateAroundJAxis(var1, var2, var3, var4, var5);
    }

    public int ConvertFacingToTopTextureRotation(int var1)
    {
        if (var1 >= 2)
        {
            if (var1 > 3)
            {
                if (var1 == 4)
                {
                    return 2;
                }

                return 1;
            }

            if (var1 == 3)
            {
                return 3;
            }
        }

        return 0;
    }

    public int ConvertFacingToBottomTextureRotation(int var1)
    {
        if (var1 >= 2)
        {
            if (var1 > 3)
            {
                if (var1 == 4)
                {
                    return 1;
                }

                return 2;
            }

            if (var1 == 3)
            {
                return 3;
            }
        }

        return 0;
    }

    public static int GetOppositeFacing(int var0)
    {
        return var0 ^ 1;
    }

    public static int RotateFacingAroundJ(int var0, boolean var1)
    {
        return var1 ? m_iRotatedFacingsAroundJCounterclockwise[var0] : m_iRotatedFacingsAroundJClockwise[var0];
    }

    public static int CycleFacing(int var0, boolean var1)
    {
        return var1 ? m_iCycledFacingsReversed[var0] : m_iCycledFacings[var0];
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.isBlockNormalCube(var2, var3, var4);
    }

    public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.isBlockNormalCube(var2, var3, var4);
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.isBlockNormalCube(var2, var3, var4);
    }

    public int RotateOnTurntable(World var1, int var2, int var3, int var4, boolean var5, int var6)
    {
        this.OnRotatedOnTurntable(var1, var2, var3, var4);

        if (!this.RotateAroundJAxis(var1, var2, var3, var4, var5))
        {
            var1.notifyBlocksOfNeighborChange(var2, var3, var4, this.blockID);
        }

        return var6;
    }

    protected void OnRotatedOnTurntable(World var1, int var2, int var3, int var4) {}

    protected int TurntableCraftingRotation(World var1, int var2, int var3, int var4, boolean var5, int var6)
    {
        ++var6;

        if (var6 >= this.GetRotationsToCraftOnTurntable(var1, var2, var3, var4))
        {
            this.OnCraftedOnTurntable(var1, var2, var3, var4);
            int var7 = this.GetNewBlockIDCraftedOnTurntable(var1, var2, var3, var4);
            int var8 = this.GetNewMetadataCraftedOnTurntable(var1, var2, var3, var4);
            int var9 = this.GetItemIDCraftedOnTurntable(var1, var2, var3, var4);
            int var10 = this.GetItemCountCraftedOnTurntable(var1, var2, var3, var4);
            int var11 = this.GetItemDamageCraftedOnTurntable(var1, var2, var3, var4);

            for (int var12 = 0; var12 < var10; ++var12)
            {
                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3 + 1, var4, var9, var11);
            }

            var1.setBlockAndMetadataWithNotify(var2, var3, var4, var7, var8);
            var6 = 0;
        }

        return var6;
    }

    public int GetRotationsToCraftOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 1;
    }

    public void OnCraftedOnTurntable(World var1, int var2, int var3, int var4)
    {
        var1.playAuxSFX(2252, var2, var3, var4, var1.getBlockId(var2, var3, var4) + (var1.getBlockMetadata(var2, var3, var4) << 12));
    }

    public int GetNewBlockIDCraftedOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 0;
    }

    public int GetNewMetadataCraftedOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 0;
    }

    public int GetItemIDCraftedOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return Item.clay.itemID;
    }

    public int GetItemCountCraftedOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 1;
    }

    public int GetItemDamageCraftedOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 0;
    }

    public boolean RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        int var7 = this.RotateMetadataAroundJAxis(var6, var5);

        if (var7 != var6)
        {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var7);
            return true;
        }
        else
        {
            return false;
        }
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        int var3 = this.GetFacing(var1);
        int var4 = RotateFacingAroundJ(var3, var2);
        return this.SetFacing(var1, var4);
    }

    public boolean CanRotateAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return false;
    }

    public boolean OnRotatedAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return true;
    }

    public int GetNewMetadataRotatedAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        return 0;
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if (this.canSilkHarvest(var5))
        {
            return this.createStackedBlock(var5);
        }
        else
        {
            int var6 = this.idDropped(var5, var1.rand, 0);
            return var6 > 0 ? new ItemStack(var6, 1, this.damageDropped(var5)) : null;
        }
    }

    public boolean IsBlockDestroyedByBlockDispenser(int var1)
    {
        return false;
    }

    public void OnRemovedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        var1.playAuxSFX(2252, var2, var3, var4, this.blockID + (var1.getBlockMetadata(var2, var3, var4) << 12));
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }

    public void OnStruckByLightning(World var1, int var2, int var3, int var4) {}

    public boolean CanMobsSpawnOn(World var1, int var2, int var3, int var4)
    {
        return this.blockMaterial.GetMobsCanSpawnOn(var1.provider.dimensionId) && this.getCollisionBoundingBoxFromPool(var1, var2, var3, var4) != null;
    }

    public float MobSpawnOnVerticalOffset(World var1, int var2, int var3, int var4)
    {
        return 0.0F;
    }

    protected void InitBlockBounds(double var1, double var3, double var5, double var7, double var9, double var11)
    {
        if (!this.m_bFixedBlockBoundsSet)
        {
            this.m_fixedBlockBounds.setBounds(var1, var3, var5, var7, var9, var11);
        }
    }

    protected void InitBlockBounds(AxisAlignedBB var1)
    {
        if (!this.m_bFixedBlockBoundsSet)
        {
            this.m_fixedBlockBounds.setBB(var1);
        }
    }

    protected AxisAlignedBB GetFixedBlockBoundsFromPool()
    {
        this.m_bFixedBlockBoundsSet = true;
        return this.m_fixedBlockBounds.MakeTemporaryCopy();
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return this.GetBlockBoundsFromPoolBasedOnState(par1World, par2, par3, par4).offset((double)par2, (double)par3, (double)par4);
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetFixedBlockBoundsFromPool();
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3)
    {
        return this.CollisionRayTraceVsBlockBounds(par1World, par2, par3, par4, par5Vec3, par6Vec3);
    }

    public MovingObjectPosition MouseOverRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        return this.collisionRayTrace(var1, var2, var3, var4, var5, var6);
    }

    public MovingObjectPosition CollisionRayTraceVsBlockBounds(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        AxisAlignedBB var7 = this.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4).offset((double)var2, (double)var3, (double)var4);
        MovingObjectPosition var8 = var7.calculateIntercept(var5, var6);

        if (var8 != null)
        {
            var8.blockX = var2;
            var8.blockY = var3;
            var8.blockZ = var4;
        }

        return var8;
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return false;
    }

    public void OnGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        var1.setBlockToAir(var2, var3, var4);
        Block var6 = blocksList[var1.getBlockId(var2, var3 - 1, var4)];

        if (var6 != null)
        {
            var6.OnVegetationAboveGrazed(var1, var2, var3 - 1, var4, var5);
        }
    }

    public void OnVegetationAboveGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5) {}

    public void NotifyNeighborsBlockDisrupted(World var1, int var2, int var3, int var4)
    {
        FCUtilsBlockPos var5 = new FCUtilsBlockPos(var2, var3, var4);
        FCUtilsBlockPos var6 = new FCUtilsBlockPos();

        for (int var7 = 0; var7 <= 5; ++var7)
        {
            var6.Set(var5);
            var6.AddFacingAsOffset(var7);
            Block var8 = blocksList[var1.getBlockId(var6.i, var6.j, var6.k)];

            if (var8 != null)
            {
                var8.OnNeighborDisrupted(var1, var6.i, var6.j, var6.k, GetOppositeFacing(var7));
            }
        }
    }

    public void OnNeighborDisrupted(World var1, int var2, int var3, int var4, int var5) {}

    public int GetHerbivoreItemFoodValue(int var1)
    {
        return this.m_iHerbivoreItemFoodValue;
    }

    public void SetHerbivoreItemFoodValue(int var1)
    {
        this.m_iHerbivoreItemFoodValue = var1;
    }

    public int GetChickenItemFoodValue(int var1)
    {
        return this.m_iBirdItemFoodValue;
    }

    public void SetChickenItemFoodValue(int var1)
    {
        this.m_iBirdItemFoodValue = var1;
    }

    public int GetPigItemFoodValue(int var1)
    {
        return this.m_iPigItemFoodValue;
    }

    public void SetPigItemFoodValue(int var1)
    {
        this.m_iPigItemFoodValue = var1;
    }

    public boolean CanDomesticatedCropsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean CanReedsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean CanSaplingsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean CanWildVegetationGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean CanNetherWartGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean CanCactusGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean IsBlockHydratedForPlantGrowthOn(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean IsConsideredNeighbouringWaterForReedGrowthOn(World var1, int var2, int var3, int var4)
    {
        for (int var5 = var2 - 1; var5 <= var2 + 1; ++var5)
        {
            for (int var6 = var4 - 1; var6 <= var4 + 1; ++var6)
            {
                if (var1.getBlockMaterial(var5, var3, var6) == Material.water)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public float GetPlantGrowthOnMultiplier(World var1, int var2, int var3, int var4, Block var5)
    {
        return 1.0F;
    }

    public boolean GetIsFertilizedForPlantGrowth(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public void NotifyOfFullStagePlantGrowthOn(World var1, int var2, int var3, int var4, Block var5) {}

    public void NotifyOfPlantAboveRemoved(World var1, int var2, int var3, int var4, Block var5) {}

    public boolean CanWeedsGrowInBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public int GetWeedsGrowthLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 0;
    }

    public void RemoveWeeds(World var1, int var2, int var3, int var4) {}

    public boolean AttemptToApplyFertilizerTo(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean GetConvertsLegacySoil(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public static boolean InstallationIntegrityTest()
    {
        return true;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        Block var6 = blocksList[par1IBlockAccess.getBlockId(par2, par3, par4)];
        return var6 != null ? var6.ShouldRenderNeighborFullFaceSide(par1IBlockAccess, par2, par3, par4, par5) : true;
    }

    public boolean ShouldRenderNeighborHalfSlabSide(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return !this.isOpaqueCube();
    }

    public boolean ShouldRenderNeighborFullFaceSide(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return !this.isOpaqueCube();
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderStandardBlock(this, var2, var3, var4);
    }

    public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5) {}

    public boolean RenderBlockWithTexture(RenderBlocks var1, int var2, int var3, int var4, Icon var5)
    {
        var1.setOverrideBlockTexture(var5);
        boolean var6;

        if (!this.renderAsNormalBlock())
        {
            var6 = this.RenderBlock(var1, var2, var3, var4);
        }
        else
        {
            var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
            var6 = var1.renderStandardBlock(this, var2, var3, var4);
        }

        var1.clearOverrideBlockTexture();
        return var6;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolForItemRender(int var1)
    {
        return this.GetFixedBlockBoundsFromPool();
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        var1.renderBlockAsItemVanilla(this, var2, var3);
    }

    public boolean DoesItemRenderAsBlock(int var1)
    {
        return RenderBlocks.DoesRenderIDRenderItemIn3d(this.getRenderType());
    }

    public void RenderCookingByKilnOverlay(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        if (var5)
        {
            IBlockAccess var6 = var1.blockAccess;

            if (!var1.hasOverrideBlockTexture() && this.GetCanBeCookedByKiln(var6, var2, var3, var4))
            {
                int var7 = var6.getBlockId(var2, var3 - 1, var4);

                if (var7 == FCBetterThanWolves.fcKiln.blockID)
                {
                    Icon var8 = FCBetterThanWolves.fcKiln.GetCookTextureForCurrentState(var6, var2, var3 - 1, var4);

                    if (var8 != null)
                    {
                        this.RenderBlockWithTexture(var1, var2, var3, var4, var8);
                    }
                }
            }
        }
    }

    public boolean ShouldRenderWhileFalling(World var1, EntityFallingSand var2)
    {
        int var3 = MathHelper.floor_double(var2.posX);
        int var4 = MathHelper.floor_double(var2.posY);
        int var5 = MathHelper.floor_double(var2.posZ);
        int var6 = var1.getBlockId(var3, var4, var5);
        return var6 != var2.blockID;
    }

    public void RenderFallingBlock(RenderBlocks var1, int var2, int var3, int var4, int var5)
    {
        var1.setRenderBounds(this.GetFixedBlockBoundsFromPool());
        var1.RenderStandardFallingBlock(this, var2, var3, var4, var5);
    }

    public boolean ShouldSideBeRenderedOnFallingBlock(int var1, int var2)
    {
        return true;
    }

    public void RenderBlockMovedByPiston(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.renderBlockAllFaces(this, var2, var3, var4);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return this.GetBlockBoundsFromPoolBasedOnState(par1World, par2, par3, par4).offset((double)par2, (double)par3, (double)par4);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, MovingObjectPosition var2)
    {
        return this.getSelectedBoundingBoxFromPool(var1, var2.blockX, var2.blockY, var2.blockZ);
    }

    public Icon GetIconByIndex(int var1)
    {
        return this.blockIcon;
    }

    public Icon GetHopperFilterIcon()
    {
        return null;
    }

    protected void RenderCrossHatch(RenderBlocks var1, int var2, int var3, int var4, Icon var5, double var6, double var8)
    {
        Tessellator var10 = Tessellator.instance;
        double var11 = (double)var2;
        double var13 = (double)var3 + var8;
        double var15 = (double)var4;
        double var17 = (double)var5.getMinU();
        double var19 = (double)var5.getMinV();
        double var21 = (double)var5.getMaxU();
        double var23 = (double)var5.getMaxV();
        double var25 = var11 + 1.0D - var6;
        double var27 = var11 + var6;
        double var31 = var15 + 1.0D;
        var10.addVertexWithUV(var25, var13 + 1.0D, var15, var17, var19);
        var10.addVertexWithUV(var25, var13 + 0.0D, var15, var17, var23);
        var10.addVertexWithUV(var25, var13 + 0.0D, var31, var21, var23);
        var10.addVertexWithUV(var25, var13 + 1.0D, var31, var21, var19);
        var10.addVertexWithUV(var25, var13 + 1.0D, var31, var17, var19);
        var10.addVertexWithUV(var25, var13 + 0.0D, var31, var17, var23);
        var10.addVertexWithUV(var25, var13 + 0.0D, var15, var21, var23);
        var10.addVertexWithUV(var25, var13 + 1.0D, var15, var21, var19);
        var10.addVertexWithUV(var27, var13 + 1.0D, var31, var17, var19);
        var10.addVertexWithUV(var27, var13 + 0.0D, var31, var17, var23);
        var10.addVertexWithUV(var27, var13 + 0.0D, var15, var21, var23);
        var10.addVertexWithUV(var27, var13 + 1.0D, var15, var21, var19);
        var10.addVertexWithUV(var27, var13 + 1.0D, var15, var17, var19);
        var10.addVertexWithUV(var27, var13 + 0.0D, var15, var17, var23);
        var10.addVertexWithUV(var27, var13 + 0.0D, var31, var21, var23);
        var10.addVertexWithUV(var27, var13 + 1.0D, var31, var21, var19);
        var27 = var11 + 1.0D;
        double var29 = var15 + var6;
        var31 = var15 + 1.0D - var6;
        var10.addVertexWithUV(var11, var13 + 1.0D, var29, var17, var19);
        var10.addVertexWithUV(var11, var13 + 0.0D, var29, var17, var23);
        var10.addVertexWithUV(var27, var13 + 0.0D, var29, var21, var23);
        var10.addVertexWithUV(var27, var13 + 1.0D, var29, var21, var19);
        var10.addVertexWithUV(var27, var13 + 1.0D, var29, var17, var19);
        var10.addVertexWithUV(var27, var13 + 0.0D, var29, var17, var23);
        var10.addVertexWithUV(var11, var13 + 0.0D, var29, var21, var23);
        var10.addVertexWithUV(var11, var13 + 1.0D, var29, var21, var19);
        var10.addVertexWithUV(var27, var13 + 1.0D, var31, var17, var19);
        var10.addVertexWithUV(var27, var13 + 0.0D, var31, var17, var23);
        var10.addVertexWithUV(var11, var13 + 0.0D, var31, var21, var23);
        var10.addVertexWithUV(var11, var13 + 1.0D, var31, var21, var19);
        var10.addVertexWithUV(var11, var13 + 1.0D, var31, var17, var19);
        var10.addVertexWithUV(var11, var13 + 0.0D, var31, var17, var23);
        var10.addVertexWithUV(var27, var13 + 0.0D, var31, var21, var23);
        var10.addVertexWithUV(var27, var13 + 1.0D, var31, var21, var19);
    }

    static
    {
        Item.itemsList[cloth.blockID] = (new ItemCloth(cloth.blockID - 256)).setUnlocalizedName("cloth");
        Item.itemsList[wood.blockID] = (new ItemMultiTextureTile(wood.blockID - 256, wood, BlockLog.woodType)).setUnlocalizedName("log");
        Item.itemsList[planks.blockID] = (new ItemMultiTextureTile(planks.blockID - 256, planks, BlockWood.woodType)).setUnlocalizedName("wood");
        Item.itemsList[silverfish.blockID] = (new ItemMultiTextureTile(silverfish.blockID - 256, silverfish, BlockSilverfish.silverfishStoneTypes)).setUnlocalizedName("monsterStoneEgg");
        Item.itemsList[stoneBrick.blockID] = (new ItemMultiTextureTile(stoneBrick.blockID - 256, stoneBrick, BlockStoneBrick.STONE_BRICK_TYPES)).setUnlocalizedName("stonebricksmooth");
        Item.itemsList[sandStone.blockID] = (new ItemMultiTextureTile(sandStone.blockID - 256, sandStone, BlockSandStone.SAND_STONE_TYPES)).setUnlocalizedName("sandStone");
        Item.itemsList[blockNetherQuartz.blockID] = (new ItemMultiTextureTile(blockNetherQuartz.blockID - 256, blockNetherQuartz, BlockQuartz.quartzBlockTypes)).setUnlocalizedName("quartzBlock");
        Item.itemsList[stoneSingleSlab.blockID] = (new ItemSlab(stoneSingleSlab.blockID - 256, stoneSingleSlab, stoneDoubleSlab, false)).setUnlocalizedName("stoneSlab");
        Item.itemsList[stoneDoubleSlab.blockID] = (new ItemSlab(stoneDoubleSlab.blockID - 256, stoneSingleSlab, stoneDoubleSlab, true)).setUnlocalizedName("stoneSlab");
        Item.itemsList[woodSingleSlab.blockID] = (new ItemSlab(woodSingleSlab.blockID - 256, woodSingleSlab, woodDoubleSlab, false)).setUnlocalizedName("woodSlab");
        Item.itemsList[woodDoubleSlab.blockID] = (new ItemSlab(woodDoubleSlab.blockID - 256, woodSingleSlab, woodDoubleSlab, true)).setUnlocalizedName("woodSlab");
        Item.itemsList[sapling.blockID] = (new ItemMultiTextureTile(sapling.blockID - 256, sapling, BlockSapling.WOOD_TYPES)).setUnlocalizedName("sapling");
        Item.itemsList[leaves.blockID] = (new ItemLeaves(leaves.blockID - 256)).setUnlocalizedName("leaves");
        Item.itemsList[vine.blockID] = new ItemColored(vine.blockID - 256, false);
        Item.itemsList[tallGrass.blockID] = (new ItemColored(tallGrass.blockID - 256, true)).setBlockNames(new String[] {"shrub", "grass", "fern"});
        Item.itemsList[snow.blockID] = new ItemSnow(snow.blockID - 256, snow);
        Item.itemsList[waterlily.blockID] = new ItemLilyPad(waterlily.blockID - 256);
        Item.itemsList[pistonBase.blockID] = new ItemPiston(pistonBase.blockID - 256);
        Item.itemsList[pistonStickyBase.blockID] = new ItemPiston(pistonStickyBase.blockID - 256);
        Item.itemsList[cobblestoneWall.blockID] = (new ItemMultiTextureTile(cobblestoneWall.blockID - 256, cobblestoneWall, BlockWall.types)).setUnlocalizedName("cobbleWall");
        Item.itemsList[anvil.blockID] = (new ItemAnvilBlock(anvil)).setUnlocalizedName("anvil");

        for (int var0 = 0; var0 < 256; ++var0)
        {
            if (blocksList[var0] != null)
            {
                if (Item.itemsList[var0] == null)
                {
                    Item.itemsList[var0] = new ItemBlock(var0 - 256);
                    blocksList[var0].initializeBlock();
                }

                if (canBlockGrass[var0] || lightOpacity[var0] == 0)
                {
                    useNeighborBrightness[var0] = true;
                }
            }
        }

        canBlockGrass[0] = true;
        StatList.initBreakableStats();
        m_iRotatedFacingsAroundJClockwise = new int[] {0, 0, 4, 5, 3, 2};
        m_iRotatedFacingsAroundJCounterclockwise = new int[] {0, 0, 5, 4, 2, 3};
        m_iCycledFacings = new int[] {4, 0, 1, 5, 3, 2};
        m_iCycledFacingsReversed = new int[] {1, 2, 5, 4, 0, 3};
    }
}
