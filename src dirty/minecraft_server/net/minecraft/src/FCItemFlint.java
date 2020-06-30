package net.minecraft.src;

public class FCItemFlint extends Item
{
    public FCItemFlint(int var1)
    {
        super(var1);
        this.SetFilterableProperties(2);
        this.setUnlocalizedName("flint");
        this.setCreativeTab(CreativeTabs.tabMaterials);
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
        return 7;
    }
}
