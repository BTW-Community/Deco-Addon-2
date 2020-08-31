package net.minecraft.src;

public class DecoTileEntityBarrelFilling extends TileEntity /*implements FCITileEntityDataPacketHandler*/ {
	private ItemStack containedItems;
	
	@Override
	public void updateEntity() {
		if (containedItems != null && containedItems.stackSize >= 8) {
			((DecoBlockBarrelFilling) Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)]).dropBlockAsItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord), 0);
			this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
		}
	}
	
	public int attemptToAddItems(ItemStack stack) {
		//Invalid items for storage
		if (!DecoBlockBarrelFilling.isValidItemForFill(stack))
			return 0;
		
		if (containedItems != null) {
			if (containedItems.stackSize >= 8) {
				return 0;
			}
			
			if (containedItems.itemID != stack.itemID) {
				return 0;
			}
			else {
				//If total does not exceed 8
				if (containedItems.stackSize + stack.stackSize <= 8) {
					containedItems.stackSize += stack.stackSize; 
					return stack.stackSize;
				}
				//If total exceeds 8 then cut off extra 
				else {
					int diff = (containedItems.stackSize + stack.stackSize - 8);
					int countToTransfer = stack.stackSize - diff;
					containedItems.stackSize += countToTransfer;
					return countToTransfer;
				}
			}
		}
		else {
			containedItems = stack.copy();
			//Limits to 8 items
			if (containedItems.stackSize > 8)
				containedItems.stackSize = 8;
			return containedItems.stackSize;
		}
	}
}