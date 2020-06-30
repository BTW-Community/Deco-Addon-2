package net.minecraft.src;

public class FCBlockCloth extends BlockCloth
{
    public FCBlockCloth()
    {
        this.setHardness(0.8F);
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.CLOTH);
        this.setStepSound(soundClothFootstep);
        this.setUnlocalizedName("cloth");
    }
}
