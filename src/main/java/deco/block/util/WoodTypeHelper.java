package deco.block.util;

import btw.BTWMod;
import btw.block.BTWBlocks;
import btw.crafting.util.FurnaceBurnTime;
import btw.world.feature.trees.grower.TreeGrowers;
import deco.block.DecoBlockIDs;
import net.minecraft.src.Block;

import java.util.HashMap;
import java.util.Map;

public class WoodTypeHelper {
    public static String[] woodNames = new String[] {"oak", "spruce", "birch", "jungle", "blood", "darkOak", "acacia", "mahogany", "mangrove", "hazel",
            "fir", "aspen", "willow", "cherry", "redwood"};
    public static String[] woodNamesCapital = new String[] {"Oak", "Spruce", "Birch", "Jungle", "Blood", "DarkOak", "Acacia", "Mahogany", "Mangrove", "Hazel",
            "Fir", "Aspen", "Willow", "Cherry", "Redwood"};
    
    public static final int VANILLA_LOG_ID = 17;
    public static final int OAK_SIGN_ID = 63;
    public static final int OAK_WALL_SIGN_ID = 68;
    
    public static final int NUM_VANILLA_WOOD = 5;
    public static final int NUM_EXTRA_WOOD = 10;
    public static final int NUM_TOTAL_WOOD = NUM_VANILLA_WOOD + NUM_EXTRA_WOOD;
    
    public static final int OAK_WOOD_TYPE = 0;
    public static final int SPRUCE_WOOD_TYPE = 1;
    public static final int BIRCH_WOOD_TYPE = 2;
    public static final int JUNGLE_WOOD_TYPE = 3;
    public static final int BLOOD_WOOD_TYPE = 4;
    public static final int DARK_OAK_WOOD_TYPE = 5;
    public static final int ACACIA_WOOD_TYPE = 6;
    public static final int MAHOGANY_WOOD_TYPE = 7;
    public static final int MANGROVE_WOOD_TYPE = 8;
    public static final int HAZEL_WOOD_TYPE = 9;
    public static final int FIR_WOOD_TYPE = 10;
    public static final int ASPEN_WOOD_TYPE = 11;
    public static final int WILLOW_WOOD_TYPE = 12;
    public static final int CHERRY_WOOD_TYPE = 13;
    public static final int REDWOOD_WOOD_TYPE = 14;
    public static final int AZALEA_WOOD_TYPE = 15;
    
    public static final int RED_AUTUMN_TYPE = 0;
    public static final int ORANGE_AUTUMN_TYPE = 1;
    public static final int YELLOW_AUTUMN_TYPE = 2;
    
    public static final int MANGROVE_SLAB_TYPE = 0;
    public static final int HAZEL_SLAB_TYPE = 1;
    public static final int FIR_SLAB_TYPE = 2;
    public static final int ASPEN_SLAB_TYPE = 3;
    public static final int WILLOW_SLAB_TYPE = 4;
    public static final int CHERRY_SLAB_TYPE = 5;
    public static final int REDWOOD_SLAB_TYPE = 6;
    
    public static final int OAK_BARREL_ID = DecoBlockIDs.BARREL_ID;
    public static final int SPRUCE_BARREL_ID = DecoBlockIDs.BARREL_ID;
    public static final int BIRCH_BARREL_ID = DecoBlockIDs.BARREL_ID;
    public static final int JUNGLE_BARREL_ID = DecoBlockIDs.BARREL_ID;
    public static final int BLOOD_BARREL_ID = DecoBlockIDs.BARREL_2_ID;
    public static final int DARK_OAK_BARREL_ID = DecoBlockIDs.BARREL_2_ID;
    public static final int ACACIA_BARREL_ID = DecoBlockIDs.BARREL_2_ID;
    public static final int MAHOGANY_BARREL_ID = DecoBlockIDs.BARREL_2_ID;
    public static final int MANGROVE_BARREL_ID = DecoBlockIDs.BARREL_3_ID;
    public static final int HAZEL_BARREL_ID = DecoBlockIDs.BARREL_3_ID;
    public static final int FIR_BARREL_ID = DecoBlockIDs.BARREL_3_ID;
    public static final int ASPEN_BARREL_ID = DecoBlockIDs.BARREL_3_ID;
    public static final int WILLOW_BARREL_ID = DecoBlockIDs.BARREL_4_ID;
    public static final int CHERRY_BARREL_ID = DecoBlockIDs.BARREL_4_ID;
    public static final int REDWOOD_BARREL_ID = DecoBlockIDs.BARREL_4_ID;
    
