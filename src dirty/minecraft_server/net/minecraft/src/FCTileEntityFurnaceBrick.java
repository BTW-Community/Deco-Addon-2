package net.minecraft.src;

public class FCTileEntityFurnaceBrick extends TileEntityFurnace implements FCITileEntityDataPacketHandler
{
    private static final float m_fChanceOfFireSpread = 0.01F;
    private boolean m_bLightOnNextUpdate = false;
    private ItemStack m_cookStack = null;
    private int m_iUnlitFuelBurnTime = 0;
    private int m_iVisualFuelLevel = 0;
    private final int m_iBrickBurnTimeMultiplier = 4;
    private final int m_iCookTimeMultiplier = 4;
    private final int m_iMaxFuelBurnTime = 14200;
    private final int m_iVisualFuelLevelIncrement = 1600;
    private final int m_iVisualSputterFuelLevel = 400;

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean var1 = this.furnaceBurnTime > 0;
        boolean var2 = false;

        if (this.furnaceBurnTime > 0)
        {
            --this.furnaceBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (var1 || this.m_bLightOnNextUpdate)
            {
                this.furnaceBurnTime += this.m_iUnlitFuelBurnTime;
                this.m_iUnlitFuelBurnTime = 0;
                this.m_bLightOnNextUpdate = false;
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.furnaceCookTime;

                if (this.furnaceCookTime >= this.GetCookTimeForCurrentItem())
                {
                    this.furnaceCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }
            else
            {
                this.furnaceCookTime = 0;
            }

            if (this.isBurning() && this.worldObj.rand.nextFloat() <= 0.01F)
            {
                FCUtilsBlockPos var3 = new FCUtilsBlockPos(this.xCoord, this.yCoord, this.zCoord);
                int var4 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) & 7;
                var3.AddFacingAsOffset(var4);
                FCBlockFire.CheckForFireSpreadAndDestructionToOneBlockLocation(this.worldObj, var3.i, var3.j, var3.k);
            }

            FCBlockFurnace var5 = (FCBlockFurnace)Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)];

            if (var1 != this.isBurning())
            {
                var2 = true;
                var5.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord, false);
            }

            this.UpdateCookStack();
            this.UpdateVisualFuelLevel();
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.fcFurnaceBrick";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);

        if (var1.hasKey("fcUnlitFuel"))
        {
            this.m_iUnlitFuelBurnTime = var1.getInteger("fcUnlitFuel");
        }

        if (var1.hasKey("fcVisualFuel"))
        {
            this.m_iVisualFuelLevel = var1.getByte("fcVisualFuel");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setInteger("fcUnlitFuel", this.m_iUnlitFuelBurnTime);
        var1.setByte("fcVisualFuel", (byte)this.m_iVisualFuelLevel);
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public int getItemBurnTime(ItemStack var1)
    {
        return super.getItemBurnTime(var1) * 4;
    }

    protected int GetCookTimeForCurrentItem()
    {
        return super.GetCookTimeForCurrentItem() * 4;
    }

    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();

        if (this.m_cookStack != null)
        {
            NBTTagCompound var2 = new NBTTagCompound();
            this.m_cookStack.writeToNBT(var2);
            var1.setCompoundTag("x", var2);
        }

        var1.setByte("y", (byte)this.m_iVisualFuelLevel);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }

    public void readNBTFromPacket(NBTTagCompound var1)
    {
        NBTTagCompound var2 = var1.getCompoundTag("x");

        if (var2 != null)
        {
            this.m_cookStack = ItemStack.loadItemStackFromNBT(var2);
        }

        this.m_iVisualFuelLevel = var1.getByte("y");
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }

    public boolean AttemptToLight()
    {
        if (this.m_iUnlitFuelBurnTime > 0)
        {
            this.m_bLightOnNextUpdate = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean HasValidFuel()
    {
        return this.m_iUnlitFuelBurnTime > 0;
    }

    private void UpdateCookStack()
    {
        ItemStack var1 = this.furnaceItemStacks[0];

        if (var1 == null)
        {
            var1 = this.furnaceItemStacks[2];

            if (var1 == null)
            {
                var1 = this.furnaceItemStacks[1];
            }
        }

        if (!ItemStack.areItemStacksEqual(var1, this.m_cookStack))
        {
            this.SetCookStack(var1);
        }
    }

    public void SetCookStack(ItemStack var1)
    {
        if (var1 != null)
        {
            this.m_cookStack = var1.copy();
        }
        else
        {
            this.m_cookStack = null;
        }

        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public ItemStack GetCookStack()
    {
        return this.m_cookStack;
    }

    public void GivePlayerCookStack(EntityPlayer var1, int var2)
    {
        if (!this.worldObj.isRemote)
        {
            this.EjectAllNotCookStacksToFacing(var1, var2);
        }

        FCUtilsItem.GivePlayerStackOrEjectFromTowardsFacing(var1, this.m_cookStack, this.xCoord, this.yCoord, this.zCoord, var2);
        this.furnaceItemStacks[0] = null;
        this.furnaceItemStacks[1] = null;
        this.furnaceItemStacks[2] = null;
        this.SetCookStack((ItemStack)null);
    }

    private void EjectAllNotCookStacksToFacing(EntityPlayer var1, int var2)
    {
        if (this.furnaceItemStacks[0] != null && !ItemStack.areItemStacksEqual(this.furnaceItemStacks[0], this.m_cookStack))
        {
            FCUtilsItem.EjectStackFromBlockTowardsFacing(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.furnaceItemStacks[0], var2);
            this.furnaceItemStacks[0] = null;
        }

        if (this.furnaceItemStacks[1] != null && !ItemStack.areItemStacksEqual(this.furnaceItemStacks[1], this.m_cookStack))
        {
            FCUtilsItem.EjectStackFromBlockTowardsFacing(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.furnaceItemStacks[1], var2);
            this.furnaceItemStacks[1] = null;
        }

        if (this.furnaceItemStacks[2] != null && !ItemStack.areItemStacksEqual(this.furnaceItemStacks[2], this.m_cookStack))
        {
            FCUtilsItem.EjectStackFromBlockTowardsFacing(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.furnaceItemStacks[2], var2);
            this.furnaceItemStacks[2] = null;
        }

        this.onInventoryChanged();
    }

    public void AddCookStack(ItemStack var1)
    {
        this.furnaceItemStacks[0] = var1;
        this.onInventoryChanged();
    }

    public int AttemptToAddFuel(ItemStack var1)
    {
        int var2 = this.m_iUnlitFuelBurnTime + this.furnaceBurnTime;
        int var3 = 14200 - var2;
        int var4 = 0;

        if (var3 > 0)
        {
            var4 = var3 / this.getItemBurnTime(var1);

            if (var4 == 0 && this.GetVisualFuelLevel() <= 2)
            {
                var4 = 1;
            }

            if (var4 > 0)
            {
                if (var4 > var1.stackSize)
                {
                    var4 = var1.stackSize;
                }

                this.m_iUnlitFuelBurnTime += this.getItemBurnTime(var1) * var4;
                this.onInventoryChanged();
            }
        }

        return var4;
    }

    private void UpdateVisualFuelLevel()
    {
        int var1 = this.m_iUnlitFuelBurnTime + this.furnaceBurnTime;
        int var2 = 0;

        if (var1 > 0)
        {
            if (var1 < 400)
            {
                var2 = 1;
            }
            else
            {
                var2 = var1 / 1600 + 2;
            }
        }

        this.SetVisualFuelLevel(var2);
    }

    public int GetVisualFuelLevel()
    {
        return this.m_iVisualFuelLevel;
    }

    public void SetVisualFuelLevel(int var1)
    {
        if (this.m_iVisualFuelLevel != var1)
        {
            this.m_iVisualFuelLevel = var1;
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
    }
}
