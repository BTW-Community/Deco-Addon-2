package net.minecraft.src;

import java.util.Random;

public class FCBlockAshGroundCover extends FCBlockGroundCover
{
    protected FCBlockAshGroundCover(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialAsh);
        this.setTickRandomly(true);
        this.setStepSound(soundSandFootstep);
        this.setUnlocalizedName("fcBlockAshGroundCover");
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.IsRainingAtPos(var2, var3, var4))
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    public boolean GetCanGrassGrowUnderBlock(World var1, int var2, int var3, int var4, boolean var5)
    {
        return false;
    }

    public boolean GetCanBlockBeReplacedByFire(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public static boolean CanAshReplaceBlock(World var0, int var1, int var2, int var3)
    {
        Block var4 = Block.blocksList[var0.getBlockId(var1, var2, var3)];
        return var4 == null || var4.IsAirBlock() || var4.IsGroundCover() && var4 != FCBetterThanWolves.fcBlockAshGroundCover;
    }

    public static boolean AttemptToPlaceAshAt(World var0, int var1, int var2, int var3)
    {
        if (CanAshReplaceBlock(var0, var1, var2, var3))
        {
            int var4 = var0.getBlockId(var1, var2 - 1, var3);
            Block var5 = Block.blocksList[var4];

            if (var5 != null && var5.CanGroundCoverRestOnBlock(var0, var1, var2 - 1, var3))
            {
                var0.setBlockWithNotify(var1, var2, var3, FCBetterThanWolves.fcBlockAshGroundCover.blockID);
                return true;
            }
        }

        return false;
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        super.randomDisplayTick(var1, var2, var3, var4, var5);

        if (var5.nextInt(10) == 0)
        {
            double var6 = (double)var3 + 0.25D;
            var1.spawnParticle("townaura", (double)var2 + var5.nextDouble(), var6, (double)var4 + var5.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }
}
