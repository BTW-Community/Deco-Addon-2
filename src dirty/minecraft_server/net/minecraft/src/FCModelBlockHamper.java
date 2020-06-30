package net.minecraft.src;

public class FCModelBlockHamper extends FCModelBlock
{
    public static final double m_dBaseHeight = 0.8125D;
    public static final double m_dBaseWidth = 0.875D;
    public static final double m_dBaseDepth = 0.75D;
    public static final double m_dBaseBottomHeight = 0.125D;
    public static final double m_dBaseBottomWidthGap = 0.0625D;
    public static final double m_dBaseHalfWidth = 0.4375D;
    public static final double m_dBaseHalfDepth = 0.375D;
    public static final double m_dLidHeight = 0.1875D;
    public static final double m_dLidWidth = 1.0D;
    public static final double m_dLidDepth = 0.875D;
    public static final double m_dLidHalfWidth = 0.5D;
    public static final double m_dLidHalfDepth = 0.4375D;
    private static final float m_fLidLayerHeight = 0.0625F;
    private static final float m_fLidLayerWidthGap = 0.0625F;
    private static final double m_dLidMinX = 0.0D;
    private static final double m_dLidMaxX = 1.0D;
    private static final double m_dLidMinZ = 0.0625D;
    private static final double m_dLidMaxZ = 0.9375D;
    private static final int m_iNumLidLayers = 3;
    private static final Vec3 m_lidRotationPoint = Vec3.createVectorHelper(0.5D, 0.8125D, 0.875D);
    public FCModelBlock m_lid;
    public AxisAlignedBB m_selectionBox;
    public AxisAlignedBB m_selectionBoxOpen;

    protected void InitModel()
    {
        this.AddBox(0.0625D, 0.125D, 0.125D, 0.9375D, 0.8125D, 0.875D);
        this.AddBox(0.125D, 0.0D, 0.1875D, 0.875D, 0.125D, 0.8125D);
        this.m_lid = new FCModelBlock();

        for (double var1 = 0.0D; var1 < 3.0D; ++var1)
        {
            double var3 = var1 * 0.0625D;
            double var5 = var1 * 0.0625D;
            this.m_lid.AddBox(0.0D + var3, 0.8125D + var5, 0.0625D + var3, 1.0D - var3, 0.875D + var5, 0.9375D - var3);
        }

        this.m_selectionBox = new AxisAlignedBB(0.0625D, 0.125D, 0.125D, 0.9375D, 0.9375D, 0.875D);
        this.m_selectionBoxOpen = new AxisAlignedBB(0.0625D, 0.125D, 0.125D, 0.9375D, 0.8125D, 0.875D);
    }

    public Vec3 GetLidRotationPoint()
    {
        return m_lidRotationPoint;
    }
}
