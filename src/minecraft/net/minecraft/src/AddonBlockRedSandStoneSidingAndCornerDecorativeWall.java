package net.minecraft.src;

public class AddonBlockRedSandStoneSidingAndCornerDecorativeWall extends AddonBlockSidingAndCornerDecorativeWall
{
    private Icon[] m_IconBySideArray = new Icon[6];

    protected AddonBlockRedSandStoneSidingAndCornerDecorativeWall(int var1)
    {
        super(var1, Material.rock, "ginger_redSandstoneDecorative_top", 0.8F, 1.34F, Block.soundStoneFootstep, "redSandStoneSiding", "Red Sandstone");
        this.SetPicksEffectiveOn();
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_IconBySideArray[0] = var1.registerIcon("ginger_redSandstoneDecorative_bottom");
        this.m_IconBySideArray[1] = var1.registerIcon("ginger_redSandstoneDecorative_top");
        Icon var2 = var1.registerIcon("ginger_redSandstoneDecorative_side");
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
        return this.m_IconBySideArray[var1];
    }
}