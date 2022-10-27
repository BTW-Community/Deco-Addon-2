package deco.item.mixins;

import btw.item.items.ShearsItem;
import deco.block.DecoBlockIDs;
import net.minecraft.src.Block;
import net.minecraft.src.ItemShears;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Mixin(ShearsItem.class)
public abstract class ShearsItemMixin extends ItemShears {
	private static ArrayList<Integer> harvestableBlocks = new ArrayList<>();
	private static ArrayList<Integer> efficientBlocks = new ArrayList<>();
	
	static {
		harvestableBlocks.add(DecoBlockIDs.CARPET_ID);
		harvestableBlocks.add(DecoBlockIDs.AUTUMN_LEAVES_ID);
		harvestableBlocks.add(DecoBlockIDs.CHERRY_LEAVES_ID);
		harvestableBlocks.add(DecoBlockIDs.ACACIA_LEAVES_ID);
		harvestableBlocks.add(DecoBlockIDs.MAHOGANY_LEAVES_ID);
		harvestableBlocks.add(DecoBlockIDs.MANGROVE_LEAVES_ID);
		harvestableBlocks.add(DecoBlockIDs.HAZEL_LEAVES_ID);
		
		efficientBlocks.addAll(harvestableBlocks);
	}
	
	public ShearsItemMixin(int itemID) {
		super(itemID);
	}
	
	@Inject(method = "canHarvestBlock", at = @At("HEAD"), cancellable = true)
	public void canHarvestBlock(ItemStack stack, World world, Block block, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
		if (harvestableBlocks.contains(block.blockID)) {
			cir.setReturnValue(true);
		}
	}
	
	@Inject(method = "isEfficientVsBlock", at = @At("TAIL"), cancellable = true)
	public void isEfficientVsBlock(ItemStack stack, World world, Block block, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
		if (efficientBlocks.contains(block.blockID)) {
			cir.setReturnValue(true);
		}
	}
}
