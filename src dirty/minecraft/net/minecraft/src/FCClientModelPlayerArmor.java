package net.minecraft.src;

public class FCClientModelPlayerArmor extends ModelBiped
{
    public FCClientModelPlayerArmor() {}

    public FCClientModelPlayerArmor(float var1)
    {
        super(var1);
    }

    public FCClientModelPlayerArmor(float var1, float var2, int var3, int var4)
    {
        super(var1, var2, var3, var4);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        EntityPlayer var8 = (EntityPlayer)var1;
        int var9 = var8.GetFatPenaltyLevel();

        if (var9 == 0)
        {
            super.render(var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            this.setRotationAngles(var2, var3, var4, var5, var6, var7, var1);
            this.bipedHead.render(var7);

            if (var9 == 1)
            {
                this.bipedBody.RenderWithScaleToBaseModel(var7, 1.0625F, 1.0F, 1.4F);
            }
            else if (var9 == 2)
            {
                this.bipedBody.RenderWithScaleToBaseModel(var7, 1.125F, 1.0F, 1.8F);
            }
            else if (var9 == 3)
            {
                this.bipedBody.RenderWithScaleToBaseModel(var7, 1.1875F, 1.0F, 2.2F);
            }
            else
            {
                this.bipedBody.RenderWithScaleToBaseModel(var7, 1.25F, 1.0F, 2.6F);
            }

            this.bipedRightArm.render(var7);
            this.bipedLeftArm.render(var7);
            this.bipedRightLeg.render(var7);
            this.bipedLeftLeg.render(var7);
            this.bipedHeadwear.render(var7);
        }
    }
}
