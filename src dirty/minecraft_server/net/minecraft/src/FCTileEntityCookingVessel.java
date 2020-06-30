package net.minecraft.src;

import java.util.List;

public abstract class FCTileEntityCookingVessel extends TileEntity implements IInventory, FCITileEntityDataPacketHandler
{
    protected final int m_iInventorySize = 27;
    private final int m_iStackSizeLimit = 64;
    private final int m_iPrimaryFireFactor = 5;
    private final int m_iSecondaryFireFactor = 3;
    protected final int m_iStackSizeToDropWhenTipped = 8;
    private final double m_dMaxPlayerInteractionDist = 64.0D;
    private final int m_iStokedTicksToCooldown = 20;
    private final int m_iTimeToCook = 4350;
    protected ItemStack[] m_Contents = new ItemStack[27];
    protected int m_iCookCounter = 0;
    protected int m_iStokedCooldownCounter = 0;
    protected boolean m_bContainsValidIngrediantsForState = false;
    private boolean m_bForceValidateOnUpdate = true;
    protected int m_iFireUnderType = 0;
    public int m_iScaledCookCounter = 0;
    public int m_iForceFacing = -1;
    public short m_sStorageSlotsOccupied = 0;

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

        if (var1.hasKey("m_iFireUnderType"))
        {
            this.m_iFireUnderType = var1.getInteger("m_iFireUnderType");
        }
        else
        {
            this.m_iFireUnderType = -1;
        }

        if (var1.hasKey("m_iFacing"))
        {
            this.m_iForceFacing = var1.getInteger("m_iFacing");
        }

