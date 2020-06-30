package net.minecraft.src;

public class FCBlockDispenserVanilla extends BlockDispenser
{
    protected FCBlockDispenserVanilla(int var1)
    {
        super(var1);
        this.setHardness(3.5F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("dispenser");
    }

    public int GetFacing(int var1)
    {
        return var1 & 7;
    }

    public int SetFacing(int var1, int var2)
    {
        return var1 & -8 | var2;
    }
}
