package net.minecraft.src;

public interface FCIBlockMechanical
{
    boolean CanOutputMechanicalPower();

    boolean CanInputMechanicalPower();

    boolean IsInputtingMechanicalPower(World var1, int var2, int var3, int var4);

    boolean IsOutputtingMechanicalPower(World var1, int var2, int var3, int var4);

    boolean CanInputAxlePowerToFacing(World var1, int var2, int var3, int var4, int var5);

    void Overpower(World var1, int var2, int var3, int var4);
}
