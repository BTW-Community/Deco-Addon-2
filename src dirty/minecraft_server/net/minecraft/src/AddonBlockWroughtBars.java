package net.minecraft.src;

public class AddonBlockWroughtBars extends AddonBlockPane
{
	public AddonBlockWroughtBars(int ID)
	{
		super(ID, "ginger_panel_wroughtbars_face", "ginger_panel_wroughtbars_side", Material.iron, true);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(Block.soundMetalFootstep);
		setUnlocalizedName("fenceSteel");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
}