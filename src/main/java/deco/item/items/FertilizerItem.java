package deco.item.items;

import btw.block.BTWBlocks;
import btw.item.BTWItems;
import btw.world.util.BlockPos;
import deco.block.DecoBlocks;
import net.minecraft.src.*;

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
			
			if ((id == Block.tilledField.blockID || id == BTWBlocks.farmland.blockID || id == BTWBlocks.planterWithSoil.blockID) && this.applyBoneMeal(world, x, y, z)) {
				--stack.stackSize;
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
						if (itemRand.nextInt(5) == 0 && Block.tallGrass.canBlockStay(world, newX, newY, newZ)) {
							world.setBlockAndMetadataWithNotify(newX, newY, newZ, Block.tallGrass.blockID, itemRand.nextInt(1) + 1);
						}
						else if (itemRand.nextInt(2) == 0 && Block.plantYellow.canBlockStay(world, newX, newY, newZ)) {
							int meta = itemRand.nextInt(24);
							
							switch (meta) {
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
								case 15:
									world.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoBlocks.flower.blockID, meta);
									break;
								case 16:
									world.setBlockWithNotify(newX, newY, newZ, Block.plantYellow.blockID);
									break;
								case 17:
									world.setBlockWithNotify(newX, newY, newZ, Block.plantRed.blockID);
									break;
								case 18:
								case 19:
								case 20:
								case 21:
									world.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoBlocks.tulip.blockID, meta - 18);
									break;
								case 22:
								case 23:
									world.setBlockAndMetadataWithNotify(newX, newY, newZ, DecoBlocks.flower2.blockID, meta - 22);
									break;
								default:
									break;
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