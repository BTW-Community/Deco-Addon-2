package deco.block.blocks;

import btw.block.blocks.FallingFullBlock;
import btw.item.BTWItems;
import deco.block.util.SandHelper;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class UnfiredTerracottaBlock extends FallingFullBlock {
	public UnfiredTerracottaBlock(int blockID) {
		super(blockID, Material.sand);
		this.setHardness(0.5F);
		this.setShovelsEffectiveOn();
		this.setFilterableProperties(8);
		this.setStepSound(soundSandFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int side, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sandPile.itemID, 6, SandHelper.SAND_TYPE, chanceOfDrop);
		return true;
	}
	
	@Override
	public float getMovementModifier(World world, int x, int y, int z) {
		return 0.8F;
	}
}
