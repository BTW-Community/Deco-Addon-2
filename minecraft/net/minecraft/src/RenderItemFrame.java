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
            var2 = true;
            var5 = (EntityItem) EntityList.createEntityOfType(EntityItem.class, par1EntityItemFrame.worldObj, 0.0D, 0.0D, 0.0D, var4);
            var5.getEntityItem().stackSize = 1;
            var5.hoverStart = 0.0F;
            var3 = var5.getEntityItem().getItem() == Item.map;
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
            
            if (var4.hasDisplayName()) {
            	System.out.println("Display name");
            	renderLivingLabel(par1EntityItemFrame, var4.getDisplayName(), par1EntityItemFrame.posX, par1EntityItemFrame.posY, par1EntityItemFrame.posZ, 8);
            }
        }
    }

    /**
     * Draws the debug or playername text above a living
     */
    protected void renderLivingLabel(EntityItemFrame itemFrame, String label, double par3, double par5, double par7, int par9)
    {
        double var10 = itemFrame.getDistanceSqToEntity(this.renderManager.livingPlayer);

        if (var10 <= (double)(par9 * par9))
        {
            FontRenderer var12 = this.getFontRendererFromRenderManager();
            float var13 = 1.6F;
            float var14 = 0.016666668F * var13;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)par3 + 0.0F, (float)par5 + itemFrame.height + 0.5F, (float)par7);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-var14, -var14, var14);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(false);

            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            Tessellator var15 = Tessellator.instance;
            byte var16 = 0;

            GL11.glDisable(GL11.GL_TEXTURE_2D);
            var15.startDrawingQuads();
            int var17 = var12.getStringWidth(label) / 2;
            var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
            var15.addVertex((double)(-var17 - 1), (double)(-1 + var16), 0.0D);
            var15.addVertex((double)(-var17 - 1), (double)(8 + var16), 0.0D);
            var15.addVertex((double)(var17 + 1), (double)(8 + var16), 0.0D);
            var15.addVertex((double)(var17 + 1), (double)(-1 + var16), 0.0D);
            var15.draw();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            var12.drawString(label, -var12.getStringWidth(label) / 2, var16, 553648127);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(true);
            var12.drawString(label, -var12.getStringWidth(label) / 2, var16, -1);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
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
