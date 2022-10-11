package deco.block;

import btw.block.BTWBlocks;
import btw.block.blocks.*;
import btw.block.tileentity.beacon.BeaconTileEntity;
import btw.item.blockitems.MouldingBlockItem;
import btw.item.blockitems.SidingAndCornerBlockItem;
import btw.item.blockitems.legacy.LegacySubstitutionBlockItem;
import btw.util.ColorUtils;
import deco.block.blocks.*;
import deco.block.blocks.legacy.LegacyRedSandBlock;
import deco.block.util.*;
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
        initPrismarine();
        initSandstone();
        initNether();
        initEnd();
        initTerracotta();
        initConcrete();
        initStoneSlabs();
        initSoil();
        initPlants();
        initWoodTypes();
    }
    
    private static void initStone() {
        
        //------ Stone Variants ------//
    
        // General
        
        DecoBlocks.stoneVariants = new StoneVariantsBlock(DecoBlockIDs.STONE_VARIANTS_ID, false);
        register(DecoBlocks.stoneVariants, StoneVariantsBlock.names);
        DecoBlocks.crackedStoneVariants = new StoneVariantsBlock(DecoBlockIDs.CRACKED_STONE_VARIANTS_ID, true);
        
        DecoBlocks.cobblestoneVariants = new CobblestoneVariantsBlock(DecoBlockIDs.COBBLESTONE_VARIANTS_ID);
        register(DecoBlocks.cobblestoneVariants, StoneVariantsBlock.names);
    
        DecoBlocks.stoneBrickVariants = new StoneBrickVariantsBlock(DecoBlockIDs.STONE_BRICK_VARIANTS_ID);
        register(DecoBlocks.stoneBrickVariants, StoneVariantsBlock.names);
    
        DecoBlocks.polishedStoneVariants = new PolishedStoneVariantsBlock(DecoBlockIDs.POLISHED_STONE_VARIANTS_ID);
        register(DecoBlocks.polishedStoneVariants, StoneVariantsBlock.names);
        
        // Granite
        
        DecoBlocks.roughGranite = new RoughStoneVariantBlock(DecoBlockIDs.ROUGH_GRANITE_ID, StoneVariantsBlock.GRANITE_TYPE);
    
        DecoBlocks.infestedGranite = new SilverfishBlock(DecoBlockIDs.INFESTED_GRANITE_ID, DecoBlocks.stoneVariants, StoneVariantsBlock.GRANITE_TYPE);
        
        DecoBlocks.looseGraniteCobblestone = new LooseCobblestoneVariantsBlock(DecoBlockIDs.LOOSE_GRANITE_COBBLESTONE_ID, StoneVariantsBlock.GRANITE_TYPE,
                "decoOverlayGraniteCobbleLava")
                .setUnlocalizedName("decoBlockGraniteCobbleLoose");
        DecoBlocks.looseGraniteBrick = new LooseStoneBrickVariantsBlock(DecoBlockIDs.LOOSE_GRANITE_BRICK_ID, StoneVariantsBlock.GRANITE_TYPE,
                "decoOverlayGraniteBrickLava")
                .setUnlocalizedName("decoBlockGraniteBrickLoose");
        
        DecoBlocks.looseGraniteCobblestoneStairs = new LooseStoneVariantStairsBlock(DecoBlockIDs.LOOSE_GRANITE_COBBLESTONE_STAIRS_ID,
                DecoBlocks.cobblestoneVariants, StoneVariantsBlock.GRANITE_TYPE, DecoBlockIDs.GRANITE_COBBLESTONE_STAIRS_ID)
                .setUnlocalizedName("decoBlockGraniteCobbleLooseStairs");
        DecoBlocks.looseGraniteBrickStairs = new LooseStoneVariantStairsBlock(DecoBlockIDs.LOOSE_GRANITE_BRICK_STAIRS_ID,
                DecoBlocks.stoneBrickVariants, StoneVariantsBlock.GRANITE_TYPE, DecoBlockIDs.GRANITE_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockGraniteBrickLooseStairs");
    
        DecoBlocks.graniteSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.GRANITE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockGranite",
                DecoBlocks.stoneVariants.blockHardness, DecoBlocks.stoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockGraniteSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.graniteSidingAndCorner.blockID - 256));
        DecoBlocks.graniteMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.GRANITE_MOULDING_ID, Material.rock,
                "decoBlockGranite", "decoBlockGraniteColumn",
                DecoBlockIDs.GRANITE_SIDING_AND_CORNER_ID,
                DecoBlocks.stoneVariants.blockHardness, DecoBlocks.stoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockGraniteMoulding");
        register(new MouldingBlockItem(DecoBlocks.graniteMoulding.blockID - 256));
        DecoBlocks.graniteStairs = new StairsBlock(DecoBlockIDs.GRANITE_STAIRS_ID, DecoBlocks.stoneVariants, StoneVariantsBlock.GRANITE_TYPE)
                .setUnlocalizedName("decoBlockGraniteStairs");
    
        DecoBlocks.graniteCobblestoneSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.GRANITE_COBBLESTONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockGraniteCobble",
                DecoBlocks.cobblestoneVariants.blockHardness, DecoBlocks.cobblestoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockGraniteCobbleSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.graniteCobblestoneSidingAndCorner.blockID - 256));
        DecoBlocks.graniteCobblestoneMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.GRANITE_COBBLESTONE_MOULDING_ID, Material.rock,
                "decoBlockGraniteCobble", "decoBlockGraniteCobbleColumn",
                DecoBlockIDs.GRANITE_COBBLESTONE_SIDING_AND_CORNER_ID,
                DecoBlocks.cobblestoneVariants.blockHardness, DecoBlocks.cobblestoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockGraniteCobbleMoulding");
        register(new MouldingBlockItem(DecoBlocks.graniteCobblestoneMoulding.blockID - 256));
        DecoBlocks.graniteCobblestoneStairs = new MortaredStairsBlock(DecoBlockIDs.GRANITE_COBBLESTONE_STAIRS_ID, DecoBlocks.cobblestoneVariants,
                StoneVariantsBlock.GRANITE_TYPE, DecoBlockIDs.LOOSE_GRANITE_COBBLESTONE_STAIRS_ID)
                .setUnlocalizedName("decoBlockGraniteCobbleStairs");
    
        DecoBlocks.graniteBrickSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.GRANITE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockGraniteBrick",
                DecoBlocks.stoneBrickVariants.blockHardness, DecoBlocks.stoneBrickVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockGraniteBrickSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.graniteBrickSidingAndCorner.blockID - 256));
        DecoBlocks.graniteBrickMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.GRANITE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockGraniteBrick", "decoBlockGraniteBrickColumn",
                DecoBlockIDs.GRANITE_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.stoneBrickVariants.blockHardness, DecoBlocks.stoneBrickVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockGraniteBrickMoulding");
        register(new MouldingBlockItem(DecoBlocks.graniteBrickMoulding.blockID - 256));
        DecoBlocks.graniteBrickStairs = new MortaredStairsBlock(DecoBlockIDs.GRANITE_BRICK_STAIRS_ID, DecoBlocks.stoneBrickVariants,
                StoneVariantsBlock.GRANITE_TYPE, DecoBlockIDs.LOOSE_GRANITE_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockGraniteBrickStairs");
        
        DecoBlocks.polishedGraniteSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.POLISHED_GRANITE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockGraniteSmooth",
                DecoBlocks.polishedStoneVariants.blockHardness, DecoBlocks.polishedStoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockGraniteSmoothSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedGraniteSidingAndCorner.blockID - 256));
        DecoBlocks.polishedGraniteMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.POLISHED_GRANITE_MOULDING_ID, Material.rock,
                "decoBlockGraniteSmooth", "decoBlockGraniteSmoothColumn",
                DecoBlockIDs.GRANITE_SIDING_AND_CORNER_ID,
                DecoBlocks.polishedStoneVariants.blockHardness, DecoBlocks.polishedStoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockGraniteSmoothMoulding");
        register(new MouldingBlockItem(DecoBlocks.polishedGraniteMoulding.blockID - 256));
        DecoBlocks.polishedGraniteStairs = new StairsBlock(DecoBlockIDs.POLISHED_GRANITE_STAIRS_ID, DecoBlocks.polishedStoneVariants,
                StoneVariantsBlock.GRANITE_TYPE)
                .setUnlocalizedName("decoBlockGraniteSmoothStairs");
    
        // Andesite
    
        DecoBlocks.roughAndesite = new RoughStoneVariantBlock(DecoBlockIDs.ROUGH_ANDESITE_ID, StoneVariantsBlock.ANDESITE_TYPE);
    
        DecoBlocks.infestedAndesite = new SilverfishBlock(DecoBlockIDs.INFESTED_ANDESITE_ID, DecoBlocks.stoneVariants, StoneVariantsBlock.ANDESITE_TYPE);
    
        DecoBlocks.looseAndesiteCobblestone = new LooseCobblestoneVariantsBlock(DecoBlockIDs.LOOSE_ANDESITE_COBBLESTONE_ID, StoneVariantsBlock.ANDESITE_TYPE,
                "decoOverlayAndesiteCobbleLava")
                .setUnlocalizedName("decoBlockAndesiteCobbleLoose");
        DecoBlocks.looseAndesiteBrick = new LooseStoneBrickVariantsBlock(DecoBlockIDs.LOOSE_ANDESITE_BRICK_ID, StoneVariantsBlock.ANDESITE_TYPE,
                "decoOverlayAndesiteBrickLava")
                .setUnlocalizedName("decoBlockAndesiteBrickLoose");
    
        DecoBlocks.looseAndesiteCobblestoneStairs = new LooseStoneVariantStairsBlock(DecoBlockIDs.LOOSE_ANDESITE_COBBLESTONE_STAIRS_ID,
                DecoBlocks.cobblestoneVariants, StoneVariantsBlock.ANDESITE_TYPE, DecoBlockIDs.ANDESITE_COBBLESTONE_STAIRS_ID)
                .setUnlocalizedName("decoBlockAndesiteCobbleLooseStairs");
        DecoBlocks.looseAndesiteBrickStairs = new LooseStoneVariantStairsBlock(DecoBlockIDs.LOOSE_ANDESITE_BRICK_STAIRS_ID,
                DecoBlocks.stoneBrickVariants, StoneVariantsBlock.ANDESITE_TYPE, DecoBlockIDs.ANDESITE_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockAndesiteBrickLooseStairs");
    
        DecoBlocks.andesiteSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.ANDESITE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockAndesite",
                DecoBlocks.stoneVariants.blockHardness, DecoBlocks.stoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockAndesiteSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.andesiteSidingAndCorner.blockID - 256));
        DecoBlocks.andesiteMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.ANDESITE_MOULDING_ID, Material.rock,
                "decoBlockAndesite", "decoBlockAndesiteColumn",
                DecoBlockIDs.ANDESITE_SIDING_AND_CORNER_ID,
                DecoBlocks.stoneVariants.blockHardness, DecoBlocks.stoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockAndesiteMoulding");
        register(new MouldingBlockItem(DecoBlocks.andesiteMoulding.blockID - 256));
        DecoBlocks.andesiteStairs = new StairsBlock(DecoBlockIDs.ANDESITE_STAIRS_ID, DecoBlocks.stoneVariants, StoneVariantsBlock.ANDESITE_TYPE)
                .setUnlocalizedName("decoBlockAndesiteStairs");
    
        DecoBlocks.andesiteCobblestoneSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.ANDESITE_COBBLESTONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockAndesiteCobble",
                DecoBlocks.cobblestoneVariants.blockHardness, DecoBlocks.cobblestoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockAndesiteCobbleSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.andesiteCobblestoneSidingAndCorner.blockID - 256));
        DecoBlocks.andesiteCobblestoneMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.ANDESITE_COBBLESTONE_MOULDING_ID, Material.rock,
                "decoBlockAndesiteCobble", "decoBlockAndesiteCobbleColumn",
                DecoBlockIDs.ANDESITE_COBBLESTONE_SIDING_AND_CORNER_ID,
                DecoBlocks.cobblestoneVariants.blockHardness, DecoBlocks.cobblestoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockAndesiteCobbleMoulding");
        register(new MouldingBlockItem(DecoBlocks.andesiteCobblestoneMoulding.blockID - 256));
        DecoBlocks.andesiteCobblestoneStairs = new MortaredStairsBlock(DecoBlockIDs.ANDESITE_COBBLESTONE_STAIRS_ID, DecoBlocks.cobblestoneVariants,
                StoneVariantsBlock.ANDESITE_TYPE, DecoBlockIDs.LOOSE_ANDESITE_COBBLESTONE_STAIRS_ID)
                .setUnlocalizedName("decoBlockAndesiteCobbleStairs");
    
        DecoBlocks.andesiteBrickSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.ANDESITE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockAndesiteBrick",
                DecoBlocks.stoneBrickVariants.blockHardness, DecoBlocks.stoneBrickVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockAndesiteBrickSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.andesiteBrickSidingAndCorner.blockID - 256));
        DecoBlocks.andesiteBrickMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.ANDESITE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockAndesiteBrick", "decoBlockAndesiteBrickColumn",
                DecoBlockIDs.ANDESITE_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.stoneBrickVariants.blockHardness, DecoBlocks.stoneBrickVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockAndesiteBrickMoulding");
        register(new MouldingBlockItem(DecoBlocks.andesiteBrickMoulding.blockID - 256));
        DecoBlocks.andesiteBrickStairs = new MortaredStairsBlock(DecoBlockIDs.ANDESITE_BRICK_STAIRS_ID, DecoBlocks.stoneBrickVariants,
                StoneVariantsBlock.ANDESITE_TYPE, DecoBlockIDs.LOOSE_ANDESITE_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockAndesiteBrickStairs");
    
        DecoBlocks.polishedAndesiteSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.POLISHED_ANDESITE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockAndesiteSmooth",
                DecoBlocks.polishedStoneVariants.blockHardness, DecoBlocks.polishedStoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockAndesiteSmoothSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedAndesiteSidingAndCorner.blockID - 256));
        DecoBlocks.polishedAndesiteMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.POLISHED_ANDESITE_MOULDING_ID, Material.rock,
                "decoBlockAndesiteSmooth", "decoBlockAndesiteSmoothColumn",
                DecoBlockIDs.ANDESITE_SIDING_AND_CORNER_ID,
                DecoBlocks.polishedStoneVariants.blockHardness, DecoBlocks.polishedStoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockAndesiteSmoothMoulding");
        register(new MouldingBlockItem(DecoBlocks.polishedAndesiteMoulding.blockID - 256));
        DecoBlocks.polishedAndesiteStairs = new StairsBlock(DecoBlockIDs.POLISHED_ANDESITE_STAIRS_ID, DecoBlocks.polishedStoneVariants,
                StoneVariantsBlock.ANDESITE_TYPE)
                .setUnlocalizedName("decoBlockAndesiteSmoothStairs");
        
        // Diorite
    
        DecoBlocks.roughDiorite = new RoughStoneVariantBlock(DecoBlockIDs.ROUGH_DIORITE_ID, StoneVariantsBlock.DIORITE_TYPE);
    
        DecoBlocks.infestedDiorite = new SilverfishBlock(DecoBlockIDs.INFESTED_DIORITE_ID, DecoBlocks.stoneVariants, StoneVariantsBlock.DIORITE_TYPE);
    
        DecoBlocks.looseDioriteCobblestone = new LooseCobblestoneVariantsBlock(DecoBlockIDs.LOOSE_DIORITE_COBBLESTONE_ID, StoneVariantsBlock.DIORITE_TYPE,
                "decoOverlayDioriteCobbleLava")
                .setUnlocalizedName("decoBlockDioriteCobbleLoose");
        DecoBlocks.looseDioriteBrick = new LooseStoneBrickVariantsBlock(DecoBlockIDs.LOOSE_DIORITE_BRICK_ID, StoneVariantsBlock.DIORITE_TYPE,
                "decoOverlayDioriteBrickLava")
                .setUnlocalizedName("decoBlockDioriteBrickLoose");
    
        DecoBlocks.looseDioriteCobblestoneStairs = new LooseStoneVariantStairsBlock(DecoBlockIDs.LOOSE_DIORITE_COBBLESTONE_STAIRS_ID,
                DecoBlocks.cobblestoneVariants, StoneVariantsBlock.DIORITE_TYPE, DecoBlockIDs.DIORITE_COBBLESTONE_STAIRS_ID)
                .setUnlocalizedName("decoBlockDioriteCobbleLooseStairs");
        DecoBlocks.looseDioriteBrickStairs = new LooseStoneVariantStairsBlock(DecoBlockIDs.LOOSE_DIORITE_BRICK_STAIRS_ID,
                DecoBlocks.stoneBrickVariants, StoneVariantsBlock.DIORITE_TYPE, DecoBlockIDs.DIORITE_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockDioriteBrickLooseStairs");
    
        DecoBlocks.dioriteSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.DIORITE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockDiorite",
                DecoBlocks.stoneVariants.blockHardness, DecoBlocks.stoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockDioriteSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.dioriteSidingAndCorner.blockID - 256));
        DecoBlocks.dioriteMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.DIORITE_MOULDING_ID, Material.rock,
                "decoBlockDiorite", "decoBlockDioriteColumn",
                DecoBlockIDs.DIORITE_SIDING_AND_CORNER_ID,
                DecoBlocks.stoneVariants.blockHardness, DecoBlocks.stoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockDioriteMoulding");
        register(new MouldingBlockItem(DecoBlocks.dioriteMoulding.blockID - 256));
        DecoBlocks.dioriteStairs = new StairsBlock(DecoBlockIDs.DIORITE_STAIRS_ID, DecoBlocks.stoneVariants, StoneVariantsBlock.DIORITE_TYPE)
                .setUnlocalizedName("decoBlockDioriteStairs");
    
        DecoBlocks.dioriteCobblestoneSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.DIORITE_COBBLESTONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockDioriteCobble",
                DecoBlocks.cobblestoneVariants.blockHardness, DecoBlocks.cobblestoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockDioriteCobbleSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.dioriteCobblestoneSidingAndCorner.blockID - 256));
        DecoBlocks.dioriteCobblestoneMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.DIORITE_COBBLESTONE_MOULDING_ID, Material.rock,
                "decoBlockDioriteCobble", "decoBlockDioriteCobbleColumn",
                DecoBlockIDs.DIORITE_COBBLESTONE_SIDING_AND_CORNER_ID,
                DecoBlocks.cobblestoneVariants.blockHardness, DecoBlocks.cobblestoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockDioriteCobbleMoulding");
        register(new MouldingBlockItem(DecoBlocks.dioriteCobblestoneMoulding.blockID - 256));
        DecoBlocks.dioriteCobblestoneStairs = new MortaredStairsBlock(DecoBlockIDs.DIORITE_COBBLESTONE_STAIRS_ID, DecoBlocks.cobblestoneVariants,
                StoneVariantsBlock.DIORITE_TYPE, DecoBlockIDs.LOOSE_DIORITE_COBBLESTONE_STAIRS_ID)
                .setUnlocalizedName("decoBlockDioriteCobbleStairs");
    
        DecoBlocks.dioriteBrickSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.DIORITE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockDioriteBrick",
                DecoBlocks.stoneBrickVariants.blockHardness, DecoBlocks.stoneBrickVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockDioriteBrickSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.dioriteBrickSidingAndCorner.blockID - 256));
        DecoBlocks.dioriteBrickMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.DIORITE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockDioriteBrick", "decoBlockDioriteBrickColumn",
                DecoBlockIDs.DIORITE_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.stoneBrickVariants.blockHardness, DecoBlocks.stoneBrickVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockDioriteBrickMoulding");
        register(new MouldingBlockItem(DecoBlocks.dioriteBrickMoulding.blockID - 256));
        DecoBlocks.dioriteBrickStairs = new MortaredStairsBlock(DecoBlockIDs.DIORITE_BRICK_STAIRS_ID, DecoBlocks.stoneBrickVariants,
                StoneVariantsBlock.DIORITE_TYPE, DecoBlockIDs.LOOSE_DIORITE_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockDioriteBrickStairs");
    
        DecoBlocks.polishedDioriteSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.POLISHED_DIORITE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockDioriteSmooth",
                DecoBlocks.polishedStoneVariants.blockHardness, DecoBlocks.polishedStoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockDioriteSmoothSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedDioriteSidingAndCorner.blockID - 256));
        DecoBlocks.polishedDioriteMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.POLISHED_DIORITE_MOULDING_ID, Material.rock,
                "decoBlockDioriteSmooth", "decoBlockDioriteSmoothColumn",
                DecoBlockIDs.DIORITE_SIDING_AND_CORNER_ID,
                DecoBlocks.polishedStoneVariants.blockHardness, DecoBlocks.polishedStoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockDioriteSmoothMoulding");
        register(new MouldingBlockItem(DecoBlocks.polishedDioriteMoulding.blockID - 256));
        DecoBlocks.polishedDioriteStairs = new StairsBlock(DecoBlockIDs.POLISHED_DIORITE_STAIRS_ID, DecoBlocks.polishedStoneVariants,
                StoneVariantsBlock.DIORITE_TYPE)
                .setUnlocalizedName("decoBlockDioriteSmoothStairs");
        
        // Slate
        
        DecoBlocks.slate = new SlateBlock(DecoBlockIDs.SLATE_ID);
        DecoBlocks.roughSlate = new RoughStoneVariantBlock(DecoBlockIDs.ROUGH_SLATE_ID, StoneVariantsBlock.SLATE_TYPE, DecoBlockIDs.SLATE_ID);
        
        DecoBlocks.infestedSlate = new SilverfishBlock(DecoBlockIDs.INFESTED_SLATE_ID, DecoBlocks.slate, 0);
        
        DecoBlocks.slateTiles = new SlateTilesBlock(DecoBlockIDs.SLATE_TILES_ID);
    
        DecoBlocks.looseSlateCobblestone = new LooseSlateCobblestoneBlock(DecoBlockIDs.LOOSE_SLATE_COBBLESTONE_ID);
        DecoBlocks.looseSlateBrick = new LooseSlateBrickBlock(DecoBlockIDs.LOOSE_SLATE_BRICK_ID);
    
        DecoBlocks.looseSlateCobblestoneStairs = new LooseStoneVariantStairsBlock(DecoBlockIDs.LOOSE_SLATE_COBBLESTONE_STAIRS_ID,
                DecoBlocks.cobblestoneVariants, StoneVariantsBlock.SLATE_TYPE, DecoBlockIDs.SLATE_COBBLESTONE_STAIRS_ID)
                .setUnlocalizedName("decoBlockSlateCobbleLooseStairs");
        DecoBlocks.looseSlateBrickStairs = new LooseStoneVariantStairsBlock(DecoBlockIDs.LOOSE_SLATE_BRICK_STAIRS_ID,
                DecoBlocks.stoneBrickVariants, StoneVariantsBlock.SLATE_TYPE, DecoBlockIDs.SLATE_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockSlateBrickLooseStairs");
    
        DecoBlocks.slateSidingAndCorner = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.SLATE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockSlate_top", "decoBlockSlate_side",
                DecoBlocks.slate.blockHardness, DecoBlocks.slate.blockResistance, Block.soundStoneFootstep,
                "decoBlockSlateSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.slateSidingAndCorner.blockID - 256));
        DecoBlocks.slateMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.SLATE_MOULDING_ID, Material.rock,
                "decoBlockSlate_top", "decoBlockSlate_side", "decoBlockSlateColumn",
                DecoBlockIDs.SLATE_SIDING_AND_CORNER_ID,
                DecoBlocks.slate.blockHardness, DecoBlocks.slate.blockResistance, Block.soundStoneFootstep,
                "decoBlockSlateMoulding");
        register(new MouldingBlockItem(DecoBlocks.slateMoulding.blockID - 256));
        DecoBlocks.slateStairs = new StairsBlock(DecoBlockIDs.SLATE_STAIRS_ID, DecoBlocks.slate, 0)
                .setUnlocalizedName("decoBlockSlateStairs");
    
        DecoBlocks.slateCobblestoneSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.SLATE_COBBLESTONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockSlateCobble",
                DecoBlocks.cobblestoneVariants.blockHardness, DecoBlocks.cobblestoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockSlateCobbleSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.slateCobblestoneSidingAndCorner.blockID - 256));
        DecoBlocks.slateCobblestoneMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.SLATE_COBBLESTONE_MOULDING_ID, Material.rock,
                "decoBlockSlateCobble", "decoBlockSlateCobbleColumn",
                DecoBlockIDs.SLATE_COBBLESTONE_SIDING_AND_CORNER_ID,
                DecoBlocks.cobblestoneVariants.blockHardness, DecoBlocks.cobblestoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockSlateCobbleMoulding");
        register(new MouldingBlockItem(DecoBlocks.slateCobblestoneMoulding.blockID - 256));
        DecoBlocks.slateCobblestoneStairs = new MortaredStairsBlock(DecoBlockIDs.SLATE_COBBLESTONE_STAIRS_ID, DecoBlocks.cobblestoneVariants, StoneVariantsBlock.SLATE_TYPE,
                DecoBlockIDs.LOOSE_SLATE_COBBLESTONE_STAIRS_ID)
                .setUnlocalizedName("decoBlockSlateCobbleStairs");
    
        DecoBlocks.slateBrickSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.SLATE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockSlateBrick",
                DecoBlocks.stoneBrickVariants.blockHardness, DecoBlocks.stoneBrickVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockSlateBrickSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.slateBrickSidingAndCorner.blockID - 256));
        DecoBlocks.slateBrickMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.SLATE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockSlateBrick", "decoBlockSlateBrickColumn",
                DecoBlockIDs.SLATE_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.stoneBrickVariants.blockHardness, DecoBlocks.stoneBrickVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockSlateBrickMoulding");
        register(new MouldingBlockItem(DecoBlocks.slateBrickMoulding.blockID - 256));
        DecoBlocks.slateBrickStairs = new MortaredStairsBlock(DecoBlockIDs.SLATE_BRICK_STAIRS_ID, DecoBlocks.stoneBrickVariants, StoneVariantsBlock.SLATE_TYPE,
                DecoBlockIDs.LOOSE_SLATE_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockSlateBrickStairs");
    
        DecoBlocks.polishedSlateSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.POLISHED_SLATE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockSlateSmooth",
                DecoBlocks.polishedStoneVariants.blockHardness, DecoBlocks.polishedStoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockSlateSmoothSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedSlateSidingAndCorner.blockID - 256));
        DecoBlocks.polishedSlateMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.POLISHED_SLATE_MOULDING_ID, Material.rock,
                "decoBlockSlateSmooth", "decoBlockSlateSmoothColumn",
                DecoBlockIDs.SLATE_SIDING_AND_CORNER_ID,
                DecoBlocks.polishedStoneVariants.blockHardness, DecoBlocks.polishedStoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockSlateSmoothMoulding");
        register(new MouldingBlockItem(DecoBlocks.polishedSlateMoulding.blockID - 256));
        DecoBlocks.polishedSlateStairs = new StairsBlock(DecoBlockIDs.POLISHED_SLATE_STAIRS_ID, DecoBlocks.polishedStoneVariants, StoneVariantsBlock.SLATE_TYPE)
                .setUnlocalizedName("decoBlockSlateSmoothStairs");
    
        DecoBlocks.slateTilesSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.SLATE_TILES_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockSlateTiles",
                DecoBlocks.slateTiles.blockHardness, DecoBlocks.slateTiles.blockResistance, Block.soundStoneFootstep,
                "decoBlockSlateTilesSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.slateTilesSidingAndCorner.blockID - 256));
        DecoBlocks.slateTilesMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.SLATE_TILES_MOULDING_ID, Material.rock,
                "decoBlockSlateTiles", "decoBlockSlateTilesColumn",
                DecoBlockIDs.SLATE_TILES_SIDING_AND_CORNER_ID,
                DecoBlocks.slateTiles.blockHardness, DecoBlocks.slateTiles.blockResistance, Block.soundStoneFootstep,
                "decoBlockSlateTilesMoulding");
        register(new MouldingBlockItem(DecoBlocks.slateTilesMoulding.blockID - 256));
        DecoBlocks.slateTilesStairs = new MortaredStairsBlock(DecoBlockIDs.SLATE_TILES_STAIRS_ID, DecoBlocks.slateTiles, 0,
                DecoBlockIDs.LOOSE_SLATE_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockSlateTileStairs");
    
        //------ White Stone ------//
    
    
    
        //------ Prismarine ------//
        
        
    
        //------ Misc Stone ------//
    
        // Misc
        
        // Extra sub blocks
    
        // Replace references as otherwise they would get messed up
        RoughStoneBlock.strataLevelBlockArray = new RoughStoneBlock[] {
                (RoughStoneBlock) BTWBlocks.upperStrataRoughStone,
                (RoughStoneBlock) BTWBlocks.midStrataRoughStone,
                (RoughStoneBlock) BTWBlocks.deepStrataRoughStone
        };
    }
    
    private static void initPrismarine() {
        DecoBlocks.prismarine = new PrismarineBlock(DecoBlockIDs.PRISMARINE_ID);
        register(DecoBlocks.prismarine, new String[] {"prismarine", "prismarineBrick", "prismarineDark"});
        
        DecoBlocks.prismarineLantern = new DecoBlock(DecoBlockIDs.PRISMARINE_LANTERN_ID, Material.glass)
                .setHardness(0.6F)
                .setResistance(0.5F)
                .setPicksEffectiveOn()
                .setLightValue(1.0F)
                .setStepSound(Block.soundGlassFootstep)
                .setUnlocalizedName("decoBlockPrismarineLantern")
                .setCreativeTab(CreativeTabs.tabBlock);
        BeaconTileEntity.addBeaconEffect(DecoBlocks.prismarineLantern.blockID, BeaconTileEntity.NIGHT_VISION_EFFECT);
        
        DecoBlocks.prismarineSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.PRISMARINE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockPrismarine",
                DecoBlocks.prismarine.blockHardness, DecoBlocks.prismarine.blockResistance, Block.soundStoneFootstep,
                "decoBlockPrismarineSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.prismarineSidingAndCorner.blockID - 256));
        DecoBlocks.prismarineMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.PRISMARINE_MOULDING_ID, Material.rock,
                "decoBlockPrismarine", "decoBlockPrismarineColumn",
                DecoBlockIDs.PRISMARINE_SIDING_AND_CORNER_ID,
                DecoBlocks.prismarine.blockHardness, DecoBlocks.prismarine.blockResistance, Block.soundStoneFootstep,
                "decoBlockPrismarineMoulding");
        register(new MouldingBlockItem(DecoBlocks.prismarineMoulding.blockID - 256));
        DecoBlocks.prismarineStairs = new StairsBlock(DecoBlockIDs.PRISMARINE_STAIRS_ID, DecoBlocks.prismarine, PrismarineBlock.DEFAULT_TYPE)
                .setUnlocalizedName("decoBlockPrismarineStairs");
    
        DecoBlocks.prismarineBrickSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.PRISMARINE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockPrismarineBrick",
                DecoBlocks.prismarine.blockHardness, DecoBlocks.prismarine.blockResistance, Block.soundStoneFootstep,
                "decoBlockPrismarineBrickSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.prismarineBrickSidingAndCorner.blockID - 256));
        DecoBlocks.prismarineBrickMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.PRISMARINE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockPrismarineBrick", "decoBlockPrismarineBrickColumn",
                DecoBlockIDs.PRISMARINE_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.prismarine.blockHardness, DecoBlocks.prismarine.blockResistance, Block.soundStoneFootstep,
                "decoBlockPrismarineBrickMoulding");
        register(new MouldingBlockItem(DecoBlocks.prismarineBrickMoulding.blockID - 256));
        DecoBlocks.prismarineBrickStairs = new StairsBlock(DecoBlockIDs.PRISMARINE_BRICK_STAIRS_ID, DecoBlocks.prismarine, PrismarineBlock.BRICK_TYPE)
                .setUnlocalizedName("decoBlockPrismarineBrickStairs");
    
        DecoBlocks.darkPrismarineSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.DARK_PRISMARINE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockDarkPrismarine",
                DecoBlocks.prismarine.blockHardness, DecoBlocks.prismarine.blockResistance, Block.soundStoneFootstep,
                "decoBlockPrismarineDarkSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.darkPrismarineSidingAndCorner.blockID - 256));
        DecoBlocks.darkPrismarineMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.DARK_PRISMARINE_MOULDING_ID, Material.rock,
                "decoBlockDarkPrismarine", "decoBlockDarkPrismarineColumn",
                DecoBlockIDs.DARK_PRISMARINE_SIDING_AND_CORNER_ID,
                DecoBlocks.prismarine.blockHardness, DecoBlocks.prismarine.blockResistance, Block.soundStoneFootstep,
                "decoBlockPrismarineDarkMoulding");
        register(new MouldingBlockItem(DecoBlocks.darkPrismarineMoulding.blockID - 256));
        DecoBlocks.darkPrismarineStairs = new StairsBlock(DecoBlockIDs.DARK_PRISMARINE_STAIRS_ID, DecoBlocks.prismarine, PrismarineBlock.DARK_TYPE)
                .setUnlocalizedName("decoBlockPrismarineDarkStairs");
    }
    
    private static void initSandstone() {
    
    }
    
    private static void initNether() {
        
        //------ General Nether Blocks ------//
        
        DecoBlocks.basalt = new DecoPillarBlock(DecoBlockIDs.BASALT_ID, BTWBlocks.netherRockMaterial,
                new String[] {
                        "decoBlockBasalt_top",
                        "decoBlockBasaltSmooth_top"
                },
                new String[] {
                        "decoBlockBasalt_side",
                        "decoBlockBasaltSmooth_side"
                })
                .setPicksEffectiveOn()
                .setCreativeTab(CreativeTabs.tabBlock)
                .setHardness(2.0F)
                .setHardness(10.0F)
                .setUnlocalizedName("decoBlockBasalt");
        register(DecoBlocks.basalt, new String[] {"basalt", "basaltSmooth"});
    
        DecoBlocks.magma = new MagmaBlock(DecoBlockIDs.MAGMA_ID);
        
        //------ Nether Brick ------//
    
        DecoBlocks.netherBrick = new DecoNetherBrickBlock(DecoBlockIDs.NETHER_BRICKS_ID);
        register(DecoBlocks.netherBrick, new String[] {"red", "redChiseled", "chiseled"});
        
        DecoBlocks.redNetherBrickSidingAndCorner = new SidingAndCornerAndDecorativeBlock(DecoBlockIDs.RED_NETHER_BRICK_SIDING_AND_CORNER_ID,
                BTWBlocks.netherRockMaterial,
                "decoBlockNetherBricksRed",
                DecoBlocks.netherBrick.blockHardness, DecoBlocks.netherBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockNetherBrickRedSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.redNetherBrickSidingAndCorner.blockID - 256));
        DecoBlocks.redNetherBrickMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.RED_NETHER_BRICK_MOULDING_ID,
                BTWBlocks.netherRockMaterial,
                "decoBlockNetherBricksRed", "decoBlockNetherBrickRedColumn",
                DecoBlockIDs.RED_NETHER_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.netherBrick.blockHardness, DecoBlocks.netherBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockNetherBrickRedMoulding");
        register(new MouldingBlockItem(DecoBlocks.redNetherBrickMoulding.blockID - 256));
        DecoBlocks.redNetherBrickStairs = new StairsBlock(DecoBlockIDs.RED_NETHER_BRICK_STAIRS_ID, DecoBlocks.netherBrick, DecoNetherBrickBlock.RED_TYPE)
                .setUnlocalizedName("decoBlockNetherBrickRedStairs");
        
        DecoBlocks.looseRedNetherBrick = new LooseRedNetherBrickBlock(DecoBlockIDs.LOOSE_RED_NETHER_BRICKS_ID);
        DecoBlocks.looseRedNetherBrickStairs = new LooseStoneVariantStairsBlock(DecoBlockIDs.LOOSE_RED_NETHER_BRICK_STAIRS_ID, DecoBlocks.looseRedNetherBrick,
                0, DecoBlockIDs.RED_NETHER_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockNetherBrickRedStairsLoose");
        
        //------ Infused Stone ------//
        
        DecoBlocks.infusedStone = new InfusedStoneBlock(DecoBlockIDs.INFUSED_STONE_ID);
        register(DecoBlocks.infusedStone, new String[] {"default", "smooth", "brick", "chiseled"});
    
        DecoBlocks.infusedStoneSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.INFUSED_STONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockInfusedStone",
                DecoBlocks.infusedStone.blockHardness, DecoBlocks.infusedStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockInfusedStoneSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.infusedStoneSidingAndCorner.blockID - 256));
        DecoBlocks.infusedStoneMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.INFUSED_STONE_MOULDING_ID, Material.rock,
                "decoBlockInfusedStone", "decoBlockInfusedStoneColumn",
                DecoBlockIDs.INFUSED_STONE_SIDING_AND_CORNER_ID,
                DecoBlocks.infusedStone.blockHardness, DecoBlocks.infusedStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockInfusedStoneMoulding");
        register(new MouldingBlockItem(DecoBlocks.infusedStoneMoulding.blockID - 256));
        DecoBlocks.infusedStoneStairs = new StairsBlock(DecoBlockIDs.INFUSED_STONE_STAIRS_ID, DecoBlocks.infusedStone, InfusedStoneBlock.TYPE_DEFAULT)
                .setUnlocalizedName("decoBlockInfusedStoneStairs");
    
        DecoBlocks.polishedInfusedStoneSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.POLISHED_INFUSED_STONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockInfusedStoneSmooth",
                DecoBlocks.infusedStone.blockHardness, DecoBlocks.infusedStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockInfusedStoneSmoothSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedInfusedStoneSidingAndCorner.blockID - 256));
        DecoBlocks.polishedInfusedStoneMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.POLISHED_INFUSED_STONE_MOULDING_ID, Material.rock,
                "decoBlockInfusedStoneSmooth", "decoBlockInfusedStoneSmoothColumn",
                DecoBlockIDs.POLISHED_INFUSED_STONE_SIDING_AND_CORNER_ID,
                DecoBlocks.infusedStone.blockHardness, DecoBlocks.infusedStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockInfusedStoneSmoothMoulding");
        register(new MouldingBlockItem(DecoBlocks.polishedInfusedStoneMoulding.blockID - 256));
        DecoBlocks.polishedInfusedStoneStairs = new StairsBlock(DecoBlockIDs.POLISHED_INFUSED_STONE_STAIRS_ID, DecoBlocks.infusedStone, InfusedStoneBlock.TYPE_SMOOTH)
                .setUnlocalizedName("decoBlockInfusedStoneSmoothStairs");
    
        DecoBlocks.infusedStoneBrickSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.INFUSED_STONE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockInfusedStoneBrick",
                DecoBlocks.infusedStone.blockHardness, DecoBlocks.infusedStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockInfusedStoneSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.infusedStoneBrickSidingAndCorner.blockID - 256));
        DecoBlocks.infusedStoneBrickMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.INFUSED_STONE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockInfusedStoneBrick", "decoBlockInfusedStoneBrickColumn",
                DecoBlockIDs.INFUSED_STONE_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.infusedStone.blockHardness, DecoBlocks.infusedStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockInfusedStoneMoulding");
        register(new MouldingBlockItem(DecoBlocks.infusedStoneBrickMoulding.blockID - 256));
        DecoBlocks.infusedStoneBrickStairs = new StairsBlock(DecoBlockIDs.INFUSED_STONE_BRICK_STAIRS_ID, DecoBlocks.infusedStone, InfusedStoneBlock.TYPE_BRICK)
                .setUnlocalizedName("decoBlockInfusedStoneBrickStairs");
    }
    
    private static void initEnd() {
        DecoBlocks.endStoneBrick = new DecoBlock(DecoBlockIDs.END_STONE_BRICK_ID, Material.rock)
                .setHardness(3.0F)
                .setResistance(15.0F)
                .setStepSound(Block.soundStoneFootstep)
                .setUnlocalizedName("decoBlockEndStoneBrick")
                .setCreativeTab(CreativeTabs.tabBlock);
        
        DecoBlocks.endStoneBrickSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.END_STONE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockEndStoneBrick",
                DecoBlocks.endStoneBrick.blockHardness, DecoBlocks.endStoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockEndStoneSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.endStoneBrickSidingAndCorner.blockID - 256));
        DecoBlocks.endStoneBrickMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.END_STONE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockEndStoneBrick", "decoBlockEndStoneBrickColumn",
                DecoBlockIDs.END_STONE_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.endStoneBrick.blockHardness, DecoBlocks.endStoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockEndStoneMoulding");
        register(new MouldingBlockItem(DecoBlocks.endStoneBrickMoulding.blockID - 256));
        DecoBlocks.endStoneBrickStairs = new StairsBlock(DecoBlockIDs.END_STONE_BRICK_STAIRS_ID, DecoBlocks.endStoneBrick, 0)
                .setUnlocalizedName("decoBlockEndStoneBrickStairs");
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
    
    // Done as its own method since it needs to be done after all referenced blocks are declared
    private static void initStoneSlabs() {
        DecoBlocks.looseStoneSlab = new LooseStoneSlabBlock(DecoBlockIDs.LOOSE_STONE_SLAB_ID,
                new Block[] {
                        DecoBlocks.looseGraniteCobblestone,
                        DecoBlocks.looseAndesiteCobblestone,
                        DecoBlocks.looseDioriteCobblestone,
                        DecoBlocks.looseGraniteBrick,
                        DecoBlocks.looseAndesiteBrick,
                        DecoBlocks.looseDioriteBrick,
                        DecoBlocks.looseSlateCobblestone,
                        DecoBlocks.looseSlateBrick
                },
                new int[] {
                        StoneHelper.GRANITE_COBBLESTONE_SLAB_ID,
                        StoneHelper.ANDESITE_COBBLESTONE_SLAB_ID,
                        StoneHelper.DIORITE_COBBLESTONE_SLAB_ID,
                        StoneHelper.GRANITE_BRICK_SLAB_ID,
                        StoneHelper.ANDESITE_BRICK_SLAB_ID,
                        StoneHelper.DIORITE_BRICK_SLAB_ID,
                        StoneHelper.SLATE_COBBLESTONE_SLAB_ID,
                        StoneHelper.SLATE_BRICK_SLAB_ID
                },
                new int[] {
                        StoneHelper.GRANITE_COBBLESTONE_SLAB_TYPE,
                        StoneHelper.ANDESITE_COBBLESTONE_SLAB_TYPE,
                        StoneHelper.DIORITE_COBBLESTONE_SLAB_TYPE,
                        StoneHelper.GRANITE_BRICK_SLAB_TYPE,
                        StoneHelper.ANDESITE_BRICK_SLAB_TYPE,
                        StoneHelper.DIORITE_BRICK_SLAB_TYPE,
                        StoneHelper.SLATE_COBBLESTONE_SLAB_TYPE,
                        StoneHelper.SLATE_BRICK_SLAB_TYPE
                })
                .setUnlocalizedName("decoBlockStoneSlabLoose");
    
        DecoBlocks.looseStoneSlab2 = new LooseStoneSlabBlock(DecoBlockIDs.LOOSE_STONE_SLAB_2_ID,
                new Block[] {
                        DecoBlocks.looseRedNetherBrick
                },
                new int[] {
                        StoneHelper.LOOSE_RED_NETHER_BRICK_SLAB_ID
                },
                new int[] {
                        StoneHelper.LOOSE_RED_NETHER_BRICK_SLAB_TYPE
                })
                .setUnlocalizedName("decoBlockStoneSlabLoose2");
        
        DecoBlocks.stoneSlab2 = new TerracottaBlock.StoneSlabBlock(DecoBlockIDs.STONE_SLAB_2_ID,
                new Block[] {
                        DecoBlocks.stoneVariants,
                        DecoBlocks.stoneVariants,
                        DecoBlocks.stoneVariants,
                        DecoBlocks.polishedStoneVariants,
                        DecoBlocks.polishedStoneVariants,
                        DecoBlocks.polishedStoneVariants,
                        DecoBlocks.cobblestoneVariants,
                        DecoBlocks.cobblestoneVariants
                },
                new int [] {
                        StoneVariantsBlock.GRANITE_TYPE,
                        StoneVariantsBlock.ANDESITE_TYPE,
                        StoneVariantsBlock.DIORITE_TYPE,
                        StoneVariantsBlock.GRANITE_TYPE,
                        StoneVariantsBlock.ANDESITE_TYPE,
                        StoneVariantsBlock.DIORITE_TYPE,
                        StoneVariantsBlock.GRANITE_TYPE,
                        StoneVariantsBlock.ANDESITE_TYPE
                },
                new boolean[] {
                        false, false, false, false, false, false,
                        true, true
                },
                new Block[] {
                        null, null, null, null, null, null,
                        DecoBlocks.looseStoneSlab,
                        DecoBlocks.looseStoneSlab
                },
                new int[] {
                        0, 0, 0, 0, 0, 0,
                        StoneHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_TYPE,
                        StoneHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_TYPE,
                })
                .setUnlocalizedName("decoBlockStoneSlab2");
        
        DecoBlocks.stoneSlab3 = new TerracottaBlock.StoneSlabBlock(DecoBlockIDs.STONE_SLAB_3_ID,
                new Block[] {
                        DecoBlocks.cobblestoneVariants,
                        DecoBlocks.stoneBrickVariants,
                        DecoBlocks.stoneBrickVariants,
                        DecoBlocks.stoneBrickVariants,
                        DecoBlocks.infusedStone,
                        DecoBlocks.infusedStone,
                        DecoBlocks.infusedStone,
                        Block.stone
                },
                new int [] {
                        StoneVariantsBlock.DIORITE_TYPE,
                        StoneVariantsBlock.GRANITE_TYPE,
                        StoneVariantsBlock.ANDESITE_TYPE,
                        StoneVariantsBlock.DIORITE_TYPE,
                        InfusedStoneBlock.TYPE_DEFAULT,
                        InfusedStoneBlock.TYPE_SMOOTH,
                        InfusedStoneBlock.TYPE_BRICK,
                        0
                },
                new boolean[] {
                        true, true, true, true,
                        false, false, false, false
                },
                new Block[] {
                        DecoBlocks.looseStoneSlab,
                        DecoBlocks.looseStoneSlab,
                        DecoBlocks.looseStoneSlab,
                        DecoBlocks.looseStoneSlab,
                        null, null, null, null
                },
                new int[] {
                        StoneHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_TYPE,
                        StoneHelper.LOOSE_GRANITE_BRICK_SLAB_TYPE,
                        StoneHelper.LOOSE_ANDESITE_BRICK_SLAB_TYPE,
                        StoneHelper.LOOSE_DIORITE_BRICK_SLAB_TYPE,
                        0, 0, 0, 0
                })
                .setUnlocalizedName("decoBlockStoneSlab3");
        
        DecoBlocks.stoneSlab6 = new TerracottaBlock.StoneSlabBlock(DecoBlockIDs.STONE_SLAB_6_ID,
                new Block[] {
                        Block.stoneBrick,
                        Block.stoneBrick,
                        DecoBlocks.endStoneBrick,
                        DecoBlocks.terracotta,
                        DecoBlocks.slate,
                        DecoBlocks.polishedStoneVariants,
                        DecoBlocks.cobblestoneVariants,
                        DecoBlocks.stoneBrickVariants
                },
                new int [] {
                        1, // Mossy
                        2, // Cracked
                        0, 0, 0,
                        StoneVariantsBlock.SLATE_TYPE,
                        StoneVariantsBlock.SLATE_TYPE,
                        StoneVariantsBlock.SLATE_TYPE
                },
                new boolean[] {
                        true, true,
                        false, false, false, false,
                        true, true
                },
                new Block[] {
                        BTWBlocks.looseStoneBrickSlab,
                        BTWBlocks.looseStoneBrickSlab,
                        null, null, null, null,
                        DecoBlocks.looseStoneSlab,
                        DecoBlocks.looseStoneSlab
                },
                new int[] {
                        0, 0, 0, 0, 0, 0,
                        StoneHelper.LOOSE_SLATE_COBBLESTONE_SLAB_TYPE,
                        StoneHelper.LOOSE_SLATE_BRICK_SLAB_TYPE
                })
                .setUnlocalizedName("decoBlockStoneSlab6");
        
        DecoBlocks.stoneSlab7 = new TerracottaBlock.StoneSlabBlock(DecoBlockIDs.STONE_SLAB_7_ID,
                new Block[] {
                        DecoBlocks.slateTiles,
                        DecoBlocks.shingles
                },
                new int [] {
                        0, 0
                },
                new boolean[] {
                        true,
                        false
                },
                new Block[] {
                        DecoBlocks.looseStoneSlab,
                        null
                },
                new int[] {
                        StoneHelper.LOOSE_SLATE_BRICK_SLAB_TYPE,
                        0
                })
                .setUnlocalizedName("decoBlockStoneSlab7");
    }
    
    private static void initSoil() {
        Item.itemsList[Block.sand.blockID] = new ItemMultiTextureTile(Block.sand.blockID - 256, Block.sand, SandHelper.sandNames);
        
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

    private static void register(Block block, String[]names) {
        Item.itemsList[block.blockID] = new ItemMultiTextureTile(block.blockID - 256, block, names);
    }
    
    private static void register(ItemBlock blockItem) {
        Item.itemsList[blockItem.itemID] = blockItem;
    }
}
