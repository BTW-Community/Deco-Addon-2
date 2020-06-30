package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCTileEntityToolPlacedRenderer extends TileEntitySpecialRenderer
{
    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        FCTileEntityToolPlaced var9 = (FCTileEntityToolPlaced)var1;
        ItemStack var10 = var9.GetToolStack();

        if (var10 != null)
        {
            this.RenderToolStack(var9, var10, var2, var4, var6);
        }
    }

    private void RenderToolStack(FCTileEntityToolPlaced var1, ItemStack var2, double var3, double var5, double var7)
    {
        int var9 = var1.worldObj.getBlockMetadata(var1.xCoord, var1.yCoord, var1.zCoord);
        int var10 = FCBetterThanWolves.fcBlockToolPlaced.GetFacing(var9);
        int var11 = FCBetterThanWolves.fcBlockToolPlaced.GetVerticalOrientation(var9);
        EntityItem var12 = new EntityItem(var1.worldObj, 0.0D, 0.0D, 0.0D, var2);
        FCItemTool var13 = (FCItemTool)var2.getItem();
        var12.getEntityItem().stackSize = 1;
        var12.hoverStart = 0.0F;
        GL11.glPushMatrix();
        float var14 = var13.GetVisualVerticalOffsetAsBlock();
        float var15 = var13.GetVisualHorizontalOffsetAsBlock();
        float var16 = 0.5F;
        float var17 = 0.5F;
        float var18 = 0.5F;

        if (var11 == 2)
        {
            if (var10 == 2)
            {
                var17 = var14;
            }
            else if (var10 == 3)
            {
                var17 = 1.0F - var14;
            }
            else if (var10 == 4)
            {
                var16 = var14;
            }
            else if (var10 == 5)
            {
                var16 = 1.0F - var14;
            }

            var18 = var15;
        }
        else if (var11 == 1)
        {
            var18 = 1.0F - var14;

            if (var10 == 2)
            {
                var17 = var15;
            }
            else if (var10 == 3)
            {
                var17 = 1.0F - var15;
            }
            else if (var10 == 4)
            {
                var16 = var15;
            }
            else if (var10 == 5)
            {
                var16 = 1.0F - var15;
            }
        }
        else
        {
            var18 = var14;

            if (var10 == 2)
            {
                var17 = 1.0F - var15;
            }
            else if (var10 == 3)
            {
                var17 = var15;
            }
            else if (var10 == 4)
            {
                var16 = 1.0F - var15;
            }
            else if (var10 == 5)
            {
                var16 = var15;
            }
        }

        GL11.glTranslatef((float)var3 + var16, (float)var5 + var18, (float)var7 + var17);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        float var19 = 0.0F;
        float var20 = 0.0F;

        if (var10 == 2)
        {
            var19 = 90.0F;
        }

        if (var10 == 3)
        {
            var19 = 270.0F;
        }
        else if (var10 == 4)
        {
            var19 = 180.0F;
        }
        else if (var10 == 5)
        {
            var19 = 0.0F;
        }

        if (var11 == 0)
        {
            var20 = 180.0F;
        }
        else if (var11 == 2)
        {
            var20 = 270.0F;
        }

        var20 += var13.GetVisualRollOffsetAsBlock();
        GL11.glRotatef(var19, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(var20, 0.0F, 0.0F, 1.0F);
        RenderItem.m_bForceFancyItemRender = true;
        RenderManager.instance.renderEntityWithPosYaw(var12, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
        RenderItem.m_bForceFancyItemRender = false;
        GL11.glPopMatrix();
    }
}
