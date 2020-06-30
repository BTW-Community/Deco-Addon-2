package net.minecraft.src;

import java.util.List;

public class FCTileEntityHopper extends TileEntity implements IInventory, FCITileEntityDataPacketHandler
{
    private final int m_iInventorySize = 19;
    private final int m_iStackSizeLimit = 64;
    private final double m_dMaxPlayerInteractionDist = 64.0D;
    private final int m_iStackSizeToEject = 8;
    private final int m_iTimeToEject = 3;
    private final int m_iOverloadSoulCount = 8;
    private final int m_iXPInventorySpace = 100;
    private final int m_iXPEjectUnitSize = 20;
    private final int m_iXPDelayBetweenDrops = 10;
    private ItemStack[] m_Contents = new ItemStack[19];
    private int m_iEjectCounter = 0;
    private int m_iContainedSoulCount = 0;
    private int m_iContainedXPCount = 0;
    private int m_iHopperXPDropDelayCount = 10;
    public boolean m_bOutputBlocked = false;
    public int m_iMechanicalPowerIndicator = 0;
    protected int m_iFilterItemID = 0;
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

        if (var1.hasKey("grindCounter"))
        {
            this.m_iEjectCounter = var1.getInteger("grindCounter");
        }

        if (var1.hasKey("iHoppeContainedSoulCount"))
        {
            this.m_iContainedSoulCount = var1.getInteger("iHoppeContainedSoulCount");
        }

