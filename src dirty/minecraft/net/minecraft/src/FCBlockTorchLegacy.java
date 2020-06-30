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

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        Vec3 var6 = this.GetParticalPos(var1, var2, var3, var4);
        var1.spawnParticle("smoke", var6.xCoord, var6.yCoord, var6.zCoord, 0.0D, 0.0D, 0.0D);
    }
}
