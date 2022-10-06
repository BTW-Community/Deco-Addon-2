package deco.block.mixins;

import btw.block.blocks.WorkStumpBlock;
import deco.block.DecoBlocks;
import deco.block.util.WoodTypeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorkStumpBlock.class)
public class WorkStumpBlockMixin extends Block {
	protected WorkStumpBlockMixin(int blockID, Material material) {
		super(blockID, material);
	}
	
	@Inject(method = "getStackRetrievedByBlockDispenser", at = @At("HEAD"))
	public void getStackRetrievedByBlockDispenser(World world, int x, int y, int z, CallbackInfoReturnable<ItemStack> info) {
		int metadata = world.getBlockMetadata(x, y, z);
		
		switch ((metadata & 7) + 1) {
			case WoodTypeHelper.CHERRY_WOOD_TYPE:
				info.setReturnValue(new ItemStack(DecoBlocks.cherryLog));
				break;
			case WoodTypeHelper.ACACIA_WOOD_TYPE:
				info.setReturnValue(new ItemStack(DecoBlocks.acaciaLog));
				break;
			case WoodTypeHelper.MAHOGANY_WOOD_TYPE:
				info.setReturnValue(new ItemStack(DecoBlocks.mahoganyLog));
				break;
			case WoodTypeHelper.MANGROVE_WOOD_TYPE:
				info.setReturnValue(new ItemStack(DecoBlocks.mangroveLog));
				break;
		}
	}
	
	@Shadow(remap = false)
	private boolean isFinishedWorkStump(int metadata) {return false;}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	public Icon[] extraSideIcons;
	@Environment(EnvType.CLIENT)
	public Icon[] extraTopIcons;
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "registerIcons", at = @At("TAIL"))
	public void registerIcons(IconRegister register, CallbackInfo info) {
		// Cherry, acacia, mahogany, mangrove
		extraSideIcons = new Icon[4];
		extraTopIcons = new Icon[4];
		
		for (int i = 0; i < extraSideIcons.length; i++) {
			this.extraSideIcons[i] = register.registerIcon("decoBlockWorkStump" + WoodTypeHelper.woodNamesCapital[i + WoodTypeHelper.NUM_VANILLA_WOOD]);
			this.extraTopIcons[i] = register.registerIcon("decoBlockTrunk" + WoodTypeHelper.woodNamesCapital[i + WoodTypeHelper.NUM_VANILLA_WOOD] + "_top");
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "getIcon", at = @At("HEAD"), cancellable = true)
	public void getIcon(int side, int metadata, CallbackInfoReturnable<Icon> info) {
		int workStumpType = metadata & 7;
		
		if (workStumpType >= WoodTypeHelper.NUM_VANILLA_WOOD - 1) {
			if (side > 1) {
				info.setReturnValue(this.extraSideIcons[workStumpType - WoodTypeHelper.NUM_VANILLA_WOOD + 1]);
			}
			else if (side == 0 || !this.isFinishedWorkStump(metadata)) {
				info.setReturnValue(this.extraTopIcons[workStumpType - WoodTypeHelper.NUM_VANILLA_WOOD + 1]);
			}
		}
	}
}
