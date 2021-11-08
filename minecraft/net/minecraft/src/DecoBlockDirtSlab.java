package net.minecraft.src;

import java.util.Random;

import com.prupe.mcpatcher.mal.block.RenderBlocksUtils;

public class DecoBlockDirtSlab extends FCBlockDirtSlab {
	public DecoBlockDirtSlab(int id) {
		super(id);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int subType = GetSubtype(world, x, y, z);

		if (subType == m_iSubtypeGrass) {
			int blockAboveMaxNaturalLight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
			int blockAboveCurrentNaturalLight = blockAboveMaxNaturalLight - world.skylightSubtracted;

			boolean isUpsideDown = GetIsUpsideDown(world, x, y, z);
			int blockAboveID = world.getBlockId(x, y + 1, z);
			Block blockAbove = Block.blocksList[blockAboveID];

			int blockAboveLight = world.getBlockLightValue(x, y + 1, z);

			if ((blockAbove != null && !blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, !isUpsideDown)) || 
					blockAboveMaxNaturalLight < DecoBlockGrass.surviveMinimumLightLevel || 
					Block.lightOpacity[world.getBlockId(x, y + 1, z)] > 2)
			{
				SetSubtype(world, x, y, z, m_iSubtypeDirt);
			}
			else if (blockAboveCurrentNaturalLight >= DecoBlockGrass.spreadFromLightLevel || 
					blockAboveLight >= DecoBlockGrass.spreadFromLightLevel)
			{
				DecoBlockGrass.checkForGrassSpreadFromLocation(world, x, y, z);
			}
		}
	}

	@Override
	public boolean CanBeGrazedOn(IBlockAccess blockAccess, int x, int y, int z, EntityAnimal animal) {
		World world = (World) blockAccess;
		int skylight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);

		return this.GetSubtype(blockAccess.getBlockMetadata(x, y, z)) == this.m_iSubtypeGrass && 
				skylight >= DecoBlockGrass.edibleMinimumLightLevel;
	}

	//----------- Client Side Functionality -----------//

	private Icon iconGrassTop;
	private Icon iconGrassTopRough;

	public void registerIcons(IconRegister register) {
		super.registerIcons(register);

		this.iconGrassTop = register.registerIcon("grass_top");
		this.iconGrassTopRough = register.registerIcon("decoBlockGrassRough");
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
		if (this.GetSubtype(blockAccess.getBlockMetadata(x, y, z)) == this.m_iSubtypeGrass) {
			World world = null;

			if (blockAccess instanceof ChunkCache) {
				ChunkCache chunkCache = (ChunkCache) blockAccess;
				world = chunkCache.worldObj;
			}
			else if (blockAccess instanceof World) {
				world = (World) blockAccess;
			}

			int skylight;

			if (world != null)
				skylight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
			else
				skylight = 9;

			Icon topIcon;

			if (skylight >= DecoBlockGrass.edibleMinimumLightLevel) {
				topIcon = this.iconGrassTop;
			}
			else {
				topIcon = this.iconGrassTopRough;
			}

			Icon betterGrassIcon = RenderBlocksUtils.getGrassTexture(Block.grass, blockAccess, x, y, z, side, topIcon);

			if (betterGrassIcon != null) {
				return betterGrassIcon;
			}
			else if (side == 1) {
				return topIcon;
			}
			else {
				return super.getBlockTexture(blockAccess, x, y, z, side);
			}
		}
		else {
			return super.getBlockTexture(blockAccess, x, y, z, side);
		}
	}
}