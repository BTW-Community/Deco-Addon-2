package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class AddonBlockWoodSlab extends FCBlockSlab
{
	/** The list of the types of step blocks. */
	public final Block[] blockTypes;
	public final int[] typeMetas;
	public final int[] sawedIDs;
	public final int[] sawedMetas;
	private Icon theIcon;

	public AddonBlockWoodSlab(int par1, Block[] blocks, int[] metas, int[] sawedIDs, int[] sawedMetas)
	{
		super(par1, FCBetterThanWolves.fcMaterialPlanks);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.SetAxesEffectiveOn();
		this.setStepSound(Block.soundWoodFootstep);
		this.SetFireProperties(FCEnumFlammability.PLANKS);
		this.SetBuoyant();
		this.blockTypes = blocks;
		this.typeMetas = metas;
		this.sawedIDs = sawedIDs;
		this.sawedMetas = sawedMetas;
	}

	public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4) {
		return 2;
	}
    
    public int damageDropped(int meta) {
    	return meta;
    }

	@Override
    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
		int metadata = var1.getBlockMetadata(var2, var3, var4);
        return sawedIDs[metadata % 8];
    }

	@Override
    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 2;
    }

	@Override
    public int GetItemDamageDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
		int metadata = var1.getBlockMetadata(var2, var3, var4);
        return sawedMetas[metadata % 8];
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

	@Override
	public int GetCombinedBlockID(int var1) {
		return blockTypes[var1].blockID;
	}

	@Override
	public int GetCombinedMetadata(int var1){
		return typeMetas[var1];
	}

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) & 7;
    }

	//CLIENT ONLY
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int side, int meta)
	{
		int baseMeta = meta & 7;

		return blockTypes[baseMeta].getIcon(side, typeMetas[baseMeta]);
	}

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("planks");
		this.theIcon = par1IconRegister.registerIcon("planks");
	}
}