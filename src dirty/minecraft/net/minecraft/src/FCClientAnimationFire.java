package net.minecraft.src;

import java.nio.ByteBuffer;

public class FCClientAnimationFire
{
    public static FCClientAnimationFire[] m_InstanceArray = new FCClientAnimationFire[] {null, null};
    public int m_iWidth;
    public int m_iHeight;
    public int m_iTextureHeight;
    public int m_iSize;
    protected float[] m_fPreviousIntensities;
    protected float[] m_fCurrentIntensities;
    private float m_fIntensityDecayFactor;
    private float m_fIntensityDecayFactorTop;
    private double m_dDistanceFromCenterIntensityModifier;
    private int m_iCenterRow;
    public final float m_fColorShiftSeperatorBlueToWhite = 0.39F;
    public final float m_fColorShiftSeperatorWhiteToRed = 0.66F;
    public final float m_fInvisiblePixelThresholdTop = 0.87F;
    public final float m_fInvisiblePixelThresholdBottom = 0.001F;

    public FCClientAnimationFire(int var1, int var2, int var3)
    {
        this.m_iWidth = var2;
        this.m_iHeight = var3 * 2;
        this.m_iTextureHeight = var3;
        this.m_iSize = this.m_iWidth * this.m_iHeight;
        this.m_fPreviousIntensities = new float[this.m_iSize];
        this.m_fCurrentIntensities = new float[this.m_iSize];

        for (int var4 = 0; var4 < this.m_iSize; ++var4)
        {
            this.m_fPreviousIntensities[var4] = 0.0F;
            this.m_fCurrentIntensities[var4] = 0.0F;
        }

        this.m_fIntensityDecayFactor = 1.0F + 0.08F * (16.0F / (float)this.m_iTextureHeight);
        this.m_fIntensityDecayFactorTop = 1.0F + 0.07F * (16.0F / (float)this.m_iTextureHeight);
        this.m_dDistanceFromCenterIntensityModifier = 0.123D * (16.0D / (double)this.m_iWidth);
        this.m_iCenterRow = this.m_iWidth / 2;
        m_InstanceArray[var1] = this;
    }

    public void Update()
    {
        this.DriftFireUpwards();
        this.GenerateNewBottomRow();
        this.m_fPreviousIntensities = this.m_fCurrentIntensities;
    }

    private void GenerateNewBottomRow()
    {
        for (int var1 = 0; var1 < this.m_iWidth; ++var1)
        {
            double var2 = (double)(this.m_iCenterRow - Math.abs(var1 - (this.m_iCenterRow - 1)));
            double var4 = Math.random() * Math.random() * Math.random() * 4.0D + Math.random() * 0.10000000149011612D + 0.2D;
            double var6 = var2 * this.m_dDistanceFromCenterIntensityModifier;
            var6 *= var6;
            this.m_fCurrentIntensities[var1 + (this.m_iHeight - 1) * this.m_iWidth] = (float)(var4 + var6);
        }
    }

    private void DriftFireUpwards()
    {
        for (int var1 = 0; var1 < this.m_iWidth; ++var1)
        {
            for (int var2 = 0; var2 < this.m_iHeight - 1; ++var2)
            {
                int var3 = 18;
                float var4 = this.m_fPreviousIntensities[var1 + (var2 + 1) * this.m_iWidth] * (float)var3;

                for (int var5 = var1 - 1; var5 <= var1 + 1; ++var5)
                {
                    for (int var6 = var2; var6 <= var2 + 1; ++var6)
                    {
                        if (var5 >= 0 && var6 >= 0 && var5 < this.m_iWidth && var6 < this.m_iHeight)
                        {
                            var4 += this.m_fPreviousIntensities[var5 + var6 * this.m_iWidth];
                        }

                        ++var3;
                    }
                }

                if (var2 < this.m_iTextureHeight)
                {
                    var4 /= (float)var3 * this.m_fIntensityDecayFactorTop;
                }
                else
                {
                    var4 /= (float)var3 * this.m_fIntensityDecayFactor;
                }

                this.m_fCurrentIntensities[var1 + var2 * this.m_iWidth] = var4;
            }
        }
    }

