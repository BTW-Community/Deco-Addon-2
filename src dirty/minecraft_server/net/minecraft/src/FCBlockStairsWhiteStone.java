package net.minecraft.src;

public class FCBlockStairsWhiteStone extends FCBlockStairs
{
    protected FCBlockStairsWhiteStone(int var1)
    {
        super(var1, Block.stone, 0);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setUnlocalizedName("fcBlockWhiteStoneStairs");
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1 & 8;
    }

    public boolean GetIsCobbleFromMetadata(int var1)
    {
        return (var1 & 8) > 0;
    }
}
