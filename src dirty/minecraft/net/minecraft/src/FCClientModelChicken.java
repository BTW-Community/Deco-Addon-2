package net.minecraft.src;

public class FCClientModelChicken extends ModelChicken
{
    private float m_fHeadRotation;

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLiving var1, float var2, float var3, float var4)
    {
        super.setLivingAnimations(var1, var2, var3, var4);
        FCEntityChicken var5 = (FCEntityChicken)var1;

        if (!var5.isChild())
        {
            this.head.rotationPointY = 15.0F + var5.GetGrazeHeadVerticalOffset(var4) * 3.0F;
        }
        else
        {
            this.head.rotationPointY = 15.0F + var5.GetGrazeHeadVerticalOffset(var4) * 1.5F;
        }

        this.bill.rotationPointY = this.chin.rotationPointY = this.head.rotationPointY;
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
        this.head.rotateAngleX = this.bill.rotateAngleX = this.chin.rotateAngleX = this.m_fHeadRotation;
    }
}
