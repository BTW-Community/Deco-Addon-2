package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientGuiCraftingAnvil extends FCClientGuiCraftingWorkbench
{
    public FCClientGuiCraftingAnvil(InventoryPlayer var1, World var2, int var3, int var4, int var5)
    {
        super(var1, var2, var3, var4, var5);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/btwmodtex/fcGuiAnvilVanilla.png");
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
    }
}
