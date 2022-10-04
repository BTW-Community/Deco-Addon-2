package deco.block.mixins;

import btw.block.blocks.SidingAndCornerAndDecorativeBlock;
import btw.block.blocks.WoodSidingAndCornerAndDecorativeBlock;
import btw.item.BTWItems;
import btw.item.blockitems.WoodSidingDecorativeStubBlockItem;
import deco.block.DecoBlockIDs;
import deco.block.DecoBlocks;
import deco.block.util.WoodTypeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.StepSound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(WoodSidingAndCornerAndDecorativeBlock.class)
public class WoodSidingAndCornerBlockMixin extends SidingAndCornerAndDecorativeBlock {
	public WoodSidingAndCornerBlockMixin(int blockID, Material material, String textureName, float hardness, float resistance, StepSound stepSound,
			String name) {
		super(blockID, material, textureName, hardness, resistance, stepSound, name);
	}
	
	@Inject(method = "getWoodTypeFromBlockID(I)I", at = @At("HEAD"), remap = false, cancellable = true)
	public void getWoodTypeFromBlockID(int blockID, CallbackInfoReturnable<Integer> info) {
		switch (blockID) {
			case DecoBlockIDs.CHERRY_SIDING_AND_CORNER_ID:
				info.setReturnValue(WoodTypeHelper.CHERRY_WOOD_TYPE);
				break;
			case DecoBlockIDs.ACACIA_SIDING_AND_CORNER_ID:
				info.setReturnValue(WoodTypeHelper.ACACIA_WOOD_TYPE);
				break;
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "renderBlockAsItem(Lnet/minecraft/src/RenderBlocks;IF)V",
			at = @At(
					value = "INVOKE",
					target = "Lbtw/block/blocks/WoodSidingAndCornerAndDecorativeBlock;" +
							"renderBenchInvBlock(Lnet/minecraft/src/RenderBlocks;Lnet/minecraft/src/Block;I)V",
					remap = false),
			locals = LocalCapture.CAPTURE_FAILHARD,
			cancellable = true)
	public void renderDecoBench(RenderBlocks render, int itemDamage, float brightness,
			CallbackInfo info,
			int subtype, Block block, int itemType, int woodType) {
		if (blockID == BTWItems.woodSidingDecorativeStubID) {
			if (woodType >= 5) {
				switch (woodType) {
					case WoodTypeHelper.CHERRY_WOOD_TYPE:
						block = DecoBlocks.cherrySidingAndCorner;
						break;
					case WoodTypeHelper.ACACIA_WOOD_TYPE:
						block = DecoBlocks.acaciaSidingAndCorner;
						break;
				}
				
				this.renderBenchInvBlock(render, block, SUBTYPE_BENCH);
				info.cancel();
			}
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "renderBlockAsItem(Lnet/minecraft/src/RenderBlocks;IF)V",
			at = @At(
					value = "INVOKE",
					target = "Lbtw/block/blocks/WoodSidingAndCornerAndDecorativeBlock;" +
							"renderFenceInvBlock(Lnet/minecraft/src/RenderBlocks;Lnet/minecraft/src/Block;I)V",
					remap = false),
			locals = LocalCapture.CAPTURE_FAILHARD,
			cancellable = true)
	public void renderDecoFence(RenderBlocks render, int itemDamage, float brightness,
			CallbackInfo info,
			int subtype, Block block, int itemType, int woodType) {
		if (blockID == BTWItems.woodSidingDecorativeStubID) {
			if (woodType >= 5) {
				switch (woodType) {
					case WoodTypeHelper.CHERRY_WOOD_TYPE:
						block = DecoBlocks.cherrySidingAndCorner;
						break;
					case WoodTypeHelper.ACACIA_WOOD_TYPE:
						block = DecoBlocks.acaciaSidingAndCorner;
						break;
				}
				
				this.renderFenceInvBlock(render, block, SUBTYPE_FENCE);
				info.cancel();
			}
		}
	}
}
