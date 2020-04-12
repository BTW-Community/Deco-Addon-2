package net.minecraft.src;

import java.util.Random;

public class AddonBlockDoorWood extends FCBlockDoorWood {
	private String[] doorIconNames;
	
	protected AddonBlockDoorWood(int ID, String[] textures) {
		super(ID);
		doorIconNames = textures;
	}
	
	@Override
    public int idDropped(int par1, Random par2Random, int par3) {
		int returnID = 0;
		
		int doorSpruce = AddonDefs.doorSpruce.blockID; 
		int doorBirch = AddonDefs.doorBirch.blockID;
		int doorJungle = AddonDefs.doorJungle.blockID;
		int doorBlood = AddonDefs.doorBlood.blockID;
		
		returnID = this.blockID == doorSpruce ? AddonDefs.itemDoorSpruce.itemID : (this.blockID == doorBirch ? AddonDefs.itemDoorBirch.itemID : (this.blockID == doorJungle ? AddonDefs.itemDoorJungle.itemID : AddonDefs.itemDoorBlood.itemID));
		
		return (par1 & 8) != 0 ? 0 : returnID;
	}
	
    public int idPicked(World par1World, int par2, int par3, int par4) {
		int doorSpruce = AddonDefs.doorSpruce.blockID; 
		int doorBirch = AddonDefs.doorBirch.blockID;
		int doorJungle = AddonDefs.doorJungle.blockID;
		int doorBlood = AddonDefs.doorBlood.blockID;
		
		return this.blockID == doorSpruce ? AddonDefs.itemDoorSpruce.itemID : (this.blockID == doorBirch ? AddonDefs.itemDoorBirch.itemID : (this.blockID == doorJungle ? AddonDefs.itemDoorJungle.itemID : AddonDefs.itemDoorBlood.itemID));
    }
}
