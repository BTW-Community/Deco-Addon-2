package net.minecraft.src;

import java.util.Random;

public class DecoBlockFramedGlass extends FCBlockGlass {
	public final String textureBase;

	public DecoBlockFramedGlass(int id, String textureBase) {
		super(id, Material.glass, false);
		this.setHardness(0.3F);
		this.setStepSound(Block.soundGlassFootstep);
		this.setUnlocalizedName(textureBase);
		this.SetPicksEffectiveOn(false);
		this.SetAxesEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabDecorations);

		this.textureBase = textureBase;
	}
}