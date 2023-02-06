package deco.block.util;

import btw.block.BTWBlocks;
import btw.crafting.util.FurnaceBurnTime;
import btw.world.feature.trees.grower.TreeGrowers;
import deco.block.DecoBlockIDs;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;

import java.util.HashMap;
import java.util.Map;

public class WoodTypeHelper {
    public static String[] woodNames = new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry", "acacia", "mahogany", "mangrove", "hazel", "fir"};
    public static String[] woodNamesCapital = new String[] {"Oak", "Spruce", "Birch", "Jungle", "Blood", "Cherry", "Acacia", "Mahogany", "Mangrove", "Hazel", "Fir"};
    
    public static final TreeGrowers.TreeWoodType CHERRY_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlocks.cherryLog.blockID, 0,
            DecoBlocks.cherryStump.blockID, 0,
            DecoBlocks.cherryLeaves.blockID, 0);
    
    public static final TreeGrowers.TreeWoodType ACACIA_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlocks.acaciaLog.blockID, 0,
            DecoBlocks.acaciaStump.blockID, 0,
            DecoBlocks.acaciaLeaves.blockID, 0);
    
    public static final TreeGrowers.TreeWoodType MAHOGANY_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlocks.mahoganyLog.blockID, 0,
            DecoBlocks.mahoganyStump.blockID, 0,
            DecoBlocks.mahoganyLeaves.blockID, 0);
    
    public static final TreeGrowers.TreeWoodType MANGROVE_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlocks.mangroveLog.blockID, 0,
            DecoBlocks.mangroveStump.blockID, 0,
            DecoBlocks.mangroveLeaves.blockID, 0);
    
    public static final TreeGrowers.TreeWoodType HAZEL_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlocks.hazelLog.blockID, 0,
            DecoBlocks.hazelStump.blockID, 0,
            DecoBlocks.hazelLeaves.blockID, 0);
    
    public static final TreeGrowers.TreeWoodType FIR_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlocks.firLog.blockID, 0,
            DecoBlocks.firStump.blockID, 0,
            DecoBlocks.firLeaves.blockID, 0);
    
    public static final TreeGrowers.TreeWoodType RED_TREE_AUTUMN_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlocks.cherryLog.blockID, 0,
            DecoBlocks.cherryStump.blockID, 0,
            DecoBlocks.autumnLeaves.blockID, 0);
    
    public static final TreeGrowers.TreeWoodType ORANGE_TREE_AUTUMN_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            Block.wood.blockID, 0,
            Block.wood.blockID, 12,
            DecoBlocks.autumnLeaves.blockID, 1);
    
    public static final TreeGrowers.TreeWoodType YELLOW_TREE_AUTUMN_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            Block.wood.blockID, 2,
            Block.wood.blockID, 14,
            DecoBlocks.autumnLeaves.blockID, 2);
    
    public static final int NUM_VANILLA_WOOD = 5;
    public static final int NUM_EXTRA_WOOD = 6;
    public static final int NUM_TOTAL_WOOD = NUM_VANILLA_WOOD + NUM_EXTRA_WOOD;

    public static final int OAK_WOOD_TYPE = 0;
    public static final int SPRUCE_WOOD_TYPE = 1;
    public static final int BIRCH_WOOD_TYPE = 2;
    public static final int JUNGLE_WOOD_TYPE = 3;
    public static final int BLOOD_WOOD_TYPE = 4;
    public static final int CHERRY_WOOD_TYPE = 5;
    public static final int ACACIA_WOOD_TYPE = 6;
    public static final int MAHOGANY_WOOD_TYPE = 7;
    public static final int MANGROVE_WOOD_TYPE = 8;
    public static final int HAZEL_WOOD_TYPE = 9;
    public static final int FIR_WOOD_TYPE = 10;
    
    public static final int MANGROVE_SLAB_TYPE = 0;
    public static final int HAZEL_SLAB_TYPE = 1;
    public static final int FIR_SLAB_TYPE = 2;
    
    public static final int OAK_BARREL_ID = DecoBlockIDs.BARREL_ID;
    public static final int SPRUCE_BARREL_ID = DecoBlockIDs.BARREL_ID;
    public static final int BIRCH_BARREL_ID = DecoBlockIDs.BARREL_ID;
    public static final int JUNGLE_BARREL_ID = DecoBlockIDs.BARREL_ID;
    public static final int BLOOD_BARREL_ID = DecoBlockIDs.BARREL_2_ID;
    public static final int CHERRY_BARREL_ID = DecoBlockIDs.BARREL_2_ID;
    public static final int ACACIA_BARREL_ID = DecoBlockIDs.BARREL_2_ID;
    public static final int MAHOGANY_BARREL_ID = DecoBlockIDs.BARREL_2_ID;
    public static final int MANGROVE_BARREL_ID = DecoBlockIDs.BARREL_3_ID;
    public static final int HAZEL_BARREL_ID = DecoBlockIDs.BARREL_3_ID;
    public static final int FIR_BARREL_ID = DecoBlockIDs.BARREL_3_ID;
    
    public static final int OAK_BARREL_TYPE = 0;
    public static final int SPRUCE_BARREL_TYPE = 1;
    public static final int BIRCH_BARREL_TYPE = 2;
    public static final int JUNGLE_BARREL_TYPE = 3;
    public static final int BLOOD_BARREL_TYPE = 0;
    public static final int CHERRY_BARREL_TYPE = 1;
    public static final int ACACIA_BARREL_TYPE = 2;
    public static final int MAHOGANY_BARREL_TYPE = 3;
    public static final int MANGROVE_BARREL_TYPE = 0;
    public static final int HAZEL_BARREL_TYPE = 1;
    public static final int FIR_BARREL_TYPE = 2;
    
    public static final int CHERRY_WORK_STUMP_ID = BTWBlocks.workStump.blockID;
    public static final int ACACIA_WORK_STUMP_ID = BTWBlocks.workStump.blockID;
    public static final int MAHOGANY_WORK_STUMP_ID = BTWBlocks.workStump.blockID;
    public static final int MANGROVE_WORK_STUMP_ID = BTWBlocks.workStump.blockID;
    public static final int HAZEL_WORK_STUMP_ID = DecoBlockIDs.WORK_STUMP_ID;
    public static final int FIR_WORK_STUMP_ID = DecoBlockIDs.WORK_STUMP_ID;
    
    public static final int CHERRY_WORK_STUMP_TYPE = 4;
    public static final int ACACIA_WORK_STUMP_TYPE = 5;
    public static final int MAHOGANY_WORK_STUMP_TYPE = 6;
    public static final int MANGROVE_WORK_STUMP_TYPE = 7;
    public static final int HAZEL_WORK_STUMP_TYPE = 0;
    public static final int FIR_WORK_STUMP_TYPE = 1;
    
    public static Map<Integer, Integer> woodTypeToSidingIDMap = new HashMap<>();
    public static Map<Integer, Integer> sidingIDToWoodTypeMap = new HashMap<>();
    
    public static Map<Integer, Integer> woodTypeToMouldingIDMap = new HashMap<>();
    public static Map<Integer, Integer> mouldingIDToWoodTypeMap = new HashMap<>();
    
    static {
        addWoodType(CHERRY_WOOD_TYPE, DecoBlockIDs.CHERRY_SIDING_AND_CORNER_ID, DecoBlockIDs.CHERRY_MOULDING_ID);
        addWoodType(ACACIA_WOOD_TYPE, DecoBlockIDs.ACACIA_SIDING_AND_CORNER_ID, DecoBlockIDs.ACACIA_MOULDING_ID);
        addWoodType(MAHOGANY_WOOD_TYPE, DecoBlockIDs.MAHOGANY_SIDING_AND_CORNER_ID, DecoBlockIDs.MAHOGANY_MOULDING_ID);
        addWoodType(MANGROVE_WOOD_TYPE, DecoBlockIDs.MANGROVE_SIDING_AND_CORNER_ID, DecoBlockIDs.MANGROVE_MOULDING_ID);
        addWoodType(HAZEL_WOOD_TYPE, DecoBlockIDs.HAZEL_SIDING_AND_CORNER_ID, DecoBlockIDs.HAZEL_MOULDING_ID);
        addWoodType(FIR_WOOD_TYPE, DecoBlockIDs.HAZEL_SIDING_AND_CORNER_ID, DecoBlockIDs.HAZEL_MOULDING_ID);
    }
    
    private static void addWoodType(int woodType, int sidingID, int mouldingID) {
        woodTypeToSidingIDMap.put(woodType, sidingID);
        sidingIDToWoodTypeMap.put(sidingID, woodType);
    
        woodTypeToSidingIDMap.put(woodType, mouldingID);
        mouldingIDToWoodTypeMap.put(mouldingID, woodType);
    }
    
    public static Map<Integer, FurnaceBurnTime> furnaceBurnTimes = new HashMap<>();
    
    static {
        furnaceBurnTimes.put(CHERRY_WOOD_TYPE, FurnaceBurnTime.PLANKS_SPRUCE);
        furnaceBurnTimes.put(ACACIA_WOOD_TYPE, FurnaceBurnTime.PLANKS_BIRCH);
        furnaceBurnTimes.put(MAHOGANY_WOOD_TYPE, FurnaceBurnTime.PLANKS_JUNGLE);
        furnaceBurnTimes.put(MANGROVE_WOOD_TYPE, FurnaceBurnTime.PLANKS_JUNGLE);
        furnaceBurnTimes.put(HAZEL_WOOD_TYPE, FurnaceBurnTime.PLANKS_OAK);
        furnaceBurnTimes.put(FIR_WOOD_TYPE, FurnaceBurnTime.PLANKS_BIRCH);
    }
    
    public static int getBarkCountForTanning(int woodType) {
        FurnaceBurnTime burnTime = furnaceBurnTimes.get(woodType);
        
        switch (burnTime) {
            case PLANKS_OAK:
                return 5;
            case PLANKS_SPRUCE:
                return 3;
            case PLANKS_BIRCH:
                return 8;
            case PLANKS_JUNGLE:
                return 2;
        }
        
        return 0;
    }
    
    public static boolean canWoodTypeGrowCocoa(int woodType) {
        return woodType == JUNGLE_WOOD_TYPE || woodType == MAHOGANY_WOOD_TYPE || woodType == MANGROVE_WOOD_TYPE;
    }
}
