package net.minecraft.src;

import java.util.Random;

public class AddonBlockChandelier extends Block
{
	public AddonBlockChandelier(int ID)
	{
		super(ID, Material.iron);
		setUnlocalizedName("ginger_lantern_gold");
		setStepSound(soundMetalFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(0.3F);
		setLightValue(1F);
		AddonManager.Register(this, "Chandelier");
		this.SetPicksEffectiveOn(true);
		this.InitBlockBounds(.25D,	0.125D,	.25D,		.75D,		1.0D,	.75D);
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

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }
}