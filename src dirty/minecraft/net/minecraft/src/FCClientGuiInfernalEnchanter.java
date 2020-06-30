package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientGuiInfernalEnchanter extends GuiContainer
{
    private static final int m_iGuiHeight = 210;
    private static final int m_iScrollIconScreenPosX = 17;
    private static final int m_iScrollIconScreenPosY = 37;
    private static final int m_iScrollIconBitmapPosX = 176;
    private static final int m_iScrollIconBitmapPosY = 0;
    private static final int m_iItemIconScreenPosX = 17;
    private static final int m_iItemIconScreenPosY = 75;
    private static final int m_iItemIconBitmapPosX = 192;
    private static final int m_iItemIconBitmapPosY = 0;
    private static final int m_iEnchantmentButtonsPosX = 60;
    private static final int m_iEnchantmentButtonsPosY = 17;
    private static final int m_iEnchantmentButtonsHeight = 19;
    private static final int m_iEnchantmentButtonsWidth = 108;
    private static final int m_iEnchantmentButtonNormalPosX = 0;
    private static final int m_iEnchantmentButtonNormalPosY = 211;
    private static final int m_iEnchantmentButtonInactivePosX = 0;
    private static final int m_iEnchantmentButtonInactivePosY = 230;
    private static final int m_iEnchantmentButtonHighlightedPosX = 108;
    private static final int m_iEnchantmentButtonHighlightedPosY = 211;
    private FCContainerInfernalEnchanter m_container;

    public FCClientGuiInfernalEnchanter(InventoryPlayer var1, World var2, int var3, int var4, int var5)
    {
        super(new FCContainerInfernalEnchanter(var1, var2, var3, var4, var5));
        this.ySize = 210;
        this.m_container = (FCContainerInfernalEnchanter)this.inventorySlots;
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        super.onGuiClosed();
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int var1, int var2, int var3)
    {
        super.mouseClicked(var1, var2, var3);
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;

        for (int var6 = 0; var6 < 5; ++var6)
        {
            int var7 = var1 - (var4 + 60);
            int var8 = var2 - (var5 + 17 + 19 * var6);

            if (var7 >= 0 && var8 >= 0 && var7 < 108 && var8 < 19)
            {
                this.mc.playerController.sendEnchantPacket(this.m_container.windowId, var6);
            }
        }
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        this.fontRenderer.drawString("Infernal Enchanter", 40, 5, 4210752);
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
        int var3 = 0;

        while (true)
        {
            FCContainerInfernalEnchanter var10001 = this.m_container;

            if (var3 >= 5)
            {
                return;
            }

            String var4 = StatCollector.translateToLocal("enchantment.level." + (var3 + 1));
            this.fontRenderer.drawString(var4, 45, 17 + var3 * 19 + 6, 4210752);
            ++var3;
        }
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        RenderHelper.disableStandardItemLighting();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/btwmodtex/fcguiinfernal.png");
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
        this.mc.renderEngine.bindTexture("/btwmodtex/fcguiinfernal.png");
        ItemStack var6 = this.m_container.m_tableInventory.getStackInSlot(0);

        if (var6 == null)
        {
            this.drawTexturedModalRect(var4 + 17, var5 + 37, 176, 0, 16, 16);
        }

        ItemStack var7 = this.m_container.m_tableInventory.getStackInSlot(1);

        if (var7 == null)
        {
            this.drawTexturedModalRect(var4 + 17, var5 + 75, 192, 0, 16, 16);
        }

        EnchantmentNameParts.instance.setRandSeed(this.m_container.m_lNameSeed);
        int var8 = 0;

        while (true)
        {
            FCContainerInfernalEnchanter var10001 = this.m_container;

            if (var8 >= 5)
            {
                return;
            }

            String var9 = EnchantmentNameParts.instance.generateRandomEnchantName();
            int var10 = this.m_container.m_CurrentEnchantmentLevels[var8];
            boolean var11 = var10 <= this.mc.thePlayer.experienceLevel && var10 <= this.m_container.m_iMaxSurroundingBookshelfLevel;
            int var12 = 6839882;
            int var13 = 8453920;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.renderEngine.bindTexture("/btwmodtex/fcguiinfernal.png");

            if (var10 > 0 && var11)
            {
                if (this.IsMouseOverEnchantmentButton(var8, var2, var3))
                {
                    this.drawTexturedModalRect(var4 + 60, var5 + 17 + 19 * var8, 108, 211, 108, 19);
                    var12 = 16777088;
                }
            }
            else
            {
                this.drawTexturedModalRect(var4 + 60, var5 + 17 + 19 * var8, 0, 230, 108, 19);
                var12 = (var12 & 16711422) >> 1;
                var13 = 4226832;
            }

            if (var10 > 0)
            {
                FontRenderer var14 = this.mc.standardGalacticFontRenderer;
                var14.drawSplitString(var9, var4 + 60 + 2, var5 + 2 + 17 + 19 * var8, 104, var12);
                var14 = this.mc.fontRenderer;
                String var15 = "" + var10;
                var14.drawStringWithShadow(var15, var4 + 60 + 108 - 2 - var14.getStringWidth(var15), var5 + 9 + 17 + 19 * var8, var13);
            }

            ++var8;
        }
    }

    private boolean IsMouseOverEnchantmentButton(int var1, int var2, int var3)
    {
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        int var6 = var2 - var4;
        int var7 = var3 - var5;
        int var8 = 17 + 19 * var1;
        return var6 >= 60 && var6 <= 168 && var7 >= var8 && var7 <= var8 + 19;
    }
}
