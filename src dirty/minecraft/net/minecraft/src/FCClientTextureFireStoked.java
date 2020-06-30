package net.minecraft.src;

public class FCClientTextureFireStoked extends FCClientTextureFire
{
    public FCClientTextureFireStoked(String var1, int var2)
    {
        super(var1, var2);
        this.m_iFireAnimationIndex = var2;
    }

    public void updateAnimation()
    {
        this.frameCounter = 0;

        if (this.m_FireAnimation != null)
        {
            this.m_FireAnimation.CopyStokedFireFrameToByteBuffer(this.m_frameBuffer, this.m_iBufferPixelSize);
        }

        this.textureSheet.UploadByteBufferToGPU(this.originX, this.originY, this.m_frameBuffer, this.m_iBufferWidth, this.m_iBufferHeight);
    }

    public boolean IsProcedurallyAnimated()
    {
        return true;
    }
}
