package deco.item;

import btw.item.items.BookItem;
import deco.block.DecoBlockIDs;
import deco.item.items.DecoItemDoor;
import deco.item.util.BookInterface;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemBlock;

public class DecoItemInitializer {
    public static void initDecoItems() {
        initGeneralItems();
        initWoodItems();
        createDecoItemBlocks();
    }
    
    private static void initGeneralItems() {
        ((BookInterface) Item.book).setEnchanted(true).setUnlocalizedName("decoItemBookEnchanted");
        DecoItems.plainBook = new BookItem(DecoItemIDs.PLAIN_BOOK_ID);
    }
    
    private static void initWoodItems() {
        DecoItems.spruceDoor = new DecoItemDoor(DecoItemIDs.SPRUCE_DOOR_ID, DecoBlockIDs.SPRUCE_DOOR_ID)
                .setUnlocalizedName("decoItemDoorSpruce");
        DecoItems.birchDoor = new DecoItemDoor(DecoItemIDs.BIRCH_DOOR_ID, DecoBlockIDs.BIRCH_DOOR_ID)
                .setUnlocalizedName("decoItemDoorBirch");
        DecoItems.jungleDoor = new DecoItemDoor(DecoItemIDs.JUNGLE_DOOR_ID, DecoBlockIDs.JUNGLE_DOOR_ID)
                .setUnlocalizedName("decoItemDoorJungle");
        DecoItems.bloodDoor = new DecoItemDoor(DecoItemIDs.BLOOD_DOOR_ID, DecoBlockIDs.BLOOD_DOOR_ID)
                .setUnlocalizedName("decoItemDoorBlood");
        DecoItems.cherryDoor = new DecoItemDoor(DecoItemIDs.CHERRY_DOOR_ID, DecoBlockIDs.CHERRY_DOOR_ID)
                .setUnlocalizedName("decoItemDoorCherry");
        DecoItems.acaciaDoor = new DecoItemDoor(DecoItemIDs.ACACIA_DOOR_ID, DecoBlockIDs.ACACIA_DOOR_ID)
                .setUnlocalizedName("decoItemDoorAcacia");
        DecoItems.mahoganyDoor = new DecoItemDoor(DecoItemIDs.MAHOGANY_DOOR_ID, DecoBlockIDs.MAHOGANY_DOOR_ID)
                .setUnlocalizedName("decoItemDoorMahogany");
        DecoItems.mangroveDoor = new DecoItemDoor(DecoItemIDs.MANGROVE_DOOR_ID, DecoBlockIDs.MANGROVE_DOOR_ID)
                .setUnlocalizedName("decoItemDoorMangrove");
        DecoItems.hazelDoor = new DecoItemDoor(DecoItemIDs.HAZEL_DOOR_ID, DecoBlockIDs.HAZEL_DOOR_ID)
                .setUnlocalizedName("decoItemDoorHazel");
    }

    private static void createDecoItemBlocks() {
        for (int i = 3000; i < 4096; i++) {
            if (Block.blocksList[i] != null && Item.itemsList[i] == null) {
                Item.itemsList[i] = new ItemBlock(i - 256);
            }
        }
    }
}
