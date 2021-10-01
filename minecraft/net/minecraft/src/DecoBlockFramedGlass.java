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
	
	//CLIENT ONLY
	private Icon[] sideTextures = new Icon[7];
	
	@Override
	public Icon getIcon(int side, int meta) {
		return sideTextures[meta];
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		sideTextures[0] = register.registerIcon(textureBase + "Oak");
		sideTextures[1] = register.registerIcon(textureBase + "Spruce");
		sideTextures[2] = register.registerIcon(textureBase + "Birch");
		sideTextures[3] = register.registerIcon(textureBase + "Jungle");
		sideTextures[4] = register.registerIcon(textureBase + "Blood");
		sideTextures[5] = register.registerIcon(textureBase + "Cherry");
		sideTextures[6] = register.registerIcon(textureBase + "Acacia");
	}
}