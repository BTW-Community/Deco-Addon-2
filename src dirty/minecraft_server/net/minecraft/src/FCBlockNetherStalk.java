package net.minecraft.src;

import java.util.Random;

public class FCBlockNetherStalk extends BlockNetherStalk
{
    protected FCBlockNetherStalk(int var1)
    {
        super(var1);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
        this.setStepSound(soundGrassFootstep);
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World var1, int var2, int var3, int var4)
    {
        return this.CanGrowOnBlock(var1, var2, var3 - 1, var4);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.provider.dimensionId != -1)
        {
            this.checkFlowerChange(var1, var2, var3, var4);
        }
        else
        {
            super.updateTick(var1, var2, var3, var4, var5);
        }
    }

    protected boolean CanGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3, var4)];
        return var5 != null && var5.CanNetherWartGrowOnBlock(var1, var2, var3, var4);
    }
}
