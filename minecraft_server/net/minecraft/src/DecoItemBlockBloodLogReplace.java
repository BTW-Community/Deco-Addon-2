package net.minecraft.src;

public class DecoItemBlockBloodLogReplace extends FCItemBlockBloodWood {
    public DecoItemBlockBloodLogReplace(int var1) {
		super(var1);
	}

    protected void PlayPlaceSound(World world, int x, int y, int z, Block block)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	
    	if (!world.isRemote) {
    		world.playAuxSFX(DecoManager.decoCustomBlockPlaceAuxFXID, x, y, z, block.blockID + (meta << 12));
    	}
    }
}
