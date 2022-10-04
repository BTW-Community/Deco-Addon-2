package deco.item.mixins;

import btw.item.blockitems.WoodMouldingStubBlockItem;
import deco.block.DecoBlocks;
import deco.block.util.WoodTypeHelper;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WoodMouldingStubBlockItem.class)
public class WoodMouldingStubMixin extends ItemBlock {
	public WoodMouldingStubMixin(int itemID) {
		super(itemID);
	}
	
	@Inject(method = "getBlockIDToPlace(IIFFF)I", at = @At("HEAD"), cancellable = true, remap = false)
	public void getBlockIDToPlace(int itemDamage, int facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Integer> info) {
		switch (itemDamage) {
			case WoodTypeHelper.CHERRY_WOOD_TYPE:
				info.setReturnValue(DecoBlocks.cherryMoulding.blockID);
				break;
			case WoodTypeHelper.ACACIA_WOOD_TYPE:
				info.setReturnValue(DecoBlocks.acaciaMoulding.blockID);
				break;
			case WoodTypeHelper.MAHOGANY_WOOD_TYPE:
				info.setReturnValue(DecoBlocks.mahoganyMoulding.blockID);
				break;
			case WoodTypeHelper.MANGROVE_WOOD_TYPE:
				info.setReturnValue(DecoBlocks.mangroveMoulding.blockID);
				break;
		}
	}
	
	@Inject(method = "getUnlocalizedName(Lnet/minecraft/src/ItemStack;)Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
	public void getUnlocalizedName(ItemStack itemStack, CallbackInfoReturnable<String> info) {
		switch (itemStack.getItemDamage()) {
			case WoodTypeHelper.CHERRY_WOOD_TYPE:
				info.setReturnValue(super.getUnlocalizedName() + ".cherry");
				break;
			case WoodTypeHelper.ACACIA_WOOD_TYPE:
				info.setReturnValue(super.getUnlocalizedName() + ".acacia");
				break;
			case WoodTypeHelper.MAHOGANY_WOOD_TYPE:
				info.setReturnValue(super.getUnlocalizedName() + ".mahogany");
				break;
			case WoodTypeHelper.MANGROVE_WOOD_TYPE:
				info.setReturnValue(super.getUnlocalizedName() + ".mangrove");
				break;
		}
	}
}
