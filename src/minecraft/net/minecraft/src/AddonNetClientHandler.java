package net.minecraft.src;

import java.io.IOException;
import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;

public class AddonNetClientHandler extends NetClientHandler {
    /** Reference to the Minecraft object. */
    private Minecraft mc;
    private WorldClient worldClient;
    
	public AddonNetClientHandler(Minecraft par1Minecraft, IntegratedServer par2IntegratedServer) throws IOException {
		super(par1Minecraft, par2IntegratedServer);
        this.mc = par1Minecraft;
        System.out.println("AddonNetClientHandler made");
	}

	public AddonNetClientHandler(Minecraft par1Minecraft, String par2Str, int par3, GuiScreen par4GuiScreen) throws IOException {
		super(par1Minecraft, par2Str, par3, par4GuiScreen);
        this.mc = par1Minecraft;
        System.out.println("AddonNetClientHandler made");
	}

	public AddonNetClientHandler(Minecraft par1Minecraft, String par2Str, int par3) throws IOException {
		super(par1Minecraft, par2Str, par3);
        this.mc = par1Minecraft;
        System.out.println("AddonNetClientHandler made");
	}

    /**
     * sets netManager and worldClient to null
     */
    public void cleanup()
    {
    	super.cleanup();
        this.worldClient = null;
    }

    public void handleLogin(Packet1Login par1Packet1Login)
    {
    	super.handleLogin(par1Packet1Login);
        this.mc.playerController = new AddonPlayerControllerMP(this.mc, this);
        System.out.println("AddonController Assigned");
    }

    /**
     * respawns the player
     */
    public void handleRespawn(Packet9Respawn par1Packet9Respawn)
    {
    	super.handleRespawn(par1Packet9Respawn);
    	
    	Field worldClientAccessField = null;
		try {
			worldClientAccessField = this.getClass().getSuperclass().getDeclaredField("worldClient");
		} catch (NoSuchFieldException e) {
			try {
				worldClientAccessField = this.getClass().getSuperclass().getDeclaredField("i");
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
    	worldClientAccessField.setAccessible(true);
    	
    	try {
			this.worldClient = (WorldClient) worldClientAccessField.get(this);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
