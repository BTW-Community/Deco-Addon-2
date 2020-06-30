package net.minecraft.src;

import java.util.Iterator;

public class FCContainerCookingVessel extends FCContainerWithInventory
{
    private FCTileEntityCookingVessel m_AssociatedTileEntity;
    private int m_iLastCookCounter;

    public FCContainerCookingVessel(IInventory var1, FCTileEntityCookingVessel var2)
    {
        super(var1, var2, 3, 9, 8, 43, 8, 111);
        this.m_AssociatedTileEntity = var2;
        this.m_iLastCookCounter = 0;
    }

    public ItemStack slotClick(int var1, int var2, int var3, EntityPlayer var4)
    {
        ItemStack var5 = super.slotClick(var1, var2, var3, var4);
        this.m_AssociatedTileEntity.onInventoryChanged();
        return var5;
    }

    public void addCraftingToCrafters(ICrafting var1)
    {
        super.addCraftingToCrafters(var1);
        var1.sendProgressBarUpdate(this, 0, this.m_AssociatedTileEntity.m_iScaledCookCounter);
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

            if (this.m_iLastCookCounter != this.m_AssociatedTileEntity.m_iScaledCookCounter)
            {
                var2.sendProgressBarUpdate(this, 0, this.m_AssociatedTileEntity.m_iScaledCookCounter);
            }
        }

        this.m_iLastCookCounter = this.m_AssociatedTileEntity.m_iScaledCookCounter;
    }

    public void updateProgressBar(int var1, int var2)
    {
        if (var1 == 0)
        {
            this.m_AssociatedTileEntity.m_iScaledCookCounter = var2;
        }
    }
}
