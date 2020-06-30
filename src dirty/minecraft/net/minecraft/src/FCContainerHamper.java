package net.minecraft.src;

public class FCContainerHamper extends FCContainerWithInventory
{
    private static final int m_iInvetoryRows = 2;
    private static final int m_iInvetoryColumns = 2;
    private static final int m_iContainerInventoryDisplayX = 71;
    private static final int m_iContainerInventoryDisplayY = 17;
    private static final int m_iPlayerInventoryDisplayX = 8;
    private static final int m_iPlayerInventoryDisplayY = 67;

    public FCContainerHamper(IInventory var1, IInventory var2)
    {
        super(var1, var2, 2, 2, 71, 17, 8, 67);
        var2.openChest();
    }

    /**
     * Callback for when the crafting gui is closed.
     */
    public void onCraftGuiClosed(EntityPlayer var1)
    {
        super.onCraftGuiClosed(var1);
        this.m_containerInventory.closeChest();
    }
}
