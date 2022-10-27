package deco.item.mixins;

import btw.item.items.SandPileItem;
import deco.block.util.SandHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SandPileItem.class)
public abstract class SandPileItemMixin extends Item {
	public SandPileItemMixin(int itemID) {
		super(itemID);
	}
	
	@Inject(method = "<init>", at = @At("TAIL"))
	public void init(int itemID, CallbackInfo ci) {
		this.setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return this.getUnlocalizedName() + "." + SandHelper.sandNames[stack.getItemDamage()];
	}
	
	//------------ Client Side Functionality ----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] extraIcons;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIconFromDamage(int metadata) {
		if (metadata == 0) {
			return this.itemIcon;
		}
		else {
			return extraIcons[metadata - 1];
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		extraIcons = new Icon[SandHelper.NUM_EXTRA_SAND_TYPES];
		
		for (int i = 0; i < SandHelper.NUM_EXTRA_SAND_TYPES; i++) {
			extraIcons[i] = register.registerIcon("decoItemPile" + SandHelper.sandNamesCapital[i + 1] + "Sand");
		}
		
		super.registerIcons(register);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubItems(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < SandHelper.NUM_SAND_TYPES; i++) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
}
