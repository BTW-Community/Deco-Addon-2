package net.minecraft.src;

public class FCItemSnowball extends ItemSnowball
{
    public FCItemSnowball(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
        this.SetFilterableProperties(2);
        this.setUnlocalizedName("snowball");
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
        return FCBetterThanWolves.fcBlockSnowSolid.blockID;
    }
}
