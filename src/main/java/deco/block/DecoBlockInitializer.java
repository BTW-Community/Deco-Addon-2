package deco.block;

import btw.block.BTWBlocks;
import btw.block.blocks.*;
import btw.item.blockitems.MouldingBlockItem;
import btw.item.blockitems.SidingAndCornerBlockItem;
import btw.item.blockitems.legacy.LegacySubstitutionBlockItem;
import btw.util.ColorUtils;
import deco.block.blocks.*;
import deco.block.blocks.legacy.LegacyRedSandBlock;
import deco.block.util.BookshelfInterface;
import deco.block.util.BookshelfType;
import deco.block.util.SandHelper;
import deco.block.util.WoodTypeHelper;
import deco.item.DecoItemIDs;
import deco.item.itemblocks.ColoredItemBlock;
import deco.item.itemblocks.DecoLogItemBlock;
import deco.item.itemblocks.DecoSlabItemBlock;
import deco.item.itemblocks.EnchantedBookshelfItemBlock;
import deco.item.util.LegacySubstitutionBlockItemInterface;
import net.minecraft.src.*;

public class DecoBlockInitializer {
    public static void initDecoBlocks() {
        initStone();
        initTerracotta();
        initConcrete();
        initSoil();
        initPlants();
        initWoodTypes();
    }
    
    private static void initStone() {
        
        //------ Stone Variants ------//
    
        // General
        
        // Granite
    
        // Andesite
        
        // Diorite
        
        // Slate
    
        //------ White Stone ------//
    
    
    
        //------ Prismarine ------//
    
        //------ Misc Stone ------//
    
        // Misc
        
        // Extra sub blocks
        
        // Slabs
        
    }
    
    private static void initSandstone() {
    
    }
    
    private static void initNether() {
    
    }
    
    private static void initEnd() {
    
    }
    
    private static void initTerracotta() {
        
        //------ Terracotta ------//
        
        DecoBlocks.unfiredTerracotta = new UnfiredTerracottaBlock(DecoBlockIDs.UNFIRED_TERRACOTTA);
        
        DecoBlocks.terracotta = new TerracottaBlock(DecoBlockIDs.TERRACOTTA_ID, "decoBlockTerracotta");
        DecoBlocks.stainedTerracotta = new ColoredTerracottaBlock(DecoBlockIDs.STAINED_TERRACOTTA_ID, "decoBlockTerracottaStained");
        Item.itemsList[DecoBlocks.stainedTerracotta.blockID] = new ColoredItemBlock(DecoBlocks.stainedTerracotta);
        
        int terracottaSubID = DecoBlockIDs.TERRACOTTA_SUB_START;
        
        DecoBlocks.terracottaSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(terracottaSubID++, Material.rock,
                "decoBlockTerracotta",
                DecoBlocks.terracotta.blockHardness, DecoBlocks.terracotta.blockResistance, Block.soundStoneFootstep,
                "decoBlockTerracottaSiding");
        Item.itemsList[DecoBlocks.terracottaSidingAndCorner.blockID] = new SidingAndCornerBlockItem(DecoBlocks.terracottaSidingAndCorner.blockID - 256);
        DecoBlocks.terracottaMoulding = new MouldingAndDecorativeWallBlock(terracottaSubID++, Material.rock,
                "decoBlockTerracotta", "decoBlockTerracottaColumn",
                DecoBlocks.terracottaSidingAndCorner.blockID,
                DecoBlocks.terracotta.blockHardness, DecoBlocks.terracotta.blockResistance, Block.soundStoneFootstep,
                "decoBlockTerracottaMoulding");
        Item.itemsList[DecoBlocks.terracottaSidingAndCorner.blockID] = new SidingAndCornerBlockItem(DecoBlocks.terracottaSidingAndCorner.blockID - 256);
        DecoBlocks.terracottaStairs = new StairsBlock(terracottaSubID++, DecoBlocks.terracotta, 0)
                .setUnlocalizedName("decoBlockTerracottaStairs");
        
        DecoBlocks.stainedTerracottaSidingAndCorner = new Block[16];
        DecoBlocks.stainedTerracottaMoulding = new Block[16];
        DecoBlocks.stainedTerracottaStairs = new Block[16];
        
        for (int color = 0; color < 16; color++) {
            DecoBlocks.stainedTerracottaSidingAndCorner[color] = new SidingAndCornerAndDecorativeWallBlock(terracottaSubID++, Material.rock,
                    "decoBlockTerracottaStained_" + ColorUtils.colorOrder[color],
                    DecoBlocks.terracotta.blockHardness, DecoBlocks.terracotta.blockResistance, Block.soundStoneFootstep,
                    "decoBlockTerracottaStainedSiding." + ColorUtils.colorOrder[color]);
            Item.itemsList[DecoBlocks.stainedTerracottaSidingAndCorner[color].blockID] =
                    new SidingAndCornerBlockItem(DecoBlocks.stainedTerracottaSidingAndCorner[color].blockID - 256);
            DecoBlocks.stainedTerracottaMoulding[color] = new MouldingAndDecorativeWallBlock(terracottaSubID++, Material.rock,
                    "decoBlockTerracottaStained_" + ColorUtils.colorOrder[color], "decoBlockTerracottaStainedColumn_" + ColorUtils.colorOrder[color],
                    DecoBlocks.stainedTerracottaSidingAndCorner[color].blockID,
                    DecoBlocks.terracotta.blockHardness, DecoBlocks.terracotta.blockResistance, Block.soundStoneFootstep,
                    "decoBlockTerracottaStainedMoulding." + ColorUtils.colorOrder[color]);
            Item.itemsList[DecoBlocks.stainedTerracottaMoulding[color].blockID] =
                    new SidingAndCornerBlockItem(DecoBlocks.stainedTerracottaMoulding[color].blockID - 256);
            DecoBlocks.stainedTerracottaStairs[color] = new StairsBlock(terracottaSubID++, DecoBlocks.stainedTerracotta, color)
                    .setUnlocalizedName("decoBlockTerracottaStainedStairs." + ColorUtils.colorOrder[color]);
        }
    
        DecoBlocks.stainedTerracottaSlab = new DecoSlabBlock(DecoBlockIDs.STAINED_TERRACOTTA_SLAB_ID, Material.rock,
                new Block[] {
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta
                },
                new int[] {0, 1, 2, 3, 4, 5, 6, 7})
                .setUnlocalizedName("decoBlockTerracottaStainedSlab");
        Item.itemsList[DecoBlocks.stainedTerracottaSlab.blockID] = new DecoSlabItemBlock(DecoBlocks.stainedTerracottaSlab.blockID - 256);
        DecoBlocks.stainedTerracottaSlab2 = new DecoSlabBlock(DecoBlockIDs.STAINED_TERRACOTTA_SLAB_2_ID, Material.rock,
                new Block[] {
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta,
                        DecoBlocks.stainedTerracotta
                },
                new int[] {8, 9, 10, 11, 12, 13, 14, 15})
                .setUnlocalizedName("decoBlockTerracottaSlabStained2");
        Item.itemsList[DecoBlocks.stainedTerracottaSlab2.blockID] = new DecoSlabItemBlock(DecoBlocks.stainedTerracottaSlab2.blockID - 256);
    
        //------ Shingles ------//
    
        DecoBlocks.shingles = new TerracottaBlock(DecoBlockIDs.SHINGLES_ID, "decoBlockShingles");
        DecoBlocks.coloredShingles = new ColoredTerracottaBlock(DecoBlockIDs.COLORED_SHINGLES_ID, "decoBlockShinglesColored");
        Item.itemsList[DecoBlocks.coloredShingles.blockID] = new ColoredItemBlock(DecoBlocks.coloredShingles);
    
        int shinglesSubID = DecoBlockIDs.SHINGLES_SUB_START;
    
        DecoBlocks.shinglesSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(shinglesSubID++, Material.rock,
                "decoBlockShingles",
                DecoBlocks.shingles.blockHardness, DecoBlocks.shingles.blockResistance, Block.soundStoneFootstep,
                "decoBlockShinglesSiding");
        Item.itemsList[DecoBlocks.shinglesSidingAndCorner.blockID] = new SidingAndCornerBlockItem(DecoBlocks.shinglesSidingAndCorner.blockID - 256);
        DecoBlocks.shinglesMoulding = new MouldingAndDecorativeWallBlock(shinglesSubID++, Material.rock,
                "decoBlockShingles", "decoBlockShinglesColumn",
                DecoBlocks.shinglesSidingAndCorner.blockID,
                DecoBlocks.shingles.blockHardness, DecoBlocks.shingles.blockResistance, Block.soundStoneFootstep,
                "decoBlockShinglesMoulding");
        Item.itemsList[DecoBlocks.shinglesSidingAndCorner.blockID] = new SidingAndCornerBlockItem(DecoBlocks.shinglesSidingAndCorner.blockID - 256);
        DecoBlocks.shinglesStairs = new StairsBlock(shinglesSubID++, DecoBlocks.shingles, 0)
                .setUnlocalizedName("decoBlockShinglesStairs");
    
        DecoBlocks.coloredShinglesSidingAndCorner = new Block[16];
        DecoBlocks.coloredShinglesMoulding = new Block[16];
        DecoBlocks.coloredShinglesStairs = new Block[16];
        
        for (int color = 0; color < 16; color++) {
            DecoBlocks.coloredShinglesSidingAndCorner[color] = new SidingAndCornerAndDecorativeWallBlock(shinglesSubID++, Material.rock,
                    "decoBlockShinglesColored_" + ColorUtils.colorOrder[color],
                    DecoBlocks.terracotta.blockHardness, DecoBlocks.terracotta.blockResistance, Block.soundStoneFootstep,
                    "decoBlockShinglesColoredSiding." + ColorUtils.colorOrder[color]);
            Item.itemsList[DecoBlocks.coloredShinglesSidingAndCorner[color].blockID] =
                    new SidingAndCornerBlockItem(DecoBlocks.coloredShinglesSidingAndCorner[color].blockID - 256);
            DecoBlocks.coloredShinglesMoulding[color] = new MouldingAndDecorativeWallBlock(shinglesSubID++, Material.rock,
                    "decoBlockShinglesColored_" + ColorUtils.colorOrder[color], "decoBlockShinglesColoredColumn_" + ColorUtils.colorOrder[color],
                    DecoBlocks.coloredShinglesSidingAndCorner[color].blockID,
                    DecoBlocks.terracotta.blockHardness, DecoBlocks.terracotta.blockResistance, Block.soundStoneFootstep,
                    "decoBlockShinglesColoredMoulding." + ColorUtils.colorOrder[color]);
            Item.itemsList[DecoBlocks.coloredShinglesMoulding[color].blockID] =
                    new SidingAndCornerBlockItem(DecoBlocks.coloredShinglesMoulding[color].blockID - 256);
            DecoBlocks.coloredShinglesStairs[color] = new StairsBlock(shinglesSubID++, DecoBlocks.coloredShingles, color)
                    .setUnlocalizedName("decoBlockShinglesColoredStairs." + ColorUtils.colorOrder[color]);
        }
    
        DecoBlocks.coloredShinglesSlab = new DecoSlabBlock(DecoBlockIDs.COLORED_SHINGLES_SLAB_ID, Material.rock,
                new Block[] {
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles
                },
                new int[] {0, 1, 2, 3, 4, 5, 6, 7})
                .setUnlocalizedName("decoBlockShinglesColoredSlab");
        Item.itemsList[DecoBlocks.coloredShinglesSlab.blockID] = new DecoSlabItemBlock(DecoBlocks.coloredShinglesSlab.blockID - 256);
        DecoBlocks.coloredShinglesSlab2 = new DecoSlabBlock(DecoBlockIDs.COLORED_SHINGLES_SLAB_2_ID, Material.rock,
                new Block[] {
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles,
                        DecoBlocks.coloredShingles
                },
                new int[] {8, 9, 10, 11, 12, 13, 14, 15})
                .setUnlocalizedName("decoBlockShinglesSlabColored2");
        Item.itemsList[DecoBlocks.coloredShinglesSlab2.blockID] = new DecoSlabItemBlock(DecoBlocks.coloredShinglesSlab2.blockID - 256);
        
        //------ Glazed Terracotta ------//
        
        DecoBlocks.glazedTerracotta = new Block[16];
        DecoBlocks.glazedTerracottaPillars = new Block[16];
        
        int glazedTerracottaID = DecoBlockIDs.GLAZED_TERRACOTTA_START;
        int glazedTerracottaPillarID = DecoBlockIDs.GLAZED_TERRACOTTA_PILLARS_START;
        
        for (int color = 0; color < 16; color++) {
            DecoBlocks.glazedTerracotta[color] = new GlazedTerracottaBlock(glazedTerracottaID++,
                    "decoBlockGlazedTerracotta." + ColorUtils.colorOrder[color],
                    "decoBlockGlazedTerracotta_" + ColorUtils.colorOrder[color]);
            
            DecoBlocks.glazedTerracottaPillars[color] = new DecoPillarBlock(glazedTerracottaPillarID++, Material.rock,
                    new String[] {"decoBlockGlazedTerracottaPillar_" + ColorUtils.colorOrder[color] + "_top"},
                    new String[] {"decoBlockGlazedTerracottaPillar_" + ColorUtils.colorOrder[color] + "_side"})
                    .setHardness(1.0F)
                    .setResistance(5.0F)
                    .setCreativeTab(CreativeTabs.tabBlock)
                    .setUnlocalizedName("decoBlockGlazedTerracottaPillar." + ColorUtils.colorOrder[color]);
        }
    }
    
