package net.minecraft.src;

public class FCTileEntityAnvil extends TileEntity
{
    private int m_iMouldContentsBitField = 0;

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setInteger("m_iMouldContentsBitField", this.m_iMouldContentsBitField);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);

        if (var1.hasKey("m_iMouldContentsBitField"))
        {
            this.m_iMouldContentsBitField = var1.getInteger("m_iMouldContentsBitField");
        }
        else
        {
            this.m_iMouldContentsBitField = 0;
        }
    }

    public void ClearMouldContents()
    {
        this.m_iMouldContentsBitField = 0;
    }

    public boolean DoesSlotContainMould(int var1, int var2)
    {
        int var3 = var1 + var2 * 4;
        return this.DoesSlotContainMould(var3);
    }

    public boolean DoesSlotContainMould(int var1)
    {
        int var2 = 1 << var1;
        return (this.m_iMouldContentsBitField & var2) > 0;
    }

    public void SetSlotContainsMould(int var1)
    {
        int var2 = 1 << var1;
        this.m_iMouldContentsBitField |= var2;
    }

    public void SetSlotContainsMould(int var1, int var2)
    {
        int var3 = 1 << var1 + var2 * 4;
        this.m_iMouldContentsBitField |= var3;
    }

    public void EjectMoulds()
    {
        boolean var1 = false;

        for (int var2 = 0; var2 < 16; ++var2)
        {
            if (this.DoesSlotContainMould(var2))
            {
                FCUtilsItem.EjectSingleItemWithRandomOffset(this.worldObj, this.xCoord, this.yCoord, this.zCoord, FCBetterThanWolves.fcItemMould.itemID, 0);
            }
        }

        this.ClearMouldContents();
    }
}
