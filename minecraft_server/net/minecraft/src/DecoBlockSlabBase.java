package net.minecraft.src;

import java.util.List;

public class DecoBlockSlabBase extends FCBlockSlab {
	public final Block[] blockTypes;
	public final int[] typeMetas;
	private Icon theIcon;
	private Icon whiteStoneIcon;
	
	public DecoBlockSlabBase(int id, Material material, Block[] blocks, int[] metas)
	{
		super(id, material);
		this.setCreativeTab(CreativeTabs.tabBlock);
		blockTypes = blocks;
		typeMetas = metas;
	}

	/**
	 * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
	 * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
	 */
	protected ItemStack createStackedBlock(int par1)
	{
		return new ItemStack(this.blockID, 2, par1 & 7);
	}

	/**
	 * Returns the slab block name with step type.
	 */
	public String getFullSlabName(int par1)
	{
		if (par1 < 0 || par1 >= blockTypes.length)
		{
			par1 = 0;
		}

		return super.getUnlocalizedName() + "." + blockTypes[par1].getUnlocalizedName();
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int var4 = 0; var4 <= blockTypes.length; ++var4)
		{
			if (var4 != 2)
			{
				par3List.add(new ItemStack(par1, 1, var4));
			}
		}
	}

	public boolean GetIsUpsideDown(IBlockAccess var1, int var2, int var3, int var4)
	{
		return this.GetIsUpsideDown(var1.getBlockMetadata(var2, var3, var4));
	}

	public boolean GetIsUpsideDown(int var1)
	{
		return (var1 & 8) > 0;
	}

	public void SetIsUpsideDown(World var1, int var2, int var3, int var4, boolean var5)
	{
		int var6 = var1.getBlockMetadata(var2, var3, var4);
		var1.setBlockMetadataWithNotify(var2, var3, var4, this.SetIsUpsideDown(var6, var5));
	}

	public int SetIsUpsideDown(int var1, boolean var2)
	{
		if (var2)
		{
			var1 |= 8;
		}
		else
		{
			var1 &= 7;
		}

		return var1;
	}

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 & 7;
    }

	@Override
	public int GetCombinedBlockID(int var1) {
		return blockTypes[var1 & 7].blockID;
	}

	@Override
	public int GetCombinedMetadata(int var1){
		return typeMetas[var1 & 7];
	}

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) & 7;
    }
}