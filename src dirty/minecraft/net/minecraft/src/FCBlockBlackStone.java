package net.minecraft.src;

public class FCBlockBlackStone extends BlockQuartz
{
    private Icon m_IconChiseled;
    private Icon m_IconLinesSide;
    private Icon m_IconLinesTop;

    public FCBlockBlackStone(int var1)
    {
        super(var1);
        this.setHardness(2.0F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("quartzBlock");
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockBlackStone");
        this.m_IconChiseled = var1.registerIcon("fcBlockBlackStone_chiseled");
        this.m_IconLinesSide = var1.registerIcon("fcBlockBlackStone_lines");
        this.m_IconLinesTop = var1.registerIcon("fcBlockBlackStone_lines_top");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var2 == 1 ? this.m_IconChiseled : (var2 == 2 ? (var1 <= 1 ? this.m_IconLinesTop : this.m_IconLinesSide) : (var2 == 3 ? (var1 >= 4 ? this.m_IconLinesTop : this.m_IconLinesSide) : (var2 == 4 ? (var1 != 2 && var1 != 3 ? this.m_IconLinesSide : this.m_IconLinesTop) : this.blockIcon)));
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderBlockQuartz(this, var2, var3, var4);
    }
}
