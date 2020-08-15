package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

public class DawnNetServerHandler extends NetServerHandler {
	private MinecraftServer mcServer;
	
	public DawnNetServerHandler(MinecraftServer par1, INetworkManager par2, EntityPlayerMP par3) {
		super(par1, par2, par3);
		mcServer = par1;
	}

    public void handleCustomPayload(Packet250CustomPayload par1Packet250CustomPayload)
    {
    	if (!DawnAddonHandler.serverCustomPacketReceived(this.mcServer, par1Packet250CustomPayload)) {
        	super.handleCustomPayload(par1Packet250CustomPayload);
        }
    }
}
