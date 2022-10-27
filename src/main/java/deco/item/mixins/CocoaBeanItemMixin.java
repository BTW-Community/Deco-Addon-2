package deco.item.mixins;

import btw.item.items.CocoaBeanItem;
import btw.item.items.FoodItem;
import btw.world.util.BlockPos;
import deco.block.util.BlockInterface;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CocoaBeanItem.class)
public abstract class CocoaBeanItemMixin extends FoodItem {
	public CocoaBeanItemMixin(int itemID, int hungerHealed, float saturationModifier, boolean isWolfMeat, String itemName) {
		super(itemID, hungerHealed, saturationModifier, isWolfMeat, itemName);
	}
	
	@Inject(method = "attemptPlaceOn", at = @At("RETURN"), remap = false, cancellable = true)
	public void attemptPlaceOn(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValue()) {
			world.playSound(x, y, z, "dig.wood", 1.0F, .75F);
		}
		else {
			int blockID = world.getBlockId(x, y, z);
			
			if (side >= 2 && blockID > 0 && ((BlockInterface) Block.blocksList[blockID]).canCocoaBeansGrowOnBlock(world, x, y, z)) {
				BlockPos targetPos = new BlockPos(x, y, z, side);
				
				if (world.isAirBlock(targetPos.x, targetPos.y, targetPos.z)) {
					int cocoaBlockID = Block.cocoaPlant.blockID;
					int cocoaMetadata = Block.blocksList[cocoaBlockID].onBlockPlaced(world, targetPos.x, targetPos.y, targetPos.z, side, hitX, hitY, hitZ, 0);
					
					world.setBlockAndMetadataWithNotify(targetPos.x, targetPos.y, targetPos.z, cocoaBlockID, cocoaMetadata);
					world.playSound(x, y, z, "dig.wood", 1.0F, .75F);
					cir.setReturnValue(true);
				}
			}
		}
	}
}
