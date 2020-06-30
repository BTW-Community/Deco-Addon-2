package net.minecraft.src;

public class FCTileEntityPulley extends TileEntity implements IInventory
{
    private final int iPulleyInventorySize = 4;
    private final int iPulleyStackSizeLimit = 64;
    private final double dPulleyMaxPlayerInteractionDist = 64.0D;
    private final int iTicksToUpdateRopeState = 20;
    private boolean m_bHasAssociatedAnchorEntity = false;
    private ItemStack[] pulleyContents = new ItemStack[4];
    public int iUpdateRopeStateCounter = 20;
    public int m_iMechanicalPowerIndicator = 0;

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Pulley";
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 4;
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
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.pulleyContents[var1];
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        this.pulleyContents[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
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
        if (this.pulleyContents[var1] != null)
        {
            ItemStack var2 = this.pulleyContents[var1];
            this.pulleyContents[var1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    public void openChest() {}

    public void closeChest() {}

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagList var2 = var1.getTagList("Items");
        this.pulleyContents = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.pulleyContents.length)
            {
                this.pulleyContents[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        if (var1.hasKey("iUpdateRopeStateCounter"))
        {
            this.iUpdateRopeStateCounter = var1.getInteger("iUpdateRopeStateCounter");
        }

        if (var1.hasKey("m_bHasAssociatedAnchorEntity"))
        {
            this.m_bHasAssociatedAnchorEntity = var1.getBoolean("m_bHasAssociatedAnchorEntity");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.pulleyContents.length; ++var3)
        {
            if (this.pulleyContents[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.pulleyContents[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        var1.setTag("Items", var2);
        var1.setInteger("iUpdateRopeStateCounter", this.iUpdateRopeStateCounter);
        var1.setBoolean("m_bHasAssociatedAnchorEntity", this.m_bHasAssociatedAnchorEntity);
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
        {
            if (this.IsMechanicallyPowered())
            {
                this.m_iMechanicalPowerIndicator = 1;
            }
            else
            {
                this.m_iMechanicalPowerIndicator = 0;
            }

            --this.iUpdateRopeStateCounter;

            if (this.iUpdateRopeStateCounter <= 0)
            {
                if (!this.m_bHasAssociatedAnchorEntity)
                {
                    boolean var1 = ((FCBlockPulley)FCBetterThanWolves.fcPulley).IsRedstoneOn(this.worldObj, this.xCoord, this.yCoord, this.zCoord);

                    if (!var1)
                    {
                        boolean var2 = ((FCBlockPulley)FCBetterThanWolves.fcPulley).IsBlockOn(this.worldObj, this.xCoord, this.yCoord, this.zCoord);

                        if (var2)
                        {
                            this.AttemptToRetractRope();
                        }
                        else
                        {
                            this.AttemptToDispenseRope();
                        }
                    }
                }

                this.iUpdateRopeStateCounter = 20;
            }
        }
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
        return true;
    }

    private boolean IsMechanicallyPowered()
    {
        return ((FCBlockPulley)FCBetterThanWolves.fcPulley).IsBlockOn(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    private boolean IsRedstonePowered()
    {
        return ((FCBlockPulley)FCBetterThanWolves.fcPulley).IsRedstoneOn(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    public boolean IsRaising()
    {
        return !this.IsRedstonePowered() && this.IsMechanicallyPowered();
    }

    public boolean IsLowering()
    {
        return !this.IsRedstonePowered() && !this.IsMechanicallyPowered() && FCUtilsInventory.GetFirstOccupiedStackOfItem(this, FCBetterThanWolves.fcItemRope.itemID) >= 0;
    }

    public void NotifyPulleyEntityOfBlockStateChange()
    {
        this.iUpdateRopeStateCounter = 20;
        this.NotifyAttachedAnchorOfEntityStateChange();
    }

    private void NotifyAttachedAnchorOfEntityStateChange()
    {
        for (int var1 = this.yCoord - 1; var1 >= 0; --var1)
        {
            int var2 = this.worldObj.getBlockId(this.xCoord, var1, this.zCoord);

            if (var2 == FCBetterThanWolves.fcAnchor.blockID)
            {
                if (((FCBlockAnchor)((FCBlockAnchor)FCBetterThanWolves.fcAnchor)).GetFacing(this.worldObj, this.xCoord, var1, this.zCoord) != 1)
                {
                    break;
                }

                if (((FCBlockAnchor)((FCBlockAnchor)FCBetterThanWolves.fcAnchor)).NotifyAnchorBlockOfAttachedPulleyStateChange(this, this.worldObj, this.xCoord, var1, this.zCoord))
                {
                    this.m_bHasAssociatedAnchorEntity = true;
                }
            }
            else if (var2 != FCBetterThanWolves.fcRopeBlock.blockID)
            {
                break;
            }
        }
    }

    boolean AttemptToRetractRope()
    {
        for (int var1 = this.yCoord - 1; var1 >= 0; --var1)
        {
            int var2 = this.worldObj.getBlockId(this.xCoord, var1, this.zCoord);

            if (var2 != FCBetterThanWolves.fcRopeBlock.blockID)
            {
                return false;
            }

            if (this.worldObj.getBlockId(this.xCoord, var1 - 1, this.zCoord) != FCBetterThanWolves.fcRopeBlock.blockID)
            {
                this.AddRopeToInventory();
                Block var3 = FCBetterThanWolves.fcRopeBlock;
                this.worldObj.playSoundEffect((double)((float)this.xCoord + 0.5F), (double)((float)var1 + 0.5F), (double)((float)this.zCoord + 0.5F), var3.stepSound.getStepSound(), var3.stepSound.getVolume() / 4.0F, var3.stepSound.getPitch() * 0.8F);
                this.worldObj.setBlockWithNotify(this.xCoord, var1, this.zCoord, 0);
                return true;
            }
        }

        return false;
    }

    public boolean AttemptToDispenseRope()
    {
        int var1 = FCUtilsInventory.GetFirstOccupiedStackOfItem(this, FCBetterThanWolves.fcItemRope.itemID);
        this.iUpdateRopeStateCounter = 20;

        if (var1 >= 0)
        {
            for (int var2 = this.yCoord - 1; var2 >= 0; --var2)
            {
                int var3 = this.worldObj.getBlockId(this.xCoord, var2, this.zCoord);

                if (FCUtilsWorld.IsReplaceableBlock(this.worldObj, this.xCoord, var2, this.zCoord))
                {
                    int var4 = FCBetterThanWolves.fcRopeBlock.onBlockPlaced(this.worldObj, this.xCoord, var2, this.zCoord, 0, 0.0F, 0.0F, 0.0F, 0);

                    if (this.worldObj.setBlockAndMetadataWithNotify(this.xCoord, var2, this.zCoord, FCBetterThanWolves.fcRopeBlock.blockID, var4))
                    {
                        Block var5 = FCBetterThanWolves.fcRopeBlock;
                        this.worldObj.playSoundEffect((double)((float)this.xCoord + 0.5F), (double)((float)var2 + 0.5F), (double)((float)this.zCoord + 0.5F), var5.stepSound.getStepSound(), var5.stepSound.getVolume() / 4.0F, var5.stepSound.getPitch() * 0.8F);
                        this.RemoveRopeFromInventory();
                        int var6 = this.worldObj.getBlockId(this.xCoord, var2 - 1, this.zCoord);

                        if (var6 == FCBetterThanWolves.fcAnchor.blockID && ((FCBlockAnchor)((FCBlockAnchor)FCBetterThanWolves.fcAnchor)).GetFacing(this.worldObj, this.xCoord, var2 - 1, this.zCoord) == 1)
                        {
                            ((FCBlockAnchor)((FCBlockAnchor)FCBetterThanWolves.fcAnchor)).NotifyAnchorBlockOfAttachedPulleyStateChange(this, this.worldObj, this.xCoord, var2 - 1, this.zCoord);
                        }

                        return true;
                    }

                    return false;
                }

                if (var3 != FCBetterThanWolves.fcRopeBlock.blockID)
                {
                    return false;
                }
            }
        }

        return false;
    }

    public void AddRopeToInventory()
    {
        ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemRope);
        this.iUpdateRopeStateCounter = 20;

        if (FCUtilsInventory.AddItemStackToInventory(this, var1))
        {
            this.worldObj.playSoundEffect((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "random.pop", 0.05F, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.7F + 1.0F);
        }
        else
        {
            FCUtilsItem.EjectStackWithRandomOffset(this.worldObj, this.xCoord, this.yCoord, this.zCoord, var1);
        }
    }

    public int GetContainedRopeCount()
    {
        return FCUtilsInventory.CountItemsInInventory(this, FCBetterThanWolves.fcItemRope.itemID, 32767);
    }

    public void RemoveRopeFromInventory()
    {
        int var1 = FCUtilsInventory.GetFirstOccupiedStackOfItem(this, FCBetterThanWolves.fcItemRope.itemID);

        if (var1 >= 0)
        {
            FCUtilsInventory.DecrStackSize(this, var1, 1);
        }
    }

    public void NotifyOfLossOfAnchorEntity()
    {
        this.m_bHasAssociatedAnchorEntity = false;
    }
}
