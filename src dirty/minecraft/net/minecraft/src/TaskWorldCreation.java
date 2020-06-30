package net.minecraft.src;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

class TaskWorldCreation extends TaskLongRunning
{
    private final String field_96589_c;
    private final String field_96587_d;
    private final String field_96588_e;
    private final String field_104065_f;

    final GuiScreenCreateOnlineWorld field_96590_a;

    public TaskWorldCreation(GuiScreenCreateOnlineWorld par1GuiScreenCreateOnlineWorld, String par2Str, String par3Str, String par4Str, String par5Str)
    {
        this.field_96590_a = par1GuiScreenCreateOnlineWorld;
        this.field_96589_c = par2Str;
        this.field_96587_d = par3Str;
        this.field_96588_e = par4Str;
        this.field_104065_f = par5Str;
    }

    public void run()
    {
        String var1 = StringTranslate.getInstance().translateKey("mco.create.world.wait");
        this.func_96576_b(var1);
        McoClient var2 = new McoClient(GuiScreenCreateOnlineWorld.func_96248_a(this.field_96590_a).session);

        try
        {
            var2.func_96386_a(this.field_96589_c, this.field_96587_d, this.field_96588_e, this.field_104065_f);
            GuiScreenCreateOnlineWorld.func_96246_c(this.field_96590_a).displayGuiScreen(GuiScreenCreateOnlineWorld.func_96247_b(this.field_96590_a));
        }
        catch (ExceptionMcoService var4)
        {
            this.func_96575_a(var4.field_96391_b);
        }
        catch (UnsupportedEncodingException var5)
        {
            ;
        }
        catch (IOException var6)
        {
            ;
        }
        catch (Exception var7)
        {
            this.func_96575_a("Failed");
        }
    }
}
