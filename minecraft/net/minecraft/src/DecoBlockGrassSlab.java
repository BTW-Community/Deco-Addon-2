package net.minecraft.src;

import java.util.Random;

import com.prupe.mcpatcher.mal.block.RenderBlocksUtils;

public class DecoBlockGrassSlab extends FCBlockGrassSlab {
	protected DecoBlockGrassSlab(int blockID) {
		super(blockID);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!DecoBlockGrass.canGrassSurviveAtLocation(world, x, y, z)) {
			// convert back to dirt in low light
			this.revertToDirt(world, x, y, z);
		}
		else if (DecoBlockGrass.canGrassSpreadFromLocation(world, x, y, z)) {
			DecoBlockGrass.checkForGrassSpreadFromLocation(world, x, y, z);

			if (isSparse(world, x, y, z) && rand.nextInt(4) == 0) {
				this.setFullyGrown(world, x, y, z);
			}
		}
	}
	
	@Override
	public boolean CanBeGrazedOn(IBlockAccess blockAccess, int x, int y, int z, EntityAnimal animal) {
    	World world = (World) blockAccess;
    	int skylight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
    	
        if (skylight >= DecoBlockGrass.EDIBLE_LIGHT_LEVEL) {
        	return super.CanBeGrazedOn(blockAccess, x, y, z, animal);
        }
        else {
        	return false;
        }
    }
    
    //----------- Client Side Functionality -----------//
    
    private Icon iconGrassTop;
	private Icon iconGrassTopSparse;
    private Icon iconGrassTopRough;
    
    public void registerIcons(IconRegister register) {
    	super.registerIcons(register);
    	
        this.iconGrassTop = register.registerIcon("grass_top");
		this.iconGrassTopSparse = register.registerIcon("fcBlockGrassSparse");
        this.iconGrassTopRough = register.registerIcon("decoBlockGrassRough");
    }

	public Icon getBlockTextureSecondPass(IBlockAccess blockAccess, int x, int y, int z, int side) {
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

		if (isSparse(blockAccess, x, y, z)) {
			topIcon = iconGrassTopSparse;
		}
		else if (skylight >= DecoBlockGrass.EDIBLE_LIGHT_LEVEL) {
			topIcon = iconGrassTop;
		}
		else {
    		topIcon = iconGrassTopRough;
    	}

		Icon betterGrassIcon = RenderBlocksUtils.getGrassTexture(this, blockAccess, x, y, z, side, topIcon);

		if (betterGrassIcon != null) {
			return betterGrassIcon;
		}
		else if (side == 1) {
			return topIcon;
		}
		else {
			return super.getBlockTextureSecondPass(blockAccess, x, y, z, side);
		}
	}
}