package net.minecraft.src;

public class FCModelBlockBucketFull extends FCModelBlockBucket
{
    public static final double m_dContentsHeight = 0.34375D;
    public static final double m_dContentsVerticalOffset = 0.125D;
    public static final double m_dContentsWidth = 0.375D;
    public static final double m_dContentsHalfWidth = 0.1875D;

    protected void InitModel()
    {
        super.InitModel();
        FCUtilsPrimitiveAABBWithBenefits var1 = new FCUtilsPrimitiveAABBWithBenefits(0.3125D, 0.125D, 0.3125D, 0.6875D, 0.46875D, 0.6875D, 4);
        this.AddPrimitive(var1);
    }
}
