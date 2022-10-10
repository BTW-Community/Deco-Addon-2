package deco.item.mixins;

import btw.item.items.BarkItem;
import deco.block.util.WoodTypeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BarkItem.class)
public class BarkItemMixin extends Item {
    public BarkItemMixin(int itemID) {
        super(itemID);
    }

    @Inject(method = "getUnlocalizedName(Lnet/minecraft/src/ItemStack;)Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
    public void getUnlocalizedName(ItemStack stack, CallbackInfoReturnable<String> info) {
        int itemDamage = stack.getItemDamage();

        info.setReturnValue(this.getUnlocalizedName() + "." + WoodTypeHelper.woodNames[itemDamage]);
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    public Icon[] extraIcons;

    @Environment(EnvType.CLIENT)
    @Inject(method = "registerIcons(Lnet/minecraft/src/IconRegister;)V", at = @At("TAIL"))
    public void registerIcons(IconRegister register, CallbackInfo info) {
        extraIcons = new Icon[WoodTypeHelper.NUM_EXTRA_WOOD];

        for (int i = 0; i < WoodTypeHelper.NUM_EXTRA_WOOD; i++) {
            extraIcons[i] = register.registerIcon("decoItemBark" + WoodTypeHelper.woodNamesCapital[i + WoodTypeHelper.NUM_VANILLA_WOOD]);
        }
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "getIconFromDamage(I)Lnet/minecraft/src/Icon;", at = @At("HEAD"), cancellable = true)
    public void getIconFromDamage(int itemDamage, CallbackInfoReturnable<Icon> info) {
        if (itemDamage >= WoodTypeHelper.NUM_VANILLA_WOOD) {
            info.setReturnValue(extraIcons[itemDamage - WoodTypeHelper.NUM_VANILLA_WOOD]);
        }
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "getSubItems(ILnet/minecraft/src/CreativeTabs;Ljava/util/List;)V", at = @At("TAIL"))
    public void getSubItems(int itemID, CreativeTabs creativeTabs, List<ItemStack> list, CallbackInfo info) {
        for (int i = 0; i < WoodTypeHelper.NUM_EXTRA_WOOD; i++) {
            list.add(new ItemStack(itemID, 1, i + WoodTypeHelper.NUM_VANILLA_WOOD));
        }
    }
}
