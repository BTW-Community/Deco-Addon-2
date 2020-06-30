package net.minecraft.src;

public class FCContainerWorkbench extends ContainerWorkbench
{
    public World m_world;
    public int m_iBlockI;
    public int m_iBlockJ;
    public int m_iBlockK;

    public FCContainerWorkbench(InventoryPlayer var1, World var2, int var3, int var4, int var5)
    {
        super(var1, var2, var3, var4, var5);
        this.m_world = var2;
        this.m_iBlockI = var3;
        this.m_iBlockJ = var4;
        this.m_iBlockK = var5;
    }

    public boolean canInteractWith(EntityPlayer var1)
    {
        int var2 = this.m_world.getBlockId(this.m_iBlockI, this.m_iBlockJ, this.m_iBlockK);
        return (var2 == FCBetterThanWolves.fcBlockWorkStump.blockID || var2 == FCBetterThanWolves.fcBlockWorkbench.blockID || var2 == Block.anvil.blockID || var2 == Block.workbench.blockID) && var1.getDistanceSq((double)this.m_iBlockI + 0.5D, (double)this.m_iBlockJ + 0.5D, (double)this.m_iBlockK + 0.5D) <= 64.0D;
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

            if (var2 == 0)
            {
                if (!this.mergeItemStack(var5, 10, 46, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (var2 >= 10 && var2 < 37)
            {
                if (!this.mergeItemStack(var5, 1, 10, false))
                {
                    return null;
                }
            }
            else if (var2 >= 37 && var2 < 46)
            {
                if (!this.mergeItemStack(var5, 1, 10, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 10, 46, true))
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

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(var1, var5);
        }

        return var3;
    }
}
