package net.minecraft.src;

import java.util.Random;

public class FCUtilsInventory
{
    public static final int m_iIgnoreMetadata = 32767;

    public static void ClearInventoryContents(IInventory var0)
    {
        for (int var1 = 0; var1 < var0.getSizeInventory(); ++var1)
        {
            ItemStack var2 = var0.getStackInSlot(var1);

            if (var2 != null)
            {
                var0.setInventorySlotContents(var1, (ItemStack)null);
            }
        }
    }

    public static void EjectInventoryContents(World var0, int var1, int var2, int var3, IInventory var4)
    {
        for (int var5 = 0; var5 < var4.getSizeInventory(); ++var5)
        {
            ItemStack var6 = var4.getStackInSlot(var5);

            if (var6 != null)
            {
                float var7 = var0.rand.nextFloat() * 0.7F + 0.15F;
                float var8 = var0.rand.nextFloat() * 0.7F + 0.15F;
                float var9 = var0.rand.nextFloat() * 0.7F + 0.15F;

                while (var6.stackSize > 0)
                {
                    int var10 = var0.rand.nextInt(21) + 10;

                    if (var10 > var6.stackSize)
                    {
                        var10 = var6.stackSize;
                    }

                    var6.stackSize -= var10;
                    EntityItem var11 = new EntityItem(var0, (double)((float)var1 + var7), (double)((float)var2 + var8), (double)((float)var3 + var9), new ItemStack(var6.itemID, var10, var6.getItemDamage()));
                    float var12 = 0.05F;
                    var11.motionX = (double)((float)var0.rand.nextGaussian() * var12);
                    var11.motionY = (double)((float)var0.rand.nextGaussian() * var12 + 0.2F);
                    var11.motionZ = (double)((float)var0.rand.nextGaussian() * var12);
                    CopyEnchantments(var11.getEntityItem(), var6);
                    var0.spawnEntityInWorld(var11);
                }
            }
        }
    }

    public static void CopyEnchantments(ItemStack var0, ItemStack var1)
    {
        if (var1.hasTagCompound())
        {
            var0.setTagCompound((NBTTagCompound)var1.getTagCompound().copy());
        }
    }

