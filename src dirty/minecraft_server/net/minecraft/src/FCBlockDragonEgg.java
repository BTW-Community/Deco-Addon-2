package net.minecraft.src;

import java.util.Random;

public class FCBlockDragonEgg extends BlockDragonEgg
{
    public FCBlockDragonEgg(int var1)
    {
        super(var1);
        this.InitBlockBounds(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.CheckForFall(var1, var2, var3, var4);
    }

    public void OnBlockDestroyedLandingFromFall(World var1, int var2, int var3, int var4, int var5)
    {
        this.dropBlockAsItem(var1, var2, var3, var4, var5, 0);
    }
}
