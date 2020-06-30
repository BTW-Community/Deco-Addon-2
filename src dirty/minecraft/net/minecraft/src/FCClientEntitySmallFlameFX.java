package net.minecraft.src;

public class FCClientEntitySmallFlameFX extends EntityFX
{
    private float m_fStartScale;

    public FCClientEntitySmallFlameFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12)
    {
        super(var1, var2, var4, var6, var8, var10, var12);
        this.motionX = this.motionX * 0.009999999776482582D + var8;
        this.motionY = this.motionY * 0.009999999776482582D + var10;
        this.motionZ = this.motionZ * 0.009999999776482582D + var12;
        this.particleScale *= 0.5F;
        this.m_fStartScale = this.particleScale;
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
        this.noClip = true;
        this.setParticleTextureIndex(48);
    }

    public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        float var8 = this.GetDecay(var2);
        this.particleScale = this.m_fStartScale * (1.0F - var8 * var8 * 0.5F);
        super.renderParticle(var1, var2, var3, var4, var5, var6, var7);
    }

    public int getBrightnessForRender(float var1)
    {
        float var2 = this.GetDecay(var1);
        int var3 = super.getBrightnessForRender(var1);
        int var4 = var3 & 255;
        int var5 = var3 >> 16 & 255;
        var4 += (int)(var2 * 15.0F * 16.0F);

        if (var4 > 240)
        {
            var4 = 240;
        }

        return var4 | var5 << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float var1)
    {
        float var2 = this.GetDecay(var1);
        float var3 = super.getBrightness(var1);
        return var3 * var2 + (1.0F - var2);
    }

    private float GetDecay(float var1)
    {
        float var2 = ((float)this.particleAge + var1) / (float)this.particleMaxAge;
        return MathHelper.clamp_float(var2, 0.0F, 1.0F);
    }
}
