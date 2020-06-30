package net.minecraft.src;

public class FCTileEntityCampfire extends TileEntity implements FCITileEntityDataPacketHandler
{
    private final int m_iCampfireBurnTimeMultiplier = 8;
    private final int m_iTimeToCook = 4800;
    private final int m_iMaxBurnTime = 6000;
    private final int m_iInitialBurnTime = 3200;
    private final int m_iWarmupTime = 200;
    private final int m_iRevertToSmallTime = 400;
    private final int m_iBlazeTime = 4800;
    private final int m_iSmoulderTime = 6000;
    private final int m_iTimeToBurnFood = 2400;
    private final float m_fChanceOfFireSpread = 0.05F;
    private final float m_fChanceOfGoingOutFromRain = 0.01F;
    private ItemStack m_spitStack = null;
    private ItemStack m_cookStack = null;
    private int m_iBurnTimeCountdown = 0;
    private int m_iBurnTimeSinceLit = 0;
    private int m_iCookCounter = 0;
    private int m_iSmoulderCounter = 0;
    private int m_iCookBurningCounter = 0;

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagCompound var2 = var1.getCompoundTag("fcSpitStack");

        if (var2 != null)
        {
            this.m_spitStack = ItemStack.loadItemStackFromNBT(var2);
        }

        NBTTagCompound var3 = var1.getCompoundTag("fcCookStack");

        if (var3 != null)
        {
            this.m_cookStack = ItemStack.loadItemStackFromNBT(var3);
        }

        if (var1.hasKey("fcBurnCounter"))
        {
            this.m_iBurnTimeCountdown = var1.getInteger("fcBurnCounter");
        }

        if (var1.hasKey("fcBurnTime"))
        {
            this.m_iBurnTimeSinceLit = var1.getInteger("fcBurnTime");
        }

        if (var1.hasKey("fcCookCounter"))
        {
            this.m_iCookCounter = var1.getInteger("fcCookCounter");
        }

        if (var1.hasKey("fcSmoulderCounter"))
        {
            this.m_iSmoulderCounter = var1.getInteger("fcSmoulderCounter");
        }

        if (var1.hasKey("fcCookBurning"))
        {
            this.m_iCookBurningCounter = var1.getInteger("fcCookBurning");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        NBTTagCompound var2;

        if (this.m_spitStack != null)
        {
            var2 = new NBTTagCompound();
            this.m_spitStack.writeToNBT(var2);
            var1.setCompoundTag("fcSpitStack", var2);
        }

        if (this.m_cookStack != null)
        {
            var2 = new NBTTagCompound();
            this.m_cookStack.writeToNBT(var2);
            var1.setCompoundTag("fcCookStack", var2);
        }

        var1.setInteger("fcBurnCounter", this.m_iBurnTimeCountdown);
        var1.setInteger("fcBurnTime", this.m_iBurnTimeSinceLit);
        var1.setInteger("fcCookCounter", this.m_iCookCounter);
        var1.setInteger("fcSmoulderCounter", this.m_iSmoulderCounter);
        var1.setInteger("fcCookBurning", this.m_iCookBurningCounter);
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
            int var1 = this.GetCurrentFireLevel();

            if (var1 > 0)
            {
                if (var1 > 1 && this.worldObj.rand.nextFloat() <= 0.05F)
                {
                    FCBlockFire.CheckForFireSpreadFromLocation(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.worldObj.rand, 0);
                }

                ++this.m_iBurnTimeSinceLit;

                if (this.m_iBurnTimeCountdown > 0)
                {
                    --this.m_iBurnTimeCountdown;

                    if (var1 == 3)
                    {
                        --this.m_iBurnTimeCountdown;
                    }
                }

                var1 = this.ValidateFireLevel();

                if (var1 > 0)
                {
                    this.UpdateCookState();

                    if (this.worldObj.rand.nextFloat() <= 0.01F && this.IsRainingOnCampfire())
                    {
                        this.ExtinguishFire(false);
                    }
                }
            }
            else if (this.m_iSmoulderCounter > 0)
            {
                --this.m_iSmoulderCounter;

                if (this.m_iSmoulderCounter == 0 || this.worldObj.rand.nextFloat() <= 0.01F && this.IsRainingOnCampfire())
                {
                    this.StopSmouldering();
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
        NBTTagCompound var2;

        if (this.m_cookStack != null)
        {
            var2 = new NBTTagCompound();
            this.m_cookStack.writeToNBT(var2);
            var1.setCompoundTag("x", var2);
        }

        if (this.m_spitStack != null)
        {
            var2 = new NBTTagCompound();
            this.m_spitStack.writeToNBT(var2);
            var1.setCompoundTag("y", var2);
        }

        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }

    public void readNBTFromPacket(NBTTagCompound var1)
    {
        NBTTagCompound var2 = var1.getCompoundTag("x");

        if (var2 != null)
        {
            this.m_cookStack = ItemStack.loadItemStackFromNBT(var2);
        }

        NBTTagCompound var3 = var1.getCompoundTag("y");

        if (var3 != null)
        {
            this.m_spitStack = ItemStack.loadItemStackFromNBT(var3);
        }

        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }

    public void SetSpitStack(ItemStack var1)
    {
        if (var1 != null)
        {
            this.m_spitStack = var1.copy();
            this.m_spitStack.stackSize = 1;
        }
        else
        {
            this.m_spitStack = null;
        }

        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public ItemStack GetSpitStack()
    {
        return this.m_spitStack;
    }

    public void SetCookStack(ItemStack var1)
    {
        if (var1 != null)
        {
            this.m_cookStack = var1.copy();
            this.m_cookStack.stackSize = 1;
        }
        else
        {
            this.m_cookStack = null;
            this.m_iCookBurningCounter = 0;
        }

        this.m_iCookCounter = 0;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public ItemStack GetCookStack()
    {
        return this.m_cookStack;
    }

    public void EjectContents()
    {
        if (this.m_spitStack != null)
        {
            FCUtilsItem.EjectStackWithRandomOffset(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.m_spitStack);
            this.m_spitStack = null;
        }

        if (this.m_cookStack != null)
        {
            FCUtilsItem.EjectStackWithRandomOffset(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.m_cookStack);
            this.m_cookStack = null;
        }
    }

    public void AddBurnTime(int var1)
    {
        this.m_iBurnTimeCountdown += var1 * 8 * 2;

        if (this.m_iBurnTimeCountdown > 6000)
        {
            this.m_iBurnTimeCountdown = 6000;
        }

        this.ValidateFireLevel();
    }

    public void OnFirstLit()
    {
        this.m_iBurnTimeCountdown = 3200;
        this.m_iBurnTimeSinceLit = 0;
    }

    public int ValidateFireLevel()
    {
        int var1 = this.GetCurrentFireLevel();

        if (var1 > 0)
        {
            if (this.m_iBurnTimeCountdown <= 0)
            {
                this.ExtinguishFire(true);
                return 0;
            }

            byte var2 = 2;

            if (this.m_iBurnTimeSinceLit >= 200 && this.m_iBurnTimeCountdown >= 400)
            {
                if (this.m_iBurnTimeCountdown > 4800)
                {
                    var2 = 3;
                }
            }
            else
            {
                var2 = 1;
            }

            if (var2 != var1)
            {
                this.ChangeFireLevel(var2);

                if (var2 == 1 && var1 == 2)
                {
                    this.worldObj.playAuxSFX(2227, this.xCoord, this.yCoord, this.zCoord, 1);
                }

                return var2;
            }
        }
        else if (this.m_iBurnTimeCountdown > 0 && FCBetterThanWolves.fcBlockCampfireUnlit.GetFuelState(this.worldObj, this.xCoord, this.yCoord, this.zCoord) == 2)
        {
            this.RelightSmouldering();
            return 1;
        }

        return var1;
    }

    private void ExtinguishFire(boolean var1)
    {
        if (var1)
        {
            this.m_iSmoulderCounter = 6000;
        }
        else
        {
            this.m_iSmoulderCounter = 0;
        }

        this.m_iCookCounter = 0;
        this.m_iCookBurningCounter = 0;
        FCBlockCampfire var2 = (FCBlockCampfire)((FCBlockCampfire)Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)]);
        var2.ExtinguishFire(this.worldObj, this.xCoord, this.yCoord, this.zCoord, var1);
    }

    private void ChangeFireLevel(int var1)
    {
        FCBlockCampfire var2 = (FCBlockCampfire)((FCBlockCampfire)Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)]);
        var2.ChangeFireLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord, var1, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
    }

