package net.minecraft.src;

public class FCTileEntityCement extends TileEntity implements FCITileEntityDataPacketHandler
{
    private int m_iDryTime = 0;
    private int m_iSpreadDist = 0;

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setInteger("dryTime", this.m_iDryTime);
        var1.setInteger("spreadDist", this.m_iSpreadDist);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);

        if (var1.hasKey("dryTime"))
        {
            this.m_iDryTime = var1.getInteger("dryTime");
        }
        else
        {
            this.m_iDryTime = 12;
        }

        if (var1.hasKey("spreadDist"))
        {
            this.m_iSpreadDist = var1.getInteger("spreadDist");
        }
        else
        {
            this.m_iSpreadDist = 16;
        }
    }

    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        var1.setShort("d", (short)this.m_iDryTime);
        var1.setShort("s", (short)this.m_iSpreadDist);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }

    public void readNBTFromPacket(NBTTagCompound var1)
    {
        this.m_iDryTime = var1.getShort("d");
        this.m_iSpreadDist = var1.getShort("s");
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }

    public int GetDryTime()
    {
        return this.m_iDryTime;
    }

    public void SetDryTime(int var1)
    {
        this.m_iDryTime = var1;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public int GetSpreadDist()
    {
        return this.m_iSpreadDist;
    }

    public void SetSpreadDist(int var1)
    {
        this.m_iSpreadDist = var1;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }
}
