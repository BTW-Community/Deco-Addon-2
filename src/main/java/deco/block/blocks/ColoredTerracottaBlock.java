package deco.block.blocks;

import btw.item.BTWItems;
import deco.block.util.SandHelper;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class ColoredTerracottaBlock extends ColoredBlock {
	public ColoredTerracottaBlock(int blockID, String name) {
		super(blockID, Material.rock, name);
		
		this.setHardness(1.0F);
		this.setResistance(5.0F);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public int getHarvestToolLevel(IBlockAccess blockAccess, int x, int y, int z) {
		return 3;
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sandPile.itemID, 8, SandHelper.RED_SAND_TYPE, chanceOfDrop);
		return true;
	}
}
