package deco.world.feature.trees.grower;

import btw.world.feature.trees.grower.*;
import deco.block.util.WoodTypeHelper;

public class DecoTreeGrowers {
	//------ Oak ------//
	
	public static final AbstractTreeGrower HUGE_OAK_TREE = new HugeTreeGrower("deco:huge_oak", 12, 21, TreeGrowers.OAK_WOOD_TYPE);
	
	//------ Spruce ------//
	
	public static final AbstractTreeGrower MEDIUM_SPRUCE_TREE = new MediumTaigaTreeGrower("deco:medium_spruce", 9, 17, TreeGrowers.SPRUCE_WOOD_TYPE); // BTA Taiga 5
	public static final AbstractTreeGrower TALL_SPRUCE_TREE = new TallTaigaTreeGrower("deco:tall_spruce", 13, 21, TreeGrowers.SPRUCE_WOOD_TYPE); // BTA Taiga 6
	public static final AbstractTreeGrower BIG_SPRUCE_TREE = new BigTaigaTreeGrower("deco:big_spruce", 10, 19, TreeGrowers.SPRUCE_WOOD_TYPE);		 // BTA Taiga 7
	public static final AbstractTreeGrower HUGE_SPRUCE_TREE = new HugeTaigaTreeGrower("deco:huge_spruce", 20, 45, TreeGrowers.SPRUCE_WOOD_TYPE);
	
	//------ Birch ------//
	
	public static final AbstractTreeGrower BIG_BIRCH_TREE = new BranchingTreeGrower("deco:big_birch", 10, 20, TreeGrowers.BIRCH_WOOD_TYPE);
	
	//------ Cherry ------//
	
