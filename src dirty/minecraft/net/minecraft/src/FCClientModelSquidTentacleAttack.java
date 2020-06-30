package net.minecraft.src;

public class FCClientModelSquidTentacleAttack extends ModelBase
{
    public ModelRenderer m_modelRenderer = new ModelRenderer(this, 48, 0);

    public FCClientModelSquidTentacleAttack()
    {
        this.m_modelRenderer.addBox(-1.0F, 0.0F, -1.0F, 2, 16, 2);
        this.m_modelRenderer.setRotationPoint(0.0F, 7.6F, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        this.setRotationAngles(0.0F, 0.0F, 0.0F, var5, var6, var7, var1);
        this.m_modelRenderer.RenderWithScaleToBaseModel(var7, var2, var3, var4);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7)
    {
        super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
        this.m_modelRenderer.rotateAngleY = var4 / (180F / (float)Math.PI);
        this.m_modelRenderer.rotateAngleX = var5 / (180F / (float)Math.PI);
    }
}
