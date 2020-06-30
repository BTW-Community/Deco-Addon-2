package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class FCClientRenderUrn extends Render
{
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        Tessellator var10 = Tessellator.instance;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var2, (float)var4, (float)var6);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        this.loadTexture("/gui/items.png");
        FCEntityUrn var11 = (FCEntityUrn)var1;
        Icon var12 = Item.itemsList[var11.m_iItemShiftedIndex].itemIcon;
        double var13 = (double)var12.getMinU();
        double var15 = (double)var12.getMaxU();
        double var17 = (double)var12.getMinV();
        double var19 = (double)var12.getMaxV();
        float var21 = 1.0F;
        float var22 = 0.5F;
        float var23 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        var10.startDrawingQuads();
        var10.setNormal(0.0F, 1.0F, 0.0F);
        var10.addVertexWithUV((double)(0.0F - var22), (double)(0.0F - var23), 0.0D, var13, var19);
        var10.addVertexWithUV((double)(var21 - var22), (double)(0.0F - var23), 0.0D, var15, var19);
        var10.addVertexWithUV((double)(var21 - var22), (double)(1.0F - var23), 0.0D, var15, var17);
        var10.addVertexWithUV((double)(0.0F - var22), (double)(1.0F - var23), 0.0D, var13, var17);
        var10.draw();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }
}
