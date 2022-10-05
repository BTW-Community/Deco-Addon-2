package deco.block.mixins;

import btw.block.blocks.SidingAndCornerBlock;
import btw.client.render.util.RenderUtils;
import deco.block.DecoBlocks;
import deco.block.util.WoodTypeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Block;
import net.minecraft.src.Icon;
import net.minecraft.src.Material;
import net.minecraft.src.RenderBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SidingAndCornerBlock.class)
public class SidingAndCornerBlockMixin extends Block {
	protected SidingAndCornerBlockMixin(int blockID, Material metadata) {
		super(blockID, metadata);
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
			Icon woodTexture = Block.blocksList[WoodTypeHelper.woodTypeToSidingIDMap.get(itemDamage)].blockIcon;
			RenderUtils.renderInvBlockWithTexture(renderBlocks, this, -0.5F, -0.5F, -0.5F, woodTexture);
			info.cancel();
		}
	}
}
