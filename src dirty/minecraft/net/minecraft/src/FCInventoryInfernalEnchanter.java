package net.minecraft.src;

class FCInventoryInfernalEnchanter extends InventoryBasic
{
    final FCContainerInfernalEnchanter m_container;

    FCInventoryInfernalEnchanter(FCContainerInfernalEnchanter var1, String var2, int var3)
    {
        super(var2, true, var3);
        this.m_container = var1;
    }

    /**
     * Called when an the contents of an Inventory change, usually
     */
    public void onInventoryChanged()
    {
        super.onInventoryChanged();
        this.m_container.onCraftMatrixChanged(this);
    }
}
