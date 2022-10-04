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
        DecoItems.cherryDoor = new DecoItemDoor(DecoItemIDs.CHERRY_DOOR_ID, DecoBlockIDs.CHERRY_DOOR_ID)
                .setUnlocalizedName("decoItemDoorCherry");
    }

    private static void createDecoItemBlocks() {
        for (int i = 3000; i < 4096; i++) {
            if (Block.blocksList[i] != null && Item.itemsList[i] == null) {
                Item.itemsList[i] = new ItemBlock(i - 256);
            }
        }
    }
}
