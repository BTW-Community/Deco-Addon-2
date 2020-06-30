package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCTileEntityFurnaceBrickRenderer extends TileEntitySpecialRenderer
{
    private static final double m_dCookingItemVisualOffset = 0.25D;

    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        FCTileEntityFurnaceBrick var9 = (FCTileEntityFurnaceBrick)var1;
        ItemStack var10 = var9.GetCookStack();

        if (var10 != null)
        {
            this.RenderCookStack(var9, var2, var4, var6, var10, var8);
        }
    }

    private void RenderCookStack(FCTileEntityFurnaceBrick var1, double var2, double var4, double var6, ItemStack var8, float var9)
    {
        int var10 = var1.worldObj.getBlockMetadata(var1.xCoord, var1.yCoord, var1.zCoord);
        EntityItem var11 = new EntityItem(var1.worldObj, 0.0D, 0.0D, 0.0D, var8);
        var11.getEntityItem().stackSize = 1;
        var11.hoverStart = 0.0F;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + 0.40625F, (float)var6 + 0.5F);
        GL11.glScalef(0.7F, 0.7F, 0.7F);
        int var12 = var10 & 7;
        Vec3 var13 = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
        float var14 = 0.0F;

        if (var12 == 2)
        {
            var14 = 0.0F;
            var13.zCoord = -0.25D;
        }
        else if (var12 == 3)
        {
            var14 = 180.0F;
            var13.zCoord = 0.25D;
        }
        else if (var12 == 4)
        {
            var14 = 90.0F;
            var13.xCoord = -0.25D;
        }
        else if (var12 == 5)
        {
            var14 = 270.0F;
            var13.xCoord = 0.25D;
        }

        GL11.glTranslatef((float)var13.xCoord, (float)var13.yCoord, (float)var13.zCoord);

        if (RenderManager.instance.options.fancyGraphics)
        {
            GL11.glRotatef(var14, 0.0F, 1.0F, 0.0F);
        }

        int var15 = this.GetItemRenderBrightnessForBlockToFacing(var1, var12);
        int var16 = var15 % 65536;
        int var17 = var15 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var16 / 1.0F, (float)var17 / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderManager.instance.renderEntityWithPosYaw(var11, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
        GL11.glPopMatrix();
    }

    protected int GetItemRenderBrightnessForBlockToFacing(FCTileEntityFurnaceBrick var1, int var2)
    {
        FCUtilsBlockPos var3 = new FCUtilsBlockPos(var1.xCoord, var1.yCoord, var1.zCoord, var2);
        return var1.worldObj.blockExists(var3.i, var3.j, var3.k) ? var1.worldObj.getLightBrightnessForSkyBlocks(var3.i, var3.j, var3.k, 0) : 0;
    }
}
