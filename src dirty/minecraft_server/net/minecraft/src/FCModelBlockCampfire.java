package net.minecraft.src;

public class FCModelBlockCampfire extends FCModelBlock
{
    private static final float m_fLogThickness = 0.125F;
    private static final float m_fMinorBorderWidth = 0.0625F;
    private static final float m_fMajorBorderWidth = 0.0F;
    private static final float m_fLayerWidthOffset = 0.0625F;
    private static final float m_fLayerLengthOffset = 0.0625F;
    private static final int m_iNumLayers = 4;
    public FCModelBlock m_modelInSnow;

    protected void InitModel()
    {
        this.m_modelInSnow = new FCModelBlock();

        for (int var1 = 0; var1 < 4; ++var1)
        {
            float var2 = (float)var1;
            float var3 = var2 * 0.125F;
            float var4 = var3 + 0.125F;
            float var5 = 0.0F;
            float var6 = 0.0F;

            if (var1 > 1)
            {
                var6 = 0.0625F * (var2 - 1.0F);
            }

            var5 = 0.0625F * var2;
            float var7 = 0.0625F + var5;
            float var8 = var7 + 0.125F;
            float var9 = 0.0F + var6;
            float var10 = 1.0F - var9;

            if ((var1 & 1) == 0)
            {
                this.AddBox((double)var9, (double)var3, (double)var7, (double)var10, (double)var4, (double)var8);
                this.AddBox((double)var9, (double)var3, (double)(1.0F - var8), (double)var10, (double)var4, (double)(1.0F - var7));

                if (var1 != 0)
                {
                    this.m_modelInSnow.AddBox((double)var9, (double)var3, (double)var7, (double)var10, (double)var4, (double)var8);
                    this.m_modelInSnow.AddBox((double)var9, (double)var3, (double)(1.0F - var8), (double)var10, (double)var4, (double)(1.0F - var7));
                }
            }
            else
            {
                this.AddBox((double)var7, (double)var3, (double)var9, (double)var8, (double)var4, (double)var10);
                this.AddBox((double)(1.0F - var8), (double)var3, (double)var9, (double)(1.0F - var7), (double)var4, (double)var10);
                this.m_modelInSnow.AddBox((double)var7, (double)var3, (double)var9, (double)var8, (double)var4, (double)var10);
                this.m_modelInSnow.AddBox((double)(1.0F - var8), (double)var3, (double)var9, (double)(1.0F - var7), (double)var4, (double)var10);
            }
        }
    }
}
