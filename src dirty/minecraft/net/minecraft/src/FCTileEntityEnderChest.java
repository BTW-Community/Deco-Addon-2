package net.minecraft.src;

public class FCTileEntityEnderChest extends TileEntityEnderChest
{
    private InventoryEnderChest m_localChestInventory = new InventoryEnderChest();

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);

        if (var1.hasKey("FCEnderItems"))
        {
            NBTTagList var2 = var1.getTagList("FCEnderItems");
            this.m_localChestInventory.loadInventoryFromNBT(var2);
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);

        if (this.m_localChestInventory != null)
        {
            var1.setTag("FCEnderItems", this.m_localChestInventory.saveInventoryToNBT());
        }
    }

    public InventoryEnderChest GetLocalEnderChestInventory()
    {
        return this.m_localChestInventory;
    }
}
