package deco.block.mixins;

import btw.block.blocks.MouldingAndDecorativeBlock;
import btw.block.blocks.WoodMouldingAndDecorativeBlock;
import btw.client.render.util.RenderUtils;
import btw.item.BTWItems;
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

@Mixin(WoodMouldingAndDecorativeBlock.class)
public class WoodMouldingBlockMixin extends MouldingAndDecorativeBlock {
	public WoodMouldingBlockMixin(int iBlockID, Material material, String sTextureName, String sColumnSideTextureName, int iMatchingCornerBlockID,
			float fHardness, float fResistance, StepSound stepSound, String name) {
		super(iBlockID, material, sTextureName, sColumnSideTextureName, iMatchingCornerBlockID, fHardness, fResistance, stepSound, name);
	}
	
	@Inject(method = "getWoodTypeFromBlockID(I)I", at = @At("HEAD"), remap = false, cancellable = true)
	public void getWoodTypeFromBlockID(int blockID, CallbackInfoReturnable<Integer> info) {
		// Reverse map lookup, returns first key to match value
		info.setReturnValue(WoodTypeHelper.woodTypeToMouldingIDMap.entrySet()
				.stream()
				.filter(entry -> Objects.equals(entry.getValue(), blockID))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList())
				.get(0));
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "renderBlockAsItem(Lnet/minecraft/src/RenderBlocks;IF)V",
			at = @At(
					value = "INVOKE",
					target = "Lbtw/client/render/util/RenderUtils;" +
							"renderInvBlockWithTexture(Lnet/minecraft/src/RenderBlocks;Lnet/minecraft/src/Block;FFFLnet/minecraft/src/Icon;)V",
					remap = false),
			cancellable = true)
	public void renderDecoWoodTexturesAsItem(RenderBlocks renderBlocks, int itemDamage, float brightness, CallbackInfo info) {
		if (itemDamage >= WoodTypeHelper.NUM_VANILLA_WOOD) {
			Icon woodTexture = Block.blocksList[WoodTypeHelper.woodTypeToMouldingIDMap.get(itemDamage)].blockIcon;
			RenderUtils.renderInvBlockWithTexture(renderBlocks, this, -0.5F, -0.5F, -0.5F, woodTexture);
			info.cancel();
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "renderBlockAsItem(Lnet/minecraft/src/RenderBlocks;IF)V",
			at = @At(
					value = "INVOKE",
					target = "Lbtw/block/blocks/WoodMouldingAndDecorativeBlock;" +
							"renderDecorativeInvBlock(Lnet/minecraft/src/RenderBlocks;Lnet/minecraft/src/Block;IF)V",
					remap = false),
			locals = LocalCapture.CAPTURE_FAILHARD,
			cancellable = true)
	public void renderDecoMouldingDecorative(RenderBlocks render, int itemDamage, float brightness,
			CallbackInfo info,
			Block block, int itemType, int woodType) {
		if (blockID == BTWItems.woodMouldingDecorativeStubID) {
			if (woodType >= WoodTypeHelper.NUM_VANILLA_WOOD) {
				block = Block.blocksList[WoodTypeHelper.woodTypeToMouldingIDMap.get(woodType)];
				this.renderDecorativeInvBlock(render, block, itemDamage, brightness);
				info.cancel();
			}
		}
	}
}