    private static void initConcrete() {
        DecoBlocks.concrete = new ColoredBlock(DecoBlockIDs.CONCRETE_ID, Material.rock, "decoBlockConcrete")
                .setHardness(1.5F)
                .setPicksEffectiveOn()
                .setCreativeTab(CreativeTabs.tabBlock)
                .setStepSound(Block.soundStoneFootstep)
                .setUnlocalizedName("decoBlockConcrete");
        Item.itemsList[DecoBlocks.concrete.blockID] = new ColoredItemBlock(DecoBlocks.concrete);
        
        DecoBlocks.concretePowder = new ConcretePowderBlock(DecoBlockIDs.CONCRETE_POWDER_ID);
        Item.itemsList[DecoBlocks.concretePowder.blockID] = new ColoredItemBlock(DecoBlocks.concretePowder);
    
        DecoBlocks.concreteSidingAndCorner = new Block[16];
        DecoBlocks.concreteMoulding = new Block[16];
        DecoBlocks.concreteStairs = new Block[16];
    
        int concreteSubID = DecoBlockIDs.CONCRETE_SUB_START;
    
        for (int color = 0; color < 16; color++) {
            DecoBlocks.concreteSidingAndCorner[color] = new SidingAndCornerAndDecorativeWallBlock(concreteSubID++, Material.rock,
                    "decoBlockConcrete_" + ColorUtils.colorOrder[color],
                    DecoBlocks.concrete.blockHardness, DecoBlocks.concrete.blockResistance, Block.soundStoneFootstep,
                    "decoBlockConcreteSiding." + ColorUtils.colorOrder[color]);
            Item.itemsList[DecoBlocks.concreteSidingAndCorner[color].blockID] = new SidingAndCornerBlockItem(DecoBlocks.concreteSidingAndCorner[color].blockID - 256);
        
            DecoBlocks.concreteMoulding[color] = new MouldingAndDecorativeWallBlock(concreteSubID++, Material.rock,
                    "decoBlockConcrete_" + ColorUtils.colorOrder[color],
                    "decoBlockConcreteColumn_" + ColorUtils.colorOrder[color],
                    DecoBlocks.concreteSidingAndCorner[color].blockID,
                    DecoBlocks.concrete.blockHardness, DecoBlocks.concrete.blockResistance, Block.soundStoneFootstep,
                    "decoBlockConcreteMoulding." + ColorUtils.colorOrder[color]);
            Item.itemsList[DecoBlocks.concreteMoulding[color].blockID] = new MouldingBlockItem(DecoBlocks.concreteMoulding[color].blockID - 256);
        
            DecoBlocks.concreteStairs[color] = new StairsBlock(concreteSubID++, DecoBlocks.concrete, color)
                    .setUnlocalizedName("decoBlockConcreteStairs." + ColorUtils.colorOrder[color]);
        }
    
        DecoBlocks.concreteSlab = new DecoSlabBlock(DecoBlockIDs.CONCRETE_SLAB_ID, Material.rock,
                new Block[] {
                        DecoBlocks.concrete,
                        DecoBlocks.concrete,
                        DecoBlocks.concrete,
                        DecoBlocks.concrete,
                        DecoBlocks.concrete,
                        DecoBlocks.concrete,
                        DecoBlocks.concrete,
                        DecoBlocks.concrete
                },
                new int[] {0, 1, 2, 3, 4, 5, 6, 7})
                .setUnlocalizedName("decoBlockConcreteSlab");
        Item.itemsList[DecoBlocks.concreteSlab.blockID] = new DecoSlabItemBlock(DecoBlocks.concreteSlab.blockID - 256);
        DecoBlocks.concreteSlab2 = new DecoSlabBlock(DecoBlockIDs.CONCRETE_SLAB_2_ID, Material.rock,
                new Block[] {
                        DecoBlocks.concrete,
                        DecoBlocks.concrete,
                        DecoBlocks.concrete,
                        DecoBlocks.concrete,
                        DecoBlocks.concrete,
                        DecoBlocks.concrete,
                        DecoBlocks.concrete,
                        DecoBlocks.concrete
                },
                new int[] {8, 9, 10, 11, 12, 13, 14, 15})
                .setUnlocalizedName("decoBlockConcreteSlab2");
        Item.itemsList[DecoBlocks.concreteSlab2.blockID] = new DecoSlabItemBlock(DecoBlocks.concreteSlab2.blockID - 256);
    }
    
