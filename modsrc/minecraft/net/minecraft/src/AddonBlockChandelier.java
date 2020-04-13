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
//CLIENT ONLY
	@Override public void randomDisplayTick(World CurrentWorld, int X_, int Y_, int Z_, Random par5Random)
	{
		float H = .55F, L = .15F, X = (float)X_, Y = (float)Y_, Z = (float)Z_;

		CurrentWorld.spawnParticle("smoke", X+L, Y+H, Z+L, 0.0D, 0.0D, 0.0D);
		CurrentWorld.spawnParticle("flame", X+L, Y+H, Z+L, 0.0D, 0.0D, 0.0D);

		CurrentWorld.spawnParticle("smoke", X+1F-L, Y+H, Z+L, 0.0D, 0.0D, 0.0D);
		CurrentWorld.spawnParticle("flame", X+1F-L, Y+H, Z+L, 0.0D, 0.0D, 0.0D);

		CurrentWorld.spawnParticle("smoke", X+L, Y+H, Z+1F-L, 0.0D, 0.0D, 0.0D);
		CurrentWorld.spawnParticle("flame", X+L, Y+H, Z+1F-L, 0.0D, 0.0D, 0.0D);

		CurrentWorld.spawnParticle("smoke", X+1F-L, Y+H, Z+1F-L, 0.0D, 0.0D, 0.0D);
		CurrentWorld.spawnParticle("flame", X+1F-L, Y+H, Z+1F-L, 0.0D, 0.0D, 0.0D);
	}
	@Override public boolean RenderBlock(RenderBlocks Render, int X, int Y, int Z)
	{
		Render.setRenderBounds(.375D, .875D, .375D, .625D, 1D, .625D);
		Render.renderStandardBlock(Block.stone, X, Y, Z);
		Render.renderCrossedSquares(this, X, Y, Z);
		return true;
	}
//
}