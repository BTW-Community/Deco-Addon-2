package net.minecraft.src;

public class AddonItemFertilizer extends Item
{
	AddonItemFertilizer(int ID)
	{
		super(ID);
		setUnlocalizedName("ginger_fertilizer");
		setCreativeTab(CreativeTabs.tabMaterials);
		SetBellowsBlowDistance(FCBetterThanWolves.fcItemCoalDust.GetBellowsBlowDistance(0));
		AddonManager.Name(this, "Fertilizer");
	}
	@Override public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
	{
		return var2 != null && !var2.canPlayerEdit(var4, var5, var6, var7, var1) ? false : this.ApplyBoneMeal(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
	}
	private boolean CanBonemealBeAppliedToBlock(World var1, int var2, int var3, int var4)
	{
		int var5 = var1.getBlockId(var2, var3, var4);
		int var6 = var1.getBlockMetadata(var2, var3, var4);
		return var5 == Block.tilledField.blockID || var5 == FCBetterThanWolves.fcPlanter.blockID && var6 == 1 || var5 == Block.grass.blockID;
	}
	private boolean ApplyBoneMeal(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
	{
		if (!this.CanBonemealBeAppliedToBlock(var3, var4, var5, var6))
			--var5;
		if (this.CanBonemealBeAppliedToBlock(var3, var4, var5, var6))
		{
			int var11 = var3.getBlockId(var4, var5, var6);
			if (var11 == Block.tilledField.blockID)
			{
				int var12 = var3.getBlockMetadata(var4, var5, var6);
				var3.setBlockAndMetadataWithNotify(var4, var5, var6, FCBetterThanWolves.fcBlockFarmlandFertilized.blockID, var12);
			}
			else if (var11 == FCBetterThanWolves.fcPlanter.blockID)
			{
				((FCBlockPlanter) ((FCBlockPlanter) FCBetterThanWolves.fcPlanter)).SetPlanterType(var3, var4, var5, var6, 2);
			}
			else if (var11 == Block.grass.blockID)
			{
				if (var3.provider.dimensionId == 1)
					return false;
				if (!var3.isRemote)
					this.GrowTallGrassAndFlowers(var3, var4, var5, var6);
			}
			--var1.stackSize;
			return true;
		}
		else return false;
	}
	public void GrowTallGrassAndFlowers(World CurrentWorld, int X, int Y, int Z)
	{
		int Index = 0;
		while (Index < 128)
		{
			int NewX = X;
			int NewY = Y + 1;
			int NewZ = Z;
			boolean Test = true;
			int SubIndex = 0;
			while (true)
			{
				if (SubIndex < Index / 16)
				{
					NewX += itemRand.nextInt(3) - 1;
					NewY += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
					NewZ += itemRand.nextInt(3) - 1;
					if (CurrentWorld.getBlockId(NewX, NewY - 1, NewZ) == Block.grass.blockID && !CurrentWorld.isBlockNormalCube(NewX, NewY, NewZ))
					{
						++SubIndex;
						continue;
					}
						Test = false;
				}
				if (Test && CurrentWorld.getBlockId(NewX, NewY, NewZ) == 0)
				{
					if (itemRand.nextInt(4) == 0 && Block.tallGrass.canBlockStay(CurrentWorld, NewX, NewY, NewZ))
						CurrentWorld.setBlockAndMetadataWithNotify(NewX, NewY, NewZ, Block.tallGrass.blockID, 1);
					else if (itemRand.nextInt(3) == 0 && Block.plantYellow.canBlockStay(CurrentWorld, NewX, NewY, NewZ))
					{
						int R = itemRand.nextInt(21);
						switch (R)
						{
							case 0:
							case 1:
							case 2:
							case 3:
							case 4:
							case 5:
							case 6:
							case 7:
							case 8:
							case 9:
							case 10:
							case 11:
							case 12:
							case 13:
							case 14:
								CurrentWorld.setBlockAndMetadataWithNotify(NewX, NewY, NewZ, AddonDefs.flower.blockID, R);
								break;
							case 15:
								CurrentWorld.setBlockWithNotify(NewX, NewY, NewZ, Block.plantYellow.blockID);
								break;
							case 16:
								CurrentWorld.setBlockWithNotify(NewX, NewY, NewZ, Block.plantRed.blockID);
								break;
							case 17:
							case 18:
							case 19:
							case 20:
								CurrentWorld.setBlockAndMetadataWithNotify(NewX, NewY, NewZ, AddonDefs.tulip.blockID, R-17);
								break;
						}
					}
					//else if (decoSapling.canBlockStay(CurrentWorld, NewX, NewY, NewZ))
					//CurrentWorld.setBlockWithNotify(NewX, NewY, NewZ, decoSapling.blockID);
				}
				++Index;
				break;
			}
		}
	}
}