package net.minecraft.src;

import java.util.Random;

public class FCBlockCocoa extends BlockCocoa
{
    public FCBlockCocoa(int var1)
    {
        super(var1);
        this.SetAxesEffectiveOn(true);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.canBlockStay(var1, var2, var3, var4))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
        else if (var1.provider.dimensionId != 1 && var1.rand.nextInt(20) == 0)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            int var7 = func_72219_c(var6);

            if (var7 < 2)
            {
                ++var7;
                var1.setBlockMetadataWithNotify(var2, var3, var4, var7 << 2 | getDirection(var6));
            }
        }
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        int var8 = func_72219_c(var5);
        byte var9 = 0;

        if (var8 >= 2)
        {
            var9 = 1;

            if (var1.rand.nextInt(4) - var7 <= 0)
            {
                var9 = 2;
            }
        }

        for (int var10 = 0; var10 < var9; ++var10)
        {
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(FCBetterThanWolves.fcItemCocoaBeans, 1, 0));
        }
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World var1, int var2, int var3, int var4)
    {
        return 0;
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return new ItemStack(FCBetterThanWolves.fcItemCocoaBeans.itemID, 1, 0);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        int var6 = getDirection(var5);
        int var7 = func_72219_c(var5);
        int var8 = 4 + var7 * 2;
        int var9 = 5 + var7 * 2;
        float var10 = (float)var8 / 2.0F;

        switch (var6)
        {
            case 0:
                return AxisAlignedBB.getAABBPool().getAABB((double)((8.0F - var10) / 16.0F), (double)((12.0F - (float)var9) / 16.0F), (double)((15.0F - (float)var8) / 16.0F), (double)((8.0F + var10) / 16.0F), 0.75D, 0.9375D);

            case 1:
                return AxisAlignedBB.getAABBPool().getAABB(0.0625D, (double)((12.0F - (float)var9) / 16.0F), (double)((8.0F - var10) / 16.0F), (double)((1.0F + (float)var8) / 16.0F), 0.75D, (double)((8.0F + var10) / 16.0F));

            case 2:
                return AxisAlignedBB.getAABBPool().getAABB((double)((8.0F - var10) / 16.0F), (double)((12.0F - (float)var9) / 16.0F), 0.0625D, (double)((8.0F + var10) / 16.0F), 0.75D, (double)((1.0F + (float)var8) / 16.0F));

            default:
                return AxisAlignedBB.getAABBPool().getAABB((double)((15.0F - (float)var8) / 16.0F), (double)((12.0F - (float)var9) / 16.0F), (double)((8.0F - var10) / 16.0F), 0.9375D, 0.75D, (double)((8.0F + var10) / 16.0F));
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return this.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4).offset((double)var2, (double)var3, (double)var4);
    }
}
