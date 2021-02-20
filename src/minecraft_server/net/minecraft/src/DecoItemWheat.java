package net.minecraft.src;

public class DecoItemWheat extends FCItemWheat {

	public DecoItemWheat(int ID) {
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
        return DecoDefs.hayBale.blockID;
    }
}
