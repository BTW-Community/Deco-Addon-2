package net.minecraft.src;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpUtil
{
    /**
     * Builds an encoded HTTP POST content string from a string map
     */
    public static String buildPostString(Map par0Map)
    {
        StringBuilder var1 = new StringBuilder();
        Iterator var2 = par0Map.entrySet().iterator();

        while (var2.hasNext())
        {
            Entry var3 = (Entry)var2.next();

            if (var1.length() > 0)
            {
                var1.append('&');
            }

            try
            {
                var1.append(URLEncoder.encode((String)var3.getKey(), "UTF-8"));
            }
            catch (UnsupportedEncodingException var6)
            {
                var6.printStackTrace();
            }

            if (var3.getValue() != null)
            {
                var1.append('=');

                try
                {
                    var1.append(URLEncoder.encode(var3.getValue().toString(), "UTF-8"));
                }
                catch (UnsupportedEncodingException var5)
                {
                    var5.printStackTrace();
                }
            }
        }

        return var1.toString();
    }

    /**
     * Sends a HTTP POST request to the given URL with data from a map
     */
    public static String sendPost(ILogAgent par0ILogAgent, URL par1URL, Map par2Map, boolean par3)
    {
        return sendPost(par0ILogAgent, par1URL, buildPostString(par2Map), par3);
    }

    /**
     * Sends a HTTP POST request to the given URL with data from a string
     */
    public static String sendPost(ILogAgent par0ILogAgent, URL par1URL, String par2Str, boolean par3)
    {
        try
        {
            HttpURLConnection var4 = (HttpURLConnection)par1URL.openConnection();
            var4.setRequestMethod("POST");
            var4.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            var4.setRequestProperty("Content-Length", "" + par2Str.getBytes().length);
            var4.setRequestProperty("Content-Language", "en-US");
            var4.setUseCaches(false);
            var4.setDoInput(true);
            var4.setDoOutput(true);
            DataOutputStream var5 = new DataOutputStream(var4.getOutputStream());
            var5.writeBytes(par2Str);
            var5.flush();
            var5.close();
            BufferedReader var6 = new BufferedReader(new InputStreamReader(var4.getInputStream()));
            StringBuffer var8 = new StringBuffer();
            String var7;

            while ((var7 = var6.readLine()) != null)
            {
                var8.append(var7);
                var8.append('\r');
            }

            var6.close();
            return var8.toString();
        }
        catch (Exception var9)
        {
            if (!par3)
            {
                if (par0ILogAgent != null)
                {
                    par0ILogAgent.logSevereException("Could not post to " + par1URL, var9);
                }
                else
                {
                    Logger.getAnonymousLogger().log(Level.SEVERE, "Could not post to " + par1URL, var9);
                }
            }

            return "";
        }
    }

    /**
     * The downloader for texturepacks stored in the server.
     */
    public static void downloadTexturePack(File par0File, String par1Str, IDownloadSuccess par2IDownloadSuccess, Map par3Map, int par4, IProgressUpdate par5IProgressUpdate)
    {
        Thread var6 = new Thread(new HttpUtilRunnable(par5IProgressUpdate, par1Str, par3Map, par0File, par2IDownloadSuccess, par4));
        var6.setDaemon(true);
        var6.start();
    }

    public static int func_76181_a() throws IOException
    {
        ServerSocket var0 = null;
        boolean var1 = true;
        int var10;

        try
        {
            var0 = new ServerSocket(0);
            var10 = var0.getLocalPort();
        }
        finally
        {
            try
            {
                if (var0 != null)
                {
                    var0.close();
                }
            }
            catch (IOException var8)
            {
                ;
            }
        }

        return var10;
    }

    public static String[] loginToMinecraft(ILogAgent par0ILogAgent, String par1Str, String par2Str)
    {
        HashMap var3 = new HashMap();
        var3.put("user", par1Str);
        var3.put("password", par2Str);
        var3.put("version", Integer.valueOf(13));
        String var4;

        try
        {
            var4 = sendPost(par0ILogAgent, new URL("http://login.minecraft.net/"), var3, false);
        }
        catch (MalformedURLException var6)
        {
            var6.printStackTrace();
            return null;
        }

        if (var4 != null && var4.length() != 0)
        {
            if (!var4.contains(":"))
            {
                if (par0ILogAgent == null)
                {
                    System.out.println("Failed to authenticate: " + var4);
                }
                else
                {
                    par0ILogAgent.logWarning("Failed to authenticae: " + var4);
                }

                return null;
            }
            else
            {
                String[] var5 = var4.split(":");
                return new String[] {var5[2], var5[3]};
            }
        }
        else
        {
            if (par0ILogAgent == null)
            {
                System.out.println("Failed to authenticate: Can\'t connect to minecraft.net");
            }
            else
            {
                par0ILogAgent.logWarning("Failed to authenticate: Can\'t connect to minecraft.net");
            }

            return null;
        }
    }

    public static String func_104145_a(URL par0URL) throws IOException
    {
        HttpURLConnection var1 = (HttpURLConnection)par0URL.openConnection();
        var1.setRequestMethod("GET");
        BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getInputStream()));
        StringBuilder var4 = new StringBuilder();
        String var3;

        while ((var3 = var2.readLine()) != null)
        {
            var4.append(var3);
            var4.append('\r');
        }

        var2.close();
        return var4.toString();
    }
}
