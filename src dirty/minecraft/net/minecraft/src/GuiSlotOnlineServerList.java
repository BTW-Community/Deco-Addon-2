package net.minecraft.src;

import org.lwjgl.opengl.GL11;

class GuiSlotOnlineServerList extends GuiScreenSelectLocation
{
    final GuiScreenOnlineServers field_96294_a;

    public GuiSlotOnlineServerList(GuiScreenOnlineServers par1)
    {
        super(GuiScreenOnlineServers.func_98075_b(par1), par1.width, par1.height, 32, par1.height - 64, 36);
        this.field_96294_a = par1;
    }

    /**
     * Gets the size of the current slot list.
     */
    protected int getSize()
    {
        return GuiScreenOnlineServers.func_98094_c(this.field_96294_a).size() + 1;
    }

    /**
     * the element in the slot that was clicked, boolean for wether it was double clicked or not
     */
    protected void elementClicked(int par1, boolean par2)
    {
        if (par1 < GuiScreenOnlineServers.func_98094_c(this.field_96294_a).size())
        {
            GuiScreenOnlineServers.func_98089_b(this.field_96294_a, par1);
            McoServer var3 = (McoServer)GuiScreenOnlineServers.func_98094_c(this.field_96294_a).get(GuiScreenOnlineServers.func_98072_d(this.field_96294_a));
            GuiScreenOnlineServers.func_96161_f(this.field_96294_a).enabled = GuiScreenOnlineServers.func_98076_f(this.field_96294_a).session.username.equals(var3.field_96405_e);
            GuiScreenOnlineServers.func_98092_g(this.field_96294_a).enabled = var3.field_96404_d.equals("OPEN") && !var3.field_98166_h;

            if (par2 && GuiScreenOnlineServers.func_98092_g(this.field_96294_a).enabled)
            {
                GuiScreenOnlineServers.func_98078_c(this.field_96294_a, GuiScreenOnlineServers.func_98072_d(this.field_96294_a));
            }
        }
    }

    /**
     * returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int par1)
    {
        return par1 == GuiScreenOnlineServers.func_98072_d(this.field_96294_a);
    }

    protected boolean func_104086_b(int par1)
    {
        return par1 < GuiScreenOnlineServers.func_98094_c(this.field_96294_a).size() && ((McoServer)GuiScreenOnlineServers.func_98094_c(this.field_96294_a).get(par1)).field_96405_e.toLowerCase().equals(GuiScreenOnlineServers.func_98091_h(this.field_96294_a).session.username);
    }

    /**
     * return the height of the content being scrolled
     */
    protected int getContentHeight()
    {
        return this.getSize() * 36;
    }

    protected void drawBackground()
    {
        this.field_96294_a.drawDefaultBackground();
    }

    protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
    {
        if (par1 < GuiScreenOnlineServers.func_98094_c(this.field_96294_a).size())
        {
            this.func_96292_b(par1, par2, par3, par4, par5Tessellator);
        }
    }

    private void func_96292_b(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
    {
        McoServer var6 = (McoServer)GuiScreenOnlineServers.func_98094_c(this.field_96294_a).get(par1);
        this.field_96294_a.drawString(GuiScreenOnlineServers.func_104038_i(this.field_96294_a), var6.func_96398_b(), par2 + 2, par3 + 1, 16777215);
        short var7 = 207;
        byte var8 = 1;

        if (var6.field_98166_h)
        {
            GuiScreenOnlineServers.func_101012_b(this.field_96294_a, par2 + var7, par3 + var8, this.field_104094_d, this.field_104095_e);
        }
        else if (var6.field_96404_d.equals("CLOSED"))
        {
            GuiScreenOnlineServers.func_101009_c(this.field_96294_a, par2 + var7, par3 + var8, this.field_104094_d, this.field_104095_e);
        }
        else if (var6.field_96405_e.equals(GuiScreenOnlineServers.func_104032_j(this.field_96294_a).session.username) && var6.field_104063_i < 7)
        {
            this.func_96293_a(par1, par2 - 14, par3, var6);
            GuiScreenOnlineServers.func_104030_a(this.field_96294_a, par2 + var7, par3 + var8, this.field_104094_d, this.field_104095_e, var6.field_104063_i);
        }
        else if (var6.field_96404_d.equals("OPEN"))
        {
            GuiScreenOnlineServers.func_104031_c(this.field_96294_a, par2 + var7, par3 + var8, this.field_104094_d, this.field_104095_e);
            this.func_96293_a(par1, par2 - 14, par3, var6);
        }

        this.field_96294_a.drawString(GuiScreenOnlineServers.func_98084_i(this.field_96294_a), var6.func_96397_a(), par2 + 2, par3 + 12, 7105644);
        this.field_96294_a.drawString(GuiScreenOnlineServers.func_101005_j(this.field_96294_a), var6.field_96405_e, par2 + 2, par3 + 12 + 11, 5000268);
    }

    private void func_96293_a(int par1, int par2, int par3, McoServer par4McoServer)
    {
        if (par4McoServer.field_96403_g != null)
        {
            synchronized (GuiScreenOnlineServers.func_101007_h())
            {
                if (GuiScreenOnlineServers.func_101010_i() < 5 && (!par4McoServer.field_96411_l || par4McoServer.field_102022_m))
                {
                    (new ThreadConnectToOnlineServer(this, par4McoServer)).start();
                }
            }

            boolean var5 = par4McoServer.field_96415_h > 61;
            boolean var6 = par4McoServer.field_96415_h < 61;
            boolean var7 = var5 || var6;

            if (par4McoServer.field_96414_k != null)
            {
                this.field_96294_a.drawString(GuiScreenOnlineServers.func_98079_k(this.field_96294_a), par4McoServer.field_96414_k, par2 + 215 - GuiScreenOnlineServers.func_98087_l(this.field_96294_a).getStringWidth(par4McoServer.field_96414_k), par3 + 1, 8421504);
            }

            if (var7)
            {
                String var8 = EnumChatFormatting.DARK_RED + par4McoServer.field_96413_j;
                this.field_96294_a.drawString(GuiScreenOnlineServers.func_98074_m(this.field_96294_a), var8, par2 + 200 - GuiScreenOnlineServers.func_101000_n(this.field_96294_a).getStringWidth(var8), par3 + 1, 8421504);
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GuiScreenOnlineServers.func_101004_o(this.field_96294_a).renderEngine.bindTexture("/gui/icons.png");
            byte var13 = 0;
            boolean var9 = false;
            String var10 = null;

            if (var7)
            {
                var10 = var5 ? "Client out of date!" : "Server out of date!";
                byte var14 = 5;
                this.field_96294_a.drawTexturedModalRect(par2 + 205, par3, var13 * 10, 176 + var14 * 8, 10, 8);
            }

            byte var11 = 4;

            if (this.field_104094_d >= par2 + 205 - var11 && this.field_104095_e >= par3 - var11 && this.field_104094_d <= par2 + 205 + 10 + var11 && this.field_104095_e <= par3 + 8 + var11)
            {
                GuiScreenOnlineServers.func_101011_a(this.field_96294_a, var10);
            }
        }
    }
}
