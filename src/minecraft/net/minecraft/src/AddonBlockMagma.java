package net.minecraft.src;

import java.util.Random;

public class AddonBlockMagma extends Block {
	public AddonBlockMagma(int id) {
		super(id, FCBetterThanWolves.fcMaterialNetherRock);
        this.setHardness(0.6F);
        this.setResistance(0.6666667F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("ginger_magma");
	}

   public void onBlockAdded(World world, int x, int y, int z) {
    	if (this.HasWaterToSidesOrTop(world, x, y, z)) {
			world.setBlockWithNotify(x, y, z, AddonDefs.basalt.blockID);
	        world.playAuxSFX(2227, x, y, z, 0);
    	}
    }
   
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
    	if (this.HasWaterToSidesOrTop(world, x, y, z)) {
			world.setBlockWithNotify(x, y, z, AddonDefs.basalt.blockID);
	        world.playAuxSFX(2227, x, y, z, 0);
    	}
    }
}
