package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class AddonBlockStep extends FCBlockSlab
{
	/** The list of the types of step blocks. */
	public final Block[] blockTypes;
	public final int[] typeMetas;
	public final boolean[] mortaredList;
	public final Block[] looseDropList;
	public final int[] looseDropMetaList;
	private Icon theIcon;

	public AddonBlockStep(int par1, Block[] blocks, int[] metas, boolean[] mortared, Block[] looseDrop, int[] looseDropMeta)
	{
		super(par1, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.setStepSound(Block.soundStoneFootstep);
		blockTypes = blocks;
		typeMetas = metas;
		mortaredList = mortared;
		looseDropList = looseDrop;
		looseDropMetaList = looseDropMeta;
	}

	public AddonBlockStep(int par1, Block[] blocks, int[] metas)
	{
		super(par1, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.setStepSound(Block.soundStoneFootstep);
		blockTypes = blocks;
		typeMetas = metas;
		mortaredList = new boolean[] {false, false, false, false, false, false, false, false};
		looseDropList = new Block[] {null, null, null, null, null, null, null, null};
		looseDropMetaList = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
	}

	public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4) {
    	int meta = var1.getBlockMetadata(var2, var3, var4);
    	Block owner = blockTypes[meta % 8];
    	
    	return (owner instanceof AddonBlockSandStone || owner instanceof AddonBlockRedSandStone) ? 3 : 1;
	}

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
    	int meta = var1.getBlockMetadata(var2, var3, var4);
        return mortaredList[meta % 8];
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (!var1.isRemote)
        {
        	Block drop = this;
        	int dropMeta = var5;
        	
        	if (looseDropList[var5 % 8] != null) {
        		drop = looseDropList[var5 % 8];
        		dropMeta = looseDropMetaList[var5 % 8];
        	}
        	
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(drop, 1, dropMeta));
        }
    }
    
    public int damageDropped(int meta) {
    	return meta;
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
