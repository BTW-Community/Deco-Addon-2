package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientRenderMovingAnchor extends Render
{
    private RenderBlocks localRenderBlocks = new RenderBlocks();

    public FCClientRenderMovingAnchor()
    {
        this.shadowSize = 0.0F;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        World var10 = var1.worldObj;
        this.localRenderBlocks.blockAccess = var10;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var2, (float)var4, (float)var6);
        GL11.glDisable(GL11.GL_LIGHTING);
        int var11 = MathHelper.floor_double(var1.posX);
        int var12 = MathHelper.floor_double(var1.posY);
        int var13 = MathHelper.floor_double(var1.posZ);
        this.loadTexture("/terrain.png");
        Block var14 = FCBetterThanWolves.fcAnchor;
        double var15 = 0.5D;
        double var17 = 0.5D;
        double var19 = FCBlockAnchor.m_dAnchorBaseHeight;
        this.localRenderBlocks.setRenderBounds(0.5D - var17, 0.0D, 0.5D - var15, 0.5D + var17, var19, 0.5D + var15);
        FCClientUtilsRender.RenderMovingBlockWithMetadata(this.localRenderBlocks, var14, var10, var11, var12, var13, 1);
        var15 = 0.125D;
        var17 = 0.125D;
        var19 = 0.25D;
        this.localRenderBlocks.setRenderBounds(0.5D - var17, FCBlockAnchor.m_dAnchorBaseHeight, 0.5D - var15, 0.5D + var17, FCBlockAnchor.m_dAnchorBaseHeight + var19, 0.5D + var15);
        FCClientUtilsRender.RenderMovingBlockWithTexture(this.localRenderBlocks, var14, var10, var11, var12, var13, ((FCBlockAnchor)FCBetterThanWolves.fcAnchor).m_IconNub);

        if (var10.getBlockId(var11, var12, var13) != FCBetterThanWolves.fcRopeBlock.blockID)
        {
            var15 = 0.06237500160932541D;
            var17 = 0.06237500160932541D;
            double var21 = 1.0D - (var1.posY - (double)var12);
            this.localRenderBlocks.setRenderBounds(0.5D - var17, FCBlockAnchor.m_dAnchorBaseHeight, 0.5D - var15, 0.5D + var17, (double)((float)(var21 + 1.0D)), 0.5D + var15);
            FCClientUtilsRender.RenderMovingBlockWithTexture(this.localRenderBlocks, var14, var10, var11, var12, var13, ((FCBlockRope)FCBetterThanWolves.fcRopeBlock).blockIcon);
        }

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
