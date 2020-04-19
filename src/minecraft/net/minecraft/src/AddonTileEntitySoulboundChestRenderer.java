package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class AddonTileEntitySoulboundChestRenderer extends TileEntitySpecialRenderer
{
    /** The Ender Chest Chest's model. */
    private ModelChest theEnderChestModel = new ModelChest();

    /**
     * Helps to render Ender Chest.
     */
    public void renderSoulboundChest(AddonTileEntitySoulboundChest tileEntity, double par2, double par4, double par6, float par8)
    {
        int var9 = 0;

        if (tileEntity.func_70309_m())
        {
            var9 = tileEntity.getBlockMetadata();
        }

        this.bindTextureByName("/item/ginger_soulboundChest.png");
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        short var10 = 0;

        if (var9 == 2)
        {
            var10 = 180;
        }

        if (var9 == 3)
        {
            var10 = 0;
        }

        if (var9 == 4)
        {
            var10 = 90;
        }

        if (var9 == 5)
        {
            var10 = -90;
        }

        GL11.glRotatef((float)var10, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        float var11 = tileEntity.prevLidAngle + (tileEntity.lidAngle - tileEntity.prevLidAngle) * par8;
        var11 = 1.0F - var11;
        var11 = 1.0F - var11 * var11 * var11;
        this.theEnderChestModel.chestLid.rotateAngleX = -(var11 * (float)Math.PI / 2.0F);
        this.theEnderChestModel.renderAll();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderSoulboundChest((AddonTileEntitySoulboundChest)par1TileEntity, par2, par4, par6, par8);
    }
}
