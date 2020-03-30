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
		
		switch (this.blockID) {
		case AddonDefs.id_doorSpruce:
			returnID =  AddonDefs.itemDoorSpruce.itemID;
			break;
		case AddonDefs.id_doorBirch:
			returnID =  AddonDefs.itemDoorBirch.itemID;
			break;
		case AddonDefs.id_doorJungle:
			returnID =  AddonDefs.itemDoorJungle.itemID;
			break;
		case AddonDefs.id_doorBlood:
			returnID =  AddonDefs.itemDoorBlood.itemID;
			break;
		}
		
		return (par1 & 8) != 0 ? 0 : returnID;
	}
	
    public int idPicked(World par1World, int par2, int par3, int par4) {
		switch (this.blockID) {
		case AddonDefs.id_doorSpruce:
			return AddonDefs.itemDoorSpruce.itemID;
		case AddonDefs.id_doorBirch:
			return AddonDefs.itemDoorBirch.itemID;
		case AddonDefs.id_doorJungle:
			return AddonDefs.itemDoorJungle.itemID;
		case AddonDefs.id_doorBlood:
			return AddonDefs.itemDoorBlood.itemID;
		default:
			return AddonDefs.itemDoorSpruce.itemID;
		}
    }
}
