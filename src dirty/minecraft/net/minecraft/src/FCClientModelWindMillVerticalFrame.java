package net.minecraft.src;

public class FCClientModelWindMillVerticalFrame extends ModelBase
{
    public ModelRenderer[] m_Components = new ModelRenderer[32];
    private final int m_iNumBlades = 8;
    private final int m_iNumComponents = 32;
    private final float m_fLocalPi = (float)Math.PI;
    private final float m_fSpokesOffsetFromCenter = 2.0F;
    private final int m_iSpokesLength = 67;
    private final float m_fHalfSpokesLength = 33.5F;
    private final int m_iSpokesWidth = 2;
    private final float m_fHalfSpokesWidth = 1.0F;
    private final float m_fSpokesVerticalOffset = 51.9F;
    private final float m_fRimOffsetFromCenter = 65.9F;
    private final int m_iRimSegmentLength = 52;
    private final float m_fHalfRimSegmentLength = 26.0F;

    public FCClientModelWindMillVerticalFrame()
    {
        for (int var1 = 0; var1 < 8; ++var1)
        {
            this.m_Components[var1] = new ModelRenderer(this, 0, 0);
            this.m_Components[var1].setTextureSize(16, 16);
            this.m_Components[var1].addBox(2.0F, -52.9F, -1.0F, 67, 2, 2);
            this.m_Components[var1].setRotationPoint(0.0F, 0.0F, 0.0F);
            this.m_Components[var1].rotateAngleY = ((float)Math.PI * 2F) * (float)var1 / 8.0F;
            this.m_Components[var1 + 8] = new ModelRenderer(this, 0, 0);
            this.m_Components[var1 + 8].setTextureSize(16, 16);
            this.m_Components[var1 + 8].addBox(2.0F, 50.9F, -1.0F, 67, 2, 2);
            this.m_Components[var1 + 8].setRotationPoint(0.0F, 0.0F, 0.0F);
            this.m_Components[var1 + 8].rotateAngleY = ((float)Math.PI * 2F) * (float)var1 / 8.0F;
            this.m_Components[var1 + 16] = new ModelRenderer(this, 0, 0);
            this.m_Components[var1 + 16].setTextureSize(16, 16);
            this.m_Components[var1 + 16].addBox(64.9F, -52.9F, -26.0F, 2, 2, 52);
            this.m_Components[var1 + 16].setRotationPoint(0.0F, 0.0F, 0.0F);
            this.m_Components[var1 + 16].rotateAngleY = ((float)Math.PI * 2F) * (float)var1 / 8.0F + 0.39269912F;
            this.m_Components[var1 + 24] = new ModelRenderer(this, 0, 0);
            this.m_Components[var1 + 24].setTextureSize(16, 16);
            this.m_Components[var1 + 24].addBox(64.9F, 50.9F, -26.0F, 2, 2, 52);
            this.m_Components[var1 + 24].setRotationPoint(0.0F, 0.0F, 0.0F);
            this.m_Components[var1 + 24].rotateAngleY = ((float)Math.PI * 2F) * (float)var1 / 8.0F + 0.39269912F;
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {}

    public void render(float var1, float var2, float var3, float var4, float var5, float var6, FCEntityWindMillVertical var7)
    {
        for (int var8 = 0; var8 < 32; ++var8)
        {
            this.m_Components[var8].render(var6);
        }
    }
}