    public void CopyRegularFireFrameToByteBuffer(ByteBuffer var1, int var2)
    {
        for (int var3 = 0; var3 < var2; ++var3)
        {
            float var4 = this.m_fCurrentIntensities[var3];

            if (var4 > 1.0F)
            {
                var4 = 1.0F;
            }
            else if (var4 < 0.0F)
            {
                var4 = 0.0F;
            }

            float var5 = 1.0F - var4;
            int var6 = 0;
            int var7 = 0;
            int var8 = 0;
            short var9 = 255;

            if (var5 <= 0.87F && var5 >= 0.001F)
            {
                float var10;
                float var11;

                if (var5 < 0.39F)
                {
                    var10 = var5 / 0.39F;
                    var11 = var10 * var10;
                    var6 = (int)(var11 * 255.0F);
                    var7 = (int)(var11 * 255.0F);
                    var8 = (int)(var10 * 100.0F) + 155;
                }
                else if (var5 < 0.66F)
                {
                    var6 = 255;
                    var7 = 255;
                    var8 = 255;
                }
                else
                {
                    var10 = 1.0F - (var5 - 0.66F) / 0.33999997F;
                    var6 = (int)(var10 * 120.0F) + 135;
                    var11 = var10 * var10;
                    var7 = (int)(var11 * 225.0F) + 30;
                    float var12 = var11 * var11;
                    var12 *= var12;
                    var8 = (int)(var12 * 255.0F);
                }
            }
            else
            {
                var9 = 0;
            }

            var1.put(var3 * 4 + 0, (byte)var6);
            var1.put(var3 * 4 + 1, (byte)var7);
            var1.put(var3 * 4 + 2, (byte)var8);
            var1.put(var3 * 4 + 3, (byte)var9);
        }
    }

    public void CopyStokedFireFrameToByteBuffer(ByteBuffer var1, int var2)
    {
        for (int var3 = 0; var3 < var2; ++var3)
        {
            float var4 = this.m_fCurrentIntensities[var3 + this.m_iTextureHeight * this.m_iWidth];

            if (var4 > 1.0F)
            {
                var4 = 1.0F;
            }
            else if (var4 < 0.0F)
            {
                var4 = 0.0F;
            }

            float var5 = 1.0F - var4;
            int var6 = 0;
            int var7 = 0;
            int var8 = 0;
            short var9 = 255;

            if (var5 <= 0.87F && var5 >= 0.001F)
            {
                float var10;
                float var11;

                if (var5 < 0.39F)
                {
                    var10 = var5 / 0.39F;
                    var11 = var10 * var10;
                    var6 = (int)(var11 * 255.0F);
                    var7 = (int)(var11 * 255.0F);
                    var8 = (int)(var10 * 100.0F) + 155;
                }
                else if (var5 < 0.66F)
                {
                    var6 = 255;
                    var7 = 255;
                    var8 = 255;
                }
                else
                {
                    var10 = 1.0F - (var5 - 0.66F) / 0.33999997F;
                    var6 = (int)(var10 * 120.0F) + 135;
                    var11 = var10 * var10;
                    var7 = (int)(var11 * 225.0F) + 30;
                    float var12 = var11 * var11;
                    var12 *= var12;
                    var8 = (int)(var12 * 255.0F);
                }
            }
            else
            {
                var9 = 0;
            }

            var1.put(var3 * 4 + 0, (byte)var6);
            var1.put(var3 * 4 + 1, (byte)var7);
            var1.put(var3 * 4 + 2, (byte)var8);
            var1.put(var3 * 4 + 3, (byte)var9);
        }
    }

    public static void UpdateInstances()
    {
        for (int var0 = 0; var0 < 2; ++var0)
        {
            if (m_InstanceArray[var0] != null)
            {
                m_InstanceArray[var0].Update();
            }
        }
    }
}
