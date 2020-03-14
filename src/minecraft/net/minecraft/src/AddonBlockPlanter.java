package net.minecraft.src;

import java.util.Random;

public class AddonBlockPlanter extends FCBlockPlanter
{
	public AddonBlockPlanter(int ID)
	{
		super(ID);
		this.setHardness(0.6F);
		this.setStepSound(soundGlassFootstep);
		this.setUnlocalizedName("fcBlockPlanter");
		this.setTickRandomly(true);
		this.SetPicksEffectiveOn(true);
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override public void updateTick(World CurrentWorld, int X, int Y, int Z, Random itemRand)
	{
		int var6 = this.GetPlanterType(CurrentWorld, X, Y, Z);

		if (var6 == 9 || var6 == 11 || var6 == 13 || var6 == 15)
		{
			int GrowthState = this.GetGrassGrowthState(CurrentWorld, X, Y, Z);
			int var8 = 0;
			int var9;
			if (CurrentWorld.isAirBlock(X, Y + 1, Z) && CurrentWorld.getBlockLightValue(X, Y + 1, Z) >= 8)
			{
				var8 = GrowthState + 1;
				if (var8 > 3)
				{
					var8 = 0;
					int R = itemRand.nextInt(28);
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
							CurrentWorld.setBlockAndMetadataWithNotify(X, Y+1, Z, AddonDefs.flower.blockID, R);
							break;
						case 14:
							R = itemRand.nextInt(5);
							switch(R)
							{//RARE FLOWERS
								case 0:
									CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, AddonDefs.flower.blockID, 15);
									break;
								case 1:
									CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, AddonDefs.tulip.blockID, 4);
									break;
								case 2:
									CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, Block.tallGrass.blockID, 0);
									break;
								case 3:
									CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, Block.tallGrass.blockID, 2);
									break;
							}
							break;
						case 15:
							CurrentWorld.setBlockWithNotify(X, Y+1, Z, Block.plantYellow.blockID);
							break;
						case 16:
							CurrentWorld.setBlockWithNotify(X, Y+1, Z, Block.plantRed.blockID);
							break;
						case 17:
						case 18:
						case 19:
						case 20:
							CurrentWorld.setBlockAndMetadataWithNotify(X, Y+1, Z, AddonDefs.tulip.blockID, R-17);
							break;
						default:
							CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, Block.tallGrass.blockID, 1);
					}
				}
			}
			if (CurrentWorld.getBlockLightValue(X, Y + 1, Z) >= 9)
			{
				for (var9 = 0; var9 < 4; ++var9)
				{
					int CurrentWorld0 = X + itemRand.nextInt(3) - 1;
					int CurrentWorld1 = Y + itemRand.nextInt(5) - 3;
					int CurrentWorld2 = Z + itemRand.nextInt(3) - 1;
					int CurrentWorld3 = CurrentWorld.getBlockId(CurrentWorld0, CurrentWorld1 + 1, CurrentWorld2);

					if (CurrentWorld.getBlockId(CurrentWorld0, CurrentWorld1, CurrentWorld2) == Block.dirt.blockID && CurrentWorld.getBlockLightValue(CurrentWorld0, CurrentWorld1 + 1, CurrentWorld2) >= 4 && Block.lightOpacity[CurrentWorld3] <= 2)CurrentWorld.setBlockWithNotify(CurrentWorld0, CurrentWorld1, CurrentWorld2, Block.grass.blockID);
				}
			}
			if (var8 != GrowthState)
				this.SetGrassGrowthState(CurrentWorld, X, Y, Z, var8);
		}
	}
}