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
}