package net.minecraft.src;

public class DecoItemNitre extends Item {
	public DecoItemNitre(int id) {
		super(id);
		this.SetBellowsBlowDistance(3);
		this.SetFilterableProperties(8);
		this.setUnlocalizedName("fcItemNitre");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (player != null && player.canPlayerEdit(x, y, z, side, stack)) {
			int id = world.getBlockId(x, y, z);

			if (id == FCBetterThanWolves.fcBlockFarmland.blockID) {
				--stack.stackSize;
				
				world.setBlockAndMetadataWithNotify(x, y, z, DecoDefs.farmlandSalted.blockID, world.getBlockMetadata(x, y, z));
				return true;
			}
		}

		return false;
	}
}