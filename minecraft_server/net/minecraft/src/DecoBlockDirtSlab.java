package net.minecraft.src;

import java.util.Random;

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
}