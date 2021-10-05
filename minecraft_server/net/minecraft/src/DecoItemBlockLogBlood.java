package net.minecraft.src;

public class DecoItemBlockLogBlood extends FCItemBlockLog
{
    public DecoItemBlockLogBlood(int var1, Block var2, String[] var3)
    {
        super(var1, var2, var3);
    }

    protected void PlayPlaceSound(World world, int x, int y, int z, Block block)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	
    	if (!world.isRemote) {
    		world.playAuxSFX(DecoManager.decoCustomBlockPlaceAuxFXID, x, y, z, block.blockID + (meta << 12));
    	}
    }
    
	public String getUnlocalizedName(ItemStack reference)
	{
		String unlocalizedName = super.getUnlocalizedName();
		switch (reference.getItemDamage()) {
		case 0:
			unlocalizedName += ".stripped";
			break;
		case 1:
			unlocalizedName += ".wood";
			break;
		case 2:
			unlocalizedName += ".strippedWood";
			break;
		}
		
		return unlocalizedName;
	}
}