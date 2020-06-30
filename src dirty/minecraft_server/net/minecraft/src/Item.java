package net.minecraft.src;

import java.util.Random;

public class Item
{
    private CreativeTabs tabToDisplayOn = null;

    /** The RNG used by the Item subclasses. */
    protected static Random itemRand = new Random();

    /** A 32000 elements Item array. */
    public static Item[] itemsList = new Item[32000];
    public static final int m_iFilterable_NoProperties = 0;
    public static final int m_iFilterable_SolidBlock = 1;
    public static final int m_iFilterable_Small = 2;
    public static final int m_iFilterable_Narrow = 4;
    public static final int m_iFilterable_Fine = 8;
    public static final int m_iFilterable_Thin = 16;
    public static Item shovelIron = (new FCItemShovel(0, EnumToolMaterial.IRON)).setUnlocalizedName("shovelIron");
    public static Item pickaxeIron = (new FCItemPickaxe(1, EnumToolMaterial.IRON)).setUnlocalizedName("pickaxeIron");
    public static Item axeIron = (new FCItemAxe(2, EnumToolMaterial.IRON)).setUnlocalizedName("hatchetIron");
    public static Item flintAndSteel = (new FCItemFlintAndSteel(3)).setUnlocalizedName("flintAndSteel");
    public static Item appleRed = (new ItemFood(4, 1, 0.0F, false)).SetFilterableProperties(2).setUnlocalizedName("apple");
    public static ItemBow bow = new FCItemBow(5);
    public static Item arrow = new FCItemArrow(6);
    public static Item coal = (new ItemCoal(7)).SetIncineratedInCrucible().SetFurnaceBurnTime(FCEnumFurnaceBurnTime.COAL).SetFilterableProperties(2).setUnlocalizedName("coal");
    public static Item diamond = (new Item(8)).SetFilterableProperties(2).setUnlocalizedName("diamond").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item ingotIron = (new Item(9)).setUnlocalizedName("ingotIron").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item ingotGold = (new Item(10)).setUnlocalizedName("ingotGold").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item swordIron = (new FCItemSword(11, EnumToolMaterial.IRON)).setUnlocalizedName("swordIron");
    public static Item swordWood = (new FCItemSword(12, EnumToolMaterial.WOOD)).setUnlocalizedName("swordWood");
    public static Item shovelWood = (new FCItemShovel(13, EnumToolMaterial.WOOD)).SetDamageVsEntity(2).setUnlocalizedName("shovelWood");
    public static Item pickaxeWood = (new FCItemPickaxe(14, EnumToolMaterial.WOOD, 1)).setUnlocalizedName("pickaxeWood");
    public static Item axeWood = (new FCItemAxe(15, EnumToolMaterial.WOOD)).setUnlocalizedName("hatchetWood");
    public static Item swordStone = (new FCItemSword(16, EnumToolMaterial.STONE)).setUnlocalizedName("swordStone");
    public static Item shovelStone = new FCItemShovelStone(17);
    public static Item pickaxeStone = (new FCItemPickaxe(18, EnumToolMaterial.STONE)).setUnlocalizedName("pickaxeStone");
    public static Item axeStone = (new FCItemAxe(19, EnumToolMaterial.STONE)).setUnlocalizedName("hatchetStone");
    public static Item swordDiamond = (new FCItemSword(20, EnumToolMaterial.EMERALD)).setUnlocalizedName("swordDiamond");
    public static Item shovelDiamond = (new FCItemShovel(21, EnumToolMaterial.EMERALD)).setUnlocalizedName("shovelDiamond");
    public static Item pickaxeDiamond = (new FCItemPickaxe(22, EnumToolMaterial.EMERALD)).setUnlocalizedName("pickaxeDiamond");
    public static Item axeDiamond = (new FCItemAxe(23, EnumToolMaterial.EMERALD)).setUnlocalizedName("hatchetDiamond");
    public static Item stick = new FCItemShaft(24);
    public static Item bowlEmpty = (new Item(25)).SetBuoyant().SetIncineratedInCrucible().setUnlocalizedName("bowl").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item bowlSoup = (new FCItemMushroomSoup(26, 3)).setUnlocalizedName("mushroomStew");
    public static Item swordGold = (new FCItemSword(27, EnumToolMaterial.GOLD)).setUnlocalizedName("swordGold");
    public static Item shovelGold = (new FCItemShovel(28, EnumToolMaterial.GOLD)).setUnlocalizedName("shovelGold");
    public static Item pickaxeGold = (new FCItemPickaxe(29, EnumToolMaterial.GOLD)).setUnlocalizedName("pickaxeGold");
    public static Item axeGold = (new FCItemAxe(30, EnumToolMaterial.GOLD)).setUnlocalizedName("hatchetGold");
    public static Item silk = (new Item(31)).SetBuoyant().SetBellowsBlowDistance(2).SetIncineratedInCrucible().SetFilterableProperties(18).setUnlocalizedName("string").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item feather = (new Item(32)).SetBuoyant().SetFurnaceBurnTime(FCEnumFurnaceBurnTime.KINDLING).SetIncineratedInCrucible().SetBellowsBlowDistance(3).SetFilterableProperties(18).setUnlocalizedName("feather").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item gunpowder = (new Item(33)).SetBellowsBlowDistance(3).SetFilterableProperties(8).setUnlocalizedName("sulphur").setPotionEffect(PotionHelper.gunpowderEffect).setCreativeTab(CreativeTabs.tabMaterials);
    public static Item hoeWood = (new FCItemHoe(34, EnumToolMaterial.WOOD)).setUnlocalizedName("hoeWood");
    public static Item hoeStone = (new FCItemHoe(35, EnumToolMaterial.STONE)).setUnlocalizedName("hoeStone");
    public static Item hoeIron = (new FCItemHoe(36, EnumToolMaterial.IRON)).setUnlocalizedName("hoeIron");
    public static Item hoeDiamond = (new FCItemHoe(37, EnumToolMaterial.EMERALD)).setUnlocalizedName("hoeDiamond");
    public static Item hoeGold = (new FCItemHoe(38, EnumToolMaterial.GOLD)).setUnlocalizedName("hoeGold");
    public static Item seeds = (new FCItemSeeds(39, Block.crops.blockID)).SetAsBasicChickenFood().setUnlocalizedName("seeds").setCreativeTab((CreativeTabs)null);
    public static Item wheat = new FCItemWheatLegacy(40);
    public static Item bread = (new ItemFood(41, 3, 0.25F, false)).setUnlocalizedName("bread");
    public static ItemArmor helmetLeather = (ItemArmor)(new FCItemArmorLeather(42, 0)).setUnlocalizedName("helmetCloth");
    public static ItemArmor plateLeather = (ItemArmor)(new FCItemArmorLeather(43, 1)).setUnlocalizedName("chestplateCloth");
    public static ItemArmor legsLeather = (ItemArmor)(new FCItemArmorLeather(44, 2)).setUnlocalizedName("leggingsCloth");
    public static ItemArmor bootsLeather = (ItemArmor)(new FCItemArmorLeather(45, 3)).setUnlocalizedName("bootsCloth");
    public static ItemArmor helmetChain = (ItemArmor)(new FCItemArmorChain(46, 0, 3)).setUnlocalizedName("helmetChain");
    public static ItemArmor plateChain = (ItemArmor)(new FCItemArmorChain(47, 1, 4)).setUnlocalizedName("chestplateChain");
    public static ItemArmor legsChain = (ItemArmor)(new FCItemArmorChain(48, 2, 4)).setUnlocalizedName("leggingsChain");
    public static ItemArmor bootsChain = (ItemArmor)(new FCItemArmorChain(49, 3, 2)).setUnlocalizedName("bootsChain");
    public static ItemArmor helmetIron = (ItemArmor)(new FCItemArmorIron(50, 0, 5)).setUnlocalizedName("helmetIron");
    public static ItemArmor plateIron = (ItemArmor)(new FCItemArmorIron(51, 1, 8)).setUnlocalizedName("chestplateIron");
    public static ItemArmor legsIron = (ItemArmor)(new FCItemArmorIron(52, 2, 7)).setUnlocalizedName("leggingsIron");
    public static ItemArmor bootsIron = (ItemArmor)(new FCItemArmorIron(53, 3, 4)).setUnlocalizedName("bootsIron");
    public static ItemArmor helmetDiamond = (ItemArmor)(new FCItemArmorDiamond(54, 0, 5)).setUnlocalizedName("helmetDiamond");
    public static ItemArmor plateDiamond = (ItemArmor)(new FCItemArmorDiamond(55, 1, 8)).setUnlocalizedName("chestplateDiamond");
    public static ItemArmor legsDiamond = (ItemArmor)(new FCItemArmorDiamond(56, 2, 7)).setUnlocalizedName("leggingsDiamond");
    public static ItemArmor bootsDiamond = (ItemArmor)(new FCItemArmorDiamond(57, 3, 4)).setUnlocalizedName("bootsDiamond");
    public static ItemArmor helmetGold = (ItemArmor)(new FCItemArmorGold(58, 0, 5)).setUnlocalizedName("helmetGold");
    public static ItemArmor plateGold = (ItemArmor)(new FCItemArmorGold(59, 1, 8)).setUnlocalizedName("chestplateGold");
    public static ItemArmor legsGold = (ItemArmor)(new FCItemArmorGold(60, 2, 7)).setUnlocalizedName("leggingsGold");
    public static ItemArmor bootsGold = (ItemArmor)(new FCItemArmorGold(61, 3, 4)).setUnlocalizedName("bootsGold");
    public static Item flint = new FCItemFlint(62);
    public static Item porkRaw = (new FCItemFood(63, 4, 0.25F, true, "porkchopRaw", true)).SetStandardFoodPoisoningEffect();
    public static Item porkCooked = (new ItemFood(64, 5, 0.25F, true)).setUnlocalizedName("porkchopCooked");
    public static Item painting = (new ItemHangingEntity(65, EntityPainting.class)).SetBuoyant().SetIncineratedInCrucible().setUnlocalizedName("painting");
    public static Item appleGold = (new ItemAppleGold(66, 1, 0.0F, false)).setAlwaysEdible().setPotionEffect(Potion.regeneration.id, 5, 0, 1.0F).SetNonBuoyant().SetNotIncineratedInCrucible().SetFilterableProperties(2).setUnlocalizedName("appleGold");
    public static Item sign = (new ItemSign(67)).SetBuoyant().SetIncineratedInCrucible().setUnlocalizedName("sign");
    public static Item doorWood = new FCItemDoorWood(68);
    public static Item bucketEmpty = new FCItemBucketEmpty(69);
    public static Item bucketWater = new FCItemBucketWater(70);
    public static Item bucketLava = new FCItemBucketLava(71);
    public static Item minecartEmpty = (new FCItemMinecart(72, 0)).setUnlocalizedName("minecart");
    public static Item saddle = (new ItemSaddle(73)).SetBuoyant().SetIncineratedInCrucible().setUnlocalizedName("saddle");
    public static Item doorIron = (new ItemDoor(74, Material.iron)).setUnlocalizedName("doorIron");
    public static Item redstone = new FCItemRedstone(75);
    public static Item snowball = new FCItemSnowball(76);
    public static Item boat = new FCItemBoat(77);
    public static Item leather = (new Item(78)).SetBuoyant().SetIncineratedInCrucible().SetFilterableProperties(16).setUnlocalizedName("leather").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item bucketMilk = new FCItemBucketMilk(79);
    public static Item brick = new FCItemBrick(80);
    public static Item clay = new FCItemClay(81);
    public static Item reed = (new ItemReed(82, Block.reed)).SetBuoyant().SetFurnaceBurnTime(FCEnumFurnaceBurnTime.KINDLING).SetIncineratedInCrucible().SetFilterableProperties(4).setUnlocalizedName("reeds").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item paper = (new Item(83)).SetBuoyant().SetBellowsBlowDistance(3).SetFurnaceBurnTime(FCEnumFurnaceBurnTime.KINDLING).SetIncineratedInCrucible().SetFilterableProperties(18).setUnlocalizedName("paper").setCreativeTab(CreativeTabs.tabMisc);
    public static Item book = new FCItemBook(84);
    public static Item slimeBall = new FCItemSlimeball(85);
    public static Item minecartCrate = (new FCItemMinecart(86, 1)).setUnlocalizedName("minecartChest");
    public static Item minecartPowered = (new FCItemMinecart(87, 2)).setUnlocalizedName("minecartFurnace");
    public static Item egg = new FCItemEgg(88);
    public static Item compass = (new Item(89)).setUnlocalizedName("compass").setCreativeTab(CreativeTabs.tabTools);
    public static ItemFishingRod fishingRod = new FCItemFishingRod(90);
    public static Item pocketSundial = (new Item(91)).setUnlocalizedName("clock").setCreativeTab(CreativeTabs.tabTools);
    public static Item lightStoneDust = (new Item(92)).SetBellowsBlowDistance(3).SetFilterableProperties(8).setUnlocalizedName("yellowDust").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item fishRaw = (new FCItemFood(93, 3, 0.25F, false, "fishRaw")).SetStandardFoodPoisoningEffect();
    public static Item fishCooked = (new ItemFood(94, 4, 0.25F, false)).setUnlocalizedName("fishCooked");
    public static Item dyePowder = new FCItemDye(95);
    public static Item bone = new FCItemBone(96);
    public static Item sugar = (new Item(97)).SetBuoyant().SetBellowsBlowDistance(3).SetIncineratedInCrucible().SetFilterableProperties(8).setUnlocalizedName("sugar").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item cake = (new ItemReed(98, Block.cake)).SetBuoyant().SetIncineratedInCrucible().setMaxStackSize(1).setUnlocalizedName("cake").setCreativeTab(CreativeTabs.tabFood);
    public static Item bed = (new ItemBed(99)).SetBuoyant().SetIncineratedInCrucible().setMaxStackSize(1).setUnlocalizedName("bed");
    public static Item redstoneRepeater = new FCItemRedstoneRepeater(100);
    public static Item cookie = (new ItemFood(101, 1, 1.0F, false)).setAlwaysEdible().SetFilterableProperties(2).setUnlocalizedName("cookie");
    public static ItemMap map = new FCItemMap(102);

