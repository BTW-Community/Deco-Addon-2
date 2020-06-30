package net.minecraft.src;

import java.applet.Applet;
import java.applet.AppletStub;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class MinecraftFakeLauncher extends Applet implements AppletStub
{
    /** Arguments that were passed to Minecraft.jar (username, sessionid etc) */
    final Map arguments;

    public MinecraftFakeLauncher(Map par1Map)
    {
        this.arguments = par1Map;
    }

    public void appletResize(int par1, int par2) {}

    public boolean isActive()
    {
        return true;
    }

    public URL getDocumentBase()
    {
        try
        {
            return new URL("http://www.minecraft.net/game/");
        }
        catch (MalformedURLException var2)
        {
            var2.printStackTrace();
            return null;
        }
    }

    public String getParameter(String par1Str)
    {
        if (this.arguments.containsKey(par1Str))
        {
            return (String)this.arguments.get(par1Str);
        }
        else
        {
            System.err.println("Client asked for parameter: " + par1Str);
            return null;
        }
    }
}
