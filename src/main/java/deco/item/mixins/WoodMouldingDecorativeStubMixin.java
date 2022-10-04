package deco.item.mixins;

import btw.item.blockitems.WoodMouldingDecorativeStubBlockItem;
import deco.block.DecoBlocks;
import deco.block.util.WoodTypeHelper;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WoodMouldingDecorativeStubBlockItem.class)
public class WoodMouldingDecorativeStubMixin extends ItemBlock {
	public WoodMouldingDecorativeStubMixin(int itemID) {
		super(itemID);
	}
	
	@Inject(method = "getBlockIDToPlace(IIFFF)I", at = @At("HEAD"), cancellable = true, remap = false)
	public void getBlockIDToPlace(int itemDamage, int facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Integer> info) {
		int woodType = WoodMouldingDecorativeStubBlockItem.getWoodType(itemDamage);
		
		switch (woodType) {
			case WoodTypeHelper.CHERRY_WOOD_TYPE:
				info.setReturnValue(DecoBlocks.cherryMoulding.blockID);
				break;
			case WoodTypeHelper.ACACIA_WOOD_TYPE:
				info.setReturnValue(DecoBlocks.acaciaMoulding.blockID);
				break;
			case WoodTypeHelper.MAHOGANY_WOOD_TYPE:
				info.setReturnValue(DecoBlocks.mahoganyMoulding.blockID);
		}
	}
	
	@Inject(method = "getUnlocalizedName(Lnet/minecraft/src/ItemStack;)Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
	public void getUnlocalizedName(ItemStack itemStack, CallbackInfoReturnable<String> info) {
		int itemDamage = itemStack.getItemDamage();
		int woodType = WoodMouldingDecorativeStubBlockItem.getWoodType(itemDamage);
		int blockType = WoodMouldingDecorativeStubBlockItem.getBlockType(itemDamage);
		
		if (woodType >= 5) {
			String woodName = "";
			
			switch (woodType) {
				case WoodTypeHelper.CHERRY_WOOD_TYPE:
					woodName = "cherry";
					break;
				case WoodTypeHelper.ACACIA_WOOD_TYPE:
					woodName = "acacia";
					break;
				case WoodTypeHelper.MAHOGANY_WOOD_TYPE:
					woodName = "mahogany";
					break;
			}
			
			String typeName = "";
			
			switch (blockType) {
				case WoodMouldingDecorativeStubBlockItem.TYPE_COLUMN:
					typeName = "column";
					break;
				case WoodMouldingDecorativeStubBlockItem.TYPE_PEDESTAL:
					typeName = "pedestal";
					break;
				case WoodMouldingDecorativeStubBlockItem.TYPE_TABLE:
					typeName = "table";
					break;
			}
			
			info.setReturnValue(super.getUnlocalizedName() + "." + woodName + "." + typeName);
		}
	}
}
