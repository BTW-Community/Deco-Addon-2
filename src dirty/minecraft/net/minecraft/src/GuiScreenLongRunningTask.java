package net.minecraft.src;

import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.client.Minecraft;

public class GuiScreenLongRunningTask extends GuiScreen
{
    private final int field_96213_b = 666;
    private final GuiScreen field_96215_c;
    private final Thread field_98118_d;
    private volatile String field_96212_d = "";
    private volatile boolean field_96219_n = false;
    private volatile String field_96220_o;
    private volatile boolean field_96218_p = false;
    private int field_96216_q = 0;
    private TaskLongRunning field_96214_r;
    public static final String[] field_96217_a = new String[] {"\u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583", "_ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584", "_ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585", "_ _ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586", "_ _ _ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587", "_ _ _ _ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588", "_ _ _ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587", "_ _ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586", "_ _ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585", "_ \u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584", "\u2583 \u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583", "\u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _", "\u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _", "\u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _ _", "\u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _ _ _", "\u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _ _ _ _", "\u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _ _ _", "\u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _ _", "\u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _ _", "\u2584 \u2585 \u2586 \u2587 \u2588 \u2587 \u2586 \u2585 \u2584 \u2583 _"};

    public GuiScreenLongRunningTask(Minecraft par1Minecraft, GuiScreen par2GuiScreen, TaskLongRunning par3TaskLongRunning)
    {
        super.buttonList = Collections.synchronizedList(new ArrayList());
        this.mc = par1Minecraft;
        this.field_96215_c = par2GuiScreen;
        this.field_96214_r = par3TaskLongRunning;
        par3TaskLongRunning.func_96574_a(this);
        this.field_98118_d = new Thread(par3TaskLongRunning);
    }

    public void func_98117_g()
    {
        this.field_98118_d.start();
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
        ++this.field_96216_q;
        this.field_96214_r.func_96573_a();
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2) {}

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.field_96214_r.func_96571_d();
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.id == 666)
        {
            this.field_96218_p = true;
            this.mc.displayGuiScreen(this.field_96215_c);
        }

        this.field_96214_r.func_96572_a(par1GuiButton);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, this.field_96212_d, this.width / 2, this.height / 2 - 50, 16777215);
        this.drawCenteredString(this.fontRenderer, "", this.width / 2, this.height / 2 - 10, 16777215);

        if (!this.field_96219_n)
        {
            this.drawCenteredString(this.fontRenderer, field_96217_a[this.field_96216_q % field_96217_a.length], this.width / 2, this.height / 2 + 15, 8421504);
        }

        if (this.field_96219_n)
        {
            this.drawCenteredString(this.fontRenderer, this.field_96220_o, this.width / 2, this.height / 2 + 15, 16711680);
        }

        super.drawScreen(par1, par2, par3);
    }

    public void func_96209_a(String par1Str)
    {
        this.field_96219_n = true;
        this.field_96220_o = par1Str;
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(666, this.width / 2 - 100, this.height / 4 + 120 + 12, "Back"));
    }

    public Minecraft func_96208_g()
    {
        return this.mc;
    }

    public void func_96210_b(String par1Str)
    {
        this.field_96212_d = par1Str;
    }

    public boolean func_96207_h()
    {
        return this.field_96218_p;
    }
}
