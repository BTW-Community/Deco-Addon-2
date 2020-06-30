package net.minecraft.src;

import java.util.Random;

public class FCUtilsRandomPositionGenerator extends RandomPositionGenerator
{
    public static boolean FindSimpleRandomTargetBlock(EntityCreature var0, int var1, int var2, FCUtilsBlockPos var3)
    {
        Random var4 = var0.getRNG();
        boolean var5 = false;
        float var6 = -99999.0F;
        int var7 = MathHelper.floor_double(var0.posX) - var1;
        int var8 = (int)var0.posY - var2;
        int var9 = MathHelper.floor_double(var0.posZ) - var1;
        var1 = var1 * 2 + 1;
        var2 = var2 * 2 + 1;

        for (int var10 = 0; var10 < 10; ++var10)
        {
            int var11 = var7 + var4.nextInt(var1);
            int var12 = var8 + var4.nextInt(var2);
            int var13 = var9 + var4.nextInt(var1);
            float var14 = var0.getBlockPathWeight(var11, var12, var13);

            if (var14 > var6)
            {
                var6 = var14;
                var3.i = var11;
                var3.j = var12;
                var3.k = var13;
                var5 = true;
            }
        }

        return var5;
    }
}
