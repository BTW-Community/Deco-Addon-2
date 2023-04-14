package deco.block.mixins;

import btw.block.BTWBlocks;
import btw.block.blocks.LogBlock;
import btw.block.blocks.WorkStumpBlock;
import btw.client.fx.BTWEffectManager;
import btw.item.BTWItems;
import btw.item.util.ItemUtils;
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
public abstract class WorkStumpBlockMixin extends Block {
	protected WorkStumpBlockMixin(int blockID, Material material) {
		super(blockID, material);
	}
	
	@Inject(method = "convertBlock", at = @At("HEAD"), cancellable = true, remap = false)
	public void convertBlock(ItemStack stack, World world, int x, int y, int z, int side, CallbackInfoReturnable<Boolean> info) {
		int metadata = world.getBlockMetadata(x, y, z);
		
		int chewedLogID = LogBlock.chewedLogArray[metadata & 3].blockID;
		
		if (!isFinishedWorkStump(metadata) && isWorkStumpItemConversionTool(stack, world, x, y, z)) {
			world.playAuxSFX(BTWEffectManager.SHAFT_RIPPED_OFF_EFFECT_ID, x, y, z, 0);
			world.setBlockMetadataWithNotify(x, y, z, metadata & 7);
			info.setReturnValue(true);
			return;
		}
		
		int newMetadata = BTWBlocks.oakChewedLog.setIsStump(0);
		
		world.setBlockAndMetadataWithNotify(x, y, z, chewedLogID, newMetadata);
		
		int barkItemDamage = metadata >= 4 ? (metadata - 1) & 7 : metadata & 7;
		
		if (!world.isRemote) {
			ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(BTWItems.bark, 1, barkItemDamage), side);
		}
		
		info.setReturnValue(true);
	}
	
	@Inject(method = "getStackRetrievedByBlockDispenser", at = @At("HEAD"), cancellable = true)
	public void getStackRetrievedByBlockDispenser(World world, int x, int y, int z, CallbackInfoReturnable<ItemStack> info) {
		int metadata = world.getBlockMetadata(x, y, z);
		
		switch (metadata & 7) {
			case WoodTypeHelper.DARK_OAK_WORK_STUMP_TYPE:
				info.setReturnValue(new ItemStack(DecoBlocks.darkOakLog));
				break;
			case WoodTypeHelper.ACACIA_WORK_STUMP_TYPE:
				info.setReturnValue(new ItemStack(DecoBlocks.acaciaLog));
				break;
			case WoodTypeHelper.MAHOGANY_WORK_STUMP_TYPE:
				info.setReturnValue(new ItemStack(DecoBlocks.mahoganyLog));
				break;
			case WoodTypeHelper.MANGROVE_WORK_STUMP_TYPE:
				info.setReturnValue(new ItemStack(DecoBlocks.mangroveLog));
				break;
		}
	}
	
	@Shadow(remap = false)
	public boolean isWorkStumpItemConversionTool(ItemStack stack, World world, int x, int y, int z) {return false;}
	
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
