package deco.world.feature.trees.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.World;

import java.util.Random;

public class BranchingTreeGrower extends AbstractTreeGrower {
	protected BranchingTreeGrower(String name, TreeGrowers.TreeWoodType woodType) {
		super(name, woodType);
	}
	
	@Override
	public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		return false;
	}
}
