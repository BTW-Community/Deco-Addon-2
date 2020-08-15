package net.minecraft.src;

public class AddonItemChain extends FCItemPlacesAsBlock
{
    public AddonItemChain(int var1)
    {
        super(var1, AddonDefs.chain.blockID);
        this.setUnlocalizedName("ginger_chainItem");
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    protected void PlayPlaceSound(World world, int x, int y, int z, Block block)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	
    	if (!world.isRemote) {
    		world.playAuxSFX(AddonManager.addonCustomBlockPlaceAuxFXID, x, y, z, block.blockID + (meta << 12));
    	}
    }
}