package net.minecraft.src;

public class FCClientEntityCindersFX extends EntityFX
{
    private float m_fParticleScale;

    public FCClientEntityCindersFX(World var1, double var2, double var4, double var6)
    {
        super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
        this.motionX *= 1.5D;
        this.motionY *= 2.0D;
        this.motionZ *= 1.5D;
        this.motionY = this.rand.nextDouble() * 0.4D + 0.05D;
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.particleScale *= this.rand.nextFloat() * 2.0F + 0.2F;
        this.m_fParticleScale = this.particleScale;
        this.particleMaxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        this.noClip = false;
        this.setParticleTextureIndex(49);
    }

    public int getBrightnessForRender(float var1)
    {
        float var2 = ((float)this.particleAge + var1) / (float)this.particleMaxAge;
        MathHelper.clamp_float(var2, 0.0F, 1.0F);
        int var3 = super.getBrightnessForRender(var1) >> 16 & 255;
        short var4 = 240;
        return var4 | var3 << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float var1)
    {
        return 1.0F;
    }

    public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        float var8 = ((float)this.particleAge + var2) / (float)this.particleMaxAge;
        this.particleScale = this.m_fParticleScale * (1.0F - var8 * var8);
        super.renderParticle(var1, var2, var3, var4, var5, var6, var7);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        float var1 = (float)this.particleAge / (float)this.particleMaxAge;

        if (this.rand.nextFloat() > var1)
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
        }

        this.motionY -= 0.03D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9990000128746033D;
        this.motionY *= 0.9990000128746033D;
        this.motionZ *= 0.9990000128746033D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}
