package deco.item.itemblocks;

import btw.util.ColorUtils;
import net.minecraft.src.Block;
import net.minecraft.src.ItemMultiTextureTile;

public class ColoredItemBlock extends ItemMultiTextureTile {
	public ColoredItemBlock(Block block) {
		super(block.blockID - 256, block, ColorUtils.colorOrder);
	}
}
