package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class AddonBlockStep extends FCBlockSlab
{
	/** The list of the types of step blocks. */
	public final Block[] blockTypes;
	public final int[] typeMetas;
	private Icon theIcon;

	public AddonBlockStep(int par1, Block[] blocks, int[] metas)
	{
		super(par1, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		blockTypes = blocks;
		typeMetas = metas;
	}

	public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4) {
		return 3;
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return AddonDefs.stoneSlab.blockID;
	}

	/**
	 * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
	 * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
	 */
	protected ItemStack createStackedBlock(int par1)
	{
		return new ItemStack(AddonDefs.stoneSlab.blockID, 2, par1 & 7);
	}

	/**
	 * Takes a block ID, returns true if it's the same as the ID for a stone or wooden single slab.
	 */
	private static boolean isBlockSingleSlab(int par0)
	{
		return par0 == AddonDefs.stoneSlab.blockID;
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

	@Override
	public int GetCombinedBlockID(int var1) {
		return blockTypes[var1].blockID;
	}

	@Override
	public int GetCombinedMetadata(int var1){
		return typeMetas[var1];
	}

	//CLIENT ONLY
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int side, int meta)
	{
		int baseMeta = meta & 7;

		return blockTypes[baseMeta].getIcon(side, typeMetas[baseMeta]);
		//return var3 == 0 ? AddonDefs.redSandStone.getBlockTextureFromSide(par1) : (var3 == 1 ? AddonDefs.prismarine.getBlockTextureFromSide(par1) : (var3 == 2 ? AddonDefs.prismarine.getIcon(par1, 1) : (var3 == 3 ? AddonDefs.prismarine.getIcon(par1, 2) : (var3 == 4 ? FCBetterThanWolves.fcAestheticOpaque.getIcon(par1, 9) : (var3 == 5 ? AddonDefs.whiteStoneBrick.getBlockTextureFromSide(par1) : (var3 == 6 ? Block.cobblestoneMossy.getBlockTextureFromSide(1) : (var3 == 7 ? Block.blockNetherQuartz.getBlockTextureFromSide(par1) : this.blockIcon)))))));
	}

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("stoneslab_top");
		this.theIcon = par1IconRegister.registerIcon("stoneslab_side");
	}
}
