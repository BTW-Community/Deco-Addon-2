package net.minecraft.src;

public class DecoItemDoor extends FCItemDoor {
	private int doorBlockID;
	private boolean isWood;

	public DecoItemDoor(int ID, String texture, String name, int doorID) {
		this(ID, texture, name, doorID, false);
	}
	
	public DecoItemDoor(int ID, String texture, String name, int doorID, boolean isWood) {
		super(ID);
		this.SetBuoyant();
		this.setUnlocalizedName(texture);
		this.SetBuoyant();
		this.setMaxStackSize(16);
		DecoManager.Name(this, name);
		doorBlockID = doorID;
		this.isWood = isWood;
		
		if (this.isWood) {
			this.SetIncineratedInCrucible();
		}
	}

	@Override
	public Block GetDoorBlock() {
		return Block.blocksList[doorBlockID];
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