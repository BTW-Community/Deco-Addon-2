package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderCow extends RenderLiving
{
    FCClientModelCowUdder m_ModelUdder = new FCClientModelCowUdder();

    public RenderCow(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
        this.setRenderPassModel(this.m_ModelUdder);
    }

    public void renderCow(EntityCow par1EntityCow, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityCow, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderCow((EntityCow)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderCow((EntityCow)par1Entity, par2, par4, par6, par8, par9);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving var1, int var2, float var3)
    {
        if (var2 == 0 && ((FCEntityCow)var1).GotMilk())
        {
            this.loadTexture("/btwmodtex/cow_udder.png");
            return 1;
        }
        else
        {
            return -1;
        }
    }

    public void RenderKickAttackDebug(FCEntityCow var1, double var2, double var4, double var6, float var8, float var9)
    {
        Vec3 var10 = var1.ComputeKickAttackCenter();
        double var13 = var4 + (double)(var1.height / 2.0F);
        double var17 = var10.xCoord - var1.posX + var2;
        double var19 = var10.yCoord - var1.posY + var4;
        double var21 = var10.zCoord - var1.posZ + var6;
        Tessellator var23 = Tessellator.instance;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        var23.startDrawing(3);

        if (var1.m_iKickAttackInProgressCounter >= 0)
        {
            var23.setColorOpaque_I(16711680);
        }
        else
        {
            var23.setColorOpaque_I(0);
        }

        var23.addVertex(var2, var13, var6);
        var23.addVertex(var17, var19, var21);
        var23.draw();
        var23.startDrawing(3);

        if (var1.m_iKickAttackInProgressCounter >= 0)
        {
            var23.setColorOpaque_I(16776960);
        }
        else
        {
            var23.setColorOpaque_I(16777215);
        }

        var23.addVertex(var17 + 1.375D, var19, var21);
        var23.addVertex(var17 - 1.375D, var19, var21);
        var23.draw();
        var23.startDrawing(3);

        if (var1.m_iKickAttackInProgressCounter >= 0)
        {
            var23.setColorOpaque_I(16776960);
        }
        else
        {
            var23.setColorOpaque_I(16777215);
        }

        var23.addVertex(var17, var19, var21 + 1.375D);
        var23.addVertex(var17, var19, var21 - 1.375D);
        var23.draw();
        var23.startDrawing(3);

        if (var1.m_iKickAttackInProgressCounter >= 0)
        {
            var23.setColorOpaque_I(16776960);
        }
        else
        {
            var23.setColorOpaque_I(16777215);
        }

        var23.addVertex(var17, var19 + 1.0D, var21);
        var23.addVertex(var17, var19 - 1.0D, var21);
        var23.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
}
