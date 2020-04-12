package net.minecraft.src;

import java.util.Random;

public class AddonBlockChandelier extends Block
{
	public AddonBlockChandelier(int ID)
	{
		super(ID, Material.iron);
		setUnlocalizedName("ginger_lantern_gold");
		setStepSound(soundStoneFootstep);
		//setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(0.3F);
		setLightValue(1F);
		AddonManager.Register(this, "Chandelier");
		this.SetPicksEffectiveOn(true);
		this.InitBlockBounds(.3125D,	0.0D,	.3125D,		.6875D,		.5D,	.6875D);
	}
	@Override public boolean isOpaqueCube()
	{
		return false;
	}
	@Override public boolean renderAsNormalBlock()
	{
		return false;
	}
	@Override public int getRenderType()
	{
		return 1;
	}
}