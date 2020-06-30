package net.minecraft.src;

public class FCTileEntityCauldron extends FCTileEntityCookingVessel
{
    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);

        if (var1.hasKey("m_iCauldronCookCounter"))
        {
            this.m_iCookCounter = var1.getInteger("m_iCauldronCookCounter");
        }

        if (var1.hasKey("m_iRenderCooldownCounter"))
        {
            this.m_iStokedCooldownCounter = var1.getInteger("m_iRenderCooldownCounter");
        }

        if (var1.hasKey("m_bContainsValidIngrediantsForState"))
        {
            this.m_bContainsValidIngrediantsForState = var1.getBoolean("m_bContainsValidIngrediantsForState");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setInteger("m_iCauldronCookCounter", this.m_iCookCounter);
        var1.setInteger("m_iRenderCooldownCounter", this.m_iStokedCooldownCounter);
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Cauldron";
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

    public void ValidateContentsForState()
    {
        this.m_bContainsValidIngrediantsForState = false;

        if (this.m_iFireUnderType == 1)
        {
            if (FCCraftingManagerCauldron.getInstance().GetCraftingResult(this) != null)
            {
                this.m_bContainsValidIngrediantsForState = true;
            }
            else if (this.GetUncookedItemInventoryIndex() >= 0)
            {
                this.m_bContainsValidIngrediantsForState = true;
            }
            else if (FCUtilsInventory.GetFirstOccupiedStackOfItem(this, FCBetterThanWolves.fcItemDung.itemID) >= 0 && this.ContainsNonFoulFood())
            {
                this.m_bContainsValidIngrediantsForState = true;
            }
        }
        else if (this.m_iFireUnderType == 2)
        {
            if (this.DoesContainExplosives())
            {
                this.m_bContainsValidIngrediantsForState = true;
            }
            else if (FCCraftingManagerCauldronStoked.getInstance().GetCraftingResult(this) != null)
            {
                this.m_bContainsValidIngrediantsForState = true;
            }
        }
    }

    protected FCCraftingManagerBulk GetCraftingManager(int var1)
    {
        return (FCCraftingManagerBulk)(var1 == 1 ? FCCraftingManagerCauldron.getInstance() : (var1 == 2 ? FCCraftingManagerCauldronStoked.getInstance() : null));
    }

    protected boolean AttemptToCookNormal()
    {
        int var1 = FCUtilsInventory.GetFirstOccupiedStackOfItem(this, FCBetterThanWolves.fcItemDung.itemID);
        return var1 >= 0 && this.TaintAllNonFoulFoodInInventory() ? true : (super.AttemptToCookNormal() ? true : this.AttemptToCookFood());
    }

    private boolean AttemptToCookFood()
    {
        int var1 = this.GetUncookedItemInventoryIndex();

        if (var1 >= 0)
        {
            ItemStack var2 = FurnaceRecipes.smelting().getSmeltingResult(this.m_Contents[var1].getItem().itemID);
            ItemStack var3 = var2.copy();
            this.decrStackSize(var1, 1);

            if (!FCUtilsInventory.AddItemStackToInventory(this, var3))
            {
                FCUtilsItem.EjectStackWithRandomOffset(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord, var3);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public int GetUncookedItemInventoryIndex()
    {
        for (int var1 = 0; var1 < 27; ++var1)
        {
            if (this.m_Contents[var1] != null)
            {
                Item var2 = this.m_Contents[var1].getItem();

                if (var2 != null && var2 instanceof ItemFood && FurnaceRecipes.smelting().getSmeltingResult(var2.itemID) != null)
                {
                    return var1;
                }
            }
        }

        return -1;
    }

    private boolean ContainsNonFoulFood()
    {
        for (int var1 = 0; var1 < 27; ++var1)
        {
            if (this.m_Contents[var1] != null)
            {
                Item var2 = this.m_Contents[var1].getItem();

                if (var2 != null)
                {
                    int var3 = var2.itemID;

                    if (var2.itemID != FCBetterThanWolves.fcItemFoulFood.itemID && var3 != FCBetterThanWolves.fcItemMushroomBrown.itemID && var3 != FCBetterThanWolves.fcItemMushroomRed.itemID && var2 instanceof ItemFood)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean TaintAllNonFoulFoodInInventory()
    {
        boolean var1 = false;

        for (int var2 = 0; var2 < 27; ++var2)
        {
            if (this.m_Contents[var2] != null)
            {
                Item var3 = this.m_Contents[var2].getItem();

                if (var3 != null)
                {
                    int var4 = var3.itemID;

                    if (var3.itemID != FCBetterThanWolves.fcItemFoulFood.itemID && var4 != FCBetterThanWolves.fcItemMushroomBrown.itemID && var4 != FCBetterThanWolves.fcItemMushroomRed.itemID && var3 instanceof ItemFood)
                    {
                        int var5 = this.m_Contents[var2].stackSize;
                        this.m_Contents[var2] = null;
                        ItemStack var6 = new ItemStack(FCBetterThanWolves.fcItemFoulFood, var5);
                        this.setInventorySlotContents(var2, var6);
                        var1 = true;
                    }
                }
            }
        }

        if (var1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
