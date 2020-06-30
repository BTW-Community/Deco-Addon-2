package net.minecraft.src;

public class AddonBlockFire extends FCBlockFire {
    protected AddonBlockFire(int id) {
		super(id);
	}

	/**
     * Checks the specified block coordinate to see if it can catch fire.  Args: blockAccess, x, y, z
     */
    public boolean canBlockCatchFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (var5 == AddonDefs.netherCoalBlock.blockID)
        {
            return true;
        }

        return super.canBlockCatchFire(var1, var2, var3, var4);
    }
}
