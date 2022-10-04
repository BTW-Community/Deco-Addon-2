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

@Mixin(WoodMouldingAndDecorativeBlock.class)
public class WoodMouldingBlockMixin extends MouldingAndDecorativeBlock {
	public WoodMouldingBlockMixin(int iBlockID, Material material, String sTextureName, String sColumnSideTextureName, int iMatchingCornerBlockID,
			float fHardness, float fResistance, StepSound stepSound, String name) {
		super(iBlockID, material, sTextureName, sColumnSideTextureName, iMatchingCornerBlockID, fHardness, fResistance, stepSound, name);
	}
	
	@Inject(method = "getWoodTypeFromBlockID(I)I", at = @At("HEAD"), remap = false, cancellable = true)
	public void getWoodTypeFromBlockID(int blockID, CallbackInfoReturnable<Integer> info) {
		switch (blockID) {
			case DecoBlockIDs.CHERRY_MOULDING_ID:
				info.setReturnValue(WoodTypeHelper.CHERRY_WOOD_TYPE);
				break;
			case DecoBlockIDs.ACACIA_MOULDING_ID:
				info.setReturnValue(WoodTypeHelper.ACACIA_WOOD_TYPE);
				break;
			case DecoBlockIDs.MAHOGANY_MOULDING_ID:
				info.setReturnValue(WoodTypeHelper.MAHOGANY_WOOD_TYPE);
				break;
			case DecoBlockIDs.MANGROVE_MOULDING_ID:
				info.setReturnValue(WoodTypeHelper.MANGROVE_WOOD_TYPE);
				break;
		}
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
		if (itemDamage >= 5) {
			Icon woodTexture = this.blockIcon;
			
			switch (itemDamage) {
				case WoodTypeHelper.CHERRY_WOOD_TYPE:
					woodTexture = DecoBlocks.cherryMoulding.blockIcon;
					break;
				case WoodTypeHelper.ACACIA_WOOD_TYPE:
					woodTexture = DecoBlocks.acaciaMoulding.blockIcon;
					break;
				case WoodTypeHelper.MAHOGANY_WOOD_TYPE:
					woodTexture = DecoBlocks.mahoganyMoulding.blockIcon;
					break;
				case WoodTypeHelper.MANGROVE_WOOD_TYPE:
					woodTexture = DecoBlocks.mangroveMoulding.blockIcon;
					break;
			}
			
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
			if (woodType >= 5) {
				switch (woodType) {
					case WoodTypeHelper.CHERRY_WOOD_TYPE:
						block = DecoBlocks.cherryMoulding;
						break;
					case WoodTypeHelper.ACACIA_WOOD_TYPE:
						block = DecoBlocks.acaciaMoulding;
						break;
					case WoodTypeHelper.MAHOGANY_WOOD_TYPE:
						block = DecoBlocks.mahoganyMoulding;
						break;
					case WoodTypeHelper.MANGROVE_WOOD_TYPE:
						block = DecoBlocks.mangroveMoulding;
						break;
				}
				
				this.renderDecorativeInvBlock(render, block, itemDamage, brightness);
				info.cancel();
			}
		}
	}
}
