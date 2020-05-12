package net.minecraft.src;

public class AddonBlockHopper extends FCBlockHopper {
	public AddonBlockHopper(int id) {
		super(id);
	}

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new AddonTileEntityHopper();
    }
}