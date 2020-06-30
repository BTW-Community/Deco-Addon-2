package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class GuiScreenOnlineServersSubscreen
{
    private final int field_104074_g;
    private final int field_104081_h;
    private final int field_104082_i;
    private final int field_104080_j;
    List field_104079_a = new ArrayList();
    String[] field_104077_b;
    String[] field_104078_c;
    String[][] field_104075_d;
    int field_104076_e = 0;
    int field_104073_f = 0;

    public GuiScreenOnlineServersSubscreen(int par1, int par2, int par3, int par4)
    {
        this.field_104074_g = par1;
        this.field_104081_h = par2;
        this.field_104082_i = par3;
        this.field_104080_j = par4;
        this.func_104068_a();
    }

    private void func_104068_a()
    {
        this.func_104070_b();
        this.field_104079_a.add(new GuiButton(5005, this.field_104082_i, this.field_104080_j + 1, 212, 20, this.func_104072_c()));
        this.field_104079_a.add(new GuiButton(5006, this.field_104082_i, this.field_104080_j + 25, 212, 20, this.func_104067_d()));
    }

    private void func_104070_b()
    {
        StringTranslate var1 = StringTranslate.getInstance();
        this.field_104077_b = new String[] {var1.translateKey("options.difficulty.normal"), var1.translateKey("options.difficulty.hard"), var1.translateKey("options.difficulty.peaceful"), var1.translateKey("options.difficulty.easy")};
        this.field_104078_c = new String[] {var1.translateKey("mco.gameMode.survival"), var1.translateKey("mco.gameMode.creative"), var1.translateKey("mco.gameMode.adventure")};
        this.field_104075_d = new String[][] {{var1.translateKey("mco.gameMode.survival.line1"), var1.translateKey("mco.gameMode.survival.line2")}, {var1.translateKey("mco.gameMode.creative.line1"), var1.translateKey("mco.gameMode.creative.line2")}, {var1.translateKey("mco.gameMode.adventure.line1"), var1.translateKey("mco.gameMode.adventure.line2")}};
    }

    private String func_104072_c()
    {
        StringTranslate var1 = StringTranslate.getInstance();
        String var2 = var1.translateKey("options.difficulty");
        return var2 + ": " + this.field_104077_b[this.field_104076_e];
    }

    private String func_104067_d()
    {
        StringTranslate var1 = StringTranslate.getInstance();
        String var2 = var1.translateKey("mco.gameMode");
        return var2 + ": " + this.field_104078_c[this.field_104073_f];
    }

    void func_104069_a(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == 5005)
            {
                this.field_104076_e = (this.field_104076_e + 1) % this.field_104077_b.length;
                par1GuiButton.displayString = this.func_104072_c();
            }
            else if (par1GuiButton.id == 5006)
            {
                this.field_104073_f = (this.field_104073_f + 1) % this.field_104078_c.length;
                par1GuiButton.displayString = this.func_104067_d();
            }
        }
    }

    public void func_104071_a(GuiScreen par1GuiScreen, FontRenderer par2FontRenderer)
    {
        par1GuiScreen.drawString(par2FontRenderer, this.field_104075_d[this.field_104073_f][0], this.field_104082_i, this.field_104080_j + 50, 10526880);
        par1GuiScreen.drawString(par2FontRenderer, this.field_104075_d[this.field_104073_f][1], this.field_104082_i, this.field_104080_j + 60, 10526880);
    }
}
