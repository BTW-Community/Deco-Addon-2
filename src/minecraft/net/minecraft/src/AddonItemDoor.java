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
	
}
