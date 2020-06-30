package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class FCTileEntityMillStone extends TileEntity implements IInventory
{
    public ItemStack m_stackMilling = null;
    public static final String m_sUnlocalizedName = "container.fcMillStone";
    private final double m_dMaxPlayerInteractionDistSq = 64.0D;
    private final int m_iTimeToGrind = 200;
    private boolean m_bValidateContentsOnUpdate = true;
    private boolean m_bContainsIngrediantsToGrind;
    public int m_iGrindCounter = 0;
    private final int m_iLegacyInventorySize = 3;
    private ItemStack[] m_legacyInventory = null;

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);

        if (var1.hasKey("Items"))
        {
            NBTTagList var2 = var1.getTagList("Items");
            this.m_legacyInventory = new ItemStack[3];

            for (int var3 = 0; var3 < var2.tagCount(); ++var3)
            {
                NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
                int var5 = var4.getByte("Slot") & 255;

                if (var5 >= 0 && var5 < this.m_legacyInventory.length)
                {
                    this.m_legacyInventory[var5] = ItemStack.loadItemStackFromNBT(var4);
                }
            }
        }

        if (var1.hasKey("grindCounter"))
        {
            this.m_iGrindCounter = var1.getInteger("grindCounter");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);

        if (!this.IsLegacyInventoryEmpty())
        {
            NBTTagList var2 = new NBTTagList();

            for (int var3 = 0; var3 < this.m_legacyInventory.length; ++var3)
            {
                if (this.m_legacyInventory[var3] != null)
                {
                    NBTTagCompound var4 = new NBTTagCompound();
                    var4.setByte("Slot", (byte)var3);
                    this.m_legacyInventory[var3].writeToNBT(var4);
                    var2.appendTag(var4);
                }
            }

            var1.setTag("Items", var2);
        }

        var1.setInteger("grindCounter", this.m_iGrindCounter);
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
            int var1 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
            FCBlockMillStone var2 = (FCBlockMillStone)Block.blocksList[var1];

            if (this.m_bValidateContentsOnUpdate)
            {
                this.ValidateContentsForGrinding(var2);
            }

            if (this.m_bContainsIngrediantsToGrind && var2.GetIsMechanicalOn(this.worldObj, this.xCoord, this.yCoord, this.zCoord))
            {
                ++this.m_iGrindCounter;

                if (this.m_iGrindCounter >= 200)
                {
                    this.GrindContents(var2);
                    this.m_iGrindCounter = 0;
                    this.m_bValidateContentsOnUpdate = true;
                }

                this.CheckForNauseateNearbyPlayers(var2);
            }
        }
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 1;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return var1 == 0 ? this.m_stackMilling : null;
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
        if (var1 == 0 && this.m_stackMilling != null)
        {
            ItemStack var2 = this.m_stackMilling;
            this.m_stackMilling = null;
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
        if (var1 == 0)
        {
            this.m_stackMilling = var2;

            if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
            {
                var2.stackSize = this.getInventoryStackLimit();
            }

            this.onInventoryChanged();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.fcMillStone";
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 1;
    }

    /**
     * Called when an the contents of an Inventory change, usually
     */
    public void onInventoryChanged()
    {
        super.onInventoryChanged();

        if (this.worldObj != null && !this.worldObj.isRemote)
        {
            if (this.ContainsWholeCompanionCube())
            {
                this.worldObj.playSoundEffect((double)((float)this.xCoord + 0.5F), (double)((float)this.yCoord + 0.5F), (double)((float)this.zCoord + 0.5F), "mob.wolf.whine", 0.5F, 2.6F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.8F);
            }

            this.m_bValidateContentsOnUpdate = true;
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) == this ? var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D : false;
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
        return false;
    }

    public void EjectStackOnMilled(ItemStack var1)
    {
        int var2 = 2 + this.worldObj.rand.nextInt(4);
        Vec3 var3 = Vec3.createVectorHelper(this.worldObj.rand.nextDouble() * 1.25D - 0.125D, (double)(this.worldObj.rand.nextFloat() * 0.0625F + 0.4375F), -0.20000000298023224D);
        var3.RotateAsBlockPosAroundJToFacing(var2);
        EntityItem var4 = new EntityItem(this.worldObj, (double)this.xCoord + var3.xCoord, (double)this.yCoord + var3.yCoord, (double)this.zCoord + var3.zCoord, var1);
        Vec3 var5 = Vec3.createVectorHelper(this.worldObj.rand.nextGaussian() * 0.025D, this.worldObj.rand.nextGaussian() * 0.025D + 0.10000000149011612D, -0.06D + this.worldObj.rand.nextGaussian() * 0.04D);
        var5.RotateAsVectorAroundJToFacing(var2);
        var4.motionX = var5.xCoord;
        var4.motionY = var5.yCoord;
        var4.motionZ = var5.zCoord;
        var4.delayBeforeCanPickup = 10;
        this.worldObj.spawnEntityInWorld(var4);
    }

    public int getGrindProgressScaled(int var1)
    {
        return this.m_iGrindCounter * var1 / 200;
    }

    public boolean IsGrinding()
    {
        return this.m_iGrindCounter > 0;
    }

    public boolean ContainsWholeCompanionCube()
    {
        return this.m_stackMilling != null && this.m_stackMilling.itemID == FCBetterThanWolves.fcCompanionCube.blockID && this.m_stackMilling.getItemDamage() == 0;
    }

    private boolean GrindContents(FCBlockMillStone var1)
    {
        if (this.m_stackMilling != null && FCCraftingManagerMillStone.getInstance().HasRecipeForSingleIngredient(this.m_stackMilling))
        {
            List var2 = FCCraftingManagerMillStone.getInstance().GetCraftingResult(this.m_stackMilling);

            if (var2 != null)
            {
                if (this.m_stackMilling.itemID == FCBetterThanWolves.fcCompanionCube.blockID && this.m_stackMilling.getItemDamage() == 0)
                {
                    this.worldObj.playAuxSFX(2242, this.xCoord, this.yCoord, this.zCoord, 0);
                }

                for (int var3 = 0; var3 < var2.size(); ++var3)
                {
                    ItemStack var4 = ((ItemStack)var2.get(var3)).copy();

                    if (var4 != null)
                    {
                        this.EjectStackOnMilled(var4);
                    }
                }

                this.m_stackMilling = null;
                return true;
            }
        }

        return false;
    }

    private void ValidateContentsForGrinding(FCBlockMillStone var1)
    {
        int var2 = var1.GetCurrentGrindingType(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        byte var3 = 0;
        this.MigrateLegacyInventory();

        if (this.m_stackMilling != null)
        {
            if (FCCraftingManagerMillStone.getInstance().HasRecipeForSingleIngredient(this.m_stackMilling))
            {
                this.m_bContainsIngrediantsToGrind = true;
                int var4 = this.m_stackMilling.getItem().itemID;

                if (var4 == FCBetterThanWolves.fcCompanionCube.blockID && this.m_stackMilling.getItemDamage() == 0)
                {
                    var3 = 3;
                }
                else if (var4 == Block.netherrack.blockID)
                {
                    var3 = 2;
                }
                else
                {
                    var3 = 1;
                }
            }
            else
            {
                var3 = 4;
                this.m_iGrindCounter = 0;
                this.m_bContainsIngrediantsToGrind = false;
            }
        }
        else
        {
            this.m_iGrindCounter = 0;
            this.m_bContainsIngrediantsToGrind = false;
        }

        this.m_bValidateContentsOnUpdate = false;

        if (var2 != var3)
        {
            var1.SetCurrentGrindingType(this.worldObj, this.xCoord, this.yCoord, this.zCoord, var3);
        }
    }

    private void CheckForNauseateNearbyPlayers(FCBlockMillStone var1)
    {
        int var2 = var1.GetCurrentGrindingType(this.worldObj, this.xCoord, this.yCoord, this.zCoord);

        if (var2 == 2 && this.worldObj.getTotalWorldTime() % 40L == 0L)
        {
            this.ApplyPotionEffectToPlayersInRange(Potion.confusion.getId(), 120, 0, 10.0D);
        }
    }

    private void ApplyPotionEffectToPlayersInRange(int var1, int var2, int var3, double var4)
    {
        Iterator var6 = this.worldObj.playerEntities.iterator();

        while (var6.hasNext())
        {
            EntityPlayer var7 = (EntityPlayer)var6.next();

            if (!var7.isDead && !var7.capabilities.isCreativeMode && Math.abs((double)this.xCoord - var7.posX) <= var4 && Math.abs((double)this.yCoord - var7.posY) <= var4 && Math.abs((double)this.zCoord - var7.posZ) <= var4)
            {
                var7.addPotionEffect(new PotionEffect(var1, var2, var3, true));
            }
        }
    }

    private void MigrateLegacyInventory()
    {
        if (this.m_stackMilling == null && this.m_legacyInventory != null)
        {
            for (int var1 = 0; var1 < this.m_legacyInventory.length; ++var1)
            {
                if (this.m_legacyInventory[var1] != null)
                {
                    ItemStack var2 = this.m_legacyInventory[var1];
                    this.m_stackMilling = var2.copy();
                    this.m_stackMilling.stackSize = 1;
                    --this.m_legacyInventory[var1].stackSize;

                    if (var2.stackSize <= 0)
                    {
                        this.m_legacyInventory[var1] = null;

                        if (this.IsLegacyInventoryEmpty())
                        {
                            this.m_legacyInventory = null;
                            break;
                        }
                    }
                }
            }
        }
    }

    private boolean IsLegacyInventoryEmpty()
    {
        if (this.m_legacyInventory != null)
        {
            for (int var1 = 0; var1 < this.m_legacyInventory.length; ++var1)
            {
                if (this.m_legacyInventory[var1] != null && this.m_legacyInventory[var1].stackSize > 0)
                {
                    return false;
                }
            }
        }

        return true;
    }

    public void EjectContents(int var1)
    {
        if (var1 < 2)
        {
            var1 = this.worldObj.rand.nextInt(4) + 2;
        }

        if (this.m_legacyInventory != null)
        {
            for (int var2 = 0; var2 < this.m_legacyInventory.length; ++var2)
            {
                if (this.m_legacyInventory[var2] != null && this.m_legacyInventory[var2].stackSize > 0)
                {
                    FCUtilsItem.EjectStackFromBlockTowardsFacing(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.m_legacyInventory[var2], var1);
                    this.m_legacyInventory[var2] = null;
                }
            }

            this.m_legacyInventory = null;
        }

        if (this.m_stackMilling != null)
        {
            FCUtilsItem.EjectStackFromBlockTowardsFacing(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.m_stackMilling, var1);
            this.m_stackMilling = null;
            this.onInventoryChanged();
        }
    }

    public void AttemptToAddSingleItemFromStack(ItemStack var1)
    {
        if (this.m_stackMilling == null)
        {
            this.m_stackMilling = var1.copy();
            this.m_stackMilling.stackSize = 1;
            --var1.stackSize;
            this.onInventoryChanged();
        }
    }
}
