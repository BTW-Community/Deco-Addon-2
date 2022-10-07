package deco.block.util;

import btw.block.BTWBlocks;
import btw.crafting.util.FurnaceBurnTime;
import deco.block.DecoBlockIDs;

import java.util.HashMap;
import java.util.Map;

public class WoodTypeHelper {
    public static String[] woodNames = new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry", "acacia", "mahogany", "mangrove", "hazel", "walnut"};
    public static String[] woodNamesCapital = new String[] {"Oak", "Spruce", "Birch", "Jungle", "Blood", "Cherry", "Acacia", "Mahogany", "Mangrove", "Hazel", "Walnut"};
    
    public static final int NUM_VANILLA_WOOD = 5;
    public static final int NUM_EXTRA_WOOD = 5;
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
    public static final int WALNUT_WOOD_TYPE = 10;
    
    public static final int MANGROVE_SLAB_TYPE = 0;
    public static final int HAZEL_SLAB_TYPE = 0;
    public static final int WALNUT_SLAB_TYPE = 0;
    
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
    public static final int WALNUT_BARREL_ID = DecoBlockIDs.BARREL_3_ID;
    
    public static final int OAK_BARREL_TYPE = 0;
    public static final int SPRUCE_BARREL_TYPE = 1;
    public static final int BIRCH_BARREL_TYPE = 2;
    public static final int JUNGLE_BARREL_TYPE = 3;
    public static final int BLOOD_BARREL_TYPE = 0;
    public static final int CHERRY_BARREL_TYPE = 1;
    public static final int ACACIA_BARREL_TYPE = 2;
    public static final int MAHOGANY_BARREL_TYPE = 3;
    public static final int MANGROVE_BARREL_TYPE = 0;
    public static final int HAZEL_BARREL_TYPE = 0;
    public static final int WALNUT_BARREL_TYPE = 0;
    
    public static final int CHERRY_WORK_STUMP_ID = BTWBlocks.workStump.blockID;
    public static final int ACACIA_WORK_STUMP_ID = BTWBlocks.workStump.blockID;
    public static final int MAHOGANY_WORK_STUMP_ID = BTWBlocks.workStump.blockID;
    public static final int MANGROVE_WORK_STUMP_ID = BTWBlocks.workStump.blockID;
    public static final int HAZEL_WORK_STUMP_ID = DecoBlockIDs.WORK_STUMP_ID;
    public static final int WALNUT_WORK_STUMP_ID = DecoBlockIDs.WORK_STUMP_ID;
    
    public static final int CHERRY_WORK_STUMP_TYPE = 4;
    public static final int ACACIA_WORK_STUMP_TYPE = 5;
    public static final int MAHOGANY_WORK_STUMP_TYPE = 6;
    public static final int MANGROVE_WORK_STUMP_TYPE = 7;
    public static final int HAZEL_WORK_STUMP_TYPE = 0;
    public static final int WALNUT_WORK_STUMP_TYPE = 1;
    
    public static Map<Integer, Integer> woodTypeToSidingIDMap = new HashMap<>();
    public static Map<Integer, Integer> woodTypeToMouldingIDMap = new HashMap<>();
    
    static {
        woodTypeToSidingIDMap.put(CHERRY_WOOD_TYPE, DecoBlockIDs.CHERRY_SIDING_AND_CORNER_ID);
        woodTypeToSidingIDMap.put(ACACIA_WOOD_TYPE, DecoBlockIDs.ACACIA_SIDING_AND_CORNER_ID);
        woodTypeToSidingIDMap.put(MAHOGANY_WOOD_TYPE, DecoBlockIDs.MAHOGANY_SIDING_AND_CORNER_ID);
        woodTypeToSidingIDMap.put(MANGROVE_WOOD_TYPE, DecoBlockIDs.MANGROVE_SIDING_AND_CORNER_ID);
        woodTypeToSidingIDMap.put(HAZEL_WOOD_TYPE, DecoBlockIDs.HAZEL_SIDING_AND_CORNER_ID);
    
        woodTypeToMouldingIDMap.put(CHERRY_WOOD_TYPE, DecoBlockIDs.CHERRY_MOULDING_ID);
        woodTypeToMouldingIDMap.put(ACACIA_WOOD_TYPE, DecoBlockIDs.ACACIA_MOULDING_ID);
        woodTypeToMouldingIDMap.put(MAHOGANY_WOOD_TYPE, DecoBlockIDs.MAHOGANY_MOULDING_ID);
        woodTypeToMouldingIDMap.put(MANGROVE_WOOD_TYPE, DecoBlockIDs.MANGROVE_MOULDING_ID);
        woodTypeToMouldingIDMap.put(HAZEL_WOOD_TYPE, DecoBlockIDs.HAZEL_MOULDING_ID);
    }
    
    public static Map<Integer, FurnaceBurnTime> furnaceBurnTimes = new HashMap<>();
    
    static {
        furnaceBurnTimes.put(CHERRY_WOOD_TYPE, FurnaceBurnTime.PLANKS_SPRUCE);
        furnaceBurnTimes.put(ACACIA_WOOD_TYPE, FurnaceBurnTime.PLANKS_BIRCH);
        furnaceBurnTimes.put(MAHOGANY_WOOD_TYPE, FurnaceBurnTime.PLANKS_JUNGLE);
        furnaceBurnTimes.put(MANGROVE_WOOD_TYPE, FurnaceBurnTime.PLANKS_JUNGLE);
        furnaceBurnTimes.put(HAZEL_WOOD_TYPE, FurnaceBurnTime.PLANKS_OAK);
    }
}
