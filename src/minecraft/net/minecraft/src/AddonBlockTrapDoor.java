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
			var10 += 8;
		}
		
		if (side == 1){
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
				world.setBlockMetadataWithNotify(x, y, z, metadataMod + 0, 2);
			}

			if (var7 == 1)
			{
				world.setBlockMetadataWithNotify(x, y, z, metadataMod + 3, 2);
			}

			if (var7 == 2)
			{
				world.setBlockMetadataWithNotify(x, y, z, metadataMod + 1, 2);
			}

			if (var7 == 3)
			{
				world.setBlockMetadataWithNotify(x, y, z, metadataMod + 2, 2);
			}
			
			flagBetterPlaceRule = false;
		}
	}

	//CLIENT ONLY
	public void registerIcons(IconRegister var1)
	{
		super.registerIcons(var1);
		//this.m_filterIcon = var1.registerIcon("fcBlockHopper_trap");
	}

	@Override public boolean RenderBlock(RenderBlocks r, int X, int Y, int Z)
	{
		super.setBlockBoundsForItemRender();
		super.setBlockBoundsBasedOnState(r.blockAccess, X, Y, Z);
		r.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(r.blockAccess, X, Y, Z));

		//0 east
		//1 west
		//2 north
		//3 south

		switch(r.blockAccess.getBlockMetadata(X, Y, Z)) {
		case 0:
		case 8:
			r.SetUvRotateTop(3);
			r.SetUvRotateBottom(3);
			break;
		case 1:
		case 9:
			r.SetUvRotateTop(0);
			r.SetUvRotateBottom(0);
			break;
		case 2:
		case 10:
			r.SetUvRotateTop(1);
			r.SetUvRotateBottom(1);
			break;
		case 3:
		case 11:
			r.SetUvRotateTop(2);
			r.SetUvRotateBottom(2);
			break;
		case 4:
		case 5:
			r.SetUvRotateEast(3);
			r.SetUvRotateWest(3);
			r.SetUvRotateNorth(1);
			r.SetUvRotateSouth(1);
			r.SetUvRotateTop(1);
			r.SetUvRotateBottom(1);
			break;
		case 6:
		case 7:
			r.SetUvRotateEast(1);
			r.SetUvRotateWest(1);
			r.SetUvRotateNorth(3);
			r.SetUvRotateSouth(3);
			r.SetUvRotateTop(1);
			r.SetUvRotateBottom(1);
			break;
		case 14:
		case 15:
			r.SetUvRotateTop(1);
			r.SetUvRotateBottom(1);
			break;
		default:
			break;
		}
		r.renderStandardBlock(this, X, Y, Z);
		r.ClearUvRotation();
		return true;
	}
}
