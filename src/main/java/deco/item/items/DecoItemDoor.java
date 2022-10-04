package deco.item.items;

import btw.item.items.DoorItemWood;
import net.minecraft.src.Block;

public class DecoItemDoor extends DoorItemWood {
	private int doorBlockID;
	
	public DecoItemDoor(int itemID, int doorBlockID) {
		super(itemID);
		this.doorBlockID = doorBlockID;
	}
	
	@Override
	public Block getDoorBlock() {
		return Block.blocksList[doorBlockID];
	}
}
