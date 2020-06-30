package net.minecraft.src;

public class FCEntityWitherPersistent extends FCEntityWither
{
    public FCEntityWitherPersistent(World var1)
    {
        super(var1);
    }

    public static void SummonWitherAtLocation(World var0, int var1, int var2, int var3)
    {
        FCEntityWither var4 = new FCEntityWither(var0);
        var4.setLocationAndAngles((double)var1 + 0.5D, (double)var2 - 1.45D, (double)var3 + 0.5D, 0.0F, 0.0F);
        var4.func_82206_m();
        var0.spawnEntityInWorld(var4);
        var0.playAuxSFX(2279, var1, var2, var3, 0);
        FCUtilsWorld.GameProgressSetWitherHasBeenSummonedServerOnly();
    }
}
