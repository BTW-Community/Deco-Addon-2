package net.minecraft.src;

public class DecoItemChain extends FCItemPlacesAsBlock
{
    public DecoItemChain(int var1)
    {
        super(var1, DecoDefs.chain.blockID);
        this.setUnlocalizedName("decoItemChain");
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    protected void PlayPlaceSound(World world, int x, int y, int z, Block block)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	
    	if (!world.isRemote) {
    		world.playAuxSFX(DecoManager.decoCustomBlockPlaceAuxFXID, x, y, z, block.blockID + (meta << 12));
    	}
    }
}