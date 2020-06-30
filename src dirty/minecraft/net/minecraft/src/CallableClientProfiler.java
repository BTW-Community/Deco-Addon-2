package net.minecraft.src;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class CallableClientProfiler implements Callable
{
    final Minecraft theMinecraft;

    public CallableClientProfiler(Minecraft par1Minecraft)
    {
        this.theMinecraft = par1Minecraft;
    }

    public String callClientProfilerInfo()
    {
        return this.theMinecraft.mcProfiler.profilingEnabled ? this.theMinecraft.mcProfiler.getNameOfLastSection() : "N/A (disabled)";
    }

    public Object call()
    {
        return this.callClientProfilerInfo();
    }
}
