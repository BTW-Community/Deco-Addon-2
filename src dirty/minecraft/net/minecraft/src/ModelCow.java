package net.minecraft.src;

public class ModelCow extends ModelQuadruped
{
    private float m_fHeadRotation;

    public ModelCow()
    {
        super(12, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
        this.head.setRotationPoint(0.0F, 4.0F, -8.0F);
        this.head.setTextureOffset(22, 0).addBox(-5.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.head.setTextureOffset(22, 0).addBox(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.body = new ModelRenderer(this, 18, 4);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.body.setTextureOffset(52, 0).addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1);
        --this.leg1.rotationPointX;
        ++this.leg2.rotationPointX;
        this.leg1.rotationPointZ += 0.0F;
        this.leg2.rotationPointZ += 0.0F;
        --this.leg3.rotationPointX;
        ++this.leg4.rotationPointX;
        --this.leg3.rotationPointZ;
        --this.leg4.rotationPointZ;
        this.field_78151_h += 2.0F;
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLiving var1, float var2, float var3, float var4)
    {
        super.setLivingAnimations(var1, var2, var3, var4);
        FCEntityCow var5 = (FCEntityCow)var1;
        this.head.rotationPointY = 4.0F + var5.GetGrazeHeadVerticalOffset(var4) * 4.0F;
        this.m_fHeadRotation = var5.GetGrazeHeadRotation(var4);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7)
    {
        super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
        this.AdjustRotationAnglesForKickAttack(var1, var2, var3, var4, var5, var6, var7);
        this.head.rotateAngleX = this.m_fHeadRotation;
    }

    private void AdjustRotationAnglesForKickAttack(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7)
    {
        FCEntityCow var8 = (FCEntityCow)var7;

        if (var8 != null)
        {
            int var9 = var8.m_iKickAttackInProgressCounter;

            if (var9 > 0)
            {
                float var10 = 2.0F;

                if (var8.m_iKickAttackLegUsed == 0)
                {
                    this.leg1.rotateAngleX = MathHelper.cos((float)Math.PI * var10) * 1.4F;
                }
                else
                {
                    this.leg2.rotateAngleX = MathHelper.cos((float)Math.PI * var10) * 1.4F;
                }
            }
        }
    }
}
