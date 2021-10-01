package net.minecraft.src;

public class DecoBlockHopper extends FCBlockHopper {
	public DecoBlockHopper(int id) {
		super(id);
	}

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new DecoTileEntityHopper();
    }
}