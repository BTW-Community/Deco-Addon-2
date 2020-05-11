package net.minecraft.src;

public class AddonBlockButtonWood extends AddonBlockButton {
    protected AddonBlockButtonWood(int var1)
    {
        super(var1, true);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyant();
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return Block.planks.getBlockTextureFromSide(1);
    }
}