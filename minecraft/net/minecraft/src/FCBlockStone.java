// FCMOD

package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockStone extends FCBlockFullBlock
{
	public FCBlockStone(int iBlockID)
	{
		super(iBlockID, Material.rock);
		
		setHardness(2.25F);
		setResistance(10F);

		SetPicksEffectiveOn();
		SetChiselsEffectiveOn();

		setStepSound(soundStoneFootstep);

		setUnlocalizedName("stone");        

		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public float getBlockHardness(World world, int i, int j, int k)
	{
		int iStrata = GetStrata(world, i, j, k);

		if (iStrata != 0)
		{
			// normal stone has a hardness of 2.25

			if (iStrata == 1)
			{
				return 3.0F;
			}
			else
			{
				return 4.5F; 
			}
		}

		return super.getBlockHardness(world, i, j, k);
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int i, int j, int k)
	{
		int iStrata = GetStrata(world, i, j, k);

		if (iStrata != 0)
		{
			// normal stone has a resistance of 10

			if (iStrata == 1)
			{
				return 13F * (3.0F / 5.0F);
			}
			else
			{
				return  20F * (3.0F / 5.0F);
			}
		}

		return super.getExplosionResistance(entity, world, i, j, k);
	}

	@Override
	public int idDropped(int metadata, Random random, int fortuneModifier) {
		if (isSlate(metadata)) {
			return DecoDefs.slateCobbleLoose.blockID;
		}
		else {
			return FCBetterThanWolves.fcBlockCobblestoneLoose.blockID;
		}
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortuneModifier) {
		super.dropBlockAsItemWithChance(world, x, y, z, metadata, chance, fortuneModifier);

		if (!world.isRemote) {
			if (isSlate(metadata)) {
				dropBlockAsItem_do(world, x, y, z, new ItemStack(DecoDefs.slateStone));
			}
			else {
				dropBlockAsItem_do(world, x, y, z, new ItemStack(FCBetterThanWolves.fcItemStone));
			}

			if (!GetIsCracked(metadata)) {
				dropBlockAsItem_do(world, x, y, z, new ItemStack(FCBetterThanWolves.fcItemPileGravel));
			}
		}
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		player.addStat(StatList.mineBlockStatArray[this.blockID], 1);

		player.AddHarvestBlockExhaustion(blockID, x, y, z, metadata);

		if (this.canSilkHarvest(metadata) && EnchantmentHelper.getSilkTouchModifier(player)) {
			ItemStack stack = this.createStackedBlock(metadata);

			if (stack != null) {
				this.dropBlockAsItem_do(world, x, y, z, stack);
			}
		}
		else {
			if (isBlockIsolated(world, x, y, z) && canToolHarvestIntact(player.getCurrentEquippedItem())) {
				ItemStack stack = this.createStackedBlock(metadata);

				if (stack != null) {
					this.dropBlockAsItem_do(world, x, y, z, stack);
				}
			}
			else {
				int fortuneModifier = EnchantmentHelper.getFortuneModifier(player);
				this.dropBlockAsItem(world, x, y, z, metadata, fortuneModifier);
			}
		}
	}

	@Override
	public boolean DropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		if (isSlate(metadata) || isCrackedSlate(metadata)) {
			DropItemsIndividualy(world, x, y, z, DecoDefs.slateStone.itemID, 5, 0, chanceOfDrop);
		}
		else {
			DropItemsIndividualy(world, x, y, z, FCBetterThanWolves.fcItemStone.itemID, 5, 0, chanceOfDrop);
		}

		int numGravel = GetIsCracked(metadata) ? 2 : 3;

		DropItemsIndividualy(world, x, y, z, FCBetterThanWolves.fcItemPileGravel.itemID, numGravel, 0, chanceOfDrop);

		return true;
	}

	@Override
	public boolean CanConvertBlock(ItemStack stack, World world, int i, int j, int k)
	{
		return true;
	}

	@Override
	public boolean ConvertBlock(ItemStack stack, World world, int x, int y, int z, int fromSide) {
		int metadata = world.getBlockMetadata(x, y, z);
		int strata = GetStrata(metadata);

		int toolLevel = GetConversionLevelForTool(stack, world, x, y, z);    	

		if (GetIsCracked(metadata)) {
			world.setBlockAndMetadataWithNotify(x, y, z, FCBlockStoneRough.m_startaLevelBlockArray[strata].blockID, 0);

			if (!world.isRemote && toolLevel > 0) {
				world.playAuxSFX(FCBetterThanWolves.m_iStoneRippedOffAuxFXID, x, y, z, 0);
				
				if (isCrackedSlate(metadata)) {
					FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(DecoDefs.slateStone, 1), fromSide);
				}
				else {
					FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(FCBetterThanWolves.fcItemStone, 1), fromSide);
				}
			}
		}
		else {
			if (toolLevel == 2) {
				// level 2 is stone pick on top strata stone, which has its own thing going on

				world.setBlockAndMetadataWithNotify(x, y, z, FCBlockStoneRough.m_startaLevelBlockArray[strata].blockID, 4);

				if (!world.isRemote) {
					world.playAuxSFX(FCBetterThanWolves.m_iStoneRippedOffAuxFXID, x, y, z, 0);
					
					if (isCrackedSlate(metadata)) {
						FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(DecoDefs.slateStone, 3), fromSide);
					}
					else {
						FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(FCBetterThanWolves.fcItemStone, 3), fromSide);
					}

					FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(FCBetterThanWolves.fcItemPileGravel, 1), fromSide);
				}
			}
			else if (toolLevel == 3) {
				// level 3 is iron chisel on first two strata, resulting in stone brick

				world.setBlockAndMetadataWithNotify(x, y, z, FCBlockStoneRough.m_startaLevelBlockArray[strata].blockID, 2);

				if (!world.isRemote) {
					world.playAuxSFX(FCBetterThanWolves.m_iStoneRippedOffAuxFXID, x, y, z, 0);
					
					if (isSlate(metadata)) {
						FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(DecoDefs.slateBrickItem, 1), fromSide);
					}
					else {
						FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1), fromSide);
					}
					
					FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(FCBetterThanWolves.fcItemPileGravel, 1), fromSide);
				}
			}
			else if (toolLevel == 4) {
				// level 4 is diamond chisel resulting in stone brick unless the block is isolated

				if (isBlockIsolated(world, x, y, z)) {
					world.setBlockToAir(x, y, z);

					ItemStack dropStack = this.createStackedBlock(metadata);

					if (stack != null) {
						this.dropBlockAsItem_do(world, x, y, z, dropStack);
					}
				}
				else {
					world.setBlockAndMetadataWithNotify(x, y, z, FCBlockStoneRough.m_startaLevelBlockArray[strata].blockID, 2);

					if (!world.isRemote) {
						world.playAuxSFX(FCBetterThanWolves.m_iStoneRippedOffAuxFXID, x, y, z, 0);
						
						if (isSlate(metadata)) {
							FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(DecoDefs.slateBrickItem, 1), fromSide);
						}
						else {
							FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 1), fromSide);
						}
						
						FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(FCBetterThanWolves.fcItemPileGravel, 1), fromSide);
					}
				}
			}
			else {
				if (!world.isRemote) {
					world.playAuxSFX(FCBetterThanWolves.m_iGravelRippedOffStoneAuxFXID, x, y, z, 0);							        

					FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(FCBetterThanWolves.fcItemPileGravel, 1), fromSide);
				}

				SetIsCracked(world, x, y, z, true);
			}
		}

		return true;
	}

	@Override
	public int GetHarvestToolLevel(IBlockAccess blockAccess, int i, int j, int k)
	{
		int iStrata = GetStrata(blockAccess, i, j, k);

		if (iStrata > 1)
		{
			return MathHelper.clamp_int(iStrata + 1, 0, 3);
		}

		return 2;
	}

	@Override
	public int GetEfficientToolLevel(IBlockAccess blockAccess, int i, int j, int k)
	{
		int iStrata = GetStrata(blockAccess, i, j, k);

		if (iStrata > 0)
		{
			return MathHelper.clamp_int(iStrata + 1, 0, 3);
		}

		return 0;
	}

	@Override
	public boolean IsNaturalStone(IBlockAccess blockAccess, int i, int j, int k)
	{
		return true;
	}

	@Override
	public boolean CanRotateOnTurntable(IBlockAccess access, int x, int y, int z) {
		return access.getBlockMetadata(x,y,z) == 8 || access.getBlockMetadata(x,y,z) == 9;
	}

	@Override
	public int RotateMetadataAroundJAxis(int meta, boolean var2) {
		if (isSlate(meta) && meta != 3) {
			if (meta == 8) {
				meta = 9;
			}
			else if (meta == 9) {
				meta = 8;
			}
		}

		return meta;
	}

	@Override
	protected ItemStack createStackedBlock(int metadata) {
		int dropMeta = metadata;

		if (isSlate(metadata))
			dropMeta = 3;

		return new ItemStack(this.blockID, 1, dropMeta);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		if (isSlate(meta)) {
			switch (side) {
			case 0:
			case 1:
				meta = 3;
				break;
			case 2:
			case 3:
				meta = 9;
				break;
			case 4:
			case 5:
				meta = 8;
			}
		}

		return meta;
	}

	//------------- Class Specific Methods ------------//

	/**
	 * Returns 1, 2, 3, or 4 depending on the level of the conversion tool.  0 if it can't convert
	 */ 
	private int GetConversionLevelForTool(ItemStack stack, World world, int i, int j, int k)
	{
		if (stack != null)
		{
			if (stack.getItem() instanceof FCItemPickaxe)
			{
				int iToolLevel = ((FCItemTool)stack.getItem()).toolMaterial.getHarvestLevel();

				if (iToolLevel >= GetEfficientToolLevel(world, i, j, k))
				{
					return 2;        				
				}
			}  
			else if (stack.getItem() instanceof FCItemChisel)
			{
				if (canToolHarvestIntact(stack))
					return 4;

				int iToolLevel = ((FCItemTool)stack.getItem()).toolMaterial.getHarvestLevel();

				if (iToolLevel >= GetEfficientToolLevel(world, i, j, k))
				{
					if (iToolLevel >= GetUberToolLevel(world, i, j, k))
					{
						return 3;
					}

					return 1;
				}
			}  
		}

		return 0;
	}

	public int GetStrata(IBlockAccess blockAccess, int i, int j, int k)
	{
		return GetStrata(blockAccess.getBlockMetadata(i, j, k));
	}

	public int GetStrata(int metadata) {
		if (metadata < 8) {
			return metadata & 3;
		}
		else {
			return 3;
		}
	}

	public void SetIsCracked(World world, int i, int j, int k, boolean bCracked)
	{
		int iMetadata = SetIsCracked(world.getBlockMetadata(i, j, k), bCracked);

		world.setBlockMetadataWithNotify(i, j, k, iMetadata);
	}

	public int SetIsCracked(int metadata, boolean cracked) {
		if (cracked) {
			if (isSlate(metadata))
				metadata = 7;
			else
				metadata |= 4;
		}
		else {
			metadata &= (~4);
		}

		return metadata;
	}

	public boolean GetIsCracked(IBlockAccess blockAccess, int i, int j, int k)
	{
		return GetIsCracked(blockAccess.getBlockMetadata(i, j, k));
	}

	public boolean GetIsCracked(int metadata) {
		return (metadata & 4) != 0 && !isSlate(metadata);
	}

	public int GetUberToolLevel(IBlockAccess blockAccess, int i, int j, int k)
	{
		return 2;
	}

	public boolean canToolHarvestIntact(ItemStack stack) {
		return (stack.getItem() instanceof FCItemPickaxe && ((FCItemPickaxe) stack.getItem()).toolMaterial.getHarvestLevel() >= EnumToolMaterial.EMERALD.getHarvestLevel()) || stack.itemID == DecoDefs.chiselDiamond.itemID;
	}

	public boolean isBlockIsolated(World world, int x, int y, int z) {
		boolean isolated = true;

		for (int dir = 0; dir < 6; dir++) {
			FCUtilsBlockPos pos = new FCUtilsBlockPos(x, y, z);
			pos.AddFacingAsOffset(dir);

			Block neighbor = Block.blocksList[world.getBlockId(pos.i, pos.j, pos.k)];

			if (neighbor != null && neighbor.IsNaturalStone(world, pos.i, pos.j, pos.k))
				isolated = false;
		}

		return isolated;
	}

	public boolean isSlate(int metadata) {
		return metadata == 3 || metadata == 8 || metadata == 9;
	}
	
	public boolean isCrackedSlate(int metadata) {
		return metadata == 7;
	}

    /**
     * Override this method to provide finer control over stonecutter behavior.
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
	@Override
    public boolean onBlockStonecut(World world, int x, int y, int z){
        int id = this.getItemIDDroppedOnStonecutter(world, x, y, z);

        if (id >= 0) {
            int count = this.getItemCountDroppedOnStonecutter(world, x, y, z);
            int meta = this.getItemDamageDroppedOnStonecutter(world, x, y, z);

            for (int i = 0; i < count; i++) {
                FCUtilsItem.EjectSingleItemWithRandomOffset(world, x, y, z, id, meta);
            }
            
            if (GetIsCracked(world.getBlockMetadata(x, y, z))) {
            	int stoneId = FCBetterThanWolves.fcItemStone.itemID;
            	
            	if (isCrackedSlate(world.getBlockMetadata(x, y, z))) {
            		stoneId = DecoDefs.slateStone.itemID;
            	}
            	
            	for (int i = 0; i < 4; i++) {
                    FCUtilsItem.EjectSingleItemWithRandomOffset(world, x, y, z, stoneId, meta);
                }
            }
        }
        else {
            if (!this.doesBlockDropAsItemOnStonecutter(world, x, y, z)) {
                return false;
            }

            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        }

        world.setBlockToAir(x, y, z);
        return true;
    }

	@Override
    public int getItemIDDroppedOnStonecutter(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        
        if (isSlate(meta) || isCrackedSlate(meta)) {
        	return DecoDefs.slateSidingAndCorner.blockID;
        }
        else {
        	return FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner.blockID;
        }
    }

	@Override
    public int getItemCountDroppedOnStonecutter(World world, int x, int y, int z) {
		if (GetIsCracked(world.getBlockMetadata(x, y, z)))
			return 1;
		else
			return 2;
    }

	@Override
    public int getItemDamageDroppedOnStonecutter(World world, int x, int y, int z)  {
        return 0;
    }

	//----------- Client Side Functionality -----------//

	private Icon[] iconByMetadataArray = new Icon[16];
	private Icon slateTopIcon;

	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		list.add(new ItemStack(blockID, 1, 0));
		list.add(new ItemStack(blockID, 1, 1));
		list.add(new ItemStack(blockID, 1, 2));
		list.add(new ItemStack(blockID, 1, 3));
		//list.add(new ItemStack(iBlockID, 1, 4));
		//list.add(new ItemStack(iBlockID, 1, 5));
		//list.add(new ItemStack(iBlockID, 1, 6));
		//list.add(new ItemStack(iBlockID, 1, 7));
	}

	@Override
	public void registerIcons(IconRegister register) {
		super.registerIcons(register);

		iconByMetadataArray[0] = blockIcon;		
		iconByMetadataArray[1] = register.registerIcon("fcBlockStone_1");
		iconByMetadataArray[2] = register.registerIcon("fcBlockStone_2");
		iconByMetadataArray[3] = register.registerIcon("decoBlockSlate_side");

		iconByMetadataArray[4] = register.registerIcon("fcBlockStone_cracked");
		iconByMetadataArray[5] = register.registerIcon("fcBlockStone_1_cracked");
		iconByMetadataArray[6] = register.registerIcon("fcBlockStone_2_cracked");
		iconByMetadataArray[7] = register.registerIcon("decoBlockSlate_cracked");

		for (int i = 8; i < 16; i++) {
			iconByMetadataArray[i] = blockIcon;
		}

		slateTopIcon = register.registerIcon("decoBlockSlate_top");
	}

	@Override
	public Icon getIcon(int side, int meta) {
		if (isSlate(meta)) {
			if (meta == 3 && (side == 0 || side == 1) ||
					meta == 8 && (side == 4 || side == 5) ||
					meta == 9 && (side == 2 || side == 3)) {
				return slateTopIcon;
			}
			else {
				return iconByMetadataArray[3];
			}
		}
		else {
			return iconByMetadataArray[meta];
		}
	}

	@Override
	public boolean RenderBlock(RenderBlocks render, int x, int y, int z) {
		int meta = render.blockAccess.getBlockMetadata(x, y, z);

		if (isSlate(meta)) {
			if (meta == 8) {
				render.SetUvRotateTop(1);
				render.SetUvRotateBottom(1);
				render.SetUvRotateWest(1);
				render.SetUvRotateEast(1);
			}
			else if (meta == 9) {
				render.SetUvRotateNorth(1);
				render.SetUvRotateSouth(1);
			}
		}

		render.setRenderBounds(0D,0D,0D,1D,1D,1D);
		render.renderStandardBlock(this, x, y, z);
		render.ClearUvRotation();
		return true;
	}
}