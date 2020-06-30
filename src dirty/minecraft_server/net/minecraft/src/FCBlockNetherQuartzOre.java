package net.minecraft.src;

public class FCBlockNetherQuartzOre extends BlockOre
{
    public FCBlockNetherQuartzOre(int var1)
    {
        super(var1);
        this.SetBlockMaterial(FCBetterThanWolves.fcMaterialNetherRock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("netherquartz");
    }
}
