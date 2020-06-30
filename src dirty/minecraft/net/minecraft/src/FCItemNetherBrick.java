package net.minecraft.src;

public class FCItemNetherBrick extends Item
{
    public FCItemNetherBrick(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcItemBrickNether");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public boolean IsPistonPackable(ItemStack var1)
    {
        return true;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 4;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return Block.netherBrick.blockID;
    }
}
