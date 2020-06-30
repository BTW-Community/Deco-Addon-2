package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCTileEntityBasketWickerRenderer extends FCTileEntityBasketRenderer
{
    private final float m_fStorageItemMaxHeight = 0.25F;

    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        super.renderTileEntityAt(var1, var2, var4, var6, var8);
        FCTileEntityBasketWicker var9 = (FCTileEntityBasketWicker)var1;
        this.RenderStorageStack(var9, var2, var4, var6, var8);
    }

    private void RenderStorageStack(FCTileEntityBasketWicker var1, double var2, double var4, double var6, float var8)
    {
        ItemStack var9 = var1.GetStorageStack();

        if (var9 != null && var1.m_fLidOpenRatio > 0.01F)
        {
            int var10 = var1.worldObj.getBlockMetadata(var1.xCoord, var1.yCoord, var1.zCoord);
            EntityItem var11 = new EntityItem(var1.worldObj, 0.0D, 0.0D, 0.0D, var9);
            var11.hoverStart = 0.0F;
            GL11.glPushMatrix();
            float var12 = 0.25F * this.GetCurrentOpenRatio(var1, var8);
            GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + var12, (float)var6 + 0.5F);

            if (RenderManager.instance.options.fancyGraphics)
            {
                float var13 = this.ConvertFacingToYaw(FCBetterThanWolves.fcBlockBasketWicker.GetFacing(var10));
                GL11.glRotatef(var13, 0.0F, 1.0F, 0.0F);
            }

            if (this.DoesStackRenderAsBlock(var9) && var9.stackSize > 4)
            {
                GL11.glTranslatef(0.0F, 0.0F, -0.15F);
            }

            RenderManager.instance.renderEntityWithPosYaw(var11, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
            GL11.glPopMatrix();
        }
    }

    private float ConvertFacingToYaw(int var1)
    {
        float var2 = 0.0F;

        if (var1 == 3)
        {
            var2 = 180.0F;
        }
        else if (var1 == 4)
        {
            var2 = 90.0F;
        }
        else if (var1 == 5)
        {
            var2 = 270.0F;
        }

        return var2;
    }

    private boolean DoesStackRenderAsBlock(ItemStack var1)
    {
        return var1.getItemSpriteNumber() == 0 && Block.blocksList[var1.itemID] != null && Block.blocksList[var1.itemID].DoesItemRenderAsBlock(var1.getItemDamage());
    }
}
