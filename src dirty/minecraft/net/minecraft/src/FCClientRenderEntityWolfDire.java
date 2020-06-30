package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientRenderEntityWolfDire extends RenderLiving
{
    private static final float m_fShadowSize = 0.5F;

    public FCClientRenderEntityWolfDire(ModelBase var1, ModelBase var2)
    {
        super(var1, 0.5F);
        this.setRenderPassModel(var1);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLiving var1, float var2)
    {
        return this.getTailRotation((FCEntityWolfDire)var1, var2);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        super.doRender(var1, var2, var4, var6, var8, var9);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving var1, float var2)
    {
        GL11.glScalef(1.5F, 1.5F, 1.5F);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving var1, int var2, float var3)
    {
        return this.renderEyes((FCEntityWolfDire)var1, var2);
    }

    protected float getTailRotation(FCEntityWolfDire var1, float var2)
    {
        return var1.getTailRotation();
    }

    protected int renderEyes(FCEntityWolfDire var1, int var2)
    {
        if (var2 != 0)
        {
            return -1;
        }
        else
        {
            this.loadTexture("/btwmodtex/fcWolfDireEyes.png");
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
            return 1;
        }
    }
}
