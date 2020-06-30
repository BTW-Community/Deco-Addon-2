package net.minecraft.src;

import java.util.Random;

public class FCBlockEndPortal extends BlockEndPortal
{
    protected FCBlockEndPortal(int var1, Material var2)
    {
        super(var1, var2);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
        this.setTickRandomly(true);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        FCUtilsWorld.GameProgressSetEndDimensionHasBeenAccessedServerOnly();
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        super.updateTick(var1, var2, var3, var4, var5);
        FCUtilsWorld.GameProgressSetEndDimensionHasBeenAccessedServerOnly();
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        return false;
    }
}
