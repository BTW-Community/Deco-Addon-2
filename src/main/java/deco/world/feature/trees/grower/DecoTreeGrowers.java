package deco.world.feature.trees.grower;

import btw.world.feature.trees.grower.*;
import deco.block.util.WoodTypeHelper;

public class DecoTreeGrowers {
	//------ Oak ------//
	
	public static final AbstractTreeGrower BRANCHING_OAK_TREE = null;
	public static final AbstractTreeGrower HUGE_OAK_TREE = null;
	
	//------ Spruce ------//
	
	public static final AbstractTreeGrower HUGE_SPRUCE_TREE = null;
	
	//------ Birch ------//
	
	public static final AbstractTreeGrower BRANCHING_BIRCH_TREE = null;
	public static final AbstractTreeGrower BIG_BIRCH_TREE = new BigTreeGrower("deco:big_birch", TreeGrowers.BIRCH_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_BIRCH_TREE = null;
	
	//------ Cherry ------//
	
	public static final AbstractTreeGrower CHERRY_TREE = new StandardTreeGrower("deco:cherry", 5, 7, WoodTypeHelper.CHERRY_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_CHERRY_TREE = new BigTreeGrower("deco:big_cherry", WoodTypeHelper.CHERRY_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_CHERRY_TREE = null;
	
	//------ Acacia ------//
	
	public static final AbstractTreeGrower ACACIA_TREE = null;
	
	//------ Mahogany ------//
	
	public static final AbstractTreeGrower MAHOGANY_TREE = null;
	
	//------ Mangrove ------//
	
	public static final AbstractTreeGrower MANGROVE_TREE = null;
	
	//------ Hazel ------//
	
	public static final AbstractTreeGrower HAZEL_TREE = new StandardTreeGrower("deco:hazel", 5, 7, WoodTypeHelper.HAZEL_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_HAZEL_TREE = new BigTreeGrower("deco:big_hazel", WoodTypeHelper.HAZEL_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_HAZEL_TREE = null;
	
	//------ Fir ------//
	
	public static final AbstractTreeGrower FIR_TREE = new TaigaTreeGrower("deco:fir", 6, 9, WoodTypeHelper.FIR_TREE_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_FIR_TREE = null;
	
	//------ Aspen ------//
	
	//------ Willow ------//
	
	//------ Dark Oak ------//
	
	//------ Autumn ------//
	
	public static final AbstractTreeGrower RED_AUTUMN_TREE = new StandardTreeGrower("deco:red_autumn", 5, 7, WoodTypeHelper.RED_TREE_AUTUMN_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_RED_AUTUMN_TREE = new BigTreeGrower("deco:big_red_autumn", WoodTypeHelper.RED_TREE_AUTUMN_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_RED_AUTUMN_TREE = null;
	
	public static final AbstractTreeGrower ORANGE_AUTUMN_TREE = new StandardTreeGrower("deco:orange_autumn", 5, 7, WoodTypeHelper.ORANGE_TREE_AUTUMN_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_ORANGE_AUTUMN_TREE = new BigTreeGrower("deco:big_orange_autumn", WoodTypeHelper.ORANGE_TREE_AUTUMN_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_ORANGE_AUTUMN_TREE = null;
	
	public static final AbstractTreeGrower YELLOW_AUTUMN_TREE = new StandardTreeGrower("deco:yellow_autumn", 5, 7, WoodTypeHelper.YELLOW_TREE_AUTUMN_WOOD_TYPE);
	public static final AbstractTreeGrower BIG_YELLOW_AUTUMN_TREE = new BigTreeGrower("deco:big_yellow_autumn", WoodTypeHelper.YELLOW_TREE_AUTUMN_WOOD_TYPE);
	public static final AbstractTreeGrower HUGE_YELLOW_AUTUMN_TREE = null;
}
