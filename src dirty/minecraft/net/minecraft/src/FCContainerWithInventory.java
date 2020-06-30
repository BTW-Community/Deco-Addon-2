package net.minecraft.src;

public class FCContainerWithInventory extends Container
{
    protected IInventory m_containerInventory;
    private int m_iNumSlotRows;
    private int m_iNumSlotColumns;
    private int m_iNumSlots;

    public FCContainerWithInventory(IInventory var1, IInventory var2, int var3, int var4, int var5, int var6, int var7, int var8)
    {
        this.m_containerInventory = var2;
        this.m_iNumSlotRows = var3;
        this.m_iNumSlotColumns = var4;
        this.m_iNumSlots = this.m_iNumSlotRows * this.m_iNumSlotColumns;
        int var9;
        int var10;

        for (var9 = 0; var9 < this.m_iNumSlotRows; ++var9)
        {
            for (var10 = 0; var10 < this.m_iNumSlotColumns; ++var10)
            {
                this.addSlotToContainer(new Slot(var2, var10 + var9 * this.m_iNumSlotColumns, var5 + var10 * 18, var6 + var9 * 18));
            }
        }

        for (var9 = 0; var9 < 3; ++var9)
        {
            for (var10 = 0; var10 < 9; ++var10)
            {
                this.addSlotToContainer(new Slot(var1, var10 + var9 * 9 + 9, var7 + var10 * 18, var8 + var9 * 18));
            }
        }

        for (var9 = 0; var9 < 9; ++var9)
        {
            this.addSlotToContainer(new Slot(var1, var9, var7 + var9 * 18, var8 + 58));
        }
    }

    public boolean canInteractWith(EntityPlayer var1)
    {
        return this.m_containerInventory.isUseableByPlayer(var1);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer var1, int var2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(var2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (var2 < this.m_iNumSlots)
            {
                if (!this.mergeItemStack(var5, this.m_iNumSlots, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 0, this.m_iNumSlots, false))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }
        }

        return var3;
    }
}
