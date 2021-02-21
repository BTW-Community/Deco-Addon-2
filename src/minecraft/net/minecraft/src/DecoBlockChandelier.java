package net.minecraft.src;

import java.util.Random;

public class DecoBlockChandelier extends Block
{
	public DecoBlockChandelier(int ID)
	{
		super(ID, Material.iron);
		setUnlocalizedName("decoBlockChandelier");
		setStepSound(soundMetalFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(0.3F);
		setLightValue(1F);
		DecoManager.Register(this, "Chandelier");
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