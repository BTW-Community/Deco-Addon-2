package deco.block;

import btw.AddonHandler;
import btw.block.BTWBlocks;
import btw.block.blocks.*;
import btw.block.tileentity.beacon.BeaconTileEntity;
import btw.block.util.Flammability;
import btw.item.blockitems.MouldingBlockItem;
import btw.item.blockitems.SaplingBlockItem;
import btw.item.blockitems.SidingAndCornerBlockItem;
import btw.item.blockitems.legacy.LegacySubstitutionBlockItem;
import btw.util.ColorUtils;
import btw.world.feature.trees.grower.TreeGrowers;
import deco.block.blocks.*;
import deco.block.blocks.CarvedPumpkinBlock;
import deco.block.blocks.FlowerPotBlock;
import deco.block.blocks.DoubleTallGrassBlock;
import deco.block.blocks.WorkbenchBlock;
import deco.block.blocks.legacy.*;
import deco.block.tileentity.FlowerPotTileEntity;
import deco.block.util.*;
import deco.item.DecoItemIDs;
import deco.item.itemblocks.ColoredItemBlock;
import deco.item.itemblocks.DecoLogItemBlock;
import deco.item.itemblocks.DecoSlabItemBlock;
import deco.item.itemblocks.EnchantedBookshelfItemBlock;
import deco.item.mixins.PlaceAsBlockItemAccessor;
import deco.item.util.LegacySubstitutionBlockItemInterface;
import deco.world.feature.trees.grower.DecoTreeGrowers;
import net.minecraft.src.*;

public class DecoBlockInitializer {
    public static void initDecoBlocks() {
        initMaterials();
        initGeneralBlocks();
        initStone();
        initWhiteStone();
        initPrismarine();
        initSandstone();
        initNether();
        initEnd();
        initTerracotta();
        initConcrete();
        initSoil();
        initPlants();
        initWoodTypes();
        initStoneSlabs();
    }
    
    private static void initMaterials() {
        DecoBlocks.carpetMaterial = new Material(MapColor.clothColor)
                .setBurning()
                .setAxesEfficientOn()
                .setRequiresTool();
        
        DecoBlocks.hayMaterial = new Material(MapColor.clothColor)
                .setBurning()
                .setAxesEfficientOn()
                .setMobsCantSpawnOn();
        
        DecoBlocks.ashMaterial = new Material(MapColor.stoneColor)
                .setRequiresTool()
                .setNetherMobsCanSpawnOn();
        
        DecoBlocks.hedgeMaterial = new Material(MapColor.foliageColor)
                .setBurning()
                .setTranslucent()
                .setNoPushMobility()
                .setAxesEfficientOn()
                .setAxesTreatAsVegetation()
                .setMobsCantSpawnOn();
    }
    
    private static void initGeneralBlocks() {
        register(Block.obsidian, new String[] {"obsidian", "infused"});
        
        DecoBlocks.coalBlock = new DecoBlock(DecoBlockIDs.COAL_BLOCK_ID, Material.rock)
                .setUnlocalizedName("decoBlockCoal")
                .setPicksEffectiveOn()
                .setFireProperties(Flammability.EXTREME)
                .setHardness(1.5F)
                .setResistance(10.0F)
                .setCreativeTab(CreativeTabs.tabBlock);
        DecoBlocks.netherCoalBlock = new NetherCoalBlock(DecoBlockIDs.NETHER_COAL_BLOCK_ID);
    
        DecoBlocks.diamondiumBlock = new DecoBlock(DecoBlockIDs.DIAMONDIUM_BLOCK, Material.iron)
                .setHardness(10.0F)
                .setResistance(2000.0F)
                .setStepSound(Block.soundMetalFootstep)
                .setUnlocalizedName("blockDiamond")
                .setCreativeTab(CreativeTabs.tabBlock);
        Block.blockDiamond.setUnlocalizedName("decoBlockDiamond");
        BeaconTileEntity.addBeaconEffect(DecoBlocks.diamondiumBlock.blockID, BeaconTileEntity.FORTUNE_EFFECT);
        
        DecoBlocks.carpet = new CarpetBlock(DecoBlockIDs.CARPET_ID);
        register(new ColoredItemBlock(DecoBlocks.carpet));
    
        DecoBlocks.bonePillar = new DecoPillarBlock(DecoBlockIDs.BONE_PILLAR_ID, BTWBlocks.miscMaterial,
                new String[] {"decoBlockBonePillar_top"},
                new String[] {"decoBlockBonePillar_side"})
                .setHardness(2.0F)
                .setPicksEffectiveOn()
                .setBuoyancy(1.0F)
                .setStepSound(Block.soundStoneFootstep)
                .setCreativeTab(CreativeTabs.tabBlock)
                .setUnlocalizedName("decoBlockBonePillar");
    
        DecoBlocks.ropeCoil = new DecoPillarBlock(DecoBlockIDs.ROPE_COIL_ID, BTWBlocks.miscMaterial,
                new String[] {"fcBlockRope_top"},
                new String[] {"fcBlockRope_side"})
                .setHardness(2.0F)
                .setAxesEffectiveOn()
                .setStepSound(Block.soundGrassFootstep)
                .setCreativeTab(CreativeTabs.tabBlock)
                .setUnlocalizedName("decoBlockRopeCoil");
        
        DecoBlocks.chain = new ChainBlock(DecoBlockIDs.CHAIN_ID);
        DecoBlocks.chainCoil = new DecoPillarBlock(DecoBlockIDs.CHAIN_COIL_ID, Material.iron,
                new String[] {"decoBlockChainCoil_top"},
                new String[] {"decoBlockChainCoil_side"})
                .setHardness(2.0F)
                .setPicksEffectiveOn()
                .setStepSound(Block.soundMetalFootstep)
                .setCreativeTab(CreativeTabs.tabBlock)
                .setUnlocalizedName("decoBlockChainCoil");
        
        DecoBlocks.paperWall = new PaneBlock(DecoBlockIDs.PAPER_WALL_ID, "decoBlockPaperWall", "decoBlockPaperWall_top", Material.wood, true)
                .setHardness(0.3F)
                .setResistance(1.0F)
                .setStepSound(Block.soundWoodFootstep)
                .setUnlocalizedName("decoBlockPaperWall")
                .setCreativeTab(CreativeTabs.tabDecorations);
        DecoBlocks.wroughtIronBars = new PaneBlock(DecoBlockIDs.WROUGHT_IRON_BARS_ID, "decoBlockWroughtIronBars", "decoBlockWroughtIronBars_top", Material.iron, true)
                .setHardness(2.5F)
                .setResistance(10.0F)
                .setStepSound(Block.soundMetalFootstep)
                .setUnlocalizedName("decoBlockWroughtIronBars")
                .setCreativeTab(CreativeTabs.tabDecorations);
        
        DecoBlocks.lantern = new LanternBlock(DecoBlockIDs.LANTERN_ID, Material.iron, "Iron", DecoItemIDs.LANTERN_ID + 256);
        DecoBlocks.paperLantern = new LanternBlock(DecoBlockIDs.PAPER_LANTERN_ID, Material.wood, "Paper", DecoItemIDs.PAPER_LANTERN_ID + 256);
        DecoBlocks.brokenPaperLantern = new LanternBlock(DecoBlockIDs.BROKEN_PAPER_LANTERN_ID, Material.wood, "PaperBroken", DecoItemIDs.BROKEN_PAPER_LANTERN_ID + 256)
                .setLightValue(0);
        
        DecoBlocks.chandelier = new ChandelierBlock(DecoBlockIDs.CHANDELIER_ID);
        
        DecoBlocks.hayBale = new HayBaleBlock(DecoBlockIDs.HAY_BALE_ID).setUnlocalizedName("decoBlockHayBale");
        DecoBlocks.hayStairs = new StairsBlock(DecoBlockIDs.HAY_BALE_STAIRS_ID, DecoBlocks.hayBale, 0);
        DecoBlocks.thatch = new HayBaleBlock(DecoBlockIDs.THATCH_ID).setUnlocalizedName("decoBlockThatch");
        DecoBlocks.thatchStairs = new StairsBlock(DecoBlockIDs.THATCH_STAIRS_ID, DecoBlocks.thatch, 0);
        
        DecoBlocks.stainedGlass = new StainedGlassBlock(DecoBlockIDs.STAINED_GLASS_ID, "decoBlockStainedGlass", DecoItemIDs.STAINED_GLASS_ITEM_ID);
        DecoBlocks.woodFramedGlass = new WoodFramedGlassBlock(DecoBlockIDs.WOOD_FRAMED_GLASS_ID, "decoBlockGlassFramed");
        register(DecoBlocks.woodFramedGlass, WoodTypeHelper.woodNames);
        DecoBlocks.ironFramedGlass = new DecoGlassBlock(DecoBlockIDs.IRON_FRAMED_GLASS_ID, "decoBlockGlassFramedIron");
        
        DecoBlocks.ironTrapdoor = new IronTrapDoorBlock(DecoBlockIDs.IRON_TRAPDOOR_ID);
        DecoBlocks.ironLadder = new IronLadderBlock(DecoBlockIDs.IRON_LADDER_ID);
    
        DecoBlocks.amethyst = new DecoBlock(DecoBlockIDs.AMETHYST_ID, Material.rock)
                .setHardness(0.3F)
                .setStepSound(Block.soundGlassFootstep)
                .setUnlocalizedName("decoBlockAmethyst")
                .setCreativeTab(CreativeTabs.tabDecorations);
        DecoBlocks.amethystShard = new AmethystShardBlock(DecoBlockIDs.AMETHYST_SHARD_ID);
    
        DecoBlocks.ash = new AshBlock(DecoBlockIDs.ASH_BLOCK_ID);
        DecoBlocks.pumice = new DecoBlock(DecoBlockIDs.PUMICE_ID, BTWBlocks.netherRockMaterial)
                .setHardness(0.8F)
                .setResistance(1.5F)
                .setUnlocalizedName("decoBlockPumice")
                .setCreativeTab(CreativeTabs.tabBlock);
        
        DecoBlocks.flowerPot = new FlowerPotBlock(DecoBlockIDs.FLOWER_POT_ID);
        TileEntity.addMapping(FlowerPotTileEntity.class, "AddonFlowerPot");
        ((PlaceAsBlockItemAccessor) Item.flowerPot).setBlockID(DecoBlockIDs.FLOWER_POT_ID);
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
        
        DecoBlocks.graniteButton = new DecoButtonBlock(DecoBlockIDs.GRANITE_BUTTON_ID, false, DecoBlocks.stoneVariants, StoneVariantsBlock.GRANITE_TYPE)
                .setUnlocalizedName("decoBlockButtonGranite");
        
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
    
        DecoBlocks.andesiteButton = new DecoButtonBlock(DecoBlockIDs.ANDESITE_BUTTON_ID, false, DecoBlocks.stoneVariants, StoneVariantsBlock.ANDESITE_TYPE)
                .setUnlocalizedName("decoBlockButtonAndesite");
    
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
    
        DecoBlocks.dioriteButton = new DecoButtonBlock(DecoBlockIDs.DIORITE_BUTTON_ID, false, DecoBlocks.stoneVariants, StoneVariantsBlock.DIORITE_TYPE)
                .setUnlocalizedName("decoBlockButtonDiorite");
    
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
    
        DecoBlocks.slateButton = new DecoButtonBlock(DecoBlockIDs.SLATE_BUTTON_ID, false, DecoBlocks.slate, 0)
                .setUnlocalizedName("decoBlockButtonSlate");
        
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
    
        // Calcite
    
        DecoBlocks.roughCalcite = new RoughStoneVariantBlock(DecoBlockIDs.ROUGH_CALCITE_ID, StoneVariantsBlock.CALCITE_TYPE);
    
        DecoBlocks.infestedCalcite = new SilverfishBlock(DecoBlockIDs.INFESTED_CALCITE_ID, DecoBlocks.stoneVariants, StoneVariantsBlock.CALCITE_TYPE);
    
        DecoBlocks.calciteButton = new DecoButtonBlock(DecoBlockIDs.CALCITE_BUTTON_ID, false, DecoBlocks.stoneVariants, StoneVariantsBlock.CALCITE_TYPE)
                .setUnlocalizedName("decoBlockButtonCalcite");
    
        DecoBlocks.looseCalciteCobblestone = new LooseCobblestoneVariantsBlock(DecoBlockIDs.LOOSE_CALCITE_COBBLESTONE_ID, StoneVariantsBlock.CALCITE_TYPE,
                "decoOverlayCalciteCobbleLava")
                .setUnlocalizedName("decoBlockCalciteCobbleLoose");
        DecoBlocks.looseCalciteBrick = new LooseStoneBrickVariantsBlock(DecoBlockIDs.LOOSE_CALCITE_BRICK_ID, StoneVariantsBlock.CALCITE_TYPE,
                "decoOverlayCalciteBrickLava")
                .setUnlocalizedName("decoBlockCalciteBrickLoose");
    
        DecoBlocks.looseCalciteCobblestoneStairs = new LooseStoneVariantStairsBlock(DecoBlockIDs.LOOSE_CALCITE_COBBLESTONE_STAIRS_ID,
                DecoBlocks.cobblestoneVariants, StoneVariantsBlock.CALCITE_TYPE, DecoBlockIDs.CALCITE_COBBLESTONE_STAIRS_ID)
                .setUnlocalizedName("decoBlockCalciteCobbleLooseStairs");
        DecoBlocks.looseCalciteBrickStairs = new LooseStoneVariantStairsBlock(DecoBlockIDs.LOOSE_CALCITE_BRICK_STAIRS_ID,
                DecoBlocks.stoneBrickVariants, StoneVariantsBlock.CALCITE_TYPE, DecoBlockIDs.CALCITE_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockCalciteBrickLooseStairs");
    
        DecoBlocks.calciteSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.CALCITE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockCalcite",
                DecoBlocks.stoneVariants.blockHardness, DecoBlocks.stoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockCalciteSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.calciteSidingAndCorner.blockID - 256));
        DecoBlocks.calciteMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.CALCITE_MOULDING_ID, Material.rock,
                "decoBlockCalcite", "decoBlockCalcite",
                DecoBlockIDs.CALCITE_SIDING_AND_CORNER_ID,
                DecoBlocks.stoneVariants.blockHardness, DecoBlocks.stoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockCalciteMoulding");
        register(new MouldingBlockItem(DecoBlocks.calciteMoulding.blockID - 256));
        DecoBlocks.calciteStairs = new StairsBlock(DecoBlockIDs.CALCITE_STAIRS_ID, DecoBlocks.stoneVariants, StoneVariantsBlock.CALCITE_TYPE)
                .setUnlocalizedName("decoBlockCalciteStairs");
    
