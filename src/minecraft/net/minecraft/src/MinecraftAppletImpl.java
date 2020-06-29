package net.minecraft.src;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.io.IOException;
import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftApplet;
import org.lwjgl.LWJGLException;

public class MinecraftAppletImpl extends Minecraft
{
    /** Reference to the main frame, in this case, the applet window itself. */
    final MinecraftApplet mainFrame;

    public MinecraftAppletImpl(MinecraftApplet par1MinecraftApplet, Canvas par2Canvas, MinecraftApplet par3MinecraftApplet, int par4, int par5, boolean par6)
    {
        super(par2Canvas, par3MinecraftApplet, par4, par5, par6);
        this.mainFrame = par1MinecraftApplet;
    }

    public void displayCrashReportInternal(CrashReport par1CrashReport)
    {
        this.mainFrame.removeAll();
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.add(new PanelCrashReport(par1CrashReport), "Center");
        this.mainFrame.validate();
    }

    /**
     * Starts the game: initializes the canvas, the title, the settings, etcetera.
     */
    public void startGame() throws LWJGLException
    {
        this.mcDataDir = getMinecraftDir();
        this.gameSettings = new GameSettings(this, this.mcDataDir);

        if (this.gameSettings.overrideHeight > 0 && this.gameSettings.overrideWidth > 0 && this.mainFrame.getParent() != null && this.mainFrame.getParent().getParent() != null)
        {
            this.mainFrame.getParent().getParent().setSize(this.gameSettings.overrideWidth, this.gameSettings.overrideHeight);
        }

        super.startGame();

        this.renderGlobal = new AddonRenderGlobal(this, this.renderEngine);
    }

    /**
     * Arguments: World foldername,  World ingame name, WorldSettings
     */
    public void launchIntegratedServer(String par1Str, String par2Str, WorldSettings par3WorldSettings)
    {
    	super.launchIntegratedServer(par1Str, par2Str, par3WorldSettings);

        try
        {
            NetClientHandler var10 = new AddonNetClientHandler(this, this.getIntegratedServer());
            
        	Field networkManagerAccessField = null;
    		try {
    			networkManagerAccessField = this.getClass().getSuperclass().getDeclaredField("myNetworkManager");
    		} catch (NoSuchFieldException e) {
    			try {
    				networkManagerAccessField = this.getClass().getSuperclass().getDeclaredField("ak");
    			} catch (NoSuchFieldException e1) {
    				e1.printStackTrace();
    			} catch (SecurityException e1) {
    				e1.printStackTrace();
    			}
    		} catch (SecurityException e) {
    			e.printStackTrace();
    		}
    		
    		networkManagerAccessField.setAccessible(true);
        	
        	try {
    			networkManagerAccessField.set(this, var10.getNetManager());
    		} catch (IllegalArgumentException e) {
    			e.printStackTrace();
    		} catch (IllegalAccessException e) {
    			e.printStackTrace();
    		}
        }
        catch (IOException var8)
        {
            this.displayCrashReport(this.addGraphicsAndWorldToCrashReport(new CrashReport("Connecting to integrated server", var8)));
        }
    }
}
