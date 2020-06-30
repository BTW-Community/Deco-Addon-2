package net.minecraft.src;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class CallableType2 implements Callable
{
    final Minecraft mc;

    public CallableType2(Minecraft par1Minecraft)
    {
        this.mc = par1Minecraft;
    }

    public String func_82886_a()
    {
        return "Client (map_client.txt)";
    }

    public Object call()
    {
        return this.func_82886_a();
    }
}
