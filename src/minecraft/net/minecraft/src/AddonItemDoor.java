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

	public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
	    if (!var3.isRemote)
			var3.playAuxSFX(2252, var4, var5, var6, doorBlock.blockID);
        return super.onItemUse(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
    }
}
