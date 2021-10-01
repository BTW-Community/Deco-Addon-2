package net.minecraft.src;

import java.util.Random;

public class DecoBlockStairsMortared extends FCBlockStairs {
	private int looseDropID;
	
	protected DecoBlockStairsMortared(int id, Block owner, int ownerMeta, int dropID) {
		super(id, owner, ownerMeta);
		looseDropID = dropID;
	}

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return looseDropID;
    }

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }
}
