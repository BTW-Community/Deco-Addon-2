package net.minecraft.src;

import java.util.List;

public class DecoItemMultiBlock extends ItemBlockWithMetadata
{
	String[] blockNames;
	public DecoItemMultiBlock(Block owner, String[] names, String preTitle, String[] titles, String postTitle)
	{
		super(owner.blockID - 256,owner);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName(owner.getUnlocalizedName());
		blockNames = names;
		for (int i = 0; i < titles.length; ++i)
			DecoManager.Name(new ItemStack(this, 1, i), preTitle + titles[i] + postTitle);
	}
	public String getUnlocalizedName(ItemStack reference)
	{
		return super.getUnlocalizedName() + "." + this.blockNames[reference.getItemDamage()];
	}
	public void getSubItems(int var1, CreativeTabs var2, List var3)
	{
		for (int var4 = 0; var4 < this.blockNames.length; ++var4)
			var3.add(new ItemStack(this, 1, var4));
	}

    protected void PlayPlaceSound(World world, int x, int y, int z, Block block)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	
    	if (!world.isRemote) {
    		world.playAuxSFX(DecoManager.decoCustomBlockPlaceAuxFXID, x, y, z, block.blockID + (meta << 12));
    	}
    }
}