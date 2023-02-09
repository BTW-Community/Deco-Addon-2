package deco.world.feature.trees.grower;

import btw.world.feature.trees.grower.*;
import deco.block.util.WoodTypeHelper;

public class DecoTreeGrowers {
	//------ Oak ------//
	
	public static final AbstractTreeGrower HUGE_OAK_TREE = new HugeTreeGrower("deco:huge_oak", TreeGrowers.OAK_WOOD_TYPE);
	
	//------ Spruce ------//
	
	public static final AbstractTreeGrower HUGE_SPRUCE_TREE = new HugeTaigaTreeGrower("deco:huge_spruce", TreeGrowers.SPRUCE_WOOD_TYPE);
	
	//------ Birch ------//
	
	public static final AbstractTreeGrower BIG_BIRCH_TREE = new BigTreeGrower("deco:big_birch", TreeGrowers.BIRCH_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_BIRCH_TREE = new HugeTreeGrower("deco:huge_birch", TreeGrowers.BIRCH_WOOD_TYPE);
	
	//------ Cherry ------//
	
	public static final AbstractTreeGrower CHERRY_TREE = new StandardTreeGrower("deco:cherry", 5, 7, WoodTypeHelper.CHERRY_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_CHERRY_TREE = new BigTreeGrower("deco:big_cherry", WoodTypeHelper.CHERRY_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_CHERRY_TREE = new HugeTreeGrower("deco:huge_cherry", WoodTypeHelper.CHERRY_TREE_WOOD_TYPE);
	
	public static final AbstractTreeGrower WHITE_CHERRY_TREE = new StandardTreeGrower("deco:white_cherry", 5, 7, WoodTypeHelper.WHITE_CHERRY_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_WHITE_CHERRY_TREE = new BigTreeGrower("deco:big_white_cherry", WoodTypeHelper.WHITE_CHERRY_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_WHITE_CHERRY_TREE = new HugeTreeGrower("deco:huge_white_cherry", WoodTypeHelper.WHITE_CHERRY_TREE_WOOD_TYPE);
	
	//------ Acacia ------//
	
	public static final AbstractTreeGrower ACACIA_TREE = new AcaciaTreeGrower("deco:acacia", WoodTypeHelper.ACACIA_TREE_WOOD_TYPE);
	
	//------ Mahogany ------//
	
	public static final AbstractTreeGrower MAHOGANY_TREE = new MahoganyTreeGrower("deco:mahogany", WoodTypeHelper.MAHOGANY_TREE_WOOD_TYPE);
	
	//------ Mangrove ------//
	
	public static final AbstractTreeGrower MANGROVE_TREE = new MangroveTreeGrower("deco:mangrove", WoodTypeHelper.MANGROVE_TREE_WOOD_TYPE);
	
	//------ Hazel ------//
	
	public static final AbstractTreeGrower HAZEL_TREE = new StandardTreeGrower("deco:hazel", 5, 7, WoodTypeHelper.HAZEL_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_HAZEL_TREE = new BigTreeGrower("deco:big_hazel", WoodTypeHelper.HAZEL_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_HAZEL_TREE = new HugeTreeGrower("deco:huge_hazel", WoodTypeHelper.HAZEL_TREE_WOOD_TYPE);
	
	//------ Fir ------//
	
	public static final AbstractTreeGrower BRANCHING_HAZEL_TREE = null;
	public static final AbstractTreeGrower FIR_TREE = new TaigaTreeGrower("deco:fir", 6, 9, WoodTypeHelper.FIR_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_FIR_TREE = new HugeTaigaTreeGrower("deco:huge_fir", WoodTypeHelper.FIR_TREE_WOOD_TYPE);
	
	//------ Aspen ------//
	
	public static final AbstractTreeGrower ASPEN_TREE = new AspenTreeGrower("deco:aspen", WoodTypeHelper.ASPEN_TREE_WOOD_TYPE);
	
	//------ Willow ------//
	
	public static final AbstractTreeGrower WILLOW_TREE = null;
	public static final AbstractTreeGrower HUGE_WILLOW_TREE = null;
	
	//------ Dark Oak ------//
	
	public static final AbstractTreeGrower DARK_OAK_TREE = null;
	public static final AbstractTreeGrower HUGE_DARK_OAK_TREE = new HugeDarkOakTreeGrower("deco:huge_dark_oak", 7, 15, WoodTypeHelper.DARK_OAK_TREE_WOOD_TYPE);
	
	//------ Redwood ------//
	
	public static final AbstractTreeGrower REDWOOD_TREE = null;
	public static final AbstractTreeGrower HUGE_REDWOOD_TREE = null;
	
	//------ Autumn ------//
	
	public static final AbstractTreeGrower RED_AUTUMN_TREE = new StandardTreeGrower("deco:red_autumn", 5, 7, WoodTypeHelper.RED_AUTUMN_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_RED_AUTUMN_TREE = new BigTreeGrower("deco:big_red_autumn", WoodTypeHelper.RED_AUTUMN_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_RED_AUTUMN_TREE = new HugeTreeGrower("deco:huge_red_autumn", WoodTypeHelper.RED_AUTUMN_TREE_WOOD_TYPE);
	
	public static final AbstractTreeGrower ORANGE_AUTUMN_TREE = new StandardTreeGrower("deco:orange_autumn", 5, 7, WoodTypeHelper.ORANGE_AUTUMN_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_ORANGE_AUTUMN_TREE = new BigTreeGrower("deco:big_orange_autumn", WoodTypeHelper.ORANGE_AUTUMN_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_ORANGE_AUTUMN_TREE = new HugeTreeGrower("deco:huge_orange_autumn", WoodTypeHelper.ORANGE_AUTUMN_TREE_WOOD_TYPE);
	
	public static final AbstractTreeGrower YELLOW_AUTUMN_TREE = new StandardTreeGrower("deco:yellow_autumn", 5, 7, WoodTypeHelper.YELLOW_AUTUMN_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_YELLOW_AUTUMN_TREE = new BigTreeGrower("deco:big_yellow_autumn", WoodTypeHelper.YELLOW_AUTUMN_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_YELLOW_AUTUMN_TREE = new HugeTreeGrower("deco:huge_yellow_autumn", WoodTypeHelper.YELLOW_AUTUMN_TREE_WOOD_TYPE);
}
