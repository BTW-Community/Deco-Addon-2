package deco.item.mixins;

import btw.item.items.StoneItem;
import deco.block.blocks.StoneVariantsBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(StoneItem.class)
public class StoneItemMixin extends Item {
	public StoneItemMixin(int itemID) {
		super(itemID);
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	public Icon[] extraIcons;
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "registerIcons(Lnet/minecraft/src/IconRegister;)V", at = @At("TAIL"))
	public void registerIcons(IconRegister register, CallbackInfo info) {
		extraIcons = new Icon[StoneVariantsBlock.NUM_TYPES];
		
		for (int i = 0; i < StoneVariantsBlock.NUM_TYPES; i++) {
			extraIcons[i] = register.registerIcon("decoItemStone" + StoneVariantsBlock.namesCapital[i]);
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "getIconFromDamage(I)Lnet/minecraft/src/Icon;", at = @At("HEAD"), cancellable = true)
	public void getIconFromDamage(int itemDamage, CallbackInfoReturnable<Icon> info) {
		if (itemDamage >= StoneVariantsBlock.NUM_TYPES) {
			info.setReturnValue(extraIcons[itemDamage - StoneVariantsBlock.NUM_VANILLA_TYPES]);
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "getSubItems(ILnet/minecraft/src/CreativeTabs;Ljava/util/List;)V", at = @At("TAIL"))
	public void getSubItems(int itemID, CreativeTabs creativeTabs, List<ItemStack> list, CallbackInfo info) {
		for (int i = 0; i < StoneVariantsBlock.NUM_TYPES; i++) {
			list.add(new ItemStack(itemID, 1, i + StoneVariantsBlock.NUM_VANILLA_TYPES));
		}
	}
}
