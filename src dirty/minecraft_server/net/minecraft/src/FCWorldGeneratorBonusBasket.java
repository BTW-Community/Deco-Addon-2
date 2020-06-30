package net.minecraft.src;

import java.util.Random;

public class FCWorldGeneratorBonusBasket extends WorldGenerator
{
    public boolean generate(World var1, Random var2, int var3, int var4, int var5)
    {
        for (int var6 = var1.getBlockId(var3, var4, var5); var4 > 1 && (var6 == 0 || var6 == Block.leaves.blockID); var6 = var1.getBlockId(var3, var4, var5))
        {
            --var4;
        }

        ++var4;

        for (int var7 = 0; var7 < 4; ++var7)
        {
            int var8 = var3 + var2.nextInt(4) - var2.nextInt(4);
            int var9 = var4 + var2.nextInt(3) - var2.nextInt(3);
            int var10 = var5 + var2.nextInt(4) - var2.nextInt(4);

            if (var1.isAirBlock(var8, var9, var10) && var1.doesBlockHaveSolidTopSurface(var8, var9 - 1, var10))
            {
                var1.setBlock(var8, var9, var10, FCBetterThanWolves.fcBlockBasketWicker.blockID, var1.rand.nextInt(4) | 4, 2);
                FCTileEntityBasketWicker var11 = (FCTileEntityBasketWicker)var1.getBlockTileEntity(var8, var9, var10);

                if (var11 != null)
                {
                    var11.SetStorageStack(new ItemStack(FCBetterThanWolves.fcItemGoldenDung));
                }

                return true;
            }
        }

        return false;
    }
}
