package net.minecraft.src;

public class FCClientModelWindMillVerticalShafts extends ModelBase
{
    public ModelRenderer[] m_Components = new ModelRenderer[8];
    private final int m_iNumBlades = 8;
    private final int m_iNumComponents = 8;
    private final float m_fLocalPi = (float)Math.PI;
    private final float m_fShaftOffsetFromCenter = 70.4F;
    private final int m_iShaftLength = 108;
    private final float m_fShaftHalfLength = 54.0F;
    private final int m_iShaftWidth = 4;
    private final float m_fHalfShaftWidth = 2.0F;

    public FCClientModelWindMillVerticalShafts()
    {
        for (int var1 = 0; var1 < 8; ++var1)
        {
            this.m_Components[var1] = new ModelRenderer(this, 0, 0);
            this.m_Components[var1].setTextureSize(16, 16);
            this.m_Components[var1].addBox(68.4F, -54.0F, -2.0F, 4, 108, 4);
            this.m_Components[var1].setRotationPoint(0.0F, 0.0F, 0.0F);
            this.m_Components[var1].rotateAngleY = ((float)Math.PI * 2F) * (float)var1 / 8.0F;
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
        for (int var8 = 0; var8 < 8; ++var8)
        {
            this.m_Components[var8].render(var6);
        }
    }
}
