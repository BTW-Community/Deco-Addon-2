package net.minecraft.src;

public class AddonItemWheat extends FCItemWheat {

	public AddonItemWheat(int ID) {
		super(ID);
	}

    public boolean IsPistonPackable(ItemStack var1)
    {
        return true;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 9;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return AddonDefs.hayBale.blockID;
    }
}
