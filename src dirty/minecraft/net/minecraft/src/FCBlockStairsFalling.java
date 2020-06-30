package net.minecraft.src;

import java.util.Random;

public class FCBlockStairsFalling extends FCBlockStairs
{
    protected FCBlockStairsFalling(int var1, Block var2, int var3)
    {
        super(var1, var2, var3);
    }

    public boolean IsFallingBlock()
    {
        return true;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        this.ScheduleCheckForFall(var1, var2, var3, var4);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        this.ScheduleCheckForFall(var1, var2, var3, var4);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.CheckForFall(var1, var2, var3, var4) && this.GetIsUpsideDown(var1, var2, var3, var4))
        {
            this.SetIsUpsideDown(var1, var2, var3, var4, false);
        }
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 2;
    }

    /**
     * Called when the falling block entity for this block is created
     */
    protected void onStartFalling(EntityFallingSand var1)
    {
        var1.metadata = this.SetIsUpsideDown(var1.metadata, false);
    }

    public void RenderFallingBlock(RenderBlocks var1, int var2, int var3, int var4, int var5)
    {
        var1.SetRenderAllFaces(true);
        var1.setRenderBounds(this.GetBoundsFromPoolForBase(var5));
        var1.RenderStandardFallingBlock(this, var2, var3, var4, var5);
        var1.setRenderBounds(this.GetBoundsFromPoolForSecondaryPiece(var5));
        var1.RenderStandardFallingBlock(this, var2, var3, var4, var5);
        var1.SetRenderAllFaces(false);
    }
}
