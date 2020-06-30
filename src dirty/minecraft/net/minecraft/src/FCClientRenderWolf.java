package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientRenderWolf extends RenderWolf
{
    public FCClientRenderWolf(ModelBase var1, ModelBase var2, float var3)
    {
        super(var1, var2, var3);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving var1, int var2, float var3)
    {
        return this.RenderGlowingEyes((FCEntityWolf)var1, var2) ? 1 : super.shouldRenderPass(var1, var2, var3);
    }

    private boolean RenderGlowingEyes(FCEntityWolf var1, int var2)
    {
        if (var2 == 2 && var1.AreEyesGlowing())
        {
            this.loadTexture("/btwmodtex/fcWolfNothingToWorryAbout.png");
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            GL11.glDisable(GL11.GL_LIGHTING);

            if (var1.isInvisible())
            {
                GL11.glDepthMask(false);
            }
            else
            {
                GL11.glDepthMask(true);
            }

            char var3 = 61680;
            int var4 = var3 % 65536;
            int var5 = var3 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var4 / 1.0F, (float)var5 / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            return true;
        }
        else
        {
            return false;
        }
    }
}
