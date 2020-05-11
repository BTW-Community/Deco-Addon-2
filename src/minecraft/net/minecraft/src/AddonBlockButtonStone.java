package net.minecraft.src;

public class AddonBlockButtonStone extends AddonBlockButton {
    protected AddonBlockButtonStone(int var1)
    {
        super(var1, false);
        this.SetPicksEffectiveOn(true);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return Block.stone.getBlockTextureFromSide(1);
    }

}
