package net.minecraft.src;

public class AddonItemDoor extends FCItemDoor {
	private BlockDoor doorBlock;
	
	public AddonItemDoor(int ID, String texture, String name, BlockDoor door) {
		super(ID);
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
        this.setUnlocalizedName(texture);
        this.SetBuoyant();
        AddonManager.Name(this, name);
        doorBlock = door;
	}

	@Override
	public Block GetDoorBlock() {
		return doorBlock;
	}

	public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int x, int y, int z, int var7, float var8, float var9, float var10)
    {
		var3.playSound(x, y, z, "dig.wood", 1.0F, .75F);
        return super.onItemUse(var1, var2, var3, x, y, z, var7, var8, var9, var10);
    }
}
