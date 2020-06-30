package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientRenderMiningCharge extends Render
{
    private RenderBlocks blockRenderer = new RenderBlocks();

    public FCClientRenderMiningCharge()
    {
        this.shadowSize = 0.5F;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        this.RenderMiningCharge((FCEntityMiningCharge)var1, var2, var4, var6, var8, var9);
    }

    public void RenderMiningCharge(FCEntityMiningCharge var1, double var2, double var4, double var6, float var8, float var9)
    {
        int var10 = var1.m_iFacing;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var2, (float)var4, (float)var6);
        float var11;

        if ((float)var1.m_iFuse - var9 + 1.0F < 10.0F)
        {
            var11 = 1.0F - ((float)var1.m_iFuse - var9 + 1.0F) / 10.0F;

            if (var11 < 0.0F)
            {
                var11 = 0.0F;
            }

            if (var11 > 1.0F)
            {
                var11 = 1.0F;
            }

            var11 *= var11;
            var11 *= var11;
            float var12 = 1.0F + var11 * 0.3F;
            GL11.glScalef(var12, var12, var12);
        }

        var11 = (1.0F - ((float)var1.m_iFuse - var9 + 1.0F) / 100.0F) * 0.8F;
        this.loadTexture("/terrain.png");
        this.RenderMiningChargeBlock(var10, var1.getBrightness(var9));

        if (var1.m_iFuse / 5 % 2 == 0)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var11);
            this.RenderMiningChargeBlock(var10, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }

        GL11.glPopMatrix();
    }

    public void RenderMiningChargeBlock(int var1, float var2)
    {
        FCBlockMiningCharge var3 = (FCBlockMiningCharge)FCBetterThanWolves.fcBlockMiningCharge;
        Tessellator var4 = Tessellator.instance;
        int var5 = var3.getRenderColor(0);
        float var6 = (float)(var5 >> 16 & 255) / 255.0F;
        float var7 = (float)(var5 >> 8 & 255) / 255.0F;
        float var8 = (float)(var5 & 255) / 255.0F;
        GL11.glColor4f(var6 * var2, var7 * var2, var8 * var2, 1.0F);
        this.SetRenderBoundsBasedOnFacing(var1);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        var4.startDrawingQuads();
        var4.setNormal(0.0F, -1.0F, 0.0F);
        this.blockRenderer.renderFaceYNeg(var3, 0.0D, 0.0D, 0.0D, var3.getBlockTextureFromMetadataCustom(0, var1));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 1.0F, 0.0F);
        this.blockRenderer.renderFaceYPos(var3, 0.0D, 0.0D, 0.0D, var3.getBlockTextureFromMetadataCustom(1, var1));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 0.0F, -1.0F);
        this.blockRenderer.renderFaceZNeg(var3, 0.0D, 0.0D, 0.0D, var3.getBlockTextureFromMetadataCustom(2, var1));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 0.0F, 1.0F);
        this.blockRenderer.renderFaceZPos(var3, 0.0D, 0.0D, 0.0D, var3.getBlockTextureFromMetadataCustom(3, var1));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(-1.0F, 0.0F, 0.0F);
        this.blockRenderer.renderFaceXNeg(var3, 0.0D, 0.0D, 0.0D, var3.getBlockTextureFromMetadataCustom(4, var1));
        var4.draw();
        var4.startDrawingQuads();
        var4.setNormal(1.0F, 0.0F, 0.0F);
        this.blockRenderer.renderFaceXPos(var3, 0.0D, 0.0D, 0.0D, var3.getBlockTextureFromMetadataCustom(5, var1));
        var4.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    void SetRenderBoundsBasedOnFacing(int var1)
    {
        FCBlockMiningCharge var2 = (FCBlockMiningCharge)((FCBlockMiningCharge)FCBetterThanWolves.fcBlockMiningCharge);
        double var3 = 0.5D;

        switch (var1)
        {
            case 0:
                this.blockRenderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, var3, 1.0D);
                break;

            case 1:
                this.blockRenderer.setRenderBounds(0.0D, var3, 0.0D, 1.0D, 1.0D, 1.0D);
                break;

            case 2:
                this.blockRenderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, var3);
                break;

            case 3:
                this.blockRenderer.setRenderBounds(0.0D, 0.0D, var3, 1.0D, 1.0D, 1.0D);
                break;

            case 4:
                this.blockRenderer.setRenderBounds(0.0D, 0.0D, 0.0D, var3, 1.0D, 1.0D);
                break;

            default:
                this.blockRenderer.setRenderBounds(var3, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        }
    }
}
