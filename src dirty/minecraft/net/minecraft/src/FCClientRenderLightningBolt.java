package net.minecraft.src;

import java.util.Random;
import org.lwjgl.opengl.GL11;

public class FCClientRenderLightningBolt extends Render
{
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        this.doRenderLightningBolt((FCEntityLightningBolt)var1, var2, var4, var6, var8, var9);
    }

    public void doRenderLightningBolt(FCEntityLightningBolt var1, double var2, double var4, double var6, float var8, float var9)
    {
        Tessellator var10 = Tessellator.instance;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        double[] var11 = new double[8];
        double[] var12 = new double[8];
        double var13 = 0.0D;
        double var15 = 0.0D;
        Random var17 = new Random(var1.m_lRenderSeed);
        int var18;

        for (var18 = 7; var18 >= 0; --var18)
        {
            var11[var18] = var13;
            var12[var18] = var15;
            var13 += (double)(var17.nextInt(11) - 5);
            var15 += (double)(var17.nextInt(11) - 5);
        }

        for (var18 = 0; var18 < 4; ++var18)
        {
            Random var19 = new Random(var1.m_lRenderSeed);

            for (int var20 = 0; var20 < 3; ++var20)
            {
                int var21 = 7;
                int var22 = 0;

                if (var20 > 0)
                {
                    var21 = 7 - var20;
                }

                if (var20 > 0)
                {
                    var22 = var21 - 2;
                }

                double var23 = var11[var21] - var13;
                double var25 = var12[var21] - var15;

                for (int var27 = var21; var27 >= var22; --var27)
                {
                    double var28 = var23;
                    double var30 = var25;

                    if (var20 == 0)
                    {
                        var23 += (double)(var19.nextInt(11) - 5);
                        var25 += (double)(var19.nextInt(11) - 5);
                    }
                    else
                    {
                        var23 += (double)(var19.nextInt(31) - 15);
                        var25 += (double)(var19.nextInt(31) - 15);
                    }

                    var10.startDrawing(5);
                    float var32 = 0.5F;
                    var10.setColorRGBA_F(0.9F * var32, 0.9F * var32, 1.0F * var32, 0.3F);
                    double var33 = 0.1D + (double)var18 * 0.2D;

                    if (var20 == 0)
                    {
                        var33 *= (double)var27 * 0.1D + 1.0D;
                    }

                    double var35 = 0.1D + (double)var18 * 0.2D;

                    if (var20 == 0)
                    {
                        var35 *= (double)(var27 - 1) * 0.1D + 1.0D;
                    }

                    for (int var37 = 0; var37 < 5; ++var37)
                    {
                        double var38 = var2 + 0.5D - var33;
                        double var40 = var6 + 0.5D - var33;

                        if (var37 == 1 || var37 == 2)
                        {
                            var38 += var33 * 2.0D;
                        }

                        if (var37 == 2 || var37 == 3)
                        {
                            var40 += var33 * 2.0D;
                        }

                        double var42 = var2 + 0.5D - var35;
                        double var44 = var6 + 0.5D - var35;

                        if (var37 == 1 || var37 == 2)
                        {
                            var42 += var35 * 2.0D;
                        }

                        if (var37 == 2 || var37 == 3)
                        {
                            var44 += var35 * 2.0D;
                        }

                        var10.addVertex(var42 + var23, var4 + (double)(var27 * 16), var44 + var25);
                        var10.addVertex(var38 + var28, var4 + (double)((var27 + 1) * 16), var40 + var30);
                    }

                    var10.draw();
                }
            }
        }

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
}
