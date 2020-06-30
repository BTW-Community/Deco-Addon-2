package net.minecraft.src;

public class FCModelBlockLogSpike extends FCModelBlock
{
    private static final float m_fRimWidth = 0.0625F;
    private static final float m_fLayerHeight = 0.125F;
    private static final float m_fFirstLayerHeight = 0.1875F;
    private static final float m_fLayerWidthGap = 0.0625F;
    private static final float m_fSelectionHeight = 0.1875F;
    private static final float m_fSelectionWidthGap = 0.0625F;
    public static final AxisAlignedBB m_boxSelection = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.1875D, 0.9375D);

    protected void InitModel()
    {
        this.AddBox(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.1875D, 0.9375D);

        for (int var1 = 1; var1 <= 6; ++var1)
        {
            float var2 = 0.0625F + 0.0625F * (float)var1;
            float var3 = 0.1875F + 0.125F * (float)(var1 - 1);
            this.AddBox((double)var2, (double)var3, (double)var2, (double)(1.0F - var2), (double)(var3 + 0.125F), (double)(1.0F - var2));
        }
    }
}
