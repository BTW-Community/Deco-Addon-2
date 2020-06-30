package net.minecraft.src;

public class FCTileEntityBasketWicker extends FCTileEntityBasket implements FCITileEntityDataPacketHandler
{
    private ItemStack m_storageStack = null;

    public FCTileEntityBasketWicker()
    {
        super(FCBetterThanWolves.fcBlockBasketWicker);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        super.updateEntity();
        this.UpdateVisualContentsState();
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagCompound var2 = var1.getCompoundTag("fcStorageStack");

        if (var2 != null)
        {
            this.m_storageStack = ItemStack.loadItemStackFromNBT(var2);
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);

        if (this.m_storageStack != null)
        {
            NBTTagCompound var2 = new NBTTagCompound();
            this.m_storageStack.writeToNBT(var2);
            var1.setCompoundTag("fcStorageStack", var2);
        }
    }

    public void EjectContents()
    {
        if (this.m_storageStack != null)
        {
            FCUtilsItem.EjectStackWithRandomOffset(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.m_storageStack);
            this.m_storageStack = null;
        }
    }

    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();

        if (this.m_storageStack != null)
        {
            NBTTagCompound var2 = new NBTTagCompound();
            this.m_storageStack.writeToNBT(var2);
            var1.setCompoundTag("x", var2);
        }

        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }

    public void readNBTFromPacket(NBTTagCompound var1)
    {
        NBTTagCompound var2 = var1.getCompoundTag("x");

        if (var2 != null)
        {
            this.m_storageStack = ItemStack.loadItemStackFromNBT(var2);
        }
    }

    public boolean ShouldStartClosingServerSide()
    {
        return !this.worldObj.isRemote && this.worldObj.getClosestPlayer((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, 8.0D) == null;
    }

    public void SetStorageStack(ItemStack var1)
    {
        if (var1 != null)
        {
            this.m_storageStack = var1.copy();
        }
        else
        {
            this.m_storageStack = null;
        }

        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public ItemStack GetStorageStack()
    {
        return this.m_storageStack;
    }

    private void UpdateVisualContentsState()
    {
        if (!this.worldObj.isRemote)
        {
            boolean var1 = FCBetterThanWolves.fcBlockBasketWicker.GetHasContents(this.worldObj, this.xCoord, this.yCoord, this.zCoord);

            if (var1 != (this.m_storageStack != null))
            {
                FCBetterThanWolves.fcBlockBasketWicker.SetHasContents(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.m_storageStack != null);
            }
        }
    }
}
