package deco.block.blocks;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;

public class NetherCoalBlock extends Block {
	public NetherCoalBlock(int id) {
		super(id, Material.rock);
		this.setUnlocalizedName("decoBlockNetherCoal");
		this.setPicksEffectiveOn();
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public boolean doesInfiniteBurnToFacing(IBlockAccess blockAccess, int x, int y, int z, int facing) {
		return true;
	}
}
