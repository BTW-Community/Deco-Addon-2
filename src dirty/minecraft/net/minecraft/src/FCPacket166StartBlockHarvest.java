package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class FCPacket166StartBlockHarvest extends Packet
{
    public int iIPos;
    public int iJPos;
    public int iKPos;
    public int iFace;
    private int iMiningSpeedModifier;

    public FCPacket166StartBlockHarvest() {}

    public FCPacket166StartBlockHarvest(int var1, int var2, int var3, int var4, float var5)
    {
        this.iIPos = var1;
        this.iJPos = var2;
        this.iKPos = var3;
        this.iFace = var4;
        this.iMiningSpeedModifier = (int)(var5 * 8000.0F);
    }

    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream var1) throws IOException
    {
        this.iIPos = var1.readInt();
        this.iJPos = var1.read();
        this.iKPos = var1.readInt();
        this.iFace = var1.read();
        this.iMiningSpeedModifier = var1.readShort();
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream var1) throws IOException
    {
        var1.writeInt(this.iIPos);
        var1.write(this.iJPos);
        var1.writeInt(this.iKPos);
        var1.write(this.iFace);
        var1.writeShort(this.iMiningSpeedModifier);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(NetHandler var1)
    {
        var1.HandleStartBlockHarvest(this);
    }

    /**
     * Abstract. Return the size of the packet (not counting the header).
     */
    public int getPacketSize()
    {
        return 12;
    }

    public float GetMiningSpeedModifier()
    {
        return (float)this.iMiningSpeedModifier / 8000.0F;
    }
}