    /**
     * Item introduced on 1.7 version, is a shear to cut leaves (you can keep the block) or get wool from sheeps.
     */
    public static ItemShears shears = (ItemShears)(new FCItemShears(103)).setUnlocalizedName("shears");
    public static Item melon = new FCItemFoodHighRes(104, 2, 0.0F, false, "melon");
    public static Item pumpkinSeeds = (new FCItemSeedFood(105, 1, 0.0F, Block.pumpkinStem.blockID)).SetAsBasicChickenFood().SetBellowsBlowDistance(2).SetFilterableProperties(8).setUnlocalizedName("seeds_pumpkin");
    public static Item melonSeeds = (new FCItemSeeds(106, Block.melonStem.blockID)).SetAsBasicChickenFood().setUnlocalizedName("seeds_melon");
    public static Item beefRaw = (new FCItemFood(107, 4, 0.25F, true, "beefRaw", true)).SetStandardFoodPoisoningEffect();
    public static Item beefCooked = (new ItemFood(108, 5, 0.25F, true)).setUnlocalizedName("beefCooked");
    public static Item chickenRaw = (new FCItemFood(109, 3, 0.25F, true, "chickenRaw")).SetStandardFoodPoisoningEffect();
    public static Item chickenCooked = (new ItemFood(110, 4, 0.25F, true)).setUnlocalizedName("chickenCooked");
    public static Item rottenFlesh = new FCItemRottenFlesh(111);
    public static Item enderPearl = (new ItemEnderPearl(112)).SetFilterableProperties(2).setUnlocalizedName("enderPearl");
    public static Item blazeRod = (new Item(113)).SetFurnaceBurnTime(FCEnumFurnaceBurnTime.BLAZE_ROD).SetFilterableProperties(4).setUnlocalizedName("blazeRod").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item ghastTear = (new Item(114)).SetFilterableProperties(2).setUnlocalizedName("ghastTear").setPotionEffect("+0-1-2-3&4-4+13").setCreativeTab(CreativeTabs.tabBrewing);
    public static Item goldNugget = (new Item(115)).SetFilterableProperties(2).setUnlocalizedName("goldNugget").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item netherStalkSeeds = (new FCItemSeeds(116, Block.netherStalk.blockID)).SetBellowsBlowDistance(1).setUnlocalizedName("netherStalkSeeds").setPotionEffect("+4");
    public static ItemPotion potion = new FCItemPotion(117);
    public static Item glassBottle = (new FCItemGlassBottle(118)).SetBuoyant().setUnlocalizedName("glassBottle");
    public static Item spiderEye = (new ItemFood(119, 2, 0.8F, false)).setPotionEffect(Potion.poison.id, 5, 0, 1.0F).SetNeutralBuoyant().SetFilterableProperties(2).setPotionEffect(PotionHelper.goldenCarrotEffect).setUnlocalizedName("spiderEye");
    public static Item fermentedSpiderEye = (new Item(120)).SetNeutralBuoyant().SetIncineratedInCrucible().SetFilterableProperties(2).setUnlocalizedName("fermentedSpiderEye").setPotionEffect(PotionHelper.fermentedSpiderEyeEffect).setCreativeTab(CreativeTabs.tabBrewing);
    public static Item blazePowder = (new Item(121)).SetBellowsBlowDistance(3).SetFilterableProperties(8).setUnlocalizedName("blazePowder").setPotionEffect(PotionHelper.blazePowderEffect).setCreativeTab(CreativeTabs.tabBrewing);
    public static Item magmaCream = (new Item(122)).SetNeutralBuoyant().setUnlocalizedName("magmaCream").setPotionEffect(PotionHelper.magmaCreamEffect).setCreativeTab(CreativeTabs.tabBrewing);
    public static Item brewingStand = (new ItemReed(123, Block.brewingStand)).setUnlocalizedName("brewingStand").setCreativeTab(CreativeTabs.tabBrewing);
    public static Item cauldron = (new ItemReed(124, Block.cauldron)).setUnlocalizedName("cauldron").setCreativeTab(CreativeTabs.tabBrewing);
    public static Item eyeOfEnder = (new ItemEnderEye(125)).SetFilterableProperties(2).setUnlocalizedName("eyeOfEnder");
    public static Item speckledMelon = (new Item(126)).setUnlocalizedName("speckledMelon").setCreativeTab(CreativeTabs.tabFood);
    public static Item monsterPlacer = (new ItemMonsterPlacer(127)).setUnlocalizedName("monsterPlacer");

