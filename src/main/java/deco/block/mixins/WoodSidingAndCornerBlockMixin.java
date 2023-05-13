package deco.block.mixins;

import btw.block.BTWBlocks;
import btw.block.blocks.SidingAndCornerAndDecorativeBlock;
import btw.block.blocks.WoodSidingAndCornerAndDecorativeBlock;
import btw.client.render.util.RenderUtils;
import btw.item.BTWItems;
import btw.item.blockitems.WoodSidingDecorativeStubBlockItem;
import deco.block.DecoBlockIDs;
import deco.block.DecoBlocks;
import deco.block.util.WoodTypeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Mixin(WoodSidingAndCornerAndDecorativeBlock.class)
public abstract class WoodSidingAndCornerBlockMixin extends SidingAndCornerAndDecorativeBlock {
	public WoodSidingAndCornerBlockMixin(int blockID, Material material, String textureName, float hardness, float resistance, StepSound stepSound,
			String name) {
		super(blockID, material, textureName, hardness, resistance, stepSound, name);
	}
	
	@Inject(method = "getWoodTypeFromBlockID(I)I", at = @At("HEAD"), remap = false, cancellable = true)
	public void getWoodTypeFromBlockID(int blockID, CallbackInfoReturnable<Integer> info) {
		if (WoodTypeHelper.sidingIDToWoodTypeMap.containsKey(blockID)) {
			info.setReturnValue(WoodTypeHelper.sidingIDToWoodTypeMap.get(blockID));
		}
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "renderBlockAsItem(Lnet/minecraft/src/RenderBlocks;IF)V",
			at = @At(
					value = "INVOKE",
					target = "Lbtw/block/blocks/WoodSidingAndCornerAndDecorativeBlock;" +
							"renderBenchInvBlock(Lnet/minecraft/src/RenderBlocks;Lnet/minecraft/src/Block;I)V"
			),
			locals = LocalCapture.CAPTURE_FAILHARD,
			cancellable = true)
	public void renderDecoBench(RenderBlocks render, int itemDamage, float brightness,
			CallbackInfo info,
			int subtype, Block block, int itemType, int woodType) {
		if (blockID == BTWItems.woodSidingDecorativeStubID) {
			if (woodType >= WoodTypeHelper.NUM_VANILLA_WOOD) {
				block = Block.blocksList[WoodTypeHelper.woodTypeToSidingIDMap.get(woodType)];
				this.renderBenchInvBlock(render, block, SUBTYPE_BENCH);
				info.cancel();
			}
		}
		else if (blockID == BTWItems.woodSidingStubID || blockID == BTWItems.woodCornerStubID) {
			if (subtype >= WoodTypeHelper.NUM_VANILLA_WOOD) {
				RenderUtils.renderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, Block.blocksList[WoodTypeHelper.woodTypeToSidingIDMap.get(subtype)].blockIcon);
				info.cancel();
			}
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "renderBlockAsItem(Lnet/minecraft/src/RenderBlocks;IF)V",
			at = @At(
					value = "INVOKE",
					target = "Lbtw/block/blocks/WoodSidingAndCornerAndDecorativeBlock;" +
							"renderFenceInvBlock(Lnet/minecraft/src/RenderBlocks;Lnet/minecraft/src/Block;I)V"
			),
			locals = LocalCapture.CAPTURE_FAILHARD,
			cancellable = true)
	public void renderDecoFence(RenderBlocks render, int itemDamage, float brightness,
			CallbackInfo info,
			int subtype, Block block, int itemType, int woodType) {
		if (blockID == BTWItems.woodSidingDecorativeStubID) {
			if (woodType >= WoodTypeHelper.NUM_VANILLA_WOOD) {
				block = Block.blocksList[WoodTypeHelper.woodTypeToSidingIDMap.get(woodType)];
				this.renderFenceInvBlock(render, block, SUBTYPE_FENCE);
				info.cancel();
			}
		}
		else if (blockID == BTWItems.woodSidingStubID || blockID == BTWItems.woodCornerStubID) {
			if (subtype >= WoodTypeHelper.NUM_VANILLA_WOOD) {
				RenderUtils.renderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, Block.blocksList[WoodTypeHelper.woodTypeToSidingIDMap.get(subtype)].blockIcon);
				info.cancel();
			}
		}
	}
}
