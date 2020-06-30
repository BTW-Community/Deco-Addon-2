package net.minecraft.src;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class CallableUpdatingScreenName implements Callable
{
    final Minecraft theMinecraft;

    public CallableUpdatingScreenName(Minecraft par1Minecraft)
    {
        this.theMinecraft = par1Minecraft;
    }

    public String callUpdatingScreenName()
    {
        return this.theMinecraft.currentScreen.getClass().getCanonicalName();
    }

    public Object call()
    {
        return this.callUpdatingScreenName();
    }
}
