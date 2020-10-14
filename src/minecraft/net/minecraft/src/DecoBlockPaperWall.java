package net.minecraft.src;

public class DecoBlockPaperWall extends DecoBlockPane
{
	public DecoBlockPaperWall(int ID)
	{
		super(ID, "decoBlockPaperWall_top", "decoBlockPaperWall", Material.wood, true);
		setHardness(0.3F);
		setResistance(1.0F);
		setStepSound(Block.soundWoodFootstep);
		setUnlocalizedName("decoBlockPaperWall");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
}