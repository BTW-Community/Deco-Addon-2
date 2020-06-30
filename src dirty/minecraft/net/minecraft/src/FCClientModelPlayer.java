package net.minecraft.src;

public class FCClientModelPlayer extends ModelBiped
{
    public FCClientModelPlayer() {}

    public FCClientModelPlayer(float var1)
    {
        super(var1);
    }

    public FCClientModelPlayer(float var1, float var2, int var3, int var4)
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
                this.bipedBody.RenderWithScaleToBaseModel(var7, 1.125F, 1.0F, 1.625F);
            }
            else if (var9 == 2)
            {
                this.bipedBody.RenderWithScaleToBaseModel(var7, 1.25F, 1.0F, 2.25F);
            }
            else if (var9 == 3)
            {
                this.bipedBody.RenderWithScaleToBaseModel(var7, 1.375F, 1.0F, 2.875F);
            }
            else
            {
                this.bipedBody.RenderWithScaleToBaseModel(var7, 1.5F, 1.0F, 3.5F);
            }

            this.bipedRightArm.render(var7);
            this.bipedLeftArm.render(var7);
            this.bipedRightLeg.render(var7);
            this.bipedLeftLeg.render(var7);
            this.bipedHeadwear.render(var7);
        }
    }
}
