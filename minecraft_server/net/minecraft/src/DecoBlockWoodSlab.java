package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockWoodSlab extends DecoBlockSlabBase
{
	/** The list of the types of step blocks. */
	public final int[] sawedIDs;
	public final int[] sawedMetas;
	private Icon theIcon;

	public DecoBlockWoodSlab(int par1, Block[] blocks, int[] metas, int[] sawedIDs, int[] sawedMetas)
	{
		super(par1, FCBetterThanWolves.fcMaterialPlanks, blocks, metas);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.SetAxesEffectiveOn();
		this.setStepSound(Block.soundWoodFootstep);
		this.SetFireProperties(FCEnumFlammability.PLANKS);
		this.SetBuoyant();
		this.sawedIDs = sawedIDs;
		this.sawedMetas = sawedMetas;
	}

	public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4) {
		return 2;
	}
}