        this.ValidateInventoryStateVariables();
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
        var1.setBoolean("m_bContainsValidIngrediantsForState", this.m_bContainsValidIngrediantsForState);
        var1.setInteger("m_iFireUnderType", this.m_iFireUnderType);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
        {
            int var1 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
            Block var2 = Block.blocksList[var1];

            if (var2 != null && var2 instanceof FCBlockCookingVessel)
            {
                FCBlockCookingVessel var3 = (FCBlockCookingVessel)var2;

                if (this.m_iForceFacing >= 0)
                {
                    var3.SetTiltFacing(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.m_iForceFacing);
                    this.m_iForceFacing = -1;
                }

                if (this.m_iFireUnderType == -1)
                {
                    this.ValidateFireUnderType();
                }

                if (!var3.GetMechanicallyPoweredFlag(this.worldObj, this.xCoord, this.yCoord, this.zCoord))
                {
                    if (this.m_iFireUnderType > 0)
                    {
                        if (this.m_bForceValidateOnUpdate)
                        {
                            this.ValidateContentsForState();
                            this.m_bForceValidateOnUpdate = false;
                        }

                        if (this.m_iFireUnderType == 2)
                        {
                            if (this.m_iStokedCooldownCounter <= 0)
                            {
                                this.m_iCookCounter = 0;
                            }

                            this.m_iStokedCooldownCounter = 20;
                            this.PerformStokedFireUpdate(this.GetCurrentFireFactor());
                        }
                        else if (this.m_iStokedCooldownCounter > 0)
                        {
                            --this.m_iStokedCooldownCounter;

                            if (this.m_iStokedCooldownCounter <= 0)
                            {
                                this.m_iCookCounter = 0;
                            }
                        }
                        else
                        {
                            this.PerformNormalFireUpdate(this.GetCurrentFireFactor());
                        }
                    }
                    else
                    {
                        this.m_iCookCounter = 0;
                    }
                }
                else
                {
                    this.m_iCookCounter = 0;
                    int var4 = var3.GetTiltFacing(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                    this.AttemptToEjectStackFromInv(var4);
                }

                this.m_iScaledCookCounter = this.m_iCookCounter * 1000 / 4350;
            }
        }
    }

    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        var1.setShort("s", this.m_sStorageSlotsOccupied);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }

    public void readNBTFromPacket(NBTTagCompound var1)
    {
        this.m_sStorageSlotsOccupied = var1.getShort("s");
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 27;
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
        if (this.m_Contents[var1] != null)
        {
            ItemStack var2 = this.m_Contents[var1];
            this.m_Contents[var1] = null;
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
        this.m_Contents[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
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
     * Called when an the contents of an Inventory change, usually
     */
    public void onInventoryChanged()
    {
        super.onInventoryChanged();
        this.m_bForceValidateOnUpdate = true;

        if (this.worldObj != null && this.ValidateInventoryStateVariables())
        {
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        if (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this)
        {
            return false;
        }
        else
        {
            int var2 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
            Block var3 = Block.blocksList[var2];
            return !(var3 instanceof FCBlockCookingVessel) ? false : (((FCBlockCookingVessel)var3).IsOpenSideBlocked(this.worldObj, this.xCoord, this.yCoord, this.zCoord) ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D);
        }
    }

    public void openChest() {}

    public void closeChest() {}

    public abstract void ValidateContentsForState();

    protected abstract FCCraftingManagerBulk GetCraftingManager(int var1);

    public int GetCurrentFireFactor()
    {
        int var1 = 0;

        if (this.m_iFireUnderType > 0)
        {
            var1 = 5;
            int var2;
            int var3;
            int var4;

            if (this.m_iFireUnderType == 1)
            {
                var2 = this.yCoord - 1;

                for (var3 = this.xCoord - 1; var3 <= this.xCoord + 1; ++var3)
                {
                    for (var4 = this.zCoord - 1; var4 <= this.zCoord + 1; ++var4)
                    {
                        if (var3 != this.xCoord || var4 != this.zCoord)
                        {
                            int var5 = this.worldObj.getBlockId(var3, var2, var4);

                            if (var5 == Block.fire.blockID || var5 == FCBetterThanWolves.fcBlockCampfireMedium.blockID || var5 == FCBetterThanWolves.fcBlockCampfireLarge.blockID)
                            {
                                var1 += 3;
                            }
                        }
                    }
                }
            }
            else
            {
                var2 = this.yCoord - 1;

                for (var3 = this.xCoord - 1; var3 <= this.xCoord + 1; ++var3)
                {
                    for (var4 = this.zCoord - 1; var4 <= this.zCoord + 1; ++var4)
                    {
                        if ((var3 != this.xCoord || var4 != this.zCoord) && this.worldObj.getBlockId(var3, var2, var4) == FCBetterThanWolves.fcBlockFireStoked.blockID)
                        {
                            var1 += 3;
                        }
                    }
                }
            }
        }

        return var1;
    }

    public void ValidateFireUnderType()
    {
        byte var1 = 0;
        int var2 = this.worldObj.getBlockId(this.xCoord, this.yCoord - 1, this.zCoord);

        if (var2 != Block.fire.blockID && var2 != FCBetterThanWolves.fcBlockCampfireMedium.blockID && var2 != FCBetterThanWolves.fcBlockCampfireLarge.blockID)
        {
            if (var2 == FCBetterThanWolves.fcBlockFireStoked.blockID)
            {
                var1 = 2;
            }
        }
        else
        {
            var1 = 1;
        }

        if (var1 != this.m_iFireUnderType)
        {
            this.m_iFireUnderType = var1;
            this.ValidateContentsForState();
        }
    }

    private void PerformNormalFireUpdate(int var1)
    {
        if (this.m_bContainsValidIngrediantsForState)
        {
            this.m_iCookCounter += var1;

            if (this.m_iCookCounter >= 4350)
            {
                this.AttemptToCookNormal();
                this.m_iCookCounter = 0;
            }
        }
        else
        {
            this.m_iCookCounter = 0;
        }
    }

    private void PerformStokedFireUpdate(int var1)
    {
        if (this.m_bContainsValidIngrediantsForState)
        {
            this.m_iCookCounter += var1;

            if (this.m_iCookCounter >= 4350)
            {
                if (this.DoesContainExplosives())
                {
                    this.BlowUp();
                }
                else
                {
                    this.AttemptToCookStoked();
                }

                this.m_iCookCounter = 0;
            }
        }
        else
        {
            this.m_iCookCounter = 0;
        }
    }

    protected boolean AttemptToCookNormal()
    {
        return this.AttemptToCookWithManager(this.GetCraftingManager(1));
    }

    protected boolean AttemptToCookStoked()
    {
        return this.AttemptToCookWithManager(this.GetCraftingManager(2));
    }

    private boolean AttemptToCookWithManager(FCCraftingManagerBulk var1)
    {
        if (var1 != null && var1.GetCraftingResult((IInventory)this) != null)
        {
            List var2 = var1.ConsumeIngrediantsAndReturnResult(this);
            assert var2 != null && var2.size() > 0;

            for (int var3 = 0; var3 < var2.size(); ++var3)
            {
                ItemStack var4 = ((ItemStack)var2.get(var3)).copy();

                if (var4 != null && !FCUtilsInventory.AddItemStackToInventory(this, var4))
                {
                    FCUtilsItem.EjectStackWithRandomOffset(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord, var4);
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public int getCookProgressScaled(int var1)
    {
        return this.m_iScaledCookCounter * var1 / 1000;
    }

    public boolean IsCooking()
    {
        return this.m_iScaledCookCounter > 0;
    }

    protected boolean DoesContainExplosives()
    {
        return FCUtilsInventory.GetFirstOccupiedStackOfItem(this, FCBetterThanWolves.fcItemHellfireDust.itemID) >= 0 || FCUtilsInventory.GetFirstOccupiedStackOfItem(this, Block.tnt.blockID) >= 0 || FCUtilsInventory.GetFirstOccupiedStackOfItem(this, Item.gunpowder.itemID) >= 0 || FCUtilsInventory.GetFirstOccupiedStackOfItem(this, FCBetterThanWolves.fcItemBlastingOil.itemID) >= 0;
    }

    private void BlowUp()
    {
        int var1 = FCUtilsInventory.CountItemsInInventory(this, FCBetterThanWolves.fcItemHellfireDust.itemID, -1);
        float var2 = (float)var1 * 10.0F / 64.0F;
        var2 += (float)FCUtilsInventory.CountItemsInInventory(this, Item.gunpowder.itemID, -1) * 10.0F / 64.0F;
        var2 += (float)FCUtilsInventory.CountItemsInInventory(this, FCBetterThanWolves.fcItemBlastingOil.itemID, -1) * 10.0F / 64.0F;
        int var3 = FCUtilsInventory.CountItemsInInventory(this, Block.tnt.blockID, -1);

        if (var3 > 0)
        {
            if (var2 < 4.0F)
            {
                var2 = 4.0F;
            }

            var2 += (float)FCUtilsInventory.CountItemsInInventory(this, Block.tnt.blockID, -1);
        }

        if (var2 < 2.0F)
        {
            var2 = 2.0F;
        }
        else if (var2 > 10.0F)
        {
            var2 = 10.0F;
        }

        FCUtilsInventory.ClearInventoryContents(this);
        this.worldObj.setBlockWithNotify(this.xCoord, this.yCoord, this.zCoord, 0);
        this.worldObj.createExplosion((Entity)null, (double)this.xCoord, (double)this.yCoord, (double)this.zCoord, var2, true);
    }

    private void AttemptToEjectStackFromInv(int var1)
    {
        int var2 = FCUtilsInventory.GetFirstOccupiedStackNotOfItem(this, Item.brick.itemID);

        if (var2 >= 0 && var2 < this.getSizeInventory())
        {
            ItemStack var3 = this.getStackInSlot(var2);
            int var4;

            if (8 > var3.stackSize)
            {
                var4 = var3.stackSize;
            }
            else
            {
                var4 = 8;
            }

            ItemStack var5 = new ItemStack(var3.itemID, var4, var3.getItemDamage());
            FCUtilsInventory.CopyEnchantments(var5, var3);
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(this.xCoord, this.yCoord, this.zCoord);
            var6.AddFacingAsOffset(var1);
            boolean var7 = false;

            if (this.worldObj.isAirBlock(var6.i, var6.j, var6.k))
            {
                var7 = true;
            }
            else if (FCUtilsWorld.IsReplaceableBlock(this.worldObj, var6.i, var6.j, var6.k))
            {
                var7 = true;
            }
            else
            {
                int var8 = this.worldObj.getBlockId(var6.i, var6.j, var6.k);
                Block var9 = Block.blocksList[var8];

                if (!var9.blockMaterial.isSolid())
                {
                    var7 = true;
                }
            }

            if (var7)
            {
                this.EjectStack(var5, var1);
                this.decrStackSize(var2, var4);
            }
        }
    }

    private void EjectStack(ItemStack var1, int var2)
    {
        Vec3 var3 = FCUtilsMisc.ConvertBlockFacingToVector(var2);
        var3.xCoord *= 0.5D;
        var3.yCoord *= 0.5D;
        var3.zCoord *= 0.5D;
        var3.xCoord += (double)((float)this.xCoord + 0.5F);
        var3.yCoord += (double)((float)this.yCoord + 0.25F);
        var3.zCoord += (double)((float)this.zCoord + 0.5F);
        EntityItem var4 = new EntityItem(this.worldObj, var3.xCoord, var3.yCoord, var3.zCoord, var1);
        Vec3 var5 = FCUtilsMisc.ConvertBlockFacingToVector(var2);
        var5.xCoord *= 0.10000000149011612D;
        var5.yCoord *= 0.10000000149011612D;
        var5.zCoord *= 0.10000000149011612D;
        var4.motionX = var5.xCoord;
        var4.motionY = var5.yCoord;
        var4.motionZ = var5.zCoord;
        var4.delayBeforeCanPickup = 10;
        this.worldObj.spawnEntityInWorld(var4);
    }

    private boolean ValidateInventoryStateVariables()
    {
        boolean var1 = false;
        short var2 = (short)FCUtilsInventory.GetNumOccupiedStacks(this);

        if (var2 != this.m_sStorageSlotsOccupied)
        {
            this.m_sStorageSlotsOccupied = var2;
            var1 = true;
        }

        return var1;
    }
}
