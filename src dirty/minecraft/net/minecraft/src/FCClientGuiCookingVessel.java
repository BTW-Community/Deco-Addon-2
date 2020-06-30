package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientGuiCookingVessel extends GuiContainer
{
    static final int m_iGuiHeight = 193;
    static final int m_iFireIconHeight = 12;
    private FCTileEntityCookingVessel m_AssociatedTileEntity;
    private int m_iContainerID;

    public FCClientGuiCookingVessel(InventoryPlayer var1, FCTileEntityCookingVessel var2, int var3)
    {
        super(new FCContainerCookingVessel(var1, var2));
        this.ySize = 193;
        this.m_AssociatedTileEntity = var2;
        this.m_iContainerID = var3;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        if (this.m_iContainerID == FCBetterThanWolves.fcCrucibleContainerID)
        {
            this.fontRenderer.drawString("Crucible", 66, 6, 4210752);
        }
        else
        {
            this.fontRenderer.drawString("Cauldron", 66, 6, 4210752);
        }

        this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/btwmodtex/fccauldron.png");
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);

        if (this.m_AssociatedTileEntity.IsCooking())
        {
            int var6 = this.m_AssociatedTileEntity.getCookProgressScaled(12);
            this.drawTexturedModalRect(var4 + 81, var5 + 19 + 12 - var6, 176, 12 - var6, 14, var6 + 2);
        }
    }
}
