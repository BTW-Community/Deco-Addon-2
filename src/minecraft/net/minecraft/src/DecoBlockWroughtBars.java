package net.minecraft.src;

public class DecoBlockWroughtBars extends DecoBlockPane
{
	public DecoBlockWroughtBars(int ID)
	{
		super(ID, "decoBlockWroughtIronBars", "decoBlockWroughtIronBars_top", Material.iron, true);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(Block.soundMetalFootstep);
		setUnlocalizedName("decoBlockWroughtIronBars");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
}