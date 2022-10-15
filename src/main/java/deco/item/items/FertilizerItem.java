package deco.item.items;

import btw.AddonHandler;
import btw.block.BTWBlocks;
import btw.item.BTWItems;
import btw.world.util.BlockPos;
import deco.block.DecoBlocks;
import deco.block.blocks.DecoFlowerBlock;
import deco.block.blocks.TallPlantBlock;
import net.minecraft.src.*;

import java.util.ArrayList;

public class FertilizerItem extends Item {
	public FertilizerItem(int itemID) {
		super(itemID);
		this.setUnlocalizedName("decoItemFertilizer");
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setFilterableProperties(8);
		this.setBellowsBlowDistance(BTWItems.coalDust.getBellowsBlowDistance(0));
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (player != null && player.canPlayerEdit(x, y, z, side, stack)) {
			int id = world.getBlockId(x, y, z);
			
			if ((id == Block.tilledField.blockID || id == BTWBlocks.farmland.blockID || id == BTWBlocks.planterWithSoil.blockID || id == Block.tallGrass.blockID)
					&& this.applyBoneMeal(world, x, y, z))
			{
				stack.stackSize--;
				return true;
			}
			else if (id == Block.grass.blockID && this.growTallGrassAndFlowers(world, x, y, z)) {
				--stack.stackSize;
				return true;
			}
		}
		
		return false;
	}
	
	private boolean applyBoneMeal(World world, int x, int y, int z) {
		Block block = Block.blocksList[world.getBlockId(x, y, z)];
		return block != null && block.attemptToApplyFertilizerTo(world, x, y, z);
	}
	
	public boolean growTallGrassAndFlowers(World world, int x, int y, int z) {
		if (!world.isRemote) {
			int i = 0;
			while (i < 128) {
				int newX = x;
				int newY = y + 1;
				int newZ = z;
				boolean test = true;
				int j = 0;
				
				// Set up values for flower spawning before entering loop
				ArrayList<Integer> spawnableFlowers = ((DecoFlowerBlock) DecoBlocks.flower).getSpawnableList();
				ArrayList<Integer> spawnableFlowers2 = ((DecoFlowerBlock) DecoBlocks.flower2).getSpawnableList();
				ArrayList<Integer> spawnableTulips = ((DecoFlowerBlock) DecoBlocks.tulip).getSpawnableList();
				ArrayList<Integer> spawnableTallFlowers = ((TallPlantBlock) DecoBlocks.tallFlower).getSpawnableList();
				
				// +2 is for vanilla flowers
				int totalSpawnableFlowerCount = spawnableFlowers.size() + spawnableFlowers2.size() + spawnableTulips.size() + spawnableTallFlowers.size() + 2;
				
				while (true) {
					if (j < i / 16) {
						newX += itemRand.nextInt(3) - 1;
						newY += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
						newZ += itemRand.nextInt(3) - 1;
						
						if (world.getBlockId(newX, newY - 1, newZ) == Block.grass.blockID && !world.isBlockNormalCube(newX, newY, newZ)) {
							j++;
							continue;
						}
						
						test = false;
					}
					
					if (test && world.getBlockId(newX, newY, newZ) == 0) {
						if (itemRand.nextInt(3) == 0 && Block.tallGrass.canBlockStay(world, newX, newY, newZ)) {
							int grassType = 0;
							
							if (itemRand.nextInt(4) == 0) {
								grassType = 1; // Fern
							}
							
							if (!AddonHandler.isModInstalled("Sock's Crops") && itemRand.nextInt(4) == 0 && world.getBlockId(newX, newY + 1, newZ) == 0) {
								world.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoBlocks.tallGrass.blockID, grassType);
								world.setBlockAndMetadataWithNotify(newX, newY + 1, newZ, DecoBlocks.tallGrass.blockID,
										((TallPlantBlock) DecoBlocks.tallGrass).setTopBlock(grassType, true));
							}
							else {
								world.setBlockAndMetadataWithNotify(newX, newY, newZ, Block.tallGrass.blockID, grassType + 1);
							}
						}
						else if (itemRand.nextInt(2) == 0 && Block.plantYellow.canBlockStay(world, newX, newY, newZ)) {
							int flowerIndex = itemRand.nextInt(totalSpawnableFlowerCount);
							
							if (flowerIndex < spawnableFlowers.size()) {
								world.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoBlocks.flower.blockID, spawnableFlowers.get(flowerIndex));
							}
							else {
								flowerIndex -= spawnableFlowers.size();
								
								if (flowerIndex == 0) {
									world.setBlockWithNotify(newX, newY, newZ, Block.plantRed.blockID);
								}
								else if (flowerIndex == 1) {
									world.setBlockWithNotify(newX, newY, newZ, Block.plantYellow.blockID);
								}
								else {
									flowerIndex -= 2;
									
									if (flowerIndex < spawnableFlowers2.size()) {
										world.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoBlocks.flower2.blockID, spawnableFlowers2.get(flowerIndex));
									}
									else {
										flowerIndex -= spawnableFlowers2.size();
										
										if (flowerIndex < spawnableTulips.size()) {
											world.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoBlocks.tulip.blockID, spawnableTulips.get(flowerIndex));
										}
										else {
											flowerIndex -= spawnableTulips.size();
											
											if (flowerIndex < spawnableTallFlowers.size()) {
												if (world.getBlockId(newX, newY + 1, newZ) == 0) {
													world.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoBlocks.tallFlower.blockID,
															spawnableTallFlowers.get(flowerIndex));
													world.setBlockAndMetadataWithNotify(newX, newY + 1, newZ, DecoBlocks.tallFlower.blockID,
															((TallPlantBlock) DecoBlocks.tallFlower).setTopBlock(spawnableTallFlowers.get(flowerIndex), true));
												}
											}
										}
									}
								}
							}
						}
					}
					
					i++;
					
					break;
				}
			}
		}
		
		return true;
	}
	
	@Override
	public boolean onItemUsedByBlockDispenser(ItemStack stack, World world, int x, int y, int z, int var6) {
		int id;
		
		int facing = BTWBlocks.blockDispenser.getFacing(world.getBlockMetadata(x, y, z));
		BlockPos pos = new BlockPos(x, y, z);
		pos.addFacingAsOffset(facing);
		
		if (facing == 1) {
			id = world.getBlockId(pos.x, pos.y, pos.z);
			
			if (id == Block.grass.blockID && this.growTallGrassAndFlowers(world, pos.x, pos.y, pos.z)) {
				world.playAuxSFX(1000, x, y, z, 0);
				return true;
			}
		}
		else {
			id = world.getBlockId(pos.x, pos.y - 1, pos.z);
			
			if (id == Block.grass.blockID && this.growTallGrassAndFlowers(world, pos.x, pos.y - 1, pos.z)) {
				world.playAuxSFX(1000, x, y, z, 0);
				return true;
			}
		}
		
		return false;
	}
}