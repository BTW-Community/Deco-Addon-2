package net.minecraft.src;

import java.util.Random;

public class FCBlockTorchNetherBurning extends FCBlockTorchBaseBurning
{
    protected FCBlockTorchNetherBurning(int var1)
    {
        super(var1);
        this.setLightValue(0.9375F);
        this.setUnlocalizedName("fcBlockTorchNetherBurning");
        this.setTickRandomly(true);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        super.updateTick(var1, var2, var3, var4, var5);
        FCBlockFire.CheckForFireSpreadAndDestructionToOneBlockLocation(var1, var2, var3 + 1, var4, var5, 0, 25);
    }

    public boolean CanBeCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        return true;
    }
}
