package net.minecraft.src;

public class AddonBlockFlowerPot extends FCBlockFlowerPot {
	public AddonBlockFlowerPot(int id) {
		super(id);
	}

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        return new AddonTileEntityFlowerPot();
    }
}
