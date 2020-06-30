package net.minecraft.src;

public class FCBlockGlowStone extends BlockGlowStone
{
    public FCBlockGlowStone(int var1)
    {
        super(var1, Material.glass);
        this.setHardness(0.6F);
        this.setResistance(0.5F);
        this.SetPicksEffectiveOn();
        this.setLightValue(1.0F);
        this.setStepSound(soundGlassFootstep);
        this.setUnlocalizedName("lightgem");
    }
}
