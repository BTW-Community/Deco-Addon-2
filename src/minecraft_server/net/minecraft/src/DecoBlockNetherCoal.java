package net.minecraft.src;

public class DecoBlockNetherCoal extends Block {
	public DecoBlockNetherCoal(int id) {
		super(id, Material.rock);
		this.setUnlocalizedName("decoBlockNetherCoal");
		this.SetPicksEffectiveOn();
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public boolean DoesInfiniteBurnToFacing(IBlockAccess blockAccess, int x, int y, int z, int facing) {
		return true;
	}
}