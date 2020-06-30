package net.minecraft.src;

import java.util.Random;

public class FCBlockRedstoneLight extends BlockRedstoneLight
{
    private final boolean powered;

    public FCBlockRedstoneLight(int var1, boolean var2)
    {
        super(var1, var2);
        this.SetPicksEffectiveOn(true);
        this.powered = var2;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return false;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        if (!var1.isRemote)
        {
            if (this.powered && !var1.isBlockIndirectlyGettingPowered(var2, var3, var4))
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 4);
            }
            else if (!this.powered && var1.isBlockIndirectlyGettingPowered(var2, var3, var4))
            {
                var1.setBlock(var2, var3, var4, Block.redstoneLampActive.blockID);
            }
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (this.powered)
        {
            if (!var1.isBlockIndirectlyGettingPowered(var2, var3, var4) && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 4);
            }
        }
        else if (var1.isBlockIndirectlyGettingPowered(var2, var3, var4))
        {
            var1.setBlockWithNotify(var2, var3, var4, Block.redstoneLampActive.blockID);
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!var1.isRemote && this.powered && !var1.isBlockIndirectlyGettingPowered(var2, var3, var4))
        {
            var1.setBlock(var2, var3, var4, Block.redstoneLampIdle.blockID);
        }
    }
}
