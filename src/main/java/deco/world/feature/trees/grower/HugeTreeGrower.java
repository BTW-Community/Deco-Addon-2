package deco.world.feature.trees.grower;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;
import net.minecraft.src.World;

import java.util.Random;

public class HugeTreeGrower extends AbstractTreeGrower {
	protected TreeGrowers.TreeWoodType woodType;
	
	protected HugeTreeGrower(String name, TreeGrowers.TreeWoodType woodType) {
		super(name);
		this.woodType = woodType;
	}
	
	@Override
	public boolean growTree(World world, Random rand, int x, int y, int z, boolean isWorldGen) {
		return false;
	}
}
