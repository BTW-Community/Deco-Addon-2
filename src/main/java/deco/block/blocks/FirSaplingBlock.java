package deco.block.blocks;

import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class FirSaplingBlock extends DecoSaplingBlock {
	public static final String[] saplingTypes = new String[]{"fir", "", "", "", "fir", "", "", "", "fir", "", "", "", "firMature", "", "", ""};
	
	public FirSaplingBlock(int blockID) {
		super(blockID, new String[]{"decoBlockSaplingFir_0"});
		this.setUnlocalizedName("decoBlockSaplingFir");
	}
	
	@Override
	public boolean generateTree(World world, Random rand, int x, int y, int z, int treeType) {
		int variant = rand.nextInt(3);
		
		switch (variant) {
			default:
			case 0:
				return generateFir1(world, rand, x, y, z);
			case 1:
				return generateFir2(world, rand, x, y, z);
			case 2:
				return generateFir3(world, rand, x, y, z);
		}
	}
	
	@Override
	public boolean generateTree2x2(World world, Random rand, int x, int y, int z, int treeType) {
		return this.generateFirLarge(world, rand, x, y, z);
	}
	
	@Override
	public int getLogID(int type) {
		return DecoBlocks.firLog.blockID;
	}
	
	@Override
	public int getStumpID(int type) {
		return DecoBlocks.firStump.blockID;
	}
	
	public boolean canGrow2x2(int treeType) {
		return true;
	}
	
	//------------- Class Specific Methods ------------//
	
	public boolean generateFir1(World world, Random rand, int x, int y, int z) {
		int treeHeight = rand.nextInt(9) + 9;
		int var7 = 2 + rand.nextInt(4);
		int var8 = treeHeight - var7;
		int var9 = 2 + rand.nextInt(2);
		boolean canGrow = true;
		
		if (y >= 1 && y + treeHeight + 1 <= 256) {
			for (int j = y; j <= y + 1 + treeHeight && canGrow; ++j) {
				int var21;
				
				if (j - y < var7) {
					var21 = 0;
				}
				else {
					var21 = var9;
				}
				
				for (int i = x - var21; i <= x + var21 && canGrow; ++i) {
					for (int k = z - var21; k <= z + var21 && canGrow; ++k) {
						if (j >= 0 && j < 256) {
							int var15 = world.getBlockId(i, j, k);
							
							if (var15 != 0 && !Block.blocksList[var15].isLeafBlock(world, i, j, k)) {
								canGrow = false;
							}
						}
						else {
							canGrow = false;
						}
					}
				}
			}
			
			if (!canGrow) {
				return false;
			}
			else {
				int blockIDBelow = world.getBlockId(x, y - 1, z);
				
				if ((blockIDBelow != 0 && Block.blocksList[blockIDBelow].canSaplingsGrowOnBlock(world, x, y - 1, z)) &&
						y < 256 - treeHeight - 1) {
					world.setBlock(x, y - 1, z, Block.dirt.blockID);
					int var21 = rand.nextInt(2);
					int var13 = 1;
					byte var22 = 0;
					
					for (int yOffset = 0; yOffset <= var8; ++yOffset) {
						int j = y + treeHeight - yOffset;
						
						for (int i = x - var21; i <= x + var21; ++i) {
							int xOffset = i - x;
							
							for (int k = z - var21; k <= z + var21; ++k) {
								int zOffset = k - z;
								
								if ((Math.abs(xOffset) != var21 || Math.abs(zOffset) != var21 || var21 <= 0) &&
										!Block.opaqueCubeLookup[world.getBlockId(i, j, k)]) {
									world.setBlockAndMetadata(i, j, k, DecoBlocks.firLeaves.blockID, 0);
								}
							}
						}
						
						if (var21 >= var13) {
							var21 = var22;
							var22 = 1;
							++var13;
							
							if (var13 > var9) {
								var13 = var9;
							}
						}
						else {
							++var21;
						}
					}
					
					int var15 = rand.nextInt(3);
					
					for (int var16 = 0; var16 < treeHeight - var15; ++var16) {
						int var17 = world.getBlockId(x, y + var16, z);
						
						if (var17 == 0 || var17 == DecoBlocks.firLeaves.blockID) {
							world.setBlockAndMetadata(x, y + var16, z, DecoBlocks.firLog.blockID, 0);
						}
					}
					
					world.setBlock(x, y, z, DecoBlocks.firStump.blockID);
					
					return true;
				}
				else {
					return false;
				}
			}
		}
		else {
			return false;
		}
	}
	
	public boolean generateFir2(World world, Random rand, int x, int y, int z) {
		int var6 = rand.nextInt(9) + 13;
		int var7 = 4 + rand.nextInt(4);
		int var8 = var6 - var7;
		int var9 = 2;
		boolean var10 = true;
		
		if (y >= 1 && y + var6 + 1 <= 256)
		{
			int var11;
			int var13;
			int var15;
			int var21;
			
			for (var11 = y; var11 <= y + 1 + var6 && var10; ++var11)
			{
				boolean var12 = true;
				
				if (var11 - y < var7)
				{
					var21 = 0;
				}
				else
				{
					var21 = var9;
				}
				
				for (var13 = x - var21; var13 <= x + var21 && var10; ++var13)
				{
					for (int var14 = z - var21; var14 <= z + var21 && var10; ++var14)
					{
						if (var11 >= 0 && var11 < 256)
						{
							var15 = world.getBlockId(var13, var11, var14);
							
							if (var15 != 0 && !Block.blocksList[var15].isLeafBlock(world, var13, var11, var14))
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
				return false;
			else
			{
				var11 = world.getBlockId(x, y - 1, z);
				
				if ((var11 != 0 && Block.blocksList[var11].canSaplingsGrowOnBlock(world, x, y - 1, z)) && y < 256 - var6 - 1)
				{
					world.setBlock(x, y - 1, z, Block.dirt.blockID);
					var21 = rand.nextInt(2);
					var13 = 1;
					byte var22 = 0;
					int var17;
					int var16;
					
					for (var15 = 0; var15 <= var8; ++var15)
					{
						var16 = y + var6 - var15;
						
						for (var17 = x - var21; var17 <= x + var21; ++var17)
						{
							int var18 = var17 - x;
							
							for (int var19 = z - var21; var19 <= z + var21; ++var19)
							{
								int var20 = var19 - z;
								
								if ((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && !Block.opaqueCubeLookup[world.getBlockId(var17, var16, var19)])
								{
									world.setBlockAndMetadata(var17, var16, var19, DecoBlocks.firLeaves.blockID, 0);
								}
							}
						}
						
						if (var21 >= var13)
						{
							var21 = var22;
							var22 = 1;
							++var13;
							
							if (var13 > var9)
							{
								var13 = var9;
							}
						}
						else
						{
							++var21;
						}
					}
					
					var15 = rand.nextInt(3);
					
					for (var16 = 0; var16 < var6 - var15; ++var16)
					{
						var17 = world.getBlockId(x, y + var16, z);
						
						if (var17 == 0 || var17 == DecoBlocks.firLeaves.blockID)
						{
							world.setBlockAndMetadata(x, y + var16, z, DecoBlocks.firLog.blockID, 0);
						}
					}
					
					world.setBlock(x, y, z, DecoBlocks.firStump.blockID);
					
					return true;
				} else
					return false;
			}
		} else
			return false;
	}
	
	public boolean generateFir3(World world, Random rand, int x, int y, int z) {
		int var6 = rand.nextInt(10) + 10;
		int var7 = 2;
		int var8 = var6 - var7;
		int var9 = 2 + rand.nextInt(2);
		boolean var10 = true;
		
		if (y >= 1 && y + var6 + 1 <= 256)
		{
			int var11;
			int var13;
			int var15;
			int var21;
			
			for (var11 = y; var11 <= y + 1 + var6 && var10; ++var11)
			{
				boolean var12 = true;
				
				if (var11 - y < var7)
				{
					var21 = 0;
				}
				else
				{
					var21 = var9;
				}
				
				for (var13 = x - var21; var13 <= x + var21 && var10; ++var13)
				{
					for (int var14 = z - var21; var14 <= z + var21 && var10; ++var14)
					{
						if (var11 >= 0 && var11 < 256)
						{
							var15 = world.getBlockId(var13, var11, var14);
							
							if (var15 != 0 && !Block.blocksList[var15].isLeafBlock(world, var13, var11, var14))
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
				return false;
			else
			{
				var11 = world.getBlockId(x, y - 1, z);
				
				if ((var11 != 0 && Block.blocksList[var11].canSaplingsGrowOnBlock(world, x, y - 1, z)) && y < 256 - var6 - 1)
				{
					world.setBlock(x, y - 1, z, Block.dirt.blockID);
					var21 = rand.nextInt(2);
					var13 = 1;
					boolean var22 = false;
					int var17;
					int var16;
					
					for (var15 = 0; var15 <= var8; ++var15)
					{
						var16 = y + var6 - var15;
						
						for (var17 = x - var21; var17 <= x + var21; ++var17)
						{
							int var18 = var17 - x;
							
							for (int var19 = z - var21; var19 <= z + var21; ++var19)
							{
								int var20 = var19 - z;
								
								if ((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && !Block.opaqueCubeLookup[world.getBlockId(var17, var16, var19)])
								{
									world.setBlockAndMetadata(var17, var16, var19, DecoBlocks.firLeaves.blockID, 0);
								}
							}
						}
						
						if (var21 >= var13)
						{
							var21 = var22 ? 1 : 0;
							var22 = true;
							++var13;
							
							if (var13 > var9)
							{
								var13 = var9;
							}
						}
						else
						{
							++var21;
						}
					}
					
					var15 = rand.nextInt(3);
					
					for (var16 = 0; var16 < var6 - var15; ++var16)
					{
						var17 = world.getBlockId(x, y + var16, z);
						
						if (var17 == 0 || var17 == DecoBlocks.firLeaves.blockID)
						{
							world.setBlockAndMetadata(x, y + var16, z, DecoBlocks.firLog.blockID, 0);
						}
					}
					
					world.setBlock(x, y, z, DecoBlocks.firStump.blockID);
					
					return true;
				} else
					return false;
			}
		} else
			return false;
	}
	
	public boolean generateFirLarge(World world, Random rand, int x, int y, int z) {
		int treeHeight = rand.nextInt(10) + 35;
		int canopyHeight = rand.nextInt(5) + 10;
		int var8 = treeHeight - canopyHeight;
		int var9 = 4;
		boolean canGrow = true;
		
		if (y >= 1 && y + treeHeight + 1 <= 256) {
			for (int j = y; j <= y + 1 + treeHeight && canGrow; ++j) {
				int var24;
				
				if (j - y < canopyHeight) {
					var24 = 0;
				}
				else {
					var24 = var9;
				}
				
				for (int i = x - var24; i <= x + var24 && canGrow; ++i) {
					for (int k = z - var24; k <= z + var24 && canGrow; ++k) {
						if (j >= 0 && j < 256) {
							int var15 = world.getBlockId(i, j, k);
							
							if (var15 != 0 && !Block.blocksList[var15].isLeafBlock(world, i, j, k)) {
								canGrow = false;
							}
						}
						else {
							canGrow = false;
						}
					}
				}
			}
			
			if (canGrow) {
				int blockIDBelow1 = world.getBlockId(x, y - 1, z);
				int blockIDBelow2 = world.getBlockId(x - 1, y - 1, z);
				int blockIDBelow3 = world.getBlockId(x, y - 1, z - 1);
				int blockBelowID4 = world.getBlockId(x - 1, y - 1, z - 1);
				
				if (y < 256 - treeHeight - 1 && blockIDBelow1 != 0 && Block.blocksList[blockIDBelow1].canSaplingsGrowOnBlock(world, x, y - 1, z) &&
						blockIDBelow2 != 0 && Block.blocksList[blockIDBelow2].canSaplingsGrowOnBlock(world, x - 1, y, z) && blockIDBelow3 != 0 &&
						Block.blocksList[blockIDBelow3].canSaplingsGrowOnBlock(world, x, y, z - 1) && blockBelowID4 != 0 &&
						Block.blocksList[blockBelowID4].canSaplingsGrowOnBlock(world, x - 1, y, z - 1))
				{
					int canopySize = rand.nextInt(2);
					int var16 = 1;
					boolean isSmallCanopyLayer = false;
					
					for (int yOffset = 0; yOffset <= var8; ++yOffset) {
						int j = y + treeHeight - yOffset;
						
						for (int i = x - canopySize; i <= x + canopySize; ++i) {
							int xOffset = i - x;
							
							for (int k = z - canopySize; k <= z + canopySize; ++k) {
								int zOffset = k - z;
								
								if ((Math.abs(xOffset) != canopySize || Math.abs(zOffset) != canopySize || canopySize <= 0) &&
										(world.isAirBlock(i, j, k) || Block.blocksList[world.getBlockId(i, j, k)].blockMaterial.isReplaceable()))
								{
									world.setBlockAndMetadata(i, j, k, DecoBlocks.firLeaves.blockID, 0);
									world.setBlockAndMetadata(i - 1, j, k, DecoBlocks.firLeaves.blockID, 0);
									world.setBlockAndMetadata(i, j, k - 1, DecoBlocks.firLeaves.blockID, 0);
									world.setBlockAndMetadata(i - 1, j, k - 1, DecoBlocks.firLeaves.blockID, 0);
								}
							}
						}
						
						if (canopySize >= var16) {
							canopySize = isSmallCanopyLayer ? 1 : 0;
							isSmallCanopyLayer = true;
							++var16;
							
							if (var16 > var9) {
								var16 = var9;
							}
						}
						else {
							++canopySize;
						}
					}
					
					int logHeightOffset = rand.nextInt(3);
					
					for (int j = 0; j < treeHeight - logHeightOffset; ++j) {
						int blockID = world.getBlockId(x, y + j, z);
						
						if (blockID == 0 || Block.blocksList[blockID].isLeafBlock(world, x, y + j, z)) {
							world.setBlockAndMetadata(x, y + j, z, DecoBlocks.firLog.blockID, 0);
							world.setBlockAndMetadata(x - 1, y + j, z, DecoBlocks.firLog.blockID, 0);
							world.setBlockAndMetadata(x, y + j, z - 1, DecoBlocks.firLog.blockID, 0);
							world.setBlockAndMetadata(x - 1, y + j, z - 1, DecoBlocks.firLog.blockID, 0);
						}
					}
					
					world.setBlock(x, y, z, DecoBlocks.firStump.blockID);
					world.setBlock(x - 1, y, z, DecoBlocks.firStump.blockID);
					world.setBlock(x, y, z - 1, DecoBlocks.firStump.blockID);
					world.setBlock(x - 1, y, z - 1, DecoBlocks.firStump.blockID);
					
					return true;
				}
			}
		}
		
		return false;
	}
}
