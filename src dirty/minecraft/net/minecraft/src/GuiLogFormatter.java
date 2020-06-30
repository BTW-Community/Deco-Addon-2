package net.minecraft.src;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

class GuiLogFormatter extends Formatter
{
    /** Reference to the GuiLogOutputHandler. */
    final GuiLogOutputHandler outputHandler;

    GuiLogFormatter(GuiLogOutputHandler par1GuiLogOutputHandler)
    {
        this.outputHandler = par1GuiLogOutputHandler;
    }

    public String format(LogRecord par1LogRecord)
    {
        StringBuilder var2 = new StringBuilder();
        var2.append(" [").append(par1LogRecord.getLevel().getName()).append("] ");
        var2.append(this.formatMessage(par1LogRecord));
        var2.append('\n');
        Throwable var3 = par1LogRecord.getThrown();

        if (var3 != null)
        {
            StringWriter var4 = new StringWriter();
            var3.printStackTrace(new PrintWriter(var4));
            var2.append(var4.toString());
        }

        return var2.toString();
    }
}
