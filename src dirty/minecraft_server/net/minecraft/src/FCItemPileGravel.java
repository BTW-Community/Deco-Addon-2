package net.minecraft.src;

public class FCItemPileGravel extends Item
{
    public FCItemPileGravel(int var1)
    {
        super(var1);
        this.SetBellowsBlowDistance(1);
        this.SetFilterableProperties(2);
        this.setUnlocalizedName("fcItemPileGravel");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public boolean IsPistonPackable(ItemStack var1)
    {
        return true;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 8;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return Block.gravel.blockID;
    }
}