    public static final int OAK_BARREL_TYPE = 0;
    public static final int SPRUCE_BARREL_TYPE = 1;
    public static final int BIRCH_BARREL_TYPE = 2;
    public static final int JUNGLE_BARREL_TYPE = 3;
    public static final int BLOOD_BARREL_TYPE = 0;
    public static final int DARK_OAK_BARREL_TYPE = 1;
    public static final int ACACIA_BARREL_TYPE = 2;
    public static final int MAHOGANY_BARREL_TYPE = 3;
    public static final int MANGROVE_BARREL_TYPE = 0;
    public static final int HAZEL_BARREL_TYPE = 1;
    public static final int FIR_BARREL_TYPE = 2;
    public static final int ASPEN_BARREL_TYPE = 3;
    public static final int WILLOW_BARREL_TYPE = 0;
    public static final int CHERRY_BARREL_TYPE = 1;
    public static final int REDWOOD_BARREL_TYPE = 2;
    
    // Hard coded to prevent null pointer
    public static final int BTW_WORK_STUMP_ID = 1030;
    
    public static final int DARK_OAK_WORK_STUMP_ID = BTW_WORK_STUMP_ID;
    public static final int ACACIA_WORK_STUMP_ID = BTW_WORK_STUMP_ID;
    public static final int MAHOGANY_WORK_STUMP_ID = BTW_WORK_STUMP_ID;
    public static final int MANGROVE_WORK_STUMP_ID = BTW_WORK_STUMP_ID;
    public static final int HAZEL_WORK_STUMP_ID = DecoBlockIDs.WORK_STUMP_ID;
    public static final int FIR_WORK_STUMP_ID = DecoBlockIDs.WORK_STUMP_ID;
    public static final int ASPEN_WORK_STUMP_ID = DecoBlockIDs.WORK_STUMP_ID;
    public static final int WILLOW_WORK_STUMP_ID = DecoBlockIDs.WORK_STUMP_ID;
    public static final int CHERRY_WORK_STUMP_ID = DecoBlockIDs.WORK_STUMP_ID;
    public static final int REDWOOD_WORK_STUMP_ID = DecoBlockIDs.WORK_STUMP_ID;
    
    public static final int DARK_OAK_WORK_STUMP_TYPE = 4;
    public static final int ACACIA_WORK_STUMP_TYPE = 5;
    public static final int MAHOGANY_WORK_STUMP_TYPE = 6;
    public static final int MANGROVE_WORK_STUMP_TYPE = 7;
    public static final int HAZEL_WORK_STUMP_TYPE = 0;
    public static final int FIR_WORK_STUMP_TYPE = 1;
    public static final int ASPEN_WORK_STUMP_TYPE = 2;
    public static final int WILLOW_WORK_STUMP_TYPE = 3;
    public static final int CHERRY_WORK_STUMP_TYPE = 4;
    public static final int REDWOOD_WORK_STUMP_TYPE = 5;
    
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
        addWoodType(FIR_WOOD_TYPE, DecoBlockIDs.FIR_SIDING_AND_CORNER_ID, DecoBlockIDs.FIR_MOULDING_ID);
        addWoodType(ASPEN_WOOD_TYPE, DecoBlockIDs.ASPEN_SIDING_AND_CORNER_ID, DecoBlockIDs.ASPEN_MOULDING_ID);
        addWoodType(WILLOW_WOOD_TYPE, DecoBlockIDs.WILLOW_SIDING_AND_CORNER_ID, DecoBlockIDs.WILLOW_MOULDING_ID);
        addWoodType(DARK_OAK_WOOD_TYPE, DecoBlockIDs.DARK_OAK_SIDING_AND_CORNER_ID, DecoBlockIDs.DARK_OAK_MOULDING_ID);
        addWoodType(REDWOOD_WOOD_TYPE, DecoBlockIDs.REDWOOD_SIDING_AND_CORNER_ID, DecoBlockIDs.REDWOOD_MOULDING_ID);
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
        furnaceBurnTimes.put(ASPEN_WOOD_TYPE, FurnaceBurnTime.PLANKS_BIRCH);
        furnaceBurnTimes.put(WILLOW_WOOD_TYPE, FurnaceBurnTime.PLANKS_OAK);
        furnaceBurnTimes.put(DARK_OAK_WOOD_TYPE, FurnaceBurnTime.PLANKS_SPRUCE);
        furnaceBurnTimes.put(REDWOOD_WOOD_TYPE, FurnaceBurnTime.PLANKS_OAK);
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
    
