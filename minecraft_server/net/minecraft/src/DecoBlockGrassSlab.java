package net.minecraft.src;

import java.util.Random;

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

			if (isSparse(world, x, y, z) && rand.nextInt(DecoBlockGrass.SELF_GROWTH_CHANCE) == 0) {
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
}