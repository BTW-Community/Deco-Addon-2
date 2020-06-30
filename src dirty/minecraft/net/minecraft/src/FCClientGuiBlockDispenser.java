package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientGuiBlockDispenser extends GuiContainer
{
    static final int iSelectionIconHeight = 20;
    static final int iGuiHeight = 182;
    private FCTileEntityBlockDispenser associatedTileEntityBlockDispenser;

    public FCClientGuiBlockDispenser(InventoryPlayer var1, FCTileEntityBlockDispenser var2)
    {
        super(new FCContainerBlockDispenser(var1, var2));
        this.associatedTileEntityBlockDispenser = var2;
        this.ySize = 182;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        this.fontRenderer.drawString("Block Dispenser", 48, 6, 4210752);
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 94 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/btwmodtex/fcguiblockdisp.png");
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
        int var6 = this.associatedTileEntityBlockDispenser.iNextSlotIndexToDispense % 4 * 18;
        int var7 = this.associatedTileEntityBlockDispenser.iNextSlotIndexToDispense / 4 * 18;
        this.drawTexturedModalRect(var4 + 51 + var6, var5 + 15 + var7, 176, 0, 20, 20);
    }
}
