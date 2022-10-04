package deco.block;

import btw.block.BTWBlocks;
import btw.block.blocks.*;
import deco.block.blocks.*;
import deco.block.util.BookshelfInterface;
import deco.block.util.BookshelfType;
import deco.block.util.WoodTypeHelper;
import deco.item.DecoItemIDs;
import deco.item.itemblocks.DecoLogItemBlock;
import deco.item.itemblocks.EnchantedBookshelfItemBlock;
import net.minecraft.src.*;

public class DecoBlockInitializer {
    public static void initDecoBlocks() {
        initWoodTypes();
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
                .setUnlocalizedName("decoBlockBarrel2");
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

        DecoBlocks.woodSingleSlab = new DecoWoodPlankSlab(DecoBlockIDs.WOOD_SINGLE_SLAB_ID,
                new int[] {
                        WoodTypeHelper.MAHOGANY_WOOD_TYPE
                },
                new String[] {
                        "mangrove"
                })
                .setUnlocalizedName("decoBlockWoodSingleSlab");
        DecoBlocks.woodDoubleSlab = new DecoWoodPlankSlab(DecoBlockIDs.WOOD_DOUBLE_SLAB_ID, true, DecoBlockIDs.WOOD_SINGLE_SLAB_ID,
                new int[] {
                        WoodTypeHelper.MAHOGANY_WOOD_TYPE
                },
                new String[] {
                        "mangrove"
                })
                .setUnlocalizedName("decoBlockWoodDoubleSlab");
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
        DecoBlocks.cherryStump = new DecoStumpBlock(DecoBlockIDs.CHERRY_STUMP_ID, WoodTypeHelper.CHERRY_WOOD_TYPE, DecoBlockIDs.CHEWED_CHERRY_LOG_ID,
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
        DecoBlocks.acaciaStump = new DecoStumpBlock(DecoBlockIDs.ACACIA_STUMP_ID, WoodTypeHelper.ACACIA_WOOD_TYPE, DecoBlockIDs.CHEWED_ACACIA_LOG_ID,
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
        DecoBlocks.mahoganyStump = new DecoStumpBlock(DecoBlockIDs.MAHOGANY_STUMP_ID, WoodTypeHelper.MAHOGANY_WOOD_TYPE, DecoBlockIDs.CHEWED_MAHOGANY_LOG_ID,
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
        DecoBlocks.mangroveStump = new DecoStumpBlock(DecoBlockIDs.MANGROVE_STUMP_ID, WoodTypeHelper.MANGROVE_WOOD_TYPE, DecoBlockIDs.CHEWED_MANGROVE_LOG_ID,
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
    }
}
