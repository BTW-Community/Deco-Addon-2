package deco.world.feature.trees.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.World;

import java.util.Random;

public class DarkOakTreeGrower extends AbstractTreeGrower {
	protected int minTreeHeight;
	protected int maxTreeHeight;
	
	public TreeGrowers.TreeWoodType woodType;
	
	public DarkOakTreeGrower(String name, int minTreeHeight, int maxTreeHeight, TreeGrowers.TreeWoodType woodType) {
		super(name);
		
		this.minTreeHeight = minTreeHeight;
		this.maxTreeHeight = maxTreeHeight;
		
		this.woodType = woodType;
	}
	
	@Override
	public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		return false;
	}
}
