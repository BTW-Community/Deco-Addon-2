package net.minecraft.src;

public class FCBlockBlackStone extends BlockQuartz
{
    public FCBlockBlackStone(int var1)
    {
        super(var1);
        this.setHardness(2.0F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("quartzBlock");
    }
}
