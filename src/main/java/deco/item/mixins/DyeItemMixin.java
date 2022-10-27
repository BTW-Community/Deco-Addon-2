package deco.item.mixins;

import btw.item.items.DyeItem;
import btw.util.ColorUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DyeItem.class)
public abstract class DyeItemMixin extends ItemDye {
	private static final String[] dyeNames = new String[] {
			"black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white",
			"black2", "red2", "green2", "brown2", "blue2", "purple2", "cyan2", "silver2", "gray2", "pink2", "lime2", "yellow2", "lightBlue2", "magenta2", "orange2", "white2"};
	
	public DyeItemMixin(int blockID) {
		super(blockID);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + dyeNames[stack.getItemDamage() & 31];
	}
	
	//------------ Client Side Functionality ----------//
	
	@Environment(EnvType.CLIENT)
	public Icon[] extraIcons = new Icon[16];
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubItems(int itemID, CreativeTabs creativeTabs, List list) {
		for (int color = 0; color < 16; ++color) {
			list.add(new ItemStack(itemID, 1, color));
		}
		
		list.add(new ItemStack(itemID, 1, ColorUtils.BLACK.colorID + 16));
		list.add(new ItemStack(itemID, 1, ColorUtils.BLUE.colorID + 16));
		list.add(new ItemStack(itemID, 1, ColorUtils.WHITE.colorID + 16));
	}
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "getIconFromDamage", at = @At("HEAD"), cancellable = true)
	public void getIconFromDamage(int metadata, CallbackInfoReturnable<Icon> cir) {
		if (metadata > 15) {
			cir.setReturnValue(extraIcons[metadata - 16]);
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "registerIcons", at = @At("TAIL"))
	public void registerIcons(IconRegister register, CallbackInfo ci) {
		for (int i = 0; i < 16; i++) {
			extraIcons[i] = register.registerIcon("decoItemDye_" + ColorUtils.colorOrder[i]);
		}
	}
}
