package net.minecraft.src;

import java.util.Random;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class FCClientRenderCanvas extends Render
{
    private Random rand = new Random();

    public void func_158_a(FCEntityCanvas var1, double var2, double var4, double var6, float var8, float var9)
    {
        this.rand.setSeed(187L);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var2, (float)var4, (float)var6);
        GL11.glRotatef(var8, 0.0F, 1.0F, 0.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        this.loadTexture("/btwmodtex/btwart01.png");
        FCEnumCanvasArt var10 = var1.m_art;
        float var11 = 0.0625F;
        GL11.glScalef(var11, var11, var11);
        this.func_159_a(var1, var10.m_iSizeX, var10.m_iSizeY, var10.m_iOffsetX, var10.m_iOffsetY);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    private void func_159_a(FCEntityCanvas var1, int var2, int var3, int var4, int var5)
    {
        float var6 = (float)(-var2) / 2.0F;
        float var7 = (float)(-var3) / 2.0F;
        float var8 = -0.5F;
        float var9 = 0.5F;
        float var10 = 0.0F;
        float var11 = var10 + 0.0625F;
        float var12 = 0.8125F;
        float var13 = var12 + 0.0625F;
        float var14 = var10;
        float var15 = var11;
        float var16 = var12 + 0.001953125F;
        float var17 = var12 + 0.001953125F;
        float var18 = var10 + 0.001953125F;
        float var19 = var10 + 0.001953125F;
        float var20 = var12;
        float var21 = var12 + 0.0625F;

        for (int var22 = 0; var22 < var2 / 16; ++var22)
        {
            for (int var23 = 0; var23 < var3 / 16; ++var23)
            {
                float var24 = var6 + (float)((var22 + 1) * 16);
                float var25 = var6 + (float)(var22 * 16);
                float var26 = var7 + (float)((var23 + 1) * 16);
                float var27 = var7 + (float)(var23 * 16);
                this.func_160_a(var1, (var24 + var25) / 2.0F, (var26 + var27) / 2.0F);
                float var28 = (float)(var4 + var2 - var22 * 16) / 256.0F;
                float var29 = (float)(var4 + var2 - (var22 + 1) * 16) / 256.0F;
                float var30 = (float)(var5 + var3 - var23 * 16) / 256.0F;
                float var31 = (float)(var5 + var3 - (var23 + 1) * 16) / 256.0F;
                Tessellator var32 = Tessellator.instance;
                var32.startDrawingQuads();
                var32.setNormal(0.0F, 0.0F, -1.0F);
                var32.addVertexWithUV((double)var24, (double)var27, (double)var8, (double)var29, (double)var30);
                var32.addVertexWithUV((double)var25, (double)var27, (double)var8, (double)var28, (double)var30);
                var32.addVertexWithUV((double)var25, (double)var26, (double)var8, (double)var28, (double)var31);
                var32.addVertexWithUV((double)var24, (double)var26, (double)var8, (double)var29, (double)var31);
                var32.setNormal(0.0F, 0.0F, 1.0F);
                var32.addVertexWithUV((double)var24, (double)var26, (double)var9, (double)var10, (double)var12);
                var32.addVertexWithUV((double)var25, (double)var26, (double)var9, (double)var11, (double)var12);
                var32.addVertexWithUV((double)var25, (double)var27, (double)var9, (double)var11, (double)var13);
                var32.addVertexWithUV((double)var24, (double)var27, (double)var9, (double)var10, (double)var13);
                var32.setNormal(0.0F, 1.0F, 0.0F);
                var32.addVertexWithUV((double)var24, (double)var26, (double)var8, (double)var14, (double)var16);
                var32.addVertexWithUV((double)var25, (double)var26, (double)var8, (double)var15, (double)var16);
                var32.addVertexWithUV((double)var25, (double)var26, (double)var9, (double)var15, (double)var17);
                var32.addVertexWithUV((double)var24, (double)var26, (double)var9, (double)var14, (double)var17);
                var32.setNormal(0.0F, -1.0F, 0.0F);
                var32.addVertexWithUV((double)var24, (double)var27, (double)var9, (double)var14, (double)var16);
                var32.addVertexWithUV((double)var25, (double)var27, (double)var9, (double)var15, (double)var16);
                var32.addVertexWithUV((double)var25, (double)var27, (double)var8, (double)var15, (double)var17);
                var32.addVertexWithUV((double)var24, (double)var27, (double)var8, (double)var14, (double)var17);
                var32.setNormal(-1.0F, 0.0F, 0.0F);
                var32.addVertexWithUV((double)var24, (double)var26, (double)var9, (double)var19, (double)var20);
                var32.addVertexWithUV((double)var24, (double)var27, (double)var9, (double)var19, (double)var21);
                var32.addVertexWithUV((double)var24, (double)var27, (double)var8, (double)var18, (double)var21);
                var32.addVertexWithUV((double)var24, (double)var26, (double)var8, (double)var18, (double)var20);
                var32.setNormal(1.0F, 0.0F, 0.0F);
                var32.addVertexWithUV((double)var25, (double)var26, (double)var8, (double)var19, (double)var20);
                var32.addVertexWithUV((double)var25, (double)var27, (double)var8, (double)var19, (double)var21);
                var32.addVertexWithUV((double)var25, (double)var27, (double)var9, (double)var18, (double)var21);
                var32.addVertexWithUV((double)var25, (double)var26, (double)var9, (double)var18, (double)var20);
                var32.draw();
            }
        }
    }

    private void func_160_a(FCEntityCanvas var1, float var2, float var3)
    {
        int var4 = MathHelper.floor_double(var1.posX);
        int var5 = MathHelper.floor_double(var1.posY + (double)(var3 / 16.0F));
        int var6 = MathHelper.floor_double(var1.posZ);

        if (var1.direction == 0)
        {
            var4 = MathHelper.floor_double(var1.posX + (double)(var2 / 16.0F));
        }

        if (var1.direction == 1)
        {
            var6 = MathHelper.floor_double(var1.posZ - (double)(var2 / 16.0F));
        }

        if (var1.direction == 2)
        {
            var4 = MathHelper.floor_double(var1.posX - (double)(var2 / 16.0F));
        }

        if (var1.direction == 3)
        {
            var6 = MathHelper.floor_double(var1.posZ + (double)(var2 / 16.0F));
        }

        int var7 = this.renderManager.worldObj.getLightBrightnessForSkyBlocks(var4, var5, var6, 0);
        int var8 = var7 % 65536;
        int var9 = var7 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var8, (float)var9);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        this.func_158_a((FCEntityCanvas)var1, var2, var4, var6, var8, var9);
    }
}
