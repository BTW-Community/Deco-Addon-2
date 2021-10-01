package net.minecraft.src;

public class DecoItemPileRedSand extends Item
{
    public DecoItemPileRedSand(int var1)
    {
        super(var1);
        this.SetBellowsBlowDistance(2);
        this.SetFilterableProperties(8);
        this.setUnlocalizedName("decoItemPileRedSand");
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
        return DecoDefs.redSand.blockID;
    }
}