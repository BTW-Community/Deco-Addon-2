package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCTileEntityBasketRenderer extends TileEntitySpecialRenderer
{
    protected RenderBlocks m_localRenderBlocks = new RenderBlocks();

    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        FCTileEntityBasket var9 = (FCTileEntityBasket)var1;
        Block var10 = Block.blocksList[var9.worldObj.getBlockId(var9.xCoord, var9.yCoord, var9.zCoord)];

        if (var10 instanceof FCBlockBasket)
        {
            this.RenderBasketLidAsBlock(var9, (FCBlockBasket)var10, var2, var4, var6, var8);
        }
    }

    private void RenderBasketLidAsBlock(FCTileEntityBasket var1, FCBlockBasket var2, double var3, double var5, double var7, float var9)
    {
        int var10 = var1.worldObj.getBlockMetadata(var1.xCoord, var1.yCoord, var1.zCoord);

        if (var2.GetIsOpen(var10))
        {
            int var11 = var2.GetFacing(var10);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)var3, (float)var5, (float)var7);
            GL11.glDisable(GL11.GL_LIGHTING);
            this.bindTextureByName("/terrain.png");
            this.m_localRenderBlocks.blockAccess = var1.worldObj;
            Tessellator.instance.startDrawingQuads();
            Tessellator.instance.setTranslation(-((double)var1.xCoord), -((double)var1.yCoord), -((double)var1.zCoord));
            FCModelBlock var12 = var2.GetLidModel(var10).MakeTemporaryCopy();
            var12.RotateAroundJToFacing(var11);
            this.m_localRenderBlocks.SetUvRotateTop(var2.ConvertFacingToTopTextureRotation(var11));
            this.m_localRenderBlocks.SetUvRotateBottom(var2.ConvertFacingToBottomTextureRotation(var11));
            var2.m_iOpenLidBrightness = var2.getMixedBrightnessForBlock(var1.worldObj, var1.xCoord, var1.yCoord, var1.zCoord);
            var2.m_bRenderingOpenLid = true;
            var12.RenderAsBlockWithColorMultiplier(this.m_localRenderBlocks, var2, var1.xCoord, var1.yCoord, var1.zCoord);
            var2.m_bRenderingOpenLid = false;
            Tessellator.instance.setTranslation(0.0D, 0.0D, 0.0D);
            float var13 = this.GetCurrentOpenRatio(var1, var9);
            float var14 = var13 * 45.0F;
            Vec3 var15 = Vec3.createVectorHelper(var2.GetLidRotationPoint());
            var15.RotateAsBlockPosAroundJToFacing(var11);
            GL11.glTranslatef((float)var15.xCoord, (float)var15.yCoord, (float)var15.zCoord);

            if (var11 == 2)
            {
                GL11.glRotatef(var14, 1.0F, 0.0F, 0.0F);
            }
            else if (var11 == 3)
            {
                GL11.glRotatef(-var14, 1.0F, 0.0F, 0.0F);
            }
            else if (var11 == 4)
            {
                GL11.glRotatef(-var14, 0.0F, 0.0F, 1.0F);
            }
            else if (var11 == 5)
            {
                GL11.glRotatef(var14, 0.0F, 0.0F, 1.0F);
            }

            GL11.glTranslatef(-((float)var15.xCoord), -((float)var15.yCoord), -((float)var15.zCoord));
            Tessellator.instance.draw();
            this.m_localRenderBlocks.ClearUvRotation();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }

    protected float GetCurrentOpenRatio(FCTileEntityBasket var1, float var2)
    {
        float var3 = var1.m_fPrevLidOpenRatio + (var1.m_fLidOpenRatio - var1.m_fPrevLidOpenRatio) * var2;
        var3 = 1.0F - var3;
        var3 = 1.0F - var3 * var3 * var3;
        return var3;
    }
}
