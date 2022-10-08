package deco.item.itemblocks;

import btw.item.blockitems.SlabBlockItem;
import deco.block.blocks.SlabInterface;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class DecoSlabItemBlock extends SlabBlockItem {
	public DecoSlabItemBlock(int itemID) {
		super(itemID);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack reference) {
		return super.getUnlocalizedName() + "." + reference.getItemDamage();
	}
	
	@Override
	public int getMetadata(int itemDamage) {
		return itemDamage;
	}
	
	@Override
	public boolean attemptToCombineWithBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, boolean var8) {
		if (this.canCombineWithBlock(world, x, y, z, itemStack.getItemDamage())) {
			int blockID = world.getBlockId(x, y, z);
			Block block = Block.blocksList[blockID];
			
			SlabInterface slabBlock = (SlabInterface) block;
			boolean isUpsideDown = slabBlock.getIsUpsideDown(world, x, y, z);
			
			if (!var8 || side == 1 && !isUpsideDown || side == 0 && isUpsideDown) {
				if (world.checkNoEntityCollision(Block.getFullBlockBoundingBoxFromPool(world, x, y, z)) && this.convertToFullBlock(world, x, y, z)) {
					world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F,
							block.stepSound.getPlaceSound(),
							(block.stepSound.getPlaceVolume() + 1.0F) / 2.0F,
							block.stepSound.getPlacePitch() * 0.8F);
					
					itemStack.stackSize--;
					Block newBlockID = Block.blocksList[world.getBlockId(x, y, z)];
					
					if (newBlockID != null) {
						world.notifyNearbyAnimalsOfPlayerBlockAddOrRemove(player, newBlockID, x, y, z);
					}
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean canCombineWithBlock(World world, int x, int y, int z, int metadata) {
		int blockID = world.getBlockId(x, y, z);
		
		if (blockID == this.getBlockID()) {
			Block block = Block.blocksList[blockID];
			
			int bottomHalfMeta = ((SlabInterface) block).setIsUpsideDown(world.getBlockMetadata(x, y, z), false);
			
			if (bottomHalfMeta == metadata) {
				return true;
			}
		}
		
		return false;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubItems(int itemID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < ((SlabInterface) Block.blocksList[itemID]).getNumTypes(); ++i) {
			list.add(new ItemStack(this, 1, i));
		}
	}
}