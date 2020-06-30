package net.minecraft.src;

import java.util.Iterator;
import java.util.Random;

public class FCContainerInfernalEnchanter extends Container
{
    public IInventory m_tableInventory;
    private World m_localWorld;
    private int m_iBlockI;
    private int m_iBlockJ;
    private int m_iBlockK;
    private static final double m_dMaxInteractionDistance = 8.0D;
    private static final double m_dMaxInteractionDistanceSq = 64.0D;
    private static final int m_iSlotScreenWidth = 18;
    private static final int m_iSlotScreenHeight = 18;
    private static final int m_iScrollSlotScreenPosX = 17;
    private static final int m_iScrollSlotScreenPosY = 37;
    private static final int m_iItemSlotScreenPosX = 17;
    private static final int m_iItemSlotScreenPosY = 75;
    private static final int m_iPlayerInventoryScreenPosX = 8;
    private static final int m_iPlayerInventoryScreenPosY = 129;
    private static final int m_iPlayerHotbarScreenPosY = 187;
    private static final int m_iHorizontalBookShelfCheckDistance = 8;
    private static final int m_iVerticalPositiveBookShelfCheckDistance = 8;
    private static final int m_iVerticalNegativeBookShelfCheckDistance = 8;
    public static final int m_iMaxEnchantmentPowerLevel = 5;
    public int[] m_CurrentEnchantmentLevels;
    public int m_iMaxSurroundingBookshelfLevel;
    public int m_iLastMaxSurroundingBookshelfLevel;
    public long m_lNameSeed;
    Random rand;

