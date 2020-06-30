package net.minecraft.src;

public class FCItemClay extends FCItemMortar
{
    public FCItemClay(int var1)
    {
        super(var1);
        this.SetFilterableProperties(2);
        this.setUnlocalizedName("clay");
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
        return FCBetterThanWolves.fcBlockUnfiredClay.blockID;
    }
}