    /**
     * Bottle o' Enchanting. Drops between 1 and 3 experience orbs when thrown.
     */
    public static Item expBottle = (new ItemExpBottle(128)).setUnlocalizedName("expBottle");

    /**
     * Fire Charge. When used in a dispenser it fires a fireball similiar to a Ghast's.
     */
    public static Item fireballCharge = new FCItemFireCharge(129);
    public static Item writableBook = (new ItemWritableBook(130)).SetBuoyant().SetIncineratedInCrucible().setUnlocalizedName("writingBook").setCreativeTab(CreativeTabs.tabMisc);
    public static Item writtenBook = (new ItemEditableBook(131)).SetBuoyant().SetIncineratedInCrucible().setUnlocalizedName("writtenBook");
    public static Item emerald = (new Item(132)).setUnlocalizedName("emerald").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item itemFrame = (new ItemHangingEntity(133, EntityItemFrame.class)).SetBuoyant().SetIncineratedInCrucible().SetFilterableProperties(1).setUnlocalizedName("frame");
    public static Item flowerPot = (new ItemReed(134, Block.flowerPot)).SetBuoyant().SetFilterableProperties(1).setUnlocalizedName("flowerPot").setCreativeTab(CreativeTabs.tabDecorations);
    public static Item carrot = (new FCItemSeedFood(135, 3, 0.0F, Block.carrot.blockID)).SetFilterableProperties(2).SetAsBasicPigFood().setUnlocalizedName("carrots");
    public static Item potato = (new FCItemSeedFood(136, 3, 0.0F, Block.potato.blockID)).SetFilterableProperties(2).SetAsBasicPigFood().setUnlocalizedName("potato");
    public static Item bakedPotato = (new ItemFood(137, 2, 0.0F, false)).SetFilterableProperties(2).SetAsBasicPigFood().setUnlocalizedName("potatoBaked");
    public static Item poisonousPotato = (new ItemFood(138, 1, 0.0F, false)).setPotionEffect(Potion.poison.id, 5, 0, 0.6F).SetFilterableProperties(2).setUnlocalizedName("potatoPoisonous");
    public static ItemEmptyMap emptyMap = new FCItemEmptyMap(139);
    public static Item goldenCarrot = (new ItemFood(140, 1, 0.0F, false)).SetNonBuoyant().SetFilterableProperties(2).setUnlocalizedName("carrotGolden");
    public static Item skull = (new ItemSkull(141)).SetBuoyant().SetIncineratedInCrucible().SetFilterableProperties(1).setUnlocalizedName("skull");
    public static Item carrotOnAStick = new FCItemCarrotOnAStick(142);
    public static Item netherStar = new FCItemNetherStar(143);
    public static Item pumpkinPie = (new ItemFood(144, 2, 2.5F, false)).setAlwaysEdible().setUnlocalizedName("pumpkinPie").setCreativeTab(CreativeTabs.tabFood);
    public static Item firework = (new ItemFirework(145)).setUnlocalizedName("fireworks");
    public static Item fireworkCharge = (new ItemFireworkCharge(146)).setUnlocalizedName("fireworksCharge").setCreativeTab(CreativeTabs.tabMisc);
    public static ItemEnchantedBook enchantedBook = (ItemEnchantedBook)(new FCItemEnchantedBook(147)).setMaxStackSize(1).setUnlocalizedName("enchantedBook");
    public static Item comparator = (new FCItemStub(148)).setUnlocalizedName("comparator");
    public static Item netherrackBrick = (new Item(149)).setUnlocalizedName("netherbrick");
    public static Item netherQuartz = (new FCItemNetherQuartz(150)).setUnlocalizedName("netherquartz").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item tntMinecart = (new FCItemStub(151)).setUnlocalizedName("minecartTnt");
    public static Item hopperMinecart = (new FCItemStub(152)).setUnlocalizedName("minecartHopper");
    public static Item minecartTnt = tntMinecart;
    public static Item minecartHopper = hopperMinecart;
    public static Item record13 = (new ItemRecord(2000, "13")).setUnlocalizedName("record");
    public static Item recordCat = (new ItemRecord(2001, "cat")).setUnlocalizedName("record").setCreativeTab((CreativeTabs)null);
    public static Item recordBlocks = (new ItemRecord(2002, "blocks")).setUnlocalizedName("record").setCreativeTab((CreativeTabs)null);
    public static Item recordChirp = (new ItemRecord(2003, "chirp")).setUnlocalizedName("record").setCreativeTab((CreativeTabs)null);
    public static Item recordFar = (new ItemRecord(2004, "far")).setUnlocalizedName("record").setCreativeTab((CreativeTabs)null);
    public static Item recordMall = (new ItemRecord(2005, "mall")).setUnlocalizedName("record").setCreativeTab((CreativeTabs)null);
    public static Item recordMellohi = (new ItemRecord(2006, "mellohi")).setUnlocalizedName("record").setCreativeTab((CreativeTabs)null);
    public static Item recordStal = (new ItemRecord(2007, "stal")).setUnlocalizedName("record").setCreativeTab((CreativeTabs)null);
    public static Item recordStrad = (new ItemRecord(2008, "strad")).setUnlocalizedName("record").setCreativeTab((CreativeTabs)null);
    public static Item recordWard = (new ItemRecord(2009, "ward")).setUnlocalizedName("record").setCreativeTab((CreativeTabs)null);
    public static Item record11 = (new ItemRecord(2010, "11")).setUnlocalizedName("record").setCreativeTab((CreativeTabs)null);
    public static Item recordWait = (new ItemRecord(2011, "wait")).setUnlocalizedName("record").setCreativeTab((CreativeTabs)null);

