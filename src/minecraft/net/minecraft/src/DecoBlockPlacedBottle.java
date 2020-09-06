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
        this.setUnlocalizedName("ginger_placedBottle");
	}
	@Override public boolean isOpaqueCube()
	{
		return false;
	}
	@Override public boolean renderAsNormalBlock()
	{
		return false;
	}


	//CLIENT ONLY
    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        FCModelBlock var6 = this.blockModel.MakeTemporaryCopy();
        return var6.RenderAsBlock(var1, this, var2, var3, var4);
    }
}
