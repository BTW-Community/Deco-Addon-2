package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientModelWindMill extends ModelBase
{
    public ModelRenderer[] windMillComponents = new ModelRenderer[8];
    private final int iNumWindMillComponents = 8;
    private final float fLocalPi = (float)Math.PI;
    private final float fBladeOffsetFromCenter = 15.0F;
    private final int iBladeLength = 84;
    private final int iBladeWidth = 16;
    private final float fShaftOffsetFromCenter = 2.5F;
    private final int iShaftLength = 97;
    private final int iShaftWidth = 4;

    public FCClientModelWindMill()
    {
        int var1;

        for (var1 = 0; var1 < 4; ++var1)
        {
            this.windMillComponents[var1] = new ModelRenderer(this, 0, 0);
            this.windMillComponents[var1].addBox(2.5F, -2.0F, -2.0F, 97, 4, 4);
            this.windMillComponents[var1].setRotationPoint(0.0F, 0.0F, 0.0F);
            this.windMillComponents[var1].rotateAngleZ = (float)Math.PI * (float)(var1 - 4) / 2.0F;
        }

        for (var1 = 4; var1 < 8; ++var1)
        {
            this.windMillComponents[var1] = new ModelRenderer(this, 0, 15);
            this.windMillComponents[var1].addBox(15.0F, 1.75F, 1.0F, 84, 16, 1);
            this.windMillComponents[var1].setRotationPoint(0.0F, 0.0F, 0.0F);
            this.windMillComponents[var1].rotateAngleX = -0.26179942F;
            this.windMillComponents[var1].rotateAngleZ = (float)Math.PI * (float)var1 / 2.0F;
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {}

    public void render(float var1, float var2, float var3, float var4, float var5, float var6, FCEntityWindMill var7)
    {
        for (int var8 = 0; var8 < 4; ++var8)
        {
            this.windMillComponents[var8].render(var6);
        }

        float var11 = 1.0F;

        for (int var9 = 4; var9 < 8; ++var9)
        {
            int var10 = var7.getBladeColor(var9 - 4);
            GL11.glColor3f(var11 * FCEntitySheep.fleeceColorTable[var10][0], var11 * FCEntitySheep.fleeceColorTable[var10][1], var11 * FCEntitySheep.fleeceColorTable[var10][2]);
            this.windMillComponents[var9].render(var6);
        }
    }
}
