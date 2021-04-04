package net.minecraft.src;

public class DecoItemGrimstoneTile extends Item
{
    public DecoItemGrimstoneTile(int var1)
    {
        super(var1);
        this.setUnlocalizedName("decoItemGrimstoneTile");
        this.SetFilterableProperties(2);
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
        return DecoDefs.grimstoneTilesLoose.blockID;
    }
}
