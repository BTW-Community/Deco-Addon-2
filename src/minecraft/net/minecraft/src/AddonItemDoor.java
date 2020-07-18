package net.minecraft.src;

public class AddonItemDoor extends FCItemDoor {
	private BlockDoor doorBlock;
	private boolean isWood;

	public AddonItemDoor(int ID, String texture, String name, BlockDoor door) {
		this(ID, texture, name, door, false);
	}
	
	public AddonItemDoor(int ID, String texture, String name, BlockDoor door, boolean isWood) {
		super(ID);
		this.SetBuoyant();
		this.setUnlocalizedName(texture);
		this.SetBuoyant();
		AddonManager.Name(this, name);
		doorBlock = door;
		this.isWood = isWood;
	}

	@Override
	public Block GetDoorBlock() {
		return doorBlock;
	}

	public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int x, int y, int z, int var7, float var8, float var9, float var10)
	{
		if (var7 == 1)
		{
			++y;

			if (var2.canPlayerEdit(x, y, z, var7, var1) && var2.canPlayerEdit(x, y + 1, z, var7, var1) && this.GetDoorBlock().canPlaceBlockAt(var3, x, y, z))
			{
				int var11 = MathHelper.floor_double((double)((var2.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
				ItemDoor.placeDoorBlock(var3, x, y, z, var11, this.GetDoorBlock());
				--var1.stackSize;
				if (isWood)
					var3.playSound(x, y, z, "dig.wood", 1.0F, .75F);
				else
					var3.playSound(x, y, z, "dig.stone", 1.0F, 1.5F);
				return true;
			}
		}

		return false;
	}
}