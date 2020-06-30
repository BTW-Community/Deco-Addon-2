package net.minecraft.src;

public class FCModelBlockChunkOre extends FCModelBlock
{
    protected static final double m_dBaseWidth = 0.1875D;
    protected static final double m_dBaseHalfWidth = 0.09375D;
    protected static final double m_dBaseHeight = 0.03125D;
    protected static final double m_dCenterWidth = 0.25D;
    protected static final double m_dCenterHalfWidth = 0.125D;
    protected static final double m_dCenterHeight = 0.1875D;
    protected static final double m_dTopWidth = 0.125D;
    protected static final double m_dTopHalfWidth = 0.0625D;
    protected static final double m_dTopHeight = 0.03125D;
    protected static final double m_dTopVerticalOffset = 0.21875D;
    protected static final double m_dTopXOffset = 0.0D;
    protected static final double m_dTopZOffset = 0.03125D;
    protected static final double m_dFringeXWidth = 0.3125D;
    protected static final double m_dFringeXHalfWidth = 0.15625D;
    protected static final double m_dFringeXDepth = 0.1875D;
    protected static final double m_dFringeXHalfDepth = 0.09375D;
    protected static final double m_dFringeXHeight = 0.09375D;
    protected static final double m_dFringeXVerticalOffset = 0.0625D;
    protected static final double m_dFringeZWidth = 0.1875D;
    protected static final double m_dFringeZHalfWidth = 0.09375D;
    protected static final double m_dFringeZDepth = 0.3125D;
    protected static final double m_dFringeZHalfDepth = 0.15625D;
    protected static final double m_dFringeZHeight = 0.09375D;
    protected static final double m_dFringeZVerticalOffset = 0.09375D;
    public static final double m_dBoundingBoxWidth = 0.25D;
    public static final double m_dBoundingBoxHalfWidth = 0.125D;
    public static final double m_dBoundingBoxHeight = 0.1875D;
    public static final double m_dBoundingBoxVerticalOffset = 0.03125D;

    protected void InitModel()
    {
        super.InitModel();
        this.AddBox(0.40625D, 0.0D, 0.40625D, 0.59375D, 0.03125D, 0.59375D);
        this.AddBox(0.375D, 0.03125D, 0.375D, 0.625D, 0.21875D, 0.625D);
        this.AddBox(0.4375D, 0.21875D, 0.46875D, 0.5625D, 0.25D, 0.59375D);
        this.AddBox(0.34375D, 0.0625D, 0.40625D, 0.65625D, 0.15625D, 0.59375D);
        this.AddBox(0.40625D, 0.09375D, 0.34375D, 0.59375D, 0.1875D, 0.65625D);
    }
}
