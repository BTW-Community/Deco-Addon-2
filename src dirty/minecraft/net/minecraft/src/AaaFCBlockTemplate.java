package net.minecraft.src;

public class AaaFCBlockTemplate extends Block
{
    protected AaaFCBlockTemplate(int var1, Material var2)
    {
        super(var1, Material.rock);
        this.setHardness(1.0F);
        this.setResistance(10.0F);
        this.SetShovelsEffectiveOn(false);
        this.SetPicksEffectiveOn(false);
        this.SetAxesEffectiveOn(false);
        this.SetChiselsEffectiveOn(false);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        this.SetNonBuoyant();
        this.SetFireProperties(FCEnumFlammability.NONE);
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.NONE);
        this.SetFilterableProperties(1);
        this.SetCanBeCookedByKiln(false);
        this.setLightOpacity(255);
        Block.useNeighborBrightness[var1] = false;
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockTemplate");
    }
}
