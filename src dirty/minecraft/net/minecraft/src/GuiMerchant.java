package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiMerchant extends GuiContainer
{
    /** Instance of IMerchant interface. */
    private IMerchant theIMerchant;
    private GuiButtonMerchant nextRecipeButtonIndex;
    private GuiButtonMerchant previousRecipeButtonIndex;
    private int currentRecipeIndex = 0;
    private String field_94082_v;
    private int iCurrentNumValidRecipes = 0;
    private int iValidRecipesScrollOffset = 0;

    public GuiMerchant(InventoryPlayer par1, IMerchant par2, World par3World, String par4)
    {
        super(new ContainerMerchant(par1, par2, par3World));
        this.theIMerchant = par2;
        this.field_94082_v = par4 != null && par4.length() >= 1 ? par4 : StatCollector.translateToLocal("entity.Villager.name");
        this.ySize = 239;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        super.initGui();
        int var1 = (this.width - this.xSize) / 2;
        int var2 = (this.height - this.ySize) / 2;
        this.buttonList.add(this.nextRecipeButtonIndex = new GuiButtonMerchant(1, var1 + 144, var2 + 118, true));
        this.buttonList.add(this.previousRecipeButtonIndex = new GuiButtonMerchant(2, var1 + 34 - 14, var2 + 118, false));
        this.nextRecipeButtonIndex.drawButton = false;
        this.previousRecipeButtonIndex.drawButton = false;
        this.nextRecipeButtonIndex.enabled = false;
        this.previousRecipeButtonIndex.enabled = false;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(this.field_94082_v, this.xSize / 2 - this.fontRenderer.getStringWidth(this.field_94082_v) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/btwmodtex/fcguitrading.png");
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
        MerchantRecipeList var6 = this.theIMerchant.getRecipes(this.mc.thePlayer);

        if (var6 != null && !var6.isEmpty())
        {
            int var7 = this.currentRecipeIndex;
            MerchantRecipe var8 = (MerchantRecipe)var6.get(var7);

            if (var8.func_82784_g())
            {
                this.mc.renderEngine.bindTexture("/btwmodtex/fcguitrading.png");
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 21, 212, 0, 28, 21);
                this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 51, 212, 0, 28, 21);
            }
        }

        this.DrawXPDisplay();
    }

    /**
     * Gets the Instance of IMerchant interface.
     */
    public IMerchant getIMerchant()
    {
        return this.theIMerchant;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);
        MerchantRecipeList var4 = this.theIMerchant.getRecipes(this.mc.thePlayer);
        ContainerMerchant var5 = this.GetAssociatedContainerMerchant();

        if (var4 != null && !var4.isEmpty() && var5 != null)
        {
            int var6 = (this.width - this.xSize) / 2;
            int var7 = (this.height - this.ySize) / 2;
            GL11.glPushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            int var8 = var4.size();

            if (var8 > 8)
            {
                var8 = 8;
            }

            ItemStack var9 = null;
            int var10 = 18;

            for (int var11 = 0; var11 < var8; ++var11)
            {
                byte var12 = 8;

                if (var11 % 2 == 1)
                {
                    var12 = 98;
                }

                MerchantRecipe var13 = (MerchantRecipe)var4.get(var11);
                GL11.glEnable(GL11.GL_LIGHTING);
                ItemStack var14 = var13.getItemToBuy();
                ItemStack var15 = var13.getSecondItemToBuy();
                ItemStack var16 = var13.getItemToSell();
                itemRenderer.zLevel = 100.0F;
                itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, var14, var6 + var12, var7 + var10);
                itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.renderEngine, var14, var6 + var12, var7 + var10);

                if (var15 != null)
                {
                    itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, var15, var6 + var12 + 18, var7 + var10);
                    itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.renderEngine, var15, var6 + var12 + 18, var7 + var10);
                }

                itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, var16, var6 + var12 + 54, var7 + var10);
                itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.renderEngine, var16, var6 + var12 + 54, var7 + var10);
                itemRenderer.zLevel = 0.0F;
                GL11.glDisable(GL11.GL_LIGHTING);
                this.mc.renderEngine.bindTexture("/btwmodtex/fcguitrading.png");
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

                if (!var13.func_82784_g())
                {
                    this.drawTexturedModalRect(var6 + var12 + 36, var7 + var10, 176, 38, 16, 16);
                    String var17 = null;

                    if (var13.m_iTradeLevel < 0)
                    {
                        if (-var13.m_iTradeLevel == var5.m_iAssociatedVillagerTradeLevel)
                        {
                            var17 = "++";
                        }
                    }
                    else if (var13.m_iTradeLevel == var5.m_iAssociatedVillagerTradeLevel && var13.m_iTradeLevel < 5)
                    {
                        var17 = "+";
                    }

                    if (var17 != null)
                    {
                        int var18 = var6 + var12 + 45 - this.mc.fontRenderer.getStringWidth(var17) / 2;
                        int var19 = var7 + var10 + 5;
                        this.mc.fontRenderer.drawString(var17, var18 + 1, var19, 0);
                        this.mc.fontRenderer.drawString(var17, var18 - 1, var19, 0);
                        this.mc.fontRenderer.drawString(var17, var18, var19 + 1, 0);
                        this.mc.fontRenderer.drawString(var17, var18, var19 - 1, 0);
                        this.mc.fontRenderer.drawString(var17, var18, var19, 8453920);
                    }
                }
                else
                {
                    this.drawTexturedModalRect(var6 + var12 + 36, var7 + var10, 191, 38, 16, 16);
                }

                if (this.isPointInRegion(var12, var10, 16, 16, par1, par2))
                {
                    var9 = var14;
                }
                else if (var15 != null && this.isPointInRegion(var12 + 18, var10, 16, 16, par1, par2))
                {
                    var9 = var15;
                }
                else if (this.isPointInRegion(var12 + 53, var10, 16, 16, par1, par2))
                {
                    var9 = var16;
                }

                if (var11 % 2 == 1)
                {
                    var10 += 18;
                }
            }

            if (var9 != null)
            {
                this.drawItemStackTooltip(var9, par1, par2);
            }

            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
        }
    }

    ContainerMerchant GetAssociatedContainerMerchant()
    {
        return this.inventorySlots != null && this.inventorySlots instanceof ContainerMerchant ? (ContainerMerchant)this.inventorySlots : null;
    }

    private void DrawXPDisplay()
    {
        ContainerMerchant var1 = this.GetAssociatedContainerMerchant();

        if (var1 != null && var1.m_iAssociatedVillagerTradeLevel > 0)
        {
            this.mc.renderEngine.bindTexture("/btwmodtex/fcguitrading.png");
            int var2 = (this.width - this.xSize) / 2;
            int var3 = (this.height - this.ySize) / 2;
            int var6;

            if (var1.m_iAssociatedVillagerTradeMaxXP > 0)
            {
                short var4 = 151;
                float var5 = (float)var1.m_iAssociatedVillagerTradeXP / (float)var1.m_iAssociatedVillagerTradeMaxXP;

                if (var1.m_iAssociatedVillagerTradeLevel >= 5)
                {
                    var5 = 1.0F;
                }

                var6 = (int)(var5 * (float)var4);

                if (var6 > 0)
                {
                    this.drawTexturedModalRect(var2 + 12, var3 + 99, 0, 251, var6, 5);
                }
            }

            String var7 = "" + var1.m_iAssociatedVillagerTradeLevel;

            if (var1.m_iAssociatedVillagerTradeLevel >= 5)
            {
                var7 = "Max";
            }

            int var8 = var2 + 88 - this.mc.fontRenderer.getStringWidth(var7) / 2;
            var6 = var3 + 93;
            this.mc.fontRenderer.drawString(var7, var8 + 1, var6, 0);
            this.mc.fontRenderer.drawString(var7, var8 - 1, var6, 0);
            this.mc.fontRenderer.drawString(var7, var8, var6 + 1, 0);
            this.mc.fontRenderer.drawString(var7, var8, var6 - 1, 0);
            this.mc.fontRenderer.drawString(var7, var8, var6, 8453920);
        }
    }

    private int IsEmeraldOnlyBuyTrade(MerchantRecipe var1)
    {
        if (var1.getSecondItemToBuy() == null)
        {
            ItemStack var2 = var1.getItemToBuy();

            if (var2 != null && var2.itemID == Item.emerald.itemID)
            {
                return var2.stackSize;
            }
        }

        return 0;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
        int var1 = this.currentRecipeIndex;
        MerchantRecipeList var2 = this.theIMerchant.getRecipes(this.mc.thePlayer);
        ContainerMerchant var3 = this.GetAssociatedContainerMerchant();
        this.iCurrentNumValidRecipes = 0;

        if (var2 != null && !var2.isEmpty() && var3 != null)
        {
            InventoryMerchant var4 = var3.getMerchantInventory();
            ItemStack var5 = var4.getStackInSlot(0);
            ItemStack var6 = var4.getStackInSlot(1);

            if (var5 != null || var6 != null)
            {
                int var7 = var2.size();

                for (int var8 = 0; var8 < var7; ++var8)
                {
                    if (var5 != null && var2.canRecipeBeUsed(var5, var6, var8) != null || var6 != null && var2.canRecipeBeUsed(var6, var5, var8) != null)
                    {
                        ++this.iCurrentNumValidRecipes;
                        MerchantRecipe var9 = (MerchantRecipe)var2.get(var8);
                        int var10 = this.IsEmeraldOnlyBuyTrade(var9);

                        if (var10 <= 0)
                        {
                            this.iValidRecipesScrollOffset = 0;
                            this.currentRecipeIndex = var8;
                            break;
                        }

                        if (this.iCurrentNumValidRecipes <= this.iValidRecipesScrollOffset + 1)
                        {
                            this.currentRecipeIndex = var8;
                        }
                    }
                }

                if (var1 != this.currentRecipeIndex)
                {
                    var3.setCurrentRecipeIndex(this.currentRecipeIndex);
                    ByteArrayOutputStream var12 = new ByteArrayOutputStream();
                    DataOutputStream var13 = new DataOutputStream(var12);

                    try
                    {
                        var13.writeInt(this.currentRecipeIndex);
                        this.mc.getNetHandler().addToSendQueue(new Packet250CustomPayload("MC|TrSel", var12.toByteArray()));
                    }
                    catch (Exception var11)
                    {
                        var11.printStackTrace();
                    }
                }
            }
        }

        if (this.iCurrentNumValidRecipes <= 0 || this.iValidRecipesScrollOffset >= this.iCurrentNumValidRecipes)
        {
            this.iValidRecipesScrollOffset = 0;
        }

        if (this.iCurrentNumValidRecipes > 1)
        {
            this.nextRecipeButtonIndex.drawButton = true;
            this.previousRecipeButtonIndex.drawButton = true;
            this.nextRecipeButtonIndex.enabled = this.iValidRecipesScrollOffset < this.iCurrentNumValidRecipes - 1;
            this.previousRecipeButtonIndex.enabled = this.iValidRecipesScrollOffset > 0;
        }
        else
        {
            this.nextRecipeButtonIndex.drawButton = false;
            this.previousRecipeButtonIndex.drawButton = false;
            this.nextRecipeButtonIndex.enabled = false;
            this.previousRecipeButtonIndex.enabled = false;
        }
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton == this.nextRecipeButtonIndex)
        {
            if (this.iValidRecipesScrollOffset < this.iCurrentNumValidRecipes - 1)
            {
                ++this.iValidRecipesScrollOffset;
            }
        }
        else if (par1GuiButton == this.previousRecipeButtonIndex && this.iValidRecipesScrollOffset > 0)
        {
            --this.iValidRecipesScrollOffset;
        }
    }
}
