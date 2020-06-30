package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderPlayer extends RenderLiving
{
    private ModelBiped modelBipedMain;
    private ModelBiped modelArmorChestplate;
    private ModelBiped modelArmor;
    private static final String[] armorFilenamePrefix = new String[] {"cloth", "chain", "iron", "diamond", "gold"};

    public RenderPlayer()
    {
        super(new FCClientModelPlayer(0.0F), 0.5F);
        this.modelBipedMain = (ModelBiped)this.mainModel;
        this.modelArmorChestplate = new FCClientModelPlayerArmor(1.0F);
        this.modelArmor = new FCClientModelPlayerArmor(0.5F);
    }

    protected void func_98191_a(EntityPlayer par1EntityPlayer)
    {
        this.loadDownloadableImageTexture(par1EntityPlayer.skinUrl, par1EntityPlayer.getTexture());
    }

    /**
     * Set the specified armor model as the player model. Args: player, armorSlot, partialTick
     */
    protected int setArmorModel(EntityPlayer par1EntityPlayer, int par2, float par3)
    {
        ItemStack var4 = par1EntityPlayer.inventory.armorItemInSlot(3 - par2);

        if (var4 != null)
        {
            Item var5 = var4.getItem();

            if (var5 instanceof ItemArmor)
            {
                ItemArmor var6 = (ItemArmor)var5;

                if (var5 instanceof FCItemArmorMod)
                {
                    return this.ShouldRenderPassModArmor(var4, par2, (FCItemArmorMod)var5);
                }

                this.loadTexture("/armor/" + armorFilenamePrefix[var6.renderIndex] + "_" + (par2 == 2 ? 2 : 1) + ".png");
                ModelBiped var7 = par2 == 2 ? this.modelArmor : this.modelArmorChestplate;
                var7.bipedHead.showModel = par2 == 0;
                var7.bipedHeadwear.showModel = par2 == 0;
                var7.bipedBody.showModel = par2 == 1 || par2 == 2;
                var7.bipedRightArm.showModel = par2 == 1;
                var7.bipedLeftArm.showModel = par2 == 1;
                var7.bipedRightLeg.showModel = par2 == 2 || par2 == 3;
                var7.bipedLeftLeg.showModel = par2 == 2 || par2 == 3;
                this.setRenderPassModel(var7);

                if (var7 != null)
                {
                    var7.onGround = this.mainModel.onGround;
                }

                if (var7 != null)
                {
                    var7.isRiding = this.mainModel.isRiding;
                }

                if (var7 != null)
                {
                    var7.isChild = this.mainModel.isChild;
                }

                float var8 = 1.0F;

                if (var6.getArmorMaterial() == EnumArmorMaterial.CLOTH)
                {
                    int var9 = var6.getColor(var4);
                    float var10 = (float)(var9 >> 16 & 255) / 255.0F;
                    float var11 = (float)(var9 >> 8 & 255) / 255.0F;
                    float var12 = (float)(var9 & 255) / 255.0F;
                    GL11.glColor3f(var8 * var10, var8 * var11, var8 * var12);

                    if (var4.isItemEnchanted())
                    {
                        return 31;
                    }

                    return 16;
                }

                GL11.glColor3f(var8, var8, var8);

                if (var4.isItemEnchanted())
                {
                    return 15;
                }

                return 1;
            }
        }

        return -1;
    }

    protected void func_82439_b(EntityPlayer par1EntityPlayer, int par2, float par3)
    {
        ItemStack var4 = par1EntityPlayer.inventory.armorItemInSlot(3 - par2);

        if (var4 != null)
        {
            Item var5 = var4.getItem();

            if (var5 instanceof ItemArmor)
            {
                if (var5 instanceof FCItemArmorMod)
                {
                    this.LoadSecondLayerOfModArmorTexture(par2, (FCItemArmorMod)var5);
                    return;
                }

                ItemArmor var6 = (ItemArmor)var5;
                this.loadTexture("/armor/" + armorFilenamePrefix[var6.renderIndex] + "_" + (par2 == 2 ? 2 : 1) + "_b.png");
                float var7 = 1.0F;
                GL11.glColor3f(var7, var7, var7);
            }
        }
    }

    public void renderPlayer(EntityPlayer par1EntityPlayer, double par2, double par4, double par6, float par8, float par9)
    {
        float var10 = 1.0F;
        GL11.glColor3f(var10, var10, var10);
        ItemStack var11 = par1EntityPlayer.inventory.getCurrentItem();
        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = var11 != null ? 1 : 0;

        if (var11 != null && par1EntityPlayer.getItemInUseCount() > 0)
        {
            EnumAction var12 = var11.getItemUseAction();

            if (var12 == EnumAction.block)
            {
                this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 3;
            }
            else if (var12 == EnumAction.bow)
            {
                this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = true;
            }
        }

        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = par1EntityPlayer.isSneaking();
        double var14 = par4 - (double)par1EntityPlayer.yOffset;

        if (par1EntityPlayer.isSneaking() && !(par1EntityPlayer instanceof EntityPlayerSP))
        {
            var14 -= 0.125D;
        }

        super.doRenderLiving(par1EntityPlayer, par2, var14, par6, par8, par9);
        this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = false;
        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = false;
        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 0;
    }

    /**
     * Method for adding special render rules
     */
    protected void renderSpecials(EntityPlayer par1EntityPlayer, float par2)
    {
        float var3 = 1.0F;
        GL11.glColor3f(var3, var3, var3);
        super.renderEquippedItems(par1EntityPlayer, par2);
        super.renderArrowsStuckInEntity(par1EntityPlayer, par2);
        ItemStack var4 = par1EntityPlayer.inventory.armorItemInSlot(3);
        float var5;

        if (var4 != null)
        {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedHead.postRender(0.0625F);

            if (var4.getItem().itemID < 4096 && Block.blocksList[var4.itemID] != null)
            {
                if (Block.blocksList[var4.itemID].DoesItemRenderAsBlock(var4.getItemDamage()))
                {
                    var5 = 0.625F;
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(var5, -var5, -var5);
                }

                this.renderManager.itemRenderer.renderItem(par1EntityPlayer, var4, 0);
            }
            else if (var4.getItem().itemID == Item.skull.itemID)
            {
                var5 = 1.0625F;
                GL11.glScalef(var5, -var5, -var5);
                String var6 = "";

                if (var4.hasTagCompound() && var4.getTagCompound().hasKey("SkullOwner"))
                {
                    var6 = var4.getTagCompound().getString("SkullOwner");
                }

                TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var4.getItemDamage(), var6);
            }

            GL11.glPopMatrix();
        }

        float var22;

        if (par1EntityPlayer.username.equals("deadmau5") && this.loadDownloadableImageTexture(par1EntityPlayer.skinUrl, (String)null))
        {
            for (int var7 = 0; var7 < 2; ++var7)
            {
                float var8 = par1EntityPlayer.prevRotationYaw + (par1EntityPlayer.rotationYaw - par1EntityPlayer.prevRotationYaw) * par2 - (par1EntityPlayer.prevRenderYawOffset + (par1EntityPlayer.renderYawOffset - par1EntityPlayer.prevRenderYawOffset) * par2);
                var5 = par1EntityPlayer.prevRotationPitch + (par1EntityPlayer.rotationPitch - par1EntityPlayer.prevRotationPitch) * par2;
                GL11.glPushMatrix();
                GL11.glRotatef(var8, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(var5, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.375F * (float)(var7 * 2 - 1), 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -0.375F, 0.0F);
                GL11.glRotatef(-var5, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-var8, 0.0F, 1.0F, 0.0F);
                var22 = 1.3333334F;
                GL11.glScalef(var22, var22, var22);
                this.modelBipedMain.renderEars(0.0625F);
                GL11.glPopMatrix();
            }
        }

        float var23;

        if (this.loadDownloadableImageTexture(par1EntityPlayer.cloakUrl, (String)null) && !par1EntityPlayer.isInvisible() && !par1EntityPlayer.getHideCape())
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
            double var24 = par1EntityPlayer.field_71091_bM + (par1EntityPlayer.field_71094_bP - par1EntityPlayer.field_71091_bM) * (double)par2 - (par1EntityPlayer.prevPosX + (par1EntityPlayer.posX - par1EntityPlayer.prevPosX) * (double)par2);
            double var10 = par1EntityPlayer.field_71096_bN + (par1EntityPlayer.field_71095_bQ - par1EntityPlayer.field_71096_bN) * (double)par2 - (par1EntityPlayer.prevPosY + (par1EntityPlayer.posY - par1EntityPlayer.prevPosY) * (double)par2);
            double var12 = par1EntityPlayer.field_71097_bO + (par1EntityPlayer.field_71085_bR - par1EntityPlayer.field_71097_bO) * (double)par2 - (par1EntityPlayer.prevPosZ + (par1EntityPlayer.posZ - par1EntityPlayer.prevPosZ) * (double)par2);
            var23 = par1EntityPlayer.prevRenderYawOffset + (par1EntityPlayer.renderYawOffset - par1EntityPlayer.prevRenderYawOffset) * par2;
            double var14 = (double)MathHelper.sin(var23 * (float)Math.PI / 180.0F);
            double var16 = (double)(-MathHelper.cos(var23 * (float)Math.PI / 180.0F));
            float var18 = (float)var10 * 10.0F;

            if (var18 < -6.0F)
            {
                var18 = -6.0F;
            }

            if (var18 > 32.0F)
            {
                var18 = 32.0F;
            }

            float var19 = (float)(var24 * var14 + var12 * var16) * 100.0F;
            float var20 = (float)(var24 * var16 - var12 * var14) * 100.0F;

            if (var19 < 0.0F)
            {
                var19 = 0.0F;
            }

            float var21 = par1EntityPlayer.prevCameraYaw + (par1EntityPlayer.cameraYaw - par1EntityPlayer.prevCameraYaw) * par2;
            var18 += MathHelper.sin((par1EntityPlayer.prevDistanceWalkedModified + (par1EntityPlayer.distanceWalkedModified - par1EntityPlayer.prevDistanceWalkedModified) * par2) * 6.0F) * 32.0F * var21;

            if (par1EntityPlayer.isSneaking())
            {
                var18 += 25.0F;
            }

            GL11.glRotatef(6.0F + var19 / 2.0F + var18, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(var20 / 2.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-var20 / 2.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            this.modelBipedMain.renderCloak(0.0625F);
            GL11.glPopMatrix();
        }

        ItemStack var25 = par1EntityPlayer.inventory.getCurrentItem();

        if (var25 != null)
        {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedRightArm.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

            if (par1EntityPlayer.fishEntity != null)
            {
                var25 = new ItemStack(Item.stick);
            }

            EnumAction var9 = null;

            if (par1EntityPlayer.getItemInUseCount() > 0)
            {
                var9 = var25.getItemUseAction();
            }

            if (var25.itemID < 4096 && Block.blocksList[var25.itemID] != null && Block.blocksList[var25.itemID].DoesItemRenderAsBlock(var25.getItemDamage()))
            {
                var5 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                var5 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-var5, -var5, var5);
            }
            else if (var25.itemID != Item.bow.itemID && var25.itemID != FCBetterThanWolves.fcItemCompositeBow.itemID)
            {
                if (Item.itemsList[var25.itemID].isFull3D())
                {
                    var5 = 0.625F;

                    if (Item.itemsList[var25.itemID].shouldRotateAroundWhenRendering())
                    {
                        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                    }

                    if (par1EntityPlayer.getItemInUseCount() > 0 && var9 == EnumAction.block)
                    {
                        GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                        GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                    }

                    GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                    GL11.glScalef(var5, -var5, var5);
                    GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                }
                else
                {
                    var5 = 0.375F;
                    GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                    GL11.glScalef(var5, var5, var5);
                    GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                }
            }
            else
            {
                var5 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(var5, -var5, var5);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }

            int var11;
            float var26;
            float var27;

            if (var25.getItem().requiresMultipleRenderPasses())
            {
                for (var11 = 0; var11 <= 1; ++var11)
                {
                    int var13 = var25.getItem().getColorFromItemStack(var25, var11);
                    var27 = (float)(var13 >> 16 & 255) / 255.0F;
                    var26 = (float)(var13 >> 8 & 255) / 255.0F;
                    var23 = (float)(var13 & 255) / 255.0F;
                    GL11.glColor4f(var27, var26, var23, 1.0F);
                    this.renderManager.itemRenderer.renderItem(par1EntityPlayer, var25, var11);
                }
            }
            else
            {
                var11 = var25.getItem().getColorFromItemStack(var25, 0);
                var22 = (float)(var11 >> 16 & 255) / 255.0F;
                var27 = (float)(var11 >> 8 & 255) / 255.0F;
                var26 = (float)(var11 & 255) / 255.0F;
                GL11.glColor4f(var22, var27, var26, 1.0F);
                this.renderManager.itemRenderer.renderItem(par1EntityPlayer, var25, 0);
            }

            GL11.glPopMatrix();
        }
    }

    protected void renderPlayerScale(EntityPlayer par1EntityPlayer, float par2)
    {
        float var3 = 0.9375F;
        GL11.glScalef(var3, var3, var3);
    }

    protected void func_96450_a(EntityPlayer par1EntityPlayer, double par2, double par4, double par6, String par8Str, float par9, double par10)
    {
        if (!FCBetterThanWolves.IsHardcorePlayerNamesEnabled(par1EntityPlayer.worldObj))
        {
            if (par10 < 100.0D)
            {
                Scoreboard var12 = par1EntityPlayer.getWorldScoreboard();
                ScoreObjective var13 = var12.func_96539_a(2);

                if (var13 != null)
                {
                    Score var14 = var12.func_96529_a(par1EntityPlayer.getEntityName(), var13);

                    if (par1EntityPlayer.isPlayerSleeping())
                    {
                        this.renderLivingLabel(par1EntityPlayer, var14.func_96652_c() + " " + var13.getDisplayName(), par2, par4 - 1.5D, par6, 64);
                    }
                    else
                    {
                        this.renderLivingLabel(par1EntityPlayer, var14.func_96652_c() + " " + var13.getDisplayName(), par2, par4, par6, 64);
                    }

                    par4 += (double)((float)this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * par9);
                }
            }

            super.func_96449_a(par1EntityPlayer, par2, par4, par6, par8Str, par9, par10);
        }
    }

    public void renderFirstPersonArm(EntityPlayer par1EntityPlayer)
    {
        float var2 = 1.0F;
        GL11.glColor3f(var2, var2, var2);
        this.modelBipedMain.onGround = 0.0F;
        this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, par1EntityPlayer);
        this.modelBipedMain.bipedRightArm.render(0.0625F);
    }

    /**
     * Renders player with sleeping offset if sleeping
     */
    protected void renderPlayerSleep(EntityPlayer par1EntityPlayer, double par2, double par4, double par6)
    {
        if (par1EntityPlayer.isEntityAlive() && par1EntityPlayer.isPlayerSleeping())
        {
            super.renderLivingAt(par1EntityPlayer, par2 + (double)par1EntityPlayer.field_71079_bU, par4 + (double)par1EntityPlayer.field_71082_cx, par6 + (double)par1EntityPlayer.field_71089_bV);
        }
        else
        {
            super.renderLivingAt(par1EntityPlayer, par2, par4, par6);
        }
    }

    /**
     * Rotates the player if the player is sleeping. This method is called in rotateCorpse.
     */
    protected void rotatePlayer(EntityPlayer par1EntityPlayer, float par2, float par3, float par4)
    {
        if (par1EntityPlayer.isEntityAlive() && par1EntityPlayer.isPlayerSleeping())
        {
            GL11.glRotatef(par1EntityPlayer.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.getDeathMaxRotation(par1EntityPlayer), 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
        }
        else
        {
            super.rotateCorpse(par1EntityPlayer, par2, par3, par4);
        }
    }

    protected void func_96449_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, String par8Str, float par9, double par10)
    {
        this.func_96450_a((EntityPlayer)par1EntityLiving, par2, par4, par6, par8Str, par9, par10);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
    {
        this.renderPlayerScale((EntityPlayer)par1EntityLiving, par2);
    }

    protected void func_82408_c(EntityLiving par1EntityLiving, int par2, float par3)
    {
        this.func_82439_b((EntityPlayer)par1EntityLiving, par2, par3);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.setArmorModel((EntityPlayer)par1EntityLiving, par2, par3);
    }

    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2)
    {
        this.renderSpecials((EntityPlayer)par1EntityLiving, par2);
    }

    protected void rotateCorpse(EntityLiving par1EntityLiving, float par2, float par3, float par4)
    {
        this.rotatePlayer((EntityPlayer)par1EntityLiving, par2, par3, par4);
    }

    /**
     * Sets a simple glTranslate on a LivingEntity.
     */
    protected void renderLivingAt(EntityLiving par1EntityLiving, double par2, double par4, double par6)
    {
        this.renderPlayerSleep((EntityPlayer)par1EntityLiving, par2, par4, par6);
    }

    protected void func_98190_a(EntityLiving par1EntityLiving)
    {
        this.func_98191_a((EntityPlayer)par1EntityLiving);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderPlayer((EntityPlayer)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderPlayer((EntityPlayer)par1Entity, par2, par4, par6, par8, par9);
    }

    private int ShouldRenderPassModArmor(ItemStack var1, int var2, FCItemArmorMod var3)
    {
        this.loadTexture(var3.GetWornTextureDirectory() + var3.GetWornTexturePrefix() + "_" + (var2 == 2 ? 2 : 1) + ".png");
        ModelBiped var4 = var2 == 2 ? this.modelArmor : this.modelArmorChestplate;
        var4.bipedHead.showModel = var2 == 0;
        var4.bipedHeadwear.showModel = var2 == 0;
        var4.bipedBody.showModel = var2 == 1 || var2 == 2;
        var4.bipedRightArm.showModel = var2 == 1;
        var4.bipedLeftArm.showModel = var2 == 1;
        var4.bipedRightLeg.showModel = var2 == 2 || var2 == 3;
        var4.bipedLeftLeg.showModel = var2 == 2 || var2 == 3;
        this.setRenderPassModel(var4);

        if (var4 != null)
        {
            var4.onGround = this.mainModel.onGround;
            var4.isRiding = this.mainModel.isRiding;
            var4.isChild = this.mainModel.isChild;
        }

        if (var3.HasCustomColors())
        {
            int var5 = var3.getColor(var1);
            float var6 = (float)(var5 >> 16 & 255) / 255.0F;
            float var7 = (float)(var5 >> 8 & 255) / 255.0F;
            float var8 = (float)(var5 & 255) / 255.0F;
            GL11.glColor3f(var6, var7, var8);
        }
        else
        {
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
        }

        return var3.HasSecondRenderLayerWhenWorn() ? (var1.isItemEnchanted() ? 31 : 16) : (var1.isItemEnchanted() ? 15 : 1);
    }

    private void LoadSecondLayerOfModArmorTexture(int var1, FCItemArmorMod var2)
    {
        this.loadTexture(var2.GetWornTextureDirectory() + var2.GetWornTexturePrefix() + "_" + (var1 == 2 ? 2 : 1) + "_b.png");
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
    }
}
