package net.minecraft.src;

public class FCTileEntityBlockDispenser extends TileEntity implements IInventory
{
    private ItemStack[] dispenserContents = new ItemStack[16];
    public int iNextSlotIndexToDispense = 0;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 16;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.dispenserContents[var1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int var1, int var2)
    {
        return FCUtilsInventory.DecrStackSize(this, var1, var2);
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        if (this.dispenserContents[var1] != null)
        {
            ItemStack var2 = this.dispenserContents[var1];
            this.dispenserContents[var1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        super.onInventoryChanged();
        this.dispenserContents[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "BlockDispenser";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagList var2 = var1.getTagList("Items");
        this.dispenserContents = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.dispenserContents.length)
            {
                this.dispenserContents[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        if (var1.hasKey("iNextSlotIndexToDispense"))
        {
            this.iNextSlotIndexToDispense = var1.getInteger("iNextSlotIndexToDispense");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.dispenserContents.length; ++var3)
        {
            if (this.dispenserContents[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.dispenserContents[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        var1.setTag("Items", var2);
        var1.setInteger("iNextSlotIndexToDispense", this.iNextSlotIndexToDispense);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isStackValidForSlot(int var1, ItemStack var2)
    {
        return true;
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return true;
    }

    public ItemStack GetCurrentItemToDispense()
    {
        if (this.iNextSlotIndexToDispense >= this.dispenserContents.length || this.dispenserContents[this.iNextSlotIndexToDispense] == null)
        {
            int var1 = this.FindNextValidSlotIndex(this.iNextSlotIndexToDispense);

            if (var1 < 0)
            {
                return null;
            }

            this.iNextSlotIndexToDispense = var1;
        }

        ItemStack var2 = this.getStackInSlot(this.iNextSlotIndexToDispense).copy();
        var2.stackSize = 1;
        return var2;
    }

    public void OnDispenseCurrentSlot()
    {
        this.decrStackSize(this.iNextSlotIndexToDispense, 1);
        int var1 = this.FindNextValidSlotIndex(this.iNextSlotIndexToDispense);

        if (var1 < 0)
        {
            this.iNextSlotIndexToDispense = 0;
        }
        else
        {
            this.iNextSlotIndexToDispense = var1;
        }
    }

    private int FindNextValidSlotIndex(int var1)
    {
        int var2;

        for (var2 = var1 + 1; var2 < this.dispenserContents.length; ++var2)
        {
            if (this.dispenserContents[var2] != null)
            {
                return var2;
            }
        }

        for (var2 = 0; var2 < var1; ++var2)
        {
            if (this.dispenserContents[var2] != null)
            {
                return var2;
            }
        }

        if (this.dispenserContents[var1] != null)
        {
            return var1;
        }
        else
        {
            return -1;
        }
    }
}