        if (var1.hasKey("iHopperContainedXPCount"))
        {
            this.m_iContainedXPCount = var1.getInteger("iHopperContainedXPCount");
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
        var1.setInteger("grindCounter", this.m_iEjectCounter);
        var1.setInteger("iHoppeContainedSoulCount", this.m_iContainedSoulCount);
        var1.setInteger("iHopperContainedXPCount", this.m_iContainedXPCount);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
        {
            boolean var1 = ((FCBlockHopper)FCBetterThanWolves.fcHopper).IsBlockOn(this.worldObj, this.xCoord, this.yCoord, this.zCoord);

            if (var1)
            {
                this.m_iMechanicalPowerIndicator = 1;
                this.AttemptToEjectXPFromInv();

                if (!this.m_bOutputBlocked)
                {
                    ++this.m_iEjectCounter;

                    if (this.m_iEjectCounter >= 3)
                    {
                        this.AttemptToEjectStackFromInv();
                        this.m_iEjectCounter = 0;
                    }
                }
                else
                {
                    this.m_iEjectCounter = 0;
                }
            }
            else
            {
                this.m_iMechanicalPowerIndicator = 0;
                this.m_iEjectCounter = 0;
                this.m_iHopperXPDropDelayCount = 0;
            }

            if (this.m_iContainedSoulCount > 0)
            {
                if (this.m_iFilterItemID == Block.slowSand.blockID)
                {
                    int var2 = this.worldObj.getBlockId(this.xCoord, this.yCoord - 1, this.zCoord);
                    int var3 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord - 1, this.zCoord);

                    if (var1 && (var2 != FCBetterThanWolves.fcAestheticNonOpaque.blockID || var3 != 0))
                    {
                        this.m_iContainedSoulCount = 0;
                    }

                    if (this.m_iContainedSoulCount >= 8)
                    {
                        if (var1 && var2 == FCBetterThanWolves.fcAestheticNonOpaque.blockID && var3 == 0)
                        {
                            this.worldObj.setBlockWithNotify(this.xCoord, this.yCoord - 1, this.zCoord, 0);
                            ItemStack var4 = new ItemStack(FCBetterThanWolves.fcItemSoulUrn.itemID, 1, 0);
                            FCUtilsItem.EjectStackWithRandomOffset(this.worldObj, this.xCoord, this.yCoord - 1, this.zCoord, var4);
                            this.m_iContainedSoulCount = 0;
                        }
                        else
                        {
                            this.HopperSoulOverload();
                        }
                    }
                }
                else
                {
                    this.m_iContainedSoulCount = 0;
                }
            }
        }
    }

    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        var1.setInteger("f", this.m_iFilterItemID);
        var1.setShort("s", this.m_sStorageSlotsOccupied);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }

    public void readNBTFromPacket(NBTTagCompound var1)
    {
        this.m_iFilterItemID = var1.getInteger("f");
        this.m_sStorageSlotsOccupied = var1.getShort("s");
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 19;
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
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Hopper";
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

        if (this.worldObj != null)
        {
            this.m_bOutputBlocked = false;

            if (this.ValidateInventoryStateVariables())
            {
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            }

            int var1 = FCUtilsInventory.GetNumOccupiedStacksInRange(this, 0, 17);
            ((FCBlockHopper)((FCBlockHopper)FCBetterThanWolves.fcHopper)).SetHopperFull(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.m_sStorageSlotsOccupied == 18);
            ((FCBlockHopper)((FCBlockHopper)FCBetterThanWolves.fcHopper)).SetHasFilter(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.m_iFilterItemID > 0);
        }
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

    public Item GetCurrentFilterItem()
    {
        return Item.itemsList[this.m_iFilterItemID];
    }

    private boolean ValidateInventoryStateVariables()
    {
        boolean var1 = false;
        int var2 = this.GetFilterIDBasedOnInventory();

        if (var2 != this.m_iFilterItemID)
        {
            this.m_iFilterItemID = var2;
            var1 = true;
        }

        short var3 = (short)FCUtilsInventory.GetNumOccupiedStacksInRange(this, 0, 17);

        if (var3 != this.m_sStorageSlotsOccupied)
        {
            this.m_sStorageSlotsOccupied = var3;
            var1 = true;
        }

        return var1;
    }

    public int GetFilterIDBasedOnInventory()
    {
        ItemStack var1 = this.getStackInSlot(18);
        return var1 != null && var1.stackSize > 0 ? var1.itemID : 0;
    }

    public Item GetFilterItem()
    {
        ItemStack var1 = this.getStackInSlot(18);
        return var1 != null ? var1.getItem() : null;
    }

    public boolean CanCurrentFilterProcessItem(ItemStack var1)
    {
        Item var2 = this.GetFilterItem();
        return var2 != null ? var2.CanItemPassIfFilter(var1) : true;
    }

    public boolean IsEjecting()
    {
        return ((FCBlockHopper)FCBetterThanWolves.fcHopper).IsBlockOn(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    private void AttemptToEjectStackFromInv()
    {
        int var1 = FCUtilsInventory.GetRandomOccupiedStackInRange(this, this.worldObj.rand, 0, 17);

        if (var1 >= 0 && var1 <= 17)
        {
            ItemStack var2 = this.getStackInSlot(var1);
            int var3;

            if (8 > var2.stackSize)
            {
                var3 = var2.stackSize;
            }
            else
            {
                var3 = 8;
            }

            ItemStack var4 = new ItemStack(var2.itemID, var3, var2.getItemDamage());
            FCUtilsInventory.CopyEnchantments(var4, var2);
            int var5 = this.xCoord;
            int var6 = this.yCoord - 1;
            int var7 = this.zCoord;
            boolean var8 = false;
            boolean var12;
            int var20;

            if (this.worldObj.isAirBlock(var5, var6, var7))
            {
                var8 = true;
            }
            else if (FCUtilsWorld.IsReplaceableBlock(this.worldObj, var5, var6, var7))
            {
                var8 = true;
            }
            else
            {
                int var9 = this.worldObj.getBlockId(var5, var6, var7);
                Block var10 = Block.blocksList[var9];

                if (var10 != null && var10.DoesBlockHopperEject(this.worldObj, var5, var6, var7))
                {
                    if (var10.DoesBlockHopperInsert(this.worldObj, var5, var6, var7))
                    {
                        this.m_bOutputBlocked = true;
                    }
                    else
                    {
                        TileEntity var11 = this.worldObj.getBlockTileEntity(var5, var6, var7);
                        var12 = false;

                        if (var11 != null && var11 instanceof IInventory)
                        {
                            byte var13 = 0;
                            int var14 = ((IInventory)var11).getSizeInventory() - 1;
                            boolean var15 = true;

                            if (var9 != Block.furnaceIdle.blockID && var9 != Block.furnaceBurning.blockID)
                            {
                                if (var9 == FCBetterThanWolves.fcHopper.blockID)
                                {
                                    var14 = 17;
                                    int var16 = ((FCTileEntityHopper)var11).m_iFilterItemID;

                                    if (var16 > 0)
                                    {
                                        var15 = false;
                                    }
                                }
                            }
                            else
                            {
                                var14 = 0;
                            }

                            if (var15)
                            {
                                boolean var21;

                                if (var9 != Block.chest.blockID && var9 != FCBetterThanWolves.fcBlockChest.blockID)
                                {
                                    var21 = FCUtilsInventory.AddItemStackToInventoryInSlotRange((IInventory)var11, var4, var13, var14);
                                }
                                else
                                {
                                    var21 = FCUtilsInventory.AddItemStackToChest((TileEntityChest)var11, var4);
                                }

                                if (!var21)
                                {
                                    var20 = var3 - var4.stackSize;
                                }
                                else
                                {
                                    var20 = var3;
                                }

                                if (var20 > 0)
                                {
                                    this.decrStackSize(var1, var20);
                                    this.worldObj.playAuxSFX(2231, this.xCoord, this.yCoord, this.zCoord, 0);
                                }
                            }
                            else
                            {
                                this.m_bOutputBlocked = true;
                            }
                        }
                        else
                        {
                            this.m_bOutputBlocked = true;
                        }
                    }
                }
                else
                {
                    var8 = true;
                }
            }

            if (var8)
            {
                List var17 = this.worldObj.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getAABBPool().getAABB((double)((float)this.xCoord + 0.4F), (double)((float)this.yCoord - 0.5F), (double)((float)this.zCoord + 0.4F), (double)((float)this.xCoord + 0.6F), (double)this.yCoord, (double)((float)this.zCoord + 0.6F)));

                if (var17 != null && var17.size() > 0)
                {
                    for (int var18 = 0; var18 < var17.size(); ++var18)
                    {
                        EntityMinecart var19 = (EntityMinecart)var17.get(var18);

                        if (var19.getMinecartType() == 1 && var19.boundingBox.intersectsWith(AxisAlignedBB.getAABBPool().getAABB((double)((float)this.xCoord), (double)((float)this.yCoord - 0.5F), (double)((float)this.zCoord), (double)((float)this.xCoord + 0.25F), (double)this.yCoord, (double)((float)this.zCoord + 1.0F))) && var19.boundingBox.intersectsWith(AxisAlignedBB.getAABBPool().getAABB((double)((float)this.xCoord + 0.75F), (double)((float)this.yCoord - 0.5F), (double)((float)this.zCoord), (double)((float)this.xCoord + 1.0F), (double)this.yCoord, (double)((float)this.zCoord + 1.0F))) && var19.boundingBox.intersectsWith(AxisAlignedBB.getAABBPool().getAABB((double)((float)this.xCoord), (double)((float)this.yCoord - 0.5F), (double)((float)this.zCoord), (double)((float)this.xCoord + 1.0F), (double)this.yCoord, (double)((float)this.zCoord + 0.25F))) && var19.boundingBox.intersectsWith(AxisAlignedBB.getAABBPool().getAABB((double)((float)this.xCoord), (double)((float)this.yCoord - 0.5F), (double)((float)this.zCoord + 0.75F), (double)((float)this.xCoord + 1.0F), (double)this.yCoord, (double)((float)this.zCoord + 1.0F))))
                        {
                            var12 = false;

                            if (FCUtilsInventory.AddItemStackToInventory((IInventory)var19, var4))
                            {
                                var20 = var3;
                            }
                            else
                            {
                                var20 = var3 - var4.stackSize;
                            }

                            if (var20 > 0)
                            {
                                this.decrStackSize(var1, var20);
                                this.worldObj.playAuxSFX(2231, this.xCoord, this.yCoord, this.zCoord, 0);
                            }

                            var8 = false;
                            break;
                        }
                    }
                }
            }

            if (var8)
            {
                this.EjectStack(var4);
                this.decrStackSize(var1, var3);
            }
        }
    }

    private void EjectStack(ItemStack var1)
    {
        float var2 = this.worldObj.rand.nextFloat() * 0.1F + 0.45F;
        float var3 = -0.35F;
        float var4 = this.worldObj.rand.nextFloat() * 0.1F + 0.45F;
        EntityItem var5 = new EntityItem(this.worldObj, (double)((float)this.xCoord + var2), (double)((float)this.yCoord + var3), (double)((float)this.zCoord + var4), var1);
        var5.motionX = 0.0D;
        var5.motionY = -0.009999999776482582D;
        var5.motionZ = 0.0D;
        var5.delayBeforeCanPickup = 10;
        this.worldObj.spawnEntityInWorld(var5);
    }

    public void AttemptToEjectXPFromInv()
    {
        boolean var1 = true;

        if (this.m_iContainedXPCount >= 20)
        {
            int var2 = this.xCoord;
            int var3 = this.yCoord - 1;
            int var4 = this.zCoord;
            boolean var5 = false;

            if (this.worldObj.isAirBlock(var2, var3, var4))
            {
                var5 = true;
            }
            else
            {
                int var6 = this.worldObj.getBlockId(var2, var3, var4);

                if (var6 == FCBetterThanWolves.fcHopper.blockID)
                {
                    var1 = this.AttemptToEjectXPIntoHopper(var2, var3, var4);
                }
                else if (var6 == FCBetterThanWolves.fcBlockArcaneVessel.blockID)
                {
                    var1 = this.AttemptToEjectXPIntoArcaneVessel(var2, var3, var4);
                }
                else if (FCUtilsWorld.IsReplaceableBlock(this.worldObj, var2, var3, var4))
                {
                    var5 = true;
                }
                else
                {
                    Block var7 = Block.blocksList[var6];

                    if (!var7.blockMaterial.isSolid())
                    {
                        var5 = true;
                    }
                }
            }

            if (var5)
            {
                if (this.m_iHopperXPDropDelayCount <= 0)
                {
                    this.EjectXPOrb(20);
                    this.m_iContainedXPCount -= 20;
                }
                else
                {
                    var1 = false;
                }
            }
        }

        if (var1)
        {
            this.ResetXPEjectCount();
        }
        else
        {
            --this.m_iHopperXPDropDelayCount;
        }
    }

    private boolean AttemptToEjectXPIntoHopper(int var1, int var2, int var3)
    {
        FCBlockHopper var4 = (FCBlockHopper)FCBetterThanWolves.fcHopper;
        FCTileEntityHopper var5 = (FCTileEntityHopper)this.worldObj.getBlockTileEntity(var1, var2, var3);

        if (var5 != null && this.m_iFilterItemID == Block.slowSand.blockID)
        {
            int var6 = 100 - var5.m_iContainedXPCount;

            if (var6 > 0)
            {
                if (this.m_iHopperXPDropDelayCount > 0)
                {
                    return false;
                }

                int var7 = 20;

                if (var6 < var7)
                {
                    var7 = var6;
                }

                var5.m_iContainedXPCount += var7;
                this.m_iContainedXPCount -= var7;
                this.worldObj.playAuxSFX(2232, this.xCoord, this.yCoord, this.zCoord, 0);
            }
        }

        return true;
    }

    private boolean AttemptToEjectXPIntoArcaneVessel(int var1, int var2, int var3)
    {
        FCBlockArcaneVessel var4 = (FCBlockArcaneVessel)FCBetterThanWolves.fcBlockArcaneVessel;
        FCTileEntityArcaneVessel var5 = (FCTileEntityArcaneVessel)this.worldObj.getBlockTileEntity(var1, var2, var3);

        if (var5 != null && !var4.GetMechanicallyPoweredFlag(this.worldObj, var1, var2, var3))
        {
            int var6 = 1000 - var5.GetContainedTotalExperience();

            if (var6 > 0)
            {
                if (this.m_iHopperXPDropDelayCount > 0)
                {
                    return false;
                }

                int var7 = 20;

                if (var6 < var7)
                {
                    var7 = var6;
                }

                var5.SetContainedRegularExperience(var5.GetContainedRegularExperience() + var7);
                this.m_iContainedXPCount -= var7;
                this.worldObj.playAuxSFX(2232, this.xCoord, this.yCoord, this.zCoord, 0);
            }
        }

        return true;
    }

    private void ResetXPEjectCount()
    {
        this.m_iHopperXPDropDelayCount = 10 + this.worldObj.rand.nextInt(3);
    }

    private void EjectXPOrb(int var1)
    {
        double var2 = this.worldObj.rand.nextDouble() * 0.1D + 0.45D;
        double var4 = -0.2D;
        double var6 = this.worldObj.rand.nextDouble() * 0.1D + 0.45D;
        EntityXPOrb var8 = new EntityXPOrb(this.worldObj, (double)this.xCoord + var2, (double)this.yCoord + var4, (double)this.zCoord + var6, var1);
        var8.motionX = 0.0D;
        var8.motionY = 0.0D;
        var8.motionZ = 0.0D;
        this.worldObj.spawnEntityInWorld(var8);
        this.worldObj.playAuxSFX(2230, this.xCoord, this.yCoord, this.zCoord, 0);
    }

    public void ResetContainedSoulCount()
    {
        this.m_iContainedSoulCount = 0;
    }

    public void IncrementContainedSoulCount(int var1)
    {
        this.m_iContainedSoulCount += var1;
    }

    private void HopperSoulOverload()
    {
        this.worldObj.playAuxSFX(2225, this.xCoord, this.yCoord, this.zCoord, 0);
        ((FCBlockHopper)((FCBlockHopper)FCBetterThanWolves.fcHopper)).BreakHopper(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    public boolean AttemptToSwallowXPOrb(World var1, int var2, int var3, int var4, EntityXPOrb var5)
    {
        int var6 = 100 - this.m_iContainedXPCount;

        if (var6 > 0)
        {
            if (var5.xpValue <= var6)
            {
                this.m_iContainedXPCount += var5.xpValue;
                var5.setDead();
                return true;
            }

            var5.xpValue -= var6;
            this.m_iContainedXPCount = 100;
        }

        return false;
    }
}
