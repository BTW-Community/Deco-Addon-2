package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientGuiHopper extends GuiContainer
{
    static final int iHopperGuiHeight = 193;
    static final int iHopperMachineIconHeight = 14;
    private FCTileEntityHopper associatedTileEntityHopper;

    public FCClientGuiHopper(InventoryPlayer var1, FCTileEntityHopper var2)
    {
        super(new FCContainerHopper(var1, var2));
        this.ySize = 193;
        this.associatedTileEntityHopper = var2;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        this.fontRenderer.drawString("Hopper", 70, 6, 4210752);
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/btwmodtex/fcHopper.png");
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);

        if (this.associatedTileEntityHopper.m_iMechanicalPowerIndicator > 0)
        {
            this.drawTexturedModalRect(var4 + 80, var5 + 18, 176, 0, 14, 14);
        }
    }
}