        DecoBlocks.calciteCobblestoneSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.CALCITE_COBBLESTONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockCalciteCobble",
                DecoBlocks.cobblestoneVariants.blockHardness, DecoBlocks.cobblestoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockCalciteCobbleSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.calciteCobblestoneSidingAndCorner.blockID - 256));
        DecoBlocks.calciteCobblestoneMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.CALCITE_COBBLESTONE_MOULDING_ID, Material.rock,
                "decoBlockCalciteCobble", "decoBlockCalciteCobble",
                DecoBlockIDs.CALCITE_COBBLESTONE_SIDING_AND_CORNER_ID,
                DecoBlocks.cobblestoneVariants.blockHardness, DecoBlocks.cobblestoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockCalciteCobbleMoulding");
        register(new MouldingBlockItem(DecoBlocks.calciteCobblestoneMoulding.blockID - 256));
        DecoBlocks.calciteCobblestoneStairs = new MortaredStairsBlock(DecoBlockIDs.CALCITE_COBBLESTONE_STAIRS_ID, DecoBlocks.cobblestoneVariants,
                StoneVariantsBlock.CALCITE_TYPE, DecoBlockIDs.LOOSE_CALCITE_COBBLESTONE_STAIRS_ID)
                .setUnlocalizedName("decoBlockCalciteCobbleStairs");
    
        DecoBlocks.calciteBrickSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.CALCITE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockCalciteBrick",
                DecoBlocks.stoneBrickVariants.blockHardness, DecoBlocks.stoneBrickVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockCalciteBrickSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.calciteBrickSidingAndCorner.blockID - 256));
        DecoBlocks.calciteBrickMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.CALCITE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockCalciteBrick", "decoBlockCalciteBrick",
                DecoBlockIDs.CALCITE_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.stoneBrickVariants.blockHardness, DecoBlocks.stoneBrickVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockCalciteBrickMoulding");
        register(new MouldingBlockItem(DecoBlocks.calciteBrickMoulding.blockID - 256));
        DecoBlocks.calciteBrickStairs = new MortaredStairsBlock(DecoBlockIDs.CALCITE_BRICK_STAIRS_ID, DecoBlocks.stoneBrickVariants,
                StoneVariantsBlock.CALCITE_TYPE, DecoBlockIDs.LOOSE_CALCITE_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockCalciteBrickStairs");
    
        DecoBlocks.polishedCalciteSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.POLISHED_CALCITE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockCalciteSmooth",
                DecoBlocks.polishedStoneVariants.blockHardness, DecoBlocks.polishedStoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockCalciteSmoothSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedCalciteSidingAndCorner.blockID - 256));
        DecoBlocks.polishedCalciteMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.POLISHED_CALCITE_MOULDING_ID, Material.rock,
                "decoBlockCalciteSmooth", "decoBlockCalciteSmoothColumn",
                DecoBlockIDs.CALCITE_SIDING_AND_CORNER_ID,
                DecoBlocks.polishedStoneVariants.blockHardness, DecoBlocks.polishedStoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockCalciteSmoothMoulding");
        register(new MouldingBlockItem(DecoBlocks.polishedCalciteMoulding.blockID - 256));
        DecoBlocks.polishedCalciteStairs = new StairsBlock(DecoBlockIDs.POLISHED_CALCITE_STAIRS_ID, DecoBlocks.polishedStoneVariants,
                StoneVariantsBlock.CALCITE_TYPE)
                .setUnlocalizedName("decoBlockCalciteSmoothStairs");
    
        // Tuff
    
        DecoBlocks.roughTuff = new RoughStoneVariantBlock(DecoBlockIDs.ROUGH_TUFF_ID, StoneVariantsBlock.TUFF_TYPE);
    
        DecoBlocks.infestedTuff = new SilverfishBlock(DecoBlockIDs.INFESTED_TUFF_ID, DecoBlocks.stoneVariants, StoneVariantsBlock.TUFF_TYPE);
    
        DecoBlocks.tuffButton = new DecoButtonBlock(DecoBlockIDs.TUFF_BUTTON_ID, false, DecoBlocks.stoneVariants, StoneVariantsBlock.TUFF_TYPE)
                .setUnlocalizedName("decoBlockButtonTuff");
    
        DecoBlocks.looseTuffCobblestone = new LooseCobblestoneVariantsBlock(DecoBlockIDs.LOOSE_TUFF_COBBLESTONE_ID, StoneVariantsBlock.TUFF_TYPE,
                "decoOverlayTuffCobbleLava")
                .setUnlocalizedName("decoBlockTuffCobbleLoose");
        DecoBlocks.looseTuffBrick = new LooseStoneBrickVariantsBlock(DecoBlockIDs.LOOSE_TUFF_BRICK_ID, StoneVariantsBlock.TUFF_TYPE,
                "decoOverlayTuffBrickLava")
                .setUnlocalizedName("decoBlockTuffBrickLoose");
    
        DecoBlocks.looseTuffCobblestoneStairs = new LooseStoneVariantStairsBlock(DecoBlockIDs.LOOSE_TUFF_COBBLESTONE_STAIRS_ID,
                DecoBlocks.cobblestoneVariants, StoneVariantsBlock.TUFF_TYPE, DecoBlockIDs.TUFF_COBBLESTONE_STAIRS_ID)
                .setUnlocalizedName("decoBlockTuffCobbleLooseStairs");
        DecoBlocks.looseTuffBrickStairs = new LooseStoneVariantStairsBlock(DecoBlockIDs.LOOSE_TUFF_BRICK_STAIRS_ID,
                DecoBlocks.stoneBrickVariants, StoneVariantsBlock.TUFF_TYPE, DecoBlockIDs.TUFF_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockTuffBrickLooseStairs");
    
        DecoBlocks.tuffSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.TUFF_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockTuff",
                DecoBlocks.stoneVariants.blockHardness, DecoBlocks.stoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockTuffSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.tuffSidingAndCorner.blockID - 256));
        DecoBlocks.tuffMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.TUFF_MOULDING_ID, Material.rock,
                "decoBlockTuff", "decoBlockTuff",
                DecoBlockIDs.TUFF_SIDING_AND_CORNER_ID,
                DecoBlocks.stoneVariants.blockHardness, DecoBlocks.stoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockTuffMoulding");
        register(new MouldingBlockItem(DecoBlocks.tuffMoulding.blockID - 256));
        DecoBlocks.tuffStairs = new StairsBlock(DecoBlockIDs.TUFF_STAIRS_ID, DecoBlocks.stoneVariants, StoneVariantsBlock.TUFF_TYPE)
                .setUnlocalizedName("decoBlockTuffStairs");
    
        DecoBlocks.tuffCobblestoneSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.TUFF_COBBLESTONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockTuffCobble",
                DecoBlocks.cobblestoneVariants.blockHardness, DecoBlocks.cobblestoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockTuffCobbleSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.tuffCobblestoneSidingAndCorner.blockID - 256));
        DecoBlocks.tuffCobblestoneMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.TUFF_COBBLESTONE_MOULDING_ID, Material.rock,
                "decoBlockTuffCobble", "decoBlockTuffCobble",
                DecoBlockIDs.TUFF_COBBLESTONE_SIDING_AND_CORNER_ID,
                DecoBlocks.cobblestoneVariants.blockHardness, DecoBlocks.cobblestoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockTuffCobbleMoulding");
        register(new MouldingBlockItem(DecoBlocks.tuffCobblestoneMoulding.blockID - 256));
        DecoBlocks.tuffCobblestoneStairs = new MortaredStairsBlock(DecoBlockIDs.TUFF_COBBLESTONE_STAIRS_ID, DecoBlocks.cobblestoneVariants,
                StoneVariantsBlock.TUFF_TYPE, DecoBlockIDs.LOOSE_TUFF_COBBLESTONE_STAIRS_ID)
                .setUnlocalizedName("decoBlockTuffCobbleStairs");
    
        DecoBlocks.tuffBrickSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.TUFF_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockTuffBrick",
                DecoBlocks.stoneBrickVariants.blockHardness, DecoBlocks.stoneBrickVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockTuffBrickSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.tuffBrickSidingAndCorner.blockID - 256));
        DecoBlocks.tuffBrickMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.TUFF_BRICK_MOULDING_ID, Material.rock,
                "decoBlockTuffBrick", "decoBlockTuffBrick",
                DecoBlockIDs.TUFF_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.stoneBrickVariants.blockHardness, DecoBlocks.stoneBrickVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockTuffBrickMoulding");
        register(new MouldingBlockItem(DecoBlocks.tuffBrickMoulding.blockID - 256));
        DecoBlocks.tuffBrickStairs = new MortaredStairsBlock(DecoBlockIDs.TUFF_BRICK_STAIRS_ID, DecoBlocks.stoneBrickVariants,
                StoneVariantsBlock.TUFF_TYPE, DecoBlockIDs.LOOSE_TUFF_BRICK_STAIRS_ID)
                .setUnlocalizedName("decoBlockTuffBrickStairs");
    
        DecoBlocks.polishedTuffSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.POLISHED_TUFF_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockTuffSmooth",
                DecoBlocks.polishedStoneVariants.blockHardness, DecoBlocks.polishedStoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockTuffSmoothSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedTuffSidingAndCorner.blockID - 256));
        DecoBlocks.polishedTuffMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.POLISHED_TUFF_MOULDING_ID, Material.rock,
                "decoBlockTuffSmooth", "decoBlockTuffSmoothColumn",
                DecoBlockIDs.TUFF_SIDING_AND_CORNER_ID,
                DecoBlocks.polishedStoneVariants.blockHardness, DecoBlocks.polishedStoneVariants.blockResistance, Block.soundStoneFootstep,
                "decoBlockTuffSmoothMoulding");
        register(new MouldingBlockItem(DecoBlocks.polishedTuffMoulding.blockID - 256));
        DecoBlocks.polishedTuffStairs = new StairsBlock(DecoBlockIDs.POLISHED_TUFF_STAIRS_ID, DecoBlocks.polishedStoneVariants,
                StoneVariantsBlock.TUFF_TYPE)
                .setUnlocalizedName("decoBlockTuffSmoothStairs");
    
        //------ Misc Stone ------//
    
        // Polished Stone
        DecoBlocks.polishedStone = new PolishedStoneBlock(DecoBlockIDs.POLISHED_STONE);
        register(DecoBlocks.polishedStone, new String[] {"upper", "mid", "deep"});
        
        DecoBlocks.polishedStoneSidingAndCornerUpperStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.POLISHED_STONE_SIDING_AND_CORNER_UPPER_STRATA_ID, Material.rock,
                "stoneslab_top",
                DecoBlocks.polishedStone.blockHardness, DecoBlocks.polishedStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockPolishedStoneSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedStoneSidingAndCornerUpperStrata.blockID - 256));
        DecoBlocks.polishedStoneMouldingUpperStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.POLISHED_STONE_MOULDING_UPPER_STRATA_ID, Material.rock,
                "stoneslab_top", "stoneslab_top",
                DecoBlockIDs.POLISHED_STONE_SIDING_AND_CORNER_UPPER_STRATA_ID,
                DecoBlocks.polishedStone.blockHardness, DecoBlocks.polishedStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockPolishedStoneMoulding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedStoneMouldingUpperStrata.blockID - 256));
        DecoBlocks.polishedStoneStairsUpperStrata = new StairsBlock(DecoBlockIDs.POLISHED_STONE_STAIRS_UPPER_STRATA_ID, DecoBlocks.polishedStone,
                PolishedStoneBlock.UPPER_STRATA_TYPE)
                .setUnlocalizedName("decoBlockPolishedStoneStairs");
    
        DecoBlocks.polishedStoneSidingAndCornerMidStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.POLISHED_STONE_SIDING_AND_CORNER_MID_STRATA_ID, Material.rock,
                "stoneslab_top_1",
                DecoBlocks.polishedStone.blockHardness, DecoBlocks.polishedStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockPolishedStoneSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedStoneSidingAndCornerMidStrata.blockID - 256));
        DecoBlocks.polishedStoneMouldingMidStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.POLISHED_STONE_MOULDING_MID_STRATA_ID, Material.rock,
                "stoneslab_top_1", "stoneslab_top_1",
                DecoBlockIDs.POLISHED_STONE_SIDING_AND_CORNER_MID_STRATA_ID,
                DecoBlocks.polishedStone.blockHardness, DecoBlocks.polishedStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockPolishedStoneMoulding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedStoneMouldingMidStrata.blockID - 256));
        DecoBlocks.polishedStoneStairsMidStrata = new StairsBlock(DecoBlockIDs.POLISHED_STONE_STAIRS_MID_STRATA_ID, DecoBlocks.polishedStone,
                PolishedStoneBlock.MID_STRATA_TYPE)
                .setUnlocalizedName("decoBlockPolishedStoneStairs");
    
        DecoBlocks.polishedStoneSidingAndCornerDeepStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.POLISHED_STONE_SIDING_AND_CORNER_DEEP_STRATA_ID, Material.rock,
                "stoneslab_top_2",
                DecoBlocks.polishedStone.blockHardness, DecoBlocks.polishedStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockPolishedStoneSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedStoneSidingAndCornerDeepStrata.blockID - 256));
        DecoBlocks.polishedStoneMouldingDeepStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.POLISHED_STONE_MOULDING_DEEP_STRATA_ID, Material.rock,
                "stoneslab_top_2", "stoneslab_top_2",
                DecoBlockIDs.POLISHED_STONE_SIDING_AND_CORNER_DEEP_STRATA_ID,
                DecoBlocks.polishedStone.blockHardness, DecoBlocks.polishedStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockPolishedStoneMoulding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedStoneMouldingDeepStrata.blockID - 256));
        DecoBlocks.polishedStoneStairsDeepStrata = new StairsBlock(DecoBlockIDs.POLISHED_STONE_STAIRS_DEEP_STRATA_ID, DecoBlocks.polishedStone,
                PolishedStoneBlock.DEEP_STRATA_TYPE)
                .setUnlocalizedName("decoBlockPolishedStoneStairs");
        
        // Cobblestone and Stone Brick
        // Upper strata
        DecoBlocks.cobblestoneSidingAndCornerUpperStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.COBBLESTONE_SIDING_AND_CORNER_UPPER_STRATA_ID,
                Material.rock,
                "stonebrick",
                Block.cobblestone.blockHardness, Block.cobblestone.blockResistance, Block.soundStoneFootstep,
                "decoBlockCobblestoneSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.cobblestoneSidingAndCornerUpperStrata.blockID - 256));
        DecoBlocks.cobblestoneMouldingUpperStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.COBBLESTONE_MOULDING_UPPER_STRATA_ID,
                Material.rock,
                "stonebrick", "stonebrick",
                DecoBlockIDs.COBBLESTONE_SIDING_AND_CORNER_UPPER_STRATA_ID,
                Block.cobblestone.blockHardness, Block.cobblestone.blockResistance, Block.soundStoneFootstep,
                "decoBlockCobblestoneMoulding");
        register(new MouldingBlockItem(DecoBlocks.cobblestoneMouldingUpperStrata.blockID - 256));
    
        DecoBlocks.mossyCobblestoneSidingAndCornerUpperStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.MOSSY_COBBLESTONE_SIDING_AND_CORNER_UPPER_STRATA_ID,
                Material.rock,
                "stoneMoss",
                Block.cobblestoneMossy.blockHardness, Block.cobblestoneMossy.blockResistance, Block.soundStoneFootstep,
                "decoBlockMossyCobbleSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.mossyCobblestoneSidingAndCornerUpperStrata.blockID - 256));
        DecoBlocks.mossyCobblestoneMouldingUpperStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.MOSSY_COBBLESTONE_MOULDING_UPPER_STRATA_ID,
                Material.rock,
                "stoneMoss", "stoneMoss",
                DecoBlockIDs.MOSSY_COBBLESTONE_SIDING_AND_CORNER_UPPER_STRATA_ID,
                Block.cobblestoneMossy.blockHardness, Block.cobblestoneMossy.blockResistance, Block.soundStoneFootstep,
                "decoBlockMossyCobbleMoulding");
        register(new MouldingBlockItem(DecoBlocks.mossyCobblestoneMouldingUpperStrata.blockID - 256));
        DecoBlocks.mossyCobblestoneStairsUpperStrata = new StairsBlock(DecoBlockIDs.MOSSY_COBBLESTONE_STAIRS_UPPER_STRATA_ID, Block.cobblestoneMossy,
                StoneHelper.MOSSY_COBBLESTONE_UPPER_STRATA_TYPE)
                .setUnlocalizedName("decoBlockMossyCobbleStairs");
    
        DecoBlocks.mossyStoneBrickSidingAndCornerUpperStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.MOSSY_STONE_BRICK_SIDING_AND_CORNER_UPPER_STRATA_ID,
                Material.rock,
                "stoneMoss",
                Block.stoneBrick.blockHardness, Block.stoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockStoneBrickMossySiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.mossyStoneBrickSidingAndCornerUpperStrata.blockID - 256));
        DecoBlocks.mossyStoneBrickMouldingUpperStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.MOSSY_STONE_BRICK_MOULDING_UPPER_STRATA_ID,
                Material.rock,
                "stoneMoss", "stoneMoss",
                DecoBlockIDs.MOSSY_STONE_BRICK_SIDING_AND_CORNER_UPPER_STRATA_ID,
                Block.stoneBrick.blockHardness, Block.stoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockStoneBrickMossyMoulding");
        register(new MouldingBlockItem(DecoBlocks.mossyStoneBrickMouldingUpperStrata.blockID - 256));
        DecoBlocks.mossyStoneBrickStairsUpperStrata = new StairsBlock(DecoBlockIDs.MOSSY_STONE_BRICK_STAIRS_UPPER_STRATA_ID, Block.stoneBrick,
                StoneHelper.MOSSY_STONE_BRICK_UPPER_STRATA_TYPE)
                .setUnlocalizedName("decoBlockStoneBrickMossyStairs");
    
        DecoBlocks.crackedStoneBrickSidingAndCornerUpperStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.CRACKED_STONE_BRICK_SIDING_AND_CORNER_UPPER_STRATA_ID,
                Material.rock,
                "stoneMoss",
                Block.stoneBrick.blockHardness, Block.stoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockStoneBrickCrackedSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.crackedStoneBrickSidingAndCornerUpperStrata.blockID - 256));
        DecoBlocks.crackedStoneBrickMouldingUpperStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.CRACKED_STONE_BRICK_MOULDING_UPPER_STRATA_ID,
                Material.rock,
                "stoneMoss", "stoneMoss",
                DecoBlockIDs.CRACKED_STONE_BRICK_SIDING_AND_CORNER_UPPER_STRATA_ID,
                Block.stoneBrick.blockHardness, Block.stoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockStoneBrickCrackedMoulding");
        register(new MouldingBlockItem(DecoBlocks.crackedStoneBrickMouldingUpperStrata.blockID - 256));
        DecoBlocks.crackedStoneBrickStairsUpperStrata = new StairsBlock(DecoBlockIDs.CRACKED_STONE_BRICK_STAIRS_UPPER_STRATA_ID, Block.stoneBrick,
                StoneHelper.CRACKED_STONE_BRICK_UPPER_STRATA_TYPE)
                .setUnlocalizedName("decoBlockStoneBrickCrackedStairs");
    
        // Mid strata
        DecoBlocks.cobblestoneSidingAndCornerMidStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.COBBLESTONE_SIDING_AND_CORNER_MID_STRATA_ID,
                Material.rock,
                "stonebrick",
                Block.cobblestone.blockHardness, Block.cobblestone.blockResistance, Block.soundStoneFootstep,
                "decoBlockCobblestoneSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.cobblestoneSidingAndCornerMidStrata.blockID - 256));
        DecoBlocks.cobblestoneMouldingMidStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.COBBLESTONE_MOULDING_MID_STRATA_ID,
                Material.rock,
                "stonebrick", "stonebrick",
                DecoBlockIDs.COBBLESTONE_SIDING_AND_CORNER_MID_STRATA_ID,
                Block.cobblestone.blockHardness, Block.cobblestone.blockResistance, Block.soundStoneFootstep,
                "decoBlockCobblestoneMoulding");
        register(new MouldingBlockItem(DecoBlocks.cobblestoneMouldingMidStrata.blockID - 256));
    
        DecoBlocks.mossyCobblestoneSidingAndCornerMidStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.MOSSY_COBBLESTONE_SIDING_AND_CORNER_MID_STRATA_ID,
                Material.rock,
                "stoneMoss",
                Block.cobblestoneMossy.blockHardness, Block.cobblestoneMossy.blockResistance, Block.soundStoneFootstep,
                "decoBlockMossyCobbleSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.mossyCobblestoneSidingAndCornerMidStrata.blockID - 256));
        DecoBlocks.mossyCobblestoneMouldingMidStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.MOSSY_COBBLESTONE_MOULDING_MID_STRATA_ID,
                Material.rock,
                "stoneMoss", "stoneMoss",
                DecoBlockIDs.MOSSY_COBBLESTONE_SIDING_AND_CORNER_MID_STRATA_ID,
                Block.cobblestoneMossy.blockHardness, Block.cobblestoneMossy.blockResistance, Block.soundStoneFootstep,
                "decoBlockMossyCobbleMoulding");
        register(new MouldingBlockItem(DecoBlocks.mossyCobblestoneMouldingMidStrata.blockID - 256));
        DecoBlocks.mossyCobblestoneStairsMidStrata = new StairsBlock(DecoBlockIDs.MOSSY_COBBLESTONE_STAIRS_MID_STRATA_ID, Block.cobblestoneMossy,
                StoneHelper.MOSSY_COBBLESTONE_MID_STRATA_TYPE)
                .setUnlocalizedName("decoBlockMossyCobbleStairs");
    
        DecoBlocks.mossyStoneBrickSidingAndCornerMidStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.MOSSY_STONE_BRICK_SIDING_AND_CORNER_MID_STRATA_ID,
                Material.rock,
                "stoneMoss",
                Block.stoneBrick.blockHardness, Block.stoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockStoneBrickMossySiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.mossyStoneBrickSidingAndCornerMidStrata.blockID - 256));
        DecoBlocks.mossyStoneBrickMouldingMidStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.MOSSY_STONE_BRICK_MOULDING_MID_STRATA_ID,
                Material.rock,
                "stoneMoss", "stoneMoss",
                DecoBlockIDs.MOSSY_STONE_BRICK_SIDING_AND_CORNER_MID_STRATA_ID,
                Block.stoneBrick.blockHardness, Block.stoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockStoneBrickMossyMoulding");
        register(new MouldingBlockItem(DecoBlocks.mossyStoneBrickMouldingMidStrata.blockID - 256));
        DecoBlocks.mossyStoneBrickStairsMidStrata = new StairsBlock(DecoBlockIDs.MOSSY_STONE_BRICK_STAIRS_MID_STRATA_ID, Block.stoneBrick,
                StoneHelper.MOSSY_STONE_BRICK_MID_STRATA_TYPE)
                .setUnlocalizedName("decoBlockStoneBrickMossyStairs");
    
        DecoBlocks.crackedStoneBrickSidingAndCornerMidStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.CRACKED_STONE_BRICK_SIDING_AND_CORNER_MID_STRATA_ID,
                Material.rock,
                "stoneMoss",
                Block.stoneBrick.blockHardness, Block.stoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockStoneBrickCrackedSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.crackedStoneBrickSidingAndCornerMidStrata.blockID - 256));
        DecoBlocks.crackedStoneBrickMouldingMidStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.CRACKED_STONE_BRICK_MOULDING_MID_STRATA_ID,
                Material.rock,
                "stoneMoss", "stoneMoss",
                DecoBlockIDs.CRACKED_STONE_BRICK_SIDING_AND_CORNER_MID_STRATA_ID,
                Block.stoneBrick.blockHardness, Block.stoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockStoneBrickCrackedMoulding");
        register(new MouldingBlockItem(DecoBlocks.crackedStoneBrickMouldingMidStrata.blockID - 256));
        DecoBlocks.crackedStoneBrickStairsMidStrata = new StairsBlock(DecoBlockIDs.CRACKED_STONE_BRICK_STAIRS_MID_STRATA_ID, Block.stoneBrick,
                StoneHelper.CRACKED_STONE_BRICK_MID_STRATA_TYPE)
                .setUnlocalizedName("decoBlockStoneBrickCrackedStairs");
    
        // Deep strata
        DecoBlocks.cobblestoneSidingAndCornerDeepStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.COBBLESTONE_SIDING_AND_CORNER_DEEP_STRATA_ID,
                Material.rock,
                "stonebrick",
                Block.cobblestone.blockHardness, Block.cobblestone.blockResistance, Block.soundStoneFootstep,
                "decoBlockCobblestoneSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.cobblestoneSidingAndCornerDeepStrata.blockID - 256));
        DecoBlocks.cobblestoneMouldingDeepStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.COBBLESTONE_MOULDING_DEEP_STRATA_ID,
                Material.rock,
                "stonebrick", "stonebrick",
                DecoBlockIDs.COBBLESTONE_SIDING_AND_CORNER_DEEP_STRATA_ID,
                Block.cobblestone.blockHardness, Block.cobblestone.blockResistance, Block.soundStoneFootstep,
                "decoBlockCobblestoneMoulding");
        register(new MouldingBlockItem(DecoBlocks.cobblestoneMouldingDeepStrata.blockID - 256));
    
        DecoBlocks.mossyCobblestoneSidingAndCornerDeepStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.MOSSY_COBBLESTONE_SIDING_AND_CORNER_DEEP_STRATA_ID,
                Material.rock,
                "stoneMoss",
                Block.cobblestoneMossy.blockHardness, Block.cobblestoneMossy.blockResistance, Block.soundStoneFootstep,
                "decoBlockMossyCobbleSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.mossyCobblestoneSidingAndCornerDeepStrata.blockID - 256));
        DecoBlocks.mossyCobblestoneMouldingDeepStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.MOSSY_COBBLESTONE_MOULDING_DEEP_STRATA_ID,
                Material.rock,
                "stoneMoss", "stoneMoss",
                DecoBlockIDs.MOSSY_COBBLESTONE_SIDING_AND_CORNER_DEEP_STRATA_ID,
                Block.cobblestoneMossy.blockHardness, Block.cobblestoneMossy.blockResistance, Block.soundStoneFootstep,
                "decoBlockMossyCobbleMoulding");
        register(new MouldingBlockItem(DecoBlocks.mossyCobblestoneMouldingDeepStrata.blockID - 256));
        DecoBlocks.mossyCobblestoneStairsDeepStrata = new StairsBlock(DecoBlockIDs.MOSSY_COBBLESTONE_STAIRS_DEEP_STRATA_ID, Block.cobblestoneMossy,
                StoneHelper.MOSSY_COBBLESTONE_DEEP_STRATA_TYPE)
                .setUnlocalizedName("decoBlockMossyCobbleStairs");
    
        DecoBlocks.mossyStoneBrickSidingAndCornerDeepStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.MOSSY_STONE_BRICK_SIDING_AND_CORNER_DEEP_STRATA_ID,
                Material.rock,
                "stoneMoss",
                Block.stoneBrick.blockHardness, Block.stoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockStoneBrickMossySiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.mossyStoneBrickSidingAndCornerDeepStrata.blockID - 256));
        DecoBlocks.mossyStoneBrickMouldingDeepStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.MOSSY_STONE_BRICK_MOULDING_DEEP_STRATA_ID,
                Material.rock,
                "stoneMoss", "stoneMoss",
                DecoBlockIDs.MOSSY_STONE_BRICK_SIDING_AND_CORNER_DEEP_STRATA_ID,
                Block.stoneBrick.blockHardness, Block.stoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockStoneBrickMossyMoulding");
        register(new MouldingBlockItem(DecoBlocks.mossyStoneBrickMouldingDeepStrata.blockID - 256));
        DecoBlocks.mossyStoneBrickStairsDeepStrata = new StairsBlock(DecoBlockIDs.MOSSY_STONE_BRICK_STAIRS_DEEP_STRATA_ID, Block.stoneBrick,
                StoneHelper.MOSSY_STONE_BRICK_DEEP_STRATA_TYPE)
                .setUnlocalizedName("decoBlockStoneBrickMossyStairs");
    
        DecoBlocks.crackedStoneBrickSidingAndCornerDeepStrata = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.CRACKED_STONE_BRICK_SIDING_AND_CORNER_DEEP_STRATA_ID,
                Material.rock,
                "stoneMoss",
                Block.stoneBrick.blockHardness, Block.stoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockStoneBrickCrackedSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.crackedStoneBrickSidingAndCornerDeepStrata.blockID - 256));
        DecoBlocks.crackedStoneBrickMouldingDeepStrata = new MouldingAndDecorativeWallBlock(DecoBlockIDs.CRACKED_STONE_BRICK_MOULDING_DEEP_STRATA_ID,
                Material.rock,
                "stoneMoss", "stoneMoss",
                DecoBlockIDs.CRACKED_STONE_BRICK_SIDING_AND_CORNER_DEEP_STRATA_ID,
                Block.stoneBrick.blockHardness, Block.stoneBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockStoneBrickCrackedMoulding");
        register(new MouldingBlockItem(DecoBlocks.crackedStoneBrickMouldingDeepStrata.blockID - 256));
        DecoBlocks.crackedStoneBrickStairsDeepStrata = new StairsBlock(DecoBlockIDs.CRACKED_STONE_BRICK_STAIRS_DEEP_STRATA_ID, Block.stoneBrick,
                StoneHelper.CRACKED_STONE_BRICK_DEEP_STRATA_TYPE)
                .setUnlocalizedName("decoBlockStoneBrickCrackedStairs");
    
        // Replace references as otherwise they would get messed up
        RoughStoneBlock.strataLevelBlockArray = new RoughStoneBlock[] {
                (RoughStoneBlock) BTWBlocks.upperStrataRoughStone,
                (RoughStoneBlock) BTWBlocks.midStrataRoughStone,
                (RoughStoneBlock) BTWBlocks.deepStrataRoughStone
        };
    }
    
    private static void initWhiteStone() {
        DecoBlocks.whiteStoneBricks = new WhiteStoneBrickBlock(DecoBlockIDs.WHITE_STONE_BRICKS_ID);
        register(DecoBlocks.whiteStoneBricks, new String[] {"default", "mossy", "cracked", "chiseled"});
        
        DecoBlocks.whiteStoneBrickSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.WHITE_STONE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockWhiteBricks",
                DecoBlocks.whiteStoneBricks.blockHardness, DecoBlocks.whiteStoneBricks.blockResistance, Block.soundStoneFootstep,
                "decoBlockWhiteBricksSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.whiteStoneBrickSidingAndCorner.blockID - 256));
        DecoBlocks.whiteStoneBrickMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.WHITE_STONE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockWhiteBricks", "decoBlockWhiteBricks",
                DecoBlockIDs.WHITE_STONE_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.whiteStoneBricks.blockHardness, DecoBlocks.whiteStoneBricks.blockResistance, Block.soundStoneFootstep,
                "decoBlockWhiteBricksMoulding");
        register(new MouldingBlockItem(DecoBlocks.whiteStoneBrickMoulding.blockID - 256));
        DecoBlocks.whiteStoneBrickStairs = new StairsBlock(DecoBlockIDs.WHITE_STONE_BRICK_STAIRS_ID, DecoBlocks.whiteStoneBricks, WhiteStoneBrickBlock.TYPE_DEFAULT)
                .setUnlocalizedName("decoBlockWhiteBricksStairs");
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
                "decoBlockPrismarineDark",
                DecoBlocks.prismarine.blockHardness, DecoBlocks.prismarine.blockResistance, Block.soundStoneFootstep,
                "decoBlockPrismarineDarkSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.darkPrismarineSidingAndCorner.blockID - 256));
        DecoBlocks.darkPrismarineMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.DARK_PRISMARINE_MOULDING_ID, Material.rock,
                "decoBlockPrismarineDark", "decoBlockPrismarineDarkColumn",
                DecoBlockIDs.DARK_PRISMARINE_SIDING_AND_CORNER_ID,
                DecoBlocks.prismarine.blockHardness, DecoBlocks.prismarine.blockResistance, Block.soundStoneFootstep,
                "decoBlockPrismarineDarkMoulding");
        register(new MouldingBlockItem(DecoBlocks.darkPrismarineMoulding.blockID - 256));
        DecoBlocks.darkPrismarineStairs = new StairsBlock(DecoBlockIDs.DARK_PRISMARINE_STAIRS_ID, DecoBlocks.prismarine, PrismarineBlock.DARK_TYPE)
                .setUnlocalizedName("decoBlockPrismarineDarkStairs");
    }
    
    private static void initSandstone() {
        
        //------ Sandstone ------//
        
        register(Block.sandStone, SandHelper.sandstoneNames);
    
        DecoBlocks.sandstoneButton = new DecoButtonBlock(DecoBlockIDs.SANDSTONE_BUTTON_ID, false, Block.sandStone, SandHelper.SANDSTONE_TYPE_DEFAULT)
                .setUnlocalizedName("decoBlockButtonSandstone");
        
        DecoBlocks.cutSandstoneSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.CUT_SANDSTONE_SIDING_AND_CORNER_ID, Material.rock,
                "sandstone_top", "sandstone_smooth", "sandstone_bottom",
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneSmoothSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.cutSandstoneSiding.blockID - 256));
        DecoBlocks.cutSandstoneMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.CUT_SANDSTONE_MOULDING_ID, Material.rock,
                "sandstone_top", "sandstone_smooth", "sandstone_bottom", "decoBlockSandstoneSmoothColumn",
                DecoBlockIDs.CUT_SANDSTONE_SIDING_AND_CORNER_ID,
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneSmoothMoulding");
        register(new MouldingBlockItem(DecoBlocks.cutSandstoneMoulding.blockID - 256));
        DecoBlocks.cutSandstoneStairs = new StairsBlock(DecoBlockIDs.CUT_SANDSTONE_STAIRS_ID, Block.sandStone, SandHelper.SANDSTONE_TYPE_CUT)
                .setUnlocalizedName("decoBlockSandStoneSmoothStairs");
    
        DecoBlocks.polishedSandstoneSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.POLISHED_SANDSTONE_SIDING_AND_CORNER_ID, Material.rock,
                "sandstone_top", "sandstone_top", "sandstone_top",
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstonePolishedSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedSandstoneSiding.blockID - 256));
        DecoBlocks.polishedSandstoneMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.POLISHED_SANDSTONE_MOULDING_ID, Material.rock,
                "sandstone_top", "sandstone_top", "sandstone_top", "sandstone_top",
                DecoBlockIDs.POLISHED_SANDSTONE_SIDING_AND_CORNER_ID,
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstonePolishedMoulding");
        register(new MouldingBlockItem(DecoBlocks.polishedSandstoneMoulding.blockID - 256));
        DecoBlocks.polishedSandstoneStairs = new StairsBlock(DecoBlockIDs.POLISHED_SANDSTONE_STAIRS_ID, Block.sandStone, SandHelper.SANDSTONE_TYPE_POLISHED)
                .setUnlocalizedName("decoBlockSandStonePolishedStairs");
    
        DecoBlocks.sandstoneBrickSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.SANDSTONE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockSandstoneBrick", "decoBlockSandstoneBrick", "decoBlockSandstoneBrick",
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneBrickSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.sandstoneBrickSiding.blockID - 256));
        DecoBlocks.sandstoneBrickMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.SANDSTONE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockSandstoneBrick", "decoBlockSandstoneBrick", "decoBlockSandstoneBrick", "decoBlockSandstoneBrick",
                DecoBlockIDs.SANDSTONE_BRICK_SIDING_AND_CORNER_ID,
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneBrickMoulding");
        register(new MouldingBlockItem(DecoBlocks.sandstoneBrickMoulding.blockID - 256));
        DecoBlocks.sandstoneBrickStairs = new StairsBlock(DecoBlockIDs.SANDSTONE_BRICK_STAIRS_ID, Block.sandStone, SandHelper.SANDSTONE_TYPE_BRICK)
                .setUnlocalizedName("decoBlockSandStoneBrickStairs");
    
        DecoBlocks.mossySandstoneSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.MOSSY_SANDSTONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockSandstoneMossy_top", "decoBlockSandstoneMossy_side", "decoBlockSandstoneMossy_bottom",
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneMossySiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.mossySandstoneSiding.blockID - 256));
        DecoBlocks.mossySandstoneMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.MOSSY_SANDSTONE_MOULDING_ID, Material.rock,
                "decoBlockSandstoneMossy_top", "decoBlockSandstoneMossy_side", "decoBlockSandstoneMossy_bottom", "decoBlockSandstoneMossy_side",
                DecoBlockIDs.MOSSY_SANDSTONE_SIDING_AND_CORNER_ID,
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneMossyMoulding");
        register(new MouldingBlockItem(DecoBlocks.mossySandstoneMoulding.blockID - 256));
        DecoBlocks.mossySandstoneStairs = new StairsBlock(DecoBlockIDs.MOSSY_SANDSTONE_STAIRS_ID, Block.sandStone, SandHelper.SANDSTONE_TYPE_MOSSY)
                .setUnlocalizedName("decoBlockSandStoneMossyStairs");
    
        DecoBlocks.largeSandstoneBrickSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.LARGE_SANDSTONE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLarge",
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneBrickLargeSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.largeSandstoneBrickSiding.blockID - 256));
        DecoBlocks.largeSandstoneBrickMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.LARGE_SANDSTONE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLarge",
                DecoBlockIDs.LARGE_SANDSTONE_BRICK_SIDING_AND_CORNER_ID,
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneBrickLargeMoulding");
        register(new MouldingBlockItem(DecoBlocks.largeSandstoneBrickMoulding.blockID - 256));
        DecoBlocks.largeSandstoneBrickStairs = new StairsBlock(DecoBlockIDs.LARGE_SANDSTONE_BRICK_STAIRS_ID, Block.sandStone, SandHelper.SANDSTONE_TYPE_LARGE_BRICK)
                .setUnlocalizedName("decoBlockSandStoneBrickLargeStairs");
    
        DecoBlocks.mossyLargeSandstoneBrickSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.MOSSY_LARGE_SANDSTONE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossy",
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneBrickLargeMossySiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.mossyLargeSandstoneBrickSiding.blockID - 256));
        DecoBlocks.mossyLargeSandstoneBrickMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.MOSSY_LARGE_SANDSTONE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossy", "decoBlockSandstoneBrickLargeMossy",
                DecoBlockIDs.MOSSY_LARGE_SANDSTONE_BRICK_MOULDING_ID,
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneBrickLargeMossyMoulding");
        register(new MouldingBlockItem(DecoBlocks.mossyLargeSandstoneBrickMoulding.blockID - 256));
        DecoBlocks.mossyLargeSandstoneBrickStairs = new StairsBlock(DecoBlockIDs.MOSSY_LARGE_SANDSTONE_BRICK_STAIRS_ID, Block.sandStone, SandHelper.SANDSTONE_TYPE_LARGE_BRICK_MOSSY)
                .setUnlocalizedName("decoBlockSandStoneBrickLargeMossyStairs");
    
        DecoBlocks.crackedSandstoneSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.CRACKED_SANDSTONE_SIDING_AND_CORNER_ID, Material.rock,
                "sandstone_bottom", "sandstone_bottom", "sandstone_bottom",
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneCrackedSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.crackedSandstoneSiding.blockID - 256));
        DecoBlocks.crackedSandstoneMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.CRACKED_SANDSTONE_MOULDING_ID, Material.rock,
                "sandstone_bottom", "sandstone_bottom", "sandstone_bottom", "sandstone_bottom",
                DecoBlockIDs.CRACKED_SANDSTONE_SIDING_AND_CORNER_ID,
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneCrackedMoulding");
        register(new MouldingBlockItem(DecoBlocks.crackedSandstoneMoulding.blockID - 256));
        DecoBlocks.crackedSandstoneStairs = new StairsBlock(DecoBlockIDs.CRACKED_SANDSTONE_STAIRS_ID, Block.sandStone, SandHelper.SANDSTONE_TYPE_CRACKED)
                .setUnlocalizedName("decoBlockSandStoneCrackedStairs");
    
        DecoBlocks.crackedLargeSandstoneBrickSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.CRACKED_LARGE_SANDSTONE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCracked",
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneBrickLargeCrackedSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.crackedLargeSandstoneBrickSiding.blockID - 256));
        DecoBlocks.crackedLargeSandstoneBrickMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.CRACKED_LARGE_SANDSTONE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCracked", "decoBlockSandstoneBrickLargeCracked",
                DecoBlockIDs.CRACKED_LARGE_SANDSTONE_BRICK_MOULDING_ID,
                Block.sandStone.blockHardness, Block.sandStone.blockResistance, Block.soundStoneFootstep,
                "decoBlockSandstoneBrickLargeCrackedMoulding");
        register(new MouldingBlockItem(DecoBlocks.crackedLargeSandstoneBrickMoulding.blockID - 256));
        DecoBlocks.crackedLargeSandstoneBrickStairs = new StairsBlock(DecoBlockIDs.CRACKED_LARGE_SANDSTONE_BRICK_STAIRS_ID, Block.sandStone, SandHelper.SANDSTONE_TYPE_LARGE_BRICK_CRACKED)
                .setUnlocalizedName("decoBlockSandStoneBrickLargeCrackedStairs");
        
        //------ Red Sandstone ------//
        
        DecoBlocks.redSandstone = new RedSandstoneBlock(DecoBlockIDs.RED_SANDSTONE_ID);
        register(DecoBlocks.redSandstone, SandHelper.sandstoneNames);
    
        DecoBlocks.redSandstoneButton = new DecoButtonBlock(DecoBlockIDs.RED_SANDSTONE_BUTTON_ID, false, DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_DEFAULT)
                .setUnlocalizedName("decoBlockButtonRedSandstone");
    
        DecoBlocks.redSandstoneSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.RED_SANDSTONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockRedSandstone_top", "decoBlockRedSandstone_side", "decoBlockRedSandstone_bottom",
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.redSandstoneSiding.blockID - 256));
        DecoBlocks.redSandstoneMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.RED_SANDSTONE_MOULDING_ID, Material.rock,
                "decoBlockRedSandstone_top", "decoBlockRedSandstone_side", "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_side",
                DecoBlockIDs.RED_SANDSTONE_SIDING_AND_CORNER_ID,
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneMoulding");
        register(new MouldingBlockItem(DecoBlocks.redSandstoneMoulding.blockID - 256));
        DecoBlocks.redSandstoneStairs = new StairsBlock(DecoBlockIDs.RED_SANDSTONE_STAIRS_ID, DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_DEFAULT)
                .setUnlocalizedName("decoBlockRedSandStoneStairs");
    
        DecoBlocks.cutRedSandstoneSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.CUT_RED_SANDSTONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockRedSandstone_top", "decoBlockRedSandstoneSmooth", "decoBlockRedSandstone_top",
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneSmoothSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.cutRedSandstoneSiding.blockID - 256));
        DecoBlocks.cutRedSandstoneMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.CUT_RED_SANDSTONE_MOULDING_ID, Material.rock,
                "decoBlockRedSandstone_top", "decoBlockRedSandstoneSmooth", "decoBlockRedSandstone_top", "decoBlockRedSandstoneSmoothColumn",
                DecoBlockIDs.CUT_RED_SANDSTONE_SIDING_AND_CORNER_ID,
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneSmoothMoulding");
        register(new MouldingBlockItem(DecoBlocks.cutRedSandstoneMoulding.blockID - 256));
        DecoBlocks.cutRedSandstoneStairs = new StairsBlock(DecoBlockIDs.CUT_RED_SANDSTONE_STAIRS_ID, DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_CUT)
                .setUnlocalizedName("decoBlockRedSandStoneSmoothStairs");
    
        DecoBlocks.polishedRedSandstoneSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.POLISHED_RED_SANDSTONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstone_top",
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstonePolishedSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.polishedRedSandstoneSiding.blockID - 256));
        DecoBlocks.polishedRedSandstoneMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.POLISHED_RED_SANDSTONE_MOULDING_ID, Material.rock,
                "decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstone_top", "decoBlockRedSandstone_top",
                DecoBlockIDs.POLISHED_RED_SANDSTONE_SIDING_AND_CORNER_ID,
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstonePolishedMoulding");
        register(new MouldingBlockItem(DecoBlocks.polishedRedSandstoneMoulding.blockID - 256));
        DecoBlocks.polishedRedSandstoneStairs = new StairsBlock(DecoBlockIDs.POLISHED_RED_SANDSTONE_STAIRS_ID, DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_POLISHED)
                .setUnlocalizedName("decoBlockRedSandStonePolishedStairs");
    
        DecoBlocks.redSandstoneBrickSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.RED_SANDSTONE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrick",
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneBrickSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.redSandstoneBrickSiding.blockID - 256));
        DecoBlocks.redSandstoneBrickMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.RED_SANDSTONE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneBrick",
                DecoBlockIDs.RED_SANDSTONE_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneBrickMoulding");
        register(new MouldingBlockItem(DecoBlocks.redSandstoneBrickMoulding.blockID - 256));
        DecoBlocks.redSandstoneBrickStairs = new StairsBlock(DecoBlockIDs.RED_SANDSTONE_BRICK_STAIRS_ID, DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_BRICK)
                .setUnlocalizedName("decoBlockRedSandStoneBrickStairs");
    
        DecoBlocks.mossyRedSandstoneSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.MOSSY_RED_SANDSTONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockRedSandstoneMossy_top", "decoBlockRedSandstoneMossy_side", "decoBlockRedSandstoneMossy_bottom",
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneMossySiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.mossyRedSandstoneSiding.blockID - 256));
        DecoBlocks.mossyRedSandstoneMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.MOSSY_RED_SANDSTONE_MOULDING_ID, Material.rock,
                "decoBlockRedSandstoneMossy_top", "decoBlockRedSandstoneMossy_side", "decoBlockRedSandstoneMossy_bottom", "decoBlockRedSandstoneMossy_side",
                DecoBlockIDs.MOSSY_RED_SANDSTONE_SIDING_AND_CORNER_ID,
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneMossyMoulding");
        register(new MouldingBlockItem(DecoBlocks.mossyRedSandstoneMoulding.blockID - 256));
        DecoBlocks.mossyRedSandstoneStairs = new StairsBlock(DecoBlockIDs.MOSSY_RED_SANDSTONE_STAIRS_ID, DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_MOSSY)
                .setUnlocalizedName("decoBlockRedSandStoneMossyStairs");
    
        DecoBlocks.largeRedSandstoneBrickSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.LARGE_RED_SANDSTONE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLarge",
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneBrickLargeSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.largeRedSandstoneBrickSiding.blockID - 256));
        DecoBlocks.largeRedSandstoneBrickMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.LARGE_RED_SANDSTONE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLarge",
                DecoBlockIDs.LARGE_RED_SANDSTONE_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneBrickLargeMoulding");
        register(new MouldingBlockItem(DecoBlocks.largeRedSandstoneBrickMoulding.blockID - 256));
        DecoBlocks.largeRedSandstoneBrickStairs = new StairsBlock(DecoBlockIDs.LARGE_RED_SANDSTONE_BRICK_STAIRS_ID, DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_LARGE_BRICK)
                .setUnlocalizedName("decoBlockRedSandStoneBrickLargeStairs");
    
        DecoBlocks.mossyLargeRedSandstoneBrickSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.MOSSY_LARGE_RED_SANDSTONE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossy",
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneBrickLargeMossySiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.mossyLargeRedSandstoneBrickSiding.blockID - 256));
        DecoBlocks.mossyLargeRedSandstoneBrickMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.MOSSY_LARGE_RED_SANDSTONE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstoneBrickLargeMossy",
                DecoBlockIDs.MOSSY_LARGE_RED_SANDSTONE_BRICK_MOULDING_ID,
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneBrickLargeMossyMoulding");
        register(new MouldingBlockItem(DecoBlocks.mossyLargeRedSandstoneBrickMoulding.blockID - 256));
        DecoBlocks.mossyLargeRedSandstoneBrickStairs = new StairsBlock(DecoBlockIDs.MOSSY_LARGE_RED_SANDSTONE_BRICK_STAIRS_ID, DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_LARGE_BRICK_MOSSY)
                .setUnlocalizedName("decoBlockRedSandStoneBrickLargeMossyStairs");
    
        DecoBlocks.crackedRedSandstoneSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.CRACKED_RED_SANDSTONE_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom",
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneCrackedSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.crackedRedSandstoneSiding.blockID - 256));
        DecoBlocks.crackedRedSandstoneMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.CRACKED_RED_SANDSTONE_MOULDING_ID, Material.rock,
                "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom", "decoBlockRedSandstone_bottom",
                DecoBlockIDs.CRACKED_RED_SANDSTONE_SIDING_AND_CORNER_ID,
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneCrackedMoulding");
        register(new MouldingBlockItem(DecoBlocks.crackedRedSandstoneMoulding.blockID - 256));
        DecoBlocks.crackedRedSandstoneStairs = new StairsBlock(DecoBlockIDs.CRACKED_RED_SANDSTONE_STAIRS_ID, DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_CRACKED)
                .setUnlocalizedName("decoBlockRedSandStoneCrackedStairs");
    
        DecoBlocks.crackedLargeRedSandstoneBrickSiding = new SidingCornerWallTopAndBottomBlock(DecoBlockIDs.CRACKED_LARGE_RED_SANDSTONE_BRICK_SIDING_AND_CORNER_ID, Material.rock,
                "decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCracked",
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneBrickLargeCrackedSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.crackedLargeRedSandstoneBrickSiding.blockID - 256));
        DecoBlocks.crackedLargeRedSandstoneBrickMoulding = new MouldingWallTopAndBottomBlock(DecoBlockIDs.CRACKED_LARGE_RED_SANDSTONE_BRICK_MOULDING_ID, Material.rock,
                "decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCracked", "decoBlockRedSandstoneBrickLargeCracked",
                DecoBlockIDs.CRACKED_LARGE_RED_SANDSTONE_BRICK_MOULDING_ID,
                DecoBlocks.redSandstone.blockHardness, DecoBlocks.redSandstone.blockResistance, Block.soundStoneFootstep,
                "decoBlockRedSandstoneBrickLargeCrackedMoulding");
        register(new MouldingBlockItem(DecoBlocks.crackedLargeRedSandstoneBrickMoulding.blockID - 256));
        DecoBlocks.crackedLargeRedSandstoneBrickStairs = new StairsBlock(DecoBlockIDs.CRACKED_LARGE_RED_SANDSTONE_BRICK_STAIRS_ID, DecoBlocks.redSandstone, SandHelper.SANDSTONE_TYPE_LARGE_BRICK_CRACKED)
                .setUnlocalizedName("decoBlockRedSandStoneBrickLargeCrackedStairs");
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
                "decoBlockNetherBrickRed",
                DecoBlocks.netherBrick.blockHardness, DecoBlocks.netherBrick.blockResistance, Block.soundStoneFootstep,
                "decoBlockNetherBrickRedSiding");
        register(new SidingAndCornerBlockItem(DecoBlocks.redNetherBrickSidingAndCorner.blockID - 256));
        DecoBlocks.redNetherBrickMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.RED_NETHER_BRICK_MOULDING_ID,
                BTWBlocks.netherRockMaterial,
                "decoBlockNetherBrickRed", "decoBlockNetherBrickRedColumn",
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
        
        DecoBlocks.infusedStoneButton = new DecoButtonBlock(DecoBlockIDs.INFUSED_STONE_BUTTON_ID, false, DecoBlocks.infusedStone, InfusedStoneBlock.TYPE_DEFAULT)
                .setUnlocalizedName("decoBlockButtonInfusedStone");
    
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
        DecoBlocks.polishedInfusedStoneStairs = new StairsBlock(DecoBlockIDs.POLISHED_INFUSED_STONE_STAIRS_ID, DecoBlocks.infusedStone, InfusedStoneBlock.TYPE_POLISHED)
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
                        SlabHelper.GRANITE_COBBLESTONE_SLAB_ID,
                        SlabHelper.ANDESITE_COBBLESTONE_SLAB_ID,
                        SlabHelper.DIORITE_COBBLESTONE_SLAB_ID,
                        SlabHelper.GRANITE_BRICK_SLAB_ID,
                        SlabHelper.ANDESITE_BRICK_SLAB_ID,
                        SlabHelper.DIORITE_BRICK_SLAB_ID,
                        SlabHelper.SLATE_COBBLESTONE_SLAB_ID,
                        SlabHelper.SLATE_BRICK_SLAB_ID
                },
                new int[] {
                        SlabHelper.GRANITE_COBBLESTONE_SLAB_TYPE,
                        SlabHelper.ANDESITE_COBBLESTONE_SLAB_TYPE,
                        SlabHelper.DIORITE_COBBLESTONE_SLAB_TYPE,
                        SlabHelper.GRANITE_BRICK_SLAB_TYPE,
                        SlabHelper.ANDESITE_BRICK_SLAB_TYPE,
                        SlabHelper.DIORITE_BRICK_SLAB_TYPE,
                        SlabHelper.SLATE_COBBLESTONE_SLAB_TYPE,
                        SlabHelper.SLATE_BRICK_SLAB_TYPE
                })
                .setUnlocalizedName("decoBlockStoneSlabLoose");
        register(new DecoSlabItemBlock(DecoBlocks.looseStoneSlab.blockID - 256));
    
        DecoBlocks.looseStoneSlab2 = new LooseStoneSlabBlock(DecoBlockIDs.LOOSE_STONE_SLAB_2_ID,
                new Block[] {
                        DecoBlocks.looseRedNetherBrick,
                        DecoBlocks.looseCalciteCobblestone,
                        DecoBlocks.looseCalciteBrick,
                        DecoBlocks.looseTuffCobblestone,
                        DecoBlocks.looseTuffBrick
                },
                new int[] {
                        SlabHelper.RED_NETHER_BRICK_SLAB_ID,
                        SlabHelper.CALCITE_COBBLESTONE_SLAB_ID,
                        SlabHelper.CALCITE_BRICK_SLAB_ID,
                        SlabHelper.TUFF_COBBLESTONE_SLAB_ID,
                        SlabHelper.TUFF_BRICK_SLAB_ID
                },
                new int[] {
                        SlabHelper.RED_NETHER_BRICK_SLAB_TYPE,
                        SlabHelper.CALCITE_COBBLESTONE_SLAB_TYPE,
                        SlabHelper.CALCITE_BRICK_SLAB_TYPE,
                        SlabHelper.TUFF_COBBLESTONE_SLAB_TYPE,
                        SlabHelper.TUFF_BRICK_SLAB_TYPE
                })
                .setUnlocalizedName("decoBlockStoneSlabLoose2");
        register(new DecoSlabItemBlock(DecoBlocks.looseStoneSlab2.blockID - 256));
    
        DecoBlocks.stoneSlab = new StoneSlabBlock(DecoBlockIDs.STONE_SLAB_ID,
                new Block[] {
                        DecoBlocks.redSandstone,
                        DecoBlocks.prismarine,
                        DecoBlocks.prismarine,
                        DecoBlocks.prismarine,
                        BTWBlocks.aestheticOpaque,
                        DecoBlocks.whiteStoneBricks,
                        Block.cobblestoneMossy,
                        DecoBlocks.netherBrick
                },
                new int [] {
                        SandHelper.SANDSTONE_TYPE_DEFAULT,
                        PrismarineBlock.DEFAULT_TYPE,
                        PrismarineBlock.BRICK_TYPE,
                        PrismarineBlock.DARK_TYPE,
                        AestheticOpaqueBlock.SUBTYPE_WHITE_STONE,
                        0, 0,
                        DecoNetherBrickBlock.RED_TYPE
                },
                new boolean[] {
                        false, false, false, false, false, false, false,
                        true
                },
                new Block[] {
                        null, null, null, null, null, null, null,
                        DecoBlocks.looseStoneSlab2
                },
                new int[] {
                        0, 0, 0, 0, 0, 0, 0,
                        SlabHelper.LOOSE_RED_NETHER_BRICK_SLAB_TYPE,
                })
                .setUnlocalizedName("decoBlockStoneSlab");
        register(new DecoSlabItemBlock(DecoBlocks.stoneSlab.blockID - 256));
        
        DecoBlocks.stoneSlab2 = new StoneSlabBlock(DecoBlockIDs.STONE_SLAB_2_ID,
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
                        SlabHelper.LOOSE_GRANITE_COBBLESTONE_SLAB_TYPE,
                        SlabHelper.LOOSE_ANDESITE_COBBLESTONE_SLAB_TYPE,
                })
                .setUnlocalizedName("decoBlockStoneSlab2");
        register(new DecoSlabItemBlock(DecoBlocks.stoneSlab2.blockID - 256));
        
        DecoBlocks.stoneSlab3 = new StoneSlabBlock(DecoBlockIDs.STONE_SLAB_3_ID,
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
                        InfusedStoneBlock.TYPE_POLISHED,
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
                        SlabHelper.LOOSE_DIORITE_COBBLESTONE_SLAB_TYPE,
                        SlabHelper.LOOSE_GRANITE_BRICK_SLAB_TYPE,
                        SlabHelper.LOOSE_ANDESITE_BRICK_SLAB_TYPE,
                        SlabHelper.LOOSE_DIORITE_BRICK_SLAB_TYPE,
                        0, 0, 0, 0
                })
                .setUnlocalizedName("decoBlockStoneSlab3");
        register(new DecoSlabItemBlock(DecoBlocks.stoneSlab3.blockID - 256));
    
        DecoBlocks.stoneSlab4 = new StoneSlabBlock(DecoBlockIDs.STONE_SLAB_4_ID,
                new Block[] {
                        Block.sandStone,
                        Block.sandStone,
                        Block.sandStone,
                        DecoBlocks.redSandstone,
                        DecoBlocks.redSandstone,
                        DecoBlocks.redSandstone,
                        Block.sandStone,
                        Block.sandStone
                },
                new int [] {
                        SandHelper.SANDSTONE_TYPE_CUT,
                        SandHelper.SANDSTONE_TYPE_POLISHED,
                        SandHelper.SANDSTONE_TYPE_BRICK,
                        SandHelper.SANDSTONE_TYPE_CUT,
                        SandHelper.SANDSTONE_TYPE_POLISHED,
                        SandHelper.SANDSTONE_TYPE_BRICK,
                        SandHelper.SANDSTONE_TYPE_MOSSY,
                        SandHelper.SANDSTONE_TYPE_LARGE_BRICK
                })
                .setUnlocalizedName("decoBlockStoneSlab4");
        register(new DecoSlabItemBlock(DecoBlocks.stoneSlab4.blockID - 256));
    
        DecoBlocks.stoneSlab5 = new StoneSlabBlock(DecoBlockIDs.STONE_SLAB_5_ID,
                new Block[] {
                        Block.sandStone,
                        DecoBlocks.redSandstone,
                        DecoBlocks.redSandstone,
                        DecoBlocks.redSandstone,
                        Block.sandStone,
                        Block.sandStone,
                        DecoBlocks.redSandstone,
                        DecoBlocks.redSandstone
                },
                new int [] {
                        SandHelper.SANDSTONE_TYPE_LARGE_BRICK_MOSSY,
                        SandHelper.SANDSTONE_TYPE_MOSSY,
                        SandHelper.SANDSTONE_TYPE_LARGE_BRICK,
                        SandHelper.SANDSTONE_TYPE_LARGE_BRICK_MOSSY,
                        SandHelper.SANDSTONE_TYPE_CRACKED,
                        SandHelper.SANDSTONE_TYPE_LARGE_BRICK_CRACKED,
                        SandHelper.SANDSTONE_TYPE_CRACKED,
                        SandHelper.SANDSTONE_TYPE_LARGE_BRICK_CRACKED
                })
                .setUnlocalizedName("decoBlockStoneSlab5");
        register(new DecoSlabItemBlock(DecoBlocks.stoneSlab5.blockID - 256));
        
        DecoBlocks.stoneSlab6 = new StoneSlabBlock(DecoBlockIDs.STONE_SLAB_6_ID,
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
                        StoneHelper.MOSSY_STONE_BRICK_UPPER_STRATA_TYPE,
                        StoneHelper.CRACKED_STONE_BRICK_UPPER_STRATA_TYPE,
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
                        SlabHelper.LOOSE_SLATE_COBBLESTONE_SLAB_TYPE,
                        SlabHelper.LOOSE_SLATE_BRICK_SLAB_TYPE
                })
                .setUnlocalizedName("decoBlockStoneSlab6");
        register(new DecoSlabItemBlock(DecoBlocks.stoneSlab6.blockID - 256));
        
        DecoBlocks.stoneSlab7 = new StoneSlabBlock(DecoBlockIDs.STONE_SLAB_7_ID,
                new Block[] {
                        DecoBlocks.slateTiles,
                        DecoBlocks.shingles,
                        DecoBlocks.mudBrick,
                        Block.cobblestoneMossy,
                        Block.cobblestoneMossy,
                        Block.stoneBrick,
                        Block.stoneBrick,
                        Block.stoneBrick
                },
                new int [] {
                        0, 0, 0,
                        StoneHelper.MOSSY_COBBLESTONE_MID_STRATA_TYPE,
                        StoneHelper.MOSSY_COBBLESTONE_DEEP_STRATA_TYPE,
                        StoneHelper.MOSSY_STONE_BRICK_MID_STRATA_TYPE,
                        StoneHelper.MOSSY_STONE_BRICK_DEEP_STRATA_TYPE,
                        StoneHelper.CRACKED_STONE_BRICK_MID_STRATA_TYPE
                },
                new boolean[] {
                        true,
                        false, false, false, false,
                        true, true, true
                },
                new Block[] {
                        DecoBlocks.looseStoneSlab,
                        null, null, null, null,
                        BTWBlocks.looseStoneBrickSlab,
                        BTWBlocks.looseStoneBrickSlab,
                        BTWBlocks.looseStoneBrickSlab
                },
                new int[] {
                        SlabHelper.LOOSE_SLATE_BRICK_SLAB_TYPE,
                        0, 0, 0, 0,
                        StoneHelper.STONE_BRICK_MID_STRATA_TYPE,
                        StoneHelper.STONE_BRICK_DEEP_STRATA_TYPE,
                        StoneHelper.STONE_BRICK_MID_STRATA_TYPE
                })
                .setUnlocalizedName("decoBlockStoneSlab7");
        register(new DecoSlabItemBlock(DecoBlocks.stoneSlab7.blockID - 256));
    
        DecoBlocks.stoneSlab8 = new StoneSlabBlock(DecoBlockIDs.STONE_SLAB_8_ID,
                new Block[] {
                        Block.stoneBrick,
                        Block.stone,
                        Block.stone,
                        DecoBlocks.stoneVariants,
                        DecoBlocks.polishedStoneVariants,
                        DecoBlocks.cobblestoneVariants,
                        DecoBlocks.stoneBrickVariants,
                        DecoBlocks.stoneVariants
                },
                new int [] {
                        StoneHelper.CRACKED_STONE_BRICK_DEEP_STRATA_TYPE,
                        1, // Mid strata
                        2, // Deep strata
                        StoneVariantsBlock.CALCITE_TYPE,
                        StoneVariantsBlock.CALCITE_TYPE,
                        StoneVariantsBlock.CALCITE_TYPE,
                        StoneVariantsBlock.CALCITE_TYPE,
                        StoneVariantsBlock.TUFF_TYPE
                },
                new boolean[] {
                        true,
                        false, false, false, false,
                        true, true,
                        false
                },
                new Block[] {
                        BTWBlocks.looseStoneBrickSlab,
                        null, null, null, null,
                        DecoBlocks.looseStoneSlab2,
                        DecoBlocks.looseStoneSlab2,
                        null
                },
                new int[] {
                        StoneHelper.STONE_BRICK_DEEP_STRATA_TYPE,
                        0, 0, 0, 0,
                        SlabHelper.LOOSE_CALCITE_COBBLESTONE_SLAB_TYPE,
                        SlabHelper.LOOSE_CALCITE_BRICK_SLAB_TYPE,
                        0
                })
                .setUnlocalizedName("decoBlockStoneSlab8");
        register(new DecoSlabItemBlock(DecoBlocks.stoneSlab8.blockID - 256));
    
        DecoBlocks.stoneSlab9 = new StoneSlabBlock(DecoBlockIDs.STONE_SLAB_9_ID,
                new Block[] {
                        DecoBlocks.polishedStoneVariants,
                        DecoBlocks.cobblestoneVariants,
                        DecoBlocks.stoneBrickVariants,
                },
                new int [] {
                        StoneVariantsBlock.TUFF_TYPE,
                        StoneVariantsBlock.TUFF_TYPE,
                        StoneVariantsBlock.TUFF_TYPE
                },
                new boolean[] {
                        false,
                        true, true
                },
                new Block[] {
                        null,
                        DecoBlocks.looseStoneSlab2,
                        DecoBlocks.looseStoneSlab2
                },
                new int[] {
                        0,
                        SlabHelper.LOOSE_TUFF_COBBLESTONE_SLAB_TYPE,
                        SlabHelper.LOOSE_TUFF_BRICK_SLAB_TYPE,
                })
                .setUnlocalizedName("decoBlockStoneSlab9");
        register(new DecoSlabItemBlock(DecoBlocks.stoneSlab9.blockID - 256));
    }
    
    private static void initSoil() {
        
        //------ Red Sands ------//
        
        Item.itemsList[Block.sand.blockID] = new ItemMultiTextureTile(Block.sand.blockID - 256, Block.sand, SandHelper.sandNames);
        
        DecoBlocks.legacyRedSand = new LegacyRedSandBlock(DecoBlockIDs.LEGACY_RED_SAND_ID);
        Item.itemsList[DecoBlocks.legacyRedSand.blockID] = new LegacySubstitutionBlockItem(DecoBlocks.legacyRedSand.blockID - 256, Block.sand.blockID);
        ((LegacySubstitutionBlockItemInterface) Item.itemsList[DecoBlocks.legacyRedSand.blockID]).setMetadataToPlace(SandHelper.RED_SAND_TYPE);
        
        DecoBlocks.redSandSlab = new RedSandSlabBlock(DecoBlockIDs.RED_SAND_SLAB_ID);
        Item.itemsList[DecoBlocks.redSandSlab.blockID] = new DecoSlabItemBlock(DecoBlocks.redSandSlab.blockID - 256);
        
        //------ Mud ------//
        
        DecoBlocks.mud = new MudBlock(DecoBlockIDs.MUD_ID);
        DecoBlocks.packedMud = new PackedMudBlock(DecoBlockIDs.PACKED_MUD_ID);
        DecoBlocks.mudBrick = new MudBrickBlock(DecoBlockIDs.MUD_BRICK_ID);
        
        DecoBlocks.mudBrickSidingAndCorner = new SidingAndCornerAndDecorativeWallBlock(DecoBlockIDs.MUD_BRICK_SIDING_AND_CORNER_ID, Material.ground,
                "decoBlockMudBrick",
                DecoBlocks.mudBrick.blockResistance, DecoBlocks.mudBrick.blockResistance, Block.soundGravelFootstep,
                "decoBlockMudBrickSiding")
                .setShovelsEffectiveOn()
                .setPicksEffectiveOn();
        register(new SidingAndCornerBlockItem(DecoBlocks.mudBrickSidingAndCorner.blockID - 256));
        DecoBlocks.mudBrickMoulding = new MouldingAndDecorativeWallBlock(DecoBlockIDs.MUD_BRICK_MOULDING_ID, Material.ground,
                "decoBlockMudBrick", "decoBlockMudBrick",
                DecoBlockIDs.MUD_BRICK_SIDING_AND_CORNER_ID,
                DecoBlocks.mudBrick.blockResistance, DecoBlocks.mudBrick.blockResistance, Block.soundGravelFootstep,
                "decoBlockMudBrickMoulding")
                .setShovelsEffectiveOn()
                .setPicksEffectiveOn();
        register(new MouldingBlockItem(DecoBlocks.mudBrickMoulding.blockID - 256));
        DecoBlocks.mudBrickStairs = new StairsBlock(DecoBlockIDs.MUD_BRICK_STAIRS_ID, DecoBlocks.mudBrick, 0)
                .setUnlocalizedName("decoBlockMudBrickStairs")
                .setShovelsEffectiveOn()
                .setPicksEffectiveOn();
        
        //------ Coarse Dirt ------//
        
        DecoBlocks.coarseDirt = new CoarseDirtBlock(DecoBlockIDs.COARSE_DIRT_ID);
        
        DecoBlocks.coarseDirtSlab = new CoarseDirtSlabBlock(DecoBlockIDs.COARSE_DIRT_SLAB_ID);
        Item.itemsList[DecoBlocks.coarseDirtSlab.blockID] = new DecoSlabItemBlock(DecoBlocks.coarseDirtSlab.blockID - 256);
        
        DecoBlocks.podzol = new PodzolBlock(DecoBlockIDs.PODZOL_ID);
    }
    
    private static void initPlants() {
        DecoBlocks.legacyAutumnSapling = new LegacyAutumnSapling(DecoBlockIDs.LEGACY_AUTUMN_SAPLING_ID);
        register(DecoBlocks.legacyAutumnSapling, LegacyAutumnSapling.saplingTypes);
        
        DecoBlocks.redAutumnSapling = new SaplingBlock(DecoBlockIDs.RED_AUTUMN_SAPLING_ID, "decoBlockSaplingAutumnRed", "decoBlockSaplingAutumnRed_0")
                .addTreeGrower(DecoTreeGrowers.RED_AUTUMN_TREE, 9)
                .addTreeGrower(DecoTreeGrowers.BIG_RED_AUTUMN_TREE, 1)
                .add2x2TreeGrower(DecoTreeGrowers.HUGE_RED_AUTUMN_TREE, 10);
        DecoBlocks.orangeAutumnSapling = new SaplingBlock(DecoBlockIDs.ORANGE_AUTUMN_SAPLING_ID, "decoBlockSaplingAutumnOrange", "decoBlockSaplingAutumnOrange_0")
                .addTreeGrower(DecoTreeGrowers.ORANGE_AUTUMN_TREE, 9)
                .addTreeGrower(DecoTreeGrowers.BIG_ORANGE_AUTUMN_TREE, 1)
                .add2x2TreeGrower(DecoTreeGrowers.HUGE_ORANGE_AUTUMN_TREE, 10);
        DecoBlocks.yellowAutumnSapling = new SaplingBlock(DecoBlockIDs.YELLOW_AUTUMN_SAPLING_ID, "decoBlockSaplingAutumnYellow", "decoBlockSaplingAutumnYellow_0")
                .addTreeGrower(DecoTreeGrowers.YELLOW_AUTUMN_TREE, 9)
                .addTreeGrower(DecoTreeGrowers.BIG_YELLOW_AUTUMN_TREE, 1)
                .add2x2TreeGrower(DecoTreeGrowers.HUGE_YELLOW_AUTUMN_TREE, 10);
        
        DecoBlocks.autumnLeaves = new DecoLeavesBlock(DecoBlockIDs.AUTUMN_LEAVES_ID,
                new int[] {
                        DecoBlockIDs.RED_AUTUMN_SAPLING_ID,
                        DecoBlockIDs.ORANGE_AUTUMN_SAPLING_ID,
                        DecoBlockIDs.YELLOW_AUTUMN_SAPLING_ID
                },
                new String[] {
                        "decoBlockLeavesAutumnRed",
                        "decoBlockLeavesAutumnOrange",
                        "decoBlockLeavesAutumnYellow"
                })
                .setUnlocalizedName("decoBlockLeavesAutumn");
        register(DecoBlocks.autumnLeaves, LegacyAutumnSapling.names);
        
        DecoBlocks.flower = new DecoFlowerBlock(DecoBlockIDs.FLOWER_ID, "decoBlockFlower",
                new String[] {
                        "yucca",
                        "hyacinth",
                        "snapdragon",
                        "pinkAlpine",
                        "chicory",
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
                },
                new boolean[] {
                        true, true, true, true, true, true, true, true, true, true, true, true,
                        false, // Peony
                        false, // Lilac
                        false, // Rosebush
                        true
                });
        Item.itemsList[DecoBlocks.flower.blockID] = new ItemMultiTextureTile(DecoBlocks.flower.blockID - 256, DecoBlocks.flower,
                ((DecoFlowerBlock) DecoBlocks.flower).getNames());
    
        DecoBlocks.flower2 = new DecoFlowerBlock(DecoBlockIDs.FLOWER_2_ID, "decoBlockFlower",
                new String[] {
                        "blackRose",
                        "lilyOfTheValley"
                });
        Item.itemsList[DecoBlocks.flower2.blockID] = new ItemMultiTextureTile(DecoBlocks.flower2.blockID - 256, DecoBlocks.flower2,
                ((DecoFlowerBlock) DecoBlocks.flower2).getNames());
    
        DecoBlocks.tulip = new DecoFlowerBlock(DecoBlockIDs.TULIP_ID, "decoBlockTulip",
                new String[] {
                        "red",
                        "pink",
                        "orange",
                        "white",
                        "blue"
                });
        Item.itemsList[DecoBlocks.tulip.blockID] = new ItemMultiTextureTile(DecoBlocks.tulip.blockID - 256, DecoBlocks.tulip,
                ((DecoFlowerBlock) DecoBlocks.tulip).getNames());
        
        DecoBlocks.tallFlower = new TallPlantBlock(DecoBlockIDs.TALL_FLOWER_ID, "decoBlockFlowerTall",
                new String[] {
                        "peony",
                        "lilac",
                        "rosebush",
                        "rhododendron",
                        "sage",
                        "fireweed",
                        "marigold",
                        "blackThistle"
                });
        register(DecoBlocks.tallFlower, ((TallPlantBlock) DecoBlocks.tallFlower).getNames());
        
        DecoBlocks.carvedPumpkin = new CarvedPumpkinBlock(DecoBlockIDs.CARVED_PUMPKIN_ID);
        register(DecoBlocks.carvedPumpkin, new String[] {"0", "1", "2"});
        DecoBlocks.jackOLantern = new JackOLanternBlock(DecoBlockIDs.JACK_O_LANTERN_ID);
        register(DecoBlocks.jackOLantern, new String[] {"0", "1", "2"});
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
                new String[] {"decoBlockBarrelBlood_top", "decoBlockBarrelDarkOak_top", "decoBlockBarrelAcacia_top", "decoBlockBarrelMahogany_top"},
                new String[] {"decoBlockBarrelBlood_side", "decoBlockBarrelDarkOak_side", "decoBlockBarrelAcacia_side", "decoBlockBarrelMahogany_side"})
                .setUnlocalizedName("decoBlockBarrel2");
        Item.itemsList[DecoBlocks.barrel2.blockID] = new ItemMultiTextureTile(DecoBlocks.barrel2.blockID - 256, DecoBlocks.barrel2, new String[] {"blood", "darkOak", "acacia", "mahogany"});
        DecoBlocks.barrel3 = new BarrelBlock(DecoBlockIDs.BARREL_3_ID,
                new String[] {"decoBlockBarrelMangrove_top", "decoBlockBarrelHazel_top", "decoBlockBarrelFir_top", "decoBlockBarrelAspen_top"},
                new String[] {"decoBlockBarrelMangrove_side", "decoBlockBarrelHazel_side", "decoBlockBarrelFir_side", "decoBlockBarrelAspen_side"})
                .setUnlocalizedName("decoBlockBarrel3");
        Item.itemsList[DecoBlocks.barrel3.blockID] = new ItemMultiTextureTile(DecoBlocks.barrel3.blockID - 256, DecoBlocks.barrel3, new String[] {"mangrove", "hazel", "fir"});
        DecoBlocks.barrel4 = new BarrelBlock(DecoBlockIDs.BARREL_4_ID,
                new String[] {"decoBlockBarrelWillow_top", "decoBlockBarrelCherry_top", "decoBlockBarrelRedwood_top"},
                new String[] {"decoBlockBarrelWillow_side", "decoBlockBarrelCherry_side", "decoBlockBarrelRedwood_side"})
                .setUnlocalizedName("decoBlockBarrel4");
        Item.itemsList[DecoBlocks.barrel4.blockID] = new ItemMultiTextureTile(DecoBlocks.barrel3.blockID - 256, DecoBlocks.barrel3, new String[] {"willow", "cherry", "redwood"});
        
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
                new int[] {
                        WoodTypeHelper.HAZEL_WOOD_TYPE,
                        WoodTypeHelper.FIR_WOOD_TYPE,
                        WoodTypeHelper.ASPEN_WOOD_TYPE,
                        WoodTypeHelper.WILLOW_WOOD_TYPE,
                        WoodTypeHelper.CHERRY_WOOD_TYPE,
                        WoodTypeHelper.REDWOOD_WOOD_TYPE
                },
                new int[] {
                        DecoBlockIDs.HAZEL_LOG_ID,
                        DecoBlockIDs.FIR_LOG_ID,
                        DecoBlockIDs.ASPEN_LOG_ID,
                        DecoBlockIDs.WILLOW_LOG_ID,
                        DecoBlockIDs.CHERRY_LOG_ID,
                        DecoBlockIDs.REDWOOD_LOG_ID
                },
                new int[] {
                        DecoBlockIDs.CHEWED_HAZEL_LOG_ID,
                        DecoBlockIDs.CHEWED_FIR_LOG_ID,
                        DecoBlockIDs.CHEWED_ASPEN_LOG_ID,
                        DecoBlockIDs.CHEWED_WILLOW_LOG_ID,
                        DecoBlockIDs.CHEWED_CHERRY_LOG_ID,
                        DecoBlockIDs.CHEWED_REDWOOD_LOG_ID
                });

        DecoBlocks.woodSingleSlab = new DecoWoodPlankSlab(DecoBlockIDs.WOOD_SINGLE_SLAB_ID,
                new int[] {
                        WoodTypeHelper.MANGROVE_WOOD_TYPE,
                        WoodTypeHelper.HAZEL_WOOD_TYPE,
                        WoodTypeHelper.FIR_WOOD_TYPE,
                        WoodTypeHelper.ASPEN_WOOD_TYPE,
                        WoodTypeHelper.WILLOW_WOOD_TYPE,
                        WoodTypeHelper.CHERRY_WOOD_TYPE,
                        WoodTypeHelper.REDWOOD_WOOD_TYPE
                },
                new String[] {
                        "mangrove",
                        "hazel",
                        "fir",
                        "aspen",
                        "willow",
                        "cherry",
                        "redwood"
                })
                .setUnlocalizedName("decoBlockVanillaWoodSlab");
        DecoBlocks.woodDoubleSlab = new DecoWoodPlankSlab(DecoBlockIDs.WOOD_DOUBLE_SLAB_ID, true, DecoBlockIDs.WOOD_SINGLE_SLAB_ID,
                new int[] {
                        WoodTypeHelper.MANGROVE_WOOD_TYPE,
                        WoodTypeHelper.HAZEL_WOOD_TYPE,
                        WoodTypeHelper.FIR_WOOD_TYPE,
                        WoodTypeHelper.ASPEN_WOOD_TYPE,
                        WoodTypeHelper.WILLOW_WOOD_TYPE,
                        WoodTypeHelper.CHERRY_WOOD_TYPE,
                        WoodTypeHelper.REDWOOD_WOOD_TYPE
                },
                new String[] {
                        "mangrove",
                        "hazel",
                        "fir",
                        "aspen",
                        "willow",
                        "cherry",
                        "redwood"
                })
                .setUnlocalizedName("decoBlockVanillaWoodSlab");
        Item.itemsList[DecoBlocks.woodSingleSlab.blockID] = new ItemSlab(DecoBlocks.woodSingleSlab.blockID - 256, (BlockHalfSlab) DecoBlocks.woodSingleSlab,
                (BlockHalfSlab) DecoBlocks.woodDoubleSlab, false);
        Item.itemsList[DecoBlocks.woodDoubleSlab.blockID] = new ItemSlab(DecoBlocks.woodDoubleSlab.blockID - 256, (BlockHalfSlab) DecoBlocks.woodSingleSlab,
                (BlockHalfSlab) DecoBlocks.woodDoubleSlab, true);
        
        DecoBlocks.workbench = new WorkbenchBlock(DecoBlockIDs.WORKBENCH_ID);
        
        DecoBlocks.hedge = new HedgeBlock(DecoBlockIDs.HEDGE_ID);
        register(DecoBlocks.hedge, WoodTypeHelper.woodNames);
        
        //------ Oak Wood ------//
        
        DecoBlocks.oakChair = new ChairBlock(DecoBlockIDs.OAK_CHAIR_ID, "Oak");
        DecoBlocks.filledOakBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_OAK_BARREL_ID, "decoBlockBarrelOak");
        Item.itemsList[DecoBlocks.filledOakBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledOakBarrel.blockID - 256, DecoBlocks.filledOakBarrel, FilledBarrelBlock.typeTags);
    
        ((SaplingBlock) BTWBlocks.oakSapling).add2x2TreeGrower(DecoTreeGrowers.HUGE_OAK_TREE, 10);
        
        // Hedge
        DecoBlocks.oakHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.OAK_HEDGE_SIDING_AND_CORNER_ID,
                "leaves", DecoBlocks.hedge, WoodTypeHelper.OAK_WOOD_TYPE,
                "decoBlockHedgeOakSiding", true);
        register(new SidingAndCornerBlockItem(DecoBlocks.oakHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.oakHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.OAK_HEDGE_MOULDING_ID,
                "leaves", "leaves",
                DecoBlockIDs.OAK_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.hedge, WoodTypeHelper.OAK_WOOD_TYPE,
                "decoBlockHedgeOakMoulding", true);
        register(new MouldingBlockItem(DecoBlocks.oakHedgeMoulding.blockID - 256));
        DecoBlocks.oakHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.OAK_HEDGE_STAIRS_ID, DecoBlocks.hedge, WoodTypeHelper.OAK_WOOD_TYPE, true)
                .setUnlocalizedName("decoBlockHedgeOakStairs");
    
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
    
        DecoBlocks.spruceButton = new DecoButtonBlock(DecoBlockIDs.SPRUCE_BUTTON_ID, true, Block.planks, WoodTypeHelper.SPRUCE_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonSpruce");
    
        ((SaplingBlock) BTWBlocks.spruceSapling).add2x2TreeGrower(DecoTreeGrowers.HUGE_SPRUCE_TREE, 10);
    
        // Hedge
        DecoBlocks.spruceHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.SPRUCE_HEDGE_SIDING_AND_CORNER_ID,
                "leaves_spruce", DecoBlocks.hedge, WoodTypeHelper.SPRUCE_WOOD_TYPE,
                "decoBlockHedgeSpruceSiding", true);
        register(new SidingAndCornerBlockItem(DecoBlocks.spruceHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.spruceHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.SPRUCE_HEDGE_MOULDING_ID,
                "leaves_spruce", "leaves_spruce",
                DecoBlockIDs.SPRUCE_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.hedge, WoodTypeHelper.SPRUCE_WOOD_TYPE,
                "decoBlockHedgeSpruceMoulding", true);
        register(new MouldingBlockItem(DecoBlocks.spruceHedgeMoulding.blockID - 256));
        DecoBlocks.spruceHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.SPRUCE_HEDGE_STAIRS_ID, DecoBlocks.hedge, WoodTypeHelper.SPRUCE_WOOD_TYPE, true)
                .setUnlocalizedName("decoBlockHedgeSpruceStairs");
    
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
    
        DecoBlocks.birchButton = new DecoButtonBlock(DecoBlockIDs.BIRCH_BUTTON_ID, true, Block.planks, WoodTypeHelper.BIRCH_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonBirch");
    
        ((SaplingBlock) BTWBlocks.birchSapling).removeTreeGrower("btw:birch");
        ((SaplingBlock) BTWBlocks.birchSapling)
                .addTreeGrower(TreeGrowers.BIRCH_TREE, 9)
                .addTreeGrower(DecoTreeGrowers.BIG_BIRCH_TREE, 1)
                .add2x2TreeGrower(DecoTreeGrowers.HUGE_BIRCH_TREE, 10);
    
        // Hedge
        DecoBlocks.birchHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.BIRCH_HEDGE_SIDING_AND_CORNER_ID,
                "leaves", DecoBlocks.hedge, WoodTypeHelper.BIRCH_WOOD_TYPE,
                "decoBlockHedgeBirchSiding", true);
        register(new SidingAndCornerBlockItem(DecoBlocks.birchHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.birchHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.BIRCH_HEDGE_MOULDING_ID,
                "leaves", "leaves",
                DecoBlockIDs.BIRCH_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.hedge, WoodTypeHelper.BIRCH_WOOD_TYPE,
                "decoBlockHedgeBirchMoulding", true);
        register(new MouldingBlockItem(DecoBlocks.birchHedgeMoulding.blockID - 256));
        DecoBlocks.birchHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.BIRCH_HEDGE_STAIRS_ID, DecoBlocks.hedge, WoodTypeHelper.BIRCH_WOOD_TYPE, true)
                .setUnlocalizedName("decoBlockHedgeBirchStairs");
    
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
    
        DecoBlocks.jungleButton = new DecoButtonBlock(DecoBlockIDs.JUNGLE_BUTTON_ID, true, Block.planks, WoodTypeHelper.JUNGLE_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonJungle");
    
        // Hedge
        DecoBlocks.jungleHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.JUNGLE_HEDGE_SIDING_AND_CORNER_ID,
                "leaves_jungle", DecoBlocks.hedge, WoodTypeHelper.JUNGLE_WOOD_TYPE,
                "decoBlockHedgeJungleSiding", true);
        register(new SidingAndCornerBlockItem(DecoBlocks.jungleHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.jungleHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.JUNGLE_HEDGE_MOULDING_ID,
                "leaves_jungle", "leaves_jungle",
                DecoBlockIDs.JUNGLE_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.hedge, WoodTypeHelper.JUNGLE_WOOD_TYPE,
                "decoBlockHedgeJungleMoulding", true);
        register(new MouldingBlockItem(DecoBlocks.jungleHedgeMoulding.blockID - 256));
        DecoBlocks.jungleHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.JUNGLE_HEDGE_STAIRS_ID, DecoBlocks.hedge, WoodTypeHelper.JUNGLE_WOOD_TYPE, true)
                .setUnlocalizedName("decoBlockHedgeJungleStairs");
    
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
    
        DecoBlocks.bloodButton = new DecoButtonBlock(DecoBlockIDs.BLOOD_BUTTON_ID, true, Block.planks, WoodTypeHelper.BLOOD_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonBlood");
    
        // Hedge
        DecoBlocks.bloodHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.BLOOD_HEDGE_SIDING_AND_CORNER_ID,
                "leaves", BTWBlocks.bloodWoodLeaves, 0,
                "decoBlockHedgeBloodSiding", true);
        register(new SidingAndCornerBlockItem(DecoBlocks.bloodHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.bloodHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.BLOOD_HEDGE_MOULDING_ID,
                "leaves", "leaves",
                DecoBlockIDs.BLOOD_HEDGE_SIDING_AND_CORNER_ID,
                BTWBlocks.bloodWoodLeaves, 0,
                "decoBlockHedgeBloodMoulding", true);
        register(new MouldingBlockItem(DecoBlocks.bloodHedgeMoulding.blockID - 256));
        DecoBlocks.bloodHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.BLOOD_HEDGE_STAIRS_ID, BTWBlocks.bloodWoodLeaves, 0, true)
                .setUnlocalizedName("decoBlockHedgeBloodStairs");
        
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
        DecoBlocks.legacyCherrySapling = new LegacyCherrySaplingBlock(DecoBlockIDs.LEGACY_CHERRY_SAPLING_ID);
        Item.itemsList[DecoBlocks.legacyCherrySapling.blockID] = new ItemMultiTextureTile(DecoBlocks.legacyCherrySapling.blockID - 256, DecoBlocks.legacyCherrySapling, LegacyCherrySaplingBlock.saplingTypes);
        DecoBlocks.cherrySapling = new SaplingBlock(DecoBlockIDs.CHERRY_SAPLING_ID, "decoBlockSaplingCherry", "decoBlockSaplingCherry_0")
                .addTreeGrower(DecoTreeGrowers.CHERRY_TREE, 9)
                .addTreeGrower(DecoTreeGrowers.BIG_CHERRY_TREE, 1)
                .add2x2TreeGrower(DecoTreeGrowers.HUGE_CHERRY_TREE, 10);
        register(new SaplingBlockItem(DecoBlocks.cherrySapling.blockID - 256, DecoBlocks.cherrySapling));

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
    
        DecoBlocks.cherryButton = new DecoButtonBlock(DecoBlockIDs.CHERRY_BUTTON_ID, true, Block.planks, WoodTypeHelper.CHERRY_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonCherry");
    
        // Hedge
        DecoBlocks.cherryHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.CHERRY_HEDGE_SIDING_AND_CORNER_ID,
                "decoBlockLeavesCherry", DecoBlocks.cherryLeaves, 0,
                "decoBlockHedgeCherrySiding", false);
        register(new SidingAndCornerBlockItem(DecoBlocks.cherryHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.cherryHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.CHERRY_HEDGE_MOULDING_ID,
                "decoBlockLeavesCherry", "decoBlockLeavesCherry",
                DecoBlockIDs.CHERRY_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.cherryLeaves, 0,
                "decoBlockHedgeCherryMoulding", false);
        register(new MouldingBlockItem(DecoBlocks.cherryHedgeMoulding.blockID - 256));
        DecoBlocks.cherryHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.CHERRY_HEDGE_STAIRS_ID, DecoBlocks.cherryLeaves, 0, false)
                .setUnlocalizedName("decoBlockHedgeCherryStairs");
    
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
        DecoBlocks.legacyAcaciaSapling = new LegacyAcaciaSaplingBlock(DecoBlockIDs.LEGACY_ACACIA_SAPLING_ID);
        Item.itemsList[DecoBlocks.legacyAcaciaSapling.blockID] = new ItemMultiTextureTile(DecoBlocks.legacyAcaciaSapling.blockID - 256, DecoBlocks.legacyAcaciaSapling, LegacyAcaciaSaplingBlock.saplingTypes);
        DecoBlocks.acaciaSapling = new SaplingBlock(DecoBlockIDs.ACACIA_SAPLING_ID, "decoBlockSaplingAcacia", "decoBlockSaplingAcacia_0")
                .addTreeGrower(DecoTreeGrowers.ACACIA_TREE, 10);
        register(new SaplingBlockItem(DecoBlocks.acaciaSapling.blockID - 256, DecoBlocks.acaciaSapling));
    
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
    
        DecoBlocks.acaciaButton = new DecoButtonBlock(DecoBlockIDs.ACACIA_BUTTON_ID, true, Block.planks, WoodTypeHelper.ACACIA_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonAcacia");
    
        // Hedge
        DecoBlocks.acaciaHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.ACACIA_HEDGE_SIDING_AND_CORNER_ID,
                "decoBlockLeavesAcacia", DecoBlocks.acaciaLeaves, 0,
                "decoBlockHedgeAcaciaSiding", false);
        register(new SidingAndCornerBlockItem(DecoBlocks.acaciaHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.acaciaHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.ACACIA_HEDGE_MOULDING_ID,
                "decoBlockLeavesAcacia", "decoBlockLeavesAcacia",
                DecoBlockIDs.ACACIA_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.acaciaLeaves, 0,
                "decoBlockHedgeAcaciaMoulding", false);
        register(new MouldingBlockItem(DecoBlocks.acaciaHedgeMoulding.blockID - 256));
        DecoBlocks.acaciaHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.ACACIA_HEDGE_STAIRS_ID, DecoBlocks.acaciaLeaves, 0, false)
                .setUnlocalizedName("decoBlockHedgeAcaciaStairs");
    
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
                .setSpawnsJungleSpiders()
                .setUnlocalizedName("decoBlockLeavesMahogany");
        DecoBlocks.legacyMahoganySapling = new LegacyMahoganySaplingBlock(DecoBlockIDs.LEGACY_MAHOGANY_SAPLING_ID);
        Item.itemsList[DecoBlocks.legacyMahoganySapling.blockID] = new ItemMultiTextureTile(DecoBlocks.legacyMahoganySapling.blockID - 256, DecoBlocks.legacyMahoganySapling, LegacyMahoganySaplingBlock.saplingTypes);
        DecoBlocks.mahoganySapling = new SaplingBlock(DecoBlockIDs.MAHOGANY_SAPLING_ID, "decoBlockSaplingMahogany", "decoBlockSaplingMahogany_0")
                .addTreeGrower(DecoTreeGrowers.MAHOGANY_TREE, 10);
        register(new SaplingBlockItem(DecoBlocks.mahoganySapling.blockID - 256, DecoBlocks.mahoganySapling));
    
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
    
        DecoBlocks.mahoganyButton = new DecoButtonBlock(DecoBlockIDs.MAHOGANY_BUTTON_ID, true, Block.planks, WoodTypeHelper.MAHOGANY_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonMahogany");
    
        // Hedge
        DecoBlocks.mahoganyHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.MAHOGANY_HEDGE_SIDING_AND_CORNER_ID,
                "decoBlockLeavesMahogany", DecoBlocks.mahoganyLeaves, 0,
                "decoBlockHedgeMahoganySiding", false);
        register(new SidingAndCornerBlockItem(DecoBlocks.mahoganyHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.mahoganyHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.MAHOGANY_HEDGE_MOULDING_ID,
                "decoBlockLeavesMahogany", "decoBlockLeavesMahogany",
                DecoBlockIDs.MAHOGANY_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.mahoganyLeaves, 0,
                "decoBlockHedgeMahoganyMoulding", false);
        register(new MouldingBlockItem(DecoBlocks.mahoganyHedgeMoulding.blockID - 256));
        DecoBlocks.mahoganyHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.MAHOGANY_HEDGE_STAIRS_ID, DecoBlocks.mahoganyLeaves, 0, false)
                .setUnlocalizedName("decoBlockHedgeMahoganyStairs");
    
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
                .setSpawnsJungleSpiders()
                .setUnlocalizedName("decoBlockLeavesMangrove");
        DecoBlocks.legacyMangroveSapling = new LegacyMangroveSaplingBlock(DecoBlockIDs.LEGACY_MANGROVE_SAPLING_ID);
        Item.itemsList[DecoBlocks.legacyMangroveSapling.blockID] = new ItemMultiTextureTile(DecoBlocks.legacyMangroveSapling.blockID - 256, DecoBlocks.legacyMangroveSapling, LegacyMangroveSaplingBlock.saplingTypes);
        DecoBlocks.mangroveSapling = new SaplingBlock(DecoBlockIDs.MANGROVE_SAPLING_ID, "decoBlockSaplingMangrove", "decoBlockSaplingMangrove_0")
                .addTreeGrower(DecoTreeGrowers.MANGROVE_TREE, 10);
        register(new SaplingBlockItem(DecoBlocks.mangroveSapling.blockID - 256, DecoBlocks.mangroveSapling));
    
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
    
        DecoBlocks.mangroveButton = new DecoButtonBlock(DecoBlockIDs.MANGROVE_BUTTON_ID, true, Block.planks, WoodTypeHelper.MANGROVE_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonMangrove");
    
        // Hedge
        DecoBlocks.mangroveHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.MANGROVE_HEDGE_SIDING_AND_CORNER_ID,
                "decoBlockLeavesMangrove", DecoBlocks.mangroveLeaves, 0,
                "decoBlockHedgeMangroveSiding", false);
        register(new SidingAndCornerBlockItem(DecoBlocks.mangroveHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.mangroveHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.MANGROVE_HEDGE_MOULDING_ID,
                "decoBlockLeavesMangrove", "decoBlockLeavesMangrove",
                DecoBlockIDs.MANGROVE_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.mangroveLeaves, 0,
                "decoBlockHedgeMangroveMoulding", false);
        register(new MouldingBlockItem(DecoBlocks.mangroveHedgeMoulding.blockID - 256));
        DecoBlocks.mangroveHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.MANGROVE_HEDGE_STAIRS_ID, DecoBlocks.mangroveLeaves, 0, false)
                .setUnlocalizedName("decoBlockHedgeMangroveStairs");
    
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
        DecoBlocks.legacyHazelSapling = new LegacyHazelSaplingBlock(DecoBlockIDs.LEGACY_HAZEL_SAPLING_ID);
        Item.itemsList[DecoBlocks.legacyHazelSapling.blockID] = new ItemMultiTextureTile(DecoBlocks.legacyHazelSapling.blockID - 256, DecoBlocks.legacyHazelSapling, LegacyHazelSaplingBlock.saplingTypes);
        DecoBlocks.hazelSapling = new SaplingBlock(DecoBlockIDs.HAZEL_SAPLING_ID, "decoBlockSaplingHazel", "decoBlockSaplingHazel_0")
                .addTreeGrower(DecoTreeGrowers.HAZEL_TREE, 9)
                .addTreeGrower(DecoTreeGrowers.BIG_HAZEL_TREE, 1)
                .add2x2TreeGrower(DecoTreeGrowers.HUGE_HAZEL_TREE, 10);
        register(new SaplingBlockItem(DecoBlocks.hazelSapling.blockID - 256, DecoBlocks.hazelSapling));
    
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
    
        DecoBlocks.hazelButton = new DecoButtonBlock(DecoBlockIDs.HAZEL_BUTTON_ID, true, Block.planks, WoodTypeHelper.HAZEL_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonHazel");
    
        // Hedge
        DecoBlocks.hazelHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.HAZEL_HEDGE_SIDING_AND_CORNER_ID,
                "decoBlockLeavesHazel", DecoBlocks.hazelLeaves, 0,
                "decoBlockHedgeHazelSiding", false);
        register(new SidingAndCornerBlockItem(DecoBlocks.hazelHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.hazelHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.HAZEL_HEDGE_MOULDING_ID,
                "decoBlockLeavesHazel", "decoBlockLeavesHazel",
                DecoBlockIDs.HAZEL_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.hazelLeaves, 0,
                "decoBlockHedgeHazelMoulding", false);
        register(new MouldingBlockItem(DecoBlocks.hazelHedgeMoulding.blockID - 256));
        DecoBlocks.hazelHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.HAZEL_HEDGE_STAIRS_ID, DecoBlocks.hazelLeaves, 0, false)
                .setUnlocalizedName("decoBlockHedgeHazelStairs");
    
        //------ Fir Wood ------//
    
        // Tree blocks
        DecoBlocks.firLog = new DecoLogBlock(DecoBlockIDs.FIR_LOG_ID, WoodTypeHelper.FIR_WOOD_TYPE, DecoBlockIDs.CHEWED_FIR_LOG_ID,
                new String[] {"decoBlockLogFir_top", "decoBlockStrippedFir_top", "decoBlockLogFir_side", "decoBlockStrippedFir_side"},
                new String[] {"decoBlockLogFir_side", "decoBlockStrippedFir_side", "decoBlockLogFir_side", "decoBlockStrippedFir_side"})
                .setUnlocalizedName("decoBlockFirLog");
        Item.itemsList[DecoBlocks.firLog.blockID] = new DecoLogItemBlock(DecoBlocks.firLog.blockID - 256, DecoBlocks.firLog);
        DecoBlocks.firStump = new DecoStumpBlock(DecoBlockIDs.FIR_STUMP_ID, DecoBlockIDs.FIR_LOG_ID, DecoBlockIDs.CHEWED_FIR_LOG_ID,
                WoodTypeHelper.FIR_WORK_STUMP_ID, WoodTypeHelper.FIR_WORK_STUMP_TYPE,
                "decoBlockTrunkFir_top", "decoBlockTrunkFir_side")
                .setUnlocalizedName("decoBlockFirStump");
        DecoBlocks.firLogSpike = new LogSpikeBlock(DecoBlockIDs.FIR_LOG_SPIKE_ID,
                "decoBlockStrippedFir_top", "decoBlockStrippedFir_side")
                .setUnlocalizedName("decoBlockChewedFir");
        DecoBlocks.chewedFirLog = new ChewedLogBlock(DecoBlockIDs.CHEWED_FIR_LOG_ID,
                "decoBlockStrippedFir_top", "decoBlockStrippedFir_side", "decoBlockTrunkFir_top",
                DecoBlocks.firLogSpike)
                .setUnlocalizedName("decoBlockSpikeFir");
    
        DecoBlocks.firLeaves = new DecoLeavesBlock(DecoBlockIDs.FIR_LEAVES_ID, DecoBlockIDs.FIR_SAPLING_ID, "decoBlockLeavesFir")
                .setUnlocalizedName("decoBlockLeavesFir");
        DecoBlocks.legacyFirSapling = new LegacyFirSaplingBlock(DecoBlockIDs.LEGACY_FIR_SAPLING_ID);
        Item.itemsList[DecoBlocks.legacyFirSapling.blockID] = new ItemMultiTextureTile(DecoBlocks.legacyFirSapling.blockID - 256, DecoBlocks.legacyFirSapling, LegacyFirSaplingBlock.saplingTypes);
        DecoBlocks.firSapling = new SaplingBlock(DecoBlockIDs.FIR_SAPLING_ID, "decoBlockSaplingFir", "decoBlockSaplingFir_0")
                .addTreeGrower(DecoTreeGrowers.FIR_TREE, 10)
                .add2x2TreeGrower(DecoTreeGrowers.HUGE_FIR_TREE, 10);
        register(new SaplingBlockItem(DecoBlocks.firSapling.blockID - 256, DecoBlocks.firSapling));
    
        // Sub blocks
        DecoBlocks.firStairs = new WoodStairsBlock(DecoBlockIDs.FIR_STAIRS_ID, Block.planks, WoodTypeHelper.FIR_WOOD_TYPE)
                .setUnlocalizedName("decoBlockFirStairs");
        DecoBlocks.firSidingAndCorner = new WoodSidingAndCornerAndDecorativeBlock(DecoBlockIDs.FIR_SIDING_AND_CORNER_ID,
                "decoBlockPlanksFir", "decoBlockFirSiding");
        DecoBlocks.firMoulding = new WoodMouldingAndDecorativeBlock(DecoBlockIDs.FIR_MOULDING_ID,
                "decoBlockPlanksFir", "decoBlockPlanksFirColumn",
                DecoBlockIDs.FIR_SIDING_AND_CORNER_ID,
                "decoBlockFirMoulding");
    
        // Decorative blocks
        DecoBlocks.firGate = new DecoGateBlock(DecoBlockIDs.FIR_GATE_ID, "decoBlockGateFir");
        DecoBlocks.firDoor = new DecoDoorBlockWood(DecoBlockIDs.FIR_DOOR_ID, DecoItemIDs.FIR_DOOR_ID, "decoBlockDoorFir_upper", "decoBlockDoorFir_lower");
        DecoBlocks.firTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.FIR_TRAPDOOR_ID, "decoBlockTrapdoorFir");
        DecoBlocks.firChair = new ChairBlock(DecoBlockIDs.FIR_CHAIR_ID, "Fir");
        DecoBlocks.filledFirBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_FIR_BARREL_ID, "decoBlockBarrelFir");
        Item.itemsList[DecoBlocks.filledFirBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledFirBarrel.blockID - 256, DecoBlocks.filledFirBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.firLadder = new DecoLadderBlock(DecoBlockIDs.FIR_LADDER_ID, DecoBlockIDs.FLAMING_FIR_LADDER_ID, "decoBlockLadderFir");
        DecoBlocks.flamingFirLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_FIR_LADDER_ID, DecoBlockIDs.FIR_LADDER_ID, "decoBlockLadderFir");
    
        DecoBlocks.firSign = new DecoSignBlock(DecoBlockIDs.FIR_SIGN_ID, WoodTypeHelper.FIR_WOOD_TYPE, true,
                "/deco/signFir.png", "decoBlockPlanksFir");
        DecoBlocks.firWallSign = new DecoSignBlock(DecoBlockIDs.FIR_WALL_SIGN_ID, WoodTypeHelper.FIR_WOOD_TYPE, false,
                "/deco/signFir.png", "decoBlockPlanksFir");
    
        DecoBlocks.firButton = new DecoButtonBlock(DecoBlockIDs.FIR_BUTTON_ID, true, Block.planks, WoodTypeHelper.FIR_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonFir");
    
        // Hedge
        DecoBlocks.firHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.FIR_HEDGE_SIDING_AND_CORNER_ID,
                "decoBlockLeavesFir", DecoBlocks.firLeaves, 0,
                "decoBlockHedgeFirSiding", false);
        register(new SidingAndCornerBlockItem(DecoBlocks.firHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.firHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.FIR_HEDGE_MOULDING_ID,
                "decoBlockLeavesFir", "decoBlockLeavesFir",
                DecoBlockIDs.FIR_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.firLeaves, 0,
                "decoBlockHedgeFirMoulding", false);
        register(new MouldingBlockItem(DecoBlocks.firHedgeMoulding.blockID - 256));
        DecoBlocks.firHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.FIR_HEDGE_STAIRS_ID, DecoBlocks.firLeaves, 0, false)
                .setUnlocalizedName("decoBlockHedgeFirStairs");
    
        //------ Aspen Wood ------//
    
        // Tree blocks
        DecoBlocks.aspenLog = new DecoLogBlock(DecoBlockIDs.ASPEN_LOG_ID, WoodTypeHelper.ASPEN_WOOD_TYPE, DecoBlockIDs.CHEWED_ASPEN_LOG_ID,
                new String[] {"decoBlockLogAspen_top", "decoBlockStrippedAspen_top", "decoBlockLogAspen_side", "decoBlockStrippedAspen_side"},
                new String[] {"decoBlockLogAspen_side", "decoBlockStrippedAspen_side", "decoBlockLogAspen_side", "decoBlockStrippedAspen_side"})
                .setUnlocalizedName("decoBlockAspenLog");
        Item.itemsList[DecoBlocks.aspenLog.blockID] = new DecoLogItemBlock(DecoBlocks.aspenLog.blockID - 256, DecoBlocks.aspenLog);
        DecoBlocks.aspenStump = new DecoStumpBlock(DecoBlockIDs.ASPEN_STUMP_ID, DecoBlockIDs.ASPEN_LOG_ID, DecoBlockIDs.CHEWED_ASPEN_LOG_ID,
                WoodTypeHelper.ASPEN_WORK_STUMP_ID, WoodTypeHelper.ASPEN_WORK_STUMP_TYPE,
                "decoBlockTrunkAspen_top", "decoBlockTrunkAspen_side")
                .setUnlocalizedName("decoBlockAspenStump");
        DecoBlocks.aspenLogSpike = new LogSpikeBlock(DecoBlockIDs.ASPEN_LOG_SPIKE_ID,
                "decoBlockStrippedAspen_top", "decoBlockStrippedAspen_side")
                .setUnlocalizedName("decoBlockChewedAspen");
        DecoBlocks.chewedAspenLog = new ChewedLogBlock(DecoBlockIDs.CHEWED_ASPEN_LOG_ID,
                "decoBlockStrippedAspen_top", "decoBlockStrippedAspen_side", "decoBlockTrunkAspen_top",
                DecoBlocks.aspenLogSpike)
                .setUnlocalizedName("decoBlockSpikeAspen");
    
        DecoBlocks.aspenLeaves = new DecoLeavesBlock(DecoBlockIDs.ASPEN_LEAVES_ID, DecoBlockIDs.ASPEN_SAPLING_ID, "decoBlockLeavesAspen")
                .setUnlocalizedName("decoBlockLeavesAspen");
        DecoBlocks.aspenSapling = new SaplingBlock(DecoBlockIDs.ASPEN_SAPLING_ID, "decoBlockSaplingAspen", "decoBlockSaplingAspen_0")
                .addTreeGrower(DecoTreeGrowers.ASPEN_TREE, 10);
        register(new SaplingBlockItem(DecoBlocks.aspenSapling.blockID - 256, DecoBlocks.aspenSapling));
    
        // Sub blocks
        DecoBlocks.aspenStairs = new WoodStairsBlock(DecoBlockIDs.ASPEN_STAIRS_ID, Block.planks, WoodTypeHelper.ASPEN_WOOD_TYPE)
                .setUnlocalizedName("decoBlockAspenStairs");
        DecoBlocks.aspenSidingAndCorner = new WoodSidingAndCornerAndDecorativeBlock(DecoBlockIDs.ASPEN_SIDING_AND_CORNER_ID,
                "decoBlockPlanksAspen", "decoBlockAspenSiding");
        DecoBlocks.aspenMoulding = new WoodMouldingAndDecorativeBlock(DecoBlockIDs.ASPEN_MOULDING_ID,
                "decoBlockPlanksAspen", "decoBlockPlanksAspenColumn",
                DecoBlockIDs.ASPEN_SIDING_AND_CORNER_ID,
                "decoBlockAspenMoulding");
    
        // Decorative blocks
        DecoBlocks.aspenGate = new DecoGateBlock(DecoBlockIDs.ASPEN_GATE_ID, "decoBlockGateAspen");
        DecoBlocks.aspenDoor = new DecoDoorBlockWood(DecoBlockIDs.ASPEN_DOOR_ID, DecoItemIDs.ASPEN_DOOR_ID, "decoBlockDoorAspen_upper", "decoBlockDoorAspen_lower");
        DecoBlocks.aspenTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.ASPEN_TRAPDOOR_ID, "decoBlockTrapdoorAspen");
        DecoBlocks.aspenChair = new ChairBlock(DecoBlockIDs.ASPEN_CHAIR_ID, "Aspen");
        DecoBlocks.filledAspenBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_ASPEN_BARREL_ID, "decoBlockBarrelAspen");
        Item.itemsList[DecoBlocks.filledAspenBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledAspenBarrel.blockID - 256, DecoBlocks.filledAspenBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.aspenLadder = new DecoLadderBlock(DecoBlockIDs.ASPEN_LADDER_ID, DecoBlockIDs.FLAMING_ASPEN_LADDER_ID, "decoBlockLadderAspen");
        DecoBlocks.flamingAspenLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_ASPEN_LADDER_ID, DecoBlockIDs.ASPEN_LADDER_ID, "decoBlockLadderAspen");
    
        DecoBlocks.aspenSign = new DecoSignBlock(DecoBlockIDs.ASPEN_SIGN_ID, WoodTypeHelper.ASPEN_WOOD_TYPE, true,
                "/deco/signAspen.png", "decoBlockPlanksAspen");
        DecoBlocks.aspenWallSign = new DecoSignBlock(DecoBlockIDs.ASPEN_WALL_SIGN_ID, WoodTypeHelper.ASPEN_WOOD_TYPE, false,
                "/deco/signAspen.png", "decoBlockPlanksAspen");
    
        DecoBlocks.aspenButton = new DecoButtonBlock(DecoBlockIDs.ASPEN_BUTTON_ID, true, Block.planks, WoodTypeHelper.ASPEN_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonAspen");
    
        // Hedge
        DecoBlocks.aspenHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.ASPEN_HEDGE_SIDING_AND_CORNER_ID,
                "decoBlockLeavesAspen", DecoBlocks.aspenLeaves, 0,
                "decoBlockHedgeAspenSiding", false);
        register(new SidingAndCornerBlockItem(DecoBlocks.aspenHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.aspenHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.ASPEN_HEDGE_MOULDING_ID,
                "decoBlockLeavesAspen", "decoBlockLeavesAspen",
                DecoBlockIDs.ASPEN_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.aspenLeaves, 0,
                "decoBlockHedgeAspenMoulding", false);
        register(new MouldingBlockItem(DecoBlocks.aspenHedgeMoulding.blockID - 256));
        DecoBlocks.aspenHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.ASPEN_HEDGE_STAIRS_ID, DecoBlocks.aspenLeaves, 0, false)
                .setUnlocalizedName("decoBlockHedgeAspenStairs");
    
        //------ Willow Wood ------//
    
        // Tree blocks
        DecoBlocks.willowLog = new DecoLogBlock(DecoBlockIDs.WILLOW_LOG_ID, WoodTypeHelper.WILLOW_WOOD_TYPE, DecoBlockIDs.CHEWED_WILLOW_LOG_ID,
                new String[] {"decoBlockLogWillow_top", "decoBlockStrippedWillow_top", "decoBlockLogWillow_side", "decoBlockStrippedWillow_side"},
                new String[] {"decoBlockLogWillow_side", "decoBlockStrippedWillow_side", "decoBlockLogWillow_side", "decoBlockStrippedWillow_side"})
                .setUnlocalizedName("decoBlockWillowLog");
        Item.itemsList[DecoBlocks.willowLog.blockID] = new DecoLogItemBlock(DecoBlocks.willowLog.blockID - 256, DecoBlocks.willowLog);
        DecoBlocks.willowStump = new DecoStumpBlock(DecoBlockIDs.WILLOW_STUMP_ID, DecoBlockIDs.WILLOW_LOG_ID, DecoBlockIDs.CHEWED_WILLOW_LOG_ID,
                WoodTypeHelper.WILLOW_WORK_STUMP_ID, WoodTypeHelper.WILLOW_WORK_STUMP_TYPE,
                "decoBlockTrunkWillow_top", "decoBlockTrunkWillow_side")
                .setUnlocalizedName("decoBlockWillowStump");
        DecoBlocks.willowLogSpike = new LogSpikeBlock(DecoBlockIDs.WILLOW_LOG_SPIKE_ID,
                "decoBlockStrippedWillow_top", "decoBlockStrippedWillow_side")
                .setUnlocalizedName("decoBlockChewedWillow");
        DecoBlocks.chewedWillowLog = new ChewedLogBlock(DecoBlockIDs.CHEWED_WILLOW_LOG_ID,
                "decoBlockStrippedWillow_top", "decoBlockStrippedWillow_side", "decoBlockTrunkWillow_top",
                DecoBlocks.willowLogSpike)
                .setUnlocalizedName("decoBlockSpikeWillow");
    
        DecoBlocks.willowLeaves = new DecoLeavesBlock(DecoBlockIDs.WILLOW_LEAVES_ID, DecoBlockIDs.WILLOW_SAPLING_ID, "decoBlockLeavesWillow")
                .setUnlocalizedName("decoBlockLeavesWillow");
        DecoBlocks.willowSapling = new SaplingBlock(DecoBlockIDs.WILLOW_SAPLING_ID, "decoBlockSaplingWillow", "decoBlockSaplingWillow_0")
                .addTreeGrower(DecoTreeGrowers.WILLOW_TREE, 10)
                .add2x2TreeGrower(DecoTreeGrowers.HUGE_WILLOW_TREE, 10);
        register(new SaplingBlockItem(DecoBlocks.willowSapling.blockID - 256, DecoBlocks.willowSapling));
    
        // Sub blocks
        DecoBlocks.willowStairs = new WoodStairsBlock(DecoBlockIDs.WILLOW_STAIRS_ID, Block.planks, WoodTypeHelper.WILLOW_WOOD_TYPE)
                .setUnlocalizedName("decoBlockWillowStairs");
        DecoBlocks.willowSidingAndCorner = new WoodSidingAndCornerAndDecorativeBlock(DecoBlockIDs.WILLOW_SIDING_AND_CORNER_ID,
                "decoBlockPlanksWillow", "decoBlockWillowSiding");
        DecoBlocks.willowMoulding = new WoodMouldingAndDecorativeBlock(DecoBlockIDs.WILLOW_MOULDING_ID,
                "decoBlockPlanksWillow", "decoBlockPlanksWillowColumn",
                DecoBlockIDs.WILLOW_SIDING_AND_CORNER_ID,
                "decoBlockWillowMoulding");
    
        // Decorative blocks
        DecoBlocks.willowGate = new DecoGateBlock(DecoBlockIDs.WILLOW_GATE_ID, "decoBlockGateWillow");
        DecoBlocks.willowDoor = new DecoDoorBlockWood(DecoBlockIDs.WILLOW_DOOR_ID, DecoItemIDs.WILLOW_DOOR_ID, "decoBlockDoorWillow_upper", "decoBlockDoorWillow_lower");
        DecoBlocks.willowTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.WILLOW_TRAPDOOR_ID, "decoBlockTrapdoorWillow");
        DecoBlocks.willowChair = new ChairBlock(DecoBlockIDs.WILLOW_CHAIR_ID, "Willow");
        DecoBlocks.filledWillowBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_WILLOW_BARREL_ID, "decoBlockBarrelWillow");
        Item.itemsList[DecoBlocks.filledWillowBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledWillowBarrel.blockID - 256, DecoBlocks.filledWillowBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.willowLadder = new DecoLadderBlock(DecoBlockIDs.WILLOW_LADDER_ID, DecoBlockIDs.FLAMING_WILLOW_LADDER_ID, "decoBlockLadderWillow");
        DecoBlocks.flamingWillowLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_WILLOW_LADDER_ID, DecoBlockIDs.WILLOW_LADDER_ID, "decoBlockLadderWillow");
    
        DecoBlocks.willowSign = new DecoSignBlock(DecoBlockIDs.WILLOW_SIGN_ID, WoodTypeHelper.WILLOW_WOOD_TYPE, true,
                "/deco/signWillow.png", "decoBlockPlanksWillow");
        DecoBlocks.willowWallSign = new DecoSignBlock(DecoBlockIDs.WILLOW_WALL_SIGN_ID, WoodTypeHelper.WILLOW_WOOD_TYPE, false,
                "/deco/signWillow.png", "decoBlockPlanksWillow");
    
        DecoBlocks.willowButton = new DecoButtonBlock(DecoBlockIDs.WILLOW_BUTTON_ID, true, Block.planks, WoodTypeHelper.WILLOW_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonWillow");
    
        // Hedge
        DecoBlocks.willowHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.WILLOW_HEDGE_SIDING_AND_CORNER_ID,
                "decoBlockLeavesWillow", DecoBlocks.willowLeaves, 0,
                "decoBlockHedgeWillowSiding", false);
        register(new SidingAndCornerBlockItem(DecoBlocks.willowHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.willowHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.WILLOW_HEDGE_MOULDING_ID,
                "decoBlockLeavesWillow", "decoBlockLeavesWillow",
                DecoBlockIDs.WILLOW_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.willowLeaves, 0,
                "decoBlockHedgeWillowMoulding", false);
        register(new MouldingBlockItem(DecoBlocks.willowHedgeMoulding.blockID - 256));
        DecoBlocks.willowHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.WILLOW_HEDGE_STAIRS_ID, DecoBlocks.willowLeaves, 0, false)
                .setUnlocalizedName("decoBlockHedgeWillowStairs");
    
        //------ DarkOak Wood ------//
    
        // Tree blocks
        DecoBlocks.darkOakLog = new DecoLogBlock(DecoBlockIDs.DARK_OAK_LOG_ID, WoodTypeHelper.DARK_OAK_WOOD_TYPE, DecoBlockIDs.CHEWED_DARK_OAK_LOG_ID,
                new String[] {"decoBlockLogDarkOak_top", "decoBlockStrippedDarkOak_top", "decoBlockLogDarkOak_side", "decoBlockStrippedDarkOak_side"},
                new String[] {"decoBlockLogDarkOak_side", "decoBlockStrippedDarkOak_side", "decoBlockLogDarkOak_side", "decoBlockStrippedDarkOak_side"})
                .setUnlocalizedName("decoBlockDarkOakLog");
        Item.itemsList[DecoBlocks.darkOakLog.blockID] = new DecoLogItemBlock(DecoBlocks.darkOakLog.blockID - 256, DecoBlocks.darkOakLog);
        DecoBlocks.darkOakStump = new DecoStumpBlock(DecoBlockIDs.DARK_OAK_STUMP_ID, DecoBlockIDs.DARK_OAK_LOG_ID, DecoBlockIDs.CHEWED_DARK_OAK_LOG_ID,
                WoodTypeHelper.DARK_OAK_WORK_STUMP_ID, WoodTypeHelper.DARK_OAK_WORK_STUMP_TYPE,
                "decoBlockTrunkDarkOak_top", "decoBlockTrunkDarkOak_side")
                .setUnlocalizedName("decoBlockDarkOakStump");
        DecoBlocks.darkOakLogSpike = new LogSpikeBlock(DecoBlockIDs.DARK_OAK_LOG_SPIKE_ID,
                "decoBlockStrippedDarkOak_top", "decoBlockStrippedDarkOak_side")
                .setUnlocalizedName("decoBlockChewedDarkOak");
        DecoBlocks.chewedDarkOakLog = new ChewedLogBlock(DecoBlockIDs.CHEWED_DARK_OAK_LOG_ID,
                "decoBlockStrippedDarkOak_top", "decoBlockStrippedDarkOak_side", "decoBlockTrunkDarkOak_top",
                DecoBlocks.darkOakLogSpike)
                .setUnlocalizedName("decoBlockSpikeDarkOak");
        
        DecoBlocks.darkOakLeaves = new DecoLeavesBlock(DecoBlockIDs.DARK_OAK_LEAVES_ID, DecoBlockIDs.REDWOOD_SAPLING_ID, "decoBlockLeavesDarkOak")
                .setUnlocalizedName("decoBlockLeavesDarkOak");
        DecoBlocks.darkOakSapling = new SaplingBlock(DecoBlockIDs.DARK_OAK_SAPLING_ID, "decoBlockSaplingDarkOak", "decoBlockSaplingDarkOak_0")
                .addTreeGrower(DecoTreeGrowers.DARK_OAK_TREE, 10)
                .add2x2TreeGrower(DecoTreeGrowers.HUGE_DARK_OAK_TREE, 10);
        register(new SaplingBlockItem(DecoBlocks.darkOakSapling.blockID - 256, DecoBlocks.darkOakSapling));
    
        // Sub blocks
        DecoBlocks.darkOakStairs = new WoodStairsBlock(DecoBlockIDs.DARK_OAK_STAIRS_ID, Block.planks, WoodTypeHelper.DARK_OAK_WOOD_TYPE)
                .setUnlocalizedName("decoBlockDarkOakStairs");
        DecoBlocks.darkOakSidingAndCorner = new WoodSidingAndCornerAndDecorativeBlock(DecoBlockIDs.DARK_OAK_SIDING_AND_CORNER_ID,
                "decoBlockPlanksDarkOak", "decoBlockDarkOakSiding");
        DecoBlocks.darkOakMoulding = new WoodMouldingAndDecorativeBlock(DecoBlockIDs.DARK_OAK_MOULDING_ID,
                "decoBlockPlanksDarkOak", "decoBlockPlanksDarkOakColumn",
                DecoBlockIDs.DARK_OAK_SIDING_AND_CORNER_ID,
                "decoBlockDarkOakMoulding");
    
        // Decorative blocks
        DecoBlocks.darkOakGate = new DecoGateBlock(DecoBlockIDs.DARK_OAK_GATE_ID, "decoBlockGateDarkOak");
        DecoBlocks.darkOakDoor = new DecoDoorBlockWood(DecoBlockIDs.DARK_OAK_DOOR_ID, DecoItemIDs.DARK_OAK_DOOR_ID, "decoBlockDoorDarkOak_upper", "decoBlockDoorDarkOak_lower");
        DecoBlocks.darkOakTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.DARK_OAK_TRAPDOOR_ID, "decoBlockTrapdoorDarkOak");
        DecoBlocks.darkOakChair = new ChairBlock(DecoBlockIDs.DARK_OAK_CHAIR_ID, "DarkOak");
        DecoBlocks.filledDarkOakBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_DARK_OAK_BARREL_ID, "decoBlockBarrelDarkOak");
        Item.itemsList[DecoBlocks.filledDarkOakBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledDarkOakBarrel.blockID - 256, DecoBlocks.filledDarkOakBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.darkOakLadder = new DecoLadderBlock(DecoBlockIDs.DARK_OAK_LADDER_ID, DecoBlockIDs.FLAMING_DARK_OAK_LADDER_ID, "decoBlockLadderDarkOak");
        DecoBlocks.flamingDarkOakLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_DARK_OAK_LADDER_ID, DecoBlockIDs.DARK_OAK_LADDER_ID, "decoBlockLadderDarkOak");
    
        DecoBlocks.darkOakSign = new DecoSignBlock(DecoBlockIDs.DARK_OAK_SIGN_ID, WoodTypeHelper.DARK_OAK_WOOD_TYPE, true,
                "/deco/signDarkOak.png", "decoBlockPlanksDarkOak");
        DecoBlocks.darkOakWallSign = new DecoSignBlock(DecoBlockIDs.DARK_OAK_WALL_SIGN_ID, WoodTypeHelper.DARK_OAK_WOOD_TYPE, false,
                "/deco/signDarkOak.png", "decoBlockPlanksDarkOak");
    
        DecoBlocks.darkOakButton = new DecoButtonBlock(DecoBlockIDs.DARK_OAK_BUTTON_ID, true, Block.planks, WoodTypeHelper.DARK_OAK_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonDarkOak");
    
        // Hedge
        DecoBlocks.darkOakHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.DARK_OAK_HEDGE_SIDING_AND_CORNER_ID,
                "decoBlockLeavesDarkOak", DecoBlocks.darkOakLeaves, 0,
                "decoBlockHedgeDarkOakSiding", false);
        register(new SidingAndCornerBlockItem(DecoBlocks.darkOakHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.darkOakHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.DARK_OAK_HEDGE_MOULDING_ID,
                "decoBlockLeavesDarkOak", "decoBlockLeavesDarkOak",
                DecoBlockIDs.DARK_OAK_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.darkOakLeaves, 0,
                "decoBlockHedgeDarkOakMoulding", false);
        register(new MouldingBlockItem(DecoBlocks.darkOakHedgeMoulding.blockID - 256));
        DecoBlocks.darkOakHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.DARK_OAK_HEDGE_STAIRS_ID, DecoBlocks.darkOakLeaves, 0, false)
                .setUnlocalizedName("decoBlockHedgeDarkOakStairs");
    
        //------ Redwood Wood ------//
    
        // Tree blocks
        DecoBlocks.redwoodLog = new DecoLogBlock(DecoBlockIDs.REDWOOD_LOG_ID, WoodTypeHelper.REDWOOD_WOOD_TYPE, DecoBlockIDs.CHEWED_REDWOOD_LOG_ID,
                new String[] {"decoBlockLogRedwood_top", "decoBlockStrippedRedwood_top", "decoBlockLogRedwood_side", "decoBlockStrippedRedwood_side"},
                new String[] {"decoBlockLogRedwood_side", "decoBlockStrippedRedwood_side", "decoBlockLogRedwood_side", "decoBlockStrippedRedwood_side"})
                .setUnlocalizedName("decoBlockRedwoodLog");
        Item.itemsList[DecoBlocks.redwoodLog.blockID] = new DecoLogItemBlock(DecoBlocks.redwoodLog.blockID - 256, DecoBlocks.redwoodLog);
        DecoBlocks.redwoodStump = new DecoStumpBlock(DecoBlockIDs.REDWOOD_STUMP_ID, DecoBlockIDs.REDWOOD_LOG_ID, DecoBlockIDs.CHEWED_REDWOOD_LOG_ID,
                WoodTypeHelper.REDWOOD_WORK_STUMP_ID, WoodTypeHelper.REDWOOD_WORK_STUMP_TYPE,
                "decoBlockTrunkRedwood_top", "decoBlockTrunkRedwood_side")
                .setUnlocalizedName("decoBlockRedwoodStump");
        DecoBlocks.redwoodLogSpike = new LogSpikeBlock(DecoBlockIDs.REDWOOD_LOG_SPIKE_ID,
                "decoBlockStrippedRedwood_top", "decoBlockStrippedRedwood_side")
                .setUnlocalizedName("decoBlockChewedRedwood");
        DecoBlocks.chewedRedwoodLog = new ChewedLogBlock(DecoBlockIDs.CHEWED_REDWOOD_LOG_ID,
                "decoBlockStrippedRedwood_top", "decoBlockStrippedRedwood_side", "decoBlockTrunkRedwood_top",
                DecoBlocks.redwoodLogSpike)
                .setUnlocalizedName("decoBlockSpikeRedwood");
    
        DecoBlocks.redwoodLeaves = new DecoLeavesBlock(DecoBlockIDs.REDWOOD_LEAVES_ID, DecoBlockIDs.REDWOOD_SAPLING_ID, "decoBlockLeavesRedwood")
                .setUnlocalizedName("decoBlockLeavesRedwood");
        DecoBlocks.redwoodSapling = new SaplingBlock(DecoBlockIDs.REDWOOD_SAPLING_ID, "decoBlockSaplingRedwood", "decoBlockSaplingRedwood_0")
                .addTreeGrower(DecoTreeGrowers.REDWOOD_TREE, 10)
                .add2x2TreeGrower(DecoTreeGrowers.HUGE_REDWOOD_TREE, 10);
        register(new SaplingBlockItem(DecoBlocks.redwoodSapling.blockID - 256, DecoBlocks.redwoodSapling));
    
        // Sub blocks
        DecoBlocks.redwoodStairs = new WoodStairsBlock(DecoBlockIDs.REDWOOD_STAIRS_ID, Block.planks, WoodTypeHelper.REDWOOD_WOOD_TYPE)
                .setUnlocalizedName("decoBlockRedwoodStairs");
        DecoBlocks.redwoodSidingAndCorner = new WoodSidingAndCornerAndDecorativeBlock(DecoBlockIDs.REDWOOD_SIDING_AND_CORNER_ID,
                "decoBlockPlanksRedwood", "decoBlockRedwoodSiding");
        DecoBlocks.redwoodMoulding = new WoodMouldingAndDecorativeBlock(DecoBlockIDs.REDWOOD_MOULDING_ID,
                "decoBlockPlanksRedwood", "decoBlockPlanksRedwoodColumn",
                DecoBlockIDs.REDWOOD_SIDING_AND_CORNER_ID,
                "decoBlockRedwoodMoulding");
    
        // Decorative blocks
        DecoBlocks.redwoodGate = new DecoGateBlock(DecoBlockIDs.REDWOOD_GATE_ID, "decoBlockGateRedwood");
        DecoBlocks.redwoodDoor = new DecoDoorBlockWood(DecoBlockIDs.REDWOOD_DOOR_ID, DecoItemIDs.REDWOOD_DOOR_ID, "decoBlockDoorRedwood_upper", "decoBlockDoorRedwood_lower");
        DecoBlocks.redwoodTrapdoor = new DecoTrapDoorBlock(DecoBlockIDs.REDWOOD_TRAPDOOR_ID, "decoBlockTrapdoorRedwood");
        DecoBlocks.redwoodChair = new ChairBlock(DecoBlockIDs.REDWOOD_CHAIR_ID, "Redwood");
        DecoBlocks.filledRedwoodBarrel = new FilledBarrelBlock(DecoBlockIDs.FILLED_REDWOOD_BARREL_ID, "decoBlockBarrelRedwood");
        Item.itemsList[DecoBlocks.filledRedwoodBarrel.blockID] = new ItemMultiTextureTile(DecoBlocks.filledRedwoodBarrel.blockID - 256, DecoBlocks.filledRedwoodBarrel, FilledBarrelBlock.typeTags);
        DecoBlocks.redwoodLadder = new DecoLadderBlock(DecoBlockIDs.REDWOOD_LADDER_ID, DecoBlockIDs.FLAMING_REDWOOD_LADDER_ID, "decoBlockLadderRedwood");
        DecoBlocks.flamingRedwoodLadder = new DecoLadderBlockFlaming(DecoBlockIDs.FLAMING_REDWOOD_LADDER_ID, DecoBlockIDs.REDWOOD_LADDER_ID, "decoBlockLadderRedwood");
    
        DecoBlocks.redwoodSign = new DecoSignBlock(DecoBlockIDs.REDWOOD_SIGN_ID, WoodTypeHelper.REDWOOD_WOOD_TYPE, true,
                "/deco/signRedwood.png", "decoBlockPlanksRedwood");
        DecoBlocks.redwoodWallSign = new DecoSignBlock(DecoBlockIDs.REDWOOD_WALL_SIGN_ID, WoodTypeHelper.REDWOOD_WOOD_TYPE, false,
                "/deco/signRedwood.png", "decoBlockPlanksRedwood");
    
        DecoBlocks.redwoodButton = new DecoButtonBlock(DecoBlockIDs.REDWOOD_BUTTON_ID, true, Block.planks, WoodTypeHelper.REDWOOD_WOOD_TYPE)
                .setUnlocalizedName("decoBlockButtonRedwood");
    
        // Hedge
        DecoBlocks.redwoodHedgeSidingAndCorner = new HedgeSidingAndCornerBlock(DecoBlockIDs.REDWOOD_HEDGE_SIDING_AND_CORNER_ID,
                "decoBlockLeavesRedwood", DecoBlocks.redwoodLeaves, 0,
                "decoBlockHedgeRedwoodSiding", false);
        register(new SidingAndCornerBlockItem(DecoBlocks.redwoodHedgeSidingAndCorner.blockID - 256));
        DecoBlocks.redwoodHedgeMoulding = new HedgeMouldingBlock(DecoBlockIDs.REDWOOD_HEDGE_MOULDING_ID,
                "decoBlockLeavesRedwood", "decoBlockLeavesRedwood",
                DecoBlockIDs.REDWOOD_HEDGE_SIDING_AND_CORNER_ID,
                DecoBlocks.redwoodLeaves, 0,
                "decoBlockHedgeRedwoodMoulding", false);
        register(new MouldingBlockItem(DecoBlocks.redwoodHedgeMoulding.blockID - 256));
        DecoBlocks.redwoodHedgeStairs = new HedgeStairsBlock(DecoBlockIDs.REDWOOD_HEDGE_STAIRS_ID, DecoBlocks.redwoodLeaves, 0, false)
                .setUnlocalizedName("decoBlockHedgeRedwoodStairs");
        
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
        
        //------ Misc ------//
    
        // This must be done last to make sure references are already created before storing them in the array
        LogBlock.chewedLogArray = new Block[] {
                BTWBlocks.oakChewedLog,
                BTWBlocks.spruceChewedLog,
                BTWBlocks.birchChewedLog,
                BTWBlocks.jungleChewedLog,
                DecoBlocks.chewedDarkOakLog,
                DecoBlocks.chewedAcaciaLog,
                DecoBlocks.chewedMahoganyLog,
                DecoBlocks.chewedMangroveLog
        };
    }
    
    public static void initAddonOverlapBlocks() {
        if (!AddonHandler.isModInstalled("Sock's Crops")) {
            DecoBlocks.tallGrass = new DoubleTallGrassBlock(DecoBlockIDs.TALL_GRASS_ID);
            register(DecoBlocks.tallGrass, ((TallPlantBlock) DecoBlocks.tallGrass).getNames());
        }
    }

    private static void register(Block block, String[]names) {
        Item.itemsList[block.blockID] = new ItemMultiTextureTile(block.blockID - 256, block, names);
    }
    
    private static void register(ItemBlock blockItem) {
        Item.itemsList[blockItem.itemID] = blockItem;
    }
}
