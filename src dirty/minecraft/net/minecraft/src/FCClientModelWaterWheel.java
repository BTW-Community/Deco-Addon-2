package net.minecraft.src;

public class FCClientModelWaterWheel extends ModelBase
{
    public ModelRenderer[] waterWheelComponents = new ModelRenderer[16];
    private final int iNumWaterWheelComponents = 16;
    private final float fLocalPi = (float)Math.PI;
    private final float fStrutDistFromCent = 30.0F;

    public FCClientModelWaterWheel()
    {
        int var1;

        for (var1 = 0; var1 < 8; ++var1)
        {
            this.waterWheelComponents[var1] = new ModelRenderer(this, 0, 0);
            this.waterWheelComponents[var1].addBox(2.5F, -1.0F, -7.0F, 36, 2, 14);
            this.waterWheelComponents[var1].setRotationPoint(0.0F, 0.0F, 0.0F);
            this.waterWheelComponents[var1].rotateAngleZ = (float)Math.PI * (float)var1 / 4.0F;
        }

        for (var1 = 0; var1 < 8; ++var1)
        {
            this.waterWheelComponents[var1 + 8] = new ModelRenderer(this, 0, 0);
            this.waterWheelComponents[var1 + 8].addBox(0.0F, -1.0F, -6.0F, 23, 2, 12);
            float var2 = ((float)Math.PI / 4F) * (float)var1;
            this.waterWheelComponents[var1 + 8].setRotationPoint(30.0F * MathHelper.cos(var2), 30.0F * MathHelper.sin(var2), 0.0F);
            this.waterWheelComponents[var1 + 8].rotateAngleZ = 1.9634956F + ((float)Math.PI / 4F) * (float)var1;
        }
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        for (int var8 = 0; var8 < 16; ++var8)
        {
            this.waterWheelComponents[var8].render(var7);
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {}
}
