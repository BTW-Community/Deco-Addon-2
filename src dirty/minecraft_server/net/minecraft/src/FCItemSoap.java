package net.minecraft.src;

public class FCItemSoap extends Item
{
    public FCItemSoap(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.setUnlocalizedName("fcItemSoap");
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
        return 5;
    }
}
