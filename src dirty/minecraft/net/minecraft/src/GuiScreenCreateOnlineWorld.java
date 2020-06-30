package net.minecraft.src;

import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class GuiScreenCreateOnlineWorld extends GuiScreen
{
    private GuiScreen field_96260_a;
    private GuiTextField field_96257_c;
    private GuiTextField field_96255_b;
    private String field_98108_c;
    private String field_98109_n;
    private static int field_96253_d = 0;
    private static int field_96261_n = 1;
    private boolean field_96256_r = false;
    private String field_96254_s = "You must enter a name!";

    public GuiScreenCreateOnlineWorld(GuiScreen par1GuiScreen)
    {
        super.buttonList = Collections.synchronizedList(new ArrayList());
        this.field_96260_a = par1GuiScreen;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.field_96257_c.updateCursorCounter();
        this.field_98108_c = this.field_96257_c.getText();
        this.field_96255_b.updateCursorCounter();
        this.field_98109_n = this.field_96255_b.getText();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        StringTranslate var1 = StringTranslate.getInstance();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(field_96253_d, this.width / 2 - 100, this.height / 4 + 120 + 17, 97, 20, var1.translateKey("mco.create.world")));
        this.buttonList.add(new GuiButton(field_96261_n, this.width / 2 + 5, this.height / 4 + 120 + 17, 95, 20, var1.translateKey("gui.cancel")));
        this.field_96257_c = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 65, 200, 20);
        this.field_96257_c.setFocused(true);

        if (this.field_98108_c != null)
        {
            this.field_96257_c.setText(this.field_98108_c);
        }

        this.field_96255_b = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 111, 200, 20);

        if (this.field_98109_n != null)
        {
            this.field_96255_b.setText(this.field_98109_n);
        }
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
            if (par1GuiButton.id == field_96261_n)
            {
                this.mc.displayGuiScreen(this.field_96260_a);
            }
            else if (par1GuiButton.id == field_96253_d)
            {
                this.func_96252_h();
            }
        }
    }

    private void func_96252_h()
    {
        if (this.func_96249_i())
        {
            TaskWorldCreation var1 = new TaskWorldCreation(this, this.field_96257_c.getText(), "Minecraft Realms Server", "NO_LOCATION", this.field_98109_n);
            GuiScreenLongRunningTask var2 = new GuiScreenLongRunningTask(this.mc, this.field_96260_a, var1);
            var2.func_98117_g();
            this.mc.displayGuiScreen(var2);
        }
    }

    private boolean func_96249_i()
    {
        this.field_96256_r = this.field_96257_c.getText() == null || this.field_96257_c.getText().trim().equals("");
        return !this.field_96256_r;
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        this.field_96257_c.textboxKeyTyped(par1, par2);
        this.field_96255_b.textboxKeyTyped(par1, par2);

        if (par1 == 9)
        {
            this.field_96257_c.setFocused(!this.field_96257_c.isFocused());
            this.field_96255_b.setFocused(!this.field_96255_b.isFocused());
        }

        if (par1 == 13)
        {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        this.field_96257_c.mouseClicked(par1, par2, par3);
        this.field_96255_b.mouseClicked(par1, par2, par3);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        StringTranslate var4 = StringTranslate.getInstance();
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, var4.translateKey("mco.selectServer.create"), this.width / 2, 11, 16777215);
        this.drawString(this.fontRenderer, var4.translateKey("mco.configure.world.name"), this.width / 2 - 100, 52, 10526880);
        this.drawString(this.fontRenderer, var4.translateKey("mco.configure.world.seed"), this.width / 2 - 100, 98, 10526880);

        if (this.field_96256_r)
        {
            this.drawCenteredString(this.fontRenderer, this.field_96254_s, this.width / 2, 167, 16711680);
        }

        this.field_96257_c.drawTextBox();
        this.field_96255_b.drawTextBox();
        super.drawScreen(par1, par2, par3);
    }

    static Minecraft func_96248_a(GuiScreenCreateOnlineWorld par0GuiScreenCreateOnlineWorld)
    {
        return par0GuiScreenCreateOnlineWorld.mc;
    }

    static GuiScreen func_96247_b(GuiScreenCreateOnlineWorld par0GuiScreenCreateOnlineWorld)
    {
        return par0GuiScreenCreateOnlineWorld.field_96260_a;
    }

    static Minecraft func_96246_c(GuiScreenCreateOnlineWorld par0GuiScreenCreateOnlineWorld)
    {
        return par0GuiScreenCreateOnlineWorld.mc;
    }
}
