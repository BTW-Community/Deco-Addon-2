package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderWither extends RenderLiving
{
    private int field_82419_a;

    public RenderWither()
    {
        super(new ModelWither(), 1.0F);
        this.field_82419_a = ((ModelWither)this.mainModel).func_82903_a();
    }

    public void func_82418_a(EntityWither par1EntityWither, double par2, double par4, double par6, float par8, float par9)
    {
        BossStatus.func_82824_a(par1EntityWither, true);
        int var10 = ((ModelWither)this.mainModel).func_82903_a();

        if (var10 != this.field_82419_a)
        {
            this.field_82419_a = var10;
            this.mainModel = new ModelWither();
        }

        super.doRenderLiving(par1EntityWither, par2, par4, par6, par8, par9);
    }

    protected void func_82415_a(EntityWither par1EntityWither, float par2)
    {
        int var3 = par1EntityWither.func_82212_n();

        if (var3 > 0)
        {
            float var4 = 2.0F - ((float)var3 - par2) / 220.0F * 0.5F;
            GL11.glScalef(var4, var4, var4);
        }
        else
        {
            GL11.glScalef(2.0F, 2.0F, 2.0F);
        }
    }

    protected int func_82417_a(EntityWither par1EntityWither, int par2, float par3)
    {
        if (par1EntityWither.isArmored())
        {
            if (par1EntityWither.isInvisible())
            {
                GL11.glDepthMask(false);
            }
            else
            {
                GL11.glDepthMask(true);
            }

            if (par2 == 1)
            {
                float var4 = (float)par1EntityWither.ticksExisted + par3;
                this.loadTexture("/armor/witherarmor.png");
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                float var5 = MathHelper.cos(var4 * 0.02F) * 3.0F;
                float var6 = var4 * 0.01F;
                GL11.glTranslatef(var5, var6, 0.0F);
                this.setRenderPassModel(this.mainModel);
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_BLEND);
                float var7 = 0.5F;
                GL11.glColor4f(var7, var7, var7, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                GL11.glTranslatef(0.0F, -0.01F, 0.0F);
                GL11.glScalef(1.1F, 1.1F, 1.1F);
                return 1;
            }

            if (par2 == 2)
            {
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
            }
        }

        return -1;
    }

    protected int func_82416_b(EntityWither par1EntityWither, int par2, float par3)
    {
        return -1;
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
    {
        this.func_82415_a((EntityWither)par1EntityLiving, par2);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.func_82417_a((EntityWither)par1EntityLiving, par2, par3);
    }

    protected int inheritRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.func_82416_b((EntityWither)par1EntityLiving, par2, par3);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82418_a((EntityWither)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82418_a((EntityWither)par1Entity, par2, par4, par6, par8, par9);
    }
}
