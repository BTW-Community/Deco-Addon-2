package net.minecraft.src;

public class FCBlockSandstoneMouldingAndDecorative extends FCBlockMouldingAndDecorative
{
    private Icon[] m_IconBySideArray = new Icon[6];

    protected FCBlockSandstoneMouldingAndDecorative(int var1, int var2)
    {
        super(var1, Material.rock, "fcBlockDecorativeSandstone_top", "fcBlockColumnSandstone_side", var2, 0.8F, 1.34F, Block.soundStoneFootstep, "fcSandstoneMoulding");
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_IconBySideArray[0] = var1.registerIcon("fcBlockDecorativeSandstone_bottom");
        this.m_IconBySideArray[1] = var1.registerIcon("fcBlockDecorativeSandstone_top");
        Icon var2 = var1.registerIcon("fcBlockDecorativeSandstone_side");
        this.m_IconBySideArray[2] = var2;
        this.m_IconBySideArray[3] = var2;
        this.m_IconBySideArray[4] = var2;
        this.m_IconBySideArray[5] = var2;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var2 != 12 ? this.m_IconBySideArray[var1] : super.getIcon(var1, var2);
    }
}
