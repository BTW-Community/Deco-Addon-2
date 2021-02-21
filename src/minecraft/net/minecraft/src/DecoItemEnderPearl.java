package net.minecraft.src;

public class DecoItemEnderPearl extends ItemEnderPearl {
	public DecoItemEnderPearl(int id) {
		super(id);
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
        return FCBetterThanWolves.fcAestheticOpaque.blockID;
    }

    public int GetResultingBlockMetadataOnPistonPack(ItemStack var1)
    {
        return 14;
    }
}