    public FCContainerInfernalEnchanter(InventoryPlayer var1, World var2, int var3, int var4, int var5)
    {
        this.m_localWorld = var2;
        this.m_iBlockI = var3;
        this.m_iBlockJ = var4;
        this.m_iBlockK = var5;
        this.rand = new Random();
        this.m_lNameSeed = this.rand.nextLong();
        this.m_tableInventory = new FCInventoryInfernalEnchanter(this, "fcInfernalEnchanterInv", 2);
        this.m_CurrentEnchantmentLevels = new int[5];
        this.ResetEnchantingLevels();
        this.m_iMaxSurroundingBookshelfLevel = 0;
        this.m_iLastMaxSurroundingBookshelfLevel = 0;
        this.addSlotToContainer(new Slot(this.m_tableInventory, 0, 17, 37));
        this.addSlotToContainer(new Slot(this.m_tableInventory, 1, 17, 75));
        int var6;

        for (var6 = 0; var6 < 3; ++var6)
        {
            for (int var7 = 0; var7 < 9; ++var7)
            {
                this.addSlotToContainer(new Slot(var1, var7 + var6 * 9 + 9, 8 + var7 * 18, 129 + var6 * 18));
            }
        }

        for (var6 = 0; var6 < 9; ++var6)
        {
            this.addSlotToContainer(new Slot(var1, var6, 8 + var6 * 18, 187));
        }

        if (var2 != null && !var2.isRemote)
        {
            this.CheckForSurroundingBookshelves();
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory var1)
    {
        if (var1 == this.m_tableInventory)
        {
            this.m_lNameSeed = this.rand.nextLong();
            this.ResetEnchantingLevels();
            this.ComputeCurrentEnchantmentLevels();

            if (this.m_localWorld != null && !this.m_localWorld.isRemote)
            {
                this.detectAndSendChanges();
            }
        }
    }

    /**
     * Callback for when the crafting gui is closed.
     */
    public void onCraftGuiClosed(EntityPlayer var1)
    {
        super.onCraftGuiClosed(var1);

        if (this.m_localWorld != null && !this.m_localWorld.isRemote)
        {
            for (int var2 = 0; var2 < this.m_tableInventory.getSizeInventory(); ++var2)
            {
                ItemStack var3 = this.m_tableInventory.getStackInSlot(var2);

                if (var3 != null)
                {
                    var1.dropPlayerItem(var3);
                }
            }
        }
    }

    public boolean canInteractWith(EntityPlayer var1)
    {
        return this.m_localWorld != null && !this.m_localWorld.isRemote ? (this.m_localWorld.getBlockId(this.m_iBlockI, this.m_iBlockJ, this.m_iBlockK) != FCBetterThanWolves.fcInfernalEnchanter.blockID ? false : var1.getDistanceSq((double)this.m_iBlockI + 0.5D, (double)this.m_iBlockJ + 0.5D, (double)this.m_iBlockK + 0.5D) <= 64.0D) : true;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer var1, int var2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(var2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (var2 <= 1)
            {
                if (!this.mergeItemStack(var5, 2, 38, true))
                {
                    return null;
                }
            }
            else if (var5.getItem().itemID == FCBetterThanWolves.fcItemArcaneScroll.itemID)
            {
                if (!this.mergeItemStack(var5, 0, 1, false))
                {
                    return null;
                }
            }
            else if (this.GetMaximumEnchantmentCost(var5) > 0)
            {
                if (!this.mergeItemStack(var5, 1, 2, false))
                {
                    return null;
                }
            }
            else if (var2 >= 2 && var2 < 29)
            {
                if (!this.mergeItemStack(var5, 29, 38, false))
                {
                    return null;
                }
            }
            else if (var2 >= 29 && var2 < 38 && !this.mergeItemStack(var5, 2, 29, false))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(var1, var5);

            if (this.m_localWorld != null && !this.m_localWorld.isRemote)
            {
                this.detectAndSendChanges();
            }
        }

        return var3;
    }

    private void CheckForSurroundingBookshelves()
    {
        int var1 = 0;

        for (int var2 = this.m_iBlockI - 8; var2 <= this.m_iBlockI + 8; ++var2)
        {
            for (int var3 = this.m_iBlockJ - 8; var3 <= this.m_iBlockJ + 8; ++var3)
            {
                for (int var4 = this.m_iBlockK - 8; var4 <= this.m_iBlockK + 8; ++var4)
                {
                    if (this.IsValidBookshelf(var2, var3, var4))
                    {
                        ++var1;
                    }
                }
            }
        }

        this.m_iMaxSurroundingBookshelfLevel = var1;
    }

    private boolean IsValidBookshelf(int var1, int var2, int var3)
    {
        int var4 = this.m_localWorld.getBlockId(var1, var2, var3);
        return var4 == Block.bookShelf.blockID && (this.m_localWorld.isAirBlock(var1 + 1, var2, var3) || this.m_localWorld.isAirBlock(var1 - 1, var2, var3) || this.m_localWorld.isAirBlock(var1, var2, var3 + 1) || this.m_localWorld.isAirBlock(var1, var2, var3 - 1));
    }

    private void SetCurrentEnchantingLevels(int var1, int var2, int var3)
    {
        this.ResetEnchantingLevels();

        if (var1 == 1)
        {
            this.m_CurrentEnchantmentLevels[0] = 30;
        }
        else if (var1 == 2)
        {
            this.m_CurrentEnchantmentLevels[0] = 15;
            this.m_CurrentEnchantmentLevels[1] = 30;
        }
        else if (var1 == 3)
        {
            this.m_CurrentEnchantmentLevels[0] = 10;
            this.m_CurrentEnchantmentLevels[1] = 20;
            this.m_CurrentEnchantmentLevels[2] = 30;
        }
        else if (var1 == 4)
        {
            this.m_CurrentEnchantmentLevels[0] = 8;
            this.m_CurrentEnchantmentLevels[1] = 15;
            this.m_CurrentEnchantmentLevels[2] = 23;
            this.m_CurrentEnchantmentLevels[3] = 30;
        }
        else if (var1 == 5)
        {
            this.m_CurrentEnchantmentLevels[0] = 6;
            this.m_CurrentEnchantmentLevels[1] = 12;
            this.m_CurrentEnchantmentLevels[2] = 18;
            this.m_CurrentEnchantmentLevels[3] = 24;
            this.m_CurrentEnchantmentLevels[4] = 30;
        }

        int var4 = (var2 - 1) * 30;

        for (int var5 = 0; var5 < 5; ++var5)
        {
            if (this.m_CurrentEnchantmentLevels[var5] > 0)
            {
                if (var3 < this.m_CurrentEnchantmentLevels[var5])
                {
                    this.m_CurrentEnchantmentLevels[var5] = 0;
                }
                else
                {
                    this.m_CurrentEnchantmentLevels[var5] += var4;
                }
            }
        }
    }

    private void ResetEnchantingLevels()
    {
        for (int var1 = 0; var1 < 5; ++var1)
        {
            this.m_CurrentEnchantmentLevels[var1] = 0;
        }
    }

    private void ComputeCurrentEnchantmentLevels()
    {
        ItemStack var1 = this.m_tableInventory.getStackInSlot(0);

        if (var1 != null && var1.getItem().itemID == FCBetterThanWolves.fcItemArcaneScroll.itemID)
        {
            ItemStack var2 = this.m_tableInventory.getStackInSlot(1);

            if (var2 != null)
            {
                int var3 = this.GetMaximumEnchantmentCost(var2);

                if (var3 > 0)
                {
                    int var4 = var1.getItemDamage();

                    if (var4 >= Enchantment.enchantmentsList.length || Enchantment.enchantmentsList[var4] == null)
                    {
                        return;
                    }

                    if (this.IsEnchantmentAppropriateForItem(var4, var2) && !this.DoesEnchantmentConflictWithExistingOnes(var4, var2))
                    {
                        int var5 = this.GetMaximumNumberOfEnchantments(var2);
                        int var6 = 0;
                        NBTTagList var7 = var2.getEnchantmentTagList();

                        if (var7 != null)
                        {
                            var6 = var2.getEnchantmentTagList().tagCount();
                        }

                        if (var6 < var5)
                        {
                            this.SetCurrentEnchantingLevels(this.GetMaxEnchantmentPowerLevel(var4, var2), var6 + 1, this.GetMaximumEnchantmentCost(var2));
                        }
                    }
                }
            }
        }
    }

    private int GetMaximumEnchantmentCost(ItemStack var1)
    {
        return var1.getItem().GetInfernalMaxEnchantmentCost();
    }

    private int GetMaximumNumberOfEnchantments(ItemStack var1)
    {
        return var1.getItem().GetInfernalMaxNumEnchants();
    }

    private boolean IsEnchantmentAppropriateForItem(int var1, ItemStack var2)
    {
        return Enchantment.enchantmentsList[var1].canApply(var2);
    }

    private boolean DoesEnchantmentConflictWithExistingOnes(int var1, ItemStack var2)
    {
        NBTTagList var3 = var2.getEnchantmentTagList();

        if (var3 != null)
        {
            int var4 = var2.getEnchantmentTagList().tagCount();

            for (int var5 = 0; var5 < var4; ++var5)
            {
                short var6 = ((NBTTagCompound)var3.tagAt(var5)).getShort("id");

                if (var6 == var1)
                {
                    return true;
                }

                if (var1 == Enchantment.silkTouch.effectId && var6 == Enchantment.fortune.effectId || var1 == Enchantment.fortune.effectId && var6 == Enchantment.silkTouch.effectId)
                {
                    return true;
                }
            }
        }

        return false;
    }

    private int GetMaxEnchantmentPowerLevel(int var1, ItemStack var2)
    {
        return var1 == Enchantment.respiration.effectId && var2.getItem().itemID == FCBetterThanWolves.fcItemPlateHelm.itemID ? 5 : Enchantment.enchantmentsList[var1].getMaxLevel();
    }

    /**
     * enchants the item on the table using the specified slot; also deducts XP from player
     */
    public boolean enchantItem(EntityPlayer var1, int var2)
    {
        if (this.m_localWorld != null && !this.m_localWorld.isRemote)
        {
            int var3 = this.m_CurrentEnchantmentLevels[var2];

            if (var3 > 0)
            {
                boolean var4 = var3 <= var1.experienceLevel && var3 <= this.m_iMaxSurroundingBookshelfLevel;

                if (var4)
                {
                    ItemStack var5 = this.m_tableInventory.getStackInSlot(0);

                    if (var5 != null && var5.getItem().itemID == FCBetterThanWolves.fcItemArcaneScroll.itemID)
                    {
                        ItemStack var6 = this.m_tableInventory.getStackInSlot(1);

                        if (var6 != null)
                        {
                            int var7 = var5.getItemDamage();

                            if (var7 < Enchantment.enchantmentsList.length && Enchantment.enchantmentsList[var7] != null)
                            {
                                var6.addEnchantment(Enchantment.enchantmentsList[var7], var2 + 1);
                                var1.addExperienceLevel(-var3);
                                this.m_tableInventory.decrStackSize(0, 1);
                                this.onCraftMatrixChanged(this.m_tableInventory);
                                this.m_localWorld.playSoundAtEntity(var1, "ambient.weather.thunder", 1.0F, this.m_localWorld.rand.nextFloat() * 0.4F + 0.8F);
                                return true;
                            }

                            return false;
                        }
                    }
                }
            }

            return false;
        }
        else
        {
            return true;
        }
    }

    public void addCraftingToCrafters(ICrafting var1)
    {
        super.addCraftingToCrafters(var1);
        var1.sendProgressBarUpdate(this, 0, this.m_iMaxSurroundingBookshelfLevel);
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

            if (this.m_iLastMaxSurroundingBookshelfLevel != this.m_iMaxSurroundingBookshelfLevel)
            {
                var2.sendProgressBarUpdate(this, 0, this.m_iMaxSurroundingBookshelfLevel);
            }
        }

        this.m_iLastMaxSurroundingBookshelfLevel = this.m_iMaxSurroundingBookshelfLevel;
    }

    public void updateProgressBar(int var1, int var2)
    {
        if (var1 == 0)
        {
            this.m_iMaxSurroundingBookshelfLevel = var2;
        }
    }
}
