package net.minecraft.src;

public class FCTileEntityCrucible extends FCTileEntityCookingVessel
{
    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        this.m_iCookCounter = var1.getInteger("m_iCrucibleCookCounter");

        if (var1.hasKey("m_iStokedCooldownCounter"))
        {
            this.m_iStokedCooldownCounter = var1.getInteger("m_iStokedCooldownCounter");
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
        var1.setInteger("m_iCrucibleCookCounter", this.m_iCookCounter);
        var1.setInteger("m_iStokedCooldownCounter", this.m_iStokedCooldownCounter);
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Crucible";
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
            if (FCCraftingManagerCrucible.getInstance().GetCraftingResult(this) != null)
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
            else if (FCCraftingManagerCrucibleStoked.getInstance().GetCraftingResult(this) != null)
            {
                this.m_bContainsValidIngrediantsForState = true;
            }
            else if (this.GetFirstStackThatContainsItemsDestroyedByStokedFire() >= 0)
            {
                this.m_bContainsValidIngrediantsForState = true;
            }
        }
    }

    protected FCCraftingManagerBulk GetCraftingManager(int var1)
    {
        return (FCCraftingManagerBulk)(var1 == 1 ? FCCraftingManagerCrucible.getInstance() : (var1 == 2 ? FCCraftingManagerCrucibleStoked.getInstance() : null));
    }

    protected boolean AttemptToCookStoked()
    {
        int var1 = this.GetFirstStackThatContainsItemsDestroyedByStokedFire();

        if (var1 >= 0)
        {
            this.decrStackSize(var1, 1);
            return true;
        }
        else
        {
            return super.AttemptToCookStoked();
        }
    }

    private int GetFirstStackThatContainsItemsDestroyedByStokedFire()
    {
        for (int var1 = 0; var1 < this.getSizeInventory(); ++var1)
        {
            if (this.getStackInSlot(var1) != null && this.getStackInSlot(var1).getItem().IsIncineratedInCrucible())
            {
                return var1;
            }
        }

        return -1;
    }
}