    public static ItemStack DecrStackSize(IInventory var0, int var1, int var2)
    {
        if (var0.getStackInSlot(var1) != null)
        {
            ItemStack var3;

            if (var0.getStackInSlot(var1).stackSize <= var2)
            {
                var3 = var0.getStackInSlot(var1);
                var0.setInventorySlotContents(var1, (ItemStack)null);
                return var3;
            }
            else
            {
                var3 = var0.getStackInSlot(var1).splitStack(var2);

                if (var0.getStackInSlot(var1).stackSize == 0)
                {
                    var0.setInventorySlotContents(var1, (ItemStack)null);
                }
                else
                {
                    var0.onInventoryChanged();
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    public static int GetNumOccupiedStacks(IInventory var0)
    {
        return GetNumOccupiedStacksInRange(var0, 0, var0.getSizeInventory() - 1);
    }

    public static int GetNumOccupiedStacksInRange(IInventory var0, int var1, int var2)
    {
        int var3 = 0;

        for (int var4 = var1; var4 <= var2; ++var4)
        {
            if (var0.getStackInSlot(var4) != null)
            {
                ++var3;
            }
        }

        return var3;
    }

    public static int GetRandomOccupiedStackInRange(IInventory var0, Random var1, int var2, int var3)
    {
        int var4 = GetNumOccupiedStacksInRange(var0, var2, var3);

        if (var4 > 0)
        {
            int var5 = var1.nextInt(var4) + 1;
            int var6 = 0;

            for (int var7 = var2; var7 <= var3; ++var7)
            {
                if (var0.getStackInSlot(var7) != null)
                {
                    ++var6;

                    if (var6 >= var5)
                    {
                        return var7;
                    }
                }
            }
        }

        return -1;
    }

    public static int GetFirstOccupiedStack(IInventory var0)
    {
        for (int var1 = 0; var1 < var0.getSizeInventory(); ++var1)
        {
            if (var0.getStackInSlot(var1) != null)
            {
                return var1;
            }
        }

        return -1;
    }

    public static int GetFirstOccupiedStackOfItem(IInventory var0, int var1)
    {
        for (int var2 = 0; var2 < var0.getSizeInventory(); ++var2)
        {
            if (var0.getStackInSlot(var2) != null && var0.getStackInSlot(var2).getItem().itemID == var1)
            {
                return var2;
            }
        }

        return -1;
    }

    public static int GetFirstOccupiedStackNotOfItem(IInventory var0, int var1)
    {
        for (int var2 = 0; var2 < var0.getSizeInventory(); ++var2)
        {
            if (var0.getStackInSlot(var2) != null && var0.getStackInSlot(var2).getItem().itemID != var1)
            {
                return var2;
            }
        }

        return -1;
    }

    public static int GetFirstEmptyStack(IInventory var0)
    {
        return GetFirstEmptyStackInSlotRange(var0, 0, var0.getSizeInventory() - 1);
    }

    public static int GetFirstEmptyStackInSlotRange(IInventory var0, int var1, int var2)
    {
        for (int var3 = var1; var3 <= var2; ++var3)
        {
            if (var0.getStackInSlot(var3) == null)
            {
                return var3;
            }
        }

        return -1;
    }

    public static int CountItemsInInventory(IInventory var0, int var1, int var2)
    {
        return CountItemsInInventory(var0, var1, var2, false);
    }

    public static int CountItemsInInventory(IInventory var0, int var1, int var2, boolean var3)
    {
        int var4 = 0;

        for (int var5 = 0; var5 < var0.getSizeInventory(); ++var5)
        {
            ItemStack var6 = var0.getStackInSlot(var5);

            if (var6 != null && var6.getItem().itemID == var1 && (var2 == 32767 || !var3 && var6.getItemDamage() == var2 || var3 && var6.getItemDamage() != var2))
            {
                var4 += var0.getStackInSlot(var5).stackSize;
            }
        }

        return var4;
    }

    public static boolean ConsumeItemsInInventory(IInventory var0, int var1, int var2, int var3)
    {
        return ConsumeItemsInInventory(var0, var1, var2, var3, false);
    }

    public static boolean ConsumeItemsInInventory(IInventory var0, int var1, int var2, int var3, boolean var4)
    {
        for (int var5 = 0; var5 < var0.getSizeInventory(); ++var5)
        {
            ItemStack var6 = var0.getStackInSlot(var5);

            if (var6 != null)
            {
                Item var7 = var6.getItem();

                if (var7.itemID == var1 && (var2 == 32767 || !var4 && var6.getItemDamage() == var2 || var4 && var6.getItemDamage() != var2))
                {
                    if (var6.stackSize >= var3)
                    {
                        DecrStackSize(var0, var5, var3);
                        return true;
                    }

                    var3 -= var6.stackSize;
                    var0.setInventorySlotContents(var5, (ItemStack)null);
                }
            }
        }

        return false;
    }

    public static boolean AddSingleItemToInventory(IInventory var0, int var1, int var2)
    {
        ItemStack var3 = new ItemStack(var1, 1, var2);
        return AddItemStackToInventory(var0, var3);
    }

    public static boolean AddItemStackToInventory(IInventory var0, ItemStack var1)
    {
        return AddItemStackToInventoryInSlotRange(var0, var1, 0, var0.getSizeInventory() - 1);
    }

    public static boolean AddItemStackToInventoryInSlotRange(IInventory var0, ItemStack var1, int var2, int var3)
    {
        return !var1.isItemDamaged() && AttemptToMergeWithExistingStacksInSlotRange(var0, var1, var2, var3) ? true : AttemptToPlaceInEmptySlotInSlotRange(var0, var1, var2, var3);
    }

    public static boolean AddItemStackToChest(TileEntityChest var0, ItemStack var1)
    {
        World var2 = var0.worldObj;

        for (int var4 = 2; var4 <= 5; ++var4)
        {
            FCUtilsBlockPos var3 = new FCUtilsBlockPos(var0.xCoord, var0.yCoord, var0.zCoord);
            var3.AddFacingAsOffset(var4);
            int var5 = var2.getBlockId(var3.i, var3.j, var3.k);

            if (var5 == Block.chest.blockID || var5 == FCBetterThanWolves.fcBlockChest.blockID)
            {
                TileEntityChest var6 = (TileEntityChest)var2.getBlockTileEntity(var3.i, var3.j, var3.k);

                if (var6 != null)
                {
                    if (var0.xCoord >= var6.xCoord && var0.zCoord >= var6.zCoord)
                    {
                        return AddItemStackToDoubleInventory(var6, var0, var1);
                    }

                    return AddItemStackToDoubleInventory(var0, var6, var1);
                }
            }
        }

        return AddItemStackToInventory(var0, var1);
    }

    private static boolean CanStacksMerge(ItemStack var0, ItemStack var1, int var2)
    {
        return var1 != null && var1.itemID == var0.itemID && var1.isStackable() && var1.stackSize < var1.getMaxStackSize() && var1.stackSize < var2 && (!var1.getHasSubtypes() || var1.getItemDamage() == var0.getItemDamage()) && ItemStack.areItemStackTagsEqual(var1, var0);
    }

    private static int FindSlotToMergeItemStackInSlotRange(IInventory var0, ItemStack var1, int var2, int var3)
    {
        int var4 = var0.getInventoryStackLimit();

        for (int var5 = var2; var5 <= var3; ++var5)
        {
            ItemStack var6 = var0.getStackInSlot(var5);

            if (CanStacksMerge(var1, var6, var4))
            {
                return var5;
            }
        }

        return -1;
    }

    private static boolean AttemptToMergeWithExistingStacks(IInventory var0, ItemStack var1)
    {
        return AttemptToMergeWithExistingStacksInSlotRange(var0, var1, 0, var0.getSizeInventory() - 1);
    }

    private static boolean AttemptToMergeWithExistingStacksInSlotRange(IInventory var0, ItemStack var1, int var2, int var3)
    {
        for (int var4 = FindSlotToMergeItemStackInSlotRange(var0, var1, var2, var3); var4 >= 0; var4 = FindSlotToMergeItemStackInSlotRange(var0, var1, var2, var3))
        {
            int var5 = var1.stackSize;
            ItemStack var6 = var0.getStackInSlot(var4);

            if (var5 > var6.getMaxStackSize() - var6.stackSize)
            {
                var5 = var6.getMaxStackSize() - var6.stackSize;
            }

            if (var5 > var0.getInventoryStackLimit() - var6.stackSize)
            {
                var5 = var0.getInventoryStackLimit() - var6.stackSize;
            }

            if (var5 == 0)
            {
                return false;
            }

            var1.stackSize -= var5;
            var6.stackSize += var5;
            var0.setInventorySlotContents(var4, var6);

            if (var1.stackSize <= 0)
            {
                return true;
            }
        }

        return false;
    }

    private static boolean AttemptToPlaceInEmptySlot(IInventory var0, ItemStack var1)
    {
        return AttemptToPlaceInEmptySlotInSlotRange(var0, var1, 0, var0.getSizeInventory() - 1);
    }

    private static boolean AttemptToPlaceInEmptySlotInSlotRange(IInventory var0, ItemStack var1, int var2, int var3)
    {
        int var4 = var1.itemID;
        int var5 = var1.getItemDamage();

        for (int var6 = GetFirstEmptyStackInSlotRange(var0, var2, var3); var6 >= 0; var6 = GetFirstEmptyStackInSlotRange(var0, var2, var3))
        {
            int var7 = var1.stackSize;

            if (var7 > var0.getInventoryStackLimit())
            {
                var7 = var0.getInventoryStackLimit();
            }

            ItemStack var8 = new ItemStack(var4, var7, var5);
            CopyEnchantments(var8, var1);
            var0.setInventorySlotContents(var6, var8);
            var1.stackSize -= var7;

            if (var1.stackSize <= 0)
            {
                return true;
            }
        }

        return false;
    }

    private static boolean AddItemStackToDoubleInventory(IInventory var0, IInventory var1, ItemStack var2)
    {
        if (!var2.isItemDamaged())
        {
            if (AttemptToMergeWithExistingStacks(var0, var2))
            {
                return true;
            }

            if (AttemptToMergeWithExistingStacks(var1, var2))
            {
                return true;
            }
        }

        return AttemptToPlaceInEmptySlot(var0, var2) ? true : AttemptToPlaceInEmptySlot(var1, var2);
    }
}
