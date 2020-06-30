package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class EntityPlayerSP extends EntityPlayer
{
    public MovementInput movementInput;
    protected Minecraft mc;

    /**
     * Used to tell if the player pressed forward twice. If this is at 0 and it's pressed (And they are allowed to
     * sprint, aka enough food on the ground etc) it sets this to 7. If it's pressed and it's greater than 0 enable
     * sprinting.
     */
    protected int sprintToggleTimer = 0;

    /** Ticks left before sprinting is disabled. */
    public int sprintingTicksLeft = 0;
    public float renderArmYaw;
    public float renderArmPitch;
    public float prevRenderArmYaw;
    public float prevRenderArmPitch;
    private MouseFilter field_71162_ch = new MouseFilter();
    private MouseFilter field_71160_ci = new MouseFilter();
    private MouseFilter field_71161_cj = new MouseFilter();

    /** The amount of time an entity has been in a Portal */
    public float timeInPortal;

    /** The amount of time an entity has been in a Portal the previous tick */
    public float prevTimeInPortal;
    private static final float m_fMinimumGloomCaveSoundChance = 0.01F;
    private static final float m_fMaximumGloomCaveSoundChance = 0.05F;
    private static final float m_fMinimumGloomCaveSoundVolume = 0.1F;
    private static final float m_fMaximumGloomCaveSoundVolume = 4.0F;
    private static final float m_fMinimumGloomGrowlSoundChance = 0.01F;
    private static final float m_fMaximumGloomGrowlSoundChance = 0.05F;
    private static final float m_fMinimumGloomGrowlSoundVolume = 0.1F;
    private static final float m_fMaximumGloomGrowlSoundVolume = 4.0F;
    private static final float m_fMaximumGloomFOVMultiplier = 1.5F;
    private static final float m_fGloomFOVMultiplierTimeForTransitionIn = 10.0F;
    private static final float m_fGloomFOVMultiplierTimeForTransitionOut = 2.0F;
    private static final float m_fGloomFOVMultiplierDeltaInPerTick = 0.0025F;
    private static final float m_fGloomFOVMultiplierDeltaOutPerTick = 0.0125F;
    private float m_fCurrentGloomFOVMultiplier = 1.0F;
    private int m_iPreviousGloomLevel = 0;
    public boolean m_bExhaustionAddedSinceLastGuiUpdate = false;

    public EntityPlayerSP(Minecraft par1Minecraft, World par2World, Session par3Session, int par4)
    {
        super(par2World);
        this.mc = par1Minecraft;
        this.dimension = par4;

        if (par3Session != null && par3Session.username != null && par3Session.username.length() > 0)
        {
            this.skinUrl = FCBetterThanWolves.fcPlayerSkinURL + StringUtils.stripControlCodes(par3Session.username) + ".png";
        }

        this.username = par3Session.username;
    }

    /**
     * Tries to moves the entity by the passed in displacement. Args: x, y, z
     */
    public void moveEntity(double par1, double par3, double par5)
    {
        super.moveEntity(par1, par3, par5);
    }

    public void updateEntityActionState()
    {
        super.updateEntityActionState();
        this.moveStrafing = this.movementInput.moveStrafe;
        this.moveForward = this.movementInput.moveForward;
        this.isJumping = this.movementInput.jump;
        this.prevRenderArmYaw = this.renderArmYaw;
        this.prevRenderArmPitch = this.renderArmPitch;
        this.renderArmPitch = (float)((double)this.renderArmPitch + (double)(this.rotationPitch - this.renderArmPitch) * 0.5D);
        this.renderArmYaw = (float)((double)this.renderArmYaw + (double)(this.rotationYaw - this.renderArmYaw) * 0.5D);
    }

    /**
     * Returns whether the entity is in a local (client) world
     */
    protected boolean isClientWorld()
    {
        return true;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (this.sprintingTicksLeft > 0)
        {
            --this.sprintingTicksLeft;

            if (this.sprintingTicksLeft == 0)
            {
                this.setSprinting(false);
            }
        }

        if (this.sprintToggleTimer > 0)
        {
            --this.sprintToggleTimer;
        }

        if (this.mc.playerController.enableEverythingIsScrewedUpMode())
        {
            this.posX = this.posZ = 0.5D;
            this.posX = 0.0D;
            this.posZ = 0.0D;
            this.rotationYaw = (float)this.ticksExisted / 12.0F;
            this.rotationPitch = 10.0F;
            this.posY = 68.5D;
        }
        else
        {
            if (!this.mc.statFileWriter.hasAchievementUnlocked(AchievementList.openInventory))
            {
                this.mc.guiAchievement.queueAchievementInformation(AchievementList.openInventory);
            }

            this.prevTimeInPortal = this.timeInPortal;

            if (this.inPortal)
            {
                if (this.mc.currentScreen != null)
                {
                    this.mc.displayGuiScreen((GuiScreen)null);
                }

                if (this.timeInPortal == 0.0F)
                {
                    this.mc.sndManager.playSoundFX("portal.trigger", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
                }

                this.timeInPortal += 0.0125F;

                if (this.timeInPortal >= 1.0F)
                {
                    this.timeInPortal = 1.0F;
                }

                this.inPortal = false;
            }
            else if (this.isPotionActive(Potion.confusion) && this.getActivePotionEffect(Potion.confusion).getDuration() > 60)
            {
                this.timeInPortal += 0.006666667F;

                if (this.timeInPortal > 1.0F)
                {
                    this.timeInPortal = 1.0F;
                }
            }
            else
            {
                if (this.timeInPortal > 0.0F)
                {
                    this.timeInPortal -= 0.05F;
                }

                if (this.timeInPortal < 0.0F)
                {
                    this.timeInPortal = 0.0F;
                }
            }

            if (this.timeUntilPortal > 0)
            {
                --this.timeUntilPortal;
            }

            boolean var1 = this.movementInput.jump;
            float var2 = 0.8F;
            boolean var3 = this.movementInput.moveForward >= var2;
            this.movementInput.updatePlayerMoveState();

            if (this.isUsingItem())
            {
                this.movementInput.moveStrafe *= 0.2F;
                this.movementInput.moveForward *= 0.2F;
                this.sprintToggleTimer = 0;
            }

            if (this.movementInput.sneak && this.ySize < 0.2F)
            {
                this.ySize = 0.2F;
            }

            this.pushOutOfBlocks(this.posX - (double)this.width * 0.35D, this.boundingBox.minY + 0.5D, this.posZ + (double)this.width * 0.35D);
            this.pushOutOfBlocks(this.posX - (double)this.width * 0.35D, this.boundingBox.minY + 0.5D, this.posZ - (double)this.width * 0.35D);
            this.pushOutOfBlocks(this.posX + (double)this.width * 0.35D, this.boundingBox.minY + 0.5D, this.posZ - (double)this.width * 0.35D);
            this.pushOutOfBlocks(this.posX + (double)this.width * 0.35D, this.boundingBox.minY + 0.5D, this.posZ + (double)this.width * 0.35D);
            boolean var4 = !this.HasStatusPenalty() || this.capabilities.allowFlying;

            if (this.onGround && !var3 && this.movementInput.moveForward >= var2 && !this.isSprinting() && var4 && !this.isUsingItem() && !this.isPotionActive(Potion.blindness))
            {
                if (this.sprintToggleTimer == 0)
                {
                    this.sprintToggleTimer = 7;
                }
                else
                {
                    this.setSprinting(true);
                    this.sprintToggleTimer = 0;
                }
            }

            if (this.isSneaking())
            {
                this.sprintToggleTimer = 0;
            }

            if (this.isSprinting() && (this.movementInput.moveForward < var2 || this.isCollidedHorizontally || !var4))
            {
                this.setSprinting(false);
            }

            if (this.capabilities.allowFlying && !var1 && this.movementInput.jump)
            {
                if (this.flyToggleTimer == 0)
                {
                    this.flyToggleTimer = 7;
                }
                else
                {
                    this.capabilities.isFlying = !this.capabilities.isFlying;
                    this.sendPlayerAbilities();
                    this.flyToggleTimer = 0;
                }
            }

            if (this.capabilities.isFlying)
            {
                if (this.movementInput.sneak)
                {
                    this.motionY -= 0.15D;
                }

                if (this.movementInput.jump)
                {
                    this.motionY += 0.15D;
                }
            }

            super.onLivingUpdate();

            if (this.onGround && this.capabilities.isFlying)
            {
                this.capabilities.isFlying = false;
                this.sendPlayerAbilities();
            }
        }
    }

    /**
     * Gets the player's field of view multiplier. (ex. when flying)
     */
    public float getFOVMultiplier()
    {
        float var1 = 1.0F;

        if (this.capabilities.isFlying)
        {
            var1 *= 1.1F;
        }

        var1 *= (this.landMovementFactor * this.getFOVSpeedModifier() / this.speedOnGround + 1.0F) / 2.0F;
        var1 *= this.UpdateGloomFOVMultiplier();

        if (this.isUsingItem() && (this.getItemInUse().itemID == Item.bow.itemID || this.getItemInUse().itemID == FCBetterThanWolves.fcItemCompositeBow.itemID))
        {
            int var2 = this.getItemInUseDuration();
            float var3 = (float)var2 / 20.0F;

            if (var3 > 1.0F)
            {
                var3 = 1.0F;
            }
            else
            {
                var3 *= var3;
            }

            var1 *= 1.0F - var3 * 0.15F;
        }

        return var1;
    }

    public void updateCloak()
    {
        this.cloakUrl = FCBetterThanWolves.fcPlayerCloakURL + StringUtils.stripControlCodes(this.username) + ".png";
    }

    /**
     * sets current screen to null (used on escape buttons of GUIs)
     */
    public void closeScreen()
    {
        super.closeScreen();
        this.mc.displayGuiScreen((GuiScreen)null);
    }

    /**
     * Displays the GUI for editing a sign. Args: tileEntitySign
     */
    public void displayGUIEditSign(TileEntity par1TileEntity)
    {
        if (par1TileEntity instanceof TileEntitySign)
        {
            this.mc.displayGuiScreen(new GuiEditSign((TileEntitySign)par1TileEntity));
        }
        else if (par1TileEntity instanceof TileEntityCommandBlock)
        {
            this.mc.displayGuiScreen(new GuiCommandBlock((TileEntityCommandBlock)par1TileEntity));
        }
    }

    /**
     * Displays the GUI for interacting with a book.
     */
    public void displayGUIBook(ItemStack par1ItemStack)
    {
        Item var2 = par1ItemStack.getItem();

        if (var2 == Item.writtenBook)
        {
            this.mc.displayGuiScreen(new GuiScreenBook(this, par1ItemStack, false));
        }
        else if (var2 == Item.writableBook)
        {
            this.mc.displayGuiScreen(new GuiScreenBook(this, par1ItemStack, true));
        }
        else if (var2 == FCBetterThanWolves.fcItemAncientProphecy)
        {
            this.mc.displayGuiScreen(new GuiScreenBook(this, par1ItemStack, false));
        }
    }

    /**
     * Displays the GUI for interacting with a chest inventory. Args: chestInventory
     */
    public void displayGUIChest(IInventory par1IInventory)
    {
        this.mc.displayGuiScreen(new GuiChest(this.inventory, par1IInventory));
    }

    public void displayGUIHopper(TileEntityHopper par1TileEntityHopper)
    {
        this.mc.displayGuiScreen(new GuiHopper(this.inventory, par1TileEntityHopper));
    }

    public void displayGUIHopperMinecart(EntityMinecartHopper par1EntityMinecartHopper)
    {
        this.mc.displayGuiScreen(new GuiHopper(this.inventory, par1EntityMinecartHopper));
    }

    /**
     * Displays the crafting GUI for a workbench.
     */
    public void displayGUIWorkbench(int par1, int par2, int par3)
    {
        this.mc.displayGuiScreen(new FCClientGuiCraftingWorkbench(this.inventory, this.worldObj, par1, par2, par3));
    }

    public void displayGUIEnchantment(int par1, int par2, int par3, String par4Str)
    {
        this.mc.displayGuiScreen(new GuiEnchantment(this.inventory, this.worldObj, par1, par2, par3, par4Str));
    }

    /**
     * Displays the GUI for interacting with an anvil.
     */
    public void displayGUIAnvil(int par1, int par2, int par3)
    {
        this.mc.displayGuiScreen(new GuiRepair(this.inventory, this.worldObj, par1, par2, par3));
    }

    /**
     * Displays the furnace GUI for the passed in furnace entity. Args: tileEntityFurnace
     */
    public void displayGUIFurnace(TileEntityFurnace par1TileEntityFurnace)
    {
        this.mc.displayGuiScreen(new GuiFurnace(this.inventory, par1TileEntityFurnace));
    }

    /**
     * Displays the GUI for interacting with a brewing stand.
     */
    public void displayGUIBrewingStand(TileEntityBrewingStand par1TileEntityBrewingStand)
    {
        this.mc.displayGuiScreen(new GuiBrewingStand(this.inventory, par1TileEntityBrewingStand));
    }

    /**
     * Displays the GUI for interacting with a beacon.
     */
    public void displayGUIBeacon(TileEntityBeacon par1TileEntityBeacon)
    {
        this.mc.displayGuiScreen(new GuiBeacon(this.inventory, par1TileEntityBeacon));
    }

    /**
     * Displays the dipsenser GUI for the passed in dispenser entity. Args: TileEntityDispenser
     */
    public void displayGUIDispenser(TileEntityDispenser par1TileEntityDispenser)
    {
        this.mc.displayGuiScreen(new GuiDispenser(this.inventory, par1TileEntityDispenser));
    }

    public void displayGUIMerchant(IMerchant par1IMerchant, String par2Str)
    {
        this.mc.displayGuiScreen(new GuiMerchant(this.inventory, par1IMerchant, this.worldObj, par2Str));
    }

    /**
     * Called when the player performs a critical hit on the Entity. Args: entity that was hit critically
     */
    public void onCriticalHit(Entity par1Entity)
    {
        this.mc.effectRenderer.addEffect(new EntityCrit2FX(this.mc.theWorld, par1Entity));
    }

    public void onEnchantmentCritical(Entity par1Entity)
    {
        EntityCrit2FX var2 = new EntityCrit2FX(this.mc.theWorld, par1Entity, "magicCrit");
        this.mc.effectRenderer.addEffect(var2);
    }

    /**
     * Called whenever an item is picked up from walking over it. Args: pickedUpEntity, stackSize
     */
    public void onItemPickup(Entity par1Entity, int par2)
    {
        this.mc.effectRenderer.addEffect(new EntityPickupFX(this.mc.theWorld, par1Entity, this, -0.5F));
    }

    /**
     * Returns if this entity is sneaking.
     */
    public boolean isSneaking()
    {
        return this.movementInput.sneak && !this.sleeping;
    }

    /**
     * Updates health locally.
     */
    public void setHealth(int par1)
    {
        int var2 = this.getHealth() - par1;

        if (var2 <= 0)
        {
            this.setEntityHealth(par1);

            if (var2 < 0)
            {
                this.hurtResistantTime = this.maxHurtResistantTime / 2;
            }
        }
        else
        {
            this.lastDamage = var2;
            this.setEntityHealth(this.getHealth());
            this.hurtResistantTime = this.maxHurtResistantTime;
            this.damageEntity(DamageSource.generic, var2);
            this.hurtTime = this.maxHurtTime = 10;
        }
    }

    /**
     * Add a chat message to the player
     */
    public void addChatMessage(String par1Str)
    {
        this.mc.ingameGUI.getChatGUI().addTranslatedMessage(par1Str, new Object[0]);
    }

    /**
     * Adds a value to a statistic field.
     */
    public void addStat(StatBase par1StatBase, int par2)
    {
        if (par1StatBase != null)
        {
            if (par1StatBase.isAchievement())
            {
                Achievement var3 = (Achievement)par1StatBase;

                if (var3.parentAchievement == null || this.mc.statFileWriter.hasAchievementUnlocked(var3.parentAchievement))
                {
                    if (!this.mc.statFileWriter.hasAchievementUnlocked(var3))
                    {
                        this.mc.guiAchievement.queueTakenAchievement(var3);
                    }

                    this.mc.statFileWriter.readStat(par1StatBase, par2);
                }
            }
            else
            {
                this.mc.statFileWriter.readStat(par1StatBase, par2);
            }
        }
    }

    private boolean isBlockTranslucent(int par1, int par2, int par3)
    {
        return this.worldObj.isBlockNormalCube(par1, par2, par3);
    }

    /**
     * Adds velocity to push the entity out of blocks at the specified x, y, z position Args: x, y, z
     */
    protected boolean pushOutOfBlocks(double par1, double par3, double par5)
    {
        int var7 = MathHelper.floor_double(par1);
        int var8 = MathHelper.floor_double(par3);
        int var9 = MathHelper.floor_double(par5);
        double var10 = par1 - (double)var7;
        double var12 = par5 - (double)var9;

        if (this.isBlockTranslucent(var7, var8, var9) || this.isBlockTranslucent(var7, var8 + 1, var9))
        {
            boolean var14 = !this.isBlockTranslucent(var7 - 1, var8, var9) && !this.isBlockTranslucent(var7 - 1, var8 + 1, var9);
            boolean var15 = !this.isBlockTranslucent(var7 + 1, var8, var9) && !this.isBlockTranslucent(var7 + 1, var8 + 1, var9);
            boolean var16 = !this.isBlockTranslucent(var7, var8, var9 - 1) && !this.isBlockTranslucent(var7, var8 + 1, var9 - 1);
            boolean var17 = !this.isBlockTranslucent(var7, var8, var9 + 1) && !this.isBlockTranslucent(var7, var8 + 1, var9 + 1);
            byte var18 = -1;
            double var19 = 9999.0D;

            if (var14 && var10 < var19)
            {
                var19 = var10;
                var18 = 0;
            }

            if (var15 && 1.0D - var10 < var19)
            {
                var19 = 1.0D - var10;
                var18 = 1;
            }

            if (var16 && var12 < var19)
            {
                var19 = var12;
                var18 = 4;
            }

            if (var17 && 1.0D - var12 < var19)
            {
                var19 = 1.0D - var12;
                var18 = 5;
            }

            float var21 = 0.1F;

            if (var18 == 0)
            {
                this.motionX = (double)(-var21);
            }

            if (var18 == 1)
            {
                this.motionX = (double)var21;
            }

            if (var18 == 4)
            {
                this.motionZ = (double)(-var21);
            }

            if (var18 == 5)
            {
                this.motionZ = (double)var21;
            }
        }

        return false;
    }

    /**
     * Set sprinting switch for Entity.
     */
    public void setSprinting(boolean par1)
    {
        super.setSprinting(par1);
        this.sprintingTicksLeft = par1 ? 600 : 0;
    }

    /**
     * Sets the current XP, total XP, and level number.
     */
    public void setXPStats(float par1, int par2, int par3)
    {
        this.experience = par1;
        this.experienceTotal = par2;
        this.experienceLevel = par3;
    }

    public void sendChatToPlayer(String par1Str)
    {
        this.mc.ingameGUI.getChatGUI().printChatMessage(par1Str);
    }

    /**
     * Returns true if the command sender is allowed to use the given command.
     */
    public boolean canCommandSenderUseCommand(int par1, String par2Str)
    {
        return par1 <= 0;
    }

    /**
     * Return the position for this command sender.
     */
    public ChunkCoordinates getPlayerCoordinates()
    {
        return new ChunkCoordinates(MathHelper.floor_double(this.posX + 0.5D), MathHelper.floor_double(this.posY + 0.5D), MathHelper.floor_double(this.posZ + 0.5D));
    }

    /**
     * Returns the item that this EntityLiving is holding, if any.
     */
    public ItemStack getHeldItem()
    {
        return this.inventory.getCurrentItem();
    }

    public void playSound(String par1Str, float par2, float par3)
    {
        this.worldObj.playSound(this.posX, this.posY - (double)this.yOffset, this.posZ, par1Str, par2, par3, false);
    }

    /**
     * increases exhaustion level by supplied amount
     */
    public void addExhaustion(float var1)
    {
        if (!this.capabilities.disableDamage)
        {
            this.m_bExhaustionAddedSinceLastGuiUpdate = true;
        }

        super.addExhaustion(var1);
    }

    public void AddExhaustionWithoutVisualFeedback(float var1)
    {
        super.addExhaustion(var1);
    }

    private float getFOVSpeedModifier()
    {
        float var1 = 1.0F;

        if (this.isPotionActive(Potion.moveSlowdown))
        {
            var1 *= 1.0F - 0.15F * (float)(this.getActivePotionEffect(Potion.moveSlowdown).getAmplifier() + 1);
        }

        return var1;
    }

    private float UpdateGloomFOVMultiplier()
    {
        int var1 = this.GetGloomLevel();

        if (var1 == 0)
        {
            this.m_fCurrentGloomFOVMultiplier -= 0.0125F;

            if (this.m_fCurrentGloomFOVMultiplier < 1.0F)
            {
                this.m_fCurrentGloomFOVMultiplier = 1.0F;
            }
        }
        else
        {
            this.m_fCurrentGloomFOVMultiplier += 0.0025F;

            if (this.m_fCurrentGloomFOVMultiplier > 1.5F)
            {
                this.m_fCurrentGloomFOVMultiplier = 1.5F;
            }
        }

        return this.m_fCurrentGloomFOVMultiplier;
    }

    protected void UpdateGloomState()
    {
        int var1 = this.GetGloomLevel();

        if (this.m_iPreviousGloomLevel != this.GetGloomLevel())
        {
            this.m_iInGloomCounter = 0;
            this.m_iPreviousGloomLevel = var1;

            if (var1 == 3)
            {
                this.playSound("mob.endermen.stare", 1.0F, 1.0F);
            }
        }

        if (var1 > 0)
        {
            ++this.m_iInGloomCounter;
            float var2 = (float)this.m_iInGloomCounter / 1200.0F;

            if (var2 > 1.0F)
            {
                var2 = 1.0F;
            }

            float var3 = 0.05F;
            float var4 = 4.0F;

            if (var1 > 1)
            {
                float var5 = 0.05F;
                float var6 = 4.0F;

                if (var1 <= 2)
                {
                    var5 = 0.01F + 0.04F * var2;
                    var6 = 0.1F + 3.9F * var2;
                }

                if (this.rand.nextFloat() < var5)
                {
                    this.PlaySoundInRandomDirection("mob.wolf.growl", var6, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F + 0.55F, 5.0D);
                }
            }
            else
            {
                var3 = 0.01F + 0.04F * var2;
                var4 = 0.1F + 3.9F * var2;
            }

            if (this.rand.nextFloat() < var3)
            {
                this.PlaySoundInRandomDirection("ambient.cave.cave", var4, 0.5F + this.rand.nextFloat(), 5.0D);
            }
        }
    }

    public void PlaySoundInRandomDirection(String var1, float var2, float var3, double var4)
    {
        double var6 = this.posX;
        double var8 = this.posY;
        double var10 = this.posZ;
        double var12 = this.rand.nextDouble();
        double var14 = (double)(-MathHelper.sin((float)(var12 * 360.0D))) * var4;
        double var16 = (double)MathHelper.cos((float)(var12 * 360.0D)) * var4;
        var6 += var14;
        var10 += var16;
        this.worldObj.playSound(var6, var8, var10, var1, var2, var3, false);
    }

    public boolean IsLocalPlayerAndHittingBlock()
    {
        return this.mc.playerController.IsHittingBlock();
    }
}
