package net.minecraft.src;

public class FCBlockFurnaceBrickBurning extends FCBlockFurnaceBrick
{
    protected FCBlockFurnaceBrickBurning(int var1)
    {
        super(var1, true);
        this.setLightValue(0.5F);
        this.setLightOpacity(8);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }
}
