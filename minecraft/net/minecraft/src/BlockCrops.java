// BTWModded
package net.minecraft.src;

import java.util.Random;

public class BlockCrops extends BlockFlower
{
	private Icon[] iconArray;

	protected BlockCrops(int par1)
	{
		super(par1);
		this.setTickRandomly(true);
		float var2 = 0.5F;
		// FCMOD: Changed
		//this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, 0.25F, 0.5F + var2);
		InitBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, 0.25F, 0.5F + var2);
		//END FCMOD
		this.setCreativeTab((CreativeTabs)null);
		this.setHardness(0.0F);
		this.setStepSound(soundGrassFootstep);
		this.disableStats();
	}

	/**
	 * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
	 * blockID passed in. Args: blockID
	 */
	// FCMOD: Removed as deprecated
	/*
    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == Block.tilledField.blockID;
    }
	 */
	// END FCMOD

	/**
	 * Ticks the block if it's been scheduled
	 */
	// FCMOD: Removed and replaced
	/*
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
        {
            int var6 = par1World.getBlockMetadata(par2, par3, par4);

            if (var6 < 7)
            {
                float var7 = this.getGrowthRate(par1World, par2, par3, par4);

                if (par5Random.nextInt((int)(25.0F / var7) + 1) == 0)
                {
                    ++var6;
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 2);
                }
            }
        }
    }
	 */
	// END FCMOD

	/**
	 * Apply bonemeal to the crops.
	 */
	public void fertilize(World par1World, int par2, int par3, int par4)
	{
		int var5 = par1World.getBlockMetadata(par2, par3, par4) + MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);

		if (var5 > 7)
		{
			var5 = 7;
		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, var5, 2);
	}

	/**
	 * Gets the growth rate for the crop. Setup to encourage rows by halving growth rate if there is diagonals, crops on
	 * different sides that aren't opposing, and by adding growth for every crop next to this one (and for crop below
	 * this one). Args: x, y, z
	 */
	// FCMOD: Removed as deprecated
	/*
    private float getGrowthRate(World par1World, int par2, int par3, int par4)
    {
        float var5 = 1.0F;
        int var6 = par1World.getBlockId(par2, par3, par4 - 1);
        int var7 = par1World.getBlockId(par2, par3, par4 + 1);
        int var8 = par1World.getBlockId(par2 - 1, par3, par4);
        int var9 = par1World.getBlockId(par2 + 1, par3, par4);
        int var10 = par1World.getBlockId(par2 - 1, par3, par4 - 1);
        int var11 = par1World.getBlockId(par2 + 1, par3, par4 - 1);
        int var12 = par1World.getBlockId(par2 + 1, par3, par4 + 1);
        int var13 = par1World.getBlockId(par2 - 1, par3, par4 + 1);
        boolean var14 = var8 == this.blockID || var9 == this.blockID;
        boolean var15 = var6 == this.blockID || var7 == this.blockID;
        boolean var16 = var10 == this.blockID || var11 == this.blockID || var12 == this.blockID || var13 == this.blockID;

        for (int var17 = par2 - 1; var17 <= par2 + 1; ++var17)
        {
            for (int var18 = par4 - 1; var18 <= par4 + 1; ++var18)
            {
                int var19 = par1World.getBlockId(var17, par3 - 1, var18);
                float var20 = 0.0F;

                if (var19 == Block.tilledField.blockID)
                {
                    var20 = 1.0F;

                    if (par1World.getBlockMetadata(var17, par3 - 1, var18) > 0)
                    {
                        var20 = 3.0F;
                    }
                }

                if (var17 != par2 || var18 != par4)
                {
                    var20 /= 4.0F;
                }

                var5 += var20;
            }
        }

        if (var16 || var14 && var15)
        {
            var5 /= 2.0F;
        }

        return var5;
    }
	 */
	// END FCMOD

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int par1, int par2)
	{
		if (par2 < 0 || par2 > 7)
		{
			par2 = 7;
		}

		return this.iconArray[par2];
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return 6;
	}

	/**
	 * Generate a seed ItemStack for this crop.
	 */
	protected int getSeedItem()
	{
		return Item.seeds.itemID;
	}

	/**
	 * Generate a crop produce ItemStack for this crop.
	 */
	protected int getCropItem()
	{
		return Item.wheat.itemID;
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		// DecoMod
		if (this.IsBlockHydratedForPlantGrowthOn(par1World, par2, par3 - 1, par4)) {
			super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);

			if (!par1World.isRemote)
			{
				if (par5 >= 7)
				{
					// FCMOD: Change
					/*
                int var8 = 3 + par7;

                for (int var9 = 0; var9 < var8; ++var9)
                {
                    if (par1World.rand.nextInt(15) <= par5)
                    {
                        this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this.getSeedItem(), 1, 0));
                    }
                }
					 */
					DropSeeds( par1World, par2, par3, par4, par5, par6, par7);
					// END FCMOD
				}
			}
		}
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	// FCMOD: Removed and replaced
	/*
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return par1 == 7 ? this.getCropItem() : this.getSeedItem();
    }
	 */
	// END FCMOD

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}

	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return this.getSeedItem();
	}

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.iconArray = new Icon[8];

		for (int var2 = 0; var2 < this.iconArray.length; ++var2)
		{
			this.iconArray[var2] = par1IconRegister.registerIcon("crops_" + var2);
		}
	}

	// FCMOD: Added New    
	@Override
	public int idDropped( int iMetadata, Random random, int iFortuneModifier )
	{
		if ( iMetadata == 7 )
		{
			return getCropItem();
		}

		return 0;
	}

	@Override
	public boolean CanBeGrazedOn( IBlockAccess blockAccess, int i, int j, int k, 
			EntityAnimal animal )
	{
		return true;
	}

	@Override
	public void OnGrazed( World world, int i, int j, int k, EntityAnimal animal )
	{
		// drop the block as an item so that animals can get the base graze value + eat
		// any tasties that drop

		dropBlockAsItem( world, i, j, k, world.getBlockMetadata( i, j, k ), 0 );

		super.OnGrazed( world, i, j, k, animal );    
	}

	@Override
	public void updateTick( World world, int i, int j, int k, Random rand )
	{
		super.updateTick( world, i, j, k, rand );

		// make sure parent update didn't destroy the block, and prevent crops growing in the end
		// note that CanGrowOnBlock() has already been confirmed in parent method    	

		if ( world.provider.dimensionId != 1 && 
				world.getBlockId( i, j, k ) == blockID )
		{
			AttemptToGrow( world, i, j, k, rand );
		}
	}

	@Override
	protected boolean CanGrowOnBlock( World world, int i, int j, int k )
	{
		Block blockOn = Block.blocksList[world.getBlockId( i, j, k )];

		return blockOn != null && blockOn.CanDomesticatedCropsGrowOnBlock( world, i, j, k );
	}

	@Override
	public boolean CanWeedsGrowInBlock( IBlockAccess blockAccess, int i, int j, int k )
	{
		return true;
	}

	//------------- Class Specific Methods ------------//

	protected void AttemptToGrow( World world, int i, int j, int k, Random rand )
	{
		if ( GetWeedsGrowthLevel( world, i, j, k ) == 0 && 
				GetGrowthLevel( world, i, j, k ) < 7 && 
				world.getBlockLightValue( i, j + 1, k ) >= 9 )
		{
			Block blockBelow = Block.blocksList[world.getBlockId( i, j - 1, k )];

			if ( blockBelow != null && 
					blockBelow.IsBlockHydratedForPlantGrowthOn( world, i, j - 1, k ) )
			{
				float fGrowthChance = GetBaseGrowthChance( world, i, j, k ) *
						blockBelow.GetPlantGrowthOnMultiplier( world, i, j - 1, k, this );

				if ( rand.nextFloat() <= fGrowthChance )
				{
					IncrementGrowthLevel( world, i, j, k );
				}
			}
		}
	}

	public void DropSeeds( World world, int i, int j, int k, int iMetadata, 
			float fChance, int iFortuneModifier )
	{
		dropBlockAsItem_do(world, i, j, k, new ItemStack( getSeedItem(), 1, 0 ) );

		if ( world.rand.nextInt( 16 ) - iFortuneModifier < 4 )
		{
			dropBlockAsItem_do(world, i, j, k, new ItemStack( getSeedItem(), 1, 0 ) );
		}
	}

	public float GetBaseGrowthChance( World world, int i, int j, int k )
	{
		return 0.05F;
	}

	protected void IncrementGrowthLevel( World world, int i, int j, int k )
	{
		int iGrowthLevel = GetGrowthLevel( world, i, j, k ) + 1;

		SetGrowthLevel( world, i, j, k, iGrowthLevel );

		if ( iGrowthLevel == 7 )
		{
			Block blockBelow = Block.blocksList[world.getBlockId( i, j - 1, k )];

			if ( blockBelow != null )
			{
				blockBelow.NotifyOfFullStagePlantGrowthOn( world, i, j - 1, k, this );
			}
		}
	}

	protected int GetGrowthLevel( IBlockAccess blockAccess, int i, int j, int k )
	{
		return GetGrowthLevel( blockAccess.getBlockMetadata( i, j, k ) );
	}

	protected int GetGrowthLevel( int iMetadata )
	{
		return iMetadata & 7;
	}

	protected void SetGrowthLevel( World world, int i, int j, int k, int iLevel )
	{
		int iMetadata = world.getBlockMetadata( i, j, k ) & (~7); // filter out old level   	

		world.setBlockMetadataWithNotify( i, j, k, iMetadata | iLevel );
	}

	protected void SetGrowthLevelNoNotify( World world, int i, int j, int k, int iLevel )
	{
		int iMetadata = world.getBlockMetadata( i, j, k ) & (~7); // filter out old level   	

		world.setBlockMetadata( i, j, k, iMetadata | iLevel );
	}

	//----------- Client Side Functionality -----------//

	@Override
	public boolean RenderBlock( RenderBlocks renderer, int i, int j, int k )
	{
		renderer.setRenderBounds( GetBlockBoundsFromPoolBasedOnState( 
				renderer.blockAccess, i, j, k ) );

		renderer.renderBlockCrops( this, i, j, k );

		FCBetterThanWolves.fcBlockWeeds.RenderWeeds( this, renderer, i, j, k );

		return true;
	}    
	// END FCMOD
}