    public static int[] getSignBlockIDs() {
        return new int[] {
                OAK_SIGN_ID,
                DecoBlockIDs.SPRUCE_SIGN_ID,
                DecoBlockIDs.BIRCH_SIGN_ID,
                DecoBlockIDs.JUNGLE_SIGN_ID,
                DecoBlockIDs.BLOOD_SIGN_ID,
                DecoBlockIDs.DARK_OAK_SIGN_ID,
                DecoBlockIDs.ACACIA_SIGN_ID,
                DecoBlockIDs.MAHOGANY_SIGN_ID,
                DecoBlockIDs.MANGROVE_SIGN_ID,
                DecoBlockIDs.HAZEL_SIGN_ID,
                DecoBlockIDs.FIR_SIGN_ID,
                DecoBlockIDs.ASPEN_SIGN_ID,
                DecoBlockIDs.WILLOW_SIGN_ID,
                DecoBlockIDs.CHERRY_SIGN_ID,
                DecoBlockIDs.REDWOOD_SIGN_ID
        };
    }
    
    public static int[] getWallSignBlockIDs() {
        return new int[] {
                OAK_WALL_SIGN_ID,
                DecoBlockIDs.SPRUCE_WALL_SIGN_ID,
                DecoBlockIDs.BIRCH_WALL_SIGN_ID,
                DecoBlockIDs.JUNGLE_WALL_SIGN_ID,
                DecoBlockIDs.BLOOD_WALL_SIGN_ID,
                DecoBlockIDs.DARK_OAK_WALL_SIGN_ID,
                DecoBlockIDs.ACACIA_WALL_SIGN_ID,
                DecoBlockIDs.MAHOGANY_WALL_SIGN_ID,
                DecoBlockIDs.MANGROVE_WALL_SIGN_ID,
                DecoBlockIDs.HAZEL_WALL_SIGN_ID,
                DecoBlockIDs.FIR_WALL_SIGN_ID,
                DecoBlockIDs.ASPEN_WALL_SIGN_ID,
                DecoBlockIDs.WILLOW_WALL_SIGN_ID,
                DecoBlockIDs.CHERRY_WALL_SIGN_ID,
                DecoBlockIDs.REDWOOD_WALL_SIGN_ID
        };
    }
    
    public static String[] getSignTextureNames() {
        return new String[] {
                "sign",
                "decoItemSignSpruce",
                "decoItemSignBirch",
                "decoItemSignJungle",
                "decoItemSignBlood",
                "decoItemSignDarkOak",
                "decoItemSignAcacia",
                "decoItemSignMahogany",
                "decoItemSignMangrove",
                "decoItemSignHazel",
                "decoItemSignFir",
                "decoItemSignAspen",
                "decoItemSignWillow",
                "decoItemSignCherry",
                "decoItemSignRedwood"
        };
    }
    
    public static boolean canWoodTypeGrowCocoa(int woodType) {
        return woodType == JUNGLE_WOOD_TYPE || woodType == MAHOGANY_WOOD_TYPE || woodType == MANGROVE_WOOD_TYPE;
    }
    
