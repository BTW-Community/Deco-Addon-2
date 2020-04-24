package net.minecraft.src;

import java.util.Random;

public class AddonBlockLogBark extends FCBlockLog {

	public AddonBlockLogBark(int ID) {
		super(ID);
		AddonManager.Register(this, new String[] {"barkOak", "barkSpruce", "barkBirch", "barkJungle"}, new String[] {"Oak Wood", "Spruce Wood", "Birch Wood", "Jungle Wood"});
	}
	
	@Override
	public boolean GetIsStump(IBlockAccess access, int x, int y, int z) {
		return false;
	}

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return AddonDefs.barkLog.blockID;
    }
}
