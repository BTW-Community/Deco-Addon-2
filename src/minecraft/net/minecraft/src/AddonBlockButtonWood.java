package net.minecraft.src;

public class AddonBlockButtonWood extends AddonBlockButton {
    protected AddonBlockButtonWood(int var1, Block owner, int ownerMeta)
    {
        super(var1, true, owner, ownerMeta);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyant();
    }
}