package deco.item.mixins;

import btw.item.items.BookItem;
import deco.item.util.BookInterface;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BookItem.class)
public abstract class BookItemMixin extends Item implements BookInterface {
	public BookItemMixin(int itemID) {
		super(itemID);
	}
	
	private boolean isEnchanted = false;
	
	//------------- Class Specific Methods ------------//
	
	@Override
	public Item setEnchanted(boolean isEnchanted) {
		this.isEnchanted = isEnchanted;
		return this;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean hasEffect(ItemStack var1) {
		return this.isEnchanted;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.itemIcon = register.registerIcon("book");
	}
}
