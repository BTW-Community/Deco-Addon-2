package net.minecraft.src;

public class DecoBlockPlacedBottle extends Block {
    private final FCModelBlock blockModel = new DecoModelBlockPlacedBottle();

	public DecoBlockPlacedBottle(int id) {
		super(id, Material.glass);
        this.setHardness(0.3F);
        this.SetPicksEffectiveOn(true);
        this.SetChiselsEffectiveOn(true);
        this.setLightOpacity(0);
        this.setStepSound(soundGlassFootstep);
        this.InitBlockBounds(.34375, 0, .34375, .65625, .5, .65625);
        this.setUnlocalizedName("decoBlockPlacedBottle");
	}
	@Override public boolean isOpaqueCube()
	{
		return false;
	}
	@Override public boolean renderAsNormalBlock()
	{
		return false;
	}
}
