package net.minecraft.src;

import java.nio.ByteBuffer;
import java.util.List;

public class FCClientTextureVesselXP extends TextureStitched
{
    ByteBuffer m_frameBuffer;
    float m_fColorMultiplierRed;
    float m_fColorMultiplierGreen;
    float m_fColorMultiplierBlue;
    int m_iBufferWidth;
    int m_iBufferHeight;
    int m_iBufferPixelSize;

    public FCClientTextureVesselXP(String var1)
    {
        super(var1);
    }

    public void init(Texture var1, List var2, int var3, int var4, int var5, int var6, boolean var7)
    {
        super.init(var1, var2, var3, var4, var5, var6, var7);
        this.m_fColorMultiplierRed = 0.0F;
        this.m_fColorMultiplierGreen = 0.0F;
        this.m_fColorMultiplierBlue = 0.0F;
        this.m_iBufferWidth = ((Texture)this.textureList.get(0)).getWidth();
        this.m_iBufferHeight = ((Texture)this.textureList.get(0)).getHeight();
        this.m_iBufferPixelSize = this.m_iBufferWidth * this.m_iBufferHeight;
        this.m_frameBuffer = ByteBuffer.allocateDirect(this.m_iBufferPixelSize * 4);
    }

    public void updateAnimation()
    {
        ++this.frameCounter;
        float var1 = (float)(this.frameCounter % 360) * (float)Math.PI / 180.0F;
        this.m_fColorMultiplierRed = (MathHelper.sin(var1) * 0.5F + 0.5F) * 0.75F + 0.25F;
        this.CopyFrameToBufferWithColorMultiplier(this.m_frameBuffer, this.m_iBufferPixelSize);
        this.textureSheet.UploadByteBufferToGPU(this.originX, this.originY, this.m_frameBuffer, this.m_iBufferWidth, this.m_iBufferHeight);
    }

    private void CopyFrameToBufferWithColorMultiplier(ByteBuffer var1, int var2)
    {
        ByteBuffer var3 = ((Texture)this.textureList.get(0)).getTextureData();

        for (int var4 = 0; var4 < var2; ++var4)
        {
            int var5 = var3.get(var4 * 4 + 0) & 255;
            int var6 = var3.get(var4 * 4 + 1) & 255;
            int var7 = var3.get(var4 * 4 + 2) & 255;
            int var8 = (int)((float)var5 * this.m_fColorMultiplierRed);
            int var9 = (int)((float)var6 * this.m_fColorMultiplierGreen);
            int var10 = (int)((float)var7 * this.m_fColorMultiplierBlue);
            byte var11 = var3.get(var4 * 4 + 3);

            if (var8 > 255 || var9 > 255 || var10 > 255 || var8 < 0 || var9 < 0 || var10 < 0)
            {
                boolean var12 = true;
            }

            var1.put(var4 * 4 + 0, (byte)var8);
            var1.put(var4 * 4 + 1, (byte)var9);
            var1.put(var4 * 4 + 2, (byte)var10);
            var1.put(var4 * 4 + 3, (byte)var11);
        }
    }

    public boolean IsProcedurallyAnimated()
    {
        return true;
    }
}
