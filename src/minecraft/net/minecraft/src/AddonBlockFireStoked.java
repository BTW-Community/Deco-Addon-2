package net.minecraft.src;

import java.util.Random;

public class AddonBlockFireStoked extends FCBlockFireStoked {
    protected AddonBlockFireStoked(int id) {
		super(id);
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
            
            if (var1.getBlockId(var2, var3 + 1, var4) == Block.netherrack.blockID)
            {
                var1.setBlockWithNotify(var2, var3 + 1, var4, AddonDefs.netherrackSuperheated.blockID);
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
}
