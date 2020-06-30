package net.minecraft.src;

public class FCModelBlockFence extends FCModelBlock
{
    protected static final double m_dPostWidth = 0.25D;
    protected static final double m_dPostHalfWidth = 0.125D;
    protected static final double m_dItemPostsWidth = 0.25D;
    protected static final double m_dItemPostsHalfWidth = 0.125D;
    protected static final double m_dItemPostsBorderGap = 0.125D;
    protected static final double m_dStrutWidth = 0.125D;
    protected static final double m_dStrutHalfWidth = 0.0625D;
    protected static final double m_dStrutHeight = 0.1875D;
    protected static final double m_dStrutBottomVerticalOffset = 0.375D;
    protected static final double m_dStrutTopVerticalOffset = 0.75D;
    public FCModelBlock m_modelStruts;
    public FCModelBlock m_modelItem;
    public AxisAlignedBB m_boxCollisionCenter;
    public AxisAlignedBB m_boxCollisionStruts;
    public AxisAlignedBB m_boxBoundsCenter;

    protected void InitModel()
    {
        this.AddBox(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
        this.m_modelStruts = new FCModelBlock();
        this.m_modelStruts.AddBox(0.4375D, 0.375D, 0.0D, 0.5625D, 0.5625D, 0.5D);
        this.m_modelStruts.AddBox(0.4375D, 0.75D, 0.0D, 0.5625D, 0.9375D, 0.5D);
        this.m_modelItem = new FCModelBlock();
        this.m_modelItem.AddBox(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 0.25D);
        this.m_modelItem.AddBox(0.375D, 0.0D, 0.75D, 0.625D, 1.0D, 1.0D);
        this.m_modelItem.AddBox(0.4375D, 0.375D, -0.125D, 0.5625D, 0.5625D, 1.125D);
        this.m_modelItem.AddBox(0.4375D, 0.75D, -0.125D, 0.5625D, 0.9375D, 1.125D);
        this.m_boxCollisionCenter = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.5D, 0.625D);
        this.m_boxCollisionStruts = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 0.5D);
        this.m_boxBoundsCenter = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
    }

    public void RenderAsItemBlock(RenderBlocks var1, Block var2, int var3)
    {
        this.m_modelItem.RenderAsItemBlock(var1, var2, var3);
    }
}
