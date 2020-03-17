package net.minecraft.src;

public class AddonBlockTrapDoor extends FCBlockTrapDoor {

	private boolean flagBetterPlaceRule;
	private boolean betterPlaceRuleIsLower;

	protected AddonBlockTrapDoor(int ID) {
		super(ID);
		this.setUnlocalizedName("trapdoor");
	}

	/**
	 * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
	 */
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		int var10 = 0;

		if (side == 2)
		{
			var10 = 0;
		}

		if (side == 3)
		{
			var10 = 1;
		}

		if (side == 4)
		{
			var10 = 2;
		}

		if (side == 5)
		{
			var10 = 3;
		}

		if (side != 1 && side != 0 && hitY > 0.5F)
		{
			var10 |= 8;
		}
		else {
			flagBetterPlaceRule = true;
			betterPlaceRuleIsLower = (side == 1 ? true : false);
		}

		return var10;
	}

	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack)
	{
		if (flagBetterPlaceRule) {
			int var7 = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			int metadataMod = (betterPlaceRuleIsLower ? 0 : 8);

			if (var7 == 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, metadataMod + 0);
			}

			if (var7 == 1)
			{
				world.setBlockMetadataWithNotify(x, y, z, metadataMod + 3);
			}

			if (var7 == 2)
			{
				world.setBlockMetadataWithNotify(x, y, z, metadataMod + 1);
			}

			if (var7 == 3)
			{
				world.setBlockMetadataWithNotify(x, y, z, metadataMod + 2);
			}
			
			flagBetterPlaceRule = false;
		}
	}
}
