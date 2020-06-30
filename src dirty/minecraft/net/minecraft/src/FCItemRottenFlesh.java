package net.minecraft.src;

public class FCItemRottenFlesh extends FCItemFood
{
    public FCItemRottenFlesh(int var1)
    {
        super(var1, 3, 0.0F, true, "rottenFlesh");
        this.SetIncreasedFoodPoisoningEffect();
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
        return FCBetterThanWolves.fcBlockRottenFlesh.blockID;
    }
}
