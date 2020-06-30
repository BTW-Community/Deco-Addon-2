package net.minecraft.src;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class CallableTickingScreenName implements Callable
{
    /** Reference to the Minecraft object. */
    final Minecraft mc;

    public CallableTickingScreenName(Minecraft par1Minecraft)
    {
        this.mc = par1Minecraft;
    }

    public String getLWJGLVersion()
    {
        return this.mc.currentScreen.getClass().getCanonicalName();
    }

    public Object call()
    {
        return this.getLWJGLVersion();
    }
}