    /** The ID of this item. */
    public final int itemID;

    /** Maximum size of the stack. */
    protected int maxStackSize = 64;

    /** Maximum damage an item can handle. */
    private int maxDamage = 0;

    /** If true, render the object in full 3D, like weapons and tools. */
    protected boolean bFull3D = false;

    /**
     * Some items (like dyes) have multiple subtypes on same item, this is field define this behavior
     */
    protected boolean hasSubtypes = false;
    private Item containerItem = null;

    /**
     * The string representing this item's effect on a potion when used as an ingredient.
     */
    private String potionEffect = null;

    /** The unlocalized name of this item. */
    private String unlocalizedName;
    public static boolean m_bSuppressConflictWarnings;
    public static final int m_iBaseHerbivoreItemFoodValue = 800;
    public static final int m_iBasePigItemFoodValue = 800;
    public static final int m_iBaseChickenItemFoodValue = 1600;
    private int m_iHerbivoreFoodValue = 0;
    private int m_iBirdFoodValue = 0;
    private int m_iPigFoodValue = 0;
    private float m_fBuoyancy = -1.0F;
    private int m_iBellowsBlowDistance = 0;
    private int m_iInfernalMaxNumEnchants = 0;
    private int m_iInfernalMaxEnchantmentCost = 0;
    protected int m_iDefaultFurnaceBurnTime = 0;
    protected boolean m_bIsInceratedInCrucible = false;
    protected int m_iFilterablePropertiesBitfield = 0;

