package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCTileEntityCampfireRenderer extends TileEntitySpecialRenderer
{
    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        FCTileEntityCampfire var9 = (FCTileEntityCampfire)var1;
        this.RenderCookStack(var9, var2, var4, var6);
    }

    private void RenderCookStack(FCTileEntityCampfire var1, double var2, double var4, double var6)
    {
        ItemStack var8 = var1.GetCookStack();

        if (var8 != null)
        {
            int var9 = var1.worldObj.getBlockMetadata(var1.xCoord, var1.yCoord, var1.zCoord);
            boolean var10 = FCBetterThanWolves.fcBlockCampfireUnlit.GetIsIAligned(var9);
            EntityItem var11 = new EntityItem(var1.worldObj, 0.0D, 0.0D, 0.0D, var8);
            var11.getEntityItem().stackSize = 1;
            var11.hoverStart = 0.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + 0.5625F, (float)var6 + 0.5F);

            if (!var10 && RenderManager.instance.options.fancyGraphics)
            {
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            }

            RenderManager.instance.renderEntityWithPosYaw(var11, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
            GL11.glPopMatrix();
        }
    }
}
