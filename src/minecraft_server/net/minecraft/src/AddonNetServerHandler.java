package net.minecraft.src;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.server.MinecraftServer;

public class AddonNetServerHandler extends NetServerHandler {
	private MinecraftServer mcServer;
	
	public AddonNetServerHandler(MinecraftServer par1, INetworkManager par2, EntityPlayerMP par3) {
		super(par1, par2, par3);
		mcServer = par1;
	}

    public void handleCustomPayload(Packet250CustomPayload par1Packet250CustomPayload)
    {
    	if (!AddonManager.ServerCustomPacketReceived(this.mcServer, par1Packet250CustomPayload, this)) {
        	super.handleCustomPayload(par1Packet250CustomPayload);
        }
    }
}