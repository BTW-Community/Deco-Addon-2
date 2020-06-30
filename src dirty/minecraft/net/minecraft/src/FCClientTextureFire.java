package net.minecraft.src;

import java.nio.ByteBuffer;
import java.util.List;

public class FCClientTextureFire extends TextureStitched
{
    ByteBuffer m_frameBuffer;
    int m_iBufferWidth;
    int m_iBufferHeight;
    int m_iBufferPixelSize;
    int m_iFireAnimationIndex;
    FCClientAnimationFire m_FireAnimation = null;

    public FCClientTextureFire(String var1, int var2)
    {
        super(var1);
        this.m_iFireAnimationIndex = var2;
    }

    public void init(Texture var1, List var2, int var3, int var4, int var5, int var6, boolean var7)
    {
        super.init(var1, var2, var3, var4, var5, var6, var7);
        this.m_iBufferWidth = ((Texture)this.textureList.get(0)).getWidth();
        this.m_iBufferHeight = ((Texture)this.textureList.get(0)).getHeight();
        this.m_iBufferPixelSize = this.m_iBufferWidth * this.m_iBufferHeight;
        this.m_frameBuffer = ByteBuffer.allocateDirect(this.m_iBufferPixelSize * 4);
        this.m_FireAnimation = FCClientAnimationFire.m_InstanceArray[this.m_iFireAnimationIndex];

        if (this.m_FireAnimation == null)
        {
            this.m_FireAnimation = new FCClientAnimationFire(this.m_iFireAnimationIndex, this.m_iBufferWidth, this.m_iBufferHeight);
        }
    }

    public void updateAnimation()
    {
        this.frameCounter = 0;

        if (this.m_FireAnimation != null)
        {
            this.m_FireAnimation.CopyRegularFireFrameToByteBuffer(this.m_frameBuffer, this.m_iBufferPixelSize);
        }

        this.textureSheet.UploadByteBufferToGPU(this.originX, this.originY, this.m_frameBuffer, this.m_iBufferWidth, this.m_iBufferHeight);
    }

    public boolean IsProcedurallyAnimated()
    {
        return true;
    }
}