    private int GetCurrentFireLevel()
    {
        FCBlockCampfire var1 = (FCBlockCampfire)((FCBlockCampfire)Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)]);
        return var1.m_iFireLevel;
    }

    private void UpdateCookState()
    {
        if (this.m_cookStack != null)
        {
            int var1 = this.GetCurrentFireLevel();

            if (var1 >= 2)
            {
                ItemStack var2 = FCCraftingManagerCampfire.instance.GetRecipeResult(this.m_cookStack.getItem().itemID);

                if (var2 != null)
                {
                    ++this.m_iCookCounter;

                    if (this.m_iCookCounter >= 4800)
                    {
                        this.SetCookStack(var2);
                        this.m_iCookCounter = 0;
                    }
                }

                if (var1 >= 3 && this.m_cookStack.itemID != FCBetterThanWolves.fcItemMeatBurned.itemID)
                {
                    ++this.m_iCookBurningCounter;

                    if (this.m_iCookBurningCounter >= 2400)
                    {
                        this.SetCookStack(new ItemStack(FCBetterThanWolves.fcItemMeatBurned));
                        this.m_iCookCounter = 0;
                        this.m_iCookBurningCounter = 0;
                    }
                }
            }
        }
    }

    public boolean GetIsCooking()
    {
        if (this.m_cookStack != null && this.GetCurrentFireLevel() >= 2)
        {
            ItemStack var1 = FCCraftingManagerCampfire.instance.GetRecipeResult(this.m_cookStack.getItem().itemID);

            if (var1 != null)
            {
                return true;
            }
        }

        return false;
    }

    public boolean GetIsFoodBurning()
    {
        return this.m_cookStack != null && this.GetCurrentFireLevel() >= 3 && this.m_cookStack.itemID != FCBetterThanWolves.fcItemMeatBurned.itemID;
    }

    public boolean IsRainingOnCampfire()
    {
        FCBlockCampfire var1 = (FCBlockCampfire)((FCBlockCampfire)Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)]);
        return var1.IsRainingOnCampfire(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    private void StopSmouldering()
    {
        this.m_iSmoulderCounter = 0;
        FCBlockCampfire var1 = (FCBlockCampfire)((FCBlockCampfire)Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)]);
        var1.StopSmouldering(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    private void RelightSmouldering()
    {
        this.m_iBurnTimeSinceLit = 0;
        FCBlockCampfire var1 = (FCBlockCampfire)((FCBlockCampfire)Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)]);
        var1.RelightFire(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }
}
