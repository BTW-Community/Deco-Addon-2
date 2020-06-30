package net.minecraft.src;

import java.util.Random;

public class FCBlockTorchLegacy extends FCBlockTorchBaseBurning
{
    protected FCBlockTorchLegacy(int var1)
    {
        super(var1);
        this.setLightValue(0.9375F);
        this.setUnlocalizedName("torch");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcBlockTorchNetherBurning.blockID;
    }
}
