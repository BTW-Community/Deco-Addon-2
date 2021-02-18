package net.minecraft.src;

import java.util.Random;

public class DecoUtilsTrees {
	public static boolean generateCherry(World var0, Random var1, int var2, int var3, int var4) {
		int var5 = 5;
		int var6 = 0;
		int var7 = 0;
		boolean var8 = false;
		
		int var9 = var1.nextInt(3) + var5;
		boolean var10 = true;

		if (var3 >= 1 && var3 + var9 + 1 <= 256)
		{
			int var11;
			byte var12;
			int var14;
			int var15;

			for (var11 = var3; var11 <= var3 + 1 + var9; ++var11)
			{
				var12 = 1;

				if (var11 == var3)
				{
					var12 = 0;
				}

				if (var11 >= var3 + 1 + var9 - 2)
				{
					var12 = 2;
				}

				for (int var13 = var2 - var12; var13 <= var2 + var12 && var10; ++var13)
				{
					for (var14 = var4 - var12; var14 <= var4 + var12 && var10; ++var14)
					{
						if (var11 >= 0 && var11 < 256)
						{
							var15 = var0.getBlockId(var13, var11, var14);

							if (!var0.isAirBlock(var13, var11, var14) && var15 != Block.leaves.blockID && var15 != Block.grass.blockID && var15 != Block.dirt.blockID && var15 != Block.wood.blockID && var15 != DecoDefs.cherryLog.blockID && var15 != DecoDefs.cherryLeaves.blockID)
							{
								var10 = false;
							}
						}
						else
						{
							var10 = false;
						}
					}
				}
			}

			if (!var10)
			{
				return false;
			}
			else
			{
				var11 = var0.getBlockId(var2, var3 - 1, var4);

				if (FCUtilsTrees.CanSaplingGrowOnBlock(var0, var2, var3 - 1, var4) && var3 < 256 - var9 - 1)
				{
					if (var11 == Block.grass.blockID)
					{
						var0.setBlockWithNotify(var2, var3 - 1, var4, Block.dirt.blockID);
					}

					var12 = 3;
					byte var21 = 0;
					int var16;
					int var17;
					int var18;

					for (var14 = var3 - var12 + var9; var14 <= var3 + var9; ++var14)
					{
						var15 = var14 - (var3 + var9);
						var16 = var21 + 1 - var15 / 2;

						for (var17 = var2 - var16; var17 <= var2 + var16; ++var17)
						{
							var18 = var17 - var2;

							for (int var19 = var4 - var16; var19 <= var4 + var16; ++var19)
							{
								int var20 = var19 - var4;

								if ((Math.abs(var18) != var16 || Math.abs(var20) != var16 || var1.nextInt(2) != 0 && var15 != 0) && !Block.opaqueCubeLookup[var0.getBlockId(var17, var14, var19)])
								{
									var0.setBlockAndMetadataWithNotify(var17, var14, var19, DecoDefs.cherryLeaves.blockID, var7);
								}
							}
						}
					}

					for (var14 = 0; var14 < var9; ++var14)
					{
						var15 = var0.getBlockId(var2, var3 + var14, var4);

						if (var0.isAirBlock(var2, var3 + var14, var4) || var15 == Block.leaves.blockID || var15 == DecoDefs.cherryLeaves.blockID)
						{
							var0.setBlockAndMetadataWithNotify(var2, var3 + var14, var4, DecoDefs.cherryLog.blockID, var6);
						}
					}

					if (var9 > 2)
					{
						var14 = var0.getBlockId(var2, var3, var4);

						if (var14 == DecoDefs.cherryLog.blockID)
						{
							var15 = var0.getBlockMetadata(var2, var3, var4);

							if (var15 == var6)
							{
								var0.setBlock(var2, var3, var4, DecoDefs.cherryStump.blockID);
							}
						}
					}

					return true;
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
	}
	
	public static boolean generateAutumn(World var0, Random var1, int var2, int var3, int var4, int leafMeta) {
		int var5 = 5;
		int var6 = 0;
		int var7 = leafMeta;
		boolean var8 = false;
		
		int var9 = var1.nextInt(3) + var5;
		boolean var10 = true;

		if (var3 >= 1 && var3 + var9 + 1 <= 256)
		{
			int var11;
			byte var12;
			int var14;
			int var15;

			for (var11 = var3; var11 <= var3 + 1 + var9; ++var11)
			{
				var12 = 1;

				if (var11 == var3)
				{
					var12 = 0;
				}

				if (var11 >= var3 + 1 + var9 - 2)
				{
					var12 = 2;
				}

				for (int var13 = var2 - var12; var13 <= var2 + var12 && var10; ++var13)
				{
					for (var14 = var4 - var12; var14 <= var4 + var12 && var10; ++var14)
					{
						if (var11 >= 0 && var11 < 256)
						{
							var15 = var0.getBlockId(var13, var11, var14);

							if (!var0.isAirBlock(var13, var11, var14) && var15 != Block.leaves.blockID && var15 != Block.grass.blockID && var15 != Block.dirt.blockID && var15 != Block.wood.blockID && var15 != DecoDefs.cherryLog.blockID && var15 != DecoDefs.cherryLeaves.blockID || var15 == DecoDefs.autumnLeaves.blockID)
							{
								var10 = false;
							}
						}
						else
						{
							var10 = false;
						}
					}
				}
			}

			if (!var10)
			{
				return false;
			}
			else
			{
				var11 = var0.getBlockId(var2, var3 - 1, var4);

				if (FCUtilsTrees.CanSaplingGrowOnBlock(var0, var2, var3 - 1, var4) && var3 < 256 - var9 - 1)
				{
					if (var11 == Block.grass.blockID)
					{
						var0.setBlockWithNotify(var2, var3 - 1, var4, Block.dirt.blockID);
					}

					var12 = 3;
					byte var21 = 0;
					int var16;
					int var17;
					int var18;

					for (var14 = var3 - var12 + var9; var14 <= var3 + var9; ++var14)
					{
						var15 = var14 - (var3 + var9);
						var16 = var21 + 1 - var15 / 2;

						for (var17 = var2 - var16; var17 <= var2 + var16; ++var17)
						{
							var18 = var17 - var2;

							for (int var19 = var4 - var16; var19 <= var4 + var16; ++var19)
							{
								int var20 = var19 - var4;

								if ((Math.abs(var18) != var16 || Math.abs(var20) != var16 || var1.nextInt(2) != 0 && var15 != 0) && !Block.opaqueCubeLookup[var0.getBlockId(var17, var14, var19)])
								{
									System.out.println(leafMeta);
									var0.setBlockAndMetadataWithNotify(var17, var14, var19, DecoDefs.autumnLeaves.blockID, leafMeta);
								}
							}
						}
					}

					for (var14 = 0; var14 < var9; ++var14)
					{
						var15 = var0.getBlockId(var2, var3 + var14, var4);

						if (var0.isAirBlock(var2, var3 + var14, var4) || var15 == Block.leaves.blockID || var15 == DecoDefs.autumnLeaves.blockID || var15 == DecoDefs.cherryLeaves.blockID)
						{
							var0.setBlockAndMetadataWithNotify(var2, var3 + var14, var4, Block.wood.blockID, var6);
						}
					}

					if (var9 > 2)
					{
						var14 = var0.getBlockId(var2, var3, var4);

						if (var14 == Block.wood.blockID)
						{
							var15 = var0.getBlockMetadata(var2, var3, var4);

							if (var15 == var6)
							{
								var0.setBlockMetadata(var2, var3, var4, var15 | 12);
							}
						}
					}

					return true;
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
	}
	
	public boolean generateAcacia(World par1World, Random par2Random, int x, int y, int z)
	{
		int var5 = par1World.getBlockId(x, y - 1, z);
		if (var5 != Block.grass.blockID)
			return false;
		int rand = 4 + par2Random.nextInt(3);
		for(int i = 0; i < rand; i++) { par1World.setBlock(x, y + i, z, Block.wood.blockID); }

        //par1World.setBlockMetadataWithClient(x, y, z, metaWood | 12); // place stump

		if(par2Random.nextInt(4) == 0) { //branch1
			par1World.setBlock(x + 0, y + rand + 1, z + 1, Block.wood.blockID);
			par1World.setBlock(x + 1, y + rand + 2, z + 2, Block.wood.blockID);
			createAcaciaLeaves(par1World, par2Random, x + 1, y + rand + 2, z + 2, 3);
			createAcaciaLeaves(par1World, par2Random, x + 1, y + rand + 3, z + 2, 2);
		}

		if(par2Random.nextInt(4) == 0) { //branch2
			par1World.setBlock(x + 1, y + rand + 0, z + 0, Block.wood.blockID);
			par1World.setBlock(x + 2, y + rand + 1, z + 0, Block.wood.blockID);
			par1World.setBlock(x + 3, y + rand + 2, z - 1, Block.wood.blockID);
			createAcaciaLeaves(par1World, par2Random, x + 3, y + rand + 3, z - 1, 3);
			createAcaciaLeaves(par1World, par2Random, x + 3, y + rand + 4, z - 1, 2);
		}

		if(par2Random.nextInt(4) == 0) { //branch3
			par1World.setBlock(x - 1, y + rand + 0, z + 0, Block.wood.blockID);
			par1World.setBlock(x - 2, y + rand + 1, z + 0, Block.wood.blockID);
			par1World.setBlock(x - 3, y + rand + 2, z - 1, Block.wood.blockID);
			par1World.setBlock(x - 4, y + rand + 3, z - 2, Block.wood.blockID);
			createAcaciaLeaves(par1World, par2Random, x - 4, y + rand + 4, z - 2, 3);
			createAcaciaLeaves(par1World, par2Random, x - 4, y + rand + 5, z - 2, 2);
		}

		if(par2Random.nextInt(4) == 0) { //branch4
			par1World.setBlock(x + 0, y + rand + 0, z - 1, Block.wood.blockID);
			par1World.setBlock(x + 1, y + rand + 1, z - 2, Block.wood.blockID);
			par1World.setBlock(x + 2, y + rand + 2, z - 2, Block.wood.blockID);
			par1World.setBlock(x + 3, y + rand + 3, z - 2, Block.wood.blockID);
			createAcaciaLeaves(par1World, par2Random, x + 3, y + rand + 3, z - 2, 3);
			createAcaciaLeaves(par1World, par2Random, x + 3, y + rand + 4, z - 2, 2);
		}

		if(par2Random.nextInt(4) == 0) { //branch5
			par1World.setBlock(x + 0, y + rand + 0, z - 1, Block.wood.blockID);
			par1World.setBlock(x + 0, y + rand + 0, z - 2, Block.wood.blockID);
			par1World.setBlock(x + 1, y + rand + 1, z - 3, Block.wood.blockID);
			createAcaciaLeaves(par1World, par2Random, x + 1, y + rand + 1, z - 3, 3);
			createAcaciaLeaves(par1World, par2Random, x + 1, y + rand + 2, z - 3, 2);
		}

		//branch6
		par1World.setBlock(x - 0, y + rand + 0, z + 0, Block.wood.blockID);
		par1World.setBlock(x - 0, y + rand + 1, z + 0, Block.wood.blockID);
		par1World.setBlock(x - 0, y + rand + 2, z - 0, Block.wood.blockID);
		createAcaciaLeaves(par1World, par2Random, x + 0, y + rand + 3, z - 0, 3);
		createAcaciaLeaves(par1World, par2Random, x + 0, y + rand + 4, z - 0, 2);

		return true;
	}

	private void createAcaciaLeaves(World par1World, Random par2Random, int x, int y, int z, int size)
	{
		for(int x1 = -size + x; x1 < size + 1 + x; x1++)
		{
			for(int z1 = -size + z; z1 < size + 1 + z; z1++)
			{
				int var5 = par1World.getBlockId(x1, y, z1);
				if (var5 == 0)
				{
					if(x1 == -size + x && z1 == -size + z ){} else if(x1 == -size + x && z1 == size + z ){} else if(x1 == size + x && z1 == -size + z ){} else if(x1 == size + x && z1 == size + z ){}
					else { par1World.setBlock(x1, y, z1, Block.leaves.blockID); }
				}
			}
		}
		if(size==3){par1World.setBlock(x, y, z, Block.wood.blockID);}
	}
}
