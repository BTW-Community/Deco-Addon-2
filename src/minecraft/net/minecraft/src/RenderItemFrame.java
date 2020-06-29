package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderItemFrame extends Render
{
    private final RenderBlocks renderBlocksInstance = new RenderBlocks();
    private Icon field_94147_f;

    public void updateIcons(IconRegister par1IconRegister)
    {
        this.field_94147_f = par1IconRegister.registerIcon("itemframe_back");
    }

    public void func_82404_a(EntityItemFrame par1EntityItemFrame, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        float var10 = (float)(par1EntityItemFrame.posX - par2) - 0.5F;
        float var11 = (float)(par1EntityItemFrame.posY - par4) - 0.5F;
        float var12 = (float)(par1EntityItemFrame.posZ - par6) - 0.5F;
        int var13 = par1EntityItemFrame.xPosition + Direction.offsetX[par1EntityItemFrame.hangingDirection];
        int var14 = par1EntityItemFrame.yPosition;
        int var15 = par1EntityItemFrame.zPosition + Direction.offsetZ[par1EntityItemFrame.hangingDirection];
        GL11.glTranslatef((float)var13 - var10, (float)var14 - var11, (float)var15 - var12);
        this.renderFrameItemAsBlock(par1EntityItemFrame);
        GL11.glPopMatrix();
    }

    /**
     * Render the item frame's item as a block.
     */
    private void renderFrameItemAsBlock(EntityItemFrame par1EntityItemFrame)
    {
        Boolean var2 = Boolean.valueOf(false);
        Boolean var3 = Boolean.valueOf(false);
        ItemStack var4 = par1EntityItemFrame.getDisplayedItem();
        EntityItem var5 = null;

        if (var4 != null)
        {
            var2 = Boolean.valueOf(true);
            var5 = new EntityItem(par1EntityItemFrame.worldObj, 0.0D, 0.0D, 0.0D, var4);
            var5.getEntityItem().stackSize = 1;
            var5.hoverStart = 0.0F;
            var3 = Boolean.valueOf(var5.getEntityItem().getItem() == Item.map);
        }

        GL11.glPushMatrix();
        this.renderManager.renderEngine.bindTexture("/terrain.png");
        GL11.glRotatef(par1EntityItemFrame.rotationYaw, 0.0F, 1.0F, 0.0F);
        Block var6 = Block.planks;
        float var7 = 0.0625F;
        float var8 = 0.75F;

        if (var3.booleanValue())
        {
            var8 = 1.0F;
        }

        float var9 = var8 / 2.0F;
        GL11.glPushMatrix();
        this.renderBlocksInstance.overrideBlockBounds(0.0D, (double)(0.5F - var9 + var7), (double)(0.5F - var9 + var7), (double)(var7 * 0.5F), (double)(0.5F + var9 - var7), (double)(0.5F + var9 - var7));
        this.renderBlocksInstance.setOverrideBlockTexture(this.field_94147_f);
        this.renderBlocksInstance.renderBlockAsItem(var6, 0, 1.0F);
        this.renderBlocksInstance.clearOverrideBlockTexture();
        this.renderBlocksInstance.unlockBlockBounds();
        GL11.glPopMatrix();
        this.renderBlocksInstance.setOverrideBlockTexture(Block.planks.getIcon(1, 2));
        GL11.glPushMatrix();
        this.renderBlocksInstance.overrideBlockBounds(0.0D, (double)(0.5F - var9), (double)(0.5F - var9), (double)(var7 + 1.0E-4F), (double)(var7 + 0.5F - var9), (double)(0.5F + var9));
        this.renderBlocksInstance.renderBlockAsItem(var6, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        this.renderBlocksInstance.overrideBlockBounds(0.0D, (double)(0.5F + var9 - var7), (double)(0.5F - var9), (double)(var7 + 1.0E-4F), (double)(0.5F + var9), (double)(0.5F + var9));
        this.renderBlocksInstance.renderBlockAsItem(var6, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        this.renderBlocksInstance.overrideBlockBounds(0.0D, (double)(0.5F - var9), (double)(0.5F - var9), (double)var7, (double)(0.5F + var9), (double)(var7 + 0.5F - var9));
        this.renderBlocksInstance.renderBlockAsItem(var6, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        this.renderBlocksInstance.overrideBlockBounds(0.0D, (double)(0.5F - var9), (double)(0.5F + var9 - var7), (double)var7, (double)(0.5F + var9), (double)(0.5F + var9));
        this.renderBlocksInstance.renderBlockAsItem(var6, 0, 1.0F);
        GL11.glPopMatrix();
        this.renderBlocksInstance.unlockBlockBounds();
        this.renderBlocksInstance.clearOverrideBlockTexture();
        GL11.glPopMatrix();

        if (var2.booleanValue() && var5 != null)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.453125F * (float)Direction.offsetX[par1EntityItemFrame.hangingDirection], 0.0F, -0.453125F * (float)Direction.offsetZ[par1EntityItemFrame.hangingDirection]);
            GL11.glRotatef(180.0F + par1EntityItemFrame.rotationYaw, 0.0F, 1.0F, 0.0F);

            if (var3.booleanValue())
            {
                Float var10 = Float.valueOf(-0.015625F);

                if (par1EntityItemFrame.getRotation() % 2 == 0)
                {
                    var10 = Float.valueOf(var10.floatValue() + var7 / 2.0F);
                }

                this.renderManager.renderEngine.bindTexture("/misc/mapbg.png");
                Tessellator var11 = Tessellator.instance;
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(-0.5F, -0.5F, var10.floatValue() - 2.0E-4F);
                GL11.glScalef(0.0078125F, 0.0078125F, 0.0078125F);
                GL11.glNormal3f(0.0F, 0.0F, -1.0F);
                var11.startDrawingQuads();
                byte var12 = 0;
                var11.addVertexWithUV((double)(0 - var12), (double)(128 + var12), 0.0D, 0.0D, 1.0D);
                var11.addVertexWithUV((double)(128 + var12), (double)(128 + var12), 0.0D, 1.0D, 1.0D);
                var11.addVertexWithUV((double)(128 + var12), (double)(0 - var12), 0.0D, 1.0D, 0.0D);
                var11.addVertexWithUV((double)(0 - var12), (double)(0 - var12), 0.0D, 0.0D, 0.0D);
                var11.draw();
                MapData var13 = Item.map.getMapData(var5.getEntityItem(), par1EntityItemFrame.worldObj);

                if (var13 != null)
                {
                    this.renderManager.itemRenderer.mapItemRenderer.renderMap((EntityPlayer)null, this.renderManager.renderEngine, var13);
                }
            }
            else
            {
                GL11.glRotatef((float)(-90 * par1EntityItemFrame.getRotation()), 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(0.0F, -0.18F, 0.0F);
                RenderItem.renderInFrame = true;
                RenderManager.instance.renderEntityWithPosYaw(var5, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                RenderItem.renderInFrame = false;
            }

            GL11.glPopMatrix();
        }
    }

    private void func_82402_b(EntityItemFrame par1EntityItemFrame)
    {
        ItemStack var2 = par1EntityItemFrame.getDisplayedItem();

        if (var2 != null)
        {
            EntityItem var3 = new EntityItem(par1EntityItemFrame.worldObj, 0.0D, 0.0D, 0.0D, var2);
            var3.getEntityItem().stackSize = 1;
            var3.hoverStart = 0.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.453125F * (float)Direction.offsetX[par1EntityItemFrame.hangingDirection], 0.0F, -0.453125F * (float)Direction.offsetZ[par1EntityItemFrame.hangingDirection]);
            GL11.glRotatef(180.0F + par1EntityItemFrame.rotationYaw, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef((float)(-90 * par1EntityItemFrame.getRotation()), 0.0F, 0.0F, 1.0F);

            if (var3.getEntityItem().getItem() == Item.map)
            {
                this.renderManager.renderEngine.bindTexture("/misc/mapbg.png");
                Tessellator tess = Tessellator.instance;
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(-0.5F, -0.5F, 0.0224F);
                GL11.glScalef(0.0078125F, 0.0078125F, 0.0078125F);
                GL11.glNormal3f(0.0F, 0.0F, -1.0F);
                tess.startDrawingQuads();
                tess.addVertexWithUV(0, 128, 0.0D, 0.0D, 1.0D);
                tess.addVertexWithUV(128, 128, 0.0D, 1.0D, 1.0D);
                tess.addVertexWithUV(128, 0, 0.0D, 1.0D, 0.0D);
                tess.addVertexWithUV(0, 0, 0.0D, 0.0D, 0.0D);
                tess.draw();
                MapData var6 = Item.map.getMapData(var3.getEntityItem(), par1EntityItemFrame.worldObj);

                if (var6 != null)
                {
                    this.renderManager.itemRenderer.mapItemRenderer.renderMap((EntityPlayer)null, this.renderManager.renderEngine, var6);
                }
            }
            else
            {
                GL11.glTranslatef(0.0F, -0.18F, 0.0F);
                RenderItem.renderInFrame = true;
                RenderManager.instance.renderEntityWithPosYaw(var3, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                RenderItem.renderInFrame = false;
            }

            GL11.glPopMatrix();
        }
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82404_a((EntityItemFrame)par1Entity, par2, par4, par6, par8, par9);
    }
}
