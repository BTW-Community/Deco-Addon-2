package net.minecraft.src;

public class FCBlockStairsWhiteStone extends FCBlockStairs
{
    private Icon m_IconWhiteCobble;

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

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockWhiteStone");
        this.m_IconWhiteCobble = var1.registerIcon("fcBlockWhiteCobble");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.GetIsCobbleFromMetadata(var2) ? this.m_IconWhiteCobble : this.blockIcon;
    }
}
