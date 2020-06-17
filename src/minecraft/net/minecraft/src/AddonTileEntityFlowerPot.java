package net.minecraft.src;

import java.util.HashSet;
import java.util.Set;

public class AddonTileEntityFlowerPot extends TileEntity implements FCITileEntityDataPacketHandler {
	private boolean hasItem = false;
	private static Set<Integer> validItemList = new HashSet<Integer>();
	
	private int currentBlockID;
	private int currentBlockMetadata;

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("StoredID", this.currentBlockID);
        nbtTagCompound.setInteger("StoredMetadata", this.currentBlockMetadata);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        this.currentBlockID = nbtTagCompound.getInteger("StoredID");
        this.currentBlockMetadata = nbtTagCompound.getInteger("StoredMetadata");
    }

    /**
     * Overriden in a sign to provide the text.
     */
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger("StoredID", this.currentBlockID);
        nbtTagCompound.setInteger("StoredMetadata", this.currentBlockMetadata);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTagCompound);
    }
    
    @Override
    public void readNBTFromPacket(NBTTagCompound nbtTagCompound) {
        this.currentBlockID = nbtTagCompound.getInteger("StoredID");
        this.currentBlockMetadata = nbtTagCompound.getInteger("StoredMetadata");
        if (currentBlockID != 0) hasItem = true;
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }
    
    public int getStoredBlockID() {
    	return currentBlockID;
    }
    
    public int getStoredBlockMetadata() {
    	return currentBlockMetadata;
    }

	public boolean hasItem() {
		return hasItem;
	}

	public boolean isValidItemForPot(int id, int meta) {
		//Special case for blood wood sapling
		if (id == FCBetterThanWolves.fcAestheticVegetation.blockID)
			return meta == 2;
		
		return validItemList.contains(id);
	}
	
	public boolean isSaplingForAltRender(int id) {
		return id == Block.sapling.blockID || id == AddonDefs.cherrySapling.blockID;
	}

	public void placeItemInPot(int itemID, int metadata) {
		hasItem = true;
		
		if (itemID == FCBetterThanWolves.fcItemMushroomBrown.itemID)
			currentBlockID = Block.mushroomBrown.blockID;
		else if (itemID == FCBetterThanWolves.fcItemMushroomRed.itemID)
			currentBlockID = Block.mushroomRed.blockID;
		else
			currentBlockID = itemID;
		currentBlockMetadata = metadata;
		
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
        
        this.worldObj.playSound(this.xCoord, this.yCoord, this.zCoord, "step.grass", 1.0F, 1.0F);
	}

	//Removes item and gives it to the player
	public void retrieveItemFromPot(EntityPlayer player) {
		if (currentBlockID == 0)
			return;
		
		if (!player.capabilities.isCreativeMode)
            FCUtilsItem.GivePlayerStackOrEjectFavorEmptyHand(player, new ItemStack(Item.itemsList[currentBlockID], 1, currentBlockMetadata), this.xCoord, this.yCoord, this.zCoord);
		
		hasItem = false;
		currentBlockID = 0;
		
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);

        if (!this.worldObj.isRemote)
        	this.worldObj.playAuxSFX(FCBetterThanWolves.m_iItemCollectionPopSoundAuxFXID, this.xCoord, this.yCoord, this.zCoord, 0);
	}

	static {
		validItemList.add(Block.plantRed.blockID);
		validItemList.add(Block.plantYellow.blockID);
		validItemList.add(Block.mushroomBrown.blockID);
		validItemList.add(Block.mushroomRed.blockID);
		validItemList.add(Block.cactus.blockID);
		validItemList.add(Block.sapling.blockID);
		validItemList.add(Block.tallGrass.blockID);
		validItemList.add(FCBetterThanWolves.fcAestheticVegetation.blockID);
		validItemList.add(AddonDefs.flower.blockID);
		validItemList.add(AddonDefs.flower2.blockID);
		validItemList.add(AddonDefs.tulip.blockID);
		validItemList.add(AddonDefs.cherrySapling.blockID);
	}
}
