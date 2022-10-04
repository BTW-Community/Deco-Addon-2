package deco.item.itemblocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Block;
import net.minecraft.src.ItemMultiTextureTile;
import net.minecraft.src.ItemStack;

public class EnchantedBookshelfItemBlock extends ItemMultiTextureTile {
	public EnchantedBookshelfItemBlock(int par1, Block par2Block, String[] par3ArrayOfStr) {
		super(par1, par2Block, par3ArrayOfStr);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean hasEffect(ItemStack itemID) {
		return itemID.itemID == Block.bookShelf.blockID;
	}
}
