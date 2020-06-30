package net.minecraft.src;

public class FCBlockPumpkin extends FCBlockGourd
{
    protected FCBlockPumpkin(int var1, boolean var2)
    {
        super(var1);
        this.setHardness(1.0F);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockPumpkinFresh");
    }

    protected Item ItemToDropOnExplode()
    {
        return Item.pumpkinSeeds;
    }

    protected int ItemCountToDropOnExplode()
    {
        return 4;
    }

    protected int AuxFXIDOnExplode()
    {
        return 2250;
    }

    protected DamageSource GetFallDamageSource()
    {
        return FCDamageSourceCustom.m_DamageSourcePumpkin;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("pumpkin_side");
        this.m_IconTop = var1.registerIcon("pumpkin_top");
    }
}
