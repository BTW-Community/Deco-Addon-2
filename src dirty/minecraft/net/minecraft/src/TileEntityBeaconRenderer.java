package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class TileEntityBeaconRenderer extends TileEntitySpecialRenderer
{
    /**
     * Render a beacon tile entity.
     */
    public void renderTileEntityBeaconAt(TileEntityBeacon par1TileEntityBeacon, double par2, double par4, double par6, float par8)
    {
        float var9 = par1TileEntityBeacon.func_82125_v_();

        if (var9 > 0.0F)
        {
            Tessellator var10 = Tessellator.instance;

            if (par1TileEntityBeacon.IsHomeBeacon())
            {
                this.bindTextureByName("/btwmodtex/fcBeam.png");
            }
            else
            {
                this.bindTextureByName("/misc/beam.png");
            }

            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDepthMask(true);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            float var11 = (float)par1TileEntityBeacon.getWorldObj().getTotalWorldTime() + par8;
            float var12 = -var11 * 0.2F - (float)MathHelper.floor_float(-var11 * 0.1F);
            byte var13 = 1;
            double var14 = (double)var11 * 0.025D * (1.0D - (double)(var13 & 1) * 2.5D);
            var10.startDrawingQuads();
            var10.setColorRGBA(255, 255, 255, 32);
            double var16 = (double)var13 * 0.2D;

            if (par1TileEntityBeacon.IsHomeBeacon())
            {
                var16 *= 1.5D;
            }

            double var18 = 0.5D + Math.cos(var14 + 2.356194490192345D) * var16;
            double var20 = 0.5D + Math.sin(var14 + 2.356194490192345D) * var16;
            double var22 = 0.5D + Math.cos(var14 + (Math.PI / 4D)) * var16;
            double var24 = 0.5D + Math.sin(var14 + (Math.PI / 4D)) * var16;
            double var26 = 0.5D + Math.cos(var14 + 3.9269908169872414D) * var16;
            double var28 = 0.5D + Math.sin(var14 + 3.9269908169872414D) * var16;
            double var30 = 0.5D + Math.cos(var14 + 5.497787143782138D) * var16;
            double var32 = 0.5D + Math.sin(var14 + 5.497787143782138D) * var16;
            double var34 = (double)(256.0F * var9);
            double var36 = 0.0D;
            double var38 = 1.0D;
            double var40 = (double)(-1.0F + var12);
            double var42 = (double)(256.0F * var9) * (0.5D / var16) + var40;
            var10.addVertexWithUV(par2 + var18, par4 + var34, par6 + var20, var38, var42);
            var10.addVertexWithUV(par2 + var18, par4, par6 + var20, var38, var40);
            var10.addVertexWithUV(par2 + var22, par4, par6 + var24, var36, var40);
            var10.addVertexWithUV(par2 + var22, par4 + var34, par6 + var24, var36, var42);
            var10.addVertexWithUV(par2 + var30, par4 + var34, par6 + var32, var38, var42);
            var10.addVertexWithUV(par2 + var30, par4, par6 + var32, var38, var40);
            var10.addVertexWithUV(par2 + var26, par4, par6 + var28, var36, var40);
            var10.addVertexWithUV(par2 + var26, par4 + var34, par6 + var28, var36, var42);
            var10.addVertexWithUV(par2 + var22, par4 + var34, par6 + var24, var38, var42);
            var10.addVertexWithUV(par2 + var22, par4, par6 + var24, var38, var40);
            var10.addVertexWithUV(par2 + var30, par4, par6 + var32, var36, var40);
            var10.addVertexWithUV(par2 + var30, par4 + var34, par6 + var32, var36, var42);
            var10.addVertexWithUV(par2 + var26, par4 + var34, par6 + var28, var38, var42);
            var10.addVertexWithUV(par2 + var26, par4, par6 + var28, var38, var40);
            var10.addVertexWithUV(par2 + var18, par4, par6 + var20, var36, var40);
            var10.addVertexWithUV(par2 + var18, par4 + var34, par6 + var20, var36, var42);
            var10.draw();
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDepthMask(false);
            var10.startDrawingQuads();
            var10.setColorRGBA(255, 255, 255, 32);
            double var44 = 0.2D;
            double var46 = 0.2D;
            double var48 = 0.8D;
            double var50 = 0.2D;
            double var52 = 0.2D;
            double var54 = 0.8D;
            double var56 = 0.8D;
            double var58 = 0.8D;

            if (par1TileEntityBeacon.IsHomeBeacon())
            {
                var44 = 0.0D;
                var46 = 0.0D;
                var48 = 1.0D;
                var50 = 0.0D;
                var52 = 0.0D;
                var54 = 1.0D;
                var56 = 1.0D;
                var58 = 1.0D;
            }

            double var60 = (double)(256.0F * var9);
            double var62 = 0.0D;
            double var64 = 1.0D;
            double var66 = (double)(-1.0F + var12);
            double var68 = (double)(256.0F * var9) + var66;
            var10.addVertexWithUV(par2 + var44, par4 + var60, par6 + var46, var64, var68);
            var10.addVertexWithUV(par2 + var44, par4, par6 + var46, var64, var66);
            var10.addVertexWithUV(par2 + var48, par4, par6 + var50, var62, var66);
            var10.addVertexWithUV(par2 + var48, par4 + var60, par6 + var50, var62, var68);
            var10.addVertexWithUV(par2 + var56, par4 + var60, par6 + var58, var64, var68);
            var10.addVertexWithUV(par2 + var56, par4, par6 + var58, var64, var66);
            var10.addVertexWithUV(par2 + var52, par4, par6 + var54, var62, var66);
            var10.addVertexWithUV(par2 + var52, par4 + var60, par6 + var54, var62, var68);
            var10.addVertexWithUV(par2 + var48, par4 + var60, par6 + var50, var64, var68);
            var10.addVertexWithUV(par2 + var48, par4, par6 + var50, var64, var66);
            var10.addVertexWithUV(par2 + var56, par4, par6 + var58, var62, var66);
            var10.addVertexWithUV(par2 + var56, par4 + var60, par6 + var58, var62, var68);
            var10.addVertexWithUV(par2 + var52, par4 + var60, par6 + var54, var64, var68);
            var10.addVertexWithUV(par2 + var52, par4, par6 + var54, var64, var66);
            var10.addVertexWithUV(par2 + var44, par4, par6 + var46, var62, var66);
            var10.addVertexWithUV(par2 + var44, par4 + var60, par6 + var46, var62, var68);
            var10.draw();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDepthMask(true);
        }
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityBeaconAt((TileEntityBeacon)par1TileEntity, par2, par4, par6, par8);
    }
}
