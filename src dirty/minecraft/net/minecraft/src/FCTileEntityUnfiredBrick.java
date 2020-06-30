package net.minecraft.src;

public class FCTileEntityUnfiredBrick extends TileEntity implements FCITileEntityDataPacketHandler
{
    private final int m_iTimeToCook = 12000;
    private final int m_iRainCookDecay = 10;
    private int m_iCookCounter = 0;
    private boolean m_bIsCooking = false;

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);

        if (var1.hasKey("fcCookCounter"))
        {
            this.m_iCookCounter = var1.getInteger("fcCookCounter");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setInteger("fcCookCounter", this.m_iCookCounter);
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
            this.UpdateCooking();
        }
        else if (this.m_bIsCooking && this.worldObj.rand.nextInt(20) == 0)
        {
            double var1 = (double)((float)this.xCoord + 0.25F + this.worldObj.rand.nextFloat() * 0.5F);
            double var3 = (double)((float)this.yCoord + 0.5F + this.worldObj.rand.nextFloat() * 0.25F);
            double var5 = (double)((float)this.zCoord + 0.25F + this.worldObj.rand.nextFloat() * 0.5F);
            this.worldObj.spawnParticle("fcwhitesmoke", var1, var3, var5, 0.0D, 0.0D, 0.0D);
        }
    }

    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        var1.setBoolean("x", this.m_bIsCooking);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }

    public void readNBTFromPacket(NBTTagCompound var1)
    {
        this.m_bIsCooking = var1.getBoolean("x");
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }

    public void UpdateCooking()
    {
        int var2 = this.worldObj.GetBlockNaturalLightValueMaximum(this.xCoord, this.yCoord, this.zCoord);
        int var3 = var2 - this.worldObj.skylightSubtracted;
        boolean var1 = var3 >= 15;
        int var4 = this.worldObj.getBlockId(this.xCoord, this.yCoord + 1, this.zCoord);
        Block var5 = Block.blocksList[var4];

        if (var5 != null && var5.IsGroundCover())
        {
            var1 = false;
        }

        if (var1 != this.m_bIsCooking)
        {
            this.m_bIsCooking = var1;
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }

        FCBlockUnfiredBrick var6 = FCBetterThanWolves.fcBlockUnfiredBrick;

        if (this.m_bIsCooking)
        {
            ++this.m_iCookCounter;

            if (this.m_iCookCounter >= 12000)
            {
                var6.OnFinishedCooking(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                return;
            }
        }
        else if (this.IsRainingOnBrick(this.worldObj, this.xCoord, this.yCoord, this.zCoord))
        {
            this.m_iCookCounter -= 10;

            if (this.m_iCookCounter < 0)
            {
                this.m_iCookCounter = 0;
            }
        }

        int var7 = var6.GetCookLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        int var8 = this.ComputeCookLevel();

        if (var7 != var8)
        {
            var6.SetCookLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord, var8);
        }
    }

    public boolean IsRainingOnBrick(World var1, int var2, int var3, int var4)
    {
        return var1.isRaining() && var1.IsRainingAtPos(var2, var3, var4);
    }

    private int ComputeCookLevel()
    {
        if (this.m_iCookCounter > 0)
        {
            int var1 = (int)((float)this.m_iCookCounter / 12000.0F * 7.0F) + 1;
            return MathHelper.clamp_int(var1, 0, 7);
        }
        else
        {
            return 0;
        }
    }
}
