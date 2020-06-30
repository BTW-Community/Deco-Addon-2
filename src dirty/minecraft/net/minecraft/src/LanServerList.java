package net.minecraft.src;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LanServerList
{
    private ArrayList listOfLanServers = new ArrayList();
    boolean wasUpdated;

    public synchronized boolean getWasUpdated()
    {
        return this.wasUpdated;
    }

    public synchronized void setWasNotUpdated()
    {
        this.wasUpdated = false;
    }

    public synchronized List getLanServers()
    {
        return Collections.unmodifiableList(this.listOfLanServers);
    }

    public synchronized void func_77551_a(String par1Str, InetAddress par2InetAddress)
    {
        String var3 = ThreadLanServerPing.getMotdFromPingResponse(par1Str);
        String var4 = ThreadLanServerPing.getAdFromPingResponse(par1Str);

        if (var4 != null)
        {
            int var5 = var4.indexOf(58);

            if (var5 > 0)
            {
                var4 = par2InetAddress.getHostAddress() + var4.substring(var5);
            }

            boolean var6 = false;
            Iterator var7 = this.listOfLanServers.iterator();

            while (var7.hasNext())
            {
                LanServer var8 = (LanServer)var7.next();

                if (var8.getServerIpPort().equals(var4))
                {
                    var8.updateLastSeen();
                    var6 = true;
                    break;
                }
            }

            if (!var6)
            {
                this.listOfLanServers.add(new LanServer(var3, var4));
                this.wasUpdated = true;
            }
        }
    }
}
