package net.minecraft.src;

import java.util.Random;

public class AddonBlockMagma extends Block {
	public AddonBlockMagma(int id) {
		super(id, FCBetterThanWolves.fcMaterialNetherRock);
        this.setHardness(0.6F);
        this.setResistance(0.6666667F);
        this.SetPicksEffectiveOn();
		setLightValue(.375F);
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
	
	//CLIENT ONLY
	private Icon overlay;
	
	public void registerIcons(IconRegister register) {
		super.registerIcons(register);
		overlay = register.registerIcon("ginger_overlay_magma");
	}
	
	public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        FCClientUtilsRender.RenderBlockFullBrightWithTexture(var1, var1.blockAccess, var2, var3, var4, this.overlay);
    }
}
