package net.minecraft.src;

public class FCBlockJukebox extends BlockJukeBox
{
    protected FCBlockJukebox(int var1)
    {
        super(var1);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WOOD_BASED_BLOCK);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("jukebox");
    }
}
