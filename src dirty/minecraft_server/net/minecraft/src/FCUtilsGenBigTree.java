package net.minecraft.src;

public class FCUtilsGenBigTree extends WorldGenBigTree
{
    public FCUtilsGenBigTree(boolean var1)
    {
        super(var1);
    }

    void genTreeLayer(int var1, int var2, int var3, float var4, byte var5, int var6)
    {
        int var7 = (int)((double)var4 + 0.618D);
        byte var8 = otherCoordPairs[var5];
        byte var9 = otherCoordPairs[var5 + 3];
        int[] var10 = new int[] {var1, var2, var3};
        int[] var11 = new int[] {0, 0, 0};
        int var12 = -var7;
        int var13 = -var7;

        for (var11[var5] = var10[var5]; var12 <= var7; ++var12)
        {
            var11[var8] = var10[var8] + var12;
            int var14 = -var7;

            while (var14 <= var7)
            {
                double var15 = Math.sqrt(Math.pow((double)Math.abs(var12) + 0.5D, 2.0D) + Math.pow((double)Math.abs(var14) + 0.5D, 2.0D));

                if (var15 > (double)var4)
                {
                    ++var14;
                }
                else
                {
                    var11[var9] = var10[var9] + var14;
                    int var17 = this.worldObj.getBlockId(var11[0], var11[1], var11[2]);

                    if (!this.worldObj.isAirBlock(var11[0], var11[1], var11[2]) && var17 != 18)
                    {
                        ++var14;
                    }
                    else
                    {
                        this.setBlockAndMetadata(this.worldObj, var11[0], var11[1], var11[2], var6, 0);
                        ++var14;
                    }
                }
            }
        }
    }

    /**
     * Checks a line of blocks in the world from the first coordinate to triplet to the second, returning the distance
     * (in blocks) before a non-air, non-leaf block is encountered and/or the end is encountered.
     */
    int checkBlockLine(int[] var1, int[] var2)
    {
        int[] var3 = new int[] {0, 0, 0};
        byte var4 = 0;
        byte var5;

        for (var5 = 0; var4 < 3; ++var4)
        {
            var3[var4] = var2[var4] - var1[var4];

            if (Math.abs(var3[var4]) > Math.abs(var3[var5]))
            {
                var5 = var4;
            }
        }

        if (var3[var5] == 0)
        {
            return -1;
        }
        else
        {
            byte var6 = otherCoordPairs[var5];
            byte var7 = otherCoordPairs[var5 + 3];
            byte var8;

            if (var3[var5] > 0)
            {
                var8 = 1;
            }
            else
            {
                var8 = -1;
            }

            double var9 = (double)var3[var6] / (double)var3[var5];
            double var11 = (double)var3[var7] / (double)var3[var5];
            int[] var13 = new int[] {0, 0, 0};
            int var14 = 0;
            int var15;

            for (var15 = var3[var5] + var8; var14 != var15; var14 += var8)
            {
                var13[var5] = var1[var5] + var14;
                var13[var6] = MathHelper.floor_double((double)var1[var6] + (double)var14 * var9);
                var13[var7] = MathHelper.floor_double((double)var1[var7] + (double)var14 * var11);
                int var16 = this.worldObj.getBlockId(var13[0], var13[1], var13[2]);

                if (!this.worldObj.isAirBlock(var13[0], var13[1], var13[2]) && var16 != 18)
                {
                    break;
                }
            }

            return var14 == var15 ? -1 : Math.abs(var14);
        }
    }

    /**
     * Returns a boolean indicating whether or not the current location for the tree, spanning basePos to to the height
     * limit, is valid.
     */
    boolean validTreeLocation()
    {
        int[] var1 = new int[] {this.basePos[0], this.basePos[1], this.basePos[2]};
        int[] var2 = new int[] {this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2]};
        int var3 = this.worldObj.getBlockId(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);

        if (!FCUtilsTrees.CanSaplingGrowOnBlock(this.worldObj, this.basePos[0], this.basePos[1] - 1, this.basePos[2]))
        {
            return false;
        }
        else
        {
            int var4 = this.checkBlockLine(var1, var2);

            if (var4 == -1)
            {
                return true;
            }
            else if (var4 < 6)
            {
                return false;
            }
            else
            {
                this.heightLimit = var4;
                return true;
            }
        }
    }
}
