package net.minecraft.src;

public class DecoBlockButtonStone extends DecoBlockButton {
    public DecoBlockButtonStone(int var1, Block owner, int ownerMeta)
    {
        super(var1, false, owner, ownerMeta);
        this.SetPicksEffectiveOn(true);
    }
}