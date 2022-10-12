package deco.item.items;

import btw.item.items.PlaceAsBlockItem;
import btw.util.ColorUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class StainedGlassItem extends PlaceAsBlockItem {
	private String name;
	
	public StainedGlassItem(int itemID, int blockID, String name) {
		super(itemID, blockID);
		this.setHasSubtypes(true);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setUnlocalizedName(name);
		this.name = name;
	}
	
	@Override
	public int getMetadata(int itemDamage) {
		return itemDamage;
	}
	
	public String getUnlocalizedName(ItemStack itemStack) {
		int itemDamage = itemStack.getItemDamage();
		
		if (itemDamage < 0 || itemDamage >= 16) {
			itemDamage = 0;
		}
		
		return super.getUnlocalizedName() + "." + ColorUtils.colorOrder[itemDamage];
	}
	
	//------------ Client Side Functionality ----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] icons;
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.icons = new Icon[16];
		
		for (int i = 0; i < 16; i++) {
			this.icons[i] = register.registerIcon(this.name + "_" + ColorUtils.colorOrder[i]);
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIconFromDamage(int itemDamage) {
		int index = MathHelper.clamp_int(itemDamage, 0, 15);
		return this.icons[index];
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubItems(int itemID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i <= 15; i++) {
			list.add(new ItemStack(itemID, 1, i));
		}
	}
}
