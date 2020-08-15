package net.minecraft.src;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;

public class DawnNetClientHandler extends NetClientHandler {
    /** Reference to the Minecraft object. */
    private Minecraft mc;
    private WorldClient worldClient;
    
	public DawnNetClientHandler(Minecraft par1Minecraft, IntegratedServer par2IntegratedServer) throws IOException {
		super(par1Minecraft, par2IntegratedServer);
        this.mc = par1Minecraft;
	}

	public DawnNetClientHandler(Minecraft par1Minecraft, String par2Str, int par3, GuiScreen par4GuiScreen) throws IOException {
		super(par1Minecraft, par2Str, par3, par4GuiScreen);
        this.mc = par1Minecraft;
	}

	public DawnNetClientHandler(Minecraft par1Minecraft, String par2Str, int par3) throws IOException {
		super(par1Minecraft, par2Str, par3);
        this.mc = par1Minecraft;
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
        this.mc.playerController = new AddonPlayerControllerMP(this.mc, this);
        this.mc.statFileWriter.readStat(StatList.joinMultiplayerStat, 1);
        this.worldClient = new WorldClient(this, new WorldSettings(0L, par1Packet1Login.gameType, false, par1Packet1Login.hardcoreMode, par1Packet1Login.terrainType), par1Packet1Login.dimension, par1Packet1Login.difficultySetting, this.mc.mcProfiler, this.mc.getLogAgent());
        this.worldClient.isRemote = true;
        this.mc.loadWorld(this.worldClient);
        this.mc.thePlayer.dimension = par1Packet1Login.dimension;
        this.mc.displayGuiScreen(new GuiDownloadTerrain(this));
        this.mc.thePlayer.entityId = par1Packet1Login.clientEntityId;
        this.currentServerMaxPlayers = par1Packet1Login.maxPlayers;
        this.mc.playerController.setGameType(par1Packet1Login.gameType);
        this.mc.gameSettings.sendSettingsToServer();
    	
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
			worldClientAccessField.set(this, this.worldClient);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
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

    public void handleCustomPayload(Packet250CustomPayload par1Packet250CustomPayload)
    {
        if ("MC|TPack".equals(par1Packet250CustomPayload.channel))
        {
            String[] var2 = (new String(par1Packet250CustomPayload.data)).split("\u0000");
            String var3 = var2[0];

            if (var2[1].equals("16"))
            {
                if (this.mc.texturePackList.getAcceptsTextures())
                {
                    this.mc.texturePackList.requestDownloadOfTexture(var3);
                }
                else if (this.mc.texturePackList.func_77300_f())
                {
                    this.mc.displayGuiScreen(new GuiYesNo(new NetClientWebTextures(this, var3), StringTranslate.getInstance().translateKey("multiplayer.texturePrompt.line1"), StringTranslate.getInstance().translateKey("multiplayer.texturePrompt.line2"), 0));
                }
            }
        }
        else if ("MC|TrList".equals(par1Packet250CustomPayload.channel))
        {
            DataInputStream var8 = new DataInputStream(new ByteArrayInputStream(par1Packet250CustomPayload.data));

            try
            {
                int var9 = var8.readInt();
                GuiScreen var4 = this.mc.currentScreen;

                if (var4 != null && var4 instanceof GuiMerchant && var9 == this.mc.thePlayer.openContainer.windowId)
                {
                    IMerchant var5 = ((GuiMerchant)var4).getIMerchant();
                    MerchantRecipeList var6 = MerchantRecipeList.readRecipiesFromStream(var8);
                    var5.setRecipes(var6);
                }
            }
            catch (IOException var7)
            {
                var7.printStackTrace();
            }
        }
        else if (!DawnAddonHandler.interceptCustomClientPacket(this.mc, par1Packet250CustomPayload))
        {
            FCAddOnHandler.ClientCustomPacketReceived(this.mc, par1Packet250CustomPayload);
        }
    }
}
