package net.minecraft.src;

import java.util.Iterator;

public class FCContainerHopper extends Container
{
    private final int iNumHopperSlotRows = 2;
    private final int iNumHopperSlotColumns = 9;
    private final int iNumHopperSlots = 18;
    private FCTileEntityHopper localTileEntityHopper;
    private int m_iLastMechanicalPowerIndicator;

    public FCContainerHopper(IInventory var1, FCTileEntityHopper var2)
    {
        this.localTileEntityHopper = var2;
        this.m_iLastMechanicalPowerIndicator = 0;
        int var3;
        int var4;

        for (var3 = 0; var3 < 2; ++var3)
        {
            for (var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(var2, var4 + var3 * 9, 8 + var4 * 18, 60 + var3 * 18));
            }
        }

        this.addSlotToContainer(new Slot(var2, 18, 80, 37));

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 111 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(var1, var3, 8 + var3 * 18, 169));
        }
    }

    public boolean canInteractWith(EntityPlayer var1)
    {
        return this.localTileEntityHopper.isUseableByPlayer(var1);
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    public ItemStack transferStackInSlot(EntityPlayer var1, int var2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(var2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (var2 < 19)
            {
                if (!this.mergeItemStack(var5, 19, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 0, 18, false))
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

    public void onCraftGuiOpened(ICrafting var1)
    {
        super.onCraftGuiOpened(var1);
        var1.sendProgressBarUpdate(this, 0, this.localTileEntityHopper.m_iMechanicalPowerIndicator);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        Iterator var1 = this.crafters.iterator();

        while (var1.hasNext())
        {
            ICrafting var2 = (ICrafting)var1.next();

            if (this.m_iLastMechanicalPowerIndicator != this.localTileEntityHopper.m_iMechanicalPowerIndicator)
            {
                var2.sendProgressBarUpdate(this, 0, this.localTileEntityHopper.m_iMechanicalPowerIndicator);
            }
        }

        this.m_iLastMechanicalPowerIndicator = this.localTileEntityHopper.m_iMechanicalPowerIndicator;
    }
}
