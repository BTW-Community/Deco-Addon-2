package net.minecraft.src;

public class DecoItemStraw extends FCItemStraw {
	public DecoItemStraw(int ID) {
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
		return DecoDefs.thatch.blockID;
	}
}