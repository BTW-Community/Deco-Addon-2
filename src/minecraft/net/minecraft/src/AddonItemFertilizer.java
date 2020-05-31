package net.minecraft.src;

public class AddonItemFertilizer extends Item
{
	AddonItemFertilizer(int ID)
	{
		super(ID);
		setUnlocalizedName("ginger_fertilizer");
		setCreativeTab(CreativeTabs.tabMaterials);
		SetFilterableProperties(8);
		SetBellowsBlowDistance(FCBetterThanWolves.fcItemCoalDust.GetBellowsBlowDistance(0));
		AddonManager.Name(this, "Fertilizer");
	}
	@Override public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int var7, float hitX, float hitY, float hitZ)
	{
		//return var2 != null && !var2.canPlayerEdit(var4, var5, var6, var7, var1) ? false : this.ApplyBoneMeal(var3, var4, var5, var6);
		if (player != null && player.canPlayerEdit(x, y, z, var7, stack)) {
			int id = world.getBlockId(x, y, z);

			if ((id == Block.tilledField.blockID || id == FCBetterThanWolves.fcBlockFarmland.blockID || id == FCBetterThanWolves.fcBlockPlanterSoil.blockID) && this.ApplyBoneMeal(world, x, y, z)) {
				--stack.stackSize;
				return true;
			}
			else if (id == Block.grass.blockID && this.GrowTallGrassAndFlowers(world, x, y, z)) {
				--stack.stackSize;
				return true;
			}
		}

		return false;
	}
	private boolean ApplyBoneMeal(World var1, int var2, int var3, int var4)
	{
		Block var5 = Block.blocksList[var1.getBlockId(var2, var3, var4)];
		return var5 != null && var5.AttemptToApplyFertilizerTo(var1, var2, var3, var4);
	}
	public boolean GrowTallGrassAndFlowers(World world, int X, int Y, int Z)
	{
		if (!world.isRemote) {
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
						if (world.getBlockId(NewX, NewY - 1, NewZ) == Block.grass.blockID && !world.isBlockNormalCube(NewX, NewY, NewZ))
						{
							++SubIndex;
							continue;
						}
						Test = false;
					}
					if (Test && world.getBlockId(NewX, NewY, NewZ) == 0)
					{
						if (itemRand.nextInt(4) == 0 && Block.tallGrass.canBlockStay(world, NewX, NewY, NewZ))
							world.setBlockAndMetadataWithNotify(NewX, NewY, NewZ, Block.tallGrass.blockID, 1);
						else if (itemRand.nextInt(3) == 0 && Block.plantYellow.canBlockStay(world, NewX, NewY, NewZ))
						{
							int R = itemRand.nextInt(22);
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
								world.setBlockAndMetadataWithNotify(NewX, NewY, NewZ, AddonDefs.flower.blockID, R);
								break;
							case 15:
								world.setBlockWithNotify(NewX, NewY, NewZ, Block.plantYellow.blockID);
								break;
							case 16:
								world.setBlockWithNotify(NewX, NewY, NewZ, Block.plantRed.blockID);
								break;
							case 17:
							case 18:
							case 19:
							case 20:
								world.setBlockAndMetadataWithNotify(NewX, NewY, NewZ, AddonDefs.tulip.blockID, R-17);
								break;
							case 21:
								world.setBlockAndMetadataWithNotify(NewX, NewY, NewZ, AddonDefs.flower2.blockID, R-21);
								break;

							}
						}
					}
					++Index;
					break;
				}
			}
		}

		return true;
	}
}