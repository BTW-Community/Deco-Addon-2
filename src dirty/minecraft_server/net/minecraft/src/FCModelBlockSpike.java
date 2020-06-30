package net.minecraft.src;

public class FCModelBlockSpike extends FCModelBlock
{
    protected static final double m_dShaftWidth = 0.0625D;
    protected static final double m_dShaftHalfWidth = 0.03125D;
    protected static final double m_dBaseWidth = 0.25D;
    protected static final double m_dBaseHalfWidth = 0.125D;
    protected static final double m_dBaseHeight = 0.125D;
    protected static final double m_dBaseHalfHeight = 0.0625D;
    protected static final double m_dBallWidth = 0.1875D;
    protected static final double m_dBallHalfWidth = 0.09375D;
    protected static final double m_dBallVeticalOffset = 0.625D;
    protected static final double m_dCandleHolderWidth = 0.25D;
    protected static final double m_dCandleHolderHalfWidth = 0.125D;
    protected static final double m_dCandleHolderHeight = 0.0625D;
    protected static final double m_dCandleHolderHalfHeight = 0.03125D;
    protected static final double m_dCandleHolderVeticalOffset = 0.9375D;
    protected static final double m_dCenterBraceWidth = 0.1875D;
    protected static final double m_dCenterBraceHalfWidth = 0.09375D;
    protected static final double m_dCenterBraceHeight = 0.25D;
    protected static final double m_dCenterBraceHalfHeight = 0.125D;
    protected static final double m_dCenterBraceVeticalOffset = 0.375D;
    protected static final double m_dSideSupportLength = 0.5D;
    protected static final double m_dSideSupportWidth = 0.0625D;
    protected static final double m_dSideSupportHalfWidth = 0.03125D;
    protected static final double m_dSideSupportHeight = 0.125D;
    protected static final double m_dSideSupportHalfHeight = 0.0625D;
    protected static final double m_dSideSupportVeticalOffset = 0.4375D;
    protected static final double m_dBoundingBoxWidth = 0.25D;
    protected static final double m_dBoundingBoxHalfWidth = 0.125D;
    public FCModelBlock m_modelBase;
    public FCModelBlock m_modelHolder;
    public FCModelBlock m_modelBall;
    public FCModelBlock m_modelCenterBrace;
    public FCModelBlock m_modelSideSupport;
    public AxisAlignedBB m_boxCollisionCenter;
    public AxisAlignedBB m_boxCollisionStrut;

    protected void InitModel()
    {
        this.AddBox(0.46875D, 0.0D, 0.46875D, 0.53125D, 1.0D, 0.53125D);
        this.m_modelBase = new FCModelBlock();
        this.m_modelBase.AddBox(0.375D, 0.0D, 0.375D, 0.625D, 0.125D, 0.625D);
        this.m_modelHolder = new FCModelBlock();
        this.m_modelHolder.AddBox(0.375D, 0.9375D, 0.375D, 0.625D, 1.0D, 0.625D);
        this.m_modelBall = new FCModelBlock();
        this.m_modelBall.AddBox(0.40625D, 0.625D, 0.40625D, 0.59375D, 0.8125D, 0.59375D);
        this.m_modelCenterBrace = new FCModelBlock();
        this.m_modelCenterBrace.AddBox(0.40625D, 0.375D, 0.40625D, 0.59375D, 0.625D, 0.59375D);
        this.m_modelSideSupport = new FCModelBlock();
        this.m_modelSideSupport.AddBox(0.46875D, 0.4375D, 0.0D, 0.53125D, 0.5625D, 0.5D);
        this.m_boxCollisionCenter = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
        this.m_boxCollisionStrut = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 0.625D);
    }
}
