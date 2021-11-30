package net.minecraft.src;

import java.util.Random;

public class DecoBlockDirtSlab extends FCBlockDirtSlab {
	public DecoBlockDirtSlab(int id) {
		super(id);
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