package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class FCClientRenderSquid extends RenderLiving
{
    FCClientModelSquidTentacleAttack m_tentacleAttackModel = new FCClientModelSquidTentacleAttack();

    public FCClientRenderSquid()
    {
        super(new FCClientModelSquid(), 0.7F);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLiving var1, float var2)
    {
        FCEntitySquid var3 = (FCEntitySquid)var1;
        return var3.m_fPrevTentacleAngle + (var3.m_fTentacleAngle - var3.m_fPrevTentacleAngle) * var2;
    }

    protected void rotateCorpse(EntityLiving var1, float var2, float var3, float var4)
    {
        FCEntitySquid var5 = (FCEntitySquid)var1;
        float var6 = var5.m_fPrevSquidPitch + (var5.m_fSquidPitch - var5.m_fPrevSquidPitch) * var4;
        float var7 = var5.m_fPrevSquidYaw + (var5.m_fSquidYaw - var5.m_fPrevSquidYaw) * var4;
        GL11.glTranslatef(0.0F, 0.5F, 0.0F);
        GL11.glRotatef(180.0F - var3, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(var6, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(var7, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, -1.2F, 0.0F);
    }

    public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9)
    {
        super.doRenderLiving(var1, var2, var4, var6, var8, var9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        super.doRenderLiving((EntityLiving)var1, var2, var4, var6, var8, var9);
        this.RenderTentacleAttack((FCEntitySquid)var1, var2, var4, var6, var8, var9);
    }

    public void RenderTentacleAttack(FCEntitySquid var1, double var2, double var4, double var6, float var8, float var9)
    {
        int var10 = var1.m_iTentacleAttackInProgressCounter;

        if (var10 > 0)
        {
            float var11 = (float)(var10 - 1) + var9;
            Vec3 var12 = var1.ComputeTentacleAttackTip(var11);

            if (var1.IsHeadCrab())
            {
                var4 -= (double)(var1.height * 2.0F / 3.0F);
            }

            double var15 = var4;

            if (!var1.IsHeadCrab())
            {
                var15 = var4 + (double)(var1.height / 2.0F);
            }

            double var19 = var12.xCoord - var1.posX + var2;
            double var21 = var12.yCoord - var1.posY + var4;
            double var23 = var12.zCoord - var1.posZ + var6;
            double var25 = var19 - var2;
            double var27 = var21 - var15;
            double var29 = var23 - var6;
            double var31 = (double)MathHelper.sqrt_double(var25 * var25 + var27 * var27 + var29 * var29);
            double var33 = (double)MathHelper.sqrt_double(var25 * var25 + var29 * var29);
            Tessellator var35 = Tessellator.instance;
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glTranslatef((float)var2, (float)var4, (float)var6);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            this.loadTexture("/mob/squid.png");
            float var36 = (float)(Math.atan2(var25, var29) * 180.0D / Math.PI);
            float var37 = (float)(Math.atan2(var33, var27) * 180.0D / Math.PI);
            float var38 = (float)(1.0D - 0.6D * var1.GetAttackProgressSin(var11));

            if (var38 > 1.0F)
            {
                var38 = 1.0F;
            }

            float var39 = (float)var31;
            this.m_tentacleAttackModel.render(var1, var38, var39, var38, var36, var37, 0.0625F);
            GL11.glPopMatrix();
        }
    }
}
