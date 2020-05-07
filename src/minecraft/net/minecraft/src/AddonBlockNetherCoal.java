package net.minecraft.src;

public class AddonBlockNetherCoal extends Block {
	public AddonBlockNetherCoal(int id) {
		super(id, Material.rock);
		this.setUnlocalizedName("ginger_netherCoalBlock");
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