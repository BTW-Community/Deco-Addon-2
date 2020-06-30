package net.minecraft.src;

import java.util.Random;

public class FCBlockFireStoked extends FCBlockFire
{
    private final int m_iTickRate = 42;

    protected FCBlockFireStoked(int var1)
    {
        super(var1);
        this.setHardness(0.0F);
        this.setLightValue(1.0F);
        this.SetFireProperties(60, 0);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockStokedFire");
        this.disableStats();
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 42;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.ValidateState(var1, var2, var3, var4))
        {
            if (var1.getBlockId(var2, var3 + 1, var4) == Block.brick.blockID)
            {
                var1.setBlockWithNotify(var2, var3 + 1, var4, FCBetterThanWolves.fcKiln.blockID);
            }

            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (var6 < 15)
            {
                ++var6;
                var1.setBlockMetadata(var2, var3, var4, var6);
            }

            this.TryToDestroyBlockWithFire(var1, var2 + 1, var3, var4, 300, var5, 0);
            this.TryToDestroyBlockWithFire(var1, var2 - 1, var3, var4, 300, var5, 0);
            this.TryToDestroyBlockWithFire(var1, var2, var3 - 1, var4, 250, var5, 0);
            this.TryToDestroyBlockWithFire(var1, var2, var3 + 1, var4, 250, var5, 0);
            this.TryToDestroyBlockWithFire(var1, var2, var3, var4 - 1, 300, var5, 0);
            this.TryToDestroyBlockWithFire(var1, var2, var3, var4 + 1, 300, var5, 0);
            CheckForFireSpreadFromLocation(var1, var2, var3, var4, var5, 0);

            if (var6 >= 3)
            {
                var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.fire.blockID, 0);
            }
            else
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1) + var1.rand.nextInt(10));
            }
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
        {
            var1.setBlockMetadata(var2, var3, var4, 0);
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1) * 4);
        }
    }

    public boolean DoesInfiniteBurnToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 == 1;
    }

    public boolean ValidateState(World var1, int var2, int var3, int var4)
    {
        if (!this.canPlaceBlockAt(var1, var2, var3, var4))
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
            return false;
        }
        else if (var1.getBlockId(var2, var3 - 1, var4) == FCBetterThanWolves.fcBBQ.blockID)
        {
            if (!FCBetterThanWolves.fcBBQ.IsLit(var1, var2, var3 - 1, var4))
            {
                var1.setBlockWithNotify(var2, var3, var4, 0);
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.fire.blockID, 0);
            return false;
        }
    }
}
