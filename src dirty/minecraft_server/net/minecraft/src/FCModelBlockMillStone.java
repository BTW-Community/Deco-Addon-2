package net.minecraft.src;

public class FCModelBlockMillStone extends FCModelBlock
{
    public static final double m_dBaseHeight = 0.5625D;
    public static final double m_dMidMinY = 0.4375D;
    public static final double m_dMidMaxY = 1.0D;
    public static final double m_dMidWidthGap = 0.0625D;
    public static final double m_dTopMinY = 0.6875D;
    public static final double m_dTopHeight = 0.25D;
    public AxisAlignedBB m_boxBase;
    public AxisAlignedBB m_boxSelection;

    protected void InitModel()
    {
        this.m_boxBase = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D);
        this.AddBox(0.0625D, 0.4375D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
        this.AddBox(0.0D, 0.6875D, 0.0D, 1.0D, 0.9375D, 1.0D);
        this.m_boxSelection = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
    }
}
