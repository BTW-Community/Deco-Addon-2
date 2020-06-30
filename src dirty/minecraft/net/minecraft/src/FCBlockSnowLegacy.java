package net.minecraft.src;

public class FCBlockSnowLegacy extends BlockSnowBlock
{
    protected FCBlockSnowLegacy(int var1)
    {
        super(var1);
        this.setHardness(0.2F);
        this.SetShovelsEffectiveOn();
        this.SetBuoyant();
        this.setStepSound(soundSnowFootstep);
        this.setUnlocalizedName("snow");
        this.setCreativeTab((CreativeTabs)null);
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }
}
