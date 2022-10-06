package deco.item.itemblocks;

import btw.util.ColorUtils;
import net.minecraft.src.Block;
import net.minecraft.src.ItemMultiTextureTile;

public class ColoredItemBlock extends ItemMultiTextureTile {
	public ColoredItemBlock(int itemID, Block block) {
		super(itemID, block, ColorUtils.colorOrder);
	}
}
