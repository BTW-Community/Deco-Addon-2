package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientGuiCraftingWorkbench extends GuiContainer
{
    private FCContainerWorkbench m_container;

    public FCClientGuiCraftingWorkbench(InventoryPlayer var1, World var2, int var3, int var4, int var5)
    {
        super(new FCContainerWorkbench(var1, var2, var3, var4, var5));
        this.m_container = (FCContainerWorkbench)this.inventorySlots;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.crafting"), 28, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
        this.DrawSecondaryOutputIndicator();
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/gui/crafting.png");
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
    }

    private void DrawSecondaryOutputIndicator()
    {
        IRecipe var1 = CraftingManager.getInstance().FindMatchingRecipe(this.m_container.craftMatrix, this.mc.theWorld);

        if (var1 != null && var1.HasSecondaryOutput())
        {
            Slot var2 = (Slot)this.m_container.inventorySlots.get(0);
            int var3 = var2.xDisplayPosition + 24;
            int var4 = var2.yDisplayPosition + 5;
            FCClientUtilsRender.DrawSecondaryCraftingOutputIndicator(this.mc, var3, var4);
        }
    }
}