    protected Item(int par1)
    {
        this.itemID = 256 + par1;

        if (!m_bSuppressConflictWarnings && itemsList[256 + par1] != null)
        {
            System.out.println("CONFLICT @ " + par1);
        }

        itemsList[256 + par1] = this;
    }

    public Item setMaxStackSize(int par1)
    {
        this.maxStackSize = par1;
        return this;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        return false;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }

    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }

    /**
     * Returns the maximum size of the stack for a specific item. *Isn't this more a Set than a Get?*
     */
    public int getItemStackLimit()
    {
        return this.maxStackSize;
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int par1)
    {
        return 0;
    }

    public boolean getHasSubtypes()
    {
        return this.hasSubtypes;
    }

    protected Item setHasSubtypes(boolean par1)
    {
        this.hasSubtypes = par1;
        return this;
    }

    /**
     * Returns the maximum damage an item can take.
     */
    public int getMaxDamage()
    {
        return this.maxDamage;
    }

    /**
     * set max damage of an Item
     */
    protected Item setMaxDamage(int par1)
    {
        this.maxDamage = par1;
        return this;
    }

    public boolean isDamageable()
    {
        return this.maxDamage > 0 && !this.hasSubtypes;
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
        return false;
    }

    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving)
    {
        return false;
    }

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity par1Entity)
    {
        return 1;
    }

    /**
     * Called when a player right clicks an entity with an item.
     */
    public boolean useItemOnEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving)
    {
        return false;
    }

    /**
     * Sets bFull3D to True and return the object.
     */
    public Item setFull3D()
    {
        this.bFull3D = true;
        return this;
    }

    /**
     * Sets the unlocalized name of this item to the string passed as the parameter, prefixed by "item."
     */
    public Item setUnlocalizedName(String par1Str)
    {
        this.unlocalizedName = par1Str;
        return this;
    }

    /**
     * Gets the localized name of the given item stack.
     */
    public String getLocalizedName(ItemStack par1ItemStack)
    {
        String var2 = this.getUnlocalizedName(par1ItemStack);
        return var2 == null ? "" : StatCollector.translateToLocal(var2);
    }

    /**
     * Returns the unlocalized name of this item.
     */
    public String getUnlocalizedName()
    {
        return "item." + this.unlocalizedName;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return "item." + this.unlocalizedName;
    }

    public Item setContainerItem(Item par1Item)
    {
        this.containerItem = par1Item;
        return this;
    }

    /**
     * If this returns true, after a recipe involving this item is crafted the container item will be added to the
     * player's inventory instead of remaining in the crafting grid.
     */
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
    {
        return true;
    }

    /**
     * If this function returns true (or the item is damageable), the ItemStack's NBT tag will be sent to the client.
     */
    public boolean getShareTag()
    {
        return true;
    }

    public Item getContainerItem()
    {
        return this.containerItem;
    }

    /**
     * True if this Item has a container item (a.k.a. crafting result)
     */
    public boolean hasContainerItem()
    {
        return this.containerItem != null;
    }

    public String getStatName()
    {
        return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
    }

    public String func_77653_i(ItemStack par1ItemStack)
    {
        return StatCollector.translateToLocal(this.getUnlocalizedName(par1ItemStack) + ".name");
    }

    public void onUpdate(ItemStack var1, World var2, EntityPlayer var3, int var4, boolean var5) {}

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {}

    /**
     * false for all Items except sub-classes of ItemMapBase
     */
    public boolean isMap()
    {
        return false;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.none;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 0;
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {}

    /**
     * Sets the string representing this item's effect on a potion when used as an ingredient.
     */
    protected Item setPotionEffect(String par1Str)
    {
        this.potionEffect = par1Str;
        return this;
    }

    /**
     * Returns a string representing what this item does to a potion.
     */
    public String getPotionEffect()
    {
        return this.potionEffect;
    }

    /**
     * Returns true if this item serves as a potion ingredient (its ingredient information is not null).
     */
    public boolean isPotionIngredient()
    {
        return this.potionEffect != null;
    }

    public String getItemDisplayName(ItemStack par1ItemStack)
    {
        return ("" + StringTranslate.getInstance().translateNamedKey(this.getLocalizedName(par1ItemStack))).trim();
    }

    /**
     * Checks isDamagable and if it cannot be stacked
     */
    public boolean isItemTool(ItemStack par1ItemStack)
    {
        return this.getItemStackLimit() == 1 && this.isDamageable();
    }

    protected MovingObjectPosition getMovingObjectPositionFromPlayer(World par1World, EntityPlayer par2EntityPlayer, boolean par3)
    {
        float var4 = 1.0F;
        float var5 = par2EntityPlayer.prevRotationPitch + (par2EntityPlayer.rotationPitch - par2EntityPlayer.prevRotationPitch) * var4;
        float var6 = par2EntityPlayer.prevRotationYaw + (par2EntityPlayer.rotationYaw - par2EntityPlayer.prevRotationYaw) * var4;
        double var7 = par2EntityPlayer.prevPosX + (par2EntityPlayer.posX - par2EntityPlayer.prevPosX) * (double)var4;
        double var9 = par2EntityPlayer.prevPosY + (par2EntityPlayer.posY - par2EntityPlayer.prevPosY) * (double)var4 + 1.62D - (double)par2EntityPlayer.yOffset;
        double var11 = par2EntityPlayer.prevPosZ + (par2EntityPlayer.posZ - par2EntityPlayer.prevPosZ) * (double)var4;
        Vec3 var13 = par1World.getWorldVec3Pool().getVecFromPool(var7, var9, var11);
        float var14 = MathHelper.cos(-var6 * 0.017453292F - (float)Math.PI);
        float var15 = MathHelper.sin(-var6 * 0.017453292F - (float)Math.PI);
        float var16 = -MathHelper.cos(-var5 * 0.017453292F);
        float var17 = MathHelper.sin(-var5 * 0.017453292F);
        float var18 = var15 * var16;
        float var19 = var14 * var16;
        double var20 = 5.0D;
        Vec3 var22 = var13.addVector((double)var18 * var20, (double)var17 * var20, (double)var19 * var20);
        return par1World.rayTraceBlocks_do_do(var13, var22, par3, !par3);
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 0;
    }

    /**
     * returns this;
     */
    public Item setCreativeTab(CreativeTabs par1CreativeTabs)
    {
        this.tabToDisplayOn = par1CreativeTabs;
        return this;
    }

    public boolean func_82788_x()
    {
        return true;
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

    public boolean CanItemBeUsedByPlayer(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6, ItemStack var7)
    {
        return true;
    }

    public boolean DoZombiesConsume()
    {
        return false;
    }

    public boolean IsEfficientVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return false;
    }

    public boolean canHarvestBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return false;
    }

    public float getStrVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return 1.0F;
    }

    public boolean IsMultiUsePerClick()
    {
        return true;
    }

    public float GetExhaustionOnUsedToHarvestBlock(int var1, World var2, int var3, int var4, int var5, int var6)
    {
        return 0.025F;
    }

    public void InitializeStackOnGiveCommand(Random var1, ItemStack var2) {}

    public void UpdateUsingItem(ItemStack var1, World var2, EntityPlayer var3) {}

    public int GetItemUseWarmupDuration()
    {
        return 7;
    }

    public boolean IgnoreDamageWhenComparingDuringUse()
    {
        return false;
    }

    public int GetHerbivoreFoodValue(int var1)
    {
        return this.m_iHerbivoreFoodValue;
    }

    public Item SetHerbivoreFoodValue(int var1)
    {
        this.m_iHerbivoreFoodValue = var1;
        return this;
    }

    public Item SetAsBasicHerbivoreFood()
    {
        return this.SetHerbivoreFoodValue(800);
    }

    public int GetChickenFoodValue(int var1)
    {
        return this.m_iBirdFoodValue;
    }

    public Item SetChickenFoodValue(int var1)
    {
        this.m_iBirdFoodValue = var1;
        return this;
    }

    public Item SetAsBasicChickenFood()
    {
        return this.SetChickenFoodValue(1600);
    }

    public int GetPigFoodValue(int var1)
    {
        return this.m_iPigFoodValue;
    }

    public Item SetPigFoodValue(int var1)
    {
        this.m_iPigFoodValue = var1;
        return this;
    }

    public Item SetAsBasicPigFood()
    {
        return this.SetPigFoodValue(800);
    }

    public boolean IsWolfFood()
    {
        return false;
    }

    public int GetWolfHealAmount()
    {
        return 0;
    }

    public Item SetBuoyancy(float var1)
    {
        this.m_fBuoyancy = var1;
        return this;
    }

    public Item SetBuoyant()
    {
        return this.SetBuoyancy(1.0F);
    }

    public Item SetNonBuoyant()
    {
        return this.SetBuoyancy(-1.0F);
    }

    public Item SetNeutralBuoyant()
    {
        return this.SetBuoyancy(0.0F);
    }

    public float GetBuoyancy(int var1)
    {
        return this.m_fBuoyancy;
    }

    public int GetWeightWhenWorn()
    {
        return 0;
    }

    public Item SetBellowsBlowDistance(int var1)
    {
        this.m_iBellowsBlowDistance = var1;
        return this;
    }

    public int GetBellowsBlowDistance(int var1)
    {
        return this.m_iBellowsBlowDistance;
    }

    public Item SetInfernalMaxNumEnchants(int var1)
    {
        this.m_iInfernalMaxNumEnchants = var1;
        return this;
    }

    public int GetInfernalMaxNumEnchants()
    {
        return this.m_iInfernalMaxNumEnchants;
    }

    public Item SetInfernalMaxEnchantmentCost(int var1)
    {
        this.m_iInfernalMaxEnchantmentCost = var1;
        return this;
    }

    public int GetInfernalMaxEnchantmentCost()
    {
        return this.m_iInfernalMaxEnchantmentCost;
    }

    public boolean IsEnchantmentApplicable(Enchantment var1)
    {
        return var1.type == EnumEnchantmentType.all;
    }

    public boolean IsConsumedInCrafting()
    {
        return true;
    }

    public boolean IsDamagedInCrafting()
    {
        return false;
    }

    public void OnUsedInCrafting(int var1, EntityPlayer var2, ItemStack var3)
    {
        this.OnUsedInCrafting(var2, var3);
    }

    public void OnUsedInCrafting(EntityPlayer var1, ItemStack var2) {}

    public void OnDamagedInCrafting(EntityPlayer var1) {}

    public void OnBrokenInCrafting(EntityPlayer var1) {}

    public int GetFurnaceBurnTime(int var1)
    {
        return this.m_iDefaultFurnaceBurnTime;
    }

    public Item SetFurnaceBurnTime(int var1)
    {
        this.m_iDefaultFurnaceBurnTime = var1;
        return this;
    }

    public Item SetFurnaceBurnTime(FCEnumFurnaceBurnTime var1)
    {
        this.SetFurnaceBurnTime(var1.m_iBurnTime);
        return this;
    }

    public int GetCampfireBurnTime(int var1)
    {
        return this.GetFurnaceBurnTime(var1);
    }

    public boolean GetCanItemStartFireOnUse(int var1)
    {
        return false;
    }

    public boolean GetCanItemBeSetOnFireOnUse(int var1)
    {
        return false;
    }

    public boolean GetCanBeFedDirectlyIntoCampfire(int var1)
    {
        return !this.GetCanItemBeSetOnFireOnUse(var1) && !this.GetCanItemStartFireOnUse(var1) && this.GetCampfireBurnTime(var1) > 0;
    }

    public boolean GetCanBeFedDirectlyIntoBrickOven(int var1)
    {
        return !this.GetCanItemBeSetOnFireOnUse(var1) && !this.GetCanItemStartFireOnUse(var1) && this.GetFurnaceBurnTime(var1) > 0;
    }

    public boolean IsIncineratedInCrucible()
    {
        return this.m_bIsInceratedInCrucible;
    }

    public Item SetIncineratedInCrucible()
    {
        this.m_bIsInceratedInCrucible = true;
        return this;
    }

    public Item SetNotIncineratedInCrucible()
    {
        this.m_bIsInceratedInCrucible = false;
        return this;
    }

    public boolean DoesConsumeContainerItemWhenCrafted(Item var1)
    {
        return false;
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

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        return true;
    }

    public int GetFilterableProperties(ItemStack var1)
    {
        return this.m_iFilterablePropertiesBitfield;
    }

    public Item SetFilterableProperties(int var1)
    {
        this.m_iFilterablePropertiesBitfield = var1;
        return this;
    }

    public boolean CanTransformItemIfFilter(ItemStack var1)
    {
        return false;
    }

    public static void SetAllPicksToBeEffectiveVsBlock(Block var0)
    {
        var0.SetPicksEffectiveOn(true);
    }

    public static void SetAllAxesToBeEffectiveVsBlock(Block var0)
    {
        var0.SetAxesEffectiveOn(true);
    }

    public static void SetAllShovelsToBeEffectiveVsBlock(Block var0)
    {
        var0.SetShovelsEffectiveOn(true);
    }

    public boolean OnItemUsedByBlockDispenser(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        FCBetterThanWolves.fcBlockDispenser.SpitOutItem(var2, var3, var4, var5, var1);
        var2.playAuxSFX(1000, var3, var4, var5, 0);
        return true;
    }

    static
    {
        StatList.initStats();
        m_bSuppressConflictWarnings = false;
    }
}
