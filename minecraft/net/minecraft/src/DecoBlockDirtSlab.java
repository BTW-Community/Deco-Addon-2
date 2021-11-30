package net.minecraft.src;

import java.util.Random;

import com.prupe.mcpatcher.mal.block.RenderBlocksUtils;

public class DecoBlockDirtSlab extends FCBlockDirtSlab {
	public DecoBlockDirtSlab(int id) {
		super(id);
	}
	
	@Override
	public boolean CanBeGrazedOn(IBlockAccess blockAccess, int x, int y, int z, EntityAnimal animal) {
    	World world = (World) blockAccess;
    	int skylight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
    	
        if (skylight >= DecoBlockGrass.edibleMinimumLightLevel) {
        	return super.CanBeGrazedOn(blockAccess, x, y, z, animal);
        }
        else {
        	return false;
        }
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