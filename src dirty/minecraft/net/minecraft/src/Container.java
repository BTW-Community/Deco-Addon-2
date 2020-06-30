package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class Container
{
    /** the list of all items(stacks) for the corresponding slot */
    public List inventoryItemStacks = new ArrayList();

    /** the list of all slots in the inventory */
    public List inventorySlots = new ArrayList();
    public int windowId = 0;
    private short transactionID = 0;
    private int field_94535_f = -1;
    private int field_94536_g = 0;
    private final Set field_94537_h = new HashSet();

    /**
     * list of all people that need to be notified when this craftinventory changes
     */
    protected List crafters = new ArrayList();
    private Set playerList = new HashSet();

    /**
     * the slot is assumed empty
     */
    protected Slot addSlotToContainer(Slot par1Slot)
    {
        par1Slot.slotNumber = this.inventorySlots.size();
        this.inventorySlots.add(par1Slot);
        this.inventoryItemStacks.add((Object)null);
        return par1Slot;
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        if (this.crafters.contains(par1ICrafting))
        {
            throw new IllegalArgumentException("Listener already listening");
        }
        else
        {
            this.crafters.add(par1ICrafting);
            par1ICrafting.sendContainerAndContentsToPlayer(this, this.getInventory());
            this.detectAndSendChanges();
        }
    }

    /**
     * Remove this crafting listener from the listener list.
     */
    public void removeCraftingFromCrafters(ICrafting par1ICrafting)
    {
        this.crafters.remove(par1ICrafting);
    }

    /**
     * returns a list if itemStacks, for each slot.
     */
    public List getInventory()
    {
        ArrayList var1 = new ArrayList();

        for (int var2 = 0; var2 < this.inventorySlots.size(); ++var2)
        {
            var1.add(((Slot)this.inventorySlots.get(var2)).getStack());
        }

        return var1;
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        for (int var1 = 0; var1 < this.inventorySlots.size(); ++var1)
        {
            ItemStack var2 = ((Slot)this.inventorySlots.get(var1)).getStack();
            ItemStack var3 = (ItemStack)this.inventoryItemStacks.get(var1);

            if (!ItemStack.areItemStacksEqual(var3, var2))
            {
                var3 = var2 == null ? null : var2.copy();
                this.inventoryItemStacks.set(var1, var3);

                for (int var4 = 0; var4 < this.crafters.size(); ++var4)
                {
                    ((ICrafting)this.crafters.get(var4)).sendSlotContents(this, var1, var3);
                }
            }
        }
    }

    /**
     * enchants the item on the table using the specified slot; also deducts XP from player
     */
    public boolean enchantItem(EntityPlayer par1EntityPlayer, int par2)
    {
        return false;
    }

    public Slot getSlotFromInventory(IInventory par1IInventory, int par2)
    {
        for (int var3 = 0; var3 < this.inventorySlots.size(); ++var3)
        {
            Slot var4 = (Slot)this.inventorySlots.get(var3);

            if (var4.isSlotInInventory(par1IInventory, par2))
            {
                return var4;
            }
        }

        return null;
    }

    public Slot getSlot(int par1)
    {
        return (Slot)this.inventorySlots.get(par1);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        Slot var3 = (Slot)this.inventorySlots.get(par2);
        return var3 != null ? var3.getStack() : null;
    }

    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
    {
        ItemStack var5 = null;
        InventoryPlayer var6 = par4EntityPlayer.inventory;
        int var7;
        ItemStack var8;
        ItemStack var12;
        int var13;

        if (par3 == 5)
        {
            int var9 = this.field_94536_g;
            this.field_94536_g = func_94532_c(par2);

            if ((var9 != 1 || this.field_94536_g != 2) && var9 != this.field_94536_g)
            {
                this.func_94533_d();
            }
            else if (var6.getItemStack() == null)
            {
                this.func_94533_d();
            }
            else if (this.field_94536_g == 0)
            {
                this.field_94535_f = func_94529_b(par2);

                if (func_94528_d(this.field_94535_f))
                {
                    this.field_94536_g = 1;
                    this.field_94537_h.clear();
                }
                else
                {
                    this.func_94533_d();
                }
            }
            else if (this.field_94536_g == 1)
            {
                Slot var10 = (Slot)this.inventorySlots.get(par1);

                if (var10 != null && func_94527_a(var10, var6.getItemStack(), true) && var10.isItemValid(var6.getItemStack()) && var6.getItemStack().stackSize > this.field_94537_h.size() && this.func_94531_b(var10))
                {
                    this.field_94537_h.add(var10);
                }
            }
            else if (this.field_94536_g == 2)
            {
                if (!this.field_94537_h.isEmpty())
                {
                    var8 = var6.getItemStack().copy();
                    var7 = var6.getItemStack().stackSize;
                    Iterator var18 = this.field_94537_h.iterator();

                    while (var18.hasNext())
                    {
                        Slot var11 = (Slot)var18.next();

                        if (var11 != null && func_94527_a(var11, var6.getItemStack(), true) && var11.isItemValid(var6.getItemStack()) && var6.getItemStack().stackSize >= this.field_94537_h.size() && this.func_94531_b(var11))
                        {
                            var12 = var8.copy();
                            var13 = var11.getHasStack() ? var11.getStack().stackSize : 0;
                            func_94525_a(this.field_94537_h, this.field_94535_f, var12, var13);

                            if (var12.stackSize > var12.getMaxStackSize())
                            {
                                var12.stackSize = var12.getMaxStackSize();
                            }

                            if (var12.stackSize > var11.getSlotStackLimit())
                            {
                                var12.stackSize = var11.getSlotStackLimit();
                            }

                            var7 -= var12.stackSize - var13;
                            var11.putStack(var12);
                        }
                    }

                    var8.stackSize = var7;

                    if (var8.stackSize <= 0)
                    {
                        var8 = null;
                    }

                    var6.setItemStack(var8);
                }

                this.func_94533_d();
            }
            else
            {
                this.func_94533_d();
            }
        }
        else if (this.field_94536_g != 0)
        {
            this.func_94533_d();
        }
        else
        {
            Slot var17;
            int var19;
            ItemStack var20;

            if ((par3 == 0 || par3 == 1) && (par2 == 0 || par2 == 1))
            {
                if (par1 == -999)
                {
                    if (var6.getItemStack() != null && par1 == -999)
                    {
                        if (par2 == 0)
                        {
                            par4EntityPlayer.dropPlayerItem(var6.getItemStack());
                            var6.setItemStack((ItemStack)null);
                        }

                        if (par2 == 1)
                        {
                            par4EntityPlayer.dropPlayerItem(var6.getItemStack().splitStack(1));

                            if (var6.getItemStack().stackSize == 0)
                            {
                                var6.setItemStack((ItemStack)null);
                            }
                        }
                    }
                }
                else if (par3 == 1)
                {
                    if (par1 < 0)
                    {
                        return null;
                    }

                    var17 = (Slot)this.inventorySlots.get(par1);

                    if (var17 != null && var17.canTakeStack(par4EntityPlayer))
                    {
                        var8 = this.transferStackInSlot(par4EntityPlayer, par1);

                        if (var8 != null)
                        {
                            var7 = var8.itemID;
                            var5 = var8.copy();

                            if (var17 != null && var17.getStack() != null && var17.getStack().itemID == var7)
                            {
                                this.retrySlotClick(par1, par2, true, par4EntityPlayer);
                            }
                        }
                    }
                }
                else
                {
                    if (par1 < 0)
                    {
                        return null;
                    }

                    var17 = (Slot)this.inventorySlots.get(par1);

                    if (var17 != null)
                    {
                        var8 = var17.getStack();
                        var12 = var6.getItemStack();

                        if (var8 != null)
                        {
                            var5 = var8.copy();
                        }

                        if (var8 == null)
                        {
                            if (var12 != null && var17.isItemValid(var12))
                            {
                                var19 = par2 == 0 ? var12.stackSize : 1;

                                if (var19 > var17.getSlotStackLimit())
                                {
                                    var19 = var17.getSlotStackLimit();
                                }

                                var17.putStack(var12.splitStack(var19));

                                if (var12.stackSize == 0)
                                {
                                    var6.setItemStack((ItemStack)null);
                                }
                            }
                        }
                        else if (var17.canTakeStack(par4EntityPlayer))
                        {
                            if (var12 == null)
                            {
                                var19 = par2 == 0 ? var8.stackSize : (var8.stackSize + 1) / 2;
                                var20 = var17.decrStackSize(var19);
                                var6.setItemStack(var20);

                                if (var8.stackSize == 0)
                                {
                                    var17.putStack((ItemStack)null);
                                }

                                var17.onPickupFromSlot(par4EntityPlayer, var6.getItemStack());
                            }
                            else if (var17.isItemValid(var12))
                            {
                                if (var8.itemID == var12.itemID && var8.getItemDamage() == var12.getItemDamage() && ItemStack.areItemStackTagsEqual(var8, var12))
                                {
                                    var19 = par2 == 0 ? var12.stackSize : 1;

                                    if (var19 > var17.getSlotStackLimit() - var8.stackSize)
                                    {
                                        var19 = var17.getSlotStackLimit() - var8.stackSize;
                                    }

                                    if (var19 > var12.getMaxStackSize() - var8.stackSize)
                                    {
                                        var19 = var12.getMaxStackSize() - var8.stackSize;
                                    }

                                    var12.splitStack(var19);

                                    if (var12.stackSize == 0)
                                    {
                                        var6.setItemStack((ItemStack)null);
                                    }

                                    var8.stackSize += var19;
                                }
                                else if (var12.stackSize <= var17.getSlotStackLimit())
                                {
                                    var17.putStack(var12);
                                    var6.setItemStack(var8);
                                }
                            }
                            else if (var8.itemID == var12.itemID && var12.getMaxStackSize() > 1 && (!var8.getHasSubtypes() || var8.getItemDamage() == var12.getItemDamage()) && ItemStack.areItemStackTagsEqual(var8, var12))
                            {
                                var19 = var8.stackSize;

                                if (var19 > 0 && var19 + var12.stackSize <= var12.getMaxStackSize())
                                {
                                    var12.stackSize += var19;
                                    var8 = var17.decrStackSize(var19);

                                    if (var8.stackSize == 0)
                                    {
                                        var17.putStack((ItemStack)null);
                                    }

                                    var17.onPickupFromSlot(par4EntityPlayer, var6.getItemStack());
                                }
                            }
                        }

                        var17.onSlotChanged();
                    }
                }
            }
            else if (par3 == 2 && par2 >= 0 && par2 < 9)
            {
                var17 = (Slot)this.inventorySlots.get(par1);

                if (var17.canTakeStack(par4EntityPlayer))
                {
                    var8 = var6.getStackInSlot(par2);
                    boolean var22 = var8 == null || var17.inventory == var6 && var17.isItemValid(var8);
                    var19 = -1;

                    if (!var22)
                    {
                        var19 = var6.getFirstEmptyStack();
                        var22 |= var19 > -1;
                    }

                    if (var17.getHasStack() && var22)
                    {
                        var20 = var17.getStack();
                        var6.setInventorySlotContents(par2, var20.copy());

                        if ((var17.inventory != var6 || !var17.isItemValid(var8)) && var8 != null)
                        {
                            if (var19 > -1)
                            {
                                var6.addItemStackToInventory(var8);
                                var17.decrStackSize(var20.stackSize);
                                var17.putStack((ItemStack)null);
                                var17.onPickupFromSlot(par4EntityPlayer, var20);
                            }
                        }
                        else
                        {
                            var17.decrStackSize(var20.stackSize);
                            var17.putStack(var8);
                            var17.onPickupFromSlot(par4EntityPlayer, var20);
                        }
                    }
                    else if (!var17.getHasStack() && var8 != null && var17.isItemValid(var8))
                    {
                        var6.setInventorySlotContents(par2, (ItemStack)null);
                        var17.putStack(var8);
                    }
                }
            }
            else if (par3 == 3 && par4EntityPlayer.capabilities.isCreativeMode && var6.getItemStack() == null && par1 >= 0)
            {
                var17 = (Slot)this.inventorySlots.get(par1);

                if (var17 != null && var17.getHasStack())
                {
                    var8 = var17.getStack().copy();
                    var8.stackSize = var8.getMaxStackSize();
                    var6.setItemStack(var8);
                }
            }
            else if (par3 == 4 && var6.getItemStack() == null && par1 >= 0)
            {
                var17 = (Slot)this.inventorySlots.get(par1);

                if (var17 != null && var17.getHasStack() && var17.canTakeStack(par4EntityPlayer))
                {
                    var8 = var17.decrStackSize(par2 == 0 ? 1 : var17.getStack().stackSize);
                    var17.onPickupFromSlot(par4EntityPlayer, var8);
                    par4EntityPlayer.dropPlayerItem(var8);
                }
            }
            else if (par3 == 6 && par1 >= 0)
            {
                var17 = (Slot)this.inventorySlots.get(par1);
                var8 = var6.getItemStack();

                if (var8 != null && (var17 == null || !var17.getHasStack() || !var17.canTakeStack(par4EntityPlayer)))
                {
                    var7 = par2 == 0 ? 0 : this.inventorySlots.size() - 1;
                    var19 = par2 == 0 ? 1 : -1;

                    for (int var21 = 0; var21 < 2; ++var21)
                    {
                        for (var13 = var7; var13 >= 0 && var13 < this.inventorySlots.size() && var8.stackSize < var8.getMaxStackSize(); var13 += var19)
                        {
                            Slot var14 = (Slot)this.inventorySlots.get(var13);

                            if (var14.getHasStack() && func_94527_a(var14, var8, true) && var14.canTakeStack(par4EntityPlayer) && this.func_94530_a(var8, var14) && (var21 != 0 || var14.getStack().stackSize != var14.getStack().getMaxStackSize()))
                            {
                                int var15 = Math.min(var8.getMaxStackSize() - var8.stackSize, var14.getStack().stackSize);
                                ItemStack var16 = var14.decrStackSize(var15);
                                var8.stackSize += var15;

                                if (var16.stackSize <= 0)
                                {
                                    var14.putStack((ItemStack)null);
                                }

                                var14.onPickupFromSlot(par4EntityPlayer, var16);
                            }
                        }
                    }
                }

                this.detectAndSendChanges();
            }
        }

        return var5;
    }

    public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot)
    {
        return true;
    }

    protected void retrySlotClick(int par1, int par2, boolean par3, EntityPlayer par4EntityPlayer)
    {
        this.slotClick(par1, par2, 1, par4EntityPlayer);
    }

    /**
     * Callback for when the crafting gui is closed.
     */
    public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
        InventoryPlayer var2 = par1EntityPlayer.inventory;

        if (var2.getItemStack() != null)
        {
            par1EntityPlayer.dropPlayerItem(var2.getItemStack());
            var2.setItemStack((ItemStack)null);
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory par1IInventory)
    {
        this.detectAndSendChanges();
    }

    /**
     * args: slotID, itemStack to put in slot
     */
    public void putStackInSlot(int par1, ItemStack par2ItemStack)
    {
        this.getSlot(par1).putStack(par2ItemStack);
    }

    /**
     * places itemstacks in first x slots, x being aitemstack.lenght
     */
    public void putStacksInSlots(ItemStack[] par1ArrayOfItemStack)
    {
        for (int var2 = 0; var2 < par1ArrayOfItemStack.length; ++var2)
        {
            this.getSlot(var2).putStack(par1ArrayOfItemStack[var2]);
        }
    }

    public void updateProgressBar(int par1, int par2) {}

    /**
     * Gets a unique transaction ID. Parameter is unused.
     */
    public short getNextTransactionID(InventoryPlayer par1InventoryPlayer)
    {
        ++this.transactionID;
        return this.transactionID;
    }

    /**
     * NotUsing because adding a player twice is an error
     */
    public boolean isPlayerNotUsingContainer(EntityPlayer par1EntityPlayer)
    {
        return !this.playerList.contains(par1EntityPlayer);
    }

    /**
     * adds or removes the player from the container based on par2
     */
    public void setPlayerIsPresent(EntityPlayer par1EntityPlayer, boolean par2)
    {
        if (par2)
        {
            this.playerList.remove(par1EntityPlayer);
        }
        else
        {
            this.playerList.add(par1EntityPlayer);
        }
    }

    public abstract boolean canInteractWith(EntityPlayer var1);

    public static int func_94529_b(int par0)
    {
        return par0 >> 2 & 3;
    }

    public static int func_94532_c(int par0)
    {
        return par0 & 3;
    }

    public static int func_94534_d(int par0, int par1)
    {
        return par0 & 3 | (par1 & 3) << 2;
    }

    public static boolean func_94528_d(int par0)
    {
        return par0 == 0 || par0 == 1;
    }

    protected void func_94533_d()
    {
        this.field_94536_g = 0;
        this.field_94537_h.clear();
    }

    public static boolean func_94527_a(Slot par0Slot, ItemStack par1ItemStack, boolean par2)
    {
        boolean var3 = par0Slot == null || !par0Slot.getHasStack();

        if (par0Slot != null && par0Slot.getHasStack() && par1ItemStack != null && par1ItemStack.isItemEqual(par0Slot.getStack()) && ItemStack.areItemStackTagsEqual(par0Slot.getStack(), par1ItemStack))
        {
            int var4 = par2 ? 0 : par1ItemStack.stackSize;
            var3 |= par0Slot.getStack().stackSize + var4 <= par1ItemStack.getMaxStackSize();
        }

        return var3;
    }

    public static void func_94525_a(Set par0Set, int par1, ItemStack par2ItemStack, int par3)
    {
        switch (par1)
        {
            case 0:
                par2ItemStack.stackSize = MathHelper.floor_float((float)par2ItemStack.stackSize / (float)par0Set.size());
                break;

            case 1:
                par2ItemStack.stackSize = 1;
        }

        par2ItemStack.stackSize += par3;
    }

    public boolean func_94531_b(Slot par1Slot)
    {
        return true;
    }

    public static int calcRedstoneFromInventory(IInventory par0IInventory)
    {
        if (par0IInventory == null)
        {
            return 0;
        }
        else
        {
            int var1 = 0;
            float var2 = 0.0F;

            for (int var3 = 0; var3 < par0IInventory.getSizeInventory(); ++var3)
            {
                ItemStack var4 = par0IInventory.getStackInSlot(var3);

                if (var4 != null)
                {
                    var2 += (float)var4.stackSize / (float)Math.min(par0IInventory.getInventoryStackLimit(), var4.getMaxStackSize());
                    ++var1;
                }
            }

            var2 /= (float)par0IInventory.getSizeInventory();
            return MathHelper.floor_float(var2 * 14.0F) + (var1 > 0 ? 1 : 0);
        }
    }

    /**
     * merges provided ItemStack with the first avaliable one in the container/player inventory
     */
    protected boolean mergeItemStack(ItemStack par1ItemStack, int par2, int par3, boolean par4)
    {
        return par4 && par3 - par2 == 36 ? this.MergeItemStackFavoringHotbar(par1ItemStack, par2, par3) : this.mergeItemStack(par1ItemStack, par2, par3);
    }

    protected boolean mergeItemStack(ItemStack var1, int var2, int var3)
    {
        boolean var4 = false;
        int var5;

        if (var1.isStackable())
        {
            for (var5 = var2; var5 < var3 && var1.stackSize > 0; ++var5)
            {
                var4 |= this.AttemptToMergeWithSlot(var1, var5);
            }
        }

        if (var1.stackSize > 0)
        {
            for (var5 = var2; var5 < var3 && var1.stackSize > 0; ++var5)
            {
                var4 |= this.AttemptToMergeWithSlotIfEmpty(var1, var5);
            }
        }

        return var4;
    }

    protected boolean MergeItemStackFavoringHotbar(ItemStack var1, int var2, int var3)
    {
        boolean var4 = false;
        int var5;

        if (var1.isStackable())
        {
            for (var5 = var3 - 9; var5 < var3 && var1.stackSize > 0; ++var5)
            {
                var4 |= this.AttemptToMergeWithSlot(var1, var5);
            }

            for (var5 = var2; var5 < var3 - 9 && var1.stackSize > 0; ++var5)
            {
                var4 |= this.AttemptToMergeWithSlot(var1, var5);
            }
        }

        if (var1.stackSize > 0)
        {
            for (var5 = var3 - 9; var5 < var3 && var1.stackSize > 0; ++var5)
            {
                var4 |= this.AttemptToMergeWithSlotIfEmpty(var1, var5);
            }

            for (var5 = var2; var5 < var3 - 9 && var1.stackSize > 0; ++var5)
            {
                var4 |= this.AttemptToMergeWithSlotIfEmpty(var1, var5);
            }
        }

        return var4;
    }

    protected boolean AttemptToMergeWithSlot(ItemStack var1, int var2)
    {
        Slot var3 = (Slot)this.inventorySlots.get(var2);
        ItemStack var4 = var3.getStack();

        if (var4 != null && var4.itemID == var1.itemID && (!var1.getHasSubtypes() || var1.getItemDamage() == var4.getItemDamage()) && ItemStack.areItemStackTagsEqual(var1, var4))
        {
            int var5 = var4.stackSize + var1.stackSize;
            int var6 = var1.getMaxStackSize();

            if (var3.getSlotStackLimit() < var6)
            {
                var6 = var3.getSlotStackLimit();
            }

            if (var4.stackSize < var6)
            {
                if (var5 <= var6)
                {
                    var1.stackSize = 0;
                    var4.stackSize = var5;
                }
                else
                {
                    var1.stackSize -= var6 - var4.stackSize;
                    var4.stackSize = var6;
                }

                var3.onSlotChanged();
                return true;
            }
        }

        return false;
    }

    protected boolean AttemptToMergeWithSlotIfEmpty(ItemStack var1, int var2)
    {
        boolean var3 = false;
        Slot var4 = (Slot)this.inventorySlots.get(var2);
        ItemStack var5 = var4.getStack();

        if (var5 == null)
        {
            int var6 = var1.getMaxStackSize();

            if (var4.getSlotStackLimit() < var6)
            {
                var6 = var4.getSlotStackLimit();
            }

            if (var1.stackSize <= var6)
            {
                var4.putStack(var1.copy());
                var1.stackSize = 0;
            }
            else
            {
                var4.putStack(var1.copy());
                var1.stackSize -= var6;
                var4.getStack().stackSize = var6;
            }

            var4.onSlotChanged();
            return true;
        }
        else
        {
            return false;
        }
    }
}
