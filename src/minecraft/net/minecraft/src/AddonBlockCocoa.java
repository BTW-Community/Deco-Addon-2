package net.minecraft.src;

public class AddonBlockCocoa extends FCBlockCocoa {
	public AddonBlockCocoa(int id) {
		super(id);
	}

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        int var5 = getDirection(par1World.getBlockMetadata(par2, par3, par4));
        par2 += Direction.offsetX[var5];
        par4 += Direction.offsetZ[var5];
        int var6 = par1World.getBlockId(par2, par3, par4);
        return (var6 == Block.wood.blockID || var6 == AddonDefs.barkLog.blockID || var6 == AddonDefs.strippedLog.blockID || var6 == AddonDefs.barkLogStripped.blockID) && BlockLog.limitToValidMetadata(par1World.getBlockMetadata(par2, par3, par4)) == 3;
    }
}