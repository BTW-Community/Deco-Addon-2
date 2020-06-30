package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiScreenOnlineServers extends GuiScreen
{
    private GuiScreen field_96188_a;
    private GuiSlotOnlineServerList field_96186_b;
    private static int field_96187_c = 0;
    private static final Object field_96185_d = new Object();
    private int field_96189_n = -1;
    private GuiButton field_96190_o;
    private GuiButton field_96198_p;
    private GuiButtonLink field_96197_q;
    private GuiButton field_96196_r;
    private String field_96195_s = null;
    private McoServerList field_96194_t;
    private boolean field_96193_u;
    private List field_96192_v = Collections.emptyList();
    private volatile int field_96199_x;
    private Long field_102019_y;
    private int field_104044_y = 0;

    public GuiScreenOnlineServers(GuiScreen par1)
    {
        this.field_96188_a = par1;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.field_96194_t = new McoServerList(this.mc.session);

        if (!this.field_96193_u)
        {
            this.field_96193_u = true;
            this.field_96186_b = new GuiSlotOnlineServerList(this);
        }
        else
        {
            this.field_96186_b.func_104084_a(this.width, this.height, 32, this.height - 64);
        }

        (new ThreadOnlineScreen(this)).start();
        this.func_96178_g();
    }

    public void func_96178_g()
    {
        StringTranslate var1 = StringTranslate.getInstance();
        this.buttonList.add(this.field_96196_r = new GuiButton(1, this.width / 2 - 154, this.height - 52, 100, 20, var1.translateKey("mco.selectServer.select")));
        this.buttonList.add(this.field_96198_p = new GuiButton(2, this.width / 2 - 48, this.height - 52, 100, 20, var1.translateKey("mco.selectServer.create")));
        this.buttonList.add(this.field_96190_o = new GuiButton(3, this.width / 2 + 58, this.height - 52, 100, 20, var1.translateKey("mco.selectServer.configure")));
        this.buttonList.add(this.field_96197_q = new GuiButtonLink(4, this.width / 2 - 154, this.height - 28, 154, 20, var1.translateKey("mco.selectServer.moreinfo")));
        this.buttonList.add(new GuiButton(0, this.width / 2 + 6, this.height - 28, 153, 20, var1.translateKey("gui.cancel")));
        boolean var2 = this.field_96189_n >= 0 && this.field_96189_n < this.field_96186_b.getSize();
        this.field_96196_r.enabled = var2 && ((McoServer)this.field_96192_v.get(this.field_96189_n)).field_96404_d.equals("OPEN") && !((McoServer)this.field_96192_v.get(this.field_96189_n)).field_98166_h;
        this.field_96190_o.enabled = var2 && this.mc.session.username.equals(((McoServer)this.field_96192_v.get(this.field_96189_n)).field_96405_e);
        this.field_96198_p.enabled = this.field_96199_x > 0;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
        ++this.field_104044_y;

        if (this.field_96194_t.func_98251_a())
        {
            List var1 = this.field_96194_t.func_98252_c();
            Iterator var2 = var1.iterator();

            while (var2.hasNext())
            {
                McoServer var3 = (McoServer)var2.next();
                Iterator var4 = this.field_96192_v.iterator();

                while (var4.hasNext())
                {
                    McoServer var5 = (McoServer)var4.next();

                    if (var3.field_96408_a == var5.field_96408_a)
                    {
                        var3.func_96401_a(var5);

                        if (this.field_102019_y != null && this.field_102019_y.longValue() == var3.field_96408_a)
                        {
                            this.field_102019_y = null;
                            var3.field_96411_l = false;
                        }

                        break;
                    }
                }
            }

            this.field_96192_v = var1;
            this.field_96194_t.func_98250_b();
        }

        this.field_96198_p.enabled = this.field_96199_x > 0;
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == 1)
            {
                this.func_96159_a(this.field_96189_n);
            }
            else if (par1GuiButton.id == 3)
            {
                List var2 = this.field_96194_t.func_98252_c();

                if (this.field_96189_n < var2.size())
                {
                    McoServer var3 = (McoServer)var2.get(this.field_96189_n);
                    McoServer var4 = this.func_98086_a(var3.field_96408_a);

                    if (var4 != null)
                    {
                        this.field_96194_t.func_98248_d();
                        this.mc.displayGuiScreen(new GuiScreenConfigureWorld(this, var4));
                    }
                }
            }
            else if (par1GuiButton.id == 0)
            {
                this.field_96194_t.func_98248_d();
                this.mc.displayGuiScreen(this.field_96188_a);
            }
            else if (par1GuiButton.id == 2)
            {
                this.field_96194_t.func_98248_d();
                this.mc.displayGuiScreen(new GuiScreenCreateOnlineWorld(this));
            }
            else if (par1GuiButton.id == 4)
            {
                this.field_96197_q.func_96135_a("http://realms.minecraft.net/");
            }
            else
            {
                this.field_96186_b.actionPerformed(par1GuiButton);
            }
        }
    }

    public void func_102018_a(long par1)
    {
        this.field_96189_n = -1;
        this.field_102019_y = Long.valueOf(par1);
    }

    private McoServer func_98086_a(long par1)
    {
        McoClient var3 = new McoClient(this.mc.session);

        try
        {
            return var3.func_98176_a(par1);
        }
        catch (ExceptionMcoService var5)
        {
            ;
        }
        catch (IOException var6)
        {
            ;
        }

        return null;
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == 59)
        {
            this.mc.gameSettings.hideServerAddress = !this.mc.gameSettings.hideServerAddress;
            this.mc.gameSettings.saveOptions();
        }
        else
        {
            if (par1 == 13)
            {
                this.actionPerformed((GuiButton)this.buttonList.get(2));
            }
            else
            {
                super.keyTyped(par1, par2);
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        this.field_96195_s = null;
        StringTranslate var4 = StringTranslate.getInstance();
        this.drawDefaultBackground();
        this.field_96186_b.drawScreen(par1, par2, par3);
        this.drawCenteredString(this.fontRenderer, var4.translateKey("mco.title"), this.width / 2, 20, 16777215);
        super.drawScreen(par1, par2, par3);

        if (this.field_96195_s != null)
        {
            this.func_96165_a(this.field_96195_s, par1, par2);
        }
    }

    private void func_96159_a(int par1)
    {
        if (par1 >= 0 && par1 < this.field_96192_v.size())
        {
            McoServer var2 = (McoServer)this.field_96192_v.get(par1);
            this.field_96194_t.func_98248_d();
            GuiScreenLongRunningTask var3 = new GuiScreenLongRunningTask(this.mc, this, new TaskOnlineConnect(this, var2));
            var3.func_98117_g();
            this.mc.displayGuiScreen(var3);
        }
    }

    private void func_101008_c(int par1, int par2, int par3, int par4)
    {
        this.mc.renderEngine.bindTexture("/gui/gui.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        this.drawTexturedModalRect(par1 * 2, par2 * 2, 191, 0, 16, 15);
        GL11.glPopMatrix();

        if (par3 >= par1 && par3 <= par1 + 9 && par4 >= par2 && par4 <= par2 + 9)
        {
            this.field_96195_s = "Expired World";
        }
    }

    private void func_104039_b(int par1, int par2, int par3, int par4, int par5)
    {
        if (this.field_104044_y % 20 < 10)
        {
            this.mc.renderEngine.bindTexture("/gui/gui.png");
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPushMatrix();
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.drawTexturedModalRect(par1 * 2, par2 * 2, 207, 0, 16, 15);
            GL11.glPopMatrix();
        }

        if (par3 >= par1 && par3 <= par1 + 9 && par4 >= par2 && par4 <= par2 + 9)
        {
            if (par5 == 0)
            {
                this.field_96195_s = "Expires in < a day";
            }
            else
            {
                this.field_96195_s = "Expires in " + par5 + (par5 > 1 ? " days" : " day");
            }
        }
    }

    private void func_101006_d(int par1, int par2, int par3, int par4)
    {
        this.mc.renderEngine.bindTexture("/gui/gui.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        this.drawTexturedModalRect(par1 * 2, par2 * 2, 207, 0, 16, 15);
        GL11.glPopMatrix();

        if (par3 >= par1 && par3 <= par1 + 9 && par4 >= par2 && par4 <= par2 + 9)
        {
            this.field_96195_s = "Open World";
        }
    }

    private void func_101001_e(int par1, int par2, int par3, int par4)
    {
        this.mc.renderEngine.bindTexture("/gui/gui.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        this.drawTexturedModalRect(par1 * 2, par2 * 2, 223, 0, 16, 15);
        GL11.glPopMatrix();

        if (par3 >= par1 && par3 <= par1 + 9 && par4 >= par2 && par4 <= par2 + 9)
        {
            this.field_96195_s = "Closed World";
        }
    }

    protected void func_96165_a(String par1Str, int par2, int par3)
    {
        if (par1Str != null)
        {
            int var4 = par2 + 12;
            int var5 = par3 - 12;
            int var6 = this.fontRenderer.getStringWidth(par1Str);
            this.drawGradientRect(var4 - 3, var5 - 3, var4 + var6 + 3, var5 + 8 + 3, -1073741824, -1073741824);
            this.fontRenderer.drawStringWithShadow(par1Str, var4, var5, -1);
        }
    }

    private void func_96174_a(McoServer par1McoServer) throws IOException
    {
        if (par1McoServer.field_96414_k.equals(""))
        {
            par1McoServer.field_96414_k = EnumChatFormatting.GRAY + "" + 0;
        }

        par1McoServer.field_96415_h = 61;
        ServerAddress var2 = ServerAddress.func_78860_a(par1McoServer.field_96403_g);
        Socket var3 = null;
        DataInputStream var4 = null;
        DataOutputStream var5 = null;

        try
        {
            var3 = new Socket();
            var3.setSoTimeout(3000);
            var3.setTcpNoDelay(true);
            var3.setTrafficClass(18);
            var3.connect(new InetSocketAddress(var2.getIP(), var2.getPort()), 3000);
            var4 = new DataInputStream(var3.getInputStream());
            var5 = new DataOutputStream(var3.getOutputStream());
            var5.write(254);
            var5.write(1);

            if (var4.read() != 255)
            {
                throw new IOException("Bad message");
            }

            String var6 = Packet.readString(var4, 256);
            char[] var7 = var6.toCharArray();

            for (int var8 = 0; var8 < var7.length; ++var8)
            {
                if (var7[var8] != 167 && var7[var8] != 0 && ChatAllowedCharacters.allowedCharacters.indexOf(var7[var8]) < 0)
                {
                    var7[var8] = 63;
                }
            }

            var6 = new String(var7);
            int var9;
            int var10;
            String[] var27;

            if (var6.startsWith("\u00a7") && var6.length() > 1)
            {
                var27 = var6.substring(1).split("\u0000");

                if (MathHelper.parseIntWithDefault(var27[0], 0) == 1)
                {
                    par1McoServer.field_96415_h = MathHelper.parseIntWithDefault(var27[1], par1McoServer.field_96415_h);
                    par1McoServer.field_96413_j = var27[2];
                    var9 = MathHelper.parseIntWithDefault(var27[4], 0);
                    var10 = MathHelper.parseIntWithDefault(var27[5], 0);

                    if (var9 >= 0 && var10 >= 0)
                    {
                        par1McoServer.field_96414_k = EnumChatFormatting.GRAY + "" + var9;
                    }
                    else
                    {
                        par1McoServer.field_96414_k = "" + EnumChatFormatting.DARK_GRAY + "???";
                    }
                }
                else
                {
                    par1McoServer.field_96413_j = "???";
                    par1McoServer.field_96415_h = 62;
                    par1McoServer.field_96414_k = "" + EnumChatFormatting.DARK_GRAY + "???";
                }
            }
            else
            {
                var27 = var6.split("\u00a7");
                var6 = var27[0];
                var9 = -1;
                var10 = -1;

                try
                {
                    var9 = Integer.parseInt(var27[1]);
                    var10 = Integer.parseInt(var27[2]);
                }
                catch (Exception var25)
                {
                    ;
                }

                par1McoServer.field_96407_c = EnumChatFormatting.GRAY + var6;

                if (var9 >= 0 && var10 > 0)
                {
                    par1McoServer.field_96414_k = EnumChatFormatting.GRAY + "" + var9;
                }
                else
                {
                    par1McoServer.field_96414_k = "" + EnumChatFormatting.DARK_GRAY + "???";
                }

                par1McoServer.field_96413_j = "1.3";
                par1McoServer.field_96415_h = 60;
            }
        }
        finally
        {
            try
            {
                if (var4 != null)
                {
                    var4.close();
                }
            }
            catch (Throwable var24)
            {
                ;
            }

            try
            {
                if (var5 != null)
                {
                    var5.close();
                }
            }
            catch (Throwable var23)
            {
                ;
            }

            try
            {
                if (var3 != null)
                {
                    var3.close();
                }
            }
            catch (Throwable var22)
            {
                ;
            }
        }
    }

    static Minecraft func_96177_a(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.mc;
    }

    static int func_98081_a(GuiScreenOnlineServers par0GuiScreenOnlineServers, int par1)
    {
        return par0GuiScreenOnlineServers.field_96199_x = par1;
    }

    static Minecraft func_98075_b(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.mc;
    }

    static List func_98094_c(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.field_96192_v;
    }

    static int func_98089_b(GuiScreenOnlineServers par0GuiScreenOnlineServers, int par1)
    {
        return par0GuiScreenOnlineServers.field_96189_n = par1;
    }

    static int func_98072_d(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.field_96189_n;
    }

    static GuiButton func_96161_f(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.field_96190_o;
    }

    static Minecraft func_98076_f(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.mc;
    }

    static GuiButton func_98092_g(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.field_96196_r;
    }

    static void func_98078_c(GuiScreenOnlineServers par0GuiScreenOnlineServers, int par1)
    {
        par0GuiScreenOnlineServers.func_96159_a(par1);
    }

    static Minecraft func_98091_h(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.mc;
    }

    static FontRenderer func_104038_i(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.fontRenderer;
    }

    static void func_101012_b(GuiScreenOnlineServers par0GuiScreenOnlineServers, int par1, int par2, int par3, int par4)
    {
        par0GuiScreenOnlineServers.func_101008_c(par1, par2, par3, par4);
    }

    static void func_101009_c(GuiScreenOnlineServers par0GuiScreenOnlineServers, int par1, int par2, int par3, int par4)
    {
        par0GuiScreenOnlineServers.func_101001_e(par1, par2, par3, par4);
    }

    static Minecraft func_104032_j(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.mc;
    }

    static void func_104030_a(GuiScreenOnlineServers par0GuiScreenOnlineServers, int par1, int par2, int par3, int par4, int par5)
    {
        par0GuiScreenOnlineServers.func_104039_b(par1, par2, par3, par4, par5);
    }

    static void func_104031_c(GuiScreenOnlineServers par0GuiScreenOnlineServers, int par1, int par2, int par3, int par4)
    {
        par0GuiScreenOnlineServers.func_101006_d(par1, par2, par3, par4);
    }

    static FontRenderer func_98084_i(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.fontRenderer;
    }

    static FontRenderer func_101005_j(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.fontRenderer;
    }

    static Object func_101007_h()
    {
        return field_96185_d;
    }

    static int func_101010_i()
    {
        return field_96187_c;
    }

    static int func_101014_j()
    {
        return field_96187_c++;
    }

    static void func_101002_a(GuiScreenOnlineServers par0GuiScreenOnlineServers, McoServer par1McoServer) throws IOException
    {
        par0GuiScreenOnlineServers.func_96174_a(par1McoServer);
    }

    static int func_101013_k()
    {
        return field_96187_c--;
    }

    static FontRenderer func_98079_k(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.fontRenderer;
    }

    static FontRenderer func_98087_l(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.fontRenderer;
    }

    static FontRenderer func_98074_m(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.fontRenderer;
    }

    static FontRenderer func_101000_n(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.fontRenderer;
    }

    static Minecraft func_101004_o(GuiScreenOnlineServers par0GuiScreenOnlineServers)
    {
        return par0GuiScreenOnlineServers.mc;
    }

    static String func_101011_a(GuiScreenOnlineServers par0GuiScreenOnlineServers, String par1Str)
    {
        return par0GuiScreenOnlineServers.field_96195_s = par1Str;
    }
}
