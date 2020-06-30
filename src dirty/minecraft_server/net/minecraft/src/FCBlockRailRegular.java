package net.minecraft.src;

public class FCBlockRailRegular extends BlockRail
{
    public FCBlockRailRegular(int var1)
    {
        super(var1);
        this.setHardness(0.7F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundMetalFootstep);
        this.setUnlocalizedName("rail");
    }
}
