package net.minecraft.src;

import java.util.Random;

public class FCBlockMushroomBrown extends FCBlockMushroom
{
    protected FCBlockMushroomBrown(int var1, String var2)
    {
        super(var1, var2);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcItemMushroomBrown.itemID;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.provider.dimensionId == 0)
        {
            if (var1.getBlockId(var2, var3 - 1, var4) == Block.mycelium.blockID && var5.nextInt(50) == 0)
            {
                this.fertilizeMushroom(var1, var2, var3, var4, var5);
            }
            else
            {
                this.CheckForSpread(var1, var2, var3, var4, var5);
            }
        }
    }

    protected boolean CanSpreadToOrFromLocation(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 - 1, var4);
        return var5 == Block.mycelium.blockID || var1.getFullBlockLightValue(var2, var3, var4) == 0;
    }

    protected void CheckForSpread(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var5.nextInt(25) == 0 && this.CanSpreadToOrFromLocation(var1, var2, var3, var4))
        {
            byte var6 = 4;
            int var7 = 5;
            int var8;
            int var9;
            int var10;

            for (var8 = var2 - var6; var8 <= var2 + var6; ++var8)
            {
                for (var9 = var4 - var6; var9 <= var4 + var6; ++var9)
                {
                    for (var10 = var3 - 1; var10 <= var3 + 1; ++var10)
                    {
                        if (var1.getBlockId(var8, var10, var9) == this.blockID)
                        {
                            --var7;

                            if (var7 <= 0)
                            {
                                return;
                            }
                        }
                    }
                }
            }

            var8 = var2 + var5.nextInt(3) - 1;
            var9 = var3 + var5.nextInt(2) - var5.nextInt(2);
            var10 = var4 + var5.nextInt(3) - 1;

            for (int var11 = 0; var11 < 4; ++var11)
            {
                if (var1.isAirBlock(var8, var9, var10) && this.canBlockStay(var1, var8, var9, var10) && this.CanSpreadToOrFromLocation(var1, var8, var9, var10))
                {
                    var2 = var8;
                    var3 = var9;
                    var4 = var10;
                }

                var8 = var2 + var5.nextInt(3) - 1;
                var9 = var3 + var5.nextInt(2) - var5.nextInt(2);
                var10 = var4 + var5.nextInt(3) - 1;
            }

            if (var1.isAirBlock(var8, var9, var10) && this.canBlockStay(var1, var8, var9, var10) && this.CanSpreadToOrFromLocation(var1, var8, var9, var10))
            {
                var1.setBlock(var8, var9, var10, this.blockID);
            }
        }
    }

    public boolean CanBlockStayDuringGenerate(World var1, int var2, int var3, int var4)
    {
        if (var3 > 24 || var1.provider.dimensionId != 0)
        {
            int var5 = var1.getBlockId(var2, var3 - 1, var4);

            if (var5 != Block.mycelium.blockID)
            {
                return false;
            }
        }

        return super.CanBlockStayDuringGenerate(var1, var2, var3, var4);
    }
}
