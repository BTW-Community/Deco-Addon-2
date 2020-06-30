package net.minecraft.src;

public class FCItemBone extends Item
{
    public FCItemBone(int var1)
    {
        super(var1);
        this.maxStackSize = 16;
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
        this.SetFilterableProperties(4);
        this.setFull3D();
        this.setUnlocalizedName("bone");
        this.setCreativeTab(CreativeTabs.tabMisc);
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
        return 15;
    }
}
