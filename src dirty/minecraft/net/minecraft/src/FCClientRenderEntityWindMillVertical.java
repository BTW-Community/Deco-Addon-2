package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientRenderEntityWindMillVertical extends Render
{
    protected FCClientModelWindMillVerticalShafts m_ModelShafts;
    protected FCClientModelWindMillVerticalSails m_ModelSails;
    protected FCClientModelWindMillVerticalFrame m_ModelFrame;

    public FCClientRenderEntityWindMillVertical()
    {
        this.shadowSize = 0.0F;
        this.m_ModelShafts = new FCClientModelWindMillVerticalShafts();
        this.m_ModelSails = new FCClientModelWindMillVerticalSails();
        this.m_ModelFrame = new FCClientModelWindMillVerticalFrame();
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        int var10 = MathHelper.floor_double(var1.posX);
        int var11 = MathHelper.floor_double(var1.posY);
        int var12 = MathHelper.floor_double(var1.posZ);

        if (var1.worldObj.checkChunksExist(var10 - 16, var11 - 16, var12 - 16, var10 + 16, var11 + 16, var12 + 16))
        {
            FCEntityWindMillVertical var13 = (FCEntityWindMillVertical)var1;
            float var14 = (float)var13.m_iTimeSinceHit - var9;
            float var15 = (float)var13.m_iCurrentDamage - var9;
            float var16 = 0.0F;

            if (var15 < 0.0F)
            {
                var15 = 0.0F;
            }

            if (var15 > 0.0F)
            {
                var16 = MathHelper.sin(var14) * var14 * var15 / 40.0F * (float)var13.m_iRockDirection;
            }

            GL11.glPushMatrix();
            GL11.glTranslatef((float)var2, (float)var4, (float)var6);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glRotatef(var13.m_fRotation, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var16, 0.0F, 1.0F, 0.0F);
            this.loadTexture("/btwmodtex/fcwindmillwoodver.png");
            this.m_ModelShafts.render(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, var13);
            this.loadTexture("/btwmodtex/fcwindmillwoodhrz.png");
            this.m_ModelFrame.render(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, var13);
            this.loadTexture("/btwmodtex/fcwindmillcloth.png");
            this.m_ModelSails.render(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, var13);
            GL11.glPopMatrix();
        }
    }
}