    public static final TreeGrowers.TreeWoodType CHERRY_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlockIDs.CHERRY_LOG_ID, 0,
            DecoBlockIDs.CHERRY_STUMP_ID, 0,
            DecoBlockIDs.CHERRY_LEAVES_ID, 0);
    
    public static final TreeGrowers.TreeWoodType ACACIA_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlockIDs.ACACIA_LOG_ID, 0,
            DecoBlockIDs.ACACIA_STUMP_ID, 0,
            DecoBlockIDs.ACACIA_LEAVES_ID, 0);
    
    public static final TreeGrowers.TreeWoodType MAHOGANY_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlockIDs.MAHOGANY_LOG_ID, 0,
            DecoBlockIDs.MAHOGANY_STUMP_ID, 0,
            DecoBlockIDs.MAHOGANY_LEAVES_ID, 0);
    
    public static final TreeGrowers.TreeWoodType MANGROVE_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlockIDs.MANGROVE_LOG_ID, 0,
            DecoBlockIDs.MANGROVE_STUMP_ID, 0,
            DecoBlockIDs.MANGROVE_LEAVES_ID, 0);
    
    public static final TreeGrowers.TreeWoodType HAZEL_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlockIDs.HAZEL_LOG_ID, 0,
            DecoBlockIDs.HAZEL_STUMP_ID, 0,
            DecoBlockIDs.HAZEL_LEAVES_ID, 0);
    
    public static final TreeGrowers.TreeWoodType FIR_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlockIDs.FIR_LOG_ID, 0,
            DecoBlockIDs.FIR_STUMP_ID, 0,
            DecoBlockIDs.FIR_LEAVES_ID, 0);
    
    public static final TreeGrowers.TreeWoodType ASPEN_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlockIDs.ASPEN_LOG_ID, 0,
            DecoBlockIDs.ASPEN_STUMP_ID, 0,
            DecoBlockIDs.ASPEN_LEAVES_ID, 0);
    
    public static final TreeGrowers.TreeWoodType WILLOW_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlockIDs.WILLOW_LOG_ID, 0,
            DecoBlockIDs.WILLOW_STUMP_ID, 0,
            DecoBlockIDs.WILLOW_LEAVES_ID, 0);
    
    public static final TreeGrowers.TreeWoodType DARK_OAK_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlockIDs.DARK_OAK_LOG_ID, 0,
            DecoBlockIDs.DARK_OAK_STUMP_ID, 0,
            DecoBlockIDs.DARK_OAK_LEAVES_ID, 0);
    
    public static final TreeGrowers.TreeWoodType REDWOOD_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlockIDs.REDWOOD_LOG_ID, 0,
            DecoBlockIDs.REDWOOD_STUMP_ID, 0,
            DecoBlockIDs.REDWOOD_LEAVES_ID, 0);
    
    public static final TreeGrowers.TreeWoodType RED_AUTUMN_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            DecoBlockIDs.DARK_OAK_LOG_ID, 0,
            DecoBlockIDs.DARK_OAK_STUMP_ID, 0,
            DecoBlockIDs.AUTUMN_LEAVES_ID, RED_AUTUMN_TYPE);
    
    public static final TreeGrowers.TreeWoodType ORANGE_AUTUMN_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            VANILLA_LOG_ID, OAK_WOOD_TYPE,
            VANILLA_LOG_ID, OAK_WOOD_TYPE | 12,
            DecoBlockIDs.AUTUMN_LEAVES_ID, ORANGE_AUTUMN_TYPE);
    
    public static final TreeGrowers.TreeWoodType YELLOW_AUTUMN_TREE_WOOD_TYPE = new TreeGrowers.TreeWoodType(
            VANILLA_LOG_ID, BIRCH_WOOD_TYPE,
            VANILLA_LOG_ID, BIRCH_WOOD_TYPE | 12,
            DecoBlockIDs.AUTUMN_LEAVES_ID, YELLOW_AUTUMN_TYPE);
}
