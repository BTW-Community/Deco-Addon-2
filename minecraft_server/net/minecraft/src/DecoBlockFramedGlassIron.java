package net.minecraft.src;

public class DecoBlockFramedGlassIron extends FCBlockGlass {
	public DecoBlockFramedGlassIron(int id) {
		super(id, Material.glass, false);
        this.setHardness(0.3F);
        this.setStepSound(Block.soundGlassFootstep);
        this.setUnlocalizedName("decoBlockGlassFramedIron");
        this.SetPicksEffectiveOn(false);
        this.SetAxesEffectiveOn();
        this.setCreativeTab(CreativeTabs.tabDecorations);
	}
}