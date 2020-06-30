package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class ItemRenderer
{
    /** A reference to the Minecraft object. */
    private Minecraft mc;
    private ItemStack itemToRender = null;

    /**
     * How far the current item has been equipped (0 disequipped and 1 fully up)
     */
    private float equippedProgress = 0.0F;
    private float prevEquippedProgress = 0.0F;

    /** Instance of RenderBlocks. */
    private RenderBlocks renderBlocksInstance = new RenderBlocks();
    public final MapItemRenderer mapItemRenderer;

    /** The index of the currently held item (0-8, or -1 if not yet updated) */
    private int equippedItemSlot = -1;

    public ItemRenderer(Minecraft par1Minecraft)
    {
        this.mc = par1Minecraft;
        this.mapItemRenderer = new MapItemRenderer(par1Minecraft.fontRenderer, par1Minecraft.gameSettings, par1Minecraft.renderEngine);
    }

    /**
     * Renders the item stack for being in an entity's hand Args: itemStack
     */
    public void renderItem(EntityLiving par1EntityLiving, ItemStack par2ItemStack, int par3)
    {
        GL11.glPushMatrix();

        if (par2ItemStack.getItemSpriteNumber() == 0 && Block.blocksList[par2ItemStack.itemID] != null && Block.blocksList[par2ItemStack.itemID].DoesItemRenderAsBlock(par2ItemStack.getItemDamage()))
        {
            this.mc.renderEngine.bindTexture("/terrain.png");
            this.renderBlocksInstance.renderBlockAsItem(Block.blocksList[par2ItemStack.itemID], par2ItemStack.getItemDamage(), 1.0F);
        }
        else
        {
            Icon var4 = par1EntityLiving.getItemIcon(par2ItemStack, par3);

            if (var4 == null)
            {
                GL11.glPopMatrix();
                return;
            }

            if (par2ItemStack.getItemSpriteNumber() == 0)
            {
                this.mc.renderEngine.bindTexture("/terrain.png");
            }
            else
            {
                this.mc.renderEngine.bindTexture("/gui/items.png");
            }

            Tessellator var5 = Tessellator.instance;
            float var6 = var4.getMinU();
            float var7 = var4.getMaxU();
            float var8 = var4.getMinV();
            float var9 = var4.getMaxV();
            float var10 = 0.0F;
            float var11 = 0.3F;
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glTranslatef(-var10, -var11, 0.0F);
            float var12 = 1.5F;
            GL11.glScalef(var12, var12, var12);
            GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
            renderItemIn2D(var5, var7, var8, var6, var9, var4.getSheetWidth(), var4.getSheetHeight(), 0.0625F);

            if (par2ItemStack != null && par2ItemStack.hasEffect() && par3 == 0)
            {
                GL11.glDepthFunc(GL11.GL_EQUAL);
                GL11.glDisable(GL11.GL_LIGHTING);
                this.mc.renderEngine.bindTexture("%blur%/misc/glint.png");
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                float var13 = 0.76F;
                GL11.glColor4f(0.5F * var13, 0.25F * var13, 0.8F * var13, 1.0F);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glPushMatrix();
                float var14 = 0.125F;
                GL11.glScalef(var14, var14, var14);
                float var15 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
                GL11.glTranslatef(var15, 0.0F, 0.0F);
                GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                renderItemIn2D(var5, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef(var14, var14, var14);
                var15 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
                GL11.glTranslatef(-var15, 0.0F, 0.0F);
                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                renderItemIn2D(var5, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDepthFunc(GL11.GL_LEQUAL);
            }

            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        }

        GL11.glPopMatrix();
    }

    /**
     * Renders an item held in hand as a 2D texture with thickness
     */
    public static void renderItemIn2D(Tessellator par0Tessellator, float par1, float par2, float par3, float par4, int par5, int par6, float par7)
    {
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, 0.0F, 1.0F);
        par0Tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)par1, (double)par4);
        par0Tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, (double)par3, (double)par4);
        par0Tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, (double)par3, (double)par2);
        par0Tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, (double)par1, (double)par2);
        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, 0.0F, -1.0F);
        par0Tessellator.addVertexWithUV(0.0D, 1.0D, (double)(0.0F - par7), (double)par1, (double)par2);
        par0Tessellator.addVertexWithUV(1.0D, 1.0D, (double)(0.0F - par7), (double)par3, (double)par2);
        par0Tessellator.addVertexWithUV(1.0D, 0.0D, (double)(0.0F - par7), (double)par3, (double)par4);
        par0Tessellator.addVertexWithUV(0.0D, 0.0D, (double)(0.0F - par7), (double)par1, (double)par4);
        par0Tessellator.draw();
        float var8 = (float)par5 * (par1 - par3);
        float var9 = (float)par6 * (par4 - par2);
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        int var10;
        float var11;
        float var12;

        for (var10 = 0; (float)var10 < var8; ++var10)
        {
            var11 = (float)var10 / var8;
            var12 = par1 + (par3 - par1) * var11 - 0.5F / (float)par5;
            par0Tessellator.addVertexWithUV((double)var11, 0.0D, (double)(0.0F - par7), (double)var12, (double)par4);
            par0Tessellator.addVertexWithUV((double)var11, 0.0D, 0.0D, (double)var12, (double)par4);
            par0Tessellator.addVertexWithUV((double)var11, 1.0D, 0.0D, (double)var12, (double)par2);
            par0Tessellator.addVertexWithUV((double)var11, 1.0D, (double)(0.0F - par7), (double)var12, (double)par2);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(1.0F, 0.0F, 0.0F);
        float var13;

        for (var10 = 0; (float)var10 < var8; ++var10)
        {
            var11 = (float)var10 / var8;
            var12 = par1 + (par3 - par1) * var11 - 0.5F / (float)par5;
            var13 = var11 + 1.0F / var8;
            par0Tessellator.addVertexWithUV((double)var13, 1.0D, (double)(0.0F - par7), (double)var12, (double)par2);
            par0Tessellator.addVertexWithUV((double)var13, 1.0D, 0.0D, (double)var12, (double)par2);
            par0Tessellator.addVertexWithUV((double)var13, 0.0D, 0.0D, (double)var12, (double)par4);
            par0Tessellator.addVertexWithUV((double)var13, 0.0D, (double)(0.0F - par7), (double)var12, (double)par4);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, 1.0F, 0.0F);

        for (var10 = 0; (float)var10 < var9; ++var10)
        {
            var11 = (float)var10 / var9;
            var12 = par4 + (par2 - par4) * var11 - 0.5F / (float)par6;
            var13 = var11 + 1.0F / var9;
            par0Tessellator.addVertexWithUV(0.0D, (double)var13, 0.0D, (double)par1, (double)var12);
            par0Tessellator.addVertexWithUV(1.0D, (double)var13, 0.0D, (double)par3, (double)var12);
            par0Tessellator.addVertexWithUV(1.0D, (double)var13, (double)(0.0F - par7), (double)par3, (double)var12);
            par0Tessellator.addVertexWithUV(0.0D, (double)var13, (double)(0.0F - par7), (double)par1, (double)var12);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, -1.0F, 0.0F);

        for (var10 = 0; (float)var10 < var9; ++var10)
        {
            var11 = (float)var10 / var9;
            var12 = par4 + (par2 - par4) * var11 - 0.5F / (float)par6;
            par0Tessellator.addVertexWithUV(1.0D, (double)var11, 0.0D, (double)par3, (double)var12);
            par0Tessellator.addVertexWithUV(0.0D, (double)var11, 0.0D, (double)par1, (double)var12);
            par0Tessellator.addVertexWithUV(0.0D, (double)var11, (double)(0.0F - par7), (double)par1, (double)var12);
            par0Tessellator.addVertexWithUV(1.0D, (double)var11, (double)(0.0F - par7), (double)par3, (double)var12);
        }

        par0Tessellator.draw();
    }

    /**
     * Renders the active item in the player's hand when in first person mode. Args: partialTickTime
     */
    public void renderItemInFirstPerson(float par1)
    {
        float var2 = this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * par1;
        EntityClientPlayerMP var3 = this.mc.thePlayer;
        float var4 = var3.prevRotationPitch + (var3.rotationPitch - var3.prevRotationPitch) * par1;
        GL11.glPushMatrix();
        GL11.glRotatef(var4, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(var3.prevRotationYaw + (var3.rotationYaw - var3.prevRotationYaw) * par1, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
        float var5;
        float var6;

        if (var3 instanceof EntityPlayerSP)
        {
            var5 = var3.prevRenderArmPitch + (var3.renderArmPitch - var3.prevRenderArmPitch) * par1;
            var6 = var3.prevRenderArmYaw + (var3.renderArmYaw - var3.prevRenderArmYaw) * par1;
            GL11.glRotatef((var3.rotationPitch - var5) * 0.1F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef((var3.rotationYaw - var6) * 0.1F, 0.0F, 1.0F, 0.0F);
        }

        ItemStack var7 = this.itemToRender;
        var5 = this.mc.theWorld.getLightBrightness(MathHelper.floor_double(var3.posX), MathHelper.floor_double(var3.posY), MathHelper.floor_double(var3.posZ));
        var5 = 1.0F;
        int var8 = this.mc.theWorld.getLightBrightnessForSkyBlocks(MathHelper.floor_double(var3.posX), MathHelper.floor_double(var3.posY), MathHelper.floor_double(var3.posZ), 0);
        int var9 = var8 % 65536;
        int var10 = var8 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var9 / 1.0F, (float)var10 / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float var11;
        float var12;
        float var13;

        if (var7 != null)
        {
            var8 = Item.itemsList[var7.itemID].getColorFromItemStack(var7, 0);
            var13 = (float)(var8 >> 16 & 255) / 255.0F;
            var12 = (float)(var8 >> 8 & 255) / 255.0F;
            var11 = (float)(var8 & 255) / 255.0F;
            GL11.glColor4f(var5 * var13, var5 * var12, var5 * var11, 1.0F);
        }
        else
        {
            GL11.glColor4f(var5, var5, var5, 1.0F);
        }

        float var14;
        float var15;
        float var16;
        Render var17;
        RenderPlayer var18;

        if (var7 != null && var7.itemID == Item.map.itemID)
        {
            GL11.glPushMatrix();
            var6 = 0.8F;
            var13 = var3.getSwingProgress(par1);
            var12 = MathHelper.sin(var13 * (float)Math.PI);
            var11 = MathHelper.sin(MathHelper.sqrt_float(var13) * (float)Math.PI);
            GL11.glTranslatef(-var11 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(var13) * (float)Math.PI * 2.0F) * 0.2F, -var12 * 0.2F);
            var13 = 1.0F - var4 / 45.0F + 0.1F;

            if (var13 < 0.0F)
            {
                var13 = 0.0F;
            }

            if (var13 > 1.0F)
            {
                var13 = 1.0F;
            }

            var13 = -MathHelper.cos(var13 * (float)Math.PI) * 0.5F + 0.5F;
            GL11.glTranslatef(0.0F, 0.0F * var6 - (1.0F - var2) * 1.2F - var13 * 0.5F + 0.04F, -0.9F * var6);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var13 * -85.0F, 0.0F, 0.0F, 1.0F);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTextureForDownloadableImage(this.mc.thePlayer.skinUrl, this.mc.thePlayer.getTexture()));
            this.mc.renderEngine.resetBoundTexture();

            for (var10 = 0; var10 < 2; ++var10)
            {
                int var26 = var10 * 2 - 1;
                GL11.glPushMatrix();
                GL11.glTranslatef(-0.0F, -0.6F, 1.1F * (float)var26);
                GL11.glRotatef((float)(-45 * var26), 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(59.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef((float)(-65 * var26), 0.0F, 1.0F, 0.0F);
                var17 = RenderManager.instance.getEntityRenderObject(this.mc.thePlayer);
                var18 = (RenderPlayer)var17;
                var16 = 1.0F;
                GL11.glScalef(var16, var16, var16);
                var18.renderFirstPersonArm(this.mc.thePlayer);
                GL11.glPopMatrix();
            }

            var12 = var3.getSwingProgress(par1);
            var11 = MathHelper.sin(var12 * var12 * (float)Math.PI);
            var14 = MathHelper.sin(MathHelper.sqrt_float(var12) * (float)Math.PI);
            GL11.glRotatef(-var11 * 20.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-var14 * 20.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-var14 * 80.0F, 1.0F, 0.0F, 0.0F);
            var15 = 0.38F;
            GL11.glScalef(var15, var15, var15);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-1.0F, -1.0F, 0.0F);
            var16 = 0.015625F;
            GL11.glScalef(var16, var16, var16);
            this.mc.renderEngine.bindTexture("/misc/mapbg.png");
            Tessellator var27 = Tessellator.instance;
            GL11.glNormal3f(0.0F, 0.0F, -1.0F);
            var27.startDrawingQuads();
            byte var25 = 7;
            var27.addVertexWithUV((double)(0 - var25), (double)(128 + var25), 0.0D, 0.0D, 1.0D);
            var27.addVertexWithUV((double)(128 + var25), (double)(128 + var25), 0.0D, 1.0D, 1.0D);
            var27.addVertexWithUV((double)(128 + var25), (double)(0 - var25), 0.0D, 1.0D, 0.0D);
            var27.addVertexWithUV((double)(0 - var25), (double)(0 - var25), 0.0D, 0.0D, 0.0D);
            var27.draw();
            MapData var29 = Item.map.getMapData(var7, this.mc.theWorld);

            if (var29 != null)
            {
                this.mapItemRenderer.renderMap(this.mc.thePlayer, this.mc.renderEngine, var29);
            }

            GL11.glPopMatrix();
        }
        else if (var7 != null)
        {
            GL11.glPushMatrix();
            var6 = 0.8F;

            if (var3.getItemInUseCount() > 0)
            {
                EnumAction var19 = var7.getItemUseAction();

                if (var19 != EnumAction.eat && var19 != EnumAction.drink)
                {
                    if (var19 == EnumAction.miscUse)
                    {
                        var12 = (float)var3.getItemInUseCount() - par1 + 1.0F;
                        var14 = var12 / (float)var7.getMaxItemUseDuration();
                        var14 = var14 * var14 * var14;
                        var14 = var14 * var14 * var14;
                        var14 = var14 * var14 * var14;
                        var15 = 1.0F - var14;
                        GL11.glTranslatef(0.0F, MathHelper.abs(MathHelper.cos(var12 / 4.0F * (float)Math.PI) * 0.1F) * (var7.getMaxItemUseDuration() - var3.getItemInUseCount() >= var7.getItem().GetItemUseWarmupDuration() ? 1.0F : 0.0F), 0.0F);
                        int var20 = MathHelper.clamp_int(32 - (var7.getMaxItemUseDuration() - var3.getItemInUseCount()), 0, 32);
                        var12 = (float)var20 - par1 + 1.0F;
                        var14 = var12 / 32.0F;
                        var14 = var14 * var14 * var14;
                        var14 = var14 * var14 * var14;
                        var14 = var14 * var14 * var14;
                        var15 = 1.0F - var14;
                        GL11.glTranslatef(var15 * 0.6F, -var15 * 0.5F, 0.0F);
                        GL11.glRotatef(var15 * 90.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(var15 * 10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(var15 * 30.0F, 0.0F, 0.0F, 1.0F);
                    }
                }
                else
                {
                    var12 = (float)var3.getItemInUseCount() - par1 + 1.0F;
                    var11 = 1.0F - var12 / (float)var7.getMaxItemUseDuration();
                    var14 = 1.0F - var11;
                    var14 = var14 * var14 * var14;
                    var14 = var14 * var14 * var14;
                    var14 = var14 * var14 * var14;
                    var15 = 1.0F - var14;
                    GL11.glTranslatef(0.0F, MathHelper.abs(MathHelper.cos(var12 / 4.0F * (float)Math.PI) * 0.1F) * (float)((double)var11 > 0.2D ? 1 : 0), 0.0F);
                    GL11.glTranslatef(var15 * 0.6F, -var15 * 0.5F, 0.0F);
                    GL11.glRotatef(var15 * 90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(var15 * 10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(var15 * 30.0F, 0.0F, 0.0F, 1.0F);
                }
            }
            else
            {
                var13 = var3.getSwingProgress(par1);
                var12 = MathHelper.sin(var13 * (float)Math.PI);
                var11 = MathHelper.sin(MathHelper.sqrt_float(var13) * (float)Math.PI);
                GL11.glTranslatef(-var11 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(var13) * (float)Math.PI * 2.0F) * 0.2F, -var12 * 0.2F);
            }

            GL11.glTranslatef(0.7F * var6, -0.65F * var6 - (1.0F - var2) * 0.6F, -0.9F * var6);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            var13 = var3.getSwingProgress(par1);
            var12 = MathHelper.sin(var13 * var13 * (float)Math.PI);
            var11 = MathHelper.sin(MathHelper.sqrt_float(var13) * (float)Math.PI);
            GL11.glRotatef(-var12 * 20.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-var11 * 20.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-var11 * 80.0F, 1.0F, 0.0F, 0.0F);
            var14 = 0.4F;
            GL11.glScalef(var14, var14, var14);
            float var23;
            float var24;

            if (var3.getItemInUseCount() > 0)
            {
                EnumAction var21 = var7.getItemUseAction();

                if (var21 == EnumAction.block)
                {
                    GL11.glTranslatef(-0.5F, 0.2F, 0.0F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
                }
                else if (var21 == EnumAction.bow)
                {
                    GL11.glRotatef(-18.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glRotatef(-12.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glTranslatef(-0.9F, 0.2F, 0.0F);
                    var16 = (float)var7.getMaxItemUseDuration() - ((float)var3.getItemInUseCount() - par1 + 1.0F);
                    var23 = var16 / 20.0F;
                    var23 = (var23 * var23 + var23 * 2.0F) / 3.0F;

                    if (var23 > 1.0F)
                    {
                        var23 = 1.0F;
                    }

                    if (var23 > 0.1F)
                    {
                        GL11.glTranslatef(0.0F, MathHelper.sin((var16 - 0.1F) * 1.3F) * 0.01F * (var23 - 0.1F), 0.0F);
                    }

                    GL11.glTranslatef(0.0F, 0.0F, var23 * 0.1F);
                    GL11.glRotatef(-335.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glTranslatef(0.0F, 0.5F, 0.0F);
                    var24 = 1.0F + var23 * 0.2F;
                    GL11.glScalef(1.0F, 1.0F, var24);
                    GL11.glTranslatef(0.0F, -0.5F, 0.0F);
                    GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
                }
            }

            if (var7.getItem().shouldRotateAroundWhenRendering())
            {
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            }

            int var28 = var7.getItem().itemID;

            if (var28 == Item.compass.itemID)
            {
                TextureCompass.compassTexture.UpdateActive();
            }

            if (var7.getItem().requiresMultipleRenderPasses())
            {
                this.renderItem(var3, var7, 0);
                int var22 = Item.itemsList[var7.itemID].getColorFromItemStack(var7, 1);
                var16 = (float)(var22 >> 16 & 255) / 255.0F;
                var23 = (float)(var22 >> 8 & 255) / 255.0F;
                var24 = (float)(var22 & 255) / 255.0F;
                GL11.glColor4f(var5 * var16, var5 * var23, var5 * var24, 1.0F);
                this.renderItem(var3, var7, 1);
            }
            else
            {
                this.renderItem(var3, var7, 0);
            }

            if (var28 == Item.compass.itemID)
            {
                TextureCompass.compassTexture.UpdateInert();
            }

            GL11.glPopMatrix();
        }
        else if (!var3.isInvisible())
        {
            GL11.glPushMatrix();
            var6 = 0.8F;
            var13 = var3.getSwingProgress(par1);
            var12 = MathHelper.sin(var13 * (float)Math.PI);
            var11 = MathHelper.sin(MathHelper.sqrt_float(var13) * (float)Math.PI);
            GL11.glTranslatef(-var11 * 0.3F, MathHelper.sin(MathHelper.sqrt_float(var13) * (float)Math.PI * 2.0F) * 0.4F, -var12 * 0.4F);
            GL11.glTranslatef(0.8F * var6, -0.75F * var6 - (1.0F - var2) * 0.6F, -0.9F * var6);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            var13 = var3.getSwingProgress(par1);
            var12 = MathHelper.sin(var13 * var13 * (float)Math.PI);
            var11 = MathHelper.sin(MathHelper.sqrt_float(var13) * (float)Math.PI);
            GL11.glRotatef(var11 * 70.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-var12 * 20.0F, 0.0F, 0.0F, 1.0F);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTextureForDownloadableImage(this.mc.thePlayer.skinUrl, this.mc.thePlayer.getTexture()));
            this.mc.renderEngine.resetBoundTexture();
            GL11.glTranslatef(-1.0F, 3.6F, 3.5F);
            GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(200.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glTranslatef(5.6F, 0.0F, 0.0F);
            var17 = RenderManager.instance.getEntityRenderObject(this.mc.thePlayer);
            var18 = (RenderPlayer)var17;
            var16 = 1.0F;
            GL11.glScalef(var16, var16, var16);
            var18.renderFirstPersonArm(this.mc.thePlayer);
            GL11.glPopMatrix();
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
    }

    /**
     * Renders all the overlays that are in first person mode. Args: partialTickTime
     */
    public void renderOverlays(float par1)
    {
        GL11.glDisable(GL11.GL_ALPHA_TEST);

        if (this.mc.thePlayer.isBurning())
        {
            this.mc.renderEngine.bindTexture("/terrain.png");
            this.renderFireInFirstPerson(par1);
        }

        if (this.mc.thePlayer.isEntityInsideOpaqueBlock())
        {
            int var2 = MathHelper.floor_double(this.mc.thePlayer.posX);
            int var3 = MathHelper.floor_double(this.mc.thePlayer.posY);
            int var4 = MathHelper.floor_double(this.mc.thePlayer.posZ);
            this.mc.renderEngine.bindTexture("/terrain.png");
            int var5 = this.mc.theWorld.getBlockId(var2, var3, var4);

            if (this.mc.theWorld.isBlockNormalCube(var2, var3, var4))
            {
                this.renderInsideOfBlock(par1, Block.blocksList[var5].getBlockTextureFromSide(2));
            }
            else
            {
                for (int var6 = 0; var6 < 8; ++var6)
                {
                    float var7 = ((float)((var6 >> 0) % 2) - 0.5F) * this.mc.thePlayer.width * 0.9F;
                    float var8 = ((float)((var6 >> 1) % 2) - 0.5F) * this.mc.thePlayer.height * 0.2F;
                    float var9 = ((float)((var6 >> 2) % 2) - 0.5F) * this.mc.thePlayer.width * 0.9F;
                    int var10 = MathHelper.floor_float((float)var2 + var7);
                    int var11 = MathHelper.floor_float((float)var3 + var8);
                    int var12 = MathHelper.floor_float((float)var4 + var9);

                    if (this.mc.theWorld.isBlockNormalCube(var10, var11, var12))
                    {
                        var5 = this.mc.theWorld.getBlockId(var10, var11, var12);
                    }
                }
            }

            if (Block.blocksList[var5] != null)
            {
                this.renderInsideOfBlock(par1, Block.blocksList[var5].getBlockTextureFromSide(2));
            }
        }

        if (this.mc.thePlayer.isInsideOfMaterial(Material.water))
        {
            this.mc.renderEngine.bindTexture("/misc/water.png");
            this.renderWarpedTextureOverlay(par1);
        }

        GL11.glEnable(GL11.GL_ALPHA_TEST);
    }

    /**
     * Renders the texture of the block the player is inside as an overlay. Args: partialTickTime, blockTextureIndex
     */
    private void renderInsideOfBlock(float par1, Icon par2Icon)
    {
        Tessellator var3 = Tessellator.instance;
        float var4 = 0.1F;
        GL11.glColor4f(var4, var4, var4, 0.5F);
        GL11.glPushMatrix();
        float var5 = -1.0F;
        float var6 = 1.0F;
        float var7 = -1.0F;
        float var8 = 1.0F;
        float var9 = -0.5F;
        float var10 = par2Icon.getMinU();
        float var11 = par2Icon.getMaxU();
        float var12 = par2Icon.getMinV();
        float var13 = par2Icon.getMaxV();
        var3.startDrawingQuads();
        var3.addVertexWithUV((double)var5, (double)var7, (double)var9, (double)var11, (double)var13);
        var3.addVertexWithUV((double)var6, (double)var7, (double)var9, (double)var10, (double)var13);
        var3.addVertexWithUV((double)var6, (double)var8, (double)var9, (double)var10, (double)var12);
        var3.addVertexWithUV((double)var5, (double)var8, (double)var9, (double)var11, (double)var12);
        var3.draw();
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Renders a texture that warps around based on the direction the player is looking. Texture needs to be bound
     * before being called. Used for the water overlay. Args: parialTickTime
     */
    private void renderWarpedTextureOverlay(float par1)
    {
        Tessellator var2 = Tessellator.instance;
        float var3 = this.mc.thePlayer.getBrightness(par1);
        GL11.glColor4f(var3, var3, var3, 0.5F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glPushMatrix();
        float var4 = 4.0F;
        float var5 = -1.0F;
        float var6 = 1.0F;
        float var7 = -1.0F;
        float var8 = 1.0F;
        float var9 = -0.5F;
        float var10 = -this.mc.thePlayer.rotationYaw / 64.0F;
        float var11 = this.mc.thePlayer.rotationPitch / 64.0F;
        var2.startDrawingQuads();
        var2.addVertexWithUV((double)var5, (double)var7, (double)var9, (double)(var4 + var10), (double)(var4 + var11));
        var2.addVertexWithUV((double)var6, (double)var7, (double)var9, (double)(0.0F + var10), (double)(var4 + var11));
        var2.addVertexWithUV((double)var6, (double)var8, (double)var9, (double)(0.0F + var10), (double)(0.0F + var11));
        var2.addVertexWithUV((double)var5, (double)var8, (double)var9, (double)(var4 + var10), (double)(0.0F + var11));
        var2.draw();
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
    }

    /**
     * Renders the fire on the screen for first person mode. Arg: partialTickTime
     */
    private void renderFireInFirstPerson(float par1)
    {
        Tessellator var2 = Tessellator.instance;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        float var3 = 1.0F;

        for (int var4 = 0; var4 < 2; ++var4)
        {
            GL11.glPushMatrix();
            Icon var5 = Block.fire.func_94438_c(1);
            float var6 = var5.getMinU();
            float var7 = var5.getMaxU();
            float var8 = var5.getMinV();
            float var9 = var5.getMaxV();
            float var10 = (0.0F - var3) / 2.0F;
            float var11 = var10 + var3;
            float var12 = 0.0F - var3 / 2.0F;
            float var13 = var12 + var3;
            float var14 = -0.5F;
            GL11.glTranslatef((float)(-(var4 * 2 - 1)) * 0.24F, -0.3F, 0.0F);
            GL11.glRotatef((float)(var4 * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
            var2.startDrawingQuads();
            var2.addVertexWithUV((double)var10, (double)var12, (double)var14, (double)var7, (double)var9);
            var2.addVertexWithUV((double)var11, (double)var12, (double)var14, (double)var6, (double)var9);
            var2.addVertexWithUV((double)var11, (double)var13, (double)var14, (double)var6, (double)var8);
            var2.addVertexWithUV((double)var10, (double)var13, (double)var14, (double)var7, (double)var8);
            var2.draw();
            GL11.glPopMatrix();
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public void updateEquippedItem()
    {
        this.prevEquippedProgress = this.equippedProgress;
        EntityClientPlayerMP var1 = this.mc.thePlayer;
        ItemStack var2 = var1.inventory.getCurrentItem();
        boolean var3 = this.equippedItemSlot == var1.inventory.currentItem && var2 == this.itemToRender;

        if (this.itemToRender == null && var2 == null)
        {
            var3 = true;
        }

        if (var2 != null && this.itemToRender != null && var2 != this.itemToRender && var2.itemID == this.itemToRender.itemID && (var2.getItemDamage() == this.itemToRender.getItemDamage() || this.itemToRender.getItem().IgnoreDamageWhenComparingDuringUse()))
        {
            this.itemToRender = var2;
            var3 = true;
        }

        float var4 = 0.4F;
        float var5 = var3 ? 1.0F : 0.0F;
        float var6 = var5 - this.equippedProgress;

        if (var6 < -var4)
        {
            var6 = -var4;
        }

        if (var6 > var4)
        {
            var6 = var4;
        }

        this.equippedProgress += var6;

        if (this.equippedProgress < 0.1F)
        {
            this.itemToRender = var2;
            this.equippedItemSlot = var1.inventory.currentItem;
        }
    }

    /**
     * Resets equippedProgress
     */
    public void resetEquippedProgress()
    {
        this.equippedProgress = 0.0F;
    }

    /**
     * Resets equippedProgress
     */
    public void resetEquippedProgress2()
    {
        this.equippedProgress = 0.0F;
    }
}
