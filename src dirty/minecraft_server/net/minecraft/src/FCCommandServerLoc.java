package net.minecraft.src;

public class FCCommandServerLoc extends CommandBase
{
    public String getCommandName()
    {
        return "loc";
    }

    public String getCommandUsage(ICommandSender var1)
    {
        return "/loc";
    }

    public void processCommand(ICommandSender var1, String[] var2)
    {
        if (var1 instanceof EntityPlayer)
        {
            EntityPlayer var3 = (EntityPlayer)var1;
            var1.sendChatToPlayer("\u00a7e" + "Current Location: " + MathHelper.floor_double(var3.posX) + ", " + MathHelper.floor_double(var3.posY) + ", " + MathHelper.floor_double(var3.posZ));
        }
    }
}
