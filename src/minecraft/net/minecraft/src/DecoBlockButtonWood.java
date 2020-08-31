package net.minecraft.src;

public class DecoBlockButtonWood extends DecoBlockButton {
    protected DecoBlockButtonWood(int var1, Block owner, int ownerMeta)
    {
        super(var1, true, owner, ownerMeta);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyant();
    }
}