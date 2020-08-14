package net.minecraft.src;

public class AddonItemBlockWithCustomSound extends ItemBlock {
	public AddonItemBlockWithCustomSound(int par1) {
		super(par1);
	}

    protected void PlayPlaceSound(World world, int x, int y, int z, Block block)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	
    	if (!world.isRemote) {
    		world.playAuxSFX(AddonManager.addonCustomBlockPlaceAuxFXID, x, y, z, block.blockID + (meta << 12));
    	}
    }
}