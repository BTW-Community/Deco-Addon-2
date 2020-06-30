package net.minecraft.src;

import java.util.Iterator;

public class FCTileEntityHamper extends FCTileEntityBasket implements IInventory
{
    public static final int m_iInventorySize = 4;
    public static final String m_sUnlocalizedName = "container.fcHamper";
    private static final int m_iStackSizeLimit = 64;
    private static final double m_dMaxPlayerInteractionDistSq = 64.0D;
    private static final int m_iFullUpdateInterval = 200;
    private ItemStack[] m_Contents = new ItemStack[4];
    private int m_iNumUsingPlayers;
    private int m_iFullUpdateCounter = 0;

    public FCTileEntityHamper()
    {
        super(FCBetterThanWolves.fcBlockHamper);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagList var2 = var1.getTagList("Items");
        this.m_Contents = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.m_Contents.length)
            {
                this.m_Contents[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.m_Contents.length; ++var3)
        {
            if (this.m_Contents[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.m_Contents[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        var1.setTag("Items", var2);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        super.updateEntity();

        if (!this.worldObj.isRemote)
        {
            if (this.m_iNumUsingPlayers > 0 && !this.m_blockBasket.GetIsOpen(this.worldObj, this.xCoord, this.yCoord, this.zCoord))
            {
                this.worldObj.playSoundEffect((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "step.gravel", 0.25F + this.worldObj.rand.nextFloat() * 0.1F, 0.5F + this.worldObj.rand.nextFloat() * 0.1F);
                this.m_blockBasket.SetIsOpen(this.worldObj, this.xCoord, this.yCoord, this.zCoord, true);

                if (this.m_bClosing)
                {
                    this.m_bClosing = false;
                    this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 1, 0);
                }
            }

            ++this.m_iFullUpdateCounter;

            if ((this.m_iFullUpdateCounter + this.xCoord + this.yCoord + this.zCoord) % 200 == 0 && this.m_iNumUsingPlayers != 0)
            {
                int var1 = this.m_iNumUsingPlayers;
                this.m_iNumUsingPlayers = 0;
                Iterator var2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB((double)((float)this.xCoord - 8.0F), (double)((float)this.yCoord - 8.0F), (double)((float)this.zCoord - 8.0F), (double)((float)(this.xCoord + 1) + 8.0F), (double)((float)(this.yCoord + 1) + 8.0F), (double)((float)(this.zCoord + 1) + 8.0F))).iterator();

                while (var2.hasNext())
                {
                    EntityPlayer var3 = (EntityPlayer)var2.next();

                    if (var3.openContainer instanceof FCContainerHamper && ((FCContainerHamper)var3.openContainer).m_containerInventory == this)
                    {
                        ++this.m_iNumUsingPlayers;
                    }
                }
            }
        }
    }

    public void EjectContents()
    {
        FCUtilsInventory.EjectInventoryContents(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this);
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 4;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.m_Contents[var1];
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
        return null;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        this.m_Contents[var1] = var2;

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
        return "container.fcHamper";
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
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest()
    {
        if (this.m_iNumUsingPlayers < 0)
        {
            this.m_iNumUsingPlayers = 0;
        }

        ++this.m_iNumUsingPlayers;
    }

    public void closeChest()
    {
        --this.m_iNumUsingPlayers;
    }

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
        return false;
    }

    public boolean ShouldStartClosingServerSide()
    {
        return !this.worldObj.isRemote && this.m_iNumUsingPlayers <= 0;
    }
}
