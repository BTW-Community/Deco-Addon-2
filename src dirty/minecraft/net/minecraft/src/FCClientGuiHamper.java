package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientGuiHamper extends GuiContainer
{
    private static final int m_iHamperGuiHeight = 149;
    private IInventory m_hamperInventory;

    public FCClientGuiHamper(InventoryPlayer var1, IInventory var2)
    {
        super(new FCContainerHamper(var1, var2));
        this.ySize = 149;
        this.m_hamperInventory = var2;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        String var3 = StatCollector.translateToLocal(this.m_hamperInventory.getInvName());
        this.fontRenderer.drawString(var3, this.xSize / 2 - this.fontRenderer.getStringWidth(var3) / 2, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/btwmodtex/fcGuiInv4.png");
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
    }
}