    private static void initSoil() {
        Item.itemsList[Block.sand.blockID] = new ItemMultiTextureTile(Block.sand.blockID - 256, Block.sand, SandHelper.names);
        
        DecoBlocks.legacyRedSand = new LegacyRedSandBlock(DecoBlockIDs.LEGACY_RED_SAND_ID);
        Item.itemsList[DecoBlocks.legacyRedSand.blockID] = new LegacySubstitutionBlockItem(DecoBlocks.legacyRedSand.blockID - 256, Block.sand.blockID);
        ((LegacySubstitutionBlockItemInterface) Item.itemsList[DecoBlocks.legacyRedSand.blockID]).setMetadataToPlace(SandHelper.RED_SAND_TYPE);
        
        DecoBlocks.redSandSlab = new RedSandSlabBlock(DecoBlockIDs.RED_SAND_SLAB_ID);
        Item.itemsList[DecoBlocks.redSandSlab.blockID] = new DecoSlabItemBlock(DecoBlocks.redSandSlab.blockID - 256);
        
        DecoBlocks.coarseDirt = new CoarseDirtBlock(DecoBlockIDs.COARSE_DIRT_ID);
        
        DecoBlocks.coarseDirtSlab = new CoarseDirtSlabBlock(DecoBlockIDs.COARSE_DIRT_SLAB_ID);
        Item.itemsList[DecoBlocks.coarseDirtSlab.blockID] = new DecoSlabItemBlock(DecoBlocks.coarseDirtSlab.blockID - 256);
        
        DecoBlocks.podzol = new PodzolBlock(DecoBlockIDs.PODZOL_ID);
    }
    
    private static void initPlants() {
        DecoBlocks.flower = new DecoFlowerBlock(DecoBlockIDs.FLOWER_ID, "decoBlockFlower",
                new String[] {
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
                        "roseBlue"
                })
                .setUnlocalizedName("decoBlockFlower");
        Item.itemsList[DecoBlocks.flower.blockID] = new ItemMultiTextureTile(DecoBlocks.flower.blockID - 256, DecoBlocks.flower,
                ((DecoFlowerBlock) DecoBlocks.flower).getNames());
    
        DecoBlocks.flower2 = new DecoFlowerBlock(DecoBlockIDs.FLOWER_2_ID, "decoBlockFlower",
                new String[] {
                        "blackRose",
                        "lilyOfTheValley"
                })
                .setUnlocalizedName("decoBlockFlower2");
        Item.itemsList[DecoBlocks.flower2.blockID] = new ItemMultiTextureTile(DecoBlocks.flower2.blockID - 256, DecoBlocks.flower2,
                ((DecoFlowerBlock) DecoBlocks.flower2).getNames());
    
        DecoBlocks.tulip = new DecoFlowerBlock(DecoBlockIDs.TULIP_ID, "decoBlockTulip",
                new String[] {
                        "red",
                        "pink",
                        "orange",
                        "white",
                        "blue"
                })
                .setUnlocalizedName("decoBlockTulip");
        Item.itemsList[DecoBlocks.tulip.blockID] = new ItemMultiTextureTile(DecoBlocks.tulip.blockID - 256, DecoBlocks.tulip,
                ((DecoFlowerBlock) DecoBlocks.tulip).getNames());
    }

    private static void initWoodTypes() {
        
        //------ General wooden blocks ------//
        
        Block.wood.setUnlocalizedName("log");
        Item.itemsList[Block.wood.blockID] = new ItemMultiTextureTile(Block.wood.blockID - 256, Block.wood,
                new String[] {"oakLog", "spruceLog", "birchLog", "jungleLog"});
        DecoBlocks.strippedLog = new DecoLogBlock(DecoBlockIDs.STRIPPED_LOG_ID,
                new int[] {
                        WoodTypeHelper.OAK_WOOD_TYPE,
                        WoodTypeHelper.SPRUCE_WOOD_TYPE,
                        WoodTypeHelper.BIRCH_WOOD_TYPE,
                        WoodTypeHelper.JUNGLE_WOOD_TYPE
                },
                new int[] {
                        BTWBlocks.oakChewedLog.blockID,
                        BTWBlocks.spruceChewedLog.blockID,
                        BTWBlocks.birchChewedLog.blockID,
                        BTWBlocks.jungleChewedLog.blockID
                },
                new boolean [] {true, true, true, true},
                new String[] {
                        "fcBlockLogStrippedOak_top",
                        "fcBlockLogStrippedSpruce_top",
                        "fcBlockLogStrippedBirch_top",
                        "fcBlockLogStrippedJungle_top"
                },
                new String[] {
                        "fcBlockLogStrippedOak_side",
                        "fcBlockLogStrippedSpruce_side",
                        "fcBlockLogStrippedBirch_side",
                        "fcBlockLogStrippedJungle_side"
                })
                .setUnlocalizedName("decoBlockLogStripped");
        Item.itemsList[DecoBlocks.strippedLog.blockID] = new ItemMultiTextureTile(DecoBlocks.strippedLog.blockID - 256, DecoBlocks.strippedLog,
                new String[] {"oak", "spruce", "birch", "jungle"});
        DecoBlocks.wood = new DecoLogBlock(DecoBlockIDs.WOOD_ID,
                new int[] {
                        WoodTypeHelper.OAK_WOOD_TYPE,
                        WoodTypeHelper.SPRUCE_WOOD_TYPE,
                        WoodTypeHelper.BIRCH_WOOD_TYPE,
                        WoodTypeHelper.JUNGLE_WOOD_TYPE
                },
                new int[] {
                        BTWBlocks.oakChewedLog.blockID,
                        BTWBlocks.spruceChewedLog.blockID,
                        BTWBlocks.birchChewedLog.blockID,
                        BTWBlocks.jungleChewedLog.blockID
                },
                new boolean [] {false, false, false, false},
                new String[] {
                        "tree_side",
                        "tree_spruce",
                        "tree_birch",
                        "tree_jungle"
                },
                new String[] {
                        "tree_side",
                        "tree_spruce",
                        "tree_birch",
                        "tree_jungle"
                })
                .setUnlocalizedName("decoBlockLogBark");
        Item.itemsList[DecoBlocks.wood.blockID] = new ItemMultiTextureTile(DecoBlocks.wood.blockID - 256, DecoBlocks.wood,
                new String[] {"oak", "spruce", "birch", "jungle"});
        DecoBlocks.strippedWood = new DecoLogBlock(DecoBlockIDs.STRIPPED_WOOD_ID,
                new int[] {
                        WoodTypeHelper.OAK_WOOD_TYPE,
                        WoodTypeHelper.SPRUCE_WOOD_TYPE,
                        WoodTypeHelper.BIRCH_WOOD_TYPE,
                        WoodTypeHelper.JUNGLE_WOOD_TYPE
                },
                new int[] {
                        BTWBlocks.oakChewedLog.blockID,
                        BTWBlocks.spruceChewedLog.blockID,
                        BTWBlocks.birchChewedLog.blockID,
                        BTWBlocks.jungleChewedLog.blockID
                },
                new boolean [] {true, true, true, true},
                new String[] {
                        "fcBlockLogStrippedOak_side",
                        "fcBlockLogStrippedSpruce_side",
                        "fcBlockLogStrippedBirch_side",
                        "fcBlockLogStrippedJungle_side"
                },
                new String[] {
                        "fcBlockLogStrippedOak_side",
                        "fcBlockLogStrippedSpruce_side",
                        "fcBlockLogStrippedBirch_side",
                        "fcBlockLogStrippedJungle_side"
                })
                .setUnlocalizedName("decoBlockLogBarkStripped");
        Item.itemsList[DecoBlocks.strippedWood.blockID] = new ItemMultiTextureTile(DecoBlocks.strippedWood.blockID - 256, DecoBlocks.strippedWood,
                new String[] {"oak", "spruce", "birch", "jungle"});
        
        Item.itemsList[Block.planks.blockID] = new ItemMultiTextureTile(Block.planks.blockID - 256, Block.planks, WoodTypeHelper.woodNames);
        
        DecoBlocks.barrel = new BarrelBlock(DecoBlockIDs.BARREL_ID,
                new String[] {"decoBlockBarrelOak_top", "decoBlockBarrelSpruce_top", "decoBlockBarrelBirch_top", "decoBlockBarrelJungle_top"},
                new String[] {"decoBlockBarrelOak_side", "decoBlockBarrelSpruce_side", "decoBlockBarrelBirch_side", "decoBlockBarrelJungle_side"})
                .setUnlocalizedName("decoBlockBarrel1");
        Item.itemsList[DecoBlocks.barrel.blockID] = new ItemMultiTextureTile(DecoBlocks.barrel.blockID - 256, DecoBlocks.barrel, new String[] {"oak", "spruce", "birch", "jungle"});
        DecoBlocks.barrel2 = new BarrelBlock(DecoBlockIDs.BARREL_2_ID,
                new String[] {"decoBlockBarrelBlood_top", "decoBlockBarrelCherry_top", "decoBlockBarrelAcacia_top", "decoBlockBarrelMahogany_top"},
                new String[] {"decoBlockBarrelBlood_side", "decoBlockBarrelCherry_side", "decoBlockBarrelAcacia_side", "decoBlockBarrelMahogany_side"})
                .setUnlocalizedName("decoBlockBarrel2");
        Item.itemsList[DecoBlocks.barrel2.blockID] = new ItemMultiTextureTile(DecoBlocks.barrel2.blockID - 256, DecoBlocks.barrel2, new String[] {"blood", "cherry", "acacia", "mahogany"});
        DecoBlocks.barrel3 = new BarrelBlock(DecoBlockIDs.BARREL_3_ID,
                new String[] {"decoBlockBarrelMangrove_top"},
                new String[] {"decoBlockBarrelMangrove_side"})
                .setUnlocalizedName("decoBlockBarrel3");
        Item.itemsList[DecoBlocks.barrel3.blockID] = new ItemMultiTextureTile(DecoBlocks.barrel3.blockID - 256, DecoBlocks.barrel3, new String[] {"mangrove"});
        
        DecoBlocks.crate = new CrateBlock(DecoBlockIDs.CRATE_ID);
        Item.itemsList[DecoBlocks.crate.blockID] = new ItemMultiTextureTile(DecoBlocks.crate.blockID - 256, DecoBlocks.crate, WoodTypeHelper.woodNames);
        
        ((BookshelfInterface) Block.bookShelf).setType(BookshelfType.ENCHANTED).setTexture("decoBlockBookshelf");
        Item.itemsList[Block.bookShelf.blockID] = new EnchantedBookshelfItemBlock(Block.bookShelf.blockID - 256, Block.bookShelf, WoodTypeHelper.woodNames);
        
        DecoBlocks.bookshelf = new BookshelfBlock(DecoBlockIDs.BOOKSHELF_ID).setUnlocalizedName("decoBlockBookshelf");
        ((BookshelfInterface) DecoBlocks.bookshelf).setType(BookshelfType.FULL).setTexture("decoBlockBookshelf");
        Item.itemsList[DecoBlocks.bookshelf.blockID] = new ItemMultiTextureTile(DecoBlocks.bookshelf.blockID - 256, DecoBlocks.bookshelf, WoodTypeHelper.woodNames);
        
        DecoBlocks.emptyBookshelf = new BookshelfBlock(DecoBlockIDs.EMPTY_BOOKSHELF_ID).setUnlocalizedName("decoBlockBookshelfEmpty");
        ((BookshelfInterface) DecoBlocks.emptyBookshelf).setType(BookshelfType.EMPTY).setTexture("decoBlockBookshelfEmpty");
        Item.itemsList[DecoBlocks.emptyBookshelf.blockID] = new ItemMultiTextureTile(DecoBlocks.emptyBookshelf.blockID - 256, DecoBlocks.emptyBookshelf, WoodTypeHelper.woodNames);
        
        DecoBlocks.bottleRack = new BookshelfBlock(DecoBlockIDs.BOTTLE_RACK_ID).setUnlocalizedName("decoBlockBottleRack");
        ((BookshelfInterface) DecoBlocks.bottleRack).setType(BookshelfType.RACK).setTexture("decoBlockBottleRack");
        Item.itemsList[DecoBlocks.bottleRack.blockID] = new ItemMultiTextureTile(DecoBlocks.bottleRack.blockID - 256, DecoBlocks.bottleRack, WoodTypeHelper.woodNames);
    
        DecoBlocks.emptyBottleRack = new BookshelfBlock(DecoBlockIDs.EMPTY_BOTTLE_RACK_ID).setUnlocalizedName("decoBlockBottleRackEmpty");
        ((BookshelfInterface) DecoBlocks.emptyBottleRack).setType(BookshelfType.RACK_EMPTY).setTexture("decoBlockBottleRackEmpty");
        Item.itemsList[DecoBlocks.emptyBottleRack.blockID] = new ItemMultiTextureTile(DecoBlocks.emptyBottleRack.blockID - 256, DecoBlocks.emptyBottleRack, WoodTypeHelper.woodNames);
        
        DecoBlocks.pergola = new PergolaBlock(DecoBlockIDs.PERGOLA_ID);
        
        DecoBlocks.workStump = new DecoWorkStumpBlock(DecoBlockIDs.WORK_STUMP_ID,
                new int[] {WoodTypeHelper.HAZEL_WOOD_TYPE},
                new int[] {DecoBlockIDs.HAZEL_LOG_ID},
                new int[] {DecoBlockIDs.CHEWED_HAZEL_LOG_ID});

        DecoBlocks.woodSingleSlab = new DecoWoodPlankSlab(DecoBlockIDs.WOOD_SINGLE_SLAB_ID,
                new int[] {
                        WoodTypeHelper.MANGROVE_WOOD_TYPE
                },
                new String[] {
                        "mangrove"
                })
                .setUnlocalizedName("decoBlockVanillaWoodSlab");
        DecoBlocks.woodDoubleSlab = new DecoWoodPlankSlab(DecoBlockIDs.WOOD_DOUBLE_SLAB_ID, true, DecoBlockIDs.WOOD_SINGLE_SLAB_ID,
                new int[] {
                        WoodTypeHelper.MANGROVE_WOOD_TYPE
                },
                new String[] {
                        "mangrove"
                })
                .setUnlocalizedName("decoBlockVanillaWoodSlab");
        Item.itemsList[DecoBlocks.woodSingleSlab.blockID] = new ItemSlab(DecoBlocks.woodSingleSlab.blockID - 256, (BlockHalfSlab) DecoBlocks.woodSingleSlab,
                (BlockHalfSlab) DecoBlocks.woodDoubleSlab, false);
        Item.itemsList[DecoBlocks.woodDoubleSlab.blockID] = new ItemSlab(DecoBlocks.woodDoubleSlab.blockID - 256, (BlockHalfSlab) DecoBlocks.woodSingleSlab,
                (BlockHalfSlab) DecoBlocks.woodDoubleSlab, true);
        
        //------ Oak Wood ------//
        
        DecoBlocks.oakChair = new ChairBlock(DecoBlockIDs.OAK_CHAIR_ID, "Oak");
        DecoBlocks.filledOakBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_OAK_BARREL_ID, "decoBlockBarrelOak");
        Item.itemsList[DecoBlocks.filledOakBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledOakBarrel.blockID - 256, DecoBlocks.filledOakBarrel, FilledBarrelBlock.typeTags);
    
