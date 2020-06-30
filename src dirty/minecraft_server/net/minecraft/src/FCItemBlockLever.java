package net.minecraft.src;

public class FCItemBlockLever extends ItemBlock
{
    public FCItemBlockLever(int var1)
    {
        super(var1);
    }

    public int GetTargetFacingPlacedByBlockDispenser(int var1)
    {
        return 1;
    }
}
