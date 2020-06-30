package net.minecraft.src;

public class FCModelBlockAnvil extends FCModelBlock
{
    private static final double m_dBaseWidth = 0.75D;
    private static final double m_dBaseHalfWidth = 0.375D;
    private static final double m_dBaseHeight = 0.25D;
    private static final double m_dBaseTopWidth = 0.5D;
    private static final double m_dBaseTopHalfWidth = 0.25D;
    private static final double m_dBaseTopDepth = 0.625D;
    private static final double m_dBaseTopHalfDepth = 0.3125D;
    private static final double m_dBaseTopHeight = 0.0625D;
    private static final double m_dShaftWidth = 0.25D;
    private static final double m_dShaftHalfWidth = 0.125D;
    private static final double m_dShaftDepth = 0.5D;
    private static final double m_dShaftHalfDepth = 0.25D;
    private static final double m_dShaftHeight = 0.3125D;
    private static final double m_dTopWidth = 0.625D;
    private static final double m_dTopHalfWidth = 0.3125D;
    private static final double m_dTopDepth = 1.0D;
    private static final double m_dTopHalfDepth = 0.5D;
    private static final double m_dTopHeight = 0.375D;
    public AxisAlignedBB m_boxSelection;

    protected void InitModel()
    {
        this.AddBox(0.125D, 0.0D, 0.125D, 0.875D, 0.25D, 0.875D);
        double var1 = 0.25D;
        this.AddBox(0.25D, var1, 0.1875D, 0.75D, var1 + 0.0625D, 0.8125D);
        var1 += 0.0625D;
        this.AddBox(0.375D, var1, 0.25D, 0.625D, var1 + 0.3125D, 0.75D);
        var1 += 0.3125D;
        this.AddBox(0.1875D, var1, 0.0D, 0.8125D, var1 + 0.375D, 1.0D);
        this.m_boxSelection = new AxisAlignedBB(0.1875D, 0.625D, 0.0D, 0.8125D, 1.0D, 1.0D);
    }
}
