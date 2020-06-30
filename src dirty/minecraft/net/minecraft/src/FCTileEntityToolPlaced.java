package net.minecraft.src;

public class FCTileEntityToolPlaced extends TileEntity implements FCITileEntityDataPacketHandler
{
    private ItemStack m_toolStack = null;

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagCompound var2 = var1.getCompoundTag("fcToolStack");

        if (var2 != null)
        {
            this.m_toolStack = ItemStack.loadItemStackFromNBT(var2);
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);

        if (this.m_toolStack != null)
        {
            NBTTagCompound var2 = new NBTTagCompound();
            this.m_toolStack.writeToNBT(var2);
            var1.setCompoundTag("fcToolStack", var2);
        }
    }

    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();

        if (this.m_toolStack != null)
        {
            NBTTagCompound var2 = new NBTTagCompound();
            this.m_toolStack.writeToNBT(var2);
            var1.setCompoundTag("x", var2);
        }

        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }

    public void readNBTFromPacket(NBTTagCompound var1)
    {
        NBTTagCompound var2 = var1.getCompoundTag("x");

        if (var2 != null)
        {
            this.m_toolStack = ItemStack.loadItemStackFromNBT(var2);
        }

        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }

    public void SetToolStack(ItemStack var1)
    {
        if (var1 != null)
        {
            this.m_toolStack = var1.copy();
        }
        else
        {
            this.m_toolStack = null;
        }
    }

    public ItemStack GetToolStack()
    {
        return this.m_toolStack;
    }

    public void EjectContents()
    {
        if (this.m_toolStack != null)
        {
            FCUtilsItem.EjectStackWithRandomOffset(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.m_toolStack);
            this.m_toolStack = null;
        }
    }
}
