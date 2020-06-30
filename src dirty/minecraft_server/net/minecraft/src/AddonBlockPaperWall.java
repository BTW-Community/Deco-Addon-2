package net.minecraft.src;

public class AddonBlockPaperWall extends AddonBlockPane
{
	public AddonBlockPaperWall(int ID)
	{
		super(ID, "ginger_panel_paperwall_face", "ginger_panel_paperwall_side", Material.wood, true);
		setHardness(0.3F);
		setResistance(1.0F);
		setStepSound(Block.soundWoodFootstep);
		setUnlocalizedName("paperWall");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
}