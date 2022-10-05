package deco.item.mixins;

import btw.item.blockitems.WoodCornerStubBlockItem;
import deco.block.DecoBlocks;
import deco.block.util.WoodTypeHelper;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WoodCornerStubBlockItem.class)
public class WoodCornerStubMixin extends ItemBlock {
    public WoodCornerStubMixin(int itemID) {
        super(itemID);
    }

    @Inject(method = "getBlockIDToPlace(IIFFF)I", at = @At("HEAD"), cancellable = true, remap = false)
    public void getBlockIDToPlace(int itemDamage, int facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Integer> info) {
        if (itemDamage >= WoodTypeHelper.NUM_VANILLA_WOOD) {
            info.setReturnValue(WoodTypeHelper.woodTypeToSidingIDMap.get(itemDamage));
        }
    }

    @Inject(method = "getUnlocalizedName(Lnet/minecraft/src/ItemStack;)Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
    public void getUnlocalizedName(ItemStack itemStack, CallbackInfoReturnable<String> info) {
        int itemDamage = itemStack.getItemDamage();
        
        if (itemDamage >= WoodTypeHelper.NUM_VANILLA_WOOD) {
            info.setReturnValue(this.getUnlocalizedName() + "." + WoodTypeHelper.woodNames[itemDamage]);
        }
    }
}
