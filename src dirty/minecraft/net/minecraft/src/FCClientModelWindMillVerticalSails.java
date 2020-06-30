package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientModelWindMillVerticalSails extends ModelBase
{
    public ModelRenderer[] m_Components = new ModelRenderer[8];
    private final int m_iNumBlades = 8;
    private final int m_iNumComponents = 8;
    private final float m_fLocalPi = (float)Math.PI;
    private final float m_fSailOffsetFromCenter = 70.9F;
    private final int m_iSailLength = 100;
    private final float m_fHalfSailLength = 50.0F;
    private final int m_iSailWidth = 20;
    private final float m_fHalfSailWidth = 10.0F;
    private final int m_iSailThickness = 1;
    private final float m_fHalfSailThickness = 0.5F;

    public FCClientModelWindMillVerticalSails()
    {
        for (int var1 = 0; var1 < 8; ++var1)
        {
            this.m_Components[var1] = new ModelRenderer(this, 0, 0);
            this.m_Components[var1].setTextureSize(16, 16);
            this.m_Components[var1].addBox(70.4F, -50.0F, -20.0F, 1, 100, 20);
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
        float var8 = 1.0F;

        for (int var9 = 0; var9 < 8; ++var9)
        {
            int var10 = var7.getBladeColor(var9);
            GL11.glColor3f(var8 * FCEntitySheep.fleeceColorTable[var10][0], var8 * FCEntitySheep.fleeceColorTable[var10][1], var8 * FCEntitySheep.fleeceColorTable[var10][2]);
            this.m_Components[var9].render(var6);
        }
    }
}