        //------ Spruce Wood ------//
    
        DecoBlocks.spruceGate = new DecoGateBlock(DecoBlockIDs.SPRUCE_GATE_ID, "decoBlockGateSpruce");
        DecoBlocks.spruceDoor = new DecoDoorBlockWood(DecoBlockIDs.SPRUCE_DOOR_ID, DecoItemIDs.SPRUCE_DOOR_ID, "decoBlockDoorSpruce_upper", "decoBlockDoorSpruce_lower");
        DecoBlocks.spruceTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.SPRUCE_TRAPDOOR_ID, "decoBlockTrapdoorSpruce");
        DecoBlocks.spruceChair = new ChairBlock(DecoBlockIDs.SPRUCE_CHAIR_ID, "Spruce");
        DecoBlocks.filledSpruceBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_SPRUCE_BARREL_ID, "decoBlockBarrelSpruce");
        Item.itemsList[DecoBlocks.filledSpruceBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledSpruceBarrel.blockID - 256, DecoBlocks.filledSpruceBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.spruceLadder = new DecoLadderBlock(DecoBlockIDs.SPRUCE_LADDER_ID, DecoBlockIDs.FLAMING_SPRUCE_LADDER_ID, "decoBlockLadderSpruce");
        DecoBlocks.flamingSpruceLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_SPRUCE_LADDER_ID, DecoBlockIDs.SPRUCE_LADDER_ID, "decoBlockLadderSpruce");
    
        DecoBlocks.spruceSign = new DecoSignBlock(DecoBlockIDs.SPRUCE_SIGN_ID, WoodTypeHelper.SPRUCE_WOOD_TYPE, true,
                "/deco/signSpruce.png", "wood_spruce");
        DecoBlocks.spruceWallSign = new DecoSignBlock(DecoBlockIDs.SPRUCE_WALL_SIGN_ID, WoodTypeHelper.SPRUCE_WOOD_TYPE, false,
                "/deco/signSpruce.png", "wood_spruce");
    
        //------ Birch Wood ------//
    
        DecoBlocks.birchGate = new DecoGateBlock(DecoBlockIDs.BIRCH_GATE_ID, "decoBlockGateBirch");
        DecoBlocks.birchDoor = new DecoDoorBlockWood(DecoBlockIDs.BIRCH_DOOR_ID, DecoItemIDs.BIRCH_DOOR_ID, "decoBlockDoorBirch_upper", "decoBlockDoorBirch_lower");
        DecoBlocks.birchTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.BIRCH_TRAPDOOR_ID, "decoBlockTrapdoorBirch");
        DecoBlocks.birchChair = new ChairBlock(DecoBlockIDs.BIRCH_CHAIR_ID, "Birch");
        DecoBlocks.filledBirchBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_BIRCH_BARREL_ID, "decoBlockBarrelBirch");
        Item.itemsList[DecoBlocks.filledBirchBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledBirchBarrel.blockID - 256, DecoBlocks.filledBirchBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.birchLadder = new DecoLadderBlock(DecoBlockIDs.BIRCH_LADDER_ID, DecoBlockIDs.FLAMING_BIRCH_LADDER_ID, "decoBlockLadderBirch");
        DecoBlocks.flamingBirchLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_BIRCH_LADDER_ID, DecoBlockIDs.BIRCH_LADDER_ID, "decoBlockLadderBirch");
    
        DecoBlocks.birchSign = new DecoSignBlock(DecoBlockIDs.BIRCH_SIGN_ID, WoodTypeHelper.BIRCH_WOOD_TYPE, true,
                "/deco/signBirch.png", "wood_birch");
        DecoBlocks.birchWallSign = new DecoSignBlock(DecoBlockIDs.BIRCH_WALL_SIGN_ID, WoodTypeHelper.BIRCH_WOOD_TYPE, false,
                "/deco/signBirch.png", "wood_birch");
    
        //------ Jungle Wood ------//
    
        DecoBlocks.jungleGate = new DecoGateBlock(DecoBlockIDs.JUNGLE_GATE_ID, "decoBlockGateJungle");
        DecoBlocks.jungleDoor = new DecoDoorBlockWood(DecoBlockIDs.JUNGLE_DOOR_ID, DecoItemIDs.JUNGLE_DOOR_ID, "decoBlockDoorJungle_upper", "decoBlockDoorJungle_lower");
        DecoBlocks.jungleTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.JUNGLE_TRAPDOOR_ID, "decoBlockTrapdoorJungle");
        DecoBlocks.jungleChair = new ChairBlock(DecoBlockIDs.JUNGLE_CHAIR_ID, "Jungle");
        DecoBlocks.filledJungleBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_JUNGLE_BARREL_ID, "decoBlockBarrelJungle");
        Item.itemsList[DecoBlocks.filledJungleBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledJungleBarrel.blockID - 256, DecoBlocks.filledJungleBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.jungleLadder = new DecoLadderBlock(DecoBlockIDs.JUNGLE_LADDER_ID, DecoBlockIDs.FLAMING_JUNGLE_LADDER_ID, "decoBlockLadderJungle");
        DecoBlocks.flamingJungleLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_JUNGLE_LADDER_ID, DecoBlockIDs.JUNGLE_LADDER_ID, "decoBlockLadderJungle");
    
        DecoBlocks.jungleSign = new DecoSignBlock(DecoBlockIDs.JUNGLE_SIGN_ID, WoodTypeHelper.JUNGLE_WOOD_TYPE, true,
                "/deco/signJungle.png", "wood_jungle");
        DecoBlocks.jungleWallSign = new DecoSignBlock(DecoBlockIDs.JUNGLE_WALL_SIGN_ID, WoodTypeHelper.JUNGLE_WOOD_TYPE, false,
                "/deco/signJungle.png", "wood_jungle");
    
        //------ Blood Wood ------//
    
        DecoBlocks.bloodLog = new DecoBloodLogBlock(DecoBlockIDs.BLOOD_LOG_ID);
        Item.itemsList[DecoBlocks.bloodLog.blockID] = new ItemMultiTextureTile(DecoBlocks.bloodLog.blockID - 256, DecoBlocks.bloodLog,
                new String[] {"stripped", "wood", "strippedWood"});
        DecoBlocks.bloodLogSpike = new LogSpikeBlock(DecoBlockIDs.BLOOD_LOG_SPIKE_ID,
                "decoBlockStrippedBlood_top", "decoBlockStrippedBlood_side")
                .setUnlocalizedName("decoBlockChewedBlood");
        DecoBlocks.chewedBloodLog = new ChewedLogBlock(DecoBlockIDs.CHEWED_BLOOD_LOG_ID,
                "decoBlockStrippedBlood_top", "decoBlockStrippedBlood_side", "decoBlockStrippedBlood_top",
                DecoBlocks.bloodLogSpike)
                .setUnlocalizedName("decoBlockSpikeBlood");
        
        DecoBlocks.bloodGate = new DecoGateBlock(DecoBlockIDs.BLOOD_GATE_ID, "decoBlockGateBlood");
        DecoBlocks.bloodDoor = new DecoDoorBlockWood(DecoBlockIDs.BLOOD_DOOR_ID, DecoItemIDs.BLOOD_DOOR_ID, "decoBlockDoorBlood_upper", "decoBlockDoorBlood_lower");
        DecoBlocks.bloodTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.BLOOD_TRAPDOOR_ID, "decoBlockTrapdoorBlood");
        DecoBlocks.bloodChair = new ChairBlock(DecoBlockIDs.BLOOD_CHAIR_ID, "Blood");
        DecoBlocks.filledBloodBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_BLOOD_BARREL_ID, "decoBlockBarrelBlood");
        Item.itemsList[DecoBlocks.filledBloodBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledBloodBarrel.blockID - 256, DecoBlocks.filledBloodBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.bloodLadder = new DecoLadderBlock(DecoBlockIDs.BLOOD_LADDER_ID, DecoBlockIDs.FLAMING_BLOOD_LADDER_ID, "decoBlockLadderBlood");
        DecoBlocks.flamingBloodLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_BLOOD_LADDER_ID, DecoBlockIDs.BLOOD_LADDER_ID, "decoBlockLadderBlood");
    
        DecoBlocks.bloodSign = new DecoSignBlock(DecoBlockIDs.BLOOD_SIGN_ID, WoodTypeHelper.BLOOD_WOOD_TYPE, true,
                "/deco/signBlood.png", "fcBlockPlanks_blood");
        DecoBlocks.bloodWallSign = new DecoSignBlock(DecoBlockIDs.BLOOD_WALL_SIGN_ID, WoodTypeHelper.BLOOD_WOOD_TYPE, false,
                "/deco/signBlood.png", "fcBlockPlanks_blood");
        
        //------ Cherry Wood ------//
        
        // Tree blocks
        DecoBlocks.cherryLog = new DecoLogBlock(DecoBlockIDs.CHERRY_LOG_ID, WoodTypeHelper.CHERRY_WOOD_TYPE, DecoBlockIDs.CHEWED_CHERRY_LOG_ID,
                new String[] {"decoBlockLogCherry_top", "decoBlockStrippedCherry_top", "decoBlockLogCherry_side", "decoBlockStrippedCherry_side"},
                new String[] {"decoBlockLogCherry_side", "decoBlockStrippedCherry_side", "decoBlockLogCherry_side", "decoBlockStrippedCherry_side"})
                .setUnlocalizedName("decoBlockCherryLog");
        Item.itemsList[DecoBlocks.cherryLog.blockID] = new DecoLogItemBlock(DecoBlocks.cherryLog.blockID - 256, DecoBlocks.cherryLog);
        DecoBlocks.cherryStump = new DecoStumpBlock(DecoBlockIDs.CHERRY_STUMP_ID, DecoBlockIDs.CHERRY_LOG_ID, DecoBlockIDs.CHEWED_CHERRY_LOG_ID,
                WoodTypeHelper.CHERRY_WORK_STUMP_ID, WoodTypeHelper.CHERRY_WORK_STUMP_TYPE,
                "decoBlockTrunkCherry_top", "decoBlockTrunkCherry_side")
                .setUnlocalizedName("decoBlockCherryStump");
        DecoBlocks.cherryLogSpike = new LogSpikeBlock(DecoBlockIDs.CHERRY_LOG_SPIKE_ID,
                "decoBlockStrippedCherry_top", "decoBlockStrippedCherry_side")
                .setUnlocalizedName("decoBlockChewedCherry");
        DecoBlocks.chewedCherryLog = new ChewedLogBlock(DecoBlockIDs.CHEWED_CHERRY_LOG_ID,
                "decoBlockStrippedCherry_top", "decoBlockStrippedCherry_side", "decoBlockTrunkCherry_top",
                DecoBlocks.cherryLogSpike)
                .setUnlocalizedName("decoBlockSpikeCherry");

        DecoBlocks.cherryLeaves = new DecoLeavesBlock(DecoBlockIDs.CHERRY_LEAVES_ID, DecoBlockIDs.CHERRY_SAPLING_ID, "decoBlockLeavesCherry")
                .setUnlocalizedName("decoBlockLeavesCherry");
        DecoBlocks.cherrySapling = new CherrySaplingBlock(DecoBlockIDs.CHERRY_SAPLING_ID);
        Item.itemsList[DecoBlocks.cherrySapling.blockID] = new ItemMultiTextureTile(DecoBlocks.cherrySapling.blockID - 256, DecoBlocks.cherrySapling, CherrySaplingBlock.saplingTypes);

        // Sub blocks
        DecoBlocks.cherryStairs = new WoodStairsBlock(DecoBlockIDs.CHERRY_STAIRS_ID, Block.planks, WoodTypeHelper.CHERRY_WOOD_TYPE)
                .setUnlocalizedName("decoBlockCherryStairs");
        DecoBlocks.cherrySidingAndCorner = new WoodSidingAndCornerAndDecorativeBlock(DecoBlockIDs.CHERRY_SIDING_AND_CORNER_ID,
                "decoBlockPlanksCherry", "decoBlockCherrySiding");
        DecoBlocks.cherryMoulding = new WoodMouldingAndDecorativeBlock(DecoBlockIDs.CHERRY_MOULDING_ID,
                "decoBlockPlanksCherry", "decoBlockPlanksCherryColumn",
                DecoBlockIDs.CHERRY_SIDING_AND_CORNER_ID,
                "decoBlockCherryMoulding");
        
        // Decorative blocks
        DecoBlocks.cherryGate = new DecoGateBlock(DecoBlockIDs.CHERRY_GATE_ID, "decoBlockGateCherry");
        DecoBlocks.cherryDoor = new DecoDoorBlockWood(DecoBlockIDs.CHERRY_DOOR_ID, DecoItemIDs.CHERRY_DOOR_ID, "decoBlockDoorCherry_upper", "decoBlockDoorCherry_lower");
        DecoBlocks.cherryTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.CHERRY_TRAPDOOR_ID, "decoBlockTrapdoorCherry");
        DecoBlocks.cherryChair = new ChairBlock(DecoBlockIDs.CHERRY_CHAIR_ID, "Cherry");
        DecoBlocks.filledCherryBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_CHERRY_BARREL_ID, "decoBlockBarrelCherry");
        Item.itemsList[DecoBlocks.filledCherryBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledCherryBarrel.blockID - 256, DecoBlocks.filledCherryBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.cherryLadder = new DecoLadderBlock(DecoBlockIDs.CHERRY_LADDER_ID, DecoBlockIDs.FLAMING_CHERRY_LADDER_ID, "decoBlockLadderCherry");
        DecoBlocks.flamingCherryLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_CHERRY_LADDER_ID, DecoBlockIDs.CHERRY_LADDER_ID, "decoBlockLadderCherry");
        
        DecoBlocks.cherrySign = new DecoSignBlock(DecoBlockIDs.CHERRY_SIGN_ID, WoodTypeHelper.CHERRY_WOOD_TYPE, true,
                "/deco/signCherry.png", "decoBlockPlanksCherry");
        DecoBlocks.cherryWallSign = new DecoSignBlock(DecoBlockIDs.CHERRY_WALL_SIGN_ID, WoodTypeHelper.CHERRY_WOOD_TYPE, false,
                "/deco/signCherry.png", "decoBlockPlanksCherry");
    
        //------ Acacia Wood ------//
    
        // Tree blocks
        DecoBlocks.acaciaLog = new DecoLogBlock(DecoBlockIDs.ACACIA_LOG_ID, WoodTypeHelper.ACACIA_WOOD_TYPE, DecoBlockIDs.CHEWED_ACACIA_LOG_ID,
                new String[] {"decoBlockLogAcacia_top", "decoBlockStrippedAcacia_top", "decoBlockLogAcacia_side", "decoBlockStrippedAcacia_side"},
                new String[] {"decoBlockLogAcacia_side", "decoBlockStrippedAcacia_side", "decoBlockLogAcacia_side", "decoBlockStrippedAcacia_side"})
                .setUnlocalizedName("decoBlockAcaciaLog");
        Item.itemsList[DecoBlocks.acaciaLog.blockID] = new DecoLogItemBlock(DecoBlocks.acaciaLog.blockID - 256, DecoBlocks.acaciaLog);
        DecoBlocks.acaciaStump = new DecoStumpBlock(DecoBlockIDs.ACACIA_STUMP_ID, DecoBlockIDs.ACACIA_LOG_ID, DecoBlockIDs.CHEWED_ACACIA_LOG_ID,
                WoodTypeHelper.ACACIA_WORK_STUMP_ID, WoodTypeHelper.ACACIA_WORK_STUMP_TYPE,
                "decoBlockTrunkAcacia_top", "decoBlockTrunkAcacia_side")
                .setUnlocalizedName("decoBlockAcaciaStump");
        DecoBlocks.acaciaLogSpike = new LogSpikeBlock(DecoBlockIDs.ACACIA_LOG_SPIKE_ID,
                "decoBlockStrippedAcacia_top", "decoBlockStrippedAcacia_side")
                .setUnlocalizedName("decoBlockChewedAcacia");
        DecoBlocks.chewedAcaciaLog = new ChewedLogBlock(DecoBlockIDs.CHEWED_ACACIA_LOG_ID,
                "decoBlockStrippedAcacia_top", "decoBlockStrippedAcacia_side", "decoBlockTrunkAcacia_top",
                DecoBlocks.acaciaLogSpike)
                .setUnlocalizedName("decoBlockSpikeAcacia");
    
        DecoBlocks.acaciaLeaves = new DecoLeavesBlock(DecoBlockIDs.ACACIA_LEAVES_ID, DecoBlockIDs.ACACIA_SAPLING_ID, "decoBlockLeavesAcacia")
                .setUnlocalizedName("decoBlockLeavesAcacia");
        DecoBlocks.acaciaSapling = new AcaciaSaplingBlock(DecoBlockIDs.ACACIA_SAPLING_ID);
        Item.itemsList[DecoBlocks.acaciaSapling.blockID] = new ItemMultiTextureTile(DecoBlocks.acaciaSapling.blockID - 256, DecoBlocks.acaciaSapling, AcaciaSaplingBlock.saplingTypes);
    
        // Sub blocks
        DecoBlocks.acaciaStairs = new WoodStairsBlock(DecoBlockIDs.ACACIA_STAIRS_ID, Block.planks, WoodTypeHelper.ACACIA_WOOD_TYPE)
                .setUnlocalizedName("decoBlockAcaciaStairs");
        DecoBlocks.acaciaSidingAndCorner = new WoodSidingAndCornerAndDecorativeBlock(DecoBlockIDs.ACACIA_SIDING_AND_CORNER_ID,
                "decoBlockPlanksAcacia", "decoBlockAcaciaSiding");
        DecoBlocks.acaciaMoulding = new WoodMouldingAndDecorativeBlock(DecoBlockIDs.ACACIA_MOULDING_ID,
                "decoBlockPlanksAcacia", "decoBlockPlanksAcaciaColumn",
                DecoBlockIDs.ACACIA_SIDING_AND_CORNER_ID,
                "decoBlockAcaciaMoulding");
    
        // Decorative blocks
        DecoBlocks.acaciaGate = new DecoGateBlock(DecoBlockIDs.ACACIA_GATE_ID, "decoBlockGateAcacia");
        DecoBlocks.acaciaDoor = new DecoDoorBlockWood(DecoBlockIDs.ACACIA_DOOR_ID, DecoItemIDs.ACACIA_DOOR_ID, "decoBlockDoorAcacia_upper", "decoBlockDoorAcacia_lower");
        DecoBlocks.acaciaTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.ACACIA_TRAPDOOR_ID, "decoBlockTrapdoorAcacia");
        DecoBlocks.acaciaChair = new ChairBlock(DecoBlockIDs.ACACIA_CHAIR_ID, "Acacia");
        DecoBlocks.filledAcaciaBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_ACACIA_BARREL_ID, "decoBlockBarrelAcacia");
        Item.itemsList[DecoBlocks.filledAcaciaBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledAcaciaBarrel.blockID - 256, DecoBlocks.filledAcaciaBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.acaciaLadder = new DecoLadderBlock(DecoBlockIDs.ACACIA_LADDER_ID, DecoBlockIDs.FLAMING_ACACIA_LADDER_ID, "decoBlockLadderAcacia");
        DecoBlocks.flamingAcaciaLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_ACACIA_LADDER_ID, DecoBlockIDs.ACACIA_LADDER_ID, "decoBlockLadderAcacia");
    
        DecoBlocks.acaciaSign = new DecoSignBlock(DecoBlockIDs.ACACIA_SIGN_ID, WoodTypeHelper.ACACIA_WOOD_TYPE, true,
                "/deco/signAcacia.png", "decoBlockPlanksAcacia");
        DecoBlocks.acaciaWallSign = new DecoSignBlock(DecoBlockIDs.ACACIA_WALL_SIGN_ID, WoodTypeHelper.ACACIA_WOOD_TYPE, false,
                "/deco/signAcacia.png", "decoBlockPlanksAcacia");
    
        //------ Mahogany Wood ------//
    
        // Tree blocks
        DecoBlocks.mahoganyLog = new DecoLogBlock(DecoBlockIDs.MAHOGANY_LOG_ID, WoodTypeHelper.MAHOGANY_WOOD_TYPE, DecoBlockIDs.CHEWED_MAHOGANY_LOG_ID,
                new String[] {"decoBlockLogMahogany_top", "decoBlockStrippedMahogany_top", "decoBlockLogMahogany_side", "decoBlockStrippedMahogany_side"},
                new String[] {"decoBlockLogMahogany_side", "decoBlockStrippedMahogany_side", "decoBlockLogMahogany_side", "decoBlockStrippedMahogany_side"})
                .setUnlocalizedName("decoBlockMahoganyLog");
        Item.itemsList[DecoBlocks.mahoganyLog.blockID] = new DecoLogItemBlock(DecoBlocks.mahoganyLog.blockID - 256, DecoBlocks.mahoganyLog);
        DecoBlocks.mahoganyStump = new DecoStumpBlock(DecoBlockIDs.MAHOGANY_STUMP_ID, DecoBlockIDs.MAHOGANY_LOG_ID, DecoBlockIDs.CHEWED_MAHOGANY_LOG_ID,
                WoodTypeHelper.MAHOGANY_WORK_STUMP_ID, WoodTypeHelper.MAHOGANY_WORK_STUMP_TYPE,
                "decoBlockTrunkMahogany_top", "decoBlockTrunkMahogany_side")
                .setUnlocalizedName("decoBlockMahoganyStump");
        DecoBlocks.mahoganyLogSpike = new LogSpikeBlock(DecoBlockIDs.MAHOGANY_LOG_SPIKE_ID,
                "decoBlockStrippedMahogany_top", "decoBlockStrippedMahogany_side")
                .setUnlocalizedName("decoBlockChewedMahogany");
        DecoBlocks.chewedMahoganyLog = new ChewedLogBlock(DecoBlockIDs.CHEWED_MAHOGANY_LOG_ID,
                "decoBlockStrippedMahogany_top", "decoBlockStrippedMahogany_side", "decoBlockTrunkMahogany_top",
                DecoBlocks.mahoganyLogSpike)
                .setUnlocalizedName("decoBlockSpikeMahogany");
    
        DecoBlocks.mahoganyLeaves = new DecoLeavesBlock(DecoBlockIDs.MAHOGANY_LEAVES_ID, DecoBlockIDs.MAHOGANY_SAPLING_ID, "decoBlockLeavesMahogany")
                .setUnlocalizedName("decoBlockLeavesMahogany");
        DecoBlocks.mahoganySapling = new MahoganySaplingBlock(DecoBlockIDs.MAHOGANY_SAPLING_ID);
        Item.itemsList[DecoBlocks.mahoganySapling.blockID] = new ItemMultiTextureTile(DecoBlocks.mahoganySapling.blockID - 256, DecoBlocks.mahoganySapling, MahoganySaplingBlock.saplingTypes);
    
        // Sub blocks
        DecoBlocks.mahoganyStairs = new WoodStairsBlock(DecoBlockIDs.MAHOGANY_STAIRS_ID, Block.planks, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
                .setUnlocalizedName("decoBlockMahoganyStairs");
        DecoBlocks.mahoganySidingAndCorner = new WoodSidingAndCornerAndDecorativeBlock(DecoBlockIDs.MAHOGANY_SIDING_AND_CORNER_ID,
                "decoBlockPlanksMahogany", "decoBlockMahoganySiding");
        DecoBlocks.mahoganyMoulding = new WoodMouldingAndDecorativeBlock(DecoBlockIDs.MAHOGANY_MOULDING_ID,
                "decoBlockPlanksMahogany", "decoBlockPlanksMahoganyColumn",
                DecoBlockIDs.MAHOGANY_SIDING_AND_CORNER_ID,
                "decoBlockMahoganyMoulding");
    
        // Decorative blocks
        DecoBlocks.mahoganyGate = new DecoGateBlock(DecoBlockIDs.MAHOGANY_GATE_ID, "decoBlockGateMahogany");
        DecoBlocks.mahoganyDoor = new DecoDoorBlockWood(DecoBlockIDs.MAHOGANY_DOOR_ID, DecoItemIDs.MAHOGANY_DOOR_ID, "decoBlockDoorMahogany_upper", "decoBlockDoorMahogany_lower");
        DecoBlocks.mahoganyTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.MAHOGANY_TRAPDOOR_ID, "decoBlockTrapdoorMahogany");
        DecoBlocks.mahoganyChair = new ChairBlock(DecoBlockIDs.MAHOGANY_CHAIR_ID, "Mahogany");
        DecoBlocks.filledMahoganyBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_MAHOGANY_BARREL_ID, "decoBlockBarrelMahogany");
        Item.itemsList[DecoBlocks.filledMahoganyBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledMahoganyBarrel.blockID - 256, DecoBlocks.filledMahoganyBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.mahoganyLadder = new DecoLadderBlock(DecoBlockIDs.MAHOGANY_LADDER_ID, DecoBlockIDs.FLAMING_MAHOGANY_LADDER_ID, "decoBlockLadderMahogany");
        DecoBlocks.flamingMahoganyLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_MAHOGANY_LADDER_ID, DecoBlockIDs.MAHOGANY_LADDER_ID, "decoBlockLadderMahogany");
    
        DecoBlocks.mahoganySign = new DecoSignBlock(DecoBlockIDs.MAHOGANY_SIGN_ID, WoodTypeHelper.MAHOGANY_WOOD_TYPE, true,
                "/deco/signMahogany.png", "decoBlockPlanksMahogany");
        DecoBlocks.mahoganyWallSign = new DecoSignBlock(DecoBlockIDs.MAHOGANY_WALL_SIGN_ID, WoodTypeHelper.MAHOGANY_WOOD_TYPE, false,
                "/deco/signMahogany.png", "decoBlockPlanksMahogany");
    
        //------ Mangrove Wood ------//
    
        // Tree blocks
        DecoBlocks.mangroveLog = new DecoLogBlock(DecoBlockIDs.MANGROVE_LOG_ID, WoodTypeHelper.MANGROVE_WOOD_TYPE, DecoBlockIDs.CHEWED_MANGROVE_LOG_ID,
                new String[] {"decoBlockLogMangrove_top", "decoBlockStrippedMangrove_top", "decoBlockLogMangrove_side", "decoBlockStrippedMangrove_side"},
                new String[] {"decoBlockLogMangrove_side", "decoBlockStrippedMangrove_side", "decoBlockLogMangrove_side", "decoBlockStrippedMangrove_side"})
                .setUnlocalizedName("decoBlockMangroveLog");
        Item.itemsList[DecoBlocks.mangroveLog.blockID] = new DecoLogItemBlock(DecoBlocks.mangroveLog.blockID - 256, DecoBlocks.mangroveLog);
        DecoBlocks.mangroveStump = new DecoStumpBlock(DecoBlockIDs.MANGROVE_STUMP_ID, DecoBlockIDs.MANGROVE_LOG_ID, DecoBlockIDs.CHEWED_MANGROVE_LOG_ID,
                WoodTypeHelper.MANGROVE_WORK_STUMP_ID, WoodTypeHelper.MANGROVE_WORK_STUMP_TYPE,
                "decoBlockTrunkMangrove_top", "decoBlockTrunkMangrove_side")
                .setUnlocalizedName("decoBlockMangroveStump");
        DecoBlocks.mangroveLogSpike = new LogSpikeBlock(DecoBlockIDs.MANGROVE_LOG_SPIKE_ID,
                "decoBlockStrippedMangrove_top", "decoBlockStrippedMangrove_side")
                .setUnlocalizedName("decoBlockChewedMangrove");
        DecoBlocks.chewedMangroveLog = new ChewedLogBlock(DecoBlockIDs.CHEWED_MANGROVE_LOG_ID,
                "decoBlockStrippedMangrove_top", "decoBlockStrippedMangrove_side", "decoBlockTrunkMangrove_top",
                DecoBlocks.mangroveLogSpike)
                .setUnlocalizedName("decoBlockSpikeMangrove");
    
        DecoBlocks.mangroveLeaves = new DecoLeavesBlock(DecoBlockIDs.MANGROVE_LEAVES_ID, DecoBlockIDs.MANGROVE_SAPLING_ID, "decoBlockLeavesMangrove")
                .setUnlocalizedName("decoBlockLeavesMangrove");
        DecoBlocks.mangroveSapling = new MangroveSaplingBlock(DecoBlockIDs.MANGROVE_SAPLING_ID);
        Item.itemsList[DecoBlocks.mangroveSapling.blockID] = new ItemMultiTextureTile(DecoBlocks.mangroveSapling.blockID - 256, DecoBlocks.mangroveSapling, MangroveSaplingBlock.saplingTypes);
    
        // Sub blocks
        DecoBlocks.mangroveStairs = new WoodStairsBlock(DecoBlockIDs.MANGROVE_STAIRS_ID, Block.planks, WoodTypeHelper.MANGROVE_WOOD_TYPE)
                .setUnlocalizedName("decoBlockMangroveStairs");
        DecoBlocks.mangroveSidingAndCorner = new WoodSidingAndCornerAndDecorativeBlock(DecoBlockIDs.MANGROVE_SIDING_AND_CORNER_ID,
                "decoBlockPlanksMangrove", "decoBlockMangroveSiding");
        DecoBlocks.mangroveMoulding = new WoodMouldingAndDecorativeBlock(DecoBlockIDs.MANGROVE_MOULDING_ID,
                "decoBlockPlanksMangrove", "decoBlockPlanksMangroveColumn",
                DecoBlockIDs.MANGROVE_SIDING_AND_CORNER_ID,
                "decoBlockMangroveMoulding");
    
        // Decorative blocks
        DecoBlocks.mangroveGate = new DecoGateBlock(DecoBlockIDs.MANGROVE_GATE_ID, "decoBlockGateMangrove");
        DecoBlocks.mangroveDoor = new DecoDoorBlockWood(DecoBlockIDs.MANGROVE_DOOR_ID, DecoItemIDs.MANGROVE_DOOR_ID, "decoBlockDoorMangrove_upper", "decoBlockDoorMangrove_lower");
        DecoBlocks.mangroveTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.MANGROVE_TRAPDOOR_ID, "decoBlockTrapdoorMangrove");
        DecoBlocks.mangroveChair = new ChairBlock(DecoBlockIDs.MANGROVE_CHAIR_ID, "Mangrove");
        DecoBlocks.filledMangroveBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_MANGROVE_BARREL_ID, "decoBlockBarrelMangrove");
        Item.itemsList[DecoBlocks.filledMangroveBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledMangroveBarrel.blockID - 256, DecoBlocks.filledMangroveBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.mangroveLadder = new DecoLadderBlock(DecoBlockIDs.MANGROVE_LADDER_ID, DecoBlockIDs.FLAMING_MANGROVE_LADDER_ID, "decoBlockLadderMangrove");
        DecoBlocks.flamingMangroveLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_MANGROVE_LADDER_ID, DecoBlockIDs.MANGROVE_LADDER_ID, "decoBlockLadderMangrove");
    
        DecoBlocks.mangroveSign = new DecoSignBlock(DecoBlockIDs.MANGROVE_SIGN_ID, WoodTypeHelper.MANGROVE_WOOD_TYPE, true,
                "/deco/signMangrove.png", "decoBlockPlanksMangrove");
        DecoBlocks.mangroveWallSign = new DecoSignBlock(DecoBlockIDs.MANGROVE_WALL_SIGN_ID, WoodTypeHelper.MANGROVE_WOOD_TYPE, false,
                "/deco/signMangrove.png", "decoBlockPlanksMangrove");
    
        //------ Hazel Wood ------//
    
        // Tree blocks
        DecoBlocks.hazelLog = new DecoLogBlock(DecoBlockIDs.HAZEL_LOG_ID, WoodTypeHelper.HAZEL_WOOD_TYPE, DecoBlockIDs.CHEWED_HAZEL_LOG_ID,
                new String[] {"decoBlockLogHazel_top", "decoBlockStrippedHazel_top", "decoBlockLogHazel_side", "decoBlockStrippedHazel_side"},
                new String[] {"decoBlockLogHazel_side", "decoBlockStrippedHazel_side", "decoBlockLogHazel_side", "decoBlockStrippedHazel_side"})
                .setUnlocalizedName("decoBlockHazelLog");
        Item.itemsList[DecoBlocks.hazelLog.blockID] = new DecoLogItemBlock(DecoBlocks.hazelLog.blockID - 256, DecoBlocks.hazelLog);
        DecoBlocks.hazelStump = new DecoStumpBlock(DecoBlockIDs.HAZEL_STUMP_ID, DecoBlockIDs.HAZEL_LOG_ID, DecoBlockIDs.CHEWED_HAZEL_LOG_ID,
                WoodTypeHelper.HAZEL_WORK_STUMP_ID, WoodTypeHelper.HAZEL_WORK_STUMP_TYPE,
                "decoBlockTrunkHazel_top", "decoBlockTrunkHazel_side")
                .setUnlocalizedName("decoBlockHazelStump");
        DecoBlocks.hazelLogSpike = new LogSpikeBlock(DecoBlockIDs.HAZEL_LOG_SPIKE_ID,
                "decoBlockStrippedHazel_top", "decoBlockStrippedHazel_side")
                .setUnlocalizedName("decoBlockChewedHazel");
        DecoBlocks.chewedHazelLog = new ChewedLogBlock(DecoBlockIDs.CHEWED_HAZEL_LOG_ID,
                "decoBlockStrippedHazel_top", "decoBlockStrippedHazel_side", "decoBlockTrunkHazel_top",
                DecoBlocks.hazelLogSpike)
                .setUnlocalizedName("decoBlockSpikeHazel");
    
        DecoBlocks.hazelLeaves = new DecoLeavesBlock(DecoBlockIDs.HAZEL_LEAVES_ID, DecoBlockIDs.HAZEL_SAPLING_ID, "decoBlockLeavesHazel")
                .setUnlocalizedName("decoBlockLeavesHazel");
        DecoBlocks.hazelSapling = new HazelSaplingBlock(DecoBlockIDs.HAZEL_SAPLING_ID);
        Item.itemsList[DecoBlocks.hazelSapling.blockID] = new ItemMultiTextureTile(DecoBlocks.hazelSapling.blockID - 256, DecoBlocks.hazelSapling, HazelSaplingBlock.saplingTypes);
    
        // Sub blocks
        DecoBlocks.hazelStairs = new WoodStairsBlock(DecoBlockIDs.HAZEL_STAIRS_ID, Block.planks, WoodTypeHelper.HAZEL_WOOD_TYPE)
                .setUnlocalizedName("decoBlockHazelStairs");
        DecoBlocks.hazelSidingAndCorner = new WoodSidingAndCornerAndDecorativeBlock(DecoBlockIDs.HAZEL_SIDING_AND_CORNER_ID,
                "decoBlockPlanksHazel", "decoBlockHazelSiding");
        DecoBlocks.hazelMoulding = new WoodMouldingAndDecorativeBlock(DecoBlockIDs.HAZEL_MOULDING_ID,
                "decoBlockPlanksHazel", "decoBlockPlanksHazelColumn",
                DecoBlockIDs.HAZEL_SIDING_AND_CORNER_ID,
                "decoBlockHazelMoulding");
    
        // Decorative blocks
        DecoBlocks.hazelGate = new DecoGateBlock(DecoBlockIDs.HAZEL_GATE_ID, "decoBlockGateHazel");
        DecoBlocks.hazelDoor = new DecoDoorBlockWood(DecoBlockIDs.HAZEL_DOOR_ID, DecoItemIDs.HAZEL_DOOR_ID, "decoBlockDoorHazel_upper", "decoBlockDoorHazel_lower");
        DecoBlocks.hazelTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.HAZEL_TRAPDOOR_ID, "decoBlockTrapdoorHazel");
        DecoBlocks.hazelChair = new ChairBlock(DecoBlockIDs.HAZEL_CHAIR_ID, "Hazel");
        DecoBlocks.filledHazelBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_HAZEL_BARREL_ID, "decoBlockBarrelHazel");
        Item.itemsList[DecoBlocks.filledHazelBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledHazelBarrel.blockID - 256, DecoBlocks.filledHazelBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.hazelLadder = new DecoLadderBlock(DecoBlockIDs.HAZEL_LADDER_ID, DecoBlockIDs.FLAMING_HAZEL_LADDER_ID, "decoBlockLadderHazel");
        DecoBlocks.flamingHazelLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_HAZEL_LADDER_ID, DecoBlockIDs.HAZEL_LADDER_ID, "decoBlockLadderHazel");
    
        DecoBlocks.hazelSign = new DecoSignBlock(DecoBlockIDs.HAZEL_SIGN_ID, WoodTypeHelper.HAZEL_WOOD_TYPE, true,
                "/deco/signHazel.png", "decoBlockPlanksHazel");
        DecoBlocks.hazelWallSign = new DecoSignBlock(DecoBlockIDs.HAZEL_WALL_SIGN_ID, WoodTypeHelper.HAZEL_WOOD_TYPE, false,
                "/deco/signHazel.png", "decoBlockPlanksHazel");
        
        //------ Painted Planks ------//
        
        DecoBlocks.pastelPlanks = new ColoredPlanksBlock(DecoBlockIDs.PASTEL_PLANKS_ID, "decoBlockPlanksPainted");
        Item.itemsList[DecoBlocks.pastelPlanks.blockID] = new ColoredItemBlock(DecoBlocks.pastelPlanks);
        
        DecoBlocks.pastelPlanksSidingAndCorner = new Block[16];
        DecoBlocks.pastelPlanksMoulding = new Block[16];
        DecoBlocks.pastelPlanksStairs = new Block[16];
        
        int pastelSubID = DecoBlockIDs.PASTEL_PLANKS_SUB_START;
        
        for (int color = 0; color < 16; color++) {
            DecoBlocks.pastelPlanksSidingAndCorner[color] = new DecoWoodSidingAndCornerAndDecorativeBlock(pastelSubID++,
                    "decoBlockPlanksPainted_" + ColorUtils.colorOrder[color],
                    "decoBlockPlanksPaintedSiding." + ColorUtils.colorOrder[color]);
            Item.itemsList[DecoBlocks.pastelPlanksSidingAndCorner[color].blockID] = new SidingAndCornerBlockItem(DecoBlocks.pastelPlanksSidingAndCorner[color].blockID - 256);
    
            DecoBlocks.pastelPlanksMoulding[color] = new DecoWoodMouldingAndDecorativeBlock(pastelSubID++,
                    "decoBlockPlanksPainted_" + ColorUtils.colorOrder[color],
                    "decoBlockPlanksPaintedColumn_" + ColorUtils.colorOrder[color],
                    DecoBlocks.pastelPlanksSidingAndCorner[color].blockID,
                    "decoBlockPlanksPaintedMoulding." + ColorUtils.colorOrder[color]);
            Item.itemsList[DecoBlocks.pastelPlanksMoulding[color].blockID] = new MouldingBlockItem(DecoBlocks.pastelPlanksMoulding[color].blockID - 256);
            
            DecoBlocks.pastelPlanksStairs[color] = new WoodStairsBlock(pastelSubID++, DecoBlocks.pastelPlanks, color)
                    .setUnlocalizedName("decoBlockPlanksPaintedStairs." + ColorUtils.colorOrder[color]);
        }
        
        DecoBlocks.pastelPlanksSlab = new DecoWoodSlabBlock(DecoBlockIDs.PASTEL_PLANKS_SLAB_ID,
                new Block[] {
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks
                },
                new int[] {0, 1, 2, 3, 4, 5, 6, 7})
                .setUnlocalizedName("decoBlockPlanksPaintedSlab");
        Item.itemsList[DecoBlocks.pastelPlanksSlab.blockID] = new DecoSlabItemBlock(DecoBlocks.pastelPlanksSlab.blockID - 256);
        DecoBlocks.pastelPlanksSlab2 = new DecoWoodSlabBlock(DecoBlockIDs.PASTEL_PLANKS_SLAB_2_ID,
                new Block[] {
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks,
                        DecoBlocks.pastelPlanks
                },
                new int[] {8, 9, 10, 11, 12, 13, 14, 15})
                .setUnlocalizedName("decoBlockPlanksPaintedSlab2");
        Item.itemsList[DecoBlocks.pastelPlanksSlab2.blockID] = new DecoSlabItemBlock(DecoBlocks.pastelPlanksSlab2.blockID - 256);
    }
}
