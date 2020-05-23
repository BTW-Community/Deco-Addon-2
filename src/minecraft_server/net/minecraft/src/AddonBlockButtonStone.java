package net.minecraft.src;

public class AddonBlockButtonStone extends AddonBlockButton {
    protected AddonBlockButtonStone(int var1, Block owner, int ownerMeta)
    {
        super(var1, false, owner, ownerMeta);
        this.SetPicksEffectiveOn(true);
    }
}