	public static final AbstractTreeGrower CHERRY_TREE = new StandardTreeGrower("deco:cherry", 5, 7, WoodTypeHelper.CHERRY_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_CHERRY_TREE = new BigTreeGrower("deco:big_cherry", 5, 16, WoodTypeHelper.CHERRY_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_CHERRY_TREE = new HugeTreeGrower("deco:huge_cherry", 12, 21, WoodTypeHelper.CHERRY_TREE_WOOD_TYPE);
	
	public static final AbstractTreeGrower WHITE_CHERRY_TREE = new StandardTreeGrower("deco:white_cherry", 5, 7, WoodTypeHelper.WHITE_CHERRY_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_WHITE_CHERRY_TREE = new BigTreeGrower("deco:big_white_cherry", 5, 16, WoodTypeHelper.WHITE_CHERRY_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_WHITE_CHERRY_TREE = new HugeTreeGrower("deco:huge_white_cherry", 12, 21, WoodTypeHelper.WHITE_CHERRY_TREE_WOOD_TYPE);
	
	//------ Acacia ------//
	
	public static final AbstractTreeGrower ACACIA_TREE = new AcaciaTreeGrower("deco:acacia", 4, 6, WoodTypeHelper.ACACIA_TREE_WOOD_TYPE);
	
	//------ Mahogany ------//
	
	public static final AbstractTreeGrower MAHOGANY_TREE = new MahoganyTreeGrower("deco:mahogany", 12, 17, WoodTypeHelper.MAHOGANY_TREE_WOOD_TYPE);
	
	//------ Mangrove ------//
	
	public static final AbstractTreeGrower MANGROVE_TREE = new MangroveTreeGrower("deco:mangrove", 9, 13, WoodTypeHelper.MANGROVE_TREE_WOOD_TYPE);
	
	//------ Hazel ------//
	
	public static final AbstractTreeGrower HAZEL_TREE = new StandardTreeGrower("deco:hazel", 5, 7, WoodTypeHelper.HAZEL_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_HAZEL_TREE = new BigTreeGrower("deco:big_hazel", 5, 16, WoodTypeHelper.HAZEL_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_HAZEL_TREE = new HugeTreeGrower("deco:huge_hazel", 12, 21, WoodTypeHelper.HAZEL_TREE_WOOD_TYPE);
	
	//------ Fir ------//
	
	public static final AbstractTreeGrower FIR_TREE = new TaigaTreeGrower("deco:fir", 6, 9, WoodTypeHelper.FIR_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower MEDIUM_FIR_TREE = new MediumTaigaTreeGrower("deco:medium_fir", 9, 17, WoodTypeHelper.FIR_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower TALL_FIR_TREE = new TallTaigaTreeGrower("deco:tall_fir", 13, 21, WoodTypeHelper.FIR_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_FIR_TREE = new BigTaigaTreeGrower("deco:big_fir", 10, 19, WoodTypeHelper.FIR_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_FIR_TREE = new HugeTaigaTreeGrower("deco:huge_fir", 20, 45, WoodTypeHelper.FIR_TREE_WOOD_TYPE);
	
	//------ Aspen ------//
	
	public static final AbstractTreeGrower ASPEN_TREE = new AspenTreeGrower("deco:aspen", 6, 10, WoodTypeHelper.ASPEN_TREE_WOOD_TYPE);
	
	//------ Willow ------//
	
	public static final AbstractTreeGrower WILLOW_TREE = new SwampTreeGrower("deco:willow", 5, 8, WoodTypeHelper.WILLOW_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_WILLOW_TREE = new HugeWillowTreeGrower("deco:huge_willow", 9, 12, WoodTypeHelper.WILLOW_TREE_WOOD_TYPE);
	
	//------ Dark Oak ------//
	
	public static final AbstractTreeGrower DARK_OAK_TREE = new BranchingTreeGrower("deco:dark_oak", 10, 20, WoodTypeHelper.DARK_OAK_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_DARK_OAK_TREE = new HugeDarkOakTreeGrower("deco:huge_dark_oak", 7, 18, WoodTypeHelper.DARK_OAK_TREE_WOOD_TYPE);
	
	//------ Redwood ------//
	
	public static final AbstractTreeGrower REDWOOD_TREE = new TallTaigaTreeGrower("deco:redwood", 13, 21, WoodTypeHelper.REDWOOD_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_REDWOOD_TREE = new HugeRedwoodTreeGrower("deco:huge_redwood", 20, 35, WoodTypeHelper.REDWOOD_TREE_WOOD_TYPE);
	
	//------ Autumn ------//
	
	public static final AbstractTreeGrower RED_AUTUMN_TREE = new StandardTreeGrower("deco:red_autumn", 5, 7, WoodTypeHelper.RED_AUTUMN_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_RED_AUTUMN_TREE = new BigTreeGrower("deco:big_red_autumn", 5, 16, WoodTypeHelper.RED_AUTUMN_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_RED_AUTUMN_TREE = new HugeTreeGrower("deco:huge_red_autumn", 12, 21, WoodTypeHelper.RED_AUTUMN_TREE_WOOD_TYPE);
	
	public static final AbstractTreeGrower ORANGE_AUTUMN_TREE = new StandardTreeGrower("deco:orange_autumn", 5, 7, WoodTypeHelper.ORANGE_AUTUMN_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_ORANGE_AUTUMN_TREE = new BigTreeGrower("deco:big_orange_autumn", 5, 16, WoodTypeHelper.ORANGE_AUTUMN_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_ORANGE_AUTUMN_TREE = new HugeTreeGrower("deco:huge_orange_autumn", 12, 21, WoodTypeHelper.ORANGE_AUTUMN_TREE_WOOD_TYPE);
	
	public static final AbstractTreeGrower YELLOW_AUTUMN_TREE = new StandardTreeGrower("deco:yellow_autumn", 5, 7, WoodTypeHelper.YELLOW_AUTUMN_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_YELLOW_AUTUMN_TREE = new BigTreeGrower("deco:big_yellow_autumn", 5, 16, WoodTypeHelper.YELLOW_AUTUMN_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_YELLOW_AUTUMN_TREE = new HugeTreeGrower("deco:huge_yellow_autumn", 12, 21, WoodTypeHelper.YELLOW_AUTUMN_TREE_WOOD_TYPE);
}
