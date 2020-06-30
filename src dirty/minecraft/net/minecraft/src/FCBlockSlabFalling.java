package net.minecraft.src;

import java.util.Random;

public abstract class FCBlockSlabFalling extends FCBlockSlab
{
    public FCBlockSlabFalling(int var1, Material var2)
    {
        super(var1, var2);
    }

    public boolean AttemptToCombineWithFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        if (var5.blockID == this.blockID && !this.GetIsUpsideDown(var1, var2, var3, var4))
        {
            this.ConvertToFullBlock(var1, var2, var3, var4);
            return true;
        }
        else
        {
            return false;
        }
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

    public boolean CanBePlacedUpsideDownAtLocation(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean ShouldRenderWhileFalling(World var1, EntityFallingSand var2)
    {
        int var3 = MathHelper.floor_double(var2.posX);
        int var4 = MathHelper.floor_double(var2.posY);
        int var5 = MathHelper.floor_double(var2.posZ);
        int var6 = var1.getBlockId(var3, var4, var5);
        Block var7 = Block.blocksList[var2.blockID];

        if (var6 == var2.blockID)
        {
            if (var2.posY - (double)var4 < 0.4D)
            {
                return false;
            }
        }
        else
        {
            FCBlockSlabFalling var8 = (FCBlockSlabFalling)var7;

            if (var8.GetCombinedBlockID(var2.metadata) == var6)
            {
                return false;
            }
        }

        return true;
    }

    public void RenderFallingBlock(RenderBlocks var1, int var2, int var3, int var4, int var5)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolFromMetadata(var5));
        var1.RenderStandardFallingBlock(this, var2, var3, var4, var5);
    }
}
