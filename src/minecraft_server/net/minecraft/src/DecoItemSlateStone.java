package net.minecraft.src;

public class DecoItemSlateStone extends Item
{
    public DecoItemSlateStone(int var1)
    {
        super(var1);
        this.setUnlocalizedName("decoItemSlateStone");
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
        return DecoDefs.slateCobbleLoose.blockID;
    }
}
