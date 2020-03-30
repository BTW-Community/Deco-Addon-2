package net.minecraft.src;

import java.util.ArrayList;

public class AddonBlockSidingAndCornerDecorativeWall extends AddonBlockSidingAndCornerAndDecorative
{
	static ArrayList<AddonBlockSidingAndCornerDecorativeWall> wallBlocks = new ArrayList<AddonBlockSidingAndCornerDecorativeWall>();
	public AddonBlockSidingAndCornerDecorativeWall(int var1, Material var2, String var3, float var4, float var5, StepSound var6, String var7, String originalName)
	{
		super(var1, var2, var3, var4, var5, var6, var7);
		setCreativeTab(CreativeTabs.tabDecorations);
		AddonManager.Name(getUnlocalizedName() + ".fence" + ".name", originalName + " Wall");
		wallBlocks.add(this);
	}
	
	@Override public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		if(par1IBlockAccess.getBlockMetadata(par2,par3,par4) != 14)
		{
			super.setBlockBoundsBasedOnState(par1IBlockAccess, par2, par3, par4);
			return;
		}

		boolean var5 = this.DoesFenceConnectTo(par1IBlockAccess, par2, par3, par4 - 1);
		boolean var6 = this.DoesFenceConnectTo(par1IBlockAccess, par2, par3, par4 + 1);
		boolean var7 = this.DoesFenceConnectTo(par1IBlockAccess, par2 - 1, par3, par4);
		boolean var8 = this.DoesFenceConnectTo(par1IBlockAccess, par2 + 1, par3, par4);
		float var9 = 0.25F;
		float var10 = 0.75F;
		float var11 = 0.25F;
		float var12 = 0.75F;
		float var13 = 1.0F;

		if (var5)
		{
			var11 = 0.0F;
		}

		if (var6)
		{
			var12 = 1.0F;
		}

		if (var7)
		{
			var9 = 0.0F;
		}

		if (var8)
		{
			var10 = 1.0F;
		}

		if (var5 && var6 && !var7 && !var8)
		{
			var13 = 0.8125F;
			var9 = 0.3125F;
			var10 = 0.6875F;
		}
		else if (!var5 && !var6 && var7 && var8)
		{
			var13 = 0.8125F;
			var11 = 0.3125F;
			var12 = 0.6875F;
		}

		this.setBlockBounds(var9, 0.0F, var11, var10, var13, var12);
	}

//CLIENT ONLY
	@Override public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
	{
		IBlockAccess var5 = var1.blockAccess;
		int var6 = var5.getBlockMetadata(var2, var3, var4);

		switch(var6)
		{
		case 12:
			return RenderBench(var1, var5, var2, var3, var4, this);
		case 14:
			return RenderFence(var1, var5, var2, var3, var4, this);
		default:
			return super.RenderBlock(var1, var2, var3, var4);
		}

		//return var6 == 12 ? RenderBench(var1, var5, var2, var3, var4, this) : (var6 == 14 ? RenderFence(var1, var5, var2, var3, var4, this) : super.RenderBlock(var1, var2, var3, var4));
	}
	public static boolean RenderFence(RenderBlocks Render, IBlockAccess blockAccess, int X, int Y, int Z, Block block)
	{
		FCBlockSidingAndCornerAndDecorative me = (FCBlockSidingAndCornerAndDecorative)block;

		boolean var5 = me.DoesFenceConnectTo(blockAccess, X - 1, Y, Z);
		boolean var6 = me.DoesFenceConnectTo(blockAccess, X + 1, Y, Z);
		boolean var7 = me.DoesFenceConnectTo(blockAccess, X, Y, Z - 1);
		boolean var8 = me.DoesFenceConnectTo(blockAccess, X, Y, Z + 1);
		boolean var9 = var7 && var8 && !var5 && !var6;
		boolean var10 = !var7 && !var8 && var5 && var6;
		boolean var11 = blockAccess.isAirBlock(X, Y + 1, Z);
		boolean top = !var11 && me.DoesFenceConnectTo(blockAccess, X, Y + 1, Z);
		if ((var9 || var10) && var11)
		{
			if (var9)
			{
				Render.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 1.0D);
				Render.renderStandardBlock(me, X, Y, Z);
			}
			else
			{
				Render.setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
				Render.renderStandardBlock(me, X, Y, Z);
			}
		}
		else if(((var5 && var6)||(var7 && var8)) && top)
		{
			if(var5 && var6)
			{
				Render.setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 1.0D, 0.6875D);
				Render.renderStandardBlock(me, X, Y, Z);
			}

			if (var7 && var8)
			{
				Render.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 1.0D, 1.0D);
				Render.renderStandardBlock(me, X, Y, Z);
			}
		}
		else
		{
			Render.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
			Render.renderStandardBlock(me, X, Y, Z);
			double height = (top?1.0D:0.8125D);

			if (var5)
			{
				Render.setRenderBounds(0.0D, 0.0D, 0.3125D, 0.25D, height, 0.6875D);
				Render.renderStandardBlock(me, X, Y, Z);
			}

			if (var6)
			{
				Render.setRenderBounds(0.75D, 0.0D, 0.3125D, 1.0D, height, 0.6875D);
				Render.renderStandardBlock(me, X, Y, Z);
			}

			if (var7)
			{
				Render.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, height, 0.25D);
				Render.renderStandardBlock(me, X, Y, Z);
			}

			if (var8)
			{
				Render.setRenderBounds(0.3125D, 0.0D, 0.75D, 0.6875D, height, 1.0D);
				Render.renderStandardBlock(me, X, Y, Z);
			}
		}
		me.setBlockBoundsBasedOnState(Render.blockAccess, X, Y, Z);
		return true;
	}
	//
}