package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class GuiScreenResetWorld extends GuiScreen
{
    private GuiScreen field_96152_a;
    private McoServer field_96150_b;
    private GuiTextField field_96151_c;
    private final int field_96149_d = 1;
    private final int field_96153_n = 2;
    private GuiButton field_96154_o;

    public GuiScreenResetWorld(GuiScreen par1, McoServer par2)
    {
        this.field_96152_a = par1;
        this.field_96150_b = par2;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.field_96151_c.updateCursorCounter();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        StringTranslate var1 = StringTranslate.getInstance();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(this.field_96154_o = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96 + 12, var1.translateKey("mco.configure.world.buttons.reset")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 120 + 12, var1.translateKey("gui.cancel")));
        this.field_96151_c = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 109, 200, 20);
        this.field_96151_c.setFocused(true);
        this.field_96151_c.setMaxStringLength(32);
        this.field_96151_c.setText("");
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        this.field_96151_c.textboxKeyTyped(par1, par2);

        if (par1 == 13)
        {
            this.actionPerformed(this.field_96154_o);
        }
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == 2)
            {
                this.mc.displayGuiScreen(this.field_96152_a);
            }
            else if (par1GuiButton.id == 1)
            {
                TaskResetWorld var2 = new TaskResetWorld(this, this.field_96150_b.field_96408_a, this.field_96151_c.getText());
                GuiScreenLongRunningTask var3 = new GuiScreenLongRunningTask(this.mc, this.field_96152_a, var2);
                var3.func_98117_g();
                this.mc.displayGuiScreen(var3);
            }
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        this.field_96151_c.mouseClicked(par1, par2, par3);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        StringTranslate var4 = StringTranslate.getInstance();
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, var4.translateKey("mco.reset.world.title"), this.width / 2, 17, 16777215);
        this.drawCenteredString(this.fontRenderer, var4.translateKey("mco.reset.world.warning"), this.width / 2, 66, 16711680);
        this.drawString(this.fontRenderer, var4.translateKey("mco.reset.world.seed"), this.width / 2 - 100, 96, 10526880);
        this.field_96151_c.drawTextBox();
        super.drawScreen(par1, par2, par3);
    }

    static GuiScreen func_96148_a(GuiScreenResetWorld par0GuiScreenResetWorld)
    {
        return par0GuiScreenResetWorld.field_96152_a;
    }

    static Minecraft func_96147_b(GuiScreenResetWorld par0GuiScreenResetWorld)
    {
        return par0GuiScreenResetWorld.mc;
    }
}
